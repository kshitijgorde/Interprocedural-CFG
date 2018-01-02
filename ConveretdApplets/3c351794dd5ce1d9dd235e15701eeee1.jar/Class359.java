// 
// Decompiled by Procyon v0.5.30
// 

final class Class359
{
    private int anInt3035;
    private boolean[] aBooleanArray3036;
    private boolean aBoolean3037;
    int anInt3038;
    private byte aByte3039;
    private byte aByte3040;
    private int anInt3041;
    private int anInt3042;
    private int anInt3043;
    private Class185 aClass185_3044;
    private Class97 aClass97_3045;
    static Class348 aClass348_3046;
    private boolean aBoolean3047;
    private int anInt3048;
    private Class246_Sub3 aClass246_Sub3_3049;
    private int anInt3050;
    private r aR3051;
    int anInt3052;
    private int anInt3053;
    private boolean aBoolean3054;
    private int anInt3055;
    static Class246_Sub3[] aClass246_Sub3Array3056;
    private Class146 aClass146_3057;
    static int anInt3058;
    int anInt3059;
    static int[] anIntArray3060;
    private int anInt3061;
    Class246_Sub5 aClass246_Sub5_3062;
    
    @Override
    protected final void finalize() {
        try {
            if (this.aClass246_Sub5_3062 != null) {
                this.aClass246_Sub5_3062.method3114();
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vo.finalize()");
        }
    }
    
    final void method3892(final ha ha, final int n) {
        try {
            this.method3897(-1, true, 262144, ha, true);
            if (n < 101) {
                this.method3899((byte)(-109));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vo.H(" + ((ha != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    private final void method3893(final Class246_Sub3 class246_Sub3, final int n) {
        try {
            if (n != -23137) {
                this.aBoolean3047 = true;
            }
        Label_0012:
            while (true) {
                if (this.aClass97_3045 == null) {
                    if (this.aBoolean3047) {
                        return;
                    }
                    this.method3902(-1, (byte)101);
                    if (this.aClass97_3045 == null) {
                        return;
                    }
                }
                int anInt3042 = Class215.anInt1614 - this.anInt3048;
                if (anInt3042 > 100 && this.aClass97_3045.anInt828 > 0) {
                    int n2;
                    for (n2 = -this.aClass97_3045.anInt828 + this.aClass97_3045.anIntArray818.length; ~n2 < ~this.anInt3050 && ~this.aClass97_3045.anIntArray811[this.anInt3050] > ~anInt3042; anInt3042 -= this.aClass97_3045.anIntArray811[this.anInt3050], ++this.anInt3050) {}
                    if (~this.anInt3050 <= ~n2) {
                        int n3 = 0;
                        for (int i = n2; i < this.aClass97_3045.anIntArray818.length; ++i) {
                            n3 += this.aClass97_3045.anIntArray811[i];
                        }
                        anInt3042 %= n3;
                    }
                    this.anInt3035 = this.anInt3050 + 1;
                    if (this.anInt3035 >= this.aClass97_3045.anIntArray818.length) {
                        this.anInt3035 -= this.aClass97_3045.anInt828;
                        if (this.anInt3035 < 0 || ~this.aClass97_3045.anIntArray818.length >= ~this.anInt3035) {
                            this.anInt3035 = -1;
                        }
                    }
                }
                while (~this.aClass97_3045.anIntArray811[this.anInt3050] > ~anInt3042) {
                    Class349.method3840((byte)(-127), class246_Sub3, this.anInt3050, this.aClass97_3045);
                    anInt3042 -= this.aClass97_3045.anIntArray811[this.anInt3050];
                    ++this.anInt3050;
                    if (this.aClass97_3045.anIntArray818.length <= this.anInt3050) {
                        this.anInt3050 -= this.aClass97_3045.anInt828;
                        if (~this.anInt3050 > -1 || ~this.aClass97_3045.anIntArray818.length >= ~this.anInt3050) {
                            this.aClass97_3045 = null;
                            continue Label_0012;
                        }
                    }
                    this.anInt3035 = this.anInt3050 + 1;
                    if (this.anInt3035 >= this.aClass97_3045.anIntArray818.length) {
                        this.anInt3035 -= this.aClass97_3045.anInt828;
                        if (~this.anInt3035 <= -1 && this.aClass97_3045.anIntArray818.length > this.anInt3035) {
                            continue;
                        }
                        this.anInt3035 = -1;
                    }
                }
                this.anInt3048 = -anInt3042 + Class215.anInt1614;
                this.anInt3042 = anInt3042;
                break;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vo.C(" + ((class246_Sub3 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final void method3894(final byte b, final ha ha) {
        try {
            if (this.aR3051 != null) {
                Class268.method3254(this.aR3051, this.aByte3040, this.aClass246_Sub3_3049.anInt5084, this.aClass246_Sub3_3049.anInt5079, this.aBooleanArray3036);
                this.aR3051 = null;
                this.aBooleanArray3036 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vo.G(" + b + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method3895(final Class146 class146, final int n, final int n2, final Class111 class147, final boolean b, final int n3, final ha ha, final boolean b2, final int n4) {
        try {
            final Class87[] method2320 = class146.method2320();
            final Class35[] method2321 = class146.method2322();
            if ((this.aClass246_Sub5_3062 == null || this.aClass246_Sub5_3062.aBoolean5099) && (method2320 != null || method2321 != null)) {
                Class352 class148 = Class130.aClass302_1028.method3546(this.anInt3052, (byte)119);
                if (class148.anIntArray2928 != null) {
                    class148 = class148.method3852(Class75.aClass140_584, (byte)(-116));
                }
                if (class148 != null) {
                    this.aClass246_Sub5_3062 = Class246_Sub5.method3117(Class215.anInt1614, true);
                }
            }
            if (b2) {
                this.aByte3040 = -118;
            }
            if (this.aClass246_Sub5_3062 != null) {
                class146.method2343(class147);
                Label_0159: {
                    if (!b) {
                        this.aClass246_Sub5_3062.method3126(Class215.anInt1614);
                        if (!client.aBoolean3553) {
                            break Label_0159;
                        }
                    }
                    this.aClass246_Sub5_3062.method3120(ha, Class215.anInt1614, method2320, method2321, false);
                }
                this.aClass246_Sub5_3062.method3123(this.aByte3039, n3, n, n2, n4);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vo.D(" + ((class146 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + ((class147 != null) ? "{...}" : "null") + ',' + b + ',' + n3 + ',' + ((ha != null) ? "{...}" : "null") + ',' + b2 + ',' + n4 + ')');
        }
    }
    
    public static void method3896(final int n) {
        try {
            if (n == 11) {
                Class359.aClass246_Sub3Array3056 = null;
                Class359.aClass348_3046 = null;
                Class359.anIntArray3060 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vo.K(" + n + ')');
        }
    }
    
    final Class146 method3897(final int n, final boolean b, int n2, final ha ha, boolean b2) {
        try {
            if (n != -1) {
                return null;
            }
            Class352 class352 = Class130.aClass302_1028.method3546(this.anInt3052, (byte)119);
            if (class352.anIntArray2928 != null) {
                class352 = class352.method3852(Class75.aClass140_584, (byte)(-118));
            }
            if (class352 == null) {
                this.method3894((byte)(-80), ha);
                this.anInt3055 = -1;
                this.anInt3061 = -1;
                this.anInt3043 = -1;
                return null;
            }
            if (!this.aBoolean3047 && class352.id != this.anInt3061) {
                this.aClass146_3057 = null;
                this.method3902(-1, (byte)101);
            }
            this.method3893(this.aClass246_Sub3_3049, -23137);
            if (b2) {
                b2 &= (this.aBoolean3037 & Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub7_4073.method579((byte)120) != 0);
                b2 &= (class352.id != this.anInt3043 || (this.aClass97_3045 != null && Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub7_4073.method579((byte)121) >= 2 && (~this.anInt3050 != ~this.anInt3055 || ((this.aClass97_3045.aBoolean823 || Class357.aBoolean3027) && this.anInt3035 != this.anInt3050))));
            }
            if (b && !b2) {
                this.anInt3061 = class352.id;
                return null;
            }
            if (b2) {
                Class268.method3254(this.aR3051, this.aByte3040, this.aClass246_Sub3_3049.anInt5084, this.aClass246_Sub3_3049.anInt5079, this.aBooleanArray3036);
                this.anInt3043 = -1;
                this.anInt3055 = -1;
            }
            final s s = Class78.aSArray594[this.aByte3040];
            s s2;
            if (this.aBoolean3054) {
                s2 = Class98_Sub46_Sub2_Sub2.aSArray6298[0];
            }
            else {
                s2 = ((this.aByte3040 < 3) ? Class78.aSArray594[this.aByte3040 + 1] : null);
            }
            Class146 class353 = null;
            if (this.aClass97_3045 == null) {
                if (this.aClass146_3057 != null && (n2 & this.aClass146_3057.ua()) == n2 && this.anInt3061 == class352.id) {
                    class353 = this.aClass146_3057;
                }
                else {
                    if (this.aClass146_3057 != null) {
                        n2 |= this.aClass146_3057.ua();
                    }
                    final Class298 method3851 = class352.method3851(this.aClass246_Sub3_3049.anInt5079, false, s2, (this.anInt3038 != 11) ? this.anInt3059 : (4 - -this.anInt3059), s.method3417(this.aClass246_Sub3_3049.anInt5084, this.aClass246_Sub3_3049.anInt5079, true), b2, this.aClass246_Sub3_3049.anInt5084, n2, this.aClass185_3044, s, ha, (~this.anInt3038 == 0xFFFFFFF4) ? 10 : this.anInt3038);
                    if (method3851 == null) {
                        this.anInt3053 = 0;
                        this.aR3051 = null;
                        this.anInt3041 = 0;
                        this.aClass146_3057 = null;
                        this.aBooleanArray3036 = null;
                    }
                    else {
                        class353 = (this.aClass146_3057 = method3851.aClass146_2477);
                        if (b2) {
                            this.aR3051 = method3851.aR2479;
                            this.aBooleanArray3036 = null;
                            Class184.method2626(this.aR3051, this.aByte3040, this.aClass246_Sub3_3049.anInt5084, this.aClass246_Sub3_3049.anInt5079, null);
                            this.anInt3055 = -1;
                            this.anInt3043 = class352.id;
                        }
                        this.anInt3041 = class353.fa();
                        this.anInt3053 = class353.ma();
                    }
                }
            }
            else {
                if (b2) {
                    n2 |= 0x40000;
                }
                class353 = class352.method3859(s, this.aClass246_Sub3_3049.anInt5084, s.method3417(this.aClass246_Sub3_3049.anInt5084, this.aClass246_Sub3_3049.anInt5079, true), (byte)(-125), ha, s2, n2, (~this.anInt3038 == 0xFFFFFFF4) ? (this.anInt3059 + 4) : this.anInt3059, this.aClass246_Sub3_3049.anInt5079, (this.anInt3038 == 11) ? 10 : this.anInt3038, this.aClass185_3044, this.anInt3050, this.aClass97_3045, this.anInt3035, this.anInt3042);
                if (class353 == null) {
                    this.anInt3053 = 0;
                    this.anInt3041 = 0;
                    this.aR3051 = null;
                    this.aBooleanArray3036 = null;
                }
                else {
                    if (b2) {
                        if (this.aBooleanArray3036 == null) {
                            this.aBooleanArray3036 = new boolean[4];
                        }
                        Class184.method2626(this.aR3051 = class353.ba(this.aR3051), this.aByte3040, this.aClass246_Sub3_3049.anInt5084, this.aClass246_Sub3_3049.anInt5079, this.aBooleanArray3036);
                        this.anInt3043 = class352.id;
                        this.anInt3055 = this.anInt3050;
                    }
                    this.anInt3041 = class353.fa();
                    this.anInt3053 = class353.ma();
                }
                this.aClass146_3057 = null;
            }
            this.anInt3061 = class352.id;
            return class353;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vo.B(" + n + ',' + b + ',' + n2 + ',' + ((ha != null) ? "{...}" : "null") + ',' + b2 + ')');
        }
    }
    
    final boolean method3898(final int n) {
        try {
            if (n < 17) {
                this.anInt3038 = -60;
            }
            return this.aBoolean3037;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vo.A(" + n + ')');
        }
    }
    
    final int method3899(final byte b) {
        try {
            if (b < 124) {
                return 32;
            }
            return this.anInt3041;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vo.L(" + b + ')');
        }
    }
    
    final void method3900(final int n, final int n2) {
        try {
            this.aBoolean3047 = true;
            if (n2 >= -41) {
                this.aClass246_Sub5_3062 = null;
            }
            this.method3902(n, (byte)101);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vo.F(" + n + ',' + n2 + ')');
        }
    }
    
    final void method3901(final Class185 aClass185_3044, final int n) {
        try {
            this.aClass185_3044 = aClass185_3044;
            if (n < -106) {
                this.aClass146_3057 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vo.I(" + ((aClass185_3044 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    private final void method3902(final int n, final byte b) {
        try {
            int n2 = n;
            boolean b2 = false;
            if (n2 == -1) {
                Class352 method3853;
                Class352 method3852 = method3853 = Class130.aClass302_1028.method3546(this.anInt3052, (byte)119);
                if (method3852.anIntArray2928 != null) {
                    method3852 = method3852.method3852(Class75.aClass140_584, (byte)(-44));
                }
                if (method3852 == null) {
                    return;
                }
                if (method3853 == method3852) {
                    method3853 = null;
                }
                if (method3852.anIntArray2979 == null) {
                    if (method3852.anInt2941 == -1) {
                        if (method3853 != null && method3853.anIntArray2979 != null) {
                            if (this.aClass97_3045 != null && method3853.method3860(this.aClass97_3045.anInt826, true)) {
                                return;
                            }
                            n2 = method3853.method3861(0);
                            if (this.anInt3061 != method3853.id) {
                                b2 = method3853.aBoolean2925;
                            }
                        }
                        else if (method3853 != null && ~method3853.anInt2941 != 0x0 && this.anInt3061 != method3853.id) {
                            b2 = method3853.aBoolean2925;
                            n2 = method3853.anInt2941;
                        }
                    }
                    else if (~method3852.id != ~this.anInt3061) {
                        b2 = method3852.aBoolean2925;
                        n2 = method3852.anInt2941;
                    }
                }
                else {
                    if (this.aClass97_3045 != null && method3852.method3860(this.aClass97_3045.anInt826, true)) {
                        return;
                    }
                    n2 = method3852.method3861(0);
                    if (method3852.id != this.anInt3061) {
                        b2 = method3852.aBoolean2925;
                    }
                }
            }
            if (n2 == -1) {
                this.aClass97_3045 = null;
            }
            else {
                this.aClass146_3057 = null;
                if (this.aClass97_3045 != null && this.aClass97_3045.anInt826 == n2) {
                    if (~this.aClass97_3045.anInt819 == -1) {
                        return;
                    }
                }
                else {
                    this.aClass97_3045 = Class151_Sub7.aClass183_5001.method2623(n2, 16383);
                }
                if (this.aClass97_3045.anIntArray818 == null) {
                    this.aClass97_3045 = null;
                }
                else {
                    if (!b2) {
                        this.anInt3042 = 1;
                        this.anInt3050 = 0;
                    }
                    else {
                        this.anInt3050 = (int)(Math.random() * this.aClass97_3045.anIntArray818.length);
                        this.anInt3042 = (int)(this.aClass97_3045.anIntArray811[this.anInt3050] * Math.random()) + 1;
                    }
                    if (b == 101) {
                        this.anInt3035 = 1 + this.anInt3050;
                        if (this.anInt3035 < 0 || ~this.aClass97_3045.anIntArray818.length >= ~this.anInt3035) {
                            this.anInt3035 = -1;
                        }
                        this.anInt3048 = Class215.anInt1614 + -this.anInt3042;
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vo.J(" + n + ',' + b + ')');
        }
    }
    
    final int method3903(final byte b) {
        try {
            return this.anInt3053;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vo.E(" + b + ')');
        }
    }
    
    Class359(final ha ha, final Class352 class352, final int anInt3038, final int anInt3039, final int n, final int n2, final Class246_Sub3 aClass246_Sub3_3049, final boolean aBoolean3054, final int n3) {
        this.anInt3043 = -1;
        this.aBoolean3047 = false;
        this.anInt3041 = 0;
        this.anInt3055 = -1;
        this.anInt3053 = 0;
        this.anInt3061 = -1;
        this.aBoolean3054 = false;
        try {
            this.aByte3039 = (byte)n;
            this.anInt3038 = anInt3038;
            this.anInt3059 = anInt3039;
            this.aByte3040 = (byte)n2;
            this.anInt3052 = class352.id;
            this.aBoolean3054 = aBoolean3054;
            this.aClass246_Sub3_3049 = aClass246_Sub3_3049;
            this.aBoolean3037 = (ha.method1771() && class352.aBoolean2935 && !this.aBoolean3054);
            if (n3 != -1) {
                this.aBoolean3047 = true;
            }
            this.method3902(n3, (byte)101);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vo.<init>(" + ((ha != null) ? "{...}" : "null") + ',' + ((class352 != null) ? "{...}" : "null") + ',' + anInt3038 + ',' + anInt3039 + ',' + n + ',' + n2 + ',' + ((aClass246_Sub3_3049 != null) ? "{...}" : "null") + ',' + aBoolean3054 + ',' + n3 + ')');
        }
    }
    
    static {
        Class359.aClass348_3046 = new Class348(10, 2, 2, 0);
        Class359.anInt3058 = 0;
        Class359.anIntArray3060 = new int[4096];
    }
}
