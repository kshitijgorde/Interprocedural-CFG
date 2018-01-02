// 
// Decompiled by Procyon v0.5.30
// 

final class Class52 implements Interface21
{
    int anInt3489;
    int anInt3490;
    int anInt3491;
    int anInt3492;
    static int[] anIntArray3493;
    static Class207 aClass207_3494;
    boolean aBoolean3495;
    int anInt3496;
    int anInt3497;
    int anInt3498;
    Class110 aClass110_3499;
    static Class280 aClass280_3500;
    int anInt3501;
    Class63 aClass63_3502;
    
    static final int method488(final boolean b) {
        try {
            if (!b) {
                method490(null, (byte)(-28));
            }
            synchronized (Class211.aClass79_1594) {
                return Class211.aClass79_1594.method797(-127);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dk.F(" + b + ')');
        }
    }
    
    @Override
    public final Class113 method70(final int n) {
        try {
            if (n != 30778) {
                this.method70(43);
            }
            return Class365.aClass113_3109;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dk.A(" + n + ')');
        }
    }
    
    static final void method489(final boolean b) {
        try {
            Class69_Sub2.aClass79_5334.method806((byte)(-127));
            Class64_Sub5.aClass79_3650.method806((byte)60);
            Class76_Sub11.aClass79_3797.method806((byte)(-97));
            if (!b) {
                Class151_Sub7.aClass79_5004.method806((byte)116);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dk.D(" + b + ')');
        }
    }
    
    static final Class259[] method490(final Class88 class88, final byte b) {
        try {
            if (!class88.method860((byte)(-112))) {
                return new Class259[0];
            }
            final Class143 method864 = class88.method864(55);
            while (method864.anInt1163 == 0) {
                Class246_Sub7.method3131(0, 10L);
            }
            if (~method864.anInt1163 == 0xFFFFFFFD) {
                return new Class259[0];
            }
            final int[] array = (int[])method864.anObject1162;
            final Class259[] array2 = new Class259[array.length >> -574919422];
            int n = 0;
            if (b >= -64) {
                Class52.anIntArray3493 = null;
            }
            while (~n > ~array2.length) {
                final Class259 class89 = new Class259();
                array2[n] = class89;
                class89.anInt1953 = array[n << -22199518];
                class89.anInt1956 = array[(n << -27193534) + 1];
                class89.anInt1955 = array[2 + (n << -1744757374)];
                class89.anInt1958 = array[(n << -1958187070) + 3];
                ++n;
            }
            return array2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dk.B(" + ((class88 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static final Class370 method491(final int n, final boolean b, final Class207 class207, final String s) {
        try {
            final int method2750 = class207.method2750((byte)(-102), s);
            if (~method2750 == 0x0) {
                return new Class370(0);
            }
            final int[] method2751 = class207.method2743(method2750, 6341);
            final Class370 class208 = new Class370(method2751.length);
            int n2 = 0;
            int n3 = 0;
            if (n < 67) {
                method489(false);
            }
            while (class208.anInt3137 > n2) {
                final Class98_Sub22 class98_Sub22 = new Class98_Sub22(class207.method2745(method2751[n3++], method2750, false));
                final int int1 = class98_Sub22.readInt(-2);
                final int short1 = class98_Sub22.readShort((byte)127);
                final int unsignedByte = class98_Sub22.readUnsignedByte((byte)96);
                if (!b && ~unsignedByte == 0xFFFFFFFE) {
                    final Class370 class209 = class208;
                    --class209.anInt3137;
                }
                else {
                    class208.anIntArray3133[n2] = int1;
                    class208.anIntArray3138[n2] = short1;
                    ++n2;
                }
            }
            return class208;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dk.C(" + n + ',' + b + ',' + ((class207 != null) ? "{...}" : "null") + ',' + ((s != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method492(final boolean b) {
        try {
            Class52.aClass280_3500 = null;
            if (!b) {
                Class52.anIntArray3493 = null;
            }
            Class52.aClass207_3494 = null;
            Class52.anIntArray3493 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dk.E(" + b + ')');
        }
    }
    
    Class52(final int anInt3501, final Class63 aClass63_3502, final Class110 aClass110_3499, final int anInt3502, final int anInt3503, final int anInt3504, final int anInt3505, final int anInt3506, final int anInt3507, final int anInt3508, final boolean aBoolean3495) {
        try {
            this.anInt3492 = anInt3506;
            this.anInt3490 = anInt3503;
            this.anInt3497 = anInt3505;
            this.anInt3489 = anInt3508;
            this.anInt3496 = anInt3504;
            this.anInt3501 = anInt3501;
            this.aClass110_3499 = aClass110_3499;
            this.anInt3498 = anInt3502;
            this.anInt3491 = anInt3507;
            this.aClass63_3502 = aClass63_3502;
            this.aBoolean3495 = aBoolean3495;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dk.<init>(" + anInt3501 + ',' + ((aClass63_3502 != null) ? "{...}" : "null") + ',' + ((aClass110_3499 != null) ? "{...}" : "null") + ',' + anInt3502 + ',' + anInt3503 + ',' + anInt3504 + ',' + anInt3505 + ',' + anInt3506 + ',' + anInt3507 + ',' + anInt3508 + ',' + aBoolean3495 + ')');
        }
    }
    
    static {
        Class52.anIntArray3493 = new int[25];
    }
}
