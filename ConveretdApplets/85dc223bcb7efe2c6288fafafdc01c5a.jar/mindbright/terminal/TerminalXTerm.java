// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.terminal;

import java.util.NoSuchElementException;

public final class TerminalXTerm extends TerminalInterpreter
{
    public static final boolean DEBUG = false;
    public static final boolean DEBUGNOTIMPL = false;
    public static final boolean DEBUGPRINT = false;
    public static final int CASE_GROUND_STATE = 0;
    public static final int CASE_IGNORE_STATE = 1;
    public static final int CASE_IGNORE_ESC = 2;
    public static final int CASE_IGNORE = 3;
    public static final int CASE_BELL = 4;
    public static final int CASE_BS = 5;
    public static final int CASE_CR = 6;
    public static final int CASE_ESC = 7;
    public static final int CASE_VMOT = 8;
    public static final int CASE_TAB = 9;
    public static final int CASE_SI = 10;
    public static final int CASE_SO = 11;
    public static final int CASE_SCR_STATE = 12;
    public static final int CASE_SCS0_STATE = 13;
    public static final int CASE_SCS1_STATE = 14;
    public static final int CASE_SCS2_STATE = 15;
    public static final int CASE_SCS3_STATE = 16;
    public static final int CASE_ESC_IGNORE = 17;
    public static final int CASE_ESC_DIGIT = 18;
    public static final int CASE_ESC_SEMI = 19;
    public static final int CASE_DEC_STATE = 20;
    public static final int CASE_ICH = 21;
    public static final int CASE_CUU = 22;
    public static final int CASE_CUD = 23;
    public static final int CASE_CUF = 24;
    public static final int CASE_CUB = 25;
    public static final int CASE_CUP = 26;
    public static final int CASE_ED = 27;
    public static final int CASE_EL = 28;
    public static final int CASE_IL = 29;
    public static final int CASE_DL = 30;
    public static final int CASE_DCH = 31;
    public static final int CASE_DA1 = 32;
    public static final int CASE_TRACK_MOUSE = 33;
    public static final int CASE_TBC = 34;
    public static final int CASE_SET = 35;
    public static final int CASE_RST = 36;
    public static final int CASE_SGR = 37;
    public static final int CASE_CPR = 38;
    public static final int CASE_DECSTBM = 39;
    public static final int CASE_DECREQTPARM = 40;
    public static final int CASE_DECSET = 41;
    public static final int CASE_DECRST = 42;
    public static final int CASE_DECALN = 43;
    public static final int CASE_GSETS = 44;
    public static final int CASE_DECSC = 45;
    public static final int CASE_DECRC = 46;
    public static final int CASE_DECKPAM = 47;
    public static final int CASE_DECKPNM = 48;
    public static final int CASE_IND = 49;
    public static final int CASE_NEL = 50;
    public static final int CASE_HTS = 51;
    public static final int CASE_RI = 52;
    public static final int CASE_SS2 = 53;
    public static final int CASE_SS3 = 54;
    public static final int CASE_CSI_STATE = 55;
    public static final int CASE_OSC = 56;
    public static final int CASE_RIS = 57;
    public static final int CASE_LS2 = 58;
    public static final int CASE_LS3 = 59;
    public static final int CASE_LS3R = 60;
    public static final int CASE_LS2R = 61;
    public static final int CASE_LS1R = 62;
    public static final int CASE_PRINT = 63;
    public static final int CASE_XTERM_SAVE = 64;
    public static final int CASE_XTERM_RESTORE = 65;
    public static final int CASE_XTERM_TITLE = 66;
    public static final int CASE_DECID = 67;
    public static final int CASE_HP_MEM_LOCK = 68;
    public static final int CASE_HP_MEM_UNLOCK = 69;
    public static final int CASE_HP_BUGGY_LL = 70;
    public static final int CASE_SEQ_CAPTURE = 71;
    public static final int CASE_ESC_SEMIOSC = 72;
    public static final int CASE_XTERM_SEQ = 73;
    public static final int CASE_ENQ = 74;
    public static final int CASE_XTERMWIN = 75;
    public static final int CASE_CNL = 76;
    public static final int CASE_CPL = 77;
    public static final int CASE_CHA = 78;
    public static final int CASE_CHT = 79;
    public static final int CASE_SU = 80;
    public static final int CASE_SD = 81;
    public static final int CASE_ECH = 82;
    public static final int CASE_CBT = 83;
    public static final int CASE_HPA = 84;
    public static final int CASE_REP = 85;
    public static final int CASE_VPA = 86;
    public static final int[] asciiLineDrawChars;
    public static final int XVK_UP = 0;
    public static final int XVK_DOWN = 1;
    public static final int XVK_RIGHT = 2;
    public static final int XVK_LEFT = 3;
    public static final int XVK_PAGE_UP = 4;
    public static final int XVK_PAGE_DOWN = 5;
    public static final int XVK_END = 6;
    public static final int XVK_HOME = 7;
    public static final int XVK_INSERT = 8;
    public static final int XVK_F1 = 9;
    public static final int XVK_F2 = 10;
    public static final int XVK_F3 = 11;
    public static final int XVK_F4 = 12;
    public static final int XVK_F5 = 13;
    public static final int XVK_F6 = 14;
    public static final int XVK_F7 = 15;
    public static final int XVK_F8 = 16;
    public static final int XVK_F9 = 17;
    public static final int XVK_F10 = 18;
    public static final int XVK_F11 = 19;
    public static final int XVK_F12 = 20;
    public static final int XVK_NUMPAD0 = 21;
    public static final int XVK_NUMPAD1 = 22;
    public static final int XVK_NUMPAD2 = 23;
    public static final int XVK_NUMPAD3 = 24;
    public static final int XVK_NUMPAD4 = 25;
    public static final int XVK_NUMPAD5 = 26;
    public static final int XVK_NUMPAD6 = 27;
    public static final int XVK_NUMPAD7 = 28;
    public static final int XVK_NUMPAD8 = 29;
    public static final int XVK_NUMPAD9 = 30;
    public static final int XVK_MULTIPLY = 31;
    public static final int XVK_ADD = 32;
    public static final int XVK_SUBTRACT = 33;
    public static final int XVK_DIVIDE = 34;
    public static final int XVK_MAX = 35;
    public final int[] vk2xvk;
    public static final int EMUL_XTERM = 0;
    public static final int EMUL_LINUX = 1;
    public static final int EMUL_SCOANSI = 2;
    public static final int EMUL_ATT6386 = 3;
    public static final int EMUL_SUN = 4;
    public static final int EMUL_AIX = 5;
    public static final int EMUL_VT220 = 6;
    public static final int EMUL_VT100 = 7;
    public static final int EMUL_ANSI = 8;
    public static final int EMUL_VT52 = 9;
    public static final int EMUL_ALTERNATENAME = 10;
    public static final int EMUL_XTERMCOL = 10;
    public static final int EMUL_LINUXLAT = 11;
    public static final int EMUL_AT386 = 13;
    public static final int EMUL_VT102 = 17;
    public static final int EMUL_VT320 = 16;
    public static final int DEFAULT_TERM = 0;
    public static final String[] terminalTypes;
    int whoAmI;
    int whoAmIReally;
    public static final String[][] specialKeyMap;
    public static final String[][] specialKeyMapShift;
    public static final String[][] specialKeyMapCtrl;
    public static final String[][] specialKeyMapCtrlShift;
    public static final String[][][] theSpecialKeyMaps;
    public static final int R_ESC = 0;
    public static final int R_SS2 = 1;
    public static final int R_SS3 = 2;
    public static final int R_DCS = 3;
    public static final int R_CSI = 4;
    public static final int R_OSC = 5;
    public static final int R_PM = 6;
    public static final int R_APC = 7;
    public static final String[] replyTypes;
    public static final char CHARSET_UK = 'A';
    public static final char CHARSET_ASCII = 'B';
    public static final char CHARSET_LINES = '0';
    public static final char CHARSET_ASCII_ALT = '1';
    public static final char CHARSET_LINES_ALT = '2';
    char[] gSets;
    int scsType;
    int curGL;
    int curSS;
    int curGLDECSC;
    char[] gSetsDECSC;
    String xtermSeq;
    String reply;
    public static final int PARAMNOTUSED = -1;
    int[] parseState;
    boolean windowRelative;
    boolean keypadAppl;
    boolean cursorKeysMode;
    public static final int MOUSE_DONTSEND = 0;
    public static final int MOUSE_X10COMP = 1;
    public static final int MOUSE_DECVT200 = 2;
    public static final int MOUSE_HLTRACK = 3;
    int sendMousePos;
    int[] param;
    int nparam;
    public static final int[] groundTable;
    public static final int[] csiTable;
    public static final int[] decTable;
    public static final int[] oscTable;
    public static final int[] xtermSeqTable;
    public static final int[] eigTable;
    public static final int[] escTable;
    public static final int[] iesTable;
    public static final int[] ignTable;
    public static final int[] scrTable;
    public static final int[] scsTable;
    
