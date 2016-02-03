package com.qsci.database.metadata.test;

import com.qsci.database.metadata.entities.model.Table;
import com.qsci.database.metadata.test.entities.PostgresEntities;
import com.qsci.database.metadata.test.entities.SQLiteEntities;
import com.qsci.database.metadata.test.entities.TestEntities;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class TestManager {

    public static Connection getConnection(String testConfigPath) throws IOException, ClassNotFoundException, SQLException {
        Connection connection = null;
        Properties testConfig = new Properties();
        testConfig.load(new FileInputStream(testConfigPath));
        String driver = testConfig.getProperty("driver");
        Class.forName(driver);
        if (driver.equals("org.sqlite.JDBC")) {
            connection = DriverManager.getConnection(testConfig.getProperty("db.testUrl"));
        } else if (driver.equals("org.postgresql.Driver")) {
            String url = testConfig.getProperty("db.url");
            String login = testConfig.getProperty("db.login");
            String password = testConfig.getProperty("db.password");
            connection = DriverManager.getConnection(url, login, password);
        }
        return connection;
    }

    public static TestEntities getEntities(Connection connection, String testConfigPath) throws IOException, SQLException, ClassNotFoundException {
        TestEntities model = null;
        Properties testConfig = new Properties();
        testConfig.load(new FileInputStream(testConfigPath));
        String driver = testConfig.getProperty("driver");
        String dumpUrl = testConfig.getProperty("db.dumpUrl");
        if (driver.equals("org.sqlite.JDBC")) {
            model = new SQLiteEntities(connection, dumpUrl);
        } else if (driver.equals("org.postgresql.Driver")) {
            /*not implemented yet*/
            /*model = new PostgresEntities(connection, dumpUrl);*/
        }
        return model;
    }

    public static File getActualFile(String testConfigPath) throws IOException {
        Properties testConfig = new Properties();
        testConfig.load(new FileInputStream(new File(testConfigPath)));
        String testBaseUrl = testConfig.getProperty("db.testUrl");
        return new File(testBaseUrl);
    }

}
