import java.awt.image.ImageObserver;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.awt.Font;
import java.applet.Applet;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import com.pzzl.utils.ImageHyperLinkPanel;
import com.pzzl.utils.ImageButton;
import com.pzzl.utils.ImagePanel;
import java.awt.Color;
import com.pzzl.utils.Panel2;

// 
// Decompiled by Procyon v0.5.30
// 

public class SetPanel extends Panel2
{
    public static final int SHADING = 3;
    public static final int SYMBOL = 2;
    public static final int COLOR = 1;
    public static final int NUMBER = 0;
    public static final int SQUIGGLE = 0;
    public static final int DIAMOND = 1;
    public static final int OVAL = 2;
    public static final int PURPLE = 1;
    public static final int RED = 0;
    public static final int GREEN = 2;
    public static final int SOLID = 0;
    public static final int LINED = 1;
    public static final int EMPTY = 2;
    public static final int ONE = 0;
    public static final int TWO = 1;
    public static final int THREE = 2;
    private SetFeature[] \u019a;
    protected int[] \u019b;
    SetTogether \u00c8;
    final int \u019c = 12;
    final int \u019d = 24;
    int \u019e;
    int \u019f;
    final int ROWS = 3;
    final int COLUMNS = 4;
    final int \u01a0 = 91;
    final int \u01a1 = 59;
    final int \u01a2 = 58;
    final int \u01a3 = 38;
    Color \u01a4;
    SetSolutionList solutionList;
    SetSolutionList \u01a5;
    int[] \u01a6;
    int[] \u01a7;
    CardImagePanel[] \u01a8;
    Color \u01a9;
    Panel2[] \u01aa;
    Color \u01ab;
    ImagePanel[] \u01ac;
    Color \u01ad;
    Panel2[] \u01ae;
    Color \u01af;
    String \u01b0;
    String \u01b1;
    boolean \u01b2;
    String \u01b3;
    boolean \u01b4;
    String \u01b5;
    int \u01b6;
    int \u01b7;
    int \u01b8;
    int \u01b9;
    int \u01ba;
    int \u01bb;
    Color \u01bc;
    Color \u01bd;
    String \u01be;
    ImageButton \u01bf;
    Panel2 \u01c0;
    int \u01c1;
    int \u01c2;
    int \u01c3;
    int \u01c4;
    Color \u01c5;
    Color \u01c6;
    Panel2 \u01c7;
    int \u01c8;
    int \u01c9;
    int \u01ca;
    int \u01cb;
    Color \u01cc;
    Panel2 \u01cd;
    int \u01ce;
    int \u01cf;
    int \u01d0;
    int \u01d1;
    Color \u01d2;
    Color \u01d3;
    ImageHyperLinkPanel \u01d4;
    int \u01d5;
    int \u01d6;
    Color \u01d7;
    Color \u01d8;
    Panel2 \u01d9;
    int \u01da;
    int \u01db;
    int \u01dc;
    int \u01dd;
    Color \u01de;
    Color \u01df;
    Panel2 \u01e0;
    int \u01e1;
    int \u01e2;
    int \u01e3;
    int \u01e4;
    Color \u01e5;
    Color \u01e6;
    Color \u01e7;
    String \u01e8;
    Panel2 \u01e9;
    int \u01ea;
    int \u01eb;
    int \u01ec;
    int \u01ed;
    Color \u01ee;
    Color \u01ef;
    Color \u01f0;
    Color \u01f1;
    String \u01f2;
    Panel2 \u01f3;
    int \u01f4;
    int \u01f5;
    int \u01fa;
    int \u01fb;
    Color \u01fc;
    Color \u01fd;
    String \u01fe;
    Panel2 \u01ff;
    int \u0200;
    int \u0201;
    int \u0202;
    int \u0203;
    Color \u0204;
    Color \u0205;
    String \u0206;
    Label \u0207;
    int \u0208;
    Color \u0209;
    Color \u020a;
    Label \u020b;
    Label \u020c;
    boolean \u020d;
    boolean \u020e;
    Move[] \u020f;
    Move[] \u0210;
    boolean hideString;
    public boolean showCandidates;
    Image \u00c2;
    String \u00c3;
    int \u0211;
    int \u0212;
    private Image \u0213;
    private Image \u0214;
    private Graphics \u0215;
    private Graphics \u0216;
    int[][] \u0217;
    int[][] \u0250;
    Image[] \u0251;
    Rectangle \u0252;
    boolean \u0253;
    boolean \u0254;
    int \u0255;
    int \u0256;
    int \u0257;
    final int \u0258 = 0;
    final int \u0259 = 1;
    final int \u025a = 2;
    final int \u025b = 3;
    final int \u025c = 4;
    final int \u025d = 5;
    final int \u025e = 50;
    final int \u025f = 25;
    final int \u0260 = 25;
    final int \u0261 = 25;
    final int \u0262 = 10;
    final int \u0263 = 12;
    final int \u0264 = 5;
    int \u0265;
    int \u0266;
    int \u0267;
    String \u0268;
    String \u013f;
    int \u0269;
    Color \u00c9;
    
