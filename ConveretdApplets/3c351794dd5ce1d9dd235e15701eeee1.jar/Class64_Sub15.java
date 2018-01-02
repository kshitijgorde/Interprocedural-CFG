// 
// Decompiled by Procyon v0.5.30
// 

final class Class64_Sub15 extends Class64
{
    static int anInt3676;
    static IncomingOpcode aClass58_3677;
    static int anInt3678;
    static Class207 aClass207_3679;
    
    static final int[] method610(final int anInt5740, final int n, final float n2, final int anInt5741, final boolean aBoolean5731, final boolean b, final int anInt5742, final int anInt5743) {
        try {
            final int[] array = new int[n];
            final Class98_Sub10_Sub35 class98_Sub10_Sub35 = new Class98_Sub10_Sub35();
            class98_Sub10_Sub35.anInt5733 = anInt5741;
            class98_Sub10_Sub35.anInt5739 = (int)(n2 * 4096.0f);
            class98_Sub10_Sub35.aBoolean5731 = aBoolean5731;
            class98_Sub10_Sub35.anInt5737 = anInt5743;
            class98_Sub10_Sub35.anInt5734 = anInt5742;
            class98_Sub10_Sub35.anInt5740 = anInt5740;
            class98_Sub10_Sub35.method1001((byte)66);
            Class64_Sub2.method559(b, n, 1);
            class98_Sub10_Sub35.method1107((byte)(-76), array, 0);
            return array;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lia.D(" + anInt5740 + ',' + n + ',' + n2 + ',' + anInt5741 + ',' + aBoolean5731 + ',' + b + ',' + anInt5742 + ',' + anInt5743 + ')');
        }
    }
    
    @Override
    final void method551(final byte b) {
        try {
            if (super.aClass98_Sub27_495.aClass64_Sub18_4071.method625((byte)(-123)) && super.anInt494 == 2) {
                super.anInt494 = 1;
            }
            if (b >= 118 && (super.anInt494 < 0 || super.anInt494 > 2)) {
                super.anInt494 = this.method552(0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lia.C(" + b + ')');
        }
    }
    
    Class64_Sub15(final int n, final Class98_Sub27 class98_Sub27) {
        super(n, class98_Sub27);
    }
    
    @Override
    final int method556(final int n, final int n2) {
        try {
            if (n2 != 29053) {
                return 21;
            }
            return 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lia.F(" + n + ',' + n2 + ')');
        }
    }
    
    public static void method611(final byte b) {
        try {
            Class64_Sub15.aClass207_3679 = null;
            Class64_Sub15.aClass58_3677 = null;
            if (b != -51) {
                Class64_Sub15.aClass207_3679 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lia.G(" + b + ')');
        }
    }
    
    final int method612(final byte b) {
        try {
            if (b <= 119) {
                this.method556(63, 110);
            }
            return super.anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lia.E(" + b + ')');
        }
    }
    
    @Override
    final void method550(final int n, final int anInt494) {
        try {
            super.anInt494 = anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lia.B(" + n + ',' + anInt494 + ')');
        }
    }
    
    Class64_Sub15(final Class98_Sub27 class98_Sub27) {
        super(class98_Sub27);
    }
    
    @Override
    final int method552(final int n) {
        try {
            if (n != 0) {
                this.method551((byte)91);
            }
            return 2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lia.A(" + n + ')');
        }
    }
    
    static {
        Class64_Sub15.anInt3676 = 0;
        Class64_Sub15.aClass58_3677 = new IncomingOpcode(119, 12);
    }
}
