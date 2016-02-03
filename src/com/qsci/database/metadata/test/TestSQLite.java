package com.qsci.database.metadata.test;

import com.qsci.database.metadata.entities.model.Table;
import com.qsci.database.metadata.test.entities.TestEntities;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Queue;

public class TestSQLite{
    private static Connection connection;
    private static TestEntities SQLIteModels;
    private static TestStructure structure= new TestStructure();


    @BeforeClass
    public static void setUp() throws SQLException, IOException, ClassNotFoundException {
        connection = TestManager.getConnection(("resources/testSQLite.properties"));
        SQLIteModels = TestManager.getEntities(connection, "resources/testSQLite.properties");
    }

    @Test
    public void testForeignKeys() throws SQLException, IOException, ClassNotFoundException {
        List<Table> actual = SQLIteModels.getActual();
        List<Table> expected = SQLIteModels.getExpected();;
        structure.testForeignKey(expected, actual);
    }
    @Test
    public void testPrimaryKeys() throws SQLException, IOException, ClassNotFoundException {
        List<Table> actual = SQLIteModels.getActual();
        List<Table> expected = SQLIteModels.getExpected();
        structure.testPrimaryKey(expected, actual);
    }
    @Test
    public void testIndexes() throws SQLException, IOException, ClassNotFoundException {
        List<Table> actual = SQLIteModels.getActual();
        List<Table> expected = SQLIteModels.getExpected();
        structure.testIndex(expected, actual);
    }
    @Test
    public void testFields() throws SQLException, IOException, ClassNotFoundException {
        List<Table> actual = SQLIteModels.getActual();
        List<Table> expected = SQLIteModels.getExpected();
        structure.testField(expected, actual);
    }

    @AfterClass
    public static void tearDown() throws SQLException, IOException, ClassNotFoundException {
        List<Table> actual = SQLIteModels.getActual();
        List<Table> expected = SQLIteModels.getExpected();
        structure.testTable(expected,actual);
        Queue<String> tablesNames = SQLIteModels.getTablesNames(actual);

        Statement statement = connection.createStatement();
        while (!tablesNames.isEmpty()) {
            statement.execute("DROP TABLE " + tablesNames.remove());
        }
        File actualFile = TestManager.getActualFile("resources/testSQLite.properties");
        actualFile.delete();


    }
}