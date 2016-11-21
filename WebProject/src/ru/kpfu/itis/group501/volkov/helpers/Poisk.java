package ru.kpfu.itis.group501.volkov.helpers;

/**
 * Created by volkov on 18.11.2016.
 */
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.kpfu.itis.group501.volkov.configs.BdSingleton;

/**
 * Created by volkov on 27.10.2016.
 */
public class Poisk extends javax.servlet.http.HttpServlet {
    private Connection conn;


    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String q = request.getParameter("q");
        if (q != null && q!="") {
            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(
                        "select first_name,last_name from users where first_name LIKE ? or last_name LIKE ?"
                );
                st.setString(1, "%"+ q + "%");
                st.setString(2, "%"+ q + "%");
                ResultSet rs = st.executeQuery();
                List<String> names = new ArrayList<String>();
                JSONArray ja = new JSONArray();
                while (rs.next()) {
                    ja.put(rs.getString("first_name")+" "+rs.getString("last_name"));
                }





                JSONObject jo = new JSONObject();
                jo.put("result", ja);

                response.setContentType("text/json");
                request.setCharacterEncoding("UTF-8");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().println(jo.toString());

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}