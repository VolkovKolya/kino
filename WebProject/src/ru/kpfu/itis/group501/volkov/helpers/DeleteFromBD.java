package ru.kpfu.itis.group501.volkov.helpers;

import ru.kpfu.itis.group501.volkov.configs.BdSingleton;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by volkov on 27.10.2016.
 */
public class DeleteFromBD {
        public void delete (String request,String parametr){
            if (BdSingleton.getConnection() != null && !parametr.equals("")) {
                try {
                    PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                    st= BdSingleton.getConnection().prepareStatement(request);
                    st.setInt(1,Integer.parseInt(parametr));
                    st.executeUpdate();
                    st.close();
                } catch (SQLException sql) {
                    sql.printStackTrace();
                }
            }
        }

    public void deleteString (String request,String parametr){
        if (BdSingleton.getConnection() != null && !parametr.equals("")) {
            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                st= BdSingleton.getConnection().prepareStatement(request);
                st.setString(1,parametr);
                st.executeUpdate();
                st.close();
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
    }
}