    public SetPanel(final SetTogether \u00e8, final Color color, final Image image, final Image[] \u0251, final String \u01b0, final String \u028a, final int n, final int n2, final int n3, final int n4) {
        this.\u019b = new int[4];
        this.solutionList = new SetSolutionList();
        this.\u01a5 = new SetSolutionList();
        this.\u01a6 = new int[12];
        this.\u01a7 = new int[13];
        this.\u01a8 = new CardImagePanel[12];
        this.\u01a9 = this.\u01a4;
        this.\u01aa = new Panel2[24];
        this.\u01ab = this.\u01a4;
        this.\u01ac = new ImagePanel[24];
        this.\u01ad = new Color(244, 244, 244);
        this.\u01af = this.\u01a4;
        this.\u01b2 = false;
        this.\u01b4 = false;
        this.\u01b5 = "";
        this.\u01b6 = 1;
        this.\u01b7 = 1;
        this.\u01b8 = 70;
        this.\u01b9 = 180;
        this.\u01ba = 50;
        this.\u01bb = 20;
        this.\u01bc = Color.white;
        this.\u01bd = Color.black;
        this.\u01be = "OK";
        this.\u01bf = new ImageButton(this.\u01be);
        this.\u01c3 = 397;
        this.\u01c4 = 227;
        this.\u01c5 = this.\u01a4;
        this.\u01c6 = Color.black;
        this.\u01c8 = 4;
        this.\u01c9 = 2;
        this.\u01ca = 397;
        this.\u01cb = 227;
        this.\u01cc = this.\u01a4;
        this.\u01ce = 4;
        this.\u01cf = 2;
        this.\u01d0 = 397;
        this.\u01d1 = 227;
        this.\u01d2 = this.\u01a4;
        this.\u01d3 = Color.black;
        this.\u01d5 = 397;
        this.\u01d6 = 26;
        this.\u01d7 = this.\u01a4;
        this.\u01d8 = Color.white;
        this.\u01db = 26;
        this.\u01dc = 397;
        this.\u01dd = 207;
        this.\u01de = this.\u01a4;
        this.\u01df = Color.white;
        this.\u01e1 = 4;
        this.\u01e2 = 237;
        this.\u01e3 = 397;
        this.\u01e4 = 32;
        this.\u01e5 = Color.yellow;
        this.\u01e6 = Color.white;
        this.\u01e7 = Color.white;
        this.\u01e8 = "main Panel";
        this.\u01ec = 397;
        this.\u01ed = 32;
        this.\u01ee = new Color(230, 239, 248);
        this.\u01ef = new Color(255, 204, 204);
        this.\u01f0 = new Color(201, 249, 197);
        this.\u01f1 = Color.black;
        this.\u01f2 = "Message Panel";
        this.\u01f5 = 25;
        this.\u01fa = 442;
        this.\u01fb = 25;
        this.\u01fc = new Color(100, 149, 237);
        this.\u01fd = Color.white;
        this.\u01fe = "Message Panel";
        this.\u0200 = 407;
        this.\u0201 = 2;
        this.\u0202 = 186;
        this.\u0203 = 300;
        this.\u0204 = this.\u01a4;
        this.\u0205 = Color.white;
        this.\u0206 = "found Panel";
        this.\u0208 = 26;
        this.\u0209 = this.\u01a4;
        this.\u020a = new Color(102, 102, 102);
        this.\u020d = false;
        this.\u020e = false;
        this.\u020f = new Move[144];
        this.\u0210 = new Move[144];
        this.hideString = false;
        this.showCandidates = false;
        this.\u0253 = false;
        this.\u0254 = false;
        this.setBounds(n, n2, n3, n4);
        this.\u01c3 = this.size().width;
        this.\u01c4 = this.size().height;
        this.\u01d0 = this.size().width;
        this.\u01d1 = this.size().height;
        this.\u00c9 = color;
        this.\u00c8 = \u00e8;
        this.\u0269 = 0;
        this.\u0251 = \u0251;
        this.setLayout(null);
        (this.\u01c0 = new Panel2()).setLayout(null);
        this.\u01c0.setBounds(this.\u01c1, this.\u01c2, this.\u01c3, this.\u01c4);
        this.\u01c0.setBackground(this.\u01c5);
        this.\u01c0.setForeground(this.\u01c6);
        this.\u01c0.setVisible(true);
        this.add(this.\u01c0);
        (this.\u01c7 = new Panel2()).setLayout(null);
        this.\u01c7.setBounds(this.\u01c8, this.\u01c9, this.\u01ca, this.\u01cb);
        this.\u01c7.setBackground(this.\u01cc);
        this.\u01c7.setForeground(this.\u01e7);
        this.\u01c7.setVisible(true);
        this.\u01c0.add(this.\u01c7);
        (this.\u01d4 = new ImageHyperLinkPanel(\u00e8, image, image, "http://www.nytimes.com/ref/crosswords/setpuzzle.html#howtoplay", "")).setLayout(null);
        this.\u01d4.setBounds(4, 272, 250, 25);
        this.\u01d4.setBackground(this.\u01d7);
        this.\u01d4.setForeground(this.\u01d8);
        this.\u01d4.setVisible(true);
        this.\u01d4.setImagePosition(0, 2);
        this.\u01d4.setImage(image);
        this.\u01c0.add(this.\u01d4);
        \u00e8.\u03a4.setBounds(280, 272, 114, 25);
        this.\u01c0.add(\u00e8.\u03a4);
        (this.\u01d9 = new Panel2()).setLayout(null);
        this.\u01d9.setBounds(this.\u01da, this.\u01db, this.\u01dc, this.\u01dd);
        this.\u01d9.setBackground(this.\u01de);
        this.\u01d9.setForeground(this.\u01df);
        this.\u01d9.setVisible(true);
        this.\u01c7.add(this.\u01d9);
        (this.\u01e0 = new Panel2()).setLayout(null);
        this.\u01e0.setBounds(this.\u01e1, this.\u01e2, this.\u01e3, this.\u01e4);
        this.\u01e0.setBackground(this.\u01e5);
        this.\u01e0.setForeground(this.\u01e6);
        this.\u01e0.setVisible(true);
        this.\u01c0.add(this.\u01e0);
        (this.\u01e9 = new Panel2()).setLayout(null);
        this.\u01e9.setBounds(this.\u01ea, this.\u01eb, this.\u01ec, this.\u01ed);
        this.\u01e9.setBackground(this.\u01ee);
        this.\u01e9.setForeground(this.\u01f1);
        this.\u01e9.setVisible(true);
        this.\u01e0.add(this.\u01e9);
        (this.\u01f3 = new Panel2()).setLayout(null);
        this.\u01f3.setBounds(this.\u01f4, this.\u01f5, this.\u01fa, this.\u01fb);
        this.\u01f3.setBackground(this.\u01fc);
        this.\u01f3.setForeground(this.\u01fd);
        this.\u01f3.setVisible(false);
        this.\u01e0.add(this.\u01f3);
        (this.\u020c = new Label("PRESS EXIT TO LEAVE ROOM!")).setBounds(0, 0, this.\u01ec, 32);
        this.\u020c.setFont(new Font("Arial", 1, 13));
        this.\u020c.setAlignment(1);
        this.\u01f3.add(this.\u020c);
        (this.\u020b = new Label("TRY TO FIND A VALID SET")).setBounds(0, 0, this.\u01ec, 32);
        this.\u020b.setFont(new Font("Arial", 1, 13));
        this.\u020b.setAlignment(1);
        this.\u01e9.add(this.\u020b);
        this.\u01bf.setBounds(this.\u01ec / 2 - this.\u01ba / 2, 75, this.\u01ba, this.\u01bb);
        this.\u01bf.setBackground(this.\u01bc);
        this.\u01bf.setForeground(this.\u01bd);
        this.\u01bf.setName(this.\u01be);
        (this.\u01ff = new Panel2()).setLayout(null);
        this.\u01ff.setBounds(this.\u0200, this.\u0201, this.\u0202, this.\u0203);
        this.\u01ff.setBackground(this.\u0204);
        this.\u01ff.setForeground(this.\u0205);
        this.\u01ff.setVisible(true);
        this.\u01c0.add(this.\u01ff);
        (this.\u0207 = new Label("SETS FOUND")).setFont(new Font("Arial", 1, 11));
        this.\u0207.setBounds(0, 0, this.\u0202, this.\u0208);
        this.\u0207.setBackground(this.\u0209);
        this.\u0207.setForeground(this.\u020a);
        this.\u01ff.add(this.\u0207);
        for (int i = 0; i < 24; ++i) {
            (this.\u01aa[i] = new Panel2()).setLayout(null);
            this.\u01aa[i].setBackground(this.\u01ab);
            this.\u01aa[i].setBounds(i % 3 * 62, 26 + i / 3 * 46, 62, 46);
            this.\u01aa[i].show();
            this.\u01ff.add(this.\u01aa[i]);
        }
        for (int j = 0; j < 24; ++j) {
            (this.\u01ac[j] = new ImagePanel()).setBackground(this.\u01ad);
            this.\u01ac[j].setBounds(2, 3, 58, 38);
            this.\u01ac[j].setBorderColor(new Color(204, 204, 204));
            this.\u01ac[j].showBorder(true);
            this.\u01ac[j].setBorderWidth(2);
            this.\u01aa[j].add(this.\u01ac[j]);
        }
        for (int k = 0; k < 12; ++k) {
            this.\u01a6[k] = 0;
        }
        for (int l = 0; l < 12; ++l) {
            (this.\u01a8[l] = new CardImagePanel(this, l)).setBounds(3, 3, 91, 59);
            this.\u01a8[l].setParent(this);
            this.\u01a8[l].setBackground(this.\u01a9);
            this.\u01a8[l].setMouseDefaultBorderColor(color);
            this.\u01a8[l].setMouseEnterBorderColor(new Color(0, 66, 118));
            this.\u01a8[l].setMousePressedBorderColor(Color.red);
            this.\u01a8[l].setBorderColor(new Color(204, 204, 204));
            this.\u01a8[l].showBorder(true);
        }
        this.\u01ae = new Panel2[12];
        for (int n5 = 0; n5 < 12; ++n5) {
            (this.\u01ae[n5] = new Panel2()).setLayout(null);
            this.\u01ae[n5].setBackground(this.\u01af);
            this.\u01ae[n5].setBorder(true);
            this.\u01ae[n5].setBorderColor(color);
            this.\u01ae[n5].add(this.\u01a8[n5]);
            this.\u01d9.add(this.\u01ae[n5]);
        }
        for (int n6 = 0; n6 < 4; ++n6) {
            this.\u01ae[n6].setBounds(n6 * 100, 0, 97, 65);
        }
        for (int n7 = 0; n7 < 4; ++n7) {
            this.\u01ae[n7 + 4].setBounds(n7 * 100, 68, 97, 65);
        }
        for (int n8 = 0; n8 < 4; ++n8) {
            this.\u01ae[n8 + 8].setBounds(n8 * 100, 136, 97, 65);
        }
        for (int n9 = 0; n9 < 12; ++n9) {}
        (this.\u01cd = new Panel2()).setLayout(null);
        this.\u01cd.setBounds(this.\u01ce, this.\u01cf, this.\u01d0, this.\u01d1);
        this.\u01cd.setBackground(this.\u01d2);
        this.\u01cd.setForeground(this.\u01d3);
        this.\u01cd.hide();
        this.add(this.\u01cd);
        this.\u01b0 = \u01b0;
        this.\u01b1 = \u028a;
        this.\u01a4 = color;
        this.\u0267 = this.\u019b(this.\u0268);
        this.repaint();
    }
    
