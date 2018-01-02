// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.logging;

public class DynamicLogger extends Logger
{
    private static final long serialVersionUID = -5963699806863917370L;
    public static final int LOG_LEVEL_NONE = 0;
    public static final int LOG_LEVEL_FATAL = 1;
    public static final int LOG_LEVEL_ERROR = 2;
    public static final int LOG_LEVEL_WARN = 3;
    public static final int LOG_LEVEL_INFO = 4;
    public static final int LOG_LEVEL_DEBUG = 5;
    public static final int LOG_LEVEL_TRACE = 6;
    public static final String[] LOG_LEVEL_STRINGS;
    private int logLevel;
    
    protected DynamicLogger(final String name) {
        super(name);
        this.logLevel = 5;
    }
    
    public static DynamicLogger getDynamicLogger(final String name) {
        return new DynamicLogger(name);
    }
    
    public static DynamicLogger getDynamicLogger(final String name, final String suffix) {
        return new DynamicLogger(name + "." + suffix);
    }
    
    public static DynamicLogger getDynamicLogger(final Class clazz) {
        return new DynamicLogger(clazz.getName());
    }
    
    public static DynamicLogger getDynamicLogger(final Class clazz, final String suffix) {
        return new DynamicLogger(clazz.getName() + "." + suffix);
    }
    
    public void setLogLevel(final int logLevel) {
        if (logLevel >= 0 && logLevel <= 6) {
            this.logLevel = logLevel;
        }
    }
    
    public int getLogLevel() {
        return this.logLevel;
    }
    
    public void setLogLevelAsString(String logLevelString) {
        if (logLevelString != null) {
            logLevelString = logLevelString.toUpperCase().trim();
            for (int i = 0; i <= 6; ++i) {
                if (logLevelString.equals(DynamicLogger.LOG_LEVEL_STRINGS[i])) {
                    this.logLevel = i;
                    break;
                }
            }
        }
    }
    
    public String getLogLevelAsString() {
        return DynamicLogger.LOG_LEVEL_STRINGS[this.logLevel];
    }
    
    public void log(final Object message) {
        switch (this.logLevel) {
            case 6: {
                super.trace(message);
                break;
            }
            case 5: {
                super.debug(message);
                break;
            }
            case 4: {
                super.info(message);
                break;
            }
            case 3: {
                super.warn(message);
                break;
            }
            case 2: {
                super.error(message);
                break;
            }
            case 1: {
                super.fatal(message);
                break;
            }
        }
    }
    
    public void log(final Object message, final Throwable t) {
        switch (this.logLevel) {
            case 6: {
                super.trace(message, t);
                break;
            }
            case 5: {
                super.debug(message, t);
                break;
            }
            case 4: {
                super.info(message, t);
                break;
            }
            case 3: {
                super.warn(message, t);
                break;
            }
            case 2: {
                super.error(message, t);
                break;
            }
            case 1: {
                super.fatal(message, t);
                break;
            }
        }
    }
    
    static {
        LOG_LEVEL_STRINGS = new String[] { "NONE", "FATAL", "ERROR", "WARN", "INFO", "DEBUG", "TRACE" };
    }
}
