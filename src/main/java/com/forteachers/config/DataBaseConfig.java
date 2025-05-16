package com.forteachers.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataBaseConfig {

    private static HikariConfig config = new HikariConfig ();
    private static HikariDataSource ds;

    static {
        config.setJdbcUrl ("jdbc_url");
        config.setUsername ("forteachers");
        config.setPassword ("1234");
        ds = new HikariDataSource (config);
    }

    private void DataSource() {}

    public static Connection getConnection() throws SQLException{
        return ds.getConnection ();
    }
}
