// 
// Decompiled by Procyon v0.5.30
// 

final class Class56
{
    private static float[] aFloatArray445;
    private int[] anIntArray446;
    private int[] anIntArray447;
    private int[] anIntArray448;
    private int[] anIntArray449;
    private static int[] anIntArray450;
    private static boolean[] aBooleanArray451;
    private static int[] anIntArray452;
    private int[][] anIntArrayArray453;
    private int anInt454;
    private int[] anIntArray455;
    private static int[] anIntArray456;
    
    final boolean method510() {
        if (Class98_Sub13.method1134() == 0) {
            return false;
        }
        for (int length = this.anIntArray446.length, i = 0; i < length; ++i) {
            Class56.anIntArray452[i] = this.anIntArray446[i];
        }
        final int method474 = Class48_Sub2_Sub1.method474(Class56.anIntArray456[this.anInt454 - 1] - 1, (byte)31);
        Class56.anIntArray450[0] = Class98_Sub13.method1138(method474);
        Class56.anIntArray450[1] = Class98_Sub13.method1138(method474);
        int n = 2;
        for (int j = 0; j < this.anIntArray449.length; ++j) {
            final int n2 = this.anIntArray449[j];
            final int n3 = this.anIntArray455[n2];
            final int n4 = this.anIntArray447[n2];
            final int n5 = (1 << n4) - 1;
            int method475 = 0;
            if (n4 > 0) {
                method475 = Class98_Sub13.aClass71Array3885[this.anIntArray448[n2]].method714();
            }
            for (int k = 0; k < n3; ++k) {
                final int n6 = this.anIntArrayArray453[n2][method475 & n5];
                method475 >>>= n4;
                Class56.anIntArray450[n++] = ((n6 >= 0) ? Class98_Sub13.aClass71Array3885[n6].method714() : 0);
            }
        }
        return true;
    }
    
    private final void method511(final int n, final int n2, int n3, final int n4, final float[] array, final int n5) {
        final int n6 = n4 - n2;
        final int n7 = n3 - n;
        final int n8 = (n6 < 0) ? (-n6) : n6;
        final int n9 = n6 / n7;
        int n10 = n2;
        int n11 = 0;
        final int n12 = (n6 < 0) ? (n9 - 1) : (n9 + 1);
        final int n13 = n8 - ((n9 < 0) ? (-n9) : n9) * n7;
        array[n] *= Class56.aFloatArray445[n10];
        if (n3 > n5) {
            n3 = n5;
        }
        for (int i = n + 1; i < n3; ++i) {
            n11 += n13;
            if (n11 >= n7) {
                n11 -= n7;
                n10 += n12;
            }
            else {
                n10 += n9;
            }
            final int n14 = i;
            array[n14] *= Class56.aFloatArray445[n10];
        }
    }
    
