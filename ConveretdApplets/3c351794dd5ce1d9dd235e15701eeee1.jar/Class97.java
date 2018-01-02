// 
// Decompiled by Procyon v0.5.30
// 

final class Class97
{
    static Class232 aClass232_806;
    int anInt807;
    int[] anIntArray808;
    int anInt809;
    int[] anIntArray810;
    int[] anIntArray811;
    boolean aBoolean812;
    boolean[] aBooleanArray813;
    static int[][] anIntArrayArray814;
    int[] anIntArray815;
    int anInt816;
    boolean aBoolean817;
    int[] anIntArray818;
    int anInt819;
    int anInt820;
    int anInt821;
    int[][] anIntArrayArray822;
    boolean aBoolean823;
    Class183 aClass183_824;
    boolean aBoolean825;
    int anInt826;
    private int[] anIntArray827;
    int anInt828;
    int anInt829;
    static boolean aBoolean830;
    static float aFloat831;
    
    final Class146 method930(final byte b, final int n, int n2, int n3, int n4, final byte b2, final Class146 class146, final int n5) {
        try {
            if (b2 <= 73) {
                return null;
            }
            final int n6 = this.anIntArray811[n2];
            n2 = this.anIntArray818[n2];
            final Class98_Sub46_Sub16 method2624 = this.aClass183_824.method2624(2, n2 >> 2132424048);
            n2 &= 0xFFFF;
            if (method2624 == null) {
                return class146.method2341(b, n3, true);
            }
            Class98_Sub46_Sub16 method2625 = null;
            if ((this.aBoolean825 || Class357.aBoolean3027) && ~n4 != 0x0 && ~n4 > ~this.anIntArray818.length) {
                n4 = this.anIntArray818[n4];
                method2625 = this.aClass183_824.method2624(2, n4 >> -768659376);
                n4 &= 0xFFFF;
            }
            if (this.aBoolean817) {
                n3 |= 0x200;
            }
            if (method2624.method1619(n2, 31239)) {
                n3 |= 0x80;
            }
            if (method2624.method1617(false, n2)) {
                n3 |= 0x100;
            }
            if (method2624.method1615(n2, false)) {
                n3 |= 0x400;
            }
            if (method2625 != null) {
                if (method2625.method1619(n4, 31239)) {
                    n3 |= 0x80;
                }
                if (method2625.method1617(false, n4)) {
                    n3 |= 0x100;
                }
                if (method2625.method1615(n4, false)) {
                    n3 |= 0x400;
                }
            }
            n3 |= 0x20;
            final Class146 method2626 = class146.method2341(b, n3, true);
            method2626.method2338(n5 - 1, method2624, n2, method2625, this.aBoolean817, n, -107, n6, n4);
            return method2626;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gaa.F(" + b + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + b2 + ',' + ((class146 != null) ? "{...}" : "null") + ',' + n5 + ')');
        }
    }
    
    static final Class246_Sub3_Sub4 method931(final int n, final int n2, final int n3, final Class clazz) {
        final Class172 class172 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[n][n2][n3];
        if (class172 == null) {
            return null;
        }
        for (Class154 class173 = class172.aClass154_1325; class173 != null; class173 = class173.aClass154_1233) {
            final Class246_Sub3_Sub4 aClass246_Sub3_Sub4_1232 = class173.aClass246_Sub3_Sub4_1232;
            if (clazz.isAssignableFrom(aClass246_Sub3_Sub4_1232.getClass()) && aClass246_Sub3_Sub4_1232.aShort6158 == n2 && aClass246_Sub3_Sub4_1232.aShort6157 == n3) {
                return aClass246_Sub3_Sub4_1232;
            }
        }
        return null;
    }
    
    final int method932(final boolean b, final int n, final boolean b2, final int n2) {
        try {
            int n3 = 0;
            int n4 = 0;
            final int n5 = this.anIntArray818[n];
            Class98_Sub46_Sub16 method2624 = null;
            final Class98_Sub46_Sub16 method2625 = this.aClass183_824.method2624(2, n5 >> 729062096);
            final int n6 = n5 & 0xFFFF;
            if (method2625 == null) {
                return n3;
            }
            if (!b2) {
                return -128;
            }
            if ((this.aBoolean825 || Class357.aBoolean3027) && n2 != -1 && this.anIntArray818.length > n2) {
                final int n7 = this.anIntArray818[n2];
                method2624 = this.aClass183_824.method2624(2, n7 >> 1613080208);
                n4 = (n7 & 0xFFFF);
            }
            if (this.aBoolean817) {
                n3 |= 0x200;
            }
            if (method2625.method1619(n6, 31239)) {
                n3 |= 0x80;
            }
            if (method2625.method1617(false, n6)) {
                n3 |= 0x100;
            }
            if (method2625.method1615(n6, false)) {
                n3 |= 0x400;
            }
            if (method2624 != null) {
                if (method2624.method1619(n4, 31239)) {
                    n3 |= 0x80;
                }
                if (method2624.method1617(false, n4)) {
                    n3 |= 0x100;
                }
                if (method2624.method1615(n4, false)) {
                    n3 |= 0x400;
                }
            }
            if (this.anIntArray827 != null && b) {
                if (~n > ~this.anIntArray827.length) {
                    final int n8 = this.anIntArray827[n];
                    if (~n8 != 0xFFFF0000) {
                        final Class98_Sub46_Sub16 method2626 = this.aClass183_824.method2624(2, n8 >> -1481112880);
                        final int n9 = n8 & 0xFFFF;
                        if (method2626 != null) {
                            if (method2626.method1619(n9, 31239)) {
                                n3 |= 0x80;
                            }
                            if (method2626.method1617(false, n9)) {
                                n3 |= 0x100;
                            }
                            if (method2626.method1615(n9, false)) {
                                n3 |= 0x400;
                            }
                        }
                    }
                }
                if ((this.aBoolean825 || Class357.aBoolean3027) && n2 != -1 && n2 < this.anIntArray827.length) {
                    final int n10 = this.anIntArray827[n2];
                    if (~n10 != 0xFFFF0000) {
                        final Class98_Sub46_Sub16 method2627 = this.aClass183_824.method2624(2, n10 >> 1795578800);
                        final int n11 = n10 & 0xFFFF;
                        if (method2627 != null) {
                            if (method2627.method1619(n11, 31239)) {
                                n3 |= 0x80;
                            }
                            if (method2627.method1617(false, n11)) {
                                n3 |= 0x100;
                            }
                            if (method2627.method1615(n11, false)) {
                                n3 |= 0x400;
                            }
                        }
                    }
                }
            }
            return 0x20 | n3;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gaa.J(" + b + ',' + n + ',' + b2 + ',' + n2 + ')');
        }
    }
    
    final void method933(final Class98_Sub22 class98_Sub22, final int n) {
        try {
            while (true) {
                final int unsignedByte = class98_Sub22.readUnsignedByte((byte)(-99));
                if (unsignedByte == 0) {
                    break;
                }
                this.method939(unsignedByte, class98_Sub22, 14735);
            }
            if (n >= -92) {
                this.method938(-54);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gaa.C(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    public static void method934(final byte b) {
        try {
            Class97.aClass232_806 = null;
            if (b == -26) {
                Class97.anIntArrayArray814 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gaa.E(" + b + ')');
        }
    }
    
    static final boolean method935(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        try {
            if (n4 <= 45) {
                return false;
            }
            for (int n7 = n3; ~n2 <= ~n7; ++n7) {
                for (int i = n5; i <= n; ++i) {
                    if (~Class372.anIntArrayArray3149[n7][i] == ~n6 && ~Class64_Sub28.anIntArrayArray3719[n7][i] >= -2) {
                        return true;
                    }
                }
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gaa.B(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    static final void method936(final int anInt6076, final int n) {
        try {
            if (n == 1024) {
                za_Sub1.anInt6076 = anInt6076;
                synchronized (Class299.aClass79_2493) {
                    Class299.aClass79_2493.method794(87);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gaa.D(" + anInt6076 + ',' + n + ')');
        }
    }
    
    final Class146 method937(int n, final int n2, int n3, final int n4, final Class146 class146, final int n5) {
        try {
            final int n6 = this.anIntArray811[n5];
            final int n7 = this.anIntArray818[n5];
            final Class98_Sub46_Sub16 method2624 = this.aClass183_824.method2624(2, n7 >> -2046821168);
            final int n8 = n7 & 0xFFFF;
            if (method2624 == null) {
                return class146.method2341((byte)1, n3, true);
            }
            Class98_Sub46_Sub16 method2625 = null;
            if ((this.aBoolean825 || Class357.aBoolean3027) && n != -1 && ~this.anIntArray818.length < ~n) {
                n = this.anIntArray818[n];
                method2625 = this.aClass183_824.method2624(2, n >> 842978512);
                n &= 0xFFFF;
            }
            Class98_Sub46_Sub16 method2626 = null;
            Class98_Sub46_Sub16 method2627 = null;
            int n9 = 0;
            int n10 = 0;
            if (this.anIntArray827 != null) {
                if (~this.anIntArray827.length < ~n5) {
                    n9 = this.anIntArray827[n5];
                    if (n9 != 65535) {
                        method2626 = this.aClass183_824.method2624(2, n9 >> -762103696);
                        n9 &= 0xFFFF;
                    }
                }
                if ((this.aBoolean825 || Class357.aBoolean3027) && n != -1 && n < this.anIntArray827.length) {
                    n10 = this.anIntArray827[n];
                    if (n10 != 65535) {
                        method2627 = this.aClass183_824.method2624(2, n10 >> 730805744);
                        n10 &= 0xFFFF;
                    }
                }
            }
            if (this.aBoolean817) {
                n3 |= 0x200;
            }
            if (method2624.method1619(n8, 31239)) {
                n3 |= 0x80;
            }
            if (method2624.method1617(false, n8)) {
                n3 |= 0x100;
            }
            if (method2624.method1615(n8, false)) {
                n3 |= 0x400;
            }
            if (method2626 != null) {
                if (method2626.method1619(n9, 31239)) {
                    n3 |= 0x80;
                }
                if (method2626.method1617(false, n9)) {
                    n3 |= 0x100;
                }
                if (method2626.method1615(n9, false)) {
                    n3 |= 0x400;
                }
            }
            if (method2625 != null) {
                if (method2625.method1619(n, 31239)) {
                    n3 |= 0x80;
                }
                if (method2625.method1617(false, n)) {
                    n3 |= 0x100;
                }
                if (method2625.method1615(n, false)) {
                    n3 |= 0x400;
                }
            }
            if (method2627 != null) {
                if (method2627.method1619(n10, 31239)) {
                    n3 |= 0x80;
                }
                if (method2627.method1617(false, n10)) {
                    n3 |= 0x100;
                }
                if (method2627.method1615(n10, false)) {
                    n3 |= 0x400;
                }
            }
            n3 |= 0x20;
            final Class146 method2628 = class146.method2341((byte)1, n3, true);
            method2628.method2338(n2 - 1, method2624, n8, method2625, this.aBoolean817, 0, 119, n6, n);
            if (method2626 != null) {
                method2628.method2338(-1 + n2, method2626, n9, method2627, this.aBoolean817, 0, -106, n6, n10);
            }
            return method2628;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gaa.A(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + ((class146 != null) ? "{...}" : "null") + ',' + n5 + ')');
        }
    }
    
    final void method938(final int n) {
        try {
            Label_0035: {
                if (~this.anInt821 == 0x0) {
                    if (this.aBooleanArray813 == null) {
                        this.anInt821 = 0;
                        if (!client.aBoolean3553) {
                            break Label_0035;
                        }
                    }
                    this.anInt821 = 2;
                }
            }
            if (this.anInt816 == -1) {
                if (this.aBooleanArray813 == null) {
                    this.anInt816 = 0;
                }
                else {
                    this.anInt816 = 2;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gaa.I(" + n + ')');
        }
    }
    
    private final void method939(final int n, final Class98_Sub22 class98_Sub22, final int n2) {
        try {
            if (n == 1) {
                final int i = class98_Sub22.readShort((byte)127);
                this.anIntArray811 = new int[i];
                for (int n3 = 0; i > n3; ++n3) {
                    this.anIntArray811[n3] = class98_Sub22.readShort((byte)127);
                }
                this.anIntArray818 = new int[i];
                for (int n4 = 0; ~i < ~n4; ++n4) {
                    this.anIntArray818[n4] = class98_Sub22.readShort((byte)127);
                }
                for (int n5 = 0; ~i < ~n5; ++n5) {
                    this.anIntArray818[n5] = (class98_Sub22.readShort((byte)127) << 1514351920) - -this.anIntArray818[n5];
                }
            }
            else if (~n == 0xFFFFFFFD) {
                this.anInt828 = class98_Sub22.readShort((byte)127);
            }
            else if (~n != 0xFFFFFFFC) {
                if (~n == 0xFFFFFFFA) {
                    this.anInt829 = class98_Sub22.readUnsignedByte((byte)(-108));
                }
                else if (~n == 0xFFFFFFF9) {
                    this.anInt820 = class98_Sub22.readShort((byte)127);
                }
                else if (n != 7) {
                    if (~n != 0xFFFFFFF7) {
                        if (~n == 0xFFFFFFF6) {
                            this.anInt821 = class98_Sub22.readUnsignedByte((byte)110);
                        }
                        else if (~n == 0xFFFFFFF5) {
                            this.anInt816 = class98_Sub22.readUnsignedByte((byte)59);
                        }
                        else if (~n != 0xFFFFFFF4) {
                            if (n != 12) {
                                if (~n != 0xFFFFFFF2) {
                                    if (n != 14) {
                                        if (~n == 0xFFFFFFF0) {
                                            this.aBoolean825 = true;
                                        }
                                        else if (~n != 0xFFFFFFEF) {
                                            if (n != 18) {
                                                if (n != 19) {
                                                    if (n == 20) {
                                                        if (this.anIntArray810 == null || this.anIntArray815 == null) {
                                                            this.anIntArray810 = new int[this.anIntArrayArray822.length];
                                                            this.anIntArray815 = new int[this.anIntArrayArray822.length];
                                                            for (int n6 = 0; ~n6 > ~this.anIntArrayArray822.length; ++n6) {
                                                                this.anIntArray810[n6] = 256;
                                                                this.anIntArray815[n6] = 256;
                                                            }
                                                        }
                                                        final int unsignedByte = class98_Sub22.readUnsignedByte((byte)(-108));
                                                        this.anIntArray810[unsignedByte] = class98_Sub22.readShort((byte)127);
                                                        this.anIntArray815[unsignedByte] = class98_Sub22.readShort((byte)127);
                                                    }
                                                }
                                                else {
                                                    if (this.anIntArray808 == null) {
                                                        this.anIntArray808 = new int[this.anIntArrayArray822.length];
                                                        for (int n7 = 0; ~this.anIntArrayArray822.length < ~n7; ++n7) {
                                                            this.anIntArray808[n7] = 255;
                                                        }
                                                    }
                                                    this.anIntArray808[class98_Sub22.readUnsignedByte((byte)25)] = class98_Sub22.readUnsignedByte((byte)(-125));
                                                }
                                            }
                                            else {
                                                this.aBoolean812 = true;
                                            }
                                        }
                                        else {
                                            this.aBoolean823 = true;
                                        }
                                    }
                                    else {
                                        this.aBoolean817 = true;
                                    }
                                }
                                else {
                                    final int short1 = class98_Sub22.readShort((byte)127);
                                    this.anIntArrayArray822 = new int[short1][];
                                    for (int n8 = 0; ~n8 > ~short1; ++n8) {
                                        final int unsignedByte2 = class98_Sub22.readUnsignedByte((byte)125);
                                        if (unsignedByte2 > 0) {
                                            (this.anIntArrayArray822[n8] = new int[unsignedByte2])[0] = class98_Sub22.method1186(Class369.method3953(n2, -14837));
                                            for (int n9 = 1; ~n9 > ~unsignedByte2; ++n9) {
                                                this.anIntArrayArray822[n8][n9] = class98_Sub22.readShort((byte)127);
                                            }
                                        }
                                    }
                                }
                            }
                            else {
                                final int unsignedByte3 = class98_Sub22.readUnsignedByte((byte)11);
                                this.anIntArray827 = new int[unsignedByte3];
                                for (int j = 0; j < unsignedByte3; ++j) {
                                    this.anIntArray827[j] = class98_Sub22.readShort((byte)127);
                                }
                                for (int k = 0; k < unsignedByte3; ++k) {
                                    this.anIntArray827[k] = (class98_Sub22.readShort((byte)127) << 1603208048) - -this.anIntArray827[k];
                                }
                            }
                        }
                        else {
                            this.anInt819 = class98_Sub22.readUnsignedByte((byte)(-114));
                        }
                    }
                    else {
                        this.anInt807 = class98_Sub22.readUnsignedByte((byte)17);
                    }
                }
                else {
                    this.anInt809 = class98_Sub22.readShort((byte)127);
                }
            }
            else {
                this.aBooleanArray813 = new boolean[256];
                for (int l = class98_Sub22.readUnsignedByte((byte)33), n10 = 0; l > n10; ++n10) {
                    this.aBooleanArray813[class98_Sub22.readUnsignedByte((byte)(-119))] = true;
                }
            }
            if (n2 != 14735) {
                this.method938(-80);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gaa.H(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n2 + ')');
        }
    }
    
    public Class97() {
        this.anInt809 = -1;
        this.aBoolean812 = false;
        this.anInt807 = 99;
        this.anInt821 = -1;
        this.anInt819 = 2;
        this.anInt820 = -1;
        this.anInt816 = -1;
        this.aBoolean817 = false;
        this.aBoolean823 = false;
        this.aBoolean825 = false;
        this.anInt829 = 5;
        this.anInt828 = -1;
    }
    
    static {
        Class97.aBoolean830 = false;
        Class97.aClass232_806 = new Class232();
    }
}
