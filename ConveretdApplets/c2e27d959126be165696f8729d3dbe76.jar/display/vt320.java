// 
// Decompiled by Procyon v0.5.30
// 

package display;

import java.awt.Container;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.applet.Applet;

public class vt320 extends Terminal implements TerminalHost
{
    private TerminalHost host;
    private int pressedKey;
    private long pressedWhen;
    private CharDisplay display;
    private static int debug;
    private String terminalID;
    int R;
    int C;
    int Sc;
    int Sr;
    int Sa;
    int attributes;
    int insertmode;
    int statusmode;
    int vt52mode;
    int normalcursor;
    boolean moveoutsidemargins;
    boolean sendcrlf;
    private boolean useibmcharset;
    private static int lastwaslf;
    private static int i;
    private static final char ESC = '\u001b';
    private static final char IND = '\u0084';
    private static final char NEL = '\u0085';
    private static final char RI = '\u008d';
    private static final char HTS = '\u0088';
    private static final char DCS = '\u0090';
    private static final char CSI = '\u009b';
    private static final char OSC = '\u009d';
    private static final int TSTATE_DATA = 0;
    private static final int TSTATE_ESC = 1;
    private static final int TSTATE_CSI = 2;
    private static final int TSTATE_DCS = 3;
    private static final int TSTATE_DCEQ = 4;
    private static final int TSTATE_ESCSQUARE = 5;
    private static final int TSTATE_OSC = 6;
    private static final int TSTATE_SETG0 = 7;
    private static final int TSTATE_SETG1 = 8;
    private static final int TSTATE_SETG2 = 9;
    private static final int TSTATE_SETG3 = 10;
    private static final int TSTATE_CSI_DOLLAR = 11;
    private static char[] gx;
    private static char gr;
    private static char gl;
    private static char[] DECSPECIAL;
    private static final int KEYUP = 4;
    private static final int KEYDOWN = 5;
    private static final int KEYLEFT = 6;
    private static final int KEYRIGHT = 7;
    private static final int KEYF1 = 8;
    private static final int KEYF2 = 9;
    private static final int KEYF3 = 10;
    private static final int KEYF4 = 11;
    private static final int KEYF5 = 12;
    private static final int KEYF6 = 13;
    private static final int KEYF7 = 14;
    private static final int KEYF8 = 15;
    private static final int KEYF9 = 16;
    private static final int KEYF10 = 17;
    private static final int KEYF11 = 18;
    private static final int KEYF12 = 19;
    private static final int KEYPGDN = 3;
    private static final int KEYPGUP = 2;
    private static final int KEYHOME = 0;
    private static final int KEYEND = 1;
    public static final int KEYPRINT_SCREEN = 20;
    public static final int KEYSCROLL_LOCK = 21;
    public static final int KEYCAPS_LOCK = 22;
    public static final int KEYNUM_LOCK = 23;
    public static final int KEYPAUSE = 24;
    public static final int KEYINSERT = 25;
    public static final int INSERT = 1025;
    private String[] FunctionKey;
    private String[] FunctionKeyShift;
    private String[] FunctionKeyCtrl;
    private String[] FunctionKeyAlt;
    private String KeyUp;
    private String KeyDown;
    private String KeyLeft;
    private String KeyRight;
    private String KeyBacktab;
    private String KeyTab;
    private String KP0;
    private String KP1;
    private String KP2;
    private String KP3;
    private String KP4;
    private String KP5;
    private String KP6;
    private String KP7;
    private String KP8;
    private String KP9;
    private String KPMinus;
    private String KPComma;
    private String KPPeriod;
    private String KPEnter;
    private String PF1;
    private String PF2;
    private String PF3;
    private String PF4;
    private String Help;
    private String Do;
    private String Find;
    private String Insert;
    private String Remove;
    private String Select;
    private String PrevScn;
    private String NextScn;
    private String osc;
    private String dcs;
    private int term_state;
    private boolean vms;
    private byte[] Tabs;
    private int[] DCEvars;
    private int DCEvar;
    private String osn;
    public static final char[] unimap;
    
