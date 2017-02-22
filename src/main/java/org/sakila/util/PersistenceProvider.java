package org.sakila.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersistenceProvider {

    private final static Logger logger = LoggerFactory.getLogger(PersistenceProvider.class);

    private Connection conn;
    private static PersistenceProvider persistenceProvider;

    public static PersistenceProvider getInstance(){
        if(persistenceProvider == null){
            persistenceProvider = new PersistenceProvider();
        }
        return persistenceProvider;
    }

    private PersistenceProvider() {    // note more general exception

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String host = "192.168.1.2";
            String databaseName = "sakila";
            String userName = "sakila";
            String password = "sakila";
            String url = "jdbc:mysql://" + host + ":3306/" + databaseName;
            
            conn = DriverManager.getConnection(url,userName, password);
            //initDb();
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage(),e);
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }
    }

    public synchronized ResultSet query(String expression) throws SQLException {

        Statement st = null;
        ResultSet rs = null;

        st = conn.createStatement();
        rs = st.executeQuery(expression);
        return rs;
    }

    public synchronized void update(String expression) throws SQLException {

        logger.debug(expression);

        Statement st = null;

        st = conn.createStatement();    // statements

        int i = st.executeUpdate(expression);    // run the query

        if (i == -1) {
            logger.error("db error : " + expression);
        }

        st.close();
    }

    public static void dump(ResultSet rs) throws SQLException {

        ResultSetMetaData meta   = rs.getMetaData();
        int               colmax = meta.getColumnCount();
        int               i;
        Object            o = null;

        for (; rs.next(); ) {
            for (i = 0; i < colmax; ++i) {
                o = rs.getObject(i + 1);    // Is SQL the first column is indexed

                // with 1 not 0
                logger.debug (o.toString() + " ");
            }

            logger.debug(" ");
        }
    }                                       //void dump( ResultSet rs )



    public PreparedStatement prepareStatement(String sql){
        try {
            return conn.prepareStatement(sql);
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }


    public static void main(String[] args) {

        PersistenceProvider db = null;

        try {
            db = new PersistenceProvider();
        } catch (Exception ex1) {
            logger.error(ex1.getMessage(),ex1);   // could not start db

            return;                   // bye bye
        }
    }
}

