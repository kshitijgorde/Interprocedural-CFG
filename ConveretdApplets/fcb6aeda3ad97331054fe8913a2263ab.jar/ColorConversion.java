import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import java.awt.Container;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.text.Document;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import java.awt.event.KeyListener;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ColorConversion extends JApplet implements KeyListener, ChangeListener
{
    private JSlider a;
    private JSlider b;
    private JSlider c;
    private JSeparator d;
    private JTextField e;
    private JTextField f;
    private JTextField g;
    private JTextField h;
    private JTextField i;
    private JTextField j;
    private JTextField k;
    private JTextField l;
    private JTextField m;
    private JTextField n;
    private JLabel o;
    private JLabel p;
    private JLabel q;
    private JLabel r;
    private JLabel s;
    private JLabel t;
    private JLabel u;
    private JLabel v;
    private JLabel w;
    private JLabel x;
    private JLabel y;
    private JLabel z;
    private JPanel A;
    private JPanel B;
    private JPanel C;
    private JPanel D;
    private JPanel E;
    private JPanel F;
    private JPanel G;
    private JPanel H;
    private JPanel I;
    private JPanel J;
    private JPanel K;
    private JPanel L;
    private JPanel M;
    private JPanel N;
    private JPanel O;
    private JPanel P;
    int Q;
    private String R;
    private String S;
    private String T;
    private String U;
    private String V;
    private String W;
    private String X;
    public static int Y;
    private static String[] Z;
    
    public ColorConversion() {
        this.Q = 0;
        this.R = ColorConversion.Z[7];
        this.S = ColorConversion.Z[8];
        this.T = ColorConversion.Z[11];
        this.U = ColorConversion.Z[6];
        this.V = ColorConversion.Z[9];
        this.W = ColorConversion.Z[5];
        this.X = ColorConversion.Z[10];
    }
    
    public void init() {
        final int y = ColorConversion.Y;
        final Container contentPane = this.getContentPane();
        (this.a = new JSlider(0, 0, 255, 0)).setPaintTicks(true);
        this.a.setPaintLabels(true);
        this.a.setMajorTickSpacing(40);
        this.a.setMinorTickSpacing(20);
        this.a.addChangeListener(this);
        (this.b = new JSlider(0, 0, 255, 0)).setPaintTicks(true);
        this.b.setPaintLabels(true);
        this.b.setMajorTickSpacing(40);
        this.b.setMinorTickSpacing(20);
        this.b.addChangeListener(this);
        (this.c = new JSlider(0, 0, 255, 0)).setPaintTicks(true);
        this.c.setPaintLabels(true);
        this.c.setMajorTickSpacing(40);
        this.c.setMinorTickSpacing(20);
        this.c.addChangeListener(this);
        (this.A = new JPanel()).setLayout(new FlowLayout());
        this.A.add(this.a);
        this.A.add(this.b);
        this.A.add(this.c);
        (this.x = new JLabel(ColorConversion.Z[27])).setVerticalAlignment(1);
        (this.y = new JLabel(ColorConversion.Z[31])).setVerticalAlignment(1);
        (this.z = new JLabel(ColorConversion.Z[29])).setVerticalAlignment(1);
        (this.B = new JPanel()).setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        this.B.setLayout(new GridLayout(3, 1));
        this.B.add(this.x);
        this.B.add(this.y);
        this.B.add(this.z);
        (this.w = new JLabel(ColorConversion.Z[16])).setHorizontalAlignment(0);
        (this.v = new JLabel(ColorConversion.Z[17])).setHorizontalAlignment(0);
        (this.l = new JTextField(3)).setDocument(new a(3, ColorConversion.Z[15], true));
        this.l.setText("");
        this.l.addKeyListener(this);
        (this.m = new JTextField("", 3)).setDocument(new a(3, ColorConversion.Z[15], true));
        this.m.setText("");
        this.m.addKeyListener(this);
        (this.n = new JTextField("", 3)).setDocument(new a(3, ColorConversion.Z[15], true));
        this.n.setText("");
        this.n.addKeyListener(this);
        (this.C = new JPanel()).setLayout(new GridLayout(4, 1, 0, 20));
        this.C.add(this.w);
        this.C.add(this.l);
        this.C.add(this.m);
        this.C.add(this.n);
        (this.i = new JTextField("", 2)).setBackground(Color.white);
        this.i.setBorder(BorderFactory.createEtchedBorder(Color.white, new Color(104, 104, 104)));
        this.i.setEditable(false);
        (this.j = new JTextField("", 2)).setBackground(Color.white);
        this.j.setBorder(BorderFactory.createEtchedBorder(Color.white, new Color(104, 104, 104)));
        this.j.setEditable(false);
        (this.k = new JTextField("", 2)).setBackground(Color.white);
        this.k.setBorder(BorderFactory.createEtchedBorder(Color.white, new Color(104, 104, 104)));
        this.k.setEditable(false);
        (this.D = new JPanel()).setLayout(new GridLayout(4, 1, 0, 20));
        this.D.add(this.v);
        this.D.add(this.i);
        this.D.add(this.j);
        this.D.add(this.k);
        (this.E = new JPanel()).setBorder(BorderFactory.createEmptyBorder(5, 0, 65, 0));
        this.E.setLayout(new FlowLayout());
        this.E.add(this.C);
        this.E.add(this.D);
        (this.F = new JPanel()).setLayout(new BorderLayout());
        this.F.add(this.B, ColorConversion.Z[26]);
        this.F.add(this.A, ColorConversion.Z[30]);
        (this.p = new JLabel(ColorConversion.Z[13])).setHorizontalAlignment(4);
        (this.f = new JTextField("", 3)).setBackground(Color.white);
        this.f.setBorder(BorderFactory.createEtchedBorder(Color.white, new Color(104, 104, 104)));
        this.f.setEditable(false);
        (this.H = new JPanel()).setLayout(new BorderLayout(2, 0));
        this.H.add(this.p, ColorConversion.Z[26]);
        this.H.add(this.f, ColorConversion.Z[30]);
        (this.q = new JLabel(ColorConversion.Z[22])).setHorizontalAlignment(4);
        (this.g = new JTextField("", 3)).setBackground(Color.white);
        this.g.setBorder(BorderFactory.createEtchedBorder(Color.white, new Color(104, 104, 104)));
        this.g.setEditable(false);
        (this.I = new JPanel()).setLayout(new BorderLayout(2, 0));
        this.I.add(this.q, ColorConversion.Z[26]);
        this.I.add(this.g, ColorConversion.Z[30]);
        (this.r = new JLabel(ColorConversion.Z[21])).setHorizontalAlignment(4);
        (this.h = new JTextField("", 3)).setBackground(Color.white);
        this.h.setBorder(BorderFactory.createEtchedBorder(Color.white, new Color(104, 104, 104)));
        this.h.setEditable(false);
        (this.J = new JPanel()).setLayout(new BorderLayout(2, 0));
        this.J.add(this.r, ColorConversion.Z[26]);
        this.J.add(this.h, ColorConversion.Z[30]);
        this.o = new JLabel(ColorConversion.Z[20]);
        (this.e = new JTextField(7)).setText("");
        this.e.setDocument(new a(6, ColorConversion.Z[28], false));
        this.e.addKeyListener(this);
        this.t = new JLabel(ColorConversion.Z[12]);
        (this.K = new JPanel()).setLayout(new GridLayout(1, 3));
        this.K.add(this.H);
        this.K.add(this.I);
        this.K.add(this.J);
        (this.L = new JPanel()).setLayout(new GridLayout(2, 2, 0, 5));
        this.L.setBorder(BorderFactory.createEmptyBorder(5, 10, 15, 10));
        this.L.add(this.o);
        this.L.add(this.e);
        this.L.add(this.t);
        this.L.add(this.K);
        this.d = new JSeparator();
        (this.M = new JPanel()).setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        this.M.setLayout(new GridLayout(1, 1));
        this.M.add(this.d);
        (this.u = new JLabel(ColorConversion.Z[24])).setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        (this.s = new JLabel(ColorConversion.Z[18])).setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.s.setBackground(new Color(0, 0, 0));
        this.s.setOpaque(true);
        (this.G = new JPanel()).setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(Color.white, new Color(102, 102, 153)), ColorConversion.Z[19], 1, 0));
        this.G.setLayout(new GridLayout(1, 1));
        this.G.add(this.s);
        (this.P = new JPanel()).setLayout(new BorderLayout());
        this.P.add(this.u, ColorConversion.Z[25]);
        this.P.add(this.F, ColorConversion.Z[30]);
        (this.N = new JPanel()).setLayout(new BorderLayout());
        this.N.add(this.L, ColorConversion.Z[30]);
        this.N.add(this.M, ColorConversion.Z[14]);
        (this.O = new JPanel()).setBorder(BorderFactory.createEmptyBorder(0, 5, 3, 5));
        this.O.setLayout(new BorderLayout());
        this.O.add(this.E, ColorConversion.Z[23]);
        this.O.add(this.P, ColorConversion.Z[30]);
        this.O.add(this.G, ColorConversion.Z[14]);
        contentPane.setLayout(new BorderLayout());
        contentPane.add(this.N, ColorConversion.Z[25]);
        contentPane.add(this.O, ColorConversion.Z[30]);
        this.e.requestFocus();
        if (y != 0) {
            a.f = !a.f;
        }
    }
    
    public String a(final int n, String string) {
        string = String.valueOf(Character.forDigit(n / 16, 16)).toUpperCase() + String.valueOf(Character.forDigit(n % 16, 16)).toUpperCase();
        return string;
    }
    
    public String a(final String s, final int n, final int n2) {
        this.Q = Character.digit(s.charAt(n), 16) * 16 + Character.digit(s.charAt(n2), 16);
        return String.valueOf(this.Q);
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        int y = ColorConversion.Y;
        if (!this.getParameter(ColorConversion.Z[1]).equals(this.S) || !this.getParameter(ColorConversion.Z[0]).equals(this.T)) {
            JOptionPane.showMessageDialog(this, this.V + this.W + this.X, ColorConversion.Z[2], 0);
            System.exit(0);
        }
        this.showStatus(this.R);
        final String s = "";
        if (changeEvent.getSource() == this.a) {
            this.l.setText("" + this.a.getValue());
            this.i.setText(this.a(Integer.parseInt(this.l.getText()), s));
        }
        if (changeEvent.getSource() == this.b) {
            this.m.setText("" + this.b.getValue());
            this.j.setText(this.a(Integer.parseInt(this.m.getText()), s));
        }
        if (changeEvent.getSource() == this.c) {
            this.n.setText("" + this.c.getValue());
            this.k.setText(this.a(Integer.parseInt(this.n.getText()), s));
        }
        this.s.setBackground(new Color(this.a.getValue(), this.b.getValue(), this.c.getValue()));
        if (a.f) {
            ColorConversion.Y = ++y;
        }
    }
    
    public void a(final JTextField textField, final JTextField textField2, final JSlider slider, int int1) {
        if (textField.getText().length() > 0 && textField.getText().length() <= 3) {
            int1 = Integer.parseInt(textField.getText());
            if (int1 <= 255) {
                slider.setValue(int1);
                if (ColorConversion.Y == 0) {
                    return;
                }
            }
            slider.setValue(255);
            textField.setText(ColorConversion.Z[3]);
            textField2.setText(ColorConversion.Z[4]);
        }
    }
    
    public void a() {
        this.l.setText("");
        this.i.setText("");
        this.a.setValue(0);
        this.m.setText("");
        this.j.setText("");
        this.b.setValue(0);
        this.n.setText("");
        this.k.setText("");
        this.c.setValue(0);
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (!this.getParameter(ColorConversion.Z[1]).equals(this.S) || !this.getParameter(ColorConversion.Z[0]).equals(this.T)) {
            JOptionPane.showMessageDialog(this, this.V + this.W + this.X, ColorConversion.Z[2], 0);
            System.exit(0);
        }
        this.showStatus(this.R);
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        final int keyCode = keyEvent.getKeyCode();
        final String text = this.e.getText();
        final int n = 0;
        final int n2 = 0;
        final int n3 = 0;
        if (keyEvent.getSource() == this.l) {
            this.a(this.l, this.i, this.a, n);
        }
        if (keyEvent.getSource() == this.m) {
            this.a(this.m, this.j, this.b, n2);
        }
        if (keyEvent.getSource() == this.n) {
            this.a(this.n, this.k, this.c, n3);
        }
        if (keyEvent.getSource() == this.e && ((keyCode >= 65 && keyCode <= 70) || (keyCode >= 48 && keyCode <= 57))) {
            this.a();
            if (text.length() <= 2) {
                this.f.setText("");
                this.g.setText("");
                this.h.setText("");
            }
            if (text.length() == 2) {
                this.f.setText(this.a(text.substring(0, text.length()), 0, 1));
                this.s.setBackground(new Color(Integer.parseInt(this.f.getText()), 0, 0));
            }
            if (text.length() <= 4) {
                this.g.setText("");
                this.h.setText("");
            }
            if (text.length() == 4) {
                final String substring = text.substring(0, text.length());
                this.f.setText(this.a(substring, 0, 1));
                this.g.setText(this.a(substring, 2, 3));
                this.s.setBackground(new Color(Integer.parseInt(this.f.getText()), Integer.parseInt(this.g.getText()), 0));
            }
            if (text.length() <= 6) {
                this.h.setText("");
            }
            if (text.length() == 6) {
                final String substring2 = text.substring(0, text.length());
                this.f.setText(this.a(substring2, 0, 1));
                this.g.setText(this.a(substring2, 2, 3));
                this.h.setText(this.a(substring2, 4, 5));
                this.s.setBackground(new Color(Integer.parseInt(this.f.getText()), Integer.parseInt(this.g.getText()), Integer.parseInt(this.h.getText())));
            }
        }
    }
    
    static {
        final String[] z = new String[32];
        final int n = 0;
        final char[] charArray = "bx\u0007-_i".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '\u0010';
                    break;
                }
                case 1: {
                    c2 = '\u001d';
                    break;
                }
                case 2: {
                    c2 = '`';
                    break;
                }
                case 3: {
                    c2 = 'F';
                    break;
                }
                default: {
                    c2 = ':';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "\u007fj\u000e#H".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '\u0010';
                    break;
                }
                case 1: {
                    c4 = '\u001d';
                    break;
                }
                case 2: {
                    c4 = '`';
                    break;
                }
                case 3: {
                    c4 = 'F';
                    break;
                }
                default: {
                    c4 = ':';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "Ys\u0003)Hbx\u00032\u001aBx\u0007/Ido\u00012S\u007fs@\u000fTvr@\u0003Hbr\u0012".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = '\u0010';
                    break;
                }
                case 1: {
                    c6 = '\u001d';
                    break;
                }
                case 2: {
                    c6 = '`';
                    break;
                }
                case 3: {
                    c6 = 'F';
                    break;
                }
                default: {
                    c6 = ':';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "\"(U".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = '\u0010';
                    break;
                }
                case 1: {
                    c8 = '\u001d';
                    break;
                }
                case 2: {
                    c8 = '`';
                    break;
                }
                case 3: {
                    c8 = 'F';
                    break;
                }
                default: {
                    c8 = ':';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "V[".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = '\u0010';
                    break;
                }
                case 1: {
                    c10 = '\u001d';
                    break;
                }
                case 2: {
                    c10 = '`';
                    break;
                }
                case 3: {
                    c10 = 'F';
                    break;
                }
                default: {
                    c10 = ':';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "0{\u0012)W0j\u000f4Qys\u0007fJbr\u0010#H|dNLn\u007f=\u0012#W\u007fk\u0005fNxx@'Odu\u000f4\u001dc=\t(\\\u007fo\r'Nyr\u000efC\u007fh@+Ocij".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = '\u0010';
                    break;
                }
                case 1: {
                    c12 = '\u001d';
                    break;
                }
                case 2: {
                    c12 = '`';
                    break;
                }
                case 3: {
                    c12 = 'F';
                    break;
                }
                default: {
                    c12 = ':';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "@q\u0005'Iu=\u00104Uft\u0004#\u001adu\u0005fY\u007fo\u0012#Yd=\u0016'Vex\u0013LS~=\u0014._0r\u0017(_b=\u0001(^0o\u0005!Qud@6[b|\r#Nuo\u0013g".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = '\u0010';
                    break;
                }
                case 1: {
                    c14 = '\u001d';
                    break;
                }
                case 2: {
                    c14 = '`';
                    break;
                }
                case 3: {
                    c14 = 'F';
                    break;
                }
                default: {
                    c14 = ':';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "Qm\u0010*_d=\u0002?\u00000Q\t5[0V\u0001(_0j\u00171\u0014z|\u0016'\\\u007fe\u0018hY\u007fp".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = '\u0010';
                    break;
                }
                case 1: {
                    c16 = '\u001d';
                    break;
                }
                case 2: {
                    c16 = '`';
                    break;
                }
                case 3: {
                    c16 = 'F';
                    break;
                }
                default: {
                    c16 = ':';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        z[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "\\t\u0013'qqs\u0005\u0006Pqk\u0001 UheN%U}".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = '\u0010';
                    break;
                }
                case 1: {
                    c18 = '\u001d';
                    break;
                }
                case 2: {
                    c18 = '`';
                    break;
                }
                case 3: {
                    c18 = 'F';
                    break;
                }
                default: {
                    c18 = ':';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        z[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = "]r\u0004/\\y~\u00012S\u007fs\u0013fN\u007f=\u0014._0r\u0017(_b=\u0001(^?r\u0012fHuz\u000b#C0k\u0001*Oun@1S|q@f\u001a0=@f0`o\u00050_~i@2Ryn@'J`q\u00052".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = '\u0010';
                    break;
                }
                case 1: {
                    c20 = '\u001d';
                    break;
                }
                case 2: {
                    c20 = '`';
                    break;
                }
                case 3: {
                    c20 = 'F';
                    break;
                }
                default: {
                    c20 = ':';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        z[n28] = new String(charArray10).intern();
        final int n31 = 10;
        final char[] charArray11 = "`h\u0012%Rqn\u0005fNxt\u0013f[`m\f#N1".toCharArray();
        final int length7 = charArray11.length;
        for (int n32 = 0; length7 > n32; ++n32) {
            final int n33 = n32;
            final char c21 = charArray11[n33];
            char c22 = '\0';
            switch (n32 % 5) {
                case 0: {
                    c22 = '\u0010';
                    break;
                }
                case 1: {
                    c22 = '\u001d';
                    break;
                }
                case 2: {
                    c22 = '`';
                    break;
                }
                case 3: {
                    c22 = 'F';
                    break;
                }
                default: {
                    c22 = ':';
                    break;
                }
            }
            charArray11[n33] = (char)(c21 ^ c22);
        }
        z[n31] = new String(charArray11).intern();
        final int n34 = 11;
        final char[] charArray12 = " -Wkv[0Vs\t&".toCharArray();
        final int length8 = charArray12.length;
        for (int n35 = 0; length8 > n35; ++n35) {
            final int n36 = n35;
            final char c23 = charArray12[n36];
            char c24 = '\0';
            switch (n35 % 5) {
                case 0: {
                    c24 = '\u0010';
                    break;
                }
                case 1: {
                    c24 = '\u001d';
                    break;
                }
                case 2: {
                    c24 = '`';
                    break;
                }
                case 3: {
                    c24 = 'F';
                    break;
                }
                default: {
                    c24 = ':';
                    break;
                }
            }
            charArray12[n36] = (char)(c23 ^ c24);
        }
        z[n34] = new String(charArray12).intern();
        final int n37 = 12;
        final char[] charArray13 = "BZ\"fLqq\u0015#\u001avr\u0012fC\u007fh\u0012fRueZf".toCharArray();
        final int length9 = charArray13.length;
        for (int n38 = 0; length9 > n38; ++n38) {
            final int n39 = n38;
            final char c25 = charArray13[n39];
            char c26 = '\0';
            switch (n38 % 5) {
                case 0: {
                    c26 = '\u0010';
                    break;
                }
                case 1: {
                    c26 = '\u001d';
                    break;
                }
                case 2: {
                    c26 = '`';
                    break;
                }
                case 3: {
                    c26 = 'F';
                    break;
                }
                default: {
                    c26 = ':';
                    break;
                }
            }
            charArray13[n39] = (char)(c25 ^ c26);
        }
        z[n37] = new String(charArray13).intern();
        final int n40 = 13;
        final char[] charArray14 = "0=2".toCharArray();
        final int length10 = charArray14.length;
        for (int n41 = 0; length10 > n41; ++n41) {
            final int n42 = n41;
            final char c27 = charArray14[n42];
            char c28 = '\0';
            switch (n41 % 5) {
                case 0: {
                    c28 = '\u0010';
                    break;
                }
                case 1: {
                    c28 = '\u001d';
                    break;
                }
                case 2: {
                    c28 = '`';
                    break;
                }
                case 3: {
                    c28 = 'F';
                    break;
                }
                default: {
                    c28 = ':';
                    break;
                }
            }
            charArray14[n42] = (char)(c27 ^ c28);
        }
        z[n40] = new String(charArray14).intern();
        final int n43 = 14;
        final char[] charArray15 = "Cr\u00152R".toCharArray();
        final int length11 = charArray15.length;
        for (int n44 = 0; length11 > n44; ++n44) {
            final int n45 = n44;
            final char c29 = charArray15[n45];
            char c30 = '\0';
            switch (n44 % 5) {
                case 0: {
                    c30 = '\u0010';
                    break;
                }
                case 1: {
                    c30 = '\u001d';
                    break;
                }
                case 2: {
                    c30 = '`';
                    break;
                }
                case 3: {
                    c30 = 'F';
                    break;
                }
                default: {
                    c30 = ':';
                    break;
                }
            }
            charArray15[n45] = (char)(c29 ^ c30);
        }
        z[n43] = new String(charArray15).intern();
        final int n46 = 15;
        final char[] charArray16 = " ,Ru\u000e%+W~\u0003".toCharArray();
        final int length12 = charArray16.length;
        for (int n47 = 0; length12 > n47; ++n47) {
            final int n48 = n47;
            final char c31 = charArray16[n48];
            char c32 = '\0';
            switch (n47 % 5) {
                case 0: {
                    c32 = '\u0010';
                    break;
                }
                case 1: {
                    c32 = '\u001d';
                    break;
                }
                case 2: {
                    c32 = '`';
                    break;
                }
                case 3: {
                    c32 = 'F';
                    break;
                }
                default: {
                    c32 = ':';
                    break;
                }
            }
            charArray16[n48] = (char)(c31 ^ c32);
        }
        z[n46] = new String(charArray16).intern();
        final int n49 = 16;
        final char[] charArray17 = "BZ\"".toCharArray();
        final int length13 = charArray17.length;
        for (int n50 = 0; length13 > n50; ++n50) {
            final int n51 = n50;
            final char c33 = charArray17[n51];
            char c34 = '\0';
            switch (n50 % 5) {
                case 0: {
                    c34 = '\u0010';
                    break;
                }
                case 1: {
                    c34 = '\u001d';
                    break;
                }
                case 2: {
                    c34 = '`';
                    break;
                }
                case 3: {
                    c34 = 'F';
                    break;
                }
                default: {
                    c34 = ':';
                    break;
                }
            }
            charArray17[n51] = (char)(c33 ^ c34);
        }
        z[n49] = new String(charArray17).intern();
        final int n52 = 17;
        final char[] charArray18 = "Xx\u0018".toCharArray();
        final int length14 = charArray18.length;
        for (int n53 = 0; length14 > n53; ++n53) {
            final int n54 = n53;
            final char c35 = charArray18[n54];
            char c36 = '\0';
            switch (n53 % 5) {
                case 0: {
                    c36 = '\u0010';
                    break;
                }
                case 1: {
                    c36 = '\u001d';
                    break;
                }
                case 2: {
                    c36 = '`';
                    break;
                }
                case 3: {
                    c36 = 'F';
                    break;
                }
                default: {
                    c36 = ':';
                    break;
                }
            }
            charArray18[n54] = (char)(c35 ^ c36);
        }
        z[n52] = new String(charArray18).intern();
        final int n55 = 18;
        final char[] charArray19 = "0=@f\u001a0=".toCharArray();
        final int length15 = charArray19.length;
        for (int n56 = 0; length15 > n56; ++n56) {
            final int n57 = n56;
            final char c37 = charArray19[n57];
            char c38 = '\0';
            switch (n56 % 5) {
                case 0: {
                    c38 = '\u0010';
                    break;
                }
                case 1: {
                    c38 = '\u001d';
                    break;
                }
                case 2: {
                    c38 = '`';
                    break;
                }
                case 3: {
                    c38 = 'F';
                    break;
                }
                default: {
                    c38 = ':';
                    break;
                }
            }
            charArray19[n57] = (char)(c37 ^ c38);
        }
        z[n55] = new String(charArray19).intern();
        final int n58 = 19;
        final char[] charArray20 = "Sr\f)H0M\u0012#Lyx\u0017".toCharArray();
        final int length16 = charArray20.length;
        for (int n59 = 0; length16 > n59; ++n59) {
            final int n60 = n59;
            final char c39 = charArray20[n60];
            char c40 = '\0';
            switch (n59 % 5) {
                case 0: {
                    c40 = '\u0010';
                    break;
                }
                case 1: {
                    c40 = '\u001d';
                    break;
                }
                case 2: {
                    c40 = '`';
                    break;
                }
                case 3: {
                    c40 = 'F';
                    break;
                }
                default: {
                    c40 = ':';
                    break;
                }
            }
            charArray20[n60] = (char)(c39 ^ c40);
        }
        z[n58] = new String(charArray20).intern();
        final int n61 = 20;
        final char[] charArray21 = "Dd\u0010#\u001air\u00154\u001a&=\u0004/]yi@._h'@f\u001a0=@e".toCharArray();
        final int length17 = charArray21.length;
        for (int n62 = 0; length17 > n62; ++n62) {
            final int n63 = n62;
            final char c41 = charArray21[n63];
            char c42 = '\0';
            switch (n62 % 5) {
                case 0: {
                    c42 = '\u0010';
                    break;
                }
                case 1: {
                    c42 = '\u001d';
                    break;
                }
                case 2: {
                    c42 = '`';
                    break;
                }
                case 3: {
                    c42 = 'F';
                    break;
                }
                default: {
                    c42 = ':';
                    break;
                }
            }
            charArray21[n63] = (char)(c41 ^ c42);
        }
        z[n61] = new String(charArray21).intern();
        final int n64 = 21;
        final char[] charArray22 = "0=\"".toCharArray();
        final int length18 = charArray22.length;
        for (int n65 = 0; length18 > n65; ++n65) {
            final int n66 = n65;
            final char c43 = charArray22[n66];
            char c44 = '\0';
            switch (n65 % 5) {
                case 0: {
                    c44 = '\u0010';
                    break;
                }
                case 1: {
                    c44 = '\u001d';
                    break;
                }
                case 2: {
                    c44 = '`';
                    break;
                }
                case 3: {
                    c44 = 'F';
                    break;
                }
                default: {
                    c44 = ':';
                    break;
                }
            }
            charArray22[n66] = (char)(c43 ^ c44);
        }
        z[n64] = new String(charArray22).intern();
        final int n67 = 22;
        final char[] charArray23 = "0='".toCharArray();
        final int length19 = charArray23.length;
        for (int n68 = 0; length19 > n68; ++n68) {
            final int n69 = n68;
            final char c45 = charArray23[n69];
            char c46 = '\0';
            switch (n68 % 5) {
                case 0: {
                    c46 = '\u0010';
                    break;
                }
                case 1: {
                    c46 = '\u001d';
                    break;
                }
                case 2: {
                    c46 = '`';
                    break;
                }
                case 3: {
                    c46 = 'F';
                    break;
                }
                default: {
                    c46 = ':';
                    break;
                }
            }
            charArray23[n69] = (char)(c45 ^ c46);
        }
        z[n67] = new String(charArray23).intern();
        final int n70 = 23;
        final char[] charArray24 = "U|\u00132".toCharArray();
        final int length20 = charArray24.length;
        for (int n71 = 0; length20 > n71; ++n71) {
            final int n72 = n71;
            final char c47 = charArray24[n72];
            char c48 = '\0';
            switch (n71 % 5) {
                case 0: {
                    c48 = '\u0010';
                    break;
                }
                case 1: {
                    c48 = '\u001d';
                    break;
                }
                case 2: {
                    c48 = '`';
                    break;
                }
                case 3: {
                    c48 = 'F';
                    break;
                }
                default: {
                    c48 = ':';
                    break;
                }
            }
            charArray24[n72] = (char)(c47 ^ c48);
        }
        z[n70] = new String(charArray24).intern();
        final int n73 = 24;
        final char[] charArray25 = "So\u0005'Nu=!ftuj@\u0005U|r\u0012|".toCharArray();
        final int length21 = charArray25.length;
        for (int n74 = 0; length21 > n74; ++n74) {
            final int n75 = n74;
            final char c49 = charArray25[n75];
            char c50 = '\0';
            switch (n74 % 5) {
                case 0: {
                    c50 = '\u0010';
                    break;
                }
                case 1: {
                    c50 = '\u001d';
                    break;
                }
                case 2: {
                    c50 = '`';
                    break;
                }
                case 3: {
                    c50 = 'F';
                    break;
                }
                default: {
                    c50 = ':';
                    break;
                }
            }
            charArray25[n75] = (char)(c49 ^ c50);
        }
        z[n73] = new String(charArray25).intern();
        final int n76 = 25;
        final char[] charArray26 = "^r\u00122R".toCharArray();
        final int length22 = charArray26.length;
        for (int n77 = 0; length22 > n77; ++n77) {
            final int n78 = n77;
            final char c51 = charArray26[n78];
            char c52 = '\0';
            switch (n77 % 5) {
                case 0: {
                    c52 = '\u0010';
                    break;
                }
                case 1: {
                    c52 = '\u001d';
                    break;
                }
                case 2: {
                    c52 = '`';
                    break;
                }
                case 3: {
                    c52 = 'F';
                    break;
                }
                default: {
                    c52 = ':';
                    break;
                }
            }
            charArray26[n78] = (char)(c51 ^ c52);
        }
        z[n76] = new String(charArray26).intern();
        final int n79 = 26;
        final char[] charArray27 = "Gx\u00132".toCharArray();
        final int length23 = charArray27.length;
        for (int n80 = 0; length23 > n80; ++n80) {
            final int n81 = n80;
            final char c53 = charArray27[n81];
            char c54 = '\0';
            switch (n80 % 5) {
                case 0: {
                    c54 = '\u0010';
                    break;
                }
                case 1: {
                    c54 = '\u001d';
                    break;
                }
                case 2: {
                    c54 = '`';
                    break;
                }
                case 3: {
                    c54 = 'F';
                    break;
                }
                default: {
                    c54 = ':';
                    break;
                }
            }
            charArray27[n81] = (char)(c53 ^ c54);
        }
        z[n79] = new String(charArray27).intern();
        final int n82 = 27;
        final char[] charArray28 = "B=".toCharArray();
        final int length24 = charArray28.length;
        for (int n83 = 0; length24 > n83; ++n83) {
            final int n84 = n83;
            final char c55 = charArray28[n84];
            char c56 = '\0';
            switch (n83 % 5) {
                case 0: {
                    c56 = '\u0010';
                    break;
                }
                case 1: {
                    c56 = '\u001d';
                    break;
                }
                case 2: {
                    c56 = '`';
                    break;
                }
                case 3: {
                    c56 = 'F';
                    break;
                }
                default: {
                    c56 = ':';
                    break;
                }
            }
            charArray28[n84] = (char)(c55 ^ c56);
        }
        z[n82] = new String(charArray28).intern();
        final int n85 = 28;
        final char[] charArray29 = " ,Ru\u000e%+W~\u0003Q_#\u0002\u007fV".toCharArray();
        final int length25 = charArray29.length;
        for (int n86 = 0; length25 > n86; ++n86) {
            final int n87 = n86;
            final char c57 = charArray29[n87];
            char c58 = '\0';
            switch (n86 % 5) {
                case 0: {
                    c58 = '\u0010';
                    break;
                }
                case 1: {
                    c58 = '\u001d';
                    break;
                }
                case 2: {
                    c58 = '`';
                    break;
                }
                case 3: {
                    c58 = 'F';
                    break;
                }
                default: {
                    c58 = ':';
                    break;
                }
            }
            charArray29[n87] = (char)(c57 ^ c58);
        }
        z[n85] = new String(charArray29).intern();
        final int n88 = 29;
        final char[] charArray30 = "R=".toCharArray();
        final int length26 = charArray30.length;
        for (int n89 = 0; length26 > n89; ++n89) {
            final int n90 = n89;
            final char c59 = charArray30[n90];
            char c60 = '\0';
            switch (n89 % 5) {
                case 0: {
                    c60 = '\u0010';
                    break;
                }
                case 1: {
                    c60 = '\u001d';
                    break;
                }
                case 2: {
                    c60 = '`';
                    break;
                }
                case 3: {
                    c60 = 'F';
                    break;
                }
                default: {
                    c60 = ':';
                    break;
                }
            }
            charArray30[n90] = (char)(c59 ^ c60);
        }
        z[n88] = new String(charArray30).intern();
        final int n91 = 30;
        final char[] charArray31 = "Sx\u000e2_b".toCharArray();
        final int length27 = charArray31.length;
        for (int n92 = 0; length27 > n92; ++n92) {
            final int n93 = n92;
            final char c61 = charArray31[n93];
            char c62 = '\0';
            switch (n92 % 5) {
                case 0: {
                    c62 = '\u0010';
                    break;
                }
                case 1: {
                    c62 = '\u001d';
                    break;
                }
                case 2: {
                    c62 = '`';
                    break;
                }
                case 3: {
                    c62 = 'F';
                    break;
                }
                default: {
                    c62 = ':';
                    break;
                }
            }
            charArray31[n93] = (char)(c61 ^ c62);
        }
        z[n91] = new String(charArray31).intern();
        final int n94 = 31;
        final char[] charArray32 = "W=".toCharArray();
        final int length28 = charArray32.length;
        for (int n95 = 0; length28 > n95; ++n95) {
            final int n96 = n95;
            final char c63 = charArray32[n96];
            char c64 = '\0';
            switch (n95 % 5) {
                case 0: {
                    c64 = '\u0010';
                    break;
                }
                case 1: {
                    c64 = '\u001d';
                    break;
                }
                case 2: {
                    c64 = '`';
                    break;
                }
                case 3: {
                    c64 = 'F';
                    break;
                }
                default: {
                    c64 = ':';
                    break;
                }
            }
            charArray32[n96] = (char)(c63 ^ c64);
        }
        z[n94] = new String(charArray32).intern();
        ColorConversion.Z = z;
    }
}
