// 
// Decompiled by Procyon v0.5.30
// 

public class DrawingArea extends NodeSub
{
    public static int[] pixels;
    public static int width;
    public static int height;
    public static int topY;
    public static int bottomY;
    public static int topX;
    public static int bottomX;
    public static int centerX;
    public static int centerY;
    public static int anInt1387;
    
    public static void initDrawingArea(final int height, final int width, final int[] pixels) {
        DrawingArea.pixels = pixels;
        DrawingArea.width = width;
        setDrawingArea(DrawingArea.height = height, 0, width, 0);
    }
    
    public static void defaultDrawingAreaSize() {
        DrawingArea.topX = 0;
        DrawingArea.topY = 0;
        DrawingArea.bottomX = DrawingArea.width;
        DrawingArea.bottomY = DrawingArea.height;
        DrawingArea.centerX = DrawingArea.bottomX;
        DrawingArea.centerY = DrawingArea.bottomX / 2;
    }
    
    public static void setDrawingArea(int height, int topX, int width, int topY) {
        if (topX < 0) {
            topX = 0;
        }
        if (topY < 0) {
            topY = 0;
        }
        if (width > DrawingArea.width) {
            width = DrawingArea.width;
        }
        if (height > DrawingArea.height) {
            height = DrawingArea.height;
        }
        DrawingArea.topX = topX;
        DrawingArea.topY = topY;
        DrawingArea.bottomX = width;
        DrawingArea.bottomY = height;
        DrawingArea.centerX = DrawingArea.bottomX;
        DrawingArea.centerY = DrawingArea.bottomX / 2;
        DrawingArea.anInt1387 = DrawingArea.bottomY / 2;
    }
    
    public static void setAllPixelsToZero() {
        for (int n = DrawingArea.width * DrawingArea.height, i = 0; i < n; ++i) {
            DrawingArea.pixels[i] = 0;
        }
    }
    
    public static void method335(final int n, int topY, int n2, int n3, final int n4, int topX) {
        if (topX < DrawingArea.topX) {
            n2 -= DrawingArea.topX - topX;
            topX = DrawingArea.topX;
        }
        if (topY < DrawingArea.topY) {
            n3 -= DrawingArea.topY - topY;
            topY = DrawingArea.topY;
        }
        if (topX + n2 > DrawingArea.bottomX) {
            n2 = DrawingArea.bottomX - topX;
        }
        if (topY + n3 > DrawingArea.bottomY) {
            n3 = DrawingArea.bottomY - topY;
        }
        final int n5 = 256 - n4;
        final int n6 = (n >> 16 & 0xFF) * n4;
        final int n7 = (n >> 8 & 0xFF) * n4;
        final int n8 = (n & 0xFF) * n4;
        final int n9 = DrawingArea.width - n2;
        int n10 = topX + topY * DrawingArea.width;
        for (int i = 0; i < n3; ++i) {
            for (int j = -n2; j < 0; ++j) {
                DrawingArea.pixels[n10++] = (n6 + (DrawingArea.pixels[n10] >> 16 & 0xFF) * n5 >> 8 << 16) + (n7 + (DrawingArea.pixels[n10] >> 8 & 0xFF) * n5 >> 8 << 8) + (n8 + (DrawingArea.pixels[n10] & 0xFF) * n5 >> 8);
            }
            n10 += n9;
        }
    }
    
    public static void drawPixels(int n, int topY, int topX, final int n2, int n3) {
        if (topX < DrawingArea.topX) {
            n3 -= DrawingArea.topX - topX;
            topX = DrawingArea.topX;
        }
        if (topY < DrawingArea.topY) {
            n -= DrawingArea.topY - topY;
            topY = DrawingArea.topY;
        }
        if (topX + n3 > DrawingArea.bottomX) {
            n3 = DrawingArea.bottomX - topX;
        }
        if (topY + n > DrawingArea.bottomY) {
            n = DrawingArea.bottomY - topY;
        }
        final int n4 = DrawingArea.width - n3;
        int n5 = topX + topY * DrawingArea.width;
        for (int i = -n; i < 0; ++i) {
            for (int j = -n3; j < 0; ++j) {
                DrawingArea.pixels[n5++] = n2;
            }
            n5 += n4;
        }
    }
    
    public static void fillPixels(final int n, final int n2, final int n3, final int n4, final int n5) {
        method339(n5, n4, n2, n);
        method339(n5 + n3 - 1, n4, n2, n);
        method341(n5, n4, n3, n);
        method341(n5, n4, n3, n + n2 - 1);
    }
    
