// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub46_Sub10 extends Class98_Sub46
{
    String aString6005;
    int anInt6006;
    int anInt6007;
    int anInt6008;
    int anInt6009;
    Class148 aClass148_6010;
    static Class354 aClass354_6011;
    static int[][] anIntArrayArray6012;
    int anInt6013;
    int anInt6014;
    static int[] anIntArray6015;
    int anInt6016;
    String aString6017;
    static Class148 aClass148_6018;
    static Class197 aClass197_6019;
    static int anInt6020;
    boolean aBoolean6021;
    static int anInt6022;
    int anInt6023;
    
    final boolean method1563(final int n, final int n2, final int[] array, final int n3) {
        try {
            if (n != 31960) {
                method1565(70, -83, false, 79, 63, 26, -76, -81, 21);
            }
            for (Class98_Sub6 class98_Sub6 = (Class98_Sub6)this.aClass148_6010.method2418(32); class98_Sub6 != null; class98_Sub6 = (Class98_Sub6)this.aClass148_6010.method2417(113)) {
                if (class98_Sub6.method977(n2, (byte)41, n3)) {
                    class98_Sub6.method979(n3, 0, n2, array);
                    return true;
                }
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gga.A(" + n + ',' + n2 + ',' + ((array != null) ? "{...}" : "null") + ',' + n3 + ')');
        }
    }
    
    final boolean method1564(final int n, final int n2, final int n3) {
        try {
            if (n != 6) {
                method1567((byte)(-19));
            }
            for (Class98_Sub6 class98_Sub6 = (Class98_Sub6)this.aClass148_6010.method2418(32); class98_Sub6 != null; class98_Sub6 = (Class98_Sub6)this.aClass148_6010.method2417(107)) {
                if (class98_Sub6.method980(n - 116, n2, n3)) {
                    return true;
                }
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gga.P(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static final void method1565(final int n, final int n2, final boolean b, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        try {
            Class258.aClass155Array1951[Class21_Sub3.anInt5389++] = new Class155(n3, n4, n, n5, n5, n, n6, n8, n8, n6, n7, n7, n2, n2);
            if (b) {
                Class98_Sub46_Sub10.aClass197_6019 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gga.O(" + n + ',' + n2 + ',' + b + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ')');
        }
    }
    
    static final void method1566(final long[] array, final int n, final byte b, final int n2, final Object[] array2) {
        try {
            if (n < n2) {
                final int n3 = (n2 + n) / 2;
                int n4 = n;
                final long n5 = array[n3];
                array[n3] = array[n2];
                array[n2] = n5;
                final Object o = array2[n3];
                array2[n3] = array2[n2];
                array2[n2] = o;
                final boolean b2 = ~n5 != Long.MIN_VALUE;
                for (int n6 = n; ~n2 < ~n6; ++n6) {
                    if (array[n6] < n5 - -(n6 & (b2 ? 1 : 0))) {
                        final long n7 = array[n6];
                        array[n6] = array[n4];
                        array[n4] = n7;
                        final Object o2 = array2[n6];
                        array2[n6] = array2[n4];
                        array2[n4++] = o2;
                    }
                }
                array[n2] = array[n4];
                array[n4] = n5;
                array2[n2] = array2[n4];
                array2[n4] = o;
                method1566(array, n, (byte)64, -1 + n4, array2);
                method1566(array, n4 + 1, (byte)(-109), n2, array2);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gga.B(" + ((array != null) ? "{...}" : "null") + ',' + n + ',' + b + ',' + n2 + ',' + ((array2 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method1567(final byte b) {
        try {
            if (b >= 41) {
                Class98_Sub46_Sub10.anIntArray6015 = null;
                Class98_Sub46_Sub10.aClass197_6019 = null;
                Class98_Sub46_Sub10.aClass148_6018 = null;
                Class98_Sub46_Sub10.anIntArrayArray6012 = null;
                Class98_Sub46_Sub10.aClass354_6011 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gga.N(" + b + ')');
        }
    }
    
    static final boolean method1568(final int i, final int n, final int n2, final int n3, final int n4, final byte b, final int n5, final int n6, final int n7, final int n8, final int n9, final Class243 class243) {
        try {
            if (b != 20) {
                return false;
            }
            int anInt1539 = n2;
            int anInt1540 = n4;
            final int n10 = 64;
            final int n11 = 64;
            final int n12 = -n10 + n2;
            final int n13 = n4 + -n11;
            PlayerUpdateMask.anIntArrayArray528[n10][n11] = 99;
            Class339.anIntArrayArray2846[n10][n11] = 0;
            int n14 = 0;
            int n15 = 0;
            Class359.anIntArray3060[n14] = anInt1539;
            Class75.anIntArray580[n14++] = anInt1540;
            final int[][] anIntArrayArray1853 = class243.anIntArrayArray1853;
        Label_0092:
            while (~n15 != ~n14) {
                anInt1539 = Class359.anIntArray3060[n15];
                anInt1540 = Class75.anIntArray580[n15];
                final int n16 = anInt1539 + -n12;
                final int n17 = anInt1540 + -n13;
                n15 = (1 + n15 & 0xFFF);
                final int n18 = -class243.anInt1854 + anInt1539;
                final int n19 = -class243.anInt1855 + anInt1540;
                if (~n5 != 0x3) {
                    if (n5 != -3) {
                        if (~n5 != 0x1) {
                            if (~n5 != 0x0) {
                                if (n5 != 0 && ~n5 != 0xFFFFFFFE && n5 != 2 && n5 != 3 && n5 != 9) {
                                    if (class243.method2938(n8, n3, anInt1540, n9, n5, 17761, i, anInt1539)) {
                                        Class22.anInt217 = anInt1540;
                                        Class199.anInt1539 = anInt1539;
                                        return true;
                                    }
                                }
                                else if (class243.method2952(n9, anInt1539, (byte)(-123), i, anInt1540, n3, n8, n5)) {
                                    Class22.anInt217 = anInt1540;
                                    Class199.anInt1539 = anInt1539;
                                    return true;
                                }
                            }
                            else if (class243.method2939(n7, n8, anInt1540, 14672, i, n9, n6, anInt1539, n)) {
                                Class22.anInt217 = anInt1540;
                                Class199.anInt1539 = anInt1539;
                                return true;
                            }
                        }
                        else if (class243.method2936(n8, n, n9, -1, anInt1540, n7, i, anInt1539, i, n6)) {
                            Class22.anInt217 = anInt1540;
                            Class199.anInt1539 = anInt1539;
                            return true;
                        }
                    }
                    else if (Class98_Sub5.method960(n8, n7, -113, anInt1540, n, anInt1539, n9, i, i)) {
                        Class199.anInt1539 = anInt1539;
                        Class22.anInt217 = anInt1540;
                        return true;
                    }
                }
                else if (anInt1539 == n8 && ~anInt1540 == ~n9) {
                    Class22.anInt217 = anInt1540;
                    Class199.anInt1539 = anInt1539;
                    return true;
                }
                final int n20 = 1 + Class339.anIntArrayArray2846[n16][n17];
                Label_0613: {
                    if (~n16 < -1 && PlayerUpdateMask.anIntArrayArray528[-1 + n16][n17] == 0 && (anIntArrayArray1853[-1 + n18][n19] & 0x43A40000) == 0x0 && ~(anIntArrayArray1853[n18 - 1][n19 + i - 1] & 0x4E240000) == -1) {
                        for (int n21 = 1; ~n21 > ~(-1 + i); ++n21) {
                            if ((anIntArrayArray1853[-1 + n18][n19 - -n21] & 0x4FA40000) != 0x0) {
                                break Label_0613;
                            }
                        }
                        Class359.anIntArray3060[n14] = -1 + anInt1539;
                        Class75.anIntArray580[n14] = anInt1540;
                        n14 = (n14 + 1 & 0xFFF);
                        PlayerUpdateMask.anIntArrayArray528[-1 + n16][n17] = 2;
                        Class339.anIntArrayArray2846[-1 + n16][n17] = n20;
                    }
                }
                Label_0778: {
                    if (n16 < 128 + -i && ~PlayerUpdateMask.anIntArrayArray528[1 + n16][n17] == -1 && (0x60E40000 & anIntArrayArray1853[n18 - -i][n19]) == 0x0 && ~(anIntArrayArray1853[n18 - -i][-1 + i + n19] & 0x78240000) == -1) {
                        for (int n22 = 1; i - 1 > n22; ++n22) {
                            if (~(anIntArrayArray1853[n18 + i][n22 + n19] & 0x78E40000) != -1) {
                                break Label_0778;
                            }
                        }
                        Class359.anIntArray3060[n14] = 1 + anInt1539;
                        Class75.anIntArray580[n14] = anInt1540;
                        PlayerUpdateMask.anIntArrayArray528[n16 + 1][n17] = 8;
                        n14 = (n14 + 1 & 0xFFF);
                        Class339.anIntArrayArray2846[n16 + 1][n17] = n20;
                    }
                }
                Label_0935: {
                    if (~n17 < -1 && ~PlayerUpdateMask.anIntArrayArray528[n16][n17 - 1] == -1 && ~(0x43A40000 & anIntArrayArray1853[n18][n19 - 1]) == -1 && (anIntArrayArray1853[-1 + n18 - -i][n19 - 1] & 0x60E40000) == 0x0) {
                        for (int j = 1; j < i - 1; ++j) {
                            if ((anIntArrayArray1853[n18 + j][-1 + n19] & 0x63E40000) != 0x0) {
                                break Label_0935;
                            }
                        }
                        Class359.anIntArray3060[n14] = anInt1539;
                        Class75.anIntArray580[n14] = -1 + anInt1540;
                        n14 = (0xFFF & 1 + n14);
                        PlayerUpdateMask.anIntArrayArray528[n16][n17 - 1] = 1;
                        Class339.anIntArrayArray2846[n16][-1 + n17] = n20;
                    }
                }
                Label_1103: {
                    if (n17 < 128 - i && ~PlayerUpdateMask.anIntArrayArray528[n16][n17 + 1] == -1 && ~(anIntArrayArray1853[n18][n19 - -i] & 0x4E240000) == -1 && (0x78240000 & anIntArrayArray1853[n18 - (-i + 1)][n19 - -i]) == 0x0) {
                        for (int n23 = 1; ~n23 > ~(-1 + i); ++n23) {
                            if (~(0x7E240000 & anIntArrayArray1853[n18 + n23][n19 + i]) != -1) {
                                break Label_1103;
                            }
                        }
                        Class359.anIntArray3060[n14] = anInt1539;
                        Class75.anIntArray580[n14] = anInt1540 + 1;
                        PlayerUpdateMask.anIntArrayArray528[n16][1 + n17] = 4;
                        n14 = (n14 + 1 & 0xFFF);
                        Class339.anIntArrayArray2846[n16][1 + n17] = n20;
                    }
                }
                Label_1284: {
                    if (~n16 < -1 && n17 > 0 && ~PlayerUpdateMask.anIntArrayArray528[-1 + n16][n17 - 1] == -1 && (anIntArrayArray1853[n18 - 1][-1 + n19] & 0x43A40000) == 0x0) {
                        for (int n24 = 1; ~n24 > ~i; ++n24) {
                            if (~(anIntArrayArray1853[-1 + n18][-1 + (n19 - -n24)] & 0x4FA40000) != -1) {
                                break Label_1284;
                            }
                            if (~(anIntArrayArray1853[-1 + n18 - -n24][n19 - 1] & 0x63E40000) != -1) {
                                break Label_1284;
                            }
                        }
                        Class359.anIntArray3060[n14] = -1 + anInt1539;
                        Class75.anIntArray580[n14] = -1 + anInt1540;
                        PlayerUpdateMask.anIntArrayArray528[n16 - 1][-1 + n17] = 3;
                        n14 = (0xFFF & n14 + 1);
                        Class339.anIntArrayArray2846[-1 + n16][-1 + n17] = n20;
                    }
                }
                Label_1464: {
                    if (~n16 > ~(128 - i) && n17 > 0 && PlayerUpdateMask.anIntArrayArray528[n16 + 1][n17 - 1] == 0 && (0x60E40000 & anIntArrayArray1853[n18 + i][n19 - 1]) == 0x0) {
                        for (int n25 = 1; ~n25 > ~i; ++n25) {
                            if ((0x78E40000 & anIntArrayArray1853[n18 - -i][n25 + (n19 - 1)]) != 0x0) {
                                break Label_1464;
                            }
                            if (~(anIntArrayArray1853[n18 - -n25][n19 - 1] & 0x63E40000) != -1) {
                                break Label_1464;
                            }
                        }
                        Class359.anIntArray3060[n14] = 1 + anInt1539;
                        Class75.anIntArray580[n14] = -1 + anInt1540;
                        PlayerUpdateMask.anIntArrayArray528[n16 + 1][n17 - 1] = 9;
                        n14 = (0xFFF & n14 + 1);
                        Class339.anIntArrayArray2846[n16 + 1][n17 - 1] = n20;
                    }
                }
                Label_1645: {
                    if (~n16 < -1 && ~(128 - i) < ~n17 && ~PlayerUpdateMask.anIntArrayArray528[-1 + n16][n17 + 1] == -1 && (0x4E240000 & anIntArrayArray1853[-1 + n18][i + n19]) == 0x0) {
                        for (int n26 = 1; i > n26; ++n26) {
                            if ((anIntArrayArray1853[-1 + n18][n19 + n26] & 0x4FA40000) != 0x0) {
                                break Label_1645;
                            }
                            if (~(0x7E240000 & anIntArrayArray1853[n18 + (-1 - -n26)][i + n19]) != -1) {
                                break Label_1645;
                            }
                        }
                        Class359.anIntArray3060[n14] = anInt1539 - 1;
                        Class75.anIntArray580[n14] = 1 + anInt1540;
                        n14 = (0xFFF & 1 + n14);
                        PlayerUpdateMask.anIntArrayArray528[-1 + n16][1 + n17] = 6;
                        Class339.anIntArrayArray2846[n16 - 1][1 + n17] = n20;
                    }
                }
                if (~(128 + -i) < ~n16 && ~n17 > ~(128 - i) && ~PlayerUpdateMask.anIntArrayArray528[n16 + 1][n17 + 1] == -1 && ~(0x78240000 & anIntArrayArray1853[i + n18][i + n19]) == -1) {
                    for (int k = 1; k < i; ++k) {
                        if ((anIntArrayArray1853[n18 + k][i + n19] & 0x7E240000) != 0x0) {
                            continue Label_0092;
                        }
                        if (~(0x78E40000 & anIntArrayArray1853[n18 + i][k + n19]) != -1) {
                            continue Label_0092;
                        }
                    }
                    Class359.anIntArray3060[n14] = anInt1539 + 1;
                    Class75.anIntArray580[n14] = 1 + anInt1540;
                    n14 = (0xFFF & 1 + n14);
                    PlayerUpdateMask.anIntArrayArray528[1 + n16][1 + n17] = 12;
                    Class339.anIntArrayArray2846[n16 + 1][n17 + 1] = n20;
                }
            }
            Class199.anInt1539 = anInt1539;
            Class22.anInt217 = anInt1540;
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gga.C(" + i + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + b + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ',' + n9 + ',' + ((class243 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method1569(final int n) {
        try {
            this.anInt6009 = 12800;
            this.anInt6023 = 0;
            this.anInt6008 = 12800;
            this.anInt6016 = 0;
            if (n != -1) {
                this.method1564(71, -12, 111);
            }
            for (Class98_Sub6 class98_Sub6 = (Class98_Sub6)this.aClass148_6010.method2418(32); class98_Sub6 != null; class98_Sub6 = (Class98_Sub6)this.aClass148_6010.method2417(117)) {
                if (~this.anInt6016 > ~class98_Sub6.anInt3838) {
                    this.anInt6016 = class98_Sub6.anInt3838;
                }
                if (this.anInt6008 > class98_Sub6.anInt3839) {
                    this.anInt6008 = class98_Sub6.anInt3839;
                }
                if (this.anInt6009 > class98_Sub6.anInt3843) {
                    this.anInt6009 = class98_Sub6.anInt3843;
                }
                if (~class98_Sub6.anInt3845 < ~this.anInt6023) {
                    this.anInt6023 = class98_Sub6.anInt3845;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gga.D(" + n + ')');
        }
    }
    
    final boolean method1570(final byte b, final int n, final int[] array, final int n2) {
        try {
            for (Class98_Sub6 class98_Sub6 = (Class98_Sub6)this.aClass148_6010.method2418(32); class98_Sub6 != null; class98_Sub6 = (Class98_Sub6)this.aClass148_6010.method2417(109)) {
                if (class98_Sub6.method980(-96, n2, n)) {
                    class98_Sub6.method982(n, n2, 88, array);
                    return true;
                }
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gga.M(" + b + ',' + n + ',' + ((array != null) ? "{...}" : "null") + ',' + n2 + ')');
        }
    }
    
    static final byte[] method1571(final byte[] array, final byte b) {
        try {
            if (b != -84) {
                method1567((byte)87);
            }
            final Class98_Sub22 class98_Sub22 = new Class98_Sub22(array);
            final int unsignedByte = class98_Sub22.readUnsignedByte((byte)(-103));
            final int int1 = class98_Sub22.readInt(-2);
            if (int1 < 0 || (Class333.anInt3390 != 0 && int1 > Class333.anInt3390)) {
                throw new RuntimeException();
            }
            if (~unsignedByte == -1) {
                final byte[] array2 = new byte[int1];
                class98_Sub22.method1190(array2, true, int1, 0);
                return array2;
            }
            final int int2 = class98_Sub22.readInt(-2);
            if (int1 > 1000000 || int1 < 0) {
                return new byte[100];
            }
            if (~int2 > -1 || (~Class333.anInt3390 != -1 && int2 > Class333.anInt3390)) {
                throw new RuntimeException();
            }
            final byte[] array3 = new byte[int2];
            if (~unsignedByte != 0xFFFFFFFE) {
                synchronized (Class284_Sub1_Sub1.aClass263_6189) {
                    Class284_Sub1_Sub1.aClass263_6189.method3218(array3, class98_Sub22, 18762);
                }
            }
            else {
                Class330.method3716(array3, int2, array, int1, 9);
            }
            return array3;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gga.G(" + ((array != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static final void method1572(final int n, final int n2, final int anInt6054) {
        try {
            if (n2 == 30585) {
                final Class98_Sub46_Sub17 method2628 = Class185.method2628(n, -40, 16);
                method2628.method1626((byte)(-103));
                method2628.anInt6054 = anInt6054;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gga.F(" + n + ',' + n2 + ',' + anInt6054 + ')');
        }
    }
    
    final boolean method1573(final int n, final int[] array, final int n2, final int n3, final int n4) {
        try {
            if (n2 >= -80) {
                method1565(31, -45, true, -110, 4, -16, -41, 5, -2);
            }
            for (Class98_Sub6 class98_Sub6 = (Class98_Sub6)this.aClass148_6010.method2418(32); class98_Sub6 != null; class98_Sub6 = (Class98_Sub6)this.aClass148_6010.method2417(103)) {
                if (class98_Sub6.method976(n3, 113, n4, n)) {
                    class98_Sub6.method982(n3, n4, 94, array);
                    return true;
                }
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gga.E(" + n + ',' + ((array != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    Class98_Sub46_Sub10(final int anInt6014, final String aString6017, final String aString6018, final int anInt6015, final int anInt6016, final boolean aBoolean6021, final int anInt6017, final int n) {
        this.anInt6007 = -1;
        this.anInt6008 = 12800;
        this.anInt6009 = 12800;
        this.anInt6016 = 0;
        this.aBoolean6021 = true;
        this.anInt6023 = 0;
        this.anInt6013 = -1;
        try {
            this.anInt6007 = anInt6017;
            this.anInt6014 = anInt6014;
            this.aString6005 = aString6018;
            this.anInt6006 = anInt6015;
            this.aString6017 = aString6017;
            this.aBoolean6021 = aBoolean6021;
            this.anInt6013 = anInt6016;
            if (this.anInt6007 == 255) {
                this.anInt6007 = 0;
            }
            this.aClass148_6010 = new Class148();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gga.<init>(" + anInt6014 + ',' + ((aString6017 != null) ? "{...}" : "null") + ',' + ((aString6018 != null) ? "{...}" : "null") + ',' + anInt6015 + ',' + anInt6016 + ',' + aBoolean6021 + ',' + anInt6017 + ',' + n + ')');
        }
    }
    
    static {
        Class98_Sub46_Sub10.anIntArrayArray6012 = new int[][] { { 0, 2, 4, 6 }, { 6, 0, 2, 4 }, { 6, 0, 2 }, { 2, 6, 0 }, { 0, 2, 6 }, { 6, 0, 2 }, { 5, 6, 0, 1, 2, 4 }, { 7, 2, 4, 4 }, { 2, 4, 4, 7 }, { 6, 6, 4, 0, 2, 2 }, { 0, 2, 2, 6, 6, 4 }, { 0, 2, 2, 4, 6, 6 }, { 0, 2, 4, 6 } };
        Class98_Sub46_Sub10.aClass148_6018 = new Class148();
        Class98_Sub46_Sub10.anInt6022 = 0;
    }
}