    private void \u019a(final int n, final int n2) {
        switch (n) {
            case 12: {
                for (int i = 0; i < 4; ++i) {
                    this.\u01ae[i].setBounds(i * 100, 0, 97, 65);
                    this.\u01ae[i].show();
                }
                for (int j = 0; j < 4; ++j) {
                    this.\u01ae[j + 4].setBounds(j * 100, 68, 97, 65);
                    this.\u01ae[j + 4].show();
                }
                for (int k = 0; k < 4; ++k) {
                    this.\u01ae[k + 8].setBounds(k * 100, 136, 97, 65);
                    this.\u01ae[k + 8].show();
                }
                break;
            }
            case 9: {
                for (int l = 0; l < 3; ++l) {
                    this.\u01ae[l].setBounds(45 + l * 110, 0, 97, 65);
                    this.\u01ae[l].show();
                }
                for (int n3 = 0; n3 < 3; ++n3) {
                    this.\u01ae[n3 + 3].setBounds(45 + n3 * 110, 68, 97, 65);
                    this.\u01ae[n3 + 3].show();
                }
                for (int n4 = 0; n4 < 3; ++n4) {
                    this.\u01ae[n4 + 6].setBounds(45 + n4 * 110, 136, 97, 65);
                    this.\u01ae[n4 + 6].show();
                }
                for (int n5 = 0; n5 < 3; ++n5) {
                    this.\u01ae[n5 + 9].hide();
                }
                break;
            }
        }
        for (int n6 = 0; n6 < n2 * 3; ++n6) {
            this.\u01aa[n6].show();
        }
        for (int n7 = n2 * 3; n7 < 24; ++n7) {
            this.\u01aa[n7].hide();
        }
    }
    
