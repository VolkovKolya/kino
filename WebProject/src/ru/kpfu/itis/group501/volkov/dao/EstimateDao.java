package ru.kpfu.itis.group501.volkov.dao;

import ru.kpfu.itis.group501.volkov.configs.BdSingleton;
import ru.kpfu.itis.group501.volkov.entities.Estimate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by volkov on 19.11.2016.
 */
public class EstimateDao implements  EstimateDaoInterface {
    @Override
    public void add(Estimate estimate) {
        if (find(estimate.getUser_id(),estimate.getMovie_id())!=null) {
            update(estimate);
        }
        else {
            if (BdSingleton.getConnection() != null && estimate != null) {
                String request = "INSERT INTO estimate (movie_id,user_id,estimate) VALUES (?,?,?) ";

                try {
                    PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                    st.setInt(1, Integer.parseInt(estimate.getMovie_id()));
                    st.setInt(2, Integer.parseInt(estimate.getUser_id()));
                    st.setInt(3, Integer.parseInt(estimate.getEstimate()));
                    st.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void update (Estimate estimate) {
        if (BdSingleton.getConnection() != null && estimate!= null) {
            String request = "Update  estimate set estimate=? where movie_id = ? and user_id=?  ";

            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                st.setInt(1, Integer.parseInt(estimate.getEstimate()));
                st.setInt(2, Integer.parseInt(estimate.getMovie_id()));
                st.setInt(3, Integer.parseInt(estimate.getUser_id()));
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public Estimate find(String user_id,String movie_id){
        if (BdSingleton.getConnection() != null && user_id!= null && movie_id!= null) {
            String request = "select * from  estimate where  movie_id = ? and user_id=?  ";

            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                st.setInt(1, Integer.parseInt(movie_id));
                st.setInt(2, Integer.parseInt(user_id));
                ResultSet resultSet = st.executeQuery();
                while (resultSet.next()) {
                    return new Estimate(resultSet.getString("id"),resultSet.getString("user_id"),resultSet.getString("movie_id"),
                            resultSet.getString("estimate") );
                }
                return null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    @Override
    public void delete(String user_id,String movie_id) {
        if (BdSingleton.getConnection() != null && user_id!= null && movie_id!= null) {
            String request = "Delete from estimate where movie_id = ? and user_id=?   ";

            try {
                PreparedStatement st = BdSingleton.getConnection().prepareStatement(request);
                st.setInt(1, Integer.parseInt(movie_id));
                st.setInt(2, Integer.parseInt(user_id));
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
