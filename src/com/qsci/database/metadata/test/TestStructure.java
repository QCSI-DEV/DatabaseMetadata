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
            while (posKey < expectedKeys.size()) {
                ForeignKey expectedKey = expectedKeys.get(posKey);
                ForeignKey actualKey = actualKeys.get(posKey);
                Assert.assertEquals(expectedKey, actualKey);
                posKey++;
            }
            posTable++;
        }
    }

    public void testPrimaryKey(List<Table> expected, List<Table> actual) {
        int pos = 0;
        while (pos < expected.size()) {
            PrimaryKey expectedKey = expected.get(pos).getPrimaryKey();
            PrimaryKey actualKey = actual.get(pos).getPrimaryKey();
            Assert.assertEquals(expectedKey, actualKey);
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
            while (posField < expectedFields.size()) {
                Field expectedField = expectedFields.get(posField);
                Field actualField = actualFields.get(posField);
                Assert.assertEquals( expectedField, actualField);
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
            while (posIndex < expectedIndex.size()) {
                Index expectedField = expectedIndex.get(posIndex);
                Index actualField = actualIndex.get(posIndex);
                Assert.assertEquals( expectedField, actualField);
                posIndex++;
            }
            posTable++;
        }
    }
}

