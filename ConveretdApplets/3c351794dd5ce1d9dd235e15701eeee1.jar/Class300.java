import java.math.BigInteger;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class300
{
    int anInt2496;
    int[] anIntArray2497;
    Class98_Sub46_Sub11 aClass98_Sub46_Sub11_2498;
    static Class65 aClass65_2499;
    static Class332 aClass332_2500;
    static BigInteger aBigInteger2501;
    
    public static void method3532(final byte b) {
        try {
            Class300.aClass332_2500 = null;
            Class300.aClass65_2499 = null;
            if (b > -53) {
                method3532((byte)(-5));
            }
            Class300.aBigInteger2501 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sh.B(" + b + ')');
        }
    }
    
    static final Class119_Sub4 method3533(final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (b != 51) {
                Class300.aClass65_2499 = null;
            }
            return new Class119_Sub4(class98_Sub22.readUShort(false), class98_Sub22.readUShort(false), class98_Sub22.readUShort(false), class98_Sub22.readUShort(false), class98_Sub22.method1186(-127), class98_Sub22.method1186(-128), class98_Sub22.readUnsignedByte((byte)(-114)));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sh.A(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static {
        Class300.aClass65_2499 = new Class65();
        Class300.aBigInteger2501 = new BigInteger("10001", 16);
    }
}
