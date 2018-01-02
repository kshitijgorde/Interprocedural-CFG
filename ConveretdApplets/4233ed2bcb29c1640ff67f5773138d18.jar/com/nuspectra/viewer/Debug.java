// 
// Decompiled by Procyon v0.5.30
// 

package com.nuspectra.viewer;

import java.util.Date;
import java.io.PrintStream;

class Debug
{
    private static boolean stack;
    protected static PrintStream out;
    protected static boolean max;
    protected static String strout;
    
    static {
        Debug.stack = true;
        Debug.out = System.out;
        Debug.max = false;
        Debug.strout = "";
    }
    
    protected static void println(final Exception e) {
        if (Debug.out != null) {
            Debug.out.println(" " + getTimeString() + " " + e.toString());
        }
    }
    
    protected static void setDebugStack(final boolean state) {
        Debug.stack = state;
    }
    
    protected static boolean getDebugStack() {
        return Debug.stack;
    }
    
    protected static void set(final boolean v) {
        Debug.out = (v ? System.out : null);
    }
    
    protected static boolean toggle() {
        set(!get());
        return get();
    }
    
    protected static boolean get() {
        return Debug.out != null;
    }
    
    protected static void set(final NuApplet a) {
        if (a == null) {
            return;
        }
        String string = a.getParameter("debug");
        if (string == null) {
            set(false);
            return;
        }
        string = string.toUpperCase();
        if (string.indexOf("ON") != -1) {
            set(a.debug = true);
        }
        if (string.indexOf("OFF") != -1) {
            set(false);
        }
        if (string.indexOf("MAX") != -1) {
            a.debug = true;
            Debug.max = true;
            set(Debug.stack = true);
        }
    }
    
    private static synchronized String getTimeString() {
        String string = String.valueOf(Thread.currentThread().getName()) + " ";
        string = String.valueOf(string) + new Date().toString().substring(11, 19);
        string = String.valueOf(string) + "." + System.currentTimeMillis() % 100L;
        return string;
    }
    
    protected static void println(final String string) {
        if (Debug.out != null) {
            Debug.out.println(String.valueOf(getTimeString()) + " " + string);
        }
    }
    
    protected static void report(final Throwable e, final String why) {
        if (Debug.out != null) {
            final Thread t = Thread.currentThread();
            println("* " + why + " " + e.toString() + " t=" + t.toString());
            if (Debug.stack && Debug.out != null) {
                e.printStackTrace(Debug.out);
            }
        }
    }
    
    protected static void report(final Throwable e) {
        report(e, "");
    }
    
    protected static void report(final String s) {
        report(new Exception(s));
    }
}
