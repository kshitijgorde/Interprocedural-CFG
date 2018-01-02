// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.logging;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Logger implements Serializable
{
    static final long serialVersionUID = 4232175575988879434L;
    protected static String PLUGIN_CLASS_PROP;
    protected static final String LOG4J_PLUGIN_CLASS_NAME = "org.jboss.logging.Log4jLoggerPlugin";
    protected static Class pluginClass;
    protected static String pluginClassName;
    private final String name;
    protected transient LoggerPlugin loggerDelegate;
    static /* synthetic */ Class class$org$jboss$logging$NullLoggerPlugin;
    
    public static String getPluginClassName() {
        return Logger.pluginClassName;
    }
    
    public static void setPluginClassName(final String pluginClassName) {
        if (!pluginClassName.equals(Logger.pluginClassName)) {
            Logger.pluginClassName = pluginClassName;
            init();
        }
    }
    
    protected Logger(final String name) {
        this.loggerDelegate = null;
        this.name = name;
        this.loggerDelegate = getDelegatePlugin(name);
    }
    
    public String getName() {
        return this.name;
    }
    
    public LoggerPlugin getLoggerPlugin() {
        return this.loggerDelegate;
    }
    
    public boolean isTraceEnabled() {
        return this.loggerDelegate.isTraceEnabled();
    }
    
    public void trace(final Object message) {
        this.loggerDelegate.trace(message);
    }
    
    public void trace(final Object message, final Throwable t) {
        this.loggerDelegate.trace(message, t);
    }
    
    public boolean isDebugEnabled() {
        return this.loggerDelegate.isDebugEnabled();
    }
    
    public void debug(final Object message) {
        this.loggerDelegate.debug(message);
    }
    
    public void debug(final Object message, final Throwable t) {
        this.loggerDelegate.debug(message, t);
    }
    
    public boolean isInfoEnabled() {
        return this.loggerDelegate.isInfoEnabled();
    }
    
    public void info(final Object message) {
        this.loggerDelegate.info(message);
    }
    
    public void info(final Object message, final Throwable t) {
        this.loggerDelegate.info(message, t);
    }
    
    public void warn(final Object message) {
        this.loggerDelegate.warn(message);
    }
    
    public void warn(final Object message, final Throwable t) {
        this.loggerDelegate.warn(message, t);
    }
    
    public void error(final Object message) {
        this.loggerDelegate.error(message);
    }
    
    public void error(final Object message, final Throwable t) {
        this.loggerDelegate.error(message, t);
    }
    
    public void fatal(final Object message) {
        this.loggerDelegate.fatal(message);
    }
    
    public void fatal(final Object message, final Throwable t) {
        this.loggerDelegate.fatal(message, t);
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        if (Logger.pluginClass == null) {
            init();
        }
        this.loggerDelegate = getDelegatePlugin(this.name);
    }
    
    public static Logger getLogger(final String name) {
        return new Logger(name);
    }
    
    public static Logger getLogger(final String name, final String suffix) {
        return new Logger(name + "." + suffix);
    }
    
    public static Logger getLogger(final Class clazz) {
        return new Logger(clazz.getName());
    }
    
    public static Logger getLogger(final Class clazz, final String suffix) {
        return new Logger(clazz.getName() + "." + suffix);
    }
    
    protected static LoggerPlugin getDelegatePlugin(final String name) {
        LoggerPlugin plugin = null;
        try {
            plugin = Logger.pluginClass.newInstance();
        }
        catch (Throwable e) {
            plugin = new NullLoggerPlugin();
        }
        try {
            plugin.init(name);
        }
        catch (Throwable e) {
            final String extraInfo = e.getMessage();
            System.err.println("Failed to initalize plugin: " + plugin + ((extraInfo != null) ? (", cause: " + extraInfo) : ""));
            plugin = new NullLoggerPlugin();
        }
        return plugin;
    }
    
    protected static void init() {
        try {
            if (Logger.pluginClassName == null) {
                Logger.pluginClassName = System.getProperty(Logger.PLUGIN_CLASS_PROP, "org.jboss.logging.Log4jLoggerPlugin");
            }
            final ClassLoader cl = Thread.currentThread().getContextClassLoader();
            Logger.pluginClass = cl.loadClass(Logger.pluginClassName);
        }
        catch (Throwable e) {
            Logger.pluginClass = ((Logger.class$org$jboss$logging$NullLoggerPlugin == null) ? (Logger.class$org$jboss$logging$NullLoggerPlugin = class$("org.jboss.logging.NullLoggerPlugin")) : Logger.class$org$jboss$logging$NullLoggerPlugin);
        }
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError().initCause(x);
        }
    }
    
    static {
        Logger.PLUGIN_CLASS_PROP = "org.jboss.logging.Logger.pluginClass";
        Logger.pluginClass = null;
        Logger.pluginClassName = null;
        init();
    }
}