    public static final boolean hasNullPadding(final int personality) {
        return personality == 6 || personality == 7 || personality == 8 || personality == 9;
    }
    
    public TerminalXTerm() {
        this(0);
    }
    
    public TerminalXTerm(final int personality) {
        this.vk2xvk = new int[] { 38, 40, 39, 37, 33, 34, 35, 36, 155, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 109, 111 };
        this.gSets = new char[4];
        this.gSetsDECSC = new char[4];
        this.xtermSeq = null;
        this.reply = null;
        this.param = new int[10];
        try {
            this.setTerminalTypeInternal(personality);
        }
        catch (NoSuchElementException e) {
            try {
                this.setTerminalTypeInternal(0);
            }
            catch (NoSuchElementException ex) {}
        }
    }
    
    public static String listAvailableTerminalTypes() {
        String list = " ";
        for (int i = 0; i < TerminalXTerm.terminalTypes.length; ++i) {
            list = list + TerminalXTerm.terminalTypes[i] + " ";
        }
        return list;
    }
    
    public static String[] getTerminalTypes() {
        int n = 0;
        for (int i = 0; i < TerminalXTerm.terminalTypes.length; ++i) {
            if (!TerminalXTerm.terminalTypes[i].equals("")) {
                ++n;
            }
        }
        final String[] types = new String[n];
        n = 0;
        for (int i = 0; i < TerminalXTerm.terminalTypes.length; ++i) {
            if (!TerminalXTerm.terminalTypes[i].equals("")) {
                types[n++] = TerminalXTerm.terminalTypes[i];
            }
        }
        return types;
    }
    
    public String terminalType() {
        return TerminalXTerm.terminalTypes[this.whoAmI];
    }
    
    private void setTerminalTypeInternal(final int type) throws NoSuchElementException {
        if (type < TerminalXTerm.terminalTypes.length && type > -1) {
            this.whoAmI = type;
            this.whoAmIReally = type;
            if (this.whoAmI >= 10) {
                this.whoAmIReally -= 10;
            }
            this.vtReset();
            return;
        }
        throw new NoSuchElementException(type + " is not a supported terminal-emulation");
    }
    
    protected void setTerminalType(final String type) throws NoSuchElementException {
        int i;
        for (i = 0; i < TerminalXTerm.terminalTypes.length && !TerminalXTerm.terminalTypes[i].equalsIgnoreCase(type); ++i) {}
        this.setTerminalTypeInternal(i);
    }
    
