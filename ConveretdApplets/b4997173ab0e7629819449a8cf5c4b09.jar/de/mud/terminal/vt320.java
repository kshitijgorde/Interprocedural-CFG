// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.terminal;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.Properties;
import java.awt.event.MouseListener;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Font;
import java.awt.event.KeyListener;

public abstract class vt320 extends VDU implements KeyListener
{
    public static final String ID = "$Id: vt320.java,v 2.41 2000/09/04 11:18:15 leo Exp $";
    private static final int debug = 0;
    private boolean localecho;
    private int pressedKey;
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
    boolean capslock;
    boolean numlock;
    int mouserpt;
    byte mousebut;
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
    private static final int TSTATE_CSI_EX = 12;
    private static char[] gx;
    private static char gr;
    private static char gl;
    private static char[] DECSPECIAL;
    private String[] Numpad;
    private String[] FunctionKey;
    private String[] FunctionKeyShift;
    private String[] FunctionKeyCtrl;
    private String[] FunctionKeyAlt;
    private String[] KeyUp;
    private String[] KeyDown;
    private String[] KeyLeft;
    private String[] KeyRight;
    private String KeyTab;
    private String KeyBacktab;
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
    private String Select;
    private String[] KeyHome;
    private String[] KeyEnd;
    private String[] Insert;
    private String[] Remove;
    private String[] PrevScn;
    private String[] NextScn;
    private String[] Escape;
    private String[] BackSpace;
    private String osc;
    private String dcs;
    private int term_state;
    private boolean vms;
    private byte[] Tabs;
    private int[] DCEvars;
    private int DCEvar;
    private static final char[] unimap;
    
    protected abstract void write(final byte[] p0);
    
    protected void beep() {
    }
    
    public void putString(final String s) {
        final int len = s.length();
        if (len > 0) {
            this.markLine(this.R, 1);
            for (int i = 0; i < len; ++i) {
                this.putChar(s.charAt(i), false);
            }
            this.setCursorPosition(this.C, this.R);
            this.redraw();
        }
    }
    
    public vt320(final int width, final int height, final Font font) {
        super(width, height, font);
        this.localecho = false;
        this.pressedKey = 32;
        this.terminalID = "vt320";
        this.attributes = 0;
        this.insertmode = 0;
        this.statusmode = 0;
        this.vt52mode = 0;
        this.normalcursor = 0;
        this.moveoutsidemargins = true;
        this.sendcrlf = true;
        this.capslock = false;
        this.numlock = false;
        this.mouserpt = 0;
        this.mousebut = 0;
        this.useibmcharset = false;
        this.term_state = 0;
        this.vms = false;
        this.DCEvars = new int[10];
        this.setVMS(false);
        this.setIBMCharset(false);
        this.setTerminalID("vt320");
        this.setBufferSize(100);
        this.setBorder(2, false);
        int nw = this.getColumns();
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
        this.Insert = new String[4];
        this.Remove = new String[4];
        this.KeyHome = new String[4];
        this.KeyEnd = new String[4];
        this.NextScn = new String[4];
        this.PrevScn = new String[4];
        this.Escape = new String[4];
        this.BackSpace = new String[4];
        final String[] insert = this.Insert;
        final int n = 0;
        final String[] insert2 = this.Insert;
        final int n2 = 1;
        final String[] insert3 = this.Insert;
        final int n3 = 2;
        final String[] insert4 = this.Insert;
        final int n4 = 3;
        final String s = "\u001b[2~";
        insert3[n3] = (insert4[n4] = s);
        insert[n] = (insert2[n2] = s);
        final String[] remove = this.Remove;
        final int n5 = 0;
        final String[] remove2 = this.Remove;
        final int n6 = 1;
        final String[] remove3 = this.Remove;
        final int n7 = 2;
        final String[] remove4 = this.Remove;
        final int n8 = 3;
        final String s2 = "\u001b[3~";
        remove3[n7] = (remove4[n8] = s2);
        remove[n5] = (remove2[n6] = s2);
        final String[] prevScn = this.PrevScn;
        final int n9 = 0;
        final String[] prevScn2 = this.PrevScn;
        final int n10 = 1;
        final String[] prevScn3 = this.PrevScn;
        final int n11 = 2;
        final String[] prevScn4 = this.PrevScn;
        final int n12 = 3;
        final String s3 = "\u001b[5~";
        prevScn3[n11] = (prevScn4[n12] = s3);
        prevScn[n9] = (prevScn2[n10] = s3);
        final String[] nextScn = this.NextScn;
        final int n13 = 0;
        final String[] nextScn2 = this.NextScn;
        final int n14 = 1;
        final String[] nextScn3 = this.NextScn;
        final int n15 = 2;
        final String[] nextScn4 = this.NextScn;
        final int n16 = 3;
        final String s4 = "\u001b[6~";
        nextScn3[n15] = (nextScn4[n16] = s4);
        nextScn[n13] = (nextScn2[n14] = s4);
        final String[] keyHome = this.KeyHome;
        final int n17 = 0;
        final String[] keyHome2 = this.KeyHome;
        final int n18 = 1;
        final String[] keyHome3 = this.KeyHome;
        final int n19 = 2;
        final String[] keyHome4 = this.KeyHome;
        final int n20 = 3;
        final String s5 = "\u001b[H";
        keyHome3[n19] = (keyHome4[n20] = s5);
        keyHome[n17] = (keyHome2[n18] = s5);
        final String[] keyEnd = this.KeyEnd;
        final int n21 = 0;
        final String[] keyEnd2 = this.KeyEnd;
        final int n22 = 1;
        final String[] keyEnd3 = this.KeyEnd;
        final int n23 = 2;
        final String[] keyEnd4 = this.KeyEnd;
        final int n24 = 3;
        final String s6 = "\u001b[F";
        keyEnd3[n23] = (keyEnd4[n24] = s6);
        keyEnd[n21] = (keyEnd2[n22] = s6);
        final String[] escape = this.Escape;
        final int n25 = 0;
        final String[] escape2 = this.Escape;
        final int n26 = 1;
        final String[] escape3 = this.Escape;
        final int n27 = 2;
        final String[] escape4 = this.Escape;
        final int n28 = 3;
        final String s7 = "\u001b";
        escape3[n27] = (escape4[n28] = s7);
        escape[n25] = (escape2[n26] = s7);
        final String[] backSpace = this.BackSpace;
        final int n29 = 0;
        final String[] backSpace2 = this.BackSpace;
        final int n30 = 1;
        final String[] backSpace3 = this.BackSpace;
        final int n31 = 2;
        final String[] backSpace4 = this.BackSpace;
        final int n32 = 3;
        final String s8 = "\b";
        backSpace3[n31] = (backSpace4[n32] = s8);
        backSpace[n29] = (backSpace2[n30] = s8);
        this.Find = "\u001b[1~";
        this.Select = "\u001b[4~";
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
        (this.KeyUp = new String[4])[0] = "\u001b[A";
        (this.KeyDown = new String[4])[0] = "\u001b[B";
        (this.KeyRight = new String[4])[0] = "\u001b[C";
        (this.KeyLeft = new String[4])[0] = "\u001b[D";
        (this.Numpad = new String[10])[0] = "\u001bOp";
        this.Numpad[1] = "\u001bOq";
        this.Numpad[2] = "\u001bOr";
        this.Numpad[3] = "\u001bOs";
        this.Numpad[4] = "\u001bOt";
        this.Numpad[5] = "\u001bOu";
        this.Numpad[6] = "\u001bOv";
        this.Numpad[7] = "\u001bOw";
        this.Numpad[8] = "\u001bOx";
        this.Numpad[9] = "\u001bOy";
        this.KPMinus = "\u001bOm";
        this.KPComma = "\u001bOl";
        this.KPPeriod = "\u001bOn";
        this.KPEnter = "\u001bOM";
        this.addKeyListener(this);
        this.addMouseListener(new MouseAdapter() {
            public void mouseEntered(final MouseEvent evt) {
                vt320.this.requestFocus();
            }
            
            public void mousePressed(final MouseEvent evt) {
                if (vt320.this.mouserpt == 0) {
                    return;
                }
                final int mods = evt.getModifiers();
                vt320.this.mousebut = 3;
                if ((mods & 0x10) == 0x10) {
                    vt320.this.mousebut = 0;
                }
                if ((mods & 0x8) == 0x8) {
                    vt320.this.mousebut = 1;
                }
                if ((mods & 0x4) == 0x4) {
                    vt320.this.mousebut = 2;
                }
                int mousecode;
                if (vt320.this.mouserpt == 9) {
                    mousecode = (0x20 | vt320.this.mousebut);
                }
                else {
                    mousecode = (vt320.this.mousebut | 0x20 | (mods & 0x7) << 2);
                }
                final Point pos = vt320.this.mouseGetPos(evt.getPoint());
                final byte[] b = { 27, 91, 77, (byte)mousecode, (byte)(32 + pos.x + 1), (byte)(32 + pos.y + 1) };
                vt320.this.write(b);
            }
            
            public void mouseReleased(final MouseEvent evt) {
                if (vt320.this.mouserpt == 0) {
                    return;
                }
                final Point pos = vt320.this.mouseGetPos(evt.getPoint());
                final int mods = evt.getModifiers();
                int mousecode;
                if (vt320.this.mouserpt == 9) {
                    mousecode = 32 + vt320.this.mousebut;
                }
                else {
                    mousecode = 35;
                }
                final byte[] b = { 27, 91, 77, (byte)mousecode, (byte)(32 + pos.x + 1), (byte)(32 + pos.y + 1) };
                vt320.this.write(b);
                vt320.this.mousebut = 0;
            }
        });
    }
    
