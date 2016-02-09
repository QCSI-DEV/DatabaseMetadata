package com.qsci.database.metadata.collectorManagers;

import com.qsci.database.metadata.collectors.Collector;
import com.qsci.database.metadata.collectors.PostgresCollector;
import com.qsci.database.metadata.collectors.SQLiteCollector;
import com.qsci.database.metadata.exceptions.UnknownTransformerException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Manager {
    final Map<String, Collector> containerCollectors = new HashMap<>();


    public Manager() {
        containerCollectors.put(SQLiteCollector.getDriverName(), new SQLiteCollector());
        containerCollectors.put(PostgresCollector.getDriverName(), new PostgresCollector());
    }

    public Set<String> getRegisteredNames() {
        return containerCollectors.keySet();
    }

    public Collector getTransformer(String driverName) throws UnknownTransformerException {
        Collector result = containerCollectors.get(driverName);
        if (result == null) {
            throw new UnknownTransformerException("Transformer are not registered:" + Collector.class.getName());
        }
        return result;
    }

    public Map<String, Collector> getTransformersMap() {
        return containerCollectors;
    }
}
