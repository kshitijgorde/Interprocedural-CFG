import java.awt.Rectangle;
import java.awt.MenuComponent;
import java.awt.Toolkit;
import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.ImageObserver;
import java.awt.event.ActionListener;
import java.awt.MenuItem;
import java.awt.Menu;
import java.awt.PopupMenu;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.util.Vector;
import java.awt.Scrollbar;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class e extends Canvas
{
    private Scrollbar a;
    private Scrollbar b;
    private Vector c;
    private Vector d;
    private Color e;
    private Canvas f;
    private Font g;
    private Font h;
    private Font i;
    private Image j;
    private Graphics k;
    private Dimension l;
    private String m;
    private String n;
    private String[] o;
    private char[] p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
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
    private boolean H;
    private boolean I;
    private boolean J;
    private t K;
    private bu L;
    private bk M;
    private bq N;
    private PopupMenu O;
    private Menu P;
    private Menu Q;
    private int R;
    private int S;
    private MenuItem T;
    private MenuItem U;
    private MenuItem V;
    private MenuItem W;
    private MenuItem X;
    private MenuItem Y;
    private MenuItem Z;
    private MenuItem ba;
    private MenuItem bb;
    private MenuItem bc;
    private boolean bd;
    
    public void a() {
        if (!irc.q || this.O == null) {
            return;
        }
        this.O.add(this.T);
        this.T.addActionListener(this.K);
        this.O.add(this.U);
        this.U.addActionListener(this.K);
        if (irc.bI) {
            this.O.add(this.bb);
            this.bb.addActionListener(this.K);
            this.O.add(this.bc);
            this.bc.addActionListener(this.K);
        }
        this.b();
    }
    
    public void a(String substring) {
        final boolean dx = bm.dX;
        if (substring == null || substring.equals("")) {
            return;
        }
        int index = substring.indexOf(" ");
    Label_0157:
        while (true) {
            while (true) {
                Label_0185: {
                    if (!irc.cA) {
                        break Label_0185;
                    }
                    if (index < 0 && this.d() != 0) {
                        this.K.e.a(l("\u0000Y0@") + this.c(substring) + "\n");
                        if (!dx) {
                            break Label_0185;
                        }
                    }
                    this.K.e.a(l("\u0000Y0@") + this.K.d + "\n");
                    if (dx) {
                        break Label_0138;
                    }
                    break Label_0185;
                    int i = 0;
                    while (i < 0) {
                        final String s;
                        this.e(s);
                        substring = substring.substring(index + 1);
                        i = substring.indexOf(" ");
                        if (!dx) {
                            index = i;
                            break;
                        }
                    }
                    break Label_0185;
                    final String s = new String(substring.substring(0, index));
                    this.i(s);
                    continue Label_0157;
                }
                if (index > 0) {
                    continue;
                }
                break;
            }
            final String s = substring;
            final int j = this.i(s);
            if (!dx) {
                if (j < 0) {
                    this.e(s);
                }
                return;
            }
            continue Label_0157;
        }
    }
    
    public void b() {
        if (!irc.q) {
            return;
        }
        this.O.add(this.V);
        this.V.addActionListener(this.K);
        this.O.add(this.W);
        this.W.addActionListener(this.K);
        this.O.add(this.X);
        this.X.addActionListener(this.K);
        this.O.add(this.Y);
        this.Y.addActionListener(this.K);
        this.O.add(this.Z);
        this.Z.addActionListener(this.K);
        this.O.add(this.ba);
        this.ba.addActionListener(this.K);
        bo.a(this.O, this.P.getFont());
    }
    
    int b(final String s) {
        int n = 0;
        if (irc.N != null) {
            n = irc.N.getHeight(this);
        }
        if (s.charAt(0) == '@' && irc.B != null) {
            n = irc.B.getHeight(this);
        }
        if (s.charAt(0) == '%' && irc.C != null) {
            n = irc.C.getHeight(this);
        }
        if (s.charAt(0) == '+' && irc.D != null) {
            n = irc.D.getHeight(this);
        }
        if (s.charAt(0) == '&' && irc.G != null) {
            n = irc.G.getHeight(this);
        }
        if (s.charAt(0) == '~' && irc.H != null) {
            n = irc.H.getHeight(this);
        }
        final int d = this.d(s);
        if (d >= 0) {
            n = ((Image)irc.M.elementAt(d)).getHeight(this);
        }
        if (n < this.g.getSize()) {
            return this.g.getSize();
        }
        return n;
    }
    
    String c(final String s) {
        final char char1 = s.charAt(0);
        if (char1 == '@' || char1 == '%' || char1 == '+') {
            return s.substring(1);
        }
        if (irc.bJ && (char1 == '!' || char1 == '*')) {
            return s.substring(1);
        }
        return s;
    }
    
    int d(String substring) {
        final boolean dx = bm.dX;
        final int size = irc.L.size();
        if (!Character.isLetter(substring.charAt(0))) {
            substring = substring.substring(1);
        }
        int n = 0;
        while (true) {
            while (true) {
                Label_0058: {
                    if (!dx) {
                        break Label_0058;
                    }
                    ((String)irc.L.elementAt(n)).equalsIgnoreCase(substring);
                    final int n2;
                    if (n2 != 0) {
                        return n;
                    }
                    ++n;
                }
                if (n != size) {
                    continue;
                }
                break;
            }
            final int n2 = -1;
            if (!dx) {
                return n2;
            }
            continue;
        }
    }
    
    public String c() {
        if (this.t == -1) {
            return null;
        }
        return this.a(this.t);
    }
    
    public int d() {
        return this.B;
    }
    
    void a(final Graphics graphics, final int n, final int n2) {
        graphics.setColor(bn.I);
        graphics.drawRect(0, 0, n - 1, n2 - 1);
        graphics.drawLine(1, 1, n - 2, 1);
        graphics.drawLine(1, 1, 1, n2 - 2);
    }
    
    public void a(final Font g) {
        this.g = g;
        this.h = new Font(this.g.getName(), 1, this.g.getSize());
        this.i = new Font(this.g.getName(), 0, this.g.getSize() / 4 * 3);
        this.D = g.getSize();
        this.F = this.getFontMetrics(g).charWidth('N');
        this.a(this.preferredSize());
        this.repaint();
    }
    
    public int e() {
        return this.c.size();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void f() {
        this.resize((irc.bh - 100 < 0) ? 20 : 100, irc.bi);
        this.f = new Canvas();
        this.a = new Scrollbar(1, 0, 1, 0, this.q);
        this.b = new Scrollbar(0, 0, 1, 0, this.r);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        super.setBackground(Color.white);
        final Panel panel = new Panel();
        panel.setLayout(layout);
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 3.0;
        gridBagConstraints.weighty = 3.0;
        layout.setConstraints(this.f, gridBagConstraints);
        panel.add(this.f);
        layout.setConstraints(this.a, gridBagConstraints);
        panel.add(this.a);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        layout.setConstraints(this.b, gridBagConstraints);
        panel.add(this.b);
        this.a(this.getPreferredSize());
        this.h();
    }
    
    private void a(final Dimension l) {
        final boolean dx = bm.dX;
        this.a.setMaximum(this.B);
        if (this.l == null || this.l.width != l.width || this.l.height != l.height) {
            this.l = l;
            this.j = this.createImage(l.width, l.height);
            if (this.j != null) {
                this.k = this.j.getGraphics();
            }
        }
        this.C = this.c.capacity();
        int w = this.w;
        int n = 0;
        final boolean b = false;
        this.q = (b ? 1 : 0);
        this.r = (b ? 1 : 0);
        while (true) {
        Label_0204:
            while (true) {
                Label_0196: {
                    if (!dx) {
                        break Label_0196;
                    }
                    final Object element = this.c.elementAt(w);
                    final String string = element.toString();
                    final int j = this.j(string);
                    if (j > this.r) {
                        this.r = j;
                    }
                    n += this.b(string) + irc.cr;
                    if (n > l.height) {
                        break Label_0204;
                    }
                    ++w;
                    ++this.q;
                }
                if (w < this.B) {
                    continue;
                }
                break;
            }
            final Object element = this;
            if (!dx) {
                if (this.q <= 0) {
                    this.q = 20;
                }
                if (this.r == 0) {
                    this.r = 50;
                }
                this.a.setBlockIncrement(this.q);
                this.b.setBlockIncrement(this.r);
                return;
            }
            continue;
        }
    }
    
    void a(String s, final int n, final int n2) {
        Image image = null;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        final int d = this.d(s);
        int f = this.f(s);
        final int n6 = s.length() + f;
        if (irc.N != null) {
            image = irc.N;
            n3 = irc.N.getWidth(this) + 2;
            n4 = irc.N.getHeight(this);
            f = 1;
        }
        if (s.charAt(0) == '@' && irc.B != null && n == 0 && d < 0) {
            if (!irc.J) {
                s = s.substring(1);
            }
            image = irc.B;
            n3 = irc.B.getWidth(this) + 2;
            n4 = irc.B.getHeight(this);
        }
        if (s.charAt(0) == '%' && irc.C != null && n == 0 && d < 0) {
            if (!irc.J) {
                s = s.substring(1);
            }
            image = irc.C;
            n3 = irc.C.getWidth(this) + 2;
            n4 = irc.C.getHeight(this);
        }
        if (s.charAt(0) == '+' && irc.D != null && n == 0 && d < 0) {
            if (!irc.J) {
                s = s.substring(1);
            }
            image = irc.D;
            n3 = irc.D.getWidth(this) + 2;
            n4 = irc.D.getHeight(this);
        }
        if (irc.bJ) {
            if (s.charAt(0) == '!' && irc.G != null && n == 0 && d < 0) {
                if (!irc.J) {
                    s = s.substring(1);
                }
                image = irc.G;
                n3 = irc.G.getWidth(this) + 2;
                n4 = irc.G.getHeight(this);
            }
            if (s.charAt(0) == '*' && irc.H != null && n == 0 && d < 0) {
                if (!irc.J) {
                    s = s.substring(1);
                }
                image = irc.H;
                n3 = irc.H.getWidth(this) + 2;
                n4 = irc.H.getHeight(this);
            }
        }
        if (d >= 0) {
            if (!Character.isLetter(s.charAt(0)) && !irc.J) {
                s = s.substring(1);
            }
            image = (Image)irc.M.elementAt(d);
            n3 = image.getWidth(this) + 2;
            n4 = image.getHeight(this);
            f = 1;
        }
        if (image != null) {
            this.k.drawImage(image, 5, n2 - n4, this);
        }
        if (n4 > this.g.getSize()) {
            n5 = (n4 - this.g.getSize()) / 2;
        }
        if (f == 0) {
            this.k.drawString((n < s.length()) ? s.substring(n) : " ", 5 + n3, n2 - n5);
            return;
        }
        if (n <= 1) {
            this.k.drawString(s, 5 + n3, n2 - n5);
            return;
        }
        this.k.drawString((n < n6) ? s.substring(n - f) : " ", 5 + n3, n2 - n5);
    }
    
    public void b(final Font font) {
        bo.a(this.O, font);
        if (this.K != null && this.K.U != null) {
            bo.a(this.K.U, font);
        }
        bo.a(this.P, font);
        bo.a(this.Q, font);
    }
    
    public void e(final String s) {
        final boolean dx = bm.dX;
        if (this.i(s) >= 0) {
            return;
        }
        final int size = this.c.size();
        String s2 = s.replace('@', '#').toUpperCase();
        if (irc.bJ) {
            s2 = s2.replace('*', '\"');
        }
        int n = 0;
        while (true) {
            while (true) {
                Label_0208: {
                    if (!dx) {
                        break Label_0208;
                    }
                    final Object o = this.c.elementAt(n);
                    String s3 = o.toString().replace('@', '#');
                    if (irc.bJ) {
                        s3 = s3.replace('*', '\"');
                    }
                    if (s3.toUpperCase().compareTo(s2.toUpperCase()) >= 0) {
                        this.c.insertElementAt(s, n);
                        if (irc.cA) {
                            this.d.insertElementAt("", n);
                        }
                        ++this.B;
                        if (n <= this.t) {
                            ++this.t;
                        }
                        if (n < this.w) {
                            ++this.w;
                        }
                        if (n >= this.w && n <= this.y) {
                            this.repaint();
                        }
                        return;
                    }
                    ++n;
                }
                if (n != size) {
                    continue;
                }
                break;
            }
            Object o;
            final Vector vector = (Vector)(o = this.c);
            if (!dx) {
                vector.insertElementAt(s, n);
                if (irc.cA) {
                    this.d.insertElementAt("", n);
                }
                ++this.B;
                this.a(this.l);
                if (n >= this.w) {
                    this.repaint();
                }
                return;
            }
            continue;
        }
    }
    
    public void a(final String s, final String s2) {
        int n = 0;
        while (true) {
            Label_0053: {
                if (!bm.dX) {
                    break Label_0053;
                }
                if (this.c((String)this.c.elementAt(n)).equalsIgnoreCase(this.c(s))) {
                    this.d.setElementAt(d.a(s2.trim()), n);
                    return;
                }
                ++n;
            }
            if (n == this.c.size()) {
                return;
            }
            continue;
        }
    }
    
    public void a(final Panel panel) {
        this.h();
        panel.add(this.O);
    }
    
    public void a(final boolean h) {
        this.H = h;
    }
    
    public void a(final Color background) {
        this.b.setBackground(background);
    }
    
    public int a(final String s, final int n) {
        final boolean dx = bm.dX;
        int n2 = 0;
        int n3 = 0;
        int n5 = 0;
    Block_4:
        while (true) {
            n3 = n2;
            final int index;
            n2 = (index = s.indexOf(" ", n3 + 1));
        Label_0022:
            while (true) {
                int n4 = n;
                int i = 0;
                while (i >= n4) {
                    i = n2;
                    if (dx) {
                        continue Label_0022;
                    }
                    if (i < 0) {
                        break;
                    }
                    n5 = n2;
                    n4 = n;
                    if (!dx) {
                        break Block_4;
                    }
                }
                break;
            }
        }
        if (n5 >= n || n2 < 0) {
            return n3;
        }
        return n2;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (!irc.cA) {
            return false;
        }
        this.bd = false;
        this.repaint();
        return false;
    }
    
    public String a(final int n) {
        if (n < 0 || n > this.B - 1) {
            return null;
        }
        return this.c.elementAt(n).toString();
    }
    
    public void b(final Color background) {
        this.a.setBackground(background);
    }
    
    public e(final int n, final boolean b, final t k, final bu l, final bk m, final bq n2) {
        this(n, b);
        this.K = k;
        this.L = l;
        this.M = m;
        this.N = n2;
    }
    
    public e(final int n, final boolean b) {
        this.e = Color.black;
        this.g = null;
        this.h = null;
        this.i = null;
        this.l = null;
        this.m = new String();
        this.n = new String();
        this.o = Toolkit.getDefaultToolkit().getFontList();
        this.p = new char[1024];
        this.s = -1;
        this.t = -1;
        this.u = 0;
        this.v = 0;
        this.w = 0;
        this.x = 0;
        this.y = 0;
        this.z = 50;
        this.B = 0;
        this.D = 15;
        this.E = 6;
        this.F = 3;
        this.G = 3;
        this.H = true;
        this.I = false;
        this.J = false;
        this.T = new MenuItem(bm.bm);
        this.U = new MenuItem(bm.bn);
        this.V = new MenuItem(bm.bo);
        this.W = new MenuItem(bm.bp);
        this.X = new MenuItem(bm.bq);
        this.Y = new MenuItem(bm.br);
        this.Z = new MenuItem(bm.dJ);
        this.ba = new MenuItem(bm.dK);
        this.bb = new MenuItem(bm.dT);
        this.bc = new MenuItem(bm.dU);
        this.bd = false;
        this.c = new Vector(n);
        if (irc.cA) {
            this.d = new Vector(n);
        }
        this.q = n;
        this.C = n;
        this.f();
    }
    
    public synchronized void paint(final Graphics graphics) {
        final boolean dx = bm.dX;
        final Dimension preferredSize = this.preferredSize();
        this.a(preferredSize);
        if (this.k == null) {
            return;
        }
        if (this.c.isEmpty()) {
            return;
        }
        this.a.setValues(this.a.getValue(), 1, 0, (this.B < this.q) ? this.B : (this.B - this.q + 1));
        Label_0148: {
            if (preferredSize.width - 5 < this.r) {
                this.b.setValues(this.b.getValue(), 1, 0, this.r / this.g.getSize());
                if (!dx) {
                    break Label_0148;
                }
            }
            this.b.setValues(this.b.getValue(), preferredSize.width, 0, preferredSize.width);
        }
        this.b.setBackground(bn.y);
        this.b.setForeground(bn.z);
        if (this.I) {
            this.I = false;
            graphics.clearRect(0, 0, preferredSize.width, preferredSize.height);
            this.a(graphics, preferredSize.width, preferredSize.height);
            return;
        }
        this.k.setColor(bn.y);
        this.k.clearRect(0, 0, preferredSize.width, preferredSize.height);
        if (this.g != null) {
            this.k.setFont(this.g);
        }
        int w = this.w;
        int n = 2;
        int n3 = 0;
        int height = 0;
    Label_0261:
        while (true) {
            final String string = this.c.elementAt(w).toString();
            final boolean equals = this.L.a().equals(this.c(string));
            this.k.setColor(bn.y);
            this.k.setFont(this.g);
            final int n2 = this.D = this.b(string);
            this.a(this.k, bn.y, 0, n, preferredSize.width, n2 + irc.cr);
            if (w == this.t && this.J) {
                this.a(this.k, bn.K, 0, n, preferredSize.width, n2 + irc.cr);
                this.k.setColor(bn.L);
                this.k.setFont(this.h);
            }
            this.k.setColor(bn.z);
            if (equals) {
                this.k.setColor(bn.J);
            }
            this.N.b(this.c(string));
            do {
                if (n3 == height) {
                    this.k.setColor(bn.M);
                }
                if (this.M.b(this.c(string)) >= 0) {
                    this.k.setColor(bn.N);
                    this.k.drawLine(5, n + n2 / 2, preferredSize.width, n + n2 / 2);
                }
                this.a(string, this.x, n + n2);
                if (irc.cA) {
                    final String g = this.g(string);
                    if (this.bd && this.S >= n && this.S <= n + n2 && g != null) {
                        this.a(this.k, g, this.R, n + n2 - 5, this.getBounds());
                    }
                }
                n += n2 + irc.cr;
                ++w;
                if (n < preferredSize.height && w < this.B) {
                    continue Label_0261;
                }
                this.y = w - 1;
                n3 = n;
                height = preferredSize.height;
            } while (dx);
            break;
        }
        if (n3 < height) {
            this.k.setColor(bn.y);
            this.k.fillRect(0, n, preferredSize.width, preferredSize.height - n);
        }
        this.k.setColor(bn.y);
        this.k.fillRect(1, this.D * (n - 1), preferredSize.width - 2, this.D);
        this.a(this.k, preferredSize.width, preferredSize.height);
        graphics.drawImage(this.j, 0, 0, this);
    }
    
    void b(final int n) {
        this.x = n;
        this.s = n;
        this.repaint();
    }
    
    void c(final int w) {
        this.w = w;
        this.repaint();
    }
    
    public void setVisible(final boolean h) {
        this.H = h;
    }
    
    public boolean g() {
        return this.H;
    }
    
    private void h() {
        this.O = new PopupMenu(l("\u001at\u0011\u0015"));
        this.P = new Menu(bm.y);
        this.Q = new Menu(bm.z);
        final MenuItem menuItem;
        this.O.add(menuItem = new MenuItem(bm.bb));
        menuItem.addActionListener(this.K);
        if (irc.ch != null) {
            final MenuItem menuItem2;
            this.O.add(menuItem2 = new MenuItem(bm.bc));
            menuItem2.addActionListener(this.K);
        }
        if (this.K != null && this.K.U != null) {
            this.O.add(this.K.U);
        }
        if (irc.bF) {
            final MenuItem menuItem3;
            this.O.add(menuItem3 = new MenuItem(bm.ba));
            menuItem3.addActionListener(this.K);
        }
        this.O.add(this.Q);
        final MenuItem menuItem4;
        this.Q.add(menuItem4 = new MenuItem(bm.Y));
        menuItem4.addActionListener(this.K);
        final MenuItem menuItem5;
        this.Q.add(menuItem5 = new MenuItem(bm.Z));
        menuItem5.addActionListener(this.K);
        if (!irc.q) {
            return;
        }
        this.O.add(this.P);
        final MenuItem menuItem6;
        this.P.add(menuItem6 = new MenuItem(bm.bg));
        menuItem6.addActionListener(this.K);
        final MenuItem menuItem7;
        this.P.add(menuItem7 = new MenuItem(bm.bh));
        menuItem7.addActionListener(this.K);
        final MenuItem menuItem8;
        this.P.add(menuItem8 = new MenuItem(bm.bi));
        menuItem8.addActionListener(this.K);
        final MenuItem menuItem9;
        this.P.add(menuItem9 = new MenuItem(bm.bj));
        menuItem9.addActionListener(this.K);
        final MenuItem menuItem10;
        this.P.add(menuItem10 = new MenuItem(bm.bk));
        menuItem10.addActionListener(this.K);
        final MenuItem menuItem11;
        this.P.add(menuItem11 = new MenuItem(bm.bl));
        menuItem11.addActionListener(this.K);
    }
    
    public void i() {
        if (!irc.q) {
            return;
        }
        this.T.removeActionListener(this.K);
        this.O.remove(this.T);
        this.U.removeActionListener(this.K);
        this.O.remove(this.U);
        if (irc.bI) {
            this.bb.removeActionListener(this.K);
            this.O.remove(this.bb);
            this.bc.removeActionListener(this.K);
            this.O.remove(this.bc);
        }
        this.j();
    }
    
    public void j() {
        if (!irc.q) {
            return;
        }
        this.V.removeActionListener(this.K);
        this.O.remove(this.V);
        this.W.removeActionListener(this.K);
        this.O.remove(this.W);
        this.X.removeActionListener(this.K);
        this.O.remove(this.X);
        this.Y.removeActionListener(this.K);
        this.O.remove(this.Y);
        this.Z.removeActionListener(this.K);
        this.O.remove(this.Z);
        this.ba.removeActionListener(this.K);
        this.O.remove(this.ba);
    }
    
    void a(final Graphics graphics, final Color color, final int n, final int n2, final int n3, final int n4) {
        final Color color2 = graphics.getColor();
        graphics.setColor(color);
        graphics.fillRect(n, n2, n3, n4);
        graphics.setColor(color2);
    }
    
    int f(final String s) {
        if ((s.charAt(0) == '@' && irc.B != null) || (s.charAt(0) == '%' && irc.C != null) || (s.charAt(0) == '+' && irc.D != null)) {
            return 1;
        }
        if (irc.bJ && ((s.charAt(0) == '!' && irc.G != null) || (s.charAt(0) == '*' && irc.H != null))) {
            return 1;
        }
        return 0;
    }
    
    public String g(final String s) {
        int n = 0;
        while (true) {
            Label_0049: {
                if (!bm.dX) {
                    break Label_0049;
                }
                if (this.c((String)this.c.elementAt(n)).equalsIgnoreCase(this.c(s))) {
                    return (String)this.d.elementAt(n);
                }
                ++n;
            }
            if (n == this.c.size()) {
                return null;
            }
            continue;
        }
    }
    
    public void h(final String s) {
        final int i = this.i(s);
        if (i == -1) {
            return;
        }
        if (i >= 0) {
            if (irc.cA) {
                this.d.removeElementAt(i);
            }
            this.c.removeElementAt(i);
            if (this.t == i) {
                this.t = -1;
            }
            if (i < this.t) {
                --this.t;
            }
            if (i < this.w) {
                --this.w;
            }
            --this.B;
            if (i >= this.w && i <= this.y) {
                this.repaint();
            }
        }
    }
    
    public int a(final int n, final int n2) {
        int w = this.w;
        int n3 = 0;
        int n4;
        do {
            final int b = this.b(this.c.elementAt(w).toString());
            n4 = n3;
            n3 += b + irc.cr;
        } while (++w < this.B && n3 <= n2);
        if (w == this.B && n4 > n2) {
            return -1;
        }
        return w - 1;
    }
    
    void a(final Graphics graphics, final String s, final int n, int size, final Rectangle rectangle) {
        final boolean dx = bm.dX;
        if (s == null || s.equals("")) {
            return;
        }
        final Color color = graphics.getColor();
        final Font font = graphics.getFont();
        graphics.setColor(bn.G);
        graphics.setFont(this.g);
        int n2 = 0;
        int n3 = 0;
        while (true) {
            while (true) {
                Label_0082: {
                    if (!dx) {
                        break Label_0082;
                    }
                    this.getFontMetrics(this.g).charWidth(s.charAt(n3));
                    final int n4;
                    final int size2;
                    n2 = n4 + size2;
                    ++n3;
                }
                if (n3 != s.length()) {
                    continue;
                }
                break;
            }
            final int n4 = size;
            final int size2 = this.g.getSize();
            if (!dx) {
                if (n4 < size2) {
                    size = this.g.getSize();
                }
                int n5 = 0;
                Label_0165: {
                    if (rectangle.width - n > n2) {
                        n5 = n;
                        if (!dx) {
                            break Label_0165;
                        }
                    }
                    if (rectangle.width > n2) {
                        n5 = rectangle.width - n2;
                        if (!dx) {
                            break Label_0165;
                        }
                    }
                    n5 = 0;
                }
                graphics.fillRect(n5, size - this.g.getSize(), n2 + 8, this.g.getSize() + 8);
                graphics.setColor(bn.H);
                graphics.drawString(s, n5 + 5, size);
                graphics.setFont(font);
                graphics.setColor(color);
                return;
            }
            continue;
        }
    }
    
    public void a(final Scrollbar b, final Scrollbar a) {
        this.b = b;
        this.a = a;
    }
    
    public int i(final String s) {
        return this.c.lastIndexOf(s);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        final int clickCount = event.clickCount;
        final int t = this.t;
        final int a;
        if ((a = this.a(n, n2)) != -1) {
            this.t = a;
            this.J = true;
        }
        if ((event.modifiers & 0x4) != 0x0 || (event.modifiers & 0x2) != 0x0) {
            this.update(this.k);
            this.O.show(this, event.x, event.y);
            this.repaint();
            return true;
        }
        if (clickCount == 1) {
            this.repaint();
        }
        if (clickCount < 2 || t != this.t) {
            return false;
        }
        this.repaint();
        final String a2 = this.a(this.t);
        if (a2.equals("")) {
            return false;
        }
        final String c = this.c(a2);
        if (this.K.q.d() < 0) {
            this.K.i.a(irc.R + bm.cF, bn.e, false);
            if (!bm.dX) {
                return true;
            }
        }
        this.K.q.c(c, false);
        irc.cy.b(c);
        return true;
    }
    
    int j(String s) {
        final boolean dx = bm.dX;
        int n = 0;
        if (irc.N != null) {
            n = irc.N.getWidth(this);
        }
        if (s.charAt(0) == '@') {
            if (irc.B != null) {
                n = irc.B.getWidth(this);
            }
            if (!irc.J) {
                s = s.substring(1);
            }
        }
        if (s.charAt(0) == '%') {
            if (irc.C != null) {
                n = irc.C.getWidth(this);
            }
            if (!irc.J) {
                s = s.substring(1);
            }
        }
        if (s.charAt(0) == '+') {
            if (irc.D != null) {
                n = irc.D.getWidth(this);
            }
            if (!irc.J) {
                s = s.substring(1);
            }
        }
        if (s.charAt(0) == '&') {
            if (irc.G != null) {
                n = irc.G.getWidth(this);
            }
            if (!irc.J) {
                s = s.substring(1);
            }
        }
        if (s.charAt(0) == '~') {
            if (irc.H != null) {
                n = irc.H.getWidth(this);
            }
            if (!irc.J) {
                s = s.substring(1);
            }
        }
        final int d = this.d(s);
        if (d >= 0) {
            n = ((Image)irc.M.elementAt(d)).getWidth(this);
        }
        int n2 = 0;
        int n3 = 0;
        while (true) {
            while (true) {
                Label_0262: {
                    if (!dx) {
                        break Label_0262;
                    }
                    final int n4 = n2 + this.getFontMetrics(this.g).charWidth(s.charAt(n3));
                    final int n5;
                    n2 = n5;
                    ++n3;
                }
                if (n3 != s.length()) {
                    continue;
                }
                break;
            }
            final int n5 = n + n2;
            if (!dx) {
                return n5;
            }
            continue;
        }
    }
    
    public void k() {
        this.a.setValue(0);
        this.b.setValue(0);
        this.I = true;
        this.repaint();
    }
    
    public void c(final Color foreground) {
        this.b.setForeground(foreground);
    }
    
    public int k(final String s) {
        final boolean dx = bm.dX;
        if (s == null) {
            return -1;
        }
        int n = 0;
        while (true) {
            while (true) {
                Label_0047: {
                    if (!dx) {
                        break Label_0047;
                    }
                    this.c.elementAt(n).toString().substring(0, 1).toUpperCase().compareTo(s);
                    final int n2;
                    if (n2 == 0) {
                        return n;
                    }
                    ++n;
                }
                if (n < this.B) {
                    continue;
                }
                break;
            }
            final int n2 = -1;
            if (!dx) {
                return n2;
            }
            continue;
        }
    }
    
    public void d(final Color foreground) {
        this.a.setForeground(foreground);
    }
    
    public boolean mouseMove(final Event event, final int r, final int s) {
        if (!irc.cA) {
            return false;
        }
        this.R = r;
        this.S = s;
        this.bd = true;
        this.repaint();
        return false;
    }
    
    private static String l(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = 'W';
                    break;
                }
                case 1: {
                    c2 = '\u0011';
                    break;
                }
                case 2: {
                    c2 = '\u007f';
                    break;
                }
                case 3: {
                    c2 = '`';
                    break;
                }
                default: {
                    c2 = 'O';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
