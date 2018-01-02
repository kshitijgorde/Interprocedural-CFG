// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j;

import org.apache.log4j.helpers.LogLog;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.log4j.helpers.Loader;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.DefaultRepositorySelector;
import org.apache.log4j.spi.RootLogger;
import java.util.Enumeration;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.RepositorySelector;

public class LogManager
{
    public static final String DEFAULT_CONFIGURATION_FILE = "log4j.properties";
    static final String DEFAULT_XML_CONFIGURATION_FILE = "log4j.xml";
    public static final String DEFAULT_CONFIGURATION_KEY = "log4j.configuration";
    public static final String CONFIGURATOR_CLASS_KEY = "log4j.configuratorClass";
    public static final String DEFAULT_INIT_OVERRIDE_KEY = "log4j.defaultInitOverride";
    private static Object guard;
    private static RepositorySelector repositorySelector;
    
    public static void setRepositorySelector(final RepositorySelector repositorySelector, final Object guard) throws IllegalArgumentException {
        if (LogManager.guard != null && LogManager.guard != guard) {
            throw new IllegalArgumentException("Attempted to reset the LoggerFactory without possessing the guard.");
        }
        if (repositorySelector == null) {
            throw new IllegalArgumentException("RepositorySelector must be non-null.");
        }
        LogManager.guard = guard;
        LogManager.repositorySelector = repositorySelector;
    }
    
    public static LoggerRepository getLoggerRepository() {
        return LogManager.repositorySelector.getLoggerRepository();
    }
    
    public static Logger getRootLogger() {
        return LogManager.repositorySelector.getLoggerRepository().getRootLogger();
    }
    
    public static Logger getLogger(final String s) {
        return LogManager.repositorySelector.getLoggerRepository().getLogger(s);
    }
    
    public static Logger getLogger(final Class clazz) {
        return LogManager.repositorySelector.getLoggerRepository().getLogger(clazz.getName());
    }
    
    public static Logger getLogger(final String s, final LoggerFactory loggerFactory) {
        return LogManager.repositorySelector.getLoggerRepository().getLogger(s, loggerFactory);
    }
    
    public static Logger exists(final String s) {
        return LogManager.repositorySelector.getLoggerRepository().exists(s);
    }
    
    public static Enumeration getCurrentLoggers() {
        return LogManager.repositorySelector.getLoggerRepository().getCurrentLoggers();
    }
    
    public static void shutdown() {
        LogManager.repositorySelector.getLoggerRepository().shutdown();
    }
    
    public static void resetConfiguration() {
        LogManager.repositorySelector.getLoggerRepository().resetConfiguration();
    }
    
    static {
        LogManager.guard = null;
        LogManager.repositorySelector = new DefaultRepositorySelector(new Hierarchy(new RootLogger(Level.DEBUG)));
        final String systemProperty = OptionConverter.getSystemProperty("log4j.defaultInitOverride", null);
        if (systemProperty == null || "false".equalsIgnoreCase(systemProperty)) {
            final String systemProperty2 = OptionConverter.getSystemProperty("log4j.configuration", null);
            final String systemProperty3 = OptionConverter.getSystemProperty("log4j.configuratorClass", null);
            URL url;
            if (systemProperty2 == null) {
                url = Loader.getResource("log4j.xml");
                if (url == null) {
                    url = Loader.getResource("log4j.properties");
                }
            }
            else {
                try {
                    url = new URL(systemProperty2);
                }
                catch (MalformedURLException ex) {
                    url = Loader.getResource(systemProperty2);
                }
            }
            if (url != null) {
                LogLog.debug("Using URL [" + url + "] for automatic log4j configuration.");
                OptionConverter.selectAndConfigure(url, systemProperty3, getLoggerRepository());
            }
            else {
                LogLog.debug("Could not find resource: [" + systemProperty2 + "].");
            }
        }
    }
}
