// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j.helpers;

public class LogLog
{
    public static final String DEBUG_KEY = "log4j.debug";
    public static final String CONFIG_DEBUG_KEY = "log4j.configDebug";
    protected static boolean debugEnabled;
    private static boolean quietMode;
    private static final String PREFIX = "log4j: ";
    private static final String ERR_PREFIX = "log4j:ERROR ";
    private static final String WARN_PREFIX = "log4j:WARN ";
    
    public static void setInternalDebugging(final boolean debugEnabled) {
        LogLog.debugEnabled = debugEnabled;
    }
    
    public static void debug(final String s) {
        if (LogLog.debugEnabled && !LogLog.quietMode) {
            System.out.println("log4j: " + s);
        }
    }
    
    public static void debug(final String s, final Throwable t) {
        if (LogLog.debugEnabled && !LogLog.quietMode) {
            System.out.println("log4j: " + s);
            if (t != null) {
                t.printStackTrace(System.out);
            }
        }
    }
    
    public static void error(final String s) {
        if (LogLog.quietMode) {
            return;
        }
        System.err.println("log4j:ERROR " + s);
    }
    
    public static void error(final String s, final Throwable t) {
        if (LogLog.quietMode) {
            return;
        }
        System.err.println("log4j:ERROR " + s);
        if (t != null) {
            t.printStackTrace();
        }
    }
    
    public static void setQuietMode(final boolean quietMode) {
        LogLog.quietMode = quietMode;
    }
    
    public static void warn(final String s) {
        if (LogLog.quietMode) {
            return;
        }
        System.err.println("log4j:WARN " + s);
    }
    
    public static void warn(final String s, final Throwable t) {
        if (LogLog.quietMode) {
            return;
        }
        System.err.println("log4j:WARN " + s);
        if (t != null) {
            t.printStackTrace();
        }
    }
    
    static {
        LogLog.debugEnabled = false;
        LogLog.quietMode = false;
        String s = OptionConverter.getSystemProperty("log4j.debug", null);
        if (s == null) {
            s = OptionConverter.getSystemProperty("log4j.configDebug", null);
        }
        if (s != null) {
            LogLog.debugEnabled = OptionConverter.toBoolean(s, true);
        }
    }
}
