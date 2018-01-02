// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.debug;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.apache.mina.core.future.IoFuture;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public final class Log
{
    private static final String ERROR;
    private static final String WARN;
    private static final String INFO;
    private static final String DEBUG;
    private static final String VERBOSE;
    private static StringBuilder buffer;
    private static Calendar calendar;
    private static Date now;
    private static SimpleDateFormat dateFormat;
    
    public static void error(final String s) {
        print(Log.ERROR, s);
    }
    
    public static void warn(final String s) {
        print(Log.WARN, s);
    }
    
    public static void info(final String s) {
        print(Log.INFO, s);
    }
    
    public static void debug(final String s) {
        print(Log.DEBUG, s);
    }
    
    public static void verbose(final String s) {
        print(Log.VERBOSE, s);
    }
    
    private static synchronized void print(final String s, final String s2) {
        if (s != null) {
            if (Log.calendar == null) {
                Log.calendar = Calendar.getInstance();
                Log.now = new Date();
                Log.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }
            Log.now.setTime(System.currentTimeMillis());
            Log.buffer.delete(0, Log.buffer.capacity());
            Log.buffer.append('[').append(Log.dateFormat.format(Log.now)).append("] ");
            Log.buffer.append('[').append(Thread.currentThread().getName()).append("] ");
            Log.buffer.append(s).append(": ").append(s2);
            System.out.println(Log.buffer.toString());
        }
    }
    
    public static void error(final String s, final Throwable t) {
        print(Log.ERROR, s, t);
    }
    
    public static void warn(final String s, final Throwable t) {
        print(Log.WARN, s, t);
    }
    
    private static synchronized void print(final String s, final String s2, final Throwable t) {
        print(s, s2);
        print(s, t.toString());
        final StackTraceElement[] stackTrace = t.getStackTrace();
        for (int i = 0; i < stackTrace.length; ++i) {
            print(s, "    " + stackTrace[i].toString());
        }
        if (t.getCause() != null) {
            final Throwable cause = t.getCause();
            print(s, "Caused by:");
            print(s, cause.toString());
            final StackTraceElement[] stackTrace2 = cause.getStackTrace();
            for (int j = 0; j < stackTrace2.length; ++j) {
                print(s, "    " + stackTrace2[j].toString());
            }
        }
    }
    
    public static void warn(final String s, final Object... array) {
        print(Log.WARN, s, array);
    }
    
    private static synchronized void print(final String s, String replaceFirst, final Object... array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            replaceFirst = replaceFirst.replaceFirst("\\{\\}", array[i].toString());
        }
        print(s, replaceFirst);
    }
    
    static {
        ERROR = "ERROR";
        WARN = "WARN";
        INFO = "INFO";
        DEBUG = "DEBUG";
        VERBOSE = "VERBOSE";
        Log.buffer = new StringBuilder();
    }
    
    public interface Listener<T extends IoFuture>
    {
    }
}
