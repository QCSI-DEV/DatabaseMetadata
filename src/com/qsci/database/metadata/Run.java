package com.qsci.database.metadata;

import com.qsci.database.metadata.collectorManagers.Manager;
import com.qsci.database.metadata.collectors.Collector;
import com.qsci.database.metadata.collectors.PostgresCollector;
import com.qsci.database.metadata.collectors.SQLiteCollector;
import com.qsci.database.metadata.entities.constraints.ForeignKey;
import com.qsci.database.metadata.entities.indexes.Index;
import com.qsci.database.metadata.entities.model.Field;
import com.qsci.database.metadata.entities.model.Table;
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

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        Manager manager = new Manager();
        Connection connection;

        logger.info("!======================================!");
        logger.info("DataBase Metadata Collector");
        logger.info("!=======================================!");

        Collector collector = null;

        Properties properties = new Properties();
        String configUrl = "resources/data.properties";
        String url = null;
        String login = null;
        String password = null;

        try {
            FileInputStream config = new FileInputStream(configUrl);
            properties.load(config);
            url = properties.getProperty("url");
            login = properties.getProperty("login");
            password = properties.getProperty("password");
        } catch (IOException e) {
            throw new IOException("File " + configUrl + ("not found"));
        }

        if (login.equals("null") && password.equals("null")) {
            Class.forName(SQLiteCollector.getDriverName());
            collector = manager.getTransformer(SQLiteCollector.getDriverName());
        } else {
            Class.forName(PostgresCollector.getDriverName());
            collector = manager.getTransformer(PostgresCollector.getDriverName());
        }

        connection = DriverManager.getConnection(url, login, password);

        String baseProductName = connection.getMetaData().getDatabaseProductName();
        logger.debug("Data base product: " + baseProductName );
        logger.debug("Data base URL: " + url+ "\n");
        List<Table> tables = collector.getTables(connection);

        for (Table table : tables) {
            logger.info("---------------------------------------------");
            logger.info("TABLE NAME: " + table.getName());
            for (Field field : table.getFields()) {
                logger.info(field);
            }
            logger.info(table.getPrimaryKey());
            logger.info(table.getPrimaryKey());
            for (ForeignKey foreignKey : table.getForeignKeys()) {
                logger.info(foreignKey);
            }
            for (Index index : table.getIndexes()) {
                logger.info(index);
            }
        }
    }

}

