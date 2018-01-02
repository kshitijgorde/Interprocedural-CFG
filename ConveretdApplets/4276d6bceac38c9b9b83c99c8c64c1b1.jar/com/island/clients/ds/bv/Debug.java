// 
// Decompiled by Procyon v0.5.30
// 

package com.island.clients.ds.bv;

import java.io.PrintStream;

public class Debug
{
    static boolean enabled;
    static PrintStream ps;
    
    static {
        Debug.enabled = false;
        Debug.ps = System.out;
    }
    
    public static synchronized void setEnabled(final boolean flag) {
        Debug.enabled = flag;
        Debug.ps.println("DEBUGGING ENABLED");
    }
    
    public static synchronized void setPrintStream(final PrintStream printstream) {
        Debug.ps = printstream;
    }
    
    public static void x(final String s) {
        if (Debug.enabled) {
            Debug.ps.println(s);
        }
    }
}
