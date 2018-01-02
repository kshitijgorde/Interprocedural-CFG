// 
// Decompiled by Procyon v0.5.30
// 

final class Class246_Sub3_Sub2_Sub1 extends Class246_Sub3_Sub2
{
    static IncomingOpcode aClass58_6335;
    private boolean aBoolean6336;
    int anInt6337;
    int anInt6338;
    int anInt6339;
    int anInt6340;
    int anInt6341;
    static OutgoingOpcode aClass171_6342;
    int anInt6343;
    private int anInt6344;
    static int anInt6345;
    int anInt6346;
    
    @Override
    final boolean method2978(final int n) {
        try {
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iha.H(" + n + ')');
        }
    }
    
    static final Class31 method3006(final String aString299, final int n, final int anInt302) {
        try {
            Class31 class31;
            try {
                class31 = (Class31)Class.forName("Class31_Sub2").newInstance();
            }
            catch (Throwable t) {
                class31 = new Class31_Sub1();
            }
            class31.anInt302 = anInt302;
            if (n != 0) {
                return null;
            }
            class31.aString299 = aString299;
            return class31;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iha.B(" + ((aString299 != null) ? "{...}" : "null") + ',' + n + ',' + anInt302 + ')');
        }
    }
    
    static final boolean method3007(final int n, final int n2, final byte b) {
        try {
            return b == -15 && ((~(0x40000 & n) != -1 | Class64_Sub10.method594(n, 6, n2)) || Class228.method2864(55, n, n2));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iha.C(" + n + ',' + n2 + ',' + b + ')');
        }
    }
    
    @Override
    final boolean method2987(final int n) {
        try {
            return n != 6540 || this.aBoolean6336;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iha.I(" + n + ')');
        }
    }
    
    @Override
    final Class246_Sub1 method2975(final ha ha, final int n) {
        try {
            final Class154 method914 = Class94.method914(super.aByte5088, super.anInt5084 >> Class151_Sub8.anInt5015, super.anInt5079 >> Class151_Sub8.anInt5015);
            if (method914 != null && method914.aClass246_Sub3_Sub4_1232.aBoolean6162) {
                final int method915 = method914.aClass246_Sub3_Sub4_1232.method2990(0);
                if (this.anInt6339 != method915) {
                    super.anInt5089 -= this.anInt6339;
                    super.anInt5089 += method915;
                    this.anInt6339 = method915;
                }
            }
            final Class111 method916 = ha.method1793();
            method916.method2091();
            if (n > -12) {
                return null;
            }
            if (method914 == null || !method914.aClass246_Sub3_Sub4_1232.aBoolean6162) {
                final s s = Class78.aSArray594[super.aByte5081];
                final int n3;
                final int n2 = n3 = this.anInt6344 << -1155039327;
                final int method917 = s.method3417(super.anInt5084 - -(-n2 / 2), super.anInt5079 - -(-n3 / 2), true);
                final int method918 = s.method3417(super.anInt5084 + n2 / 2, super.anInt5079 - -(-n3 / 2), true);
                final int method919 = s.method3417(super.anInt5084 - -(-n2 / 2), super.anInt5079 - -(n3 / 2), true);
                final int method920 = s.method3417(n2 / 2 + super.anInt5084, n3 / 2 + super.anInt5079, true);
                final int n4 = (method918 <= method917) ? method918 : method917;
                final int n5 = (~method920 >= ~method919) ? method920 : method919;
                final int n6 = (~method918 > ~method920) ? method918 : method920;
                final int n7 = (~method917 > ~method919) ? method917 : method919;
                if (n3 != 0) {
                    final int n8 = 0x3FFF & (int)(2607.5945876176133 * Math.atan2(n4 - n5, n3));
                    if (~n8 != -1) {
                        method916.method2105(n8);
                    }
                }
                if (~n2 != -1) {
                    final int n9 = 0x3FFF & (int)(2607.5945876176133 * Math.atan2(-n6 + n7, n2));
                    if (n9 != 0) {
                        method916.method2090(-n9);
                    }
                }
                int n10 = method920 + method917;
                if (~n10 < ~(method918 + method919)) {
                    n10 = method919 + method918;
                }
                final int n11 = (n10 >> 147921889) - super.anInt5089;
                if (~n11 != -1) {
                    method916.method2106(0, n11, 0);
                }
            }
            method916.method2106(super.anInt5084, -10 + super.anInt5089, super.anInt5079);
            final Class246_Sub1 method921 = Class94.method915(3, (byte)(-47), true);
            this.aBoolean6336 = false;
            this.anInt6344 = 0;
            if (~this.anInt6343 != 0x0) {
                final Class146 method922 = Class98_Sub46_Sub19.aClass205_6068.method2714(this.anInt6343, (byte)(-118)).method3501(0, 2048, 0, null, -1, ha, this.anInt6337, 128, null);
                if (method922 != null) {
                    if (!Class239.aBoolean1839) {
                        method922.method2325(method916, method921.aClass246_Sub6Array5067[2], 0);
                    }
                    else {
                        method922.method2329(method916, method921.aClass246_Sub6Array5067[2], Class16.anInt197, 0);
                    }
                    this.aBoolean6336 |= method922.F();
                    this.anInt6344 = method922.ma();
                }
            }
            if (this.anInt6341 != -1) {
                final Class146 method923 = Class98_Sub46_Sub19.aClass205_6068.method2714(this.anInt6341, (byte)(-122)).method3501(0, 2048, 0, null, -1, ha, this.anInt6346, 128, null);
                if (method923 != null) {
                    if (!Class239.aBoolean1839) {
                        method923.method2325(method916, method921.aClass246_Sub6Array5067[1], 0);
                    }
                    else {
                        method923.method2329(method916, method921.aClass246_Sub6Array5067[1], Class16.anInt197, 0);
                    }
                    this.aBoolean6336 |= method923.F();
                    if (method923.ma() > this.anInt6344) {
                        this.anInt6344 = method923.ma();
                    }
                }
            }
            final Class146 method924 = Class98_Sub46_Sub19.aClass205_6068.method2714(this.anInt6340, (byte)(-121)).method3501(0, 2048, 0, null, -1, ha, this.anInt6338, 128, null);
            if (method924 != null) {
                if (Class239.aBoolean1839) {
                    method924.method2329(method916, method921.aClass246_Sub6Array5067[0], Class16.anInt197, 0);
                }
                else {
                    method924.method2325(method916, method921.aClass246_Sub6Array5067[0], 0);
                }
                this.aBoolean6336 |= method924.F();
                if (~method924.ma() < ~this.anInt6344) {
                    this.anInt6344 = method924.ma();
                }
            }
            return method921;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iha.QA(" + ((ha != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    final int method2985(final boolean b) {
        try {
            if (b) {
                Class246_Sub3_Sub2_Sub1.aClass171_6342 = null;
            }
            return this.anInt6344;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iha.O(" + b + ')');
        }
    }
    
    static final void method3008(final byte b) {
        try {
            synchronized (Class299.aClass79_2493) {
                if (b == 60) {
                    Class299.aClass79_2493.method806((byte)(-114));
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iha.E(" + b + ')');
        }
    }
    
    @Override
    final int method2986(final int n) {
        try {
            if (n != -14240) {
                return 114;
            }
            int n2 = Class98_Sub46_Sub19.aClass205_6068.method2714(this.anInt6340, (byte)(-116)).anInt2445;
            if (this.anInt6341 != -1) {
                final Class297 method2714 = Class98_Sub46_Sub19.aClass205_6068.method2714(this.anInt6341, (byte)(-122));
                if (~method2714.anInt2445 < ~n2) {
                    n2 = method2714.anInt2445;
                }
            }
            if (~this.anInt6343 != 0x0) {
                final Class297 method2715 = Class98_Sub46_Sub19.aClass205_6068.method2714(this.anInt6343, (byte)(-118));
                if (~n2 > ~method2715.anInt2445) {
                    n2 = method2715.anInt2445;
                }
            }
            return n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iha.FB(" + n + ')');
        }
    }
    
    @Override
    final boolean method2976(final int n, final ha ha, final byte b, final int n2) {
        try {
            if (b < 59) {
                this.method2990(-84);
            }
            final Class111 method1793 = ha.method1793();
            method1793.method2100(super.anInt5084, super.anInt5089 - 10, super.anInt5079);
            final Class297 method1794 = Class98_Sub46_Sub19.aClass205_6068.method2714(this.anInt6340, (byte)(-118));
            final Class146 method1795 = method1794.method3501(0, 131072, 0, null, -1, ha, this.anInt6338, 128, null);
            Label_0132: {
                if (method1795 != null) {
                    if (Class239.aBoolean1839) {
                        if (!method1795.method2333(n, n2, method1793, true, method1794.anInt2445, Class16.anInt197)) {
                            break Label_0132;
                        }
                    }
                    else if (!method1795.method2339(n, n2, method1793, true, method1794.anInt2445)) {
                        break Label_0132;
                    }
                    return true;
                }
            }
            Label_0234: {
                if (~this.anInt6341 != 0x0) {
                    final Class297 method1796 = Class98_Sub46_Sub19.aClass205_6068.method2714(this.anInt6341, (byte)(-121));
                    final Class146 method1797 = method1796.method3501(0, 131072, 0, null, -1, ha, this.anInt6346, 128, null);
                    if (method1797 != null) {
                        if (Class239.aBoolean1839) {
                            if (!method1797.method2333(n, n2, method1793, true, method1796.anInt2445, Class16.anInt197)) {
                                break Label_0234;
                            }
                        }
                        else if (!method1797.method2339(n, n2, method1793, true, method1796.anInt2445)) {
                            break Label_0234;
                        }
                        return true;
                    }
                }
            }
            if (this.anInt6343 != -1) {
                final Class297 method1798 = Class98_Sub46_Sub19.aClass205_6068.method2714(this.anInt6343, (byte)(-127));
                final Class146 method1799 = method1798.method3501(0, 131072, 0, null, -1, ha, this.anInt6337, 128, null);
                if (method1799 != null) {
                    if (Class239.aBoolean1839) {
                        if (!method1799.method2333(n, n2, method1793, true, method1798.anInt2445, Class16.anInt197)) {
                            return false;
                        }
                    }
                    else if (!method1799.method2339(n, n2, method1793, true, method1798.anInt2445)) {
                        return false;
                    }
                    return true;
                }
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iha.TA(" + n + ',' + ((ha != null) ? "{...}" : "null") + ',' + b + ',' + n2 + ')');
        }
    }
    
    @Override
    final int method2990(final int n) {
        try {
            if (n != 0) {
                this.anInt6343 = 51;
            }
            return -10;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iha.J(" + n + ')');
        }
    }
    
    static final void method3009(final int n, final Class98_Sub46_Sub8 class98_Sub46_Sub8, final int n2, final int n3, final int n4, final int n5, final int n6, final ha ha, final int n7, final int n8, int n9, final int n10) {
        try {
            if (~n6 < ~n10 && n6 < n3 + n10 && n7 > -13 + n4 && n7 < 3 + n4 && class98_Sub46_Sub8.aBoolean5984) {
                n9 = n2;
            }
            int[] array = null;
            if (!Class299_Sub2.method3526(119, class98_Sub46_Sub8.anInt5990)) {
                if (class98_Sub46_Sub8.anInt5988 == -1) {
                    if (!Class36.method340(class98_Sub46_Sub8.anInt5990, (byte)(-87))) {
                        if (Class98_Sub10_Sub21.method1064(class98_Sub46_Sub8.anInt5990, false)) {
                            Class352 class352;
                            if (~class98_Sub46_Sub8.anInt5990 == 0xFFFFFC0E) {
                                class352 = Class130.aClass302_1028.method3546((int)class98_Sub46_Sub8.aLong5987, (byte)119);
                            }
                            else {
                                class352 = Class130.aClass302_1028.method3546((int)(0x7FFFFFFFL & class98_Sub46_Sub8.aLong5987 >>> 615469152), (byte)119);
                            }
                            if (class352.anIntArray2928 != null) {
                                class352 = class352.method3852(Class75.aClass140_584, (byte)(-66));
                            }
                            if (class352 != null) {
                                array = class352.anIntArray2934;
                            }
                        }
                    }
                    else {
                        final Class98_Sub39 class98_Sub39 = (Class98_Sub39)Class260.aClass377_3254.method3990((int)class98_Sub46_Sub8.aLong5987, -1);
                        if (class98_Sub39 != null) {
                            Class141 class353 = class98_Sub39.aClass246_Sub3_Sub4_Sub2_Sub1_4187.aClass141_6504;
                            if (class353.anIntArray1109 != null) {
                                class353 = class353.method2300(Class75.aClass140_584, (byte)32);
                            }
                            if (class353 != null) {
                                array = class353.anIntArray1152;
                            }
                        }
                    }
                }
                else {
                    array = Class98_Sub46_Sub19.aClass205_6068.method2714(class98_Sub46_Sub8.anInt5988, (byte)(-117)).anIntArray2436;
                }
            }
            else {
                array = Class98_Sub46_Sub19.aClass205_6068.method2714((int)class98_Sub46_Sub8.aLong5987, (byte)(-127)).anIntArray2436;
            }
            String s = Class86.method845((byte)(-124), class98_Sub46_Sub8);
            if (array != null) {
                s += Class64_Sub25.method653(0, array);
            }
            Class98_Sub10_Sub34.aClass43_5730.method413(n4, Class64_Sub5.anIntArray3652, n8, s, n9, 3 + n10, (byte)18, Class217.aClass332Array3408);
            if (class98_Sub46_Sub8.aBoolean5983) {
                Class284_Sub2_Sub2.aClass332_6199.method3735(Class42_Sub1.aClass197_5354.method2674(s, 118) + n10 + 5, -12 + n4);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iha.D(" + n + ',' + ((class98_Sub46_Sub8 != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + ((ha != null) ? "{...}" : "null") + ',' + n7 + ',' + n8 + ',' + n9 + ',' + n10 + ')');
        }
    }
    
    Class246_Sub3_Sub2_Sub1(final int n, final int n2, final int n3, final int n4, final int n5) {
        super(n, n2, n3, n4, n5);
        this.aBoolean6336 = false;
        this.anInt6339 = 0;
        this.anInt6343 = -1;
        this.anInt6344 = 0;
        this.anInt6341 = -1;
    }
    
    @Override
    final Class228 method2974(final byte b, final ha ha) {
        try {
            if (b != -53) {
                this.method2974((byte)(-91), null);
            }
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iha.KA(" + b + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method3010(final byte b) {
        try {
            if (b == -37) {
                Class246_Sub3_Sub2_Sub1.aClass58_6335 = null;
                Class246_Sub3_Sub2_Sub1.aClass171_6342 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iha.A(" + b + ')');
        }
    }
    
    @Override
    final void method2988(final ha ha, final int n) {
    }
    
    static {
        Class246_Sub3_Sub2_Sub1.aClass58_6335 = new IncomingOpcode(65, -1);
        Class246_Sub3_Sub2_Sub1.aClass171_6342 = new OutgoingOpcode(73, 8);
        Class246_Sub3_Sub2_Sub1.anInt6345 = 0;
    }
}
