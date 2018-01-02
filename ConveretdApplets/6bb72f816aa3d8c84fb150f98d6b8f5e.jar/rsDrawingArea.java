// 
// Decompiled by Procyon v0.5.30
// 

public class rsDrawingArea extends DrawingArea
{
    public static int height;
    public static int bottomX;
    public static int[] anIntArray1399;
    public static int topX;
    public static int width;
    public static int[] pixels;
    public static int topY;
    public static int bottomY;
    public static int[] anIntArray1405;
    
    public static void drawBox(final int n, final int n2, final int n3, final int n4, final int n5) {
        drawHorizontalLine(n, n2, n3, n5);
        drawHorizontalLine(n, n2 + n4 - 1, n3, n5);
        drawVerticalLine(n, n2, n4, n5);
        drawVerticalLine(n + n3 - 1, n2, n4, n5);
    }
    
    public static void setDefaultDrawingArea() {
        rsDrawingArea.topX = 0;
        rsDrawingArea.topY = 0;
        rsDrawingArea.bottomX = rsDrawingArea.width;
        rsDrawingArea.bottomY = rsDrawingArea.height;
        clear();
    }
    
    public static void method1417(int i, int j, int n, int n2, final int n3) {
        n -= i;
        n2 -= j;
        if (n2 == 0) {
            if (n >= 0) {
                drawHorizontalLine(i, j, n + 1, n3);
            }
            else {
                drawHorizontalLine(i + n, j, -n + 1, n3);
            }
        }
        else if (n == 0) {
            if (n2 >= 0) {
                drawVerticalLine(i, j, n2 + 1, n3);
            }
            else {
                drawVerticalLine(i, j + n2, -n2 + 1, n3);
            }
        }
        else {
            if (n + n2 < 0) {
                i += n;
                n = -n;
                j += n2;
                n2 = -n2;
            }
            if (n > n2) {
                j <<= 16;
                j += 32768;
                n2 <<= 16;
                final int n4 = (int)Math.floor(n2 / n + 0.5);
                n += i;
                if (i < rsDrawingArea.topX) {
                    j += n4 * (rsDrawingArea.topX - i);
                    i = rsDrawingArea.topX;
                }
                if (n >= rsDrawingArea.bottomX) {
                    n = rsDrawingArea.bottomX - 1;
                }
                while (i <= n) {
                    final int n5 = j >> 16;
                    if (n5 >= rsDrawingArea.topY && n5 < rsDrawingArea.bottomY) {
                        rsDrawingArea.pixels[i + n5 * n] = n3;
                    }
                    j += n4;
                    ++i;
                }
            }
            else {
                i <<= 16;
                i += 32768;
                n <<= 16;
                final int n6 = (int)Math.floor(n / n2 + 0.5);
                n2 += j;
                if (j < rsDrawingArea.topY) {
                    i += n6 * (rsDrawingArea.topY - j);
                    j = rsDrawingArea.topY;
                }
                if (n2 >= rsDrawingArea.bottomY) {
                    n2 = rsDrawingArea.bottomY - 1;
                }
                while (j <= n2) {
                    final int n7 = i >> 16;
                    if (n7 >= rsDrawingArea.topX && n7 < rsDrawingArea.bottomX) {
                        rsDrawingArea.pixels[n7 + j * n] = n3;
                    }
                    i += n6;
                    ++j;
                }
            }
        }
    }
    
    public static void clearDrawingArea() {
        int i;
        int n;
        for (i = 0, n = rsDrawingArea.width * rsDrawingArea.height - 7; i < n; rsDrawingArea.pixels[i++] = 0, rsDrawingArea.pixels[i++] = 0, rsDrawingArea.pixels[i++] = 0, rsDrawingArea.pixels[i++] = 0, rsDrawingArea.pixels[i++] = 0, rsDrawingArea.pixels[i++] = 0, rsDrawingArea.pixels[i++] = 0, rsDrawingArea.pixels[i++] = 0) {}
        for (n += 7; i < n; rsDrawingArea.pixels[i++] = 0) {}
    }
    
    public static void method1419(int i, int j, final int n, final int[] array, final int[] array2) {
        int n2 = i + j * rsDrawingArea.width;
        int n3;
        for (j = 0; j < array.length; ++j) {
            n3 = n2 + array[j];
            for (i = -array2[j]; i < 0; ++i) {
                rsDrawingArea.pixels[n3++] = n;
            }
            n2 += rsDrawingArea.width;
        }
    }
    
