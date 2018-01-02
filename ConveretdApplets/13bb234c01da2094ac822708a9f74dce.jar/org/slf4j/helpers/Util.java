// 
// Decompiled by Procyon v0.5.30
// 

package org.slf4j.helpers;

public class Util
{
    public static final void a(final String s, final Throwable t) {
        System.err.println(s);
        System.err.println("Reported exception:");
        t.printStackTrace();
    }
    
    public static final void a(final String s) {
        System.err.println("SLF4J: " + s);
    }
}
