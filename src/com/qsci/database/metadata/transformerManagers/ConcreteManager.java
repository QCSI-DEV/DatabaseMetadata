package com.qsci.database.metadata.transformerManagers;

import com.qsci.database.metadata.exceptions.UnknownTransformerException;
import com.qsci.database.metadata.transformers.Transformer;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConcreteManager implements Manager {
    public ConcreteManager() {
        containerTransformers = new HashMap<>();
    }

    private Map<String, Transformer> containerTransformers = null;

    static Connection connection = null;

    @Override
    public boolean register(String driverName, Transformer concreteTransformer) {
        if (containerTransformers.containsKey(driverName)) {
            return false;
        }
        containerTransformers.put(driverName, concreteTransformer);
        return true;
    }

    @Override
    public boolean unRegister(String driverName) {
        if (containerTransformers.containsKey(driverName)) {
            containerTransformers.remove(driverName);
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(String driverName) {
        if (containerTransformers.containsKey(driverName)) {
            return true;
        }
        return false;
    }

    @Override
    public List<String> getRegisteredNames() {
        List<String> transformerNames = new ArrayList<>();
        transformerNames.addAll(containerTransformers.keySet());
        return transformerNames;
    }

    @Override
    public Transformer getTransformer(String typeDataBase) throws UnknownTransformerException {

        Transformer result = containerTransformers.get(typeDataBase);
        if (result == null) {
            throw new UnknownTransformerException("No such transformer");
        }
        return result;
    }
}