    private int \u019b(final String s) {
        if (s != null) {
            try {
                return Integer.parseInt(s);
            }
            catch (Exception ex) {
                return 0;
            }
        }
        return 0;
    }
    
    public boolean setPuzzle(String tagValue) {
        this.\u020b.setText("Try to find a valid set.");
        this.\u0269 = 0;
        this.\u0257 = 1;
        tagValue = this.getTagValue(tagValue, "<DATA>");
        if (tagValue.length() == 0) {
            return false;
        }
        final String tagValue2 = this.getTagValue(tagValue, "<NAME>");
        if (tagValue2.length() == 0) {
            return false;
        }
        this.\u01b3 = tagValue2;
        if (this.getTagValue(tagValue, "<TITLE>").length() == 0) {
            return false;
        }
        String s = this.getTagValue(tagValue, "<STARTGRID>");
        if (s.length() == 0) {
            return false;
        }
        this.solutionList.solutionList.removeAllElements();
        this.\u01a5.solutionList.removeAllElements();
        String \u026e = this.getTagValue(tagValue, "<SOLUTIONGRID>");
        this.\u00c8.\u026e = \u026e;
        if (\u026e.length() == 0) {
            return false;
        }
        int n = \u026e.indexOf("(");
        int n2 = \u026e.indexOf(")");
        this.\u019f = 0;
        while (n >= 0 && n2 >= 0) {
            final String substring = \u026e.substring(n + 1, n2);
            final int int1 = Integer.parseInt(substring.substring(0, substring.indexOf(",")).trim());
            final String trim = substring.substring(substring.indexOf(",") + 1).trim();
            final int int2 = Integer.parseInt(trim.substring(0, trim.indexOf(",")).trim());
            final int int3 = Integer.parseInt(trim.substring(trim.indexOf(",") + 1).trim());
            \u026e = \u026e.substring(n2 + 1);
            n = \u026e.indexOf("(");
            n2 = \u026e.indexOf(")");
            this.solutionList.solutionList.addElement(new SetSolution(int1, int2, int3));
            ++this.\u019f;
        }
        int \u019e = 0;
        for (int i = s.indexOf(","); i > 0; i = s.indexOf(","), ++\u019e) {
            this.\u01a6[\u019e] = Integer.parseInt(s.substring(0, i));
            this.\u01a7[\u019e] = this.\u01a6[\u019e];
            s = s.substring(s.indexOf(",") + 1);
            this.\u01a8[\u019e].setImage(this.\u0251[this.\u01a6[\u019e]], 91, 59);
        }
        this.\u019e = \u019e;
        this.clearSolutionList();
        this.\u019a(this.\u019e, this.\u019f);
        this.resetCardPanel();
        return true;
    }
    
