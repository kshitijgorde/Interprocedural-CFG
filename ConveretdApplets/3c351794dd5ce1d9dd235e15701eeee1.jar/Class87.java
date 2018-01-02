// 
// Decompiled by Procyon v0.5.30
// 

final class Class87
{
    int anInt656;
    Class87 aClass87_657;
    byte aByte658;
    int anInt659;
    static Class246_Sub3_Sub4_Sub2_Sub2 aClass246_Sub3_Sub4_Sub2_Sub2_660;
    int anInt661;
    int anInt662;
    int anInt663;
    int anInt664;
    static OutgoingOpcode aClass171_665;
    int anInt666;
    static int[] anIntArray667;
    int anInt668;
    int anInt669;
    int anInt670;
    int anInt671;
    private int anInt672;
    static int anInt673;
    int anInt674;
    
    public static void method853(final int n) {
        try {
            Class87.aClass171_665 = null;
            if (n > -5) {
                method854(-66, -83, -85);
            }
            Class87.anIntArray667 = null;
            Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fk.C(" + n + ')');
        }
    }
    
    static final boolean method854(final int n, final int n2, final int n3) {
        try {
            return n2 != 28733 || ~(n3 & 0x800) != -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fk.B(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static final boolean method855(final int n, final Class24 class24) {
        try {
            if (class24 == null) {
                return false;
            }
            if (!class24.aBoolean258) {
                return false;
            }
            if (n <= 73) {
                method853(126);
            }
            return class24.method284(64, Class278.anInterface6_2060) && Class248.aClass377_1894.method3990(class24.anInt228, -1) == null && Class366.aClass377_3114.method3990(class24.anInt246, -1) == null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fk.E(" + n + ',' + ((class24 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final Class92 method856(final int n) {
        try {
            if (n != 0) {
                this.aByte658 = -8;
            }
            return Class98_Sub45.method1520(this.anInt672, n ^ 0x3A23);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fk.A(" + n + ')');
        }
    }
    
    Class87(final int anInt672, final int anInt673, final int anInt674, final int anInt675, final byte aByte658) {
        try {
            this.anInt661 = anInt674;
            this.anInt672 = anInt672;
            this.aByte658 = aByte658;
            this.anInt674 = anInt675;
            this.anInt666 = anInt673;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fk.<init>(" + anInt672 + ',' + anInt673 + ',' + anInt674 + ',' + anInt675 + ',' + aByte658 + ')');
        }
    }
    
    final Class87 method857(final int n, final boolean b, final int n2, final int n3) {
        try {
            if (!b) {
                return null;
            }
            return new Class87(this.anInt672, n, n3, n2, this.aByte658);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fk.D(" + n + ',' + b + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static {
        Class87.anIntArray667 = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 85, 80, 84, 0, 91, 0, 0, 0, 81, 82, 86, 0, 0, 0, 0, 0, 0, 0, 0, 13, 0, 0, 0, 0, 83, 104, 105, 103, 102, 96, 98, 97, 99, 0, 0, 0, 0, 0, 0, 0, 25, 16, 17, 18, 19, 20, 21, 22, 23, 24, 0, 0, 0, 0, 0, 0, 0, 48, 68, 66, 50, 34, 51, 52, 53, 39, 54, 55, 56, 70, 69, 40, 41, 32, 35, 49, 36, 38, 67, 33, 65, 37, 64, 0, 0, 0, 0, 0, 228, 231, 227, 233, 224, 219, 225, 230, 226, 232, 89, 87, 0, 88, 229, 90, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 0, 0, 0, 101, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        Class87.aClass171_665 = new OutgoingOpcode(16, -1);
        Class87.anInt673 = 1400;
    }
}
