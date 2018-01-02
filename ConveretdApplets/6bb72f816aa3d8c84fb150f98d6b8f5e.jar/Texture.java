// 
// Decompiled by Procyon v0.5.30
// 

final class Texture extends DrawingArea
{
    public static final int anInt1459 = -477;
    public static boolean lowMem;
    static boolean aBoolean1462;
    private static boolean aBoolean1463;
    public static boolean aBoolean1464;
    public static int anInt1465;
    public static int textureInt1;
    public static int textureInt2;
    private static int[] anIntArray1468;
    public static final int[] anIntArray1469;
    public static int[] anIntArray1470;
    public static int[] anIntArray1471;
    public static int[] anIntArray1472;
    private static int anInt1473;
    public static Background[] aBackgroundArray1474s;
    private static boolean[] aBooleanArray1475;
    private static int[] anIntArray1476;
    private static int anInt1477;
    private static int[][] anIntArrayArray1478;
    private static int[][] anIntArrayArray1479;
    public static int[] anIntArray1480;
    public static int anInt1481;
    public static int[] anIntArray1482;
    private static int[][] anIntArrayArray1483;
    
    public static void nullLoader() {
        Texture.anIntArray1468 = null;
        Texture.anIntArray1468 = null;
        Texture.anIntArray1470 = null;
        Texture.anIntArray1471 = null;
        Texture.anIntArray1472 = null;
        Texture.aBackgroundArray1474s = null;
        Texture.aBooleanArray1475 = null;
        Texture.anIntArray1476 = null;
        Texture.anIntArrayArray1478 = null;
        Texture.anIntArrayArray1479 = null;
        Texture.anIntArray1480 = null;
        Texture.anIntArray1482 = null;
        Texture.anIntArrayArray1483 = null;
    }
    
    public static void method364() {
        Texture.anIntArray1472 = new int[DrawingArea.height];
        for (int i = 0; i < DrawingArea.height; ++i) {
            Texture.anIntArray1472[i] = DrawingArea.width * i;
        }
        Texture.textureInt1 = DrawingArea.width / 2;
        Texture.textureInt2 = DrawingArea.height / 2;
    }
    
    public static void method365(final int n, final int n2) {
        Texture.anIntArray1472 = new int[n2];
        for (int i = 0; i < n2; ++i) {
            Texture.anIntArray1472[i] = n * i;
        }
        Texture.textureInt1 = n / 2;
        Texture.textureInt2 = n2 / 2;
    }
    
    public static void method366() {
        Texture.anIntArrayArray1478 = null;
        for (int i = 0; i < 50; ++i) {
            Texture.anIntArrayArray1479[i] = null;
        }
    }
    
    public static void method367() {
        if (Texture.anIntArrayArray1478 == null) {
            Texture.anInt1477 = 20;
            if (Texture.lowMem) {
                Texture.anIntArrayArray1478 = new int[Texture.anInt1477][16384];
            }
            else {
                Texture.anIntArrayArray1478 = new int[Texture.anInt1477][65536];
            }
            for (int i = 0; i < 50; ++i) {
                Texture.anIntArrayArray1479[i] = null;
            }
        }
    }
    
    public static void method368(final StreamLoader streamLoader) {
        Texture.anInt1473 = 0;
        for (int i = 0; i < 50; ++i) {
            try {
                Texture.aBackgroundArray1474s[i] = new Background(streamLoader, String.valueOf(i), 0);
                if (Texture.lowMem && Texture.aBackgroundArray1474s[i].anInt1456 == 128) {
                    Texture.aBackgroundArray1474s[i].method356();
                }
                else {
                    Texture.aBackgroundArray1474s[i].method357();
                }
                ++Texture.anInt1473;
            }
            catch (Exception ex) {}
        }
    }
    
    public static int method369(final int n) {
        if (Texture.anIntArray1476[n] != 0) {
            return Texture.anIntArray1476[n];
        }
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        final int length = Texture.anIntArrayArray1483[n].length;
        for (int i = 0; i < length; ++i) {
            n2 += (Texture.anIntArrayArray1483[n][i] >> 16 & 0xFF);
            n3 += (Texture.anIntArrayArray1483[n][i] >> 8 & 0xFF);
            n4 += (Texture.anIntArrayArray1483[n][i] & 0xFF);
        }
        int method373 = method373((n2 / length << 16) + (n3 / length << 8) + n4 / length, 1.4);
        if (method373 == 0) {
            method373 = 1;
        }
        return Texture.anIntArray1476[n] = method373;
    }
    
    public static void method370(final int n) {
        if (Texture.anIntArrayArray1479[n] == null) {
            return;
        }
        Texture.anIntArrayArray1478[Texture.anInt1477++] = Texture.anIntArrayArray1479[n];
        Texture.anIntArrayArray1479[n] = null;
    }
    
    private static int[] method371(int n) {
        if (n == 1) {
            n = 24;
        }
        Texture.anIntArray1480[n] = Texture.anInt1481++;
        if (Texture.anIntArrayArray1479[n] != null) {
            return Texture.anIntArrayArray1479[n];
        }
        int[] array;
        if (Texture.anInt1477 > 0) {
            array = Texture.anIntArrayArray1478[--Texture.anInt1477];
            Texture.anIntArrayArray1478[Texture.anInt1477] = null;
        }
        else {
            int n2 = 0;
            int n3 = -1;
            for (int i = 0; i < Texture.anInt1473; ++i) {
                if (Texture.anIntArrayArray1479[i] != null && (Texture.anIntArray1480[i] < n2 || n3 == -1)) {
                    n2 = Texture.anIntArray1480[i];
                    n3 = i;
                }
            }
            array = Texture.anIntArrayArray1479[n3];
            Texture.anIntArrayArray1479[n3] = null;
        }
        Texture.anIntArrayArray1479[n] = array;
        final Background background = Texture.aBackgroundArray1474s[n];
        final int[] array2 = Texture.anIntArrayArray1483[n];
        if (Texture.lowMem) {
            Texture.aBooleanArray1475[n] = false;
            for (int j = 0; j < 4096; ++j) {
                final int[] array3 = array;
                final int n4 = j;
                final int n5 = array2[background.aByteArray1450[j]] & 0xF8F8FF;
                array3[n4] = n5;
                final int n6 = n5;
                if (n6 == 0) {
                    Texture.aBooleanArray1475[n] = true;
                }
                array[4096 + j] = (n6 - (n6 >>> 3) & 0xF8F8FF);
                array[8192 + j] = (n6 - (n6 >>> 2) & 0xF8F8FF);
                array[12288 + j] = (n6 - (n6 >>> 2) - (n6 >>> 3) & 0xF8F8FF);
            }
        }
        else {
            if (background.anInt1452 == 64) {
                for (int k = 0; k < 128; ++k) {
                    for (int l = 0; l < 128; ++l) {
                        array[l + (k << 7)] = array2[background.aByteArray1450[(l >> 1) + (k >> 1 << 6)]];
                    }
                }
            }
            else {
                for (int n7 = 0; n7 < 16384; ++n7) {
                    array[n7] = array2[background.aByteArray1450[n7]];
                }
            }
            Texture.aBooleanArray1475[n] = false;
            for (int n8 = 0; n8 < 16384; ++n8) {
                final int[] array4 = array;
                final int n9 = n8;
                array4[n9] &= 0xF8F8FF;
                final int n10 = array[n8];
                if (n10 == 0) {
                    Texture.aBooleanArray1475[n] = true;
                }
                array[16384 + n8] = (n10 - (n10 >>> 3) & 0xF8F8FF);
                array[32768 + n8] = (n10 - (n10 >>> 2) & 0xF8F8FF);
                array[49152 + n8] = (n10 - (n10 >>> 2) - (n10 >>> 3) & 0xF8F8FF);
            }
        }
        return array;
    }
    
    public static void method372(double n) {
        n += Math.random() * 0.03 - 0.015;
        int n2 = 0;
        for (int i = 0; i < 512; ++i) {
            final double n3 = i / 8 / 64.0 + 0.0078125;
            final double n4 = (i & 0x7) / 8.0 + 0.0625;
            for (int j = 0; j < 128; ++j) {
                double n8;
                double n7;
                double n6;
                final double n5 = n6 = (n7 = (n8 = j / 128.0));
                if (n4 != 0.0) {
                    double n9;
                    if (n5 < 0.5) {
                        n9 = n5 * (1.0 + n4);
                    }
                    else {
                        n9 = n5 + n4 - n5 * n4;
                    }
                    final double n10 = 2.0 * n5 - n9;
                    double n11 = n3 + 0.3333333333333333;
                    if (n11 > 1.0) {
                        --n11;
                    }
                    final double n12 = n3;
                    double n13 = n3 - 0.3333333333333333;
                    if (n13 < 0.0) {
                        ++n13;
                    }
                    if (6.0 * n11 < 1.0) {
                        n8 = n10 + (n9 - n10) * 6.0 * n11;
                    }
                    else if (2.0 * n11 < 1.0) {
                        n8 = n9;
                    }
                    else if (3.0 * n11 < 2.0) {
                        n8 = n10 + (n9 - n10) * (0.6666666666666666 - n11) * 6.0;
                    }
                    else {
                        n8 = n10;
                    }
                    if (6.0 * n12 < 1.0) {
                        n7 = n10 + (n9 - n10) * 6.0 * n12;
                    }
                    else if (2.0 * n12 < 1.0) {
                        n7 = n9;
                    }
                    else if (3.0 * n12 < 2.0) {
                        n7 = n10 + (n9 - n10) * (0.6666666666666666 - n12) * 6.0;
                    }
                    else {
                        n7 = n10;
                    }
                    if (6.0 * n13 < 1.0) {
                        n6 = n10 + (n9 - n10) * 6.0 * n13;
                    }
                    else if (2.0 * n13 < 1.0) {
                        n6 = n9;
                    }
                    else if (3.0 * n13 < 2.0) {
                        n6 = n10 + (n9 - n10) * (0.6666666666666666 - n13) * 6.0;
                    }
                    else {
                        n6 = n10;
                    }
                }
                int method373 = method373(((int)(n8 * 256.0) << 16) + ((int)(n7 * 256.0) << 8) + (int)(n6 * 256.0), n);
                if (method373 == 0) {
                    method373 = 1;
                }
                Texture.anIntArray1482[n2++] = method373;
            }
        }
        for (int k = 0; k < 50; ++k) {
            if (Texture.aBackgroundArray1474s[k] != null) {
                final int[] anIntArray1451 = Texture.aBackgroundArray1474s[k].anIntArray1451;
                Texture.anIntArrayArray1483[k] = new int[anIntArray1451.length];
                for (int l = 0; l < anIntArray1451.length; ++l) {
                    Texture.anIntArrayArray1483[k][l] = method373(anIntArray1451[l], n);
                    if ((Texture.anIntArrayArray1483[k][l] & 0xF8F8FF) == 0x0 && l != 0) {
                        Texture.anIntArrayArray1483[k][l] = 1;
                    }
                }
            }
        }
        for (int n14 = 0; n14 < 50; ++n14) {
            method370(n14);
        }
    }
    