    public void clearSolutionList() {
        this.\u020b.setText("Try to find a valid set.");
        for (int i = 0; i < this.solutionList.solutionList.size() * 3; ++i) {
            this.\u01aa[i].setBackground(this.\u01a4);
            this.\u01ac[i].setBorderColor(new Color(204, 204, 204));
            this.\u01ac[i].setImage(null);
            this.\u01ac[i].repaint();
            this.\u01ac[i].show();
            this.\u01ff.repaint();
        }
        this.\u01a5.solutionList.removeAllElements();
        this.\u0269 = 0;
        this.repaint();
    }
    
    public int getTagValueInt(final String s, final String s2, final String s3) {
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
    
    public void reset() {
        if (this.\u0213 != null) {
            this.buildOffscreenBufferImage();
            this.\u0215.drawImage(this.\u0214, 0, 0, this);
        }
        this.repaint();
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
    
    public void buildOffscreenBufferImage() {
        switch (this.\u0257) {
            case 1: {
                this.drawBoardSituation();
            }
            case 3: {
                this.drawBoardSituation();
            }
            case 2: {
                this.displayHelp();
            }
            default: {}
        }
    }
    
    public void drawBoardSituation() {
        this.\u0216.setColor(this.\u01a4);
        for (int i = 0; i < 4; ++i) {
            this.\u0216.drawImage(this.\u0251[this.\u01a6[i]], i * 100, 0, 91, 59, this);
        }
        for (int j = 0; j < 4; ++j) {
            this.\u0216.drawImage(this.\u0251[this.\u01a6[j + 4]], j * 100, 70, 91, 59, this);
        }
        for (int k = 0; k < 4; ++k) {
            this.\u0216.drawImage(this.\u0251[this.\u01a6[k + 8]], k * 100, 140, 91, 59, this);
        }
    }
    
    public void displayHelp() {
        this.drawBoardSituation();
        this.\u0216.setColor(new Color(204, 255, 204));
        this.\u0216.fillRect(20 + this.\u0265, 20 + this.\u0266, this.\u0211 - 40, this.\u0212 - 40);
        this.\u0216.setColor(Color.black);
        this.\u0216.drawRect(20 + this.\u0265, 20 + this.\u0266, this.\u0211 - 40, this.\u0212 - 40);
        this.\u0216.drawString("HELP", 40 + this.\u0265, 40 + this.\u0266);
        this.\u0216.setColor(new Color(204, 255, 255));
        this.\u0216.fillRect(this.\u0252.x, this.\u0252.y, this.\u0252.width, this.\u0252.height);
        this.\u0216.setColor(Color.black);
        this.\u0216.drawRect(this.\u0252.x, this.\u0252.y, this.\u0252.width, this.\u0252.height);
        this.\u0216.drawString("OK", this.\u0252.x + 15, this.\u0252.y + 15);
    }
    
    public boolean limitReached() {
        int n = 0;
        for (int i = 0; i < this.\u019e; ++i) {
            if (this.\u01a8[i].getMousePressed() && ++n == 3) {
                return true;
            }
        }
        return false;
    }
    
    public void resetCardPanel() {
        int n = 0;
        final int[] array = new int[3];
        for (int i = 0; i < this.\u019e; ++i) {
            if (this.\u01a8[i].getMousePressed()) {
                array[n] = i + 1;
                ++n;
            }
        }
        if (n == 3) {
            for (int j = 0; j < this.\u019e; ++j) {
                this.\u01a8[j].setMousePressed(false);
            }
        }
    }
    
    public void resetChecked() {
        for (int i = 0; i < this.\u019e; ++i) {
            this.\u01a8[i].setMousePressed(false);
        }
    }
    
    public boolean checkSolution() {
        int n = 0;
        final int[] array = new int[3];
        for (int i = 0; i < this.\u019e; ++i) {
            if (this.\u01a8[i].getMousePressed()) {
                array[n] = i + 1;
                ++n;
            }
        }
        if (n == 3) {
            if (this.solutionList.isSolution(array[0], array[1], array[2]) && !this.\u01a5.isSolution(array[0], array[1], array[2])) {
                final int n2 = this.\u0269 * 3;
                final Color black = Color.black;
                Color background;
                if (this.\u00c8.displayTogetherPanel && this.\u00c8.\u0139.inRoom()) {
                    background = this.\u00c8.getUserColor(this.\u00c8.\u026b);
                }
                else {
                    background = this.\u01a4;
                }
                this.\u01ac[n2].setImage(this.\u0251[this.\u01a6[array[0] - 1]], 58, 38);
                this.\u01aa[n2].setBackground(background);
                this.\u01ac[n2 + 1].setImage(this.\u0251[this.\u01a6[array[1] - 1]], 58, 38);
                this.\u01aa[n2 + 1].setBackground(background);
                this.\u01ac[n2 + 2].setImage(this.\u0251[this.\u01a6[array[2] - 1]], 58, 38);
                this.\u01aa[n2 + 2].setBackground(background);
                ++this.\u0269;
                this.\u01a5.solutionList.addElement(new SetSolution(array[0], array[1], array[2]));
                this.\u00c8.processSetFound(array[0], array[1], array[2]);
                if (this.\u01a5.solutionList.size() != this.solutionList.solutionList.size()) {
                    this.\u020b.setText("Correct! You have found a set.");
                    this.\u020b.setBackground(this.\u01f0);
                }
                else {
                    this.allSetsFound();
                }
                for (int j = 0; j < this.\u019e; ++j) {
                    this.\u01a8[j].setMousePressed(false);
                }
            }
            else {
                this.\u020b.setBackground(this.\u01ef);
                if (this.\u01a5.isSolution(array[0], array[1], array[2])) {
                    this.\u020b.setText("You already found this set.");
                }
                else {
                    this.\u020b.setText(this.\u019d(array[0] - 1, array[1] - 1, array[2] - 1));
                }
            }
        }
        else {
            this.\u020b.setBackground(this.\u01ee);
            this.\u020b.setText("Try to find a valid set.");
        }
        return true;
    }
    
    private int \u019c(final int n) {
        int n2 = this.\u01a7[n];
        if (n2 < 1 || n2 > 81) {
            final String s = null;
            System.out.println("gn = [" + n2 + "]");
            throw new Error(s);
        }
        return --n2;
    }
    
    private String \u019d(final int n, final int n2, final int n3) {
        final int[] array = { this.\u019c(n), this.\u019c(n2), this.\u019c(n3) };
        this.\u019a = SetPanel$FEATURES.DEFAULT_FEATURES;
        final int[][] array2 = new int[3][this.\u019a.length];
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 4; ++j) {
                array2[i][j] = (int)(array[i] % Math.pow(3.0, j + 1) / Math.pow(3.0, j));
            }
        }
        for (int k = 0; k < 4; ++k) {
            if ((array2[0][k] + array2[1][k] + array2[2][k]) % 3 != 0) {
                String s = "NONE";
                if (array2[0][k] == array2[1][k]) {
                    s = this.\u019e(k, array2[0][k]);
                }
                if (array2[1][k] == array2[2][k]) {
                    s = this.\u019e(k, array2[1][k]);
                }
                if (array2[2][k] == array2[0][k]) {
                    s = this.\u019e(k, array2[2][k]);
                }
                return SetPanel$FEATURES.DEFAULT_FEATURES2[k] + " are incorrect, two are " + s + " and one is not!";
            }
        }
        return "ERROR";
    }
    
