package dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    static final Logger LOG = LoggerFactory.getLogger(ConnectionFactory.class);
    public static final String URL = "jdbc:hsqldb:hsql://localhost/testdb;ifexists=true";
    public static final String USER = "SA";
    public static final String PASS = "";

    private static ConnectionFactory connectionFactory;

    private Connection connection;

    private ConnectionFactory(){
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver" );
            connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        } catch (ClassNotFoundException e) {
           LOG.error("Couldn't find proper jdbc driver", e);
        }
    }

    public static ConnectionFactory getInstance(){
        if(connectionFactory == null) connectionFactory = new ConnectionFactory();

        return connectionFactory;
    }

    public Connection getConnection()
    {
        if (connection == null){
            try {
                Class.forName("org.hsqldb.jdbc.JDBCDriver" );
                connection = DriverManager.getConnection(URL, USER, PASS);
            } catch (SQLException ex) {
                throw new RuntimeException("Error connecting to the database", ex);
            } catch (ClassNotFoundException e) {
                LOG.error("Couldn't find proper jdbc driver", e);
            }
        }
        return connection;
    }

}
