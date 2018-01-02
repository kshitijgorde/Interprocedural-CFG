import java.awt.Dimension;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class101
{
    int anInt847;
    static Class126 aClass126_848;
    static int anInt849;
    int anInt850;
    int anInt851;
    int anInt852;
    int anInt853;
    static int anInt854;
    private byte aByte855;
    static boolean aBoolean856;
    static Class115 aClass115_857;
    
    public static void method1696(final boolean b) {
        try {
            Class101.aClass115_857 = null;
            Class101.aClass126_848 = null;
            if (!b) {
                method1702(27, true, false);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gf.D(" + b + ')');
        }
    }
    
    static final void method1697(final Class98_Sub46 aClass98_Sub46_4265, final byte b, final Class98_Sub46 class98_Sub46) {
        try {
            if (b == 37) {
                if (class98_Sub46.aClass98_Sub46_4265 != null) {
                    class98_Sub46.method1524((byte)(-90));
                }
                class98_Sub46.aClass98_Sub46_4262 = aClass98_Sub46_4265.aClass98_Sub46_4262;
                class98_Sub46.aClass98_Sub46_4265 = aClass98_Sub46_4265;
                class98_Sub46.aClass98_Sub46_4265.aClass98_Sub46_4262 = class98_Sub46;
                class98_Sub46.aClass98_Sub46_4262.aClass98_Sub46_4265 = class98_Sub46;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gf.E(" + ((aClass98_Sub46_4265 != null) ? "{...}" : "null") + ',' + b + ',' + ((class98_Sub46 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final int method1698(final boolean b) {
        try {
            if (!b) {
                method1696(false);
            }
            if ((this.aByte855 & 0x8) != 0x8) {
                return 0;
            }
            return 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gf.B(" + b + ')');
        }
    }
    
    static final void method1699(final Canvas canvas, final int n) {
        try {
            final Dimension size = canvas.getSize();
            Class287_Sub2.method3391(size.height, size.width, 2);
            if (n != 7) {
                method1699(null, -101);
            }
            if (~Class98_Sub46.anInt4261 != 0xFFFFFFFE) {
                Class154.aHa1231.method1741(canvas, Class151_Sub7.anInt5005, Class149.anInt1208);
            }
            else {
                Class154.aHa1231.method1741(canvas, aa_Sub1.anInt3556, Class48_Sub1_Sub2.anInt5519);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gf.G(" + ((canvas != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final int method1700(final int n) {
        try {
            if (n != 7) {
                this.anInt851 = 29;
            }
            return 0x7 & this.aByte855;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gf.A(" + n + ')');
        }
    }
    
    static final Class246_Sub3_Sub5 method1701(final int n, final int n2, final int n3) {
        final Class172 class172 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[n][n2][n3];
        if (class172 == null) {
            return null;
        }
        return class172.aClass246_Sub3_Sub5_1334;
    }
    
    public Class101() {
    }
    
    static final void method1702(final int n, final boolean b, final boolean b2) {
        try {
            if (b) {
                ++Class98_Sub18.anInt3952;
                Class122.method2199(false);
            }
            if (b2) {
                ++Class98_Sub10_Sub9.anInt5580;
                Class93_Sub1.method904((byte)(-110));
            }
            if (n != 8) {
                Class101.aClass115_857 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gf.F(" + n + ',' + b + ',' + b2 + ')');
        }
    }
    
    Class101(final Class98_Sub22 class98_Sub22) {
        try {
            this.aByte855 = class98_Sub22.readSignedByte((byte)(-19));
            this.anInt852 = class98_Sub22.readShort((byte)127);
            this.anInt847 = class98_Sub22.readInt(-2);
            this.anInt853 = class98_Sub22.readInt(-2);
            this.anInt851 = class98_Sub22.readInt(-2);
            this.anInt850 = class98_Sub22.readInt(-2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gf.<init>(" + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class101.anInt849 = -1;
        Class101.aBoolean856 = true;
        Class101.aClass126_848 = new Class126();
    }
}