    public static void nullLoader() {
        rsDrawingArea.pixels = null;
        rsDrawingArea.anIntArray1399 = null;
        rsDrawingArea.anIntArray1405 = null;
    }
    
    public static void drawVerticalLineAlpha(final int n, int topY, int n2, final int n3, final int n4) {
        if (n >= rsDrawingArea.topX && n < rsDrawingArea.bottomX) {
            if (topY < rsDrawingArea.topY) {
                n2 -= rsDrawingArea.topY - topY;
                topY = rsDrawingArea.topY;
            }
            if (topY + n2 > rsDrawingArea.bottomY) {
                n2 = rsDrawingArea.bottomY - topY;
            }
            final int n5 = 256 - n4;
            final int n6 = (n3 >> 16 & 0xFF) * n4;
            final int n7 = (n3 >> 8 & 0xFF) * n4;
            final int n8 = (n3 & 0xFF) * n4;
            int n9 = n + topY * rsDrawingArea.width;
            for (int i = 0; i < n2; ++i) {
                rsDrawingArea.pixels[n9] = (n6 + (rsDrawingArea.pixels[n9] >> 16 & 0xFF) * n5 >> 8 << 16) + (n7 + (rsDrawingArea.pixels[n9] >> 8 & 0xFF) * n5 >> 8 << 8) + (n8 + (rsDrawingArea.pixels[n9] & 0xFF) * n5 >> 8);
                n9 += rsDrawingArea.width;
            }
        }
    }
    
    public static void setDrawingArea(final int[] array) {
        rsDrawingArea.topX = array[0];
        rsDrawingArea.topY = array[1];
        rsDrawingArea.bottomX = array[2];
        rsDrawingArea.bottomY = array[3];
        clear();
    }
    
    public static void startSetAreaColor(final int n, final int n2, final int n3) {
        if (n >= rsDrawingArea.topX && n2 >= rsDrawingArea.topY && n < rsDrawingArea.bottomX && n2 < rsDrawingArea.bottomY) {
            rsDrawingArea.pixels[n + n2 * rsDrawingArea.width] = n3;
        }
    }
    
    public static void method1424(final int[] anIntArray1399, final int[] anIntArray1400) {
        if (anIntArray1399.length != rsDrawingArea.bottomY - rsDrawingArea.topY || anIntArray1400.length != rsDrawingArea.bottomY - rsDrawingArea.topY) {
            throw new IllegalArgumentException();
        }
        rsDrawingArea.anIntArray1399 = anIntArray1399;
        rsDrawingArea.anIntArray1405 = anIntArray1400;
    }
    
    public static void method1425(final int n, int n2, int n3, final int n4) {
        if (n3 == 0) {
            startSetAreaColor(n, n2, n4);
        }
        else {
            if (n3 < 0) {
                n3 = -n3;
            }
            int topY = n2 - n3;
            if (topY < rsDrawingArea.topY) {
                topY = rsDrawingArea.topY;
            }
            int bottomY = n2 + n3 + 1;
            if (bottomY > rsDrawingArea.bottomY) {
                bottomY = rsDrawingArea.bottomY;
            }
            int i = topY;
            final int n5 = n3 * n3;
            int n6 = 0;
            int n7 = n2 - i;
            int n8 = n7 * n7;
            int n9 = n8 - n7;
            if (n2 > bottomY) {
                n2 = bottomY;
            }
            while (i < n2) {
                while (n9 <= n5 || n8 <= n5) {
                    n8 += n6 + n6;
                    n9 += n6 + ++n6;
                }
                int topX = n - n6 + 1;
                if (topX < rsDrawingArea.topX) {
                    topX = rsDrawingArea.topX;
                }
                int bottomX = n + n6;
                if (bottomX > rsDrawingArea.bottomX) {
                    bottomX = rsDrawingArea.bottomX;
                }
                int n10 = topX + i * rsDrawingArea.width;
                for (int j = topX; j < bottomX; ++j) {
                    rsDrawingArea.pixels[n10++] = n4;
                }
                ++i;
                n8 -= n7 + --n7;
                n9 -= n7 + n7;
            }
            int n11 = n3;
            int n12 = i - n2;
            final int n13 = n12 * n12 + n5;
            for (int n14 = n13 - n11, n15 = n13 - n12; i < bottomY; ++i, n15 += n12 + n12, n14 += n12 + ++n12) {
                while (n15 > n5 && n14 > n5) {
                    n15 -= n11 + --n11;
                    n14 -= n11 + n11;
                }
                int topX2 = n - n11;
                if (topX2 < rsDrawingArea.topX) {
                    topX2 = rsDrawingArea.topX;
                }
                int n16 = n + n11;
                if (n16 > rsDrawingArea.bottomX - 1) {
                    n16 = rsDrawingArea.bottomX - 1;
                }
                int n17 = topX2 + i * rsDrawingArea.width;
                for (int k = topX2; k <= n16; ++k) {
                    rsDrawingArea.pixels[n17++] = n4;
                }
            }
        }
    }
    
