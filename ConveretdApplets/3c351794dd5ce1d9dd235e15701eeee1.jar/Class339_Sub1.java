// 
// Decompiled by Procyon v0.5.30
// 

final class Class339_Sub1 extends Class339
{
    private Class98_Sub46_Sub13 aClass98_Sub46_Sub13_5306;
    private Class377 aClass377_5307;
    static Class326 aClass326_5308;
    private int anInt5309;
    private int anInt5310;
    private int anInt5311;
    private Class253 aClass253_5312;
    private Class312 aClass312_5313;
    private Class17 aClass17_5314;
    static Class326 aClass326_5315;
    static float aFloat5316;
    private byte[] aByteArray5317;
    private Class135 aClass135_5318;
    private byte[] aByteArray5319;
    private Class17 aClass17_5320;
    private int anInt5321;
    private Class148 aClass148_5322;
    private int anInt5323;
    private boolean aBoolean5324;
    private Class148 aClass148_5325;
    private boolean aBoolean5326;
    private long aLong5327;
    private boolean aBoolean5328;
    
    final int method3791(final byte b) {
        try {
            if (b > -33) {
                return 42;
            }
            if (this.aClass312_5313 == null) {
                return 0;
            }
            if (!this.aBoolean5324) {
                return this.aClass312_5313.anInt2665;
            }
            final Class98 method2418 = this.aClass148_5322.method2418(32);
            if (method2418 == null) {
                return 0;
            }
            return (int)method2418.aLong832;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lw.R(" + b + ')');
        }
    }
    
    private final Class98_Sub46_Sub13 method3792(final int n, final int n2, final int n3) {
        try {
            Class98_Sub46_Sub13 class98_Sub46_Sub13 = (Class98_Sub46_Sub13)this.aClass377_5307.method3990(n2, n ^ 0x1);
            if (class98_Sub46_Sub13 != null && ~n3 == -1 && !class98_Sub46_Sub13.aBoolean6037 && class98_Sub46_Sub13.aBoolean6038) {
                class98_Sub46_Sub13.method942(71);
                class98_Sub46_Sub13 = null;
            }
            if (class98_Sub46_Sub13 == null) {
                if (~n3 == -1) {
                    if (this.aClass17_5314 != null && this.aByteArray5317[n2] != -1) {
                        class98_Sub46_Sub13 = this.aClass253_5312.method3184(1, this.aClass17_5314, n2);
                    }
                    else {
                        if (this.aClass135_5318.method2253(69)) {
                            return null;
                        }
                        class98_Sub46_Sub13 = this.aClass135_5318.method2252(this.anInt5310, (byte)2, n2, 119, true);
                    }
                }
                else if (n3 != 1) {
                    if (n3 != 2) {
                        throw new RuntimeException();
                    }
                    if (this.aClass17_5314 == null) {
                        throw new RuntimeException();
                    }
                    if (this.aByteArray5317[n2] != -1) {
                        throw new RuntimeException();
                    }
                    if (this.aClass135_5318.method2263(20)) {
                        return null;
                    }
                    class98_Sub46_Sub13 = this.aClass135_5318.method2252(this.anInt5310, (byte)2, n2, 108, false);
                }
                else {
                    if (this.aClass17_5314 == null) {
                        throw new RuntimeException();
                    }
                    class98_Sub46_Sub13 = this.aClass253_5312.method3176((byte)(-38), n2, this.aClass17_5314);
                }
                this.aClass377_5307.method3996(class98_Sub46_Sub13, n2, -1);
            }
            if (class98_Sub46_Sub13.aBoolean6038) {
                return null;
            }
            final byte[] method1591 = class98_Sub46_Sub13.method1591(90);
            if (n != -2) {
                this.method3784((byte)(-125));
            }
            if (class98_Sub46_Sub13 instanceof Class98_Sub46_Sub13_Sub2) {
                try {
                    if (method1591 == null || ~method1591.length >= -3) {
                        throw new RuntimeException();
                    }
                    Class279.aCRC32_2097.reset();
                    Class279.aCRC32_2097.update(method1591, 0, -2 + method1591.length);
                    if (~(int)Class279.aCRC32_2097.getValue() != ~this.aClass312_5313.anIntArray2673[n2]) {
                        throw new RuntimeException();
                    }
                    if (this.aClass312_5313.aByteArrayArray2675 != null && this.aClass312_5313.aByteArrayArray2675[n2] != null) {
                        final byte[] array = this.aClass312_5313.aByteArrayArray2675[n2];
                        final byte[] method1592 = Class64_Sub11.method595(0, (byte)(-122), method1591, -2 + method1591.length);
                        for (int i = 0; i < 64; ++i) {
                            if (method1592[i] != array[i]) {
                                throw new RuntimeException();
                            }
                        }
                    }
                    if (~(((0xFF & method1591[-2 + method1591.length]) << 1865901960) - -(0xFF & method1591[method1591.length - 1])) != ~(0xFFFF & this.aClass312_5313.anIntArray2667[n2])) {
                        throw new RuntimeException();
                    }
                    if (~this.aByteArray5317[n2] != 0xFFFFFFFE) {
                        ++this.anInt5311;
                        this.aByteArray5317[n2] = 1;
                    }
                    if (!class98_Sub46_Sub13.aBoolean6037) {
                        class98_Sub46_Sub13.method942(n + 74);
                    }
                    return class98_Sub46_Sub13;
                }
                catch (Exception ex2) {
                    this.aByteArray5317[n2] = -1;
                    class98_Sub46_Sub13.method942(52);
                    if (class98_Sub46_Sub13.aBoolean6037 && !this.aClass135_5318.method2253(100)) {
                        this.aClass377_5307.method3996(this.aClass135_5318.method2252(this.anInt5310, (byte)2, n2, 122, true), n2, -1);
                    }
                    return null;
                }
            }
            try {
                if (method1591 == null || ~method1591.length >= -3) {
                    throw new RuntimeException();
                }
                Class279.aCRC32_2097.reset();
                Class279.aCRC32_2097.update(method1591, 0, method1591.length - 2);
                if (~this.aClass312_5313.anIntArray2673[n2] != ~(int)Class279.aCRC32_2097.getValue()) {
                    throw new RuntimeException();
                }
                if (this.aClass312_5313.aByteArrayArray2675 != null && this.aClass312_5313.aByteArrayArray2675[n2] != null) {
                    final byte[] array2 = this.aClass312_5313.aByteArrayArray2675[n2];
                    final byte[] method1593 = Class64_Sub11.method595(0, (byte)(-124), method1591, -2 + method1591.length);
                    for (int n4 = 0; ~n4 > -65; ++n4) {
                        if (~array2[n4] != ~method1593[n4]) {
                            throw new RuntimeException();
                        }
                    }
                }
                this.aClass135_5318.anInt1065 = 0;
                this.aClass135_5318.anInt1066 = 0;
            }
            catch (RuntimeException ex3) {
                this.aClass135_5318.method2257(67);
                class98_Sub46_Sub13.method942(76);
                if (class98_Sub46_Sub13.aBoolean6037 && !this.aClass135_5318.method2253(88)) {
                    this.aClass377_5307.method3996(this.aClass135_5318.method2252(this.anInt5310, (byte)2, n2, 127, true), n2, n ^ 0x1);
                }
                return null;
            }
            method1591[method1591.length - 2] = (byte)(this.aClass312_5313.anIntArray2667[n2] >>> 969573768);
            method1591[-1 + method1591.length] = (byte)this.aClass312_5313.anIntArray2667[n2];
            if (this.aClass17_5314 != null) {
                this.aClass253_5312.method3179(method1591, (byte)61, this.aClass17_5314, n2);
                if (this.aByteArray5317[n2] != 1) {
                    ++this.anInt5311;
                    this.aByteArray5317[n2] = 1;
                }
            }
            if (!class98_Sub46_Sub13.aBoolean6037) {
                class98_Sub46_Sub13.method942(102);
            }
            return class98_Sub46_Sub13;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lw.O(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final byte[] method3786(final int n, final int n2) {
        try {
            final Class98_Sub46_Sub13 method3792 = this.method3792(n2 - 2, n, n2);
            if (method3792 == null) {
                return null;
            }
            final byte[] method3793 = method3792.method1591(n2 + 113);
            method3792.method942(52);
            return method3793;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lw.A(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final Class312 method3784(final byte b) {
        try {
            if (this.aClass312_5313 != null) {
                return this.aClass312_5313;
            }
            if (b > -102) {
                this.method3800(-6);
            }
            if (this.aClass98_Sub46_Sub13_5306 == null) {
                if (this.aClass135_5318.method2253(70)) {
                    return null;
                }
                this.aClass98_Sub46_Sub13_5306 = this.aClass135_5318.method2252(255, (byte)0, this.anInt5310, 126, true);
            }
            if (this.aClass98_Sub46_Sub13_5306.aBoolean6038) {
                return null;
            }
            final byte[] method1591 = this.aClass98_Sub46_Sub13_5306.method1591(25);
            if (!(this.aClass98_Sub46_Sub13_5306 instanceof Class98_Sub46_Sub13_Sub2)) {
                try {
                    if (method1591 == null) {
                        throw new RuntimeException();
                    }
                    this.aClass312_5313 = new Class312(method1591, this.anInt5309, this.aByteArray5319);
                }
                catch (RuntimeException ex2) {
                    this.aClass135_5318.method2257(89);
                    this.aClass312_5313 = null;
                    if (this.aClass135_5318.method2253(92)) {
                        this.aClass98_Sub46_Sub13_5306 = null;
                    }
                    else {
                        this.aClass98_Sub46_Sub13_5306 = this.aClass135_5318.method2252(255, (byte)0, this.anInt5310, 111, true);
                    }
                    return null;
                }
                if (this.aClass17_5320 != null) {
                    this.aClass253_5312.method3179(method1591, (byte)21, this.aClass17_5320, this.anInt5310);
                }
            }
            else {
                try {
                    if (method1591 == null) {
                        throw new RuntimeException();
                    }
                    this.aClass312_5313 = new Class312(method1591, this.anInt5309, this.aByteArray5319);
                    if (this.anInt5321 != this.aClass312_5313.anInt2676) {
                        throw new RuntimeException();
                    }
                }
                catch (RuntimeException ex3) {
                    this.aClass312_5313 = null;
                    if (this.aClass135_5318.method2253(51)) {
                        this.aClass98_Sub46_Sub13_5306 = null;
                    }
                    else {
                        this.aClass98_Sub46_Sub13_5306 = this.aClass135_5318.method2252(255, (byte)0, this.anInt5310, 117, true);
                    }
                    return null;
                }
            }
            this.aClass98_Sub46_Sub13_5306 = null;
            if (this.aClass17_5314 != null) {
                this.aByteArray5317 = new byte[this.aClass312_5313.anInt2668];
                this.anInt5311 = 0;
            }
            return this.aClass312_5313;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lw.H(" + b + ')');
        }
    }
    
    @Override
    final int method3790(final int n, final byte b) {
        try {
            if (b >= -10) {
                Class339_Sub1.aClass326_5315 = null;
            }
            final Class98_Sub46_Sub13 class98_Sub46_Sub13 = (Class98_Sub46_Sub13)this.aClass377_5307.method3990(n, -1);
            if (class98_Sub46_Sub13 != null) {
                return class98_Sub46_Sub13.method1590(100);
            }
            return 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lw.B(" + n + ',' + b + ')');
        }
    }
    
    final void method3793(final int n) {
        try {
            if (this.aClass17_5314 != null) {
                this.aBoolean5326 = true;
                if (this.aClass148_5322 == null) {
                    this.aClass148_5322 = new Class148();
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lw.L(" + n + ')');
        }
    }
    
    public static void method3794(final byte b) {
        try {
            if (b == -101) {
                Class339_Sub1.aClass326_5308 = null;
                Class339_Sub1.aClass326_5315 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lw.J(" + b + ')');
        }
    }
    
    final int method3795(final int n) {
        try {
            if (n > -24) {
                this.method3792(104, -12, -110);
            }
            if (this.method3784((byte)(-116)) != null) {
                return 100;
            }
            if (this.aClass98_Sub46_Sub13_5306 == null) {
                return 0;
            }
            return this.aClass98_Sub46_Sub13_5306.method1590(100);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lw.Q(" + n + ')');
        }
    }
    
    @Override
    final void method3785(final int n, final boolean b) {
        try {
            if (this.aClass17_5314 != null) {
                if (b) {
                    this.method3793(51);
                }
                for (Class98 class98 = this.aClass148_5325.method2418(32); class98 != null; class98 = this.aClass148_5325.method2417(97)) {
                    if (n == class98.aLong832) {
                        return;
                    }
                }
                final Class98 class99 = new Class98();
                class99.aLong832 = n;
                this.aClass148_5325.method2419(class99, -20911);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lw.D(" + n + ',' + b + ')');
        }
    }
    
    final void method3796(final int n) {
        try {
            if (n == 7899) {
                if (this.aClass148_5322 != null) {
                    if (this.method3784((byte)(-128)) == null) {
                        return;
                    }
                    if (this.aBoolean5324) {
                        boolean b = true;
                        for (Class98 class98 = this.aClass148_5322.method2418(32); class98 != null; class98 = this.aClass148_5322.method2417(123)) {
                            final int n2 = (int)class98.aLong832;
                            if (~this.aByteArray5317[n2] == -1) {
                                this.method3792(-2, n2, 1);
                            }
                            if (~this.aByteArray5317[n2] != -1) {
                                class98.method942(90);
                            }
                            else {
                                b = false;
                            }
                        }
                        while (~this.aClass312_5313.anIntArray2670.length < ~this.anInt5323) {
                            if (~this.aClass312_5313.anIntArray2670[this.anInt5323] == -1) {
                                ++this.anInt5323;
                            }
                            else {
                                if (~this.aClass253_5312.anInt1935 <= -251) {
                                    b = false;
                                    break;
                                }
                                if (~this.aByteArray5317[this.anInt5323] == -1) {
                                    this.method3792(-2, this.anInt5323, 1);
                                }
                                if (~this.aByteArray5317[this.anInt5323] == -1) {
                                    final Class98 class99 = new Class98();
                                    class99.aLong832 = this.anInt5323;
                                    b = false;
                                    this.aClass148_5322.method2419(class99, -20911);
                                }
                                ++this.anInt5323;
                            }
                        }
                        if (b) {
                            this.anInt5323 = 0;
                            this.aBoolean5324 = false;
                        }
                    }
                    else if (!this.aBoolean5326) {
                        this.aClass148_5322 = null;
                    }
                    else {
                        boolean b2 = true;
                        for (Class98 class100 = this.aClass148_5322.method2418(32); class100 != null; class100 = this.aClass148_5322.method2417(112)) {
                            final int n3 = (int)class100.aLong832;
                            if (~this.aByteArray5317[n3] != 0xFFFFFFFE) {
                                this.method3792(-2, n3, 2);
                            }
                            if (this.aByteArray5317[n3] == 1) {
                                class100.method942(n ^ 0x1E9F);
                            }
                            else {
                                b2 = false;
                            }
                        }
                        while (this.anInt5323 < this.aClass312_5313.anIntArray2670.length) {
                            if (~this.aClass312_5313.anIntArray2670[this.anInt5323] == -1) {
                                ++this.anInt5323;
                            }
                            else {
                                if (this.aClass135_5318.method2263(n ^ 0x1ECF)) {
                                    b2 = false;
                                    break;
                                }
                                if (this.aByteArray5317[this.anInt5323] != 1) {
                                    this.method3792(-2, this.anInt5323, 2);
                                }
                                if (this.aByteArray5317[this.anInt5323] != 1) {
                                    final Class98 class101 = new Class98();
                                    class101.aLong832 = this.anInt5323;
                                    this.aClass148_5322.method2419(class101, -20911);
                                    b2 = false;
                                }
                                ++this.anInt5323;
                            }
                        }
                        if (b2) {
                            this.anInt5323 = 0;
                            this.aBoolean5326 = false;
                        }
                    }
                }
                if (this.aBoolean5328 && ~this.aLong5327 >= ~Class343.method3819(-47)) {
                    for (Class98_Sub46_Sub13 class98_Sub46_Sub13 = (Class98_Sub46_Sub13)this.aClass377_5307.method3998(122); class98_Sub46_Sub13 != null; class98_Sub46_Sub13 = (Class98_Sub46_Sub13)this.aClass377_5307.method3995(-1)) {
                        if (!class98_Sub46_Sub13.aBoolean6038) {
                            if (!class98_Sub46_Sub13.aBoolean6036) {
                                class98_Sub46_Sub13.aBoolean6036 = true;
                            }
                            else {
                                if (!class98_Sub46_Sub13.aBoolean6037) {
                                    throw new RuntimeException();
                                }
                                class98_Sub46_Sub13.method942(51);
                            }
                        }
                    }
                    this.aLong5327 = Class343.method3819(-47) + 1000L;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lw.N(" + n + ')');
        }
    }
    
    final void method3797(final int n) {
        try {
            if (this.aClass148_5322 != null && this.method3784((byte)(-110)) != null) {
                if (n != -1) {
                    this.method3790(29, (byte)(-69));
                }
                for (Class98 class98 = this.aClass148_5325.method2418(n + 33); class98 != null; class98 = this.aClass148_5325.method2417(117)) {
                    final int n2 = (int)class98.aLong832;
                    if (~n2 > -1 || this.aClass312_5313.anInt2668 <= n2 || this.aClass312_5313.anIntArray2670[n2] == 0) {
                        class98.method942(84);
                    }
                    else {
                        if (~this.aByteArray5317[n2] == -1) {
                            this.method3792(n - 1, n2, 1);
                        }
                        if (this.aByteArray5317[n2] == -1) {
                            this.method3792(n ^ 0x1, n2, 2);
                        }
                        if (~this.aByteArray5317[n2] == 0xFFFFFFFE) {
                            class98.method942(113);
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lw.K(" + n + ')');
        }
    }
    
    final int method3798(final int n) {
        try {
            if (n <= 35) {
                return -57;
            }
            return this.anInt5311;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lw.M(" + n + ')');
        }
    }
    
    static final void method3799(final int n) {
        try {
            ++Class39_Sub1.anInt3594;
            final Class98_Sub11 method3023 = Class246_Sub3_Sub4.method3023(n + 260, Class98_Sub23.aClass171_3998, Class331.aClass117_2811);
            method3023.aClass98_Sub22_Sub1_3865.method1194(n, 121);
            Class98_Sub10_Sub29.sendPacket(false, method3023);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lw.I(" + n + ')');
        }
    }
    
    final int method3800(final int n) {
        try {
            if (n != 0) {
                method3794((byte)48);
            }
            if (this.aClass312_5313 == null) {
                return 0;
            }
            return this.aClass312_5313.anInt2665;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lw.P(" + n + ')');
        }
    }
    
    Class339_Sub1(final int anInt5310, final Class17 aClass17_5314, final Class17 aClass17_5315, final Class135 aClass135_5318, final Class253 aClass253_5312, final int anInt5311, final byte[] aByteArray5319, final int anInt5312, final boolean aBoolean5328) {
        this.anInt5311 = 0;
        this.aClass377_5307 = new Class377(16);
        this.anInt5323 = 0;
        this.aClass148_5325 = new Class148();
        this.aLong5327 = 0L;
        try {
            this.aClass17_5314 = aClass17_5314;
            this.anInt5310 = anInt5310;
            Label_0090: {
                if (this.aClass17_5314 != null) {
                    this.aBoolean5324 = true;
                    this.aClass148_5322 = new Class148();
                    if (!client.aBoolean3553) {
                        break Label_0090;
                    }
                }
                this.aBoolean5324 = false;
            }
            this.aClass135_5318 = aClass135_5318;
            this.aClass17_5320 = aClass17_5315;
            this.aBoolean5328 = aBoolean5328;
            this.anInt5309 = anInt5311;
            this.aClass253_5312 = aClass253_5312;
            this.anInt5321 = anInt5312;
            this.aByteArray5319 = aByteArray5319;
            if (this.aClass17_5320 != null) {
                this.aClass98_Sub46_Sub13_5306 = this.aClass253_5312.method3184(1, this.aClass17_5320, this.anInt5310);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lw.<init>(" + anInt5310 + ',' + ((aClass17_5314 != null) ? "{...}" : "null") + ',' + ((aClass17_5315 != null) ? "{...}" : "null") + ',' + ((aClass135_5318 != null) ? "{...}" : "null") + ',' + ((aClass253_5312 != null) ? "{...}" : "null") + ',' + anInt5311 + ',' + ((aByteArray5319 != null) ? "{...}" : "null") + ',' + anInt5312 + ',' + aBoolean5328 + ')');
        }
    }
}
