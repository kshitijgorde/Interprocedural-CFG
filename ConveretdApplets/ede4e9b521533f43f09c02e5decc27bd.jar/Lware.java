import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.awt.Event;
import java.awt.Cursor;
import java.util.Locale;
import java.awt.Button;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;
import java.applet.AppletContext;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public final class Lware extends Frame
{
    String a;
    String b;
    String c;
    String d;
    String e;
    String f;
    int g;
    String h;
    private AppletContext i;
    private boolean j;
    private String k;
    int l;
    private String m;
    private static final String n = "z6\u0007\u0005Sr4\u000b\u0018Wj2\u0006\u0011@c+\t\u001b]s\"\u0014\u0015Ifs[O\u0010=t";
    private static final int o = 30;
    private static final int p = 1073741824;
    private static final int q = 1073741823;
    private static final int r = 15;
    private static final int s = 32768;
    private static final int t = 32767;
    public static int u;
    
    public Lware(final Applet applet, final String s) {
        final int u = Lware.u;
        this.a = b("+lB\"Iy$\u0005\u001eT\u007f$\u0010\u0012C");
        this.b = b("](\u0011\u001eS+6\u0015\u0000\tj/\u0004\u000eSn \u000fYDd,B\u0003H+%\r\u0000Ig.\u0003\u0013");
        this.c = b("M\u0013'2\u0007J/\u0004\u000e\u0007m.\u0010Wpb/\u0006\u0018PxmB:Fha\r\u0005\u0007G(\f\u0002_%");
        this.d = b("+\u007fBWh@mB3h\\\u000f.8fO`BW\u001b+");
        this.e = b("n/");
        this.f = b("~2");
        this.g = 18;
        this.h = b("c5\u0016\u0007\u001d$n\u0015\u0000P% \f\u0011^\u007f$\u0003\u001a\th.\u000f");
        this.j = false;
        this.l = 4;
        this.i = applet.getAppletContext();
        String s2;
        try {
            s2 = applet.getDocumentBase().getProtocol();
        }
        catch (SecurityException ex) {
            s2 = b("m(\u000e\u0012");
        }
        try {
            this.k = applet.getDocumentBase().getHost();
        }
        catch (SecurityException ex2) {
            this.k = "";
        }
        this.k.toLowerCase();
        s2.toLowerCase();
        boolean equals = false;
        Label_0264: {
            Label_0263: {
                final boolean b;
                Label_0243: {
                    Label_0231: {
                        if (u == 0) {
                            Lware lware = null;
                            Label_0222: {
                                if (!s2.equals(b("m(\u000e\u0012"))) {
                                    lware = this;
                                    if (u != 0) {
                                        break Label_0222;
                                    }
                                    if (this.k.length() >= 1) {
                                        lware = this;
                                        if (u != 0) {
                                            break Label_0222;
                                        }
                                        if (!this.k.startsWith(b("g.\u0001\u0016K"))) {
                                            b = (equals = this.k.equals(b(":sUY\u0017%qLF")));
                                            if (u != 0) {
                                                break Label_0243;
                                            }
                                            if (!b) {
                                                break Label_0231;
                                            }
                                        }
                                    }
                                }
                                lware = this;
                            }
                            lware.j = true;
                        }
                        if (u == 0) {
                            break Label_0263;
                        }
                    }
                    final boolean startsWith;
                    equals = (startsWith = this.k.startsWith(b("|6\u0015Y")));
                }
                if (u != 0) {
                    break Label_0264;
                }
                if (b) {
                    this.k = this.k.substring(4);
                }
            }
            equals = false;
        }
        boolean b2 = equals;
        boolean b7 = false;
        boolean equalsIgnoreCase = false;
        boolean b6 = false;
        boolean b5 = false;
        boolean b4 = false;
        Label_0342: {
            boolean startsWith2 = false;
            Label_0315: {
                try {
                    b(":oR");
                    final boolean b3 = startsWith2 = System.getProperty(b("a \u0014\u0016\t}$\u0010\u0004Nd/")).startsWith(b(":oR"));
                    if (u != 0) {
                        break Label_0315;
                    }
                    if (!b3) {
                        b2 = true;
                    }
                }
                catch (Exception ex3) {}
                b4 = (startsWith2 = (b5 = (b6 = (equalsIgnoreCase = (b7 = b2)))));
                if (u != 0) {
                    break Label_0342;
                }
            }
            if (startsWith2) {
                try {
                    try {
                        this.a();
                    }
                    catch (NoSuchMethodError noSuchMethodError) {}
                }
                catch (Exception ex4) {}
            }
            b5 = (b4 = (b6 = (equalsIgnoreCase = (b7 = this.e.equalsIgnoreCase(b("o$"))))));
        }
        if (u == 0) {
            if (b4) {
                this.a = b("+lB\"Iy$\u0005\u001eT\u007f3\u000b\u0012U\u007f$B;Nq$\f\r");
                this.b = b("I$\u0011\u0002Dc$\fWtb$B\u0000P|o\u0003\u0019Ar5\u0007\u0016J%\"\r\u001a\u0007`.\u0011\u0003Be-\r\u0004By");
                this.c = b("O.\u0015\u0019Kd \u0006WQd/B6Im8B\u0011\u00dbya5\u001eIo.\u0015\u0004\u000b+\f\u0003\u0014\u0007~/\u0006Wkb/\u0017\u000f\t");
                this.d = b("+\u007fBWh@mB3h\\\u000f.8fO`BW\u001b+");
            }
            b6 = (b5 = (equalsIgnoreCase = (b7 = this.e.equalsIgnoreCase(b("n2")))));
        }
        if (u == 0) {
            if (b5) {
                this.a = b("+lB$Nea0\u0012@b2\u0016\u0005Fy");
                this.b = b("](\u0011\u001eSna\u0015\u0000P% \f\u0011^\u007f$\u0003\u001a\th.\u000fWWj3\u0003WHi5\u0007\u0019Bya\u0007\u001b\u0007{3\r\u0010Uj,\u0003");
                this.c = b("J/\u0004\u000e\u0007l3\u0003\u0003Rb5\r\u0004\u0007{ \u0010\u0016\u0007\\(\f\u0013H|2NWjj\"B\u000e\u0007G(\f\u0002_%");
                this.d = b("+\u007fBWeb$\f[\u0007D\u000362iL\u0000.8\u0006+a^W");
            }
            equalsIgnoreCase = (b6 = (b7 = this.e.equalsIgnoreCase(b("m3"))));
        }
        if (u == 0) {
            if (b6) {
                this.a = b("+lB9Hea\u0007\u0019Un&\u000b\u0004Sy¨");
                this.b = b("](\u0011\u001eSn;B\u0000P|o\u0003\u0019Ar5\u0007\u0016J%\"\r\u001a\u0007{.\u0017\u0005\u0007\u007f¨\u000e\u009eDc \u0010\u0010By");
                this.c = b("L\u0013##rB\u0015B6Im8B\u0007H~3B Ne%\r\u0000T'a/\u0016D+.\u0017Wkb/\u0017\u000f\t");
                this.d = b("D\nNWs\u00c2\r«4oJ\u0013%2u*");
            }
            b7 = (equalsIgnoreCase = this.e.equalsIgnoreCase(b("b5")));
        }
        boolean equalsIgnoreCase3 = false;
        boolean equalsIgnoreCase2 = false;
        Label_0657: {
            if (u == 0) {
                if (equalsIgnoreCase) {
                    this.a = b("+lB9Hea\u0010\u0012@b2\u0016\u0005F\u007f.");
                    this.b = b("](\u0011\u001eSja\u0015\u0000P% \f\u0011^\u007f$\u0003\u001a\th.\u000fWWn3B\u0004Dj3\u000b\u0014Fy$");
                    this.c = b("L\u0013##nXa#\u0019Ara\u0012\u0012U+\u0016\u000b\u0019Cd6\u0011[\u0007F \u0001WH+\r\u000b\u0019Rso");
                    this.d = b("+\u007fBWh@mB$dJ\u0013+4f*aBK\u0007");
                }
                equalsIgnoreCase2 = (b7 = (equalsIgnoreCase3 = this.e.equalsIgnoreCase(b("a "))));
                if (u != 0) {
                    break Label_0657;
                }
            }
            if (b7) {
                this.a = b("+lB\u675d\u765c\u9339\u302f\u30a5\u3095\u4e0a\u306c\u3018");
                this.b = b("\u3058\u302f\u30c0\u30a0\u30cb\u30c8\u3089\u300c\u30b7\u3081\u30f8\u30ac\u309e\u30be\u3048+6\u0015\u0000\tj/\u0004\u000eSn \u000fYDd,B\u303c\u30ae\uff0a");
                this.c = b("J/\u0004\u000e\u3049\u712a\u65d8\u30a5\u3095\u726f\u3064\u30405\u001eIo.\u0015\u0004\bF \u0001Xkb/\u0017\u000f\u3040\u4f74\u7569\u3005\u303a\u3059\u3052");
                this.d = b("+\u007fBW\u3088\u30e1\u3082\u30cd\u3020\u3041\u300a\u3081\u30c4\u3084\u30ca\u30f7\u3088\u3035\u3011\u3068\u306b\u3014\u3026\uff76\u0007+}B");
                this.g = 16;
                this.h = String.valueOf(this.h) + b("$-\fXMj1M");
            }
            equalsIgnoreCase3 = (equalsIgnoreCase2 = this.e.equalsIgnoreCase(b("`.")));
        }
        boolean equalsIgnoreCase5 = false;
        boolean equalsIgnoreCase4 = false;
        Label_0798: {
            if (u == 0) {
                if (equalsIgnoreCase2) {
                    this.a = b("+lB\ub486\ub87a\uc77fa\ub47a\uc9b7\u0007\uc541\uc519\uc2d7\ub2bf\ub2c3");
                    this.b = b("|6\u0015YFe'\u001b\u0003Bj,L\u0014Hfa\uc0ce\uc703\ud29f\uc5db\uc15dB\ub293\uc693\ub857\ub49dB\ubc6c\uc71b\uc133\uc6d5");
                    this.c = b("\uc703\ub385\uc6d2\uc6de\u000b+\ub9a5\ud0ca\ud1d7\uc2fb\uc6a2mB\ub9db\ub222\uc2af\uc6e8B\ud573\ub98b\uc6e3\uc5f5B6Im8");
                    this.d = b("+\u007fBW\ub2c3\uc6bf\ub81d\ub4beV\u0007+}B");
                    this.h = String.valueOf(this.h) + b("$-\fXLd3\u0007\u0016I$");
                }
                equalsIgnoreCase4 = (equalsIgnoreCase3 = (equalsIgnoreCase5 = this.e.equalsIgnoreCase(b("e-"))));
                if (u != 0) {
                    break Label_0798;
                }
            }
            if (equalsIgnoreCase3) {
                this.a = b("+lB8Il$\u0010\u0012@b2\u0016\u0005Bn3\u0006");
                this.b = b("I$\u0018\u0018B`a\u0015\u0000P% \f\u0011^\u007f$\u0003\u001a\th.\u000fWHfa\u0016\u0012\u0007o.\u0015\u0019Kd \u0006\u0012I");
                this.c = b("L\u0013##nXa#\u0019Ara\u0014\u0018Hya5\u001eIo.\u0015\u0004\u000b+\f\u0003\u0014\u0007n/B;Ne4\u001aY");
                this.d = b("+\u007fBWh@mB3h\\\u000f.8fO`BW\u001b+");
            }
            equalsIgnoreCase5 = (equalsIgnoreCase4 = this.e.equalsIgnoreCase(b("{5")));
        }
        boolean b9 = false;
        boolean b8 = false;
        Label_0910: {
            if (u == 0) {
                if (equalsIgnoreCase4) {
                    this.a = b("+lB9\u00c4da\u0010\u0012@b2\u0016\u0005Fo.");
                    this.b = b("](\u0011\u001eSna\u0015\u0000P% \f\u0011^\u007f$\u0003\u001a\th.\u000fWWj3\u0003WCd6\f\u001bHj%");
                    this.c = b("J/\u0004\u000e\u0007L\u0013£#nXa\u0012\u0016Uja5\u001eIo.\u0015\u0004\u000b+\f\u0003\u0014\u0007d4B;Ne4\u001aY");
                    this.d = b("+\u007fBWh@mB3h\\\u000f.8fO`BW\u001b+");
                }
                final boolean equalsIgnoreCase6;
                b8 = (equalsIgnoreCase5 = (equalsIgnoreCase6 = (b9 = this.e.equalsIgnoreCase(b("x7")))));
                if (u != 0) {
                    break Label_0910;
                }
            }
            if (equalsIgnoreCase5) {
                this.a = b("+lB8Un&\u000b\u0004Sy$\u0010\u0016C");
                this.b = b("I$\u0011\u0081L+6\u0015\u0000\tj/\u0004\u000eSn \u000fYDd,B\u0011\u00d1ya\u0003\u0003S+-\u0003\u0013Cja\f\u0012U");
                this.c = b("J/\u0004\u000e\u0007m·\u0010Wpb/\u0006\u0018PxmB:Fha\u0007\u001bKn3B;Ne4\u001aW`Y\u00006>t*");
                this.d = b("+\u007fBWh@mB;fO\u0005#WiN\u0013CW\u00077a");
            }
            b8 = (b9 = this.e.equalsIgnoreCase(b("j3")));
        }
        Lware lware2 = null;
        Label_1278: {
            Label_1063: {
                final boolean startsWith3;
                Label_1007: {
                    if (u == 0) {
                        if (b8) {
                            String property = "i";
                            try {
                                property = System.getProperty(b("i3\r\u0000Tn3L\u0001Be%\r\u0005"));
                            }
                            catch (Exception ex5) {}
                            startsWith3 = property.startsWith(b("E$\u0016\u0004Dj1\u0007"));
                            if (u != 0) {
                                break Label_1007;
                            }
                            if (!startsWith3) {
                                this.a = b("+\u001eB\u064d\u066d\u063aa\u0627\u0644\u060b\u064f");
                                this.b = b("\u064f\u0605\u0648\u065a\u0662\u0641\u0605B\u0000P|o\u0003\u0019Ar5\u0007\u0016J%\"\r\u001a\u0007\u0628\u066f\u0648\u0646");
                                this.c = b("\u0623\u0670\u0624\u0650\u0662\u0627a\u0645\u0633\u0604\u064d\u0600\u0628W\u0662\u0627\u0666\u0624\u0650\u066c+\u0605\u0641\u0631\u061f\u064e\u0668B\u063f\u066d\u064d\u066e\u062a\u0645\u062b+\u0604\u0645\u0634\u062b+\u0666\u062aW\u0663\u0641\u0607\u0621\u0644");
                                this.d = b("+\u007fBW\u0662\u0643\u0666\u0623\u0635\u0007\u0607a\u0645\u065f\u0608\u0628a\u0645\u0633\u060d\u0626\u0604\u0628\u0633\u0007+}B");
                            }
                        }
                        final boolean equalsIgnoreCase6;
                        b9 = (equalsIgnoreCase6 = this.e.equalsIgnoreCase(b("y4")));
                    }
                    if (u != 0) {
                        break Label_1063;
                    }
                }
                if (startsWith3) {
                    this.a = b("+lB\u046a\u0412+\u0476\u0452\u0437\u0412\u0438\u0479\u0423\u0435\u0467\u0433\u0401\u045c\u0445\u0417\u0436\u047c\u0452\u0438\u0007\u0431\u047f\u045d\u044f\u0468*");
                    this.b = b("\u0414\u047f\u0423\u0442\u0465\u0433\u0403\u0457WP|6L\u0016Im8\u0016\u0012Ffo\u0001\u0018J+\u0475\u0459\u0438\u0007\u0434\u047f\u0459\u0434\u0460\u043e\u047c\u045a\u0438\u0007\u043a\u0474\u0423\u0448\u041c\u043b\u0403\u045f\u043c\u0462");
                    this.c = b("\u043b\u047e\u045d\u044c\u0412\u0449\u047f\u0450Wfe'\u001bW\u0413\u0430\u040eB Ne%\r\u0000T'a/\u0016D+\u0479\u0459\u044f\u0007G(\f\u0002_%");
                    this.d = b("+\u007fBWh@mB\u0460\u0437\u0418\u0461\u0441\u0460\u043f\u0429\u046dCW\u00077a");
                }
                lware2 = this;
                if (u != 0) {
                    break Label_1278;
                }
                b9 = this.e.equalsIgnoreCase(b("q)"));
            }
            Label_1241: {
                if (b9) {
                    Lware lware3 = this;
                    Lware lware4 = null;
                    Label_1213: {
                        Label_1176: {
                            if (u == 0) {
                                if (!this.f.equalsIgnoreCase(b("H\u000f"))) {
                                    lware4 = this;
                                    if (u != 0) {
                                        break Label_1213;
                                    }
                                    if (!this.f.equalsIgnoreCase(b("H\t"))) {
                                        break Label_1176;
                                    }
                                }
                                this.a = b("+lB\u675d\u6ccf\u5187");
                                this.b = b("\u8bfc\u5271\u0015\u0000P% \f\u0011^\u007f$\u0003\u001a\th.\u000f\u4e7c\u8f5a");
                                this.c = b("\u5146\u8d78#\u0019Ara\u0004\u0018U+\u0016\u000b\u0019Cd6\u0011[\u0007F \u0001W\u6231+\r\u000b\u0019Rs");
                                this.d = b("+\u007fBW\u7849\u5b91mB\u4e7c\u8f5a*aBK\u0007");
                                lware3 = this;
                            }
                            lware3.h = String.valueOf(this.h) + b("$-\fXDc(\u0011\u001eJ{n");
                            if (u == 0) {
                                break Label_1241;
                            }
                        }
                        this.a = b("+lB\u675d\u8a1c\u5181");
                        this.b = b("\u8ac0\u5271\u0015\u0000P% \f\u0011^\u007f$\u0003\u001a\th.\u000f\u4e7c\u8f2e");
                        this.c = b("\u5146\u8cfa#\u0019Ara\u0004\u0018U+\u0016\u000b\u0019Cd6\u0011[\u0007F \u0001W\u6231+\r\u000b\u0019Rs");
                        this.d = b("+\u007fBW\u789d\u5b91mB\u4e7c\u8f2e*aBK\u0007");
                        lware4 = this;
                    }
                    lware4.h = String.valueOf(this.h) + b("$-\fXDc(\u0016\u0005Fon");
                }
            }
            this.setTitle(b("A \u0014\u0016\u0007J1\u0012\u001bB\u007fa\u0000\u000e\u0007j/\u0004\u000eSn \u000fYDd,"));
            this.setBackground(new Color(235, 235, 245));
            this.setForeground(Color.black);
            lware2 = this;
        }
        lware2.setFont(new Font(b("X8\u0011\u0003Bf"), 1, 12));
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        final Label label = new Label(String.valueOf(s) + this.a, 1);
        layout.setConstraints(label, gridBagConstraints);
        this.add(label);
        final Label label2 = new Label(this.b, 1);
        layout.setConstraints(label2, gridBagConstraints);
        this.add(label2);
        final Label label3 = new Label(this.c, 1);
        layout.setConstraints(label3, gridBagConstraints);
        this.add(label3);
        gridBagConstraints.gridwidth = 1;
        final Label label4 = new Label(" ");
        layout.setConstraints(label4, gridBagConstraints);
        this.add(label4);
        final Label label5 = new Label(" ");
        layout.setConstraints(label5, gridBagConstraints);
        this.add(label5);
        final Label label6 = new Label(" ");
        layout.setConstraints(label6, gridBagConstraints);
        this.add(label6);
        final Label label7 = new Label(" ");
        layout.setConstraints(label7, gridBagConstraints);
        this.add(label7);
        final Label label8 = new Label(" ");
        layout.setConstraints(label8, gridBagConstraints);
        this.add(label8);
        gridBagConstraints.gridwidth = 0;
        final Label label9 = new Label(" ");
        layout.setConstraints(label9, gridBagConstraints);
        this.add(label9);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 4;
        final Button button = new Button(this.d);
        layout.setConstraints(button, gridBagConstraints);
        button.setBackground(Color.yellow);
        button.setFont(new Font(b("X8\u0011\u0003Bf"), 1, this.g));
        Lware lware5 = this;
        Label_1731: {
            if (u == 0) {
                this.add(button);
                if (b2) {
                    try {
                        try {
                            this.a(button);
                        }
                        catch (NoSuchMethodError noSuchMethodError2) {}
                    }
                    catch (Exception ex6) {
                        if (u == 0) {
                            break Label_1731;
                        }
                    }
                }
                lware5 = this;
            }
            lware5.setCursor(12);
        }
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridwidth = 0;
        final Label label10 = new Label(" ");
        layout.setConstraints(label10, gridBagConstraints);
        this.add(label10);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 6;
        final Label label11 = new Label(" ");
        layout.setConstraints(label11, gridBagConstraints);
        this.add(label11);
        this.pack();
    }
    
    void a() {
        try {
            final Locale default1 = Locale.getDefault();
            this.e = default1.getLanguage();
            this.f = default1.getCountry();
        }
        catch (NoSuchMethodError noSuchMethodError) {}
    }
    
    void a(final Button button) {
        try {
            button.setCursor(new Cursor(12));
        }
        catch (NoSuchMethodError noSuchMethodError) {}
    }
    
    public final boolean action(final Event event, final Object o) {
        if (o.equals(this.d)) {
            this.hide();
            try {
                this.i.showDocument(new URL(this.h), b("T#\u000e\u0016I`"));
            }
            catch (Exception ex) {}
        }
        return true;
    }
    
    public final boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.l = 5;
            this.hide();
        }
        if (event.id == 501) {
            this.hide();
            try {
                this.i.showDocument(new URL(this.h), b("T#\u000e\u0016I`"));
            }
            catch (Exception ex) {}
        }
        if (event.id == 1005 && this.l > 1) {
            if (this.l < 5) {
                this.toFront();
            }
            --this.l;
        }
        if (event.id == 1004) {
            this.show();
            this.toFront();
        }
        return super.handleEvent(event);
    }
    
    public final void dck() {
        final int u = Lware.u;
        final boolean j = this.j;
        final boolean b = true;
        if ((u != 0 || j != b) && u == 0) {
            goto Label_0018;
        }
    }
    
    public final String dr(final String s, int n, final boolean b) {
        final int i = Lware.u;
        final int[][] array = new int[2][];
        final int n2 = 0;
        final int[] array2 = new int[16];
        array2[0] = 714175159;
        array2[1] = 1;
        array[n2] = array2;
        array[1] = new int[] { 2, 0 };
        final int[][] array3 = array;
        final int[][] array4 = new int[2][];
        final int n3 = 0;
        final int[] array5 = new int[16];
        array5[0] = 116921063;
        array5[1] = 2;
        array4[n3] = array5;
        array4[1] = new int[] { 2, 0 };
        final int[][] array6 = array4;
        Lware lware = this;
        final String m = "";
        Label_0106: {
            if (i != 0) {
                break Label_0106;
            }
            this.m = m;
            if (n == 1) {
                if (this.j != b) {
                    do {
                        ++n;
                    } while (i == 0);
                }
                return "";
            }
            try {
                lware = this;
                final DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(lware.a(m)));
                final byte[] array7 = new byte[4];
                try {
                    byte[] array8;
                    int[][] b2;
                    int[][] b3;
                    int n4;
                    String string;
                    int n5;
                    Label_0254_Outer:Label_0290_Outer:
                    while (true) {
                        array8 = new byte[dataInputStream.readByte()];
                        dataInputStream.readFully(array8);
                        b2 = this.b();
                        this.a(array8, b2);
                        b3 = this.b();
                        this.b(b3, b2, array3, array6);
                        this.a(b3, array7);
                        n4 = (array7[0] << 24 & 0xFF000000) + (array7[1] << 16 & 0xFF0000) + (array7[2] << 8 & 0xFF00) + (array7[3] & 0xFF);
                        string = "";
                        n5 = 0;
                        while (true) {
                            while (true) {
                                Label_0293: {
                                    if (i == 0) {
                                        break Label_0293;
                                    }
                                    string = String.valueOf(this.a(n4 & 0x3F)) + string;
                                    n4 >>= 6;
                                    ++n5;
                                }
                                if (n5 < 5) {
                                    continue Label_0290_Outer;
                                }
                                break;
                            }
                            this.m = String.valueOf(this.m) + string;
                            if (i == 0) {
                                continue Label_0254_Outer;
                            }
                            continue;
                        }
                    }
                }
                catch (EOFException ex) {
                    final String j = this.m;
                    if (i == 0) {
                        if (j.equals(this.k)) {
                            this.j = true;
                        }
                        final String k = this.m;
                    }
                    return j;
                }
            }
            catch (Exception ex2) {
                return "";
            }
        }
    }
    
    private final String a(int n) {
        final int u = Lware.u;
        int n5;
        final int n4;
        final int n3;
        final int n2 = n3 = (n4 = (n5 = n));
        Label_0118: {
            final int n8;
            final int n9;
            Label_0076: {
                final int n10;
                final int n11;
                Label_0063: {
                    Label_0059: {
                        final int n7;
                        Label_0036: {
                            if (u == 0) {
                                if (n2 >= 0) {
                                    final int n6 = n;
                                    n7 = 10;
                                    if (u != 0) {
                                        break Label_0036;
                                    }
                                    if (n6 < n7) {
                                        n += 48;
                                        if (u == 0) {
                                            break Label_0118;
                                        }
                                    }
                                }
                                n8 = n;
                            }
                            n9 = 10;
                            if (u != 0) {
                                break Label_0059;
                            }
                        }
                        if (n2 >= n7) {
                            n10 = n;
                            n11 = 36;
                            if (u != 0) {
                                break Label_0063;
                            }
                            if (n10 < n11) {
                                n += 87;
                                if (u == 0) {
                                    break Label_0118;
                                }
                            }
                        }
                    }
                    if (u != 0) {
                        break Label_0076;
                    }
                }
                if (n10 == n11) {
                    n = 45;
                    if (u == 0) {
                        break Label_0118;
                    }
                }
            }
            Label_0107: {
                int n12 = 0;
                Label_0102: {
                    if (u == 0) {
                        if (n8 == n9) {
                            n = 46;
                            if (u == 0) {
                                break Label_0118;
                            }
                        }
                        n12 = (n5 = n);
                        if (u != 0) {
                            break Label_0102;
                        }
                    }
                    if (n8 != n9) {
                        break Label_0107;
                    }
                    n12 = 38;
                }
                n = n12;
                if (u == 0) {
                    break Label_0118;
                }
            }
            System.out.println(b("h)\u0003\u0005\u0007e.\u0016WT~1\u0012"));
        }
        if (n != 38) {
            return new Character((char)n).toString();
        }
        return "";
    }
    
    private final byte[] a(final String s) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int n = 0;
        final char[] charArray = s.toCharArray();
        try {
            while (true) {
                Label_0178: {
                    if (Lware.u == 0) {
                        break Label_0178;
                    }
                    byteArrayOutputStream.write((b("z6\u0007\u0005Sr4\u000b\u0018Wj2\u0006\u0011@c+\t\u001b]s\"\u0014\u0015Ifs[O\u0010=t").indexOf(charArray[n++]) << 3) + (b("z6\u0007\u0005Sr4\u000b\u0018Wj2\u0006\u0011@c+\t\u001b]s\"\u0014\u0015Ifs[O\u0010=t").indexOf(charArray[n]) >>> 2));
                    byteArrayOutputStream.write((b("z6\u0007\u0005Sr4\u000b\u0018Wj2\u0006\u0011@c+\t\u001b]s\"\u0014\u0015Ifs[O\u0010=t").indexOf(charArray[n++]) << 6) + (b("z6\u0007\u0005Sr4\u000b\u0018Wj2\u0006\u0011@c+\t\u001b]s\"\u0014\u0015Ifs[O\u0010=t").indexOf(charArray[n++]) << 1) + (b("z6\u0007\u0005Sr4\u000b\u0018Wj2\u0006\u0011@c+\t\u001b]s\"\u0014\u0015Ifs[O\u0010=t").indexOf(charArray[n]) >> 4));
                    byteArrayOutputStream.write((b("z6\u0007\u0005Sr4\u000b\u0018Wj2\u0006\u0011@c+\t\u001b]s\"\u0014\u0015Ifs[O\u0010=t").indexOf(charArray[n++]) << 4) + b("z6\u0007\u0005Sr4\u000b\u0018Wj2\u0006\u0011@c+\t\u001b]s\"\u0014\u0015Ifs[O\u0010=t").indexOf(charArray[n++]));
                }
                if (n < charArray.length) {
                    continue;
                }
                break;
            }
        }
        catch (Exception ex) {}
        return byteArrayOutputStream.toByteArray();
    }
    
    private final int[][] b() {
        final int[][] array = new int[2][];
        array[0] = new int[16];
        (array[1] = new int[2])[0] = 0;
        array[1][1] = 0;
        return array;
    }
    
    private final void a(final int[][] array, final int[][] array2) {
        if (array == array2) {
            return;
        }
        array[0] = new int[array2[0].length];
        array[1][1] = array2[1][1];
        array[1][0] = array2[1][0];
        if (array2[1][0] > 0) {
            System.arraycopy(array2[0], 0, array[0], 0, array2[1][0]);
        }
    }
    
    private final int b(final int[][] array, final int[][] array2) {
        final int u = Lware.u;
        final int n = array[1][0];
        final int n2 = array2[1][0];
        final int n3 = n;
        final int n4 = n2;
        if (u == 0) {
            if (n3 < n4) {
                return -1;
            }
            final int n6;
            final int n5 = n6 = n;
            if (u != 0) {
                return n5;
            }
        }
        int n5;
        if (n3 > n4) {
            n5 = 1;
        }
        else {
            final int[] array3 = array[0];
            final int[] array4 = array2[0];
            int n7 = n - 1;
            while (true) {
                while (true) {
                    Label_0107: {
                        if (u == 0) {
                            break Label_0107;
                        }
                        final int n9;
                        int n8 = n9 = array3[n7];
                        final int n10 = array4[n7];
                        if (u == 0) {
                            final int n11;
                            if (n11 < n10) {
                                return -1;
                            }
                            final int n12 = n8 = array3[n7];
                            if (u != 0) {
                                return n12;
                            }
                            final int n13 = array4[n7];
                        }
                        if (n8 <= n10) {
                            --n7;
                            break Label_0107;
                        }
                        return 1;
                    }
                    if (n7 >= 0) {
                        continue;
                    }
                    break;
                }
                int n8;
                final int n11 = n8 = 0;
                if (u == 0) {
                    return n11;
                }
                continue;
            }
        }
        return n5;
    }
    
    private final int a(final int[][] array) {
        final int u = Lware.u;
        final int n2;
        final int n = n2 = array[1][0];
        if (u == 0 && n2 == 0) {
            return 0;
        }
        int n3 = n2;
        int n4 = array[0][n - 1];
        while (true) {
            Label_0052: {
                if (u == 0) {
                    break Label_0052;
                }
                final int n5;
                n4 = n5;
                ++n3;
            }
            if (n4 != 0) {
                continue;
            }
            final int n5 = n3;
            if (u == 0) {
                return n5;
            }
            continue;
        }
    }
    
    private final void b(final int[][] array) {
        array[0][0] = 0;
        array[1][1] = 0;
        array[1][0] = 0;
    }
    
    private final void a(final int[][] array, int n) {
        final int[] array2 = array[0];
        if (n <= array2.length) {
            return;
        }
        n += 16;
        final int[] array3 = new int[n];
        System.arraycopy(array2, 0, array3, 0, array2.length);
        array[0] = array3;
    }
    
    private final void a(final int[][] array, final int[][] array2, final int n) {
        final int u = Lware.u;
        final short n2 = (short)n;
        final int n3 = array2[1][0];
        if (u == 0 && n3 == 0) {
            array[0][0] = 0;
            array[1][1] = 0;
            array[1][0] = 0;
            return;
        }
        final int n4 = n3;
        final short n5 = (short)(n2 / 30);
        final int n6 = array2[1][0];
        this.a(array, array[1][0] = n6 + n5);
        final int[] array3 = array[0];
        System.arraycopy(array2[0], 0, array3, n5, n6);
        int n9;
        int n8;
        final int n7 = n8 = (n9 = n5);
        if (u == 0) {
            if (n7 > 0) {
                int n10 = n5 - 1;
                while (true) {
                    Label_0130: {
                        if (u == 0) {
                            break Label_0130;
                        }
                        array3[n10] = 0;
                        --n10;
                    }
                    if (n10 >= 0) {
                        continue;
                    }
                    break;
                }
            }
            n9 = (n8 = n4);
        }
        if (u == 0) {
            if (n8 == 0) {
                return;
            }
            n9 = 0;
        }
        int n11 = n9;
        int n12 = array[1][0];
        int n13 = n5;
        int n16 = 0;
        while (true) {
            while (true) {
                Label_0200: {
                    if (u == 0) {
                        break Label_0200;
                    }
                    final int n14 = array3[n13];
                    final int n15 = n16;
                    array3[n13] = ((n15 << n4 | n11) & 0x3FFFFFFF);
                    n11 = n15 >>> 30 - n4;
                    ++n13;
                }
                if (n13 < n12) {
                    continue;
                }
                break;
            }
            n16 = n11;
            if (u != 0) {
                continue;
            }
            break;
        }
        if (n16 != 0) {
            ++n12;
            this.a(array, n12);
            array[0][n12 - 1] = n11;
            array[1][0] = n12;
        }
    }
    
    private final int c(final int[][] array, final int[][] array2) {
        if (array[1][0] == 0 && array2[1][0] == 0) {
            return 0;
        }
        if (array[1][1] != 0) {
            if (array2[1][1] != 0) {
                return this.b(array2, array);
            }
            return -1;
        }
        else {
            if (array2[1][1] != 0) {
                return 1;
            }
            return this.b(array, array2);
        }
    }
    
    private final void a(final int[][] array, int[][] array2, int[][] array3) {
        final int u = Lware.u;
        Lware lware = this;
        final int[][] array4 = array2;
        final int[][] array5 = array3;
        if (u == 0) {
            Label_0082: {
                switch (this.b(array4, array5)) {
                    case 0: {
                        array[0][0] = 0;
                        array[1][1] = 0;
                        array[1][0] = 0;
                        return;
                    }
                    case -1: {
                        final int[][] array6 = array2;
                        array2 = array3;
                        array3 = array6;
                        array[1][1] = 1;
                        if (u != 0) {
                            break Label_0082;
                        }
                        break;
                    }
                    case 1: {
                        array[1][1] = 0;
                        break;
                    }
                }
            }
            lware = this;
        }
        lware.a(array4, array5[1][0]);
        final int[] array7 = array2[0];
        final int[] array8 = array3[0];
        final int[] array9 = array[0];
        final int n = array2[1][0];
        final int n2 = array3[1][0];
        int i = 0;
        int n3 = 0;
        while (true) {
            Label_0197: {
                if (u == 0) {
                    break Label_0197;
                }
                final int n4 = array7[n3] - array8[n3];
                final int n5 = i;
                if (u == 0 && n5 == 0) {}
                final int n6 = n4 - n5;
                array9[n3] = (n6 & 0x3FFFFFFF);
                final int n7 = n6;
                if (u == 0 && n7 < 0) {}
                i = n7;
                ++n3;
            }
            if (n3 < n2) {
                continue;
            }
            break;
        }
        while (true) {
            while (true) {
                while (i != 0) {
                    final int n8 = n3;
                    final int n9 = n;
                    if (u != 0 || u != 0) {
                        if (n8 > n9) {
                            System.arraycopy(array7, n3, array9, n3, array2[1][0] - n3);
                        }
                        final int n10 = array2[1][0];
                        int n11 = n10;
                        int[] array10 = null;
                        int n12 = 0;
                        Label_0354: {
                            while (true) {
                                Label_0325: {
                                    if (u == 0) {
                                        break Label_0325;
                                    }
                                    --n11;
                                }
                                if (n11 > 0) {
                                    array10 = array9;
                                    n12 = n11 - 1;
                                    if (u != 0 || u != 0) {
                                        break Label_0354;
                                    }
                                    if (array10[n12] == 0) {
                                        continue;
                                    }
                                }
                                break;
                            }
                            final int[] array11 = array[1];
                        }
                        array10[n12] = n11;
                        return;
                    }
                    if (n8 >= n9) {
                        break;
                    }
                    final int n14;
                    final int n13 = n14 = array7[n3] - 1;
                    Label_0251: {
                        Label_0241: {
                            if (u == 0) {
                                if (n14 < 0) {
                                    break Label_0241;
                                }
                                array9[n3] = n13;
                            }
                            i = n14;
                            if (u == 0) {
                                break Label_0251;
                            }
                        }
                        array9[n3] = (n13 & 0x3FFFFFFF);
                    }
                    ++n3;
                }
                final int n15;
                final int n10 = n15 = array2[1][0];
                if (u == 0) {
                    continue;
                }
                break;
            }
            continue;
        }
    }
    
    private final void b(final int[][] array, int[][] array2, int[][] array3) {
        final int u = Lware.u;
        final int n = array2[1][0];
        if (u == 0) {
            if (n < array3[1][0]) {
                final int[][] array4 = array2;
                array2 = array3;
                array3 = array4;
            }
            final int n2 = array2[1][0];
        }
        final int n3 = n;
        final int n4 = array3[1][0];
        this.a(array, array[1][0] = n3);
        array[1][1] = 0;
        final int[] array5 = array2[0];
        final int[] array6 = array3[0];
        final int[] array7 = array[0];
        int i = 0;
        int n5 = 0;
        while (true) {
            Label_0155: {
                if (u == 0) {
                    break Label_0155;
                }
                final int n6 = array5[n5] + array6[n5];
                final int n7 = i;
                if (u == 0 && n7 == 0) {}
                final int n8 = n6 + n7;
                array7[n5] = (n8 & 0x3FFFFFFF);
                final int n9 = n8;
                if (u == 0 && n9 >= 1073741824) {}
                i = n9;
                ++n5;
            }
            if (n5 < n4) {
                continue;
            }
            break;
        }
        while (true) {
            while (true) {
                while (i != 0) {
                    final int n10 = n5;
                    final int n11 = n3;
                    if (u != 0 || u != 0) {
                        if (n10 > n11) {
                            System.arraycopy(array5, n5, array7, n5, n3 - n5);
                        }
                        final int n12 = i;
                        if (n12 != 0) {
                            final int[] array8 = array[1];
                            final int n13 = 0;
                            ++array8[n13];
                            this.a(array, array[1][0]);
                            array[0][n5] = 1;
                        }
                        return;
                    }
                    if (n10 >= n11) {
                        break;
                    }
                    final int n15;
                    final int n14 = n15 = array5[n5] + 1;
                    Label_0211: {
                        Label_0201: {
                            if (u == 0) {
                                if (n15 >= 1073741824) {
                                    break Label_0201;
                                }
                                array7[n5] = n14;
                            }
                            i = n15;
                            if (u == 0) {
                                break Label_0211;
                            }
                        }
                        array7[n5] = (n14 & 0x3FFFFFFF);
                    }
                    ++n5;
                }
                final int n16;
                final int n12 = n16 = array2[1][0];
                if (u == 0) {
                    continue;
                }
                break;
            }
            continue;
        }
    }
    
    private final void c(final int[][] array, final int[][] array2, final int[][] array3) {
        if (array2[1][0] == 0) {
            this.a(array, array3);
            if (array3[1][0] > 0) {
                array[1][0] = ((array3[1][1] == 0) ? 1 : 0);
            }
            return;
        }
        if (array3[1][0] == 0) {
            this.a(array, array2);
            return;
        }
        if (array2[1][1] != 0) {
            if (array3[1][1] != 0) {
                this.a(array, array3, array2);
                return;
            }
            this.b(array, array3, array2);
            array[1][1] = 1;
        }
        else {
            if (array3[1][1] != 0) {
                this.b(array, array2, array3);
                return;
            }
            this.a(array, array2, array3);
        }
    }
    
    private final void b(final int[][] array, final int[][] array2, final int n) {
        final int u = Lware.u;
        final short n2 = (short)n;
        final short n3 = (short)(n2 % 30);
        final short n5;
        final short n4 = n5 = (short)(n2 / 30);
        int n6 = 0;
        Label_0108: {
            if (u == 0) {
                if (n5 >= array2[1][0]) {
                    array[0][0] = 0;
                    array[1][1] = 0;
                    array[1][0] = 0;
                    return;
                }
                this.a(array, array[1][0] = array2[1][0] - n4);
                System.arraycopy(array2[0], n4, array[0], 0, array[1][0]);
                final short n7;
                n6 = (n7 = n3);
                if (u != 0) {
                    break Label_0108;
                }
            }
            if (n5 == 0) {
                return;
            }
            n6 = 0;
        }
        int n8 = n6;
        final int[] array3 = array[0];
        final int n9 = array[1][0];
        int n10 = n9 - 1;
        int n11 = 0;
        int n12;
        while (true) {
            while (true) {
                Label_0169: {
                    if (u == 0) {
                        break Label_0169;
                    }
                    array3[n10] = (array3[n10] >>> n3 | n8);
                    n8 = n11;
                    --n10;
                }
                if (n10 > 0) {
                    continue;
                }
                break;
            }
            array3[0] = (array3[0] >>> n3 | n8);
            n11 = (n12 = n9);
            if (u != 0) {
                continue;
            }
            break;
        }
        int[] array5 = null;
        final int[] array4;
        int n14 = 0;
        final int n13;
        Label_0226: {
            if (u == 0) {
                if (n11 <= 0) {
                    return;
                }
                array4 = (array5 = array3);
                n13 = (n14 = n9 - 1);
                if (u != 0) {
                    break Label_0226;
                }
                n12 = array4[n13];
            }
            if (n12 != 0) {
                return;
            }
            final int[] array6;
            array5 = (array6 = array[1]);
            final int n15;
            n14 = (n15 = 0);
        }
        array5[n14] = array4[n13] - 1;
    }
    
    private final void d(final int[][] array, final int[][] array2, final int[][] array3) {
        final int u = Lware.u;
        if (array == array2) {
            System.out.println(b("y$\u0011W\u001a+,"));
        }
        this.a(array, array2);
        final int b = this.b(array2, array3);
        if (u == 0) {
            if (b < 0) {
                return;
            }
            final int n = this.a(array2) - this.a(array3);
        }
        int n2 = b;
        final int[][] b2 = this.b();
        this.a(b2, array3, n2);
        while (true) {
            Label_0110: {
                if (u == 0) {
                    break Label_0110;
                }
                Lware lware = this;
                int[][] array4 = array;
                final int[][] array5 = b2;
                if (u == 0) {
                    if (this.c(array, array5) >= 0) {
                        this.c(array, array, b2);
                    }
                    lware = this;
                    array4 = b2;
                }
                lware.b(array4, array5, 1);
                --n2;
            }
            if (n2 < 0) {
                return;
            }
            continue;
        }
    }
    
    private final void c(final int[][] array) {
        array[0][0] = 1;
        array[1][1] = 0;
        array[1][0] = 1;
    }
    
    private final void a(int[][] b, int[][] b2, final int[][] array, final int[][] array2) {
        final int u = Lware.u;
        final int n = array2[1][0];
        if (u == 0) {
            if (n == 0) {
                System.out.println(b("o(\u0014WEraR"));
            }
            this.c(array, array2);
        }
        if (n < 0) {
            int[][] array4;
            final int[][] array3 = array4 = b2;
            if (u == 0) {
                if (array3 != null) {
                    this.a(b2, array);
                }
                final int[][] array5;
                array4 = (array5 = b);
            }
            if (u == 0) {
                if (array3 == null) {
                    return;
                }
                b[0][0] = 0;
                b[1][1] = 0;
                array4 = b;
            }
            array4[1][0] = 0;
            return;
        }
        int[][] b3;
        final int[][] array6 = b3 = b;
        if (u == 0) {
            if (array6 == null) {
                b = this.b();
            }
            final int[][] array7;
            b3 = (array7 = b2);
        }
        if (u == 0) {
            if (array6 == null) {
                b2 = this.b();
            }
            b3 = this.b();
        }
        final int[][] array8 = b3;
        this.a(b2, array);
        b[0][0] = 0;
        b[1][1] = 0;
        b[1][0] = 0;
        int n2 = this.a(array) - this.a(array2);
        this.a(array8, array2, n2);
        while (true) {
            while (true) {
                Label_0280: {
                    if (u == 0) {
                        break Label_0280;
                    }
                    final int n3 = b[1][0];
                    Lware lware = null;
                    final int[][] array9;
                    final int[][] array10;
                    Label_0273: {
                        Label_0268: {
                            if (u == 0) {
                                if (n3 == 0) {
                                    lware = this;
                                    array9 = b2;
                                    array10 = array8;
                                    if (u != 0) {
                                        break Label_0273;
                                    }
                                    if (this.c(array9, array10) < 0) {
                                        break Label_0268;
                                    }
                                    b[0][0] = 1;
                                    b[1][1] = 0;
                                    b[1][0] = 1;
                                    this.c(b2, b2, array8);
                                    if (u == 0) {
                                        break Label_0268;
                                    }
                                }
                                this.a(b, b, 1);
                                lware = this;
                                final int[][] array11 = b2;
                                final int[][] array12 = array8;
                                if (u != 0) {
                                    break Label_0273;
                                }
                                this.c(array11, array12);
                            }
                            if (n3 < 0) {
                                break Label_0268;
                            }
                            final int[] array13 = b[0];
                            final int n5;
                            final int n4 = array13[n5 = 0] | 0x1;
                            final int n6;
                            final int n7;
                            array13[n6] = n7;
                            this.c(b2, b2, array8);
                        }
                        lware = this;
                    }
                    lware.b(array9, array10, 1);
                    --n2;
                }
                if (n2 >= 0) {
                    continue;
                }
                break;
            }
            final int[] array14 = b[1];
            final int n6 = 1;
            boolean b4;
            final int n7 = (b4 = (array[1][1] != 0)) ? 1 : 0;
            if (u == 0) {
                if (u == 0) {
                    if (n7 == 0) {
                        b4 = false;
                    }
                    else {
                        b4 = true;
                    }
                }
                final int n8 = array2[1][1];
                if (u == 0 && n8 != 0) {}
                final boolean b5 = ((b4 ? 1 : 0) ^ n8) != 0x0;
                if (u == 0 && !b5) {}
                array14[n6] = (b5 ? 1 : 0);
                return;
            }
            continue;
        }
    }
    
    private final int d(final int[][] array, final int[][] array2) {
        final int[][] b = this.b();
        b[0][0] = 1;
        b[1][1] = 0;
        b[1][0] = 1;
        final int a = this.a(array2);
        this.a(b, b, 2 * a);
        this.a(array, null, b, array2);
        return a + 1;
    }
    
    private final void e(final int[][] array, final int[][] array2, final int[][] array3) {
        final int u = Lware.u;
        int[][] array4 = array;
        int[][] array5 = array2;
        int[][] array6 = null;
        Label_0037: {
            Label_0036: {
                Label_0025: {
                    if (u == 0) {
                        if (array == array2) {
                            break Label_0025;
                        }
                        array6 = array;
                        array4 = array;
                        if (u != 0) {
                            break Label_0037;
                        }
                        array5 = array3;
                    }
                    if (array4 != array5) {
                        break Label_0036;
                    }
                }
                System.out.println(b("ja\r\u0005\u0007ia_WU"));
            }
            array6 = array2;
        }
        final int[] array7 = array6[1];
        final int n = 0;
        if (u == 0) {
            Label_0062: {
                if (array7[n] != 0) {
                    final int[] array8 = array3[1];
                    final int n2 = 0;
                    if (u == 0) {
                        if (array8[n2] == 0) {
                            break Label_0062;
                        }
                        final int[] array9 = array[1];
                    }
                    final int n3 = array2[1][1];
                    if (u == 0 && n3 != 0) {}
                    final int n4 = array3[1][1];
                    if (u == 0 && n4 != 0) {}
                    final boolean b = (n3 ^ n4) != 0x0;
                    if (u == 0 && !b) {}
                    array8[n2] = (b ? 1 : 0);
                    this.a(array, array[1][0] = array2[1][0] + array3[1][0]);
                    final int[] array10 = array2[0];
                    final int[] array11 = array3[0];
                    final int[] array12 = array[0];
                    final int n5 = array[1][0];
                    int n6 = n5 - 1;
                    int n7;
                    while (true) {
                        while (true) {
                            Label_0204: {
                                if (u == 0) {
                                    break Label_0204;
                                }
                                array12[n6] = 0;
                                --n6;
                            }
                            if (n6 >= 0) {
                                continue;
                            }
                            break;
                        }
                        n7 = 0;
                        if (u != 0) {
                            continue;
                        }
                        break;
                    }
                    while (true) {
                        Label_0320: {
                            if (u == 0) {
                                break Label_0320;
                            }
                            long n8 = 0L;
                            final long n9 = array10[n7];
                            int n10 = n7;
                            int n11 = 0;
                            int[] array13 = null;
                            int n13 = 0;
                            while (true) {
                                Label_0294: {
                                    if (u == 0) {
                                        break Label_0294;
                                    }
                                    final long n12 = array13[n13] + (array11[n11] * n9 + n8);
                                    n8 = n12 >>> 30;
                                    array12[n10++] = ((int)n12 & 0x3FFFFFFF);
                                    ++n11;
                                }
                                if (n11 < array3[1][0]) {
                                    continue;
                                }
                                array13 = array12;
                                n13 = n10;
                                if (u != 0) {
                                    continue;
                                }
                                break;
                            }
                            array13[n13] = (int)n8;
                            ++n7;
                        }
                        if (n7 >= array2[1][0]) {
                            int[] array15;
                            final int[] array14 = array15 = array12;
                            int n15;
                            final int n14 = n15 = n5 - 1;
                            if (u == 0) {
                                if (array14[n14] != 0) {
                                    return;
                                }
                                final int[] array16;
                                array15 = (array16 = array[1]);
                                final int n16;
                                n15 = (n16 = 0);
                            }
                            array15[n15] = array14[n14] - 1;
                            return;
                        }
                        continue;
                    }
                }
            }
            array[0][0] = 0;
            array[1][1] = 0;
            final int[] array17 = array[1];
        }
        array7[n] = 0;
    }
    
    private final void a(final int[][] array, final int[][] array2, final int[][] array3, final int[][] array4, final int[][] array5, final short n) {
        final int[][] b = this.b();
        final int[][] b2 = this.b();
        final int[][] b3 = this.b();
        final int[][] b4 = this.b();
        this.e(b, array2, array3);
        this.b(b4, b, n - 1);
        this.e(b2, b4, array5);
        this.b(b3, b2, n - 1);
        this.e(b2, array4, b3);
        this.c(array, b, b2);
        int n2 = 0;
        while (true) {
            Label_0122: {
                if (Lware.u == 0) {
                    break Label_0122;
                }
                if (n2++ > 2) {
                    System.out.println(b("f.\u0006\u001aRga\u0004\u0016Ng"));
                }
                this.c(array, array, array4);
            }
            if (this.c(array, array4) < 0) {
                return;
            }
            continue;
        }
    }
    
    private final boolean b(final int[][] array, int n) {
        final int n2 = n % 30;
        n /= 30;
        return n < array[1][0] && (array[0][n] & 1L << n2) != 0x0L;
    }
    
    private final void b(final int[][] array, final int[][] array2, final int[][] array3, final int[][] array4) {
        final int u = Lware.u;
        final int[][] b = this.b();
        final int[][] b2 = this.b();
        this.d(b2, array2, array4);
        final int a = this.a(array3);
        final int[] array5 = array3[0];
        final int n = 0;
        Label_0079: {
            if (u == 0) {
                if ((array5[n] & 0x1) != 0x0) {
                    this.d(array, array2, array4);
                    if (u == 0) {
                        break Label_0079;
                    }
                }
                array[0][0] = 1;
                array[1][1] = 0;
                final int[] array6 = array[1];
            }
            array5[n] = 1;
        }
        final int d = this.d(b, array4);
        int n2 = 1;
        while (true) {
            Label_0147: {
                if (u == 0) {
                    break Label_0147;
                }
                this.a(b2, b2, b2, array4, b, (short)d);
                Lware lware = this;
                int[][] array7 = array3;
                Label_0144: {
                    if (u == 0) {
                        if (!this.b(array3, n2)) {
                            break Label_0144;
                        }
                        lware = this;
                        array7 = array;
                    }
                    lware.a(array7, array, b2, array4, b, (short)d);
                }
                ++n2;
            }
            if (n2 >= a) {
                return;
            }
            continue;
        }
    }
    
    private final int a(final int[][] array, final byte[] array2) {
        final int u = Lware.u;
        final int n = (this.a(array) + 7) / 8;
        final int length = array2.length;
        if (u == 0 && length < n) {
            System.out.println(b("b/\u0000\u001eI+#\u0017\u0011\u0007x,\u0003\u001bK"));
            goto Label_0039;
        }
        int n2 = length;
        int n3 = 0;
        int n4 = n - 1;
        while (true) {
            while (true) {
                Label_0137: {
                    if (u == 0) {
                        break Label_0137;
                    }
                    final int n5 = (int)(array[0][n2] >>> n3 & 0xFFL);
                    final int n7;
                    int n6 = n7;
                    n3 += 8;
                    Label_0134: {
                        if (u == 0) {
                            if (n3 >= 30) {
                                n3 -= 30;
                                ++n2;
                                if (u != 0) {
                                    break Label_0134;
                                }
                                if (n3 > 0) {
                                    n6 |= (int)(array[0][n2] << 8 - n3 & 0xFFL);
                                }
                            }
                            array2[n4] = (byte)n6;
                        }
                    }
                    --n4;
                }
                if (n4 >= 0) {
                    continue;
                }
                break;
            }
            final int n7 = n;
            if (u == 0) {
                return n7;
            }
            continue;
        }
    }
    
    private final void a(final byte[] array, final int[][] array2) {
        final int u = Lware.u;
        array2[1][1] = 0;
        this.a(array2, array2[1][0] = (array.length * 8 + 30 - 1) / 30);
        int n = 0;
        array2[0][n] = 0;
        int n2 = 0;
        int n3 = array.length - 1;
        while (true) {
            Label_0140: {
                if (u == 0) {
                    break Label_0140;
                }
                final int n4 = array[n3] & 0xFF;
                final int[] array3 = array2[0];
                final int n5 = n;
                array3[n5] |= (n4 << n2 & 0x3FFFFFFF);
                n2 += 8;
                final int n6 = n2;
                Label_0137: {
                    final int[] array4;
                    final int n7;
                    int n9 = 0;
                    Label_0136: {
                        if (u == 0) {
                            if (n6 < 30) {
                                break Label_0137;
                            }
                            ++n;
                            array4 = array2[0];
                            n7 = n;
                            final int n8 = n9 = 0;
                            if (u != 0) {
                                break Label_0136;
                            }
                            array4[n7] = n8;
                            n2 -= 30;
                        }
                        if (n6 <= 0) {
                            break Label_0137;
                        }
                        final int[] array5 = array2[0];
                        n9 = n4 >>> 8 - n2;
                    }
                    array4[n7] = n9;
                }
                --n3;
            }
            if (n3 < 0) {
                while (array2[1][0] > 0) {
                    int[] array7;
                    final int[] array6 = array7 = array2[0];
                    int n11;
                    final int n10 = n11 = array2[1][0] - 1;
                    if (u == 0) {
                        if (array6[n10] != 0) {
                            break;
                        }
                        final int[] array8;
                        array7 = (array8 = array2[1]);
                        final int n12;
                        n11 = (n12 = 0);
                    }
                    array7[n11] = array6[n10] - 1;
                }
                return;
            }
            continue;
        }
    }
    
    private static String b(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = '\u000b';
                    break;
                }
                case 1: {
                    c2 = 'A';
                    break;
                }
                case 2: {
                    c2 = 'b';
                    break;
                }
                case 3: {
                    c2 = 'w';
                    break;
                }
                default: {
                    c2 = '\'';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
