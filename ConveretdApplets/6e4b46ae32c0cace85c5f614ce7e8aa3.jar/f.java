// 
// Decompiled by Procyon v0.5.30
// 

public class f
{
    public static boolean a() {
        return System.getProperty("java.vendor").toLowerCase().indexOf("netscape") >= 0;
    }
    
    public static boolean b() {
        return System.getProperty("java.version").compareTo("1.1") >= 0 && (c() || !a() || System.getProperty("java.version").compareTo("1.1.5") >= 0);
    }
    
    public static boolean c() {
        return System.getProperty("java.vendor").toLowerCase().indexOf("microsoft") >= 0;
    }
}
