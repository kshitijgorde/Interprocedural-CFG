// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.logging;

public class NullLoggerPlugin implements LoggerPlugin
{
    public void init(final String name) {
    }
    
    public boolean isTraceEnabled() {
        return false;
    }
    
    public void trace(final Object message) {
    }
    
    public void trace(final Object message, final Throwable t) {
    }
    
    public boolean isDebugEnabled() {
        return false;
    }
    
    public void debug(final Object message) {
    }
    
    public void debug(final Object message, final Throwable t) {
    }
    
    public boolean isInfoEnabled() {
        return false;
    }
    
    public void info(final Object message) {
    }
    
    public void info(final Object message, final Throwable t) {
    }
    
    public void error(final Object message) {
    }
    
    public void error(final Object message, final Throwable t) {
    }
    
    public void fatal(final Object message) {
    }
    
    public void fatal(final Object message, final Throwable t) {
    }
    
    public void warn(final Object message) {
    }
    
    public void warn(final Object message, final Throwable t) {
    }
}
