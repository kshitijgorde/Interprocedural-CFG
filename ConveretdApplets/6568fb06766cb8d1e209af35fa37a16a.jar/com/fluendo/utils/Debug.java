// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.utils;

public class Debug
{
    public static final int NONE = 0;
    public static final int ERROR = 1;
    public static final int WARNING = 2;
    public static final int INFO = 3;
    public static final int DEBUG = 4;
    public static int level;
    private static int counter;
    private static long startTime;
    public static final String[] prefix;
    static /* synthetic */ Class class$com$fluendo$utils$Debug;
    
    public static final int genId() {
        Class class$;
        Class class$com$fluendo$utils$Debug;
        if (Debug.class$com$fluendo$utils$Debug == null) {
            class$com$fluendo$utils$Debug = (Debug.class$com$fluendo$utils$Debug = (class$ = class$("com.fluendo.utils.Debug")));
        }
        else {
            class$ = (class$com$fluendo$utils$Debug = Debug.class$com$fluendo$utils$Debug);
        }
        final Class clazz = class$com$fluendo$utils$Debug;
        synchronized (class$) {
            return Debug.counter++;
        }
    }
    
    public static String rpad(final String s, final int n) {
        if (n > s.length()) {
            final int n2 = n - s.length();
            final char[] array = new char[n2];
            for (int i = 0; i < n2; ++i) {
                array[i] = ' ';
            }
            return s + new String(array);
        }
        return s;
    }
    
    public static void log(final int n, final String s) {
        final long currentTimeMillis = System.currentTimeMillis();
        if (Debug.startTime == 0L) {
            Debug.startTime = currentTimeMillis;
        }
        final long n2 = currentTimeMillis - Debug.startTime;
        if (n <= Debug.level) {
            if (Debug.level >= 4) {
                System.out.println("[" + rpad(Thread.currentThread().getName(), 30) + " " + rpad(Long.toString(n2), 6) + " " + Debug.prefix[n] + "] " + s);
            }
            else {
                System.out.println("[" + Debug.prefix[n] + "] " + s);
            }
        }
    }
    
    public static void error(final String s) {
        log(1, s);
    }
    
    public static void warning(final String s) {
        log(2, s);
    }
    
    public static void warn(final String s) {
        log(2, s);
    }
    
    public static void info(final String s) {
        log(3, s);
    }
    
    public static void debug(final String s) {
        log(4, s);
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
        Debug.level = 3;
        Debug.counter = 0;
        Debug.startTime = 0L;
        prefix = new String[] { "NONE", "ERRO", "WARN", "INFO", "DBUG" };
    }
}
