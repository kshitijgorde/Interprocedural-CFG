// 
// Decompiled by Procyon v0.5.30
// 

package MudFE;

import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Color;
import java.applet.Applet;
import java.util.Hashtable;

public class Decoder
{
    ScrolledDisplay def_display;
    private static final boolean debug = false;
    private static final boolean disp_debug = false;
    private static final boolean fes_debug = false;
    private static final boolean ascii_debug = false;
    private static final boolean sdebug = false;
    private final boolean splitwhos = false;
    private final int NORMAL = 0;
    private final int ESC_MODE = 1;
    private final int ESC_MODE2 = 2;
    private final int CODE_MODE = 3;
    private final int IDENTIFY_DATA = 4;
    private final int SNOOP_NAME = 5;
    private final int DUMMY = 6;
    private final int DREAMWORD = 7;
    private final int ESC_MODE3 = 8;
    private int MODE;
    private String id;
    private StringBuffer snoop_name;
    private StringBuffer dream_word;
    private BitBucket bitbucket;
    int win_to_create;
    int win_to_close;
    private MudFrame theframe;
    Hashtable snoops;
    private int[] new_code;
    private int code_count;
    private int prompt;
    boolean prompt_received;
    StringBuffer parstring;
    final int MaxPCount = 6;
    int[] ascii_parms;
    int pcount;
    StamCatcher stCat;
    StamMaxCatcher stmaxCat;
    SnoopHolder snoopholder;
    private MudStack color_stack;
    Applet applet;
    
    private void do_ansii_colour() {
        final Attribute na = new Attribute(this.color_stack.getColour());
        for (int i = 0; i < this.pcount; ++i) {
            switch (this.ascii_parms[i]) {
                case 1: {
                    na.setFg(Color.white);
                    na.setBg(MudFrame.febackground);
                }
                case 30: {
                    na.setFg(MudFrame.febackground);
                    break;
                }
                case 31: {
                    na.setFg(Color.red);
                    break;
                }
                case 32: {
                    na.setFg(Color.green);
                    break;
                }
                case 33: {
                    na.setFg(Color.yellow);
                    break;
                }
                case 34: {
                    na.setFg(Color.blue);
                    break;
                }
                case 35: {
                    na.setFg(Color.magenta);
                    break;
                }
                case 36: {
                    na.setFg(Color.cyan);
                    break;
                }
                case 37: {
                    na.setFg(Color.white);
                    break;
                }
                case 40: {
                    na.setBg(MudFrame.febackground);
                    break;
                }
                case 41: {
                    na.setBg(Color.red);
                    break;
                }
                case 42: {
                    na.setBg(Color.green);
                    break;
                }
                case 43: {
                    na.setBg(Color.yellow);
                    break;
                }
                case 44: {
                    na.setBg(Color.blue);
                    break;
                }
                case 45: {
                    na.setBg(Color.magenta);
                    break;
                }
                case 46: {
                    na.setBg(Color.cyan);
                    break;
                }
                case 47: {
                    na.setBg(Color.white);
                    break;
                }
            }
        }
        if (!this.color_stack.empty()) {
            this.color_stack.pop();
        }
        this.color_stack.mudPush(na);
    }
    
    public void hidefes() {
        try {
            if (this.theframe.hp != null) {
                this.theframe.hp.hide();
                this.theframe.validate();
                if (this.theframe.hp.exec != null && this.theframe.hp.exec.isAlive()) {
                    this.theframe.hp.exec.stop();
                    this.theframe.hp.exec = null;
                }
            }
        }
        catch (Exception e) {
            System.err.println("Exception in hidefes");
            e.printStackTrace();
        }
    }
    
    public Decoder(final ScrolledDisplay df, final MudFrame f) {
        this.MODE = 0;
        this.id = "";
        this.snoop_name = new StringBuffer(10);
        this.dream_word = new StringBuffer(15);
        this.bitbucket = new BitBucket();
        this.new_code = new int[4];
        this.code_count = 0;
        this.prompt = 0;
        this.prompt_received = false;
        this.parstring = new StringBuffer();
        this.ascii_parms = new int[6];
        this.pcount = 0;
        this.snoopholder = new SnoopHolder();
        this.def_display = df;
        this.color_stack = new MudStack(df);
        this.snoops = new Hashtable();
        this.theframe = f;
        this.stCat = new StamCatcher(df, this.theframe);
        this.stmaxCat = new StamMaxCatcher(df, this.theframe);
    }
    
    private void docode(final int b) {
        if (this.MODE != 3) {
            this.MODE = 3;
            this.code_count = 0;
        }
        if (this.code_count > 3) {
            System.err.println("Error, received more than 4 codes in a row");
            this.MODE = 0;
            this.code_count = 0;
            return;
        }
        this.new_code[this.code_count++] = b;
    }
    
    private void dopop() {
        this.MODE = 0;
        this.color_stack.pop();
    }
    
    public void addBytes(final byte[] bu) {
        for (int i = 0; i < bu.length; ++i) {
            this.addChar(bu[i]);
        }
        if (this.def_display == MudStack.curr_display) {
            this.def_display.checkrepaint();
        }
    }
    
    public void play(final String st) {
        if (!this.theframe.playsounds) {
            return;
        }
        if (this.theframe.mainapplet == null) {
            System.out.println("Not in an applet, so no sound.");
            return;
        }
        URL ur = null;
        try {
            ur = new URL(this.theframe.codebase + "sounds/");
        }
        catch (MalformedURLException e) {
            System.err.println("Sound files not found");
            return;
        }
        if (ur != null) {
            this.theframe.mainapplet.play(ur, st);
        }
    }
    
