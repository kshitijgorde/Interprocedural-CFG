// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

public class r
{
    private static String a;
    private static String b;
    private static boolean c;
    private static boolean d;
    
    public static boolean a() {
        return r.c;
    }
    
    public static boolean b() {
        return r.d;
    }
    
    public static boolean c() {
        if (!r.d) {
            return false;
        }
        final String[] split;
        if ((split = r.b.split("\\.")).length < 2) {
            return false;
        }
        try {
            final int intValue = Integer.valueOf(split[0]);
            final int intValue2 = Integer.valueOf(split[1]);
            return intValue == 10 && intValue2 >= 5;
        }
        catch (NumberFormatException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public static String d() {
        return r.b;
    }
    
    static {
        System.getProperty("os.arch").toLowerCase();
        r.a = System.getProperty("os.name").toLowerCase();
        r.b = System.getProperty("os.version");
        r.c = r.a.contains("win");
        r.d = r.a.startsWith("mac os x");
    }
}