    private String \u019e(final int n, final int n2) {
        switch (n) {
            case 0: {
                return SetPanel$FEATURES.NUMBER.getFeatureVariation(n2 + 1);
            }
            case 1: {
                return SetPanel$FEATURES.COLOR.getFeatureVariation(n2 + 1);
            }
            case 2: {
                return SetPanel$FEATURES.SYMBOL.getFeatureVariation(n2 + 1);
            }
            case 3: {
                return SetPanel$FEATURES.SHADING.getFeatureVariation(n2 + 1);
            }
            default: {
                return "NONE";
            }
        }
    }
    
    public void setSolution(final int n, final int n2, final int n3) {
        if (this.solutionList.isSolution(n, n2, n3) && !this.\u01a5.isSolution(n, n2, n3)) {
            final int n4 = this.\u0269 * 3;
            this.\u01ac[n4].setImage(this.\u0251[this.\u01a6[n - 1]], 58, 38);
            this.\u01ac[n4 + 1].setImage(this.\u0251[this.\u01a6[n2 - 1]], 58, 38);
            this.\u01ac[n4 + 2].setImage(this.\u0251[this.\u01a6[n3 - 1]], 58, 38);
            ++this.\u0269;
            this.\u01a5.solutionList.addElement(new SetSolution(n, n2, n3));
            if (this.\u01a5.solutionList.size() == this.solutionList.solutionList.size()) {
                this.allSetsFound();
            }
        }
    }
    
