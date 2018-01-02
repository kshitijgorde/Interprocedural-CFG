import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.event.WindowListener;
import java.applet.Applet;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class l extends Frame implements ActionListener
{
    protected String a;
    protected String b;
    protected String[] c;
    protected int[] d;
    protected int[] e;
    protected int[] f;
    protected int[] g;
    protected int h;
    protected int i;
    protected int j;
    protected int k;
    protected boolean l;
    protected f[] m;
    protected f[] n;
    protected f[] o;
    protected f[] p;
    protected f[] q;
    protected f[] r;
    protected f s;
    protected f t;
    protected f u;
    protected f v;
    protected f w;
    protected f x;
    protected Button y;
    protected Button z;
    protected boolean A;
    protected boolean B;
    public Applet C;
    protected ActionListener D;
    int E;
    int F;
    int G;
    public static final String[] H;
    boolean I;
    protected boolean J;
    private static final String[] K;
    
    public l(final String[] array, final String s, final boolean b, final Applet applet, final ActionListener actionListener) {
        this(null, null, array, s, b, applet, actionListener, 200, 200);
    }
    
    public l(final String a, final String b, final String[] array, final String title, final boolean l, final Applet c, final ActionListener d, final int f, final int g) {
        int c2 = d.c;
        this.a = null;
        this.b = null;
        this.l = true;
        this.A = false;
        this.B = false;
        this.E = 200;
        this.F = 200;
        this.G = 200;
        this.I = false;
        this.J = true;
        this.I = false;
        if (a != null) {
            this.I = true;
        }
        this.a = a;
        this.b = b;
        this.F = f;
        this.G = g;
        this.setTitle(title);
        this.c = new String[array.length];
        int i = 0;
        while (i < array.length) {
            this.c[i] = array[i];
            ++i;
            if (c2 != 0) {
                break;
            }
        }
        this.l = l;
        this.d = new int[array.length];
        this.e = new int[array.length];
        int j = 0;
        while (j < this.e.length) {
            this.e[j] = 0;
            ++j;
            if (c2 != 0) {
                break;
            }
        }
        this.h = 0;
        this.i = 0;
        if (this.I) {
            this.f = new int[array.length];
            this.g = new int[array.length];
            int k = 0;
            while (k < this.g.length) {
                this.g[k] = 0;
                ++k;
                if (c2 != 0) {
                    break;
                }
            }
            this.j = 0;
            this.k = 0;
        }
        this.B = false;
        if (c != null) {
            this.B = true;
            this.C = c;
        }
        if (d != null) {
            this.D = d;
        }
        this.setResizable(true);
        this.E = 200;
        this.c();
        this.addWindowListener(new h(this));
        if (q.a != 0) {
            d.c = ++c2;
        }
    }
    
    public void a(final int n) {
        this.a(n, true);
    }
    
    public void a(final int n, final boolean b) {
        if (!this.J) {
            return;
        }
        if (n >= this.d.length) {
            return;
        }
        final int[] d = this.d;
        ++d[n];
        final int[] e = this.e;
        ++e[n];
        ++this.h;
        ++this.i;
        if (this.I) {
            final int[] f = this.f;
            ++f[n];
            final int[] g = this.g;
            ++g[n];
            ++this.j;
            ++this.k;
        }
        if (this.isShowing() && b) {
            this.b();
        }
    }
    
    public void b(final int n) {
        this.b(n, true);
    }
    
    public void b(final int n, final boolean b) {
        if (!this.J) {
            return;
        }
        if (n >= this.d.length) {
            return;
        }
        final int[] e = this.e;
        ++e[n];
        ++this.i;
        if (this.I) {
            final int[] g = this.g;
            ++g[n];
            ++this.k;
        }
        if (this.isShowing() && b) {
            this.b();
        }
    }
    
    public void a() {
        final int c = d.c;
        int i = 0;
        while (i < this.d.length) {
            this.d[i] = 0;
            this.e[i] = 0;
            if (this.I) {
                this.f[i] = 0;
                this.g[i] = 0;
            }
            ++i;
            if (c != 0) {
                break;
            }
        }
        this.h = 0;
        this.i = 0;
        if (this.I) {
            this.j = 0;
            this.k = 0;
        }
        this.b();
    }
    
    protected void b() {
        final int c = d.c;
        int i = 0;
        while (i < this.c.length) {
            this.n[i].a(this.d[i] + l.K[0] + this.e[i]);
            int n = 0;
            Label_0099: {
                if (this.e[i] != 0) {
                    n = (int)Math.round(this.d[i] / this.e[i] * 100.0);
                    if (c == 0) {
                        break Label_0099;
                    }
                }
                n = 0;
            }
            this.o[i].a(n + "%");
            if (this.I) {
                this.q[i].a(this.f[i] + l.K[0] + this.g[i]);
                int n2 = 0;
                Label_0218: {
                    if (this.g[i] != 0) {
                        n2 = (int)Math.round(this.f[i] / this.g[i] * 100.0);
                        if (c == 0) {
                            break Label_0218;
                        }
                    }
                    n2 = 0;
                }
                this.r[i].a(n2 + "%");
            }
            ++i;
            if (c != 0) {
                break;
            }
        }
        if (this.l) {
            if (this.t != null) {
                this.t.a(this.h + l.K[0] + this.i);
            }
            if (this.u != null) {
                int n3 = 0;
                Label_0346: {
                    if (this.i != 0) {
                        n3 = (int)Math.round(this.h / this.i * 100.0);
                        if (c == 0) {
                            break Label_0346;
                        }
                    }
                    n3 = 0;
                }
                this.u.a(n3 + "%");
            }
            if (this.I) {
                if (this.w != null) {
                    this.w.a(this.j + l.K[0] + this.k);
                }
                if (this.x != null) {
                    int n4 = 0;
                    Label_0465: {
                        if (this.k != 0) {
                            n4 = (int)Math.round(this.j / this.k * 100.0);
                            if (c == 0) {
                                break Label_0465;
                            }
                        }
                        n4 = 0;
                    }
                    this.x.a(n4 + "%");
                }
            }
        }
        this.validate();
        this.pack();
    }
    
    protected void c() {
        this.setLayout(new mb(l.K[3]));
        this.m = new f[this.c.length];
        this.n = new f[this.c.length];
        this.o = new f[this.c.length];
        Label_0081: {
            if (this.I) {
                this.c(this.e());
                if (d.c == 0) {
                    break Label_0081;
                }
            }
            this.d();
        }
        (this.y = new Button(l.K[13])).addActionListener(this);
        final Panel panel = new Panel(new lb(l.K[3]));
        panel.add(l.K[8], this.y);
        (this.z = new Button(l.K[12])).addActionListener(this);
        panel.add(l.K[8], this.z);
        this.add(l.K[8], panel);
        this.validate();
        this.pack();
    }
    
    protected int d() {
        final int c = d.c;
        final boolean b = this.c.length > 1 && this.l;
        final Panel panel = new Panel(new kb(this.c.length, 3));
        int i = 0;
        while (i < this.c.length) {
            this.m[i] = new f(this.c[i], this.E, 30, 0);
            this.n[i] = new f(l.K[2], 101, 30, 1);
            this.o[i] = new f(l.K[1], 51, 30, 1);
            panel.add(l.K[6], this.m[i]);
            panel.add(l.K[8], this.n[i]);
            panel.add(l.K[9], this.o[i]);
            ++i;
            if (c != 0) {
                break;
            }
        }
        this.add(l.K[7], panel);
        if (b) {
            this.add(l.K[7], new c());
            this.s = new f(l.K[11], this.E, 30, 0);
            this.t = new f(l.K[2], 101, 30, 1);
            this.u = new f(l.K[1], 51, 30, 1);
            ++i;
            final Panel panel2 = new Panel(new kb(1, 3));
            panel2.add(l.K[6], this.s);
            panel2.add(l.K[8], this.t);
            panel2.add(l.K[9], this.u);
            this.add(l.K[7], panel2);
            ++i;
        }
        return i;
    }
    
    protected int e() {
        final int c = d.c;
        this.add(l.K[5], new f(this.a, 20 + this.E + 101 + 51, 30, 0));
        this.p = new f[this.c.length];
        this.q = new f[this.c.length];
        this.r = new f[this.c.length];
        final Panel panel = new Panel(new kb(this.c.length, 4));
        int i = 0;
        while (i < this.c.length) {
            this.p[i] = new f(this.c[i], this.E, 30, 0);
            this.q[i] = new f(l.K[2], 101, 30, 1);
            this.r[i] = new f(l.K[1], 51, 30, 1);
            panel.add(l.K[6], new f("", 20, 30, 2));
            panel.add(l.K[6], this.p[i]);
            panel.add(l.K[8], this.q[i]);
            panel.add(l.K[9], this.r[i]);
            ++i;
            if (c != 0) {
                break;
            }
        }
        this.add(l.K[7], panel);
        if (this.c.length > 1 && this.l) {
            this.add(l.K[7], new c());
            ++i;
            this.v = new f(l.K[4], 20 + this.E, 30, 0);
            this.w = new f(l.K[2], 101, 30, 1);
            this.x = new f(l.K[1], 51, 30, 1);
            final Panel panel2 = new Panel(new lb(l.K[3]));
            panel2.add(l.K[6], this.v);
            panel2.add(l.K[8], this.w);
            panel2.add(l.K[9], this.x);
            this.add(l.K[7], panel2);
        }
        return 2 + i;
    }
    
    protected int c(final int n) {
        final int c = d.c;
        this.add(l.K[5], new f(this.b, 20 + this.E + 101 + 51, 30, 0));
        this.m = new f[this.c.length];
        this.n = new f[this.c.length];
        this.o = new f[this.c.length];
        final Panel panel = new Panel(new kb(this.c.length, 4));
        int i = 0;
        while (i < this.c.length) {
            this.m[i] = new f(this.c[i], this.E, 30, 0);
            this.n[i] = new f(l.K[2], 101, 30, 1);
            this.o[i] = new f(l.K[1], 51, 30, 1);
            panel.add(l.K[6], new f("", 20, 30, 2));
            panel.add(l.K[6], this.m[i]);
            panel.add(l.K[8], this.n[i]);
            panel.add(l.K[9], this.o[i]);
            ++i;
            if (c != 0) {
                break;
            }
        }
        this.add(l.K[7], panel);
        if (this.c.length > 1 && this.l) {
            this.add(l.K[7], new c());
            ++i;
            this.s = new f(l.K[4], 20 + this.E, 2, 0);
            this.t = new f(l.K[2], 101, 30, 1);
            this.u = new f(l.K[1], 51, 30, 1);
            final Panel panel2 = new Panel(new lb(l.K[3]));
            panel2.add(l.K[6], this.s);
            panel2.add(l.K[8], this.t);
            panel2.add(l.K[9], this.u);
            this.add(l.K[7], panel2);
        }
        return n + 2 + i;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.y) {
            this.a();
            return;
        }
        if (actionEvent.getSource() == this.z && this.C != null) {
            this.setVisible(false);
        }
    }
    
    public void setVisible(final boolean visible) {
        if (visible && (this.isShowing() || this.isVisible())) {
            this.setVisible(false);
        }
        if (!this.A && this.s != null) {
            final Font font = new Font(l.K[10], 1, 12);
            this.s.a(font);
            this.t.a(font);
            this.u.a(font);
            if (this.I && this.v != null) {
                this.v.a(font);
                this.w.a(font);
                this.x.a(font);
            }
            this.A = true;
        }
        super.setVisible(visible);
        this.validate();
        this.pack();
        this.b();
    }
    
    public boolean f() {
        return this.J;
    }
    
    public void a(final boolean j) {
        this.J = j;
    }
    
    static {
        K = new String[] { z(z("%\\fO\u0000jU3")), z(z("5\u0016")), z(z("5\u0013|NT%\\u\u001b\u0010")), z(z("K\\aOH")), z(z("Q\\gZL")), z(z("IVuO")), z(z("RV`O")), z(z("CZ\u007fW")), z(z("FV}OEw")), z(z("@R`O")), z(z("AZrWOb")), z(z("Q\\gZL%")), z(z("F_|HE")), z(z("WV`^T")) };
        H = new String[] { z(z("F_|HIkT3OHl@3LIkW|L\u0000rZ\u007fW")), z(z("wV`^T%G{^\u0000VP|IEg\\rID+")), z(z("A\\3BOp\u0013dRSm\u0013gT\u0000f\\}OIkFv\u0004")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= ' ';
        }
        return charArray;
    }
    
    private static String z(final char[] array) {
        final int i = array.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = array[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = '\u0005';
                    break;
                }
                case 1: {
                    c2 = '3';
                    break;
                }
                case 2: {
                    c2 = '\u0013';
                    break;
                }
                case 3: {
                    c2 = ';';
                    break;
                }
                default: {
                    c2 = ' ';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
