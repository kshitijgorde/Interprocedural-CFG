// 
// Decompiled by Procyon v0.5.30
// 

final class Class117
{
    private int anInt968;
    private int[] anIntArray969;
    private int anInt970;
    private int anInt971;
    private int anInt972;
    private int[] anIntArray973;
    static int[] anIntArray974;
    
    public static void method2163(final int n) {
        try {
            if (n != -20732) {
                Class117.anIntArray974 = null;
            }
            Class117.anIntArray974 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hj.E(" + n + ')');
        }
    }
    
    static final void method2164(final ha aHa5709, final int anInt2407, final int anInt2408, final int anInt2409, final int anInt2410, final int anInt2411, final int anInt2412, final boolean b, final boolean b2) {
        Class98_Sub10_Sub30.aHa5709 = aHa5709;
        Class294.anInt2407 = anInt2407;
        Class375.aBoolean3170 = (Class294.anInt2407 > 1 && Class98_Sub10_Sub30.aHa5709.method1810());
        Class151_Sub8.anInt5015 = anInt2408;
        r_Sub2.anInt6333 = 1 << Class151_Sub8.anInt5015;
        Class207.anInt1577 = r_Sub2.anInt6333 >> 1;
        Math.sqrt(Class207.anInt1577 * Class207.anInt1577 + Class207.anInt1577 * Class207.anInt1577);
        Class364.anInt3103 = anInt2409;
        Class366.anInt3112 = anInt2410;
        Class64_Sub9.anInt3662 = anInt2411;
        Class259.anInt1959 = anInt2412;
        Class376.aClass142_3174 = Class111_Sub3.method2133((byte)(-20));
        Class64_Sub22.method645((byte)102);
        Class246_Sub2.aClass172ArrayArrayArray5077 = new Class172[anInt2409][Class366.anInt3112][Class64_Sub9.anInt3662];
        Class98_Sub46_Sub2_Sub2.aSArray6298 = new s[anInt2409];
        if (b) {
            Class40.anIntArrayArray367 = new int[Class366.anInt3112][Class64_Sub9.anInt3662];
            Class299_Sub2.aByteArrayArray5291 = new byte[Class366.anInt3112][Class64_Sub9.anInt3662];
            Class304.aShortArrayArray2534 = new short[Class366.anInt3112][Class64_Sub9.anInt3662];
            Class252.aClass172ArrayArrayArray1927 = new Class172[1][Class366.anInt3112][Class64_Sub9.anInt3662];
            Class81.aSArray618 = new s[1];
        }
        else {
            Class40.anIntArrayArray367 = null;
            Class299_Sub2.aByteArrayArray5291 = null;
            Class304.aShortArrayArray2534 = null;
            Class252.aClass172ArrayArrayArray1927 = null;
            Class81.aSArray618 = null;
        }
        if (b2) {
            Class373_Sub3.aLongArrayArrayArray5476 = new long[anInt2409][anInt2410][anInt2411];
            Class98_Sub10_Sub31.aClass1Array5717 = new Class1[65535];
            Class21_Sub4.aBooleanArray5399 = new boolean[65535];
            Class226.anInt1705 = 0;
        }
        else {
            Class373_Sub3.aLongArrayArrayArray5476 = null;
            Class98_Sub10_Sub31.aClass1Array5717 = null;
            Class21_Sub4.aBooleanArray5399 = null;
            Class226.anInt1705 = 0;
        }
        Class248.method3158(false);
        Class379.aClass246_Sub3Array3198 = new Class246_Sub3[2];
        Class359.aClass246_Sub3Array3056 = new Class246_Sub3[2];
        Class130.aClass246_Sub3Array1029 = new Class246_Sub3[2];
        Class32.aClass246_Sub3Array307 = new Class246_Sub3[10000];
        Class302.anInt2523 = 0;
        Class246_Sub4_Sub2.aClass246_Sub3Array6173 = new Class246_Sub3[5000];
        Class353.anInt3009 = 0;
        Class246_Sub3_Sub5_Sub2.aClass246_Sub3_Sub4Array6273 = new Class246_Sub3_Sub4[5000];
        Class347.anInt2907 = 0;
        Class74.aBooleanArrayArray551 = new boolean[Class259.anInt1959 + Class259.anInt1959 + 1][Class259.anInt1959 + Class259.anInt1959 + 1];
        Class319.aBooleanArrayArray2702 = new boolean[Class259.anInt1959 + Class259.anInt1959 + 2][Class259.anInt1959 + Class259.anInt1959 + 2];
        Class347.anIntArray2906 = new int[Class259.anInt1959 + Class259.anInt1959 + 2];
        Class98_Sub10_Sub27.aClass84_5692 = Class98_Sub10_Sub27.aClass84_5693;
        if (Class375.aBoolean3170) {
            Class34.aBooleanArrayArrayArray325 = new boolean[anInt2409][Class259.anInt1959 + Class259.anInt1959 + 1][Class259.anInt1959 + Class259.anInt1959 + 1];
            Class64_Sub12.aBooleanArrayArrayArray3673 = new boolean[anInt2409][][];
            if (Class98_Sub46_Sub5.aClass174Array5970 != null) {
                Class249.method3162();
            }
            Class98_Sub46_Sub5.aClass174Array5970 = new Class174[Class294.anInt2407];
            Class98_Sub10_Sub30.aHa5709.method1783(Class98_Sub46_Sub5.aClass174Array5970.length + 1);
            Class98_Sub10_Sub30.aHa5709.method1807(0);
            for (int i = 0; i < Class98_Sub46_Sub5.aClass174Array5970.length; ++i) {
                Class98_Sub46_Sub5.aClass174Array5970[i] = new Class174(i + 1, Class98_Sub10_Sub30.aHa5709);
                new Thread(Class98_Sub46_Sub5.aClass174Array5970[i], "wr" + i).start();
            }
            int n;
            if (Class294.anInt2407 == 2) {
                n = 4;
                Class18.anInt212 = 2;
            }
            else if (Class294.anInt2407 == 3) {
                n = 6;
                Class18.anInt212 = 3;
            }
            else {
                n = 8;
                Class18.anInt212 = 4;
            }
            Class98_Sub43_Sub3.aClass245Array5922 = new Class245[n];
            for (int j = 0; j < n; ++j) {
                Class98_Sub43_Sub3.aClass245Array5922[j] = new Class245(Class55.aStringArrayArray441[Class294.anInt2407 - 2][j]);
            }
        }
        else {
            Class18.anInt212 = 1;
        }
        s.anIntArray2205 = new int[Class18.anInt212 - 1];
        Class15.anIntArray182 = new int[Class18.anInt212 - 1];
    }
    
    private final void method2165(final byte b) {
        try {
            int n8;
            int n7;
            int n6;
            int n5;
            int n4;
            int n3;
            int n2;
            int n = n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = -1640531527))))));
            for (int n9 = 0; ~n9 > -5; ++n9) {
                final int n10 = n2 ^ n << -910493557;
                final int n11 = n + n3;
                final int n12 = n4 + n10;
                final int n13 = n11 ^ n3 >>> -424891550;
                final int n14 = n5 + n13;
                final int n15 = n3 + n12 ^ n12 << -1200133080;
                final int n16 = n12 + n14;
                final int n17 = n6 + n15;
                n4 = (n16 ^ n14 >>> -151076080);
                final int n18 = n14 + n17;
                final int n19 = n7 + n4;
                n5 = (n18 ^ n17 << -958066198);
                final int n20 = n8 + n5;
                n6 = (n17 + n19 ^ n19 >>> 1081109060);
                final int n21 = n10 + n6;
                n7 = (n19 + n20 ^ n20 << -2066782968);
                final int n22 = n20 + n21;
                n = n13 + n7;
                n8 = (n22 ^ n21 >>> -1653233111);
                n2 = n21 + n;
                n3 = n15 + n8;
            }
            for (int i = 0; i < 256; i += 8) {
                final int n23 = n + this.anIntArray973[i + 1];
                final int n24 = n2 + this.anIntArray973[i];
                final int n25 = n8 + this.anIntArray973[i + 7];
                final int n26 = n6 + this.anIntArray973[5 + i];
                final int n27 = n3 + this.anIntArray973[2 + i];
                final int n28 = n5 + this.anIntArray973[4 + i];
                final int n29 = n4 + this.anIntArray973[3 + i];
                final int n30 = n7 + this.anIntArray973[i + 6];
                final int n31 = n24 ^ n23 << 1494265387;
                final int n32 = n23 + n27;
                final int n33 = n29 + n31;
                final int n34 = n32 ^ n27 >>> 386271234;
                final int n35 = n28 + n34;
                final int n36 = n27 + n33 ^ n33 << -459009784;
                final int n37 = n33 + n35;
                final int n38 = n26 + n36;
                n4 = (n37 ^ n35 >>> 1744658032);
                final int n39 = n30 + n4;
                n5 = (n35 + n38 ^ n38 << -754939414);
                final int n40 = n25 + n5;
                n6 = (n38 + n39 ^ n39 >>> -1730138780);
                final int n41 = n31 + n6;
                n7 = (n39 + n40 ^ n40 << -949934008);
                n = n34 + n7;
                n8 = (n40 + n41 ^ n41 >>> -1543727799);
                n3 = n36 + n8;
                n2 = n41 + n;
                this.anIntArray969[i] = n2;
                this.anIntArray969[1 + i] = n;
                this.anIntArray969[i + 2] = n3;
                this.anIntArray969[i + 3] = n4;
                this.anIntArray969[4 + i] = n5;
                this.anIntArray969[5 + i] = n6;
                this.anIntArray969[i + 6] = n7;
                this.anIntArray969[i + 7] = n8;
            }
            for (int n42 = 0; ~n42 > -257; n42 += 8) {
                final int n43 = n8 + this.anIntArray969[n42 + 7];
                final int n44 = n4 + this.anIntArray969[n42 + 3];
                final int n45 = n5 + this.anIntArray969[4 + n42];
                final int n46 = n + this.anIntArray969[1 + n42];
                final int n47 = n7 + this.anIntArray969[6 + n42];
                final int n48 = n2 + this.anIntArray969[n42];
                final int n49 = n6 + this.anIntArray969[n42 + 5];
                final int n50 = n3 + this.anIntArray969[n42 + 2];
                final int n51 = n48 ^ n46 << -542763221;
                final int n52 = n46 + n50;
                final int n53 = n44 + n51;
                final int n54 = n52 ^ n50 >>> -902646974;
                final int n55 = n45 + n54;
                final int n56 = n50 + n53 ^ n53 << 334759432;
                final int n57 = n49 + n56;
                n4 = (n53 + n55 ^ n55 >>> 967114160);
                final int n58 = n47 + n4;
                n5 = (n55 + n57 ^ n57 << 755037322);
                final int n59 = n43 + n5;
                n6 = (n57 + n58 ^ n58 >>> -1484981788);
                final int n60 = n58 + n59;
                final int n61 = n51 + n6;
                n7 = (n60 ^ n59 << 463243080);
                n = n54 + n7;
                n8 = (n59 + n61 ^ n61 >>> 1562519465);
                n3 = n56 + n8;
                n2 = n61 + n;
                this.anIntArray969[n42] = n2;
                this.anIntArray969[n42 + 1] = n;
                this.anIntArray969[n42 + 2] = n3;
                this.anIntArray969[n42 + 3] = n4;
                this.anIntArray969[n42 + 4] = n5;
                this.anIntArray969[5 + n42] = n6;
                this.anIntArray969[6 + n42] = n7;
                this.anIntArray969[n42 + 7] = n8;
            }
            this.method2166((byte)94);
            this.anInt970 = 256;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hj.B(" + b + ')');
        }
    }
    
    private final void method2166(final byte b) {
        try {
            if (b == 94) {
                this.anInt968 += ++this.anInt972;
                for (int i = 0; i < 256; ++i) {
                    final int n = this.anIntArray969[i];
                    if (~(0x2 & i) != -1) {
                        if (~(0x1 & i) != -1) {
                            this.anInt971 ^= this.anInt971 >>> -2137572944;
                        }
                        else {
                            this.anInt971 ^= this.anInt971 << -255770270;
                        }
                    }
                    else if ((i & 0x1) == 0x0) {
                        this.anInt971 ^= this.anInt971 << -1951024339;
                    }
                    else {
                        this.anInt971 ^= this.anInt971 >>> 1690224390;
                    }
                    this.anInt971 += this.anIntArray969[0xFF & 128 + i];
                    this.anIntArray973[i] = (this.anInt968 = this.anIntArray969[Class202.method2702((this.anIntArray969[i] = this.anInt971 + this.anIntArray969[Class202.method2702(1020, n) >> -875557438] - -this.anInt968) >> -1052867128, 1020) >> -1635419422] - -n);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hj.G(" + b + ')');
        }
    }
    
    final int method2167(final int n) {
        try {
            if (n <= 76) {
                this.anInt968 = -71;
            }
            if (~this.anInt970 == -1) {
                this.method2166((byte)94);
                this.anInt970 = 256;
            }
            return 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hj.C(" + n + ')');
        }
    }
    
    private Class117() {
    }
    
    final int method2168(final int n) {
        try {
            if (n != 3) {
                return 20;
            }
            if (this.anInt970 == 0) {
                this.method2166((byte)94);
                this.anInt970 = 256;
            }
            return 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hj.D(" + n + ')');
        }
    }
    
    static final void method2169(final int[] array, final long[] array2, final int n, final int n2, final boolean b) {
        try {
            if (n < n2) {
                final int n3 = (n2 + n) / 2;
                int n4 = n;
                final long n5 = array2[n3];
                array2[n3] = array2[n2];
                array2[n2] = n5;
                final int n6 = array[n3];
                array[n3] = array[n2];
                array[n2] = n6;
                final boolean b2 = n5 != Long.MAX_VALUE;
                for (int n7 = n; ~n2 < ~n7; ++n7) {
                    if (n5 - -((b2 ? 1 : 0) & n7) > array2[n7]) {
                        final long n8 = array2[n7];
                        array2[n7] = array2[n4];
                        array2[n4] = n8;
                        final int n9 = array[n7];
                        array[n7] = array[n4];
                        array[n4++] = n9;
                    }
                }
                array2[n2] = array2[n4];
                array2[n4] = n5;
                array[n2] = array[n4];
                array[n4] = n6;
                method2169(array, array2, n, n4 - 1, b);
                method2169(array, array2, 1 + n4, n2, b);
            }
            if (b) {
                method2164(null, 101, -71, 107, 35, -44, 75, true, true);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hj.F(" + ((array != null) ? "{...}" : "null") + ',' + ((array2 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + b + ')');
        }
    }
    
    Class117(final int[] array) {
        try {
            this.anIntArray969 = new int[256];
            this.anIntArray973 = new int[256];
            for (int n = 0; ~array.length < ~n; ++n) {
                this.anIntArray973[n] = array[n];
            }
            this.method2165((byte)(-123));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hj.<init>(" + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class117.anIntArray974 = new int[50];
    }
}
