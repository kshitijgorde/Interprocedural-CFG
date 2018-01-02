// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util;

import java.awt.Color;
import prefuse.util.collections.IntObjectHashMap;

public class ColorLib
{
    public static final char HEX_PREFIX = '#';
    private static final IntObjectHashMap colorMap;
    private static int misses;
    private static int lookups;
    private static final float scale = 0.7f;
    public static final float[] CATEGORY_HUES;
    public static final int DEFAULT_MAP_SIZE = 64;
    
    public static int rgb(final int n, final int n2, final int n3) {
        return rgba(n, n2, n3, 255);
    }
    
    public static int gray(final int n) {
        return rgba(n, n, n, 255);
    }
    
    public static int gray(final int n, final int n2) {
        return rgba(n, n, n, n2);
    }
    
    public static int hex(String substring) {
        if (substring.charAt(0) == '#') {
            substring = substring.substring(1);
        }
        if (substring.length() > 6) {
            return setAlpha(Integer.parseInt(substring.substring(2), 16), Integer.parseInt(substring.substring(0, 2), 16));
        }
        return setAlpha(Integer.parseInt(substring, 16), 255);
    }
    
    public static int hsb(final float n, final float n2, final float n3) {
        return Color.HSBtoRGB(n, n2, n3);
    }
    
    public static int hsba(final float n, final float n2, final float n3, final float n4) {
        return setAlpha(Color.HSBtoRGB(n, n2, n3), (int)(n4 * 255.0f + 0.5) & 0xFF);
    }
    
    public static int rgba(final int n, final int n2, final int n3, final int n4) {
        return (n4 & 0xFF) << 24 | (n & 0xFF) << 16 | (n2 & 0xFF) << 8 | (n3 & 0xFF) << 0;
    }
    
    public static int rgba(final float n, final float n2, final float n3, final float n4) {
        return ((int)(n4 * 255.0f + 0.5) & 0xFF) << 24 | ((int)(n * 255.0f + 0.5) & 0xFF) << 16 | ((int)(n2 * 255.0f + 0.5) & 0xFF) << 8 | ((int)(n3 * 255.0f + 0.5) & 0xFF);
    }
    
    public static int color(final Color color) {
        return color.getRGB();
    }
    
    public static int red(final int n) {
        return n >> 16 & 0xFF;
    }
    
    public static int green(final int n) {
        return n >> 8 & 0xFF;
    }
    
    public static int blue(final int n) {
        return n & 0xFF;
    }
    
    public static int alpha(final int n) {
        return n >> 24 & 0xFF;
    }
    
    public static int setAlpha(final int n, final int n2) {
        return rgba(red(n), green(n), blue(n), n2);
    }
    
    public static Color getColor(final float n, final float n2, final float n3, final float n4) {
        return getColor(rgba(n, n2, n3, n4));
    }
    
    public static Color getColor(final float n, final float n2, final float n3) {
        return getColor(n, n2, n3, 1.0f);
    }
    
    public static Color getColor(final int n, final int n2, final int n3, final int n4) {
        return getColor(rgba(n, n2, n3, n4));
    }
    
    public static Color getColor(final int n, final int n2, final int n3) {
        return getColor(n, n2, n3, 255);
    }
    
    public static Color getGrayscale(final int n) {
        return getColor(n, n, n, 255);
    }
    
    public static Color getColor(final int n) {
        Color color;
        if ((color = (Color)ColorLib.colorMap.get(n)) == null) {
            color = new Color(n, true);
            ColorLib.colorMap.put(n, color);
            ++ColorLib.misses;
        }
        ++ColorLib.lookups;
        return color;
    }
    
    public static int getCacheMissCount() {
        return ColorLib.misses;
    }
    
    public static int getCacheLookupCount() {
        return ColorLib.lookups;
    }
    
    public static void clearCache() {
        ColorLib.colorMap.clear();
    }
    
    public static int interp(final int n, final int n2, final double n3) {
        final double n4 = 1.0 - n3;
        return rgba((int)Math.round(n3 * red(n2) + n4 * red(n)), (int)Math.round(n3 * green(n2) + n4 * green(n)), (int)Math.round(n3 * blue(n2) + n4 * blue(n)), (int)Math.round(n3 * alpha(n2) + n4 * alpha(n)));
    }
    
    public static int darker(final int n) {
        return rgba(Math.max(0, (int)(0.7f * red(n))), Math.max(0, (int)(0.7f * green(n))), Math.max(0, (int)(0.7f * blue(n))), alpha(n));
    }
    
