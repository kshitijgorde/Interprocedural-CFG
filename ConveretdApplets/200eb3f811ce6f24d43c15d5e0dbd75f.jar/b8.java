// 
// Decompiled by Procyon v0.5.30
// 

public class b8 implements a0
{
    private static String a;
    private static ac b;
    public static String c;
    public static String d;
    public static String e;
    
    public static String a() {
        boolean b = false;
        try {
            Class.forName("com.is_teledata.obfuscation.Obf");
            b = true;
        }
        catch (Exception ex) {}
        try {
            Class.forName("com.is_teledata.obfuscation.Plain");
            if (b) {
                return "unknown";
            }
            return "plain";
        }
        catch (Exception ex2) {
            if (b) {
                return "obfuscated";
            }
            return "unknown";
        }
    }
    
    public static void b() {
        b8.b.a();
    }
    
    static {
        b8.a = "";
        b8.b = new ac("yyyyMMddHHmm");
        b8.c = "200706041718";
        b8.d = "DUNNO";
        b8.e = "IS.Teledata MDG Java API version 2.1.12 " + b8.c;
    }
}