    public vt320(final int width, final int height) {
        this(width, height, new Font("Monospaced", 0, 10));
    }
    
    public vt320(final Font font) {
        this(80, 24, font);
    }
    
    public vt320() {
        this(80, 24, new Font("Monospaced", 0, 10));
    }
    
    public void setLocalEcho(final boolean echo) {
        this.localecho = echo;
    }
    
    public void setVMS(final boolean vms) {
        this.vms = vms;
    }
    
    public void setIBMCharset(final boolean ibm) {
        this.useibmcharset = ibm;
    }
    
    public void setKeyCodes(final Properties codes) {
        final String[] prefixes = { "", "S", "C", "A" };
        for (int i = 0; i < 10; ++i) {
            final String res = codes.getProperty("NUMPAD" + i);
            if (res != null) {
                this.Numpad[i] = unEscape(res);
            }
        }
        for (int i = 1; i < 20; ++i) {
            String res = codes.getProperty("F" + i);
            if (res != null) {
                this.FunctionKey[i] = unEscape(res);
            }
            res = codes.getProperty("SF" + i);
            if (res != null) {
                this.FunctionKeyShift[i] = unEscape(res);
            }
            res = codes.getProperty("CF" + i);
            if (res != null) {
                this.FunctionKeyCtrl[i] = unEscape(res);
            }
            res = codes.getProperty("AF" + i);
            if (res != null) {
                this.FunctionKeyAlt[i] = unEscape(res);
            }
        }
        for (int i = 0; i < 4; ++i) {
            String res = codes.getProperty(prefixes[i] + "PGUP");
            if (res != null) {
                this.PrevScn[i] = unEscape(res);
            }
            res = codes.getProperty(prefixes[i] + "PGDOWN");
            if (res != null) {
                this.NextScn[i] = unEscape(res);
            }
            res = codes.getProperty(prefixes[i] + "END");
            if (res != null) {
                this.KeyEnd[i] = unEscape(res);
            }
            res = codes.getProperty(prefixes[i] + "HOME");
            if (res != null) {
                this.KeyHome[i] = unEscape(res);
            }
            res = codes.getProperty(prefixes[i] + "INSERT");
            if (res != null) {
                this.Insert[i] = unEscape(res);
            }
            res = codes.getProperty(prefixes[i] + "REMOVE");
            if (res != null) {
                this.Remove[i] = unEscape(res);
            }
            res = codes.getProperty(prefixes[i] + "UP");
            if (res != null) {
                this.KeyUp[i] = unEscape(res);
            }
            res = codes.getProperty(prefixes[i] + "DOWN");
            if (res != null) {
                this.KeyDown[i] = unEscape(res);
            }
            res = codes.getProperty(prefixes[i] + "LEFT");
            if (res != null) {
                this.KeyLeft[i] = unEscape(res);
            }
            res = codes.getProperty(prefixes[i] + "RIGHT");
            if (res != null) {
                this.KeyRight[i] = unEscape(res);
            }
            res = codes.getProperty(prefixes[i] + "ESCAPE");
            if (res != null) {
                this.Escape[i] = unEscape(res);
            }
            res = codes.getProperty(prefixes[i] + "BACKSPACE");
            if (res != null) {
                this.BackSpace[i] = unEscape(res);
            }
        }
    }
    
