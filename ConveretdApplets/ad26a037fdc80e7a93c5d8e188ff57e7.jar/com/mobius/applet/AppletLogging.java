// 
// Decompiled by Procyon v0.5.30
// 

package com.mobius.applet;

import java.util.Properties;
import com.mobius.logging.LoggingApi;
import com.mobius.logging.LogMessageType;

public class AppletLogging
{
    public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    public static final String COMMTRACE_KEY = "commtrace";
    public static final String MEMTRACE_KEY = "memtrace";
    public static final String FLOWTRACE_KEY = "flowtrace";
    public static final String ACTIONTRACE_KEY = "actiontrace";
    public static final String DEFECTTRACE_KEY = "defecttrace";
    public static final String ALLTRACE_KEY = "alltrace";
    public static final String THREADINFO_KEY = "threadinfo";
    public static final LogMessageType commtrace;
    public static final LogMessageType memtrace;
    public static final LogMessageType flowtrace;
    public static final LogMessageType actiontrace;
    public static final LogMessageType defecttrace;
    public static final LoggingApi logger;
    
    public static synchronized void loadTracingPreferences(final Properties properties) {
        if (properties == null) {
            return;
        }
        final String property = properties.getProperty("alltrace");
        if (property != null && property.length() != 0) {
            final Boolean value = Boolean.valueOf(property);
            AppletLogging.logger.Configure(AppletLogging.commtrace, value);
            AppletLogging.logger.Configure(AppletLogging.memtrace, value);
            AppletLogging.logger.Configure(AppletLogging.flowtrace, value);
            AppletLogging.logger.Configure(AppletLogging.actiontrace, value);
        }
        AppletLogging.logger.Configure(AppletLogging.defecttrace, true);
        final String property2 = properties.getProperty("commtrace");
        if (property2 != null && property2.length() != 0) {
            AppletLogging.logger.Configure(AppletLogging.commtrace, Boolean.valueOf(property2));
        }
        final String property3 = properties.getProperty("memtrace");
        if (property3 != null && property3.length() != 0) {
            AppletLogging.logger.Configure(AppletLogging.memtrace, Boolean.valueOf(property3));
        }
        final String property4 = properties.getProperty("flowtrace");
        if (property4 != null && property4.length() != 0) {
            AppletLogging.logger.Configure(AppletLogging.flowtrace, Boolean.valueOf(property4));
        }
        final String property5 = properties.getProperty("actiontrace");
        if (property5 != null && property5.length() != 0) {
            AppletLogging.logger.Configure(AppletLogging.actiontrace, Boolean.valueOf(property5));
        }
        final String property6 = properties.getProperty("threadinfo");
        if (property6 != null && property6.length() != 0) {
            AppletLogging.logger.EnableThreadInfo(Boolean.valueOf(property6));
        }
        else {
            AppletLogging.logger.EnableThreadInfo(false);
        }
    }
    
    public static void showMemoryUsage(final Object o) {
        final Runtime runtime = Runtime.getRuntime();
        final long freeMemory = runtime.freeMemory();
        final long totalMemory = runtime.totalMemory();
        AppletLogging.logger.Write(AppletLogging.memtrace, o, "Memory Used:" + (totalMemory - freeMemory), " Free:" + freeMemory, " Total:" + totalMemory);
    }
    
    public static void showDebuggingFlags() {
        AppletLogging.logger.Write(AppletLogging.commtrace, null, "Commtrace enabled.");
        AppletLogging.logger.Write(AppletLogging.memtrace, null, "Memtrace enabled.");
        AppletLogging.logger.Write(AppletLogging.flowtrace, null, "Flowtrace enabled.");
        AppletLogging.logger.Write(AppletLogging.actiontrace, null, "Actiontrace enabled.");
    }
    
    static {
        commtrace = LogMessageType.defineType("commtrace");
        memtrace = LogMessageType.defineType("memtrace");
        flowtrace = LogMessageType.defineType("flowtrace");
        actiontrace = LogMessageType.defineType("actiontrace");
        defecttrace = LogMessageType.defineType("defecttrace");
        logger = LoggingApi.GetSystemLogger();
    }
}