    public void setSolution(final int n, final int n2, final int n3, final Color background) {
        if (this.solutionList.isSolution(n, n2, n3) && !this.\u01a5.isSolution(n, n2, n3)) {
            final int n4 = this.\u0269 * 3;
            this.\u01ac[n4].setImage(this.\u0251[this.\u01a6[n - 1]], 58, 38);
            this.\u01aa[n4].setBackground(background);
            this.\u01ac[n4 + 1].setImage(this.\u0251[this.\u01a6[n2 - 1]], 58, 38);
            this.\u01aa[n4 + 1].setBackground(background);
            this.\u01ac[n4 + 2].setImage(this.\u0251[this.\u01a6[n3 - 1]], 58, 38);
            this.\u01aa[n4 + 2].setBackground(background);
            ++this.\u0269;
            this.\u01a5.solutionList.addElement(new SetSolution(n, n2, n3));
            if (this.\u01a5.solutionList.size() == this.solutionList.solutionList.size()) {
                this.allSetsFound();
            }
            this.repaint();
        }
    }
    
    public void allSetsFound() {
        this.\u020b.setText("Congratulations! You've found all sets.");
        this.\u020b.setBackground(this.\u01f0);
        if (this.\u00c8.\u0139.inRoom()) {
            this.\u01f3.setVisible(true);
            this.\u020c.setText("PRESS NEXT FOR NEXT PUZZLE!");
            this.\u020c.setBackground(new Color(143, 188, 143));
            this.\u00c8.showNextPuzzleButton();
        }
    }
    
    public void updatePuzzle(final String s) {
        final int \u019b = this.\u019b(this.getTagValue(s, "<X>"));
        final int \u019b2 = this.\u019b(this.getTagValue(s, "<Y>"));
        final boolean b = true;
        this.\u0217[\u019b][\u019b2] = (b ? 1 : 0);
        this.\u0250[\u019b][\u019b2] = (b ? 1 : 0);
    }
    
