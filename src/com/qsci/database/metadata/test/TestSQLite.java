import com.qsci.database.metadata.entities.constraints.ForeignKey;
import com.qsci.database.metadata.entities.constraints.PrimaryKey;
import com.qsci.database.metadata.entities.indexes.Index;
import com.qsci.database.metadata.entities.model.Field;
import org.junit.Assert;

import java.util.List;

public class TestSQLite extends TestStructure {



    @Override
    public void testForeignKeysCollector() {
        int posTable = 0;
        int posFK = 0;
        List<ForeignKey> expectedKeys;
        List<ForeignKey> actualKeys;
        while (posTable < expectedTables.size()) {
            expectedKeys = expectedTables.get(posTable).getForeignKeys();
            actualKeys = actualTables.get(posTable).getForeignKeys();
            posTable++;
            if (actualKeys.size() != expectedKeys.size()) {
                Assert.fail("TO DO");
            }
            while (posFK < expectedKeys.size()) {
                ForeignKey expectedKey = expectedKeys.get(posFK);
                ForeignKey actualKey = actualKeys.get(posFK);
                String message = "TO DO";
                Assert.assertEquals(message,expectedKey,actualKey);
                posFK++;
            }
        }


    }

    @Override
    public void testPrimaryKeysCollector() {
        int pos = 0;
        while (pos < expectedTables.size()) {
            PrimaryKey expectedKey = expectedTables.get(pos).getPrimaryKey();
            PrimaryKey actualKey = actualTables.get(pos).getPrimaryKey();
            String message = "TO DO";
            Assert.assertEquals(message, expectedKey, actualKey);
            pos++;
        }
    }

    @Override
    public void testFieldsCollector() {
        int posTable = 0;
        int posField = 0;
        List<Field> expectedFields;
        List<Field> actualFields;
        while (posTable < expectedTables.size()) {
            expectedFields = expectedTables.get(posTable).getInde();
            actualFields = actualTables.get(posTable).getInde();
            posTable++;
            if (actualFields.size() != expectedFields.size()) {
                Assert.fail("TO DO");
            }
            while (posField < expectedFields.size()) {
                Field expectedField = expectedFields.get(posField);
                Field actualField = actualFields.get(posField);
                String message = "TO DO";
                Assert.assertEquals(message,expectedField,actualField);
                posField++;
            }
        }
    }

    @Override
    public void testIndexesCollector() {
        int posTable = 0;
        int posIndex = 0;
        List<Index> expectedIndex;
        List<Index> actualIndex;
        while (posTable < expectedTables.size()) {
            expectedIndex = expectedTables.get(posTable).getIndexes();
            actualIndex = actualTables.get(posTable).getIndexes();
            posTable++;
            if (actualIndex.size() != expectedIndex.size()) {
                Assert.fail("TO DO");
            }
            while (posIndex < expectedIndex.size()) {
                Index expectedField = expectedIndex.get(posIndex);
                Index actualField = actualIndex.get(posIndex);
                String message = "TO DO";
                Assert.assertEquals(message,expectedField,actualField);
                posIndex++;
            }
        }

    }
}