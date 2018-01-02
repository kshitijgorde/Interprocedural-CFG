// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j;

import org.apache.log4j.spi.OptionHandler;
import java.util.StringTokenizer;
import java.util.Enumeration;
import org.apache.log4j.or.RendererMap;
import org.apache.log4j.spi.RendererSupport;
import org.apache.log4j.config.PropertySetter;
import org.apache.log4j.helpers.OptionConverter;
import java.net.URL;
import java.io.IOException;
import org.apache.log4j.helpers.LogLog;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.Properties;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.LoggerFactory;
import java.util.Hashtable;
import org.apache.log4j.spi.Configurator;

public class PropertyConfigurator implements Configurator
{
    protected Hashtable registry;
    protected LoggerFactory loggerFactory;
    static final String CATEGORY_PREFIX = "log4j.category.";
    static final String LOGGER_PREFIX = "log4j.logger.";
    static final String FACTORY_PREFIX = "log4j.factory";
    static final String ADDITIVITY_PREFIX = "log4j.additivity.";
    static final String ROOT_CATEGORY_PREFIX = "log4j.rootCategory";
    static final String ROOT_LOGGER_PREFIX = "log4j.rootLogger";
    static final String APPENDER_PREFIX = "log4j.appender.";
    static final String RENDERER_PREFIX = "log4j.renderer.";
    static final String THRESHOLD_PREFIX = "log4j.threshold";
    public static final String LOGGER_FACTORY_KEY = "log4j.loggerFactory";
    private static final String INTERNAL_ROOT_NAME = "root";
    static /* synthetic */ Class class$org$apache$log4j$spi$LoggerFactory;
    static /* synthetic */ Class class$org$apache$log4j$Appender;
    static /* synthetic */ Class class$org$apache$log4j$Layout;
    
    public PropertyConfigurator() {
        this.registry = new Hashtable(11);
        this.loggerFactory = new DefaultCategoryFactory();
    }
    
    public void doConfigure(final String s, final LoggerRepository loggerRepository) {
        final Properties properties = new Properties();
        try {
            final FileInputStream fileInputStream = new FileInputStream(s);
            properties.load(fileInputStream);
            fileInputStream.close();
        }
        catch (IOException ex) {
            LogLog.error("Could not read configuration file [" + s + "].", ex);
            LogLog.error("Ignoring configuration file [" + s + "].");
            return;
        }
        this.doConfigure(properties, loggerRepository);
    }
    
    public static void configure(final String s) {
        new PropertyConfigurator().doConfigure(s, LogManager.getLoggerRepository());
    }
    
    public static void configure(final URL url) {
        new PropertyConfigurator().doConfigure(url, LogManager.getLoggerRepository());
    }
    
    public static void configure(final Properties properties) {
        new PropertyConfigurator().doConfigure(properties, LogManager.getLoggerRepository());
    }
    
    public static void configureAndWatch(final String s) {
        configureAndWatch(s, 60000L);
    }
    
    public static void configureAndWatch(final String s, final long delay) {
        final PropertyWatchdog propertyWatchdog = new PropertyWatchdog(s);
        propertyWatchdog.setDelay(delay);
        propertyWatchdog.start();
    }
    
    public void doConfigure(final Properties properties, final LoggerRepository loggerRepository) {
        String s = properties.getProperty("log4j.debug");
        if (s == null) {
            s = properties.getProperty("log4j.configDebug");
            if (s != null) {
                LogLog.warn("[log4j.configDebug] is deprecated. Use [log4j.debug] instead.");
            }
        }
        if (s != null) {
            LogLog.setInternalDebugging(OptionConverter.toBoolean(s, true));
        }
        final String andSubst = OptionConverter.findAndSubst("log4j.threshold", properties);
        if (andSubst != null) {
            loggerRepository.setThreshold(OptionConverter.toLevel(andSubst, Level.ALL));
            LogLog.debug("Hierarchy threshold set to [" + loggerRepository.getThreshold() + "].");
        }
        this.configureRootCategory(properties, loggerRepository);
        this.configureLoggerFactory(properties);
        this.parseCatsAndRenderers(properties, loggerRepository);
        LogLog.debug("Finished configuring.");
        this.registry.clear();
    }
    
    public void doConfigure(final URL url, final LoggerRepository loggerRepository) {
        final Properties properties = new Properties();
        LogLog.debug("Reading configuration from URL " + url);
        try {
            properties.load(url.openStream());
        }
        catch (IOException ex) {
            LogLog.error("Could not read configuration file from URL [" + url + "].", ex);
            LogLog.error("Ignoring configuration file [" + url + "].");
            return;
        }
        this.doConfigure(properties, loggerRepository);
    }
    
    protected void configureLoggerFactory(final Properties properties) {
        final String andSubst = OptionConverter.findAndSubst("log4j.loggerFactory", properties);
        if (andSubst != null) {
            LogLog.debug("Setting category factory to [" + andSubst + "].");
            PropertySetter.setProperties(this.loggerFactory = (LoggerFactory)OptionConverter.instantiateByClassName(andSubst, (PropertyConfigurator.class$org$apache$log4j$spi$LoggerFactory == null) ? (PropertyConfigurator.class$org$apache$log4j$spi$LoggerFactory = class$("org.apache.log4j.spi.LoggerFactory")) : PropertyConfigurator.class$org$apache$log4j$spi$LoggerFactory, this.loggerFactory), properties, "log4j.factory.");
        }
    }
    
