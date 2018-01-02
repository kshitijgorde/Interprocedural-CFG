// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.util;

import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;

public class Log
{
    private int debuglevel;
    private LogTarget[] logTargets;
    private HashMap logContexts;
    private static Log singleton;
    
    protected Log() {
        this.logContexts = new HashMap();
        this.logTargets = new LogTarget[0];
        this.debuglevel = 100;
    }
    
    public static synchronized Log getInstance() {
        if (Log.singleton == null) {
            Log.singleton = new Log();
        }
        return Log.singleton;
    }
    
    protected static synchronized void defineLog(final Log singleton) {
        Log.singleton = singleton;
    }
    
    public int getDebuglevel() {
        return this.debuglevel;
    }
    
    protected void setDebuglevel(final int debuglevel) {
        this.debuglevel = debuglevel;
    }
    
    public synchronized void addTarget(final LogTarget logTarget) {
        if (logTarget == null) {
            throw new NullPointerException();
        }
        final LogTarget[] logTargets = new LogTarget[this.logTargets.length + 1];
        System.arraycopy(this.logTargets, 0, logTargets, 0, this.logTargets.length);
        logTargets[this.logTargets.length] = logTarget;
        this.logTargets = logTargets;
    }
    
    public synchronized void removeTarget(final LogTarget logTarget) {
        if (logTarget == null) {
            throw new NullPointerException();
        }
        final ArrayList list = new ArrayList();
        list.addAll(Arrays.asList(this.logTargets));
        list.remove(logTarget);
        this.logTargets = list.toArray(new LogTarget[list.size()]);
    }
    
    public LogTarget[] getTargets() {
        final LogTarget[] array = new LogTarget[this.logTargets.length];
        System.arraycopy(array, 0, this.logTargets, 0, this.logTargets.length);
        return array;
    }
    
    public synchronized void replaceTargets(final LogTarget logTarget) {
        if (logTarget == null) {
            throw new NullPointerException();
        }
        this.logTargets = new LogTarget[] { logTarget };
    }
    
    public static void debug(final Object o) {
        log(3, o);
    }
    
    public static void debug(final Object o, final Exception ex) {
        log(3, o, ex);
    }
    
    public static void info(final Object o) {
        log(2, o);
    }
    
    public static void info(final Object o, final Exception ex) {
        log(2, o, ex);
    }
    
    public static void warn(final Object o) {
        log(1, o);
    }
    
    public static void warn(final Object o, final Exception ex) {
        log(1, o, ex);
    }
    
    public static void error(final Object o) {
        log(0, o);
    }
    
    public static void error(final Object o, final Exception ex) {
        log(0, o, ex);
    }
    
    protected void doLog(int n, final Object o) {
        if (n > 3) {
            n = 3;
        }
        if (n <= this.debuglevel) {
            for (int i = 0; i < this.logTargets.length; ++i) {
                this.logTargets[i].log(n, o);
            }
        }
    }
    
    public static void log(final int n, final Object o) {
        getInstance().doLog(n, o);
    }
    
    public static void log(final int n, final Object o, final Exception ex) {
        getInstance().doLog(n, o, ex);
    }
    
    protected void doLog(int n, final Object o, final Exception ex) {
        if (n > 3) {
            n = 3;
        }
        if (n <= this.debuglevel) {
            for (int i = 0; i < this.logTargets.length; ++i) {
                this.logTargets[i].log(n, o, ex);
            }
        }
    }
    
    protected void init() {
    }
    
    public static boolean isDebugEnabled() {
        return getInstance().getDebuglevel() <= 3;
    }
    
    public static boolean isInfoEnabled() {
        return getInstance().getDebuglevel() <= 2;
    }
    
    public static boolean isWarningEnabled() {
        return getInstance().getDebuglevel() <= 1;
    }
    
    public static boolean isErrorEnabled() {
        return getInstance().getDebuglevel() <= 0;
    }
    
    public static LogContext createContext(final Class clazz) {
        return createContext(clazz.getName());
    }
    
    public static LogContext createContext(final String s) {
        return getInstance().internalCreateContext(s);
    }
    
    protected LogContext internalCreateContext(final String s) {
        synchronized (this.logContexts) {
            LogContext logContext = this.logContexts.get(s);
            if (logContext == null) {
                logContext = new LogContext(s);
                this.logContexts.put(s, logContext);
            }
            return logContext;
        }
    }
    
    public static class SimpleMessage
    {
        private String message;
        private Object[] param;
        
        public SimpleMessage(final String message, final Object o) {
            this.message = message;
            this.param = new Object[] { o };
        }
        
        public SimpleMessage(final String message, final Object o, final Object o2) {
            this.message = message;
            this.param = new Object[] { o, o2 };
        }
        
        public SimpleMessage(final String message, final Object o, final Object o2, final Object o3) {
            this.message = message;
            this.param = new Object[] { o, o2, o3 };
        }
        
        public SimpleMessage(final String message, final Object o, final Object o2, final Object o3, final Object o4) {
            this.message = message;
            this.param = new Object[] { o, o2, o3, o4 };
        }
        
        public SimpleMessage(final String message, final Object[] param) {
            this.message = message;
            this.param = param;
        }
        
        public String toString() {
            final StringBuffer sb = new StringBuffer();
            sb.append(this.message);
            if (this.param != null) {
                for (int i = 0; i < this.param.length; ++i) {
                    sb.append(this.param[i]);
                }
            }
            return sb.toString();
        }
    }
}
