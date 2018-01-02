// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util;

import java.awt.Font;
import prefuse.util.collections.IntObjectHashMap;

public class FontLib
{
    private static final IntObjectHashMap fontMap;
    private static int misses;
    private static int lookups;
    
    public static Font getFont(final String s, final double n) {
        return getFont(s, 0, (int)Math.floor(n));
    }
    
    public static Font getFont(final String s, final int n, final double n2) {
        return getFont(s, n, (int)Math.floor(n2));
    }
    
    public static Font getFont(final String s, final int n, final int n2) {
        final int n3 = (s.hashCode() << 8) + (n2 << 2) + n;
        Font font;
        if ((font = (Font)FontLib.fontMap.get(n3)) == null) {
            font = new Font(s, n, n2);
            FontLib.fontMap.put(n3, font);
            ++FontLib.misses;
        }
        ++FontLib.lookups;
        return font;
    }
    
    public static int getCacheMissCount() {
        return FontLib.misses;
    }
    
    public static int getCacheLookupCount() {
        return FontLib.lookups;
    }
    
    public static void clearCache() {
        FontLib.fontMap.clear();
    }
    
    public static Font getIntermediateFont(final Font font, final Font font2, final double n) {
        String s;
        int n2;
        if (n < 0.5) {
            s = font.getName();
            n2 = font.getStyle();
        }
        else {
            s = font2.getName();
            n2 = font2.getStyle();
        }
        return getFont(s, n2, (int)Math.round(n * font2.getSize() + (1.0 - n) * font.getSize()));
    }
    
    static {
        fontMap = new IntObjectHashMap();
        FontLib.misses = 0;
        FontLib.lookups = 0;
    }
}