    private static int method373(final int n, final double n2) {
        return ((int)(Math.pow((n >> 16) / 256.0, n2) * 256.0) << 16) + ((int)(Math.pow((n >> 8 & 0xFF) / 256.0, n2) * 256.0) << 8) + (int)(Math.pow((n & 0xFF) / 256.0, n2) * 256.0);
    }
    
    public static void method374(int n, int n2, int n3, int n4, int n5, int n6, int n7, int n8, int n9) {
        int n10 = 0;
        int n11 = 0;
        if (n2 != n) {
            n10 = (n5 - n4 << 16) / (n2 - n);
            n11 = (n8 - n7 << 15) / (n2 - n);
        }
        int n12 = 0;
        int n13 = 0;
        if (n3 != n2) {
            n12 = (n6 - n5 << 16) / (n3 - n2);
            n13 = (n9 - n8 << 15) / (n3 - n2);
        }
        int n14 = 0;
        int n15 = 0;
        if (n3 != n) {
            n14 = (n4 - n6 << 16) / (n - n3);
            n15 = (n7 - n9 << 15) / (n - n3);
        }
        if (n <= n2 && n <= n3) {
            if (n >= DrawingArea.bottomY) {
                return;
            }
            if (n2 > DrawingArea.bottomY) {
                n2 = DrawingArea.bottomY;
            }
            if (n3 > DrawingArea.bottomY) {
                n3 = DrawingArea.bottomY;
            }
            if (n2 < n3) {
                n4 = (n6 = n4 << 16);
                n7 = (n9 = n7 << 15);
                if (n < 0) {
                    n6 -= n14 * n;
                    n4 -= n10 * n;
                    n9 -= n15 * n;
                    n7 -= n11 * n;
                    n = 0;
                }
                n5 <<= 16;
                n8 <<= 15;
                if (n2 < 0) {
                    n5 -= n12 * n2;
                    n8 -= n13 * n2;
                    n2 = 0;
                }
                if ((n != n2 && n14 < n10) || (n == n2 && n14 > n12)) {
                    n3 -= n2;
                    n2 -= n;
                    n = Texture.anIntArray1472[n];
                    while (--n2 >= 0) {
                        method375(DrawingArea.pixels, n, n6 >> 16, n4 >> 16, n9 >> 7, n7 >> 7);
                        n6 += n14;
                        n4 += n10;
                        n9 += n15;
                        n7 += n11;
                        n += DrawingArea.width;
                    }
                    while (--n3 >= 0) {
                        method375(DrawingArea.pixels, n, n6 >> 16, n5 >> 16, n9 >> 7, n8 >> 7);
                        n6 += n14;
                        n5 += n12;
                        n9 += n15;
                        n8 += n13;
                        n += DrawingArea.width;
                    }
                    return;
                }
                n3 -= n2;
                n2 -= n;
                n = Texture.anIntArray1472[n];
                while (--n2 >= 0) {
                    method375(DrawingArea.pixels, n, n4 >> 16, n6 >> 16, n7 >> 7, n9 >> 7);
                    n6 += n14;
                    n4 += n10;
                    n9 += n15;
                    n7 += n11;
                    n += DrawingArea.width;
                }
                while (--n3 >= 0) {
                    method375(DrawingArea.pixels, n, n5 >> 16, n6 >> 16, n8 >> 7, n9 >> 7);
                    n6 += n14;
                    n5 += n12;
                    n9 += n15;
                    n8 += n13;
                    n += DrawingArea.width;
                }
            }
            else {
                n4 = (n5 = n4 << 16);
                n7 = (n8 = n7 << 15);
                if (n < 0) {
                    n5 -= n14 * n;
                    n4 -= n10 * n;
                    n8 -= n15 * n;
                    n7 -= n11 * n;
                    n = 0;
                }
                n6 <<= 16;
                n9 <<= 15;
                if (n3 < 0) {
                    n6 -= n12 * n3;
                    n9 -= n13 * n3;
                    n3 = 0;
                }
                if ((n != n3 && n14 < n10) || (n == n3 && n12 > n10)) {
                    n2 -= n3;
                    n3 -= n;
                    n = Texture.anIntArray1472[n];
                    while (--n3 >= 0) {
                        method375(DrawingArea.pixels, n, n5 >> 16, n4 >> 16, n8 >> 7, n7 >> 7);
                        n5 += n14;
                        n4 += n10;
                        n8 += n15;
                        n7 += n11;
                        n += DrawingArea.width;
                    }
                    while (--n2 >= 0) {
                        method375(DrawingArea.pixels, n, n6 >> 16, n4 >> 16, n9 >> 7, n7 >> 7);
                        n6 += n12;
                        n4 += n10;
                        n9 += n13;
                        n7 += n11;
                        n += DrawingArea.width;
                    }
                    return;
                }
                n2 -= n3;
                n3 -= n;
                n = Texture.anIntArray1472[n];
                while (--n3 >= 0) {
                    method375(DrawingArea.pixels, n, n4 >> 16, n5 >> 16, n7 >> 7, n8 >> 7);
                    n5 += n14;
                    n4 += n10;
                    n8 += n15;
                    n7 += n11;
                    n += DrawingArea.width;
                }
                while (--n2 >= 0) {
                    method375(DrawingArea.pixels, n, n4 >> 16, n6 >> 16, n7 >> 7, n9 >> 7);
                    n6 += n12;
                    n4 += n10;
                    n9 += n13;
                    n7 += n11;
                    n += DrawingArea.width;
                }
            }
        }
        else if (n2 <= n3) {
            if (n2 >= DrawingArea.bottomY) {
                return;
            }
            if (n3 > DrawingArea.bottomY) {
                n3 = DrawingArea.bottomY;
            }
            if (n > DrawingArea.bottomY) {
                n = DrawingArea.bottomY;
            }
            if (n3 < n) {
                n5 = (n4 = n5 << 16);
                n8 = (n7 = n8 << 15);
                if (n2 < 0) {
                    n4 -= n10 * n2;
                    n5 -= n12 * n2;
                    n7 -= n11 * n2;
                    n8 -= n13 * n2;
                    n2 = 0;
                }
                n6 <<= 16;
                n9 <<= 15;
                if (n3 < 0) {
                    n6 -= n14 * n3;
                    n9 -= n15 * n3;
                    n3 = 0;
                }
                if ((n2 != n3 && n10 < n12) || (n2 == n3 && n10 > n14)) {
                    n -= n3;
                    n3 -= n2;
                    n2 = Texture.anIntArray1472[n2];
                    while (--n3 >= 0) {
                        method375(DrawingArea.pixels, n2, n4 >> 16, n5 >> 16, n7 >> 7, n8 >> 7);
                        n4 += n10;
                        n5 += n12;
                        n7 += n11;
                        n8 += n13;
                        n2 += DrawingArea.width;
                    }
                    while (--n >= 0) {
                        method375(DrawingArea.pixels, n2, n4 >> 16, n6 >> 16, n7 >> 7, n9 >> 7);
                        n4 += n10;
                        n6 += n14;
                        n7 += n11;
                        n9 += n15;
                        n2 += DrawingArea.width;
                    }
                    return;
                }
                n -= n3;
                n3 -= n2;
                n2 = Texture.anIntArray1472[n2];
                while (--n3 >= 0) {
                    method375(DrawingArea.pixels, n2, n5 >> 16, n4 >> 16, n8 >> 7, n7 >> 7);
                    n4 += n10;
                    n5 += n12;
                    n7 += n11;
                    n8 += n13;
                    n2 += DrawingArea.width;
                }
                while (--n >= 0) {
                    method375(DrawingArea.pixels, n2, n6 >> 16, n4 >> 16, n9 >> 7, n7 >> 7);
                    n4 += n10;
                    n6 += n14;
                    n7 += n11;
                    n9 += n15;
                    n2 += DrawingArea.width;
                }
            }
            else {
                n5 = (n6 = n5 << 16);
                n8 = (n9 = n8 << 15);
                if (n2 < 0) {
                    n6 -= n10 * n2;
                    n5 -= n12 * n2;
                    n9 -= n11 * n2;
                    n8 -= n13 * n2;
                    n2 = 0;
                }
                n4 <<= 16;
                n7 <<= 15;
                if (n < 0) {
                    n4 -= n14 * n;
                    n7 -= n15 * n;
                    n = 0;
                }
                if (n10 < n12) {
                    n3 -= n;
                    n -= n2;
                    n2 = Texture.anIntArray1472[n2];
                    while (--n >= 0) {
                        method375(DrawingArea.pixels, n2, n6 >> 16, n5 >> 16, n9 >> 7, n8 >> 7);
                        n6 += n10;
                        n5 += n12;
                        n9 += n11;
                        n8 += n13;
                        n2 += DrawingArea.width;
                    }
                    while (--n3 >= 0) {
                        method375(DrawingArea.pixels, n2, n4 >> 16, n5 >> 16, n7 >> 7, n8 >> 7);
                        n4 += n14;
                        n5 += n12;
                        n7 += n15;
                        n8 += n13;
                        n2 += DrawingArea.width;
                    }
                    return;
                }
                n3 -= n;
                n -= n2;
                n2 = Texture.anIntArray1472[n2];
                while (--n >= 0) {
                    method375(DrawingArea.pixels, n2, n5 >> 16, n6 >> 16, n8 >> 7, n9 >> 7);
                    n6 += n10;
                    n5 += n12;
                    n9 += n11;
                    n8 += n13;
                    n2 += DrawingArea.width;
                }
                while (--n3 >= 0) {
                    method375(DrawingArea.pixels, n2, n5 >> 16, n4 >> 16, n8 >> 7, n7 >> 7);
                    n4 += n14;
                    n5 += n12;
                    n7 += n15;
                    n8 += n13;
                    n2 += DrawingArea.width;
                }
            }
        }
        else {
            if (n3 >= DrawingArea.bottomY) {
                return;
            }
            if (n > DrawingArea.bottomY) {
                n = DrawingArea.bottomY;
            }
            if (n2 > DrawingArea.bottomY) {
                n2 = DrawingArea.bottomY;
            }
            if (n < n2) {
                n6 = (n5 = n6 << 16);
                n9 = (n8 = n9 << 15);
                if (n3 < 0) {
                    n5 -= n12 * n3;
                    n6 -= n14 * n3;
                    n8 -= n13 * n3;
                    n9 -= n15 * n3;
                    n3 = 0;
                }
                n4 <<= 16;
                n7 <<= 15;
                if (n < 0) {
                    n4 -= n10 * n;
                    n7 -= n11 * n;
                    n = 0;
                }
                if (n12 < n14) {
                    n2 -= n;
                    n -= n3;
                    n3 = Texture.anIntArray1472[n3];
                    while (--n >= 0) {
                        method375(DrawingArea.pixels, n3, n5 >> 16, n6 >> 16, n8 >> 7, n9 >> 7);
                        n5 += n12;
                        n6 += n14;
                        n8 += n13;
                        n9 += n15;
                        n3 += DrawingArea.width;
                    }
                    while (--n2 >= 0) {
                        method375(DrawingArea.pixels, n3, n5 >> 16, n4 >> 16, n8 >> 7, n7 >> 7);
                        n5 += n12;
                        n4 += n10;
                        n8 += n13;
                        n7 += n11;
                        n3 += DrawingArea.width;
                    }
                    return;
                }
                n2 -= n;
                n -= n3;
                n3 = Texture.anIntArray1472[n3];
                while (--n >= 0) {
                    method375(DrawingArea.pixels, n3, n6 >> 16, n5 >> 16, n9 >> 7, n8 >> 7);
                    n5 += n12;
                    n6 += n14;
                    n8 += n13;
                    n9 += n15;
                    n3 += DrawingArea.width;
                }
                while (--n2 >= 0) {
                    method375(DrawingArea.pixels, n3, n4 >> 16, n5 >> 16, n7 >> 7, n8 >> 7);
                    n5 += n12;
                    n4 += n10;
                    n8 += n13;
                    n7 += n11;
                    n3 += DrawingArea.width;
                }
            }
            else {
                n6 = (n4 = n6 << 16);
                n9 = (n7 = n9 << 15);
                if (n3 < 0) {
                    n4 -= n12 * n3;
                    n6 -= n14 * n3;
                    n7 -= n13 * n3;
                    n9 -= n15 * n3;
                    n3 = 0;
                }
                n5 <<= 16;
                n8 <<= 15;
                if (n2 < 0) {
                    n5 -= n10 * n2;
                    n8 -= n11 * n2;
                    n2 = 0;
                }
                if (n12 < n14) {
                    n -= n2;
                    n2 -= n3;
                    n3 = Texture.anIntArray1472[n3];
                    while (--n2 >= 0) {
                        method375(DrawingArea.pixels, n3, n4 >> 16, n6 >> 16, n7 >> 7, n9 >> 7);
                        n4 += n12;
                        n6 += n14;
                        n7 += n13;
                        n9 += n15;
                        n3 += DrawingArea.width;
                    }
                    while (--n >= 0) {
                        method375(DrawingArea.pixels, n3, n5 >> 16, n6 >> 16, n8 >> 7, n9 >> 7);
                        n5 += n10;
                        n6 += n14;
                        n8 += n11;
                        n9 += n15;
                        n3 += DrawingArea.width;
                    }
                    return;
                }
                n -= n2;
                n2 -= n3;
                n3 = Texture.anIntArray1472[n3];
                while (--n2 >= 0) {
                    method375(DrawingArea.pixels, n3, n6 >> 16, n4 >> 16, n9 >> 7, n7 >> 7);
                    n4 += n12;
                    n6 += n14;
                    n7 += n13;
                    n9 += n15;
                    n3 += DrawingArea.width;
                }
                while (--n >= 0) {
                    method375(DrawingArea.pixels, n3, n6 >> 16, n5 >> 16, n9 >> 7, n8 >> 7);
                    n5 += n10;
                    n6 += n14;
                    n8 += n11;
                    n9 += n15;
                    n3 += DrawingArea.width;
                }
            }
        }
    }
    
