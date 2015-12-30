package com.qsci.database.metadata;

import com.qsci.database.metadata.metaDataEntityes.indexes.Index;
import com.qsci.database.metadata.metaDataEntityes.model.Field;
import com.qsci.database.metadata.metaDataEntityes.model.Table;
import com.qsci.database.metadata.transformerManagers.Manager;
import com.qsci.database.metadata.transformers.PostgresTransformer;
import com.qsci.database.metadata.transformers.SQLiteTransformer;
import com.qsci.database.metadata.transformers.Transformer;
import com.qsci.database.metadata.userData.UserData;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Run {

    public final static Logger logger = Logger.getLogger(Run.class);

    public static void main(String[] args) throws SQLException, ClassNotFoundException {


        logger.info("!======================================!");
        logger.info("DataBase Metadata Transformer v.0.1");
        logger.info("!=======================================!");

        Manager manager = new Manager();
        Transformer transformer = manager.getTransformer(PostgresTransformer.getDriverName());
        Class.forName(PostgresTransformer.getDriverName());
        UserData userData = new UserData();
        /*userData.setUrl("JDBC:sqlite:personBase.sqlite");*/
        userData.setUrl("jdbc:postgresql://localhost:5432/person");
        userData.setLogin("postgres");
        userData.setPassword("******");
        Connection connection = DriverManager.getConnection(userData.getUrl(),userData.getLogin(),userData.getPassword());
        connection.getMetaData().getDatabaseProductVersion();
        String baseProductName  = connection.getMetaData().getDatabaseProductName();
        logger.debug("Data base product: " + baseProductName);
        List<Table> tables = transformer.getTables(connection);
        for (Table table : tables) {
            logger.info(table);
            logger.info(table.getPrimaryKey());
            logger.info(table.getIndexes());
            for (Index index : table.getIndexes()) {
                logger.info(index);
            }
            for (Field field : table.getFields()) {
                logger.info(field);
            }
        }


    }

}

