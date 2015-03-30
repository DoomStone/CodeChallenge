package com.tradeshfit.dao;

import com.tradeshfit.model.dao.JdbcMessagesDAO;
import org.junit.Assert;
import org.junit.Test;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import static org.mockito.Mockito.*;

public class JdbcMessagesDAOTest {

    @Test
    public void testInsertUseDataSource(){
        try {
            DataSource dataSource = mock(DataSource.class);
            Connection connection = mock(Connection.class);
            PreparedStatement preparedStatement = mock(PreparedStatement.class);

            when(dataSource.getConnection()).thenReturn(connection);
            when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);

            JdbcMessagesDAO dao = new JdbcMessagesDAO(dataSource);
            Date date = new Date();
            dao.insert("Kasper", date);
            verify(dataSource, times(1)).getConnection();
            verify(connection, times(1)).prepareStatement(any(String.class));
            verify(preparedStatement, times(1)).setString(1, "Kasper");
            verify(preparedStatement, times(1)).executeUpdate();
            verify(preparedStatement, times(1)).close();
            verify(connection, times(1)).close();
        } catch (Exception exp){
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testGetMessageNoResults(){
        try {
            DataSource dataSource = mock(DataSource.class);
            Connection connection = mock(Connection.class);
            PreparedStatement preparedStatement = mock(PreparedStatement.class);
            ResultSet resultSet = mock(ResultSet.class);

            when(dataSource.getConnection()).thenReturn(connection);
            when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            when(resultSet.next()).thenReturn(false);

            JdbcMessagesDAO dao = new JdbcMessagesDAO(dataSource);
            dao.getMessage(10);
            verify(dataSource, times(1)).getConnection();
            verify(connection, times(1)).prepareStatement(any(String.class));
            verify(preparedStatement, times(1)).setInt(1, 10);
            verify(preparedStatement, times(1)).executeQuery();
            verify(preparedStatement, times(1)).close();
            verify(connection, times(1)).close();
        } catch (Exception exp){
            Assert.assertTrue(false);
        }
    }


    @Test
    public void testGetMessageResults(){
        try {
            DataSource dataSource = mock(DataSource.class);
            Connection connection = mock(Connection.class);
            PreparedStatement preparedStatement = mock(PreparedStatement.class);
            ResultSet resultSet = mock(ResultSet.class);

            when(dataSource.getConnection()).thenReturn(connection);
            when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            when(resultSet.next()).thenReturn(true).thenReturn(false);
            when(resultSet.getInt("id")).thenReturn(1);
            when(resultSet.getString("message")).thenReturn("Hello Kasper");
            Date date = new Date();
            when(resultSet.getDate("created")).thenReturn(new java.sql.Date(date.getTime()));

            JdbcMessagesDAO dao = new JdbcMessagesDAO(dataSource);
            dao.getMessage(10);
            verify(dataSource, times(1)).getConnection();
            verify(connection, times(1)).prepareStatement(any(String.class));
            verify(preparedStatement, times(1)).setInt(1, 10);
            verify(preparedStatement, times(1)).executeQuery();
            verify(preparedStatement, times(1)).close();
            verify(connection, times(1)).close();
            verify(resultSet, times(2)).next();
        } catch (Exception exp){
            Assert.assertTrue(false);
        }
    }
}