    static {
        vt320.debug = 0;
        vt320.lastwaslf = 0;
        vt320.gx = new char[] { 'B', '0', 'A', '<' };
        vt320.gr = '\u0001';
        vt320.gl = '\0';
        vt320.DECSPECIAL = new char[] { '@', '\u2666', '\u2592', '\u2409', '\u240c', '\u240d', '\u240a', 'º', '±', '\u2424', '\u240b', '\u2518', '\u2510', '\u250c', '\u2514', '\u253c', '\u2594', '\u2580', '\u2500', '\u25ac', '_', '\u251c', '\u2524', '\u2534', '\u252c', '\u2502', '\u2264', '\u2265', '¶', '\u2260', '£', '·' };
        unimap = new char[] { '\0', '\u0001', '\u0002', '\u0003', '\u0004', '\u0005', '\u0006', '\u0007', '\b', '\t', '\n', '\u000b', '\f', '\r', '\u000e', '\u000f', '\u0010', '\u0011', '\u0012', '\u0013', '\u0014', '\u0015', '\u0016', '\u0017', '\u0018', '\u0019', '\u001a', '\u001b', '\u001c', '\u001d', '\u001e', '\u001f', ' ', '!', '\"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', '<', '=', '>', '?', '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '[', '\\', ']', '^', '_', '`', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '{', '|', '}', '~', '\u007f', '\u00c7', '\u00fc', '\u00e9', '\u00e2', '\u00e4', '\u00e0', '\u00e5', '\u00e7', '\u00ea', '\u00eb', '\u00e8', '\u00ef', '\u00ee', '\u00ec', '\u00c4', '\u00c5', '\u00c9', '\u00e6', '\u00c6', '\u00f4', '\u00f6', '\u00f2', '\u00fb', '\u00f9', '\u00ff', '\u00d6', '\u00dc', '¢', '£', '¥', '\u20a7', '\u0192', '\u00e1', '\u00ed', '\u00f3', '\u00fa', '\u00f1', '\u00d1', 'ª', 'º', '¿', '\u2310', '¬', '½', '¼', '¡', '«', '»', '\u2591', '\u2592', '\u2593', '\u2502', '\u2524', '\u2561', '\u2562', '\u2556', '\u2555', '\u2563', '\u2551', '\u2557', '\u255d', '\u255c', '\u255b', '\u2510', '\u2514', '\u2534', '\u252c', '\u251c', '\u2500', '\u253c', '\u255e', '\u255f', '\u255a', '\u2554', '\u2569', '\u2566', '\u2560', '\u2550', '\u256c', '\u2567', '\u2568', '\u2564', '\u2565', '\u2559', '\u2558', '\u2552', '\u2553', '\u256b', '\u256a', '\u2518', '\u250c', '\u2588', '\u2584', '\u258c', '\u2590', '\u2580', '\u03b1', '\u00df', '\u0393', '\u03c0', '\u03a3', '\u03c3', 'µ', '\u03c4', '\u03a6', '\u0398', '\u03a9', '\u03b4', '\u221e', '\u03c6', '\u03b5', '\u2229', '\u2261', '±', '\u2265', '\u2264', '\u2320', '\u2321', '\u00f7', '\u2248', '°', '\u2219', '·', '\u221a', '\u207f', '²', '\u25a0', ' ' };
    }
    
    public vt320() {
        this.host = (TerminalHost)this;
        this.pressedKey = 32;
        this.pressedWhen = 32L;
        this.terminalID = "vt320";
        this.attributes = 0;
        this.insertmode = 0;
        this.statusmode = 0;
        this.vt52mode = 0;
        this.normalcursor = 0;
        this.moveoutsidemargins = true;
        this.sendcrlf = true;
        this.useibmcharset = false;
        this.term_state = 0;
        this.vms = false;
        this.DCEvars = new int[10];
        this.osn = System.getProperty("os.name");
    }
    
    private void InitTerminalVars() {
        int nw = this.display.getColumns();
        if (nw < 132) {
            nw = 132;
        }
        this.Tabs = new byte[nw];
        for (int i = 0; i < nw; i += 8) {
            this.Tabs[i] = 1;
        }
        this.PF1 = "\u001bOP";
        this.PF2 = "\u001bOQ";
        this.PF3 = "\u001bOR";
        this.PF4 = "\u001bOS";
        this.Find = "\u001b[1~";
        this.Insert = "\u001b[2~";
        this.Remove = "\u001b[3~";
        this.Select = "\u001b[4~";
        this.PrevScn = "\u001b[5~";
        this.NextScn = "\u001b[6~";
        this.Help = "\u001b[28~";
        this.Do = "\u001b[29~";
        (this.FunctionKey = new String[21])[0] = "";
        this.FunctionKey[1] = this.PF1;
        this.FunctionKey[2] = this.PF2;
        this.FunctionKey[3] = this.PF3;
        this.FunctionKey[4] = this.PF4;
        this.FunctionKey[5] = "\u001b[15~";
        this.FunctionKey[6] = "\u001b[17~";
        this.FunctionKey[7] = "\u001b[18~";
        this.FunctionKey[8] = "\u001b[19~";
        this.FunctionKey[9] = "\u001b[20~";
        this.FunctionKey[10] = "\u001b[21~";
        this.FunctionKey[11] = "\u001b[23~";
        this.FunctionKey[12] = "\u001b[24~";
        this.FunctionKey[13] = "\u001b[25~";
        this.FunctionKey[14] = "\u001b[26~";
        this.FunctionKey[15] = this.Help;
        this.FunctionKey[16] = this.Do;
        this.FunctionKey[17] = "\u001b[31~";
        this.FunctionKey[18] = "\u001b[32~";
        this.FunctionKey[19] = "\u001b[33~";
        this.FunctionKey[20] = "\u001b[34~";
        this.FunctionKeyShift = new String[21];
        this.FunctionKeyAlt = new String[21];
        this.FunctionKeyCtrl = new String[21];
        for (int j = 0; j < 20; ++j) {
            this.FunctionKeyShift[j] = "";
            this.FunctionKeyAlt[j] = "";
            this.FunctionKeyCtrl[j] = "";
        }
        this.FunctionKeyShift[15] = this.Find;
        this.FunctionKeyShift[16] = this.Select;
        this.KeyTab = "\t";
        this.KeyBacktab = "\u001bOP\t";
        this.KeyUp = "\u001b[A";
        this.KeyDown = "\u001b[B";
        this.KeyRight = "\u001b[C";
        this.KeyLeft = "\u001b[D";
        this.KP0 = "\u001bOp";
        this.KP1 = "\u001bOq";
        this.KP2 = "\u001bOr";
        this.KP3 = "\u001bOs";
        this.KP4 = "\u001bOt";
        this.KP5 = "\u001bOu";
        this.KP6 = "\u001bOv";
        this.KP7 = "\u001bOw";
        this.KP8 = "\u001bOx";
        this.KP9 = "\u001bOy";
        this.KPMinus = "\u001bOm";
        this.KPComma = "\u001bOl";
        this.KPPeriod = "\u001bOn";
        this.KPEnter = "\u001bOM";
    }
    
    private void _SetCursor(final int row, final int col) {
        int maxr = this.display.getRows();
        final int tm = this.display.getTopMargin();
        this.R = ((row < 0) ? 0 : row);
        this.C = ((col < 0) ? 0 : col);
        if (!this.moveoutsidemargins) {
            this.R += this.display.getTopMargin();
            maxr = this.display.getBottomMargin();
        }
        if (this.R > maxr) {
            this.R = maxr;
        }
    }
    
    public void addNotify() {
        if (this.display == null) {
            String width = "80";
            String height = "24";
            String resize = "font";
            String font = "Courier";
            String fs = "14";
            String bufs = "100";
            String scrb = "false";
            String vms = "false";
            String ibmcset = "false";
            final Applet applet = (Applet)((Component)this).getParent();
            if (applet != null) {
                try {
                    this.host = (TerminalHost)applet;
                }
                catch (ClassCastException ex) {
                    System.err.println("vt320: " + applet + " cannot receive terminal input");
                    this.host = (TerminalHost)this;
                }
                width = applet.getParameter("VTcolumns");
                height = applet.getParameter("VTrows");
                font = applet.getParameter("VTfont");
                fs = applet.getParameter("VTfontsize");
                bufs = applet.getParameter("VTbuffer");
                scrb = applet.getParameter("VTscrollbar");
                vms = applet.getParameter("VTvms");
                resize = applet.getParameter("VTresize");
                resize = ((resize == null) ? "font" : resize);
                ibmcset = applet.getParameter("VTcharset");
                if (ibmcset != null && ibmcset.equals("ibm")) {
                    this.useibmcharset = true;
                }
                if (applet.getParameter("VTid") != null) {
                    this.terminalID = applet.getParameter("VTid");
                }
            }
            (this.display = new CharDisplay((width == null) ? 80 : ((int)new Integer(width)), (height == null) ? 24 : ((int)new Integer(height)), (font == null) ? "Courier" : font, (fs == null) ? 14 : ((int)new Integer(fs)))).setBottomMargin(((height == null) ? 24 : new Integer(height)) - 1);
            this.display.setBufferSize((bufs == null) ? 100 : ((int)new Integer(bufs)));
            if (resize.equals("none")) {
                this.display.setResizeStrategy(0);
            }
            if (resize.equals("font")) {
                this.display.setResizeStrategy(2);
            }
            if (resize.equals("screen")) {
                this.display.setResizeStrategy(1);
            }
            this.display.setBorder(2, false);
            ((Container)this).setLayout(new BorderLayout());
            if (scrb != null && !scrb.equals("false")) {
                if (scrb.equals("left") || scrb.equals("true")) {
                    this.display.setScrollbar("West");
                }
                else if (scrb.equals("right")) {
                    this.display.setScrollbar("East");
                }
                else {
                    System.out.println("vt320: unknown scrollbar location: " + scrb);
                }
            }
            if (vms != null && vms.equals("true")) {
                this.vms = true;
            }
            ((Container)this).add("Center", (Component)this.display);
            this.InitTerminalVars();
            for (int i = 1; i < 20; ++i) {
                if (applet.getParameter("F" + i) != null) {
                    this.FunctionKey[i] = unEscape(applet.getParameter("F" + i));
                }
                if (applet.getParameter("SF" + i) != null) {
                    this.FunctionKeyShift[i] = unEscape(applet.getParameter("SF" + i));
                }
                if (applet.getParameter("CF" + i) != null) {
                    this.FunctionKeyCtrl[i] = unEscape(applet.getParameter("CF" + i));
                }
                if (applet.getParameter("AF" + i) != null) {
                    this.FunctionKeyAlt[i] = unEscape(applet.getParameter("AF" + i));
                }
            }
        }
        super.addNotify();
    }
    
    public String[][] getParameterInfo() {
        final String[][] pinfo = { { "VTcolumns", "Integer", "Columns of the terminal" }, { "VTrows", "Integer", "Rows of the terminal" }, { "VTfont", "String", "Terminal font (default is Courier)" }, { "VTfontsize", "Integer", "Font size" }, { "VTbuffer", "Integer", "Scrollback buffer size" }, { "VTscrollbar", "Boolean", "Enable or disable scrollbar" }, { "VTresize", "String", "One of none, font, screen" }, { "VTid", "String", "Terminal id, standard is VT320" }, { "VTcharset", "String", "Charset used" }, { "VTvms", "Boolean", "Enable or disable VMS key mappings" }, { "F1 - F20", "String", "Programmable Function Keys" }, { "SF1 - SF20", "String", "Programmable Shift-ed Function Keys" }, { "CF1 - CF20", "String", "Programmable Control-ed Function Keys" }, { "AF1 - AF20", "String", "Programmable Alt-ed Function Keys" } };
        return pinfo;
    }
    
    public Dimension getSize() {
        return new Dimension(this.display.getColumns(), this.display.getRows());
    }
    
    public String getTerminalType() {
        return this.terminalID;
    }
    
    public boolean handleEvent(final Event evt) {
        if (evt.id == 504) {
            ((Component)this.display).requestFocus();
            return true;
        }
        if (evt.id == 505) {
            ((Component)this).nextFocus();
            return true;
        }
        final int id = evt.id;
        final boolean control = (0x2 & evt.modifiers) != 0x0;
        final boolean shift = (0x1 & evt.modifiers) != 0x0;
        final boolean alt = (0x8 & evt.modifiers) != 0x0;
        Label_0942: {
            if (this.pressedKey != 10 || (evt.key != 10 && evt.key != 13) || evt.when - this.pressedWhen >= 50L) {
                final int priorKey = this.pressedKey;
                if (id == 401) {
                    this.pressedKey = evt.key;
                }
                else {
                    if (evt.key != 46 || evt.id == 402 || evt.key == this.pressedKey) {
                        break Label_0942;
                    }
                    this.pressedKey = 0;
                }
                this.pressedWhen = evt.when;
                if (evt.key == 10 && !control) {
                    if (this.sendcrlf) {
                        this.host.send("\r\n");
                    }
                    else {
                        this.host.send("\r");
                    }
                    return true;
                }
                if (((!this.vms && evt.key == 50) || evt.key == 32) && control) {
                    return this.host.send("\u0000");
                }
                if (this.vms) {
                    if (evt.key == 8) {
                        if (shift && !control) {
                            return this.host.send("\n");
                        }
                        if (control && !shift) {
                            return this.host.send("\u0018");
                        }
                        return this.host.send("\u007f");
                    }
                    else if (evt.key == 127 && !control) {
                        if (shift) {
                            return this.host.send(this.Insert);
                        }
                        return this.host.send(this.Remove);
                    }
                    else if (control) {
                        switch (evt.key) {
                            case 48: {
                                return this.host.send(this.KP0);
                            }
                            case 49: {
                                return this.host.send(this.KP1);
                            }
                            case 50: {
                                return this.host.send(this.KP2);
                            }
                            case 51: {
                                return this.host.send(this.KP3);
                            }
                            case 52: {
                                return this.host.send(this.KP4);
                            }
                            case 53: {
                                return this.host.send(this.KP5);
                            }
                            case 54: {
                                return this.host.send(this.KP6);
                            }
                            case 55: {
                                return this.host.send(this.KP7);
                            }
                            case 56: {
                                return this.host.send(this.KP8);
                            }
                            case 57: {
                                return this.host.send(this.KP9);
                            }
                            case 46: {
                                return this.host.send(this.KPPeriod);
                            }
                            case 31:
                            case 45: {
                                return this.host.send(this.KPMinus);
                            }
                            case 43: {
                                return this.host.send(this.KPComma);
                            }
                            case 10: {
                                return this.host.send(this.KPEnter);
                            }
                            case 47: {
                                return this.host.send(this.PF2);
                            }
                            case 42: {
                                return this.host.send(this.PF3);
                            }
                            default: {
                                if (shift && evt.key < 32) {
                                    return this.host.send(String.valueOf(this.PF1) + (char)(evt.key + 64));
                                }
                                break;
                            }
                        }
                    }
                }
                if (shift && evt.key == 9) {
                    return this.host.send(this.KeyBacktab);
                }
                return this.host.send(String.valueOf((char)evt.key));
            }
        }
        String input = "";
        if (evt.id == 403) {
            String[] fmap = this.FunctionKey;
            if (shift) {
                fmap = this.FunctionKeyShift;
            }
            if (control) {
                fmap = this.FunctionKeyCtrl;
            }
            if (alt) {
                fmap = this.FunctionKeyAlt;
            }
            switch (evt.key % 1000) {
                case 8: {
                    input = fmap[1];
                    break;
                }
                case 9: {
                    input = fmap[2];
                    break;
                }
                case 10: {
                    input = fmap[3];
                    break;
                }
                case 11: {
                    input = fmap[4];
                    break;
                }
                case 12: {
                    input = fmap[5];
                    break;
                }
                case 13: {
                    input = fmap[6];
                    break;
                }
                case 14: {
                    input = fmap[7];
                    break;
                }
                case 15: {
                    input = fmap[8];
                    break;
                }
                case 16: {
                    input = fmap[9];
                    break;
                }
                case 17: {
                    input = fmap[10];
                    break;
                }
                case 18: {
                    input = fmap[11];
                    break;
                }
                case 19: {
                    input = fmap[12];
                    break;
                }
                case 4: {
                    input = this.KeyUp;
                    break;
                }
                case 5: {
                    input = this.KeyDown;
                    break;
                }
                case 6: {
                    input = this.KeyLeft;
                    break;
                }
                case 7: {
                    input = this.KeyRight;
                    break;
                }
                case 3: {
                    input = this.NextScn;
                    break;
                }
                case 2: {
                    input = this.PrevScn;
                    break;
                }
                case 25: {
                    input = this.Insert;
                    break;
                }
                case 0: {
                    if (this.vms) {
                        input = "\b";
                        break;
                    }
                }
                case 1: {
                    if (this.vms) {
                        input = "\u0005";
                        break;
                    }
                }
                case 23: {
                    if (!this.vms || !control) {
                        break;
                    }
                    if (this.pressedKey != evt.key) {
                        this.pressedKey = evt.key;
                        input = this.PF1;
                        break;
                    }
                    this.pressedKey = 32;
                    break;
                }
                default: {
                    System.out.println("vt320: unknown event:" + evt.key);
                    break;
                }
            }
        }
        return input != null && input.length() > 0 && this.host.send(input);
    }
    
    private void handle_dcs(final String dcs) {
        System.out.println("DCS: " + dcs);
    }
    
    private void handle_osc(final String osc) {
        System.out.println("OSC: " + osc);
    }
    
    public char map_cp850_unicode(final char x) {
        if (x >= '\u0100') {
            return x;
        }
        return vt320.unimap[x];
    }
    
    public void putChar(final char c) {
        this.putChar(c, true);
        this.display.redraw();
    }
    
    public void putChar(char c, final boolean doshowcursor) {
        final int rows = this.display.getRows();
        final int columns = this.display.getColumns();
        final int tm = this.display.getTopMargin();
        final int bm = this.display.getBottomMargin();
        if (vt320.debug > 4) {
            System.out.println("putChar(" + c + " [" + (int)c + "]) at R=" + this.R + " , C=" + this.C + ", columns=" + columns + ", rows=" + rows);
        }
        this.display.markLine(this.R, 1);
        if (c > '\u00ff') {
            if (vt320.debug > 0) {
                System.out.println("char > 255:" + (int)c);
            }
            return;
        }
        Label_7430: {
            switch (this.term_state) {
                case 0: {
                    if (!this.useibmcharset) {
                        boolean doneflag = true;
                        switch (c) {
                            case '\u009d': {
                                this.osc = "";
                                this.term_state = 6;
                                break;
                            }
                            case '\u008d': {
                                if (this.R > tm) {
                                    --this.R;
                                }
                                else {
                                    this.display.insertLine(this.R, 1, true);
                                }
                                if (vt320.debug > 1) {
                                    System.out.println("RI");
                                    break;
                                }
                                break;
                            }
                            case '\u0084': {
                                if (this.R == tm - 1 || this.R == bm || this.R == rows - 1) {
                                    this.display.insertLine(this.R, 1, false);
                                }
                                else {
                                    ++this.R;
                                }
                                if (vt320.debug > 1) {
                                    System.out.println("IND (at " + this.R + " )");
                                    break;
                                }
                                break;
                            }
                            case '\u0085': {
                                if (this.R == tm - 1 || this.R == bm || this.R == rows - 1) {
                                    this.display.insertLine(this.R, 1, false);
                                }
                                else {
                                    ++this.R;
                                }
                                this.C = 0;
                                if (vt320.debug > 1) {
                                    System.out.println("NEL (at " + this.R + " )");
                                    break;
                                }
                                break;
                            }
                            case '\u0088': {
                                this.Tabs[this.C] = 1;
                                if (vt320.debug > 1) {
                                    System.out.println("HTS");
                                    break;
                                }
                                break;
                            }
                            case '\u0090': {
                                this.dcs = "";
                                this.term_state = 3;
                                break;
                            }
                            default: {
                                doneflag = false;
                                break;
                            }
                        }
                        if (doneflag) {
                            break;
                        }
                    }
                    switch (c) {
                        case '\u009b': {
                            this.term_state = 4;
                            break Label_7430;
                        }
                        case '\u001b': {
                            this.term_state = 1;
                            vt320.lastwaslf = 0;
                            break Label_7430;
                        }
                        case '\b': {
                            --this.C;
                            if (this.C < 0) {
                                this.C = 0;
                            }
                            vt320.lastwaslf = 0;
                            break Label_7430;
                        }
                        case '\t': {
                            if (this.insertmode == 1) {
                                int newc = this.C;
                                do {
                                    this.display.insertChar(this.C, this.R, ' ', this.attributes);
                                    if (++newc < columns) {
                                        continue;
                                    }
                                    break;
                                } while (this.Tabs[newc] == 0);
                            }
                            else {
                                do {
                                    this.display.putChar(this.C++, this.R, ' ', this.attributes);
                                } while (this.C < columns && this.Tabs[this.C] == 0);
                            }
                            vt320.lastwaslf = 0;
                            break Label_7430;
                        }
                        case '\r': {
                            this.C = 0;
                            break Label_7430;
                        }
                        case '\n': {
                            if (vt320.debug > 3) {
                                System.out.println("R= " + this.R + ", bm " + bm + ", tm=" + tm + ", rows=" + rows);
                            }
                            if (!this.vms) {
                                if (vt320.lastwaslf != 0 && vt320.lastwaslf != c) {
                                    break Label_7430;
                                }
                                vt320.lastwaslf = c;
                            }
                            if (this.R == bm || this.R >= rows - 1) {
                                this.display.insertLine(this.R, 1);
                                break Label_7430;
                            }
                            ++this.R;
                            break Label_7430;
                        }
                        case '\u000e': {
                            vt320.gl = '\u0001';
                            break Label_7430;
                        }
                        case '\u000f': {
                            vt320.gl = '\0';
                            break Label_7430;
                        }
                        default: {
                            vt320.lastwaslf = 0;
                            if (c >= ' ') {
                                if (this.C >= columns) {
                                    if (this.R < rows - 1) {
                                        ++this.R;
                                    }
                                    else {
                                        this.display.insertLine(this.R, false);
                                    }
                                    this.C = 0;
                                }
                                if (vt320.gx[vt320.gl] == '0' && c >= '_' && c <= '~') {
                                    if (vt320.debug > 3) {
                                        System.out.print("Mapping " + c + " (index " + ((short)c - 95) + " to ");
                                    }
                                    c = vt320.DECSPECIAL[(short)c - 95];
                                    if (vt320.debug > 3) {
                                        System.out.println(String.valueOf(c) + " (" + (int)c + ")");
                                    }
                                }
                                if (this.useibmcharset) {
                                    c = this.map_cp850_unicode(c);
                                }
                                if (vt320.debug > 4) {
                                    System.out.println("output " + c + " at " + this.C + "," + this.R);
                                }
                                if (this.insertmode == 1) {
                                    this.display.insertChar(this.C, this.R, c, this.attributes);
                                }
                                else {
                                    this.display.putChar(this.C, this.R, c, this.attributes);
                                }
                                ++this.C;
                                break Label_7430;
                            }
                            if (c != '\0' && vt320.debug > 0) {
                                System.out.println("TSTATE_DATA char: " + (int)c);
                                break Label_7430;
                            }
                            break Label_7430;
                        }
                    }
                    break;
                }
                case 6: {
                    if (c < ' ' && c != '\u001b') {
                        this.handle_osc(this.osc);
                        this.term_state = 0;
                        break;
                    }
                    if (c == '\\' && this.osc.charAt(this.osc.length() - 1) == '\u001b') {
                        this.handle_osc(this.osc);
                        this.term_state = 0;
                        break;
                    }
                    this.osc = String.valueOf(this.osc) + c;
                    break;
                }
                case 1: {
                    switch (c) {
                        case '#': {
                            this.term_state = 5;
                            break Label_7430;
                        }
                        case 'c': {
                            this.term_state = 0;
                            break Label_7430;
                        }
                        case '[': {
                            this.term_state = 2;
                            this.DCEvar = 0;
                            this.DCEvars[0] = 0;
                            this.DCEvars[1] = 0;
                            this.DCEvars[2] = 0;
                            this.DCEvars[3] = 0;
                            break Label_7430;
                        }
                        case ']': {
                            this.osc = "";
                            this.term_state = 6;
                            break Label_7430;
                        }
                        case 'P': {
                            this.dcs = "";
                            this.term_state = 3;
                            break Label_7430;
                        }
                        case 'E': {
                            if (this.R == tm - 1 || this.R == bm || this.R == rows - 1) {
                                this.display.insertLine(this.R, 1, false);
                            }
                            else {
                                ++this.R;
                            }
                            this.C = 0;
                            if (vt320.debug > 1) {
                                System.out.println("ESC E (at " + this.R + ")");
                            }
                            this.term_state = 0;
                            break Label_7430;
                        }
                        case 'D': {
                            if (this.R == tm - 1 || this.R == bm || this.R == rows - 1) {
                                this.display.insertLine(this.R, 1, false);
                            }
                            else {
                                ++this.R;
                            }
                            if (vt320.debug > 1) {
                                System.out.println("ESC D (at " + this.R + " )");
                            }
                            this.term_state = 0;
                            break Label_7430;
                        }
                        case 'M': {
                            if (this.R >= tm && this.R <= bm) {
                                this.display.insertLine(this.R, 1, true);
                            }
                            if (vt320.debug > 1) {
                                System.out.println("ESC M ");
                            }
                            this.term_state = 0;
                            break Label_7430;
                        }
                        case 'H': {
                            if (vt320.debug > 1) {
                                System.out.println("ESC H at " + this.C);
                            }
                            if (this.C >= columns) {
                                this.C = columns - 1;
                            }
                            this.Tabs[this.C] = 1;
                            this.term_state = 0;
                            break Label_7430;
                        }
                        case '=': {
                            if (vt320.debug > 0) {
                                System.out.println("ESC =");
                            }
                            this.term_state = 0;
                            break Label_7430;
                        }
                        case '>': {
                            if (vt320.debug > 0) {
                                System.out.println("ESC >");
                            }
                            this.term_state = 0;
                            break Label_7430;
                        }
                        case '7': {
                            this.Sc = this.C;
                            this.Sr = this.R;
                            this.Sa = this.attributes;
                            if (vt320.debug > 1) {
                                System.out.println("ESC 7");
                            }
                            this.term_state = 0;
                            break Label_7430;
                        }
                        case '8': {
                            this.C = this.Sc;
                            this.R = this.Sr;
                            this.attributes = this.Sa;
                            this.term_state = 0;
                            if (vt320.debug > 1) {
                                System.out.println("ESC 7");
                                break Label_7430;
                            }
                            break Label_7430;
                        }
                        case '(': {
                            this.term_state = 7;
                            break Label_7430;
                        }
                        case ')': {
                            this.term_state = 8;
                            break Label_7430;
                        }
                        case '*': {
                            this.term_state = 9;
                            break Label_7430;
                        }
                        case '+': {
                            this.term_state = 10;
                            break Label_7430;
                        }
                        case '~': {
                            this.term_state = 0;
                            vt320.gr = '\u0001';
                            break Label_7430;
                        }
                        case 'n': {
                            this.term_state = 0;
                            vt320.gl = '\u0002';
                            break Label_7430;
                        }
                        case '}': {
                            this.term_state = 0;
                            vt320.gr = '\u0002';
                            break Label_7430;
                        }
                        case 'o': {
                            this.term_state = 0;
                            vt320.gl = '\u0003';
                            break Label_7430;
                        }
                        case '|': {
                            this.term_state = 0;
                            vt320.gr = '\u0003';
                            break Label_7430;
                        }
                        default: {
                            System.out.println("ESC unknown letter: (" + (int)c + ")");
                            this.term_state = 0;
                            break Label_7430;
                        }
                    }
                    break;
                }
                case 7: {
                    if (c != '0' && c != 'A' && c != 'B') {
                        System.out.println("ESC ( : G0 char set?  (" + (int)c + ")");
                    }
                    else {
                        if (vt320.debug > 2) {
                            System.out.println("ESC ( : G0 char set  (" + c + " " + (int)c + ")");
                        }
                        vt320.gx[0] = c;
                    }
                    this.term_state = 0;
                    break;
                }
                case 8: {
                    if (c != '0' && c != 'A' && c != 'B') {
                        System.out.println("ESC ) :G1 char set?  (" + (int)c + ")");
                    }
                    else {
                        if (vt320.debug > 2) {
                            System.out.println("ESC ) :G1 char set  (" + c + " " + (int)c + ")");
                        }
                        vt320.gx[1] = c;
                    }
                    this.term_state = 0;
                    break;
                }
                case 9: {
                    if (c != '0' && c != 'A' && c != 'B') {
                        System.out.println("ESC*:G2 char set?  (" + (int)c + ")");
                    }
                    else {
                        if (vt320.debug > 2) {
                            System.out.println("ESC*:G2 char set  (" + c + " " + (int)c + ")");
                        }
                        vt320.gx[2] = c;
                    }
                    this.term_state = 0;
                    break;
                }
                case 10: {
                    if (c != '0' && c != 'A' && c != 'B') {
                        System.out.println("ESC+:G3 char set?  (" + (int)c + ")");
                    }
                    else {
                        if (vt320.debug > 2) {
                            System.out.println("ESC+:G3 char set  (" + c + " " + (int)c + ")");
                        }
                        vt320.gx[3] = c;
                    }
                    this.term_state = 0;
                    break;
                }
                case 5: {
                    switch (c) {
                        case '8': {
                            for (int i = 0; i < columns; ++i) {
                                for (int j = 0; j < rows; ++j) {
                                    this.display.putChar(i, j, 'E', 0);
                                }
                            }
                            break;
                        }
                        default: {
                            System.out.println("ESC # " + c + " not supported.");
                            break;
                        }
                    }
                    this.term_state = 0;
                    break;
                }
                case 3: {
                    if (c == '\\' && this.dcs.charAt(this.dcs.length() - 1) == '\u001b') {
                        this.handle_dcs(this.dcs);
                        this.term_state = 0;
                        break;
                    }
                    this.dcs = String.valueOf(this.dcs) + c;
                    break;
                }
                case 4: {
                    switch (c) {
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9': {
                            this.DCEvars[this.DCEvar] = this.DCEvars[this.DCEvar] * 10 + c - 48;
                            break Label_7430;
                        }
                        case 'r': {
                            System.out.println("ESC [ ? " + this.DCEvars[0] + " r");
                            switch (this.DCEvars[0]) {
                                case 3: {
                                    final Dimension size = this.display.size();
                                    this.display.setWindowSize(80, rows);
                                    ((Container)this).layout();
                                    break;
                                }
                                case 6: {
                                    this.moveoutsidemargins = true;
                                    break;
                                }
                            }
                            this.term_state = 0;
                            break Label_7430;
                        }
                        case 'h': {
                            if (vt320.debug > 0) {
                                System.out.println("ESC [ ? " + this.DCEvars[0] + " h");
                            }
                            switch (this.DCEvars[0]) {
                                case 1: {
                                    this.KeyUp = "\u001bOA";
                                    this.KeyDown = "\u001bOB";
                                    this.KeyRight = "\u001bOC";
                                    this.KeyLeft = "\u001bOD";
                                    break;
                                }
                                case 3: {
                                    final Dimension size = this.display.size();
                                    this.display.setWindowSize(132, rows);
                                    ((Container)this).layout();
                                    break;
                                }
                                case 6: {
                                    this.moveoutsidemargins = false;
                                    break;
                                }
                            }
                            this.term_state = 0;
                            break Label_7430;
                        }
                        case 'l': {
                            if (vt320.debug > 0) {
                                System.out.println("ESC [ ? " + this.DCEvars[0] + " l");
                            }
                            switch (this.DCEvars[0]) {
                                case 1: {
                                    this.KeyUp = "\u001b[A";
                                    this.KeyDown = "\u001b[B";
                                    this.KeyRight = "\u001b[C";
                                    this.KeyLeft = "\u001b[D";
                                    break;
                                }
                                case 3: {
                                    final Dimension size = this.display.size();
                                    this.display.setWindowSize(80, rows);
                                    ((Container)this).layout();
                                    break;
                                }
                                case 6: {
                                    this.moveoutsidemargins = true;
                                    break;
                                }
                            }
                            this.term_state = 0;
                            break Label_7430;
                        }
                        case ';': {
                            ++this.DCEvar;
                            this.DCEvars[this.DCEvar] = 0;
                            break Label_7430;
                        }
                        case 'n': {
                            if (vt320.debug > 0) {
                                System.out.println("ESC [ ? " + this.DCEvars[0] + " n");
                            }
                            switch (this.DCEvars[0]) {
                                case 15: {
                                    this.host.send("\u001b[?13n");
                                    System.out.println("ESC[5n");
                                    break;
                                }
                            }
                            this.term_state = 0;
                            break Label_7430;
                        }
                        default: {
                            if (vt320.debug > 0) {
                                System.out.println("ESC [ ? " + this.DCEvars[0] + " " + c);
                            }
                            this.term_state = 0;
                            break Label_7430;
                        }
                    }
                    break;
                }
                case 11: {
                    switch (c) {
                        case '}': {
                            System.out.println("Active Status Display now " + this.DCEvars[0]);
                            this.statusmode = this.DCEvars[0];
                            break;
                        }
                        case '~': {
                            System.out.println("Status Line mode now " + this.DCEvars[0]);
                            break;
                        }
                        default: {
                            System.out.println("UNKNOWN Status Display code " + c + ", with Pn=" + this.DCEvars[0]);
                            break;
                        }
                    }
                    this.term_state = 0;
                    break;
                }
                case 2: {
                    switch (c) {
                        case '$': {
                            this.term_state = 11;
                            break Label_7430;
                        }
                        case '?': {
                            this.DCEvar = 0;
                            this.DCEvars[0] = 0;
                            this.term_state = 4;
                            break Label_7430;
                        }
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9': {
                            this.DCEvars[this.DCEvar] = this.DCEvars[this.DCEvar] * 10 + c - 48;
                            break Label_7430;
                        }
                        case ';': {
                            ++this.DCEvar;
                            this.DCEvars[this.DCEvar] = 0;
                            break Label_7430;
                        }
                        case 'c': {
                            this.host.send("\u001b[?1;2c");
                            this.term_state = 0;
                            if (vt320.debug > 1) {
                                System.out.println("ESC [ " + this.DCEvars[0] + " c");
                                break Label_7430;
                            }
                            break Label_7430;
                        }
                        case 'q': {
                            if (vt320.debug > 1) {
                                System.out.println("ESC [ " + this.DCEvars[0] + " q");
                            }
                            this.term_state = 0;
                            break Label_7430;
                        }
                        case 'g': {
                            switch (this.DCEvars[0]) {
                                case 3: {
                                    final int nw = this.display.getColumns();
                                    this.Tabs = new byte[nw];
                                    break;
                                }
                                case 0: {
                                    this.Tabs[this.C] = 0;
                                    break;
                                }
                            }
                            if (vt320.debug > 1) {
                                System.out.println("ESC [ " + this.DCEvars[0] + " g");
                            }
                            this.term_state = 0;
                            break Label_7430;
                        }
                        case 'h': {
                            switch (this.DCEvars[0]) {
                                case 4: {
                                    this.insertmode = 1;
                                    break;
                                }
                                case 20: {
                                    this.sendcrlf = true;
                                    break;
                                }
                                default: {
                                    System.out.println("unsupported: ESC [ " + this.DCEvars[0] + " h");
                                    break;
                                }
                            }
                            this.term_state = 0;
                            if (vt320.debug > 1) {
                                System.out.println("ESC [ " + this.DCEvars[0] + " h");
                                break Label_7430;
                            }
                            break Label_7430;
                        }
                        case 'l': {
                            switch (this.DCEvars[0]) {
                                case 4: {
                                    this.insertmode = 0;
                                    break;
                                }
                                case 20: {
                                    this.sendcrlf = false;
                                    break;
                                }
                            }
                            this.term_state = 0;
                            if (vt320.debug > 1) {
                                System.out.println("ESC [ " + this.DCEvars[0] + " l");
                                break Label_7430;
                            }
                            break Label_7430;
                        }
                        case 'A': {
                            int limit;
                            if (this.R > bm) {
                                limit = bm + 1;
                            }
                            else if (this.R >= tm) {
                                limit = tm;
                            }
                            else {
                                limit = 0;
                            }
                            if (this.DCEvars[0] == 0) {
                                --this.R;
                            }
                            else {
                                this.R -= this.DCEvars[0];
                            }
                            if (this.R < limit) {
                                this.R = limit;
                            }
                            this.term_state = 0;
                            if (vt320.debug > 1) {
                                System.out.println("ESC [ " + this.DCEvars[0] + " A");
                                break Label_7430;
                            }
                            break Label_7430;
                        }
                        case 'B': {
                            int limit;
                            if (this.R < tm) {
                                limit = tm - 1;
                            }
                            else if (this.R <= bm) {
                                limit = bm;
                            }
                            else {
                                limit = rows - 1;
                            }
                            if (this.DCEvars[0] == 0) {
                                ++this.R;
                            }
                            else {
                                this.R += this.DCEvars[0];
                            }
                            if (this.R > limit) {
                                this.R = limit;
                            }
                            else if (vt320.debug > 2) {
                                System.out.println("Not limited.");
                            }
                            if (vt320.debug > 2) {
                                System.out.println("to: " + this.R);
                            }
                            this.term_state = 0;
                            if (vt320.debug > 1) {
                                System.out.println("ESC [ " + this.DCEvars[0] + " B (at C=" + this.C + ")");
                                break Label_7430;
                            }
                            break Label_7430;
                        }
                        case 'C': {
                            if (this.DCEvars[0] == 0) {
                                ++this.C;
                            }
                            else {
                                this.C += this.DCEvars[0];
                            }
                            if (this.C > columns - 1) {
                                this.C = columns - 1;
                            }
                            this.term_state = 0;
                            if (vt320.debug > 1) {
                                System.out.println("ESC [ " + this.DCEvars[0] + " C");
                                break Label_7430;
                            }
                            break Label_7430;
                        }
                        case 'd': {
                            this.R = this.DCEvars[0];
                            System.out.println("ESC [ " + this.DCEvars[0] + " d");
                            this.term_state = 0;
                            break Label_7430;
                        }
                        case 'D': {
                            if (this.DCEvars[0] == 0) {
                                --this.C;
                            }
                            else {
                                this.C -= this.DCEvars[0];
                            }
                            if (this.C < 0) {
                                this.C = 0;
                            }
                            this.term_state = 0;
                            if (vt320.debug > 1) {
                                System.out.println("ESC [ " + this.DCEvars[0] + " D");
                                break Label_7430;
                            }
                            break Label_7430;
                        }
                        case 'r': {
                            if (this.DCEvar > 0) {
                                this.R = this.DCEvars[1] - 1;
                                if (this.R < 0) {
                                    this.R = rows - 1;
                                }
                                else if (this.R >= rows) {
                                    this.R = rows - 1;
                                }
                            }
                            else {
                                this.R = rows - 1;
                            }
                            this.display.setBottomMargin(this.DCEvars[1] - 1);
                            if (this.R >= this.DCEvars[0]) {
                                this.R = this.DCEvars[0] - 1;
                                if (this.R < 0) {
                                    this.R = 0;
                                }
                            }
                            this.display.setTopMargin(this.DCEvars[0] - 1);
                            this._SetCursor(0, 0);
                            if (vt320.debug > 1) {
                                System.out.println("ESC [" + this.DCEvars[0] + " ; " + this.DCEvars[1] + " r");
                            }
                            this.term_state = 0;
                            break Label_7430;
                        }
                        case 'G': {
                            this.C = this.DCEvars[0];
                            System.out.println("ESC [ " + this.DCEvars[0] + " G");
                            this.term_state = 0;
                            break Label_7430;
                        }
                        case 'H': {
                            this._SetCursor(this.DCEvars[0] - 1, this.DCEvars[1] - 1);
                            this.term_state = 0;
                            if (vt320.debug > 2) {
                                System.out.println("ESC [ " + this.DCEvars[0] + ";" + this.DCEvars[1] + " H, moveoutsidemargins " + this.moveoutsidemargins);
                                System.out.println("\t-> R now " + this.R + ", C now " + this.C);
                                break Label_7430;
                            }
                            break Label_7430;
                        }
                        case 'f': {
                            this.R = this.DCEvars[0] - 1;
                            this.C = this.DCEvars[1] - 1;
                            if (this.C < 0) {
                                this.C = 0;
                            }
                            if (this.R < 0) {
                                this.R = 0;
                            }
                            this.term_state = 0;
                            if (vt320.debug > 2) {
                                System.out.println("ESC [ " + this.DCEvars[0] + ";" + this.DCEvars[1] + " f");
                                break Label_7430;
                            }
                            break Label_7430;
                        }
                        case 'L': {
                            if (this.DCEvars[0] == 0) {
                                this.display.insertLine(this.R, true);
                            }
                            else {
                                this.display.insertLine(this.R, this.DCEvars[0], true);
                            }
                            this.term_state = 0;
                            if (vt320.debug > 1) {
                                System.out.println("ESC [ " + this.DCEvars[0] + " L (at R " + this.R + ")");
                                break Label_7430;
                            }
                            break Label_7430;
                        }
                        case 'M': {
                            if (vt320.debug > 1) {
                                System.out.println("ESC [ " + this.DCEvars[0] + "M at R=" + this.R);
                            }
                            if (this.DCEvars[0] == 0) {
                                this.display.deleteLine(this.R);
                            }
                            else {
                                for (int i = 0; i < this.DCEvars[0]; ++i) {
                                    this.display.deleteLine(this.R);
                                }
                            }
                            this.term_state = 0;
                            break Label_7430;
                        }
                        case 'K': {
                            if (vt320.debug > 1) {
                                System.out.println("ESC [ " + this.DCEvars[0] + " K");
                            }
                            switch (this.DCEvars[0]) {
                                case 0: {
                                    if (this.C < columns - 1) {
                                        this.display.deleteArea(this.C, this.R, columns - this.C, 1);
                                        break;
                                    }
                                    break;
                                }
                                case 1: {
                                    if (this.C > 0) {
                                        this.display.deleteArea(0, this.R, this.C, 1);
                                        break;
                                    }
                                    break;
                                }
                                case 2: {
                                    this.display.deleteArea(0, this.R, columns, 1);
                                    break;
                                }
                            }
                            this.term_state = 0;
                            break Label_7430;
                        }
                        case 'J': {
                            switch (this.DCEvars[0]) {
                                case 0: {
                                    if (this.R < rows - 1) {
                                        this.display.deleteArea(0, this.R + 1, columns, rows - this.R - 1);
                                    }
                                    if (this.C < columns - 1) {
                                        this.display.deleteArea(this.C, this.R, columns - this.C, 1);
                                        break;
                                    }
                                    break;
                                }
                                case 1: {
                                    if (this.R > 0) {
                                        this.display.deleteArea(0, 0, columns, this.R - 1);
                                    }
                                    if (this.C > 0) {
                                        this.display.deleteArea(0, this.R, this.C, 1);
                                        break;
                                    }
                                    break;
                                }
                                case 2: {
                                    this.display.deleteArea(0, 0, columns, rows);
                                    break;
                                }
                            }
                            if (vt320.debug > 1) {
                                System.out.println("ESC [ " + this.DCEvars[0] + " J");
                            }
                            this.term_state = 0;
                            break Label_7430;
                        }
                        case '@': {
                            if (vt320.debug > 1) {
                                System.out.println("ESC [ " + this.DCEvars[0] + " @");
                            }
                            for (int i = 0; i < this.DCEvars[0]; ++i) {
                                this.display.insertChar(this.C, this.R, ' ', this.attributes);
                            }
                            this.term_state = 0;
                            break Label_7430;
                        }
                        case 'P': {
                            if (vt320.debug > 1) {
                                System.out.println("ESC [ " + this.DCEvars[0] + " P, C=" + this.C + ",R=" + this.R);
                            }
                            if (this.DCEvars[0] == 0) {
                                this.DCEvars[0] = 1;
                            }
                            for (int i = 0; i < this.DCEvars[0]; ++i) {
                                this.display.deleteChar(this.C, this.R);
                            }
                            this.term_state = 0;
                            break Label_7430;
                        }
                        case 'n': {
                            switch (this.DCEvars[0]) {
                                case 5: {
                                    this.host.send("\u001b[0n");
                                    if (vt320.debug > 1) {
                                        System.out.println("ESC[5n");
                                        break;
                                    }
                                    break;
                                }
                                case 6: {
                                    this.host.send("\u001b[" + this.R + ";" + this.C + "R");
                                    if (vt320.debug > 1) {
                                        System.out.println("ESC[6n");
                                        break;
                                    }
                                    break;
                                }
                                default: {
                                    if (vt320.debug > 0) {
                                        System.out.println("ESC [ " + this.DCEvars[0] + " n??");
                                        break;
                                    }
                                    break;
                                }
                            }
                            this.term_state = 0;
                            break Label_7430;
                        }
                        case 'm': {
                            if (vt320.debug > 3) {
                                System.out.print("ESC [ ");
                            }
                            if (this.DCEvar == 0 && this.DCEvars[0] == 0) {
                                this.attributes = 0;
                            }
                            vt320.i = 0;
                        Label_7273_Outer:
                            while (vt320.i <= this.DCEvar) {
                                while (true) {
                                    switch (this.DCEvars[vt320.i]) {
                                        case 0: {
                                            if (this.DCEvar > 0) {
                                                this.attributes = 0;
                                            }
                                            break Label_7273;
                                        }
                                        default: {
                                            System.out.println("ESC [ " + this.DCEvars[vt320.i] + " m unknown...");
                                        }
                                        case 5:
                                        case 25: {
                                            if (vt320.debug > 3) {
                                                System.out.print(this.DCEvars[vt320.i] + ";");
                                            }
                                            ++vt320.i;
                                            continue Label_7273_Outer;
                                        }
                                        case 4: {
                                            this.attributes |= 0x2;
                                            continue;
                                        }
                                        case 1: {
                                            this.attributes |= 0x1;
                                            continue;
                                        }
                                        case 7: {
                                            this.attributes |= 0x4;
                                            continue;
                                        }
                                        case 27: {
                                            this.attributes &= 0xFFFFFFFB;
                                            continue;
                                        }
                                        case 24: {
                                            this.attributes &= 0xFFFFFFFD;
                                            continue;
                                        }
                                        case 22: {
                                            this.attributes &= 0xFFFFFFFE;
                                            continue;
                                        }
                                        case 30:
                                        case 31:
                                        case 32:
                                        case 33:
                                        case 34:
                                        case 35:
                                        case 36:
                                        case 37: {
                                            this.attributes &= 0xFFFFFF87;
                                            this.attributes |= this.DCEvars[vt320.i] - 30 + 1 << 3;
                                            continue;
                                        }
                                        case 39: {
                                            this.attributes &= 0xFFFFFF87;
                                            continue;
                                        }
                                        case 40:
                                        case 41:
                                        case 42:
                                        case 43:
                                        case 44:
                                        case 45:
                                        case 46:
                                        case 47: {
                                            this.attributes &= 0xFFFFF87F;
                                            this.attributes |= this.DCEvars[vt320.i] - 40 + 1 << 7;
                                            continue;
                                        }
                                        case 49: {
                                            this.attributes &= 0xFFFFF87F;
                                            continue;
                                        }
                                    }
                                    break;
                                }
                            }
                            if (vt320.debug > 3) {
                                System.out.print(" (attributes = " + this.attributes + ")m \n");
                            }
                            this.term_state = 0;
                            break Label_7430;
                        }
                        default: {
                            if (vt320.debug > 0) {
                                System.out.println("ESC [ unknown letter:" + c + " (" + (int)c + ")");
                            }
                            this.term_state = 0;
                            break Label_7430;
                        }
                    }
                    break;
                }
                default: {
                    this.term_state = 0;
                    break;
                }
            }
        }
        if (this.C > columns) {
            this.C = columns;
        }
        if (this.R > rows) {
            this.R = rows;
        }
        if (this.C < 0) {
            this.C = 0;
        }
        if (this.R < 0) {
            this.R = 0;
        }
        if (doshowcursor) {
            this.display.setCursorPos(this.C, this.R);
        }
        this.display.markLine(this.R, 1);
    }
    
    public void putString(final String s) {
        final int len = s.length();
        this.display.markLine(this.R, 1);
        for (int i = 0; i < len; ++i) {
            this.putChar(s.charAt(i), false);
        }
        this.display.setCursorPos(this.C, this.R);
        this.display.redraw();
    }
    
    public boolean send(final String s) {
        this.putString(s);
        return true;
    }
    
    public String toString() {
        return "$Id: vt320.java,v 1.60 1999/03/20 18:12:41 marcus Exp $ " + this.display.version;
    }
    
    static String unEscape(final String tmp) {
        int idx = 0;
        int oldidx = 0;
        String cmd = "";
        while ((idx = tmp.indexOf(92, oldidx)) >= 0 && ++idx <= tmp.length()) {
            cmd = String.valueOf(cmd) + tmp.substring(oldidx, idx - 1);
            if (idx == tmp.length()) {
                return cmd;
            }
            switch (tmp.charAt(idx)) {
                case 'b': {
                    cmd = String.valueOf(cmd) + "\b";
                    break;
                }
                case 'e': {
                    cmd = String.valueOf(cmd) + "\u001b";
                    break;
                }
                case 'n': {
                    cmd = String.valueOf(cmd) + "\n";
                    break;
                }
                case 'r': {
                    cmd = String.valueOf(cmd) + "\r";
                    break;
                }
                case 't': {
                    cmd = String.valueOf(cmd) + "\t";
                    break;
                }
                case 'v': {
                    cmd = String.valueOf(cmd) + "\u000b";
                    break;
                }
                case 'a': {
                    cmd = String.valueOf(cmd) + "\u0012";
                    break;
                }
                default: {
                    if (tmp.charAt(idx) >= '0' && tmp.charAt(idx) <= '9') {
                        vt320.i = idx;
                        while (vt320.i < tmp.length() && tmp.charAt(vt320.i) >= '0' && tmp.charAt(vt320.i) <= '9') {
                            ++vt320.i;
                        }
                        cmd = String.valueOf(cmd) + (char)(int)new Integer(tmp.substring(idx, vt320.i));
                        idx = vt320.i - 1;
                        break;
                    }
                    cmd = String.valueOf(cmd) + tmp.substring(idx, ++idx);
                    break;
                }
            }
            oldidx = ++idx;
        }
        if (oldidx <= tmp.length()) {
            cmd = String.valueOf(cmd) + tmp.substring(oldidx);
        }
        return cmd;
    }
}