    void configureRootCategory(final Properties properties, final LoggerRepository loggerRepository) {
        String s = "log4j.rootLogger";
        String s2 = OptionConverter.findAndSubst("log4j.rootLogger", properties);
        if (s2 == null) {
            s2 = OptionConverter.findAndSubst("log4j.rootCategory", properties);
            s = "log4j.rootCategory";
        }
        if (s2 == null) {
            LogLog.debug("Could not find root logger information. Is this OK?");
        }
        else {
            final Logger rootLogger = loggerRepository.getRootLogger();
            synchronized (rootLogger) {
                this.parseCategory(properties, rootLogger, s, "root", s2);
            }
        }
    }
    
    protected void parseCatsAndRenderers(final Properties properties, final LoggerRepository loggerRepository) {
        final Enumeration<?> propertyNames = properties.propertyNames();
        while (propertyNames.hasMoreElements()) {
            final String s = (String)propertyNames.nextElement();
            if (s.startsWith("log4j.category.") || s.startsWith("log4j.logger.")) {
                String s2 = null;
                if (s.startsWith("log4j.category.")) {
                    s2 = s.substring("log4j.category.".length());
                }
                else if (s.startsWith("log4j.logger.")) {
                    s2 = s.substring("log4j.logger.".length());
                }
                final String andSubst = OptionConverter.findAndSubst(s, properties);
                final Logger logger = loggerRepository.getLogger(s2, this.loggerFactory);
                synchronized (logger) {
                    this.parseCategory(properties, logger, s, s2, andSubst);
                    this.parseAdditivityForLogger(properties, logger, s2);
                    continue;
                }
            }
            if (s.startsWith("log4j.renderer.")) {
                final String substring = s.substring("log4j.renderer.".length());
                final String andSubst2 = OptionConverter.findAndSubst(s, properties);
                if (!(loggerRepository instanceof RendererSupport)) {
                    continue;
                }
                RendererMap.addRenderer((RendererSupport)loggerRepository, substring, andSubst2);
            }
        }
    }
    
    void parseAdditivityForLogger(final Properties properties, final Logger logger, final String s) {
        final String andSubst = OptionConverter.findAndSubst("log4j.additivity." + s, properties);
        LogLog.debug("Handling log4j.additivity." + s + "=[" + andSubst + "]");
        if (andSubst != null && !andSubst.equals("")) {
            final boolean boolean1 = OptionConverter.toBoolean(andSubst, true);
            LogLog.debug("Setting additivity for \"" + s + "\" to " + boolean1);
            logger.setAdditivity(boolean1);
        }
    }
    
    void parseCategory(final Properties properties, final Logger logger, final String s, final String s2, final String s3) {
        LogLog.debug("Parsing for [" + s2 + "] with value=[" + s3 + "].");
        final StringTokenizer stringTokenizer = new StringTokenizer(s3, ",");
        if (!s3.startsWith(",") && !s3.equals("")) {
            if (!stringTokenizer.hasMoreTokens()) {
                return;
            }
            final String nextToken = stringTokenizer.nextToken();
            LogLog.debug("Level token is [" + nextToken + "].");
            if ("inherited".equalsIgnoreCase(nextToken) || "null".equalsIgnoreCase(nextToken)) {
                if (s2.equals("root")) {
                    LogLog.warn("The root logger cannot be set to null.");
                }
                else {
                    logger.setLevel(null);
                }
            }
            else {
                logger.setLevel(OptionConverter.toLevel(nextToken, Level.DEBUG));
            }
            LogLog.debug("Category " + s2 + " set to " + logger.getLevel());
        }
        logger.removeAllAppenders();
        while (stringTokenizer.hasMoreTokens()) {
            final String trim = stringTokenizer.nextToken().trim();
            if (trim != null) {
                if (trim.equals(",")) {
                    continue;
                }
                LogLog.debug("Parsing appender named \"" + trim + "\".");
                final Appender appender = this.parseAppender(properties, trim);
                if (appender == null) {
                    continue;
                }
                logger.addAppender(appender);
            }
        }
    }
    
    Appender parseAppender(final Properties properties, final String name) {
        final Appender registryGet = this.registryGet(name);
        if (registryGet != null) {
            LogLog.debug("Appender \"" + name + "\" was already parsed.");
            return registryGet;
        }
        final String string = "log4j.appender." + name;
        final String string2 = string + ".layout";
        final Appender appender = (Appender)OptionConverter.instantiateByKey(properties, string, (PropertyConfigurator.class$org$apache$log4j$Appender == null) ? (PropertyConfigurator.class$org$apache$log4j$Appender = class$("org.apache.log4j.Appender")) : PropertyConfigurator.class$org$apache$log4j$Appender, null);
        if (appender == null) {
            LogLog.error("Could not instantiate appender named \"" + name + "\".");
            return null;
        }
        appender.setName(name);
        if (appender instanceof OptionHandler) {
            if (appender.requiresLayout()) {
                final Layout layout = (Layout)OptionConverter.instantiateByKey(properties, string2, (PropertyConfigurator.class$org$apache$log4j$Layout == null) ? (PropertyConfigurator.class$org$apache$log4j$Layout = class$("org.apache.log4j.Layout")) : PropertyConfigurator.class$org$apache$log4j$Layout, null);
                if (layout != null) {
                    appender.setLayout(layout);
                    LogLog.debug("Parsing layout options for \"" + name + "\".");
                    PropertySetter.setProperties(layout, properties, string2 + ".");
                    LogLog.debug("End of parsing for \"" + name + "\".");
                }
            }
            PropertySetter.setProperties(appender, properties, string + ".");
            LogLog.debug("Parsed \"" + name + "\" options.");
        }
        this.registryPut(appender);
        return appender;
    }
    
    void registryPut(final Appender appender) {
        this.registry.put(appender.getName(), appender);
    }
    
    Appender registryGet(final String s) {
        return this.registry.get(s);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
