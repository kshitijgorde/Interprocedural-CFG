// 
// Decompiled by Procyon v0.5.30
// 

package jchatbox.client.util;

public class Debug
{
    public static int LEVEL;
    
    public static void log(final int n, final String s) {
        if (n <= Debug.LEVEL) {
            System.err.println(s);
        }
    }
    
    static {
        Debug.LEVEL = 1;
    }
}