    public static void createDrawingArea(final int topX, final int topY, final int bottomX, final int bottomY) {
        if (rsDrawingArea.topX < topX) {
            rsDrawingArea.topX = topX;
        }
        if (rsDrawingArea.topY < topY) {
            rsDrawingArea.topY = topY;
        }
        if (rsDrawingArea.bottomX > bottomX) {
            rsDrawingArea.bottomX = bottomX;
        }
        if (rsDrawingArea.bottomY > bottomY) {
            rsDrawingArea.bottomY = bottomY;
        }
        clear();
    }
    
    public static void setDrawingArea(int topX, int topY, int width, int height) {
        if (topX < 0) {
            topX = 0;
        }
        if (topY < 0) {
            topY = 0;
        }
        if (width > rsDrawingArea.width) {
            width = rsDrawingArea.width;
        }
        if (height > rsDrawingArea.height) {
            height = rsDrawingArea.height;
        }
        rsDrawingArea.topX = topX;
        rsDrawingArea.topY = topY;
        rsDrawingArea.bottomX = width;
        rsDrawingArea.bottomY = height;
        clear();
    }
    
    public static void drawHorizontalLine(int topX, final int n, int n2, final int n3) {
        if (n >= rsDrawingArea.topY && n < rsDrawingArea.bottomY) {
            if (topX < rsDrawingArea.topX) {
                n2 -= rsDrawingArea.topX - topX;
                topX = rsDrawingArea.topX;
            }
            if (topX + n2 > rsDrawingArea.bottomX) {
                n2 = rsDrawingArea.bottomX - topX;
            }
            final int n4 = topX + n * rsDrawingArea.width;
            for (int i = 0; i < n2; ++i) {
                rsDrawingArea.pixels[n4 + i] = n3;
            }
        }
    }
    
    public static void drawHorizontalLineAlpha(int topX, final int n, int n2, final int n3, final int n4) {
        if (n >= rsDrawingArea.topY && n < rsDrawingArea.bottomY) {
            if (topX < rsDrawingArea.topX) {
                n2 -= rsDrawingArea.topX - topX;
                topX = rsDrawingArea.topX;
            }
            if (topX + n2 > rsDrawingArea.bottomX) {
                n2 = rsDrawingArea.bottomX - topX;
            }
            final int n5 = 256 - n4;
            final int n6 = (n3 >> 16 & 0xFF) * n4;
            final int n7 = (n3 >> 8 & 0xFF) * n4;
            final int n8 = (n3 & 0xFF) * n4;
            int n9 = topX + n * rsDrawingArea.width;
            for (int i = 0; i < n2; ++i) {
                rsDrawingArea.pixels[n9++] = (n6 + (rsDrawingArea.pixels[n9] >> 16 & 0xFF) * n5 >> 8 << 16) + (n7 + (rsDrawingArea.pixels[n9] >> 8 & 0xFF) * n5 >> 8 << 8) + (n8 + (rsDrawingArea.pixels[n9] & 0xFF) * n5 >> 8);
            }
        }
    }
    
    public static void drawVerticalLine(final int n, int topY, int n2, final int n3) {
        if (n >= rsDrawingArea.topX && n < rsDrawingArea.bottomX) {
            if (topY < rsDrawingArea.topY) {
                n2 -= rsDrawingArea.topY - topY;
                topY = rsDrawingArea.topY;
            }
            if (topY + n2 > rsDrawingArea.bottomY) {
                n2 = rsDrawingArea.bottomY - topY;
            }
            final int n4 = n + topY * rsDrawingArea.width;
            for (int i = 0; i < n2; ++i) {
                rsDrawingArea.pixels[n4 + i * rsDrawingArea.width] = n3;
            }
        }
    }
    
