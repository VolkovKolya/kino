package ru.kpfu.itis.group501.volkov.helpers;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import ru.kpfu.itis.group501.volkov.configs.Singleton;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by volkov on 12.10.2016.
 */
public class Helper {
    public static void render(HttpServletRequest request, HttpServletResponse response, String s, Map root)throws ServletException, IOException {
        Template tmpl = Singleton.getConfig(
                request.getServletContext()
        ).getTemplate(s);
        try {
            tmpl.process(root, response.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