    public void setTerminalID(final String terminalID) {
        this.terminalID = terminalID;
    }
    
    public String getTerminalID() {
        return this.terminalID;
    }
    
    private boolean write(final String s, final boolean doecho) {
        this.write(s.getBytes());
        if (doecho) {
            this.putString(s);
        }
        return true;
    }
    
    private boolean write(final String s) {
        return this.write(s, this.localecho);
    }
    
    static String unEscape(final String tmp) {
        int idx = 0;
        int oldidx = 0;
        String cmd = "";
        while ((idx = tmp.indexOf(92, oldidx)) >= 0 && ++idx <= tmp.length()) {
            cmd += tmp.substring(oldidx, idx - 1);
            if (idx == tmp.length()) {
                return cmd;
            }
            switch (tmp.charAt(idx)) {
                case 'b': {
                    cmd += "\b";
                    break;
                }
                case 'e': {
                    cmd += "\u001b";
                    break;
                }
                case 'n': {
                    cmd += "\n";
                    break;
                }
                case 'r': {
                    cmd += "\r";
                    break;
                }
                case 't': {
                    cmd += "\t";
                    break;
                }
                case 'v': {
                    cmd += "\u000b";
                    break;
                }
                case 'a': {
                    cmd += "\u0012";
                    break;
                }
                default: {
                    if (tmp.charAt(idx) >= '0' && tmp.charAt(idx) <= '9') {
                        vt320.i = idx;
                        while (vt320.i < tmp.length() && tmp.charAt(vt320.i) >= '0' && tmp.charAt(vt320.i) <= '9') {
                            ++vt320.i;
                        }
                        cmd += (char)Integer.parseInt(tmp.substring(idx, vt320.i));
                        idx = vt320.i - 1;
                        break;
                    }
                    cmd += tmp.substring(idx, ++idx);
                    break;
                }
            }
            oldidx = ++idx;
        }
        if (oldidx <= tmp.length()) {
            cmd += tmp.substring(oldidx);
        }
        return cmd;
    }
    
    public void keyTyped(final KeyEvent evt) {
        final boolean control = evt.isControlDown();
        final boolean shift = evt.isShiftDown();
        final boolean alt = evt.isAltDown();
        final int keyCode = evt.getKeyCode();
        final char keyChar = evt.getKeyChar();
        if (keyChar == '\u001b' || keyChar == '\b') {
            return;
        }
        if (shift && keyChar == '\t') {
            this.write(this.KeyBacktab, false);
            return;
        }
        if (alt) {
            this.write("" + (char)(keyChar | '\u0080'));
            return;
        }
        if ((keyChar != '\r' && keyChar != '\n') || control) {
            this.write("" + keyChar);
        }
    }
    
    public void keyReleased(final KeyEvent evt) {
    }
    