    public static void initDrawingArea(final int[] pixels, final int width, final int height) {
        rsDrawingArea.pixels = pixels;
        setDrawingArea(0, 0, rsDrawingArea.width = width, rsDrawingArea.height = height);
    }
    
    public static void drawBoxAlpha(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        drawHorizontalLineAlpha(n, n2, n3, n5, n6);
        drawHorizontalLineAlpha(n, n2 + n4 - 1, n3, n5, n6);
        if (n4 >= 3) {
            drawVerticalLineAlpha(n, n2 + 1, n4 - 2, n5, n6);
            drawVerticalLineAlpha(n + n3 - 1, n2 + 1, n4 - 2, n5, n6);
        }
    }
    
    public static void method1434(final int n, int n2, int n3, final int n4, final int n5) {
        if (n5 != 0) {
            if (n5 == 256) {
                method1425(n, n2, n3, n4);
            }
            else {
                if (n3 < 0) {
                    n3 = -n3;
                }
                final int n6 = 256 - n5;
                final int n7 = (n4 >> 16 & 0xFF) * n5;
                final int n8 = (n4 >> 8 & 0xFF) * n5;
                final int n9 = (n4 & 0xFF) * n5;
                int topY = n2 - n3;
                if (topY < rsDrawingArea.topY) {
                    topY = rsDrawingArea.topY;
                }
                int bottomY = n2 + n3 + 1;
                if (bottomY > rsDrawingArea.bottomY) {
                    bottomY = rsDrawingArea.bottomY;
                }
                int i = topY;
                final int n10 = n3 * n3;
                int n11 = 0;
                int n12 = n2 - i;
                int n13 = n12 * n12;
                int n14 = n13 - n12;
                if (n2 > bottomY) {
                    n2 = bottomY;
                }
                while (i < n2) {
                    while (n14 <= n10 || n13 <= n10) {
                        n13 += n11 + n11;
                        n14 += n11 + ++n11;
                    }
                    int topX = n - n11 + 1;
                    if (topX < rsDrawingArea.topX) {
                        topX = rsDrawingArea.topX;
                    }
                    int bottomX = n + n11;
                    if (bottomX > rsDrawingArea.bottomX) {
                        bottomX = rsDrawingArea.bottomX;
                    }
                    int n15 = topX + i * rsDrawingArea.width;
                    for (int j = topX; j < bottomX; ++j) {
                        rsDrawingArea.pixels[n15++] = (n7 + (rsDrawingArea.pixels[n15] >> 16 & 0xFF) * n6 >> 8 << 16) + (n8 + (rsDrawingArea.pixels[n15] >> 8 & 0xFF) * n6 >> 8 << 8) + (n9 + (rsDrawingArea.pixels[n15] & 0xFF) * n6 >> 8);
                    }
                    ++i;
                    n13 -= n12 + --n12;
                    n14 -= n12 + n12;
                }
                int n16 = n3;
                int n17 = -n12;
                final int n18 = n17 * n17 + n10;
                for (int n19 = n18 - n16, n20 = n18 - n17; i < bottomY; ++i, n20 += n17 + n17, n19 += n17 + ++n17) {
                    while (n20 > n10 && n19 > n10) {
                        n20 -= n16 + --n16;
                        n19 -= n16 + n16;
                    }
                    int topX2 = n - n16;
                    if (topX2 < rsDrawingArea.topX) {
                        topX2 = rsDrawingArea.topX;
                    }
                    int n21 = n + n16;
                    if (n21 > rsDrawingArea.bottomX - 1) {
                        n21 = rsDrawingArea.bottomX - 1;
                    }
                    int n22 = topX2 + i * rsDrawingArea.width;
                    for (int k = topX2; k <= n21; ++k) {
                        rsDrawingArea.pixels[n22++] = (n7 + (rsDrawingArea.pixels[n22] >> 16 & 0xFF) * n6 >> 8 << 16) + (n8 + (rsDrawingArea.pixels[n22] >> 8 & 0xFF) * n6 >> 8 << 8) + (n9 + (rsDrawingArea.pixels[n22] & 0xFF) * n6 >> 8);
                    }
                }
            }
        }
    }
    
