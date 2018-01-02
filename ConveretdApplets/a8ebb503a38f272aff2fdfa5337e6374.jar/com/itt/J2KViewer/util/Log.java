// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.util;

import java.util.logging.Level;
import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Log
{
    private Logger logger;
    private static LogManager mgr;
    static /* synthetic */ Class class$com$itt$J2KViewer$util$Log;
    
    public Log(final Class clazz) {
        this.logger = null;
        this.logger = Logger.getLogger(clazz.getName());
        if (Log.mgr == null) {
            this.loadLoggerConfig();
        }
    }
    
    private void loadLoggerConfig() {
        try {
            (Log.mgr = LogManager.getLogManager()).readConfiguration(((Log.class$com$itt$J2KViewer$util$Log == null) ? (Log.class$com$itt$J2KViewer$util$Log = class$("com.itt.J2KViewer.util.Log")) : Log.class$com$itt$J2KViewer$util$Log).getResource("/logging.properties").openStream());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        catch (NullPointerException ex2) {
            ex2.printStackTrace();
        }
    }
    
    public void debuglowest(final String s) {
        this.inferAndLog(Level.FINEST, s, null);
    }
    
    public void debuglowest(final String s, final Throwable t) {
        this.inferAndLog(Level.FINEST, s, t);
    }
    
    public void debuglow(final String s) {
        this.inferAndLog(Level.FINER, s, null);
    }
    
    public void debuglow(final String s, final Throwable t) {
        this.inferAndLog(Level.FINER, s, t);
    }
    
    public void debug(final String s) {
        this.inferAndLog(Level.FINE, s, null);
    }
    
    public void debug(final String s, final Throwable t) {
        this.inferAndLog(Level.FINE, s, t);
    }
    
    public void info(final String s) {
        this.inferAndLog(Level.INFO, s, null);
    }
    
    public void info(final String s, final Throwable t) {
        this.inferAndLog(Level.INFO, s, t);
    }
    
    public void warn(final String s) {
        this.inferAndLog(Level.WARNING, s, null);
    }
    
    public void warn(final String s, final Throwable t) {
        this.inferAndLog(Level.WARNING, s, t);
    }
    
    public void error(final String s) {
        this.inferAndLog(Level.SEVERE, s, null);
    }
    
    public void error(final String s, final Throwable t) {
        this.inferAndLog(Level.SEVERE, s, t);
    }
    
    public void error(final Throwable t) {
        this.inferAndLog(Level.SEVERE, "", t);
    }
    
    public void fatal(final String s) {
        this.inferAndLog(Level.SEVERE, s, null);
    }
    
    public void fatal(final String s, final Throwable t) {
        this.inferAndLog(Level.SEVERE, s, t);
    }
    
    public void fatal(final Throwable t) {
        this.inferAndLog(Level.SEVERE, "", t);
    }
    
    public boolean isDebugLowestEnabled() {
        return this.logger.isLoggable(Level.FINEST);
    }
    
    public boolean isDebugLowEnabled() {
        return this.logger.isLoggable(Level.FINER);
    }
    
    public boolean isDebugEnabled() {
        return this.logger.isLoggable(Level.FINE);
    }
    
    public boolean isInfoEnabled() {
        return this.logger.isLoggable(Level.INFO);
    }
    
    public boolean isWarnEnabled() {
        return this.logger.isLoggable(Level.WARNING);
    }
    
    public void entering() {
        final StackTraceElement stackTraceElement = this.getStackTraceElement();
        if (stackTraceElement != null) {
            this.logger.entering(stackTraceElement.getClassName(), stackTraceElement.getMethodName());
        }
    }
    
    public void exiting() {
        final StackTraceElement stackTraceElement = this.getStackTraceElement();
        if (stackTraceElement != null) {
            this.logger.exiting(stackTraceElement.getClassName(), stackTraceElement.getMethodName());
        }
    }
    
    private void inferAndLog(final Level level, final String s, final Throwable t) {
        final StackTraceElement stackTraceElement = this.getStackTraceElement();
        if (stackTraceElement != null) {
            this.logger.logp(level, stackTraceElement.getClassName(), stackTraceElement.getMethodName(), s, t);
        }
    }
    
    private StackTraceElement getStackTraceElement() {
        StackTraceElement[] stackTrace;
        int n;
        for (stackTrace = new Throwable().getStackTrace(), n = 1; n < stackTrace.length && stackTrace[n].getClassName().endsWith("util.Log"); ++n) {}
        if (n < stackTrace.length) {
            return stackTrace[n];
        }
        return null;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
    
    static {
        Log.mgr = null;
    }
}
