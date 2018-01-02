// 
// Decompiled by Procyon v0.5.30
// 

package com.postx.util.logging;

import java.util.Vector;

public class Logger
{
    public static final String Ident = "$Id: Logger.java,v 1.3 2009/08/03 03:36:01 blm Exp $";
    public static final Logger global;
    private Vector handlers;
    private Level level;
    
    public void finer(final String s) {
        this.log(Level.FINER, s);
    }
    
    public void log(final Level level, final String s) {
        if (!this.isLoggable(level)) {
            return;
        }
        this.log(new LogRecord(level, s));
    }
    
    public void log(final Level level, final String s, final Throwable thrown) {
        if (!this.isLoggable(level)) {
            return;
        }
        final LogRecord logRecord = new LogRecord(level, s);
        logRecord.setThrown(thrown);
        this.log(logRecord);
    }
    
    public void log(final LogRecord logRecord) {
        for (int size = this.handlers.size(), i = 0; i < size; ++i) {
            ((Handler)this.handlers.elementAt(i)).publish(logRecord);
        }
    }
    
    private static String formatValue(final Object o) {
        if (o == null) {
            return "null";
        }
        if (o instanceof String) {
            return "\"" + o + "\"";
        }
        if (o.getClass().isArray()) {
            return "[" + formatArray((Object[])o) + "]";
        }
        return o.toString();
    }
    
    public void warning(final String s) {
        this.log(Level.WARNING, s);
    }
    
    protected Logger() {
        this.handlers = new Vector();
        this.level = Level.INFO;
    }
    
    public void entering(final String s, final String s2, final Object[] array) {
        final Level finer = Level.FINER;
        if (!this.isLoggable(finer)) {
            return;
        }
        final StringBuffer append = new StringBuffer("ENTRY ").append(s).append(".").append(s2).append("(");
        if (array != null) {
            append.append(formatArray(array));
        }
        append.append(")");
        this.log(finer, append.toString());
    }
    
    public void exiting(final String s, final String s2) {
        final Level finer = Level.FINER;
        if (!this.isLoggable(finer)) {
            return;
        }
        this.log(finer, "RETURN " + s + "." + s2);
    }
    
    public void exiting(final String s, final String s2, final Object o) {
        final Level finer = Level.FINER;
        if (!this.isLoggable(finer)) {
            return;
        }
        this.log(finer, "RETURN " + s + "." + s2 + " -> " + formatValue(o));
    }
    
    public void info(final String s) {
        this.log(Level.INFO, s);
    }
    
    public void config(final String s) {
        this.log(Level.CONFIG, s);
    }
    
    private static String formatArray(final Object[] array) {
        final StringBuffer sb = new StringBuffer();
        for (int length = array.length, i = 0; i < length; ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(formatValue(array[i]));
        }
        return sb.toString();
    }
    
    public void finest(final String s) {
        this.log(Level.FINEST, s);
    }
    
    static {
        (global = new Logger()).addHandler(new ConsoleHandler());
    }
    
    public void severe(final String s) {
        this.log(Level.SEVERE, s);
    }
    
    public void addHandler(final Handler handler) {
        this.handlers.addElement(handler);
    }
    
    public void fine(final String s) {
        this.log(Level.FINE, s);
    }
    
    public void setLevel(final Level level) {
        this.level = level;
    }
    
    public boolean isLoggable(final Level level) {
        return level.intValue() >= this.level.intValue();
    }
}