    private int donecode() {
        Label_5245: {
            if (this.code_count > 0) {
                if (this.prompt > 0 && MudStack.curr_display == this.def_display) {
                    if (this.code_count < 2 || (this.new_code[0] != 97 && (this.new_code[0] != 12 || this.new_code[1] != 8))) {
                        if (this.code_count == 1 && this.new_code[0] == 1) {
                            this.color_stack.mudPush(this.bitbucket);
                            return -1;
                        }
                        if (this.code_count == 2 && this.new_code[0] == 1 && this.new_code[1] == 4) {
                            this.color_stack.mudPush(this.bitbucket);
                            return -1;
                        }
                        if (this.code_count > 1 && this.new_code[0] == 1 && this.new_code[1] == this.prompt) {
                            this.color_stack.mudPush(this.bitbucket);
                            return -1;
                        }
                        this.prompt = 0;
                    }
                }
                switch (this.new_code[0]) {
                    case 0: {
                        this.color_stack = new MudStack(this.def_display);
                        this.hidefes();
                        this.prompt_received = false;
                        if (this.theframe.co != null) {
                            this.theframe.co.hide();
                        }
                        this.theframe.prompt_received = false;
                        this.theframe.inputHandler.merge();
                        this.theframe.repaint();
                        this.theframe.mainapplet.showStatus("   ");
                        return 0;
                    }
                    case 1: {
                        if (this.code_count == 1) {
                            return 1;
                        }
                        switch (this.new_code[1]) {
                            case 0: {
                                return 1;
                            }
                            case 1: {
                                this.prompt = 1;
                                if (!this.prompt_received) {
                                    this.prompt_received = true;
                                    this.theframe.prompted();
                                }
                                return 2;
                            }
                            case 2: {
                                if (!this.prompt_received) {
                                    this.prompt_received = true;
                                    this.theframe.prompted();
                                }
                                this.prompt = 2;
                                return 3;
                            }
                            case 3: {
                                if (!this.prompt_received) {
                                    this.prompt_received = true;
                                    this.theframe.prompted();
                                }
                                this.prompt = 3;
                                return 4;
                            }
                            case 4: {
                                if (!this.prompt_received) {
                                    this.prompt_received = true;
                                    this.theframe.prompted();
                                }
                                this.prompt = 4;
                                return 5;
                            }
                            case 5: {
                                if (!this.prompt_received) {
                                    this.prompt_received = true;
                                    this.theframe.prompted();
                                }
                                this.prompt = 5;
                                return 4;
                            }
                            default: {
                                break Label_5245;
                            }
                        }
                        break;
                    }
                    case 2: {
                        if (this.code_count == 1) {
                            return 6;
                        }
                        switch (this.new_code[1]) {
                            case 0: {
                                return 6;
                            }
                            case 1: {
                                return 7;
                            }
                            case 2: {
                                return 8;
                            }
                            default: {
                                break Label_5245;
                            }
                        }
                        break;
                    }
                    case 3: {
                        if (this.code_count == 1) {
                            return 9;
                        }
                        Label_1039: {
                            switch (this.new_code[1]) {
                                case 0: {
                                    if (this.code_count == 2) {
                                        return 13;
                                    }
                                    switch (this.new_code[2]) {
                                        case 0: {
                                            return 13;
                                        }
                                        case 1: {
                                            return 10;
                                        }
                                        case 2: {
                                            return 11;
                                        }
                                        case 3: {
                                            return 12;
                                        }
                                        default: {
                                            break Label_1039;
                                        }
                                    }
                                    break;
                                }
                                case 1: {
                                    if (this.code_count == 2) {
                                        return 13;
                                    }
                                    switch (this.new_code[2]) {
                                        case 0: {
                                            return 13;
                                        }
                                        case 1: {
                                            return 14;
                                        }
                                        case 2: {
                                            return 15;
                                        }
                                        case 3: {
                                            return 16;
                                        }
                                        default: {
                                            break Label_1039;
                                        }
                                    }
                                    break;
                                }
                                case 2: {
                                    if (this.code_count == 2) {
                                        return 17;
                                    }
                                    switch (this.new_code[2]) {
                                        case 0: {
                                            return 17;
                                        }
                                        case 1: {
                                            return 18;
                                        }
                                        case 2: {
                                            return 19;
                                        }
                                        case 3: {
                                            return 20;
                                        }
                                        default: {
                                            break Label_1039;
                                        }
                                    }
                                    break;
                                }
                                case 3: {
                                    if (this.code_count == 2) {
                                        return 21;
                                    }
                                    switch (this.new_code[2]) {
                                        case 0: {
                                            return 21;
                                        }
                                        case 1: {
                                            return 22;
                                        }
                                        case 2: {
                                            return 23;
                                        }
                                        case 3: {
                                            return 24;
                                        }
                                        default: {
                                            break Label_1039;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    case 4: {
                        if (this.code_count == 1) {
                            return 25;
                        }
                        Label_1274: {
                            switch (this.new_code[1]) {
                                case 0: {
                                    if (this.code_count == 2) {
                                        return 25;
                                    }
                                    switch (this.new_code[2]) {
                                        case 0: {
                                            return 25;
                                        }
                                        case 1: {
                                            return 26;
                                        }
                                        case 2: {
                                            return 27;
                                        }
                                        case 3: {
                                            return 28;
                                        }
                                        case 4: {
                                            return 29;
                                        }
                                        case 5: {
                                            return 30;
                                        }
                                        case 6: {
                                            return 31;
                                        }
                                        case 7: {
                                            return 32;
                                        }
                                        case 8: {
                                            return 33;
                                        }
                                        default: {
                                            break Label_1274;
                                        }
                                    }
                                    break;
                                }
                                case 1: {
                                    if (this.code_count == 2) {
                                        return 34;
                                    }
                                    switch (this.new_code[2]) {
                                        case 0: {
                                            return 34;
                                        }
                                        case 1: {
                                            return 35;
                                        }
                                        case 2: {
                                            return 36;
                                        }
                                        case 3: {
                                            return 37;
                                        }
                                        case 4: {
                                            return 38;
                                        }
                                        case 5: {
                                            return 39;
                                        }
                                        case 6: {
                                            return 40;
                                        }
                                        case 7: {
                                            return 41;
                                        }
                                        case 8: {
                                            return 42;
                                        }
                                        default: {
                                            break Label_1274;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    case 5: {
                        if (this.code_count == 1) {
                            return 43;
                        }
                        Label_1541: {
                            switch (this.new_code[1]) {
                                case 0: {
                                    if (this.code_count == 2) {
                                        return 43;
                                    }
                                    switch (this.new_code[2]) {
                                        case 0: {
                                            return 43;
                                        }
                                        case 1: {
                                            return 44;
                                        }
                                        case 2: {
                                            return 45;
                                        }
                                        case 3: {
                                            return 46;
                                        }
                                        case 4: {
                                            return 47;
                                        }
                                        case 5: {
                                            return 48;
                                        }
                                        case 6: {
                                            return 49;
                                        }
                                        case 7: {
                                            return 50;
                                        }
                                        case 8: {
                                            return 51;
                                        }
                                        case 9: {
                                            return 52;
                                        }
                                        case 10: {
                                            return 207;
                                        }
                                        default: {
                                            break Label_1541;
                                        }
                                    }
                                    break;
                                }
                                case 1: {
                                    if (this.code_count == 2) {
                                        return 53;
                                    }
                                    switch (this.new_code[2]) {
                                        case 0: {
                                            return 53;
                                        }
                                        case 1: {
                                            return 54;
                                        }
                                        case 2: {
                                            return 55;
                                        }
                                        case 3: {
                                            return 56;
                                        }
                                        case 4: {
                                            return 57;
                                        }
                                        case 5: {
                                            return 58;
                                        }
                                        case 6: {
                                            return 59;
                                        }
                                        case 7: {
                                            return 60;
                                        }
                                        case 8: {
                                            return 61;
                                        }
                                        case 9: {
                                            return 62;
                                        }
                                        case 10: {
                                            return 208;
                                        }
                                        default: {
                                            break Label_1541;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    case 6: {
                        if (this.code_count == 1) {
                            return 63;
                        }
                        switch (this.new_code[1]) {
                            case 0: {
                                return 64;
                            }
                            case 1: {
                                return 65;
                            }
                            case 2: {
                                return 66;
                            }
                            case 3: {
                                return 67;
                            }
                            case 4: {
                                return 68;
                            }
                            case 5: {
                                return 69;
                            }
                            case 6: {
                                return 70;
                            }
                            case 7: {
                                return 71;
                            }
                            case 8: {
                                return 72;
                            }
                            case 9: {
                                return 73;
                            }
                            case 10: {
                                return 74;
                            }
                            case 11: {
                                if (this.code_count == 2) {
                                    return 75;
                                }
                                switch (this.new_code[2]) {
                                    case 0: {
                                        return 75;
                                    }
                                    default: {
                                        return 76;
                                    }
                                }
                                break;
                            }
                            case 12: {
                                if (this.code_count == 2) {
                                    return 77;
                                }
                                switch (this.new_code[2]) {
                                    case 0: {
                                        return 77;
                                    }
                                    default: {
                                        return 78;
                                    }
                                }
                                break;
                            }
                            default: {
                                break Label_5245;
                            }
                        }
                        break;
                    }
                    case 7: {
                        if (this.code_count == 1) {
                            return 79;
                        }
                        switch (this.new_code[1]) {
                            case 0: {
                                if (this.code_count == 2) {
                                    return 80;
                                }
                                switch (this.new_code[2]) {
                                    case 1: {
                                        return 80;
                                    }
                                    default: {
                                        return 81;
                                    }
                                }
                                break;
                            }
                            case 1: {
                                if (this.code_count == 2) {
                                    return 82;
                                }
                                switch (this.new_code[2]) {
                                    case 1: {
                                        return 83;
                                    }
                                    default: {
                                        return 82;
                                    }
                                }
                                break;
                            }
                            case 2: {
                                if (this.code_count == 2) {
                                    return 84;
                                }
                                switch (this.new_code[2]) {
                                    case 1: {
                                        return 85;
                                    }
                                    case 2: {
                                        return 86;
                                    }
                                    default: {
                                        return 84;
                                    }
                                }
                                break;
                            }
                            case 3: {
                                return 87;
                            }
                            case 4: {
                                return 88;
                            }
                            default: {
                                break Label_5245;
                            }
                        }
                        break;
                    }
                    case 8: {
                        if (this.code_count == 1) {
                            return 89;
                        }
                        switch (this.new_code[1]) {
                            case 0: {
                                return 89;
                            }
                            case 1: {
                                return 90;
                            }
                            case 2: {
                                return 91;
                            }
                            case 3: {
                                return 92;
                            }
                            case 4: {
                                return 93;
                            }
                            case 5: {
                                return 94;
                            }
                            case 6: {
                                return 95;
                            }
                            case 7: {
                                return 96;
                            }
                            case 8: {
                                return 97;
                            }
                            case 9: {
                                return 98;
                            }
                            case 10: {
                                return 99;
                            }
                            case 11: {
                                return 100;
                            }
                            case 12: {
                                return 101;
                            }
                            case 13: {
                                return 102;
                            }
                            default: {
                                break Label_5245;
                            }
                        }
                        break;
                    }
                    case 9: {
                        if (this.code_count == 1) {
                            return 103;
                        }
                        Label_2236: {
                            switch (this.new_code[1]) {
                                case 0: {
                                    return 103;
                                }
                                case 1: {
                                    return 104;
                                }
                                case 2: {
                                    return 105;
                                }
                                case 3: {
                                    return 106;
                                }
                                case 4: {
                                    return 107;
                                }
                                case 5: {
                                    if (this.code_count == 2 || (this.code_count == 3 && this.new_code[2] == 0)) {
                                        return 108;
                                    }
                                    switch (this.new_code[2]) {
                                        case 1: {
                                            return 109;
                                        }
                                        case 2: {
                                            return 110;
                                        }
                                        default: {
                                            break Label_2236;
                                        }
                                    }
                                    break;
                                }
                                case 6: {
                                    return 111;
                                }
                                case 7: {
                                    return 112;
                                }
                                case 8: {
                                    return 113;
                                }
                                case 9: {
                                    return 114;
                                }
                                case 10: {
                                    return 115;
                                }
                            }
                        }
                        break;
                    }
                    case 10: {
                        this.color_stack.mudPush(new shieldstopper());
                        if (this.code_count == 1) {
                            return 116;
                        }
                        switch (this.new_code[1]) {
                            case 0: {
                                return 116;
                            }
                            case 1: {
                                return 117;
                            }
                            case 2: {
                                return 118;
                            }
                            case 3: {
                                return 119;
                            }
                            case 4: {
                                return 120;
                            }
                            default: {
                                break Label_5245;
                            }
                        }
                        break;
                    }
                    case 11: {
                        if (this.code_count == 1) {
                            return 121;
                        }
                        switch (this.new_code[1]) {
                            case 0: {
                                return 121;
                            }
                            case 1: {
                                return 122;
                            }
                            case 2: {
                                return 123;
                            }
                            case 3: {
                                return 124;
                            }
                            default: {
                                return 125;
                            }
                        }
                        break;
                    }
                    case 12: {
                        if (this.code_count == 1) {
                            return 142;
                        }
                        Label_2756: {
                            switch (this.new_code[1]) {
                                case 0: {
                                    return 142;
                                }
                                case 1: {
                                    return 143;
                                }
                                case 2: {
                                    if (this.code_count == 2) {
                                        return 144;
                                    }
                                    switch (this.new_code[2]) {
                                        case 1: {
                                            return 145;
                                        }
                                        case 2: {
                                            return 146;
                                        }
                                        default: {
                                            break Label_2756;
                                        }
                                    }
                                    break;
                                }
                                case 3: {
                                    this.color_stack.mudPush(new shieldstopper());
                                    if (this.code_count == 2) {
                                        return 147;
                                    }
                                    switch (this.new_code[2]) {
                                        case 1: {
                                            return 148;
                                        }
                                        case 2: {
                                            return 149;
                                        }
                                        default: {
                                            return 150;
                                        }
                                    }
                                    break;
                                }
                                case 4: {
                                    return 150;
                                }
                                case 5:
                                case 6: {
                                    return 151;
                                }
                                case 7: {
                                    return 152;
                                }
                                case 8: {
                                    if (this.code_count == 2) {
                                        if (this.theframe.hp != null && this.color_stack.insnoop == 0) {
                                            this.color_stack.mudPush(this.theframe.hp);
                                            return -1;
                                        }
                                        return 153;
                                    }
                                    else {
                                        switch (this.new_code[2]) {
                                            case 1: {
                                                if (this.color_stack.insnoop != 0) {
                                                    this.color_stack.mudPush(this.bitbucket);
                                                    return -1;
                                                }
                                                if (this.theframe.hp != null) {
                                                    this.color_stack.mudPush(this.theframe.hp);
                                                    return -1;
                                                }
                                                return 153;
                                            }
                                            case 2: {
                                                if (this.color_stack.insnoop == 0) {
                                                    return 154;
                                                }
                                                this.color_stack.mudPush(this.bitbucket);
                                                return -1;
                                            }
                                            case 3: {
                                                return 155;
                                            }
                                            default: {
                                                break Label_2756;
                                            }
                                        }
                                    }
                                    break;
                                }
                                case 9: {
                                    if (this.color_stack.insnoop != 0) {
                                        this.color_stack.mudPush(this.bitbucket);
                                        return -1;
                                    }
                                    return 154;
                                }
                                case 10: {
                                    if (this.color_stack.insnoop != 0) {
                                        this.color_stack.mudPush(this.bitbucket);
                                        return -1;
                                    }
                                    return 154;
                                }
                                default: {
                                    break Label_5245;
                                }
                            }
                        }
                        break;
                    }
                    case 13: {
                        if (this.code_count == 1) {
                            this.play("moo.au");
                            return 156;
                        }
                        Label_4174: {
                            switch (this.new_code[1]) {
                                case 0: {
                                    this.play("moo.au");
                                    return 156;
                                }
                                case 1: {
                                    if (this.code_count == 2) {
                                        this.play("moo.au");
                                        return 156;
                                    }
                                    switch (this.new_code[2]) {
                                        case 1: {
                                            this.play("moo.au");
                                            return 156;
                                        }
                                        case 2: {
                                            this.play("moo.au");
                                            return 157;
                                        }
                                        default: {
                                            break Label_4174;
                                        }
                                    }
                                    break;
                                }
                                case 2: {
                                    if (this.code_count == 2) {
                                        this.play("swamp.au");
                                        return 158;
                                    }
                                    switch (this.new_code[2]) {
                                        case 1: {
                                            this.play("swamp.au");
                                            return 158;
                                        }
                                        case 2: {
                                            this.play("swamp.au");
                                            return 159;
                                        }
                                        default: {
                                            break Label_4174;
                                        }
                                    }
                                    break;
                                }
                                case 3: {
                                    if (this.code_count == 2) {
                                        return 160;
                                    }
                                    switch (this.new_code[2]) {
                                        case 1: {
                                            this.play("growl.au");
                                            return 160;
                                        }
                                        case 2: {
                                            this.play("growl.au");
                                            return 161;
                                        }
                                        default: {
                                            break Label_4174;
                                        }
                                    }
                                    break;
                                }
                                case 4: {
                                    if (this.code_count == 2) {
                                        return 162;
                                    }
                                    switch (this.new_code[2]) {
                                        case 1: {
                                            this.play("thunder.au");
                                            return 162;
                                        }
                                        case 2: {
                                            this.play("thunder.au");
                                            return 163;
                                        }
                                        default: {
                                            break Label_4174;
                                        }
                                    }
                                    break;
                                }
                                case 5: {
                                    if (this.code_count == 2) {
                                        return 164;
                                    }
                                    switch (this.new_code[2]) {
                                        case 1: {
                                            this.play("tcrack.au");
                                            return 164;
                                        }
                                        case 2: {
                                            this.play("tcrack.au");
                                            return 165;
                                        }
                                        default: {
                                            break Label_4174;
                                        }
                                    }
                                    break;
                                }
                                case 6: {
                                    if (this.code_count == 2) {
                                        return 166;
                                    }
                                    switch (this.new_code[2]) {
                                        case 1: {
                                            this.play("scream.au");
                                            return 166;
                                        }
                                        case 2: {
                                            this.play("scream.au");
                                            return 167;
                                        }
                                        default: {
                                            break Label_4174;
                                        }
                                    }
                                    break;
                                }
                                case 7: {
                                    if (this.code_count == 2) {
                                        return 168;
                                    }
                                    switch (this.new_code[2]) {
                                        case 1: {
                                            this.play("boom.au");
                                            return 168;
                                        }
                                        case 2: {
                                            this.play("boom.au");
                                            return 169;
                                        }
                                        default: {
                                            break Label_4174;
                                        }
                                    }
                                    break;
                                }
                                case 8: {
                                    if (this.code_count == 2) {
                                        return 170;
                                    }
                                    switch (this.new_code[2]) {
                                        case 1: {
                                            this.play("bell.au");
                                            return 170;
                                        }
                                        case 2: {
                                            this.play("bell.au");
                                            return 171;
                                        }
                                        default: {
                                            break Label_4174;
                                        }
                                    }
                                    break;
                                }
                                case 9: {
                                    if (this.code_count == 2) {
                                        return 172;
                                    }
                                    switch (this.new_code[2]) {
                                        case 1: {
                                            this.play("bang.au");
                                            return 172;
                                        }
                                        case 2: {
                                            this.play("bang.au");
                                            return 173;
                                        }
                                        default: {
                                            break Label_4174;
                                        }
                                    }
                                    break;
                                }
                                case 10: {
                                    if (this.code_count == 2) {
                                        return 174;
                                    }
                                    switch (this.new_code[2]) {
                                        case 1: {
                                            this.play("wolf.au");
                                            return 174;
                                        }
                                        case 2: {
                                            this.play("wolf.au");
                                            return 175;
                                        }
                                        default: {
                                            break Label_4174;
                                        }
                                    }
                                    break;
                                }
                                case 11: {
                                    if (this.code_count == 2) {
                                        return 176;
                                    }
                                    switch (this.new_code[2]) {
                                        case 1: {
                                            this.play("roar1.au");
                                            return 176;
                                        }
                                        case 2: {
                                            this.play("roar1.au");
                                            return 177;
                                        }
                                        default: {
                                            break Label_4174;
                                        }
                                    }
                                    break;
                                }
                                case 12: {
                                    if (this.code_count == 2) {
                                        return 178;
                                    }
                                    switch (this.new_code[2]) {
                                        case 1: {
                                            this.play("bell.au");
                                            return 178;
                                        }
                                        case 2: {
                                            this.play("bell.au");
                                            return 179;
                                        }
                                        default: {
                                            break Label_4174;
                                        }
                                    }
                                    break;
                                }
                                case 13: {
                                    if (this.code_count == 2) {
                                        return 180;
                                    }
                                    switch (this.new_code[2]) {
                                        case 1: {
                                            this.play("cannon.au");
                                            return 180;
                                        }
                                        case 2: {
                                            this.play("cannon.au");
                                            return 181;
                                        }
                                        default: {
                                            break Label_4174;
                                        }
                                    }
                                    break;
                                }
                                case 14: {
                                    if (this.code_count == 2) {
                                        return 182;
                                    }
                                    switch (this.new_code[2]) {
                                        case 1: {
                                            this.play("flute.au");
                                            return 182;
                                        }
                                        case 2: {
                                            this.play("flute.au");
                                            return 183;
                                        }
                                        default: {
                                            break Label_4174;
                                        }
                                    }
                                    break;
                                }
                                case 15: {
                                    if (this.code_count == 2) {
                                        return 184;
                                    }
                                    switch (this.new_code[2]) {
                                        case 1: {
                                            this.play("horn.au");
                                            return 184;
                                        }
                                        case 2: {
                                            this.play("horn.au");
                                            return 185;
                                        }
                                        default: {
                                            break Label_4174;
                                        }
                                    }
                                    break;
                                }
                                case 16: {
                                    if (this.code_count == 2) {
                                        return 186;
                                    }
                                    switch (this.new_code[2]) {
                                        case 1: {
                                            this.play("badhorn.au");
                                            return 186;
                                        }
                                        case 2: {
                                            this.play("badhorn.au");
                                            return 187;
                                        }
                                        default: {
                                            break Label_4174;
                                        }
                                    }
                                    break;
                                }
                                case 17: {
                                    if (this.code_count == 2) {
                                        return 188;
                                    }
                                    switch (this.new_code[2]) {
                                        case 1: {
                                            this.play("conch.au");
                                            return 188;
                                        }
                                        case 2: {
                                            this.play("conch.au");
                                            return 189;
                                        }
                                        default: {
                                            break Label_4174;
                                        }
                                    }
                                    break;
                                }
                                case 18: {
                                    if (this.code_count == 2) {
                                        return 190;
                                    }
                                    switch (this.new_code[2]) {
                                        case 1: {
                                            this.play("fod.au");
                                            return 190;
                                        }
                                        case 2: {
                                            this.play("fod.au");
                                            return 191;
                                        }
                                        default: {
                                            break Label_4174;
                                        }
                                    }
                                    break;
                                }
                                case 19: {
                                    if (this.code_count == 2) {
                                        return 192;
                                    }
                                    switch (this.new_code[2]) {
                                        case 1: {
                                            this.play("ffod.au");
                                            return 192;
                                        }
                                        case 2: {
                                            this.play("ffod.au");
                                            return 193;
                                        }
                                        default: {
                                            break Label_4174;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    case 14: {
                        if (this.code_count == 1) {
                            return 194;
                        }
                        Label_4428: {
                            switch (this.new_code[1]) {
                                case 0: {
                                    return 194;
                                }
                                case 1: {
                                    return 195;
                                }
                                case 2: {
                                    return 196;
                                }
                                case 3: {
                                    if (this.code_count == 2) {
                                        return 197;
                                    }
                                    switch (this.new_code[2]) {
                                        case 0: {
                                            return 197;
                                        }
                                        case 1: {
                                            return 198;
                                        }
                                        case 2: {
                                            return 197;
                                        }
                                        case 3: {
                                            return 198;
                                        }
                                        default: {
                                            break Label_4428;
                                        }
                                    }
                                    break;
                                }
                                case 4: {
                                    if (this.code_count == 2) {
                                        return 199;
                                    }
                                    switch (this.new_code[2]) {
                                        case 0: {
                                            return 199;
                                        }
                                        case 1: {
                                            return 200;
                                        }
                                        case 2: {
                                            return 199;
                                        }
                                        case 3: {
                                            return 200;
                                        }
                                        default: {
                                            break Label_4428;
                                        }
                                    }
                                    break;
                                }
                                case 5: {
                                    if (this.code_count == 2) {
                                        return 201;
                                    }
                                    switch (this.new_code[2]) {
                                        case 0: {
                                            return 201;
                                        }
                                        case 1: {
                                            return 202;
                                        }
                                        default: {
                                            break Label_4428;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    case 15: {
                        if (this.code_count < 3 || this.new_code[2] == 0) {
                            this.MODE = 7;
                            this.dream_word.setLength(0);
                            return 203;
                        }
                        return 204;
                    }
                    case 16: {
                        return 209;
                    }
                    case 89: {
                        if (!(MudStack.curr_display instanceof HealthPanel)) {
                            if ((this.code_count == 1 || (this.code_count == 2 && this.new_code[1] == 0) || (this.code_count == 3 && this.new_code[1] == 0 && this.new_code[2] == 0)) && this.color_stack.insnoop == 0) {
                                this.stCat.reset();
                                this.color_stack.mudPush(this.stCat);
                                return -1;
                            }
                            if (this.code_count == 3 && this.new_code[1] == 0 && this.new_code[2] == 1 && this.color_stack.insnoop == 0) {
                                this.stmaxCat.reset();
                                this.color_stack.mudPush(this.stmaxCat);
                                return -1;
                            }
                        }
                        return 205;
                    }
                    case 90: {
                        if (this.code_count == 1) {
                            this.color_stack.docatch();
                            return -1;
                        }
                        switch (this.new_code[1]) {
                            case 0: {
                                this.color_stack.docatch();
                                return -1;
                            }
                            case 1: {
                                this.color_stack.dothrow();
                                return -1;
                            }
                            default: {
                                return -1;
                            }
                        }
                        break;
                    }
                    case 94: {
                        this.MODE = 5;
                        this.snoop_name.setLength(0);
                        return -1;
                    }
                    case 95: {
                        this.MODE = 4;
                        return -1;
                    }
                    case 96: {
                        if (this.code_count == 4) {
                            this.win_to_close = this.new_code[1] * 100 + this.new_code[2];
                        }
                        else if (this.code_count == 3) {
                            this.win_to_close = this.new_code[1];
                        }
                        else {
                            System.err.println("Code 96, too few parameters");
                        }
                        this.MODE = 6;
                        return -1;
                    }
                    case 97: {
                        int wintr = 0;
                        if (this.code_count == 2) {
                            wintr = this.new_code[1];
                        }
                        else {
                            if (this.code_count != 3) {
                                System.err.println("In code 97, didn't receive two, received " + this.code_count);
                                return -1;
                            }
                            wintr = this.new_code[1] * 100 + this.new_code[2];
                        }
                        this.color_stack.mudPush(this.snoopholder);
                        return -1;
                    }
                    case 98: {
                        return 1;
                    }
                    case 99: {
                        Color tfg = Attribute.getdffg();
                        Color tbg = Attribute.getdfbg();
                        if (this.code_count > 1) {
                            switch (this.new_code[1]) {
                                case 0: {
                                    tfg = Attribute.getdfbg();
                                    break;
                                }
                                case 1: {
                                    tfg = Color.red;
                                    break;
                                }
                                case 2: {
                                    tfg = Color.green;
                                    break;
                                }
                                case 3: {
                                    tfg = Color.orange.darker();
                                    break;
                                }
                                case 4: {
                                    tfg = Color.blue;
                                    break;
                                }
                                case 5: {
                                    tfg = Color.magenta;
                                    break;
                                }
                                case 6: {
                                    tfg = Color.cyan;
                                    break;
                                }
                                case 7: {
                                    tfg = Color.lightGray;
                                    break;
                                }
                                case 8: {
                                    tfg = Color.gray;
                                    break;
                                }
                                case 9: {
                                    tfg = Color.red.brighter();
                                    break;
                                }
                                case 10: {
                                    tfg = Color.green.brighter();
                                    break;
                                }
                                case 11: {
                                    tfg = Color.yellow;
                                    break;
                                }
                                case 12: {
                                    tfg = Color.blue.brighter();
                                    break;
                                }
                                case 13: {
                                    tfg = Color.magenta.brighter();
                                    break;
                                }
                                case 14: {
                                    tfg = Color.cyan.brighter();
                                    break;
                                }
                                case 15: {
                                    tfg = Attribute.getdffg();
                                    break;
                                }
                            }
                        }
                        if (this.code_count > 2) {
                            switch (this.new_code[2]) {
                                case 0: {
                                    tbg = Attribute.getdfbg();
                                    break;
                                }
                                case 1: {
                                    tbg = Color.red;
                                    break;
                                }
                                case 2: {
                                    tbg = Color.green;
                                    break;
                                }
                                case 3: {
                                    tbg = Color.orange.darker();
                                    break;
                                }
                                case 4: {
                                    tbg = Color.blue;
                                    break;
                                }
                                case 5: {
                                    tbg = Color.magenta;
                                    break;
                                }
                                case 6: {
                                    tbg = Color.cyan;
                                    break;
                                }
                                case 7: {
                                    tbg = Color.lightGray;
                                    break;
                                }
                            }
                        }
                        this.color_stack.mudPush(new Attribute(tfg, tbg));
                        return -1;
                    }
                }
            }
        }
        System.err.println("Fall through: [" + this.new_code[0] + ((this.code_count > 1) ? ("," + this.new_code[1]) : "") + ((this.code_count > 2) ? ("," + this.new_code[2]) : "") + "]");
        return -1;
    }
    
    public void addChar(final byte by) {
        if (by == 0) {
            return;
        }
        char b;
        if (by > 0) {
            b = (char)by;
        }
        else {
            b = (char)(256 + by);
        }
        Label_0145: {
            switch (this.MODE) {
                case 7: {
                    if (b == '\u00ff') {
                        this.MODE = 0;
                        this.theframe.inputHandler.macroStrings[11] = "say " + this.dream_word.toString() + "^M";
                        break Label_0145;
                    }
                    this.dream_word.append(b);
                    break Label_0145;
                }
                case 0: {
                    switch (b) {
                        case '\u001b': {
                            this.MODE = 1;
                            break;
                        }
                        case '\u00ff': {
                            this.dopop();
                            break;
                        }
                        default: {
                            if (b >= '\u009b') {
                                this.docode(b - '\u009b');
                                break;
                            }
                            if ((b >= ' ' && b < '\u007f') || b == '\n' || b == '\b' || b == '\r') {
                                final Object ob = MudStack.curr_display;
                                if (ob instanceof DisplayInterface) {
                                    if (this.prompt > 0 && ob == this.def_display && this.color_stack.empty()) {
                                        this.prompt = 0;
                                    }
                                    ((DisplayInterface)ob).addChar(b, this.color_stack.getColour());
                                }
                                else {
                                    System.err.println("Not an interface");
                                }
                                break;
                            }
                            final int i = b;
                            if (i != 0) {
                                System.err.print("Character [" + i + "] received");
                                break;
                            }
                            break;
                        }
                    }
                    break;
                }
                case 1: {
                    if (b == '-') {
                        this.MODE = 2;
                    }
                    else if (b == '[') {
                        this.MODE = 8;
                    }
                    else {
                        System.err.println("ESC not followed by -: ignoring");
                        this.MODE = 0;
                    }
                    break;
                }
                case 2: {
                    switch (b) {
                        case 'C': {
                            MudStack.curr_display.cls();
                            break;
                        }
                        case 'K': {
                            MudStack.curr_display.cleol();
                            break;
                        }
                        case 'R': {
                            final Attribute rv = new Attribute(Attribute._defaultbg, Attribute._defaultfg);
                            this.color_stack.mudPush(rv);
                            break;
                        }
                        case 'r': {
                            if (!this.color_stack.empty()) {
                                this.color_stack.pop();
                            }
                            break;
                        }
                        default: {
                            System.err.println("Esc - followed by " + b + ". Ignoring");
                            break;
                        }
                    }
                    this.MODE = 0;
                    break;
                }
                case 8: {
                    if (Character.isDigit(b)) {
                        this.parstring.append(b);
                    }
                    else {
                        if (this.pcount < 6) {
                            if (this.parstring.toString() != "") {
                                try {
                                    final int no = Integer.parseInt(this.parstring.toString());
                                    this.ascii_parms[this.pcount++] = no;
                                }
                                catch (NumberFormatException ex) {}
                            }
                        }
                        else {
                            System.err.println("Too many ascii paramters");
                        }
                        this.parstring.setLength(0);
                        if (b != ';') {
                            this.MODE = 0;
                            if (b == 'm') {
                                this.do_ansii_colour();
                            }
                            else if (b == 'J') {
                                this.def_display.cls();
                            }
                            else if (b == 'H' || b == 'f') {
                                if (this.pcount == 0) {
                                    this.def_display.position(0, 0);
                                }
                                else if (this.pcount == 1) {
                                    this.def_display.position(0, this.ascii_parms[0] - 1);
                                }
                                else {
                                    this.def_display.position(this.ascii_parms[1] - 1, this.ascii_parms[0] - 1);
                                }
                            }
                            else if (b == 'A') {
                                if (this.pcount == 0) {
                                    this.ascii_parms[0] = 1;
                                }
                                for (int i = 0; i < this.ascii_parms[0]; ++i) {
                                    this.def_display.up();
                                }
                            }
                            else if (b == 'B') {
                                if (this.pcount == 0) {
                                    this.ascii_parms[0] = 1;
                                }
                                for (int i = 0; i < this.ascii_parms[0]; ++i) {
                                    this.def_display.down();
                                }
                            }
                            else if (b == 'C') {
                                if (this.pcount == 0) {
                                    this.ascii_parms[0] = 1;
                                }
                                for (int i = 0; i < this.ascii_parms[0]; ++i) {
                                    this.def_display.right();
                                }
                            }
                            else if (b == 'D') {
                                if (this.pcount == 0) {
                                    this.ascii_parms[0] = 1;
                                }
                                for (int i = 0; i < this.ascii_parms[0]; ++i) {
                                    this.def_display.left();
                                }
                            }
                            else if (b == 'K') {
                                this.def_display.cleol();
                            }
                            else if (b == 'n') {
                                System.out.println("Status request requested");
                                final char ESC = '\u001b';
                                if (this.theframe.mudbox != null) {
                                    this.theframe.mudbox.sendString(ESC + "[" + (this.def_display.getRow() - 1) + ";" + (this.def_display.getCol() - 1) + "R");
                                }
                            }
                            this.pcount = 0;
                        }
                    }
                    break;
                }
                case 3: {
                    if (b == '\u00ff') {
                        this.MODE = 0;
                        final int x = this.donecode();
                        if (x >= 0) {
                            this.donew(x);
                        }
                    }
                    else if (b >= '\u009b') {
                        this.docode(b - '\u009b');
                    }
                    else if (this.code_count > 0 && (this.new_code[0] == 94 || this.new_code[0] == 96 || this.new_code[0] == 97 || this.new_code[0] == 98)) {
                        this.docode(b + '\u009b');
                    }
                    else {
                        System.err.println("Received code less than 155 in code");
                        this.MODE = 0;
                    }
                    break;
                }
                case 4: {
                    if (b == '\u00ff') {
                        this.MODE = 0;
                    }
                    else {
                        this.id += b;
                    }
                    break;
                }
                case 5: {
                    if (b == '\u00ff') {
                        this.MODE = 0;
                    }
                    else {
                        this.snoop_name.append(b);
                    }
                    this.theframe.inputHandler.inputLine.requestFocus();
                    break;
                }
                case 6: {
                    if (b == '\u00ff') {
                        this.MODE = 0;
                    }
                    else {
                        System.err.println("Non-255 received in dummy.");
                    }
                    break;
                }
                default: {
                    System.err.println("Unnkown Mode, resetting");
                    this.MODE = 0;
                    break;
                }
            }
        }
    }
    
    public void showfes() {
        try {
            this.theframe.hp.exec = new Thread(this.theframe.hp, "Shield thread: " + HealthPanel.hpthread_no++);
            this.theframe.hp.show();
            this.theframe.validate();
            this.theframe.hp.exec.start();
        }
        catch (Exception e) {
            System.err.println("Exception in showfes");
            e.printStackTrace();
        }
    }
    
    public void addString(final String b) {
        final char[] bu = b.toCharArray();
        for (int i = 0; i < b.length(); ++i) {
            this.addChar((byte)bu[i]);
        }
        if (this.def_display == MudStack.curr_display) {
            this.def_display.checkrepaint();
        }
    }
    
    private void donew(final int n) {
        this.color_stack.mudPush(n);
    }
}