    public static int brighter(final int n) {
        int red = red(n);
        int green = green(n);
        int blue = blue(n);
        final int n2 = 3;
        if (red == 0 && green == 0 && blue == 0) {
            return rgba(n2, n2, n2, alpha(n));
        }
        if (red > 0 && red < n2) {
            red = n2;
        }
        if (green > 0 && green < n2) {
            green = n2;
        }
        if (blue > 0 && blue < n2) {
            blue = n2;
        }
        return rgba(Math.min(255, (int)(red / 0.7f)), Math.min(255, (int)(green / 0.7f)), Math.min(255, (int)(blue / 0.7f)), alpha(n));
    }
    
    public static int desaturate(final int n) {
        final int n2 = n & 0xFF000000;
        final int n3 = Math.min((int)(((n & 0xFF0000) >> 16) * 0.2125f + ((n & 0xFF00) >> 8) * 0.7154f + (n & 0xFF) * 0.0721f), 255) & 0xFF;
        return n2 | n3 << 16 | n3 << 8 | n3;
    }
    
    public static int saturate(final int n, final float n2) {
        final float[] rgBtoHSB = Color.RGBtoHSB(red(n), green(n), blue(n), null);
        return hsb(rgBtoHSB[0], n2, rgBtoHSB[2]);
    }
    
    public static int[] getCoolPalette(final int n) {
        final int[] array = new int[n];
        for (int i = 0; i < n; ++i) {
            final float n2 = i / Math.max(n - 1, 1.0f);
            array[i] = rgba(n2, 1.0f - n2, 1.0f, 1.0f);
        }
        return array;
    }
    
    public static int[] getCoolPalette() {
        return getCoolPalette(64);
    }
    
    public static int[] getHotPalette(final int n) {
        final int[] array = new int[n];
        for (int i = 0; i < n; ++i) {
            final int n2 = 3 * n / 8;
            array[i] = rgba((i < n2) ? ((i + 1) / n2) : 1.0f, (i < n2) ? 0.0f : ((i < 2 * n2) ? ((i - n2) / n2) : 1.0f), (i < 2 * n2) ? 0.0f : ((i - 2 * n2) / (n - 2 * n2)), 1.0f);
        }
        return array;
    }
    
    public static int[] getHotPalette() {
        return getHotPalette(64);
    }
    
    public static int[] getCategoryPalette(final int n, final float n2, final float n3, final float n4, final float n5) {
        final int[] array = new int[n];
        float n6 = n2;
        for (int i = 0; i < n; ++i) {
            final int n7 = i % ColorLib.CATEGORY_HUES.length;
            if (n7 == 0) {
                n6 = n2 + i / n * (n3 - n2);
            }
            array[i] = hsba(ColorLib.CATEGORY_HUES[n7], n6, n4, n5);
        }
        return array;
    }
    
    public static int[] getCategoryPalette(final int n) {
        return getCategoryPalette(n, 1.0f, 0.4f, 1.0f, 1.0f);
    }
    
    public static int[] getHSBPalette(final int n, final float n2, final float n3) {
        final int[] array = new int[n];
        for (int i = 0; i < n; ++i) {
            array[i] = hsb(i / (n - 1), n2, n3);
        }
        return array;
    }
    
    public static int[] getHSBPalette() {
        return getHSBPalette(64, 1.0f, 1.0f);
    }
    
    public static int[] getInterpolatedPalette(final int n, final int n2, final int n3) {
        final int[] array = new int[n];
        for (int i = 0; i < n; ++i) {
            array[i] = interp(n2, n3, i / (n - 1));
        }
        return array;
    }
    
    public static int[] getInterpolatedPalette(final int n, final int n2) {
        return getInterpolatedPalette(64, n, n2);
    }
    
    public static int[] getGrayscalePalette(final int n) {
        final int[] array = new int[n];
        for (int i = 0; i < n; ++i) {
            array[n - i - 1] = gray(Math.round(255.0f * (0.2f + 0.6f * i / (n - 1))));
        }
        return array;
    }
    
    public static int[] getGrayscalePalette() {
        return getGrayscalePalette(64);
    }
    
    static {
        colorMap = new IntObjectHashMap();
        ColorLib.misses = 0;
        ColorLib.lookups = 0;
        CATEGORY_HUES = new float[] { 0.0f, 0.083333336f, 0.16666667f, 0.33333334f, 0.5f, 0.5833333f, 0.6666667f, 0.8333333f, 0.9166667f };
    }
}
