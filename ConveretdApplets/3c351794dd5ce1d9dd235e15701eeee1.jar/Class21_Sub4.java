import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class21_Sub4 extends Class21 implements Interface4_Impl1
{
    private int anInt5393;
    static int anInt5394;
    private int anInt5395;
    static int anInt5396;
    private int anInt5397;
    static OutgoingOpcode aClass171_5398;
    static boolean[] aBooleanArray5399;
    
    public static void method277(final byte b) {
        try {
            if (b == 0) {
                Class21_Sub4.aBooleanArray5399 = null;
                Class21_Sub4.aClass171_5398 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wha.D(" + b + ')');
        }
    }
    
    static final void method278(final int n, final int n2, final int n3, final int n4, final int n5, final byte b, final int n6) {
        try {
            if (b != 65) {
                method278(-57, -60, 38, -120, -43, (byte)53, 87);
            }
            if (~Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub22_4066.method641((byte)127) != -1 && n4 != 0 && ~Class306.anInt2566 > -51 && ~n3 != 0x0) {
                Class245.aClass338Array1865[Class306.anInt2566++] = new Class338((byte)1, n3, n4, n5, n, n6, n2, null);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wha.C(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + b + ',' + n6 + ')');
        }
    }
    
    Class21_Sub4(final ha_Sub3_Sub2 ha_Sub3_Sub2, final Class164 class164, final int anInt5393, final int anInt5394, final int anInt5395, final byte[] array) {
        super(ha_Sub3_Sub2, 32879, class164, Class162.aClass162_1266, anInt5393 * (anInt5394 * anInt5395), false);
        try {
            this.anInt5395 = anInt5394;
            this.anInt5393 = anInt5393;
            this.anInt5397 = anInt5395;
            super.aHa_Sub3_Sub2_3233.method2005(this, -8);
            OpenGL.glPixelStorei(3317, 1);
            OpenGL.glTexImage3Dub(super.anInt3235, 0, this.method260(0), this.anInt5393, this.anInt5395, this.anInt5397, 0, Class196.method2665(false, super.aClass164_3237), 5121, array, 0);
            OpenGL.glPixelStorei(3317, 4);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wha.<init>(" + ((ha_Sub3_Sub2 != null) ? "{...}" : "null") + ',' + ((class164 != null) ? "{...}" : "null") + ',' + anInt5393 + ',' + anInt5394 + ',' + anInt5395 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method279(final int n, final int n2) {
        try {
            if (n != 16953) {
                method278(13, -128, 94, 94, 86, (byte)(-31), -92);
            }
            Class185.method2628(n2, n ^ 0xFFFFBD9E, 12).method1621(0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wha.A(" + n + ',' + n2 + ')');
        }
    }
    
    static {
        Class21_Sub4.anInt5394 = 0;
        Class21_Sub4.anInt5396 = 0;
        Class21_Sub4.aClass171_5398 = new OutgoingOpcode(53, 3);
    }
}
