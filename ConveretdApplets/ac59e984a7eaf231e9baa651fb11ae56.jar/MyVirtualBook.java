import java.awt.image.ImageObserver;
import java.awt.Graphics;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.Container;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import java.awt.Font;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.MediaTracker;
import java.awt.CardLayout;
import java.applet.AudioClip;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Image;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class MyVirtualBook extends JApplet implements ActionListener, Runnable
{
    private boolean a;
    private boolean b;
    private boolean c;
    private boolean d;
    private Timer e;
    private a_ f;
    private a_ g;
    private a_ h;
    private JButton i;
    private JButton j;
    private JButton k;
    private JLabel l;
    private JLabel m;
    private JLabel n;
    private JLabel o;
    private JLabel p;
    private JPanel q;
    private JPanel r;
    private JPanel s;
    private JPanel t;
    private JPanel u;
    private JPanel v;
    private JPanel w;
    private int x;
    private int y;
    private int z;
    private int A;
    private int B;
    private int C;
    private int D;
    private int E;
    private int F;
    private int G;
    private Image[] H;
    private Image[] I;
    private Color J;
    private Color K;
    private String L;
    private String M;
    private String N;
    private String O;
    private int P;
    private int Q;
    private Image R;
    private ImageIcon S;
    private ImageIcon T;
    private String U;
    private String V;
    private AudioClip W;
    private AudioClip X;
    private CardLayout Y;
    private JPanel Z;
    private JPanel ba;
    private JPanel bb;
    private MediaTracker bc;
    boolean bd;
    private Thread be;
    private String bf;
    private String bg;
    private String bh;
    private String bi;
    private String bj;
    private String bk;
    private String bl;
    public static boolean bm;
    private static String[] bn;
    
    public MyVirtualBook() {
        this.a = true;
        this.b = false;
        this.c = false;
        this.d = false;
        this.z = 0;
        this.E = 0;
        this.F = 0;
        this.G = 0;
        this.L = " ";
        this.M = " ";
        this.N = " ";
        this.O = "";
        this.bf = MyVirtualBook.bn[7];
        this.bg = MyVirtualBook.bn[5];
        this.bh = MyVirtualBook.bn[8];
        this.bi = MyVirtualBook.bn[4];
        this.bj = MyVirtualBook.bn[6];
        this.bk = MyVirtualBook.bn[9];
        this.bl = MyVirtualBook.bn[3];
    }
    
    public void init() {
        final int d = a_.d;
        this.b();
        (this.o = new JLabel()).setHorizontalAlignment(4);
        this.o.setText(MyVirtualBook.bn[33]);
        (this.p = new JLabel()).setText(this.O);
        this.Z = new JPanel();
        this.Y = new CardLayout();
        this.Z.setLayout(this.Y);
        (this.ba = new JPanel()).setLayout(new GridLayout(1, 2));
        this.ba.add(this.o);
        this.ba.add(this.p);
        this.Z.add(this.ba, this.o.getText());
        this.W = this.getAudioClip(this.getDocumentBase(), MyVirtualBook.bn[49]);
        this.X = this.getAudioClip(this.getDocumentBase(), MyVirtualBook.bn[43]);
        this.bc = new MediaTracker(this);
        this.a();
        (this.k = new JButton()).setFont(new Font(MyVirtualBook.bn[39], 1, 11));
        this.k.addActionListener(this);
        this.c();
        this.d();
        this.bd = false;
        Label_0375: {
            if (this.b) {
                this.T = new ImageIcon(this.H[3]);
                this.P = this.T.getIconWidth();
                this.Q = this.T.getIconHeight();
                if (d == 0) {
                    break Label_0375;
                }
                MyVirtualBook.bm = !MyVirtualBook.bm;
            }
            this.T = new ImageIcon(this.H[1]);
            this.P = this.T.getIconWidth();
            this.Q = this.T.getIconHeight();
        }
        this.P += 10 - this.P % 10;
        this.R = this.getImage(this.getCodeBase(), MyVirtualBook.bn[34]);
        this.S = new ImageIcon(this.R);
        this.G = Integer.parseInt(this.getParameter(MyVirtualBook.bn[35]));
        this.e = new Timer(this.G, this);
        Label_0502: {
            if (this.getParameter(MyVirtualBook.bn[32]).toLowerCase().trim().equals(MyVirtualBook.bn[37])) {
                this.d = true;
                if (d == 0) {
                    break Label_0502;
                }
            }
            this.d = false;
        }
        this.f = new a_(this.H, this.I);
        this.g = new a_(this.H, this.I);
        this.h = new a_(this.R);
        (this.i = new JButton(MyVirtualBook.bn[44])).setFont(new Font(MyVirtualBook.bn[39], 1, 11));
        this.i.setVisible(false);
        this.i.addActionListener(this);
        (this.j = new JButton(MyVirtualBook.bn[11])).setFont(new Font(MyVirtualBook.bn[39], 1, 11));
        this.j.addActionListener(this);
        (this.n = new JLabel()).setText(MyVirtualBook.bn[38]);
        this.n.setForeground(Color.black);
        this.n.setBackground(this.J);
        this.n.setOpaque(true);
        this.n.setHorizontalAlignment(0);
        this.n.setFont(new Font(MyVirtualBook.bn[42], 0, 10));
        (this.u = new JPanel()).setBorder(BorderFactory.createEmptyBorder(0, 6, 0, 0));
        this.u.setBackground(this.J);
        (this.v = new JPanel()).setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 6));
        this.v.setBackground(this.J);
        (this.t = new JPanel()).setBorder(BorderFactory.createEmptyBorder(1, this.P + 15, 2, 16));
        this.t.setBackground(this.J);
        this.t.setLayout(new GridLayout(1, 1));
        this.t.add(this.n);
        (this.q = new JPanel()).setLayout(new GridLayout(1, 2));
        this.q.add(this.f);
        this.q.add(this.g);
        (this.w = new JPanel()).setBackground(this.J);
        this.w.setLayout(new GridLayout(1, 1));
        this.w.add(this.k);
        this.w.setVisible(false);
        (this.r = new JPanel()).setBackground(this.J);
        this.r.setLayout(new GridLayout(1, 2));
        this.r.add(this.i);
        this.r.add(this.j);
        this.s = new JPanel();
        Label_1172: {
            if (this.getParameter(MyVirtualBook.bn[18]).toLowerCase().trim().equals(MyVirtualBook.bn[25])) {
                this.s.setBorder(BorderFactory.createEmptyBorder(2, 14, 5, 11));
                this.w.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 1));
                this.t.setBorder(BorderFactory.createEmptyBorder(1, this.P + 16, 2, 16));
                if (d == 0) {
                    break Label_1172;
                }
            }
            this.s.setBorder(BorderFactory.createEmptyBorder(2, 15, 5, 15));
        }
        this.s.setBackground(this.J);
        this.s.setLayout(new GridLayout(2, 1));
        this.s.add(this.r);
        this.s.add(this.w);
        (this.bb = new JPanel()).setLayout(new BorderLayout());
        this.bb.add(this.t, MyVirtualBook.bn[40]);
        this.bb.add(this.q, MyVirtualBook.bn[41]);
        this.bb.add(this.s, MyVirtualBook.bn[45]);
        this.bb.add(this.u, MyVirtualBook.bn[46]);
        Label_1362: {
            if (this.z == 1) {
                this.bb.add(this.h, MyVirtualBook.bn[36]);
                if (d == 0) {
                    break Label_1362;
                }
            }
            this.bb.add(this.v, MyVirtualBook.bn[36]);
        }
        this.Z.add(this.bb, "");
        this.getContentPane().add(this.Z, MyVirtualBook.bn[41]);
        this.U = this.getParameter(MyVirtualBook.bn[47]);
        this.V = this.getParameter(MyVirtualBook.bn[48]);
        (this.be = new Thread(this)).start();
    }
    
    public void start() {
        this.e();
    }
    
    public void stop() {
        this.f();
    }
    
    public void run() {
        try {
            while (this.bc.statusAll(true) != 8) {
                this.O += ".";
                this.p.setText(this.O);
                if (this.p.getText().length() > 10) {
                    this.O = "";
                    this.p.setText(this.O);
                }
                final Thread be = this.be;
                Thread.sleep(200L);
            }
        }
        catch (InterruptedException ex) {}
        finally {
            this.bd = true;
            this.Y.next(this.Z);
        }
    }
    
    public void a() {
        for (int f = 1; this.getParameter(MyVirtualBook.bn[15] + f) != null; ++f) {
            this.F = f;
        }
    }
    
    public void b() {
        try {
            final String trim = this.getParameter(MyVirtualBook.bn[1]).trim();
            final String trim2 = this.getParameter(MyVirtualBook.bn[2]).trim();
            final String trim3 = this.getParameter(MyVirtualBook.bn[0]).trim();
            final int int1 = Integer.parseInt(trim);
            final int int2 = Integer.parseInt(trim2);
            final int int3 = Integer.parseInt(trim3);
            if (int1 >= 0 && int1 <= 255 && int2 >= 0 && int2 <= 255 && int3 >= 0 && int3 <= 255) {
                this.J = new Color(int1, int2, int3);
            }
            else {
                this.J = new Color(204, 204, 204);
            }
        }
        catch (NumberFormatException ex) {}
    }
    
    public void c() {
        final String parameter = this.getParameter(MyVirtualBook.bn[18]);
        if (parameter.toLowerCase().trim().equals(MyVirtualBook.bn[28])) {
            this.F += 4;
            this.b = true;
            this.L = MyVirtualBook.bn[23];
            this.M = MyVirtualBook.bn[21];
            this.N = MyVirtualBook.bn[17];
            this.k.setText(MyVirtualBook.bn[27]);
        }
        else if (parameter.toLowerCase().trim().equals(MyVirtualBook.bn[25])) {
            this.F += 4;
            this.b = true;
            this.L = MyVirtualBook.bn[22];
            this.M = MyVirtualBook.bn[29];
            this.N = MyVirtualBook.bn[26];
            this.B = 3;
            this.z = 1;
            this.k.setText(MyVirtualBook.bn[16]);
        }
        else if (parameter.toLowerCase().trim().equals(MyVirtualBook.bn[30])) {
            this.F += 4;
            this.b = true;
            this.L = MyVirtualBook.bn[20];
            this.M = MyVirtualBook.bn[21];
            this.N = MyVirtualBook.bn[31];
            this.k.setText(MyVirtualBook.bn[19]);
        }
        else {
            this.b = false;
            ++this.F;
            this.k.setText(MyVirtualBook.bn[24]);
        }
        if (this.F % 2 != 0) {
            this.c = true;
            ++this.F;
        }
        else {
            this.c = false;
        }
    }
    
    public void d() {
        int n = 0;
        this.H = new Image[this.F];
        this.I = new Image[this.F];
        if (this.b) {
            for (int i = 0; i < this.F; ++i) {
                if (i == 0) {
                    this.H[i] = this.getImage(this.getCodeBase(), MyVirtualBook.bn[51]);
                    this.I[i] = this.getImage(this.getCodeBase(), MyVirtualBook.bn[51]);
                }
                if (i > 0 && i < 3) {
                    this.H[i] = this.getImage(this.getCodeBase(), this.L + (i + 1) + this.M);
                    this.I[i] = this.getImage(this.getCodeBase(), this.L + (i + 1) + this.M);
                }
                if (!this.c) {
                    if (i >= 3 && i < this.F - 1) {
                        ++n;
                        this.H[i] = this.getImage(this.getCodeBase(), this.getParameter(MyVirtualBook.bn[15] + n).trim());
                        this.I[i] = this.getImage(this.getCodeBase(), this.getParameter(MyVirtualBook.bn[15] + n).trim());
                    }
                    if (i == this.F - 1) {
                        this.H[i] = this.getImage(this.getCodeBase(), this.N);
                        this.I[i] = this.getImage(this.getCodeBase(), this.N);
                    }
                }
                else if (this.c) {
                    if (i >= 3 && i < this.F - 2) {
                        ++n;
                        this.H[i] = this.getImage(this.getCodeBase(), this.getParameter(MyVirtualBook.bn[15] + n).trim());
                        this.I[i] = this.getImage(this.getCodeBase(), this.getParameter(MyVirtualBook.bn[15] + n).trim());
                    }
                    if (i == this.F - 2) {
                        this.H[i] = this.getImage(this.getCodeBase(), MyVirtualBook.bn[50]);
                        this.I[i] = this.getImage(this.getCodeBase(), MyVirtualBook.bn[50]);
                    }
                    if (i == this.F - 1) {
                        this.H[i] = this.getImage(this.getCodeBase(), this.N);
                        this.I[i] = this.getImage(this.getCodeBase(), this.N);
                    }
                }
                this.bc.addImage(this.I[i], i);
            }
        }
        else {
            for (int j = 0; j < this.F; ++j) {
                if (j == 0) {
                    this.H[j] = this.getImage(this.getCodeBase(), MyVirtualBook.bn[51]);
                    this.I[j] = this.getImage(this.getCodeBase(), MyVirtualBook.bn[51]);
                }
                if (!this.c) {
                    if (j > 0) {
                        ++n;
                        this.H[j] = this.getImage(this.getCodeBase(), this.getParameter(MyVirtualBook.bn[15] + n).trim());
                        this.I[j] = this.getImage(this.getCodeBase(), this.getParameter(MyVirtualBook.bn[15] + n).trim());
                    }
                }
                else if (this.c) {
                    if (j > 0 && j < this.F - 1) {
                        ++n;
                        this.H[j] = this.getImage(this.getCodeBase(), this.getParameter(MyVirtualBook.bn[15] + n).trim());
                        this.I[j] = this.getImage(this.getCodeBase(), this.getParameter(MyVirtualBook.bn[15] + n).trim());
                    }
                    if (j == this.F - 1) {
                        this.H[j] = this.getImage(this.getCodeBase(), MyVirtualBook.bn[51]);
                        this.I[j] = this.getImage(this.getCodeBase(), MyVirtualBook.bn[51]);
                    }
                }
                this.bc.addImage(this.I[j], j);
            }
        }
    }
    
    public synchronized void e() {
        if (!this.a) {
            if (!this.e.isRunning()) {
                this.e.start();
            }
        }
    }
    
    public synchronized void f() {
        if (this.e.isRunning()) {
            this.e.stop();
        }
    }
    
    public void g() {
        this.a = false;
        this.e();
        this.A = 0;
        this.C = 0;
        this.D = 0;
        this.x = this.P;
    }
    
    public void h() {
        if (this.C <= this.P / 10) {
            ++this.A;
            this.g.repaint();
        }
        if (this.C == this.P / 10) {
            this.A = 0;
            this.D = 1;
            this.y = this.P;
        }
        if (this.D == 1) {
            ++this.A;
            this.f.repaint();
        }
    }
    
    public void i() {
        if (this.C <= this.P / 10) {
            ++this.A;
            this.f.repaint();
        }
        if (this.C == this.P / 10) {
            this.A = 0;
            this.D = 1;
            this.y = this.P;
        }
        if (this.D == 1) {
            ++this.A;
            this.g.repaint();
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (!this.U.equals(this.bf) || !this.V.equals(this.bh)) {
            JOptionPane.showMessageDialog(this, this.bj + this.bk + this.bl, MyVirtualBook.bn[10], 0);
            System.exit(0);
        }
        this.showStatus(this.bg);
        if (actionEvent.getSource() == this.i) {
            if (this.d) {
                this.X.play();
            }
            this.B = 1;
            this.g();
            this.E -= 2;
        }
        if (actionEvent.getSource() == this.j) {
            this.n.setText(MyVirtualBook.bn[14]);
            this.n.setBackground(this.J);
            if (this.d) {
                this.X.play();
            }
            this.B = 2;
            this.g();
            this.E += 2;
        }
        if (actionEvent.getSource() == this.k) {
            this.n.setText(MyVirtualBook.bn[12]);
            this.n.setBackground(new Color(204, 204, 204));
            if (this.d) {
                this.W.play();
            }
            if (this.z == 0) {
                this.B = 0;
            }
            else if (this.z == 1) {
                this.B = 3;
            }
            this.E = 0;
            this.f.repaint();
            this.g.repaint();
            this.i.setVisible(false);
            this.w.setVisible(false);
            this.a = true;
        }
        ++this.C;
        this.x -= 10;
        this.y -= 10;
        if (this.B == 2) {
            this.h();
        }
        if (this.B == 1) {
            this.i();
        }
        if (this.D == 1 && this.A == this.P / 10) {
            this.a = true;
            this.f();
        }
        if (this.a) {
            if (this.E > 0) {
                this.i.setVisible(true);
                this.i.setEnabled(true);
                this.w.setVisible(true);
            }
            if (this.E > 1) {
                this.j.setText(MyVirtualBook.bn[13]);
            }
            if (this.E <= 1) {
                this.j.setText(MyVirtualBook.bn[11]);
            }
            if (this.E < this.F - 2) {
                this.j.setEnabled(true);
            }
            else {
                this.j.setText("");
            }
        }
        else {
            this.i.setEnabled(false);
            this.j.setEnabled(false);
            if (this.E <= 1) {
                this.i.setVisible(false);
                this.w.setVisible(false);
            }
        }
    }
    
    static {
        final String[] bn = new String[52];
        final int n = 0;
        final char[] charArray = "\u0012\t\t\u001f\u001a\u001c\u0001$#\u0017".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'p';
                    break;
                }
                case 1: {
                    c2 = 'n';
                    break;
                }
                case 2: {
                    c2 = 'V';
                    break;
                }
                case 3: {
                    c2 = '|';
                    break;
                }
                default: {
                    c2 = 'u';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        bn[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "\u0012\t\t\u001f\u001a\u001c\u0001$#\u0007".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'p';
                    break;
                }
                case 1: {
                    c4 = 'n';
                    break;
                }
                case 2: {
                    c4 = 'V';
                    break;
                }
                case 3: {
                    c4 = '|';
                    break;
                }
                default: {
                    c4 = 'u';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        bn[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "\u0012\t\t\u001f\u001a\u001c\u0001$#\u0012".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = 'p';
                    break;
                }
                case 1: {
                    c6 = 'n';
                    break;
                }
                case 2: {
                    c6 = 'V';
                    break;
                }
                case 3: {
                    c6 = '|';
                    break;
                }
                default: {
                    c6 = 'u';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        bn[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "\u0000\u001b$\u001f\u001d\u0011\u001d3\\\u0001\u0018\u0007%\\\u0014\u0000\u001e:\u0019\u0001Q".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = 'p';
                    break;
                }
                case 1: {
                    c8 = 'n';
                    break;
                }
                case 2: {
                    c8 = 'V';
                    break;
                }
                case 3: {
                    c8 = '|';
                    break;
                }
                default: {
                    c8 = 'u';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        bn[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = " \u00023\u001d\u0006\u0015N&\u000e\u001a\u0006\u00072\u0019U\u0004\u00063\\\u0016\u001f\u001c$\u0019\u0016\u0004N \u001d\u0019\u0005\u000b%v\u001c\u001eN\"\u0014\u0010P\u0001!\u0012\u0010\u0002N7\u0012\u0011P\u001c3\u001b\u001e\u0015\u0017v\f\u0014\u0002\u000f;\u0019\u0001\u0015\u001c%]".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = 'p';
                    break;
                }
                case 1: {
                    c10 = 'n';
                    break;
                }
                case 2: {
                    c10 = 'V';
                    break;
                }
                case 3: {
                    c10 = '|';
                    break;
                }
                default: {
                    c10 = 'u';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        bn[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "1\u001e&\u0010\u0010\u0004N4\u0005OP\"?\u000f\u0014P%7\u0012\u0010P\u0019!\u000b[\u001a\u000f \u001d\u0013\u001f\u0016.R\u0016\u001f\u0003".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = 'p';
                    break;
                }
                case 1: {
                    c12 = 'n';
                    break;
                }
                case 2: {
                    c12 = 'V';
                    break;
                }
                case 3: {
                    c12 = '|';
                    break;
                }
                default: {
                    c12 = 'u';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        bn[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "=\u00012\u0015\u0013\u0019\r7\b\u001c\u001f\u0000%\\\u0001\u001fN\"\u0014\u0010P\u0001!\u0012\u0010\u0002N7\u0012\u0011_\u0001$\\\u0007\u0015\t=\u0019\fP\u00187\u0010\u0000\u0015\u001dv\u000b\u001c\u001c\u0002v\\UPNv\\\u007f\u0000\u001c3\n\u0010\u001e\u001av\b\u001d\u0019\u001dv\u001d\u0005\u0000\u00023\b".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = 'p';
                    break;
                }
                case 1: {
                    c14 = 'n';
                    break;
                }
                case 2: {
                    c14 = 'V';
                    break;
                }
                case 3: {
                    c14 = '|';
                    break;
                }
                default: {
                    c14 = 'u';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        bn[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "<\u0007%\u001d>\u0011\u00003<\u001f\u0011\u00187\u001a\u001a\b\u0016x\u001f\u001a\u001d".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = 'p';
                    break;
                }
                case 1: {
                    c16 = 'n';
                    break;
                }
                case 2: {
                    c16 = 'V';
                    break;
                }
                case 3: {
                    c16 = '|';
                    break;
                }
                default: {
                    c16 = 'u';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        bn[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "@^aQ9;C`IFF".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = 'p';
                    break;
                }
                case 1: {
                    c18 = 'n';
                    break;
                }
                case 2: {
                    c18 = 'V';
                    break;
                }
                case 3: {
                    c18 = '|';
                    break;
                }
                default: {
                    c18 = 'u';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        bn[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = "P\b$\u0013\u0018P\u00199\u000e\u001e\u0019\u00001\\\u0005\u0002\u0001&\u0019\u0007\u001c\u0017xv!\u001fN$\u0019\u0018\u001f\u00183\\\u0001\u0018\u000bv\u001d\u0000\u0004\u00069\u000eR\u0003N?\u0012\u0013\u001f\u001c;\u001d\u0001\u0019\u00018\\\f\u001f\u001bv\u0011\u0000\u0003\u001a\\".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = 'p';
                    break;
                }
                case 1: {
                    c20 = 'n';
                    break;
                }
                case 2: {
                    c20 = 'V';
                    break;
                }
                case 3: {
                    c20 = '|';
                    break;
                }
                default: {
                    c20 = 'u';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        bn[n28] = new String(charArray10).intern();
        final int n31 = 10;
        final char[] charArray11 = "9\u00005\u0013\u0007\u0002\u000b5\bU\"\u000b1\u0015\u0006\u0004\u001c7\b\u001c\u001f\u0000v5\u001b\u0016\u0001v9\u0007\u0002\u0001$".toCharArray();
        final int length7 = charArray11.length;
        for (int n32 = 0; length7 > n32; ++n32) {
            final int n33 = n32;
            final char c21 = charArray11[n33];
            char c22 = '\0';
            switch (n32 % 5) {
                case 0: {
                    c22 = 'p';
                    break;
                }
                case 1: {
                    c22 = 'n';
                    break;
                }
                case 2: {
                    c22 = 'V';
                    break;
                }
                case 3: {
                    c22 = '|';
                    break;
                }
                default: {
                    c22 = 'u';
                    break;
                }
            }
            charArray11[n33] = (char)(c21 ^ c22);
        }
        bn[n31] = new String(charArray11).intern();
        final int n34 = 11;
        final char[] charArray12 = "?\u001e3\u0012".toCharArray();
        final int length8 = charArray12.length;
        for (int n35 = 0; length8 > n35; ++n35) {
            final int n36 = n35;
            final char c23 = charArray12[n36];
            char c24 = '\0';
            switch (n35 % 5) {
                case 0: {
                    c24 = 'p';
                    break;
                }
                case 1: {
                    c24 = 'n';
                    break;
                }
                case 2: {
                    c24 = 'V';
                    break;
                }
                case 3: {
                    c24 = '|';
                    break;
                }
                default: {
                    c24 = 'u';
                    break;
                }
            }
            charArray12[n36] = (char)(c23 ^ c24);
        }
        bn[n34] = new String(charArray12).intern();
        final int n37 = 12;
        final char[] charArray13 = "2\u0017l\\9\u0019\u001d77\u0014\u001e\u000b\u0016\u0016\u0014\u0006\u000f0\u0013\r\b@5\u0013\u0018".toCharArray();
        final int length9 = charArray13.length;
        for (int n38 = 0; length9 > n38; ++n38) {
            final int n39 = n38;
            final char c25 = charArray13[n39];
            char c26 = '\0';
            switch (n38 % 5) {
                case 0: {
                    c26 = 'p';
                    break;
                }
                case 1: {
                    c26 = 'n';
                    break;
                }
                case 2: {
                    c26 = 'V';
                    break;
                }
                case 3: {
                    c26 = '|';
                    break;
                }
                default: {
                    c26 = 'u';
                    break;
                }
            }
            charArray13[n39] = (char)(c25 ^ c26);
        }
        bn[n37] = new String(charArray13).intern();
        final int n40 = 13;
        final char[] charArray14 = ">\u000b.\bUN".toCharArray();
        final int length10 = charArray14.length;
        for (int n41 = 0; length10 > n41; ++n41) {
            final int n42 = n41;
            final char c27 = charArray14[n42];
            char c28 = '\0';
            switch (n41 % 5) {
                case 0: {
                    c28 = 'p';
                    break;
                }
                case 1: {
                    c28 = 'n';
                    break;
                }
                case 2: {
                    c28 = 'V';
                    break;
                }
                case 3: {
                    c28 = '|';
                    break;
                }
                default: {
                    c28 = 'u';
                    break;
                }
            }
            charArray14[n42] = (char)(c27 ^ c28);
        }
        bn[n40] = new String(charArray14).intern();
        final int n43 = 14;
        final char[] charArray15 = "PNv\\UPNv\\UPNv\\UPNv".toCharArray();
        final int length11 = charArray15.length;
        for (int n44 = 0; length11 > n44; ++n44) {
            final int n45 = n44;
            final char c29 = charArray15[n45];
            char c30 = '\0';
            switch (n44 % 5) {
                case 0: {
                    c30 = 'p';
                    break;
                }
                case 1: {
                    c30 = 'n';
                    break;
                }
                case 2: {
                    c30 = 'V';
                    break;
                }
                case 3: {
                    c30 = '|';
                    break;
                }
                default: {
                    c30 = 'u';
                    break;
                }
            }
            charArray15[n45] = (char)(c29 ^ c30);
        }
        bn[n43] = new String(charArray15).intern();
        final int n46 = 15;
        final char[] charArray16 = "\u0019\u00031".toCharArray();
        final int length12 = charArray16.length;
        for (int n47 = 0; length12 > n47; ++n47) {
            final int n48 = n47;
            final char c31 = charArray16[n48];
            char c32 = '\0';
            switch (n47 % 5) {
                case 0: {
                    c32 = 'p';
                    break;
                }
                case 1: {
                    c32 = 'n';
                    break;
                }
                case 2: {
                    c32 = 'V';
                    break;
                }
                case 3: {
                    c32 = '|';
                    break;
                }
                default: {
                    c32 = 'u';
                    break;
                }
            }
            charArray16[n48] = (char)(c31 ^ c32);
        }
        bn[n46] = new String(charArray16).intern();
        final int n49 = 16;
        final char[] charArray17 = "3\u00029\u000f\u0010P(9\u0010\u0011\u0015\u001c".toCharArray();
        final int length13 = charArray17.length;
        for (int n50 = 0; length13 > n50; ++n50) {
            final int n51 = n50;
            final char c33 = charArray17[n51];
            char c34 = '\0';
            switch (n50 % 5) {
                case 0: {
                    c34 = 'p';
                    break;
                }
                case 1: {
                    c34 = 'n';
                    break;
                }
                case 2: {
                    c34 = 'V';
                    break;
                }
                case 3: {
                    c34 = '|';
                    break;
                }
                default: {
                    c34 = 'u';
                    break;
                }
            }
            charArray17[n51] = (char)(c33 ^ c34);
        }
        bn[n49] = new String(charArray17).intern();
        final int n52 = 17;
        final char[] charArray18 = "^A\"\u0019\u0018\u0000\u00027\b\u0010\u0003A&\u0014\u001a\u0004\u0001\u0017\u0010\u0017\u0005\u0003bR\u001f\u0000\t".toCharArray();
        final int length14 = charArray18.length;
        for (int n53 = 0; length14 > n53; ++n53) {
            final int n54 = n53;
            final char c35 = charArray18[n54];
            char c36 = '\0';
            switch (n53 % 5) {
                case 0: {
                    c36 = 'p';
                    break;
                }
                case 1: {
                    c36 = 'n';
                    break;
                }
                case 2: {
                    c36 = 'V';
                    break;
                }
                case 3: {
                    c36 = '|';
                    break;
                }
                default: {
                    c36 = 'u';
                    break;
                }
            }
            charArray18[n54] = (char)(c35 ^ c36);
        }
        bn[n52] = new String(charArray18).intern();
        final int n55 = 18;
        final char[] charArray19 = "\u0005\u001d3#\u0001\u0015\u0003&\u0010\u0014\u0004\u000b".toCharArray();
        final int length15 = charArray19.length;
        for (int n56 = 0; length15 > n56; ++n56) {
            final int n57 = n56;
            final char c37 = charArray19[n57];
            char c38 = '\0';
            switch (n56 % 5) {
                case 0: {
                    c38 = 'p';
                    break;
                }
                case 1: {
                    c38 = 'n';
                    break;
                }
                case 2: {
                    c38 = 'V';
                    break;
                }
                case 3: {
                    c38 = '|';
                    break;
                }
                default: {
                    c38 = 'u';
                    break;
                }
            }
            charArray19[n57] = (char)(c37 ^ c38);
        }
        bn[n55] = new String(charArray19).intern();
        final int n58 = 19;
        final char[] charArray20 = "3\u00029\u000f\u0010P=5\u000e\u0014\u0000\f9\u0013\u001e".toCharArray();
        final int length16 = charArray20.length;
        for (int n59 = 0; length16 > n59; ++n59) {
            final int n60 = n59;
            final char c39 = charArray20[n60];
            char c40 = '\0';
            switch (n59 % 5) {
                case 0: {
                    c40 = 'p';
                    break;
                }
                case 1: {
                    c40 = 'n';
                    break;
                }
                case 2: {
                    c40 = 'V';
                    break;
                }
                case 3: {
                    c40 = '|';
                    break;
                }
                default: {
                    c40 = 'u';
                    break;
                }
            }
            charArray20[n60] = (char)(c39 ^ c40);
        }
        bn[n58] = new String(charArray20).intern();
        final int n61 = 20;
        final char[] charArray21 = "^A\"\u0019\u0018\u0000\u00027\b\u0010\u0003A%\u001f\u0007\u0011\u001e\u0014\u0013\u001a\u001b".toCharArray();
        final int length17 = charArray21.length;
        for (int n62 = 0; length17 > n62; ++n62) {
            final int n63 = n62;
            final char c41 = charArray21[n63];
            char c42 = '\0';
            switch (n62 % 5) {
                case 0: {
                    c42 = 'p';
                    break;
                }
                case 1: {
                    c42 = 'n';
                    break;
                }
                case 2: {
                    c42 = 'V';
                    break;
                }
                case 3: {
                    c42 = '|';
                    break;
                }
                default: {
                    c42 = 'u';
                    break;
                }
            }
            charArray21[n63] = (char)(c41 ^ c42);
        }
        bn[n61] = new String(charArray21).intern();
        final int n64 = 21;
        final char[] charArray22 = "^\u0004&\u001b".toCharArray();
        final int length18 = charArray22.length;
        for (int n65 = 0; length18 > n65; ++n65) {
            final int n66 = n65;
            final char c43 = charArray22[n66];
            char c44 = '\0';
            switch (n65 % 5) {
                case 0: {
                    c44 = 'p';
                    break;
                }
                case 1: {
                    c44 = 'n';
                    break;
                }
                case 2: {
                    c44 = 'V';
                    break;
                }
                case 3: {
                    c44 = '|';
                    break;
                }
                default: {
                    c44 = 'u';
                    break;
                }
            }
            charArray22[n66] = (char)(c43 ^ c44);
        }
        bn[n64] = new String(charArray22).intern();
        final int n67 = 22;
        final char[] charArray23 = "^A\"\u0019\u0018\u0000\u00027\b\u0010\u0003A0\u0013\u0019\u0014\u000b$".toCharArray();
        final int length19 = charArray23.length;
        for (int n68 = 0; length19 > n68; ++n68) {
            final int n69 = n68;
            final char c45 = charArray23[n69];
            char c46 = '\0';
            switch (n68 % 5) {
                case 0: {
                    c46 = 'p';
                    break;
                }
                case 1: {
                    c46 = 'n';
                    break;
                }
                case 2: {
                    c46 = 'V';
                    break;
                }
                case 3: {
                    c46 = '|';
                    break;
                }
                default: {
                    c46 = 'u';
                    break;
                }
            }
            charArray23[n69] = (char)(c45 ^ c46);
        }
        bn[n67] = new String(charArray23).intern();
        final int n70 = 23;
        final char[] charArray24 = "^A\"\u0019\u0018\u0000\u00027\b\u0010\u0003A&\u0014\u001a\u0004\u0001\u0017\u0010\u0017\u0005\u0003".toCharArray();
        final int length20 = charArray24.length;
        for (int n71 = 0; length20 > n71; ++n71) {
            final int n72 = n71;
            final char c47 = charArray24[n72];
            char c48 = '\0';
            switch (n71 % 5) {
                case 0: {
                    c48 = 'p';
                    break;
                }
                case 1: {
                    c48 = 'n';
                    break;
                }
                case 2: {
                    c48 = 'V';
                    break;
                }
                case 3: {
                    c48 = '|';
                    break;
                }
                default: {
                    c48 = 'u';
                    break;
                }
            }
            charArray24[n72] = (char)(c47 ^ c48);
        }
        bn[n70] = new String(charArray24).intern();
        final int n73 = 24;
        final char[] charArray25 = "3\u00029\u000f\u0010P,9\u0013\u001e".toCharArray();
        final int length21 = charArray25.length;
        for (int n74 = 0; length21 > n74; ++n74) {
            final int n75 = n74;
            final char c49 = charArray25[n75];
            char c50 = '\0';
            switch (n74 % 5) {
                case 0: {
                    c50 = 'p';
                    break;
                }
                case 1: {
                    c50 = 'n';
                    break;
                }
                case 2: {
                    c50 = 'V';
                    break;
                }
                case 3: {
                    c50 = '|';
                    break;
                }
                default: {
                    c50 = 'u';
                    break;
                }
            }
            charArray25[n75] = (char)(c49 ^ c50);
        }
        bn[n73] = new String(charArray25).intern();
        final int n76 = 25;
        final char[] charArray26 = "\u0016\u0001:\u0018\u0010\u0002".toCharArray();
        final int length22 = charArray26.length;
        for (int n77 = 0; length22 > n77; ++n77) {
            final int n78 = n77;
            final char c51 = charArray26[n78];
            char c52 = '\0';
            switch (n77 % 5) {
                case 0: {
                    c52 = 'p';
                    break;
                }
                case 1: {
                    c52 = 'n';
                    break;
                }
                case 2: {
                    c52 = 'V';
                    break;
                }
                case 3: {
                    c52 = '|';
                    break;
                }
                default: {
                    c52 = 'u';
                    break;
                }
            }
            charArray26[n78] = (char)(c51 ^ c52);
        }
        bn[n76] = new String(charArray26).intern();
        final int n79 = 26;
        final char[] charArray27 = "^A\"\u0019\u0018\u0000\u00027\b\u0010\u0003A0\u0013\u0019\u0014\u000b$H[\u0017\u00070".toCharArray();
        final int length23 = charArray27.length;
        for (int n80 = 0; length23 > n80; ++n80) {
            final int n81 = n80;
            final char c53 = charArray27[n81];
            char c54 = '\0';
            switch (n80 % 5) {
                case 0: {
                    c54 = 'p';
                    break;
                }
                case 1: {
                    c54 = 'n';
                    break;
                }
                case 2: {
                    c54 = 'V';
                    break;
                }
                case 3: {
                    c54 = '|';
                    break;
                }
                default: {
                    c54 = 'u';
                    break;
                }
            }
            charArray27[n81] = (char)(c53 ^ c54);
        }
        bn[n79] = new String(charArray27).intern();
        final int n82 = 27;
        final char[] charArray28 = "3\u00029\u000f\u0010P/:\u001e\u0000\u001d".toCharArray();
        final int length24 = charArray28.length;
        for (int n83 = 0; length24 > n83; ++n83) {
            final int n84 = n83;
            final char c55 = charArray28[n84];
            char c56 = '\0';
            switch (n83 % 5) {
                case 0: {
                    c56 = 'p';
                    break;
                }
                case 1: {
                    c56 = 'n';
                    break;
                }
                case 2: {
                    c56 = 'V';
                    break;
                }
                case 3: {
                    c56 = '|';
                    break;
                }
                default: {
                    c56 = 'u';
                    break;
                }
            }
            charArray28[n84] = (char)(c55 ^ c56);
        }
        bn[n82] = new String(charArray28).intern();
        final int n85 = 28;
        final char[] charArray29 = "\u0011\u00024\t\u0018".toCharArray();
        final int length25 = charArray29.length;
        for (int n86 = 0; length25 > n86; ++n86) {
            final int n87 = n86;
            final char c57 = charArray29[n87];
            char c58 = '\0';
            switch (n86 % 5) {
                case 0: {
                    c58 = 'p';
                    break;
                }
                case 1: {
                    c58 = 'n';
                    break;
                }
                case 2: {
                    c58 = 'V';
                    break;
                }
                case 3: {
                    c58 = '|';
                    break;
                }
                default: {
                    c58 = 'u';
                    break;
                }
            }
            charArray29[n87] = (char)(c57 ^ c58);
        }
        bn[n85] = new String(charArray29).intern();
        final int n88 = 29;
        final char[] charArray30 = "^\t?\u001a".toCharArray();
        final int length26 = charArray30.length;
        for (int n89 = 0; length26 > n89; ++n89) {
            final int n90 = n89;
            final char c59 = charArray30[n90];
            char c60 = '\0';
            switch (n89 % 5) {
                case 0: {
                    c60 = 'p';
                    break;
                }
                case 1: {
                    c60 = 'n';
                    break;
                }
                case 2: {
                    c60 = 'V';
                    break;
                }
                case 3: {
                    c60 = '|';
                    break;
                }
                default: {
                    c60 = 'u';
                    break;
                }
            }
            charArray30[n90] = (char)(c59 ^ c60);
        }
        bn[n88] = new String(charArray30).intern();
        final int n91 = 30;
        final char[] charArray31 = "\u0003\r$\u001d\u0005\u0012\u00019\u0017".toCharArray();
        final int length27 = charArray31.length;
        for (int n92 = 0; length27 > n92; ++n92) {
            final int n93 = n92;
            final char c61 = charArray31[n93];
            char c62 = '\0';
            switch (n92 % 5) {
                case 0: {
                    c62 = 'p';
                    break;
                }
                case 1: {
                    c62 = 'n';
                    break;
                }
                case 2: {
                    c62 = 'V';
                    break;
                }
                case 3: {
                    c62 = '|';
                    break;
                }
                default: {
                    c62 = 'u';
                    break;
                }
            }
            charArray31[n93] = (char)(c61 ^ c62);
        }
        bn[n91] = new String(charArray31).intern();
        final int n94 = 31;
        final char[] charArray32 = "^A\"\u0019\u0018\u0000\u00027\b\u0010\u0003A%\u001f\u0007\u0011\u001e\u0014\u0013\u001a\u001bZx\u0016\u0005\u0017".toCharArray();
        final int length28 = charArray32.length;
        for (int n95 = 0; length28 > n95; ++n95) {
            final int n96 = n95;
            final char c63 = charArray32[n96];
            char c64 = '\0';
            switch (n95 % 5) {
                case 0: {
                    c64 = 'p';
                    break;
                }
                case 1: {
                    c64 = 'n';
                    break;
                }
                case 2: {
                    c64 = 'V';
                    break;
                }
                case 3: {
                    c64 = '|';
                    break;
                }
                default: {
                    c64 = 'u';
                    break;
                }
            }
            charArray32[n96] = (char)(c63 ^ c64);
        }
        bn[n94] = new String(charArray32).intern();
        final int n97 = 32;
        final char[] charArray33 = "\u0003\u0001#\u0012\u0011".toCharArray();
        final int length29 = charArray33.length;
        for (int n98 = 0; length29 > n98; ++n98) {
            final int n99 = n98;
            final char c65 = charArray33[n99];
            char c66 = '\0';
            switch (n98 % 5) {
                case 0: {
                    c66 = 'p';
                    break;
                }
                case 1: {
                    c66 = 'n';
                    break;
                }
                case 2: {
                    c66 = 'V';
                    break;
                }
                case 3: {
                    c66 = '|';
                    break;
                }
                default: {
                    c66 = 'u';
                    break;
                }
            }
            charArray33[n99] = (char)(c65 ^ c66);
        }
        bn[n97] = new String(charArray33).intern();
        final int n100 = 33;
        final char[] charArray34 = "<\u00017\u0018\u001c\u001e\t".toCharArray();
        final int length30 = charArray34.length;
        for (int n101 = 0; length30 > n101; ++n101) {
            final int n102 = n101;
            final char c67 = charArray34[n102];
            char c68 = '\0';
            switch (n101 % 5) {
                case 0: {
                    c68 = 'p';
                    break;
                }
                case 1: {
                    c68 = 'n';
                    break;
                }
                case 2: {
                    c68 = 'V';
                    break;
                }
                case 3: {
                    c68 = '|';
                    break;
                }
                default: {
                    c68 = 'u';
                    break;
                }
            }
            charArray34[n102] = (char)(c67 ^ c68);
        }
        bn[n100] = new String(charArray34).intern();
        final int n103 = 34;
        final char[] charArray35 = "^A\"\u0019\u0018\u0000\u00027\b\u0010\u0003A0\u0013\u0019\u0014\u000b$\u000f\u001c\u0014\u000bx\u001b\u001c\u0016".toCharArray();
        final int length31 = charArray35.length;
        for (int n104 = 0; length31 > n104; ++n104) {
            final int n105 = n104;
            final char c69 = charArray35[n105];
            char c70 = '\0';
            switch (n104 % 5) {
                case 0: {
                    c70 = 'p';
                    break;
                }
                case 1: {
                    c70 = 'n';
                    break;
                }
                case 2: {
                    c70 = 'V';
                    break;
                }
                case 3: {
                    c70 = '|';
                    break;
                }
                default: {
                    c70 = 'u';
                    break;
                }
            }
            charArray35[n105] = (char)(c69 ^ c70);
        }
        bn[n103] = new String(charArray35).intern();
        final int n106 = 35;
        final char[] charArray36 = "\u0003\u001e3\u0019\u0011".toCharArray();
        final int length32 = charArray36.length;
        for (int n107 = 0; length32 > n107; ++n107) {
            final int n108 = n107;
            final char c71 = charArray36[n108];
            char c72 = '\0';
            switch (n107 % 5) {
                case 0: {
                    c72 = 'p';
                    break;
                }
                case 1: {
                    c72 = 'n';
                    break;
                }
                case 2: {
                    c72 = 'V';
                    break;
                }
                case 3: {
                    c72 = '|';
                    break;
                }
                default: {
                    c72 = 'u';
                    break;
                }
            }
            charArray36[n108] = (char)(c71 ^ c72);
        }
        bn[n106] = new String(charArray36).intern();
        final int n109 = 36;
        final char[] charArray37 = "5\u000f%\b".toCharArray();
        final int length33 = charArray37.length;
        for (int n110 = 0; length33 > n110; ++n110) {
            final int n111 = n110;
            final char c73 = charArray37[n111];
            char c74 = '\0';
            switch (n110 % 5) {
                case 0: {
                    c74 = 'p';
                    break;
                }
                case 1: {
                    c74 = 'n';
                    break;
                }
                case 2: {
                    c74 = 'V';
                    break;
                }
                case 3: {
                    c74 = '|';
                    break;
                }
                default: {
                    c74 = 'u';
                    break;
                }
            }
            charArray37[n111] = (char)(c73 ^ c74);
        }
        bn[n109] = new String(charArray37).intern();
        final int n112 = 37;
        final char[] charArray38 = "\u001f\u0000".toCharArray();
        final int length34 = charArray38.length;
        for (int n113 = 0; length34 > n113; ++n113) {
            final int n114 = n113;
            final char c75 = charArray38[n114];
            char c76 = '\0';
            switch (n113 % 5) {
                case 0: {
                    c76 = 'p';
                    break;
                }
                case 1: {
                    c76 = 'n';
                    break;
                }
                case 2: {
                    c76 = 'V';
                    break;
                }
                case 3: {
                    c76 = '|';
                    break;
                }
                default: {
                    c76 = 'u';
                    break;
                }
            }
            charArray38[n114] = (char)(c75 ^ c76);
        }
        bn[n112] = new String(charArray38).intern();
        final int n115 = 38;
        final char[] charArray39 = "PNv\\UPNv\\UPNv\\UPNv\\UPNv\\UPNv\\".toCharArray();
        final int length35 = charArray39.length;
        for (int n116 = 0; length35 > n116; ++n116) {
            final int n117 = n116;
            final char c77 = charArray39[n117];
            char c78 = '\0';
            switch (n116 % 5) {
                case 0: {
                    c78 = 'p';
                    break;
                }
                case 1: {
                    c78 = 'n';
                    break;
                }
                case 2: {
                    c78 = 'V';
                    break;
                }
                case 3: {
                    c78 = '|';
                    break;
                }
                default: {
                    c78 = 'u';
                    break;
                }
            }
            charArray39[n117] = (char)(c77 ^ c78);
        }
        bn[n115] = new String(charArray39).intern();
        final int n118 = 39;
        final char[] charArray40 = "\u0014\u00077\u0010\u001a\u0017".toCharArray();
        final int length36 = charArray40.length;
        for (int n119 = 0; length36 > n119; ++n119) {
            final int n120 = n119;
            final char c79 = charArray40[n120];
            char c80 = '\0';
            switch (n119 % 5) {
                case 0: {
                    c80 = 'p';
                    break;
                }
                case 1: {
                    c80 = 'n';
                    break;
                }
                case 2: {
                    c80 = 'V';
                    break;
                }
                case 3: {
                    c80 = '|';
                    break;
                }
                default: {
                    c80 = 'u';
                    break;
                }
            }
            charArray40[n120] = (char)(c79 ^ c80);
        }
        bn[n118] = new String(charArray40).intern();
        final int n121 = 40;
        final char[] charArray41 = ">\u0001$\b\u001d".toCharArray();
        final int length37 = charArray41.length;
        for (int n122 = 0; length37 > n122; ++n122) {
            final int n123 = n122;
            final char c81 = charArray41[n123];
            char c82 = '\0';
            switch (n122 % 5) {
                case 0: {
                    c82 = 'p';
                    break;
                }
                case 1: {
                    c82 = 'n';
                    break;
                }
                case 2: {
                    c82 = 'V';
                    break;
                }
                case 3: {
                    c82 = '|';
                    break;
                }
                default: {
                    c82 = 'u';
                    break;
                }
            }
            charArray41[n123] = (char)(c81 ^ c82);
        }
        bn[n121] = new String(charArray41).intern();
        final int n124 = 41;
        final char[] charArray42 = "3\u000b8\b\u0010\u0002".toCharArray();
        final int length38 = charArray42.length;
        for (int n125 = 0; length38 > n125; ++n125) {
            final int n126 = n125;
            final char c83 = charArray42[n126];
            char c84 = '\0';
            switch (n125 % 5) {
                case 0: {
                    c84 = 'p';
                    break;
                }
                case 1: {
                    c84 = 'n';
                    break;
                }
                case 2: {
                    c84 = 'V';
                    break;
                }
                case 3: {
                    c84 = '|';
                    break;
                }
                default: {
                    c84 = 'u';
                    break;
                }
            }
            charArray42[n126] = (char)(c83 ^ c84);
        }
        bn[n124] = new String(charArray42).intern();
        final int n127 = 42;
        final char[] charArray43 = "1\u001c?\u001d\u0019".toCharArray();
        final int length39 = charArray43.length;
        for (int n128 = 0; length39 > n128; ++n128) {
            final int n129 = n128;
            final char c85 = charArray43[n129];
            char c86 = '\0';
            switch (n128 % 5) {
                case 0: {
                    c86 = 'p';
                    break;
                }
                case 1: {
                    c86 = 'n';
                    break;
                }
                case 2: {
                    c86 = 'V';
                    break;
                }
                case 3: {
                    c86 = '|';
                    break;
                }
                default: {
                    c86 = 'u';
                    break;
                }
            }
            charArray43[n129] = (char)(c85 ^ c86);
        }
        bn[n127] = new String(charArray43).intern();
        final int n130 = 43;
        final char[] charArray44 = "^A\"\u0019\u0018\u0000\u00027\b\u0010\u0003A0\u0010\u001c\u0000@!\u001d\u0003".toCharArray();
        final int length40 = charArray44.length;
        for (int n131 = 0; length40 > n131; ++n131) {
            final int n132 = n131;
            final char c87 = charArray44[n132];
            char c88 = '\0';
            switch (n131 % 5) {
                case 0: {
                    c88 = 'p';
                    break;
                }
                case 1: {
                    c88 = 'n';
                    break;
                }
                case 2: {
                    c88 = 'V';
                    break;
                }
                case 3: {
                    c88 = '|';
                    break;
                }
                default: {
                    c88 = 'u';
                    break;
                }
            }
            charArray44[n132] = (char)(c87 ^ c88);
        }
        bn[n130] = new String(charArray44).intern();
        final int n133 = 44;
        final char[] charArray45 = "LN\u0006\u000e\u0010\u0006\u00079\t\u0006".toCharArray();
        final int length41 = charArray45.length;
        for (int n134 = 0; length41 > n134; ++n134) {
            final int n135 = n134;
            final char c89 = charArray45[n135];
            char c90 = '\0';
            switch (n134 % 5) {
                case 0: {
                    c90 = 'p';
                    break;
                }
                case 1: {
                    c90 = 'n';
                    break;
                }
                case 2: {
                    c90 = 'V';
                    break;
                }
                case 3: {
                    c90 = '|';
                    break;
                }
                default: {
                    c90 = 'u';
                    break;
                }
            }
            charArray45[n135] = (char)(c89 ^ c90);
        }
        bn[n133] = new String(charArray45).intern();
        final int n136 = 45;
        final char[] charArray46 = "#\u0001#\b\u001d".toCharArray();
        final int length42 = charArray46.length;
        for (int n137 = 0; length42 > n137; ++n137) {
            final int n138 = n137;
            final char c91 = charArray46[n138];
            char c92 = '\0';
            switch (n137 % 5) {
                case 0: {
                    c92 = 'p';
                    break;
                }
                case 1: {
                    c92 = 'n';
                    break;
                }
                case 2: {
                    c92 = 'V';
                    break;
                }
                case 3: {
                    c92 = '|';
                    break;
                }
                default: {
                    c92 = 'u';
                    break;
                }
            }
            charArray46[n138] = (char)(c91 ^ c92);
        }
        bn[n136] = new String(charArray46).intern();
        final int n139 = 46;
        final char[] charArray47 = "'\u000b%\b".toCharArray();
        final int length43 = charArray47.length;
        for (int n140 = 0; length43 > n140; ++n140) {
            final int n141 = n140;
            final char c93 = charArray47[n141];
            char c94 = '\0';
            switch (n140 % 5) {
                case 0: {
                    c94 = 'p';
                    break;
                }
                case 1: {
                    c94 = 'n';
                    break;
                }
                case 2: {
                    c94 = 'V';
                    break;
                }
                case 3: {
                    c94 = '|';
                    break;
                }
                default: {
                    c94 = 'u';
                    break;
                }
            }
            charArray47[n141] = (char)(c93 ^ c94);
        }
        bn[n139] = new String(charArray47).intern();
        final int n142 = 47;
        final char[] charArray48 = "\u001f\u00198\u0019\u0007".toCharArray();
        final int length44 = charArray48.length;
        for (int n143 = 0; length44 > n143; ++n143) {
            final int n144 = n143;
            final char c95 = charArray48[n144];
            char c96 = '\0';
            switch (n143 % 5) {
                case 0: {
                    c96 = 'p';
                    break;
                }
                case 1: {
                    c96 = 'n';
                    break;
                }
                case 2: {
                    c96 = 'V';
                    break;
                }
                case 3: {
                    c96 = '|';
                    break;
                }
                default: {
                    c96 = 'u';
                    break;
                }
            }
            charArray48[n144] = (char)(c95 ^ c96);
        }
        bn[n142] = new String(charArray48).intern();
        final int n145 = 48;
        final char[] charArray49 = "\u0002\u000b1\u0017\u0010\t".toCharArray();
        final int length45 = charArray49.length;
        for (int n146 = 0; length45 > n146; ++n146) {
            final int n147 = n146;
            final char c97 = charArray49[n147];
            char c98 = '\0';
            switch (n146 % 5) {
                case 0: {
                    c98 = 'p';
                    break;
                }
                case 1: {
                    c98 = 'n';
                    break;
                }
                case 2: {
                    c98 = 'V';
                    break;
                }
                case 3: {
                    c98 = '|';
                    break;
                }
                default: {
                    c98 = 'u';
                    break;
                }
            }
            charArray49[n147] = (char)(c97 ^ c98);
        }
        bn[n145] = new String(charArray49).intern();
        final int n148 = 49;
        final char[] charArray50 = "^A\"\u0019\u0018\u0000\u00027\b\u0010\u0003A4\u0013\u001a\u001b-:\u0013\u0006\u0015@!\u001d\u0003".toCharArray();
        final int length46 = charArray50.length;
        for (int n149 = 0; length46 > n149; ++n149) {
            final int n150 = n149;
            final char c99 = charArray50[n150];
            char c100 = '\0';
            switch (n149 % 5) {
                case 0: {
                    c100 = 'p';
                    break;
                }
                case 1: {
                    c100 = 'n';
                    break;
                }
                case 2: {
                    c100 = 'V';
                    break;
                }
                case 3: {
                    c100 = '|';
                    break;
                }
                default: {
                    c100 = 'u';
                    break;
                }
            }
            charArray50[n150] = (char)(c99 ^ c100);
        }
        bn[n148] = new String(charArray50).intern();
        final int n151 = 50;
        final char[] charArray51 = "^A\"\u0019\u0018\u0000\u00027\b\u0010\u0003A4\u0010\u0014\u001e\u0005x\u0016\u0005\u0017".toCharArray();
        final int length47 = charArray51.length;
        for (int n152 = 0; length47 > n152; ++n152) {
            final int n153 = n152;
            final char c101 = charArray51[n153];
            char c102 = '\0';
            switch (n152 % 5) {
                case 0: {
                    c102 = 'p';
                    break;
                }
                case 1: {
                    c102 = 'n';
                    break;
                }
                case 2: {
                    c102 = 'V';
                    break;
                }
                case 3: {
                    c102 = '|';
                    break;
                }
                default: {
                    c102 = 'u';
                    break;
                }
            }
            charArray51[n153] = (char)(c101 ^ c102);
        }
        bn[n151] = new String(charArray51).intern();
        final int n154 = 51;
        final char[] charArray52 = "^A\"\u0019\u0018\u0000\u00027\b\u0010\u0003A\"\u000e\u0014\u001e\u001d\u0014\u0010\u0014\u001e\u0005x\u001b\u001c\u0016".toCharArray();
        final int length48 = charArray52.length;
        for (int n155 = 0; length48 > n155; ++n155) {
            final int n156 = n155;
            final char c103 = charArray52[n156];
            char c104 = '\0';
            switch (n155 % 5) {
                case 0: {
                    c104 = 'p';
                    break;
                }
                case 1: {
                    c104 = 'n';
                    break;
                }
                case 2: {
                    c104 = 'V';
                    break;
                }
                case 3: {
                    c104 = '|';
                    break;
                }
                default: {
                    c104 = 'u';
                    break;
                }
            }
            charArray52[n156] = (char)(c103 ^ c104);
        }
        bn[n154] = new String(charArray52).intern();
        MyVirtualBook.bn = bn;
    }
    
    class a_ extends JPanel
    {
        Image[] a;
        Image[] b;
        Image c;
        public static int d;
        private static String e;
        
        public a_(final Image c) {
            int d = a_.d;
            this.c = c;
            this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 4));
            this.setBackground(MyVirtualBook.this.J);
            if (MyVirtualBook.bm) {
                a_.d = ++d;
            }
        }
        
        public a_(final Image[] a, final Image[] b) {
            this.a = a;
            this.b = b;
            this.setBackground(MyVirtualBook.this.J);
        }
        
        public void paintComponent(final Graphics graphics) {
            super.paintComponent(graphics);
            try {
                if (MyVirtualBook.this.z == 1 && this.equals(MyVirtualBook.this.h)) {
                    graphics.drawImage(this.c, 0, 0, this.getWidth() - 3, this.getHeight() - 1, this);
                }
                if (MyVirtualBook.this.B == 3) {
                    if (this.equals(MyVirtualBook.this.f)) {
                        graphics.drawImage(this.a[MyVirtualBook.this.E], 0, 0, MyVirtualBook.this.P, MyVirtualBook.this.Q, this);
                    }
                    if (this.equals(MyVirtualBook.this.g)) {
                        graphics.drawImage(this.a[MyVirtualBook.this.E + 3], 0, 0, MyVirtualBook.this.P, MyVirtualBook.this.Q, this);
                        graphics.drawImage(this.b[MyVirtualBook.this.E + 1], 0, 0, MyVirtualBook.this.P, MyVirtualBook.this.Q, this);
                    }
                }
                if (MyVirtualBook.this.B == 0) {
                    if (this.equals(MyVirtualBook.this.f)) {
                        graphics.drawImage(this.a[MyVirtualBook.this.E], 0, 0, MyVirtualBook.this.P, MyVirtualBook.this.Q, this);
                    }
                    if (this.equals(MyVirtualBook.this.g)) {
                        graphics.drawImage(this.a[MyVirtualBook.this.E + 1], 0, 0, MyVirtualBook.this.P, MyVirtualBook.this.Q, this);
                    }
                }
                if (MyVirtualBook.this.B == 1) {
                    if (this.equals(MyVirtualBook.this.f)) {
                        graphics.drawImage(this.a[MyVirtualBook.this.E], 0, 0, MyVirtualBook.this.P, MyVirtualBook.this.Q, this);
                        graphics.drawImage(this.b[MyVirtualBook.this.E + 2], MyVirtualBook.this.C * 10, 0, MyVirtualBook.this.x, MyVirtualBook.this.Q, this);
                    }
                    if (this.equals(MyVirtualBook.this.g)) {
                        graphics.drawImage(this.a[MyVirtualBook.this.E + 3], 0, 0, MyVirtualBook.this.P, MyVirtualBook.this.Q, this);
                        graphics.drawImage(this.b[MyVirtualBook.this.E + 1], 0, 0, MyVirtualBook.this.A * 10, MyVirtualBook.this.Q, this);
                    }
                }
                if (MyVirtualBook.this.B == 2) {
                    if (this.equals(MyVirtualBook.this.f)) {
                        graphics.drawImage(this.a[MyVirtualBook.this.E - 2], 0, 0, MyVirtualBook.this.P, MyVirtualBook.this.Q, this);
                        graphics.drawImage(this.b[MyVirtualBook.this.E], MyVirtualBook.this.y - 10, 0, MyVirtualBook.this.A * 10, MyVirtualBook.this.Q, this);
                    }
                    if (this.equals(MyVirtualBook.this.g)) {
                        graphics.drawImage(this.a[MyVirtualBook.this.E + 1], 0, 0, MyVirtualBook.this.P, MyVirtualBook.this.Q, this);
                        graphics.drawImage(this.b[MyVirtualBook.this.E - 1], MyVirtualBook.this.A / 10 - 10, 0, MyVirtualBook.this.x, MyVirtualBook.this.Q, this);
                    }
                }
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                MyVirtualBook.this.showStatus(a_.e);
            }
        }
        
        static {
            final char[] charArray = "l2\u0018i\u0007\u0013@\u0004Iu@\r+A0Z@,I G\u0004".toCharArray();
            final int i = charArray.length;
            for (int n = 0; i > n; ++n) {
                final int n2 = n;
                final char c = charArray[n2];
                char c2 = '\0';
                switch (n % 5) {
                    case 0: {
                        c2 = ')';
                        break;
                    }
                    case 1: {
                        c2 = '`';
                        break;
                    }
                    case 2: {
                        c2 = 'J';
                        break;
                    }
                    case 3: {
                        c2 = '&';
                        break;
                    }
                    default: {
                        c2 = 'U';
                        break;
                    }
                }
                charArray[n2] = (char)(c ^ c2);
            }
            a_.e = new String(charArray).intern();
        }
    }
}
