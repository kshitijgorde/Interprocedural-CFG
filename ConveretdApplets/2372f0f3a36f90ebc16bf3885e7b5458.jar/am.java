// 
// Decompiled by Procyon v0.5.30
// 

public class am extends aa
{
    public static float[] a;
    
    public Object a(final s s, final w w) {
        int n = 0;
        int n2 = -1;
        final av av = new av();
        av.a = w.d(5);
        for (int i = 0; i < av.a; ++i) {
            av.b[i] = w.d(4);
            if (n2 < av.b[i]) {
                n2 = av.b[i];
            }
        }
        for (int j = 0; j < n2 + 1; ++j) {
            av.c[j] = w.d(3) + 1;
            av.d[j] = w.d(2);
            if (av.d[j] < 0) {
                av.a();
                return null;
            }
            if (av.d[j] != 0) {
                av.e[j] = w.d(8);
            }
            if (av.e[j] < 0 || av.e[j] >= s.n) {
                av.a();
                return null;
            }
            for (int k = 0; k < 1 << av.d[j]; ++k) {
                av.f[j][k] = w.d(8) - 1;
                if (av.f[j][k] < -1 || av.f[j][k] >= s.n) {
                    av.a();
                    return null;
                }
            }
        }
        av.g = w.d(2) + 1;
        final int d = w.d(4);
        int l = 0;
        int n3 = 0;
        while (l < av.a) {
            for (n += av.c[av.b[l]]; n3 < n; ++n3) {
                final int[] h = av.h;
                final int n4 = n3 + 2;
                final int d2 = w.d(d);
                h[n4] = d2;
                final int n5 = d2;
                if (n5 < 0 || n5 >= 1 << d) {
                    av.a();
                    return null;
                }
            }
            ++l;
        }
        av.h[0] = 0;
        av.h[1] = 1 << d;
        return av;
    }
    
    public Object a(final u u, final af af, final Object o) {
        int f = 0;
        final int[] array = new int[65];
        final av i = (av)o;
        final a0 a0 = new a0();
        a0.i = i;
        a0.g = i.h[1];
        for (int j = 0; j < i.a; ++j) {
            f += i.c[i.b[j]];
        }
        f += 2;
        a0.f = f;
        for (int k = 0; k < f; ++k) {
            array[k] = k;
        }
        for (int l = 0; l < f - 1; ++l) {
            for (int n = l; n < f; ++n) {
                if (i.h[array[l]] > i.h[array[n]]) {
                    final int n2 = array[n];
                    array[n] = array[l];
                    array[l] = n2;
                }
            }
        }
        for (int n3 = 0; n3 < f; ++n3) {
            a0.b[n3] = array[n3];
        }
        for (int n4 = 0; n4 < f; ++n4) {
            a0.c[a0.b[n4]] = n4;
        }
        for (int n5 = 0; n5 < f; ++n5) {
            a0.a[n5] = i.h[a0.b[n5]];
        }
        switch (i.g) {
            case 1: {
                a0.h = 256;
                break;
            }
            case 2: {
                a0.h = 128;
                break;
            }
            case 3: {
                a0.h = 86;
                break;
            }
            case 4: {
                a0.h = 64;
                break;
            }
            default: {
                a0.h = -1;
                break;
            }
        }
        for (int n6 = 0; n6 < f - 2; ++n6) {
            int n7 = 0;
            int n8 = 1;
            int n9 = 0;
            int g = a0.g;
            final int n10 = i.h[n6 + 2];
            for (int n11 = 0; n11 < n6 + 2; ++n11) {
                final int n12 = i.h[n11];
                if (n12 > n9 && n12 < n10) {
                    n7 = n11;
                    n9 = n12;
                }
                if (n12 < g && n12 > n10) {
                    n8 = n11;
                    g = n12;
                }
            }
            a0.e[n6] = n7;
            a0.d[n6] = n8;
        }
        return a0;
    }
    
    public void a(final Object o) {
    }
    