    public void keyPressed(final KeyEvent evt) {
        final boolean control = evt.isControlDown();
        final boolean shift = evt.isShiftDown();
        final boolean alt = evt.isAltDown();
        final int keyCode = evt.getKeyCode();
        final char keyChar = evt.getKeyChar();
        if ((keyCode == 10 || keyChar == '\n') && !control) {
            this.write("\n", false);
            if (this.localecho) {
                this.putString("\r\n");
            }
        }
        if (((!this.vms && keyChar == '2') || keyChar == ' ') && control) {
            this.write("\u0000");
        }
        if (this.vms) {
            if (keyChar == '\b') {
                if (shift && !control) {
                    this.write("\n");
                }
                else if (control && !shift) {
                    this.write("\u0018");
                }
                else {
                    this.write("\u007f");
                }
            }
            else if (keyChar == '\u007f' && !control) {
                if (shift) {
                    this.write(this.Insert[0]);
                }
                else {
                    this.write(this.Remove[0]);
                }
            }
            else if (control) {
                switch (keyChar) {
                    case '0': {
                        this.write(this.Numpad[0]);
                        return;
                    }
                    case '1': {
                        this.write(this.Numpad[1]);
                        return;
                    }
                    case '2': {
                        this.write(this.Numpad[2]);
                        return;
                    }
                    case '3': {
                        this.write(this.Numpad[3]);
                        return;
                    }
                    case '4': {
                        this.write(this.Numpad[4]);
                        return;
                    }
                    case '5': {
                        this.write(this.Numpad[5]);
                        return;
                    }
                    case '6': {
                        this.write(this.Numpad[6]);
                        return;
                    }
                    case '7': {
                        this.write(this.Numpad[7]);
                        return;
                    }
                    case '8': {
                        this.write(this.Numpad[8]);
                        return;
                    }
                    case '9': {
                        this.write(this.Numpad[9]);
                        return;
                    }
                    case '.': {
                        this.write(this.KPPeriod);
                        return;
                    }
                    case '\u001f':
                    case '-': {
                        this.write(this.KPMinus);
                        return;
                    }
                    case '+': {
                        this.write(this.KPComma);
                        return;
                    }
                    case '\n': {
                        this.write(this.KPEnter);
                        return;
                    }
                    case '/': {
                        this.write(this.PF2);
                        return;
                    }
                    case '*': {
                        this.write(this.PF3);
                        return;
                    }
                }
            }
            if (shift && keyChar < ' ') {
                this.write(this.PF1 + (char)(keyChar + '@'));
            }
            return;
        }
        int xind = 0;
        String[] fmap = this.FunctionKey;
        if (shift) {
            fmap = this.FunctionKeyShift;
            xind = 1;
        }
        if (control) {
            fmap = this.FunctionKeyCtrl;
            xind = 2;
        }
        if (alt) {
            fmap = this.FunctionKeyAlt;
            xind = 3;
        }
        if (evt.isActionKey()) {
            switch (keyCode) {
                case 96: {
                    this.write(this.Numpad[0], false);
                    break;
                }
                case 97: {
                    this.write(this.Numpad[1], false);
                    break;
                }
                case 98: {
                    this.write(this.Numpad[2], false);
                    break;
                }
                case 99: {
                    this.write(this.Numpad[3], false);
                    break;
                }
                case 100: {
                    this.write(this.Numpad[4], false);
                    break;
                }
                case 101: {
                    this.write(this.Numpad[5], false);
                    break;
                }
                case 102: {
                    this.write(this.Numpad[6], false);
                    break;
                }
                case 103: {
                    this.write(this.Numpad[7], false);
                    break;
                }
                case 104: {
                    this.write(this.Numpad[8], false);
                    break;
                }
                case 105: {
                    this.write(this.Numpad[9], false);
                    break;
                }
            }
        }
        switch (keyCode) {
            case 112: {
                this.write(fmap[1], false);
                break;
            }
            case 113: {
                this.write(fmap[2], false);
                break;
            }
            case 114: {
                this.write(fmap[3], false);
                break;
            }
            case 115: {
                this.write(fmap[4], false);
                break;
            }
            case 116: {
                this.write(fmap[5], false);
                break;
            }
            case 117: {
                this.write(fmap[6], false);
                break;
            }
            case 118: {
                this.write(fmap[7], false);
                break;
            }
            case 119: {
                this.write(fmap[8], false);
                break;
            }
            case 120: {
                this.write(fmap[9], false);
                break;
            }
            case 121: {
                this.write(fmap[10], false);
                break;
            }
            case 122: {
                this.write(fmap[11], false);
                break;
            }
            case 123: {
                this.write(fmap[12], false);
                break;
            }
            case 38: {
                this.write(this.KeyUp[xind], false);
                break;
            }
            case 40: {
                this.write(this.KeyDown[xind], false);
                break;
            }
            case 37: {
                this.write(this.KeyLeft[xind], false);
                break;
            }
            case 39: {
                this.write(this.KeyRight[xind], false);
                break;
            }
            case 34: {
                this.write(this.NextScn[xind], false);
                break;
            }
            case 33: {
                this.write(this.PrevScn[xind], false);
                break;
            }
            case 155: {
                this.write(this.Insert[xind], false);
                break;
            }
            case 127: {
                this.write(this.Remove[xind], false);
                break;
            }
            case 27: {
                this.write(this.Escape[xind], false);
                break;
            }
            case 8: {
                this.write(this.BackSpace[xind]);
                break;
            }
            case 36: {
                if (this.vms) {
                    this.write("\b", false);
                    break;
                }
                this.write(this.KeyHome[xind], false);
                break;
            }
            case 35: {
                if (this.vms) {
                    this.write("\u0005", false);
                    break;
                }
                this.write(this.KeyEnd[xind], false);
                break;
            }
            case 144: {
                if (this.vms && control) {
                    if (this.pressedKey != keyCode) {
                        this.pressedKey = keyCode;
                        this.write(this.PF1, false);
                    }
                    else {
                        this.pressedKey = 32;
                    }
                }
                if (!control) {
                    this.numlock = !this.numlock;
                    break;
                }
                break;
            }
            case 20: {
                this.capslock = !this.capslock;
                break;
            }
        }
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
    
    private void _SetCursor(final int row, final int col) {
        int maxr = this.getRows();
        final int tm = this.getTopMargin();
        this.R = ((row < 0) ? 0 : row);
        this.C = ((col < 0) ? 0 : col);
        if (!this.moveoutsidemargins) {
            this.R += this.getTopMargin();
            maxr = this.getBottomMargin();
        }
        if (this.R > maxr) {
            this.R = maxr;
        }
    }
    
    private void putChar(char c, final boolean doshowcursor) {
        final int rows = this.getRows();
        final int columns = this.getColumns();
        final int tm = this.getTopMargin();
        final int bm = this.getBottomMargin();
        this.markLine(this.R, 1);
        if (c > '\u00ff') {}
        Label_6432: {
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
                                    break;
                                }
                                this.insertLine(this.R, 1, true);
                                break;
                            }
                            case '\u0084': {
                                if (this.R == tm - 1 || this.R == bm || this.R == rows - 1) {
                                    this.insertLine(this.R, 1, false);
                                    break;
                                }
                                ++this.R;
                                break;
                            }
                            case '\u0085': {
                                if (this.R == tm - 1 || this.R == bm || this.R == rows - 1) {
                                    this.insertLine(this.R, 1, false);
                                }
                                else {
                                    ++this.R;
                                }
                                this.C = 0;
                                break;
                            }
                            case '\u0088': {
                                this.Tabs[this.C] = 1;
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
                            break Label_6432;
                        }
                        case '\u001b': {
                            this.term_state = 1;
                            vt320.lastwaslf = 0;
                            break Label_6432;
                        }
                        case '\f': {
                            System.out.println("FormFeed ... should we clear the page?");
                            final boolean b = false;
                            this.R = (b ? 1 : 0);
                            this.C = (b ? 1 : 0);
                            break Label_6432;
                        }
                        case '\b': {
                            --this.C;
                            if (this.C < 0) {
                                this.C = 0;
                            }
                            vt320.lastwaslf = 0;
                            break Label_6432;
                        }
                        case '\t': {
                            if (this.insertmode == 1) {
                                int newc = this.C;
                                do {
                                    this.insertChar(this.C, this.R, ' ', this.attributes);
                                } while (++newc < columns && this.Tabs[newc] == 0);
                            }
                            else {
                                do {
                                    this.putChar(this.C++, this.R, ' ', this.attributes);
                                } while (this.C < columns && this.Tabs[this.C] == 0);
                            }
                            vt320.lastwaslf = 0;
                            break Label_6432;
                        }
                        case '\r': {
                            this.C = 0;
                            break Label_6432;
                        }
                        case '\n': {
                            if (!this.vms) {
                                if (vt320.lastwaslf != 0 && vt320.lastwaslf != c) {
                                    break Label_6432;
                                }
                                vt320.lastwaslf = c;
                            }
                            if (this.R == bm || this.R >= rows - 1) {
                                this.insertLine(this.R, 1);
                                break Label_6432;
                            }
                            ++this.R;
                            break Label_6432;
                        }
                        case '\u0007': {
                            this.beep();
                            break Label_6432;
                        }
                        case '\u000e': {
                            vt320.gl = '\u0001';
                            break Label_6432;
                        }
                        case '\u000f': {
                            vt320.gl = '\0';
                            break Label_6432;
                        }
                        default: {
                            vt320.lastwaslf = 0;
                            if (c >= ' ') {
                                if (this.C >= columns) {
                                    if (this.R < rows - 1) {
                                        ++this.R;
                                    }
                                    else {
                                        this.insertLine(this.R, false);
                                    }
                                    this.C = 0;
                                }
                                if (vt320.gx[vt320.gl] == '0' && c >= '_' && c <= '~') {
                                    c = vt320.DECSPECIAL[(short)c - 95];
                                }
                                if (this.useibmcharset) {
                                    c = this.map_cp850_unicode(c);
                                }
                                if (this.insertmode == 1) {
                                    this.insertChar(this.C, this.R, c, this.attributes);
                                }
                                else {
                                    this.putChar(this.C, this.R, c, this.attributes);
                                }
                                ++this.C;
                                break Label_6432;
                            }
                            if (c != '\0') {
                                break Label_6432;
                            }
                            break Label_6432;
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
                    this.osc += c;
                    break;
                }
                case 1: {
                    this.term_state = 0;
                    switch (c) {
                        case '#': {
                            this.term_state = 5;
                            break Label_6432;
                        }
                        case 'c': {
                            vt320.gx[0] = 'B';
                            vt320.gx[1] = '0';
                            vt320.gx[2] = 'A';
                            vt320.gx[3] = '<';
                            vt320.gl = '\0';
                            vt320.gr = '\u0001';
                            int nw = this.getColumns();
                            if (nw < 132) {
                                nw = 132;
                            }
                            this.Tabs = new byte[nw];
                            for (int i = 0; i < nw; i += 8) {
                                this.Tabs[i] = 1;
                            }
                            break Label_6432;
                        }
                        case '[': {
                            this.DCEvar = 0;
                            this.DCEvars[0] = 0;
                            this.DCEvars[1] = 0;
                            this.DCEvars[2] = 0;
                            this.DCEvars[3] = 0;
                            this.term_state = 2;
                            break Label_6432;
                        }
                        case ']': {
                            this.osc = "";
                            this.term_state = 6;
                            break Label_6432;
                        }
                        case 'P': {
                            this.dcs = "";
                            this.term_state = 3;
                            break Label_6432;
                        }
                        case 'E': {
                            if (this.R == tm - 1 || this.R == bm || this.R == rows - 1) {
                                this.insertLine(this.R, 1, false);
                            }
                            else {
                                ++this.R;
                            }
                            this.C = 0;
                            break Label_6432;
                        }
                        case 'D': {
                            if (this.R == tm - 1 || this.R == bm || this.R == rows - 1) {
                                this.insertLine(this.R, 1, false);
                                break Label_6432;
                            }
                            ++this.R;
                            break Label_6432;
                        }
                        case 'M': {
                            if (this.R >= tm && this.R <= bm) {
                                this.insertLine(this.R, 1, true);
                                break Label_6432;
                            }
                            break Label_6432;
                        }
                        case 'H': {
                            if (this.C >= columns) {
                                this.C = columns - 1;
                            }
                            this.Tabs[this.C] = 1;
                            break Label_6432;
                        }
                        case '=': {
                            break Label_6432;
                        }
                        case '>': {
                            break Label_6432;
                        }
                        case '7': {
                            this.Sc = this.C;
                            this.Sr = this.R;
                            this.Sa = this.attributes;
                            break Label_6432;
                        }
                        case '8': {
                            this.C = this.Sc;
                            this.R = this.Sr;
                            this.attributes = this.Sa;
                            break Label_6432;
                        }
                        case '(': {
                            this.term_state = 7;
                            break Label_6432;
                        }
                        case ')': {
                            this.term_state = 8;
                            break Label_6432;
                        }
                        case '*': {
                            this.term_state = 9;
                            break Label_6432;
                        }
                        case '+': {
                            this.term_state = 10;
                            break Label_6432;
                        }
                        case '~': {
                            vt320.gr = '\u0001';
                            break Label_6432;
                        }
                        case 'n': {
                            vt320.gl = '\u0002';
                            break Label_6432;
                        }
                        case '}': {
                            vt320.gr = '\u0002';
                            break Label_6432;
                        }
                        case 'o': {
                            vt320.gl = '\u0003';
                            break Label_6432;
                        }
                        case '|': {
                            vt320.gr = '\u0003';
                            break Label_6432;
                        }
                        default: {
                            System.out.println("ESC unknown letter: (" + (int)c + ")");
                            break Label_6432;
                        }
                    }
                    break;
                }
                case 7: {
                    if (c != '0' && c != 'A' && c != 'B') {
                        System.out.println("ESC ( " + c + ": G0 char set?  (" + (int)c + ")");
                    }
                    else {
                        vt320.gx[0] = c;
                    }
                    this.term_state = 0;
                    break;
                }
                case 8: {
                    if (c != '0' && c != 'A' && c != 'B') {
                        System.out.println("ESC ) " + c + " (" + (int)c + ") :G1 char set?");
                    }
                    else {
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
                        vt320.gx[3] = c;
                    }
                    this.term_state = 0;
                    break;
                }
                case 5: {
                    switch (c) {
                        case '8': {
                            for (int j = 0; j < columns; ++j) {
                                for (int k = 0; k < rows; ++k) {
                                    this.putChar(j, k, 'E', 0);
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
                    this.dcs += c;
                    break;
                }
                case 4: {
                    this.term_state = 0;
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
                            this.term_state = 4;
                            break Label_6432;
                        }
                        case ';': {
                            ++this.DCEvar;
                            this.DCEvars[this.DCEvar] = 0;
                            this.term_state = 4;
                            break Label_6432;
                        }
                        case 's': {
                            System.out.println("ESC [ ? " + this.DCEvars[0] + " s unimplemented!");
                            break Label_6432;
                        }
                        case 'r': {
                            System.out.println("ESC [ ? " + this.DCEvars[0] + " r");
                            vt320.i = 0;
                            while (vt320.i <= this.DCEvar) {
                                switch (this.DCEvars[vt320.i]) {
                                    case 3: {
                                        final Dimension size = this.getSize();
                                        this.setScreenSize(80, rows);
                                        break;
                                    }
                                    case 4: {
                                        break;
                                    }
                                    case 5: {
                                        break;
                                    }
                                    case 6: {
                                        this.moveoutsidemargins = true;
                                        break;
                                    }
                                    case 12: {
                                        break;
                                    }
                                    case 9:
                                    case 1000:
                                    case 1001:
                                    case 1002:
                                    case 1003: {
                                        this.mouserpt = this.DCEvars[vt320.i];
                                        break;
                                    }
                                    default: {
                                        System.out.println("ESC [ ? " + this.DCEvars[0] + " r, unimplemented!");
                                        break;
                                    }
                                }
                                ++vt320.i;
                            }
                            break Label_6432;
                        }
                        case 'h': {
                            vt320.i = 0;
                            while (vt320.i <= this.DCEvar) {
                                switch (this.DCEvars[vt320.i]) {
                                    case 1: {
                                        this.KeyUp[0] = "\u001bOA";
                                        this.KeyDown[0] = "\u001bOB";
                                        this.KeyRight[0] = "\u001bOC";
                                        this.KeyLeft[0] = "\u001bOD";
                                        break;
                                    }
                                    case 3: {
                                        final Dimension size = this.getSize();
                                        this.setScreenSize(132, rows);
                                        break;
                                    }
                                    case 6: {
                                        this.moveoutsidemargins = false;
                                        break;
                                    }
                                    case 25: {
                                        this.showCursor(true);
                                        this.redraw();
                                        break;
                                    }
                                    case 9:
                                    case 1000:
                                    case 1001:
                                    case 1002:
                                    case 1003: {
                                        this.mouserpt = this.DCEvars[vt320.i];
                                        break;
                                    }
                                    default: {
                                        System.out.println("ESC [ ? " + this.DCEvars[0] + " h, unsupported.");
                                        break;
                                    }
                                }
                                ++vt320.i;
                            }
                            break Label_6432;
                        }
                        case 'i': {
                            switch (this.DCEvars[0]) {
                                case 1: {}
                            }
                            break Label_6432;
                        }
                        case 'l': {
                            vt320.i = 0;
                            while (vt320.i <= this.DCEvar) {
                                switch (this.DCEvars[vt320.i]) {
                                    case 1: {
                                        this.KeyUp[0] = "\u001b[A";
                                        this.KeyDown[0] = "\u001b[B";
                                        this.KeyRight[0] = "\u001b[C";
                                        this.KeyLeft[0] = "\u001b[D";
                                        break;
                                    }
                                    case 3: {
                                        final Dimension size = this.getSize();
                                        this.setScreenSize(80, rows);
                                        break;
                                    }
                                    case 6: {
                                        this.moveoutsidemargins = true;
                                        break;
                                    }
                                    case 25: {
                                        this.showCursor(false);
                                        this.redraw();
                                        break;
                                    }
                                    case 9:
                                    case 1000:
                                    case 1001:
                                    case 1002:
                                    case 1003: {
                                        this.mouserpt = 0;
                                        break;
                                    }
                                    default: {
                                        System.out.println("ESC [ ? " + this.DCEvars[0] + " l, unsupported.");
                                        break;
                                    }
                                }
                                ++vt320.i;
                            }
                            break Label_6432;
                        }
                        case 'n': {
                            switch (this.DCEvars[0]) {
                                case 15: {
                                    this.write("\u001b[?13n", false);
                                    System.out.println("ESC[5n");
                                    break Label_6432;
                                }
                                default: {
                                    System.out.println("ESC [ ? " + this.DCEvars[0] + " n, unsupported.");
                                    break Label_6432;
                                }
                            }
                            break;
                        }
                        default: {
                            System.out.println("ESC [ ? " + this.DCEvars[0] + " " + c + ", unsupported.");
                            break Label_6432;
                        }
                    }
                    break;
                }
                case 12: {
                    this.term_state = 0;
                    switch (c) {
                        case '\u001b': {
                            this.term_state = 1;
                            break Label_6432;
                        }
                        default: {
                            System.out.println("Unknown character ESC[! character is " + (int)c);
                            break Label_6432;
                        }
                    }
                    break;
                }
                case 11: {
                    this.term_state = 0;
                    switch (c) {
                        case '}': {
                            System.out.println("Active Status Display now " + this.DCEvars[0]);
                            this.statusmode = this.DCEvars[0];
                            break Label_6432;
                        }
                        case '~': {
                            System.out.println("Status Line mode now " + this.DCEvars[0]);
                            break Label_6432;
                        }
                        default: {
                            System.out.println("UNKNOWN Status Display code " + c + ", with Pn=" + this.DCEvars[0]);
                            break Label_6432;
                        }
                    }
                    break;
                }
                case 2: {
                    this.term_state = 0;
                    switch (c) {
                        case '$': {
                            this.term_state = 11;
                            break Label_6432;
                        }
                        case '!': {
                            this.term_state = 12;
                            break Label_6432;
                        }
                        case '?': {
                            this.DCEvar = 0;
                            this.DCEvars[0] = 0;
                            this.term_state = 4;
                            break Label_6432;
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
                            this.term_state = 2;
                            break Label_6432;
                        }
                        case ';': {
                            ++this.DCEvar;
                            this.DCEvars[this.DCEvar] = 0;
                            this.term_state = 2;
                            break Label_6432;
                        }
                        case 'c': {
                            this.write("\u001b[?1;2c", false);
                            break Label_6432;
                        }
                        case 'q': {
                            break Label_6432;
                        }
                        case 'g': {
                            switch (this.DCEvars[0]) {
                                case 3: {
                                    final int nw2 = this.getColumns();
                                    this.Tabs = new byte[nw2];
                                    break;
                                }
                                case 0: {
                                    this.Tabs[this.C] = 0;
                                    break;
                                }
                            }
                            break Label_6432;
                        }
                        case 'h': {
                            switch (this.DCEvars[0]) {
                                case 4: {
                                    this.insertmode = 1;
                                    break Label_6432;
                                }
                                case 20: {
                                    System.out.println("Setting CRLF to TRUE");
                                    this.sendcrlf = true;
                                    break Label_6432;
                                }
                                default: {
                                    System.out.println("unsupported: ESC [ " + this.DCEvars[0] + " h");
                                    break Label_6432;
                                }
                            }
                            break;
                        }
                        case 'i': {
                            switch (this.DCEvars[0]) {
                                case 0: {
                                    break Label_6432;
                                }
                                case 4: {
                                    break Label_6432;
                                }
                                case 5: {
                                    break Label_6432;
                                }
                                default: {
                                    System.out.println("ESC [ " + this.DCEvars[0] + " i, unimplemented!");
                                    break Label_6432;
                                }
                            }
                            break;
                        }
                        case 'l': {
                            switch (this.DCEvars[0]) {
                                case 4: {
                                    this.insertmode = 0;
                                    break Label_6432;
                                }
                                case 20: {
                                    System.out.println("Setting CRLF to FALSE");
                                    this.sendcrlf = false;
                                    break Label_6432;
                                }
                                default: {
                                    System.out.println("ESC [ " + this.DCEvars[0] + " l, unimplemented!");
                                    break Label_6432;
                                }
                            }
                            break;
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
                                break Label_6432;
                            }
                            break Label_6432;
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
                                break Label_6432;
                            }
                            break Label_6432;
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
                                break Label_6432;
                            }
                            break Label_6432;
                        }
                        case 'd': {
                            this.R = this.DCEvars[0];
                            System.out.println("ESC [ " + this.DCEvars[0] + " d");
                            break Label_6432;
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
                                break Label_6432;
                            }
                            break Label_6432;
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
                            this.setBottomMargin(this.DCEvars[1] - 1);
                            if (this.R >= this.DCEvars[0]) {
                                this.R = this.DCEvars[0] - 1;
                                if (this.R < 0) {
                                    this.R = 0;
                                }
                            }
                            this.setTopMargin(this.DCEvars[0] - 1);
                            this._SetCursor(0, 0);
                            break Label_6432;
                        }
                        case 'G': {
                            this.C = this.DCEvars[0];
                            break Label_6432;
                        }
                        case 'H': {
                            this._SetCursor(this.DCEvars[0] - 1, this.DCEvars[1] - 1);
                            break Label_6432;
                        }
                        case 'f': {
                            this.R = this.DCEvars[0] - 1;
                            this.C = this.DCEvars[1] - 1;
                            if (this.C < 0) {
                                this.C = 0;
                            }
                            if (this.R < 0) {
                                this.R = 0;
                                break Label_6432;
                            }
                            break Label_6432;
                        }
                        case 'L': {
                            if (this.DCEvars[0] == 0) {
                                this.insertLine(this.R, true);
                                break Label_6432;
                            }
                            this.insertLine(this.R, this.DCEvars[0], true);
                            break Label_6432;
                        }
                        case 'M': {
                            if (this.DCEvars[0] == 0) {
                                this.deleteLine(this.R);
                                break Label_6432;
                            }
                            for (int l = 0; l < this.DCEvars[0]; ++l) {
                                this.deleteLine(this.R);
                            }
                            break Label_6432;
                        }
                        case 'K': {
                            switch (this.DCEvars[0]) {
                                case 0: {
                                    if (this.C < columns - 1) {
                                        this.deleteArea(this.C, this.R, columns - this.C, 1);
                                        break;
                                    }
                                    break;
                                }
                                case 1: {
                                    if (this.C > 0) {
                                        this.deleteArea(0, this.R, this.C, 1);
                                        break;
                                    }
                                    break;
                                }
                                case 2: {
                                    this.deleteArea(0, this.R, columns, 1);
                                    break;
                                }
                            }
                            break Label_6432;
                        }
                        case 'J': {
                            switch (this.DCEvars[0]) {
                                case 0: {
                                    if (this.R < rows - 1) {
                                        this.deleteArea(0, this.R + 1, columns, rows - this.R - 1);
                                    }
                                    if (this.C < columns - 1) {
                                        this.deleteArea(this.C, this.R, columns - this.C, 1);
                                        break;
                                    }
                                    break;
                                }
                                case 1: {
                                    if (this.R > 0) {
                                        this.deleteArea(0, 0, columns, this.R - 1);
                                    }
                                    if (this.C > 0) {
                                        this.deleteArea(0, this.R, this.C, 1);
                                        break;
                                    }
                                    break;
                                }
                                case 2: {
                                    this.deleteArea(0, 0, columns, rows);
                                    break;
                                }
                            }
                            break Label_6432;
                        }
                        case '@': {
                            for (int m = 0; m < this.DCEvars[0]; ++m) {
                                this.insertChar(this.C, this.R, ' ', this.attributes);
                            }
                            break Label_6432;
                        }
                        case 'P': {
                            if (this.DCEvars[0] == 0) {
                                this.DCEvars[0] = 1;
                            }
                            for (int i2 = 0; i2 < this.DCEvars[0]; ++i2) {
                                this.deleteChar(this.C, this.R);
                            }
                            break Label_6432;
                        }
                        case 'n': {
                            switch (this.DCEvars[0]) {
                                case 5: {
                                    this.write("\u001b[0n", false);
                                    break Label_6432;
                                }
                                case 6: {
                                    this.write("\u001b[" + (this.R + 1) + ";" + (this.C + 1) + "R", false);
                                    break Label_6432;
                                }
                                default: {
                                    break Label_6432;
                                }
                            }
                            break;
                        }
                        case 's': {
                            this.Sc = this.C;
                            this.Sr = this.R;
                            this.Sa = this.attributes;
                            break Label_6432;
                        }
                        case 'u': {
                            this.C = this.Sc;
                            this.R = this.Sr;
                            this.attributes = this.Sa;
                            break Label_6432;
                        }
                        case 'm': {
                            if (this.DCEvar == 0 && this.DCEvars[0] == 0) {
                                this.attributes = 0;
                            }
                            vt320.i = 0;
                            while (vt320.i <= this.DCEvar) {
                                switch (this.DCEvars[vt320.i]) {
                                    case 0: {
                                        if (this.DCEvar > 0) {
                                            this.attributes = 0;
                                            break;
                                        }
                                        break;
                                    }
                                    case 4: {
                                        this.attributes |= 0x2;
                                        break;
                                    }
                                    case 1: {
                                        this.attributes |= 0x1;
                                        break;
                                    }
                                    case 7: {
                                        this.attributes |= 0x4;
                                        break;
                                    }
                                    case 5: {
                                        break;
                                    }
                                    case 25: {
                                        break;
                                    }
                                    case 27: {
                                        this.attributes &= 0xFFFFFFFB;
                                        break;
                                    }
                                    case 24: {
                                        this.attributes &= 0xFFFFFFFD;
                                        break;
                                    }
                                    case 22: {
                                        this.attributes &= 0xFFFFFFFE;
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
                                        this.attributes &= 0xFFFFFF87;
                                        this.attributes |= this.DCEvars[vt320.i] - 30 + 1 << 3;
                                        break;
                                    }
                                    case 39: {
                                        this.attributes &= 0xFFFFFF87;
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
                                        this.attributes &= 0xFFFFF87F;
                                        this.attributes |= this.DCEvars[vt320.i] - 40 + 1 << 7;
                                        break;
                                    }
                                    case 49: {
                                        this.attributes &= 0xFFFFF87F;
                                        break;
                                    }
                                    default: {
                                        System.out.println("ESC [ " + this.DCEvars[vt320.i] + " m unknown...");
                                        break;
                                    }
                                }
                                ++vt320.i;
                            }
                            break Label_6432;
                        }
                        default: {
                            System.out.println("ESC [ unknown letter:" + c + " (" + (int)c + ")");
                            break Label_6432;
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
            this.setCursorPosition(this.C, this.R);
        }
        this.markLine(this.R, 1);
    }
    