    public static void method375(final int[] array, int n, int n2, int centerX, int n3, final int n4) {
        final int n5 = 0;
        if (Texture.aBoolean1462) {
            if (centerX > DrawingArea.centerX) {
                centerX = DrawingArea.centerX;
            }
            if (n2 < 0) {
                n3 -= n2 * n5;
                n2 = 0;
            }
        }
        if (n2 < centerX) {
            n += n2;
            n3 += n5 * n2;
            if (Texture.aBoolean1464) {
                int n6 = centerX - n2 >> 2;
                int n7;
                if (n6 > 0) {
                    n7 = (n4 - n3) * Texture.anIntArray1468[n6] >> 15;
                }
                else {
                    n7 = 0;
                }
                if (Texture.anInt1465 == 0) {
                    if (n6 > 0) {
                        do {
                            final int n8 = Texture.anIntArray1482[n3 >> 8];
                            n3 += n7;
                            array[n++] = n8;
                            array[n++] = n8;
                            array[n++] = n8;
                            array[n++] = n8;
                        } while (--n6 > 0);
                    }
                    int n9 = centerX - n2 & 0x3;
                    if (n9 > 0) {
                        final int n10 = Texture.anIntArray1482[n3 >> 8];
                        do {
                            array[n++] = n10;
                        } while (--n9 > 0);
                    }
                }
                else {
                    final int anInt1465 = Texture.anInt1465;
                    final int n11 = 256 - Texture.anInt1465;
                    if (n6 > 0) {
                        do {
                            final int n12 = Texture.anIntArray1482[n3 >> 8];
                            n3 += n7;
                            final int n13 = ((n12 & 0xFF00FF) * n11 >> 8 & 0xFF00FF) + ((n12 & 0xFF00) * n11 >> 8 & 0xFF00);
                            final int n14 = array[n];
                            array[n++] = n13 + ((n14 & 0xFF00FF) * anInt1465 >> 8 & 0xFF00FF) + ((n14 & 0xFF00) * anInt1465 >> 8 & 0xFF00);
                            final int n15 = array[n];
                            array[n++] = n13 + ((n15 & 0xFF00FF) * anInt1465 >> 8 & 0xFF00FF) + ((n15 & 0xFF00) * anInt1465 >> 8 & 0xFF00);
                            final int n16 = array[n];
                            array[n++] = n13 + ((n16 & 0xFF00FF) * anInt1465 >> 8 & 0xFF00FF) + ((n16 & 0xFF00) * anInt1465 >> 8 & 0xFF00);
                            final int n17 = array[n];
                            array[n++] = n13 + ((n17 & 0xFF00FF) * anInt1465 >> 8 & 0xFF00FF) + ((n17 & 0xFF00) * anInt1465 >> 8 & 0xFF00);
                        } while (--n6 > 0);
                    }
                    int n18 = centerX - n2 & 0x3;
                    if (n18 > 0) {
                        final int n19 = Texture.anIntArray1482[n3 >> 8];
                        final int n20 = ((n19 & 0xFF00FF) * n11 >> 8 & 0xFF00FF) + ((n19 & 0xFF00) * n11 >> 8 & 0xFF00);
                        do {
                            final int n21 = array[n];
                            array[n++] = n20 + ((n21 & 0xFF00FF) * anInt1465 >> 8 & 0xFF00FF) + ((n21 & 0xFF00) * anInt1465 >> 8 & 0xFF00);
                        } while (--n18 > 0);
                    }
                }
            }
            else {
                final int n22 = (n4 - n3) / (centerX - n2);
                int n23 = centerX - n2;
                if (Texture.anInt1465 == 0) {
                    do {
                        array[n++] = Texture.anIntArray1482[n3 >> 8];
                        n3 += n22;
                    } while (--n23 > 0);
                }
                else {
                    final int anInt1466 = Texture.anInt1465;
                    final int n24 = 256 - Texture.anInt1465;
                    do {
                        final int n25 = Texture.anIntArray1482[n3 >> 8];
                        n3 += n22;
                        final int n26 = ((n25 & 0xFF00FF) * n24 >> 8 & 0xFF00FF) + ((n25 & 0xFF00) * n24 >> 8 & 0xFF00);
                        final int n27 = array[n];
                        array[n++] = n26 + ((n27 & 0xFF00FF) * anInt1466 >> 8 & 0xFF00FF) + ((n27 & 0xFF00) * anInt1466 >> 8 & 0xFF00);
                    } while (--n23 > 0);
                }
            }
        }
    }
    
