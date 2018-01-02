import java.util.Random;
import java.util.zip.CRC32;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class279
{
    int anInt2095;
    static int anInt2096;
    static CRC32 aCRC32_2097;
    String aString2098;
    static int anInt2099;
    static double aDouble2100;
    
    @Override
    public final String toString() {
        try {
            throw new IllegalStateException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rga.toString()");
        }
    }
    
    public static void method3321(final boolean b) {
        try {
            if (!b) {
                method3321(true);
            }
            Class279.aCRC32_2097 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rga.A(" + b + ')');
        }
    }
    
    Class279(final String aString2098, final int anInt2095) {
        try {
            this.aString2098 = aString2098;
            this.anInt2095 = anInt2095;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rga.<init>(" + ((aString2098 != null) ? "{...}" : "null") + ',' + anInt2095 + ')');
        }
    }
    
    static final void method3322(final Class98 aClass98_836, final Class98 class98, final byte b) {
        try {
            if (class98.aClass98_833 != null) {
                class98.method942(b ^ 0x71);
            }
            if (b != 24) {
                Class279.anInt2099 = 4;
            }
            class98.aClass98_833 = aClass98_836.aClass98_833;
            class98.aClass98_836 = aClass98_836;
            class98.aClass98_833.aClass98_836 = class98;
            class98.aClass98_836.aClass98_833 = class98;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rga.B(" + ((aClass98_836 != null) ? "{...}" : "null") + ',' + ((class98 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static final byte[] method3323(final int n, final int n2) {
        try {
            Class98_Sub46_Sub7 class98_Sub46_Sub7 = (Class98_Sub46_Sub7)Class81.aClass100_617.method1694((byte)117, n);
            if (class98_Sub46_Sub7 == null) {
                final byte[] array = new byte[512];
                final Random random = new Random(n);
                for (int n3 = 0; ~n3 > -256; ++n3) {
                    array[n3] = (byte)n3;
                }
                for (int i = 0; i < 255; ++i) {
                    final int n4 = -i + 255;
                    final int method546 = Class63.method546(-28737, n4, random);
                    final byte b = array[method546];
                    array[method546] = array[n4];
                    array[n4] = (array[-i + 511] = b);
                }
                class98_Sub46_Sub7 = new Class98_Sub46_Sub7(array);
                Class81.aClass100_617.method1695(26404, class98_Sub46_Sub7, n);
            }
            if (n2 != 512) {
                method3322(null, null, (byte)75);
            }
            return class98_Sub46_Sub7.aByteArray5981;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rga.C(" + n + ',' + n2 + ')');
        }
    }
    
    static {
        Class279.anInt2096 = 0;
        Class279.aCRC32_2097 = new CRC32();
        Class279.anInt2099 = 0;
    }
}
