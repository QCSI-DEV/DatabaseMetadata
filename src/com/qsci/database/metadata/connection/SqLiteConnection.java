package com.qsci.database.metadata.connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.DriverManager;

public class SqLiteConnection extends AbstractConnection {
    public SqLiteConnection() {
        userData =setUserData();
    }

    final String pathToDriver = "org.sqlite.JDBC";

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(pathToDriver);
        Connection connection = DriverManager.getConnection(userData.getPathToDb());
        return connection;
    }


    @Override
     UserCredentials setUserData() {
        UserCredentials userDataWrapper = new UserCredentials();
        System.out.println("Set path to SQLite data base:");
        Scanner in = new Scanner(System.in);
        userDataWrapper.setPathToDb(in.next());
        /*TO DO exceptions*/
        /*TO DO repeater > if you make some wrong*/
        return userDataWrapper;
    }
}
