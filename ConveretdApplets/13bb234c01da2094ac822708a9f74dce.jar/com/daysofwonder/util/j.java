// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.util;

import java.io.OutputStream;
import java.io.PrintWriter;

public class j
{
    private static final PrintWriter a;
    
    public static int a(final String s) {
        try {
            String s2 = C.a("Debug.Level." + s);
            if (s2 == null) {
                s2 = C.a("Debug.Level.*");
                if (s2 == null) {
                    return 0;
                }
            }
            try {
                return Integer.parseInt(s2);
            }
            catch (NumberFormatException ex) {
                return 0;
            }
        }
        catch (Exception ex2) {
            return 0;
        }
    }
    
    public static PrintWriter a() {
        return j.a;
    }
    
    static {
        a = new PrintWriter(System.out, true);
    }
}