    public void reset() {
        vt320.gx[0] = 'B';
        vt320.gx[1] = '0';
        vt320.gx[2] = 'A';
        vt320.gx[3] = '<';
        vt320.gl = '\0';
        vt320.gr = '\u0001';
        int nw = this.getColumns();
        if (nw < 132) {
            nw = 132;
        }
        this.Tabs = new byte[nw];
        for (int i = 0; i < nw; i += 8) {
            this.Tabs[i] = 1;
        }
        this.term_state = 0;
    }
    
    static {
        vt320.lastwaslf = 0;
        vt320.gx = new char[] { 'B', '0', 'A', '<' };
        vt320.gr = '\u0001';
        vt320.gl = '\0';
        vt320.DECSPECIAL = new char[] { '@', '\u2666', '\u2592', '\u2409', '\u240c', '\u240d', '\u240a', 'º', '±', '\u2424', '\u240b', '\u2518', '\u2510', '\u250c', '\u2514', '\u253c', '\u2594', '\u2580', '\u2500', '\u25ac', '_', '\u251c', '\u2524', '\u2534', '\u252c', '\u2502', '\u2264', '\u2265', '¶', '\u2260', '£', '·' };
        unimap = new char[] { '\0', '\u0001', '\u0002', '\u0003', '\u0004', '\u0005', '\u0006', '\u0007', '\b', '\t', '\n', '\u000b', '\f', '\r', '\u000e', '\u000f', '\u0010', '\u0011', '\u0012', '\u0013', '\u0014', '\u0015', '\u0016', '\u0017', '\u0018', '\u0019', '\u001a', '\u001b', '\u001c', '\u001d', '\u001e', '\u001f', ' ', '!', '\"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', '<', '=', '>', '?', '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '[', '\\', ']', '^', '_', '`', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '{', '|', '}', '~', '\u007f', '\u00c7', '\u00fc', '\u00e9', '\u00e2', '\u00e4', '\u00e0', '\u00e5', '\u00e7', '\u00ea', '\u00eb', '\u00e8', '\u00ef', '\u00ee', '\u00ec', '\u00c4', '\u00c5', '\u00c9', '\u00e6', '\u00c6', '\u00f4', '\u00f6', '\u00f2', '\u00fb', '\u00f9', '\u00ff', '\u00d6', '\u00dc', '¢', '£', '¥', '\u20a7', '\u0192', '\u00e1', '\u00ed', '\u00f3', '\u00fa', '\u00f1', '\u00d1', 'ª', 'º', '¿', '\u2310', '¬', '½', '¼', '¡', '«', '»', '\u2591', '\u2592', '\u2593', '\u2502', '\u2524', '\u2561', '\u2562', '\u2556', '\u2555', '\u2563', '\u2551', '\u2557', '\u255d', '\u255c', '\u255b', '\u2510', '\u2514', '\u2534', '\u252c', '\u251c', '\u2500', '\u253c', '\u255e', '\u255f', '\u255a', '\u2554', '\u2569', '\u2566', '\u2560', '\u2550', '\u256c', '\u2567', '\u2568', '\u2564', '\u2565', '\u2559', '\u2558', '\u2552', '\u2553', '\u256b', '\u256a', '\u2518', '\u250c', '\u2588', '\u2584', '\u258c', '\u2590', '\u2580', '\u03b1', '\u00df', '\u0393', '\u03c0', '\u03a3', '\u03c3', 'µ', '\u03c4', '\u03a6', '\u0398', '\u03a9', '\u03b4', '\u221e', '\u03c6', '\u03b5', '\u2229', '\u2261', '±', '\u2265', '\u2264', '\u2320', '\u2321', '\u00f7', '\u2248', '°', '\u2219', '·', '\u221a', '\u207f', '²', '\u25a0', ' ' };
    }
}
