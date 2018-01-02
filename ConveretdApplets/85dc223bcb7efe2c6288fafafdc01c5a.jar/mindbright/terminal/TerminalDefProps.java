// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.terminal;

import java.util.Hashtable;
import java.awt.Toolkit;
import java.util.Properties;

public abstract class TerminalDefProps
{
    public static final String[] terminalTypes;
    public static final int PROP_NAME = 0;
    public static final int PROP_VALUE = 1;
    public static final int PROP_DESC = 2;
    public static final int PROP_ALLOWED = 3;
    public static Properties defaultProperties;
    public static final String[][] defaultPropDesc;
    public static String[] systemFonts;
    
    public static String listAvailableTerminalTypes() {
        String list = " ";
        for (int i = 0; i < TerminalDefProps.terminalTypes.length; ++i) {
            list = list + TerminalDefProps.terminalTypes[i] + " ";
        }
        return list;
    }
    
    public static String fontList() {
        if (TerminalDefProps.systemFonts == null) {
            TerminalDefProps.systemFonts = Toolkit.getDefaultToolkit().getFontList();
        }
        String list = "";
        for (int i = 0; i < TerminalDefProps.systemFonts.length; ++i) {
            list += TerminalDefProps.systemFonts[i];
            if (i < TerminalDefProps.systemFonts.length - 1) {
                list += ", ";
            }
        }
        return list;
    }
    
    public static String defaultFont() {
        if (fontExists("monospaced")) {
            return "Monospaced";
        }
        if (fontExists("courier")) {
            return "Courier";
        }
        if (fontExists("dialoginput")) {
            return "DialogInput";
        }
        return TerminalDefProps.systemFonts[0];
    }
    
    public static boolean fontExists(final String font) {
        if (TerminalDefProps.systemFonts == null) {
            TerminalDefProps.systemFonts = Toolkit.getDefaultToolkit().getFontList();
        }
        int i;
        for (i = 0; i < TerminalDefProps.systemFonts.length && !TerminalDefProps.systemFonts[i].equalsIgnoreCase(font); ++i) {}
        return i != TerminalDefProps.systemFonts.length;
    }
    
    public static boolean isProperty(final String key) {
        return TerminalDefProps.defaultProperties.containsKey(key);
    }
    
    static {
        terminalTypes = new String[] { "xterm", "linux", "scoansi", "att6386", "sun", "aixterm", "vt220", "vt100", "ansi", "vt52", "xterm-color", "linux-lat", "", "at386", "", "", "vt320", "vt102" };
        TerminalDefProps.defaultProperties = new Properties();
        defaultPropDesc = new String[][] { { "rv", "false", "reverse video", "(true/false)" }, { "aw", "true", "autowrap of line if output reaches edge of window", "(true/false)" }, { "rw", "false", "reverse autowrap when going off left edge of window", "(true/false)" }, { "im", "false", "insert mode", "(true/false)" }, { "al", "false", "do auto-linefeed", "(true/false)" }, { "sk", "true", "reposition scroll-area to bottom on keyboard input", "(true/false)" }, { "si", "true", "reposition scroll-area to bottom on output to screen", "(true/false)" }, { "lp", "false", "use PgUp, PgDn, Home, End keys locally or escape them to shell", "(true/false)" }, { "sc", "false", "put <CR><NL> instead of <CR> at end of lines when selecting", "(true/false)" }, { "vi", "true", "visible cursor", "(true/false)" }, { "ad", "false", "ASCII Line-draw-characters", "(true/false)" }, { "le", "false", "do local echo", "(true/false)" }, { "sf", "false", "scale font when resizing window", "(true/false)" }, { "vb", "false", "visual bell", "(true/false)" }, { "ct", "true", "map <ctrl>+<space> to <NUL>", "(true/false)" }, { "dc", "false", "toggle 80/132 columns", "(true/false)" }, { "da", "true", "enable 80/132 switching", "(true/false)" }, { "cs", "true", "copy on mouse-selection", "(true/false)" }, { "fn", defaultFont(), "name of font to use in terminal", "(" + fontList() + ")" }, { "fs", "12", "size of font to use in terminal", "(system dep.)" }, { "gm", "80x24", "geometry of terminal", "('<cols>x<rows>')" }, { "te", TerminalDefProps.terminalTypes[0], "name of terminal to emulate", "(" + listAvailableTerminalTypes() + ")" }, { "sl", "512", "number of lines to save in \"scrollback\" buffer", "(0 - 8k)" }, { "sb", "right", "scrollbar position", "(none/left/right)" }, { "bg", "white", "background color", "(<name> or '<r>,<g>,<b>')" }, { "fg", "black", "foreground color", "(<name> or '<r>,<g>,<b>')" }, { "cc", "i_blue", "cursor color", "(<name> or '<r>,<g>,<b>')" }, { "rg", "bottom", "resize gravity, fixpoint of screen when resizing", "(top/bottom)" }, { "bs", "DEL", "character to send on BACKSPACE", "('BS' or 'DEL')" }, { "de", "DEL", "character to send on DELETE", "('BS' or 'DEL')" }, { "sd", "\" \"", "delimeter characters for click-selection", "<string>" } };
        for (int i = 0; i < TerminalDefProps.defaultPropDesc.length; ++i) {
            ((Hashtable<String, String>)TerminalDefProps.defaultProperties).put(TerminalDefProps.defaultPropDesc[i][0], TerminalDefProps.defaultPropDesc[i][1]);
        }
    }
}
