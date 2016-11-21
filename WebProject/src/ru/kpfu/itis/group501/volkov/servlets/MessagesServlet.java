package ru.kpfu.itis.group501.volkov.servlets;

        import ru.kpfu.itis.group501.volkov.entities.Message;
        import ru.kpfu.itis.group501.volkov.entities.User;
        import ru.kpfu.itis.group501.volkov.helpers.Helper;
        import ru.kpfu.itis.group501.volkov.services.MessageService;
        import ru.kpfu.itis.group501.volkov.services.UserService;

        import javax.servlet.ServletException;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

/**
 * Created by volkov on 18.10.2016.
 */
public class MessagesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MessageService ms = new MessageService();


        UserService us = new UserService();

        User user = (User)request.getSession().getAttribute("current_user");
        Map<String, Object> root = new HashMap<>();
        root.put("user", user);
        root.put("err", request.getParameter("err"));

        List<Message> messages = new ArrayList<>();
        messages = ms.findOneId(user.getId());
        if (messages != null) {
            for (Message c : messages) {
                User u = us.findId(c.getFrom_user_id());
                c.setUserName(u.getFirst_Name() + " " + u.getLast_Name());
                if (c.getText().length()>55) {
                    c.setText(c.getText().substring(0, 55) + "...");
                }
                if (u.getId().equals(user.getId())){
                    c.setUser(us.findId(c.getUser_id()));
                }
                else c.setUser(u);
            }
        }

        root.put("message", messages);


        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Helper.render(request,response,"messages.ftl",root);
    }
}