    public void updateChat(final String s) {
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public SetPanel(final int n, final int n2, final int n3, final int n4) {
        this.\u019b = new int[4];
        this.solutionList = new SetSolutionList();
        this.\u01a5 = new SetSolutionList();
        this.\u01a6 = new int[12];
        this.\u01a7 = new int[13];
        this.\u01a8 = new CardImagePanel[12];
        this.\u01a9 = this.\u01a4;
        this.\u01aa = new Panel2[24];
        this.\u01ab = this.\u01a4;
        this.\u01ac = new ImagePanel[24];
        this.\u01ad = new Color(244, 244, 244);
        this.\u01af = this.\u01a4;
        this.\u01b2 = false;
        this.\u01b4 = false;
        this.\u01b5 = "";
        this.\u01b6 = 1;
        this.\u01b7 = 1;
        this.\u01b8 = 70;
        this.\u01b9 = 180;
        this.\u01ba = 50;
        this.\u01bb = 20;
        this.\u01bc = Color.white;
        this.\u01bd = Color.black;
        this.\u01be = "OK";
        this.\u01bf = new ImageButton(this.\u01be);
        this.\u01c3 = 397;
        this.\u01c4 = 227;
        this.\u01c5 = this.\u01a4;
        this.\u01c6 = Color.black;
        this.\u01c8 = 4;
        this.\u01c9 = 2;
        this.\u01ca = 397;
        this.\u01cb = 227;
        this.\u01cc = this.\u01a4;
        this.\u01ce = 4;
        this.\u01cf = 2;
        this.\u01d0 = 397;
        this.\u01d1 = 227;
        this.\u01d2 = this.\u01a4;
        this.\u01d3 = Color.black;
        this.\u01d5 = 397;
        this.\u01d6 = 26;
        this.\u01d7 = this.\u01a4;
        this.\u01d8 = Color.white;
        this.\u01db = 26;
        this.\u01dc = 397;
        this.\u01dd = 207;
        this.\u01de = this.\u01a4;
        this.\u01df = Color.white;
        this.\u01e1 = 4;
        this.\u01e2 = 237;
        this.\u01e3 = 397;
        this.\u01e4 = 32;
        this.\u01e5 = Color.yellow;
        this.\u01e6 = Color.white;
        this.\u01e7 = Color.white;
        this.\u01e8 = "main Panel";
        this.\u01ec = 397;
        this.\u01ed = 32;
        this.\u01ee = new Color(230, 239, 248);
        this.\u01ef = new Color(255, 204, 204);
        this.\u01f0 = new Color(201, 249, 197);
        this.\u01f1 = Color.black;
        this.\u01f2 = "Message Panel";
        this.\u01f5 = 25;
        this.\u01fa = 442;
        this.\u01fb = 25;
        this.\u01fc = new Color(100, 149, 237);
        this.\u01fd = Color.white;
        this.\u01fe = "Message Panel";
        this.\u0200 = 407;
        this.\u0201 = 2;
        this.\u0202 = 186;
        this.\u0203 = 300;
        this.\u0204 = this.\u01a4;
        this.\u0205 = Color.white;
        this.\u0206 = "found Panel";
        this.\u0208 = 26;
        this.\u0209 = this.\u01a4;
        this.\u020a = new Color(102, 102, 102);
        this.\u020d = false;
        this.\u020e = false;
        this.\u020f = new Move[144];
        this.\u0210 = new Move[144];
        this.hideString = false;
        this.showCandidates = false;
        this.\u0253 = false;
        this.\u0254 = false;
        this.reshape(n, n2, n3, n4);
    }
    
    public void setString(final String \u00e3) {
        this.\u00c3 = \u00e3;
    }
    
    public void setImage(final Image image, final Image image2) {
        this.repaint();
    }
    
    public void hideString() {
        this.hideString = true;
    }
    
    public String getCurrentSituation() {
        final StringBuffer sb = new StringBuffer();
        sb.append("<NAME>" + this.\u01b3 + "</NAME>");
        sb.append("<BOARD>");
        for (int i = 0; i < this.\u0255; ++i) {
            for (int j = 0; j < this.\u0256; ++j) {
                sb.append(String.valueOf(this.\u0217[i][j]) + " ");
            }
        }
        sb.append("</BOARD>");
        return sb.toString();
    }
    
    public String getCurrentSituationSubmit() {
        final StringBuffer sb = new StringBuffer();
        sb.append("<NAME>" + this.\u01b3 + "</NAME>");
        sb.append("<BOARD>");
        for (int i = 0; i < this.\u0255; ++i) {
            for (int j = 0; j < this.\u0256; ++j) {
                sb.append(String.valueOf(this.\u0217[j][i]) + " ");
            }
        }
        sb.append("</BOARD>");
        return sb.toString();
    }
    
    public String getCurrentEmptySituation() {
        final StringBuffer sb = new StringBuffer();
        sb.append("<NAME>" + this.\u01b3 + "</NAME>");
        sb.append("<BOARD>");
        for (int i = 0; i < this.\u0255; ++i) {
            for (int j = 0; j < this.\u0256; ++j) {
                if (this.\u0250[i][j] > 0) {
                    sb.append(String.valueOf(this.\u0217[i][j]) + " ");
                }
                else {
                    sb.append("0 ");
                }
            }
        }
        sb.append("</BOARD>");
        return sb.toString();
    }
    
    public void setCurrentSituation(final String s) {
        this.getTagValue(s, "<NAME>");
        String s2 = this.getTagValue(s, "<BOARD>");
        for (int i = 0; i < this.\u0255; ++i) {
            for (int j = 0; j < this.\u0256; ++j) {
                final int n = 0;
                final int index = s2.indexOf(" ");
                this.\u0217[i][j] = Integer.parseInt(s2.substring(n, index));
                s2 = s2.substring(index + 1);
            }
        }
        this.buildOffscreenBufferImage();
        this.\u0215.drawImage(this.\u0214, 0, 0, this);
        this.repaint();
    }
}
