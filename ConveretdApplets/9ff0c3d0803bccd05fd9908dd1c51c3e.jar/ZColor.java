import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class ZColor
{
    static final int Z_CURRENT = 0;
    static final int Z_DEFAULT = 1;
    static final int Z_BLACK = 2;
    static final int Z_RED = 3;
    static final int Z_GREEN = 4;
    static final int Z_YELLOW = 5;
    static final int Z_BLUE = 6;
    static final int Z_MAGENTA = 7;
    static final int Z_CYAN = 8;
    static final int Z_WHITE = 9;
    
    static Color getcolor(final int number) {
        switch (number) {
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
    
    static Color getcolor(final String name) {
        if (name.equalsIgnoreCase("black")) {
            return Color.black;
        }
        if (name.equalsIgnoreCase("red")) {
            return Color.red;
        }
        if (name.equalsIgnoreCase("green")) {
            return Color.green;
        }
        if (name.equalsIgnoreCase("yellow")) {
            return Color.yellow;
        }
        if (name.equalsIgnoreCase("blue")) {
            return Color.blue;
        }
        if (name.equalsIgnoreCase("magenta")) {
            return Color.magenta;
        }
        if (name.equalsIgnoreCase("cyan")) {
            return Color.cyan;
        }
        if (name.equalsIgnoreCase("white")) {
            return Color.white;
        }
        return Color.gray;
    }
    
    static int getcolornumber(final String name) {
        if (name.equalsIgnoreCase("black")) {
            return 2;
        }
        if (name.equalsIgnoreCase("red")) {
            return 3;
        }
        if (name.equalsIgnoreCase("green")) {
            return 4;
        }
        if (name.equalsIgnoreCase("yellow")) {
            return 5;
        }
        if (name.equalsIgnoreCase("blue")) {
            return 6;
        }
        if (name.equalsIgnoreCase("magenta")) {
            return 7;
        }
        if (name.equalsIgnoreCase("cyan")) {
            return 8;
        }
        if (name.equalsIgnoreCase("white")) {
            return 9;
        }
        return 2;
    }
}
