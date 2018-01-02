import java.awt.Dimension;
import java.awt.Canvas;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class76_Sub11 extends Class76
{
    static Class332 aClass332_3795;
    static int[] anIntArray3796;
    static Class79 aClass79_3797;
    static int anInt3798;
    static Applet anApplet3799;
    static int anInt3800;
    
    @Override
    final void method739(final int n) {
        try {
            if (n != -2) {
                this.method745((byte)(-106));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wk.C(" + n + ')');
        }
    }
    
    static final ha method771(final int n, final Canvas canvas, final int n2, final d d, final int n3, final Class207 class207) {
        try {
            if (n2 <= 21) {
                return null;
            }
            int width = 0;
            int height = 0;
            if (canvas != null) {
                final Dimension size = canvas.getSize();
                width = size.width;
                height = size.height;
            }
            return ha.method1742((byte)64, n, canvas, d, width, class207, n3, height);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wk.F(" + n + ',' + ((canvas != null) ? "{...}" : "null") + ',' + n2 + ',' + ((d != null) ? "{...}" : "null") + ',' + n3 + ',' + ((class207 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method743(final int n, final boolean b) {
        try {
            if (n <= 93) {
                method771(-78, null, 97, null, 28, null);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wk.D(" + n + ',' + b + ')');
        }
    }
    
    @Override
    final void method748(final int n, final boolean b) {
        try {
            if (n != 69) {
                Class76_Sub11.aClass332_3795 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wk.B(" + n + ',' + b + ')');
        }
    }
    
    Class76_Sub11(final ha_Sub3 ha_Sub3) {
        super(ha_Sub3);
    }
    
    public static void method772(final byte b) {
        try {
            Class76_Sub11.aClass332_3795 = null;
            Class76_Sub11.anApplet3799 = null;
            if (b >= 64) {
                Class76_Sub11.aClass79_3797 = null;
                Class76_Sub11.anIntArray3796 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wk.A(" + b + ')');
        }
    }
    
    @Override
    final void method746(final int n, final int n2, final int n3) {
        try {
            if (n3 >= -75) {
                Class76_Sub11.aClass332_3795 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wk.E(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final boolean method745(final byte b) {
        try {
            if (b != 27) {
                Class76_Sub11.anApplet3799 = null;
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wk.H(" + b + ')');
        }
    }
    
    @Override
    final void method742(final int n, final int n2, final Interface4 interface4) {
        try {
            if (n != 6) {
                Class76_Sub11.anInt3798 = 101;
            }
            super.aHa_Sub3_585.method2005(interface4, -123);
            super.aHa_Sub3_585.method2015(n2, (byte)117);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wk.I(" + n + ',' + n2 + ',' + ((interface4 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class76_Sub11.anIntArray3796 = new int[250];
        Class76_Sub11.aClass79_3797 = new Class79(8);
    }
}
