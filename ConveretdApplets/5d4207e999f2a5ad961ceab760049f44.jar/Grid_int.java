import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.awt.Graphics;
import java.io.InputStream;
import java.net.URLEncoder;
import java.awt.Label;
import java.awt.Component;
import java.awt.LayoutManager;
import java.io.IOException;
import java.io.DataInputStream;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Button;
import java.net.URL;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Grid_int extends Applet implements ActionListener, KeyListener, MouseListener, MouseMotionListener
{
    CGrid_int CCGrid;
    private URLLab_int a;
    URLLab_int ka;
    private static final String b = "%SUBMIT%";
    private static final String c = "%MARKED%";
    private static final String d = "%PROGRESS%";
    private static final String TimeStr = "%TIME%";
    private static final String CharSetHolder = "%CHARSET%";
    static final String e = "SansSerif";
    private static final boolean XYZ = false;
    private static final boolean NoURL = false;
    String Err;
    String SaveURL;
    String SubmitURL;
    private URL DataFile;
    private static final int L = 2;
    private int h;
    private Button[] G;
    private URLLab_int K;
    private long BoxTime;
    private int copylen;
    int AA;
    ClueView_int[] OO;
    boolean DD;
    private static final String k = " Software © 2006 ";
    private static final boolean URLLeft = true;
    String t;
    String u;
    String OutputCharset;
    FontMetrics FM;
    boolean OneLetWds;
    private FontMetrics CodedFM;
    private Font AlpFont;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int CheatCount;
    private int MaxCheats;
    boolean V7;
    int U;
    int V;
    int W;
    Color X;
    Color ScrollColor;
    boolean ScrollGrad;
    Font Y;
    public int NumFilled;
    int Z;
    int R;
    String SS;
    String Q;
    URLLab_int P;
    boolean jk;
    boolean O;
    int[] PP;
    String[] QQ;
    String[] RR;
    boolean HasURLs;
    int IB;
    int IC;
    
    public Grid_int() {
        this.ka = null;
        this.h = 0;
        this.K = null;
        this.t = "Cp1252";
        this.u = "Cp1252";
        this.OutputCharset = "Cp1252";
        this.OneLetWds = false;
        this.AlpFont = null;
        this.l = 190;
        this.m = 7;
        this.n = 11;
        this.CheatCount = 0;
        this.MaxCheats = 1000000;
        this.V7 = false;
        this.X = Color.black;
        this.ScrollColor = Color.black;
        this.Y = null;
        this.P = null;
        this.jk = false;
        this.O = false;
        this.HasURLs = false;
    }
    
    String E(final DataInputStream dataInputStream) throws IOException {
        final byte[] array = new byte[dataInputStream.readByte() & 0xFF];
        dataInputStream.readFully(array);
        return new String(array, this.u);
    }
    
    String IA(final DataInputStream dataInputStream) throws IOException {
        final byte[] array = new byte[dataInputStream.readShort() & 0xFFFF];
        dataInputStream.readFully(array);
        return new String(array, this.u);
    }
    
    private String q(final int n) {
        if (n == 161) {
            return "Cp1253";
        }
        if (n == 162) {
            return "Cp1254";
        }
        if (n == 238) {
            return "Cp1250";
        }
        if (n == 204) {
            return "Cp1251";
        }
        if (n == 186) {
            return "Cp1257";
        }
        if (n == 129) {
            return "KSC5601";
        }
        if (n == 128) {
            return "SJIS";
        }
        if (n == 148) {
            return "GB2312";
        }
        if (n == 136) {
            return "Big5";
        }
        if (n == 177) {
            return "Cp1255";
        }
        if (n == 163) {
            return "Cp1258";
        }
        if (n == 222) {
            return "Cp874";
        }
        if (n == 255) {
            return "UTF8";
        }
        return "Cp1252";
    }
    
    public void init() {
        this.setLayout(null);
        int int1 = 0;
        this.G = new Button[this.m];
        this.setBackground(this.colParam("BACKCOLOR", Color.white));
        if (this.getBackground().getGreen() < 130 && this.getBackground().getRed() < 130 && this.getBackground().getBlue() < 130) {
            this.X = Color.white;
        }
        this.X = this.colParam("CLUECOLOR", this.X);
        this.ScrollColor = this.colParam("SCROLLCOLOR", Color.black);
        this.ScrollGrad = (this.getParameter("SCROLLGRAD") != null);
        this.Q = this.getParameter("FINISHEDURL");
        try {
            this.DataFile = new URL(this.getDocumentBase(), this.getParameter("DATAFILE"));
            final InputStream openStream = this.DataFile.openStream();
            try {
                final DataInputStream dataInputStream = new DataInputStream(openStream);
                final byte byte1 = dataInputStream.readByte();
                final byte byte2 = dataInputStream.readByte();
                this.U = (((byte2 & 0x1) != 0x0) ? 2 : 0);
                if ((byte2 & 0x10) != 0x0) {
                    this.t = this.q(dataInputStream.readByte() & 0xFF);
                    this.u = this.q(dataInputStream.readByte() & 0xFF);
                    final String parameter = this.getParameter("CHARSET");
                    if (parameter != null) {
                        this.u = parameter;
                    }
                }
                this.OutputCharset = this.getParameter("OUTPUTCHARSET");
                if (this.OutputCharset == null) {
                    this.OutputCharset = this.t;
                }
                this.DD = ((byte2 & 0x8) != 0x0);
                final String parameter2 = this.getParameter("COMPLETECORRECT");
                if (parameter2 != null) {
                    this.DD = (parameter2 == "1");
                }
                this.jk = ((byte2 & 0x20) != 0x0);
                final boolean b = (byte2 & 0x4) != 0x0;
                final int n = 25;
                int n2 = 55;
                final int n3 = 4;
                if ((byte2 & 0x40) != 0x0) {
                    n2 = 75;
                }
                this.O = ((byte2 & 0x80) != 0x0);
                final String parameter3 = this.getParameter("FRIENDLYSUBMIT");
                if (parameter3 != null) {
                    this.O = (parameter3 == "1");
                }
                this.W = ((byte1 == 0 || b) ? 0 : (n2 + n3 * 2));
                int n4 = 0;
                if (byte1 != 0) {
                    final String parameter4 = this.getParameter("MAXCHEATS");
                    if (parameter4 != null) {
                        this.MaxCheats = Integer.valueOf(parameter4);
                    }
                    final Font font = new Font("SansSerif", 0, 11);
                    for (int i = 0; i <= this.m - 1; ++i) {
                        if ((byte1 & 1 << i) != 0x0) {
                            (this.G[i] = new Button(this.E(dataInputStream))).setFont(font);
                            ++n4;
                        }
                    }
                }
                this.SubmitURL = this.getParameter("SUBMIT");
                this.SaveURL = this.getParameter("SAVE");
                this.SS = this.IA(dataInputStream);
                final byte byte3 = dataInputStream.readByte();
                byte b2;
                byte byte4;
                if ((byte3 & 0x40) != 0x0) {
                    b2 = (byte)(byte3 - 64);
                    byte4 = dataInputStream.readByte();
                }
                else {
                    b2 = byte3;
                    byte4 = 0;
                }
                this.OneLetWds = ((byte4 & 0x2) != 0x0);
                this.V7 = ((byte4 & 0x4) != 0x0);
                this.CCGrid = new CGrid_int();
                this.CCGrid.App = this;
                this.CCGrid.jm = ((byte2 & 0x2) != 0x0);
                this.CCGrid.jl = (this.U == 2);
                this.CCGrid.HD(dataInputStream);
                if (this.jk) {
                    this.QQ = new String[this.CCGrid.ab];
                    this.RR = new String[this.CCGrid.ac];
                    for (int j = 1; j <= this.CCGrid.ab; ++j) {
                        this.QQ[j - 1] = this.E(dataInputStream);
                    }
                    for (int k = 1; k <= this.CCGrid.ac; ++k) {
                        this.RR[k - 1] = this.E(dataInputStream);
                    }
                }
                this.CCGrid.HW(false);
                this.CCGrid.ad = this.CCGrid.GA;
                this.CCGrid.ae = this.CCGrid.AG;
                final int n5 = this.CCGrid.ae - (this.jk ? this.CCGrid.B : 0);
                final int n6 = this.jk ? this.CCGrid.B : 0;
                this.AA = dataInputStream.readByte();
                if (this.AA != 0) {
                    final Color colParam = this.colParam("CLUESELCOLOR", new Color(dataInputStream.readInt()));
                    byte byte5 = dataInputStream.readByte();
                    final byte byte6 = dataInputStream.readByte();
                    String s;
                    if ((byte5 & 0x80) == 0x0) {
                        s = "SansSerif";
                    }
                    else {
                        s = "TimesRoman";
                        byte5 &= 0x7F;
                    }
                    this.Y = new Font(s, 0, byte5);
                    this.R = byte5 + 2;
                    final byte byte7 = dataInputStream.readByte();
                    this.l = dataInputStream.readInt();
                    int1 = dataInputStream.readInt();
                    this.OO = new ClueView_int[this.AA];
                    for (int l = 0; l < this.AA; ++l) {
                        this.OO[l] = new ClueView_int();
                        this.OO[l].App = this;
                        this.OO[l].SelColor = colParam;
                        this.OO[l].JE = this.CCGrid;
                        this.OO[l].it = ((byte7 & 0x8) != 0x0);
                        this.OO[l].iu = ((byte7 & 0x1) != 0x0);
                        this.OO[l].ip = ((byte7 & 0x2) != 0x0);
                        this.OO[l].il = ((byte7 & 0x4) != 0x0);
                        this.OO[l].DoURLs = ((byte4 & 0x1) != 0x0);
                        this.OO[l].jc(dataInputStream);
                        if (b2 != 2) {
                            this.OO[l].setBounds(this.W + ((b2 == 0) ? (this.CCGrid.ad + int1) : 0), n6 + l * (n5 / 2 + int1 / 2) + this.R + 2, this.l, n5 / 2 - this.R - 2 - int1 / 2);
                        }
                        else {
                            this.OO[l].setBounds(this.W + (this.CCGrid.ad / 2 + int1) * l, this.CCGrid.ae + this.R + 2 + int1, this.CCGrid.ad / 2 - int1, this.l);
                        }
                        if (l == 0) {
                            this.OO[l].jc = true;
                        }
                        this.add(this.OO[l]);
                        this.OO[l].addMouseListener(this);
                        if (this.OO[l].DoURLs) {
                            this.OO[l].addMouseMotionListener(this);
                        }
                        this.OO[l].addKeyListener(this);
                        this.OO[l].je(this.OO[l].getGraphics());
                        Label label;
                        if ((byte6 & 0x4) != 0x0) {
                            label = new Label(this.OO[l].ik, 1);
                        }
                        else {
                            label = new Label(this.OO[l].ik);
                        }
                        label.setFont(new Font(s, byte6 & 0x3, this.R));
                        label.setForeground(this.X);
                        int ju = this.OO[l].JU;
                        int n7 = 0;
                        if ((byte6 & 0x4) == 0x0) {
                            n7 = this.OO[l].ir - 1;
                        }
                        final int n8 = this.OO[l].getSize().width - this.OO[l].JU - this.OO[l].iv - n7;
                        if (b2 != 2) {
                            if (b2 == 0) {
                                ju += this.CCGrid.ad + int1;
                            }
                            label.setBounds(this.W + ju + n7, n6 + l * (n5 / 2 + int1 / 2), n8, this.R);
                        }
                        else {
                            label.setBounds(this.W + ju + n7 + (this.CCGrid.ad / 2 + int1) * l, this.CCGrid.ae + int1, n8, this.R);
                        }
                        this.add(label);
                    }
                }
                else {
                    this.R = 14;
                }
                if (b2 == 1 && this.AA != 0) {
                    this.h = this.l + int1;
                }
                this.CCGrid.setBounds(this.W + this.h, 0, this.CCGrid.GA, this.CCGrid.AG);
                this.CCGrid.HK(this.CCGrid.ga.x, this.CCGrid.ga.y);
                this.a = new URLLab_int("crossword-compiler.com");
                final Font font2 = new Font("SansSerif", 0, this.n);
                final FontMetrics fontMetrics = this.a.getFontMetrics(font2);
                this.copylen = fontMetrics.stringWidth(" Software © 2006 ");
                final int copylen = this.copylen;
                if (this.getParameter("EDU") == null) {
                    this.a.IG("http://www.crossword-compiler.com/cgi-bin/applet.pl?" + URLEncoder.encode(this.getDocumentBase().toString()));
                }
                this.a.setFont(font2);
                this.a.BaseLine = this.n - fontMetrics.getDescent() + 2;
                this.a.URLLen = fontMetrics.stringWidth(this.a.IP);
                if (this.getParameter("TIMER") != null) {
                    this.ka = new URLLab_int();
                    Font font3 = font2;
                    FontMetrics fontMetrics2 = fontMetrics;
                    if (byte1 != 0) {
                        font3 = new Font("SansSerif", 1, 13);
                        fontMetrics2 = this.ka.getFontMetrics(font3);
                    }
                    this.ka.addMouseListener(this);
                    this.ka.setFont(font3);
                    this.ka.BaseLine = this.a.BaseLine + ((byte1 != 0) ? 2 : 0);
                    this.ka.setSize(Math.min(n2, fontMetrics2.stringWidth("10:00:00")), this.n + 2 + ((byte1 != 0) ? 4 : 0));
                    final String parameter5 = this.getParameter("STARTTIME");
                    try {
                        if (parameter5 != null) {
                            this.ka.setDoneTime(Integer.parseInt(parameter5));
                        }
                    }
                    catch (Exception ex) {}
                }
                final int n9 = copylen + this.a.URLLen;
                if (this.CCGrid.BA != null) {
                    if (this.ka != null) {
                        this.ka.setLocation(this.W + this.h, this.CCGrid.ae + 3 * this.CCGrid.FA / 2);
                    }
                    this.a.setBounds(this.copylen + this.W + this.h + (this.CCGrid.ad - n9) / 2, this.CCGrid.ae + 3 * this.CCGrid.FA / 2, this.a.URLLen, this.n + 2);
                }
                else if (b2 != 2 || this.AA == 0) {
                    final int urlLen = this.a.URLLen;
                    this.a.setBounds((b2 == 0 && b) ? this.copylen : Math.max(0, this.W + this.h + this.CCGrid.ad - urlLen), this.CCGrid.ae + 1, this.a.URLLen, this.n + 2);
                    if (this.ka != null) {
                        this.ka.setLocation((b2 == 0 && b) ? Math.max(0, this.W + this.CCGrid.ad - this.ka.getSize().width) : (this.h + this.W), this.CCGrid.ae + 1);
                        this.ka.Right = (b2 == 0 && b);
                    }
                }
                else {
                    this.a.setBounds(Math.max(0, this.W + this.CCGrid.ad - n9), this.CCGrid.ae + int1 + this.l + this.R, this.a.URLLen, this.n + 2);
                    if (this.ka != null) {
                        this.ka.setLocation(this.W, this.a.getBounds().y);
                    }
                }
                String s2 = "";
                int n10 = 0;
                try {
                    while (true) {
                        final String string = Integer.toString(n10);
                        String s3 = this.getParameter(s2 + "PICTURE" + string);
                        if (s3 == null && s2 == "") {
                            s2 = "S";
                            n10 = 0;
                            s3 = this.getParameter("SPICTURE0");
                        }
                        if (s3 == null) {
                            break;
                        }
                        final URLLab_int urlLab_int = new URLLab_int(this.getImage(this.getDocumentBase(), s3));
                        final String parameter6 = this.getParameter(s2 + "PICTURERECT" + string);
                        final int int2 = Integer.parseInt(parameter6.substring(0, 1));
                        urlLab_int.setBounds(Integer.parseInt(parameter6.substring(1, 5)) + (((int2 & 0x1) != 0x0) ? this.CCGrid.getBounds().x : 0), Integer.parseInt(parameter6.substring(5, 9)) + (((int2 & 0x1) != 0x0) ? this.CCGrid.getBounds().y : 0), Integer.parseInt(parameter6.substring(9, 13)), Integer.parseInt(parameter6.substring(13, 17)));
                        if ((int2 & 0x1) != 0x0) {
                            urlLab_int.getBounds().translate(this.CCGrid.getBounds().x, this.CCGrid.getBounds().y);
                        }
                        urlLab_int.IG(this.getParameter(s2 + "PICTUREURL" + string));
                        urlLab_int.URLFrame = this.getParameter(s2 + "PICTUREURLFRAME" + string);
                        if (!urlLab_int.getBounds().intersects(this.a.getBounds())) {
                            this.add(urlLab_int, ((int2 & 0x2) != 0x0) ? -1 : 0);
                            urlLab_int.addMouseListener(this);
                        }
                        ++n10;
                    }
                    final String parameter7 = this.getParameter("COMPLETIONPICTURE");
                    if (parameter7 != null) {
                        this.P = new URLLab_int(this.getImage(this.getDocumentBase(), parameter7));
                    }
                }
                catch (Exception ex2) {}
                if (this.getSize().height < this.a.getBounds().y + this.a.getBounds().height && System.getProperty("os.version").compareTo("4.2") > 0) {
                    this.Err = "Wrong size";
                    this.remove(this.CCGrid);
                    if (this.AA > 0) {
                        this.remove(this.OO[0]);
                    }
                }
                if (this.Err == null) {
                    this.add(this.a, 0);
                    this.a.addMouseListener(this);
                    this.add(this.CCGrid, -1);
                    this.CCGrid.addMouseListener(this);
                    this.CCGrid.addKeyListener(this);
                    this.addMouseListener(this);
                }
                this.o = this.a.getBounds().x + this.a.getBounds().width;
                this.p = this.a.getBounds().y + this.a.BaseLine;
                if (byte1 != 0) {
                    (this.PP = new int[this.m])[0] = 4;
                    this.PP[1] = 3;
                    this.PP[2] = 2;
                    this.PP[3] = 1;
                    this.PP[4] = 6;
                    this.PP[5] = 0;
                    this.PP[6] = 5;
                    if (b) {
                        final int max = Math.max(0, Math.min((this.CCGrid.ad + ((this.AA == 0) ? 0 : (this.l + int1)) - (n4 + 2) * n2) / n4, n2 * 2 / 5));
                        final int n11 = this.a.getBounds().y + this.a.getBounds().height + 10;
                        final int n12 = (this.CCGrid.ad + ((this.AA == 0) ? 0 : (this.l + int1)) - n4 * (n2 + max) + max) / 2;
                        int n13 = 0;
                        for (int n14 = 0; n14 <= this.m - 1; ++n14) {
                            final Button button = this.G[this.PP[n14]];
                            if (button != null) {
                                button.setBounds(n12 + n13 * (n2 + max), n11, n2, n);
                                this.add(button);
                                button.addActionListener(this);
                                button.setActionCommand(Integer.toString(n14));
                                ++n13;
                            }
                        }
                    }
                    else {
                        int n15 = 0;
                        for (int n16 = 0; n16 <= this.m - 1; ++n16) {
                            final Button button2 = this.G[this.PP[n16]];
                            if (button2 != null) {
                                button2.setBounds(n3, n6 + n15 * (n + 5), n2, n);
                                this.add(button2);
                                button2.addActionListener(this);
                                button2.setActionCommand(Integer.toString(n16));
                                ++n15;
                            }
                        }
                        if (this.ka != null) {
                            this.ka.setBounds(n3, n6 + n15 * (n + 5) + (n - this.n) / 2, n2, this.n + 2);
                            this.ka.Center = true;
                        }
                    }
                }
                if (this.ka != null) {
                    this.add(this.ka);
                }
                this.CCGrid.requestFocus();
                if (this.getParameter("SHOWSOL") != null) {
                    this.CCGrid.HT();
                }
            }
            finally {
                try {
                    openStream.close();
                }
                catch (Exception ex3) {}
            }
            if (this.ka != null && this.getParameter("TIMEFROMLOAD") != null) {
                this.ka.startTimer();
            }
        }
        catch (Exception ex4) {
            if (this.Err == null) {
                this.Err = "Error reading crossword file. Check " + this.getParameter("DATAFILE") + " is uploaded and readable.";
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.Err != null) {
            try {
                final byte[] array = { 67 };
                new String(array, this.u);
                new String(array, this.t);
            }
            catch (Exception ex) {
                this.Err = "Your browser does not support the required character set";
            }
            graphics.drawString(this.Err, this.W, graphics.getFontMetrics().getHeight());
        }
        else {
            this.ID(graphics);
            if (this.getBackground().getGreen() < 130 && this.getBackground().getRed() < 130 && this.getBackground().getBlue() < 130) {
                graphics.setColor(Color.white);
            }
            else {
                graphics.setColor(Color.black);
            }
            graphics.setFont(this.a.getFont());
            graphics.drawString(" Software © 2006 ", this.a.getBounds().x + -this.copylen, this.p);
        }
    }
    
    void ID(final Graphics graphics) {
        if (this.CCGrid.BA != null) {
            if (this.CCGrid.AE == 0) {
                this.IE(graphics);
            }
            graphics.setFont(this.AlpFont);
            int n = 0;
            for (int i = 0; i < this.CCGrid.BA.length(); ++i) {
                final char char1 = this.CCGrid.BA.charAt(i);
                if (this.CCGrid.ag) {
                    graphics.setColor((this.CCGrid.BD.indexOf(char1) == -1) ? this.getForeground() : Color.gray);
                }
                else {
                    graphics.setColor(this.getForeground());
                }
                graphics.drawString(String.valueOf(char1), n + this.IB, this.CCGrid.AG + 2 + this.CCGrid.FA);
                n += this.CodedFM.charWidth(char1) + 2;
            }
        }
    }
    
    void IE(final Graphics graphics) {
        graphics.setFont(this.CCGrid.AD);
        this.FM = graphics.getFontMetrics();
        if (this.CCGrid.ba) {
            this.CCGrid.AF = this.CCGrid.B - (this.CCGrid.B - this.FM.getAscent() + 1) / 2;
        }
        else {
            this.CCGrid.AF = this.CCGrid.B - (this.FM.getDescent() + 2) / 2;
        }
        this.CCGrid.CA = this.FM.getWidths();
        graphics.setFont(null);
        graphics.setFont(this.CCGrid.AC);
        this.CCGrid.AE = graphics.getFontMetrics().getAscent();
        if (this.CCGrid.DA == 3) {
            final CGrid_int ccGrid = this.CCGrid;
            ++ccGrid.AE;
        }
        if (this.CCGrid.BA != null) {
            int n = this.FM.stringWidth(this.CCGrid.BA) + (this.CCGrid.BA.length() - 1) * 2;
            if (n > this.getSize().width) {
                graphics.setFont(this.AlpFont = new Font("SansSerif", 1, this.CCGrid.EA + 1));
                this.CodedFM = graphics.getFontMetrics();
                n = this.CodedFM.stringWidth(this.CCGrid.BA) + (this.CCGrid.BA.length() - 1) * 2;
            }
            else {
                this.CodedFM = this.FM;
                this.AlpFont = this.CCGrid.AD;
            }
            this.IB = this.W + this.h + (this.CCGrid.GA - n) / 2;
            this.IC = this.IB + n;
        }
    }
    
    void IF(final String s, final String s2, final boolean b) {
        if (s != null && s.length() != 0) {
            final int index = s.indexOf("%SUBMIT%");
            String s3;
            if (index != -1) {
                s3 = s.substring(0, index) + this.GetSubmit() + s.substring(index + 8);
            }
            else {
                s3 = s;
            }
            final int index2 = s3.indexOf("%PROGRESS%");
            if (index2 != -1) {
                s3 = s3.substring(0, index2) + this.GetProgress() + s3.substring(index2 + 10);
            }
            final int index3 = s3.indexOf("%MARKED%");
            if (index3 != -1) {
                s3 = s3.substring(0, index3) + this.GetProgressLongMarks() + s3.substring(index3 + 8);
            }
            final int index4 = s3.indexOf("%CHARSET%");
            if (index4 != -1) {
                s3 = s3.substring(0, index4) + this.OutputCharset + s3.substring(index4 + 9);
            }
            final int index5 = s3.indexOf("%TIME%");
            if (index5 != -1 && this.ka != null) {
                s3 = s3.substring(0, index5) + Integer.toString(this.ka.ke) + s3.substring(index5 + 6);
            }
            String s4 = s2;
            if (s4 == null) {
                s4 = (b ? "_self" : "_blank");
            }
            try {
                this.getAppletContext().showDocument(new URL(this.getDocumentBase(), s3), s4);
            }
            catch (MalformedURLException ex) {}
            this.CCGrid.requestFocus();
        }
    }
    
    public void ShowCompletionPic() {
        try {
            if (this.P.IJ != null) {
                this.P.IJ.waitForAll();
            }
            final int width = this.P.IO.getWidth(this);
            final int height = this.P.IO.getHeight(this);
            this.P.setBounds((this.getBounds().width - width) / 2, (this.getBounds().height - height) / 2, width, height);
            this.P.IG(this.getParameter("COMPLETIONPICTUREURL"));
            this.P.URLFrame = this.getParameter("COMPLETIONPICTUREURLFRAME");
            this.P.addMouseListener(this);
            this.add(this.P, 0);
            this.P.repaint();
            this.K = this.P;
            this.BoxTime = new Date().getTime();
        }
        catch (Exception ex) {}
    }
    
    public void ShowCompletionMessage() {
        if (this.SS != null) {
            try {
                if (this.Y == null) {
                    this.Y = new Font("SansSerif", 0, 12);
                }
                this.K = new URLLab_int(this.SS, this.Y);
                final int width = this.K.getSize().width;
                final int height = this.K.getSize().height;
                this.K.setBounds((this.getSize().width - width) / 2, (this.getSize().height - height) / 2, width, height);
                this.K.addMouseListener(this);
                this.add(this.K, 0);
                this.K.repaint();
                this.BoxTime = new Date().getTime();
            }
            catch (Exception ex) {}
        }
    }
    
    public void SolutionClick() {
        this.CCGrid.HT();
        if (this.ka != null) {
            this.ka.kd = true;
        }
        this.CCGrid.requestFocus();
    }
    
    public void CheckClick() {
        this.CCGrid.check();
        this.CCGrid.requestFocus();
    }
    
    public void RevertClick() {
        this.CCGrid.HW(true);
        if (this.ka != null) {
            this.ka.kd = false;
        }
        this.CCGrid.requestFocus();
        if (this.CCGrid.ag) {
            this.CCGrid.BD = "";
            this.ID(this.getGraphics());
        }
    }
    
    private boolean r(final String s) {
        return s.indexOf("%SUBMIT%") == -1 && s.indexOf("%PROGRESS%") == -1 && s.indexOf("%MARKED%") == -1;
    }
    
    public void SubmitClick() {
        if (this.r(this.SubmitURL)) {
            this.SubmitURL += this.GetSubmit();
        }
        this.IF(this.SubmitURL, this.getParameter("SUBMITFRAME"), true);
    }
    
    public void SaveClick() {
        if (this.r(this.SaveURL)) {
            this.SaveURL += this.GetProgress();
        }
        this.IF(this.SaveURL, this.getParameter("SAVEFRAME"), true);
    }
    
    public void RevealClick() {
        ++this.CheatCount;
        if (this.CheatCount <= this.MaxCheats) {
            this.CCGrid.HR();
        }
        this.CCGrid.requestFocus();
        this.CCGrid.HN();
    }
    
    public void RevealLetterClick() {
        ++this.CheatCount;
        if (this.CheatCount <= this.MaxCheats) {
            this.CCGrid.HS();
        }
        this.CCGrid.requestFocus();
        this.CCGrid.HN();
    }
    
    public char GetCurrentSolutionLetter() {
        return this.CCGrid.jn[this.CCGrid.ga.x][this.CCGrid.ga.y].IT;
    }
    
    public String GetSubmit() {
        return this.CCGrid.HX(this.O ? 1 : 0);
    }
    
    public String GetProgress() {
        return this.CCGrid.HX(0);
    }
    
    public String GetProgressLong() {
        return this.CCGrid.HX(1);
    }
    
    public String GetProgressLongMarks() {
        return this.CCGrid.HX(2);
    }
    
    public int GetTime() {
        if (this.ka != null) {
            return this.ka.ke;
        }
        return -1;
    }
    
    public Color colParam(final String s, final Color color) {
        final String parameter = this.getParameter(s);
        if (parameter != null) {
            return new Color(Integer.parseInt(parameter.substring(1, 3), 16), Integer.parseInt(parameter.substring(3, 5), 16), Integer.parseInt(parameter.substring(5, 7), 16));
        }
        return color;
    }
    
    private void s() {
        if (this.K != null && new Date().getTime() - this.BoxTime > 2000L) {
            this.remove(this.K);
            this.K = null;
        }
    }
    
    boolean TT(final MouseEvent mouseEvent) {
        if (this.CCGrid.BA != null) {
            final int x = mouseEvent.getX();
            final int y = mouseEvent.getY();
            final int n = this.CCGrid.AG + 2;
            if (y >= n && y <= n + this.CCGrid.FA && x >= this.IB && x <= this.IC) {
                char c = this.CCGrid.BA.charAt(0);
                int n2 = this.CodedFM.charWidth(c) + this.IB + 1;
                for (int i = 1; i <= this.CCGrid.BA.length(); ++i) {
                    if (x < n2) {
                        this.CCGrid.HY(c);
                        this.CCGrid.requestFocus();
                        return true;
                    }
                    c = this.CCGrid.BA.charAt(i);
                    n2 += this.CodedFM.charWidth(c) + 2;
                }
            }
        }
        return false;
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final Object source = mouseEvent.getSource();
        for (int i = 0; i < this.AA; ++i) {
            if (source == this.OO[i]) {
                this.OO[i].ClueURLMouseOver(mouseEvent);
            }
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        final Object source = mouseEvent.getSource();
        if (((URLLab_int)source).getClass().getName().equals("URLLab_int")) {
            ((URLLab_int)source).SetMouseOver(true);
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        final Object source = mouseEvent.getSource();
        if (((URLLab_int)source).getClass().getName().equals("URLLab_int")) {
            ((URLLab_int)source).SetMouseOver(false);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final Object source = mouseEvent.getSource();
        if (source == this) {
            this.TT(mouseEvent);
        }
        else if (source == this.CCGrid) {
            this.CCGrid.HO(mouseEvent);
        }
        else {
            for (int i = 0; i < this.AA; ++i) {
                if (source == this.OO[i]) {
                    this.OO[i].ji(mouseEvent);
                }
            }
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.s();
        final Object source = mouseEvent.getSource();
        if (((URLLab_int)source).getClass().getName().equals("URLLab_int")) {
            ((URLLab_int)source).IH();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        final Object source = mouseEvent.getSource();
        if (this.ka != null && !this.ka.kd) {
            this.ka.startTimer();
        }
        if (source == this.CCGrid) {
            this.CCGrid.HQ(mouseEvent);
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        try {
            final int intValue = Integer.decode(actionCommand);
            switch (this.PP[intValue]) {
                case 0: {
                    this.RevealClick();
                    break;
                }
                case 1: {
                    this.CheckClick();
                    break;
                }
                case 2: {
                    this.SubmitClick();
                    break;
                }
                case 3: {
                    this.SaveClick();
                    break;
                }
                case 4: {
                    this.RevertClick();
                    break;
                }
                case 5: {
                    this.SolutionClick();
                    break;
                }
                case 6: {
                    this.RevealLetterClick();
                    break;
                }
            }
            if (this.CheatCount >= this.MaxCheats && (this.PP[intValue] == 0 || this.PP[intValue] == 6)) {
                this.G[this.PP[intValue]].setEnabled(false);
            }
        }
        catch (Exception ex) {}
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        this.s();
        if (this.ka != null && !this.ka.kd) {
            this.ka.startTimer();
        }
        keyEvent.getSource();
        this.CCGrid.HP(keyEvent);
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        keyEvent.getSource();
        this.CCGrid.GridKeyTyped(keyEvent.getKeyChar());
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
}
