package com.qsci.database.metadata;

import com.qsci.database.metadata.entities.indexes.Index;
import com.qsci.database.metadata.entities.model.Field;
import com.qsci.database.metadata.entities.model.Table;
import com.qsci.database.metadata.transformerManagers.Manager;
import com.qsci.database.metadata.transformers.SQLiteTransformer;
import com.qsci.database.metadata.transformers.Transformer;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class Run {

    public final static Logger logger = Logger.getLogger(Run.class);

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {


        logger.info("!======================================!");
        logger.info("DataBase Metadata Transformer");
        logger.info("!=======================================!");

        Manager manager = new Manager();
        Transformer transformer = manager.getTransformer(SQLiteTransformer.getDriverName());
        Class.forName(SQLiteTransformer.getDriverName());
        /*userData.setUrl("jdbc:postgresql://localhost:5432/person");
        userData.setLogin("postgres");
        userData.setPassword("******");*/
        /*properties = delete user data*/
        Connection connection;
        Properties properties = new Properties();
        String userDataLocation = "resources/data.properties";
        String url = null;
        String login = null;
        String password = null;

        try {
            FileInputStream in = new FileInputStream(userDataLocation);
            properties.load(in);
            url = properties.getProperty("db.url");
            login = properties.getProperty("db.login");
            password = properties.getProperty("db.password");

        } catch (IOException e) {
            throw new IOException("File " + userDataLocation + ("not found"));
        }

        if (login == null && password == null) {
            connection = DriverManager.getConnection(url, login, password);

        } else {
            connection = DriverManager.getConnection(url);
        }

        connection.getMetaData().getDatabaseProductVersion();
        String baseProductName = connection.getMetaData().getDatabaseProductName();
        logger.debug("Data base product: " + baseProductName);
        List<Table> tables = transformer.getTables(connection);
        for (Table table : tables) {
            logger.info(table);
            logger.info(table.getPrimaryKey());
            for (Index index : table.getIndexes()) {
                logger.info(index);
            }
            for (Field field : table.getFields()) {
                logger.info(field);
            }
        }


    }

}

