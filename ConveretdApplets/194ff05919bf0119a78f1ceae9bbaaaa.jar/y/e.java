// 
// Decompiled by Procyon v0.5.30
// 

package y;

public final class e
{
    public static final e a;
    public static final e b;
    public static final e c;
    public static final e d;
    public static final e e;
    public static final e f;
    private static e g;
    private int a;
    private String a;
    
    private e(final int a, final String a2) {
        this.a = a;
        this.a = a2;
    }
    
    public final boolean a(final e e) {
        return this.a >= e.a;
    }
    
    public final String toString() {
        return this.a;
    }
    
    public static e a(String upperCase, final e e) {
        if (upperCase == null) {
            return e;
        }
        if ((upperCase = upperCase.toUpperCase()).equals("ALL")) {
            return e.g;
        }
        if (upperCase.equals("DEBUG")) {
            return e.f;
        }
        if (upperCase.equals("INFO")) {
            return e.e;
        }
        if (upperCase.equals("WARN")) {
            return e.d;
        }
        if (upperCase.equals("ERROR")) {
            return e.c;
        }
        if (upperCase.equals("FATAL")) {
            return e.b;
        }
        if (upperCase.equals("OFF")) {
            return e.a;
        }
        return e;
    }
    
    static {
        a = new e(Integer.MAX_VALUE, "OFF");
        b = new e(50000, "FATAL");
        c = new e(40000, "ERROR");
        d = new e(30000, "WARN");
        e = new e(20000, "INFO");
        f = new e(10000, "DEBUG");
        y.e.g = new e(Integer.MIN_VALUE, "ALL");
    }
}
