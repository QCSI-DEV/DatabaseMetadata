package com.qsci.database.metadata;

import com.qsci.database.metadata.entities.SQLiteEntities;
import com.qsci.database.metadata.entities.TestEntities;
import com.qsci.database.metadata.exceptions.NoConnectionExceptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class TestManager {
    public static Connection getConnection(String testConfigPath) throws IOException, ClassNotFoundException, SQLException {
        Connection connection = null;
        Properties testConfig = new Properties();
        testConfig.load(new FileInputStream(testConfigPath));
        String driver = testConfig.getProperty("driver");
        Class.forName(driver);
        if (driver.equals("org.sqlite.JDBC")) {
            String url = "JDBC:sqlite:" + testConfig.getProperty("db.testUrl");
            connection = DriverManager.getConnection(url);
            return connection;
        } else if (driver.equals("org.postgresql.Driver")) {
            String url = testConfig.getProperty("db.testUrl");
            String login = testConfig.getProperty("db.login");
            String password = testConfig.getProperty("db.password");
            connection = DriverManager.getConnection(url, login, password);
            return connection;
        }
        String message = "cant get connection to: " + driver;
        throw new NoConnectionExceptions(message);
    }

    public static TestEntities getEntities(Connection connection, String testConfigPath) throws Exception {
        TestEntities model = null;
        Properties testConfig = new Properties();
        testConfig.load(new FileInputStream(testConfigPath));
        String driver = testConfig.getProperty("driver");
        String dumpUrl = testConfig.getProperty("db.dumpUrl");
        if (driver.equals("org.sqlite.JDBC")) {
            model = new SQLiteEntities(connection, dumpUrl);
        } else if (driver.equals("org.postgresql.Driver")) {
            /*TODO not implemented yet*/
        }
        return model;
    }

    public static File getDumpRecoveryFile(String testConfigPath) throws IOException {
        Properties testConfig = new Properties();
        testConfig.load(new FileInputStream(new File(testConfigPath)));
        String testBaseUrl = testConfig.getProperty("db.testUrl");
        return new File(testBaseUrl);
    }

}
