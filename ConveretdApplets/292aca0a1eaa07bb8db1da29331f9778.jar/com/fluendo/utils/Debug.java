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
    
    public static void log(final int lev, final String line) {
        if (lev <= Debug.level) {
            System.out.println(Debug.prefix[lev] + line);
        }
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
        Debug.level = 3;
        Debug.counter = 0;
        prefix = new String[] { "[NONE] ", "[ERRO] ", "[WARN] ", "[INFO] ", "[DBUG] " };
    }
}