    final int mapLineDrawToAscii(char c) {
        if (c >= '_' && c <= '~') {
            c = (char)TerminalXTerm.asciiLineDrawChars[c - '_'];
        }
        else if (c >= '+' && c <= '.') {
            c = (char)TerminalXTerm.asciiLineDrawChars[c - '+' + ' '];
        }
        else if (c == ' ') {}
        return c;
    }
    
    final char mapLineDrawToLinux(char c) {
        switch (c) {
            case ' ': {
                c = ' ';
                break;
            }
            case '\u0004': {
                c = '`';
                break;
            }
            case '±': {
                c = 'a';
                break;
            }
            case '\u00f8': {
                c = 'f';
                break;
            }
            case '\u00f1': {
                c = 'g';
                break;
            }
            case '°': {
                c = 'h';
                break;
            }
            case '\u0089':
            case '\u00d9': {
                c = 'j';
                break;
            }
            case '\u008c':
            case '¿': {
                c = 'k';
                break;
            }
            case '\u0086':
            case '\u00da': {
                c = 'l';
                break;
            }
            case '\u0083':
            case '\u00c0': {
                c = 'm';
                break;
            }
            case '\u00c5': {
                c = 'n';
                break;
            }
            case '\u008a':
            case '\u00c4': {
                c = 'q';
                break;
            }
            case '\u00f2': {
                c = 'r';
                break;
            }
            case '\u0087':
            case '\u00c3': {
                c = 't';
                break;
            }
            case '\u008d':
            case '´': {
                c = 'u';
                break;
            }
            case '\u00c1': {
                c = 'v';
                break;
            }
            case '\u00c2': {
                c = 'w';
                break;
            }
            case '\u0085':
            case '³': {
                c = 'x';
                break;
            }
            case '\u00f3':
            case '\u00f9': {
                c = 'y';
                break;
            }
            case '\u00fa': {
                c = 'z';
                break;
            }
            case '\u00e3':
            case '\u00fb': {
                c = '{';
                break;
            }
            case '\u00d8':
            case '\u00fc': {
                c = '|';
                break;
            }
            case '\u009c':
            case '\u00fd': {
                c = '}';
                break;
            }
            case '\u00fe': {
                c = '~';
                break;
            }
            case '\u0019': {
                c = '.';
                break;
            }
            case '\u00db': {
                c = '+';
                break;
            }
            case '\u0018': {
                c = '-';
                break;
            }
        }
        return c;
    }
    
    final char mapLineDrawToATT6386(char c) {
        switch (c) {
            case ' ': {
                c = ' ';
                break;
            }
            case '`': {
                c = '`';
                break;
            }
            case '1': {
                c = 'a';
                break;
            }
            case 'x': {
                c = 'f';
                break;
            }
            case 'q': {
                c = 'g';
                break;
            }
            case '0': {
                c = 'h';
                break;
            }
            case 'Y': {
                c = 'j';
                break;
            }
            case '?': {
                c = 'k';
                break;
            }
            case 'Z': {
                c = 'l';
                break;
            }
            case '@': {
                c = 'm';
                break;
            }
            case 'E': {
                c = 'n';
                break;
            }
            case 'o': {
                c = 'o';
                break;
            }
            case 'p': {
                c = 'p';
                break;
            }
            case 'D': {
                c = 'q';
                break;
            }
            case 'r': {
                c = 'r';
                break;
            }
            case 's': {
                c = 's';
                break;
            }
            case 'C': {
                c = 't';
                break;
            }
            case '4': {
                c = 'u';
                break;
            }
            case 'A': {
                c = 'v';
                break;
            }
            case 'B': {
                c = 'w';
                break;
            }
            case '3': {
                c = 'x';
                break;
            }
            case 'y': {
                c = 'y';
                break;
            }
            case 'z': {
                c = 'z';
                break;
            }
            case '{': {
                c = '{';
                break;
            }
            case '|': {
                c = '|';
                break;
            }
            case '}': {
                c = '}';
                break;
            }
            case '~': {
                c = '~';
                break;
            }
        }
        return c;
    }
    