    public Object a(final v v, final Object o, final Object o2) {
        final a0 a0 = (a0)o;
        final av i = a0.i;
        final ah[] u = v.k.u;
        if (v.b.d(1) == 1) {
            int[] array = null;
            if (o2 instanceof int[]) {
                array = (int[])o2;
            }
            if (array == null || array.length < a0.f) {
                array = new int[a0.f];
            }
            else {
                for (int j = 0; j < array.length; ++j) {
                    array[j] = 0;
                }
            }
            array[0] = v.b.d(ae.a(a0.h - 1, 0));
            array[1] = v.b.d(ae.a(a0.h - 1, 0));
            int k = 0;
            int n = 2;
            while (k < i.a) {
                final int n2 = i.b[k];
                final int n3 = i.c[n2];
                final int n4 = i.d[n2];
                final int n5 = 1 << n4;
                int a2 = 0;
                if (n4 != 0) {
                    a2 = u[i.e[n2]].a(v.b);
                    if (a2 == -1) {
                        return null;
                    }
                }
                for (int l = 0; l < n3; ++l) {
                    final int n6 = i.f[n2][a2 & n5 - 1];
                    a2 >>>= n4;
                    if (n6 >= 0) {
                        if ((array[n + l] = u[n6].a(v.b)) == -1) {
                            return null;
                        }
                    }
                    else {
                        array[n + l] = 0;
                    }
                }
                n += n3;
                ++k;
            }
            for (int n7 = 2; n7 < a0.f; ++n7) {
                final int a3 = a(i.h[a0.e[n7 - 2]], i.h[a0.d[n7 - 2]], array[a0.e[n7 - 2]], array[a0.d[n7 - 2]], i.h[n7]);
                final int n8 = a0.h - a3;
                final int n9 = a3;
                final int n10 = ((n8 < n9) ? n8 : n9) << 1;
                final int n11 = array[n7];
                if (n11 != 0) {
                    int n12;
                    if (n11 >= n10) {
                        if (n8 > n9) {
                            n12 = n11 - n9;
                        }
                        else {
                            n12 = -1 - (n11 - n8);
                        }
                    }
                    else if ((n11 & 0x1) != 0x0) {
                        n12 = -(n11 + 1 >>> 1);
                    }
                    else {
                        n12 = n11 >> 1;
                    }
                    array[n7] = n12 + a3;
                    final int[] array2 = array;
                    final int n13 = a0.e[n7 - 2];
                    array2[n13] &= 0x7FFF;
                    final int[] array3 = array;
                    final int n14 = a0.d[n7 - 2];
                    array3[n14] &= 0x7FFF;
                }
                else {
                    array[n7] = (a3 | 0x8000);
                }
            }
            return array;
        }
        return null;
    }
    
    private static int a(final int n, final int n2, int n3, int n4, final int n5) {
        n3 &= 0x7FFF;
        n4 &= 0x7FFF;
        final int n6 = n4 - n3;
        final int n7 = Math.abs(n6) * (n5 - n) / (n2 - n);
        if (n6 < 0) {
            return n3 - n7;
        }
        return n3 + n7;
    }
    
    public int a(final v v, final Object o, final Object o2, final float[] array) {
        final a0 a0 = (a0)o;
        final av i = a0.i;
        final int n = v.k.b.h[v.g] / 2;
        if (o2 != null) {
            final int[] array2 = (int[])o2;
            int n2 = 0;
            int n3 = 0;
            int n4 = array2[0] * i.g;
            for (int j = 1; j < a0.f; ++j) {
                final int n5 = a0.b[j];
                final int n6 = array2[n5] & 0x7FFF;
                if (n6 == array2[n5]) {
                    final int n7 = n6 * i.g;
                    n2 = i.h[n5];
                    a(n3, n2, n4, n7, array);
                    n3 = n2;
                    n4 = n7;
                }
            }
            for (int k = n2; k < n; ++k) {
                final int n8 = k;
                array[n8] *= array[k - 1];
            }
            return 1;
        }
        for (int l = 0; l < n; ++l) {
            array[l] = 0.0f;
        }
        return 0;
    }
    
    private static void a(int n, final int n2, final int n3, final int n4, final float[] array) {
        final int n5 = n4 - n3;
        final int n6 = n2 - n;
        final int abs = Math.abs(n5);
        final int n7 = n5 / n6;
        final int n8 = (n5 < 0) ? (n7 - 1) : (n7 + 1);
        int n9 = n3;
        int n10 = 0;
        final int n11 = abs - Math.abs(n7 * n6);
        array[n] *= am.a[n9];
        while (++n < n2) {
            n10 += n11;
            if (n10 >= n6) {
                n10 -= n6;
                n9 += n8;
            }
            else {
                n9 += n7;
            }
            array[n] *= am.a[n9];
        }
    }
    
