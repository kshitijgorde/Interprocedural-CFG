// 
// Decompiled by Procyon v0.5.30
// 

package russotto.zplet;

import java.awt.Color;

public class ZColor
{
    public static final int Z_CURRENT = 0;
    public static final int Z_DEFAULT = 1;
    public static final int Z_BLACK = 2;
    public static final int Z_RED = 3;
    public static final int Z_GREEN = 4;
    public static final int Z_YELLOW = 5;
    public static final int Z_BLUE = 6;
    public static final int Z_MAGENTA = 7;
    public static final int Z_CYAN = 8;
    public static final int Z_WHITE = 9;
    
    public static Color getcolor(final int n) {
        switch (n) {
            case 2: {
                return Color.black;
            }
            case 3: {
                return Color.red;
            }
            case 4: {
                return Color.green;
            }
            case 5: {
                return Color.yellow;
            }
            case 6: {
                return Color.blue;
            }
            case 7: {
                return Color.magenta;
            }
            case 8: {
                return Color.cyan;
            }
            case 9: {
                return Color.white;
            }
            default: {
                return Color.gray;
            }
        }
    }
    
    public static Color getcolor(final String s) {
        if (s.equalsIgnoreCase("black")) {
            return Color.black;
        }
        if (s.equalsIgnoreCase("red")) {
            return Color.red;
        }
        if (s.equalsIgnoreCase("green")) {
            return Color.green;
        }
        if (s.equalsIgnoreCase("yellow")) {
            return Color.yellow;
        }
        if (s.equalsIgnoreCase("blue")) {
            return Color.blue;
        }
        if (s.equalsIgnoreCase("magenta")) {
            return Color.magenta;
        }
        if (s.equalsIgnoreCase("cyan")) {
            return Color.cyan;
        }
        if (s.equalsIgnoreCase("white")) {
            return Color.white;
        }
        return Color.gray;
    }
    
    public static int getcolornumber(final String s) {
        if (s.equalsIgnoreCase("black")) {
            return 2;
        }
        if (s.equalsIgnoreCase("red")) {
            return 3;
        }
        if (s.equalsIgnoreCase("green")) {
            return 4;
        }
        if (s.equalsIgnoreCase("yellow")) {
            return 5;
        }
        if (s.equalsIgnoreCase("blue")) {
            return 6;
        }
        if (s.equalsIgnoreCase("magenta")) {
            return 7;
        }
        if (s.equalsIgnoreCase("cyan")) {
            return 8;
        }
        if (s.equalsIgnoreCase("white")) {
            return 9;
        }
        return 2;
    }
}