    private static final int method512(final int[] array, final int n) {
        final int n2 = array[n];
        int n3 = -1;
        int n4 = Integer.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            final int n5 = array[i];
            if (n5 < n2 && n5 > n4) {
                n3 = i;
                n4 = n5;
            }
        }
        return n3;
    }
    
    final void method513(final float[] array, final int n) {
        final int length = this.anIntArray446.length;
        final int n2 = Class56.anIntArray456[this.anInt454 - 1];
        Class56.aBooleanArray451[0] = (Class56.aBooleanArray451[1] = true);
        for (int i = 2; i < length; ++i) {
            final int method512 = method512(Class56.anIntArray452, i);
            final int method513 = method516(Class56.anIntArray452, i);
            final int method514 = this.method517(Class56.anIntArray452[method512], Class56.anIntArray450[method512], Class56.anIntArray452[method513], Class56.anIntArray450[method513], Class56.anIntArray452[i]);
            final int n3 = Class56.anIntArray450[i];
            final int n4 = n2 - method514;
            final int n5 = method514;
            final int n6 = ((n4 < n5) ? n4 : n5) << 1;
            if (n3 != 0) {
                Class56.aBooleanArray451[method512] = (Class56.aBooleanArray451[method513] = true);
                Class56.aBooleanArray451[i] = true;
                if (n3 >= n6) {
                    Class56.anIntArray450[i] = ((n4 > n5) ? (n3 - n5 + method514) : (method514 - n3 + n4 - 1));
                }
                else {
                    Class56.anIntArray450[i] = (((n3 & 0x1) != 0x0) ? (method514 - (n3 + 1) / 2) : (method514 + n3 / 2));
                }
            }
            else {
                Class56.aBooleanArray451[i] = false;
                Class56.anIntArray450[i] = method514;
            }
        }
        this.method514(0, length - 1);
        int n7 = 0;
        int n8 = Class56.anIntArray450[0] * this.anInt454;
        for (int j = 1; j < length; ++j) {
            if (Class56.aBooleanArray451[j]) {
                final int n9 = Class56.anIntArray452[j];
                final int n10 = Class56.anIntArray450[j] * this.anInt454;
                this.method511(n7, n8, n9, n10, array, n);
                if (n9 >= n) {
                    return;
                }
                n7 = n9;
                n8 = n10;
            }
        }
        final float n11 = Class56.aFloatArray445[n8];
        for (int k = n7; k < n; ++k) {
            final int n12 = k;
            array[n12] *= n11;
        }
    }
    
    private final void method514(final int n, final int n2) {
        if (n < n2) {
            int n3 = n;
            final int n4 = Class56.anIntArray452[n3];
            final int n5 = Class56.anIntArray450[n3];
            final boolean b = Class56.aBooleanArray451[n3];
            for (int i = n + 1; i <= n2; ++i) {
                final int n6 = Class56.anIntArray452[i];
                if (n6 < n4) {
                    Class56.anIntArray452[n3] = n6;
                    Class56.anIntArray450[n3] = Class56.anIntArray450[i];
                    Class56.aBooleanArray451[n3] = Class56.aBooleanArray451[i];
                    ++n3;
                    Class56.anIntArray452[i] = Class56.anIntArray452[n3];
                    Class56.anIntArray450[i] = Class56.anIntArray450[n3];
                    Class56.aBooleanArray451[i] = Class56.aBooleanArray451[n3];
                }
            }
            Class56.anIntArray452[n3] = n4;
            Class56.anIntArray450[n3] = n5;
            Class56.aBooleanArray451[n3] = b;
            this.method514(n, n3 - 1);
            this.method514(n3 + 1, n2);
        }
    }
    
    public static void method515() {
        Class56.anIntArray456 = null;
        Class56.aFloatArray445 = null;
        Class56.anIntArray452 = null;
        Class56.anIntArray450 = null;
        Class56.aBooleanArray451 = null;
    }
    
    private static final int method516(final int[] array, final int n) {
        final int n2 = array[n];
        int n3 = -1;
        int n4 = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            final int n5 = array[i];
            if (n5 > n2 && n5 < n4) {
                n3 = i;
                n4 = n5;
            }
        }
        return n3;
    }
    
    private final int method517(final int n, final int n2, final int n3, final int n4, final int n5) {
        final int n6 = n4 - n2;
        final int n7 = ((n6 < 0) ? (-n6) : n6) * (n5 - n) / (n3 - n);
        if (n6 < 0) {
            return n2 - n7;
        }
        return n2 + n7;
    }
    
    Class56() {
        if (Class98_Sub13.method1138(16) != 1) {
            throw new RuntimeException();
        }
        final int method1138 = Class98_Sub13.method1138(5);
        int n = 0;
        this.anIntArray449 = new int[method1138];
        for (int i = 0; i < method1138; ++i) {
            final int method1139 = Class98_Sub13.method1138(4);
            if ((this.anIntArray449[i] = method1139) >= n) {
                n = method1139 + 1;
            }
        }
        this.anIntArray455 = new int[n];
        this.anIntArray447 = new int[n];
        this.anIntArray448 = new int[n];
        this.anIntArrayArray453 = new int[n][];
        for (int j = 0; j < n; ++j) {
            this.anIntArray455[j] = Class98_Sub13.method1138(3) + 1;
            final int[] anIntArray447 = this.anIntArray447;
            final int n2 = j;
            final int method1140 = Class98_Sub13.method1138(2);
            anIntArray447[n2] = method1140;
            final int n3 = method1140;
            if (n3 != 0) {
                this.anIntArray448[j] = Class98_Sub13.method1138(8);
            }
            final int n4 = 1 << n3;
            final int[] array = new int[n4];
            this.anIntArrayArray453[j] = array;
            for (int k = 0; k < n4; ++k) {
                array[k] = Class98_Sub13.method1138(8) - 1;
            }
        }
        this.anInt454 = Class98_Sub13.method1138(2) + 1;
        final int method1141 = Class98_Sub13.method1138(4);
        int n5 = 2;
        for (int l = 0; l < method1138; ++l) {
            n5 += this.anIntArray455[this.anIntArray449[l]];
        }
        (this.anIntArray446 = new int[n5])[0] = 0;
        this.anIntArray446[1] = 1 << method1141;
        int n6 = 2;
        for (int n7 = 0; n7 < method1138; ++n7) {
            for (int n8 = this.anIntArray449[n7], n9 = 0; n9 < this.anIntArray455[n8]; ++n9) {
                this.anIntArray446[n6++] = Class98_Sub13.method1138(method1141);
            }
        }
        if (Class56.anIntArray452 == null || Class56.anIntArray452.length < n6) {
            Class56.anIntArray452 = new int[n6];
            Class56.anIntArray450 = new int[n6];
            Class56.aBooleanArray451 = new boolean[n6];
        }
    }
    
    static {
        Class56.aFloatArray445 = new float[] { 1.0649863E-7f, 1.1341951E-7f, 1.2079015E-7f, 1.2863978E-7f, 1.369995E-7f, 1.459025E-7f, 1.5538409E-7f, 1.6548181E-7f, 1.7623574E-7f, 1.8768856E-7f, 1.998856E-7f, 2.128753E-7f, 2.2670913E-7f, 2.4144197E-7f, 2.5713223E-7f, 2.7384212E-7f, 2.9163792E-7f, 3.1059022E-7f, 3.307741E-7f, 3.5226967E-7f, 3.7516213E-7f, 3.995423E-7f, 4.255068E-7f, 4.5315863E-7f, 4.8260745E-7f, 5.1397E-7f, 5.4737063E-7f, 5.829419E-7f, 6.208247E-7f, 6.611694E-7f, 7.041359E-7f, 7.4989464E-7f, 7.98627E-7f, 8.505263E-7f, 9.057983E-7f, 9.646621E-7f, 1.0273513E-6f, 1.0941144E-6f, 1.1652161E-6f, 1.2409384E-6f, 1.3215816E-6f, 1.4074654E-6f, 1.4989305E-6f, 1.5963394E-6f, 1.7000785E-6f, 1.8105592E-6f, 1.9282195E-6f, 2.053526E-6f, 2.1869757E-6f, 2.3290977E-6f, 2.4804558E-6f, 2.6416496E-6f, 2.813319E-6f, 2.9961443E-6f, 3.1908505E-6f, 3.39821E-6f, 3.619045E-6f, 3.8542307E-6f, 4.1047006E-6f, 4.371447E-6f, 4.6555283E-6f, 4.958071E-6f, 5.280274E-6f, 5.623416E-6f, 5.988857E-6f, 6.3780467E-6f, 6.7925284E-6f, 7.2339453E-6f, 7.704048E-6f, 8.2047E-6f, 8.737888E-6f, 9.305725E-6f, 9.910464E-6f, 1.0554501E-5f, 1.1240392E-5f, 1.1970856E-5f, 1.2748789E-5f, 1.3577278E-5f, 1.4459606E-5f, 1.5399271E-5f, 1.6400005E-5f, 1.7465769E-5f, 1.8600793E-5f, 1.9809577E-5f, 2.1096914E-5f, 2.2467912E-5f, 2.3928002E-5f, 2.5482977E-5f, 2.7139005E-5f, 2.890265E-5f, 3.078091E-5f, 3.2781227E-5f, 3.4911533E-5f, 3.718028E-5f, 3.9596467E-5f, 4.2169668E-5f, 4.491009E-5f, 4.7828602E-5f, 5.0936775E-5f, 5.424693E-5f, 5.7772202E-5f, 6.152657E-5f, 6.552491E-5f, 6.9783084E-5f, 7.4317984E-5f, 7.914758E-5f, 8.429104E-5f, 8.976875E-5f, 9.560242E-5f, 1.0181521E-4f, 1.0843174E-4f, 1.1547824E-4f, 1.2298267E-4f, 1.3097477E-4f, 1.3948625E-4f, 1.4855085E-4f, 1.5820454E-4f, 1.6848555E-4f, 1.7943469E-4f, 1.9109536E-4f, 2.0351382E-4f, 2.167393E-4f, 2.3082423E-4f, 2.4582449E-4f, 2.6179955E-4f, 2.7881275E-4f, 2.9693157E-4f, 3.1622787E-4f, 3.3677815E-4f, 3.5866388E-4f, 3.8197188E-4f, 4.0679457E-4f, 4.3323037E-4f, 4.613841E-4f, 4.913675E-4f, 5.2329927E-4f, 5.573062E-4f, 5.935231E-4f, 6.320936E-4f, 6.731706E-4f, 7.16917E-4f, 7.635063E-4f, 8.1312325E-4f, 8.6596457E-4f, 9.2223985E-4f, 9.821722E-4f, 0.0010459992f, 0.0011139743f, 0.0011863665f, 0.0012634633f, 0.0013455702f, 0.0014330129f, 0.0015261382f, 0.0016253153f, 0.0017309374f, 0.0018434235f, 0.0019632196f, 0.0020908006f, 0.0022266726f, 0.0023713743f, 0.0025254795f, 0.0026895993f, 0.0028643848f, 0.0030505287f, 0.003248769f, 0.0034598925f, 0.0036847359f, 0.0039241905f, 0.0041792067f, 0.004450795f, 0.004740033f, 0.005048067f, 0.0053761187f, 0.005725489f, 0.0060975635f, 0.0064938175f, 0.0069158226f, 0.0073652514f, 0.007843887f, 0.008353627f, 0.008896492f, 0.009474637f, 0.010090352f, 0.01074608f, 0.011444421f, 0.012188144f, 0.012980198f, 0.013823725f, 0.014722068f, 0.015678791f, 0.016697686f, 0.017782796f, 0.018938422f, 0.020169148f, 0.021479854f, 0.022875736f, 0.02436233f, 0.025945531f, 0.027631618f, 0.029427277f, 0.031339627f, 0.03337625f, 0.035545226f, 0.037855156f, 0.0403152f, 0.042935107f, 0.045725275f, 0.048696756f, 0.05186135f, 0.05523159f, 0.05882085f, 0.062643364f, 0.06671428f, 0.07104975f, 0.075666964f, 0.08058423f, 0.08582105f, 0.09139818f, 0.097337745f, 0.1036633f, 0.11039993f, 0.11757434f, 0.12521498f, 0.13335215f, 0.14201812f, 0.15124726f, 0.16107617f, 0.1715438f, 0.18269168f, 0.19456401f, 0.20720787f, 0.22067343f, 0.23501402f, 0.25028655f, 0.26655158f, 0.28387362f, 0.3023213f, 0.32196787f, 0.34289113f, 0.36517414f, 0.3889052f, 0.41417846f, 0.44109413f, 0.4697589f, 0.50028646f, 0.53279793f, 0.5674221f, 0.6042964f, 0.64356697f, 0.6853896f, 0.72993004f, 0.777365f, 0.8278826f, 0.88168305f, 0.9389798f, 1.0f };
        Class56.anIntArray456 = new int[] { 256, 128, 86, 64 };
    }
}
