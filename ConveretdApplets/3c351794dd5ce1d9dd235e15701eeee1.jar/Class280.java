import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class280
{
    static Class164 aClass164_2101;
    int anInt2102;
    int anInt2103;
    private Class207 aClass207_2104;
    static int anInt2105;
    static Rectangle[] aRectangleArray2106;
    private Class79 aClass79_2107;
    static Class148 aClass148_2108;
    private Class207 aClass207_2109;
    private Interface1 anInterface1_2110;
    static Class113 aClass113_2111;
    static long aLong2112;
    
    static final void method3324(final Class98_Sub46_Sub8 class98_Sub46_Sub8, final int n) {
        try {
            if (!Class246_Sub3_Sub4_Sub2_Sub2.aBoolean6540) {
                class98_Sub46_Sub8.method942(87);
                if (n != -32612) {
                    method3329(110);
                }
                --Class359.anInt3058;
                if (!class98_Sub46_Sub8.aBoolean5989) {
                    Class98_Sub46_Sub9 class98_Sub46_Sub9;
                    for (class98_Sub46_Sub9 = (Class98_Sub46_Sub9)Class98_Sub47.aClass377_4274.method3990(class98_Sub46_Sub8.aLong5991, -1); class98_Sub46_Sub9 != null && !class98_Sub46_Sub9.aString5998.equals(class98_Sub46_Sub8.aString5992); class98_Sub46_Sub9 = (Class98_Sub46_Sub9)Class98_Sub47.aClass377_4274.method3993(125)) {}
                    if (class98_Sub46_Sub9 != null && class98_Sub46_Sub9.method1557((byte)(-105), class98_Sub46_Sub8)) {
                        Class9.method189(class98_Sub46_Sub9, (byte)87);
                    }
                }
                else {
                    for (Class98_Sub46_Sub9 class98_Sub46_Sub10 = (Class98_Sub46_Sub9)Class98_Sub18.aClass215_3949.method2792(-1); class98_Sub46_Sub10 != null; class98_Sub46_Sub10 = (Class98_Sub46_Sub9)Class98_Sub18.aClass215_3949.method2787(0)) {
                        if (class98_Sub46_Sub10.aString5998.equals(class98_Sub46_Sub8.aString5992)) {
                            boolean b = false;
                            for (Class98_Sub46_Sub8 class98_Sub46_Sub11 = (Class98_Sub46_Sub8)class98_Sub46_Sub10.aClass215_5999.method2792(-1); class98_Sub46_Sub11 != null; class98_Sub46_Sub11 = (Class98_Sub46_Sub8)class98_Sub46_Sub10.aClass215_5999.method2787(0)) {
                                if (class98_Sub46_Sub11 == class98_Sub46_Sub8) {
                                    if (class98_Sub46_Sub10.method1557((byte)(-125), class98_Sub46_Sub8)) {
                                        Class9.method189(class98_Sub46_Sub10, (byte)87);
                                    }
                                    b = true;
                                    break;
                                }
                            }
                            if (b) {
                                break;
                            }
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rh.E(" + ((class98_Sub46_Sub8 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final Class98_Sub46_Sub11 method3325(final int n, final int n2) {
        try {
            final Class98_Sub46_Sub11 class98_Sub46_Sub11 = (Class98_Sub46_Sub11)this.aClass79_2107.method802(-125, n);
            if (n2 <= 31) {
                this.method3326(null, null, (byte)46, -3L);
            }
            if (class98_Sub46_Sub11 != null) {
                return class98_Sub46_Sub11;
            }
            byte[] array = null;
            Label_0084: {
                if (~n <= -32769) {
                    array = this.aClass207_2104.method2745(0x7FFF & n, 1, false);
                    if (!client.aBoolean3553) {
                        break Label_0084;
                    }
                }
                array = this.aClass207_2109.method2745(n, 1, false);
            }
            final Class98_Sub46_Sub11 class98_Sub46_Sub12 = new Class98_Sub46_Sub11();
            class98_Sub46_Sub12.aClass280_6028 = this;
            if (array != null) {
                class98_Sub46_Sub12.method1584((byte)(-89), new Class98_Sub22(array));
            }
            if (~n <= -32769) {
                class98_Sub46_Sub12.method1575((byte)(-123));
            }
            this.aClass79_2107.method805(n, class98_Sub46_Sub12, (byte)(-80));
            return class98_Sub46_Sub12;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rh.G(" + n + ',' + n2 + ')');
        }
    }
    
    final String method3326(final Class348 class348, final int[] array, final byte b, final long n) {
        try {
            if (this.anInterface1_2110 != null) {
                final String method1 = this.anInterface1_2110.method1(17438, n, class348, array);
                if (method1 != null) {
                    return method1;
                }
            }
            return Long.toString(n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rh.A(" + ((class348 != null) ? "{...}" : "null") + ',' + ((array != null) ? "{...}" : "null") + ',' + b + ',' + n + ')');
        }
    }
    
    static final void method3327(final int n, final Class97 class97, final byte b) {
        try {
            if (~Class306.anInt2566 > -51 && class97 != null && class97.anIntArrayArray822 != null && ~class97.anIntArrayArray822.length < ~n && class97.anIntArrayArray822[n] != null) {
                final int n2 = class97.anIntArrayArray822[n][0];
                int n3 = n2 >> -103665464;
                if (class97.anIntArrayArray822[n].length > 1) {
                    final int n4 = (int)(Math.random() * class97.anIntArrayArray822[n].length);
                    if (n4 > 0) {
                        n3 = class97.anIntArrayArray822[n][n4];
                    }
                }
                final int n5 = (0xF6 & n2) >> 264525829;
                int method2307 = 256;
                if (class97.anIntArray810 != null && class97.anIntArray815 != null) {
                    method2307 = Class142.method2307(class97.anIntArray810[n], class97.anIntArray815[n], 52);
                }
                if (!class97.aBoolean812) {
                    Class301.method3537(method2307, (byte)1, n3, n5, 0, 255);
                }
                else {
                    Class98_Sub10_Sub9.method1036(-1962608884, 255, n5, n3, false, 0, method2307);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rh.D(" + n + ',' + ((class97 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static final void method3328(final int n, final int n2, final int n3, final int n4, final int n5, final Class98_Sub42 class98_Sub42) {
        try {
            if (~class98_Sub42.anInt4210 != 0x0 || class98_Sub42.anIntArray4208 != null) {
                int n6 = 0;
                final int n7 = class98_Sub42.anInt4236 * Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub22_4051.method641((byte)124) >> -884604664;
                if (~n4 >= ~class98_Sub42.anInt4224) {
                    if (~n4 > ~class98_Sub42.anInt4229) {
                        n6 += -n4 + class98_Sub42.anInt4229;
                    }
                }
                else {
                    n6 += -class98_Sub42.anInt4224 + n4;
                }
                if (class98_Sub42.anInt4216 < n) {
                    n6 += -class98_Sub42.anInt4216 + n;
                }
                else if (~class98_Sub42.anInt4225 < ~n) {
                    n6 += class98_Sub42.anInt4225 + -n;
                }
                if (class98_Sub42.anInt4228 == 0 || n6 - 256 > class98_Sub42.anInt4228 || ~Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub22_4051.method641((byte)120) == -1 || ~class98_Sub42.anInt4220 != ~n3) {
                    if (class98_Sub42.aClass98_Sub31_Sub5_4232 != null) {
                        Class81.aClass98_Sub31_Sub3_619.method1374(class98_Sub42.aClass98_Sub31_Sub5_4232);
                        class98_Sub42.aClass98_Sub24_Sub1_4214 = null;
                        class98_Sub42.aClass98_Sub31_Sub5_4232 = null;
                        class98_Sub42.aClass98_Sub13_4213 = null;
                    }
                    if (class98_Sub42.aClass98_Sub31_Sub5_4230 != null) {
                        Class81.aClass98_Sub31_Sub3_619.method1374(class98_Sub42.aClass98_Sub31_Sub5_4230);
                        class98_Sub42.aClass98_Sub24_Sub1_4211 = null;
                        class98_Sub42.aClass98_Sub13_4231 = null;
                        class98_Sub42.aClass98_Sub31_Sub5_4230 = null;
                    }
                }
                else {
                    int n8 = n6 - n2;
                    if (n8 < 0) {
                        n8 = 0;
                    }
                    int anInt4228 = -class98_Sub42.anInt4217 + class98_Sub42.anInt4228;
                    if (~anInt4228 > -1) {
                        anInt4228 = class98_Sub42.anInt4228;
                    }
                    int n9 = n7;
                    final int n10 = n8 - class98_Sub42.anInt4217;
                    if (~n10 < -1 && ~anInt4228 < -1) {
                        n9 = n7 * (anInt4228 - n10) / anInt4228;
                    }
                    Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.method3034(n2 - 256);
                    int n11 = 8192;
                    final int n12 = (class98_Sub42.anInt4229 + class98_Sub42.anInt4224) / 2 + -n4;
                    final int n13 = (class98_Sub42.anInt4225 + class98_Sub42.anInt4216) / 2 - n;
                    if (n12 != 0 || ~n13 != -1) {
                        int n14 = -4096 + (-Class186.anInt3424 + -(int)(2607.5945876176133 * Math.atan2(n12, n13))) & 0x3FFF;
                        if (~n14 < -8193) {
                            n14 = 16384 + -n14;
                        }
                        int n15;
                        if (~n8 >= -1) {
                            n15 = 8192;
                        }
                        else if (n8 >= 4096) {
                            n15 = 16384;
                        }
                        else {
                            n15 = 8192 - -(n8 * 8192 / 4096);
                        }
                        n11 = (16384 - n15 >> 461462369) + n15 * n14 / 8192;
                    }
                    if (class98_Sub42.aClass98_Sub31_Sub5_4232 == null) {
                        if (class98_Sub42.anInt4210 >= 0) {
                            final int n16 = (class98_Sub42.anInt4237 != 256 || class98_Sub42.anInt4223 != 256) ? Class142.method2307(class98_Sub42.anInt4223, class98_Sub42.anInt4237, n2 - 347) : 256;
                            if (!class98_Sub42.aBoolean4215) {
                                final Class37 method342 = Class37.method342(Class76_Sub2.aClass207_3733, class98_Sub42.anInt4210, 0);
                                if (method342 != null) {
                                    final Class98_Sub31_Sub5 method343 = Class98_Sub31_Sub5.method1402(method342.method344().method1269(Class148.aClass314_1195), n16, n9 << -44812154, n11);
                                    method343.method1422(-1);
                                    Class81.aClass98_Sub31_Sub3_619.method1371(method343);
                                    class98_Sub42.aClass98_Sub31_Sub5_4232 = method343;
                                }
                            }
                            else {
                                if (class98_Sub42.aClass98_Sub13_4213 == null) {
                                    class98_Sub42.aClass98_Sub13_4213 = Class98_Sub13.method1137(Class196.aClass207_1512, class98_Sub42.anInt4210);
                                }
                                if (class98_Sub42.aClass98_Sub13_4213 != null) {
                                    if (class98_Sub42.aClass98_Sub24_Sub1_4214 == null) {
                                        class98_Sub42.aClass98_Sub24_Sub1_4214 = class98_Sub42.aClass98_Sub13_4213.method1132(new int[] { 22050 });
                                    }
                                    if (class98_Sub42.aClass98_Sub24_Sub1_4214 != null) {
                                        final Class98_Sub31_Sub5 method344 = Class98_Sub31_Sub5.method1402(class98_Sub42.aClass98_Sub24_Sub1_4214, n16, n9 << -669300698, n11);
                                        method344.method1422(-1);
                                        Class81.aClass98_Sub31_Sub3_619.method1371(method344);
                                        class98_Sub42.aClass98_Sub31_Sub5_4232 = method344;
                                    }
                                }
                            }
                        }
                    }
                    else {
                        class98_Sub42.aClass98_Sub31_Sub5_4232.method1427(n9);
                        class98_Sub42.aClass98_Sub31_Sub5_4232.method1431(n11);
                    }
                    if (class98_Sub42.aClass98_Sub31_Sub5_4230 == null) {
                        if (class98_Sub42.anIntArray4208 != null) {
                            final int anInt4229 = class98_Sub42.anInt4221 - n5;
                            class98_Sub42.anInt4221 = anInt4229;
                            if (~anInt4229 >= -1) {
                                final int n17 = (class98_Sub42.anInt4237 != 256 || class98_Sub42.anInt4223 != 256) ? (class98_Sub42.anInt4223 + (int)(Math.random() * (-class98_Sub42.anInt4223 + class98_Sub42.anInt4237))) : 256;
                                if (class98_Sub42.aBoolean4226) {
                                    if (class98_Sub42.aClass98_Sub13_4231 == null) {
                                        class98_Sub42.aClass98_Sub13_4231 = Class98_Sub13.method1137(Class196.aClass207_1512, class98_Sub42.anIntArray4208[(int)(Math.random() * class98_Sub42.anIntArray4208.length)]);
                                    }
                                    if (class98_Sub42.aClass98_Sub13_4231 != null) {
                                        if (class98_Sub42.aClass98_Sub24_Sub1_4211 == null) {
                                            class98_Sub42.aClass98_Sub24_Sub1_4211 = class98_Sub42.aClass98_Sub13_4231.method1132(new int[] { 22050 });
                                        }
                                        if (class98_Sub42.aClass98_Sub24_Sub1_4211 != null) {
                                            final Class98_Sub31_Sub5 method345 = Class98_Sub31_Sub5.method1402(class98_Sub42.aClass98_Sub24_Sub1_4211, n17, n9 << -1521283386, n11);
                                            method345.method1422(0);
                                            Class81.aClass98_Sub31_Sub3_619.method1371(method345);
                                            class98_Sub42.aClass98_Sub31_Sub5_4230 = method345;
                                            class98_Sub42.anInt4221 = (int)(Math.random() * (-class98_Sub42.anInt4219 + class98_Sub42.anInt4205)) + class98_Sub42.anInt4219;
                                        }
                                    }
                                }
                                else {
                                    final Class37 method346 = Class37.method342(Class76_Sub2.aClass207_3733, class98_Sub42.anIntArray4208[(int)(Math.random() * class98_Sub42.anIntArray4208.length)], 0);
                                    if (method346 != null) {
                                        final Class98_Sub31_Sub5 method347 = Class98_Sub31_Sub5.method1402(method346.method344().method1269(Class148.aClass314_1195), n17, n9 << 1722792038, n11);
                                        method347.method1422(0);
                                        Class81.aClass98_Sub31_Sub3_619.method1371(method347);
                                        class98_Sub42.anInt4221 = (int)((-class98_Sub42.anInt4219 + class98_Sub42.anInt4205) * Math.random()) + class98_Sub42.anInt4219;
                                        class98_Sub42.aClass98_Sub31_Sub5_4230 = method347;
                                    }
                                }
                            }
                        }
                    }
                    else {
                        class98_Sub42.aClass98_Sub31_Sub5_4230.method1427(n9);
                        class98_Sub42.aClass98_Sub31_Sub5_4230.method1431(n11);
                        if (!class98_Sub42.aClass98_Sub31_Sub5_4230.method941((byte)78)) {
                            class98_Sub42.aClass98_Sub31_Sub5_4230 = null;
                            class98_Sub42.aClass98_Sub13_4231 = null;
                            class98_Sub42.aClass98_Sub24_Sub1_4211 = null;
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rh.F(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + ((class98_Sub42 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method3329(final int n) {
        try {
            Class280.aClass148_2108 = null;
            Class280.aRectangleArray2106 = null;
            Class280.aClass164_2101 = null;
            if (n > -120) {
                method3327(-15, null, (byte)(-55));
            }
            Class280.aClass113_2111 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rh.C(" + n + ')');
        }
    }
    
    static final void method3330(final int n) {
        try {
            synchronized (Class211.aClass79_1594) {
                if (n != 1) {
                    Class280.aClass148_2108 = null;
                }
                Class211.aClass79_1594.method794(n + 30);
            }
            synchronized (PlayerUpdate.aClass79_3411) {
                PlayerUpdate.aClass79_3411.method794(16);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rh.B(" + n + ')');
        }
    }
    
    Class280(final int n, final Class207 aClass207_2109, final Class207 aClass207_2110, final Interface1 anInterface1_2110) {
        this.anInt2103 = 0;
        this.anInt2102 = 0;
        this.aClass79_2107 = new Class79(64);
        this.anInterface1_2110 = null;
        try {
            this.aClass207_2109 = aClass207_2109;
            this.anInterface1_2110 = anInterface1_2110;
            this.aClass207_2104 = aClass207_2110;
            if (this.aClass207_2109 != null) {
                this.anInt2102 = this.aClass207_2109.method2761(0, 1);
            }
            if (this.aClass207_2104 != null) {
                this.anInt2103 = this.aClass207_2104.method2761(0, 1);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rh.<init>(" + n + ',' + ((aClass207_2109 != null) ? "{...}" : "null") + ',' + ((aClass207_2110 != null) ? "{...}" : "null") + ',' + ((anInterface1_2110 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class280.aClass164_2101 = new Class164(1);
        Class280.aRectangleArray2106 = new Rectangle[100];
        Class280.anInt2105 = 1;
        Class280.aClass148_2108 = new Class148();
        Class280.aLong2112 = 1L;
        Class280.aClass113_2111 = new Class113(5, 1);
    }
}
