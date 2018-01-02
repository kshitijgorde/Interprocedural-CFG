// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.log;

public class D
{
    public static final int B = 0;
    public static final int A = 1;
    public static final int E = 2;
    public static final int I = 3;
    public static final int F = 4;
    public static final String D = "INFO";
    public static final String C = "DEBUG";
    public static final String H = "WARN";
    public static final String J = "ERROR";
    public static final String G = "FATAL";
    
    public static String A(final int n) {
        String s = null;
        switch (n) {
            case 0: {
                s = "INFO";
                break;
            }
            case 1: {
                s = "DEBUG";
                break;
            }
            case 2: {
                s = "WARN";
                break;
            }
            case 3: {
                s = "ERROR";
                break;
            }
            case 4: {
                s = "FATAL";
                break;
            }
        }
        return s;
    }
    
    public static int A(final String s) {
        int n = -1;
        if ("INFO".equals(s)) {
            n = 0;
        }
        if ("DEBUG".equals(s)) {
            n = 1;
        }
        if ("WARN".equals(s)) {
            n = 2;
        }
        if ("ERROR".equals(s)) {
            n = 3;
        }
        if ("FATAL".equals(s)) {
            n = 4;
        }
        return n;
    }
}
