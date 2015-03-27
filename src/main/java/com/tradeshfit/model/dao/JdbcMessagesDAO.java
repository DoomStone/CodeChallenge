package com.tradeshfit.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JdbcMessagesDAO implements MessagesDAO {

    private DataSource dataSource;
    public JdbcMessagesDAO(){

    }
    @Autowired
    public JdbcMessagesDAO(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public void insert(String message, Date created) {
        String insertSql = "INSERT INTO recent (message, created) VALUES (?, ?)";
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);

            preparedStatement.setString(1, message);
            preparedStatement.setTimestamp(2, new Timestamp(created.getTime()));
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        } finally {
            if(connection != null){
                try{
                    connection.close();
                } catch (SQLException e) {
                    // Ignore
                }
            }
        }
    }

    public List<MessageDAO> getMessage(int limit) {
        List<MessageDAO> messages = new ArrayList<MessageDAO>();

        String sql = "SELECT * FROM recent ORDER BY id DESC LIMIT ?";
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, limit);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                messages.add(new MessageDAO(
                        resultSet.getInt("id"),
                        resultSet.getString("message"),
                        new java.util.Date(resultSet.getDate("created").getTime())));
            }
            preparedStatement.close();

        } catch (SQLException e){
            throw new RuntimeException(e);
        } finally {
            if(connection != null){
                try{
                    connection.close();
                } catch (SQLException e) {
                    // Ignore
                }
            }
        }

        return messages;
    }
}
