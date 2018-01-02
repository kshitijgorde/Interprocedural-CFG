// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Color;

public class bd
{
    public static Color a(String substring) {
        if (substring.startsWith("#")) {
            substring = substring.substring(1);
        }
        return new Color(Integer.parseInt(substring, 16));
    }
    
    public static String b(final Color color) {
        return Integer.toString(color.getRGB() & 0xFFFFFF, 16);
    }
}
