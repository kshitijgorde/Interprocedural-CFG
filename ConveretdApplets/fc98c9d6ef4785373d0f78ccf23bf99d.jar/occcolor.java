import java.util.StringTokenizer;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class occcolor
{
    public static Color ConvertColor(final String s) {
        int int1 = 0;
        int int2 = 0;
        int int3 = 0;
        s.trim();
        final int index = s.indexOf("#");
        if (index != -1 && index + 1 != s.length()) {
            return new Color(Integer.parseInt(s.substring(index + 1), 16));
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        if (stringTokenizer.countTokens() > 3 || stringTokenizer.countTokens() < 3) {
            return Color.black;
        }
        while (stringTokenizer.hasMoreTokens()) {
            int1 = Integer.parseInt(stringTokenizer.nextToken().trim());
            int2 = Integer.parseInt(stringTokenizer.nextToken().trim());
            int3 = Integer.parseInt(stringTokenizer.nextToken().trim());
        }
        if (int1 > 255) {
            int1 = 255;
        }
        if (int2 > 255) {
            int2 = 255;
        }
        if (int3 > 255) {
            int3 = 255;
        }
        return new Color(int1, int2, int3);
    }
}
