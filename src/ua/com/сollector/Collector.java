package ua.com.сollector;

/**
 * Realization of this class can connect to concrete DB and get metadata.
 * <p/>
 *
 * @author Maksym Skulinets
 * @version 1.0
 */
public abstract class Collector {
    private static String pathToDriver = null;
    /*NOTE: to load concrete JDBC driver in "permgen"*/

    public static String getPathToDriver() {
        return pathToDriver;
    }

    public static void setPathToDriver(String pathToDriver) {
        Collector.pathToDriver = pathToDriver;
    }

    public abstract void getConnection(String name,String password, String Url);
    /*connect to concrete DB */

    public abstract String getGeneralMetaData();

    public abstract String getTablesMetaData();

    public abstract String getColumnsMetaData();

    public abstract String getForeignKeysMetaData();

    public abstract String getIndexesMetaData();

    public abstract String getUniqueIndexesMetaData();

    public abstract String getConstraints();



}
