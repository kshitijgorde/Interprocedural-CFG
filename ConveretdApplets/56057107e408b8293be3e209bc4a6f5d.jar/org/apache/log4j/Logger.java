// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j;

import org.apache.log4j.spi.LoggerFactory;

public class Logger extends Category
{
    private static final String FQCN;
    static /* synthetic */ Class class$org$apache$log4j$Logger;
    
    protected Logger(final String s) {
        super(s);
    }
    
    public static Logger getLogger(final String s) {
        return LogManager.getLogger(s);
    }
    
    public static Logger getLogger(final Class clazz) {
        return LogManager.getLogger(clazz.getName());
    }
    
    public static Logger getRootLogger() {
        return LogManager.getRootLogger();
    }
    
    public static Logger getLogger(final String s, final LoggerFactory loggerFactory) {
        return LogManager.getLogger(s, loggerFactory);
    }
    
    public void trace(final Object o) {
        if (super.repository.isDisabled(5000)) {
            return;
        }
        if (Level.TRACE.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(Logger.FQCN, Level.TRACE, o, null);
        }
    }
    
    public void trace(final Object o, final Throwable t) {
        if (super.repository.isDisabled(5000)) {
            return;
        }
        if (Level.TRACE.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(Logger.FQCN, Level.TRACE, o, t);
        }
    }
    
    public boolean isTraceEnabled() {
        return !super.repository.isDisabled(5000) && Level.TRACE.isGreaterOrEqual(this.getEffectiveLevel());
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        FQCN = ((Logger.class$org$apache$log4j$Logger == null) ? (Logger.class$org$apache$log4j$Logger = class$("org.apache.log4j.Logger")) : Logger.class$org$apache$log4j$Logger).getName();
    }
}
