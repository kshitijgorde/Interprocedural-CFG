import java.util.StringTokenizer;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

class ocfontc
{
    static Font getFontSD(final String s, final String s2) {
        Font font = new Font("Helvetica", 0, 12);
        final String[] array = new String[3];
        int n = 0;
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
            s.trim();
            if (stringTokenizer.countTokens() != 3) {
                return font;
            }
            while (stringTokenizer.hasMoreTokens()) {
                array[n] = stringTokenizer.nextToken().trim();
                ++n;
            }
            font = new Font(array[0], FontType(array[1]), Integer.parseInt(array[2]));
        }
        catch (Exception ex) {
            return font;
        }
        return font;
    }
    
    public static int FontType(final String s) {
        int n = 0;
        if (s.equalsIgnoreCase("bold")) {
            ++n;
        }
        if (s.equalsIgnoreCase("bolditalic")) {
            n += 3;
        }
        if (s.equalsIgnoreCase("italic")) {
            n += 2;
        }
        return n;
    }
}
