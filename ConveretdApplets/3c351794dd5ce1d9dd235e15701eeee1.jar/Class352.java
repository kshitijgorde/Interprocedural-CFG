// 
// Decompiled by Procyon v0.5.30
// 

final class Class352
{
    boolean aBoolean2925;
    int[] anIntArray2926;
    boolean aBoolean2927;
    int[] anIntArray2928;
    private int anInt2929;
    private byte aByte2930;
    private int anInt2931;
    private byte aByte2932;
    int anInt2933;
    int[] anIntArray2934;
    boolean aBoolean2935;
    int id;
    private int[] anIntArray2937;
    private int anInt2938;
    String[] aStringArray2939;
    private int anInt2940;
    int anInt2941;
    private byte aByte2942;
    static IncomingOpcode aClass58_2943;
    private Class377 aClass377_2944;
    int anInt2945;
    private int anInt2946;
    boolean aBoolean2947;
    int anInt2948;
    int anInt2949;
    int anInt2950;
    int[][] anIntArrayArray2951;
    String name;
    int anInt2953;
    private int anInt2954;
    private byte[] aByteArray2955;
    int anInt2956;
    boolean aBoolean2957;
    int anInt2958;
    boolean clippingFlag;
    boolean walkable;
    boolean aBoolean2961;
    int anInt2962;
    Class302 aClass302_2963;
    private int anInt2964;
    short[] aShortArray2965;
    int anInt2966;
    private byte aByte2967;
    private int anInt2968;
    boolean aBoolean2969;
    int anInt2970;
    private byte aByte2971;
    int anInt2972;
    private int anInt2973;
    short[] aShortArray2974;
    int anInt2975;
    boolean aBoolean2976;
    int anInt2977;
    int sizeX;
    int[] anIntArray2979;
    private int anInt2980;
    int anInt2981;
    boolean aBoolean2982;
    private int anInt2983;
    boolean aBoolean2984;
    private int anInt2985;
    int anInt2986;
    int anInt2987;
    private int anInt2988;
    private int anInt2989;
    int anInt2990;
    int sizeY;
    boolean aBoolean2992;
    static IncomingOpcode aClass58_2993;
    byte[] aByteArray2994;
    private short[] aShortArray2995;
    int anInt2996;
    private int anInt2997;
    int anInt2998;
    int actionCount;
    static Class332[] aClass332Array3000;
    static int[] anIntArray3001;
    int anInt3002;
    private short[] aShortArray3003;
    boolean aBoolean3004;
    boolean aBoolean3005;
    int anInt3006;
    boolean aBoolean3007;
    int anInt3008;
    
