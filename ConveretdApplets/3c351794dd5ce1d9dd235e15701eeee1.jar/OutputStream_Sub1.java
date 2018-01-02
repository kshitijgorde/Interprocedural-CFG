import java.awt.Canvas;
import java.io.IOException;
import java.io.OutputStream;

// 
// Decompiled by Procyon v0.5.30
// 

final class OutputStream_Sub1 extends OutputStream
{
    static int anInt32;
    static Class293 aClass293_33;
    static OutgoingOpcode aClass171_34;
    static boolean aBoolean35;
    static Class240 aClass240_36;
    static int anInt37;
    static int[] anIntArray38;
    
    @Override
    public final void write(final int n) throws IOException {
        try {
            throw new IOException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dg.write(" + n + ')');
        }
    }
    
    static final ha method128(final d d, final int n, final int n2, final Canvas canvas, final int n3) {
        try {
            if (n3 != 500) {
                OutputStream_Sub1.anIntArray38 = null;
            }
            return new ha_Sub2(canvas, d, n2, n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dg.A(" + ((d != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + ((canvas != null) ? "{...}" : "null") + ',' + n3 + ')');
        }
    }
    
    static final void method129(final int anInt5153, final int anInt5154, final Class63 aClass63_1216, final int anInt5155, final int anInt5156, final boolean b, final int anInt5157, final int anInt5158, final int anInt5159, final int anInt5160, final int anInt5161, final Class110 aClass110_1547) {
        try {
            Class64_Sub26.aClass324_3713 = null;
            Class42_Sub2.aClass324_5359 = null;
            Class45.anInt384 = anInt5158;
            Class76_Sub10.anInt3793 = anInt5154;
            Class231.aClass324_1733 = null;
            Class98_Sub10_Sub38.anInt5751 = anInt5157;
            Class98_Sub10_Sub18.anInt5626 = anInt5155;
            Class98_Sub27.anInt4060 = anInt5161;
            Class151.aClass63_1216 = aClass63_1216;
            if (b) {
                OutputStream_Sub1.anInt32 = 118;
            }
            Class277.anInt2050 = anInt5159;
            Class15.anInt170 = anInt5156;
            Class302.anInt2524 = anInt5160;
            Class246_Sub10.anInt5153 = anInt5153;
            Class202.aClass110_1547 = aClass110_1547;
            ha_Sub3.method2062(-4264);
            Class98_Sub10.aBoolean3858 = true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dg.D(" + anInt5153 + ',' + anInt5154 + ',' + ((aClass63_1216 != null) ? "{...}" : "null") + ',' + anInt5155 + ',' + anInt5156 + ',' + b + ',' + anInt5157 + ',' + anInt5158 + ',' + anInt5159 + ',' + anInt5160 + ',' + anInt5161 + ',' + ((aClass110_1547 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method130(final int n, final int n2, final int n3) {
        try {
            Class299_Sub2.method3523(n3, n, n2 ^ n2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dg.B(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    public static void method131(final byte b) {
        try {
            OutputStream_Sub1.aClass171_34 = null;
            OutputStream_Sub1.aClass240_36 = null;
            OutputStream_Sub1.aClass293_33 = null;
            OutputStream_Sub1.anIntArray38 = null;
            if (b != 4) {
                OutputStream_Sub1.anInt32 = -72;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dg.C(" + b + ')');
        }
    }
    
    static {
        OutputStream_Sub1.aClass293_33 = null;
        OutputStream_Sub1.anInt32 = 500;
        OutputStream_Sub1.aClass171_34 = new OutgoingOpcode(8, 3);
        OutputStream_Sub1.aBoolean35 = false;
        OutputStream_Sub1.anIntArray38 = new int[] { 16776960, 16711680, 65280, 65535, 16711935, 16777215 };
    }
}
