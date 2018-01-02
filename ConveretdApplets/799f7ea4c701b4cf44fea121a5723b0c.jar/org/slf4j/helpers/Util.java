// 
// Decompiled by Procyon v0.5.30
// 

package org.slf4j.helpers;

public class Util
{
    public static final void reportFailure(final String msg, final Throwable t) {
        System.err.println(msg);
        System.err.println("Reported exception:");
        t.printStackTrace();
    }
    
    public static final void reportFailure(final String msg) {
        System.err.println("SLF4J: " + msg);
    }
}