    public static void method338(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        method340(n4, n5, n, n3, n6);
        method340(n4, n5, n + n2 - 1, n3, n6);
        if (n2 >= 3) {
            method342(n4, n6, n3, n + 1, n2 - 2);
            method342(n4, n6 + n5 - 1, n3, n + 1, n2 - 2);
        }
    }
    
    public static void method339(final int n, final int n2, int n3, int topX) {
        if (n < DrawingArea.topY || n >= DrawingArea.bottomY) {
            return;
        }
        if (topX < DrawingArea.topX) {
            n3 -= DrawingArea.topX - topX;
            topX = DrawingArea.topX;
        }
        if (topX + n3 > DrawingArea.bottomX) {
            n3 = DrawingArea.bottomX - topX;
        }
        final int n4 = topX + n * DrawingArea.width;
        for (int i = 0; i < n3; ++i) {
            DrawingArea.pixels[n4 + i] = n2;
        }
    }
    
    private static void method340(final int n, int n2, final int n3, final int n4, int topX) {
        if (n3 < DrawingArea.topY || n3 >= DrawingArea.bottomY) {
            return;
        }
        if (topX < DrawingArea.topX) {
            n2 -= DrawingArea.topX - topX;
            topX = DrawingArea.topX;
        }
        if (topX + n2 > DrawingArea.bottomX) {
            n2 = DrawingArea.bottomX - topX;
        }
        final int n5 = 256 - n4;
        final int n6 = (n >> 16 & 0xFF) * n4;
        final int n7 = (n >> 8 & 0xFF) * n4;
        final int n8 = (n & 0xFF) * n4;
        int n9 = topX + n3 * DrawingArea.width;
        for (int i = 0; i < n2; ++i) {
            DrawingArea.pixels[n9++] = (n6 + (DrawingArea.pixels[n9] >> 16 & 0xFF) * n5 >> 8 << 16) + (n7 + (DrawingArea.pixels[n9] >> 8 & 0xFF) * n5 >> 8 << 8) + (n8 + (DrawingArea.pixels[n9] & 0xFF) * n5 >> 8);
        }
    }
    
    public static void method341(int topY, final int n, int n2, final int n3) {
        if (n3 < DrawingArea.topX || n3 >= DrawingArea.bottomX) {
            return;
        }
        if (topY < DrawingArea.topY) {
            n2 -= DrawingArea.topY - topY;
            topY = DrawingArea.topY;
        }
        if (topY + n2 > DrawingArea.bottomY) {
            n2 = DrawingArea.bottomY - topY;
        }
        final int n4 = n3 + topY * DrawingArea.width;
        for (int i = 0; i < n2; ++i) {
            DrawingArea.pixels[n4 + i * DrawingArea.width] = n;
        }
    }
    
    private static void method342(final int n, final int n2, final int n3, int topY, int n4) {
        if (n2 < DrawingArea.topX || n2 >= DrawingArea.bottomX) {
            return;
        }
        if (topY < DrawingArea.topY) {
            n4 -= DrawingArea.topY - topY;
            topY = DrawingArea.topY;
        }
        if (topY + n4 > DrawingArea.bottomY) {
            n4 = DrawingArea.bottomY - topY;
        }
        final int n5 = 256 - n3;
        final int n6 = (n >> 16 & 0xFF) * n3;
        final int n7 = (n >> 8 & 0xFF) * n3;
        final int n8 = (n & 0xFF) * n3;
        int n9 = n2 + topY * DrawingArea.width;
        for (int i = 0; i < n4; ++i) {
            DrawingArea.pixels[n9] = (n6 + (DrawingArea.pixels[n9] >> 16 & 0xFF) * n5 >> 8 << 16) + (n7 + (DrawingArea.pixels[n9] >> 8 & 0xFF) * n5 >> 8 << 8) + (n8 + (DrawingArea.pixels[n9] & 0xFF) * n5 >> 8);
            n9 += DrawingArea.width;
        }
    }
    
    public static void method336(int n, int topY, int topX, final int n2, int n3) {
        if (topX < DrawingArea.topX) {
            n3 -= DrawingArea.topX - topX;
            topX = DrawingArea.topX;
        }
        if (topY < DrawingArea.topY) {
            n -= DrawingArea.topY - topY;
            topY = DrawingArea.topY;
        }
        if (topX + n3 > DrawingArea.bottomX) {
            n3 = DrawingArea.bottomX - topX;
        }
        if (topY + n > DrawingArea.bottomY) {
            n = DrawingArea.bottomY - topY;
        }
        final int n4 = DrawingArea.width - n3;
        int n5 = topX + topY * DrawingArea.width;
        for (int i = -n; i < 0; ++i) {
            for (int j = -n3; j < 0; ++j) {
                DrawingArea.pixels[n5++] = n2;
            }
            n5 += n4;
        }
    }
}
