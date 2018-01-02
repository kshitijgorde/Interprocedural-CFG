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
    public static final String[] prefix;
    
    public static void log(final int n, final String s) {
        if (n <= Debug.level) {
            System.out.println(Debug.prefix[n] + s);
        }
    }
    
    static {
        Debug.level = 3;
        prefix = new String[] { "[NONE] ", "[ERRO] ", "[WARN] ", "[INFO] ", "[DBUG] " };
    }
}
