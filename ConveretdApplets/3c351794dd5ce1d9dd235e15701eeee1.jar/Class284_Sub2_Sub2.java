// 
// Decompiled by Procyon v0.5.30
// 

final class Class284_Sub2_Sub2 extends Class284_Sub2
{
    private byte[] aByteArray6196;
    static IncomingOpcode aClass58_6197;
    static OutgoingOpcode aClass171_6198;
    static Class332 aClass332_6199;
    static int[] anIntArray6200;
    static short aShort6201;
    static int[] anIntArray6202;
    static d aD6203;
    
    public static void method3378(final int n) {
        try {
            Class284_Sub2_Sub2.aClass58_6197 = null;
            Class284_Sub2_Sub2.aClass171_6198 = null;
            Class284_Sub2_Sub2.anIntArray6202 = null;
            Class284_Sub2_Sub2.anIntArray6200 = null;
            Class284_Sub2_Sub2.aD6203 = null;
            if (n == 8) {
                Class284_Sub2_Sub2.aClass332_6199 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kh.N(" + n + ')');
        }
    }
    
    public Class284_Sub2_Sub2() {
        super(8, 5, 8, 8, 2, 0.1f, 0.55f, 3.0f);
    }
    
    @Override
    final void method3375(final int n, final int n2, final byte b) {
        try {
            int n3 = n * 2;
            this.aByteArray6196[n3++] = -1;
            this.aByteArray6196[n3] = (byte)(3 * (0xFF & b) >> 737825957);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kh.L(" + n + ',' + n2 + ',' + b + ')');
        }
    }
    
    final byte[] method3379(final int n, final int n2, final int n3, final int n4) {
        try {
            if (n2 != 20283) {
                return null;
            }
            this.aByteArray6196 = new byte[2 * n4 * n3 * n];
            this.method3361((byte)(-116), n4, n3, n);
            return this.aByteArray6196;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kh.M(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    static {
        Class284_Sub2_Sub2.aClass58_6197 = new IncomingOpcode(56, 10);
        Class284_Sub2_Sub2.aClass171_6198 = new OutgoingOpcode(54, 7);
        Class284_Sub2_Sub2.aShort6201 = 1;
        Class284_Sub2_Sub2.anIntArray6200 = new int[16384];
        Class284_Sub2_Sub2.anIntArray6202 = new int[16384];
        final double n = 3.834951969714103E-4;
        for (int n2 = 0; ~n2 > -16385; ++n2) {
            Class284_Sub2_Sub2.anIntArray6200[n2] = (int)(16384.0 * Math.sin(n2 * n));
            Class284_Sub2_Sub2.anIntArray6202[n2] = (int)(Math.cos(n * n2) * 16384.0);
        }
    }
}
