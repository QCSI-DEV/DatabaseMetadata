package com.qsci.database.metadata;

import com.qsci.database.metadata.exceptions.UnknownTransformerException;
import com.qsci.database.metadata.exceptions.WrongDataForConnectException;
import com.qsci.database.metadata.metaDataEntityes.constraints.ForeignKey;
import com.qsci.database.metadata.metaDataEntityes.indexes.Index;
import com.qsci.database.metadata.metaDataEntityes.indexes.UniqueIndex;
import com.qsci.database.metadata.metaDataEntityes.model.DefaultValue;
import com.qsci.database.metadata.metaDataEntityes.model.Field;
import com.qsci.database.metadata.metaDataEntityes.model.Table;
import com.qsci.database.metadata.transformerManagers.ConcreteManager;
import com.qsci.database.metadata.transformerManagers.Manager;
import com.qsci.database.metadata.transformers.SQLiteTransformer;
import com.qsci.database.metadata.transformers.Transformer;
import com.qsci.database.metadata.userInfo.UserInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Run {
    public static void main(String[] args) throws UnknownTransformerException, WrongDataForConnectException, SQLException, ClassNotFoundException {


        Scanner in = new Scanner(System.in);
        logLn("!======================================!");
        logLn("DataBase Metadata Transformer v.0.1");
        logLn("!=======================================!");

        Manager manager = new ConcreteManager();
        Transformer transformer = manager.getTransformer(SQLiteTransformer.getDriverName());
        Class.forName(SQLiteTransformer.getDriverName());
        UserInfo userData = new UserInfo();
        userData.setUrl("JDBC:sqlite:personBase");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(userData.getUrl());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Table> tables = transformer.getTables(connection);
        for (Table table : tables) {
            logLn("********************************");
            logLn("Table name: " + table.getName());
            logLn("********************************");
            /*TO DO  primaryKey*/
            /*printPrimaryKey(table);*/

            List<Field> fields = table.getFields();
            printFields(fields);

            List<ForeignKey> foreignKeys = table.getForeignKeys();
            printForeignKeys(foreignKeys);

            List<Index> indexes = table.getIndexes();
            printIndexes(indexes);

            List<UniqueIndex> uniqueIndexes = table.getUniqueIndexes();
            printUniqueIndexes(uniqueIndexes);


        }
    }

    private static void log(String text) {
        System.out.println(text);
    }


    private static void logLn(String text) {
        System.out.println(text);
    }

    public static void printFields(List<Field> fields) {
        for (Field field : fields) {
            logLn("FIELD" + field.getName());
            logLn("Type:" + field.getType());
            if (field.isChecked()) {
                log(" (checked value) ");
            }
            if (field.isAutoincrement()) {
                log(" (auto incremented) ");
            }
            if (field.isNullable()) {
                log(" (default null) ");
            }
            if (field.isUnique()) {
                log(" (unique value) ");
            }
            String defaultValue = field.getDefaultValue().map(DefaultValue::getDefaultValue).orElse("");
            if (!defaultValue.equals("")) {
                logLn("default value = " + defaultValue);
            }
            System.out.println();

        }


    }


    public static void printForeignKeys(List<ForeignKey> foreignKeys) {
        for (ForeignKey foreignKey : foreignKeys) {
            String name = foreignKey.getIdentifier();
            String fromTable = foreignKey.fromTable().getName();
            String toTable = foreignKey.toTable().getName();
            String fromName = foreignKey.fromName().getName();
            String toName = foreignKey.toName().getName();
            String mechanismUpdate = foreignKey.getReferenceOnUpdate().getMechanism();
            String mechanismDelete = foreignKey.getReferenceOnDelete().getMechanism();
            logLn("FOREIGN KEY:" + name);
            logLn("    Table: " + fromTable + " name: " + fromName);
            logLn("    References > ");
            logLn("    Table: " + fromTable + " name: " + toName);
            logLn("    On update: " + mechanismUpdate);
            logLn("    On delete: " + mechanismDelete);

        }
    }

    public static void printUniqueIndexes(List<UniqueIndex> indexes) {
        for (UniqueIndex index : indexes) {
            logLn("UNIQUE INDEX:" + index.getIdentifier());
            List<Field> indexFields = index.getUniqueIndex();
            logLn("    uniqueIndex by fields:");
            for (Field indexField : indexFields) {
                log(indexField + ", ");
            }
            System.out.println();
        }
    }

    public static void printIndexes(List<Index> indexes) {
        for (Index index : indexes) {
            logLn("INDEX:" + index.getIdentifier());
            List<Field> indexFields = index.getIndex();
            logLn("    index by fields:");
            for (Field indexField : indexFields) {
                log(indexField + ", ");
            }
            System.out.println();
        }
    }
    /*TO DO  primaryKey*/
    /*public static void printPrimaryKey(Table t) {
        List<Field> emptyList = new ArrayList<>();
        *//*PrimaryKey primaryKey = t.getPrimaryKey().map(PrimaryKey::getPrimaryKey).orElse(emptyList);*//*
        PrimaryKey primaryKey = t.getPrimaryKey().orElse(new Pr);
        List<Field> pkFields = primaryKey.getPrimaryKey();
        System.out.println("PRIMARY KEY: ");
        for (Field pkField : pkFields) {
            System.out.print(pkField.getName());
            if (pkField.isAutoincrement()) {
                System.out.print("(autoIncremented)");
            }
            if (pkField.isDefaultNull()) {
                System.out.print("(default is NULL)");
            }
            System.out.print(", ");

        }
        System.out.println();*/


}

