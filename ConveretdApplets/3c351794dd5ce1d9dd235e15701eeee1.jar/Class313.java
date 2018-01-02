import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class313
{
    private long aLong2678;
    private long aLong2679;
    static float aFloat2680;
    boolean aBoolean2681;
    private int anInt2682;
    int[] anIntArray2683;
    int anInt2684;
    private int[] anIntArray2685;
    
    final Class146 method3624(final byte b, final int n, final Class83 class83, final Class205 class84, final ha ha, final int n2, final Interface6 interface6, final Class97 class85, final Class301 class86, final int n3, final Class183 class87, final int n4) {
        try {
            if (this.anInt2684 != -1) {
                return class86.method3538(5, this.anInt2684).method2299(class85, false, interface6, n, n2, class87, n4, ha, null, n3);
            }
            int method1777 = n4;
            if (class85 != null) {
                boolean b2 = false;
                boolean b3 = false;
                boolean b4 = false;
                boolean b5 = false;
                method1777 |= 0x20;
                final int n5 = class85.anIntArray818[n2];
                final int n6 = n5 >>> 1075836880;
                final Class98_Sub46_Sub16 method1778 = class87.method2624(2, n6);
                final int n7 = n5 & 0xFFFF;
                if (method1778 != null) {
                    b3 |= method1778.method1619(n7, 31239);
                    b2 |= method1778.method1617(false, n7);
                    b5 |= method1778.method1615(n7, false);
                    b4 |= class85.aBoolean817;
                }
                if ((class85.aBoolean825 || Class357.aBoolean3027) && n3 != -1 && class85.anIntArray818.length > n3) {
                    final int n8 = class85.anIntArray818[n3];
                    final int n9 = n8 >>> 334721392;
                    final int n10 = n8 & 0xFFFF;
                    final Class98_Sub46_Sub16 class98_Sub46_Sub16 = (n9 == n6) ? method1778 : class87.method2624(2, n9);
                    if (class98_Sub46_Sub16 != null) {
                        b3 |= class98_Sub46_Sub16.method1619(n10, 31239);
                        b2 |= class98_Sub46_Sub16.method1617(false, n10);
                        b5 |= class98_Sub46_Sub16.method1615(n10, false);
                    }
                }
                if (b3) {
                    method1777 |= 0x80;
                }
                if (b2) {
                    method1777 |= 0x100;
                }
                if (b4) {
                    method1777 |= 0x200;
                }
                if (b5) {
                    method1777 |= 0x400;
                }
            }
            Class146 method1779;
            synchronized (PlayerUpdate.aClass79_3411) {
                method1779 = (Class146)PlayerUpdate.aClass79_3411.method802(-128, this.aLong2678);
            }
            if (method1779 == null || ha.c(method1779.ua(), method1777) != 0) {
                if (method1779 != null) {
                    method1777 = ha.method1777(method1777, method1779.ua());
                }
                final int n11 = method1777;
                boolean b6 = false;
                for (int n12 = 0; ~n12 > -13; ++n12) {
                    final int n13 = this.anIntArray2685[n12];
                    if (~(n13 & 0x40000000) == -1) {
                        if ((n13 & Integer.MIN_VALUE) != 0x0 && !class83.method826(0x3FFFFFFF & n13, 3).method2474(113)) {
                            b6 = true;
                        }
                    }
                    else if (!class84.method2714(0x3FFFFFFF & n13, (byte)(-128)).method3489(this.aBoolean2681, 92)) {
                        b6 = true;
                    }
                }
                if (b6) {
                    return null;
                }
                final Class178[] array = new Class178[12];
                int n14 = 0;
                for (int n15 = 0; ~n15 > -13; ++n15) {
                    final int n16 = this.anIntArray2685[n15];
                    if (~(n16 & 0x40000000) != -1) {
                        final Class178 method1780 = class84.method2714(n16 & 0x3FFFFFFF, (byte)(-120)).method3486(this.aBoolean2681, 0);
                        if (method1780 != null) {
                            array[n14++] = method1780;
                        }
                    }
                    else if ((Integer.MIN_VALUE & n16) != 0x0) {
                        final Class178 method1781 = class83.method826(n16 & 0x3FFFFFFF, 3).method2476((byte)(-99));
                        if (method1781 != null) {
                            array[n14++] = method1781;
                        }
                    }
                }
                method1779 = ha.method1790(new Class178(array, n14), n11 | 0x4000, Class81.anInt624, 64, 768);
                for (int i = 0; i < 5; ++i) {
                    for (int n17 = 0; ~Class61.aShortArrayArrayArray478.length < ~n17; ++n17) {
                        if (this.anIntArray2683[i] < Class61.aShortArrayArrayArray478[n17][i].length) {
                            method1779.ia(Class98_Sub10_Sub11.aShortArrayArray5597[n17][i], Class61.aShortArrayArrayArray478[n17][i][this.anIntArray2683[i]]);
                        }
                    }
                }
                method1779.s(method1777);
                synchronized (PlayerUpdate.aClass79_3411) {
                    PlayerUpdate.aClass79_3411.method805(this.aLong2678, method1779, (byte)(-80));
                }
            }
            if (class85 == null) {
                return method1779;
            }
            method1779.method2341((byte)4, method1777, true);
            if (b <= 83) {
                this.aLong2678 = -7L;
            }
            return class85.method937(n3, n, n4, 127, method1779, n2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tfa.G(" + b + ',' + n + ',' + ((class83 != null) ? "{...}" : "null") + ',' + ((class84 != null) ? "{...}" : "null") + ',' + ((ha != null) ? "{...}" : "null") + ',' + n2 + ',' + ((interface6 != null) ? "{...}" : "null") + ',' + ((class85 != null) ? "{...}" : "null") + ',' + ((class86 != null) ? "{...}" : "null") + ',' + n3 + ',' + ((class87 != null) ? "{...}" : "null") + ',' + n4 + ')');
        }
    }
    
    static final void method3625(final int n) {
        try {
            final int[] array = new int[Class98_Sub46_Sub19.aClass205_6068.anInt1554];
            int n2 = 0;
            for (int i = 0; i < Class98_Sub46_Sub19.aClass205_6068.anInt1554; ++i) {
                final Class297 method2714 = Class98_Sub46_Sub19.aClass205_6068.method2714(i, (byte)(-124));
                if (~method2714.anInt2458 <= -1 || ~method2714.anInt2466 <= -1) {
                    array[n2++] = i;
                }
            }
            Class255.anIntArray3207 = new int[n2];
            try {
                final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("equipids.txt"));
                for (int n3 = 0; ~n3 > ~n2; ++n3) {
                    Class255.anIntArray3207[n3] = array[n3];
                    bufferedWriter.write(array[n3] + ":" + n3);
                    bufferedWriter.newLine();
                }
                bufferedWriter.flush();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            if (n != -1439) {
                method3625(57);
            }
        }
        catch (RuntimeException ex2) {
            throw Class64_Sub27.method667(ex2, "tfa.C(" + n + ')');
        }
    }
    
    final Class146 method3626(final int n, final Class97 class97, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final Class83 class98, final ha ha, final int n8, final Class183 class99) {
        try {
            if (n8 != 256) {
                method3636(-13, 94);
            }
            int method1777 = n7;
            if (class97 != null) {
                boolean b = false;
                boolean b2 = false;
                boolean b3 = false;
                boolean b4 = false;
                final int n9 = class97.anIntArray818[n];
                method1777 |= 0x20;
                final int n10 = n9 >>> -1674141136;
                final Class98_Sub46_Sub16 method1778 = class99.method2624(n8 ^ 0x102, n10);
                final int n11 = n9 & 0xFFFF;
                if (method1778 != null) {
                    b2 |= method1778.method1619(n11, 31239);
                    b |= method1778.method1617(false, n11);
                    b4 |= method1778.method1615(n11, false);
                    b3 |= class97.aBoolean817;
                }
                if ((class97.aBoolean825 || Class357.aBoolean3027) && n6 != -1 && class97.anIntArray818.length > n6) {
                    final int n12 = class97.anIntArray818[n6];
                    final int n13 = n12 >>> 1835918000;
                    final int n14 = n12 & 0xFFFF;
                    Class98_Sub46_Sub16 method1779;
                    if (~n13 == ~n10) {
                        method1779 = method1778;
                    }
                    else {
                        method1779 = class99.method2624(2, n14 >>> 243215344);
                    }
                    if (method1779 != null) {
                        b2 |= method1779.method1619(n14, n8 + 30983);
                        b |= method1779.method1617(false, n14);
                        b4 |= method1779.method1615(n14, false);
                    }
                }
                if (b2) {
                    method1777 |= 0x80;
                }
                if (b) {
                    method1777 |= 0x100;
                }
                if (b3) {
                    method1777 |= 0x200;
                }
                if (b4) {
                    method1777 |= 0x400;
                }
            }
            final long n15 = n5 << -715025360 | n3 << 270913248 | n4;
            Class146 method1780;
            synchronized (PlayerUpdate.aClass79_3411) {
                method1780 = (Class146)PlayerUpdate.aClass79_3411.method802(-125, n15);
            }
            if (method1780 == null || ~ha.c(method1780.ua(), method1777) != -1) {
                if (method1780 != null) {
                    method1777 = ha.method1777(method1777, method1780.ua());
                }
                final int n16 = method1777;
                final Class178[] array = new Class178[3];
                int n17 = 0;
                if (!class98.method826(n4, n8 - 253).method2474(n8 - 150) || !class98.method826(n5, n8 - 253).method2474(125) || !class98.method826(n3, 3).method2474(n8 - 150)) {
                    return null;
                }
                final Class178 method1781 = class98.method826(n4, 3).method2476((byte)(-103));
                if (method1781 != null) {
                    array[n17++] = method1781;
                }
                final Class178 method1782 = class98.method826(n5, n8 - 253).method2476((byte)68);
                if (method1782 != null) {
                    array[n17++] = method1782;
                }
                final Class178 method1783 = class98.method826(n3, n8 ^ 0x103).method2476((byte)110);
                if (method1783 != null) {
                    array[n17++] = method1783;
                }
                method1780 = ha.method1790(new Class178(array, n17), n16 | 0x4000, Class81.anInt624, 64, 768);
                for (int n18 = 0; ~n18 > -6; ++n18) {
                    for (int n19 = 0; Class61.aShortArrayArrayArray478.length > n19; ++n19) {
                        if (this.anIntArray2683[n18] < Class61.aShortArrayArrayArray478[n19][n18].length) {
                            method1780.ia(Class98_Sub10_Sub11.aShortArrayArray5597[n19][n18], Class61.aShortArrayArrayArray478[n19][n18][this.anIntArray2683[n18]]);
                        }
                    }
                }
                method1780.s(method1777);
                synchronized (PlayerUpdate.aClass79_3411) {
                    PlayerUpdate.aClass79_3411.method805(n15, method1780, (byte)(-80));
                }
            }
            if (class97 == null) {
                return method1780;
            }
            return class97.method937(n6, n2, n7, -43, method1780.method2341((byte)4, method1777, true), n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tfa.J(" + n + ',' + ((class97 != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + ((class98 != null) ? "{...}" : "null") + ',' + ((ha != null) ? "{...}" : "null") + ',' + n8 + ',' + ((class99 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method3627(final int[] anIntArray2685, final int n, final boolean aBoolean2681, final int[] anIntArray2686, final int anInt2682, final int anInt2683) {
        try {
            if (anInt2682 != this.anInt2682) {
                this.anInt2682 = anInt2682;
            }
            this.anIntArray2685 = anIntArray2685;
            this.aBoolean2681 = aBoolean2681;
            this.anInt2684 = anInt2683;
            this.anIntArray2683 = anIntArray2686;
            if (n >= -18) {
                this.anInt2682 = 120;
            }
            this.method3633(95);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tfa.M(" + ((anIntArray2685 != null) ? "{...}" : "null") + ',' + n + ',' + aBoolean2681 + ',' + ((anIntArray2686 != null) ? "{...}" : "null") + ',' + anInt2682 + ',' + anInt2683 + ')');
        }
    }
    
    final Class146 method3628(final Class257 class257, final Class97 class258, final Class97 class259, final Class183 class260, final int n, final Class83 class261, final int[] array, final Class205 class262, final boolean b, final int n2, final Class226[] array2, final Class301 class263, final Interface6 interface6, final boolean b2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final ha ha) {
        try {
            if (~this.anInt2684 != 0x0) {
                return class263.method3538(5, this.anInt2684).method2301(n, n8, array, n7, (byte)101, class258, array2, ha, n2, n6, interface6, null, n5, class257, n3, n4, class260, class259);
            }
            int method1777 = n3;
            long aLong2678 = this.aLong2678;
            int[] anIntArray2685 = this.anIntArray2685;
            if (class258 != null && (class258.anInt820 >= 0 || class258.anInt809 >= 0)) {
                anIntArray2685 = new int[12];
                for (int i = 0; i < 12; ++i) {
                    anIntArray2685[i] = this.anIntArray2685[i];
                }
                if (~class258.anInt820 <= -1) {
                    if (class258.anInt820 != 65535) {
                        anIntArray2685[5] = Class41.method366(class258.anInt820, 1073741824);
                        aLong2678 ^= anIntArray2685[5] << 1378327584;
                    }
                    else {
                        anIntArray2685[5] = 0;
                        aLong2678 ^= 0xFFFFFFFF00000000L;
                    }
                }
                if (class258.anInt809 >= 0) {
                    if (~class258.anInt809 != 0xFFFF0000) {
                        anIntArray2685[3] = Class41.method366(class258.anInt809, 1073741824);
                        aLong2678 ^= anIntArray2685[3];
                    }
                    else {
                        anIntArray2685[3] = 0;
                        aLong2678 ^= 0xFFFFFFFFL;
                    }
                }
            }
            boolean b3 = false;
            boolean b4 = false;
            boolean b5 = false;
            boolean b6 = class258 != null || class259 != null;
            final int j = (array2 == null) ? 0 : array2.length;
            for (int n9 = 0; ~n9 > ~j; ++n9) {
                PacketParser.aClass98_Sub46_Sub16Array5468[n9] = null;
                if (array2[n9] != null) {
                    final Class97 method1778 = class260.method2623(array2[n9].anInt1700, 16383);
                    if (method1778.anIntArray818 != null) {
                        b6 = true;
                        Class356.aClass97Array3023[n9] = method1778;
                        final int anInt1702 = array2[n9].anInt1702;
                        final int anInt1703 = array2[n9].anInt1701;
                        final int n10 = method1778.anIntArray818[anInt1702];
                        PacketParser.aClass98_Sub46_Sub16Array5468[n9] = class260.method2624(2, n10 >>> -1761370288);
                        final int n11 = n10 & 0xFFFF;
                        Class367.anIntArray3546[n9] = n11;
                        if (PacketParser.aClass98_Sub46_Sub16Array5468[n9] != null) {
                            b4 |= PacketParser.aClass98_Sub46_Sub16Array5468[n9].method1619(n11, 31239);
                            b3 |= PacketParser.aClass98_Sub46_Sub16Array5468[n9].method1617(false, n11);
                            b5 |= PacketParser.aClass98_Sub46_Sub16Array5468[n9].method1615(n11, !b);
                        }
                        if ((!method1778.aBoolean825 && !Class357.aBoolean3027) || anInt1703 == -1 || ~anInt1703 <= ~method1778.anIntArray818.length) {
                            Class98_Sub10_Sub17.anIntArray5624[n9] = 0;
                            Class218.anIntArray1631[n9] = 0;
                            Class351.aClass98_Sub46_Sub16Array2924[n9] = null;
                            Class292.anIntArray3355[n9] = -1;
                        }
                        else {
                            Class98_Sub10_Sub17.anIntArray5624[n9] = method1778.anIntArray811[anInt1702];
                            Class218.anIntArray1631[n9] = array2[n9].anInt1707;
                            final int n12 = method1778.anIntArray818[anInt1703];
                            Class351.aClass98_Sub46_Sub16Array2924[n9] = class260.method2624(2, n12 >>> 347419536);
                            final int n13 = n12 & 0xFFFF;
                            Class292.anIntArray3355[n9] = n13;
                            if (Class351.aClass98_Sub46_Sub16Array2924[n9] != null) {
                                b4 |= Class351.aClass98_Sub46_Sub16Array2924[n9].method1619(n13, 31239);
                                b3 |= Class351.aClass98_Sub46_Sub16Array2924[n9].method1617(false, n13);
                                b5 |= Class351.aClass98_Sub46_Sub16Array2924[n9].method1615(n13, false);
                            }
                        }
                    }
                }
            }
            int n14 = -1;
            int n15 = -1;
            int n16 = 0;
            Class98_Sub46_Sub16 method1779 = null;
            Class98_Sub46_Sub16 class98_Sub46_Sub16 = null;
            int n17 = -1;
            int n18 = -1;
            int n19 = 0;
            Class98_Sub46_Sub16 method1780 = null;
            Class98_Sub46_Sub16 class98_Sub46_Sub17 = null;
            if (b6) {
                method1777 |= 0x20;
                if (class258 != null) {
                    final int n20 = class258.anIntArray818[n];
                    final int n21 = n20 >>> -454090480;
                    n14 = (n20 & 0xFFFF);
                    method1779 = class260.method2624(2, n21);
                    if (method1779 != null) {
                        b4 |= method1779.method1619(n14, 31239);
                        b3 |= method1779.method1617(false, n14);
                        b5 |= method1779.method1615(n14, false);
                    }
                    if ((class258.aBoolean825 || Class357.aBoolean3027) && ~n6 != 0x0 && class258.anIntArray818.length > n6) {
                        final int n22 = class258.anIntArray818[n6];
                        n16 = class258.anIntArray811[n];
                        final int n23 = n22 >>> -240024528;
                        class98_Sub46_Sub16 = ((~n23 == ~n21) ? method1779 : class260.method2624(2, n23));
                        n15 = (n22 & 0xFFFF);
                        if (class98_Sub46_Sub16 != null) {
                            b4 |= class98_Sub46_Sub16.method1619(n15, 31239);
                            b3 |= class98_Sub46_Sub16.method1617(false, n15);
                            b5 |= class98_Sub46_Sub16.method1615(n15, false);
                        }
                    }
                }
                if (class259 != null) {
                    final int n24 = class259.anIntArray818[n7];
                    final int n25 = n24 >>> 1300157968;
                    n17 = (n24 & 0xFFFF);
                    method1780 = class260.method2624(2, n25);
                    if (method1780 != null) {
                        b4 |= method1780.method1619(n17, 31239);
                        b3 |= method1780.method1617(!b, n17);
                        b5 |= method1780.method1615(n17, false);
                    }
                    if ((class259.aBoolean825 || Class357.aBoolean3027) && n4 != -1 && class259.anIntArray818.length > n4) {
                        n19 = class259.anIntArray811[n7];
                        final int n26 = class259.anIntArray818[n4];
                        final int n27 = n26 >>> -1612855760;
                        class98_Sub46_Sub17 = ((~n27 != ~n25) ? class260.method2624(2, n27) : method1780);
                        n18 = (n26 & 0xFFFF);
                        if (class98_Sub46_Sub17 != null) {
                            b4 |= class98_Sub46_Sub17.method1619(n18, 31239);
                            b3 |= class98_Sub46_Sub17.method1617(false, n18);
                            b5 |= class98_Sub46_Sub17.method1615(n18, false);
                        }
                    }
                }
                if (b4) {
                    method1777 |= 0x80;
                }
                if (b3) {
                    method1777 |= 0x100;
                }
                if (b5) {
                    method1777 |= 0x400;
                }
            }
            Class146 method1781;
            synchronized (Class211.aClass79_1594) {
                method1781 = (Class146)Class211.aClass79_1594.method802(-119, aLong2678);
            }
            Class294 method1782 = null;
            if (~this.anInt2682 != 0x0) {
                method1782 = class257.method3199(false, this.anInt2682);
            }
            if (method1781 == null || ~ha.c(method1781.ua(), method1777) != -1) {
                if (method1781 != null) {
                    method1777 = ha.method1777(method1777, method1781.ua());
                }
                final int n28 = method1777;
                boolean b7 = false;
                for (int k = 0; k < 12; ++k) {
                    final int n29 = anIntArray2685[k];
                    if (~(n29 & 0x40000000) == -1) {
                        if (~(n29 & Integer.MIN_VALUE) != -1 && !class261.method826(0x3FFFFFFF & n29, 3).method2475(0)) {
                            b7 = true;
                        }
                    }
                    else if (!class262.method2714(n29 & 0x3FFFFFFF, (byte)(-128)).method3492(0, this.aBoolean2681)) {
                        b7 = true;
                    }
                }
                if (!b7) {
                    final Class178[] array3 = new Class178[12];
                    for (int n30 = 0; ~n30 > -13; ++n30) {
                        final int n31 = anIntArray2685[n30];
                        if (~(n31 & 0x40000000) == -1) {
                            if (~(Integer.MIN_VALUE & n31) != -1) {
                                final Class178 method1783 = class261.method826(0x3FFFFFFF & n31, 3).method2473(2);
                                if (method1783 != null) {
                                    array3[n30] = method1783;
                                }
                            }
                        }
                        else {
                            final Class178 method1784 = class262.method2714(0x3FFFFFFF & n31, (byte)(-124)).method3500(this.aBoolean2681, 124);
                            if (method1784 != null) {
                                array3[n30] = method1784;
                            }
                        }
                    }
                    if (method1782 != null && method1782.anIntArrayArray2366 != null) {
                        for (int n32 = 0; method1782.anIntArrayArray2366.length > n32; ++n32) {
                            if (array3[n32] != null) {
                                int n33 = 0;
                                int n34 = 0;
                                int n35 = 0;
                                int n36 = 0;
                                int n37 = 0;
                                int n38 = 0;
                                if (method1782.anIntArrayArray2366[n32] != null) {
                                    n38 = method1782.anIntArrayArray2366[n32][5] << -264909725;
                                    n36 = method1782.anIntArrayArray2366[n32][3] << 819372707;
                                    n34 = method1782.anIntArrayArray2366[n32][1];
                                    n37 = method1782.anIntArrayArray2366[n32][4] << 1212264099;
                                    n33 = method1782.anIntArrayArray2366[n32][0];
                                    n35 = method1782.anIntArrayArray2366[n32][2];
                                }
                                if (~n36 != -1 || ~n37 != -1 || n38 != 0) {
                                    array3[n32].method2600(n38, n36, (byte)117, n37);
                                }
                                if (n33 != 0 || n34 != 0 || n35 != 0) {
                                    array3[n32].method2597(n35, n33, (byte)104, n34);
                                }
                            }
                        }
                    }
                    method1781 = ha.method1790(new Class178(array3, array3.length), n28 | 0x4000, Class81.anInt624, 64, 850);
                    for (int l = 0; l < 5; ++l) {
                        for (int n39 = 0; ~Class61.aShortArrayArrayArray478.length < ~n39; ++n39) {
                            if (~Class61.aShortArrayArrayArray478[n39][l].length < ~this.anIntArray2683[l]) {
                                method1781.ia(Class98_Sub10_Sub11.aShortArrayArray5597[n39][l], Class61.aShortArrayArrayArray478[n39][l][this.anIntArray2683[l]]);
                            }
                        }
                    }
                    if (b2) {
                        method1781.s(method1777);
                        synchronized (Class211.aClass79_1594) {
                            Class211.aClass79_1594.method805(aLong2678, method1781, (byte)(-80));
                        }
                        this.aLong2679 = aLong2678;
                    }
                }
                else {
                    if (this.aLong2679 != -1L) {
                        synchronized (Class211.aClass79_1594) {
                            method1781 = (Class146)Class211.aClass79_1594.method802(-123, this.aLong2679);
                        }
                    }
                    if (method1781 == null || ha.c(method1781.ua(), method1777) != 0) {
                        return null;
                    }
                }
            }
            final Class146 method1785 = method1781.method2341((byte)4, method1777, b);
            boolean b8 = false;
            if (array != null) {
                for (int n40 = 0; n40 < 12; ++n40) {
                    if (~array[n40] != 0x0) {
                        b8 = true;
                    }
                }
            }
            if (!b6 && !b8) {
                return method1785;
            }
            Class111[] method1786 = null;
            if (method1782 != null) {
                method1786 = method1782.method3481(ha, (byte)45);
            }
            if (b8 && method1786 != null) {
                for (int n41 = 0; ~n41 > -13; ++n41) {
                    if (method1786[n41] != null) {
                        method1785.method2331(method1786[n41], 1 << n41, true);
                    }
                }
            }
            int n42 = 0;
            int n43 = 1;
            while (j > n42) {
                if (PacketParser.aClass98_Sub46_Sub16Array5468[n42] != null) {
                    method1785.method2323(Class367.anIntArray3546[n42], -1 + Class218.anIntArray1631[n42], Class351.aClass98_Sub46_Sub16Array2924[n42], -27033, n43, PacketParser.aClass98_Sub46_Sub16Array5468[n42], false, Class292.anIntArray3355[n42], null, 0, Class98_Sub10_Sub17.anIntArray5624[n42]);
                }
                n43 <<= 1;
                ++n42;
            }
            if (b8) {
                for (int n44 = 0; ~n44 > -13; ++n44) {
                    if (array[n44] != -1) {
                        final int n45 = -n8 + array[n44] & 0x3FFF;
                        final Class111 method1787 = ha.method1821();
                        method1787.method2101(n45);
                        method1785.method2331(method1787, 1 << n44, false);
                    }
                }
            }
            if (b8 && method1786 != null) {
                for (int n46 = 0; n46 < 12; ++n46) {
                    if (method1786[n46] != null) {
                        method1785.method2331(method1786[n46], 1 << n46, false);
                    }
                }
            }
            if (method1779 == null || method1780 == null) {
                if (method1779 != null) {
                    method1785.method2338(-1 + n5, method1779, n14, class98_Sub46_Sub16, false, 0, 112, n16, n15);
                }
                else if (method1780 != null) {
                    method1785.method2338(n2 - 1, method1780, n17, class98_Sub46_Sub17, false, 0, 119, n19, n18);
                }
            }
            else {
                method1785.method2321(n16, n14, method1779, class98_Sub46_Sub16, class258.aBooleanArray813, n19, 28777, n17, method1780, n18, -1 + n5, false, class98_Sub46_Sub17, -1 + n2, n15);
            }
            for (int n47 = 0; j > n47; ++n47) {
                PacketParser.aClass98_Sub46_Sub16Array5468[n47] = null;
                Class351.aClass98_Sub46_Sub16Array2924[n47] = null;
                Class356.aClass97Array3023[n47] = null;
            }
            return method1785;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tfa.K(" + ((class257 != null) ? "{...}" : "null") + ',' + ((class258 != null) ? "{...}" : "null") + ',' + ((class259 != null) ? "{...}" : "null") + ',' + ((class260 != null) ? "{...}" : "null") + ',' + n + ',' + ((class261 != null) ? "{...}" : "null") + ',' + ((array != null) ? "{...}" : "null") + ',' + ((class262 != null) ? "{...}" : "null") + ',' + b + ',' + n2 + ',' + ((array2 != null) ? "{...}" : "null") + ',' + ((class263 != null) ? "{...}" : "null") + ',' + ((interface6 != null) ? "{...}" : "null") + ',' + b2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final IncomingOpcode[] method3629(final int n) {
        try {
            if (n <= 1) {
                method3630(-6, 26);
            }
            return new IncomingOpcode[] { Class370.aClass58_3134, Class18.aClass58_215, Class98_Sub10_Sub20.aClass58_5638, Class98_Sub32_Sub1.aClass58_5883, Class251.aClass58_1921, Class177.aClass58_1381, Class76.aClass58_587, Class211.aClass58_1595, Class53.aClass58_431, Class98_Sub50.aClass58_4291, Class232.aClass58_1743, Class40.aClass58_369, Class151_Sub6.aClass58_4998, Class246_Sub4_Sub2.aClass58_6182, Class287.aClass58_2194, Class13.aClass58_161, Class64_Sub27.aClass58_3715, Class98_Sub22_Sub2.aClass58_5793, PacketParser.aClass58_5466, Class348.aClass58_2912, Class98_Sub41.aClass58_4199, Class48_Sub1_Sub2.aClass58_5520, InputStream_Sub1.aClass58_28, Class320.aClass58_2708, Class246_Sub3_Sub4_Sub5.aClass58_6264, Class39.aClass58_364, Class188.aClass58_1452, Class60.aClass58_476, Class246_Sub3_Sub4_Sub2_Sub2.aClass58_6516, Class302.aClass58_2514, Class98_Sub46_Sub8.aClass58_5996, Class150.aClass58_1212, Class98_Sub31_Sub2.aClass58_5838, Class224_Sub1.aClass58_5032, Class64_Sub1.aClass58_3637, Class8.aClass58_112, Class98_Sub46_Sub15.aClass58_6041, Class369.aClass58_3132, Class309.aClass58_2651, Class55.aClass58_433, Class98_Sub10_Sub15.aClass58_5615, Class352.aClass58_2993, Class98_Sub42.aClass58_4222, Class151_Sub8.aClass58_5011, Class77.aClass58_592, Class16.aClass58_191, Class246_Sub3_Sub2.aClass58_6151, Class299.aClass58_2495, Class64_Sub2.aClass58_3642, Class150.aClass58_1210, Class322.aClass58_2713, Class372.aClass58_3147, Class98_Sub10_Sub20.aClass58_5635, Class149.aClass58_1207, Class336.aClass58_2825, Class246_Sub3_Sub4_Sub3.aClass58_6457, Class284_Sub2_Sub2.aClass58_6197, Class98_Sub26.aClass58_4029, Class93_Sub3.aClass58_5493, Class217.aClass58_3406, Class98_Sub46_Sub6.aClass58_5975, Class27.aClass58_277, Class93_Sub1.aClass58_5482, Class283.aClass58_2139, Class64_Sub10.aClass58_3665, Class246_Sub3_Sub2_Sub1.aClass58_6335, Class98_Sub12.aClass58_3877, aa_Sub3.aClass58_3566, Class98_Sub10_Sub14.aClass58_5606, Class76.aClass58_589, Class27.aClass58_274, Class335.aClass58_2816, Class196.aClass58_1507, Class64_Sub2.aClass58_3645, Class339.aClass58_2844, Class44.aClass58_379, Class151_Sub6.aClass58_4997, Class98_Sub10_Sub14.aClass58_5608, Class65.aClass58_499, Class211.aClass58_1596, Class151_Sub6.aClass58_4999, Class3.aClass58_75, Class266.aClass58_1992, Class246_Sub3.aClass58_5086, Class146.aClass58_1179, Class159.aClass58_1253, Class98_Sub34.aClass58_4128, Class151_Sub5.aClass58_4992, Class98_Sub18.aClass58_3946, Class312.aClass58_2661, Class283.aClass58_2143, Class98_Sub46_Sub19.aClass58_6057, Class207.aClass58_1576, Class315.aClass58_3533, aa_Sub1.aClass58_3554, Class36.aClass58_344, Class308.aClass58_2581, Class98_Sub10_Sub28.aClass58_5697, Class246_Sub4_Sub1.aClass58_6166, Class77.aClass58_591, Class301.aClass58_2507, s_Sub1.aClass58_5205, Class213.aClass58_1609, Class15.aClass58_184, Class76_Sub2.aClass58_3731, Class69_Sub2.aClass58_5333, Class25.aClass58_266, Class284_Sub1.aClass58_5176, Class352.aClass58_2943, Class180.aClass58_3398, Class358.aClass58_3029, Class287.aClass58_2187, Class246_Sub3_Sub4_Sub4.aClass58_6487, Class59.aClass58_469, Class98_Sub22.aClass58_3993, Class147.aClass58_1192, Class277.aClass58_2052, Exception_Sub1.aClass58_43, Class98_Sub47.aClass58_4270, Class64_Sub15.aClass58_3677, Class73.aClass58_3482, Class98_Sub6.aClass58_3844 };
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tfa.I(" + n + ')');
        }
    }
    
    static final Class98_Sub10 method3630(final int n, final int n2) {
        try {
            if (n != 0) {
                if (~n == 0xFFFFFFFE) {
                    return new Class98_Sub10_Sub22();
                }
                if (n == 2) {
                    return new Class98_Sub10_Sub18();
                }
                if (n == 3) {
                    return new Class98_Sub10_Sub3();
                }
                if (n == 4) {
                    return new Class98_Sub10_Sub38();
                }
                if (n == 5) {
                    return new Class98_Sub10_Sub24();
                }
                if (n == 6) {
                    return new Class98_Sub10_Sub15();
                }
                if (n == 7) {
                    return new Class98_Sub10_Sub7();
                }
                if (~n == 0xFFFFFFF7) {
                    return new Class98_Sub10_Sub9();
                }
                if (~n == 0xFFFFFFF6) {
                    return new Class98_Sub10_Sub11();
                }
                if (n == 10) {
                    return new Class98_Sub10_Sub33();
                }
                if (n == 11) {
                    return new Class98_Sub10_Sub4();
                }
                if (n == 12) {
                    return new Class98_Sub10_Sub30();
                }
                if (~n == 0xFFFFFFF2) {
                    return new Class98_Sub10_Sub8();
                }
                if (~n == 0xFFFFFFF1) {
                    return new Class98_Sub10_Sub17();
                }
                if (n == 15) {
                    return new Class98_Sub10_Sub26();
                }
                if (n == 16) {
                    return new Class98_Sub10_Sub32();
                }
                if (~n == 0xFFFFFFEE) {
                    return new Class98_Sub10_Sub6();
                }
                if (~n == 0xFFFFFFED) {
                    return new Class98_Sub10_Sub5_Sub1();
                }
                if (n == 19) {
                    return new Class98_Sub10_Sub2();
                }
                if (n == 20) {
                    return new Class98_Sub10_Sub29();
                }
                if (n == 21) {
                    return new Class98_Sub10_Sub12();
                }
                if (~n == 0xFFFFFFE9) {
                    return new Class98_Sub10_Sub39();
                }
                if (n == 23) {
                    return new Class98_Sub10_Sub27();
                }
                if (~n == 0xFFFFFFE7) {
                    return new Class98_Sub10_Sub16();
                }
                if (~n == 0xFFFFFFE6) {
                    return new Class98_Sub10_Sub14();
                }
                if (n == 26) {
                    return new Class98_Sub10_Sub31();
                }
                if (n == 27) {
                    return new Class98_Sub10_Sub23();
                }
                if (n == 28) {
                    return new Class98_Sub10_Sub28();
                }
                if (n == 29) {
                    return new Class98_Sub10_Sub36();
                }
                if (~n == 0xFFFFFFE1) {
                    return new Class98_Sub10_Sub10();
                }
                if (n == 31) {
                    return new Class98_Sub10_Sub34();
                }
                if (n == 32) {
                    return new Class98_Sub10_Sub37();
                }
                if (n == 33) {
                    return new Class98_Sub10_Sub20();
                }
                if (~n == 0xFFFFFFDD) {
                    return new Class98_Sub10_Sub35();
                }
                if (n == 35) {
                    return new Class98_Sub10_Sub1();
                }
                if (~n == 0xFFFFFFDB) {
                    return new Class98_Sub10_Sub25();
                }
                if (n == 37) {
                    return new Class98_Sub10_Sub21();
                }
                if (~n == 0xFFFFFFD9) {
                    return new Class98_Sub10_Sub19();
                }
                if (n != 39) {
                    return null;
                }
                if (!client.aBoolean3553) {
                    return new Class98_Sub10_Sub5();
                }
            }
            return new Class98_Sub10_Sub13();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tfa.A(" + n + ',' + n2 + ')');
        }
    }
    
    final void method3631(final int n, final int n2, final Class83 class83, final int n3) {
        try {
            final int n4 = Class370.anIntArray3135[n2];
            if (n == 12 && class83.method826(n3, 3) != null) {
                this.anIntArray2685[n4] = Class41.method366(n3, Integer.MIN_VALUE);
                System.out.println("Info " + this.anIntArray2685[n4]);
                this.method3633(n + 105);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tfa.E(" + n + ',' + n2 + ',' + ((class83 != null) ? "{...}" : "null") + ',' + n3 + ')');
        }
    }
    
    final void method3632(final int n, final int n2, final int n3) {
        try {
            if (n3 == -9) {
                this.anIntArray2683[n2] = n;
                this.method3633(87);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tfa.H(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    private final void method3633(final int n) {
        try {
            final long[] aLongArray3164 = Class374.aLongArray3164;
            this.aLong2678 = -1L;
            this.aLong2678 = (this.aLong2678 >>> -9573176 ^ aLongArray3164[(int)(0xFFL & (this.aLong2678 ^ this.anInt2682 >> 1903458056))]);
            this.aLong2678 = (this.aLong2678 >>> 1694471240 ^ aLongArray3164[(int)(0xFFL & (this.anInt2682 ^ this.aLong2678))]);
            if (n <= 86) {
                this.method3635(false, false);
            }
            for (int n2 = 0; ~n2 > -13; ++n2) {
                this.aLong2678 = (aLongArray3164[(int)(0xFFL & (this.aLong2678 ^ this.anIntArray2685[n2] >> -1026146760))] ^ this.aLong2678 >>> 1854597576);
                this.aLong2678 = (this.aLong2678 >>> 649681160 ^ aLongArray3164[(int)(0xFFL & (this.anIntArray2685[n2] >> -773668400 ^ this.aLong2678))]);
                this.aLong2678 = (this.aLong2678 >>> 519626696 ^ aLongArray3164[(int)((this.anIntArray2685[n2] >> -1074692952 ^ this.aLong2678) & 0xFFL)]);
                this.aLong2678 = (aLongArray3164[(int)(0xFFL & (this.aLong2678 ^ this.anIntArray2685[n2]))] ^ this.aLong2678 >>> 690165768);
            }
            for (int i = 0; i < 5; ++i) {
                this.aLong2678 = (aLongArray3164[(int)(0xFFL & (this.anIntArray2683[i] ^ this.aLong2678))] ^ this.aLong2678 >>> 376623304);
            }
            this.aLong2678 = (this.aLong2678 >>> -1793821816 ^ aLongArray3164[(int)(((this.aBoolean2681 ? 1 : 0) ^ this.aLong2678) & 0xFFL)]);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tfa.F(" + n + ')');
        }
    }
    
    final void method3634(final int n, final int n2, final Class205 class205, final int n3) {
        try {
            if (n3 != 1073741824) {
                this.aBoolean2681 = false;
            }
            if (n != -1) {
                if (class205.method2714(n, (byte)(-118)) != null) {
                    this.anIntArray2685[n2] = Class41.method366(1073741824, n);
                    this.method3633(n3 ^ 0x4000005D);
                }
            }
            else {
                this.anIntArray2685[n2] = 0;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tfa.L(" + n + ',' + n2 + ',' + ((class205 != null) ? "{...}" : "null") + ',' + n3 + ')');
        }
    }
    
    final void method3635(final boolean aBoolean2681, final boolean b) {
        try {
            this.aBoolean2681 = aBoolean2681;
            if (b) {
                this.method3628(null, null, null, null, 16, null, null, null, false, -109, null, null, null, false, 116, 111, 105, -25, 30, -75, null);
            }
            this.method3633(123);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tfa.D(" + aBoolean2681 + ',' + b + ')');
        }
    }
    
    static final int method3636(final int n, final int n2) {
        if (Class40.anIntArrayArray367 != null) {
            return Class40.anIntArrayArray367[n][n2] & 0xFFFFFF;
        }
        return 0;
    }
    
    public Class313() {
        this.anInt2684 = -1;
    }
    
    static {
        Class313.aFloat2680 = 0.25f;
    }
}
