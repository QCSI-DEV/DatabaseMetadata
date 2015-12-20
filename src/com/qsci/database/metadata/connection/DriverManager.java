package com.qsci.database.metadata.connection;

import com.qsci.database.metadata.exceptions.NoConnectionExceptions;

public class DriverManager {
    private DriverManager() {
    }

    public static AbstractConnection getConnectionWrapper(String dataBaseType) throws NoConnectionExceptions {
        if (dataBaseType.equalsIgnoreCase("sqlite")) {
            return new SqLiteConnection();
        }
        String message ="Cant connect to " + dataBaseType + " data base";
        throw new NoConnectionExceptions(message);
    }
}
