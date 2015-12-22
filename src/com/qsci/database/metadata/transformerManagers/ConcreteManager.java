package com.qsci.database.metadata.transformerManagers;

import com.qsci.database.metadata.exceptions.UnknownTransformerException;
import com.qsci.database.metadata.transformers.SQLiteTransformer;
import com.qsci.database.metadata.transformers.Transformer;

import java.util.*;

public class ConcreteManager implements Manager {
    final Map<String, Transformer> containerTransformers = new HashMap<>();


    public ConcreteManager() {
        register(SQLiteTransformer.getDriverName(),new SQLiteTransformer());
    }

    @Override
    public boolean register(String driverName, Transformer concreteTransformer) {
        boolean flag = true;
        if (containerTransformers.containsKey(driverName)) {
            flag = false;
        }
        containerTransformers.put(driverName, concreteTransformer);
        return flag;
    }

    @Override
    public boolean unRegister(String driverName) {
        return containerTransformers.remove(driverName) != null;
    }

    @Override
    public boolean contains(String driverName) {
        return containerTransformers.containsKey(driverName);
    }

    @Override
    public Set<String> getRegisteredNames() {
        return containerTransformers.keySet();
    }

    @Override
    public Transformer getTransformer(String driverName) throws UnknownTransformerException {
        Transformer result = containerTransformers.get(driverName);
        if (result == null) {
            throw new UnknownTransformerException("No such transformer");
        }
        return result;
    }
}
