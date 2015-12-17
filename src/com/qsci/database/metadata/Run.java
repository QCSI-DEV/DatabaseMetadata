package com.qsci.database.metadata;

import com.qsci.database.metadata.exceptions.UnknownTransformerException;
import com.qsci.database.metadata.exceptions.WrongDataForConnectException;
import com.qsci.database.metadata.managers.TransformerManager;
import com.qsci.database.metadata.metaDataEntityes.DataForConnect;
import com.qsci.database.metadata.metaDataEntityes.constraints.ForeignKey;
import com.qsci.database.metadata.metaDataEntityes.constraints.PrimaryKey;
import com.qsci.database.metadata.metaDataEntityes.indexes.Index;
import com.qsci.database.metadata.metaDataEntityes.indexes.UniqueIndex;
import com.qsci.database.metadata.metaDataEntityes.model.DefaultValue;
import com.qsci.database.metadata.metaDataEntityes.model.Field;
import com.qsci.database.metadata.metaDataEntityes.model.Table;
import com.qsci.database.metadata.transformers.TransformerMetaData;

import java.util.List;

public class Run {
    public static void main(String[] args) throws UnknownTransformerException, WrongDataForConnectException {
        DataForConnect data = null;
        data.setUrl("url");
        data.setLogin("login");
        data.setPassword("password");
        TransformerManager manager = null;
        manager.register("null", null);
        manager.register("null", null);
        TransformerMetaData concreteTransformer = null;

        try {
            concreteTransformer = manager.getTransformer(data);
        } catch (UnknownTransformerException e) {
            throw new UnknownTransformerException("UNKNOWN TRANSFORMER");
        } catch (WrongDataForConnectException e) {
            String message = "login:" + data.getLogin() + "password" + data.getPassword() + "url" + data.getUrl();
            System.out.println("WRONG DATA CHECK:" + message);
        }

        List<Table> tables = concreteTransformer.getTables();
        for (Table table : tables) {
            System.out.println("********************************");
            System.out.println("Table name: " + table.getName());
            System.out.println("********************************");

            printPrimaryKey(table);

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

    public static void printPrimaryKey(Table t) {

        PrimaryKey primaryKey = t.getPrimaryKey().map(PrimaryKey::getPrimaryKey).orElse(new PrimaryKey(new List<Field>));
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
        System.out.println();
    }


}
