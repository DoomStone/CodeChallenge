package com.tradeshfit.dao;

import com.tradeshfit.model.dao.JdbcMessagesDAO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import static org.mockito.Mockito.*;

public class JdbcMessagesDAOTest {
    @Mock
    private DataSource dataSource;
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private ResultSet resultSet;

    @Before
    public void setUp() throws SQLException{
        MockitoAnnotations.initMocks(this);
        when(this.dataSource.getConnection()).thenReturn(connection);
        when(this.connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
    }

    @Test
    public void testCreateInstance(){
        new JdbcMessagesDAO();
        new JdbcMessagesDAO(this.dataSource);
    }

    @Test
    public void testInsertUseDataSource()throws SQLException{

        JdbcMessagesDAO dao = new JdbcMessagesDAO(dataSource);
        Date date = new Date();
        dao.insert("Kasper", date);
        verify(dataSource, times(1)).getConnection();
        verify(connection, times(1)).prepareStatement(any(String.class));
        verify(preparedStatement, times(1)).setString(1, "Kasper");
        verify(preparedStatement, times(1)).executeUpdate();
        verify(preparedStatement, times(1)).close();
        verify(connection, times(1)).close();
    }

    @Test
    public void testGetMessageNoResults()throws SQLException{
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
    }


    @Test
    public void testGetMessageResults() throws SQLException{
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
    }

    @Test
    public void testErrorWhenInserting() throws SQLException{
        when(this.preparedStatement.executeUpdate()).thenThrow(new SQLException());
        JdbcMessagesDAO dao = new JdbcMessagesDAO(dataSource);
        Date date = new Date();

        try{
            dao.insert("Kasper", date);
        } catch (RuntimeException exp){
            Assert.assertTrue(true);
        }

        verify(dataSource, times(1)).getConnection();
        verify(connection, times(1)).prepareStatement(any(String.class));
        verify(preparedStatement, times(1)).setString(1, "Kasper");
        verify(preparedStatement, times(1)).executeUpdate();
        verify(connection, times(1)).close();
    }

    @Test
    public void testInsertUnableToOpenConnection() throws SQLException{
        when(this.dataSource.getConnection()).thenThrow(new SQLException());
        JdbcMessagesDAO dao = new JdbcMessagesDAO(dataSource);
        Date date = new Date();

        try{
            dao.insert("Kasper", date);
        } catch (RuntimeException exp){
            Assert.assertTrue(true);
        }

        verify(dataSource, times(1)).getConnection();
    }


    @Test
    public void testErrorWhenGettingMessageResults() throws SQLException{
        when(preparedStatement.executeQuery()).thenThrow(new SQLException());

        JdbcMessagesDAO dao = new JdbcMessagesDAO(dataSource);
        try{
            dao.getMessage(10);
        } catch (RuntimeException exp){
            Assert.assertTrue(true);
        }
        verify(dataSource, times(1)).getConnection();
        verify(connection, times(1)).prepareStatement(any(String.class));
        verify(preparedStatement, times(1)).setInt(1, 10);
        verify(preparedStatement, times(1)).executeQuery();
        verify(connection, times(1)).close();
        verify(resultSet, times(0)).next();
    }
}