    public static void fillBoxAlpha(int topX, int topY, int n, int n2, int n3, final int n4) {
        if (topX < rsDrawingArea.topX) {
            n -= rsDrawingArea.topX - topX;
            topX = rsDrawingArea.topX;
        }
        if (topY < rsDrawingArea.topY) {
            n2 -= rsDrawingArea.topY - topY;
            topY = rsDrawingArea.topY;
        }
        if (topX + n > rsDrawingArea.bottomX) {
            n = rsDrawingArea.bottomX - topX;
        }
        if (topY + n2 > rsDrawingArea.bottomY) {
            n2 = rsDrawingArea.bottomY - topY;
        }
        n3 = ((n3 & 0xFF00FF) * n4 >> 8 & 0xFF00FF) + ((n3 & 0xFF00) * n4 >> 8 & 0xFF00);
        final int n5 = 256 - n4;
        final int n6 = rsDrawingArea.width - n;
        int n7 = topX + topY * rsDrawingArea.width;
        for (int i = 0; i < n2; ++i) {
            for (int j = -n; j < 0; ++j) {
                final int n8 = rsDrawingArea.pixels[n7];
                rsDrawingArea.pixels[n7++] = n3 + (((n8 & 0xFF00FF) * n5 >> 8 & 0xFF00FF) + ((n8 & 0xFF00) * n5 >> 8 & 0xFF00));
            }
            n7 += n6;
        }
    }
    
    public static void method1436(final int[] array) {
        array[0] = rsDrawingArea.topX;
        array[1] = rsDrawingArea.topY;
        array[2] = rsDrawingArea.bottomX;
        array[3] = rsDrawingArea.bottomY;
    }
    
    public static void method1437(int topX, int topY, int n, int n2, final int n3, final int n4) {
        int n5 = 0;
        final int n6 = 65536 / n2;
        if (topX < rsDrawingArea.topX) {
            n -= rsDrawingArea.topX - topX;
            topX = rsDrawingArea.topX;
        }
        if (topY < rsDrawingArea.topY) {
            n5 += (rsDrawingArea.topY - topY) * n6;
            n2 -= rsDrawingArea.topY - topY;
            topY = rsDrawingArea.topY;
        }
        if (topX + n > rsDrawingArea.bottomX) {
            n = rsDrawingArea.bottomX - topX;
        }
        if (topY + n2 > rsDrawingArea.bottomY) {
            n2 = rsDrawingArea.bottomY - topY;
        }
        final int n7 = rsDrawingArea.width - n;
        int n8 = topX + topY * rsDrawingArea.width;
        for (int i = -n2; i < 0; ++i) {
            final int n9 = 65536 - n5 >> 8;
            final int n10 = n5 >> 8;
            final int n11 = ((n3 & 0xFF00FF) * n9 + (n4 & 0xFF00FF) * n10 & 0xFF00FF00) + ((n3 & 0xFF00) * n9 + (n4 & 0xFF00) * n10 & 0xFF0000) >>> 8;
            for (int j = -n; j < 0; ++j) {
                rsDrawingArea.pixels[n8++] = n11;
            }
            n8 += n7;
            n5 += n6;
        }
    }
    
    public static void clear() {
        rsDrawingArea.anIntArray1399 = null;
        rsDrawingArea.anIntArray1405 = null;
    }
    
    public static void fillBox(int topX, int topY, int n, int n2, final int n3) {
        if (topX < rsDrawingArea.topX) {
            n -= rsDrawingArea.topX - topX;
            topX = rsDrawingArea.topX;
        }
        if (topY < rsDrawingArea.topY) {
            n2 -= rsDrawingArea.topY - topY;
            topY = rsDrawingArea.topY;
        }
        if (topX + n > rsDrawingArea.bottomX) {
            n = rsDrawingArea.bottomX - topX;
        }
        if (topY + n2 > rsDrawingArea.bottomY) {
            n2 = rsDrawingArea.bottomY - topY;
        }
        final int n4 = rsDrawingArea.width - n;
        int n5 = topX + topY * rsDrawingArea.width;
        for (int i = -n2; i < 0; ++i) {
            for (int j = -n; j < 0; ++j) {
                rsDrawingArea.pixels[n5++] = n3;
            }
            n5 += n4;
        }
    }
    
    static {
        rsDrawingArea.bottomY = 0;
        rsDrawingArea.bottomX = 0;
        rsDrawingArea.topX = 0;
        rsDrawingArea.topY = 0;
    }
}
