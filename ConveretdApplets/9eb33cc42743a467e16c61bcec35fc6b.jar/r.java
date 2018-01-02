import java.util.Hashtable;
import java.awt.Toolkit;
import java.util.Properties;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class r
{
    public static final String[] e0;
    public static Properties e5;
    public static final String[][] e4;
    public static String[] e3;
    
    public static String dz() {
        String string = " ";
        for (int i = 0; i < r.e0.length; ++i) {
            string = String.valueOf(string) + r.e0[i] + " ";
        }
        return string;
    }
    
    public static String dy() {
        if (r.e3 == null) {
            r.e3 = Toolkit.getDefaultToolkit().getFontList();
        }
        String s = "";
        for (int i = 0; i < r.e3.length; ++i) {
            s = String.valueOf(s) + r.e3[i];
            if (i < r.e3.length - 1) {
                s = String.valueOf(s) + ", ";
            }
        }
        return s;
    }
    
    public static String dx() {
        if (dw("monospaced")) {
            return "Monospaced";
        }
        if (dw("courier")) {
            return "Courier";
        }
        if (dw("dialoginput")) {
            return "DialogInput";
        }
        return r.e3[0];
    }
    
    public static boolean dw(final String s) {
        if (r.e3 == null) {
            r.e3 = Toolkit.getDefaultToolkit().getFontList();
        }
        int n;
        for (n = 0; n < r.e3.length && !r.e3[n].equalsIgnoreCase(s); ++n) {}
        return n != r.e3.length;
    }
    
    public static boolean dv(final String s) {
        return r.e5.containsKey(s);
    }
    
    static {
        e0 = new String[] { "xterm", "linux", "scoansi", "att6386", "sun", "aixterm", "vt220", "vt100", "ansi", "vt52", "xterm-color", "linux-lat", "", "at386", "", "", "vt320", "vt102" };
        r.e5 = new Properties();
        e4 = new String[][] { { "rv", "false", "reverse video", "(true/false)" }, { "aw", "true", "autowrap of line if output reaches edge of window", "(true/false)" }, { "rw", "false", "reverse autowrap when going off left edge of window", "(true/false)" }, { "im", "false", "insert mode", "(true/false)" }, { "al", "false", "do auto-linefeed", "(true/false)" }, { "sk", "true", "reposition scroll-area to bottom on keyboard input", "(true/false)" }, { "si", "true", "reposition scroll-area to bottom on output to screen", "(true/false)" }, { "lp", "false", "use PgUp, PgDn, Home, End keys locally or escape them to shell", "(true/false)" }, { "sc", "false", "put <CR><NL> instead of <CR> at end of lines when selecting", "(true/false)" }, { "vi", "true", "visible cursor", "(true/false)" }, { "ad", "false", "ASCII Line-draw-characters", "(true/false)" }, { "le", "false", "do local echo", "(true/false)" }, { "sf", "false", "scale font when resizing window", "(true/false)" }, { "vb", "false", "visual bell", "(true/false)" }, { "ct", "true", "map <ctrl>+<space> to <NUL>", "(true/false)" }, { "dc", "false", "toggle 80/132 columns", "(true/false)" }, { "da", "true", "enable 80/132 switching", "(true/false)" }, { "cs", "true", "copy on mouse-selection", "(true/false)" }, { "fn", dx(), "name of font to use in terminal", "(" + dy() + ")" }, { "fs", "12", "size of font to use in terminal", "(system dep.)" }, { "gm", "80x24", "geometry of terminal", "('<cols>x<rows>')" }, { "te", r.e0[0], "name of terminal to emulate", "(" + dz() + ")" }, { "sl", "512", "number of lines to save in \"scrollback\" buffer", "(0 - 8k)" }, { "sb", "right", "scrollbar position", "(none/left/right)" }, { "bg", "white", "background color", "(<name> or '<r>,<g>,<b>')" }, { "fg", "black", "foreground color", "(<name> or '<r>,<g>,<b>')" }, { "cc", "i_blue", "cursor color", "(<name> or '<r>,<g>,<b>')" }, { "rg", "bottom", "resize gravity, fixpoint of screen when resizing", "(top/bottom)" }, { "bs", "DEL", "character to send on BACKSPACE", "('BS' or 'DEL')" }, { "de", "DEL", "character to send on DELETE", "('BS' or 'DEL')" }, { "sd", "\" \"", "delimeter characters for click-selection", "<string>" } };
        for (int i = 0; i < r.e4.length; ++i) {
            ((Hashtable<String, String>)r.e5).put(r.e4[i][0], r.e4[i][1]);
        }
    }
}
