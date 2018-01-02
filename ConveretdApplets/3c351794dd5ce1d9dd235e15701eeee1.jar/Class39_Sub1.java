import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class39_Sub1 extends Class39
{
    static Class277 aClass277_3590;
    public static int anInt3591;
    static Interface10[] anInterface10Array3592;
    static int anInt3593;
    static int anInt3594;
    private int[] anIntArray3595;
    
    @Override
    final void method352(final int n, final int i, final float n2, final int n3, final float n4, int n5, final float n6, final float n7, final float[] array, final int n8, final int n9) {
        try {
            final int n10 = (int)(n8 * n6 - n9) & 0xFF;
            final int n11 = (int)(-1.0f + n7 * i) & 0xFF;
            final int n12 = (int)(-1.0f + n * n2) & 0xFF;
            final float n13 = n3 * n2;
            final int n14 = (int)n13;
            final int n15 = 1 + n14;
            final float n16 = n13 - n14;
            final float n17 = 1.0f - n16;
            final int n18 = n14 & n12;
            final int n19 = n15 & n12;
            final float method2188 = Class119_Sub4.method2188(n16, 1024);
            final int n20 = this.anIntArray3595[n18];
            final int n21 = this.anIntArray3595[n19];
            for (int n22 = 0; i > n22; ++n22) {
                final float n23 = n22 * n7;
                final int n24 = (int)n23;
                final int n25 = n24 + 1;
                final float n26 = -n24 + n23;
                final float n27 = -n26 + 1.0f;
                final float method2189 = Class119_Sub4.method2188(n26, n9 + 1023);
                final int n28 = n25 & n11;
                final int n29 = n24 & n11;
                final int n30 = this.anIntArray3595[n20 + n29];
                final int n31 = this.anIntArray3595[n20 + n28];
                final int n32 = this.anIntArray3595[n29 - -n21];
                final int n33 = this.anIntArray3595[n21 + n28];
                for (int n34 = 0; ~n34 > ~n8; ++n34) {
                    final float n35 = n6 * n34;
                    final int n36 = (int)n35;
                    final int n37 = n36 + 1;
                    final float n38 = n35 - n36;
                    final float n39 = -n38 + 1.0f;
                    final float method2190 = Class119_Sub4.method2188(n38, 1024);
                    final int n40 = n36 & n10;
                    final int n41 = n37 & n10;
                    array[n5++] = Class98_Sub10_Sub5.method1014(Class98_Sub10_Sub5.method1014(Class98_Sub10_Sub5.method1014(Class178.method2588(n39, Class202.method2702(this.anIntArray3595[n30 + n40], 7), -24576, n27, n17), (byte)112, Class178.method2588(n38, Class202.method2702(7, this.anIntArray3595[n30 + n41]), -24576, n27, n17), method2190), (byte)(-107), Class98_Sub10_Sub5.method1014(Class178.method2588(n39, Class202.method2702(7, this.anIntArray3595[n31 + n40]), -24576, n26, n17), (byte)107, Class178.method2588(n38, Class202.method2702(7, this.anIntArray3595[n41 - -n31]), -24576, n26, n17), method2190), method2189), (byte)(-124), Class98_Sub10_Sub5.method1014(Class98_Sub10_Sub5.method1014(Class178.method2588(n39, Class202.method2702(this.anIntArray3595[n40 + n32], 7), -24576, n27, n16), (byte)(-89), Class178.method2588(n38, Class202.method2702(7, this.anIntArray3595[n32 + n41]), n9 - 24577, n27, n16), method2190), (byte)(-103), Class98_Sub10_Sub5.method1014(Class178.method2588(n39, Class202.method2702(7, this.anIntArray3595[n33 + n40]), -24576, n26, n16), (byte)(-108), Class178.method2588(n38, Class202.method2702(7, this.anIntArray3595[n41 - -n33]), -24576, n26, n16), method2190), method2189), method2188) * n4;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kga.C(" + n + ',' + i + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + ((array != null) ? "{...}" : "null") + ',' + n8 + ',' + n9 + ')');
        }
    }
    
    public static void method354(final byte b) {
        try {
            if (b != -107) {
                method354((byte)65);
            }
            Class39_Sub1.aClass277_3590 = null;
            Class39_Sub1.anInterface10Array3592 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kga.A(" + b + ')');
        }
    }
    
    static final void method355(final int n) {
        try {
            Class373.method3962((byte)0);
            Class246_Sub7.aClass48_5119 = null;
            Class140.aClass48_3245 = null;
            Class98_Sub46_Sub4.aClass48_5962 = null;
            Class76.aClass28ArrayArray586 = null;
            Class98_Sub37.aHa4185 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kga.B(" + n + ')');
        }
    }
    
    Class39_Sub1(final int n) {
        this.anIntArray3595 = new int[512];
        try {
            final Random random = new Random(n);
            for (int n2 = 0; ~n2 > -257; ++n2) {
                this.anIntArray3595[n2] = (this.anIntArray3595[n2 + 256] = n2);
            }
            for (int i = 0; i < 256; ++i) {
                final int n3 = random.nextInt() & 0xFF;
                final int n4 = this.anIntArray3595[n3];
                this.anIntArray3595[n3] = (this.anIntArray3595[256 + n3] = this.anIntArray3595[i]);
                this.anIntArray3595[i] = (this.anIntArray3595[i + 256] = n4);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kga.<init>(" + n + ')');
        }
    }
    
    static {
        Class39_Sub1.aClass277_3590 = Class354.method3872((byte)83);
    }
}