    static {
        am.a = new float[] { 1.0649863E-7f, 1.1341951E-7f, 1.2079015E-7f, 1.2863978E-7f, 1.369995E-7f, 1.459025E-7f, 1.5538409E-7f, 1.6548181E-7f, 1.7623574E-7f, 1.8768856E-7f, 1.998856E-7f, 2.128753E-7f, 2.2670913E-7f, 2.4144197E-7f, 2.5713223E-7f, 2.7384212E-7f, 2.9163792E-7f, 3.1059022E-7f, 3.307741E-7f, 3.5226967E-7f, 3.7516213E-7f, 3.995423E-7f, 4.255068E-7f, 4.5315863E-7f, 4.8260745E-7f, 5.1397E-7f, 5.4737063E-7f, 5.829419E-7f, 6.208247E-7f, 6.611694E-7f, 7.041359E-7f, 7.4989464E-7f, 7.98627E-7f, 8.505263E-7f, 9.057983E-7f, 9.646621E-7f, 1.0273513E-6f, 1.0941144E-6f, 1.1652161E-6f, 1.2409384E-6f, 1.3215816E-6f, 1.4074654E-6f, 1.4989305E-6f, 1.5963394E-6f, 1.7000785E-6f, 1.8105592E-6f, 1.9282195E-6f, 2.053526E-6f, 2.1869757E-6f, 2.3290977E-6f, 2.4804558E-6f, 2.6416496E-6f, 2.813319E-6f, 2.9961443E-6f, 3.1908505E-6f, 3.39821E-6f, 3.619045E-6f, 3.8542307E-6f, 4.1047006E-6f, 4.371447E-6f, 4.6555283E-6f, 4.958071E-6f, 5.280274E-6f, 5.623416E-6f, 5.988857E-6f, 6.3780467E-6f, 6.7925284E-6f, 7.2339453E-6f, 7.704048E-6f, 8.2047E-6f, 8.737888E-6f, 9.305725E-6f, 9.910464E-6f, 1.0554501E-5f, 1.1240392E-5f, 1.1970856E-5f, 1.2748789E-5f, 1.3577278E-5f, 1.4459606E-5f, 1.5399271E-5f, 1.6400005E-5f, 1.7465769E-5f, 1.8600793E-5f, 1.9809577E-5f, 2.1096914E-5f, 2.2467912E-5f, 2.3928002E-5f, 2.5482977E-5f, 2.7139005E-5f, 2.890265E-5f, 3.078091E-5f, 3.2781227E-5f, 3.4911533E-5f, 3.718028E-5f, 3.9596467E-5f, 4.2169668E-5f, 4.491009E-5f, 4.7828602E-5f, 5.0936775E-5f, 5.424693E-5f, 5.7772202E-5f, 6.152657E-5f, 6.552491E-5f, 6.9783084E-5f, 7.4317984E-5f, 7.914758E-5f, 8.429104E-5f, 8.976875E-5f, 9.560242E-5f, 1.0181521E-4f, 1.0843174E-4f, 1.1547824E-4f, 1.2298267E-4f, 1.3097477E-4f, 1.3948625E-4f, 1.4855085E-4f, 1.5820454E-4f, 1.6848555E-4f, 1.7943469E-4f, 1.9109536E-4f, 2.0351382E-4f, 2.167393E-4f, 2.3082423E-4f, 2.4582449E-4f, 2.6179955E-4f, 2.7881275E-4f, 2.9693157E-4f, 3.1622787E-4f, 3.3677815E-4f, 3.5866388E-4f, 3.8197188E-4f, 4.0679457E-4f, 4.3323037E-4f, 4.613841E-4f, 4.913675E-4f, 5.2329927E-4f, 5.573062E-4f, 5.935231E-4f, 6.320936E-4f, 6.731706E-4f, 7.16917E-4f, 7.635063E-4f, 8.1312325E-4f, 8.6596457E-4f, 9.2223985E-4f, 9.821722E-4f, 0.0010459992f, 0.0011139743f, 0.0011863665f, 0.0012634633f, 0.0013455702f, 0.0014330129f, 0.0015261382f, 0.0016253153f, 0.0017309374f, 0.0018434235f, 0.0019632196f, 0.0020908006f, 0.0022266726f, 0.0023713743f, 0.0025254795f, 0.0026895993f, 0.0028643848f, 0.0030505287f, 0.003248769f, 0.0034598925f, 0.0036847359f, 0.0039241905f, 0.0041792067f, 0.004450795f, 0.004740033f, 0.005048067f, 0.0053761187f, 0.005725489f, 0.0060975635f, 0.0064938175f, 0.0069158226f, 0.0073652514f, 0.007843887f, 0.008353627f, 0.008896492f, 0.009474637f, 0.010090352f, 0.01074608f, 0.011444421f, 0.012188144f, 0.012980198f, 0.013823725f, 0.014722068f, 0.015678791f, 0.016697686f, 0.017782796f, 0.018938422f, 0.020169148f, 0.021479854f, 0.022875736f, 0.02436233f, 0.025945531f, 0.027631618f, 0.029427277f, 0.031339627f, 0.03337625f, 0.035545226f, 0.037855156f, 0.0403152f, 0.042935107f, 0.045725275f, 0.048696756f, 0.05186135f, 0.05523159f, 0.05882085f, 0.062643364f, 0.06671428f, 0.07104975f, 0.075666964f, 0.08058423f, 0.08582105f, 0.09139818f, 0.097337745f, 0.1036633f, 0.11039993f, 0.11757434f, 0.12521498f, 0.13335215f, 0.14201812f, 0.15124726f, 0.16107617f, 0.1715438f, 0.18269168f, 0.19456401f, 0.20720787f, 0.22067343f, 0.23501402f, 0.25028655f, 0.26655158f, 0.28387362f, 0.3023213f, 0.32196787f, 0.34289113f, 0.36517414f, 0.3889052f, 0.41417846f, 0.44109413f, 0.4697589f, 0.50028646f, 0.53279793f, 0.5674221f, 0.6042964f, 0.64356697f, 0.6853896f, 0.72993004f, 0.777365f, 0.8278826f, 0.88168305f, 0.9389798f, 1.0f };
    }
}
