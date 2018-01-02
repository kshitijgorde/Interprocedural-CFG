// 
// Decompiled by Procyon v0.5.30
// 

package teletext;

public class TeletextCodes
{
    public static final byte ALPHA_BLACK = 0;
    public static final byte ALPHA_RED = 1;
    public static final byte ALPHA_GREEN = 2;
    public static final byte ALPHA_YELLOW = 3;
    public static final byte ALPHA_BLUE = 4;
    public static final byte ALPHA_MAGENTA = 5;
    public static final byte ALPHA_CYAN = 6;
    public static final byte ALPHA_WHITE = 7;
    public static final byte STEADY = 9;
    public static final byte FLASH = 8;
    public static final byte END_BOX = 10;
    public static final byte START_BOX = 11;
    public static final byte NORMAL_HEIGHT = 12;
    public static final byte DOUBLE_HEIGHT = 13;
    public static final byte GRAPH_BLACK = 16;
    public static final byte GRAPH_RED = 17;
    public static final byte GRAPH_GREEN = 18;
    public static final byte GRAPH_YELLOW = 19;
    public static final byte GRAPH_BLUE = 20;
    public static final byte GRAPH_MAGENTA = 21;
    public static final byte GRAPH_CYAN = 22;
    public static final byte GRAPH_WHITE = 23;
    public static final byte CONCEAL = 24;
    public static final byte GLUE_GRAPHICS = 25;
    public static final byte SEPARATE_GRAPHICS = 26;
    public static final byte AUTONUMBER = 27;
    public static final byte BLACK_BACKGROUND = 28;
    public static final byte NEW_BACKGROUND = 29;
    public static final byte HOLD_GRAPHICS = 30;
    public static final byte RELEASE_GRAPHICS = 31;
    
    public static boolean isCode(final char c) {
        return c >= '\0' && c <= '\u001f';
    }
    
    public static boolean isGraphCode(final char c) {
        return c >= '\u0010' && c <= '\u0017';
    }
    
    public static boolean isAlphaCode(final char c) {
        return c >= '\0' && c <= '\u0007';
    }
    
    public static byte codeToColor(final char c) {
        switch ((byte)c) {
            case 0:
            case 16: {
                return 0;
            }
            case 1:
            case 17: {
                return 1;
            }
            case 2:
            case 18: {
                return 2;
            }
            case 3:
            case 19: {
                return 3;
            }
            case 4:
            case 20: {
                return 4;
            }
            case 5:
            case 21: {
                return 5;
            }
            case 6:
            case 22: {
                return 6;
            }
            case 7:
            case 23: {
                return 7;
            }
            default: {
                return -1;
            }
        }
    }
}
