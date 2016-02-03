package com.qsci.database.metadata.test;

import com.qsci.database.metadata.entities.constraints.ForeignKey;
import com.qsci.database.metadata.entities.constraints.PrimaryKey;
import com.qsci.database.metadata.entities.indexes.Index;
import com.qsci.database.metadata.entities.model.Field;
import com.qsci.database.metadata.entities.model.Table;
import org.junit.Assert;

import java.util.List;

public class TestStructure {


    public void testForeignKey(List<Table> expected, List<Table> actual) {
        int posTable = 0;
        int posKey = 0;
        List<ForeignKey> expectedKeys;
        List<ForeignKey> actualKeys;
        while (posTable < expected.size()) {
            expectedKeys = expected.get(posTable).getForeignKeys();
            actualKeys = actual.get(posTable).getForeignKeys();
            if (actualKeys.size() != expectedKeys.size()) {
                Assert.fail(getTableInfo(expected, actual));
            }
            posTable++;

            while (posKey < expectedKeys.size()) {
                ForeignKey expectedKey = expectedKeys.get(posKey);
                ForeignKey actualKey = actualKeys.get(posKey);
                String message = getTableInfo(expected, actual, posTable);
                Assert.assertEquals(message, expectedKey, actualKey);
                posKey++;
            }

        }

    }

    public void testPrimaryKey(List<Table> expected, List<Table> actual) {
        int pos = 0;
        while (pos < expected.size()) {
            PrimaryKey expectedKey = expected.get(pos).getPrimaryKey();
            PrimaryKey actualKey = actual.get(pos).getPrimaryKey();
            String message = getTableInfo(expected, actual, pos);
            Assert.assertEquals(message, expectedKey, actualKey);
            pos++;
        }
    }

    public void testField(List<Table> expected, List<Table> actual) {
        int posTable = 0;
        int posField = 0;
        List<Field> expectedFields;
        List<Field> actualFields;
        while (posTable < expected.size()) {
            expectedFields = expected.get(posTable).getFields();
            actualFields = actual.get(posTable).getFields();
            if (actualFields.size() != expectedFields.size()) {
                Assert.fail(getTableInfo(expected, actual));
            }
            while (posField < expectedFields.size()) {
                Field expectedField = expectedFields.get(posField);
                Field actualField = actualFields.get(posField);
                String message = getTableInfo(expected, actual, posTable);
                Assert.assertEquals(message, expectedField, actualField);
                posField++;
            }
            posTable++;
        }
    }

    public void testIndex(List<Table> expected, List<Table> actual) {
        int posTable = 0;
        int posIndex = 0;
        List<Index> expectedIndex;
        List<Index> actualIndex;
        while (posTable < expected.size()) {
            expectedIndex = expected.get(posTable).getIndexes();
            actualIndex = actual.get(posTable).getIndexes();
            if (actualIndex.size() != expectedIndex.size()) {
                Assert.fail(getTableInfo(expected, actual));
            }
            while (posIndex < expectedIndex.size()) {
                Index expectedField = expectedIndex.get(posIndex);
                Index actualField = actualIndex.get(posIndex);
                String message = getTableInfo(expected, actual, posTable);
                Assert.assertEquals(message, expectedField, actualField);
                posIndex++;
            }
            posTable++;
        }
    }


    public void testTable(List<Table> expected, List<Table> actual) {
        int pos = 0;
        while (pos < expected.size()) {
            Table expectedTable = expected.get(pos);
            Table actualTable = actual.get(pos);
            Assert.assertEquals(expectedTable, actualTable);
        }
    }

    protected String getTableInfo(List<Table> expected, List<Table> actual, int posTable) {
        if (posTable == 0) {
            return "empty entry";
        }
        return "\n" + "Expected " + expected.get(posTable - 1)
                + "Actual " + actual.get(posTable - 1);
    }

    protected String getTableInfo(List<Table> expected, List<Table> actual) {
        return "\n" + "Expected tables quantity: " + (expected.size())
                + "Actual tables quantity:" + (expected.size());
    }


}

