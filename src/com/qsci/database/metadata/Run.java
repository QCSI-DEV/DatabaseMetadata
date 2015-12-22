package com.qsci.database.metadata;

import com.qsci.database.metadata.exceptions.UnknownTransformerException;
import com.qsci.database.metadata.exceptions.WrongDataForConnectException;
import com.qsci.database.metadata.metaDataEntityes.constraints.ForeignKey;
import com.qsci.database.metadata.metaDataEntityes.indexes.Index;
import com.qsci.database.metadata.metaDataEntityes.indexes.UniqueIndex;
import com.qsci.database.metadata.metaDataEntityes.model.DefaultValue;
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
import java.util.Scanner;

public class Run {
    public static void main(String[] args) throws UnknownTransformerException, WrongDataForConnectException, SQLException, ClassNotFoundException {

        logLn("!======================================!");
        logLn("DataBase Metadata Transformer v.0.1");
        logLn("!=======================================!");

        Manager manager = new Manager();
        Transformer transformer = manager.getTransformer(SQLiteTransformer.getDriverName());
        Class.forName(SQLiteTransformer.getDriverName());
        UserData userData = new UserData();
        userData.setUrl("JDBC:sqlite:personBase");

        Connection connection = DriverManager.getConnection(userData.getUrl());
        List<Table> tables = transformer.getTables(connection);
        for (Table table : tables) {
            logLn("********************************");
            logLn("Table name: " + table.getName());
            logLn("********************************");
            /*TO DO  primaryKey*/
            /*printPrimaryKey(table);*/

            List<Field> fields = table.getFields();

            List<ForeignKey> foreignKeys = table.getForeignKeys();

            List<Index> indexes = table.getIndexes();

            List<UniqueIndex> uniqueIndexes = table.getUniqueIndexes();

        }
    }

    private static void log(String text) {
        System.out.println(text);
    }


    private static void logLn(String text) {
        System.out.println(text);
    }




}

