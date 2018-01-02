// 
// Decompiled by Procyon v0.5.30
// 

package org.slf4j;

public final class LoggerFactory
{
    private static Logger LOGGER;
    
    public static Logger getLogger$4ecaad6a() {
        return LoggerFactory.LOGGER;
    }
    
    static {
        LoggerFactory.LOGGER = new Logger();
    }
}
