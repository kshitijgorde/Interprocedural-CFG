// 
// Decompiled by Procyon v0.5.30
// 

final class Class284_Sub1_Sub1 extends Class284_Sub1
{
    static Class200 aClass200_6187;
    private byte[] aByteArray6188;
    static Class263 aClass263_6189;
    static int[] anIntArray6190;
    
    public static void method3365(final byte b) {
        try {
            Class284_Sub1_Sub1.aClass263_6189 = null;
            Class284_Sub1_Sub1.anIntArray6190 = null;
            if (b == 89) {
                Class284_Sub1_Sub1.aClass200_6187 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ed.K(" + b + ')');
        }
    }
    
    public Class284_Sub1_Sub1() {
        super(12, 5, 16, 2, 2, 0.45f);
    }
    
    final byte[] method3366(final boolean b, final int n, final int n2, final int n3) {
        try {
            if (!b) {
                Class284_Sub1_Sub1.anIntArray6190 = null;
            }
            this.aByteArray6188 = new byte[2 * n2 * (n3 * n)];
            this.method3361((byte)(-37), n3, n, n2);
            return this.aByteArray6188;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ed.J(" + b + ',' + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static long method3367(final long n, final long n2) {
        try {
            return n ^ n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ed.L(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final void method3363(byte b, final int n, final byte b2) {
        try {
            if (b2 == 42) {
                int n2 = 2 * n;
                b = (byte)(127 + ((b & 0xFF) >> 768033697));
                this.aByteArray6188[n2++] = b;
                this.aByteArray6188[n2] = b;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ed.M(" + b + ',' + n + ',' + b2 + ')');
        }
    }
    
    static final int method3368(final int n, final String s, final Class98_Sub22 class98_Sub22) {
        try {
            final int anInt3991 = class98_Sub22.anInt3991;
            final byte[] method152 = aa.method152(0, s);
            class98_Sub22.method1237(method152.length, -120);
            if (n != 127) {
                Class284_Sub1_Sub1.aClass263_6189 = null;
            }
            class98_Sub22.anInt3991 += Class146_Sub3.aClass213_4949.method2780(method152.length, class98_Sub22.aByteArray3992, 0, class98_Sub22.anInt3991, method152, 6350);
            return -anInt3991 + class98_Sub22.anInt3991;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ed.N(" + n + ',' + ((s != null) ? "{...}" : "null") + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class284_Sub1_Sub1.aClass200_6187 = new Class200();
        Class284_Sub1_Sub1.anIntArray6190 = new int[4];
        Class284_Sub1_Sub1.aClass263_6189 = new Class263();
    }
}
