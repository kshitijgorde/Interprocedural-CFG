// 
// Decompiled by Procyon v0.5.30
// 

final class OutgoingOpcode
{
    static int anInt1318;
    int size;
    private int opcode;
    
    static final void method2540(final int n, final int n2, final Class197 class197, final int n3, final Class293 class198, final int n4, final byte b, final String s, final aa aa, final int n5, final int n6, final Class43 class199) {
        try {
            int n7 = 0;
            Label_0044: {
                if (~Class98_Sub46_Sub20_Sub2.anInt6319 == 0xFFFFFFFB) {
                    n7 = ((int)Class98_Sub22_Sub2.aFloat5794 & 0x3FFF);
                    if (!client.aBoolean3553) {
                        break Label_0044;
                    }
                }
                n7 = ((int)Class98_Sub22_Sub2.aFloat5794 - -Class204.anInt1553 & 0x3FFF);
            }
            final int n8 = Math.max(class198.anInt2311 / 2, class198.anInt2258 / 2) + 10;
            if (~(n8 * n8) <= ~(n6 * n6 + n * n)) {
                if (b < 73) {
                    method2540(52, -42, null, -60, null, 123, (byte)1, null, null, -98, -39, null);
                }
                int n9 = Class284_Sub2_Sub2.anIntArray6200[n7];
                int n10 = Class284_Sub2_Sub2.anIntArray6202[n7];
                if (Class98_Sub46_Sub20_Sub2.anInt6319 != 4) {
                    n9 = n9 * 256 / (Class151.anInt1213 + 256);
                    n10 = n10 * 256 / (256 + Class151.anInt1213);
                }
                final int n11 = n6 * n9 + n * n10 >> 2091290062;
                final int n12 = n10 * n6 + -(n * n9) >> 1204621454;
                final int method2670 = class197.method2670(100, s, null, (byte)106);
                final int method2671 = class197.method2672(null, 100, 0, s, true);
                final int n13 = n11 - method2670 / 2;
                if (-class198.anInt2311 <= n13 && ~class198.anInt2311 <= ~n13 && -class198.anInt2258 <= n12 && ~n12 >= ~class198.anInt2258) {
                    class199.method408(class198.anInt2311 / 2 + n13 - -n2, null, method2670, s, n2, 0, aa, 1, (byte)(-75), n5, null, 0, n3, 0, -method2671 + -n12 + n3 - (-(class198.anInt2258 / 2) - -n4), 50);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lga.B(" + n + ',' + n2 + ',' + ((class197 != null) ? "{...}" : "null") + ',' + n3 + ',' + ((class198 != null) ? "{...}" : "null") + ',' + n4 + ',' + b + ',' + ((s != null) ? "{...}" : "null") + ',' + ((aa != null) ? "{...}" : "null") + ',' + n5 + ',' + n6 + ',' + ((class199 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    public final String toString() {
        try {
            throw new IllegalStateException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lga.toString()");
        }
    }
    
    final int method2541(final int n) {
        try {
            if (n != 2) {
                OutgoingOpcode.anInt1318 = -128;
            }
            return this.opcode;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lga.A(" + n + ')');
        }
    }
    
    OutgoingOpcode(final int opcode, final int size) {
        try {
            this.opcode = opcode;
            this.size = size;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lga.<init>(" + opcode + ',' + size + ')');
        }
    }
}
