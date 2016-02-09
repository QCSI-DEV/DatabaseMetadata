package com.qsci.database.metadata;

import com.qsci.database.metadata.entities.TestEntities;
import com.qsci.database.metadata.entities.model.Table;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestSQLite {
    private static TestStructure structure = new TestStructure();
    private static Connection connection;
    private static TestEntities SQLIteModels;
    private static List<Table> actual;
    private static List<Table> expected;


    @BeforeClass
    public static void setUp() throws Exception {
        connection = TestManager.getConnection(("resources/testSQLite.properties"));
        SQLIteModels = TestManager.getEntities(connection, "resources/testSQLite.properties");
        actual = SQLIteModels.getActual();
        expected = SQLIteModels.getExpected();
    }

    @Test
    public void testForeignKeys() throws SQLException, IOException, ClassNotFoundException {
        structure.testForeignKey(expected, actual);
    }

    @Test
    public void testPrimaryKeys() throws SQLException, IOException, ClassNotFoundException {
        structure.testPrimaryKey(expected, actual);
    }

    @Test
    public void testIndexes() throws SQLException, IOException, ClassNotFoundException {
        structure.testIndex(expected, actual);
    }

    @Test
    public void testFields() throws SQLException, IOException, ClassNotFoundException {
        structure.testField(expected, actual);
    }

    @AfterClass
    public static void tearDown() throws SQLException, IOException, ClassNotFoundException {
        connection.close();
        File actualFile = TestManager.getDumpRecoveryFile("resources/testSQLite.properties");
        actualFile.delete();
    }
}