// 
// Decompiled by Procyon v0.5.30
// 

class Class337 implements Interface21
{
    int anInt3535;
    static int[] anIntArray3536;
    static long aLong3537;
    Class63 aClass63_3538;
    static int anInt3539;
    Class110 aClass110_3540;
    int anInt3541;
    int anInt3542;
    
    public static void method3775(final int n) {
        try {
            Class337.anIntArray3536 = null;
            if (n != 10003) {
                Class337.anIntArray3536 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uk.B(" + n + ')');
        }
    }
    
    @Override
    public Class113 method70(final int n) {
        try {
            if (n != 30778) {
                this.method70(-3);
            }
            return Class280.aClass113_2111;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uk.A(" + n + ')');
        }
    }
    
    Class337(final int anInt3535, final Class63 aClass63_3538, final Class110 aClass110_3540, final int anInt3536, final int anInt3537) {
        try {
            this.anInt3542 = anInt3537;
            this.anInt3535 = anInt3535;
            this.aClass63_3538 = aClass63_3538;
            this.aClass110_3540 = aClass110_3540;
            this.anInt3541 = anInt3536;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uk.<init>(" + anInt3535 + ',' + ((aClass63_3538 != null) ? "{...}" : "null") + ',' + ((aClass110_3540 != null) ? "{...}" : "null") + ',' + anInt3536 + ',' + anInt3537 + ')');
        }
    }
    
    static {
        Class337.anIntArray3536 = new int[] { 2047, 16383, 65535 };
    }
}
