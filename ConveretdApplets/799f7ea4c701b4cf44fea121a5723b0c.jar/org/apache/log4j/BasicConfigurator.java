// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j;

public class BasicConfigurator
{
    public static void configure() {
        final Logger root = Logger.getRootLogger();
        root.addAppender(new ConsoleAppender(new PatternLayout("%r [%t] %p %c %x - %m%n")));
    }
    
    public static void configure(final Appender appender) {
        final Logger root = Logger.getRootLogger();
        root.addAppender(appender);
    }
    
    public static void resetConfiguration() {
        LogManager.resetConfiguration();
    }
}
