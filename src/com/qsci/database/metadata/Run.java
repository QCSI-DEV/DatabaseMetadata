package com.qsci.database.metadata;

import com.qsci.database.metadata.connection.AbstractConnection;
import com.qsci.database.metadata.connection.DriverManager;
import com.qsci.database.metadata.exceptions.NoConnectionExceptions;
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
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Run {
    public static void main(String[] args) throws UnknownTransformerException, WrongDataForConnectException {

        Scanner in = new Scanner(System.in);
        System.out.println("!======================================!");
        System.out.println("DataBase Metadata Transformer v.0.1");
        System.out.println("!=======================================!");

        Manager manager = new ConcreteManager();
        manager.register(SQLiteTransformer.getName(), new SQLiteTransformer());
        System.out.println("Choose type of data base which you would like connect: ");
        int choice = 0;
        for (String transformerName : manager.getRegisteredNames()) {
            System.out.println((++choice) + ". " + transformerName);
        }
        System.out.println("Your choice (use number to choose):");
        choice = in.nextInt();
        String choiceName = manager.getRegisteredNames().get(--choice);
        AbstractConnection connectionWrapper = null;
        Connection connection = null;

        try {
            connectionWrapper = DriverManager.getConnectionWrapper(choiceName);
        } catch (NoConnectionExceptions noConnectionExceptions) {
            noConnectionExceptions.printStackTrace();
        }
        try {
            connection = connectionWrapper.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Transformer transformer = manager.getTransformer(choiceName);
        transformer.setConnection(connection);
        List<Table> tables = transformer.getTables();
        for (Table table : tables) {
            System.out.println("********************************");
            System.out.println("Table name: " + table.getName());
            System.out.println("********************************");
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


    public static void printFields(List<Field> fields) {
        for (Field field : fields) {
            System.out.println("FIELD" + field.getName());
            System.out.println("Type:" + field.getType());
            if (field.isChecked()) {
                System.out.print(" (checked value) ");
            }
            if (field.isAutoincrement()) {
                System.out.print(" (auto incremented) ");
            }
            if (field.isDefaultNull()) {
                System.out.print(" (default null) ");
            }
            if (field.isUnique()) {
                System.out.print(" (unique value) ");
            }
            String defaultValue = field.getDefaultValue().map(DefaultValue::getDefaultValue).orElse("");
            if (!defaultValue.equals("")) {
                System.out.println("default value = " + defaultValue);
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
            System.out.println("FOREIGN KEY:" + name);
            System.out.println("    Table: " + fromTable + " name: " + fromName);
            System.out.println("    References > ");
            System.out.println("    Table: " + fromTable + " name: " + toName);
            System.out.println("    On update: " + mechanismUpdate);
            System.out.println("    On delete: " + mechanismDelete);

        }
    }

    public static void printUniqueIndexes(List<UniqueIndex> indexes) {
        for (UniqueIndex index : indexes) {
            System.out.println("UNIQUE INDEX:" + index.getIdentifier());
            List<Field> indexFields = index.getUniqueIndex();
            System.out.println("    uniqueIndex by fields:");
            for (Field indexField : indexFields) {
                System.out.print(indexField + ", ");
            }
            System.out.println();
        }
    }

    public static void printIndexes(List<Index> indexes) {
        for (Index index : indexes) {
            System.out.println("INDEX:" + index.getIdentifier());
            List<Field> indexFields = index.getIndex();
            System.out.println("    index by fields:");
            for (Field indexField : indexFields) {
                System.out.print(indexField + ", ");
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