    public static void method376(int n, int n2, int n3, int n4, int n5, int n6, final int n7) {
        int n8 = 0;
        if (n2 != n) {
            n8 = (n5 - n4 << 16) / (n2 - n);
        }
        int n9 = 0;
        if (n3 != n2) {
            n9 = (n6 - n5 << 16) / (n3 - n2);
        }
        int n10 = 0;
        if (n3 != n) {
            n10 = (n4 - n6 << 16) / (n - n3);
        }
        if (n <= n2 && n <= n3) {
            if (n >= DrawingArea.bottomY) {
                return;
            }
            if (n2 > DrawingArea.bottomY) {
                n2 = DrawingArea.bottomY;
            }
            if (n3 > DrawingArea.bottomY) {
                n3 = DrawingArea.bottomY;
            }
            if (n2 < n3) {
                n4 = (n6 = n4 << 16);
                if (n < 0) {
                    n6 -= n10 * n;
                    n4 -= n8 * n;
                    n = 0;
                }
                n5 <<= 16;
                if (n2 < 0) {
                    n5 -= n9 * n2;
                    n2 = 0;
                }
                if ((n != n2 && n10 < n8) || (n == n2 && n10 > n9)) {
                    n3 -= n2;
                    n2 -= n;
                    n = Texture.anIntArray1472[n];
                    while (--n2 >= 0) {
                        method377(DrawingArea.pixels, n, n7, n6 >> 16, n4 >> 16);
                        n6 += n10;
                        n4 += n8;
                        n += DrawingArea.width;
                    }
                    while (--n3 >= 0) {
                        method377(DrawingArea.pixels, n, n7, n6 >> 16, n5 >> 16);
                        n6 += n10;
                        n5 += n9;
                        n += DrawingArea.width;
                    }
                    return;
                }
                n3 -= n2;
                n2 -= n;
                n = Texture.anIntArray1472[n];
                while (--n2 >= 0) {
                    method377(DrawingArea.pixels, n, n7, n4 >> 16, n6 >> 16);
                    n6 += n10;
                    n4 += n8;
                    n += DrawingArea.width;
                }
                while (--n3 >= 0) {
                    method377(DrawingArea.pixels, n, n7, n5 >> 16, n6 >> 16);
                    n6 += n10;
                    n5 += n9;
                    n += DrawingArea.width;
                }
            }
            else {
                n4 = (n5 = n4 << 16);
                if (n < 0) {
                    n5 -= n10 * n;
                    n4 -= n8 * n;
                    n = 0;
                }
                n6 <<= 16;
                if (n3 < 0) {
                    n6 -= n9 * n3;
                    n3 = 0;
                }
                if ((n != n3 && n10 < n8) || (n == n3 && n9 > n8)) {
                    n2 -= n3;
                    n3 -= n;
                    n = Texture.anIntArray1472[n];
                    while (--n3 >= 0) {
                        method377(DrawingArea.pixels, n, n7, n5 >> 16, n4 >> 16);
                        n5 += n10;
                        n4 += n8;
                        n += DrawingArea.width;
                    }
                    while (--n2 >= 0) {
                        method377(DrawingArea.pixels, n, n7, n6 >> 16, n4 >> 16);
                        n6 += n9;
                        n4 += n8;
                        n += DrawingArea.width;
                    }
                    return;
                }
                n2 -= n3;
                n3 -= n;
                n = Texture.anIntArray1472[n];
                while (--n3 >= 0) {
                    method377(DrawingArea.pixels, n, n7, n4 >> 16, n5 >> 16);
                    n5 += n10;
                    n4 += n8;
                    n += DrawingArea.width;
                }
                while (--n2 >= 0) {
                    method377(DrawingArea.pixels, n, n7, n4 >> 16, n6 >> 16);
                    n6 += n9;
                    n4 += n8;
                    n += DrawingArea.width;
                }
            }
        }
        else if (n2 <= n3) {
            if (n2 >= DrawingArea.bottomY) {
                return;
            }
            if (n3 > DrawingArea.bottomY) {
                n3 = DrawingArea.bottomY;
            }
            if (n > DrawingArea.bottomY) {
                n = DrawingArea.bottomY;
            }
            if (n3 < n) {
                n5 = (n4 = n5 << 16);
                if (n2 < 0) {
                    n4 -= n8 * n2;
                    n5 -= n9 * n2;
                    n2 = 0;
                }
                n6 <<= 16;
                if (n3 < 0) {
                    n6 -= n10 * n3;
                    n3 = 0;
                }
                if ((n2 != n3 && n8 < n9) || (n2 == n3 && n8 > n10)) {
                    n -= n3;
                    n3 -= n2;
                    n2 = Texture.anIntArray1472[n2];
                    while (--n3 >= 0) {
                        method377(DrawingArea.pixels, n2, n7, n4 >> 16, n5 >> 16);
                        n4 += n8;
                        n5 += n9;
                        n2 += DrawingArea.width;
                    }
                    while (--n >= 0) {
                        method377(DrawingArea.pixels, n2, n7, n4 >> 16, n6 >> 16);
                        n4 += n8;
                        n6 += n10;
                        n2 += DrawingArea.width;
                    }
                    return;
                }
                n -= n3;
                n3 -= n2;
                n2 = Texture.anIntArray1472[n2];
                while (--n3 >= 0) {
                    method377(DrawingArea.pixels, n2, n7, n5 >> 16, n4 >> 16);
                    n4 += n8;
                    n5 += n9;
                    n2 += DrawingArea.width;
                }
                while (--n >= 0) {
                    method377(DrawingArea.pixels, n2, n7, n6 >> 16, n4 >> 16);
                    n4 += n8;
                    n6 += n10;
                    n2 += DrawingArea.width;
                }
            }
            else {
                n5 = (n6 = n5 << 16);
                if (n2 < 0) {
                    n6 -= n8 * n2;
                    n5 -= n9 * n2;
                    n2 = 0;
                }
                n4 <<= 16;
                if (n < 0) {
                    n4 -= n10 * n;
                    n = 0;
                }
                if (n8 < n9) {
                    n3 -= n;
                    n -= n2;
                    n2 = Texture.anIntArray1472[n2];
                    while (--n >= 0) {
                        method377(DrawingArea.pixels, n2, n7, n6 >> 16, n5 >> 16);
                        n6 += n8;
                        n5 += n9;
                        n2 += DrawingArea.width;
                    }
                    while (--n3 >= 0) {
                        method377(DrawingArea.pixels, n2, n7, n4 >> 16, n5 >> 16);
                        n4 += n10;
                        n5 += n9;
                        n2 += DrawingArea.width;
                    }
                    return;
                }
                n3 -= n;
                n -= n2;
                n2 = Texture.anIntArray1472[n2];
                while (--n >= 0) {
                    method377(DrawingArea.pixels, n2, n7, n5 >> 16, n6 >> 16);
                    n6 += n8;
                    n5 += n9;
                    n2 += DrawingArea.width;
                }
                while (--n3 >= 0) {
                    method377(DrawingArea.pixels, n2, n7, n5 >> 16, n4 >> 16);
                    n4 += n10;
                    n5 += n9;
                    n2 += DrawingArea.width;
                }
            }
        }
        else {
            if (n3 >= DrawingArea.bottomY) {
                return;
            }
            if (n > DrawingArea.bottomY) {
                n = DrawingArea.bottomY;
            }
            if (n2 > DrawingArea.bottomY) {
                n2 = DrawingArea.bottomY;
            }
            if (n < n2) {
                n6 = (n5 = n6 << 16);
                if (n3 < 0) {
                    n5 -= n9 * n3;
                    n6 -= n10 * n3;
                    n3 = 0;
                }
                n4 <<= 16;
                if (n < 0) {
                    n4 -= n8 * n;
                    n = 0;
                }
                if (n9 < n10) {
                    n2 -= n;
                    n -= n3;
                    n3 = Texture.anIntArray1472[n3];
                    while (--n >= 0) {
                        method377(DrawingArea.pixels, n3, n7, n5 >> 16, n6 >> 16);
                        n5 += n9;
                        n6 += n10;
                        n3 += DrawingArea.width;
                    }
                    while (--n2 >= 0) {
                        method377(DrawingArea.pixels, n3, n7, n5 >> 16, n4 >> 16);
                        n5 += n9;
                        n4 += n8;
                        n3 += DrawingArea.width;
                    }
                    return;
                }
                n2 -= n;
                n -= n3;
                n3 = Texture.anIntArray1472[n3];
                while (--n >= 0) {
                    method377(DrawingArea.pixels, n3, n7, n6 >> 16, n5 >> 16);
                    n5 += n9;
                    n6 += n10;
                    n3 += DrawingArea.width;
                }
                while (--n2 >= 0) {
                    method377(DrawingArea.pixels, n3, n7, n4 >> 16, n5 >> 16);
                    n5 += n9;
                    n4 += n8;
                    n3 += DrawingArea.width;
                }
            }
            else {
                n6 = (n4 = n6 << 16);
                if (n3 < 0) {
                    n4 -= n9 * n3;
                    n6 -= n10 * n3;
                    n3 = 0;
                }
                n5 <<= 16;
                if (n2 < 0) {
                    n5 -= n8 * n2;
                    n2 = 0;
                }
                if (n9 < n10) {
                    n -= n2;
                    n2 -= n3;
                    n3 = Texture.anIntArray1472[n3];
                    while (--n2 >= 0) {
                        method377(DrawingArea.pixels, n3, n7, n4 >> 16, n6 >> 16);
                        n4 += n9;
                        n6 += n10;
                        n3 += DrawingArea.width;
                    }
                    while (--n >= 0) {
                        method377(DrawingArea.pixels, n3, n7, n5 >> 16, n6 >> 16);
                        n5 += n8;
                        n6 += n10;
                        n3 += DrawingArea.width;
                    }
                    return;
                }
                n -= n2;
                n2 -= n3;
                n3 = Texture.anIntArray1472[n3];
                while (--n2 >= 0) {
                    method377(DrawingArea.pixels, n3, n7, n6 >> 16, n4 >> 16);
                    n4 += n9;
                    n6 += n10;
                    n3 += DrawingArea.width;
                }
                while (--n >= 0) {
                    method377(DrawingArea.pixels, n3, n7, n6 >> 16, n5 >> 16);
                    n5 += n8;
                    n6 += n10;
                    n3 += DrawingArea.width;
                }
            }
        }
    }
    