    final void method3850(final Class98_Sub22 class98_Sub22, final boolean b) {
        try {
            if (b) {
                this.anIntArrayArray2951 = null;
            }
            while (true) {
                final int unsignedByte = class98_Sub22.readUnsignedByte((byte)24);
                if (unsignedByte == 0) {
                    break;
                }
                this.method3863(class98_Sub22, unsignedByte, 7);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vha.F(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    final Class298 method3851(final int n, final boolean b, final s s, final int n2, final int n3, final boolean b2, final int n4, final int n5, final Class185 class185, final s s2, final ha ha, int n6) {
        try {
            if (s.method3419(-95, n6)) {
                n6 = 4;
            }
            final long n7 = n2 + ((n6 << 300896803) + (this.id << -1555969174));
            if (b) {
                this.method3857(-75);
            }
            long n8 = n7 | ha.anInt937 << -1993352131;
            if (class185 != null) {
                n8 |= class185.aLong1448 << -2073542368;
            }
            int method1777 = n5;
            if (this.aByte2971 == 3) {
                method1777 |= 0x7;
            }
            else {
                if (~this.aByte2971 != -1 || this.anInt2988 != 0) {
                    method1777 |= 0x2;
                }
                if (~this.anInt2940 != -1) {
                    method1777 |= 0x1;
                }
                if (this.anInt2989 != 0) {
                    method1777 |= 0x4;
                }
            }
            if (b2) {
                method1777 |= 0x40000;
            }
            final Class298 class186;
            synchronized (this.aClass302_2963.aClass79_2525) {
                class186 = (Class298)this.aClass302_2963.aClass79_2525.method802(-124, n8);
            }
            final Class146 class187 = (class186 != null) ? class186.aClass146_2477 : null;
            r r = null;
            Class146 aClass146_2477;
            if (class187 != null && ~ha.c(class187.ua(), method1777) == -1) {
                r = class186.aR2479;
                aClass146_2477 = class186.aClass146_2477;
                if (b2 && r == null) {
                    final Class298 class188 = class186;
                    final r ba = aClass146_2477.ba(null);
                    class188.aR2479 = ba;
                    r = ba;
                }
            }
            else {
                if (class187 != null) {
                    method1777 = ha.method1777(method1777, class187.ua());
                }
                int n9 = method1777;
                if (n6 == 10 && ~n2 < -4) {
                    n9 |= 0x5;
                }
                aClass146_2477 = this.method3855(n6, n2, ha, n9, class185, (byte)(-75));
                if (aClass146_2477 == null) {
                    return null;
                }
                if (~n6 == 0xFFFFFFF5 && n2 > 3) {
                    aClass146_2477.a(2048);
                }
                if (b2) {
                    r = aClass146_2477.ba(null);
                }
                aClass146_2477.s(method1777);
                final Class298 class189 = new Class298();
                class189.aR2479 = r;
                class189.aClass146_2477 = aClass146_2477;
                synchronized (this.aClass302_2963.aClass79_2525) {
                    this.aClass302_2963.aClass79_2525.method805(n8, class189, (byte)(-80));
                }
            }
            final boolean b3 = ~this.aByte2971 != -1 && (s2 != null || s != null);
            final boolean b4 = this.anInt2940 != 0 || this.anInt2988 != 0 || ~this.anInt2989 != -1;
            Class146 aClass146_2478;
            if (!b3 && !b4) {
                aClass146_2478 = aClass146_2477.method2341((byte)0, n5, true);
            }
            else {
                aClass146_2478 = aClass146_2477.method2341((byte)0, method1777, true);
                if (b3) {
                    aClass146_2478.p(this.aByte2971, this.anInt2985, s2, s, n4, n3, n);
                }
                if (b4) {
                    aClass146_2478.H(this.anInt2940, this.anInt2988, this.anInt2989);
                }
                aClass146_2478.s(n5);
            }
            Class224_Sub3_Sub1.aClass298_6145.aR2479 = r;
            Class224_Sub3_Sub1.aClass298_6145.aClass146_2477 = aClass146_2478;
            return Class224_Sub3_Sub1.aClass298_6145;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vha.B(" + n + ',' + b + ',' + ((s != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ',' + b2 + ',' + n4 + ',' + n5 + ',' + ((class185 != null) ? "{...}" : "null") + ',' + ((s2 != null) ? "{...}" : "null") + ',' + ((ha != null) ? "{...}" : "null") + ',' + n6 + ')');
        }
    }
    
    final Class352 method3852(final Interface6 interface6, final byte b) {
        try {
            if (b >= -37) {
                this.method3864((byte)102, 45, null);
            }
            int n = -1;
            Label_0071: {
                if (this.anInt2983 != -1) {
                    n = interface6.method7(this.anInt2983, 7373);
                    if (!client.aBoolean3553) {
                        break Label_0071;
                    }
                }
                if (~this.anInt2968 != 0x0) {
                    n = interface6.method6(this.anInt2968, -121);
                }
            }
            if (n >= 0 && ~n > ~(this.anIntArray2928.length - 1) && ~this.anIntArray2928[n] != 0x0) {
                return this.aClass302_2963.method3546(this.anIntArray2928[n], (byte)119);
            }
            final int n2 = this.anIntArray2928[this.anIntArray2928.length - 1];
            if (~n2 != 0x0) {
                return this.aClass302_2963.method3546(n2, (byte)119);
            }
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vha.K(" + ((interface6 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    final boolean method3853(final byte b, final int n) {
        try {
            if (b != 49) {
                this.aClass302_2963 = null;
            }
            if (this.anIntArrayArray2951 == null) {
                return true;
            }
            synchronized (this.aClass302_2963.aClass207_2512) {
                for (int n2 = 0; this.aByteArray2994.length > n2; ++n2) {
                    if (~n == ~this.aByteArray2994[n2]) {
                        for (int n3 = 0; this.anIntArrayArray2951[n2].length > n3; ++n3) {
                            if (!this.aClass302_2963.aClass207_2512.method2751(0, this.anIntArrayArray2951[n2][n3], -6329)) {
                                return false;
                            }
                        }
                        return true;
                    }
                }
            }
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vha.L(" + b + ',' + n + ')');
        }
    }
    
    private final void method3854(final Class98_Sub22 class98_Sub22, final int n) {
        try {
            for (int unsignedByte = class98_Sub22.readUnsignedByte((byte)(-113)), i = 0; i < unsignedByte; ++i) {
                ++class98_Sub22.anInt3991;
                class98_Sub22.anInt3991 += 2 * class98_Sub22.readUnsignedByte((byte)25);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vha.Q(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    private final Class146 method3855(final int n, int n2, final ha ha, int n3, final Class185 class185, final byte b) {
        try {
            final int n4 = 64 + this.anInt2931;
            final int n5 = 850 + this.anInt2980;
            final int n6 = n3;
            final boolean b2 = this.aBoolean2961 || (~n == 0xFFFFFFFD && n2 > 3);
            if (b2) {
                n3 |= 0x10;
            }
            if (~n2 == -1) {
                if (~this.anInt2938 != 0xFFFFFF7F || this.anInt2973 != 0) {
                    n3 |= 0x1;
                }
                if (~this.anInt2929 != 0xFFFFFF7F || this.anInt2946 != 0) {
                    n3 |= 0x4;
                }
            }
            else {
                n3 |= 0xD;
            }
            if (~this.anInt2954 != 0xFFFFFF7F || this.anInt2997 != 0) {
                n3 |= 0x2;
            }
            if (this.aShortArray3003 != null) {
                n3 |= 0x4000;
            }
            if (this.aShortArray2995 != null) {
                n3 |= 0x8000;
            }
            if (~this.aByte2932 != -1) {
                n3 |= 0x80000;
            }
            Class146 method1790 = null;
            if (this.aByteArray2994 == null) {
                return null;
            }
            int n7 = -1;
            for (int n8 = 0; ~this.aByteArray2994.length < ~n8; ++n8) {
                if (~n == ~this.aByteArray2994[n8]) {
                    n7 = n8;
                    break;
                }
            }
            if (~n7 == 0x0) {
                return null;
            }
            final int[] array = (class185 != null && class185.anIntArray1446 != null) ? class185.anIntArray1446 : this.anIntArrayArray2951[n7];
            final int length = array.length;
            if (~length < -1) {
                long n9 = ha.anInt937;
                for (int i = 0; i < length; ++i) {
                    n9 = array[i] + n9 * 67783L;
                }
                synchronized (this.aClass302_2963.aClass79_2522) {
                    method1790 = (Class146)this.aClass302_2963.aClass79_2522.method802(-122, n9);
                }
                if (method1790 != null) {
                    if (n4 != method1790.WA()) {
                        n3 |= 0x1000;
                    }
                    if (n5 != method1790.da()) {
                        n3 |= 0x2000;
                    }
                }
                if (method1790 == null || ~ha.c(method1790.ua(), n3) != -1) {
                    int method1791 = 0x1F01F | n3;
                    if (method1790 != null) {
                        method1791 = ha.method1777(method1791, method1790.ua());
                    }
                    Class178 method1792 = null;
                    synchronized (Class64_Sub20.aClass178Array3699) {
                        for (int j = 0; j < length; ++j) {
                            synchronized (this.aClass302_2963.aClass207_2512) {
                                method1792 = Class98_Sub6.method981(0, -9252, this.aClass302_2963.aClass207_2512, array[j] & 0xFFFF);
                            }
                            if (method1792 == null) {
                                return null;
                            }
                            if (method1792.anInt1387 < 13) {
                                method1792.method2592(13746, 2);
                            }
                            if (length > 1) {
                                Class64_Sub20.aClass178Array3699[j] = method1792;
                            }
                        }
                        if (~length < -2) {
                            method1792 = new Class178(Class64_Sub20.aClass178Array3699, length);
                        }
                    }
                    method1790 = ha.method1790(method1792, method1791, this.aClass302_2963.anInt2528, n4, n5);
                    synchronized (this.aClass302_2963.aClass79_2522) {
                        this.aClass302_2963.aClass79_2522.method805(n9, method1790, (byte)(-80));
                    }
                }
            }
            if (method1790 == null) {
                return null;
            }
            final Class146 method1793 = method1790.method2341((byte)0, n3, true);
            if (~n4 != ~method1790.WA()) {
                method1793.C(n4);
            }
            if (~method1790.da() != ~n5) {
                method1793.LA(n5);
            }
            if (b2) {
                method1793.v();
            }
            if (n == 4 && n2 > 3) {
                method1793.k(2048);
                method1793.H(180, 0, -180);
            }
            n2 &= 0x3;
            if (b != -75) {
                return null;
            }
            if (~n2 != 0xFFFFFFFE) {
                if (n2 != 2) {
                    if (~n2 == 0xFFFFFFFC) {
                        method1793.k(12288);
                    }
                }
                else {
                    method1793.k(8192);
                }
            }
            else {
                method1793.k(4096);
            }
            if (this.aShortArray3003 != null) {
                short[] array2;
                if (class185 == null || class185.aShortArray1447 == null) {
                    array2 = this.aShortArray2965;
                }
                else {
                    array2 = class185.aShortArray1447;
                }
                for (int n10 = 0; ~n10 > ~this.aShortArray3003.length; ++n10) {
                    if (this.aByteArray2955 == null || ~n10 <= ~this.aByteArray2955.length) {
                        method1793.ia(this.aShortArray3003[n10], array2[n10]);
                    }
                    else {
                        method1793.ia(this.aShortArray3003[n10], Class372.aShortArray3153[0xFF & this.aByteArray2955[n10]]);
                    }
                }
            }
            if (this.aShortArray2995 != null) {
                short[] array3;
                if (class185 == null || class185.aShortArray1444 == null) {
                    array3 = this.aShortArray2974;
                }
                else {
                    array3 = class185.aShortArray1444;
                }
                for (int n11 = 0; ~this.aShortArray2995.length < ~n11; ++n11) {
                    method1793.aa(this.aShortArray2995[n11], array3[n11]);
                }
            }
            if (this.aByte2932 != 0) {
                method1793.method2337(this.aByte2930, this.aByte2942, this.aByte2967, 0xFF & this.aByte2932);
            }
            if (this.anInt2938 != 128 || this.anInt2954 != 128 || this.anInt2929 != 128) {
                method1793.O(this.anInt2938, this.anInt2954, this.anInt2929);
            }
            if (~this.anInt2973 != -1 || this.anInt2997 != 0 || ~this.anInt2946 != -1) {
                method1793.H(this.anInt2973, this.anInt2997, this.anInt2946);
            }
            method1793.s(n6);
            return method1793;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vha.H(" + n + ',' + n2 + ',' + ((ha != null) ? "{...}" : "null") + ',' + n3 + ',' + ((class185 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static final void method3856(final byte b) {
        try {
            for (int i = 0; i < 5; ++i) {
                Class217.aBooleanArray3410[i] = false;
            }
            Class98_Sub10_Sub14.anInt5613 = Class215.anInt1614;
            Class299.anInt2494 = Class246_Sub3_Sub4_Sub2.anInt6357;
            Class98_Sub4.anInt3828 = 0;
            Class98_Sub46_Sub20_Sub2.anInt6319 = 5;
            Class246_Sub3_Sub4_Sub2_Sub1.anInt6511 = Class98_Sub46_Sub10.anInt6020;
            Class96.anInt801 = Class186.anInt3424;
            Class98_Sub41.anInt4202 = 0;
            Class98_Sub50.anInt4292 = Class134.anInt3461;
            Class368.anInt3128 = -1;
            Class53_Sub1.anInt3636 = -1;
            Class363.anInt3095 = Class79.anInt601;
            Class116.anInt967 = (Class64_Sub26.anInt3712 = -1);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vha.E(" + b + ')');
        }
    }
    
    final boolean method3857(final int n) {
        try {
            if (this.anIntArrayArray2951 == null) {
                return true;
            }
            boolean b = true;
            synchronized (this.aClass302_2963.aClass207_2512) {
                for (int n2 = 0; this.anIntArrayArray2951.length > n2; ++n2) {
                    for (int n3 = 0; ~this.anIntArrayArray2951[n2].length < ~n3; ++n3) {
                        b &= this.aClass302_2963.aClass207_2512.method2751(0, this.anIntArrayArray2951[n2][n3], -6329);
                    }
                }
            }
            return n != 18182 || b;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vha.J(" + n + ')');
        }
    }
    
    final boolean method3858(final int n) {
        try {
            if (this.anIntArray2928 == null) {
                return this.anInt2996 != -1 || this.anIntArray2926 != null;
            }
            if (n <= 91) {
                return false;
            }
            for (int n2 = 0; ~this.anIntArray2928.length < ~n2; ++n2) {
                if (this.anIntArray2928[n2] != -1) {
                    final Class352 method3546 = this.aClass302_2963.method3546(this.anIntArray2928[n2], (byte)119);
                    if (method3546.anInt2996 != -1 || method3546.anIntArray2926 != null) {
                        return true;
                    }
                }
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vha.P(" + n + ')');
        }
    }
    
    final Class146 method3859(final s s, final int n, final int n2, final byte b, final ha ha, final s s2, int method1777, final int n3, final int n4, int n5, final Class185 class185, final int n6, final Class97 class186, final int n7, final int n8) {
        try {
            if (s.method3419(112, n5)) {
                n5 = 4;
            }
            final long n9 = n3 + ((this.id << -1435947510) - -(n5 << -1440567773));
            final int n10 = method1777;
            long n11 = n9 | ha.anInt937 << 2020858845;
            if (class185 != null) {
                n11 |= class185.aLong1448 << -950504928;
            }
            if (class186 != null) {
                method1777 |= class186.method932(false, n6, true, n7);
            }
            Label_0172: {
                if (~this.aByte2971 != 0xFFFFFFFC) {
                    if (this.aByte2971 != 0 || ~this.anInt2988 != -1) {
                        method1777 |= 0x2;
                    }
                    if (~this.anInt2940 != -1) {
                        method1777 |= 0x1;
                    }
                    if (this.anInt2989 == 0) {
                        break Label_0172;
                    }
                    method1777 |= 0x4;
                    if (!client.aBoolean3553) {
                        break Label_0172;
                    }
                }
                method1777 |= 0x7;
            }
            if (n5 == 10 && n3 > 3) {
                method1777 |= 0x5;
            }
            Class146 class187;
            synchronized (this.aClass302_2963.aClass79_2527) {
                class187 = (Class146)this.aClass302_2963.aClass79_2527.method802(-127, n11);
            }
            if (class187 == null || ha.c(class187.ua(), method1777) != 0) {
                if (class187 != null) {
                    method1777 = ha.method1777(method1777, class187.ua());
                }
                class187 = this.method3855(n5, n3, ha, method1777, class185, (byte)(-75));
                if (class187 == null) {
                    return null;
                }
                synchronized (this.aClass302_2963.aClass79_2527) {
                    this.aClass302_2963.aClass79_2527.method805(n11, class187, (byte)(-80));
                }
            }
            int n12 = 0;
            if (class186 != null) {
                n12 = 1;
                class187 = class186.method930((byte)1, 0x3 & n3, n6, method1777, n7, (byte)86, class187, n8);
            }
            if (n5 == 10 && n3 > 3) {
                if (n12 == 0) {
                    n12 = 1;
                    class187 = class187.method2341((byte)3, method1777, true);
                }
                class187.a(2048);
            }
            if (~this.aByte2971 != -1) {
                if (n12 == 0) {
                    class187 = class187.method2341((byte)3, method1777, true);
                    n12 = 1;
                }
                class187.p(this.aByte2971, this.anInt2985, s, s2, n, n2, n4);
            }
            if (~this.anInt2940 != -1 || ~this.anInt2988 != -1 || this.anInt2989 != 0) {
                if (n12 == 0) {
                    n12 = 1;
                    class187 = class187.method2341((byte)3, method1777, true);
                }
                class187.H(this.anInt2940, this.anInt2988, this.anInt2989);
            }
            if (n12 != 0) {
                class187.s(n10);
            }
            return class187;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vha.A(" + ((s != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + b + ',' + ((ha != null) ? "{...}" : "null") + ',' + ((s2 != null) ? "{...}" : "null") + ',' + method1777 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + ((class185 != null) ? "{...}" : "null") + ',' + n6 + ',' + ((class186 != null) ? "{...}" : "null") + ',' + n7 + ',' + n8 + ')');
        }
    }
    
    final boolean method3860(final int n, final boolean b) {
        try {
            if (!b) {
                this.anInt2929 = -32;
            }
            if (~n == 0x0) {
                return false;
            }
            if (~this.anInt2941 == ~n) {
                return true;
            }
            if (this.anIntArray2979 != null) {
                for (int i = 0; i < this.anIntArray2979.length; ++i) {
                    if (~this.anIntArray2979[i] == ~n) {
                        return true;
                    }
                }
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vha.D(" + n + ',' + b + ')');
        }
    }
    
    final int method3861(final int n) {
        try {
            if (n != 0) {
                return 55;
            }
            if (this.anIntArray2979 != null) {
                int n2;
                int n3;
                for (n2 = (int)(this.anInt2964 * Math.random()), n3 = 0; ~this.anIntArray2937[n3] >= ~n2; n2 -= this.anIntArray2937[n3], ++n3) {}
                return this.anIntArray2979[n3];
            }
            return -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vha.C(" + n + ')');
        }
    }
    
    public static void method3862(final int n) {
        try {
            Class352.aClass332Array3000 = null;
            Class352.aClass58_2993 = null;
            if (n > -22) {
                Class352.aClass332Array3000 = null;
            }
            Class352.aClass58_2943 = null;
            Class352.anIntArray3001 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vha.N(" + n + ')');
        }
    }
    
    private final void method3863(final Class98_Sub22 class98_Sub22, final int n, final int n2) {
        try {
            if (n == 1 || n == 5) {
                if (~n == 0xFFFFFFFA && this.aClass302_2963.aBoolean2513) {
                    this.method3854(class98_Sub22, 15);
                }
                final int unsignedByte = class98_Sub22.readUnsignedByte((byte)(-117));
                this.aByteArray2994 = new byte[unsignedByte];
                this.anIntArrayArray2951 = new int[unsignedByte][];
                for (int i = 0; i < unsignedByte; ++i) {
                    this.aByteArray2994[i] = class98_Sub22.readSignedByte((byte)(-19));
                    final int unsignedByte2 = class98_Sub22.readUnsignedByte((byte)(-101));
                    this.anIntArrayArray2951[i] = new int[unsignedByte2];
                    for (int j = 0; j < unsignedByte2; ++j) {
                        this.anIntArrayArray2951[i][j] = class98_Sub22.readShort((byte)127);
                    }
                }
                if (~n == 0xFFFFFFFA && !this.aClass302_2963.aBoolean2513) {
                    this.method3854(class98_Sub22, n2 + 67);
                }
            }
            else if (n == 2) {
                this.name = class98_Sub22.readString((byte)84);
            }
            else if (~n == 0xFFFFFFF1) {
                this.sizeY = class98_Sub22.readUnsignedByte((byte)107);
            }
            else if (n != 15) {
                if (~n != 0xFFFFFFEE) {
                    if (n != 18) {
                        if (n == 19) {
                            this.anInt2998 = class98_Sub22.readUnsignedByte((byte)3);
                        }
                        else if (n != 21) {
                            if (~n == 0xFFFFFFE9) {
                                this.aBoolean3007 = true;
                            }
                            else if (n == 23) {
                                this.anInt2956 = 1;
                            }
                            else if (~n != 0xFFFFFFE7) {
                                if (n == 27) {
                                    this.actionCount = 1;
                                }
                                else if (n != 28) {
                                    if (~n == 0xFFFFFFE2) {
                                        this.anInt2931 = class98_Sub22.readSignedByte((byte)(-19));
                                    }
                                    else if (~n != 0xFFFFFFD8) {
                                        if (~n > -31 || n >= 35) {
                                            if (~n == 0xFFFFFFD7) {
                                                final int unsignedByte3 = class98_Sub22.readUnsignedByte((byte)73);
                                                this.aShortArray3003 = new short[unsignedByte3];
                                                this.aShortArray2965 = new short[unsignedByte3];
                                                for (int k = 0; k < unsignedByte3; ++k) {
                                                    this.aShortArray3003[k] = (short)class98_Sub22.readShort((byte)127);
                                                    this.aShortArray2965[k] = (short)class98_Sub22.readShort((byte)127);
                                                }
                                            }
                                            else if (~n != 0xFFFFFFD6) {
                                                if (~n != 0xFFFFFFD5) {
                                                    if (n == 62) {
                                                        this.aBoolean2961 = true;
                                                    }
                                                    else if (n != 64) {
                                                        if (~n == 0xFFFFFFBE) {
                                                            this.anInt2938 = class98_Sub22.readShort((byte)127);
                                                        }
                                                        else if (~n != 0xFFFFFFBD) {
                                                            if (~n == 0xFFFFFFBC) {
                                                                this.anInt2929 = class98_Sub22.readShort((byte)127);
                                                            }
                                                            else if (~n != 0xFFFFFFBA) {
                                                                if (~n == 0xFFFFFFB9) {
                                                                    this.anInt2973 = class98_Sub22.readUShort(false) << -836995390;
                                                                }
                                                                else if (~n == 0xFFFFFFB8) {
                                                                    this.anInt2997 = class98_Sub22.readUShort(false) << -1352000926;
                                                                }
                                                                else if (~n != 0xFFFFFFB7) {
                                                                    if (n != 73) {
                                                                        if (n != 74) {
                                                                            if (n != 75) {
                                                                                if (~n != 0xFFFFFFB2 && ~n != 0xFFFFFFA3) {
                                                                                    if (n == 78) {
                                                                                        this.anInt2996 = class98_Sub22.readShort((byte)127);
                                                                                        this.anInt2981 = class98_Sub22.readUnsignedByte((byte)(-108));
                                                                                    }
                                                                                    else if (n != 79) {
                                                                                        if (~n != 0xFFFFFFAE) {
                                                                                            if (n == 82) {
                                                                                                this.aBoolean2982 = true;
                                                                                            }
                                                                                            else if (n == 88) {
                                                                                                this.aBoolean2935 = false;
                                                                                            }
                                                                                            else if (~n == 0xFFFFFFA6) {
                                                                                                this.aBoolean2925 = false;
                                                                                            }
                                                                                            else if (n == 91) {
                                                                                                this.aBoolean2927 = true;
                                                                                            }
                                                                                            else if (n != 93) {
                                                                                                if (n == 94) {
                                                                                                    this.aByte2971 = 4;
                                                                                                }
                                                                                                else if (~n == 0xFFFFFFA0) {
                                                                                                    this.aByte2971 = 5;
                                                                                                    this.anInt2985 = class98_Sub22.readUShort(false);
                                                                                                }
                                                                                                else if (~n == 0xFFFFFF9E) {
                                                                                                    this.aBoolean3004 = true;
                                                                                                }
                                                                                                else if (n == 98) {
                                                                                                    this.aBoolean3005 = true;
                                                                                                }
                                                                                                else if (~n != 0xFFFFFF9C) {
                                                                                                    if (n == 100) {
                                                                                                        this.anInt2933 = class98_Sub22.readUnsignedByte((byte)(-123));
                                                                                                        this.anInt2977 = class98_Sub22.readShort((byte)127);
                                                                                                    }
                                                                                                    else if (n == 101) {
                                                                                                        this.anInt2962 = class98_Sub22.readUnsignedByte((byte)(-105));
                                                                                                    }
                                                                                                    else if (n == 102) {
                                                                                                        this.anInt2990 = class98_Sub22.readShort((byte)127);
                                                                                                    }
                                                                                                    else if (~n != 0xFFFFFF98) {
                                                                                                        if (~n == 0xFFFFFF97) {
                                                                                                            this.anInt2987 = class98_Sub22.readUnsignedByte((byte)(-5));
                                                                                                        }
                                                                                                        else if (~n != 0xFFFFFF96) {
                                                                                                            if (n == 106) {
                                                                                                                final int unsignedByte4 = class98_Sub22.readUnsignedByte((byte)(-112));
                                                                                                                this.anIntArray2937 = new int[unsignedByte4];
                                                                                                                this.anIntArray2979 = new int[unsignedByte4];
                                                                                                                for (int n3 = 0; ~n3 > ~unsignedByte4; ++n3) {
                                                                                                                    this.anIntArray2979[n3] = class98_Sub22.readShort((byte)127);
                                                                                                                    final int unsignedByte5 = class98_Sub22.readUnsignedByte((byte)(-116));
                                                                                                                    this.anIntArray2937[n3] = unsignedByte5;
                                                                                                                    this.anInt2964 += unsignedByte5;
                                                                                                                }
                                                                                                            }
                                                                                                            else if (~n != 0xFFFFFF94) {
                                                                                                                if (n < 150 || ~n <= -156) {
                                                                                                                    if (n == 160) {
                                                                                                                        final int unsignedByte6 = class98_Sub22.readUnsignedByte((byte)(-102));
                                                                                                                        this.anIntArray2934 = new int[unsignedByte6];
                                                                                                                        for (int n4 = 0; ~n4 > ~unsignedByte6; ++n4) {
                                                                                                                            this.anIntArray2934[n4] = class98_Sub22.readShort((byte)127);
                                                                                                                        }
                                                                                                                    }
                                                                                                                    else if (n == 162) {
                                                                                                                        this.aByte2971 = 3;
                                                                                                                        this.anInt2985 = class98_Sub22.readInt(-2);
                                                                                                                    }
                                                                                                                    else if (n == 163) {
                                                                                                                        this.aByte2930 = class98_Sub22.readSignedByte((byte)(-19));
                                                                                                                        this.aByte2942 = class98_Sub22.readSignedByte((byte)(-19));
                                                                                                                        this.aByte2967 = class98_Sub22.readSignedByte((byte)(-19));
                                                                                                                        this.aByte2932 = class98_Sub22.readSignedByte((byte)(-19));
                                                                                                                    }
                                                                                                                    else if (n == 164) {
                                                                                                                        this.anInt2940 = class98_Sub22.readUShort(false);
                                                                                                                    }
                                                                                                                    else if (~n != 0xFFFFFF5A) {
                                                                                                                        if (~n != 0xFFFFFF59) {
                                                                                                                            if (~n != 0xFFFFFF58) {
                                                                                                                                if (~n == 0xFFFFFF57) {
                                                                                                                                    this.aBoolean2992 = true;
                                                                                                                                }
                                                                                                                                else if (n == 169) {
                                                                                                                                    this.aBoolean2957 = true;
                                                                                                                                }
                                                                                                                                else if (~n != 0xFFFFFF55) {
                                                                                                                                    if (~n != 0xFFFFFF54) {
                                                                                                                                        if (n == 173) {
                                                                                                                                            this.anInt3006 = class98_Sub22.readShort((byte)127);
                                                                                                                                            this.anInt2950 = class98_Sub22.readShort((byte)127);
                                                                                                                                        }
                                                                                                                                        else if (~n == 0xFFFFFF4E) {
                                                                                                                                            this.aBoolean2984 = true;
                                                                                                                                        }
                                                                                                                                        else if (~n == 0xFFFFFF4D) {
                                                                                                                                            this.anInt2970 = class98_Sub22.readUnsignedByte((byte)(-111));
                                                                                                                                        }
                                                                                                                                        else if (n == 249) {
                                                                                                                                            final int unsignedByte7 = class98_Sub22.readUnsignedByte((byte)107);
                                                                                                                                            if (this.aClass377_2944 == null) {
                                                                                                                                                this.aClass377_2944 = new Class377(Class48.method453(423660257, unsignedByte7));
                                                                                                                                            }
                                                                                                                                            for (int l = 0; l < unsignedByte7; ++l) {
                                                                                                                                                final boolean b = class98_Sub22.readUnsignedByte((byte)(-109)) == 1;
                                                                                                                                                final int method1186 = class98_Sub22.method1186(n2 - 133);
                                                                                                                                                Class98 class98;
                                                                                                                                                if (!b) {
                                                                                                                                                    class98 = new Class98_Sub34(class98_Sub22.readInt(-2));
                                                                                                                                                }
                                                                                                                                                else {
                                                                                                                                                    class98 = new Class98_Sub15(class98_Sub22.readString((byte)84));
                                                                                                                                                }
                                                                                                                                                this.aClass377_2944.method3996(class98, method1186, -1);
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                    else {
                                                                                                                                        this.anInt2953 = class98_Sub22.readSmart(1689622712);
                                                                                                                                    }
                                                                                                                                }
                                                                                                                                else {
                                                                                                                                    this.anInt2986 = class98_Sub22.readSmart(1689622712);
                                                                                                                                }
                                                                                                                            }
                                                                                                                            else {
                                                                                                                                this.anInt2945 = class98_Sub22.readShort((byte)127);
                                                                                                                            }
                                                                                                                        }
                                                                                                                        else {
                                                                                                                            this.anInt2989 = class98_Sub22.readUShort(false);
                                                                                                                        }
                                                                                                                    }
                                                                                                                    else {
                                                                                                                        this.anInt2988 = class98_Sub22.readUShort(false);
                                                                                                                    }
                                                                                                                }
                                                                                                                else {
                                                                                                                    this.aStringArray2939[n - 150] = class98_Sub22.readString((byte)84);
                                                                                                                    if (!this.aClass302_2963.aBoolean2516) {
                                                                                                                        this.aStringArray2939[-150 + n] = null;
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                            else {
                                                                                                                this.anInt2958 = class98_Sub22.readShort((byte)127);
                                                                                                            }
                                                                                                        }
                                                                                                        else {
                                                                                                            this.aBoolean2976 = true;
                                                                                                        }
                                                                                                    }
                                                                                                    else {
                                                                                                        this.anInt2956 = 0;
                                                                                                    }
                                                                                                }
                                                                                                else {
                                                                                                    this.anInt3002 = class98_Sub22.readUnsignedByte((byte)(-112));
                                                                                                    this.anInt3008 = class98_Sub22.readShort((byte)127);
                                                                                                }
                                                                                            }
                                                                                            else {
                                                                                                this.aByte2971 = 3;
                                                                                                this.anInt2985 = class98_Sub22.readShort((byte)127);
                                                                                            }
                                                                                        }
                                                                                        else {
                                                                                            this.aByte2971 = 2;
                                                                                            this.anInt2985 = 256 * class98_Sub22.readUnsignedByte((byte)76);
                                                                                        }
                                                                                    }
                                                                                    else {
                                                                                        this.anInt2949 = class98_Sub22.readShort((byte)127);
                                                                                        this.anInt2972 = class98_Sub22.readShort((byte)127);
                                                                                        this.anInt2981 = class98_Sub22.readUnsignedByte((byte)(-121));
                                                                                        final int unsignedByte8 = class98_Sub22.readUnsignedByte((byte)121);
                                                                                        this.anIntArray2926 = new int[unsignedByte8];
                                                                                        for (int n5 = 0; ~unsignedByte8 < ~n5; ++n5) {
                                                                                            this.anIntArray2926[n5] = class98_Sub22.readShort((byte)127);
                                                                                        }
                                                                                    }
                                                                                }
                                                                                else {
                                                                                    this.anInt2983 = class98_Sub22.readShort((byte)127);
                                                                                    if (~this.anInt2983 == 0xFFFF0000) {
                                                                                        this.anInt2983 = -1;
                                                                                    }
                                                                                    this.anInt2968 = class98_Sub22.readShort((byte)127);
                                                                                    if (this.anInt2968 == 65535) {
                                                                                        this.anInt2968 = -1;
                                                                                    }
                                                                                    int short1 = -1;
                                                                                    if (n == 92) {
                                                                                        short1 = class98_Sub22.readShort((byte)127);
                                                                                        if (short1 == 65535) {
                                                                                            short1 = -1;
                                                                                        }
                                                                                    }
                                                                                    final int unsignedByte9 = class98_Sub22.readUnsignedByte((byte)(-106));
                                                                                    this.anIntArray2928 = new int[unsignedByte9 + 2];
                                                                                    for (int n6 = 0; unsignedByte9 >= n6; ++n6) {
                                                                                        this.anIntArray2928[n6] = class98_Sub22.readShort((byte)127);
                                                                                        if (this.anIntArray2928[n6] == 65535) {
                                                                                            this.anIntArray2928[n6] = -1;
                                                                                        }
                                                                                    }
                                                                                    this.anIntArray2928[1 + unsignedByte9] = short1;
                                                                                }
                                                                            }
                                                                            else {
                                                                                this.anInt2975 = class98_Sub22.readUnsignedByte((byte)74);
                                                                            }
                                                                        }
                                                                        else {
                                                                            this.clippingFlag = true;
                                                                        }
                                                                    }
                                                                    else {
                                                                        this.aBoolean2969 = true;
                                                                    }
                                                                }
                                                                else {
                                                                    this.anInt2946 = class98_Sub22.readUShort(false) << -784917758;
                                                                }
                                                            }
                                                            else {
                                                                this.anInt2948 = class98_Sub22.readUnsignedByte((byte)78);
                                                            }
                                                        }
                                                        else {
                                                            this.anInt2954 = class98_Sub22.readShort((byte)127);
                                                        }
                                                    }
                                                    else {
                                                        this.aBoolean2947 = false;
                                                    }
                                                }
                                                else {
                                                    final int unsignedByte10 = class98_Sub22.readUnsignedByte((byte)106);
                                                    this.aByteArray2955 = new byte[unsignedByte10];
                                                    for (int n7 = 0; ~unsignedByte10 < ~n7; ++n7) {
                                                        this.aByteArray2955[n7] = class98_Sub22.readSignedByte((byte)(-19));
                                                    }
                                                }
                                            }
                                            else {
                                                final int unsignedByte11 = class98_Sub22.readUnsignedByte((byte)2);
                                                this.aShortArray2974 = new short[unsignedByte11];
                                                this.aShortArray2995 = new short[unsignedByte11];
                                                for (int n8 = 0; unsignedByte11 > n8; ++n8) {
                                                    this.aShortArray2995[n8] = (short)class98_Sub22.readShort((byte)127);
                                                    this.aShortArray2974[n8] = (short)class98_Sub22.readShort((byte)127);
                                                }
                                            }
                                        }
                                        else {
                                            this.aStringArray2939[n - 30] = class98_Sub22.readString((byte)84);
                                        }
                                    }
                                    else {
                                        this.anInt2980 = class98_Sub22.readSignedByte((byte)(-19)) * 5;
                                    }
                                }
                                else {
                                    this.anInt2966 = class98_Sub22.readUnsignedByte((byte)110) << -69774750;
                                }
                            }
                            else {
                                this.anInt2941 = class98_Sub22.readShort((byte)127);
                                if (~this.anInt2941 == 0xFFFF0000) {
                                    this.anInt2941 = -1;
                                }
                            }
                        }
                        else {
                            this.aByte2971 = 1;
                        }
                    }
                    else {
                        this.walkable = false;
                    }
                }
                else {
                    this.walkable = false;
                    this.actionCount = 0;
                }
            }
            else {
                this.sizeX = class98_Sub22.readUnsignedByte((byte)35);
            }
            if (n2 != 7) {
                this.method3857(33);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vha.I(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    final String method3864(final byte b, final int n, final String s) {
        try {
            if (b <= 85) {
                this.aShortArray2965 = null;
            }
            if (this.aClass377_2944 == null) {
                return s;
            }
            final Class98_Sub15 class98_Sub15 = (Class98_Sub15)this.aClass377_2944.method3990(n, -1);
            if (class98_Sub15 == null) {
                return s;
            }
            return class98_Sub15.aString3917;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vha.M(" + b + ',' + n + ',' + ((s != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method3865(final int n) {
        try {
            if (~this.anInt2998 == 0x0) {
                this.anInt2998 = 0;
                if (this.aByteArray2994 != null && ~this.aByteArray2994.length == 0xFFFFFFFE && ~this.aByteArray2994[0] == 0xFFFFFFF5) {
                    this.anInt2998 = 1;
                }
                for (int i = 0; i < 5; ++i) {
                    if (this.aStringArray2939[i] != null) {
                        this.anInt2998 = 1;
                        break;
                    }
                }
            }
            if (~this.anInt2941 != 0x0 || this.aBoolean3005 || this.anIntArray2928 != null) {
                this.aBoolean2984 = true;
            }
            if (n <= 14) {
                this.anInt2931 = -53;
            }
            if (this.anInt2975 == -1) {
                this.anInt2975 = ((this.actionCount != 0) ? 1 : 0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vha.G(" + n + ')');
        }
    }
    
    final int method3866(final int n, final int n2, final int n3) {
        try {
            if (n3 != 1) {
                this.method3865(87);
            }
            if (this.aClass377_2944 == null) {
                return n;
            }
            final Class98_Sub34 class98_Sub34 = (Class98_Sub34)this.aClass377_2944.method3990(n2, -1);
            if (class98_Sub34 == null) {
                return n;
            }
            return class98_Sub34.anInt4126;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vha.O(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    public Class352() {
        this.anInt2931 = 0;
        this.anInt2945 = 0;
        this.anIntArray2937 = null;
        this.aBoolean2925 = true;
        this.anInt2941 = -1;
        this.anInt2949 = 0;
        this.aBoolean2935 = true;
        this.aBoolean2947 = true;
        this.anInt2929 = 128;
        this.aByte2932 = 0;
        this.anInt2954 = 128;
        this.aBoolean2957 = false;
        this.anInt2946 = 0;
        this.clippingFlag = false;
        this.aByte2971 = 0;
        this.aBoolean2927 = false;
        this.anInt2953 = 0;
        this.anInt2970 = 0;
        this.aStringArray2939 = new String[5];
        this.anInt2962 = 0;
        this.anInt2966 = 64;
        this.anInt2968 = -1;
        this.anInt2933 = -1;
        this.anInt2973 = 0;
        this.anInt2940 = 0;
        this.anInt2977 = -1;
        this.sizeX = 1;
        this.anInt2983 = -1;
        this.name = "null";
        this.anInt2975 = -1;
        this.anInt2948 = 0;
        this.anInt2958 = -1;
        this.aBoolean2976 = false;
        this.aBoolean2982 = false;
        this.anInt2938 = 128;
        this.anInt2988 = 0;
        this.sizeY = 1;
        this.aBoolean2984 = false;
        this.anInt2989 = 0;
        this.anInt2980 = 0;
        this.anIntArray2979 = null;
        this.anInt2990 = -1;
        this.aBoolean2992 = false;
        this.anInt2950 = 256;
        this.anInt2997 = 0;
        this.walkable = true;
        this.anInt2996 = -1;
        this.aBoolean2961 = false;
        this.anInt2986 = 960;
        this.anInt2964 = 0;
        this.anInt2972 = 0;
        this.aBoolean2969 = false;
        this.aBoolean3004 = false;
        this.anInt2985 = -1;
        this.aBoolean3005 = false;
        this.anInt2981 = 0;
        this.anInt2987 = 255;
        this.aBoolean3007 = false;
        this.anInt3002 = -1;
        this.actionCount = 2;
        this.anInt3006 = 256;
        this.anInt3008 = -1;
        this.anInt2956 = -1;
        this.anInt2998 = -1;
    }
    
    static {
        Class352.aClass58_2943 = new IncomingOpcode(108, 2);
        Class352.aClass58_2993 = new IncomingOpcode(41, 4);
    }
}
