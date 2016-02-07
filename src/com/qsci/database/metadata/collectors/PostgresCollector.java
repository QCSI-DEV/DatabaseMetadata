package com.qsci.database.metadata.collectors;

import com.qsci.database.metadata.entities.model.Table;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostgresCollector extends Collector {
    public final static Logger logger = Logger.getLogger(PostgresCollector.class);


    public static String getDriverName() {
        return "org.postgresql.Driver";
    }

    @Override
    public List<Table> getTables(Connection connection) throws SQLException {
        String  schemaPattern = "public";
        String catalog = connection.getCatalog();
        ResultSet sourceTables = connection.getMetaData().getTables(catalog, schemaPattern, null, null);
        List<Table> destinationTables = new ArrayList<>();

        while (sourceTables.next()) {
            destinationTables.add(new Table(sourceTables.getString("TABLE_NAME")));
        }

        for (Table destinationTable : destinationTables) {

            connection.getMetaData().getExportedKeys(catalog, schemaPattern, destinationTable.getName());
            DatabaseMetaData metaData = connection.getMetaData();
            insertForeignKeys(destinationTable,metaData);

            ResultSet sourcePrimaryKey =
                    connection.getMetaData().getPrimaryKeys(catalog, schemaPattern, destinationTable.getName());
            insertPrimaryKey(destinationTable, sourcePrimaryKey);

            ResultSet sourceIndex =
                    connection.getMetaData().getIndexInfo(catalog, schemaPattern, destinationTable.getName(), false, false);
            insertIndex(destinationTable, sourceIndex);

            ResultSet sourceField =
                    connection.getMetaData().getColumns(catalog, schemaPattern, destinationTable.getName(), null);
            insertField(destinationTable, sourceField);

        }
        return destinationTables;

    }
}