    private static void method377(final int[] array, int n, int n2, int n3, int centerX) {
        if (Texture.aBoolean1462) {
            if (centerX > DrawingArea.centerX) {
                centerX = DrawingArea.centerX;
            }
            if (n3 < 0) {
                n3 = 0;
            }
        }
        if (n3 >= centerX) {
            return;
        }
        n += n3;
        int n4 = centerX - n3 >> 2;
        if (Texture.anInt1465 == 0) {
            while (--n4 >= 0) {
                array[n++] = n2;
                array[n++] = n2;
                array[n++] = n2;
                array[n++] = n2;
            }
            int n5 = centerX - n3 & 0x3;
            while (--n5 >= 0) {
                array[n++] = n2;
            }
            return;
        }
        final int anInt1465 = Texture.anInt1465;
        final int n6 = 256 - Texture.anInt1465;
        n2 = ((n2 & 0xFF00FF) * n6 >> 8 & 0xFF00FF) + ((n2 & 0xFF00) * n6 >> 8 & 0xFF00);
        while (--n4 >= 0) {
            array[n] = n2 + ((array[n] & 0xFF00FF) * anInt1465 >> 8 & 0xFF00FF) + ((array[n] & 0xFF00) * anInt1465 >> 8 & 0xFF00);
            ++n;
            array[n] = n2 + ((array[n] & 0xFF00FF) * anInt1465 >> 8 & 0xFF00FF) + ((array[n] & 0xFF00) * anInt1465 >> 8 & 0xFF00);
            ++n;
            array[n] = n2 + ((array[n] & 0xFF00FF) * anInt1465 >> 8 & 0xFF00FF) + ((array[n] & 0xFF00) * anInt1465 >> 8 & 0xFF00);
            ++n;
            array[n] = n2 + ((array[n] & 0xFF00FF) * anInt1465 >> 8 & 0xFF00FF) + ((array[n] & 0xFF00) * anInt1465 >> 8 & 0xFF00);
            ++n;
        }
        int n7 = centerX - n3 & 0x3;
        while (--n7 >= 0) {
            array[n] = n2 + ((array[n] & 0xFF00FF) * anInt1465 >> 8 & 0xFF00FF) + ((array[n] & 0xFF00) * anInt1465 >> 8 & 0xFF00);
        }
        ++n;
    }
    
