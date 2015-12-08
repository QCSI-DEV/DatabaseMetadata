package ua.com.manager;

import ua.com.exceptions.UnknownJDBCDriverException;
import ua.com.сollector.Collector;

import java.util.Map;

/**
 * Realization of this class manage realization of Collectors
 * <p/>
 *
 * @author Maksym Skulinets
 * @version 1.0
 */

public abstract class AbstractDbMetaDataManager {
    /*NOTE: we will use Map realization to keep concrete Collectors*/
    private static Map<String, Collector> registeredCollectors;

    abstract boolean registerJDBCDriver(String nameDriver, Collector concreteCollector);

    abstract boolean unRegisterJDBCDriver(String nameDriver);

    abstract void loadMetaData(String UrlLocation) throws UnknownJDBCDriverException;
    /*get URL of data base and search correct realization of collector for it
    * if manager have not that throw UnknownJDBCDriverException */
}
