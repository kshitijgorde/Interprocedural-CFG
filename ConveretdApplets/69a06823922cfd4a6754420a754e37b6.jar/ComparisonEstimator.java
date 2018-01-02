import java.awt.MenuBar;
import java.awt.event.MouseEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Color;
import java.util.Hashtable;
import java.awt.Panel;
import java.awt.Label;
import java.awt.Button;
import java.awt.Choice;
import java.awt.event.MouseListener;
import java.awt.event.ComponentListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ComparisonEstimator extends Applet implements ActionListener, ItemListener, ComponentListener, MouseListener, nb
{
    private static final long serialVersionUID = 8348435191842246321L;
    private final int a = 300;
    private final int b = 300;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private final String[] g;
    private final int h = 0;
    private final int i = 1;
    private final int[] j;
    private final String[] k;
    protected j l;
    private int m;
    private int n;
    private ob o;
    private k p;
    private f q;
    private f r;
    private Choice s;
    private Button t;
    private Button u;
    private Button v;
    private Button w;
    private Label x;
    private Label y;
    private Label z;
    private Choice A;
    private Label B;
    private Choice C;
    private Panel D;
    private Panel E;
    private Panel F;
    private b G;
    private b H;
    private Panel I;
    private l J;
    private boolean K;
    private boolean L;
    private int M;
    private ib N;
    private static boolean O;
    private static Hashtable P;
    private static final String[] Q;
    
    public ComparisonEstimator() {
        this.c = ComparisonEstimator.Q[7];
        this.d = ComparisonEstimator.Q[4];
        this.e = ComparisonEstimator.Q[2];
        this.f = ComparisonEstimator.Q[1];
        this.g = new String[] { ComparisonEstimator.Q[32], ComparisonEstimator.Q[30] };
        this.j = new int[] { 0, 1 };
        this.k = new String[] { ComparisonEstimator.Q[31], ComparisonEstimator.Q[29] };
        this.m = this.j[0];
        this.K = false;
        this.L = false;
        this.M = 0;
    }
    
    public void init() {
        final int u = a.u;
        if (ComparisonEstimator.P == null) {
            ComparisonEstimator.P = jb.a(this);
        }
        if (ComparisonEstimator.P == null) {
            System.exit(1);
        }
        this.o = new ob();
        if (this.getParameter(ComparisonEstimator.Q[21]) != null) {
            try {
                this.L = new Boolean(this.getParameter(ComparisonEstimator.Q[21]).trim());
            }
            catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        }
        this.setBackground(Color.white);
        this.q = new f("", this.getSize().width / 3, 30, 1);
        this.r = new f("", this.getSize().width / 3, 30, 0);
        this.s = new Choice();
        int i = 0;
        while (i < this.k.length) {
            this.s.add(this.k[i]);
            ++i;
            if (u != 0) {
                int a = q.a;
                q.a = ++a;
                break;
            }
        }
        (this.t = new Button(ComparisonEstimator.Q[14])).addActionListener(this);
        (this.u = new Button(ComparisonEstimator.Q[17])).addActionListener(this);
        (this.v = new Button(ComparisonEstimator.Q[16])).addActionListener(this);
        this.x = new Label(" ", 1);
        this.y = new Label(" ", 1);
        this.z = new Label(ComparisonEstimator.Q[22]);
        (this.A = new Choice()).add(ComparisonEstimator.Q[7]);
        this.A.add(ComparisonEstimator.Q[4]);
        this.A.add(ComparisonEstimator.Q[2]);
        this.A.add(ComparisonEstimator.Q[1]);
        this.A.addItemListener(this);
        this.A.addMouseListener(this);
        this.B = new Label(ComparisonEstimator.Q[12]);
        this.C = new Choice();
        int j = 0;
        while (j < this.g.length) {
            this.C.add("" + this.g[j]);
            ++j;
            if (u != 0) {
                break;
            }
        }
        this.C.addItemListener(this);
        this.C.addMouseListener(this);
        this.G = new b();
        this.H = new b();
        this.G.addComponentListener(this);
        this.H.addComponentListener(this);
        this.n = (int)Math.floor(this.o.b() * 4.0);
        this.G.b(this.n);
        this.H.b(this.n);
        this.G.setSize(300, 300);
        this.H.setSize(300, 300);
        (this.D = new Panel()).add(this.q);
        this.D.add(this.s);
        this.D.add(this.r);
        (this.E = new Panel(new FlowLayout(1))).add(this.t);
        this.E.add(this.u);
        this.E.add(this.v);
        (this.F = new Panel(new FlowLayout(1))).add(this.z);
        this.F.add(this.A);
        this.F.add(this.B);
        this.F.add(this.C);
        (this.I = new Panel(new FlowLayout(1))).add(this.G);
        this.I.add(this.H);
        (this.w = new Button(ComparisonEstimator.Q[18])).addActionListener(this);
        this.J = new l(new String[] { ComparisonEstimator.Q[4], ComparisonEstimator.Q[2], ComparisonEstimator.Q[1] }, ComparisonEstimator.Q[11], true, this, this);
        final Panel panel = new Panel(new lb(ComparisonEstimator.Q[10]));
        final int n = (this.getSize().width - 200 - 325) / 2 - 100;
        panel.add(ComparisonEstimator.Q[13], this.w);
        panel.add(ComparisonEstimator.Q[15], new o(this.J));
        panel.add(ComparisonEstimator.Q[13], new f("", n, 30, 0));
        panel.add(ComparisonEstimator.Q[13], new p(this));
        this.setLayout(new mb(ComparisonEstimator.Q[15]));
        this.add(ComparisonEstimator.Q[20], this.D);
        this.add(ComparisonEstimator.Q[20], this.E);
        this.add(ComparisonEstimator.Q[20], this.F);
        this.add(ComparisonEstimator.Q[20], this.x);
        this.add(ComparisonEstimator.Q[20], this.y);
        this.add(ComparisonEstimator.Q[15], this.I);
        this.add(ComparisonEstimator.Q[15], panel);
        if (this.getParameter(ComparisonEstimator.Q[21]) != null && new Boolean(this.getParameter(ComparisonEstimator.Q[21]).trim())) {
            int k = 0;
            while (k < hb.b.length) {
                System.out.println(hb.b[k] + ComparisonEstimator.Q[19] + this.getParameter(hb.b[k]));
                ++k;
                if (u != 0) {
                    break;
                }
            }
        }
        if (this.getParameter(hb.b[5]) != null && new Boolean(this.getParameter(hb.b[5]))) {
            (this.N = new ib(this.getParameter(hb.b[2]), Integer.parseInt(this.getParameter(hb.b[3])), this.getParameter(hb.b[4]), Integer.parseInt(this.getParameter(hb.b[1])), Integer.parseInt(this.getParameter(hb.b[0])))).a(this, ComparisonEstimator.Q[9]);
        }
    }
    
    public void stop() {
        if (this.J != null) {
            this.J.setVisible(false);
            this.J.dispose();
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final int u = a.u;
        if (ComparisonEstimator.O && n.a(actionEvent)) {
            return;
        }
        if (actionEvent.getSource() == this.t) {
            this.a();
            if (u == 0) {
                return;
            }
        }
        if (actionEvent.getSource() == this.u) {
            if (this.J.f() && !this.K && hb.a) {
                this.n = this.G.b();
                Label_0128: {
                    if (this.n == 0 || this.n == 1) {
                        this.n = 0;
                        if (u == 0) {
                            break Label_0128;
                        }
                    }
                    if (this.n == 2) {
                        this.n = 1;
                        if (u == 0) {
                            break Label_0128;
                        }
                    }
                    this.n = 2;
                }
                if (this.N != null) {
                    this.a(this.n, false);
                }
                if (this.J != null) {
                    this.J.b(this.n);
                }
            }
            this.b();
            if (u == 0) {
                return;
            }
        }
        if (actionEvent.getSource() == this.v) {
            this.G.d();
            this.H.d();
            if (u == 0) {
                return;
            }
        }
        if (actionEvent.getSource() == this.w && (this.p == null || !this.p.isShowing())) {
            if (this.m == 0) {
                (this.p = new k(this, this)).setVisible(true);
                if (u == 0) {
                    return;
                }
            }
            this.a(ComparisonEstimator.Q[33]);
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        final int u = a.u;
        if (itemEvent.getSource() == this.A || (itemEvent.getSource() == this.C && this.j[this.C.getSelectedIndex()] != this.m)) {
            if (this.J.f() && !this.K && hb.a) {
                this.n = this.G.b();
                Label_0125: {
                    if (this.n == 0 || this.n == 1) {
                        this.n = 0;
                        if (u == 0) {
                            break Label_0125;
                        }
                    }
                    if (this.n == 2) {
                        this.n = 1;
                        if (u == 0) {
                            break Label_0125;
                        }
                    }
                    this.n = 2;
                }
                if (this.N != null) {
                    this.a(this.n, false);
                }
                if (this.J != null) {
                    this.J.b(this.n);
                }
            }
            this.w.setEnabled(this.C.getSelectedIndex() == 0);
            this.b();
        }
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        this.b();
        this.validate();
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.repaint();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    private void a() {
        final int u = a.u;
        boolean b = false;
        if (this.K) {
            return;
        }
        int b2 = this.G.b();
        Label_0053: {
            if (b2 == 0 || b2 == 1) {
                b2 = 0;
                if (u == 0) {
                    break Label_0053;
                }
            }
            if (b2 == 2) {
                b2 = 1;
                if (u == 0) {
                    break Label_0053;
                }
            }
            b2 = 2;
        }
        Label_0144: {
            if (this.s.getSelectedIndex() == 0 && this.G.c() > this.H.c()) {
                b = true;
                if (u == 0) {
                    break Label_0144;
                }
            }
            if (this.s.getSelectedIndex() == 1 && this.G.c() < this.H.c()) {
                b = true;
                if (u == 0) {
                    break Label_0144;
                }
            }
            if (this.G.c() == this.H.c()) {
                b = true;
            }
        }
        if (b) {
            final String s = new String();
            final String a = this.G.a(ComparisonEstimator.Q[28]);
            this.x.setText(ComparisonEstimator.Q[27] + (Character.toUpperCase(a.charAt(0)) + a.substring(1) + ComparisonEstimator.Q[25] + this.H.a(ComparisonEstimator.Q[23]) + "."));
            this.y.setText(ComparisonEstimator.Q[26]);
            this.K = true;
            this.J.a(b2);
            this.a(b2, true);
            if (u == 0) {
                return;
            }
        }
        this.x.setText(ComparisonEstimator.Q[24]);
        this.y.setText("");
        this.J.b(b2);
        this.a(b2, false);
    }
    
    private void a(final int n, final boolean b) {
        this.K = true;
        if (this.N == null || this.J == null || !this.J.f()) {
            return;
        }
        this.N.a(this.a(n), b, this.C.getSelectedIndex() + 1);
    }
    
    private int a(final int n) {
        switch (n) {
            case 0: {
                return 122;
            }
            case 1: {
                return 123;
            }
            case 2: {
                return 124;
            }
            default: {
                return 122;
            }
        }
    }
    
    private void b() {
        final int u = a.u;
        if (this.N != null) {
            this.N.h();
        }
        final String selectedItem = this.A.getSelectedItem();
        Label_0164: {
            if (selectedItem.equals(ComparisonEstimator.Q[7])) {
                this.n = (int)Math.floor(this.o.b() * 4.0);
                if (u == 0) {
                    break Label_0164;
                }
            }
            if (selectedItem.equals(ComparisonEstimator.Q[4])) {
                this.n = ((this.o.b() >= 0.5) ? 1 : 0);
                if (u == 0) {
                    break Label_0164;
                }
            }
            if (selectedItem.equals(ComparisonEstimator.Q[2])) {
                this.n = 2;
                if (u == 0) {
                    break Label_0164;
                }
            }
            if (selectedItem.equals(ComparisonEstimator.Q[1])) {
                this.n = 3;
                if (u == 0) {
                    break Label_0164;
                }
            }
            System.err.println(ComparisonEstimator.Q[3]);
            this.n = 4;
        }
        this.G.b(this.n);
        this.H.b(this.n);
        this.m = this.j[this.C.getSelectedIndex()];
        ++this.M;
        final int[] c = this.o.c();
        final int[] array = new int[4];
        int i = 0;
        while (i < c.length) {
            array[i] = (c[i] + this.M + (i + 1)) % 10000;
            ++i;
            if (u != 0) {
                break;
            }
        }
        array[3] = (2 * array[3] + 1) % 10000;
        final ob ob = new ob(array);
        int j = 0;
        while (j < c.length) {
            array[j] = (c[j] + this.M + (j + 5)) % 10000;
            ++j;
            if (u != 0) {
                break;
            }
        }
        array[3] = (2 * array[3] + 1) % 10000;
        final ob ob2 = new ob(array);
        int k = 0;
        while (k < c.length) {
            array[k] = (c[k] + this.M + (k + 9)) % 10000;
            ++k;
            if (u != 0) {
                break;
            }
        }
        array[3] = (2 * array[3] + 1) % 10000;
        final ob ob3 = new ob(array);
        int l = 0;
        while (l < c.length) {
            array[l] = (c[l] + this.M + (l + 13)) % 10000;
            ++l;
            if (u != 0) {
                break;
            }
        }
        array[3] = (2 * array[3] + 1) % 10000;
        final ob ob4 = new ob(array);
        int n = 0;
        while (n < c.length) {
            array[n] = (c[n] + this.M + (n + 17)) % 10000;
            ++n;
            if (u != 0) {
                break;
            }
        }
        array[3] = (2 * array[3] + 1) % 10000;
        final ob ob5 = new ob(array);
        Label_0614: {
            if (this.m == 0) {
                this.G.a(ob.c(), ob2.c());
                this.H.a(ob3.c(), this.G.a()[1]);
                if (u == 0) {
                    break Label_0614;
                }
            }
            this.H.a(ob4.c(), ob5.c());
        }
        this.G.a((this.m == 0) ? 0 : 2);
        this.H.a((this.m == 0) ? 0 : 2);
        this.G.f();
        this.H.f();
        while (Math.abs(this.G.c() - this.H.c()) / Math.max(this.G.c(), this.H.c()) < 0.05) {
            this.G.f();
            this.H.f();
            if (u != 0) {
                break;
            }
        }
        final String s = new String();
        final String a = this.G.a();
        this.q.a(Character.toUpperCase(a.charAt(0)) + a.substring(1) + ComparisonEstimator.Q[8]);
        this.r.a(this.H.a() + ComparisonEstimator.Q[0]);
        this.x.setText("");
        this.y.setText("");
        this.K = false;
        if (this.L && this.G.c() > 0.0 && this.H.c() > 0.0) {
            System.out.println(ComparisonEstimator.Q[5] + ub.a(this.G.c(), 1));
            System.out.println(ComparisonEstimator.Q[6] + ub.a(this.H.c(), 1));
        }
    }
    
    public String getParameter(final String s) {
        return hb.a(s) ? super.getParameter(s) : ComparisonEstimator.P.get(s);
    }
    
    public static void main(final String[] array) {
        ComparisonEstimator.O = true;
        final ComparisonEstimator comparisonEstimator = new ComparisonEstimator();
        new m(ComparisonEstimator.P = jb.a(comparisonEstimator), comparisonEstimator, n.b(comparisonEstimator)).validate();
    }
    
    public void a(final int[] array) {
        if (this.C.getSelectedIndex() != 0) {
            this.a(ComparisonEstimator.Q[33]);
            return;
        }
        this.M = 0;
        this.o.a(array[0], array[1], array[2], array[3]);
        this.b();
    }
    
    public void a(final String s) {
        this.a(ComparisonEstimator.Q[34], s);
    }
    
    public void a(final String s, final String s2) {
        if (this.l == null || !this.l.isShowing()) {
            (this.l = new j(j.a(this.getParent()), s, s2)).setVisible(true);
        }
    }
    
    static {
        final String[] q = new String[35];
        final int n = 0;
        final char[] charArray = "E\u00176^K\r\u001dx\fV\u0002\u0010,P".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'e';
                    break;
                }
                case 1: {
                    c2 = 'x';
                    break;
                }
                case 2: {
                    c2 = 'X';
                    break;
                }
                case 3: {
                    c2 = '~';
                    break;
                }
                default: {
                    c2 = '?';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        q[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "$\n=\u001f".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'e';
                    break;
                }
                case 1: {
                    c4 = 'x';
                    break;
                }
                case 2: {
                    c4 = 'X';
                    break;
                }
                case 3: {
                    c4 = '~';
                    break;
                }
                default: {
                    c4 = '?';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        q[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = ")\u001d6\u0019K\r".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = 'e';
                    break;
                }
                case 1: {
                    c6 = 'x';
                    break;
                }
                case 2: {
                    c6 = 'X';
                    break;
                }
                case 3: {
                    c6 = '~';
                    break;
                }
                default: {
                    c6 = '?';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        q[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "0\u0016*\u001b\\\n\u001f6\u0017E\u0000\u001cx\u000eM\n\u001a4\u001bRE\f!\u000eZ".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = 'e';
                    break;
                }
                case 1: {
                    c8 = 'x';
                    break;
                }
                case 2: {
                    c8 = 'X';
                    break;
                }
                case 3: {
                    c8 = '~';
                    break;
                }
                default: {
                    c8 = '?';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        q[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "&\u0017-\u0010K\f\u0016?".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = 'e';
                    break;
                }
                case 1: {
                    c10 = 'x';
                    break;
                }
                case 2: {
                    c10 = 'X';
                    break;
                }
                case 3: {
                    c10 = '~';
                    break;
                }
                default: {
                    c10 = '?';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        q[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = ")\u001d>\n\u0005E".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = 'e';
                    break;
                }
                case 1: {
                    c12 = 'x';
                    break;
                }
                case 2: {
                    c12 = 'X';
                    break;
                }
                case 3: {
                    c12 = '~';
                    break;
                }
                default: {
                    c12 = '?';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        q[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "7\u0011?\u0016K_X".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = 'e';
                    break;
                }
                case 1: {
                    c14 = 'x';
                    break;
                }
                case 2: {
                    c14 = 'X';
                    break;
                }
                case 3: {
                    c14 = '~';
                    break;
                }
                default: {
                    c14 = '?';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        q[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "7\u00196\u001aP\b".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = 'e';
                    break;
                }
                case 1: {
                    c16 = 'x';
                    break;
                }
                case 2: {
                    c16 = 'X';
                    break;
                }
                case 3: {
                    c16 = '~';
                    break;
                }
                default: {
                    c16 = '?';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        q[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "E\u00176^K\r\u001dx\u0012Z\u0003\fx\u0017L".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = 'e';
                    break;
                }
                case 1: {
                    c18 = 'x';
                    break;
                }
                case 2: {
                    c18 = 'X';
                    break;
                }
                case 3: {
                    c18 = '~';
                    break;
                }
                default: {
                    c18 = '?';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        q[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = "(1\u0011;O$1\u001a?~.;\u0019/z$\f/9\u0014(\f.\u0004n\u0012\fj\u000e|J)53p(?o=l\u0012\u001e1GV\u001c+\u000e\u000f{\u001f*\rME\b2/1X'\u001f\b\u000b^\u0002\u000002V\tA=9Y=*\u0000\u0013P4\u0002+\u000e\u00141Ho\u0015NT\u000b0\u0014fS:h/\tU\u0011 \u001fK&(\u001e\u001a~\u0007\u001646p\u001f4,-n<\n\n&s7\")\u001ct\u001d\u00170*k\u0012\u0011-I\u0007.\u001d>\u0015]*@\u001d$\b\u001f=\u00136Z] m;F\u0004\u001f-F\u0006\u0002=\u0017\fP->3\u0012E\u0013\u0016,\rV\u0013\u0001,3\b1//$P#H\u00004z.-\u00122I3 \u0017\tg==59Y#K`=[\\\u00166\u0006Q\u0011 (=W6\u001d<<u-5k7\u000e-\u0001o\u0014\f\u00149hUmR-\rIM4\u001391J\u000e\u0014\u0000\rq1H\u001bHJ)Om5e(\n\u0017&\u0010=\u000f\u001b;p(\u0012\f\frPJ\u0019\u0016\b\u0017\u000e\b.x5\u0019\u00028\f\u000b2\u0014H\u0006\u0013/`\u000fI)),\u0018I*\u00125*\u0010VS\u001f0O7\u0015i9G!\fl:N$O1\u000eg4\ba?Y$+\u0017'H.*-)pT)/7{$)\u0019<~\n1\u001a?}\u0012\b\u000fJ}\u0010=5\u0019[\u001c\u0016+6S/\u0002a\u001dP 5\r\u0018T\r\u001b-9w\t\n\t(\b]\u0000=5I\f,s*L1\u000e;\n\nR\u0012\u0002\u0006j\u0002\t\u001f-{UO,9g\u00161a<UW6??\\\u0003(\n/R\u0014Aj=q\r\u0011=UH\t!\u001cUj\u0012372\u000f\b\u00133\u0014[\u00105/?n(Wm&u?\b1<|<6\u001d\u000f](6\u0000L}P\u00169\u0015p)\u001c\u000e\u0018e\u0015\u0012\u00174o1<\u0012\u0018\t\u000bN\"?\u0007<\f\u0016)\u0006P\u0010\u00158\t\u000e\b1\u0014h227:\u0007\fN1\u0018O#>\u000e3wT?:Nj*Sj:{3\u0015\u000e7[\u0006\u0000(GU\u0014/\u0019\u001d^4\n0\u0012R]S\u0015$W0\u000e\u001e\u0015j7I\"GV\u0006\u0017\u00001o-\njJJ\u0017-kN^#4!*J)\u0015\u000f<gSM9:i0S36QU1++Y\"\n55H/<9'v\u0011)\n/MP\u001d+\u0016\n\u0010;(&\u000f/3l\u0013K\u000436OE44>']* \u000b8T\u0015>\u0001\be\r4\u0014NxQ*j\u0007p\\)7\u0016h\t1k\u001fn ;?'z$J\fLe,S\u0001&M\u001d\u001a\u001a(\r5J?\u0013H/\u0019\u0010Kl\u0013)\u000e\u0007\u0006$\u000bk\fi\u00019\u0012F|<)?\u001a\u000b+I\u0019\u0010f27=8H7<\u0019\u0012p]\u001b7't \u001c+Nx\u001c)\u001e5H\u001f!\u001a\u0018}+-\u0002MtN\u0017?\u001aN\u0000,a\f\u00101S\u000f\u000eT\u0014,.H~\u001d+\u0016\u0017] @\u001f1x\u001c l&p21\u000f\u0014[7K\u0015N^10\tJj 2-\u0014g\u0007\u001fl+\\\u0007\r6\u0010N0;<0]4,\r\u0004z/\"0\fw\u000f\u00169\u0012t\n=\u001b\u0019f 9iHV\"\u0010k\rQ.<\u000f\u0017}\u00015\u0011;w\u0000\u0012\n:P<2\u0000N\u000e\\\u001e-\u000em\rN\u001aU~#\u0015\u001cN\u000fU+\u0010\u000ef(Nw)u\u0015O\u001bOKQ>1=\u000bTN\u001bHT\u001dS.;g\n>\u0002\u000fhR\r\f\u000br,\u0012i+m=*h\u000fmP\u000e\u0016Ql51\u00137\u0007\u0004\njLv\"05Gj\f+*IM!\u000bmMG!N4;t0\u001d6\u0016W(>,\u0015MN6\"N\f\u0012\u001c\u000b.\b?,\u001fO|N\u001f\t2q3\u0000\t+V(@\t\u000b\fT\u001b\u0015=X<9\u0013\u000e^<K!:r\nA9\u0017r.Sl3\u0010\t\u0019\u0010\nv\t:3\u0012\b)O\":o<\u0010\u0016)\fWJ\u000fIKR2<\u0004XWL:\u001d}U\u001b=\u0013\u0014<\r5Jj)\u0014\u001e-j]\u0016h\nm\u000f6\u0017$S\u0010\u0014\"3O6\u001b\u0013\rZ\u0015\"w\u000b\rJWo\u0010h\u000124<s\u0012\u0001o+j/\u0017=&X/\u0010\u0012\u001cU\t\t +wP\u0016k\u0016x=\u00103)|\r45<j\u0011\b*)\bR>\u00190Q#\u0000<:J\u00177\u001bOS\u001d/)O\rU\u0017\u0001\u0012~N9\t5}\u0002)\u001b\u001fZW\u000f!Is(J\u001b7i7\t+.V#W\u001e\u000fi!\u000b*\u0004\tN\u0000\"O\t,\u00120JwSS1-x=\u0016\r)iN2\u000e\bv*\b\u001c\u001aO\\L\"H\t\fA\b\u001cp\nM\u001dGV(L0\u001b\t2/mJr$ \u0000\nK?<\u0000\u0004L 2\u0015&~&\u001a-G\u000b\r,m\u0017g\u0014\r\u0010\u001c]6\u001d,$\r\bJ\u0012JGJ\u0014\u000f\u0016T\u0017\u001f\u0016\u000fJ-\u0012lQn6-,\u000fs\u0014\u0011\u00147n3\u00192\u001bM\u000eM7O\u0010\u0016<kHG\\Ss'\u0014\f;\n/t'\u001f\t=h\u0015\u001a\n)nQ2o\u001a[73\u0017\u0004l\u001f7\u001b\br\u0014N:8y\u000b.0:I305&m\u0016\bhF\r4/\u0016\u001a\u000f\u0004Jm\u0018O'<a\u001aF\u0016W=\u001bx\u0010>a.LP=n)e*\u0014s,n\n\u000f*\u0015p=\rk\u0010\u0006\u0011\"h\u001cg&,<(Q]70\u000fi\u0007L\u0010Qh\u0015\u0012\u001c7]R7;\u001cr\u00149m\u0006L2K\u0002\u001aj\u001f;\u001a4eQ:\u0015\u0017\\+?\u0013\u0011U\u0000<hIE\u000e@\u0002\u0015F\u00126s;\rQN(:X\u0015\u000f\u000eOlU\u0002\u00100HXE".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = 'e';
                    break;
                }
                case 1: {
                    c20 = 'x';
                    break;
                }
                case 2: {
                    c20 = 'X';
                    break;
                }
                case 3: {
                    c20 = '~';
                    break;
                }
                default: {
                    c20 = '?';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        q[n28] = new String(charArray10).intern();
        final int n31 = 10;
        final char[] charArray11 = "+\u0017*\nW".toCharArray();
        final int length7 = charArray11.length;
        for (int n32 = 0; length7 > n32; ++n32) {
            final int n33 = n32;
            final char c21 = charArray11[n33];
            char c22 = '\0';
            switch (n32 % 5) {
                case 0: {
                    c22 = 'e';
                    break;
                }
                case 1: {
                    c22 = 'x';
                    break;
                }
                case 2: {
                    c22 = 'X';
                    break;
                }
                case 3: {
                    c22 = '~';
                    break;
                }
                default: {
                    c22 = '?';
                    break;
                }
            }
            charArray11[n33] = (char)(c21 ^ c22);
        }
        q[n31] = new String(charArray11).intern();
        final int n34 = 11;
        final char[] charArray12 = "6\u001b7\fZ".toCharArray();
        final int length8 = charArray12.length;
        for (int n35 = 0; length8 > n35; ++n35) {
            final int n36 = n35;
            final char c23 = charArray12[n36];
            char c24 = '\0';
            switch (n35 % 5) {
                case 0: {
                    c24 = 'e';
                    break;
                }
                case 1: {
                    c24 = 'x';
                    break;
                }
                case 2: {
                    c24 = 'X';
                    break;
                }
                case 3: {
                    c24 = '~';
                    break;
                }
                default: {
                    c24 = '?';
                    break;
                }
            }
            charArray12[n36] = (char)(c23 ^ c24);
        }
        q[n34] = new String(charArray12).intern();
        final int n37 = 12;
        final char[] charArray13 = "!\u0011>\u0018V\u0006\r4\nF_X".toCharArray();
        final int length9 = charArray13.length;
        for (int n38 = 0; length9 > n38; ++n38) {
            final int n39 = n38;
            final char c25 = charArray13[n39];
            char c26 = '\0';
            switch (n38 % 5) {
                case 0: {
                    c26 = 'e';
                    break;
                }
                case 1: {
                    c26 = 'x';
                    break;
                }
                case 2: {
                    c26 = 'X';
                    break;
                }
                case 3: {
                    c26 = '~';
                    break;
                }
                default: {
                    c26 = '?';
                    break;
                }
            }
            charArray13[n39] = (char)(c25 ^ c26);
        }
        q[n37] = new String(charArray13).intern();
        final int n40 = 13;
        final char[] charArray14 = "2\u001d+\n".toCharArray();
        final int length10 = charArray14.length;
        for (int n41 = 0; length10 > n41; ++n41) {
            final int n42 = n41;
            final char c27 = charArray14[n42];
            char c28 = '\0';
            switch (n41 % 5) {
                case 0: {
                    c28 = 'e';
                    break;
                }
                case 1: {
                    c28 = 'x';
                    break;
                }
                case 2: {
                    c28 = 'X';
                    break;
                }
                case 3: {
                    c28 = '~';
                    break;
                }
                default: {
                    c28 = '?';
                    break;
                }
            }
            charArray14[n42] = (char)(c27 ^ c28);
        }
        q[n40] = new String(charArray14).intern();
        final int n43 = 14;
        final char[] charArray15 = "&\u0010=\u001dTE96\rH\u0000\n".toCharArray();
        final int length11 = charArray15.length;
        for (int n44 = 0; length11 > n44; ++n44) {
            final int n45 = n44;
            final char c29 = charArray15[n45];
            char c30 = '\0';
            switch (n44 % 5) {
                case 0: {
                    c30 = 'e';
                    break;
                }
                case 1: {
                    c30 = 'x';
                    break;
                }
                case 2: {
                    c30 = 'X';
                    break;
                }
                case 3: {
                    c30 = '~';
                    break;
                }
                default: {
                    c30 = '?';
                    break;
                }
            }
            charArray15[n45] = (char)(c29 ^ c30);
        }
        q[n43] = new String(charArray15).intern();
        final int n46 = 15;
        final char[] charArray16 = "&\u001d6\nZ\u0017".toCharArray();
        final int length12 = charArray16.length;
        for (int n47 = 0; length12 > n47; ++n47) {
            final int n48 = n47;
            final char c31 = charArray16[n48];
            char c32 = '\0';
            switch (n47 % 5) {
                case 0: {
                    c32 = 'e';
                    break;
                }
                case 1: {
                    c32 = 'x';
                    break;
                }
                case 2: {
                    c32 = 'X';
                    break;
                }
                case 3: {
                    c32 = '~';
                    break;
                }
                default: {
                    c32 = '?';
                    break;
                }
            }
            charArray16[n48] = (char)(c31 ^ c32);
        }
        q[n46] = new String(charArray16).intern();
        final int n49 = 16;
        final char[] charArray17 = "-\u00116\n".toCharArray();
        final int length13 = charArray17.length;
        for (int n50 = 0; length13 > n50; ++n50) {
            final int n51 = n50;
            final char c33 = charArray17[n51];
            char c34 = '\0';
            switch (n50 % 5) {
                case 0: {
                    c34 = 'e';
                    break;
                }
                case 1: {
                    c34 = 'x';
                    break;
                }
                case 2: {
                    c34 = 'X';
                    break;
                }
                case 3: {
                    c34 = '~';
                    break;
                }
                default: {
                    c34 = '?';
                    break;
                }
            }
            charArray17[n51] = (char)(c33 ^ c34);
        }
        q[n49] = new String(charArray17).intern();
        final int n52 = 17;
        final char[] charArray18 = "+\u001d \n\u001f5\n7\u001cS\u0000\u0015".toCharArray();
        final int length14 = charArray18.length;
        for (int n53 = 0; length14 > n53; ++n53) {
            final int n54 = n53;
            final char c35 = charArray18[n54];
            char c36 = '\0';
            switch (n53 % 5) {
                case 0: {
                    c36 = 'e';
                    break;
                }
                case 1: {
                    c36 = 'x';
                    break;
                }
                case 2: {
                    c36 = 'X';
                    break;
                }
                case 3: {
                    c36 = '~';
                    break;
                }
                default: {
                    c36 = '?';
                    break;
                }
            }
            charArray18[n54] = (char)(c35 ^ c36);
        }
        q[n52] = new String(charArray18).intern();
        final int n55 = 18;
        final char[] charArray19 = "6\u001d=\u001a\u001f7\u00196\u001aP\b".toCharArray();
        final int length15 = charArray19.length;
        for (int n56 = 0; length15 > n56; ++n56) {
            final int n57 = n56;
            final char c37 = charArray19[n57];
            char c38 = '\0';
            switch (n56 % 5) {
                case 0: {
                    c38 = 'e';
                    break;
                }
                case 1: {
                    c38 = 'x';
                    break;
                }
                case 2: {
                    c38 = 'X';
                    break;
                }
                case 3: {
                    c38 = '~';
                    break;
                }
                default: {
                    c38 = '?';
                    break;
                }
            }
            charArray19[n57] = (char)(c37 ^ c38);
        }
        q[n55] = new String(charArray19).intern();
        final int n58 = 19;
        final char[] charArray20 = "_X".toCharArray();
        final int length16 = charArray20.length;
        for (int n59 = 0; length16 > n59; ++n59) {
            final int n60 = n59;
            final char c39 = charArray20[n60];
            char c40 = '\0';
            switch (n59 % 5) {
                case 0: {
                    c40 = 'e';
                    break;
                }
                case 1: {
                    c40 = 'x';
                    break;
                }
                case 2: {
                    c40 = 'X';
                    break;
                }
                case 3: {
                    c40 = '~';
                    break;
                }
                default: {
                    c40 = '?';
                    break;
                }
            }
            charArray20[n60] = (char)(c39 ^ c40);
        }
        q[n58] = new String(charArray20).intern();
        final int n61 = 20;
        final char[] charArray21 = "#\u00114\u0012".toCharArray();
        final int length17 = charArray21.length;
        for (int n62 = 0; length17 > n62; ++n62) {
            final int n63 = n62;
            final char c41 = charArray21[n63];
            char c42 = '\0';
            switch (n62 % 5) {
                case 0: {
                    c42 = 'e';
                    break;
                }
                case 1: {
                    c42 = 'x';
                    break;
                }
                case 2: {
                    c42 = 'X';
                    break;
                }
                case 3: {
                    c42 = '~';
                    break;
                }
                default: {
                    c42 = '?';
                    break;
                }
            }
            charArray21[n63] = (char)(c41 ^ c42);
        }
        q[n61] = new String(charArray21).intern();
        final int n64 = 21;
        final char[] charArray22 = "\u0001\u001d:\u000bX".toCharArray();
        final int length18 = charArray22.length;
        for (int n65 = 0; length18 > n65; ++n65) {
            final int n66 = n65;
            final char c43 = charArray22[n66];
            char c44 = '\0';
            switch (n65 % 5) {
                case 0: {
                    c44 = 'e';
                    break;
                }
                case 1: {
                    c44 = 'x';
                    break;
                }
                case 2: {
                    c44 = 'X';
                    break;
                }
                case 3: {
                    c44 = '~';
                    break;
                }
                default: {
                    c44 = '?';
                    break;
                }
            }
            charArray22[n66] = (char)(c43 ^ c44);
        }
        q[n64] = new String(charArray22).intern();
        final int n67 = 22;
        final char[] charArray23 = "5\n7\u001cS\u0000\u0015x*F\u0015\u001db^".toCharArray();
        final int length19 = charArray23.length;
        for (int n68 = 0; length19 > n68; ++n68) {
            final int n69 = n68;
            final char c45 = charArray23[n69];
            char c46 = '\0';
            switch (n68 % 5) {
                case 0: {
                    c46 = 'e';
                    break;
                }
                case 1: {
                    c46 = 'x';
                    break;
                }
                case 2: {
                    c46 = 'X';
                    break;
                }
                case 3: {
                    c46 = '~';
                    break;
                }
                default: {
                    c46 = '?';
                    break;
                }
            }
            charArray23[n69] = (char)(c45 ^ c46);
        }
        q[n67] = new String(charArray23).intern();
        final int n70 = 23;
        final char[] charArray24 = "\n\u0016x\nW\u0000X*\u0017X\r\f".toCharArray();
        final int length20 = charArray24.length;
        for (int n71 = 0; length20 > n71; ++n71) {
            final int n72 = n71;
            final char c47 = charArray24[n72];
            char c48 = '\0';
            switch (n71 % 5) {
                case 0: {
                    c48 = 'e';
                    break;
                }
                case 1: {
                    c48 = 'x';
                    break;
                }
                case 2: {
                    c48 = 'X';
                    break;
                }
                case 3: {
                    c48 = '~';
                    break;
                }
                default: {
                    c48 = '?';
                    break;
                }
            }
            charArray24[n72] = (char)(c47 ^ c48);
        }
        q[n70] = new String(charArray24).intern();
        final int n73 = 24;
        final char[] charArray25 = ",\u0016;\u0011M\u0017\u001d;\n".toCharArray();
        final int length21 = charArray25.length;
        for (int n74 = 0; length21 > n74; ++n74) {
            final int n75 = n74;
            final char c49 = charArray25[n75];
            char c50 = '\0';
            switch (n74 % 5) {
                case 0: {
                    c50 = 'e';
                    break;
                }
                case 1: {
                    c50 = 'x';
                    break;
                }
                case 2: {
                    c50 = 'X';
                    break;
                }
                case 3: {
                    c50 = '~';
                    break;
                }
                default: {
                    c50 = '?';
                    break;
                }
            }
            charArray25[n75] = (char)(c49 ^ c50);
        }
        q[n73] = new String(charArray25).intern();
        final int n76 = 25;
        final char[] charArray26 = "E\u00196\u001a\u001f".toCharArray();
        final int length22 = charArray26.length;
        for (int n77 = 0; length22 > n77; ++n77) {
            final int n78 = n77;
            final char c51 = charArray26[n78];
            char c52 = '\0';
            switch (n77 % 5) {
                case 0: {
                    c52 = 'e';
                    break;
                }
                case 1: {
                    c52 = 'x';
                    break;
                }
                case 2: {
                    c52 = 'X';
                    break;
                }
                case 3: {
                    c52 = '~';
                    break;
                }
                default: {
                    c52 = '?';
                    break;
                }
            }
            charArray26[n78] = (char)(c51 ^ c52);
        }
        q[n76] = new String(charArray26).intern();
        final int n79 = 26;
        final char[] charArray27 = "&\u00141\u001dTEZ\u0016\u001bG\u0011X\b\fP\u0007\u0014=\u0013\u001dE\f7^X\nX7\u0010\u0011".toCharArray();
        final int length23 = charArray27.length;
        for (int n80 = 0; length23 > n80; ++n80) {
            final int n81 = n80;
            final char c53 = charArray27[n81];
            char c54 = '\0';
            switch (n80 % 5) {
                case 0: {
                    c54 = 'e';
                    break;
                }
                case 1: {
                    c54 = 'x';
                    break;
                }
                case 2: {
                    c54 = 'X';
                    break;
                }
                case 3: {
                    c54 = '~';
                    break;
                }
                default: {
                    c54 = '?';
                    break;
                }
            }
            charArray27[n81] = (char)(c53 ^ c54);
        }
        q[n79] = new String(charArray27).intern();
        final int n82 = 27;
        final char[] charArray28 = "&\u0017*\fZ\u0006\fy^\u001f".toCharArray();
        final int length24 = charArray28.length;
        for (int n83 = 0; length24 > n83; ++n83) {
            final int n84 = n83;
            final char c55 = charArray28[n84];
            char c56 = '\0';
            switch (n83 % 5) {
                case 0: {
                    c56 = 'e';
                    break;
                }
                case 1: {
                    c56 = 'x';
                    break;
                }
                case 2: {
                    c56 = 'X';
                    break;
                }
                case 3: {
                    c56 = '~';
                    break;
                }
                default: {
                    c56 = '?';
                    break;
                }
            }
            charArray28[n84] = (char)(c55 ^ c56);
        }
        q[n82] = new String(charArray28).intern();
        final int n85 = 28;
        final char[] charArray29 = "\n\u0016x\nW\u0000X4\u001bY\u0011".toCharArray();
        final int length25 = charArray29.length;
        for (int n86 = 0; length25 > n86; ++n86) {
            final int n87 = n86;
            final char c57 = charArray29[n87];
            char c58 = '\0';
            switch (n86 % 5) {
                case 0: {
                    c58 = 'e';
                    break;
                }
                case 1: {
                    c58 = 'x';
                    break;
                }
                case 2: {
                    c58 = 'X';
                    break;
                }
                case 3: {
                    c58 = '~';
                    break;
                }
                default: {
                    c58 = '?';
                    break;
                }
            }
            charArray29[n87] = (char)(c57 ^ c58);
        }
        q[n85] = new String(charArray29).intern();
        final int n88 = 29;
        final char[] charArray30 = "\t\u001d+\r\u001f\u0011\u00109\u0010".toCharArray();
        final int length26 = charArray30.length;
        for (int n89 = 0; length26 > n89; ++n89) {
            final int n90 = n89;
            final char c59 = charArray30[n90];
            char c60 = '\0';
            switch (n89 % 5) {
                case 0: {
                    c60 = 'e';
                    break;
                }
                case 1: {
                    c60 = 'x';
                    break;
                }
                case 2: {
                    c60 = 'X';
                    break;
                }
                case 3: {
                    c60 = '~';
                    break;
                }
                default: {
                    c60 = '?';
                    break;
                }
            }
            charArray30[n90] = (char)(c59 ^ c60);
        }
        q[n88] = new String(charArray30).intern();
        final int n91 = 30;
        final char[] charArray31 = ")\u001d.\u001bSEJ".toCharArray();
        final int length27 = charArray31.length;
        for (int n92 = 0; length27 > n92; ++n92) {
            final int n93 = n92;
            final char c61 = charArray31[n93];
            char c62 = '\0';
            switch (n92 % 5) {
                case 0: {
                    c62 = 'e';
                    break;
                }
                case 1: {
                    c62 = 'x';
                    break;
                }
                case 2: {
                    c62 = 'X';
                    break;
                }
                case 3: {
                    c62 = '~';
                    break;
                }
                default: {
                    c62 = '?';
                    break;
                }
            }
            charArray31[n93] = (char)(c61 ^ c62);
        }
        q[n91] = new String(charArray31).intern();
        final int n94 = 31;
        final char[] charArray32 = "\u0002\n=\u001fK\u0000\nx\nW\u0004\u0016".toCharArray();
        final int length28 = charArray32.length;
        for (int n95 = 0; length28 > n95; ++n95) {
            final int n96 = n95;
            final char c63 = charArray32[n96];
            char c64 = '\0';
            switch (n95 % 5) {
                case 0: {
                    c64 = 'e';
                    break;
                }
                case 1: {
                    c64 = 'x';
                    break;
                }
                case 2: {
                    c64 = 'X';
                    break;
                }
                case 3: {
                    c64 = '~';
                    break;
                }
                default: {
                    c64 = '?';
                    break;
                }
            }
            charArray32[n96] = (char)(c63 ^ c64);
        }
        q[n94] = new String(charArray32).intern();
        final int n97 = 32;
        final char[] charArray33 = ")\u001d.\u001bSEI".toCharArray();
        final int length29 = charArray33.length;
        for (int n98 = 0; length29 > n98; ++n98) {
            final int n99 = n98;
            final char c65 = charArray33[n99];
            char c66 = '\0';
            switch (n98 % 5) {
                case 0: {
                    c66 = 'e';
                    break;
                }
                case 1: {
                    c66 = 'x';
                    break;
                }
                case 2: {
                    c66 = 'X';
                    break;
                }
                case 3: {
                    c66 = '~';
                    break;
                }
                default: {
                    c66 = '?';
                    break;
                }
            }
            charArray33[n99] = (char)(c65 ^ c66);
        }
        q[n97] = new String(charArray33).intern();
        final int n100 = 33;
        final char[] charArray34 = "5\u0014=\u001fL\u0000X+\u001bZ\u0001X,\u0016ZE\n9\u0010[\n\u0015x\u0010J\b\u001a=\f\u001f\u0002\u001d6\u001bM\u0004\f7\f\u001f\n\u0016x\u0012Z\u0013\u001d4^P\u000b\u001dx\u001fQ\u0001X,\u0016Z\u000bX+\tV\u0011\u001b0^K\nX4\u001bI\u0000\u0014xL\u0011".toCharArray();
        final int length30 = charArray34.length;
        for (int n101 = 0; length30 > n101; ++n101) {
            final int n102 = n101;
            final char c67 = charArray34[n102];
            char c68 = '\0';
            switch (n101 % 5) {
                case 0: {
                    c68 = 'e';
                    break;
                }
                case 1: {
                    c68 = 'x';
                    break;
                }
                case 2: {
                    c68 = 'X';
                    break;
                }
                case 3: {
                    c68 = '~';
                    break;
                }
                default: {
                    c68 = '?';
                    break;
                }
            }
            charArray34[n102] = (char)(c67 ^ c68);
        }
        q[n100] = new String(charArray34).intern();
        final int n103 = 34;
        final char[] charArray35 = " \n*\u0011M".toCharArray();
        final int length31 = charArray35.length;
        for (int n104 = 0; length31 > n104; ++n104) {
            final int n105 = n104;
            final char c69 = charArray35[n105];
            char c70 = '\0';
            switch (n104 % 5) {
                case 0: {
                    c70 = 'e';
                    break;
                }
                case 1: {
                    c70 = 'x';
                    break;
                }
                case 2: {
                    c70 = 'X';
                    break;
                }
                case 3: {
                    c70 = '~';
                    break;
                }
                default: {
                    c70 = '?';
                    break;
                }
            }
            charArray35[n105] = (char)(c69 ^ c70);
        }
        q[n103] = new String(charArray35).intern();
        Q = q;
        ComparisonEstimator.O = false;
    }
}