    public static void method378(int n, int n2, int n3, int n4, int n5, int n6, int n7, int n8, int n9, final int n10, int n11, int n12, final int n13, int n14, int n15, final int n16, int n17, int n18, final int n19) {
        final int[] method371 = method371(n19);
        Texture.aBoolean1463 = !Texture.aBooleanArray1475[n19];
        n11 = n10 - n11;
        n14 = n13 - n14;
        n17 = n16 - n17;
        n12 -= n10;
        n15 -= n13;
        n18 -= n16;
        final int n20 = n12 * n13 - n15 * n10 << 14;
        final int n21 = n15 * n16 - n18 * n13 << 8;
        final int n22 = n18 * n10 - n12 * n16 << 5;
        final int n23 = n11 * n13 - n14 * n10 << 14;
        final int n24 = n14 * n16 - n17 * n13 << 8;
        final int n25 = n17 * n10 - n11 * n16 << 5;
        final int n26 = n14 * n12 - n11 * n15 << 14;
        final int n27 = n17 * n15 - n14 * n18 << 8;
        final int n28 = n11 * n18 - n17 * n12 << 5;
        int n29 = 0;
        int n30 = 0;
        if (n2 != n) {
            n29 = (n5 - n4 << 16) / (n2 - n);
            n30 = (n8 - n7 << 16) / (n2 - n);
        }
        int n31 = 0;
        int n32 = 0;
        if (n3 != n2) {
            n31 = (n6 - n5 << 16) / (n3 - n2);
            n32 = (n9 - n8 << 16) / (n3 - n2);
        }
        int n33 = 0;
        int n34 = 0;
        if (n3 != n) {
            n33 = (n4 - n6 << 16) / (n - n3);
            n34 = (n7 - n9 << 16) / (n - n3);
        }
        if (n <= n2 && n <= n3) {
            if (n >= DrawingArea.bottomY) {
                return;
            }
            if (n2 > DrawingArea.bottomY) {
                n2 = DrawingArea.bottomY;
            }
            if (n3 > DrawingArea.bottomY) {
                n3 = DrawingArea.bottomY;
            }
            if (n2 < n3) {
                n4 = (n6 = n4 << 16);
                n7 = (n9 = n7 << 16);
                if (n < 0) {
                    n6 -= n33 * n;
                    n4 -= n29 * n;
                    n9 -= n34 * n;
                    n7 -= n30 * n;
                    n = 0;
                }
                n5 <<= 16;
                n8 <<= 16;
                if (n2 < 0) {
                    n5 -= n31 * n2;
                    n8 -= n32 * n2;
                    n2 = 0;
                }
                final int n35 = n - Texture.textureInt2;
                int n36 = n20 + n22 * n35;
                int n37 = n23 + n25 * n35;
                int n38 = n26 + n28 * n35;
                if ((n != n2 && n33 < n29) || (n == n2 && n33 > n31)) {
                    n3 -= n2;
                    n2 -= n;
                    n = Texture.anIntArray1472[n];
                    while (--n2 >= 0) {
                        method379(DrawingArea.pixels, method371, n, n6 >> 16, n4 >> 16, n9 >> 8, n7 >> 8, n36, n37, n38, n21, n24, n27);
                        n6 += n33;
                        n4 += n29;
                        n9 += n34;
                        n7 += n30;
                        n += DrawingArea.width;
                        n36 += n22;
                        n37 += n25;
                        n38 += n28;
                    }
                    while (--n3 >= 0) {
                        method379(DrawingArea.pixels, method371, n, n6 >> 16, n5 >> 16, n9 >> 8, n8 >> 8, n36, n37, n38, n21, n24, n27);
                        n6 += n33;
                        n5 += n31;
                        n9 += n34;
                        n8 += n32;
                        n += DrawingArea.width;
                        n36 += n22;
                        n37 += n25;
                        n38 += n28;
                    }
                    return;
                }
                n3 -= n2;
                n2 -= n;
                n = Texture.anIntArray1472[n];
                while (--n2 >= 0) {
                    method379(DrawingArea.pixels, method371, n, n4 >> 16, n6 >> 16, n7 >> 8, n9 >> 8, n36, n37, n38, n21, n24, n27);
                    n6 += n33;
                    n4 += n29;
                    n9 += n34;
                    n7 += n30;
                    n += DrawingArea.width;
                    n36 += n22;
                    n37 += n25;
                    n38 += n28;
                }
                while (--n3 >= 0) {
                    method379(DrawingArea.pixels, method371, n, n5 >> 16, n6 >> 16, n8 >> 8, n9 >> 8, n36, n37, n38, n21, n24, n27);
                    n6 += n33;
                    n5 += n31;
                    n9 += n34;
                    n8 += n32;
                    n += DrawingArea.width;
                    n36 += n22;
                    n37 += n25;
                    n38 += n28;
                }
            }
            else {
                n4 = (n5 = n4 << 16);
                n7 = (n8 = n7 << 16);
                if (n < 0) {
                    n5 -= n33 * n;
                    n4 -= n29 * n;
                    n8 -= n34 * n;
                    n7 -= n30 * n;
                    n = 0;
                }
                n6 <<= 16;
                n9 <<= 16;
                if (n3 < 0) {
                    n6 -= n31 * n3;
                    n9 -= n32 * n3;
                    n3 = 0;
                }
                final int n39 = n - Texture.textureInt2;
                int n40 = n20 + n22 * n39;
                int n41 = n23 + n25 * n39;
                int n42 = n26 + n28 * n39;
                if ((n != n3 && n33 < n29) || (n == n3 && n31 > n29)) {
                    n2 -= n3;
                    n3 -= n;
                    n = Texture.anIntArray1472[n];
                    while (--n3 >= 0) {
                        method379(DrawingArea.pixels, method371, n, n5 >> 16, n4 >> 16, n8 >> 8, n7 >> 8, n40, n41, n42, n21, n24, n27);
                        n5 += n33;
                        n4 += n29;
                        n8 += n34;
                        n7 += n30;
                        n += DrawingArea.width;
                        n40 += n22;
                        n41 += n25;
                        n42 += n28;
                    }
                    while (--n2 >= 0) {
                        method379(DrawingArea.pixels, method371, n, n6 >> 16, n4 >> 16, n9 >> 8, n7 >> 8, n40, n41, n42, n21, n24, n27);
                        n6 += n31;
                        n4 += n29;
                        n9 += n32;
                        n7 += n30;
                        n += DrawingArea.width;
                        n40 += n22;
                        n41 += n25;
                        n42 += n28;
                    }
                    return;
                }
                n2 -= n3;
                n3 -= n;
                n = Texture.anIntArray1472[n];
                while (--n3 >= 0) {
                    method379(DrawingArea.pixels, method371, n, n4 >> 16, n5 >> 16, n7 >> 8, n8 >> 8, n40, n41, n42, n21, n24, n27);
                    n5 += n33;
                    n4 += n29;
                    n8 += n34;
                    n7 += n30;
                    n += DrawingArea.width;
                    n40 += n22;
                    n41 += n25;
                    n42 += n28;
                }
                while (--n2 >= 0) {
                    method379(DrawingArea.pixels, method371, n, n4 >> 16, n6 >> 16, n7 >> 8, n9 >> 8, n40, n41, n42, n21, n24, n27);
                    n6 += n31;
                    n4 += n29;
                    n9 += n32;
                    n7 += n30;
                    n += DrawingArea.width;
                    n40 += n22;
                    n41 += n25;
                    n42 += n28;
                }
            }
        }
        else if (n2 <= n3) {
            if (n2 >= DrawingArea.bottomY) {
                return;
            }
            if (n3 > DrawingArea.bottomY) {
                n3 = DrawingArea.bottomY;
            }
            if (n > DrawingArea.bottomY) {
                n = DrawingArea.bottomY;
            }
            if (n3 < n) {
                n5 = (n4 = n5 << 16);
                n8 = (n7 = n8 << 16);
                if (n2 < 0) {
                    n4 -= n29 * n2;
                    n5 -= n31 * n2;
                    n7 -= n30 * n2;
                    n8 -= n32 * n2;
                    n2 = 0;
                }
                n6 <<= 16;
                n9 <<= 16;
                if (n3 < 0) {
                    n6 -= n33 * n3;
                    n9 -= n34 * n3;
                    n3 = 0;
                }
                final int n43 = n2 - Texture.textureInt2;
                int n44 = n20 + n22 * n43;
                int n45 = n23 + n25 * n43;
                int n46 = n26 + n28 * n43;
                if ((n2 != n3 && n29 < n31) || (n2 == n3 && n29 > n33)) {
                    n -= n3;
                    n3 -= n2;
                    n2 = Texture.anIntArray1472[n2];
                    while (--n3 >= 0) {
                        method379(DrawingArea.pixels, method371, n2, n4 >> 16, n5 >> 16, n7 >> 8, n8 >> 8, n44, n45, n46, n21, n24, n27);
                        n4 += n29;
                        n5 += n31;
                        n7 += n30;
                        n8 += n32;
                        n2 += DrawingArea.width;
                        n44 += n22;
                        n45 += n25;
                        n46 += n28;
                    }
                    while (--n >= 0) {
                        method379(DrawingArea.pixels, method371, n2, n4 >> 16, n6 >> 16, n7 >> 8, n9 >> 8, n44, n45, n46, n21, n24, n27);
                        n4 += n29;
                        n6 += n33;
                        n7 += n30;
                        n9 += n34;
                        n2 += DrawingArea.width;
                        n44 += n22;
                        n45 += n25;
                        n46 += n28;
                    }
                    return;
                }
                n -= n3;
                n3 -= n2;
                n2 = Texture.anIntArray1472[n2];
                while (--n3 >= 0) {
                    method379(DrawingArea.pixels, method371, n2, n5 >> 16, n4 >> 16, n8 >> 8, n7 >> 8, n44, n45, n46, n21, n24, n27);
                    n4 += n29;
                    n5 += n31;
                    n7 += n30;
                    n8 += n32;
                    n2 += DrawingArea.width;
                    n44 += n22;
                    n45 += n25;
                    n46 += n28;
                }
                while (--n >= 0) {
                    method379(DrawingArea.pixels, method371, n2, n6 >> 16, n4 >> 16, n9 >> 8, n7 >> 8, n44, n45, n46, n21, n24, n27);
                    n4 += n29;
                    n6 += n33;
                    n7 += n30;
                    n9 += n34;
                    n2 += DrawingArea.width;
                    n44 += n22;
                    n45 += n25;
                    n46 += n28;
                }
            }
            else {
                n5 = (n6 = n5 << 16);
                n8 = (n9 = n8 << 16);
                if (n2 < 0) {
                    n6 -= n29 * n2;
                    n5 -= n31 * n2;
                    n9 -= n30 * n2;
                    n8 -= n32 * n2;
                    n2 = 0;
                }
                n4 <<= 16;
                n7 <<= 16;
                if (n < 0) {
                    n4 -= n33 * n;
                    n7 -= n34 * n;
                    n = 0;
                }
                final int n47 = n2 - Texture.textureInt2;
                int n48 = n20 + n22 * n47;
                int n49 = n23 + n25 * n47;
                int n50 = n26 + n28 * n47;
                if (n29 < n31) {
                    n3 -= n;
                    n -= n2;
                    n2 = Texture.anIntArray1472[n2];
                    while (--n >= 0) {
                        method379(DrawingArea.pixels, method371, n2, n6 >> 16, n5 >> 16, n9 >> 8, n8 >> 8, n48, n49, n50, n21, n24, n27);
                        n6 += n29;
                        n5 += n31;
                        n9 += n30;
                        n8 += n32;
                        n2 += DrawingArea.width;
                        n48 += n22;
                        n49 += n25;
                        n50 += n28;
                    }
                    while (--n3 >= 0) {
                        method379(DrawingArea.pixels, method371, n2, n4 >> 16, n5 >> 16, n7 >> 8, n8 >> 8, n48, n49, n50, n21, n24, n27);
                        n4 += n33;
                        n5 += n31;
                        n7 += n34;
                        n8 += n32;
                        n2 += DrawingArea.width;
                        n48 += n22;
                        n49 += n25;
                        n50 += n28;
                    }
                    return;
                }
                n3 -= n;
                n -= n2;
                n2 = Texture.anIntArray1472[n2];
                while (--n >= 0) {
                    method379(DrawingArea.pixels, method371, n2, n5 >> 16, n6 >> 16, n8 >> 8, n9 >> 8, n48, n49, n50, n21, n24, n27);
                    n6 += n29;
                    n5 += n31;
                    n9 += n30;
                    n8 += n32;
                    n2 += DrawingArea.width;
                    n48 += n22;
                    n49 += n25;
                    n50 += n28;
                }
                while (--n3 >= 0) {
                    method379(DrawingArea.pixels, method371, n2, n5 >> 16, n4 >> 16, n8 >> 8, n7 >> 8, n48, n49, n50, n21, n24, n27);
                    n4 += n33;
                    n5 += n31;
                    n7 += n34;
                    n8 += n32;
                    n2 += DrawingArea.width;
                    n48 += n22;
                    n49 += n25;
                    n50 += n28;
                }
            }
        }
        else {
            if (n3 >= DrawingArea.bottomY) {
                return;
            }
            if (n > DrawingArea.bottomY) {
                n = DrawingArea.bottomY;
            }
            if (n2 > DrawingArea.bottomY) {
                n2 = DrawingArea.bottomY;
            }
            if (n < n2) {
                n6 = (n5 = n6 << 16);
                n9 = (n8 = n9 << 16);
                if (n3 < 0) {
                    n5 -= n31 * n3;
                    n6 -= n33 * n3;
                    n8 -= n32 * n3;
                    n9 -= n34 * n3;
                    n3 = 0;
                }
                n4 <<= 16;
                n7 <<= 16;
                if (n < 0) {
                    n4 -= n29 * n;
                    n7 -= n30 * n;
                    n = 0;
                }
                final int n51 = n3 - Texture.textureInt2;
                int n52 = n20 + n22 * n51;
                int n53 = n23 + n25 * n51;
                int n54 = n26 + n28 * n51;
                if (n31 < n33) {
                    n2 -= n;
                    n -= n3;
                    n3 = Texture.anIntArray1472[n3];
                    while (--n >= 0) {
                        method379(DrawingArea.pixels, method371, n3, n5 >> 16, n6 >> 16, n8 >> 8, n9 >> 8, n52, n53, n54, n21, n24, n27);
                        n5 += n31;
                        n6 += n33;
                        n8 += n32;
                        n9 += n34;
                        n3 += DrawingArea.width;
                        n52 += n22;
                        n53 += n25;
                        n54 += n28;
                    }
                    while (--n2 >= 0) {
                        method379(DrawingArea.pixels, method371, n3, n5 >> 16, n4 >> 16, n8 >> 8, n7 >> 8, n52, n53, n54, n21, n24, n27);
                        n5 += n31;
                        n4 += n29;
                        n8 += n32;
                        n7 += n30;
                        n3 += DrawingArea.width;
                        n52 += n22;
                        n53 += n25;
                        n54 += n28;
                    }
                    return;
                }
                n2 -= n;
                n -= n3;
                n3 = Texture.anIntArray1472[n3];
                while (--n >= 0) {
                    method379(DrawingArea.pixels, method371, n3, n6 >> 16, n5 >> 16, n9 >> 8, n8 >> 8, n52, n53, n54, n21, n24, n27);
                    n5 += n31;
                    n6 += n33;
                    n8 += n32;
                    n9 += n34;
                    n3 += DrawingArea.width;
                    n52 += n22;
                    n53 += n25;
                    n54 += n28;
                }
                while (--n2 >= 0) {
                    method379(DrawingArea.pixels, method371, n3, n4 >> 16, n5 >> 16, n7 >> 8, n8 >> 8, n52, n53, n54, n21, n24, n27);
                    n5 += n31;
                    n4 += n29;
                    n8 += n32;
                    n7 += n30;
                    n3 += DrawingArea.width;
                    n52 += n22;
                    n53 += n25;
                    n54 += n28;
                }
            }
            else {
                n6 = (n4 = n6 << 16);
                n9 = (n7 = n9 << 16);
                if (n3 < 0) {
                    n4 -= n31 * n3;
                    n6 -= n33 * n3;
                    n7 -= n32 * n3;
                    n9 -= n34 * n3;
                    n3 = 0;
                }
                n5 <<= 16;
                n8 <<= 16;
                if (n2 < 0) {
                    n5 -= n29 * n2;
                    n8 -= n30 * n2;
                    n2 = 0;
                }
                final int n55 = n3 - Texture.textureInt2;
                int n56 = n20 + n22 * n55;
                int n57 = n23 + n25 * n55;
                int n58 = n26 + n28 * n55;
                if (n31 < n33) {
                    n -= n2;
                    n2 -= n3;
                    n3 = Texture.anIntArray1472[n3];
                    while (--n2 >= 0) {
                        method379(DrawingArea.pixels, method371, n3, n4 >> 16, n6 >> 16, n7 >> 8, n9 >> 8, n56, n57, n58, n21, n24, n27);
                        n4 += n31;
                        n6 += n33;
                        n7 += n32;
                        n9 += n34;
                        n3 += DrawingArea.width;
                        n56 += n22;
                        n57 += n25;
                        n58 += n28;
                    }
                    while (--n >= 0) {
                        method379(DrawingArea.pixels, method371, n3, n5 >> 16, n6 >> 16, n8 >> 8, n9 >> 8, n56, n57, n58, n21, n24, n27);
                        n5 += n29;
                        n6 += n33;
                        n8 += n30;
                        n9 += n34;
                        n3 += DrawingArea.width;
                        n56 += n22;
                        n57 += n25;
                        n58 += n28;
                    }
                    return;
                }
                n -= n2;
                n2 -= n3;
                n3 = Texture.anIntArray1472[n3];
                while (--n2 >= 0) {
                    method379(DrawingArea.pixels, method371, n3, n6 >> 16, n4 >> 16, n9 >> 8, n7 >> 8, n56, n57, n58, n21, n24, n27);
                    n4 += n31;
                    n6 += n33;
                    n7 += n32;
                    n9 += n34;
                    n3 += DrawingArea.width;
                    n56 += n22;
                    n57 += n25;
                    n58 += n28;
                }
                while (--n >= 0) {
                    method379(DrawingArea.pixels, method371, n3, n6 >> 16, n5 >> 16, n9 >> 8, n8 >> 8, n56, n57, n58, n21, n24, n27);
                    n5 += n29;
                    n6 += n33;
                    n8 += n30;
                    n9 += n34;
                    n3 += DrawingArea.width;
                    n56 += n22;
                    n57 += n25;
                    n58 += n28;
                }
            }
        }
    }
    
