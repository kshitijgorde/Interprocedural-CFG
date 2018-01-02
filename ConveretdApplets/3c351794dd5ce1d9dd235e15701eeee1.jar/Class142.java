// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class142
{
    static Class377 aClass377_1157;
    static Class79 aClass79_1158;
    static Class105 aClass105_1159;
    static int anInt1160;
    
    public static void method2306(final int n) {
        try {
            Class142.aClass79_1158 = null;
            Class142.aClass105_1159 = null;
            Class142.aClass377_1157 = null;
            if (n != -1) {
                Class142.anInt1160 = 44;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jp.C(" + n + ')');
        }
    }
    
    static final int method2307(final int n, final int n2, final int n3) {
        try {
            final double n4 = Math.log(n2) / Math.log(2.0);
            final double n5 = Math.log(n) / Math.log(2.0);
            return (int)(0.5 + Math.pow(2.0, n5 + Math.random() * (-n5 + n4)));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jp.D(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    abstract long method2308(final byte p0);
    
    static final void method2309(final int n, final String aString716) {
        try {
            Class89.aString716 = aString716;
            if (Class76_Sub11.anApplet3799 != null) {
                try {
                    String s = Class76_Sub11.anApplet3799.getParameter("cookieprefix") + "settings=" + aString716 + "; version=1; path=/; domain=" + Class76_Sub11.anApplet3799.getParameter("cookiehost");
                    if (n != 19208) {
                        Class142.aClass105_1159 = null;
                    }
                    Label_0168: {
                        if (~aString716.length() == -1) {
                            s += "; Expires=Thu, 01-Jan-1970 00:00:00 GMT; Max-Age=0";
                            if (!client.aBoolean3553) {
                                break Label_0168;
                            }
                        }
                        s = s + "; Expires=" + Class98_Sub40.method1471(5090, 94608000000L + Class343.method3819(n - 19255)) + "; Max-Age=" + 94608000L;
                    }
                    Class203.method2706(Class76_Sub11.anApplet3799, "document.cookie=\"" + s + "\"", 9202);
                }
                catch (Throwable t) {}
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jp.E(" + n + ',' + ((aString716 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method2310(final byte b) {
        try {
            Class232.aClass79_1740.method794(18);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jp.B(" + b + ')');
        }
    }
    
    static {
        Class142.aClass377_1157 = new Class377(32);
        Class142.aClass79_1158 = new Class79(64);
        Class142.anInt1160 = 0;
        Class142.aClass105_1159 = new Class105("", 10);
    }
}
