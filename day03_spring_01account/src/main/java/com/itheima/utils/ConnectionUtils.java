package com.itheima.utils;

import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtils {
  private ThreadLocal<Connection> tl =   new ThreadLocal<Connection>();
  private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public Connection getConnection(){
        Connection connection = null;
        try {
            connection = tl.get();
            if (connection==null){
                connection = dataSource.getConnection();
                tl.set(connection);
            }
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeConnection(){
        tl.remove();
    }
}
