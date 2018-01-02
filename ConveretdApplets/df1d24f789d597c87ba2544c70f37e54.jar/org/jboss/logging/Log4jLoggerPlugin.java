// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.logging;

import org.apache.log4j.Priority;
import org.apache.log4j.Level;
import org.apache.log4j.Category;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Log4jLoggerPlugin implements LoggerPlugin
{
    private static final String FQCN;
    private transient Logger log;
    static /* synthetic */ Class class$org$jboss$logging$Logger;
    
    public void init(final String name) {
        this.log = LogManager.getLogger(name);
    }
    
    public Category getCategory() {
        return (Category)this.log;
    }
    
    public Logger getLogger() {
        return this.log;
    }
    
    public boolean isTraceEnabled() {
        final Level l = Level.TRACE;
        return this.log.isEnabledFor((Priority)l) && l.isGreaterOrEqual((Priority)this.log.getEffectiveLevel());
    }
    
    public void trace(final Object message) {
        this.log.log(Log4jLoggerPlugin.FQCN, (Priority)Level.TRACE, message, (Throwable)null);
    }
    
    public void trace(final Object message, final Throwable t) {
        this.log.log(Log4jLoggerPlugin.FQCN, (Priority)Level.TRACE, message, t);
    }
    
    public boolean isDebugEnabled() {
        final Level l = Level.DEBUG;
        return this.log.isEnabledFor((Priority)l) && l.isGreaterOrEqual((Priority)this.log.getEffectiveLevel());
    }
    
    public void debug(final Object message) {
        this.log.log(Log4jLoggerPlugin.FQCN, (Priority)Level.DEBUG, message, (Throwable)null);
    }
    
    public void debug(final Object message, final Throwable t) {
        this.log.log(Log4jLoggerPlugin.FQCN, (Priority)Level.DEBUG, message, t);
    }
    
    public boolean isInfoEnabled() {
        final Level l = Level.INFO;
        return this.log.isEnabledFor((Priority)l) && l.isGreaterOrEqual((Priority)this.log.getEffectiveLevel());
    }
    
    public void info(final Object message) {
        this.log.log(Log4jLoggerPlugin.FQCN, (Priority)Level.INFO, message, (Throwable)null);
    }
    
    public void info(final Object message, final Throwable t) {
        this.log.log(Log4jLoggerPlugin.FQCN, (Priority)Level.INFO, message, t);
    }
    
    public void warn(final Object message) {
        this.log.log(Log4jLoggerPlugin.FQCN, (Priority)Level.WARN, message, (Throwable)null);
    }
    
    public void warn(final Object message, final Throwable t) {
        this.log.log(Log4jLoggerPlugin.FQCN, (Priority)Level.WARN, message, t);
    }
    
    public void error(final Object message) {
        this.log.log(Log4jLoggerPlugin.FQCN, (Priority)Level.ERROR, message, (Throwable)null);
    }
    
    public void error(final Object message, final Throwable t) {
        this.log.log(Log4jLoggerPlugin.FQCN, (Priority)Level.ERROR, message, t);
    }
    
    public void fatal(final Object message) {
        this.log.log(Log4jLoggerPlugin.FQCN, (Priority)Level.FATAL, message, (Throwable)null);
    }
    
    public void fatal(final Object message, final Throwable t) {
        this.log.log(Log4jLoggerPlugin.FQCN, (Priority)Level.FATAL, message, t);
    }
    
    public void log(final Priority p, final Object message) {
        this.log.log(Log4jLoggerPlugin.FQCN, p, message, (Throwable)null);
    }
    
    public void log(final Priority p, final Object message, final Throwable t) {
        this.log.log(Log4jLoggerPlugin.FQCN, p, message, t);
    }
    
    public void log(final Level l, final Object message) {
        this.log.log(Log4jLoggerPlugin.FQCN, (Priority)l, message, (Throwable)null);
    }
    
    public void log(final Level l, final Object message, final Throwable t) {
        this.log.log(Log4jLoggerPlugin.FQCN, (Priority)l, message, t);
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
        FQCN = ((Log4jLoggerPlugin.class$org$jboss$logging$Logger == null) ? (Log4jLoggerPlugin.class$org$jboss$logging$Logger = class$("org.jboss.logging.Logger")) : Log4jLoggerPlugin.class$org$jboss$logging$Logger).getName();
    }
}
