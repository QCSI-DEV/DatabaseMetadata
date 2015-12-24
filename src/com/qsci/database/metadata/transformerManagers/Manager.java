package com.qsci.database.metadata.transformerManagers;

import com.qsci.database.metadata.exceptions.UnknownTransformerException;
import com.qsci.database.metadata.transformers.SQLiteTransformer;
import com.qsci.database.metadata.transformers.Transformer;

import java.util.*;

public class Manager {
    final Map<String, Transformer> containerTransformers = new HashMap<>();

    public Manager() {
        containerTransformers.put(SQLiteTransformer.getDriverName(),new SQLiteTransformer());
    }

    public Set<String> getRegisteredNames() {
        return containerTransformers.keySet();
    }

    public Transformer getTransformer(String driverName) throws UnknownTransformerException {
        Transformer result = containerTransformers.get(driverName);
        if (result == null) {
            throw new UnknownTransformerException("No such transformer");
        }
        return result;
    }

    public Map<String, Transformer> getTransformersMap() {
        return containerTransformers;
    }
}
