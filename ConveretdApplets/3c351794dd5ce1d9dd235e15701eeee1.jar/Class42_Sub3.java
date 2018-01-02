import jaggl.OpenGL;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class42_Sub3 extends Class42
{
    static Canvas aCanvas5361;
    private int anInt5362;
    static Class348 aClass348_5363;
    static Class111 aClass111_5364;
    static int anInt5365;
    static int anInt5366;
    
    @Override
    public final void method3(final byte b) {
        try {
            if (b > -117) {
                Class42_Sub3.aCanvas5361 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pea.B(" + b + ')');
        }
    }
    
    public static void method392(final int n) {
        try {
            Class42_Sub3.aClass111_5364 = null;
            Class42_Sub3.aCanvas5361 = null;
            Class42_Sub3.aClass348_5363 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pea.D(" + n + ')');
        }
    }
    
    final void method393(final int n, final boolean b) {
        try {
            if (n == 3552) {
                super.aHa_Sub1_3227.method1863(1, this);
                OpenGL.glTexParameteri(super.anInt3226, 10242, b ? 10497 : 33071);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pea.C(" + n + ',' + b + ')');
        }
    }
    
    static final Class207 method394(final int n, final boolean b, final int n2, final int n3) {
        try {
            Class17 class17 = null;
            if (Class98_Sub37.aClass225_4178 != null) {
                class17 = new Class17(n3, Class98_Sub37.aClass225_4178, Class55.aClass225Array444[n3], 1000000);
            }
            Class100.aClass339_Sub1Array844[n3] = Class161.aClass109_1261.method1734(72, Class29.aClass17_298, class17, n3);
            if (n >= -41) {
                method394(-63, false, 100, 8);
            }
            Class100.aClass339_Sub1Array844[n3].method3793(107);
            return new Class207(Class100.aClass339_Sub1Array844[n3], b, n2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pea.A(" + n + ',' + b + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    Class42_Sub3(final ha_Sub1 ha_Sub1, final int n, final int anInt5362, final byte[] array, final int n2) {
        super(ha_Sub1, 3552, n, anInt5362, false);
        try {
            this.anInt5362 = anInt5362;
            super.aHa_Sub1_3227.method1863(1, this);
            OpenGL.glPixelStorei(3317, 1);
            OpenGL.glTexImage1Dub(super.anInt3226, 0, super.anInt3230, this.anInt5362, 0, n2, 5121, array, 0);
            OpenGL.glPixelStorei(3317, 4);
            this.method372(-28003, true);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pea.<init>(" + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + n + ',' + anInt5362 + ',' + ((array != null) ? "{...}" : "null") + ',' + n2 + ')');
        }
    }
    
    static {
        Class42_Sub3.aClass348_5363 = new Class348(6, 0, 4, 2);
        Class42_Sub3.anInt5365 = 0;
    }
}
