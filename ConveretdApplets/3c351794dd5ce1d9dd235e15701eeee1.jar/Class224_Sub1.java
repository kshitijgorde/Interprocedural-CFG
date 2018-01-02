import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class224_Sub1 extends Class224
{
    static int anInt5031;
    static IncomingOpcode aClass58_5032;
    static int[] anIntArray5033;
    static int[] anIntArray5034;
    static Class207 aClass207_5035;
    static Color[] aColorArray5036;
    
    static final String method2834(final int n, String s) {
        try {
            String substring = null;
            if (n != 0) {
                method2835(-70);
            }
            final int index = s.indexOf("--> ");
            if (~index <= -1) {
                substring = s.substring(0, index + 4);
                s = s.substring(index + 4);
            }
            if (s.startsWith("directlogin ")) {
                final int index2 = s.indexOf(" ", "directlogin ".length());
                if (~index2 <= -1) {
                    final int length = s.length();
                    s = s.substring(0, index2) + " ";
                    for (int n2 = index2 + 1; ~length < ~n2; ++n2) {
                        s += "*";
                    }
                }
            }
            if (substring != null) {
                return substring + s;
            }
            return s;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cw.B(" + n + ',' + ((s != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method2835(final int n) {
        try {
            Class224_Sub1.aClass207_5035 = null;
            Class224_Sub1.anIntArray5034 = null;
            Class224_Sub1.aColorArray5036 = null;
            Class224_Sub1.anIntArray5033 = null;
            if (n != -15870) {
                method2834(56, null);
            }
            Class224_Sub1.aClass58_5032 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cw.A(" + n + ')');
        }
    }
    
    static {
        Class224_Sub1.anInt5031 = 0;
        Class224_Sub1.anIntArray5034 = new int[] { 3, 7, 15 };
        Class224_Sub1.anIntArray5033 = new int[1];
        Class224_Sub1.aClass58_5032 = new IncomingOpcode(33, -2);
        Class224_Sub1.aColorArray5036 = new Color[] { new Color(9179409), new Color(16777215), new Color(16726277), new Color(16726277) };
    }
}
