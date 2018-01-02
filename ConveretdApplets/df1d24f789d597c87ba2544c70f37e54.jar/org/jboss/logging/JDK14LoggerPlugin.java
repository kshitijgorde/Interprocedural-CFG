// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

public class JDK14LoggerPlugin implements LoggerPlugin
{
    private Logger log;
    
    public void init(final String name) {
        this.log = Logger.getLogger(name);
    }
    
    public boolean isTraceEnabled() {
        return this.log.isLoggable(Level.FINER);
    }
    
    public void trace(final Object message) {
        this.log.finer(message.toString());
    }
    
    public void trace(final Object message, final Throwable t) {
        this.log.log(Level.FINER, message.toString(), t);
    }
    
    public boolean isDebugEnabled() {
        return this.log.isLoggable(Level.FINE);
    }
    
    public void debug(final Object message) {
        this.log.fine(message.toString());
    }
    
    public void debug(final Object message, final Throwable t) {
        this.log.log(Level.FINE, message.toString(), t);
    }
    
    public boolean isInfoEnabled() {
        return this.log.isLoggable(Level.INFO);
    }
    
    public void info(final Object message) {
        this.log.info(message.toString());
    }
    
    public void info(final Object message, final Throwable t) {
        this.log.log(Level.INFO, message.toString(), t);
    }
    
    public void warn(final Object message) {
        this.log.warning(message.toString());
    }
    
    public void warn(final Object message, final Throwable t) {
        this.log.log(Level.WARNING, message.toString(), t);
    }
    
    public void error(final Object message) {
        this.log.severe(message.toString());
    }
    
    public void error(final Object message, final Throwable t) {
        this.log.log(Level.SEVERE, message.toString(), t);
    }
    
    public void fatal(final Object message) {
        this.log.severe(message.toString());
    }
    
    public void fatal(final Object message, final Throwable t) {
        this.log.log(Level.SEVERE, message.toString(), t);
    }
}
