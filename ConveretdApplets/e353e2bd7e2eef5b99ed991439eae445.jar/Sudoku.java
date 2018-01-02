import java.awt.Event;
import java.awt.MediaTracker;
import java.net.MalformedURLException;
import java.net.URL;
import java.applet.Applet;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.awt.LayoutManager;
import com.pzzl.utils.Timer;
import com.pzzl.utils.TimerCanvas;
import com.pzzl.utils.ImageCanvas;
import java.awt.CheckboxGroup;
import java.awt.Checkbox;
import com.pzzl.utils.TextCanvas;
import com.pzzl.utils.ImageButton;
import java.awt.Label;
import java.awt.Font;
import com.pzzl.utils.ImagePanel;
import java.awt.Image;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class Sudoku extends PZZLApplet
{
    Color \u011a;
    Color \u011b;
    Color \u011c;
    Color \u011d;
    Color \u011e;
    Color \u011f;
    Color \u0120;
    Color \u0121;
    Color \u0122;
    Color \u0123;
    Color \u0124;
    Color \u0125;
    Color \u0126;
    Color \u0127;
    Color \u0128;
    Color \u0129;
    String[] \u012a;
    boolean \u012b;
    boolean \u012c;
    Image \u012d;
    Image \u012e;
    Image \u012f;
    Image \u0130;
    Image \u0131;
    Image \u0132;
    Image \u0133;
    Image \u0134;
    Image \u0135;
    Image \u0136;
    Image \u0137;
    Image \u0138;
    Image \u0139;
    Image \u013a;
    Image \u013b;
    Image \u013c;
    Image \u013d;
    Image \u013e;
    Image \u013f;
    Image \u0140;
    Image \u0141;
    Image \u0142;
    Image \u0143;
    Image \u0144;
    Image \u0145;
    Image \u0146;
    Image \u0147;
    Image \u0148;
    Image \u0149;
    Image \u014a;
    Image \u014b;
    Image \u014c;
    Image \u014d;
    Image \u014e;
    Image \u014f;
    Image \u0150;
    Image \u0151;
    Image \u0152;
    Image \u0153;
    Image \u0154;
    Image \u0155;
    Image \u0156;
    Image \u0157;
    Image \u0158;
    Image \u0159;
    Image \u015a;
    Image \u015b;
    Image \u015c;
    Image \u015d;
    Image \u015e;
    ImagePanel \u015f;
    int \u0160;
    int \u0161;
    int \u0162;
    int \u0163;
    Color \u0164;
    Color \u0165;
    String \u0166;
    ImagePanel \u0167;
    int \u0168;
    int \u0169;
    int \u016a;
    int \u016b;
    Color \u016c;
    Color \u016d;
    String \u016e;
    ImagePanel \u016f;
    int \u0170;
    int \u0171;
    int \u0172;
    int \u0173;
    Color \u0174;
    Color \u0175;
    String \u0176;
    ImagePanel \u0177;
    int \u0178;
    int \u0179;
    int \u017a;
    int \u017b;
    Color \u017c;
    Color \u017d;
    String \u017e;
    ImagePanel \u017f;
    int \u0180;
    int \u0181;
    int \u0182;
    int \u0183;
    Color \u0184;
    Color \u0185;
    String \u0186;
    SudokuPanel \u0187;
    Font \u0188;
    Font \u0189;
    Font \u018a;
    Font \u018b;
    ImagePanel \u018c;
    int \u018d;
    int \u018e;
    int \u018f;
    int \u0190;
    Color \u0191;
    Color \u0192;
    String \u0193;
    Label \u0194;
    int \u0195;
    int \u0196;
    int \u0197;
    int \u0198;
    String \u0199;
    Color \u019a;
    ImageButton \u019b;
    int \u019c;
    int \u019d;
    int \u019e;
    int \u019f;
    Color \u01a0;
    Color \u01a1;
    String \u01a2;
    ImagePanel \u01a3;
    int \u01a4;
    int \u01a5;
    int \u01a6;
    int \u01a7;
    Color \u01a8;
    Color \u01a9;
    String \u01aa;
    Label \u01ab;
    int \u01ac;
    int \u01ad;
    int \u01ae;
    int \u01af;
    String \u01b0;
    Color \u01b1;
    TextCanvas \u01b2;
    int \u01b3;
    int \u01b4;
    int \u01b5;
    int \u01b6;
    Color \u01b7;
    ImageButton \u01b8;
    int \u01b9;
    int \u01ba;
    int \u01bb;
    int \u01bc;
    Color \u01bd;
    Color \u01be;
    String \u01bf;
    ImagePanel \u01c0;
    int \u01c1;
    int \u01c2;
    int \u01c3;
    int \u01c4;
    Color \u01c5;
    Color \u01c6;
    String \u01c7;
    Label \u01c8;
    int \u01c9;
    int \u01ca;
    int \u01cb;
    int \u01cc;
    String \u01cd;
    Color \u01ce;
    TextCanvas \u01cf;
    String \u01d0;
    int \u01d1;
    int \u01d2;
    int \u01d3;
    int \u01d4;
    Color \u01d5;
    Checkbox \u01d6;
    String \u01d7;
    int \u01d8;
    int \u01d9;
    int \u01da;
    int \u01db;
    Color \u01dc;
    Color \u01dd;
    Color \u01de;
    Checkbox \u01df;
    String \u01e0;
    int \u01e1;
    int \u01e2;
    int \u01e3;
    int \u01e4;
    Color \u01e5;
    Checkbox \u01e6;
    String \u01e7;
    int \u01e8;
    int \u01e9;
    int \u01ea;
    int \u01eb;
    Color \u01ec;
    CheckboxGroup \u01ed;
    ImageButton \u01ee;
    int \u01ef;
    int \u01f0;
    int \u01f1;
    int \u01f2;
    Color \u01f3;
    Color \u01f4;
    String \u01f5;
    ImageButton \u01fa;
    int \u01fb;
    int \u01fc;
    int \u01fd;
    int \u01fe;
    Color \u01ff;
    Color \u0200;
    String \u0201;
    Color \u0202;
    Color \u0203;
    String \u0204;
    int \u0205;
    int \u0206;
    int \u0207;
    int \u0208;
    Color \u0209;
    Color \u020a;
    String \u020b;
    ImageButton \u020c;
    int \u020d;
    int \u020e;
    int \u020f;
    int \u0210;
    Color \u0211;
    Color \u0212;
    String \u0213;
    ImageButton \u0214;
    int \u0215;
    int \u0216;
    int \u0217;
    int \u0250;
    Color \u0251;
    Color \u0252;
    String \u0253;
    ImageButton \u0254;
    int \u0255;
    int \u0256;
    int \u0257;
    int \u0258;
    Color \u0259;
    Color \u025a;
    String \u025b;
    ImageButton \u025c;
    int \u025d;
    int \u025e;
    int \u025f;
    int \u0260;
    Color \u0261;
    Color \u0262;
    String \u0263;
    ImageButton \u0264;
    int \u0265;
    int \u0266;
    int \u0267;
    int \u0268;
    Color \u0269;
    Color \u026a;
    String \u026b;
    ImageCanvas \u026c;
    int \u026d;
    int \u026e;
    int \u026f;
    int \u0270;
    Color \u0271;
    Color \u0272;
    String \u0273;
    TimerCanvas \u0274;
    int \u0275;
    int \u0276;
    int \u0277;
    int \u0278;
    Color \u0279;
    Color \u027a;
    String \u027b;
    ImageButton \u027c;
    int \u027d;
    int \u027e;
    int \u027f;
    int \u0280;
    Color \u0281;
    Color \u0282;
    String \u0283;
    ImageButton \u0284;
    int \u0285;
    int \u0286;
    int \u0287;
    int \u0288;
    Color \u0289;
    Color \u028a;
    String \u028b;
    ImageButton \u028c;
    int \u028d;
    int \u028e;
    int \u028f;
    int \u0290;
    Color \u0291;
    Color \u0292;
    String \u0293;
    ImageButton \u0294;
    int \u0295;
    int \u0296;
    int \u0297;
    int \u0298;
    Color \u0299;
    Color \u029a;
    String \u029b;
    ImagePanel \u029c;
    int \u029d;
    int \u029e;
    int \u029f;
    int \u02a0;
    Color \u02a1;
    Color \u02a2;
    String \u02a3;
    Timer \u02a4;
    String \u02a5;
    Font \u02a6;
    int \u02a7;
    int \u02a8;
    Label \u02b0;
    Label \u02b1;
    String \u02b2;
    String \u02b3;
    HyperlinkComponent \u02b4;
    String \u02b5;
    String \u02b6;
    String \u02b7;
    String \u02b8;
    String \u02bb;
    
    public void init() {
        this.setLayout(null);
        this.loadImages();
        this.requestFocus();
        this.readHelpText();
        this.readColors();
        this.readText();
        this.setText();
        this.setLayout(null);
        this.setBackground(this.\u011c);
        this.\u011b();
        this.\u02b7 = this.getParameter("username");
        this.\u02bb = this.getParameter("sessionID");
        if (this.\u02bb != null) {
            this.\u02b7 = this.\u02bb;
            this.\u02b8 = this.\u02bb;
        }
        else {
            this.\u02b8 = this.\u02b7;
        }
        this.\u0167.hide();
        String s;
        if (this.\u02b7 == null && this.\u02bb == null) {
            s = this.getUUID("", "");
        }
        else {
            s = this.getUUID(this.\u02b7, this.\u02b8);
        }
        this.\u0294.setBounds(this.\u0295, this.\u0296, this.\u0297, this.\u0298);
        this.\u0284.setBounds(this.\u0285, this.\u0286, this.\u0287, this.\u0288);
        this.\u016f.setBounds(this.\u0170, this.\u0169, this.\u0172, this.\u0173);
        this.\u0177.setBounds(this.\u0178, this.\u0179 - this.\u016b, this.\u017a, this.\u017b);
        this.\u011a(s);
    }
    
    private void \u011a(final String s) {
        final String doGet = this.doGet("pm=load&type=current&uuid=" + URLEncoder.encode(s));
        if (doGet.startsWith("<ERROR>")) {
            super.appletInitResult = 1;
            return;
        }
        if (!this.\u0187.setPuzzle(doGet)) {
            super.appletInitResult = 1;
            return;
        }
        this.\u0187.repaint();
        this.repaint();
        this.\u02a4.repaint();
        super.appletInitResult = 0;
        this.repaint();
    }
    
    public String readTextFromJar(final String s) {
        final StringBuffer sb = new StringBuffer();
        try {
            String line;
            while ((line = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(s))).readLine()) != null) {
                sb.append(String.valueOf(line) + " <n> ");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }
    
    public void readHelpText() {
        this.\u02a5 = this.readTextFromJar("help.txt");
    }
    
    public void readColors() {
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("colors.txt")));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final String tagValueString = this.getTagValueString(line, "[", "]");
                final String trim = line.substring(line.indexOf("]") + 1).trim();
                final String trim2 = this.getTagValueString(trim, "[", "]").trim();
                final String trim3 = trim.substring(trim.indexOf("]") + 1).trim();
                final String trim4 = this.getTagValueString(trim3, "[", "]").trim();
                final String trim5 = this.getTagValueString(trim3.substring(trim3.indexOf("]") + 1).trim(), "[", "]").trim();
                int int1;
                int int2;
                int int3;
                try {
                    int1 = Integer.parseInt(trim2);
                    int2 = Integer.parseInt(trim4);
                    int3 = Integer.parseInt(trim5);
                }
                catch (Exception ex2) {
                    int1 = 255;
                    int2 = 255;
                    int3 = 255;
                }
                if (tagValueString.equals("APPLETCOLOR")) {
                    this.\u011c = new Color(int1, int2, int3);
                }
                if (tagValueString.equals("CURSOR_COLOR")) {
                    this.\u011a = new Color(int1, int2, int3);
                }
                if (tagValueString.equals("FONTCOLOR")) {
                    this.\u011e = new Color(int1, int2, int3);
                }
                if (tagValueString.equals("HEADERLABEL_BACKGROUND")) {
                    this.\u011d = new Color(int1, int2, int3);
                }
                if (tagValueString.equals("STARTCELL_BACKGROUND_COLOR")) {
                    this.\u011f = new Color(int1, int2, int3);
                }
                if (tagValueString.equals("STARTCELL_FOREGROUND_COLOR")) {
                    this.\u0120 = new Color(int1, int2, int3);
                }
                if (tagValueString.equals("CHECK_WRONG_COLOR")) {
                    this.\u011b = new Color(int1, int2, int3);
                }
                if (tagValueString.equals("MENUBAR")) {
                    this.\u0121 = new Color(int1, int2, int3);
                }
                if (tagValueString.equals("ACTIVE_LETTER")) {
                    this.\u0126 = new Color(int1, int2, int3);
                }
                if (tagValueString.equals("ACTIVE_WORD")) {
                    this.\u0127 = new Color(int1, int2, int3);
                }
                if (tagValueString.equals("CLUE_BACKGROUND")) {
                    this.\u0122 = new Color(int1, int2, int3);
                }
                if (tagValueString.equals("LETTER_WRONG")) {
                    this.\u0125 = new Color(int1, int2, int3);
                }
                if (tagValueString.equals("ACTIVE_CLUE")) {
                    this.\u0123 = new Color(int1, int2, int3);
                }
                if (tagValueString.equals("CROSSING_CLUE")) {
                    this.\u0124 = new Color(int1, int2, int3);
                }
                if (tagValueString.equals("LETTER_SHOWN")) {
                    this.\u0128 = new Color(int1, int2, int3);
                }
                if (tagValueString.equals("MARK")) {
                    this.\u0129 = new Color(int1, int2, int3);
                }
            }
            bufferedReader.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void readText() {
        this.\u012a = new String[150];
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("text.txt")));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final int n = line.indexOf("]", 0) + 1;
                if (n >= 0) {
                    this.\u012a[this.getTagValue(line, "[", "]")] = line.substring(n).trim();
                }
            }
            bufferedReader.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void setText() {
        this.\u01cd = this.\u012a[50];
        this.\u01d0 = this.\u012a[51];
        this.\u01d7 = this.\u012a[52];
        this.\u01e0 = this.\u012a[53];
        this.\u01e7 = this.\u012a[54];
        this.\u02b2 = this.\u012a[55];
        this.\u02b3 = this.\u012a[56];
        this.\u02b5 = this.\u012a[57];
        this.\u02b6 = this.\u012a[58];
        this.\u01b0 = this.\u012a[60];
    }
    
    public int getTagValue(final String s, final String s2, final String s3) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, String.valueOf(s2) + s3);
        String nextToken;
        try {
            if (stringTokenizer.hasMoreTokens()) {
                nextToken = stringTokenizer.nextToken();
            }
            else {
                nextToken = "0";
            }
        }
        catch (NoSuchElementException ex) {
            nextToken = "0";
        }
        int int1;
        try {
            int1 = Integer.parseInt(nextToken);
        }
        catch (Exception ex2) {
            int1 = 0;
        }
        return int1;
    }
    
    public String removeChar(final String s, final char c) {
        String string = "";
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) != c) {
                string = String.valueOf(string) + s.charAt(i);
            }
        }
        return string;
    }
    
    public String getTagValueString(final String s, final String s2, final String s3) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, String.valueOf(s2) + s3);
        String nextToken;
        try {
            if (stringTokenizer.hasMoreTokens()) {
                nextToken = stringTokenizer.nextToken();
            }
            else {
                nextToken = "";
            }
        }
        catch (NoSuchElementException ex) {
            nextToken = "";
        }
        return nextToken;
    }
    
    private void \u011b() {
        (this.\u015f = new ImagePanel()).setLayout(null);
        this.\u015f.setBounds(this.\u0160, this.\u0161, this.\u0162, this.\u0163);
        this.\u015f.setBackground(this.\u0164);
        this.\u015f.setForeground(this.\u0165);
        this.\u015f.setString(this.\u0166);
        this.add(this.\u015f);
        (this.\u0167 = new ImagePanel()).setBounds(this.\u0168, this.\u0169, this.\u016a, this.\u016b);
        this.\u0167.setBackground(this.\u016c);
        this.\u0167.setForeground(this.\u016d);
        this.\u0167.setString(this.\u016e);
        this.\u0167.hideString();
        this.\u015f.add(this.\u0167);
        (this.\u016f = new ImagePanel()).setLayout(null);
        this.\u016f.setBounds(this.\u0170, this.\u0171, this.\u0172, this.\u0173);
        this.\u016f.setBackground(this.\u0174);
        this.\u016f.setForeground(this.\u0175);
        this.\u016f.setString(this.\u0176);
        this.\u015f.add(this.\u016f);
        (this.\u0177 = new ImagePanel()).setBounds(this.\u0178, this.\u0179, this.\u017a, this.\u017b);
        this.\u0177.setBackground(this.\u017c);
        this.\u0177.setForeground(this.\u017d);
        this.\u0177.setString(this.\u017e);
        this.\u0177.setImage(this.\u012d);
        this.\u0177.hideString();
        this.\u016f.add(this.\u0177);
        (this.\u017f = new ImagePanel()).setLayout(null);
        this.\u017f.setBounds(this.\u0180, this.\u0181, this.\u0182, this.\u0183);
        this.\u017f.setBackground(this.\u0184);
        this.\u017f.setForeground(this.\u0185);
        this.\u017f.setString(this.\u0186);
        this.\u017f.hideString();
        this.\u017f.setImage(this.\u012e);
        this.\u016f.add(this.\u017f);
        (this.\u0187 = new SudokuPanel(this.\u011c, this.\u011f, this.\u0120, this.\u011b, this.\u011a, this.\u0158, this.\u015a, this.\u015b, this.\u0159, this.\u015c, this.\u015d, this.\u015e, this.\u012a[100], this.\u012a[101])).setBounds(this.\u0205, this.\u0206, this.\u0207, this.\u0208);
        this.\u0187.setBackground(this.\u0209);
        this.\u0187.setForeground(this.\u020a);
        this.\u0187.setString(this.\u020b);
        this.\u0187.hideString();
        this.\u016f.add(this.\u0187);
        (this.\u018c = new ImagePanel()).setLayout(null);
        this.\u018c.setBounds(this.\u018d, this.\u018e, this.\u018f, this.\u0190);
        this.\u018c.setBackground(this.\u0191);
        this.\u018c.setForeground(this.\u0192);
        this.\u018c.setString(this.\u0193);
        this.\u018c.showBorder(true);
        this.\u018c.hideString();
        this.\u018c.hide();
        this.\u016f.add(this.\u018c);
        (this.\u0194 = new Label(this.\u0199)).setAlignment(1);
        this.\u0194.setBounds(this.\u0195, this.\u0196, this.\u0197, this.\u0198);
        this.\u0194.setBackground(this.\u011d);
        this.\u0194.setForeground(this.\u019a);
        this.\u018c.add(this.\u0194);
        (this.\u019b = new ImageButton(this.\u0152, this.\u0153, this.\u0154)).setBounds(this.\u019c, this.\u019d, this.\u019e, this.\u019f);
        this.\u019b.setBackground(this.\u01a0);
        this.\u019b.setForeground(this.\u01a1);
        this.\u019b.setString(this.\u01a2);
        this.\u019b.hideString();
        this.\u019b.hide();
        this.\u019b.addMouseListener(new Sudoku$1(this));
        this.\u018c.add(this.\u019b);
        (this.\u01a3 = new ImagePanel()).setLayout(null);
        this.\u01a3.setBounds(this.\u01a4, this.\u01a5, this.\u01a6, this.\u01a7);
        this.\u01a3.setBackground(this.\u01a8);
        this.\u01a3.setForeground(this.\u01a9);
        this.\u01a3.setString(this.\u01aa);
        this.\u01a3.showBorder(true);
        this.\u01a3.hideString();
        this.\u01a3.hide();
        this.\u016f.add(this.\u01a3);
        (this.\u01ab = new Label(this.\u01b0)).setAlignment(1);
        this.\u01ab.setBounds(this.\u01ac, this.\u01ad, this.\u01ae, this.\u01af);
        this.\u01ab.setBackground(this.\u011d);
        this.\u01ab.setForeground(this.\u01b1);
        this.\u01a3.add(this.\u01ab);
        (this.\u01b2 = new TextCanvas()).setFont(this.\u018b);
        this.\u01b2.setText(this.\u02a5);
        this.\u01b2.setBounds(this.\u01b3, this.\u01b4, this.\u01b5, this.\u01b6);
        this.\u01b2.setBackground(this.\u01b7);
        this.\u01b2.setForeground(this.\u011e);
        this.\u01a3.add(this.\u01b2);
        (this.\u01b8 = new ImageButton(this.\u0152, this.\u0153, this.\u0154)).setBounds(this.\u01b9, this.\u01ba, this.\u01bb, this.\u01bc);
        this.\u01b8.setBackground(this.\u01bd);
        this.\u01b8.setForeground(this.\u01be);
        this.\u01b8.setString(this.\u01bf);
        this.\u01b8.hideString();
        this.\u01b8.addMouseListener(new Sudoku$2(this));
        this.\u01a3.add(this.\u01b8);
        (this.\u01c0 = new ImagePanel()).setLayout(null);
        this.\u01c0.setBounds(this.\u01c1, this.\u01c2, this.\u01c3, this.\u01c4);
        this.\u01c0.setBackground(this.\u01c5);
        this.\u01c0.setForeground(this.\u01c6);
        this.\u01c0.setString(this.\u01c7);
        this.\u01c0.showBorder(true);
        this.\u01c0.hideString();
        this.\u01c0.hide();
        this.\u016f.add(this.\u01c0);
        (this.\u01c8 = new Label(this.\u01cd)).setAlignment(1);
        this.\u01c8.setBounds(this.\u01c9, this.\u01ca, this.\u01cb, this.\u01cc);
        this.\u01c8.setBackground(this.\u011d);
        this.\u01c8.setForeground(this.\u01ce);
        this.\u01c0.add(this.\u01c8);
        (this.\u01cf = new TextCanvas()).setText(this.\u01d0);
        this.\u01cf.setBounds(this.\u01d1, this.\u01d2, this.\u01d3, this.\u01d4);
        this.\u01cf.setBackground(this.\u01d5);
        this.\u01cf.setForeground(this.\u011e);
        this.\u01c0.add(this.\u01cf);
        this.\u01ed = new CheckboxGroup();
        (this.\u01d6 = new Checkbox(this.\u01d7, this.\u01ed, true)).setBounds(this.\u01d8, this.\u01d9, this.\u01da, this.\u01db);
        this.\u01d6.setBackground(this.\u01de);
        this.\u01d6.setForeground(this.\u011e);
        this.\u01c0.add(this.\u01d6);
        (this.\u01df = new Checkbox(this.\u01e0, this.\u01ed, false)).setBounds(this.\u01e1, this.\u01e2, this.\u01e3, this.\u01e4);
        this.\u01df.setBackground(this.\u01e5);
        this.\u01df.setForeground(this.\u011e);
        this.\u01c0.add(this.\u01df);
        (this.\u01e6 = new Checkbox(this.\u01e7, this.\u01ed, false)).setBounds(this.\u01e8, this.\u01e9, this.\u01ea, this.\u01eb);
        this.\u01e6.setBackground(this.\u01ec);
        this.\u01e6.setForeground(this.\u011e);
        this.\u01c0.add(this.\u01e6);
        (this.\u01ee = new ImageButton(this.\u0152, this.\u0153, this.\u0154)).setBounds(this.\u01ef, this.\u01f0, this.\u01f1, this.\u01f2);
        this.\u01ee.setBackground(this.\u01f3);
        this.\u01ee.setForeground(this.\u01f4);
        this.\u01ee.setString(this.\u01f5);
        this.\u01ee.hideString();
        this.\u01ee.addMouseListener(new Sudoku$3(this));
        this.\u01c0.add(this.\u01ee);
        (this.\u01fa = new ImageButton(this.\u0155, this.\u0156, this.\u0157)).setBounds(this.\u01fb, this.\u01fc, this.\u01fd, this.\u01fe);
        this.\u01fa.setBackground(this.\u01ff);
        this.\u01fa.setForeground(this.\u0200);
        this.\u01fa.setString(this.\u0201);
        this.\u01fa.hideString();
        this.\u01fa.addMouseListener(new Sudoku$4(this));
        (this.\u02b0 = new Label()).setBackground(this.\u01dc);
        this.\u02b0.setText(this.\u02b2);
        this.\u02b0.setFont(this.\u02a6);
        this.\u02b0.reshape(1, 120, this.\u02a7, this.\u02a8);
        this.\u02b0.setAlignment(1);
        this.\u02b0.setForeground(Color.black);
        (this.\u02b1 = new Label()).setBackground(this.\u01dd);
        this.\u02b1.setText(this.\u02b3);
        this.\u02b1.setFont(this.\u02a6);
        this.\u02b1.reshape(1, 140, this.\u02a7, this.\u02a8);
        this.\u02b1.setAlignment(1);
        this.\u02b1.setForeground(Color.black);
        this.\u01c0.add(this.\u02b0);
        this.\u01c0.add(this.\u02b1);
        this.\u01c0.add(this.\u01fa);
        (this.\u02b4 = new HyperlinkComponent(this.\u02b5, this.\u02b6, this, 11)).reshape(125, 165, 100, 12);
        this.\u01c0.add(this.\u02b4);
        (this.\u026c = new ImageCanvas(this.\u0135)).setBounds(this.\u026d, this.\u026e, this.\u026f, this.\u0270);
        this.\u026c.setBackground(this.\u0271);
        this.\u026c.setForeground(this.\u0272);
        this.\u016f.add(this.\u026c);
        (this.\u0274 = new TimerCanvas(this.\u0136)).setBounds(this.\u0275, this.\u0276, this.\u0277, this.\u0278);
        this.\u0274.setBackground(this.\u0279);
        this.\u0274.setForeground(this.\u027a);
        this.\u0274.setText(this.\u027b);
        this.\u0274.setFontColor(this.\u027a);
        this.\u016f.add(this.\u0274);
        (this.\u020c = new ImageButton(this.\u0140, this.\u0141, this.\u0142)).setBounds(this.\u020d, this.\u020e, this.\u020f, this.\u0210);
        this.\u020c.setBackground(this.\u0211);
        this.\u020c.setForeground(this.\u0212);
        this.\u020c.setString(this.\u0213);
        this.\u020c.hideString();
        this.\u020c.addMouseListener(new Sudoku$5(this));
        this.\u016f.add(this.\u020c);
        (this.\u0214 = new ImageButton(this.\u0149, this.\u014a, this.\u014b)).setBounds(this.\u0215, this.\u0216, this.\u0217, this.\u0250);
        this.\u0214.setBackground(this.\u0251);
        this.\u0214.setForeground(this.\u0252);
        this.\u0214.setString(this.\u0253);
        this.\u0214.hideString();
        this.\u0214.addMouseListener(new Sudoku$6(this));
        this.\u016f.add(this.\u0214);
        this.\u0254 = new ImageButton(this.\u014c, this.\u014d, this.\u014e);
        this.\u0254.maintainState = true;
        this.\u0254.setBounds(this.\u0255, this.\u0256, this.\u0257, this.\u0258);
        this.\u0254.setBackground(this.\u0259);
        this.\u0254.setForeground(this.\u025a);
        this.\u0254.setString(this.\u025b);
        this.\u0254.hideString();
        this.\u0254.addMouseListener(new Sudoku$7(this));
        this.\u016f.add(this.\u0254);
        (this.\u025c = new ImageButton(this.\u014f, this.\u0150, this.\u0151)).setBounds(this.\u025d, this.\u025e, this.\u025f, this.\u0260);
        this.\u025c.setBackground(this.\u0261);
        this.\u025c.setForeground(this.\u0262);
        this.\u025c.setString(this.\u0263);
        this.\u025c.hideString();
        this.\u025c.addMouseListener(new Sudoku$8(this));
        this.\u016f.add(this.\u025c);
        this.\u0264 = new ImageButton(this.\u0143, this.\u0144, this.\u0145);
        this.\u0264.maintainState = true;
        this.\u0264.setBounds(this.\u0265, this.\u0266, this.\u0267, this.\u0268);
        this.\u0264.setBackground(this.\u0269);
        this.\u0264.setForeground(this.\u026a);
        this.\u0264.setString(this.\u026b);
        this.\u0264.hideString();
        this.\u0264.addMouseListener(new Sudoku$9(this));
        this.\u016f.add(this.\u0264);
        (this.\u027c = new ImageButton(this.\u0137, this.\u0138, this.\u0139)).setBounds(this.\u027d, this.\u027e, this.\u027f, this.\u0280);
        this.\u027c.setBackground(this.\u0281);
        this.\u027c.setForeground(this.\u0282);
        this.\u027c.setString(this.\u0283);
        this.\u027c.hideString();
        this.\u027c.addMouseListener(new Sudoku$10(this));
        this.\u016f.add(this.\u027c);
        (this.\u0284 = new ImageButton(this.\u012f, this.\u0130, this.\u0131)).setBounds(this.\u0285, this.\u0286, this.\u0287, this.\u0288);
        this.\u0284.setBackground(this.\u0289);
        this.\u0284.setForeground(this.\u028a);
        this.\u0284.setString(this.\u028b);
        this.\u0284.hideString();
        this.\u0284.addMouseListener(new Sudoku$11(this));
        this.\u017f.add(this.\u0284);
        this.\u028c = new ImageButton(this.\u0146, this.\u0147, this.\u0148);
        this.\u028c.maintainState = true;
        this.\u028c.setBounds(this.\u028d, this.\u028e, this.\u028f, this.\u0290);
        this.\u028c.setBackground(this.\u0291);
        this.\u028c.setForeground(this.\u0292);
        this.\u028c.setString(this.\u0293);
        this.\u028c.hideString();
        this.\u028c.addMouseListener(new Sudoku$12(this));
        this.\u016f.add(this.\u028c);
        (this.\u0294 = new ImageButton(this.\u0132, this.\u0133, this.\u0134)).setBounds(this.\u0295, this.\u0296, this.\u0297, this.\u0298);
        this.\u0294.setBackground(this.\u0299);
        this.\u0294.setForeground(this.\u029a);
        this.\u0294.setString(this.\u029b);
        this.\u0294.hideString();
        this.\u0294.addMouseListener(new Sudoku$13(this));
        this.\u017f.add(this.\u0294);
        (this.\u029c = new ImagePanel()).setLayout(null);
        this.\u029c.setBounds(this.\u029d, this.\u029e, this.\u029f, this.\u02a0);
        this.\u029c.setBackground(this.\u02a1);
        this.\u029c.setForeground(this.\u02a2);
        this.\u029c.setString(this.\u02a3);
        this.\u029c.showBorder(false);
        this.\u029c.hideString();
        this.\u017f.add(this.\u029c);
        (this.\u02a4 = new Timer(this.\u0274)).setForeground(Color.black);
        this.\u02a4.start();
    }
    
    private void \u011c() {
        String s = this.\u0187.getCurrentSituation();
        String s2 = "";
        if (this.\u01d6.getState()) {
            s = this.\u0187.getCurrentEmptySituation();
            s2 = "empty";
        }
        if (this.\u01df.getState()) {
            s = this.\u0187.getCurrentSituation();
            s2 = "current";
        }
        if (this.\u01e6.getState()) {
            s = this.\u0187.getCurrentSolution();
            s2 = "solution";
        }
        final String string = String.valueOf(super.host) + "/" + super.context + "/" + super.servlet + "?pm=pdf&data=" + URLEncoder.encode(s) + "&type=" + s2;
        final String s3 = "_blank";
        try {
            this.getAppletContext().showDocument(new URL("http://" + string), s3);
        }
        catch (MalformedURLException ex) {}
    }
    
    public void start() {
        this.\u0187.requestFocus();
        this.\u02a4.repaint();
        this.repaint();
    }
    
    public void loadImages() {
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(this.\u0158 = this.getImage(this.getCodeBase(), "graphics/board.gif"), 0);
        mediaTracker.addImage(this.\u012d = this.getImage(this.getCodeBase(), "graphics/footer.gif"), 0);
        mediaTracker.addImage(this.\u012e = this.getImage(this.getCodeBase(), "graphics/menubalk.gif"), 0);
        mediaTracker.addImage(this.\u0152 = this.getImage(this.getCodeBase(), "graphics/btn_ok_0.gif"), 0);
        mediaTracker.addImage(this.\u0153 = this.getImage(this.getCodeBase(), "graphics/btn_ok_2.gif"), 0);
        mediaTracker.addImage(this.\u0154 = this.getImage(this.getCodeBase(), "graphics/btn_ok_1.gif"), 0);
        mediaTracker.addImage(this.\u0155 = this.getImage(this.getCodeBase(), "graphics/btn_cancel_0.gif"), 0);
        mediaTracker.addImage(this.\u0156 = this.getImage(this.getCodeBase(), "graphics/btn_cancel_2.gif"), 0);
        mediaTracker.addImage(this.\u0157 = this.getImage(this.getCodeBase(), "graphics/btn_cancel_1.gif"), 0);
        mediaTracker.addImage(this.\u012f = this.getImage(this.getCodeBase(), "graphics/mnu_print_0.gif"), 0);
        mediaTracker.addImage(this.\u0130 = this.getImage(this.getCodeBase(), "graphics/mnu_print_2.gif"), 0);
        mediaTracker.addImage(this.\u0131 = this.getImage(this.getCodeBase(), "graphics/mnu_print_1.gif"), 0);
        mediaTracker.addImage(this.\u0132 = this.getImage(this.getCodeBase(), "graphics/mnu_help_0.gif"), 0);
        mediaTracker.addImage(this.\u0133 = this.getImage(this.getCodeBase(), "graphics/mnu_help_2.gif"), 0);
        mediaTracker.addImage(this.\u0134 = this.getImage(this.getCodeBase(), "graphics/mnu_help_1.gif"), 0);
        mediaTracker.addImage(this.\u0135 = this.getImage(this.getCodeBase(), "graphics/timerlabel.gif"), 0);
        mediaTracker.addImage(this.\u0136 = this.getImage(this.getCodeBase(), "graphics/timer_bg.gif"), 0);
        mediaTracker.addImage(this.\u0137 = this.getImage(this.getCodeBase(), "graphics/btn_start_0.gif"), 0);
        mediaTracker.addImage(this.\u0138 = this.getImage(this.getCodeBase(), "graphics/btn_start_2.gif"), 0);
        mediaTracker.addImage(this.\u0139 = this.getImage(this.getCodeBase(), "graphics/btn_start_1.gif"), 0);
        mediaTracker.addImage(this.\u013a = this.getImage(this.getCodeBase(), "graphics/btn_stop_0.gif"), 0);
        mediaTracker.addImage(this.\u013b = this.getImage(this.getCodeBase(), "graphics/btn_stop_2.gif"), 0);
        mediaTracker.addImage(this.\u013c = this.getImage(this.getCodeBase(), "graphics/btn_stop_1.gif"), 0);
        mediaTracker.addImage(this.\u013d = this.getImage(this.getCodeBase(), "graphics/btn_done_0.gif"), 0);
        mediaTracker.addImage(this.\u013e = this.getImage(this.getCodeBase(), "graphics/btn_done_2.gif"), 0);
        mediaTracker.addImage(this.\u013f = this.getImage(this.getCodeBase(), "graphics/btn_done_1.gif"), 0);
        mediaTracker.addImage(this.\u0140 = this.getImage(this.getCodeBase(), "graphics/btn_undo_0.gif"), 0);
        mediaTracker.addImage(this.\u0141 = this.getImage(this.getCodeBase(), "graphics/btn_undo_2.gif"), 0);
        mediaTracker.addImage(this.\u0142 = this.getImage(this.getCodeBase(), "graphics/btn_undo_1.gif"), 0);
        mediaTracker.addImage(this.\u0143 = this.getImage(this.getCodeBase(), "graphics/btn_check_0.gif"), 0);
        mediaTracker.addImage(this.\u0144 = this.getImage(this.getCodeBase(), "graphics/btn_check_2.gif"), 0);
        mediaTracker.addImage(this.\u0145 = this.getImage(this.getCodeBase(), "graphics/btn_check_1.gif"), 0);
        mediaTracker.addImage(this.\u0146 = this.getImage(this.getCodeBase(), "graphics/btn_1-9_0.gif"), 0);
        mediaTracker.addImage(this.\u0147 = this.getImage(this.getCodeBase(), "graphics/btn_1-9_2.gif"), 0);
        mediaTracker.addImage(this.\u0148 = this.getImage(this.getCodeBase(), "graphics/btn_1-9_1.gif"), 0);
        mediaTracker.addImage(this.\u0149 = this.getImage(this.getCodeBase(), "graphics/btn_hint_0.gif"), 0);
        mediaTracker.addImage(this.\u014a = this.getImage(this.getCodeBase(), "graphics/btn_hint_2.gif"), 0);
        mediaTracker.addImage(this.\u014b = this.getImage(this.getCodeBase(), "graphics/btn_hint_1.gif"), 0);
        this.\u014c = this.getImage(this.getCodeBase(), "graphics/btn_pencil_0.gif");
        mediaTracker.addImage(this.\u0149, 0);
        this.\u014d = this.getImage(this.getCodeBase(), "graphics/btn_pencil_2.gif");
        mediaTracker.addImage(this.\u014a, 0);
        this.\u014e = this.getImage(this.getCodeBase(), "graphics/btn_pencil_1.gif");
        mediaTracker.addImage(this.\u014b, 0);
        mediaTracker.addImage(this.\u014f = this.getImage(this.getCodeBase(), "graphics/btn_reveal_0.gif"), 0);
        mediaTracker.addImage(this.\u0150 = this.getImage(this.getCodeBase(), "graphics/btn_reveal_2.gif"), 0);
        mediaTracker.addImage(this.\u0151 = this.getImage(this.getCodeBase(), "graphics/btn_reveal_1.gif"), 0);
        mediaTracker.addImage(this.\u015b = this.getImage(this.getCodeBase(), "graphics/dragItems.gif"), 0);
        mediaTracker.addImage(this.\u0159 = this.getImage(this.getCodeBase(), "graphics/pencil.gif"), 0);
        mediaTracker.addImage(this.\u015a = this.getImage(this.getCodeBase(), "graphics/startItems.gif"), 0);
        mediaTracker.addImage(this.\u015c = this.getImage(this.getCodeBase(), "graphics/btn_cijfers_0.gif"), 0);
        mediaTracker.addImage(this.\u015d = this.getImage(this.getCodeBase(), "graphics/btn_cijfers_1.gif"), 0);
        mediaTracker.addImage(this.\u015e = this.getImage(this.getCodeBase(), "graphics/btn_cijfers_2.gif"), 0);
        try {
            mediaTracker.waitForAll(0L);
        }
        catch (Exception ex) {
            System.out.println("Error while waiting for images = [" + ex.toString() + "]");
        }
    }
    
    public boolean keyDown(final Event event, final int n) {
        return this.\u0187.isVisible() && this.\u0187.processKey(event, n);
    }
    
    public String getTagValue(final String s, final String s2) {
        final String lowerCase = s.toLowerCase();
        final String lowerCase2 = s2.toLowerCase();
        final int n = lowerCase.indexOf(lowerCase2, 0) + lowerCase2.length();
        final int index = lowerCase.indexOf("</" + lowerCase2.substring(1, lowerCase2.length()), 0);
        if (n >= 0 & index > 0) {
            return s.substring(n, index);
        }
        return "";
    }
    
    public String removeTagValue(final String s, final String s2) {
        final String lowerCase = s.toLowerCase();
        final String lowerCase2 = s2.toLowerCase();
        final int index = lowerCase.indexOf(lowerCase2, 0);
        final int index2 = lowerCase.indexOf("</" + lowerCase2.substring(1, lowerCase2.length()), 0);
        if (index >= 0 & index2 > 0) {
            return s.substring(0, index).concat(s.substring(index2 + s2.length() + 1, s.length()));
        }
        return s;
    }
    
    public int getTagValueInt(final String s, final String s2) {
        int int1;
        try {
            final String lowerCase = s.toLowerCase();
            final String lowerCase2 = s2.toLowerCase();
            final int n = lowerCase.indexOf(lowerCase2, 0) + lowerCase2.length();
            final int index = lowerCase.indexOf("</" + lowerCase2.substring(1, lowerCase2.length()), 0);
            if (n >= 0 & index > 0) {
                int1 = Integer.parseInt(s.substring(n, index));
            }
            else {
                int1 = 0;
            }
        }
        catch (NumberFormatException ex) {
            System.out.println("Number format exception! [" + ex.toString() + "]");
            int1 = 0;
        }
        return int1;
    }
    
    public String getUUID(String encode, String encode2) {
        encode = URLEncoder.encode(encode);
        encode2 = URLEncoder.encode(encode2);
        return this.doGet("pm=uuid&username=" + encode + "&password=" + encode2);
    }
    
    public Sudoku() {
        this.\u012b = false;
        this.\u012c = false;
        this.\u0162 = 475;
        this.\u0163 = 368;
        this.\u0164 = this.\u011c;
        this.\u0165 = Color.white;
        this.\u0166 = "Main Panel";
        this.\u016a = 475;
        this.\u016b = 69;
        this.\u016c = this.\u011c;
        this.\u016d = Color.white;
        this.\u016e = "Header Panel";
        this.\u0171 = 70;
        this.\u0172 = 475;
        this.\u0173 = 368;
        this.\u0174 = this.\u011c;
        this.\u0175 = Color.white;
        this.\u0176 = "Center Panel";
        this.\u0178 = 17;
        this.\u0179 = 424;
        this.\u017a = 475;
        this.\u017b = 14;
        this.\u017c = this.\u011c;
        this.\u017d = Color.white;
        this.\u017e = "Footer Panel";
        this.\u0182 = 475;
        this.\u0183 = 22;
        this.\u0184 = this.\u011c;
        this.\u0185 = Color.white;
        this.\u0186 = "Button Panel";
        this.\u0188 = new Font("Verdana", 1, 11);
        this.\u0189 = new Font("Verdana", 0, 11);
        this.\u018a = new Font("Roman", 1, 12);
        this.\u018b = new Font("Roman", 1, 10);
        this.\u018d = 62;
        this.\u018e = 170;
        this.\u018f = 255;
        this.\u0190 = 46;
        this.\u0191 = this.\u011c;
        this.\u0192 = Color.white;
        this.\u0193 = "messagePanel";
        this.\u0195 = 2;
        this.\u0196 = 2;
        this.\u0197 = 251;
        this.\u0198 = 20;
        this.\u0199 = "SAVING SUDOKU";
        this.\u019a = Color.white;
        this.\u019c = 100;
        this.\u019d = 24;
        this.\u019e = 67;
        this.\u019f = 20;
        this.\u01a0 = new Color(204, 255, 204);
        this.\u01a1 = new Color(0, 0, 102);
        this.\u01a2 = "OK";
        this.\u01a4 = 17;
        this.\u01a5 = 30;
        this.\u01a6 = 355;
        this.\u01a7 = 300;
        this.\u01a8 = this.\u011c;
        this.\u01a9 = Color.white;
        this.\u01aa = "helpPanel";
        this.\u01ac = 2;
        this.\u01ad = 2;
        this.\u01ae = 351;
        this.\u01af = 20;
        this.\u01b1 = Color.white;
        this.\u01b3 = 2;
        this.\u01b4 = 15;
        this.\u01b5 = 351;
        this.\u01b6 = 255;
        this.\u01b7 = this.\u011c;
        this.\u01b9 = 140;
        this.\u01ba = 275;
        this.\u01bb = 67;
        this.\u01bc = 20;
        this.\u01bd = new Color(204, 255, 204);
        this.\u01be = new Color(0, 0, 102);
        this.\u01bf = "help";
        this.\u01c1 = 17;
        this.\u01c2 = 70;
        this.\u01c3 = 355;
        this.\u01c4 = 250;
        this.\u01c5 = this.\u011c;
        this.\u01c6 = Color.white;
        this.\u01c7 = "printPanel";
        this.\u01c9 = 2;
        this.\u01ca = 2;
        this.\u01cb = 351;
        this.\u01cc = 20;
        this.\u01ce = Color.white;
        this.\u01d1 = 2;
        this.\u01d2 = 24;
        this.\u01d3 = 351;
        this.\u01d4 = 20;
        this.\u01d5 = this.\u011c;
        this.\u01d8 = 130;
        this.\u01d9 = 42;
        this.\u01da = 120;
        this.\u01db = 20;
        this.\u01dc = this.\u011c;
        this.\u01dd = this.\u011c;
        this.\u01de = this.\u011c;
        this.\u01e1 = 130;
        this.\u01e2 = 62;
        this.\u01e3 = 120;
        this.\u01e4 = 20;
        this.\u01e5 = this.\u011c;
        this.\u01e8 = 130;
        this.\u01e9 = 82;
        this.\u01ea = 120;
        this.\u01eb = 20;
        this.\u01ec = this.\u011c;
        this.\u01ef = 80;
        this.\u01f0 = 200;
        this.\u01f1 = 67;
        this.\u01f2 = 20;
        this.\u01f3 = new Color(204, 255, 204);
        this.\u01f4 = new Color(0, 0, 102);
        this.\u01f5 = "print";
        this.\u01fb = 205;
        this.\u01fc = 200;
        this.\u01fd = 67;
        this.\u01fe = 20;
        this.\u01ff = new Color(204, 255, 204);
        this.\u0200 = new Color(0, 0, 102);
        this.\u0201 = "Cancel";
        this.\u0202 = this.\u011c;
        this.\u0203 = Color.white;
        this.\u0204 = "saveHeaderPanelLabel";
        this.\u0205 = 17;
        this.\u0206 = 42;
        this.\u0207 = 355;
        this.\u0208 = 306;
        this.\u0209 = this.\u011c;
        this.\u020a = Color.white;
        this.\u020b = "Board Panel";
        this.\u020d = 390;
        this.\u020e = 160;
        this.\u020f = 67;
        this.\u0210 = 20;
        this.\u0211 = new Color(204, 255, 204);
        this.\u0212 = new Color(0, 0, 102);
        this.\u0213 = "Undo";
        this.\u0215 = 390;
        this.\u0216 = 292;
        this.\u0217 = 67;
        this.\u0250 = 20;
        this.\u0251 = new Color(204, 255, 204);
        this.\u0252 = new Color(0, 0, 102);
        this.\u0253 = "Hint";
        this.\u0255 = 390;
        this.\u0256 = 182;
        this.\u0257 = 67;
        this.\u0258 = 20;
        this.\u0259 = new Color(204, 255, 204);
        this.\u025a = new Color(0, 0, 102);
        this.\u025b = "pencil";
        this.\u025d = 390;
        this.\u025e = 314;
        this.\u025f = 67;
        this.\u0260 = 20;
        this.\u0261 = new Color(204, 255, 204);
        this.\u0262 = new Color(0, 0, 102);
        this.\u0263 = "Reveal";
        this.\u0265 = 390;
        this.\u0266 = 204;
        this.\u0267 = 67;
        this.\u0268 = 20;
        this.\u0269 = new Color(204, 255, 204);
        this.\u026a = new Color(0, 0, 102);
        this.\u026b = "Check";
        this.\u026d = 390;
        this.\u026e = 50;
        this.\u026f = 67;
        this.\u0270 = 17;
        this.\u0271 = this.\u011c;
        this.\u0272 = new Color(0, 0, 102);
        this.\u0273 = "00:00";
        this.\u0275 = 390;
        this.\u0276 = 70;
        this.\u0277 = 67;
        this.\u0278 = 25;
        this.\u0279 = this.\u011c;
        this.\u027a = Color.white;
        this.\u027b = "00:00";
        this.\u027d = 390;
        this.\u027e = 97;
        this.\u027f = 67;
        this.\u0280 = 20;
        this.\u0281 = new Color(204, 255, 204);
        this.\u0282 = new Color(0, 0, 102);
        this.\u0283 = "Clock";
        this.\u0285 = 67;
        this.\u0286 = 4;
        this.\u0287 = 70;
        this.\u0288 = 19;
        this.\u0289 = new Color(204, 255, 204);
        this.\u028a = new Color(0, 0, 102);
        this.\u028b = "Print";
        this.\u028d = 390;
        this.\u028e = 270;
        this.\u028f = 67;
        this.\u0290 = 20;
        this.\u0291 = new Color(204, 255, 204);
        this.\u0292 = new Color(0, 0, 102);
        this.\u0293 = "1..9";
        this.\u0295 = 20;
        this.\u0296 = 4;
        this.\u0297 = 45;
        this.\u0298 = 19;
        this.\u0299 = new Color(204, 255, 204);
        this.\u029a = new Color(0, 0, 102);
        this.\u029b = "Help";
        this.\u029d = 358;
        this.\u029e = 3;
        this.\u029f = 110;
        this.\u02a0 = 19;
        this.\u02a1 = this.\u011c;
        this.\u02a2 = Color.white;
        this.\u02a3 = "levelPanel";
        this.\u02a5 = "";
        this.\u02a6 = new Font("roman", 0, 11);
        this.\u02a7 = this.\u01c3 - 2;
        this.\u02a8 = 20;
    }
    
    static void \u011d(final Sudoku sudoku) {
        sudoku.\u011c();
    }
}