    private static void method379(final int[] array, final int[] array2, int n, int n2, int centerX, int n3, final int n4, int n5, int n6, int n7, final int n8, final int n9, final int n10) {
        int n11 = 0;
        int n12 = 0;
        if (n2 >= centerX) {
            return;
        }
        int n14;
        int n15;
        if (Texture.aBoolean1462) {
            final int n13 = (n4 - n3) / (centerX - n2);
            if (centerX > DrawingArea.centerX) {
                centerX = DrawingArea.centerX;
            }
            if (n2 < 0) {
                n3 -= n2 * n13;
                n2 = 0;
            }
            if (n2 >= centerX) {
                return;
            }
            n14 = centerX - n2 >> 3;
            n15 = n13 << 12;
            n3 <<= 9;
        }
        else {
            if (centerX - n2 > 7) {
                n14 = centerX - n2 >> 3;
                n15 = (n4 - n3) * Texture.anIntArray1468[n14] >> 6;
            }
            else {
                n14 = 0;
                n15 = 0;
            }
            n3 <<= 9;
        }
        n += n2;
        if (Texture.lowMem) {
            int n16 = 0;
            int n17 = 0;
            final int n18 = n2 - Texture.textureInt1;
            n5 += (n8 >> 3) * n18;
            n6 += (n9 >> 3) * n18;
            n7 += (n10 >> 3) * n18;
            final int n19 = n7 >> 12;
            if (n19 != 0) {
                n11 = n5 / n19;
                n12 = n6 / n19;
                if (n11 < 0) {
                    n11 = 0;
                }
                else if (n11 > 4032) {
                    n11 = 4032;
                }
            }
            n5 += n8;
            n6 += n9;
            n7 += n10;
            final int n20 = n7 >> 12;
            if (n20 != 0) {
                n16 = n5 / n20;
                n17 = n6 / n20;
                if (n16 < 7) {
                    n16 = 7;
                }
                else if (n16 > 4032) {
                    n16 = 4032;
                }
            }
            int n21 = n16 - n11 >> 3;
            int n22 = n17 - n12 >> 3;
            int n23 = n11 + ((n3 & 0x600000) >> 3);
            int n24 = n3 >> 23;
            if (Texture.aBoolean1463) {
                while (n14-- > 0) {
                    array[n++] = array2[(n12 & 0xFC0) + (n23 >> 6)] >>> n24;
                    final int n25 = n23 + n21;
                    final int n26 = n12 + n22;
                    array[n++] = array2[(n26 & 0xFC0) + (n25 >> 6)] >>> n24;
                    final int n27 = n25 + n21;
                    final int n28 = n26 + n22;
                    array[n++] = array2[(n28 & 0xFC0) + (n27 >> 6)] >>> n24;
                    final int n29 = n27 + n21;
                    final int n30 = n28 + n22;
                    array[n++] = array2[(n30 & 0xFC0) + (n29 >> 6)] >>> n24;
                    final int n31 = n29 + n21;
                    final int n32 = n30 + n22;
                    array[n++] = array2[(n32 & 0xFC0) + (n31 >> 6)] >>> n24;
                    final int n33 = n31 + n21;
                    final int n34 = n32 + n22;
                    array[n++] = array2[(n34 & 0xFC0) + (n33 >> 6)] >>> n24;
                    final int n35 = n33 + n21;
                    final int n36 = n34 + n22;
                    array[n++] = array2[(n36 & 0xFC0) + (n35 >> 6)] >>> n24;
                    array[n++] = array2[(n36 + n22 & 0xFC0) + (n35 + n21 >> 6)] >>> n24;
                    final int n37 = n16;
                    n12 = n17;
                    n5 += n8;
                    n6 += n9;
                    n7 += n10;
                    final int n38 = n7 >> 12;
                    if (n38 != 0) {
                        n16 = n5 / n38;
                        n17 = n6 / n38;
                        if (n16 < 7) {
                            n16 = 7;
                        }
                        else if (n16 > 4032) {
                            n16 = 4032;
                        }
                    }
                    n21 = n16 - n37 >> 3;
                    n22 = n17 - n12 >> 3;
                    n3 += n15;
                    n23 = n37 + ((n3 & 0x600000) >> 3);
                    n24 = n3 >> 23;
                }
                int n39 = centerX - n2 & 0x7;
                while (n39-- > 0) {
                    array[n++] = array2[(n12 & 0xFC0) + (n23 >> 6)] >>> n24;
                    n23 += n21;
                    n12 += n22;
                }
                return;
            }
            while (n14-- > 0) {
                final int n40;
                if ((n40 = array2[(n12 & 0xFC0) + (n23 >> 6)] >>> n24) != 0) {
                    array[n] = n40;
                }
                ++n;
                final int n41 = n23 + n21;
                final int n42 = n12 + n22;
                final int n43;
                if ((n43 = array2[(n42 & 0xFC0) + (n41 >> 6)] >>> n24) != 0) {
                    array[n] = n43;
                }
                ++n;
                final int n44 = n41 + n21;
                final int n45 = n42 + n22;
                final int n46;
                if ((n46 = array2[(n45 & 0xFC0) + (n44 >> 6)] >>> n24) != 0) {
                    array[n] = n46;
                }
                ++n;
                final int n47 = n44 + n21;
                final int n48 = n45 + n22;
                final int n49;
                if ((n49 = array2[(n48 & 0xFC0) + (n47 >> 6)] >>> n24) != 0) {
                    array[n] = n49;
                }
                ++n;
                final int n50 = n47 + n21;
                final int n51 = n48 + n22;
                final int n52;
                if ((n52 = array2[(n51 & 0xFC0) + (n50 >> 6)] >>> n24) != 0) {
                    array[n] = n52;
                }
                ++n;
                final int n53 = n50 + n21;
                final int n54 = n51 + n22;
                final int n55;
                if ((n55 = array2[(n54 & 0xFC0) + (n53 >> 6)] >>> n24) != 0) {
                    array[n] = n55;
                }
                ++n;
                final int n56 = n53 + n21;
                final int n57 = n54 + n22;
                final int n58;
                if ((n58 = array2[(n57 & 0xFC0) + (n56 >> 6)] >>> n24) != 0) {
                    array[n] = n58;
                }
                ++n;
                final int n59;
                if ((n59 = array2[(n57 + n22 & 0xFC0) + (n56 + n21 >> 6)] >>> n24) != 0) {
                    array[n] = n59;
                }
                ++n;
                final int n60 = n16;
                n12 = n17;
                n5 += n8;
                n6 += n9;
                n7 += n10;
                final int n61 = n7 >> 12;
                if (n61 != 0) {
                    n16 = n5 / n61;
                    n17 = n6 / n61;
                    if (n16 < 7) {
                        n16 = 7;
                    }
                    else if (n16 > 4032) {
                        n16 = 4032;
                    }
                }
                n21 = n16 - n60 >> 3;
                n22 = n17 - n12 >> 3;
                n3 += n15;
                n23 = n60 + ((n3 & 0x600000) >> 3);
                n24 = n3 >> 23;
            }
            int n62 = centerX - n2 & 0x7;
            while (n62-- > 0) {
                final int n63;
                if ((n63 = array2[(n12 & 0xFC0) + (n23 >> 6)] >>> n24) != 0) {
                    array[n] = n63;
                }
                ++n;
                n23 += n21;
                n12 += n22;
            }
        }
        else {
            int n64 = 0;
            int n65 = 0;
            final int n66 = n2 - Texture.textureInt1;
            n5 += (n8 >> 3) * n66;
            n6 += (n9 >> 3) * n66;
            n7 += (n10 >> 3) * n66;
            final int n67 = n7 >> 14;
            if (n67 != 0) {
                n11 = n5 / n67;
                n12 = n6 / n67;
                if (n11 < 0) {
                    n11 = 0;
                }
                else if (n11 > 16256) {
                    n11 = 16256;
                }
            }
            n5 += n8;
            n6 += n9;
            n7 += n10;
            final int n68 = n7 >> 14;
            if (n68 != 0) {
                n64 = n5 / n68;
                n65 = n6 / n68;
                if (n64 < 7) {
                    n64 = 7;
                }
                else if (n64 > 16256) {
                    n64 = 16256;
                }
            }
            int n69 = n64 - n11 >> 3;
            int n70 = n65 - n12 >> 3;
            int n71 = n11 + (n3 & 0x600000);
            int n72 = n3 >> 23;
            if (Texture.aBoolean1463) {
                while (n14-- > 0) {
                    array[n++] = array2[(n12 & 0x3F80) + (n71 >> 7)] >>> n72;
                    final int n73 = n71 + n69;
                    final int n74 = n12 + n70;
                    array[n++] = array2[(n74 & 0x3F80) + (n73 >> 7)] >>> n72;
                    final int n75 = n73 + n69;
                    final int n76 = n74 + n70;
                    array[n++] = array2[(n76 & 0x3F80) + (n75 >> 7)] >>> n72;
                    final int n77 = n75 + n69;
                    final int n78 = n76 + n70;
                    array[n++] = array2[(n78 & 0x3F80) + (n77 >> 7)] >>> n72;
                    final int n79 = n77 + n69;
                    final int n80 = n78 + n70;
                    array[n++] = array2[(n80 & 0x3F80) + (n79 >> 7)] >>> n72;
                    final int n81 = n79 + n69;
                    final int n82 = n80 + n70;
                    array[n++] = array2[(n82 & 0x3F80) + (n81 >> 7)] >>> n72;
                    final int n83 = n81 + n69;
                    final int n84 = n82 + n70;
                    array[n++] = array2[(n84 & 0x3F80) + (n83 >> 7)] >>> n72;
                    array[n++] = array2[(n84 + n70 & 0x3F80) + (n83 + n69 >> 7)] >>> n72;
                    final int n85 = n64;
                    n12 = n65;
                    n5 += n8;
                    n6 += n9;
                    n7 += n10;
                    final int n86 = n7 >> 14;
                    if (n86 != 0) {
                        n64 = n5 / n86;
                        n65 = n6 / n86;
                        if (n64 < 7) {
                            n64 = 7;
                        }
                        else if (n64 > 16256) {
                            n64 = 16256;
                        }
                    }
                    n69 = n64 - n85 >> 3;
                    n70 = n65 - n12 >> 3;
                    n3 += n15;
                    n71 = n85 + (n3 & 0x600000);
                    n72 = n3 >> 23;
                }
                int n87 = centerX - n2 & 0x7;
                while (n87-- > 0) {
                    array[n++] = array2[(n12 & 0x3F80) + (n71 >> 7)] >>> n72;
                    n71 += n69;
                    n12 += n70;
                }
                return;
            }
            while (n14-- > 0) {
                final int n88;
                if ((n88 = array2[(n12 & 0x3F80) + (n71 >> 7)] >>> n72) != 0) {
                    array[n] = n88;
                }
                ++n;
                final int n89 = n71 + n69;
                final int n90 = n12 + n70;
                final int n91;
                if ((n91 = array2[(n90 & 0x3F80) + (n89 >> 7)] >>> n72) != 0) {
                    array[n] = n91;
                }
                ++n;
                final int n92 = n89 + n69;
                final int n93 = n90 + n70;
                final int n94;
                if ((n94 = array2[(n93 & 0x3F80) + (n92 >> 7)] >>> n72) != 0) {
                    array[n] = n94;
                }
                ++n;
                final int n95 = n92 + n69;
                final int n96 = n93 + n70;
                final int n97;
                if ((n97 = array2[(n96 & 0x3F80) + (n95 >> 7)] >>> n72) != 0) {
                    array[n] = n97;
                }
                ++n;
                final int n98 = n95 + n69;
                final int n99 = n96 + n70;
                final int n100;
                if ((n100 = array2[(n99 & 0x3F80) + (n98 >> 7)] >>> n72) != 0) {
                    array[n] = n100;
                }
                ++n;
                final int n101 = n98 + n69;
                final int n102 = n99 + n70;
                final int n103;
                if ((n103 = array2[(n102 & 0x3F80) + (n101 >> 7)] >>> n72) != 0) {
                    array[n] = n103;
                }
                ++n;
                final int n104 = n101 + n69;
                final int n105 = n102 + n70;
                final int n106;
                if ((n106 = array2[(n105 & 0x3F80) + (n104 >> 7)] >>> n72) != 0) {
                    array[n] = n106;
                }
                ++n;
                final int n107;
                if ((n107 = array2[(n105 + n70 & 0x3F80) + (n104 + n69 >> 7)] >>> n72) != 0) {
                    array[n] = n107;
                }
                ++n;
                final int n108 = n64;
                n12 = n65;
                n5 += n8;
                n6 += n9;
                n7 += n10;
                final int n109 = n7 >> 14;
                if (n109 != 0) {
                    n64 = n5 / n109;
                    n65 = n6 / n109;
                    if (n64 < 7) {
                        n64 = 7;
                    }
                    else if (n64 > 16256) {
                        n64 = 16256;
                    }
                }
                n69 = n64 - n108 >> 3;
                n70 = n65 - n12 >> 3;
                n3 += n15;
                n71 = n108 + (n3 & 0x600000);
                n72 = n3 >> 23;
            }
            int n110 = centerX - n2 & 0x7;
            while (n110-- > 0) {
                final int n111;
                if ((n111 = array2[(n12 & 0x3F80) + (n71 >> 7)] >>> n72) != 0) {
                    array[n] = n111;
                }
                ++n;
                n71 += n69;
                n12 += n70;
            }
        }
    }
    
    static {
        Texture.lowMem = false;
        Texture.aBoolean1464 = true;
        Texture.aBackgroundArray1474s = new Background[50];
        Texture.aBooleanArray1475 = new boolean[50];
        Texture.anIntArray1476 = new int[50];
        Texture.anIntArrayArray1479 = new int[50][];
        Texture.anIntArray1480 = new int[50];
        Texture.anIntArray1482 = new int[65536];
        Texture.anIntArrayArray1483 = new int[50][];
        Texture.anIntArray1468 = new int[512];
        anIntArray1469 = new int[2048];
        Texture.anIntArray1470 = new int[2048];
        Texture.anIntArray1471 = new int[2048];
        for (int i = 1; i < 512; ++i) {
            Texture.anIntArray1468[i] = 32768 / i;
        }
        for (int j = 1; j < 2048; ++j) {
            Texture.anIntArray1469[j] = 65536 / j;
        }
        for (int k = 0; k < 2048; ++k) {
            Texture.anIntArray1470[k] = (int)(65536.0 * Math.sin(k * 0.0030679615));
            Texture.anIntArray1471[k] = (int)(65536.0 * Math.cos(k * 0.0030679615));
        }
    }
}