    public int interpretChar(char c) {
        if (c > '\u00ff') {
            return c;
        }
        switch (this.parseState[c]) {
            case 63: {
                int ic = -1;
                switch (this.gSets[this.curGL]) {
                    case 'A': {
                        if (c == '#') {
                            ic = 163;
                            break;
                        }
                        break;
                    }
                    case '1':
                    case 'B': {
                        ic = c;
                        if (ic >= 32) {
                            break;
                        }
                        if (ic == 0 && hasNullPadding(this.whoAmIReally)) {
                            return -1;
                        }
                        this.term.write('^');
                        ic += 64;
                        break;
                    }
                    case '0':
                    case '2': {
                        if (!this.term.getOption(10)) {
                            if (this.whoAmIReally == 1) {
                                c = this.mapLineDrawToLinux(c);
                            }
                            else if (this.whoAmIReally == 3) {
                                c = this.mapLineDrawToATT6386(c);
                            }
                            this.term.writeLineDrawChar(c);
                            break;
                        }
                        ic = this.mapLineDrawToAscii(c);
                        break;
                    }
                }
                return ic;
            }
            case 0: {
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 1: {
                this.parseState = TerminalXTerm.ignTable;
                break;
            }
            case 2: {
                this.parseState = TerminalXTerm.iesTable;
            }
            case 4: {
                this.term.doBell();
                break;
            }
            case 5: {
                this.term.doBS();
                break;
            }
            case 6: {
                this.term.doCR();
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 7: {
                this.parseState = TerminalXTerm.escTable;
                break;
            }
            case 8: {
                this.term.doLF();
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 9: {
                this.term.doTab();
                break;
            }
            case 10: {
                this.curGL = 0;
                break;
            }
            case 11: {
                this.curGL = 1;
                break;
            }
            case 12: {
                this.parseState = TerminalXTerm.scrTable;
                break;
            }
            case 13: {
                this.scsType = 0;
                this.parseState = TerminalXTerm.scsTable;
                break;
            }
            case 14: {
                this.scsType = 1;
                this.parseState = TerminalXTerm.scsTable;
                break;
            }
            case 15: {
                this.scsType = 2;
                this.parseState = TerminalXTerm.scsTable;
                break;
            }
            case 16: {
                this.scsType = 3;
                this.parseState = TerminalXTerm.scsTable;
                break;
            }
            case 17: {
                this.parseState = TerminalXTerm.eigTable;
                break;
            }
            case 18: {
                int val = this.param[this.nparam - 1];
                if (val == -1) {
                    val = 0;
                }
                this.param[this.nparam - 1] = 10 * val + (c - '0');
                break;
            }
            case 19: {
                this.param[this.nparam++] = -1;
                break;
            }
            case 72: {
                this.param[this.nparam++] = -1;
                this.xtermSeq = "";
                this.parseState = TerminalXTerm.xtermSeqTable;
                break;
            }
            case 20: {
                this.parseState = TerminalXTerm.decTable;
                break;
            }
            case 21: {
                int val = this.param[0];
                if (val < 1) {
                    val = 1;
                }
                this.term.insertChars(val);
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 77: {
                this.term.doCR();
            }
            case 22: {
                int val = this.param[0];
                if (val < 1) {
                    val = 1;
                }
                this.term.cursorUp(val);
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 76: {
                this.term.doCR();
            }
            case 23: {
                int val = this.param[0];
                if (val < 1) {
                    val = 1;
                }
                this.term.cursorDown(val);
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 24: {
                int val = this.param[0];
                if (val < 1) {
                    val = 1;
                }
                this.term.cursorForward(val);
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 25: {
                int val = this.param[0];
                if (val < 1) {
                    val = 1;
                }
                this.term.cursorBackward(val);
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 26: {
                int v = this.param[0];
                int h = this.param[1];
                if (v < 1) {
                    v = 1;
                }
                if (this.nparam < 2 || h < 1) {
                    h = 1;
                }
                this.term.cursorSetPos(v - 1, h - 1, this.windowRelative);
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 27: {
                switch (this.param[0]) {
                    case -1:
                    case 0: {
                        this.term.clearBelow();
                        break;
                    }
                    case 1: {
                        this.term.clearAbove();
                        break;
                    }
                    case 2: {
                        this.term.clearScreen();
                        this.term.cursorSetPos(0, 0, this.windowRelative);
                        break;
                    }
                }
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 28: {
                switch (this.param[0]) {
                    case -1:
                    case 0: {
                        this.term.clearRight();
                        break;
                    }
                    case 1: {
                        this.term.clearLeft();
                        break;
                    }
                    case 2: {
                        this.term.clearLine();
                        break;
                    }
                }
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 29: {
                int val = this.param[0];
                if (val < 1) {
                    val = 1;
                }
                this.term.insertLines(val);
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 30: {
                int val = this.param[0];
                if (val < 1) {
                    val = 1;
                }
                this.term.deleteLines(val);
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 31: {
                int val = this.param[0];
                if (val < 1) {
                    val = 1;
                }
                this.term.deleteChars(val);
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 67: {
                this.param[0] = -1;
            }
            case 32: {
                this.reply = TerminalXTerm.replyTypes[4] + "?1;2c";
                this.term.sendBytes(this.reply.getBytes());
                this.parseState = TerminalXTerm.groundTable;
            }
            case 34: {
                if (this.param[0] <= 0) {
                    this.term.clearTab(this.term.getCursorH());
                }
                else if (this.param[0] == 3) {
                    this.term.clearAllTabs();
                }
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 35: {
                this.ansiModes(true);
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 36: {
                this.ansiModes(false);
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 37: {
                this.sgrModes();
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 38: {
                this.reply = null;
                if (this.param[0] == 5) {
                    this.reply = TerminalXTerm.replyTypes[4] + "0n";
                }
                else if (this.param[0] == 6) {
                    this.reply = TerminalXTerm.replyTypes[4] + (this.term.getCursorV() + 1) + ";" + (this.term.getCursorH() + 1) + "R";
                }
                if (this.reply != null) {
                    this.term.sendBytes(this.reply.getBytes());
                }
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 39: {
                int top = this.param[0];
                int bot = this.param[1];
                if (top < 1) {
                    top = 1;
                }
                if (this.nparam < 2 || bot == -1 || bot == 0 || bot > this.term.rows()) {
                    bot = this.term.rows();
                }
                if (bot > top) {
                    this.term.setWindow(top - 1, bot);
                    this.term.cursorSetPos(0, 0, this.windowRelative);
                }
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 40: {
                if (this.param[0] == -1 || this.param[0] == 1 || this.param[0] == 0) {
                    this.reply = TerminalXTerm.replyTypes[4] + String.valueOf(this.term.getCursorV() + 2) + ";1;1;112;112;1;0x";
                }
                if (this.reply != null) {
                    this.term.sendBytes(this.reply.getBytes());
                }
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 41: {
                this.dpModes(true);
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 42: {
                this.dpModes(false);
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 43: {
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 44: {
                this.gSets[this.scsType] = c;
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 45: {
                this.term.cursorSave();
                this.curGLDECSC = this.curGL;
                System.arraycopy(this.gSets, 0, this.gSetsDECSC, 0, 4);
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 46: {
                this.term.cursorRestore();
                this.curGL = this.curGLDECSC;
                System.arraycopy(this.gSetsDECSC, 0, this.gSets, 0, 4);
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 47: {
                this.keypadAppl = true;
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 48: {
                this.keypadAppl = false;
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 49: {
                this.term.cursorIndex(1);
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 50: {
                this.term.cursorIndex(1);
                this.term.doCR();
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 51: {
                this.term.setTab(this.term.getCursorH());
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 52: {
                this.term.cursorIndexRev(1);
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 53: {
                this.curSS = 2;
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 54: {
                this.curSS = 3;
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 55: {
                this.param[0] = -1;
                this.nparam = 1;
                this.parseState = TerminalXTerm.csiTable;
                break;
            }
            case 56: {
                this.param[0] = -1;
                this.nparam = 1;
                this.parseState = TerminalXTerm.oscTable;
                break;
            }
            case 57: {
                this.vtReset();
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 58: {
                this.curGL = 2;
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 59: {
                this.curGL = 3;
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 60: {
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 61: {
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 62: {
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 64: {
                this.xtermSavemodes();
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 65: {
                this.xtermRestoremodes();
                this.parseState = TerminalXTerm.groundTable;
            }
            case 68: {
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 69: {
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 70: {
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 73: {
                this.xtermModes();
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 71: {
                this.xtermSeq += c;
                break;
            }
            case 74: {
                this.term.sendBytes(this.terminalType().getBytes());
                break;
            }
            case 75: {
                this.xtermWinCtrl();
                this.parseState = TerminalXTerm.groundTable;
            }
            case 79: {
                int val = this.param[0];
                if (val == -1) {
                    val = 1;
                }
                this.term.doTabs(val);
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 80: {
                int val = this.param[0];
                if (val == -1) {
                    val = 1;
                }
                this.term.scrollUp(val);
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 81: {
                int val = this.param[0];
                if (val == -1) {
                    val = 1;
                }
                this.term.scrollDown(val);
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 82: {
                int val = this.param[0];
                if (val == -1) {
                    val = 1;
                }
                this.term.eraseChars(val);
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 83: {
                int val = this.param[0];
                if (val == -1) {
                    val = 1;
                }
                this.term.doBackTabs(val);
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 78:
            case 84: {
                int h = this.param[0];
                if (h < 1) {
                    h = 1;
                }
                this.term.cursorSetPos(this.term.getCursorV(), h - 1, false);
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 85: {
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
            case 86: {
                int v = this.param[0];
                if (v < 1) {
                    v = 1;
                }
                this.term.cursorSetPos(v - 1, this.term.getCursorH(), false);
                this.parseState = TerminalXTerm.groundTable;
                break;
            }
        }
        return -1;
    }
    
    protected void ansiModes(final boolean set) {
        for (int i = 0; i < this.nparam; ++i) {
            switch (this.param[i]) {
                case 4: {
                    this.term.setOption(3, set);
                }
                case 20: {
                    this.term.setOption(4, set);
                    break;
                }
            }
        }
    }
    
    protected void sgrModes() {
        for (int i = 0; i < this.nparam; ++i) {
            switch (this.param[i]) {
                case -1:
                case 0: {
                    this.term.clearAllAttributes();
                    break;
                }
                case 1:
                case 5: {
                    this.term.setAttribute(1, true);
                    break;
                }
                case 4: {
                    this.term.setAttribute(4, true);
                    break;
                }
                case 7: {
                    this.term.setAttribute(16, true);
                }
                case 22: {
                    this.term.setAttribute(1, false);
                    break;
                }
                case 24: {
                    this.term.setAttribute(4, false);
                    break;
                }
                case 25: {
                    this.term.setAttribute(1, false);
                    break;
                }
                case 27: {
                    this.term.setAttribute(16, false);
                }
                case 10: {
                    this.gSets[this.scsType] = 'B';
                    break;
                }
                case 11:
                case 12: {
                    this.gSets[this.scsType] = '0';
                    break;
                }
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37: {
                    this.term.setForegroundColor(this.param[i] - 30);
                    break;
                }
                case 90:
                case 91:
                case 92:
                case 93:
                case 94:
                case 95:
                case 96:
                case 97: {
                    this.term.setForegroundColor(this.param[i] - 90);
                    break;
                }
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47: {
                    this.term.setBackgroundColor(this.param[i] - 40);
                    break;
                }
                case 100:
                case 101:
                case 102:
                case 103:
                case 104:
                case 105:
                case 106:
                case 107: {
                    this.term.setBackgroundColor(this.param[i] - 100);
                    break;
                }
                case 39: {
                    this.term.setForegroundColor(-1);
                    break;
                }
                case 49: {
                    this.term.setBackgroundColor(-1);
                    break;
                }
            }
        }
    }
    
    protected void dpModes(final boolean set) {
        for (int i = 0; i < this.nparam; ++i) {
            switch (this.param[i]) {
                case 1: {
                    this.cursorKeysMode = set;
                    break;
                }
                case 2: {
                    if (set) {
                        this.resetGSets();
                        break;
                    }
                    break;
                }
                case 3: {
                    this.term.setOption(15, set);
                }
                case 5: {
                    this.term.setOption(0, set);
                    break;
                }
                case 6: {
                    this.windowRelative = set;
                    break;
                }
                case 7: {
                    this.term.setOption(1, set);
                }
                case 9: {
                    if (set) {
                        this.sendMousePos = 1;
                        break;
                    }
                    this.sendMousePos = 0;
                    break;
                }
                case 18: {}
                case 25: {
                    this.term.setOption(9, set);
                }
                case 40: {
                    this.term.setOption(16, set);
                }
                case 41: {}
                case 42: {}
                case 45: {
                    this.term.setOption(2, set);
                }
                case 47: {
                    this.toggleAlternateBuffer(set);
                }
                case 1000: {
                    if (set) {
                        this.sendMousePos = 2;
                        break;
                    }
                    this.sendMousePos = 0;
                    break;
                }
                case 1001: {
                    if (set) {
                        this.sendMousePos = 3;
                        break;
                    }
                    this.sendMousePos = 0;
                    break;
                }
                case 1002: {}
                case 1003: {}
                case 1047: {}
                case 1048: {}
            }
        }
    }
    
    protected void xtermModes() {
        switch (this.param[0]) {
            case 0:
            case 1:
            case 2: {
                if (this.term instanceof TerminalWin) {
                    ((TerminalWin)this.term).setTitle(this.xtermSeq);
                    break;
                }
                break;
            }
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17: {}
            case 20: {}
            case 39: {}
            case 49: {}
            case 46: {}
        }
    }
    
    protected void xtermWinCtrl() {
        for (int i = 0; i < this.nparam; ++i) {
            switch (this.param[i]) {
                case 1: {}
                case 2: {}
                case 3: {}
                case 4: {}
                case 5: {}
                case 6: {}
                case 7: {}
                case 8: {}
                case 11: {}
                case 13: {}
                case 14: {}
                case 18: {}
                case 20: {}
            }
        }
    }
    
    protected void xtermSavemodes() {
    }
    
    protected void xtermRestoremodes() {
    }
    
    protected void toggleAlternateBuffer(final boolean set) {
    }
    
    protected final void resetGSets() {
        this.gSets[0] = 'B';
        this.gSets[1] = '0';
        this.gSets[2] = 'B';
        this.gSets[3] = 'B';
        this.curGL = 0;
    }
    
    public void vtReset() {
        this.resetGSets();
        this.curSS = 0;
        this.parseState = TerminalXTerm.groundTable;
        this.windowRelative = false;
        this.keypadAppl = false;
        this.cursorKeysMode = false;
        this.sendMousePos = 0;
        if (this.term != null) {
            this.term.resetWindow();
            this.term.clearScreen();
            this.term.cursorSetPos(0, 0, false);
            this.term.resetTabs();
        }
    }
    
    protected void notImplemented(final String cmd) {
        System.out.println("not implemented: " + cmd);
    }
    
    final int mapVKToXVK(final int vk) {
        int i;
        for (i = 0; i < 35 && this.vk2xvk[i] != vk; ++i) {}
        return i;
    }
    
    public final int mapModToTab(final int modifiers) {
        int table = 0;
        if ((modifiers & 0x1) != 0x0) {
            table = 1;
        }
        if ((modifiers & 0x2) != 0x0) {
            table += 2;
        }
        return table;
    }
    
    public final String mapSpecialKeys(final int virtualKey, final int modifiers) {
        final int xvk = this.mapVKToXVK(virtualKey);
        final int modTable = this.mapModToTab(modifiers);
        final String[][] specKeyMap = TerminalXTerm.theSpecialKeyMaps[modTable];
        return specKeyMap[xvk][this.whoAmIReally];
    }
    
    public void keyHandler(final int virtualKey, final int modifiers) {
        String specialKey = null;
        String prefix = "";
        switch (virtualKey) {
            case 37:
            case 38:
            case 39:
            case 40: {
                if (this.cursorKeysMode) {
                    prefix = TerminalXTerm.replyTypes[2];
                }
                else {
                    prefix = TerminalXTerm.replyTypes[4];
                }
                specialKey = this.mapSpecialKeys(virtualKey, modifiers);
                break;
            }
            case 33:
            case 34:
            case 35:
            case 36: {
                if (!this.term.getOption(7)) {
                    prefix = TerminalXTerm.replyTypes[4];
                    specialKey = this.mapSpecialKeys(virtualKey, modifiers);
                    break;
                }
                break;
            }
            case 155: {
                prefix = TerminalXTerm.replyTypes[4];
                specialKey = this.mapSpecialKeys(virtualKey, modifiers);
                break;
            }
            case 112:
            case 113:
            case 114:
            case 115:
            case 116:
            case 117:
            case 118:
            case 119:
            case 120:
            case 121:
            case 122:
            case 123: {
                if (this.whoAmIReally == 7 || this.whoAmIReally == 8 || this.whoAmIReally == 3) {
                    prefix = TerminalXTerm.replyTypes[2];
                }
                else if (this.whoAmIReally == 6) {
                    if (virtualKey > 115) {
                        prefix = TerminalXTerm.replyTypes[4];
                    }
                    else {
                        prefix = TerminalXTerm.replyTypes[2];
                    }
                }
                else if (this.whoAmIReally == 9) {
                    prefix = TerminalXTerm.replyTypes[0];
                }
                else {
                    prefix = TerminalXTerm.replyTypes[4];
                }
                specialKey = this.mapSpecialKeys(virtualKey, modifiers);
                break;
            }
        }
        if (specialKey != null) {
            specialKey = prefix + specialKey;
            this.term.sendBytes(specialKey.getBytes());
        }
    }
    
    int xButton(final int modifiers) {
        int butt = 0;
        if ((modifiers & 0x10) != 0x0) {
            butt = 0;
        }
        else if ((modifiers & 0x8) != 0x0) {
            butt = 1;
        }
        else if ((modifiers & 0x4) != 0x0) {
            butt = 2;
        }
        return butt;
    }
    
    int xKeyState(final int modifiers) {
        int key = 0;
        if ((modifiers & 0x1) != 0x0) {
            key |= 0x1;
        }
        if ((modifiers & 0x8) != 0x0) {
            key |= 0x2;
        }
        if ((modifiers & 0x2) != 0x0) {
            key |= 0x4;
        }
        return key << 2;
    }
    
    public void mouseHandler(final int row, final int col, final boolean press, final int modifiers) {
        switch (this.sendMousePos) {
            case 1: {
                if (press) {
                    this.term.sendBytes(("\u001b[M" + (char)(32 + this.xButton(modifiers)) + (char)(32 + col + 1) + (char)(32 + row + 1)).getBytes());
                    break;
                }
                break;
            }
            case 2: {
                this.term.sendBytes(("\u001b[M" + (char)(32 + (press ? (this.xButton(modifiers) | this.xKeyState(modifiers)) : 3)) + (char)(32 + col + 1) + (char)(32 + row + 1)).getBytes());
                break;
            }
        }
    }
    
    static {
        asciiLineDrawChars = new int[] { 32, 43, 58, 32, 32, 32, 32, 92, 35, 35, 35, 43, 43, 43, 43, 43, 126, 45, 45, 45, 95, 43, 43, 43, 43, 124, 60, 62, 42, 33, 102, 111, 62, 60, 94, 118 };
        terminalTypes = new String[] { "xterm", "linux", "scoansi", "att6386", "sun", "aixterm", "vt220", "vt100", "ansi", "vt52", "xterm-color", "linux-lat", "", "at386", "", "", "vt320", "vt102" };
        specialKeyMap = new String[][] { { "A", "A", "A", "A", "A", "A", "A", "A", "A", "A" }, { "B", "B", "B", "B", "B", "B", "B", "B", "B", "B" }, { "C", "C", "C", "C", "C", "C", "C", "C", "C", "C" }, { "D", "D", "D", "D", "D", "D", "D", "D", "D", "D" }, { "5~", "5~", "I", "V", "216z", "150q", "5~", "5~", "5~", "5~" }, { "6~", "6~", "G", "U", "222z", "154q", "6~", "6~", "6~", "6~" }, { "F", "4~", "F", "Y", "220z", "146q", "4~", "4~", "4~", "4~" }, { "H", "1~", "H", "H", "214z", "H", "1~", "1~", "1~", "1~" }, { "2~", "2~", "L", "@", "2~", "139q", "2~", "2~", "L", "L" }, { "11~", "[A", "M", "P", "224z", "001q", "P", "P", "P", "P" }, { "12~", "[B", "N", "Q", "225z", "002q", "Q", "Q", "Q", "Q" }, { "13~", "[C", "O", "R", "226z", "003q", "R", "R", "R", "R" }, { "14~", "[D", "P", "S", "227z", "004q", "S", "S", "S", "S" }, { "15~", "[E", "Q", "T", "228z", "005q", "17~", null, null, null }, { "17~", "17~", "R", "U", "229z", "006q", "18~", null, null, null }, { "18~", "18~", "S", "V", "230z", "007q", "19~", null, null, null }, { "19~", "19~", "T", "W", "231z", "008q", "20~", null, null, null }, { "20~", "20~", "U", "X", "232z", "009q", "21~", null, null, null }, { "21~", "21~", "V", "Y", "233z", "010q", "29~", null, null, null }, { "23~", "23~", null, "Z", "234z", "011q", null, null, null, null }, { "24~", "24~", null, "A", "235z", "012q", null, null, null, null } };
        specialKeyMapShift = new String[][] { { "A", "A", "A", "A", "A", "A", "A", "A", "A", "A" }, { "B", "B", "B", "B", "B", "B", "B", "B", "B", "B" }, { "C", "C", "C", "C", "C", "C", "C", "C", "C", "C" }, { "D", "D", "D", "D", "D", "D", "D", "D", "D", "D" }, { "5~", "5~", "I", "V", "216z", "150q", "5~", "5~", "5~", "5~" }, { "6~", "6~", "G", "U", "222z", "154q", "6~", "6~", "6~", "6~" }, { "4~", "4~", "F", "Y", "220z", "146q", "4~", "4~", "4~", "4~" }, { "@", "1~", "H", "H", "214z", "H", "1~", "1~", "1~", "1~" }, { "2~", "2~", "L", "@", "2~", "139q", "2~", "2~", "L", "L" }, { "23~", "23~", "M", "P", "224z", "013q", "P", "P", "P", "P" }, { "24~", "24~", "N", "Q", "225z", "014q", "Q", "Q", "Q", "Q" }, { "25~", "25~", "O", "R", "226z", "015q", "R", "R", "R", "R" }, { "26~", "26~", "P", "S", "227z", "016q", "S", "S", "S", "S" }, { "28~", "28~", "Q", "T", "228z", "017q", "17~", null, null, null }, { "29~", "29~", "R", "U", "229z", "018q", "18~", null, null, null }, { "31~", "31~", "S", "V", "230z", "019q", "19~", null, null, null }, { "32~", "32~", "T", "W", "231z", "020q", "20~", null, null, null }, { "33~", "33~", "U", "X", "232z", "021q", "21~", null, null, null }, { "34~", "34~", "V", "Y", "233z", "022q", "29~", null, null, null }, { "23$", null, null, "Z", "234z", "023q", null, null, null, null }, { "24$", null, null, "A", "235z", "024q", null, null, null, null } };
        specialKeyMapCtrl = new String[][] { { "A", "A", "A", "A", "A", "A", "A", "A", "A", "A" }, { "B", "B", "B", "B", "B", "B", "B", "B", "B", "B" }, { "C", "C", "C", "C", "C", "C", "C", "C", "C", "C" }, { "D", "D", "D", "D", "D", "D", "D", "D", "D", "D" }, { "5~", "5~", "I", "V", "216z", "150q", "5~", "5~", "5~", "5~" }, { "6~", "6~", "G", "U", "222z", "154q", "6~", "6~", "6~", "6~" }, { "4~", "4~", "F", "Y", "220z", "146q", "4~", "4~", "4~", "4~" }, { "@", "1~", "H", "H", "214z", "H", "1~", "1~", "1~", "1~" }, { "2~", "2~", "L", "@", "2~", "139q", "2~", "2~", "L", "L" }, { "11^", null, "M", "P", "224z", "025q", "P", "P", "P", "P" }, { "12^", null, "N", "Q", "225z", "026q", "Q", "Q", "Q", "Q" }, { "13^", null, "O", "R", "226z", "027q", "R", "R", "R", "R" }, { "14^", null, "P", "S", "227z", "028q", "S", "S", "S", "S" }, { "15^", null, "Q", "T", "228z", "029q", "17~", null, null, null }, { "17^", null, "R", "U", "229z", "030q", "18~", null, null, null }, { "18^", null, "S", "V", "230z", "031q", "19~", null, null, null }, { "19^", null, "T", "W", "231z", "032q", "20~", null, null, null }, { "20^", null, "U", "X", "232z", "033q", "21~", null, null, null }, { "21^", null, "V", "Y", "233z", "034q", "29~", null, null, null }, { "23^", null, null, "Z", "234z", "035q", null, null, null, null }, { "24^", null, null, "A", "235z", "036q", null, null, null, null } };
        specialKeyMapCtrlShift = new String[][] { { "A", "A", "A", "A", "A", "A", "A", "A", "A", "A" }, { "B", "B", "B", "B", "B", "B", "B", "B", "B", "B" }, { "C", "C", "C", "C", "C", "C", "C", "C", "C", "C" }, { "D", "D", "D", "D", "D", "D", "D", "D", "D", "D" }, { "5~", "5~", "I", "V", "216z", "150q", "5~", "5~", "5~", "5~" }, { "6~", "6~", "G", "U", "222z", "154q", "6~", "6~", "6~", "6~" }, { "4~", "4~", "F", "Y", "220z", "146q", "4~", "4~", "4~", "4~" }, { "@", "1~", "H", "H", "214z", "H", "1~", "1~", "1~", "1~" }, { "2~", "2~", "L", "@", "2~", "139q", "2~", "2~", "L", "L" }, { "23^", null, "M", "P", "224z", "001q", "P", "P", "P", "P" }, { "24^", null, "N", "Q", "225z", "002q", "Q", "Q", "Q", "Q" }, { "25^", null, "O", "R", "226z", "003q", "R", "R", "R", "R" }, { "26^", null, "P", "S", "227z", "004q", "S", "S", "S", "S" }, { "28^", null, "Q", "T", "228z", "005q", "17~", null, null, null }, { "29^", null, "R", "U", "229z", "006q", "18~", null, null, null }, { "31^", null, "S", "V", "230z", "007q", "19~", null, null, null }, { "32^", null, "T", "W", "231z", "008q", "20~", null, null, null }, { "33^", null, "U", "X", "232z", "009q", "21~", null, null, null }, { "34^", null, "V", "Y", "233z", "010q", "29~", null, null, null }, { "23@", null, null, "Z", "234z", "011q", null, null, null, null }, { "24@", null, null, "A", "235z", "012q", null, null, null, null } };
        theSpecialKeyMaps = new String[][][] { TerminalXTerm.specialKeyMap, TerminalXTerm.specialKeyMapShift, TerminalXTerm.specialKeyMapCtrl, TerminalXTerm.specialKeyMapCtrlShift };
        replyTypes = new String[] { "\u001b", "\u001bN", "\u001bO", "\u001bP", "\u001b[", "\u001b]", "\u001b^", "\u001b_" };
        groundTable = new int[] { 63, 63, 63, 63, 63, 74, 63, 4, 5, 9, 8, 8, 8, 6, 11, 10, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 7, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 0, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63 };
        csiTable = new int[] { 3, 3, 3, 3, 3, 3, 3, 4, 5, 9, 8, 8, 8, 6, 11, 10, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 7, 3, 3, 3, 3, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 3, 19, 3, 3, 3, 20, 21, 22, 23, 24, 25, 76, 77, 78, 26, 79, 27, 28, 29, 30, 0, 0, 31, 0, 0, 80, 81, 0, 0, 0, 82, 0, 83, 0, 0, 0, 81, 0, 84, 0, 85, 32, 86, 0, 26, 34, 35, 0, 0, 0, 36, 37, 38, 0, 0, 0, 39, 45, 75, 46, 0, 0, 40, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        decTable = new int[] { 3, 3, 3, 3, 3, 3, 3, 4, 5, 9, 8, 8, 8, 6, 11, 10, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 7, 3, 3, 3, 3, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 3, 19, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 41, 0, 0, 0, 42, 0, 0, 0, 0, 0, 65, 64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        oscTable = new int[] { 3, 3, 3, 3, 3, 3, 3, 3, 5, 9, 8, 8, 8, 6, 11, 10, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 7, 3, 3, 3, 3, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 3, 72, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 41, 0, 0, 0, 42, 0, 0, 0, 0, 0, 65, 64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        xtermSeqTable = new int[] { 71, 71, 71, 71, 71, 71, 71, 73, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71 };
        eigTable = new int[] { 3, 3, 3, 3, 3, 3, 3, 4, 5, 9, 8, 8, 8, 6, 11, 10, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 7, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        escTable = new int[] { 3, 3, 3, 3, 3, 3, 3, 4, 5, 9, 8, 8, 8, 6, 11, 10, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 7, 3, 3, 3, 3, 17, 17, 17, 12, 17, 17, 17, 17, 13, 14, 15, 16, 17, 17, 17, 17, 0, 0, 0, 0, 0, 0, 0, 45, 46, 0, 0, 0, 0, 47, 48, 0, 0, 0, 0, 0, 49, 50, 70, 0, 51, 0, 0, 0, 0, 52, 53, 54, 1, 0, 0, 0, 66, 0, 0, 0, 0, 0, 67, 55, 0, 56, 1, 1, 0, 0, 0, 57, 0, 0, 0, 0, 0, 0, 0, 0, 68, 69, 58, 59, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 60, 61, 62, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        iesTable = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        ignTable = new int[] { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 3, 0, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        scrTable = new int[] { 3, 3, 3, 3, 3, 3, 3, 4, 5, 9, 8, 8, 8, 6, 11, 10, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 7, 3, 3, 3, 3, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 0, 0, 0, 0, 0, 0, 0, 0, 43, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        scsTable = new int[] { 3, 3, 3, 3, 3, 3, 3, 4, 5, 9, 8, 8, 8, 6, 11, 10, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 7, 3, 3, 3, 3, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 44, 44, 44, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 44, 44, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    }
}
