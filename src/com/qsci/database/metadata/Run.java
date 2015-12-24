package com.qsci.database.metadata;

import com.qsci.database.metadata.metaDataEntityes.model.Field;
import com.qsci.database.metadata.metaDataEntityes.model.Table;
import com.qsci.database.metadata.transformerManagers.Manager;
import com.qsci.database.metadata.transformers.SQLiteTransformer;
import com.qsci.database.metadata.transformers.Transformer;
import com.qsci.database.metadata.userData.UserData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Run {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        logLn("!======================================!");
        logLn("DataBase Metadata Transformer v.0.1");
        logLn("!=======================================!");

        Manager manager = new Manager();
        Transformer transformer = manager.getTransformer(SQLiteTransformer.getDriverName());
        Class.forName(SQLiteTransformer.getDriverName());
        UserData userData = new UserData();
        userData.setUrl("JDBC:sqlite:personBase.sqlite");

        Connection connection = DriverManager.getConnection(userData.getUrl());
        List<Table> tables = transformer.getTables(connection);
        for (Table table : tables) {
            System.out.println(table);
            System.out.println(table.getPrimaryKey());
            for (Field field : table.getFields()) {
                System.out.println(field);
            }
            System.out.println(

            );
        }
            /*TO DO  index  | uniqueIndex | foreignKEYS*/
            /*TO DO to Fields get "autoincremented"*/
            /*TO DO add log4j and drop sout*/
    }


    private static void log(String text) {
        System.out.println(text);
    }


    private static void logLn(String text) {
        System.out.println(text);
    }


}

