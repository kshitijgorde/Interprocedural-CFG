import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.Cursor;
import java.net.URL;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.util.StringTokenizer;
import java.awt.event.KeyListener;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.util.Vector;
import java.awt.event.ComponentListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class e extends Canvas implements MouseListener, MouseMotionListener, ComponentListener
{
    private final esChat a;
    public int b;
    public int c;
    public boolean d;
    public String e;
    protected int f;
    protected int g;
    protected int h;
    protected int i;
    protected int j;
    protected kb k;
    protected Vector l;
    protected Vector m;
    protected Vector n;
    protected int o;
    protected int p;
    protected int q;
    protected Color r;
    protected boolean s;
    protected boolean t;
    protected final Color[] u;
    protected int v;
    protected int w;
    protected int x;
    protected int y;
    protected int z;
    protected int A;
    protected int B;
    protected int C;
    protected Font D;
    fb E;
    boolean F;
    Image G;
    String H;
    boolean I;
    b J;
    int K;
    boolean L;
    String M;
    StringBuffer N;
    boolean O;
    private static String[] P;
    
    public e(final esChat a, final int j, final boolean d) {
        this.a = a;
        this.F = false;
        this.I = false;
        this.E = null;
        this.J = null;
        this.f = 2;
        this.g = 2;
        this.i = 0;
        this.b = 0;
        a.getClass();
        this.k = new kb(a);
        this.l = new Vector();
        this.m = new Vector();
        this.n = new Vector();
        this.p = -1;
        this.r = a.f;
        this.s = false;
        this.t = true;
        this.u = new Color[] { Color.white, Color.black, new Color(0, 0, 127), new Color(0, 143, 0), new Color(255, 0, 0), new Color(127, 0, 0), new Color(158, 0, 158), new Color(255, 127, 0), new Color(255, 255, 0), new Color(0, 255, 0), new Color(0, 143, 143), new Color(0, 255, 255), new Color(0, 0, 255), new Color(255, 0, 255), new Color(127, 127, 127), new Color(206, 206, 206) };
        this.K = 0;
        this.v = -1;
        this.w = -1;
        this.x = -1;
        this.y = -1;
        this.z = -1;
        this.A = -1;
        this.B = -1;
        this.C = -1;
        this.L = false;
        this.N = new StringBuffer();
        this.O = true;
        a.Wb = a.z;
        this.j = j;
        this.d = d;
        this.M = a.r;
        this.setBackground(a.h);
        this.setForeground(a.f);
        e e = this;
        if (!d.r) {
            if (this.d) {
                this.setBackground(a.h);
                this.setForeground(a.f);
            }
            this.setFont(new Font(a.eb, a.d, a.hb));
            e = this;
        }
        e.getFontMetrics(this.D);
        this.h = 0;
        this.addKeyListener(new h(this));
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addComponentListener(this);
    }
    
    public int a(final String s, final String s2) {
        final boolean r = d.r;
        final int length = s.length();
        final int length2 = s2.length();
        final char c = (char)length;
        char c2;
        if (!r && c < length2) {
            c2 = (char)length;
            if (r) {
                goto Label_0035;
            }
        }
        else {
            c2 = c;
        }
        char c3 = '\0';
        while (true) {
            while (true) {
                Label_0102: {
                    if (!r) {
                        break Label_0102;
                    }
                    this.a(s.charAt(c3));
                    final char c5;
                    final char c4 = c5;
                    final char a = this.a(s2.charAt(c3));
                    final char c6 = c4;
                    final char c7 = a;
                    if (!r) {
                        if (c6 < c7) {
                            return 1;
                        }
                        final char c8;
                        final int n = c8 = c4;
                        if (r) {
                            return n;
                        }
                    }
                    if (c6 <= c7) {
                        ++c3;
                        break Label_0102;
                    }
                    return -1;
                }
                if (c3 < c2) {
                    continue;
                }
                break;
            }
            char c9;
            final char c5 = c9 = (char)length;
            if (!r) {
                final char c10 = (char)length2;
                if (!r) {
                    if (c5 < c10) {
                        return 1;
                    }
                    final int n2 = c9 = (char)length;
                    if (r) {
                        return n2;
                    }
                }
                int n2;
                if (c9 > c10) {
                    n2 = -1;
                }
                else {
                    n2 = 0;
                }
                return n2;
            }
            continue;
        }
    }
    
    public void a(final String s) {
        final boolean r = d.r;
        int n = 0;
        lb lb = this.k.b;
        e e = null;
        Label_0092: {
            while (true) {
                Label_0028: {
                    if (!r) {
                        break Label_0028;
                    }
                    lb = lb.c;
                    ++n;
                }
                if (lb != null) {
                    e = this;
                    if (r || r) {
                        break Label_0092;
                    }
                    if (this.a(s, ((mb)lb.b).b) < 0) {
                        continue;
                    }
                }
                break;
            }
            this.k.a(new mb(this, s), n);
            ++this.b;
            this.f();
            e = this;
        }
        e.repaint();
    }
    
    public void b(final String s) {
        final boolean r = d.r;
        int n = 0;
        lb lb = this.k.b;
        e e = null;
        e e2 = null;
        Label_0078: {
            while (true) {
                Label_0028: {
                    if (!r) {
                        break Label_0028;
                    }
                    lb = lb.c;
                    ++n;
                }
                if (lb != null) {
                    e = this;
                    e2 = this;
                    if (r || r) {
                        break Label_0078;
                    }
                    if (this.a(s, ((mb)lb.b).b) < 0) {
                        continue;
                    }
                }
                break;
            }
            this.k.a(new mb(this, s), n);
            e = this;
            e2 = this;
        }
        e.b = e2.b + 1;
    }
    
    public void c(final String s) {
        final boolean r = d.r;
        int n = 0;
        lb lb = this.k.b;
        while (true) {
        Label_0083:
            while (true) {
                Label_0079: {
                    if (!r) {
                        break Label_0079;
                    }
                    final e e = this;
                    final int int1 = Integer.parseInt(e.a.e(s, " "));
                    final int int2 = Integer.parseInt(this.a.e(((mb)lb.b).b, " "));
                    if (r) {
                        return;
                    }
                    if (int1 > int2) {
                        break Label_0083;
                    }
                    lb = lb.c;
                    ++n;
                }
                if (lb != null) {
                    continue;
                }
                break;
            }
            this.k.a(new mb(this, s), n);
            ++this.b;
            this.f();
            final e e = this;
            if (r) {
                continue;
            }
            break;
        }
        this.repaint();
    }
    
    public void d(final String s) {
        final boolean r = d.r;
        final int b = this.b;
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n");
        final int b2 = this.b + stringTokenizer.countTokens();
        int n = b;
        while (true) {
            Label_0072: {
                if (!r) {
                    break Label_0072;
                }
                final mb mb = new mb(this, stringTokenizer.nextToken());
                this.a(mb);
                this.k.a(mb);
                ++n;
            }
            if (n >= b2) {
                this.b = b2;
                this.repaint();
                return;
            }
            continue;
        }
    }
    
    public void e(final String s) {
        final boolean r = d.r;
        lb b = this.k.b;
        while (true) {
            Label_0070: {
                if (!r) {
                    break Label_0070;
                }
                final lb lb = b;
                if (!r) {
                    if (((mb)lb.b).b.equalsIgnoreCase(s)) {
                        this.k.a(b);
                        --this.b;
                        this.f();
                        this.repaint();
                        return;
                    }
                    final lb c = b.c;
                }
                b = lb;
            }
            if (b == null) {
                return;
            }
            continue;
        }
    }
    
    protected void a(final int n, final int n2, final int n3, final int n4) {
        final boolean r = d.r;
        final FontMetrics fontMetrics = this.getFontMetrics(this.D);
        this.p = -1;
        this.q = -1;
        int q = 0;
        final Dimension size = this.getSize();
        lb lb = this.k.b;
        while (true) {
            while (true) {
                Label_1087: {
                    if (!r) {
                        break Label_1087;
                    }
                    final Object b = lb.b;
                    final String b2 = ((mb)b).b;
                    final int n5 = ((mb)lb.b).d + size.height - this.c + this.o - this.K - this.c;
                    int c = n2;
                    Label_1080: {
                        if (!r) {
                            if (n2 > n5) {
                                q += b2.length();
                                if (!r) {
                                    break Label_1080;
                                }
                            }
                            c = ((mb)lb.b).c;
                        }
                        int n6 = c;
                        final int n7 = n6 + size.height - this.c + this.o - this.K - this.c;
                        int n8 = n4;
                        if (!r) {
                            if (n7 > n4) {
                                return;
                            }
                            final int f = this.f;
                            n8 = 2;
                        }
                        int n9 = n7 + n8;
                        int n10 = 0;
                        int n22 = 0;
                        int n23 = 0;
                    Label_1073:
                        while (true) {
                            int n42 = 0;
                            int length = 0;
                            Label_1070: {
                                Label_1063: {
                                    if (!r) {
                                        break Label_1063;
                                    }
                                    char c2 = b2.charAt(n10);
                                    this.h = fontMetrics.charWidth(c2);
                                    char c4;
                                    final char c3;
                                    int n11 = c3 = (c4 = c2);
                                    int n14;
                                    final int n13;
                                    int n12 = n13 = (n14 = 1);
                                    int n15 = 0;
                                    int n16 = 0;
                                    do {
                                        final char c8;
                                        final int n20;
                                        Label_0310: {
                                            Label_0302: {
                                                Label_0291: {
                                                    if (!r) {
                                                        if (n15 != n16) {
                                                            final char c5 = (char)(n11 = (c4 = c2));
                                                            final int n17 = n12 = (n14 = 2);
                                                            if (r) {
                                                                break Label_0291;
                                                            }
                                                            if (c5 != n17) {
                                                                final char c6 = (char)(n11 = (c4 = c2));
                                                                final int n18 = n12 = (n14 = 31);
                                                                if (r) {
                                                                    break Label_0291;
                                                                }
                                                                if (c6 != n18) {
                                                                    final char c7 = (char)(n11 = (c4 = c2));
                                                                    final int n19 = n12 = (n14 = 22);
                                                                    if (r) {
                                                                        break Label_0291;
                                                                    }
                                                                    if (c7 != n19) {
                                                                        final char c9;
                                                                        c8 = (c9 = c2);
                                                                        final int n21;
                                                                        n20 = (n21 = 15);
                                                                        if (r) {
                                                                            break Label_0310;
                                                                        }
                                                                        if (c8 != n20) {
                                                                            break Label_0302;
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        n11 = n10;
                                                        n12 = b2.length() - 1;
                                                    }
                                                }
                                                if (r) {
                                                    break Label_1073;
                                                }
                                                if (n11 >= n12) {
                                                    break Label_1073;
                                                }
                                                ++n10;
                                            }
                                            b2.charAt(n10);
                                        }
                                        final int n33;
                                        final int n34;
                                        Label_0640: {
                                            if (!r) {
                                                Label_0617: {
                                                    if (c8 == n20) {
                                                        n22 = n10;
                                                        n23 = b2.length() - 1;
                                                        if (r) {
                                                            break Label_1073;
                                                        }
                                                        if (n22 >= n23) {
                                                            break Label_1073;
                                                        }
                                                        ++n10;
                                                        final char c10 = (char)(b2.charAt(n10) - '0');
                                                        char c9;
                                                        final char c11 = c9 = c10;
                                                        int n21;
                                                        final int n24 = n21 = -1;
                                                        Label_0464: {
                                                            if (!r) {
                                                                Label_0455: {
                                                                    if (c11 > n24) {
                                                                        final char c12 = c9 = c10;
                                                                        final int n26;
                                                                        final int n25 = n26 = (n21 = 10);
                                                                        if (r) {
                                                                            break Label_0464;
                                                                        }
                                                                        if (c12 < n25) {
                                                                            final int n27 = b2.length() - n10;
                                                                            final int n28 = 2;
                                                                            if (r) {
                                                                                break Label_1073;
                                                                            }
                                                                            if (n27 < n28) {
                                                                                break Label_1073;
                                                                            }
                                                                            final char c13 = (char)(b2.charAt(n10 + 1) - '0');
                                                                            int n29;
                                                                            final char c14 = (char)(n29 = (c4 = c13));
                                                                            int n31;
                                                                            final int n30 = n31 = (n14 = -1);
                                                                            Label_0452: {
                                                                                if (!r) {
                                                                                    if (c14 <= n30) {
                                                                                        break Label_0452;
                                                                                    }
                                                                                    final char c15;
                                                                                    n29 = (c15 = (c4 = c13));
                                                                                    final int n32;
                                                                                    n31 = (n32 = (n14 = 10));
                                                                                }
                                                                                if (!r) {
                                                                                    if (c14 >= n30) {
                                                                                        break Label_0452;
                                                                                    }
                                                                                    n29 = b2.length() - n10;
                                                                                    n31 = 2;
                                                                                }
                                                                                if (r) {
                                                                                    break Label_1073;
                                                                                }
                                                                                if (n29 <= n31) {
                                                                                    break Label_1073;
                                                                                }
                                                                                n10 += 2;
                                                                                if (!r) {
                                                                                    break Label_0455;
                                                                                }
                                                                            }
                                                                            ++n10;
                                                                        }
                                                                    }
                                                                }
                                                                b2.charAt(n10);
                                                            }
                                                        }
                                                        if (r) {
                                                            break Label_0640;
                                                        }
                                                        if (c11 == n24) {
                                                            n33 = n10;
                                                            n34 = b2.length() - 1;
                                                            if (r) {
                                                                break Label_0640;
                                                            }
                                                            if (n33 < n34) {
                                                                ++n10;
                                                                final char c16 = (char)(b2.charAt(n10) - '0');
                                                                int n35;
                                                                final char c17 = (char)(n35 = (c4 = c16));
                                                                int n37;
                                                                final int n36 = n37 = (n14 = -1);
                                                                Label_0614: {
                                                                    if (!r) {
                                                                        if (c17 <= n36) {
                                                                            break Label_0614;
                                                                        }
                                                                        final char c18;
                                                                        n35 = (c18 = (c4 = c16));
                                                                        final int n38;
                                                                        n37 = (n38 = (n14 = 10));
                                                                    }
                                                                    if (!r) {
                                                                        if (c17 >= n36) {
                                                                            break Label_0614;
                                                                        }
                                                                        n35 = b2.length() - n10;
                                                                        n37 = 2;
                                                                    }
                                                                    if (r) {
                                                                        break Label_1073;
                                                                    }
                                                                    if (n35 < n37) {
                                                                        break Label_1073;
                                                                    }
                                                                    final char c19 = (char)(b2.charAt(n10 + 1) - '0');
                                                                    if (!r) {
                                                                        Label_0606: {
                                                                            if (c19 > -1) {
                                                                                int n39;
                                                                                final char c20 = (char)(n39 = (c4 = c19));
                                                                                int n41;
                                                                                final int n40 = n41 = (n14 = 10);
                                                                                if (!r) {
                                                                                    if (c20 >= n40) {
                                                                                        break Label_0606;
                                                                                    }
                                                                                    n39 = b2.length() - n10;
                                                                                    n41 = 2;
                                                                                }
                                                                                if (r) {
                                                                                    break Label_1073;
                                                                                }
                                                                                if (n39 <= n41) {
                                                                                    break Label_1073;
                                                                                }
                                                                                n10 += 2;
                                                                                if (!r) {
                                                                                    break Label_0617;
                                                                                }
                                                                            }
                                                                        }
                                                                        ++n10;
                                                                    }
                                                                    if (!r) {
                                                                        break Label_0617;
                                                                    }
                                                                }
                                                                --n10;
                                                            }
                                                        }
                                                    }
                                                }
                                                c2 = b2.charAt(n10);
                                                this.h = fontMetrics.charWidth(c2);
                                            }
                                        }
                                        if (n33 == n34) {
                                            break Label_1063;
                                        }
                                        n15 = (n11 = (n42 = (c4 = c2)));
                                        n16 = (n12 = (length = (n14 = 3)));
                                    } while (r);
                                    if (r) {
                                        break Label_1070;
                                    }
                                    if (n15 != n16) {
                                        final char c21 = (char)(n42 = c2);
                                        final int n43 = length = 15;
                                        if (r) {
                                            break Label_1070;
                                        }
                                        if (c21 != n43) {
                                            final char c22 = (char)(n42 = c2);
                                            final int n44 = length = 2;
                                            if (r) {
                                                break Label_1070;
                                            }
                                            if (c22 != n44) {
                                                final char c23 = (char)(n42 = c2);
                                                final int n45 = length = 31;
                                                if (r) {
                                                    break Label_1070;
                                                }
                                                if (c23 != n45) {
                                                    final char c24 = (char)(n42 = c2);
                                                    final int n46 = length = 22;
                                                    if (r) {
                                                        break Label_1070;
                                                    }
                                                    if (c24 != n46) {
                                                        final int n47 = n9 + this.h + 1;
                                                        final int width = size.width;
                                                        if (!r) {
                                                            if (n47 >= width) {
                                                                final int n49;
                                                                final int n48 = n49 = n6 + size.height - this.c + this.o - this.K - this.c;
                                                                final int p4;
                                                                Label_0821: {
                                                                    if (!r) {
                                                                        if (n2 > n49) {
                                                                            final int n50 = n48 + this.c;
                                                                            if (r) {
                                                                                break Label_0821;
                                                                            }
                                                                            if (n2 < n50) {
                                                                                p4 = this.p;
                                                                                final int n51 = -1;
                                                                                if (r) {
                                                                                    break Label_0821;
                                                                                }
                                                                                if (p4 == n51) {
                                                                                    this.p = q + n10;
                                                                                }
                                                                            }
                                                                        }
                                                                        n9 = this.f + 2;
                                                                        final int c25 = this.c;
                                                                    }
                                                                }
                                                                n6 = p4 + n49;
                                                            }
                                                            final int n52 = n6 + size.height - this.c + this.o - this.K;
                                                            final int c26 = this.c;
                                                        }
                                                        final int n53 = n47 - width;
                                                        int p5;
                                                        final int n55;
                                                        final int n54 = n55 = (p5 = n53);
                                                        int n56 = n4;
                                                        if (!r) {
                                                            if (n54 > n4) {
                                                                return;
                                                            }
                                                            final int h;
                                                            n56 = (h = n53);
                                                        }
                                                        final int n57;
                                                        final int n58;
                                                        final int n59;
                                                        Label_0962: {
                                                            if (!r) {
                                                                Label_0958: {
                                                                    if (n54 > n56) {
                                                                        int h;
                                                                        n57 = (h = n53 + this.c);
                                                                        if (!r) {
                                                                            if (n2 < n57) {
                                                                                n58 = (p5 = this.p);
                                                                                n59 = -1;
                                                                                if (!r) {
                                                                                    if (n58 == n59) {
                                                                                        int n60 = n;
                                                                                        final int n62;
                                                                                        final int n61 = n62 = (h = this.f + 2);
                                                                                        Label_0949: {
                                                                                            if (!r) {
                                                                                                if (n <= n61) {
                                                                                                    break Label_0949;
                                                                                                }
                                                                                                n60 = n;
                                                                                            }
                                                                                            if (r) {
                                                                                                break Label_0962;
                                                                                            }
                                                                                            if (n60 < n61) {
                                                                                                break Label_0958;
                                                                                            }
                                                                                            final int n63 = n9 + this.h;
                                                                                            if (r) {
                                                                                                break Label_0962;
                                                                                            }
                                                                                            if (n > n63) {
                                                                                                break Label_0958;
                                                                                            }
                                                                                        }
                                                                                        this.p = q + n10;
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        final int p6;
                                                        Label_1057: {
                                                            if (!r) {
                                                                Label_1051: {
                                                                    if (n58 > n57) {
                                                                        final int n64 = n53 + this.c;
                                                                        if (r) {
                                                                            break Label_1057;
                                                                        }
                                                                        if (n4 < n64) {
                                                                            p6 = this.p;
                                                                            final int n65 = -1;
                                                                            if (r) {
                                                                                break Label_1057;
                                                                            }
                                                                            if (p6 != n65) {
                                                                                int n66 = n3;
                                                                                final int h;
                                                                                final int n67 = h = this.f + 2;
                                                                                Label_1042: {
                                                                                    if (!r) {
                                                                                        if (n3 <= n67) {
                                                                                            break Label_1042;
                                                                                        }
                                                                                        n66 = n3;
                                                                                    }
                                                                                    if (r) {
                                                                                        break Label_1057;
                                                                                    }
                                                                                    if (n66 < n67) {
                                                                                        break Label_1051;
                                                                                    }
                                                                                    final int n68 = n9 + this.h;
                                                                                    if (r) {
                                                                                        break Label_1057;
                                                                                    }
                                                                                    if (n3 > n68) {
                                                                                        break Label_1051;
                                                                                    }
                                                                                }
                                                                                this.q = q + n10;
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                                int h = this.h;
                                                            }
                                                        }
                                                        n9 = p6 + n59;
                                                        ++n10;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                n42 = n10;
                                length = b2.length();
                            }
                            if (n42 < length) {
                                continue;
                            }
                            break;
                        }
                        q = n22 + n23;
                    }
                    lb = lb.c;
                }
                if (lb != null) {
                    continue;
                }
                break;
            }
            final Object b = this;
            if (!r) {
                final int p7 = this.p;
                final int n69 = -1;
                e e = null;
                Label_1124: {
                    if (!r) {
                        if (p7 == n69) {
                            return;
                        }
                        e = this;
                        if (r) {
                            break Label_1124;
                        }
                        final int q2 = this.q;
                    }
                    if (p7 != n69) {
                        return;
                    }
                    e = this;
                }
                e.q = q;
                return;
            }
            continue;
        }
    }
    
    public char a(final char c) {
        final boolean r = d.r;
        final char lowerCase = Character.toLowerCase(c);
        char c8;
        char c7;
        char c6;
        char c5;
        char c4;
        char c3;
        final char c2 = c3 = (c4 = (c5 = (c6 = (c7 = (c8 = lowerCase)))));
        int n5;
        int n4;
        int n3;
        int n2;
        int n;
        char c10;
        final char c9 = c10 = (char)(n = (n2 = (n3 = (n4 = (n5 = 3)))));
        if (!r) {
            if (c2 == c9) {
                return '\0';
            }
            final char c11;
            c3 = (c11 = (c4 = (c5 = (c6 = (c7 = (c8 = lowerCase))))));
            final char c12;
            c10 = (c12 = (char)(n = (n2 = (n3 = (n4 = (n5 = 64))))));
        }
        if (!r) {
            if (c2 == c9) {
                return '\u0001';
            }
            c4 = (c3 = (c5 = (c6 = (c7 = (c8 = lowerCase)))));
            n = (c10 = (char)(n2 = (n3 = (n4 = (n5 = 37)))));
        }
        if (!r) {
            if (c3 == c10) {
                return '\u0002';
            }
            c5 = (c4 = (c6 = (c7 = (c8 = lowerCase))));
            n2 = (n = (n3 = (n4 = (n5 = 43))));
        }
        if (!r) {
            if (c4 == n) {
                return '\u0003';
            }
            c6 = (c5 = (c7 = (c8 = lowerCase)));
            n3 = (n2 = (n4 = (n5 = 44)));
        }
        if (!r) {
            if (c5 == n2) {
                return '\u0004';
            }
            c6 = (c8 = lowerCase);
            n3 = (n5 = 97);
        }
        final char c13;
        final int n6;
        if (!r) {
            if (c6 >= n3) {
                c13 = (c8 = lowerCase);
                n6 = (n5 = 122);
                if (!r) {
                    if (c13 <= n6) {
                        return (char)(lowerCase + ' ');
                    }
                }
            }
        }
        final char c14;
        final char c15;
        if (!r) {
            if (c13 >= n6) {
                c14 = lowerCase;
                c15 = '9';
                if (!r) {
                    if (c14 <= c15) {
                        return (char)(lowerCase + '\u00c8');
                    }
                }
            }
        }
        return (char)(c14 + c15);
    }
    
    public void a() {
        this.f();
        this.repaint();
    }
    
    public void b() {
        this.k.a();
        this.b = 0;
        this.f();
        this.repaint();
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        Runtime.getRuntime().gc();
        this.f();
        this.a.l = true;
        this.a.m = true;
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    protected void a(final Graphics graphics) {
        final boolean r = d.r;
        final Dimension size = this.getSize();
        int n2;
        final int n = n2 = (this.d ? 1 : 0);
        if (!r) {
            if (n != 0) {
                return;
            }
            n2 = size.height;
        }
        final int n3 = n2;
        int n4 = 0;
        while (true) {
            Label_0168: {
                if (!r) {
                    break Label_0168;
                }
                final mb mb = this.n.elementAt(n4);
                final int n5 = mb.d + n3 - this.c;
                final int n6 = mb.c - 4;
                final int n7 = n5 - this.K;
                Label_0165: {
                    if (!r) {
                        if (n7 < 0) {
                            break Label_0165;
                        }
                        final int n8 = n5 - this.K;
                    }
                    if (!r) {
                        if (n7 < size.height) {
                            graphics.drawImage(this.a.Wb, n6, n5 - this.K, n6 + 23, n5 - this.K + 17, mb.e * 23, 0, 23 + mb.e * 23, 17, this);
                        }
                    }
                }
                ++n4;
            }
            if (n4 < this.n.size()) {
                continue;
            }
            break;
        }
    }
    
    public void a(final int k) {
        this.K = k;
        this.repaint();
    }
    
    public String c() {
        final boolean r = d.r;
        final StringBuffer sb = new StringBuffer("");
        lb lb = this.k.b;
        while (true) {
            while (true) {
                Label_0053: {
                    if (!r) {
                        break Label_0053;
                    }
                    sb.append(((mb)lb.b).b);
                    sb.append("\n");
                    lb = lb.c;
                }
                if (lb != null) {
                    continue;
                }
                break;
            }
            final StringBuffer sb2 = sb;
            if (!r) {
                return sb2.toString();
            }
            continue;
        }
    }
    
    public Font getFont() {
        return this.D;
    }
    
    public int getHeight() {
        return this.getSize().height;
    }
    
    public Vector d() {
        final boolean r = d.r;
        final Vector<String> vector = new Vector<String>();
        lb lb = this.k.b;
        while (true) {
            while (true) {
                Label_0043: {
                    if (!r) {
                        break Label_0043;
                    }
                    final Vector<String> vector2 = vector;
                    vector2.addElement(((mb)lb.b).b);
                    lb = lb.c;
                }
                if (lb != null) {
                    continue;
                }
                break;
            }
            Vector<String> vector2;
            final Vector<String> vector3 = vector2 = vector;
            if (!r) {
                return vector3;
            }
            continue;
        }
    }
    
    public Dimension getMinimumSize() {
        e e = this;
        if (!d.r) {
            if (this.j == 0) {
                return super.getMinimumSize();
            }
            e = this;
        }
        final FontMetrics fontMetrics = e.getFontMetrics(new Font(this.a.eb, this.a.d, this.a.hb));
        return new Dimension(fontMetrics.charWidth('a') * 5, fontMetrics.getHeight());
    }
    
    public Dimension getPreferredSize() {
        e e = this;
        if (!d.r) {
            if (this.j == 0) {
                return super.getPreferredSize();
            }
            e = this;
        }
        final FontMetrics fontMetrics = e.getFontMetrics(new Font(this.a.eb, this.a.d, this.a.hb));
        return new Dimension(fontMetrics.charWidth('a') * this.j, fontMetrics.getHeight());
    }
    
    public String e() {
        final boolean r = d.r;
        lb b = this.k.b;
        while (true) {
            Label_0049: {
                if (!r) {
                    break Label_0049;
                }
                final lb lb = b;
                if (!r) {
                    if (((mb)lb.b).g) {
                        return ((mb)b.b).b;
                    }
                    final lb c = b.c;
                }
                b = lb;
            }
            if (b == null) {
                return null;
            }
            continue;
        }
    }
    
    public int getWidth() {
        return this.getSize().width;
    }
    
    public boolean f(final String s) {
        final boolean r = d.r;
        lb b = this.k.b;
        while (true) {
            while (true) {
                Label_0044: {
                    if (!r) {
                        break Label_0044;
                    }
                    final lb lb = b;
                    Label_0043: {
                        if (r) {
                            break Label_0043;
                        }
                        ((mb)lb.b).b.equalsIgnoreCase(s);
                        final boolean b2;
                        if (b2) {
                            return true;
                        }
                        final lb c = b.c;
                    }
                    b = lb;
                }
                if (b != null) {
                    continue;
                }
                break;
            }
            final boolean b2 = false;
            if (!r) {
                return b2;
            }
            continue;
        }
    }
    
    public void g(final String s) {
        this.G = this.a.b(String.valueOf(this.a.M) + "/" + s);
        this.F = true;
    }
    
    public void f() {
        final boolean r = d.r;
        final FontMetrics fontMetrics = this.getFontMetrics(this.D);
        this.c = fontMetrics.getHeight();
        this.o = fontMetrics.getAscent();
        this.i = 0;
        this.l.setSize(0);
        this.m.setSize(0);
        this.n.setSize(0);
        lb lb = this.k.b;
        while (true) {
            Label_0086: {
                if (!r) {
                    break Label_0086;
                }
                this.a((mb)lb.b);
                lb = lb.c;
            }
            if (lb == null) {
                return;
            }
            continue;
        }
    }
    
    protected void a(final mb mb) {
        final boolean r = d.r;
        final FontMetrics fontMetrics = this.getFontMetrics(this.D);
        final StringBuffer sb = new StringBuffer();
        final StringBuffer sb2 = new StringBuffer();
        final StringBuffer sb3 = new StringBuffer();
        this.c = fontMetrics.getHeight();
        this.o = fontMetrics.getAscent();
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        final Dimension size = this.getSize();
        final String b = mb.b;
        mb.c = this.i;
        int n7 = this.i + this.c;
        int n8 = this.f + 2;
        int n9 = 0;
        int length3 = 0;
        int n29 = 0;
        int n33 = 0;
        Label_1861: {
        Label_1857:
            while (true) {
                int n47 = 0;
                int length4 = 0;
                Label_1854: {
                    Label_1847: {
                        if (!r) {
                            break Label_1847;
                        }
                        char c = b.charAt(n9);
                        this.h = fontMetrics.charWidth(c);
                        int n12;
                        int n11;
                        int n10 = n11 = (n12 = c);
                        int n17;
                        int n16;
                        int n15;
                        final int n14;
                        int n13 = n14 = (n15 = (n16 = (n17 = 32)));
                        int n18 = 0;
                        int n19 = 0;
                        do {
                            final int n20;
                            if (!r) {
                                Label_0207: {
                                    if (n18 == n19) {
                                        n20 = (n10 = (n11 = (n12 = sb.length())));
                                        if (r) {
                                            break Label_0207;
                                        }
                                        if (n20 > 0) {
                                            this.l.addElement(new mb(this, sb.toString(), n, n2 - this.c, 0));
                                            sb.setLength(0);
                                        }
                                    }
                                    final int n21;
                                    n10 = (n21 = (n11 = (n12 = sb3.length())));
                                }
                                n15 = (n13 = (n16 = (n17 = 1)));
                            }
                            if (!r) {
                                if (n20 > n13) {
                                    boolean b9;
                                    boolean equalsIgnoreCase;
                                    boolean b8;
                                    boolean b7;
                                    boolean b6;
                                    boolean b5;
                                    boolean b4;
                                    boolean b3;
                                    final boolean b2 = b3 = (b4 = (b5 = (b6 = (b7 = (b8 = (equalsIgnoreCase = (b9 = sb3.toString().substring(0, 2).equals(e.P[4]))))))));
                                    final StringBuffer sb4;
                                    Label_0794: {
                                        if (!r) {
                                            if (b2) {
                                                this.n.addElement(new mb(this, null, n5, n6 - this.c, 0));
                                                if (!r) {
                                                    break Label_0794;
                                                }
                                            }
                                            final boolean b10;
                                            b3 = (b10 = (b4 = (b5 = (b6 = (b7 = (b8 = (equalsIgnoreCase = (b9 = sb3.toString().substring(0, 2).equals(e.P[1])))))))));
                                        }
                                        if (!r) {
                                            if (b2) {
                                                this.n.addElement(new mb(this, null, n5, n6 - this.c, 5));
                                                if (!r) {
                                                    break Label_0794;
                                                }
                                            }
                                            b4 = (b3 = (b5 = (b6 = (b7 = (b8 = (equalsIgnoreCase = (b9 = sb3.toString().substring(0, 2).equals(e.P[3]))))))));
                                        }
                                        if (!r) {
                                            if (b3) {
                                                this.n.addElement(new mb(this, null, n5, n6 - this.c, 6));
                                                if (!r) {
                                                    break Label_0794;
                                                }
                                            }
                                            b5 = (b4 = (b6 = (b7 = (b8 = (equalsIgnoreCase = (b9 = sb3.toString().substring(0, 2).equals(e.P[7])))))));
                                        }
                                        if (!r) {
                                            if (b4) {
                                                this.n.addElement(new mb(this, null, n5, n6 - this.c, 7));
                                                if (!r) {
                                                    break Label_0794;
                                                }
                                            }
                                            b6 = (b5 = (b7 = (b8 = (equalsIgnoreCase = (b9 = sb3.toString().substring(0, 2).equals(e.P[8]))))));
                                        }
                                        if (!r) {
                                            if (b5) {
                                                this.n.addElement(new mb(this, null, n5, n6 - this.c, 8));
                                                if (!r) {
                                                    break Label_0794;
                                                }
                                            }
                                            b7 = (b6 = (b8 = (equalsIgnoreCase = (b9 = sb3.toString().substring(0, 2).substring(0, 2).equals(e.P[6])))));
                                        }
                                        if (!r) {
                                            if (b6) {
                                                this.n.addElement(new mb(this, null, n5, n6 - this.c, 9));
                                                if (!r) {
                                                    break Label_0794;
                                                }
                                            }
                                            b8 = (b7 = (equalsIgnoreCase = (b9 = sb3.toString().substring(0, 2).equals(e.P[2]))));
                                        }
                                        if (!r) {
                                            if (b7) {
                                                this.n.addElement(new mb(this, null, n5, n6 - this.c, 1));
                                                if (!r) {
                                                    break Label_0794;
                                                }
                                            }
                                            equalsIgnoreCase = (b8 = (b9 = sb3.toString().substring(0, 2).equals(e.P[9])));
                                        }
                                        if (!r) {
                                            if (b8) {
                                                this.n.addElement(new mb(this, null, n5, n6 - this.c, 2));
                                                if (!r) {
                                                    break Label_0794;
                                                }
                                            }
                                            b9 = (equalsIgnoreCase = sb3.toString().substring(0, 2).equalsIgnoreCase(e.P[0]));
                                        }
                                        if (!r) {
                                            if (equalsIgnoreCase) {
                                                this.n.addElement(new mb(this, null, n5, n6 - this.c, 3));
                                                if (!r) {
                                                    break Label_0794;
                                                }
                                            }
                                            sb4 = sb3;
                                            if (r) {
                                                break Label_0794;
                                            }
                                            b9 = sb4.toString().substring(0, 2).equals(e.P[5]);
                                        }
                                        if (b9) {
                                            this.n.addElement(new mb(this, null, n5, n6 - this.c, 4));
                                        }
                                    }
                                    sb4.setLength(0);
                                }
                                n10 = (n12 = c);
                                n16 = (n15 = (n17 = 32));
                            }
                            final int n22;
                            if (!r) {
                                Label_0982: {
                                    if (n10 == n15) {
                                        n22 = (n12 = sb2.length());
                                        if (r) {
                                            break Label_0982;
                                        }
                                        if (n22 > 0) {
                                            final int length = sb2.length();
                                            final int n23 = 4;
                                            final StringBuffer sb5;
                                            Label_0974: {
                                                if (!r) {
                                                    Label_0911: {
                                                        if (length > n23) {
                                                            final boolean equalsIgnoreCase2 = sb2.toString().substring(0, 4).equalsIgnoreCase(e.P[11]);
                                                            if (r) {
                                                                break Label_0911;
                                                            }
                                                            if (equalsIgnoreCase2) {
                                                                this.m.addElement(new mb(this, sb2.toString(), n3, n4 - this.c, 0));
                                                                if (!r) {
                                                                    break Label_0974;
                                                                }
                                                            }
                                                        }
                                                        sb5 = sb2;
                                                        if (r) {
                                                            break Label_0974;
                                                        }
                                                        sb5.length();
                                                    }
                                                }
                                                if (length > n23) {
                                                    final StringBuffer sb6 = sb2;
                                                    if (!r) {
                                                        if (sb6.toString().substring(0, 7).equalsIgnoreCase(e.P[10])) {
                                                            this.m.addElement(new mb(this, sb2.toString(), n3, n4 - this.c, 0));
                                                        }
                                                    }
                                                }
                                            }
                                            sb5.setLength(0);
                                        }
                                    }
                                    n12 = (n11 = c);
                                }
                                n17 = (n16 = 1);
                            }
                            final char c5;
                            final int n27;
                            Label_1066: {
                                Label_1058: {
                                    Label_1052: {
                                        if (!r) {
                                            if (n22 != n16) {
                                                final char c2 = (char)(n12 = c);
                                                final int n24 = n17 = 2;
                                                if (r) {
                                                    break Label_1052;
                                                }
                                                if (c2 != n24) {
                                                    final char c3 = (char)(n12 = c);
                                                    final int n25 = n17 = 31;
                                                    if (r) {
                                                        break Label_1052;
                                                    }
                                                    if (c3 != n25) {
                                                        final char c4 = (char)(n12 = c);
                                                        final int n26 = n17 = 22;
                                                        if (r) {
                                                            break Label_1052;
                                                        }
                                                        if (c4 != n26) {
                                                            final char c6;
                                                            c5 = (c6 = c);
                                                            final int n28;
                                                            n27 = (n28 = 15);
                                                            if (r) {
                                                                break Label_1066;
                                                            }
                                                            if (c5 != n27) {
                                                                break Label_1058;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            final int length2;
                                            n12 = (length2 = (length3 = n9));
                                            if (r) {
                                                break Label_1861;
                                            }
                                            n17 = b.length() - 1;
                                        }
                                    }
                                    if (n12 >= n17) {
                                        break Label_1857;
                                    }
                                    ++n9;
                                }
                                b.charAt(n9);
                            }
                            final int n38;
                            final int n39;
                            Label_1395: {
                                if (!r) {
                                    Label_1373: {
                                        if (c5 == n27) {
                                            int length2;
                                            n29 = (length2 = (length3 = n9));
                                            if (r) {
                                                break Label_1861;
                                            }
                                            if (n29 >= b.length() - 1) {
                                                break Label_1857;
                                            }
                                            ++n9;
                                            final char c7 = (char)(b.charAt(n9) - '0');
                                            char c6;
                                            final char c8 = c6 = c7;
                                            int n28;
                                            final int n30 = n28 = -1;
                                            Label_1220: {
                                                if (!r) {
                                                    Label_1211: {
                                                        if (c8 > n30) {
                                                            final char c9 = c6 = c7;
                                                            final int n32;
                                                            final int n31 = n32 = (n28 = 10);
                                                            if (r) {
                                                                break Label_1220;
                                                            }
                                                            if (c9 < n31) {
                                                                n33 = (length3 = b.length() - n9);
                                                                if (r) {
                                                                    break Label_1861;
                                                                }
                                                                if (n33 < 2) {
                                                                    break Label_1857;
                                                                }
                                                                final char c10 = (char)(b.charAt(n9 + 1) - '0');
                                                                int n34;
                                                                final char c11 = (char)(n34 = c10);
                                                                int n36;
                                                                final int n35 = n36 = -1;
                                                                Label_1208: {
                                                                    if (!r) {
                                                                        if (c11 <= n35) {
                                                                            break Label_1208;
                                                                        }
                                                                        final char c12;
                                                                        n34 = (c12 = c10);
                                                                        final int n37;
                                                                        n36 = (n37 = 10);
                                                                    }
                                                                    if (!r) {
                                                                        if (c11 >= n35) {
                                                                            break Label_1208;
                                                                        }
                                                                        n34 = (length2 = (length3 = b.length() - n9));
                                                                        if (r) {
                                                                            break Label_1861;
                                                                        }
                                                                        n36 = 2;
                                                                    }
                                                                    if (n34 <= n36) {
                                                                        break Label_1857;
                                                                    }
                                                                    n9 += 2;
                                                                    if (!r) {
                                                                        break Label_1211;
                                                                    }
                                                                }
                                                                ++n9;
                                                            }
                                                        }
                                                    }
                                                    b.charAt(n9);
                                                }
                                            }
                                            if (r) {
                                                break Label_1395;
                                            }
                                            if (c8 == n30) {
                                                n38 = n9;
                                                n39 = b.length() - 1;
                                                if (r) {
                                                    break Label_1395;
                                                }
                                                if (n38 < n39) {
                                                    ++n9;
                                                    final char c13 = (char)(b.charAt(n9) - '0');
                                                    int n40;
                                                    final char c14 = (char)(n40 = c13);
                                                    int n42;
                                                    final int n41 = n42 = -1;
                                                    Label_1370: {
                                                        if (!r) {
                                                            if (c14 <= n41) {
                                                                break Label_1370;
                                                            }
                                                            final char c15;
                                                            n40 = (c15 = c13);
                                                            final int n43;
                                                            n42 = (n43 = 10);
                                                        }
                                                        if (!r) {
                                                            if (c14 >= n41) {
                                                                break Label_1370;
                                                            }
                                                            n40 = (length2 = (length3 = b.length() - n9));
                                                            if (r) {
                                                                break Label_1861;
                                                            }
                                                            n42 = 2;
                                                        }
                                                        if (n40 < n42) {
                                                            break Label_1857;
                                                        }
                                                        final char c16 = (char)(b.charAt(n9 + 1) - '0');
                                                        if (!r) {
                                                            Label_1362: {
                                                                if (c16 > -1) {
                                                                    int n44;
                                                                    final char c17 = (char)(n44 = c16);
                                                                    int n46;
                                                                    final int n45 = n46 = 10;
                                                                    if (!r) {
                                                                        if (c17 >= n45) {
                                                                            break Label_1362;
                                                                        }
                                                                        n44 = (length2 = (length3 = b.length() - n9));
                                                                        if (r) {
                                                                            break Label_1861;
                                                                        }
                                                                        n46 = 2;
                                                                    }
                                                                    if (n44 <= n46) {
                                                                        break Label_1857;
                                                                    }
                                                                    n9 += 2;
                                                                    if (!r) {
                                                                        break Label_1373;
                                                                    }
                                                                }
                                                            }
                                                            ++n9;
                                                        }
                                                        if (!r) {
                                                            break Label_1373;
                                                        }
                                                    }
                                                    --n9;
                                                }
                                            }
                                        }
                                    }
                                    c = b.charAt(n9);
                                    this.h = fontMetrics.charWidth(c);
                                }
                            }
                            if (n38 == n39) {
                                break Label_1847;
                            }
                            int n21;
                            n18 = (n21 = (n10 = (n11 = (n12 = (n47 = c)))));
                            n19 = (n13 = (n15 = (n16 = (n17 = (length4 = 3)))));
                        } while (r);
                        if (r) {
                            break Label_1854;
                        }
                        if (n18 != n19) {
                            final char c18 = (char)(n47 = c);
                            final int n48 = length4 = 15;
                            if (r) {
                                break Label_1854;
                            }
                            if (c18 != n48) {
                                final char c19 = (char)(n47 = c);
                                final int n49 = length4 = 2;
                                if (r) {
                                    break Label_1854;
                                }
                                if (c19 != n49) {
                                    final char c20 = (char)(n47 = c);
                                    final int n50 = length4 = 31;
                                    if (r) {
                                        break Label_1854;
                                    }
                                    if (c20 != n50) {
                                        final char c21 = (char)(n47 = c);
                                        final int n51 = length4 = 22;
                                        if (r) {
                                            break Label_1854;
                                        }
                                        if (c21 != n51) {
                                            int n52;
                                            boolean d;
                                            final boolean b11 = d = ((n52 = (this.d ? 1 : 0)) != 0);
                                            final int n53;
                                            Label_1516: {
                                                if (!r) {
                                                    if (!b11) {
                                                        n53 = (n52 = n8 + this.h + 1);
                                                        if (r) {
                                                            break Label_1516;
                                                        }
                                                        if (n53 >= size.width) {
                                                            n8 = this.f + 2;
                                                            n7 += this.c;
                                                        }
                                                    }
                                                    d = this.d;
                                                }
                                            }
                                            Label_1845: {
                                                if (!r) {
                                                    int n69 = 0;
                                                    int h = 0;
                                                    Label_1837: {
                                                        Label_1831: {
                                                            if (n53 == 0) {
                                                                int length5;
                                                                int n60;
                                                                int n59;
                                                                int n58;
                                                                int n57;
                                                                int n56;
                                                                int n55;
                                                                final int n54 = n55 = (n56 = (n57 = (n58 = (n59 = (n60 = (length5 = (n52 = sb.length())))))));
                                                                final int n64;
                                                                Label_1630: {
                                                                    Label_1625: {
                                                                        if (!r) {
                                                                            if (n54 > 0) {
                                                                                sb.append(c);
                                                                                if (!r) {
                                                                                    break Label_1625;
                                                                                }
                                                                            }
                                                                            final int n61;
                                                                            n55 = (n61 = (n56 = (n57 = (n58 = (n59 = (n60 = (length5 = (n52 = c))))))));
                                                                        }
                                                                        Label_1589: {
                                                                            if (!r) {
                                                                                if (n54 != 35) {
                                                                                    final int n62 = n55 = (n56 = (n57 = (n58 = (n59 = (n60 = (length5 = (n52 = c)))))));
                                                                                    if (r) {
                                                                                        break Label_1589;
                                                                                    }
                                                                                    if (n62 != 60) {
                                                                                        final int n63 = n56 = (n57 = (n58 = (n59 = (n60 = (length5 = (n52 = c))))));
                                                                                        if (r) {
                                                                                            break Label_1630;
                                                                                        }
                                                                                        if (n63 != 38) {
                                                                                            break Label_1625;
                                                                                        }
                                                                                    }
                                                                                }
                                                                                n55 = (n57 = (n58 = (n59 = (n60 = (length5 = (n52 = sb2.length()))))));
                                                                            }
                                                                        }
                                                                        if (r) {
                                                                            break Label_1630;
                                                                        }
                                                                        if (n55 == 0) {
                                                                            n64 = (n57 = (n58 = (n59 = (n60 = (length5 = (n52 = sb3.length()))))));
                                                                            if (r) {
                                                                                break Label_1630;
                                                                            }
                                                                            if (n64 == 0) {
                                                                                sb.append(c);
                                                                                n = n8;
                                                                                n2 = n7;
                                                                            }
                                                                        }
                                                                    }
                                                                    n57 = (n56 = (n58 = (n59 = (n60 = (length5 = (n52 = sb3.length()))))));
                                                                }
                                                                final int n66;
                                                                Label_1721: {
                                                                    Label_1716: {
                                                                        if (!r) {
                                                                            if (n64 > 0) {
                                                                                sb3.append(c);
                                                                                if (!r) {
                                                                                    break Label_1716;
                                                                                }
                                                                            }
                                                                            n58 = (n57 = (n59 = (n60 = (length5 = (n52 = c)))));
                                                                        }
                                                                        if (!r) {
                                                                            if (n57 != 58) {
                                                                                final int n65 = n59 = (n60 = (length5 = (n52 = c)));
                                                                                if (r) {
                                                                                    break Label_1721;
                                                                                }
                                                                                if (n65 != 59) {
                                                                                    break Label_1716;
                                                                                }
                                                                            }
                                                                            n58 = (n60 = (length5 = (n52 = sb2.length())));
                                                                        }
                                                                        if (r) {
                                                                            break Label_1721;
                                                                        }
                                                                        if (n58 == 0) {
                                                                            n66 = (n60 = (length5 = (n52 = sb.length())));
                                                                            if (r) {
                                                                                break Label_1721;
                                                                            }
                                                                            if (n66 == 0) {
                                                                                sb3.append(c);
                                                                                n5 = n8;
                                                                                n6 = n7;
                                                                            }
                                                                        }
                                                                    }
                                                                    n60 = (n59 = (length5 = (n52 = sb2.length())));
                                                                }
                                                                if (!r) {
                                                                    if (n66 > 0) {
                                                                        sb2.append(c);
                                                                        if (!r) {
                                                                            break Label_1831;
                                                                        }
                                                                    }
                                                                    length5 = (n60 = (n52 = c));
                                                                }
                                                                Label_1794: {
                                                                    if (!r) {
                                                                        if (n60 != 119) {
                                                                            final int n67 = length5 = (n52 = c);
                                                                            if (r) {
                                                                                break Label_1794;
                                                                            }
                                                                            if (n67 != 87) {
                                                                                final int n68 = length5 = (n52 = c);
                                                                                if (r) {
                                                                                    break Label_1794;
                                                                                }
                                                                                if (n68 != 104) {
                                                                                    final char c22 = (char)(n69 = c);
                                                                                    final int n70 = h = 72;
                                                                                    if (r) {
                                                                                        break Label_1837;
                                                                                    }
                                                                                    if (c22 != n70) {
                                                                                        break Label_1831;
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                        length5 = sb.length();
                                                                    }
                                                                }
                                                                if (r) {
                                                                    break Label_1845;
                                                                }
                                                                if (length5 == 0) {
                                                                    final int length6 = sb3.length();
                                                                    if (r) {
                                                                        break Label_1845;
                                                                    }
                                                                    if (length6 == 0) {
                                                                        sb2.append(c);
                                                                        n3 = n8;
                                                                        n4 = n7;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        n69 = n8;
                                                        h = this.h;
                                                    }
                                                    n8 = n69 + h;
                                                    ++n9;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    n47 = n9;
                    length4 = b.length();
                }
                if (n47 < length4) {
                    continue;
                }
                break;
            }
            length3 = sb.length();
        }
        if (!r) {
            if (n29 > 0) {
                this.l.addElement(new mb(this, sb.toString(), n, n2 - this.c, 0));
            }
            final int length2;
            length3 = (length2 = sb3.length());
        }
        final int n71 = 1;
        Label_2585: {
            int n73 = 0;
            int length7 = 0;
            int n72 = 0;
            Label_2474: {
                if (!r) {
                    if (n33 > n71) {
                        boolean b19;
                        boolean equalsIgnoreCase3;
                        boolean b18;
                        boolean b17;
                        boolean b16;
                        boolean b15;
                        boolean b14;
                        boolean b13;
                        final boolean b12 = b13 = (b14 = (b15 = (b16 = (b17 = (b18 = (equalsIgnoreCase3 = (b19 = sb3.toString().equals(e.P[4]))))))));
                        final StringBuffer sb7;
                        Label_2435: {
                            if (!r) {
                                if (b12) {
                                    this.n.addElement(new mb(this, null, n5, n6 - this.c, 0));
                                    if (!r) {
                                        break Label_2435;
                                    }
                                }
                                final boolean b20;
                                b13 = (b20 = (b14 = (b15 = (b16 = (b17 = (b18 = (equalsIgnoreCase3 = (b19 = sb3.toString().equals(e.P[1])))))))));
                            }
                            if (!r) {
                                if (b12) {
                                    this.n.addElement(new mb(this, null, n5, n6 - this.c, 5));
                                    if (!r) {
                                        break Label_2435;
                                    }
                                }
                                b14 = (b13 = (b15 = (b16 = (b17 = (b18 = (equalsIgnoreCase3 = (b19 = sb3.toString().equals(e.P[3]))))))));
                            }
                            if (!r) {
                                if (b13) {
                                    this.n.addElement(new mb(this, null, n5, n6 - this.c, 6));
                                    if (!r) {
                                        break Label_2435;
                                    }
                                }
                                b15 = (b14 = (b16 = (b17 = (b18 = (equalsIgnoreCase3 = (b19 = sb3.toString().equals(e.P[7])))))));
                            }
                            if (!r) {
                                if (b14) {
                                    this.n.addElement(new mb(this, null, n5, n6 - this.c, 7));
                                    if (!r) {
                                        break Label_2435;
                                    }
                                }
                                b16 = (b15 = (b17 = (b18 = (equalsIgnoreCase3 = (b19 = sb3.toString().equals(e.P[8]))))));
                            }
                            if (!r) {
                                if (b15) {
                                    this.n.addElement(new mb(this, null, n5, n6 - this.c, 8));
                                    if (!r) {
                                        break Label_2435;
                                    }
                                }
                                b17 = (b16 = (b18 = (equalsIgnoreCase3 = (b19 = sb3.toString().equals(e.P[6])))));
                            }
                            if (!r) {
                                if (b16) {
                                    this.n.addElement(new mb(this, null, n5, n6 - this.c, 9));
                                    if (!r) {
                                        break Label_2435;
                                    }
                                }
                                b18 = (b17 = (equalsIgnoreCase3 = (b19 = sb3.toString().equals(e.P[2]))));
                            }
                            if (!r) {
                                if (b17) {
                                    this.n.addElement(new mb(this, null, n5, n6 - this.c, 1));
                                    if (!r) {
                                        break Label_2435;
                                    }
                                }
                                equalsIgnoreCase3 = (b18 = (b19 = sb3.toString().equals(e.P[9])));
                            }
                            if (!r) {
                                if (b18) {
                                    this.n.addElement(new mb(this, null, n5, n6 - this.c, 2));
                                    if (!r) {
                                        break Label_2435;
                                    }
                                }
                                b19 = (equalsIgnoreCase3 = sb3.toString().equalsIgnoreCase(e.P[0]));
                            }
                            if (!r) {
                                if (equalsIgnoreCase3) {
                                    this.n.addElement(new mb(this, null, n5, n6 - this.c, 3));
                                    if (!r) {
                                        break Label_2435;
                                    }
                                }
                                sb7 = sb3;
                                if (r) {
                                    break Label_2435;
                                }
                                b19 = sb7.toString().equals(e.P[5]);
                            }
                            if (b19) {
                                this.n.addElement(new mb(this, null, n5, n6 - this.c, 4));
                            }
                        }
                        sb7.setLength(0);
                    }
                    n72 = (length3 = (length7 = (n73 = sb2.length())));
                    if (r) {
                        break Label_2474;
                    }
                }
                if (length3 <= n71) {
                    break Label_2585;
                }
                length7 = (n72 = (n73 = (sb2.toString().substring(0, 4).equalsIgnoreCase(e.P[11]) ? 1 : 0)));
            }
            if (!r) {
                if (n72 != 0) {
                    this.m.addElement(new mb(this, sb2.toString(), n3, n4 - this.c, 0));
                    if (!r) {
                        break Label_2585;
                    }
                }
                n73 = (length7 = sb2.length());
            }
            if (!r) {
                if (length7 <= 7) {
                    break Label_2585;
                }
                n73 = (sb2.toString().substring(0, 7).equalsIgnoreCase(e.P[10]) ? 1 : 0);
            }
            if (n73 != 0) {
                this.m.addElement(new mb(this, sb2.toString(), n3, n4 - this.c, 0));
            }
        }
        this.i = n7;
        mb.d = n7;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final boolean r = d.r;
        final Dimension size = this.getSize();
        final int x = mouseEvent.getX();
        final int n = size.width - 30;
        int n5 = 0;
        int d = 0;
        Label_0095: {
            Label_0088: {
                if (!r) {
                    if (x > n) {
                        final int y = mouseEvent.getY();
                        final int n2 = size.height - 70;
                        if (r) {
                            break Label_0088;
                        }
                        if (y < n2) {
                            final int y2 = mouseEvent.getY();
                            final int n3 = size.height - 100;
                            if (r) {
                                break Label_0088;
                            }
                            if (y2 > n3) {
                                this.a.Db.d = false;
                            }
                        }
                    }
                    final int n4;
                    d = (n4 = (n5 = mouseEvent.getClickCount()));
                    if (r) {
                        break Label_0095;
                    }
                }
            }
            if (x != n) {
                return;
            }
            n5 = (d = (this.d ? 1 : 0));
        }
        if (!r) {
            if (d != 0) {
                return;
            }
            n5 = mouseEvent.getX();
        }
        final int n6 = n5;
        final int y3 = mouseEvent.getY();
        int n7 = 0;
        int n8;
        while (true) {
            while (true) {
                Label_0411: {
                    if (!r) {
                        break Label_0411;
                    }
                    n8 = this.l.elementAt(n7).d + size.height - this.c;
                    final int c = this.l.elementAt(n7).c;
                    final String b = this.l.elementAt(n7).b;
                    int n11;
                    int n10;
                    final int n9 = n10 = (n11 = n8 - this.K);
                    int n14;
                    int n13;
                    final int n12 = n13 = (n14 = y3);
                    Label_0408: {
                        if (!r) {
                            if (n9 > n12) {
                                break Label_0408;
                            }
                            final int n15;
                            n10 = (n15 = (n11 = n8 - this.K + this.c));
                            final int n16;
                            n13 = (n16 = (n14 = y3));
                        }
                        if (!r) {
                            if (n9 < n12) {
                                break Label_0408;
                            }
                            n11 = (n10 = n6);
                            n14 = (n13 = c);
                        }
                        Label_0398: {
                            final String substring;
                            Label_0288: {
                                int equals = 0;
                                Label_0272: {
                                    if (!r) {
                                        if (n10 < n13) {
                                            break Label_0408;
                                        }
                                        equals = (n11 = n6);
                                        if (r) {
                                            break Label_0272;
                                        }
                                        n14 = c + b.length() * this.h;
                                    }
                                    if (n11 > n14) {
                                        break Label_0408;
                                    }
                                    substring = b.substring(0, 1);
                                    if (r) {
                                        break Label_0288;
                                    }
                                    equals = (substring.equals("<") ? 1 : 0);
                                }
                                if (equals == 0) {
                                    break Label_0398;
                                }
                                b.substring(1, b.length() - 1);
                            }
                            final String s2;
                            final String s = s2 = substring;
                            if (!r && s2 == null) {
                                return;
                            }
                            if (s2.length() <= 0) {
                                return;
                            }
                            y i = this.a.i(s);
                            if (!r) {
                                if (i == null) {
                                    i = new y(this.a, s);
                                    this.a.Zb.b.a(s, i, false);
                                    this.a.Zb.b.a(i);
                                }
                                this.a.Zb.b.a(i);
                            }
                            if (!r) {
                                return;
                            }
                        }
                        this.a.l(b);
                        return;
                    }
                    ++n7;
                }
                if (n7 < this.l.size()) {
                    continue;
                }
                break;
            }
            n8 = 0;
            if (r) {
                continue;
            }
            break;
        }
        while (true) {
            while (true) {
                Label_0645: {
                    if (!r) {
                        break Label_0645;
                    }
                    final Object element = this.m.elementAt(n8);
                    final int n17 = ((mb)element).d + size.height - this.c;
                    final int c2 = this.m.elementAt(n8).c;
                    String b2 = this.m.elementAt(n8).b;
                    int n20;
                    int n19;
                    final int n18 = n19 = (n20 = n17 - this.K);
                    int n23;
                    int n22;
                    final int n21 = n22 = (n23 = y3);
                    Label_0642: {
                        if (!r) {
                            if (n18 > n21) {
                                break Label_0642;
                            }
                            final int n24;
                            n19 = (n24 = (n20 = n17 - this.K + this.c));
                            final int n25;
                            n22 = (n25 = (n23 = y3));
                        }
                        if (!r) {
                            if (n18 < n21) {
                                break Label_0642;
                            }
                            n20 = (n19 = n6);
                            n23 = (n22 = c2);
                        }
                        Label_0586: {
                            if (!r) {
                                if (n19 < n22) {
                                    break Label_0642;
                                }
                                final int equalsIgnoreCase = n20 = n6;
                                if (r) {
                                    break Label_0586;
                                }
                                n23 = c2 + b2.length() * this.h;
                            }
                            if (n20 > n23) {
                                break Label_0642;
                            }
                            try {
                                final String substring2 = b2.substring(0, 1);
                                Label_0612: {
                                    if (!r) {
                                        final int equalsIgnoreCase = substring2.equalsIgnoreCase("w") ? 1 : 0;
                                        if (equalsIgnoreCase == 0) {
                                            break Label_0612;
                                        }
                                        new StringBuffer(e.P[10]).append(b2).toString();
                                    }
                                    b2 = substring2;
                                }
                                this.a.getAppletContext().showDocument(new URL(b2), e.P[12]);
                                return;
                            }
                            catch (Exception ex) {
                                return;
                            }
                        }
                    }
                    ++n8;
                }
                if (n8 < this.m.size()) {
                    continue;
                }
                break;
            }
            final Object element = this;
            if (r) {
                continue;
            }
            break;
        }
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final boolean r = d.r;
        this.v = mouseEvent.getX();
        this.w = mouseEvent.getY();
        int n2;
        int x;
        final int n = x = (n2 = (this.d ? 1 : 0));
        final boolean b;
        Label_0183: {
            if (!r) {
                Label_0177: {
                    if (n != 0) {
                        e e = this;
                        if (!r) {
                            if (this.L) {
                                b = ((n2 = (this.O ? 1 : 0)) != 0);
                                if (r) {
                                    break Label_0183;
                                }
                                if (b) {
                                    break Label_0177;
                                }
                            }
                            e = this;
                        }
                        lb b2 = e.k.b;
                        while (true) {
                            Label_0172: {
                                if (!r) {
                                    break Label_0172;
                                }
                                final int c = ((mb)b2.b).c;
                                final int n3 = c - this.K;
                                final int w = this.w;
                                final lb lb;
                                Label_0171: {
                                    Label_0167: {
                                        Label_0156: {
                                            int g = 0;
                                            Label_0137: {
                                                if (!r) {
                                                    if (n3 > w) {
                                                        break Label_0156;
                                                    }
                                                    final int n4;
                                                    g = (n4 = c - this.K + this.c);
                                                    if (r) {
                                                        break Label_0137;
                                                    }
                                                    final int w2 = this.w;
                                                }
                                                if (n3 < w) {
                                                    break Label_0156;
                                                }
                                                lb = b2;
                                                if (r) {
                                                    break Label_0171;
                                                }
                                                g = (((mb)lb.b).g ? 1 : 0);
                                            }
                                            if (g != 0) {
                                                break Label_0167;
                                            }
                                            ((mb)b2.b).g = true;
                                            if (!r) {
                                                break Label_0167;
                                            }
                                        }
                                        ((mb)b2.b).g = false;
                                    }
                                    final lb c2 = b2.c;
                                }
                                b2 = lb;
                            }
                            if (b2 == null) {
                                return;
                            }
                            continue;
                        }
                    }
                }
                final int n5 = mouseEvent.getModifiers() & 0x4;
            }
        }
        if (!r) {
            if (n > 0) {
                return;
            }
            x = this.x;
        }
        final int n6 = -1;
        Label_0355: {
            e e2 = null;
            Label_0348: {
                final int v;
                Label_0283: {
                    if (!r) {
                        if ((b ? 1 : 0) == n6) {
                            v = this.v;
                            final int n7 = -1;
                            if (r) {
                                break Label_0283;
                            }
                            if (v != n7) {
                                this.x = this.v;
                                this.y = this.w;
                                this.C = this.y;
                                this.B = this.x;
                                this.z = this.C;
                                this.A = this.B;
                                if (!r) {
                                    break Label_0355;
                                }
                            }
                        }
                        e2 = this;
                        if (r) {
                            break Label_0348;
                        }
                        n2 = this.w;
                        final int z = this.z;
                    }
                }
                if (v > n6) {
                    this.y = this.w;
                    this.C = this.z;
                    this.x = this.v;
                    this.B = this.A;
                    if (!r) {
                        break Label_0355;
                    }
                }
                this.y = this.z;
                this.C = this.w;
                this.x = this.A;
                e2 = this;
            }
            e2.B = this.v;
        }
        this.repaint();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final boolean r = d.r;
        final int x = this.x;
        e e = null;
        Label_0031: {
            if (!r) {
                if (x != -1) {
                    return;
                }
                e = this;
                if (r) {
                    break Label_0031;
                }
                final boolean d = this.d;
            }
            if (x != 0) {
                return;
            }
            e = this;
        }
        final Dimension size = e.getSize();
        final int x2 = mouseEvent.getX();
        final int y = mouseEvent.getY();
        int n = 0;
        int n2;
        while (true) {
            while (true) {
                Label_0195: {
                    if (!r) {
                        break Label_0195;
                    }
                    n2 = this.l.elementAt(n).d + size.height - this.c;
                    final int c = this.l.elementAt(n).c;
                    final String b = this.l.elementAt(n).b;
                    int n5;
                    int n4;
                    final int n3 = n4 = (n5 = n2 - this.K);
                    int n8;
                    int n7;
                    final int n6 = n7 = (n8 = y);
                    Label_0192: {
                        if (!r) {
                            if (n3 > n6) {
                                break Label_0192;
                            }
                            final int n9;
                            n4 = (n9 = (n5 = n2 - this.K + this.c));
                            final int n10;
                            n7 = (n10 = (n8 = y));
                        }
                        if (!r) {
                            if (n3 < n6) {
                                break Label_0192;
                            }
                            n5 = (n4 = x2);
                            n8 = (n7 = c);
                        }
                        if (!r) {
                            if (n4 < n7) {
                                break Label_0192;
                            }
                            n5 = x2;
                            n8 = c + b.length() * this.h;
                        }
                        if (n5 <= n8) {
                            this.setCursor(Cursor.getPredefinedCursor(12));
                            return;
                        }
                    }
                    ++n;
                }
                if (n < this.l.size()) {
                    continue;
                }
                break;
            }
            n2 = 0;
            if (r) {
                continue;
            }
            break;
        }
        while (true) {
            while (true) {
                Label_0361: {
                    if (!r) {
                        break Label_0361;
                    }
                    final Object element = this.m.elementAt(n2);
                    final int n11 = ((mb)element).d + size.height - this.c;
                    final int c2 = this.m.elementAt(n2).c;
                    final String b2 = this.m.elementAt(n2).b;
                    int n14;
                    int n13;
                    final int n12 = n13 = (n14 = n11 - this.K);
                    int n17;
                    int n16;
                    final int n15 = n16 = (n17 = y);
                    Label_0358: {
                        if (!r) {
                            if (n12 > n15) {
                                break Label_0358;
                            }
                            final int n18;
                            n13 = (n18 = (n14 = n11 - this.K + this.c));
                            final int n19;
                            n16 = (n19 = (n17 = y));
                        }
                        if (!r) {
                            if (n12 < n15) {
                                break Label_0358;
                            }
                            n14 = (n13 = x2);
                            n17 = (n16 = c2);
                        }
                        if (!r) {
                            if (n13 < n16) {
                                break Label_0358;
                            }
                            n14 = x2;
                            n17 = c2 + b2.length() * this.h;
                        }
                        if (n14 <= n17) {
                            this.setCursor(Cursor.getPredefinedCursor(12));
                            return;
                        }
                    }
                    ++n2;
                }
                if (n2 < this.m.size()) {
                    continue;
                }
                break;
            }
            final Object element = this;
            if (r) {
                continue;
            }
            break;
        }
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final boolean r = d.r;
        this.e = "";
        e e = this;
        if (!r) {
            if (this.d) {
                e e2 = this;
                if (!r) {
                    if (this.i == 0) {
                        return;
                    }
                    e2 = this;
                }
                lb lb = e2.k.b;
                while (true) {
                    while (true) {
                        Label_0168: {
                            if (!r) {
                                break Label_0168;
                            }
                            final Object b = lb.b;
                            final int c = ((mb)b).c;
                            final int n = c - this.K;
                            Label_0163: {
                                Object b2 = null;
                                Label_0156: {
                                    Label_0137: {
                                        if (!r) {
                                            if (n <= mouseEvent.getY()) {
                                                final int n2 = c - this.K + this.c;
                                                if (r) {
                                                    break Label_0137;
                                                }
                                                if (n2 >= mouseEvent.getY()) {
                                                    ((mb)lb.b).g = false;
                                                    ((mb)lb.b).g = true;
                                                    if (!r) {
                                                        break Label_0163;
                                                    }
                                                }
                                            }
                                            b2 = this;
                                            if (r) {
                                                break Label_0156;
                                            }
                                            final boolean l = this.L;
                                        }
                                    }
                                    if (n != 0) {
                                        b2 = this;
                                        if (r) {
                                            break Label_0156;
                                        }
                                        if (this.O) {
                                            break Label_0163;
                                        }
                                    }
                                    b2 = lb.b;
                                }
                                ((mb)b2).g = false;
                            }
                            lb = lb.c;
                        }
                        if (lb != null) {
                            continue;
                        }
                        break;
                    }
                    final Object b = this;
                    if (r) {
                        continue;
                    }
                    break;
                }
                this.repaint();
            }
            e = this;
        }
        e.s = true;
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        final boolean r = d.r;
        this.v = -1;
        this.w = -1;
        this.x = -1;
        this.y = -1;
        this.s = false;
        this.repaint();
        e e = this;
        e e2 = this;
        e e3 = this;
        Label_0082: {
            if (!r) {
                if (!this.d) {
                    e = this;
                    e2 = this;
                    e3 = this;
                    if (r) {
                        break Label_0082;
                    }
                    if (this.e.length() > 1) {
                        e = this;
                        e2 = this;
                        e3 = this;
                        if (r) {
                            break Label_0082;
                        }
                        if (this.p > 0) {
                            this.a.r(this.e);
                        }
                    }
                }
                e = this;
                e2 = this;
                e3 = this;
            }
        }
        if (!r) {
            if (e3.E == null) {
                return;
            }
            e = this;
            e2 = this;
        }
        if (!r) {
            if (e2.L) {
                return;
            }
            e = this;
        }
        e.E.requestFocus();
    }
    
    public void paint(final Graphics graphics) {
        final boolean r = d.r;
        final Dimension size = this.getSize();
        int n = 0;
        int n2 = 0;
        Color f = this.a.f;
        int n3 = 0;
        final StringBuffer sb = new StringBuffer();
        Font d = this.D;
        final String name = d.getName();
        final int size2 = d.getSize();
        final FontMetrics fontMetrics = this.getFontMetrics(d);
        final Font font = d;
        final Font font2 = new Font(name, 1, size2);
        e e = this;
        final char c;
        Label_0397: {
            if (!r) {
                Label_0393: {
                    if (this.J != null) {
                        final char c2;
                        c = (c2 = this.M.charAt(8));
                        if (r) {
                            break Label_0397;
                        }
                        if (c == 'n') {
                            final int b;
                            final int n4 = b = this.J.b();
                            if (!r) {
                                if (b == this.J.a() - 1) {
                                    final boolean d2;
                                    final boolean b2 = d2 = this.d;
                                    if (!r) {
                                        if (b2) {}
                                    }
                                }
                            }
                            final int n5 = b;
                            int n6 = this.i / this.c;
                            final int n7 = (this.J.getSize().height + 1) / this.c;
                            int d3;
                            final boolean b3 = (d3 = (this.d ? 1 : 0)) != 0;
                            if (!r) {
                                Label_0333: {
                                    if (b3) {
                                        final int n8 = this.b * this.c;
                                        final b j;
                                        Label_0326: {
                                            if (!r) {
                                                if (n8 > this.getSize().height) {
                                                    n6 = n6 - n7 + 1;
                                                    this.J.show();
                                                    this.a.Zb.invalidate();
                                                    this.a.Zb.validate();
                                                    if (!r) {
                                                        break Label_0333;
                                                    }
                                                }
                                                n6 = 1;
                                                j = this.J;
                                                if (r) {
                                                    break Label_0326;
                                                }
                                                j.b();
                                            }
                                            if (n8 == 0) {
                                                this.J.setVisible(false);
                                                this.a.Zb.invalidate();
                                                this.a.Zb.validate();
                                                if (!r) {
                                                    break Label_0333;
                                                }
                                            }
                                            final b i = this.J;
                                        }
                                        j.a(1, 0, 1, 1);
                                    }
                                }
                                d3 = n5;
                            }
                            if (d3 != 0) {
                                this.K = this.K + (this.i - n4 * this.c) - this.c;
                                this.J.a(n6 - 1, 1, 0, n6);
                                if (!r) {
                                    break Label_0393;
                                }
                            }
                            this.J.a(n6);
                        }
                    }
                }
                e = this;
            }
            final boolean s = e.s;
        }
        if (!r) {
            if (c != '\0') {
                final char c2;
                final boolean b4 = (c2 = (char)(this.d ? 1 : 0)) != '\0';
                if (!r) {
                    if (!b4) {
                        this.a(this.B, this.C, this.x, this.y);
                    }
                }
            }
        }
        final Image image = this.createImage(size.width, size.height);
        final Graphics graphics2 = image.getGraphics();
        final int width = this.a.C.getWidth(this);
        final int height = this.a.C.getHeight(this);
        final int n10;
        final int n9 = n10 = width;
        final int n11;
        Label_0584: {
            if (!r) {
                if (n9 > 1) {
                    n11 = height;
                    if (r) {
                        break Label_0584;
                    }
                    if (n11 > 1) {
                        int n12 = 0;
                    Label_0548_Outer:
                        while (true) {
                            Label_0571: {
                                if (!r) {
                                    break Label_0571;
                                }
                                int n13 = 0;
                                int n14 = 0;
                                while (true) {
                                    while (true) {
                                        Label_0550: {
                                            if (!r) {
                                                break Label_0550;
                                            }
                                            graphics2.drawImage(this.a.C, n12, n13, this);
                                            n13 = n14;
                                        }
                                        if (n13 < size.height) {
                                            continue Label_0548_Outer;
                                        }
                                        break;
                                    }
                                    n14 = n12 + width;
                                    if (r) {
                                        continue;
                                    }
                                    break;
                                }
                                n12 = n14;
                            }
                            if (n12 < size.width) {
                                continue;
                            }
                            break;
                        }
                    }
                }
                final boolean d4 = this.d;
            }
        }
        if (!r) {
            if (n9 == 0) {
                final boolean d5 = this.a.Db.d;
                if (!r) {
                    if (d5) {
                        this.a.Db.a(graphics2, size.width - 200, size.height - 100);
                    }
                }
            }
        }
        int width2 = n11;
        int height2 = 0;
        int n16;
        int d6;
        int n15;
        final boolean b5 = (n15 = (d6 = (n16 = (this.F ? 1 : 0)))) != 0;
        final int n17;
        Label_0734: {
            if (!r) {
                e e2 = null;
                Label_0731: {
                    if (b5) {
                        e2 = this;
                        if (r) {
                            break Label_0731;
                        }
                        if (this.G != null) {
                            n17 = (d6 = (n16 = (this.a.k.checkAll() ? 1 : 0)));
                            if (r) {
                                break Label_0734;
                            }
                            if (n17 != 0) {
                                width2 = this.G.getWidth(this);
                                height2 = this.G.getHeight(this);
                                graphics2.drawImage(this.G, this.getSize().width - width2 - 6, 3, this);
                            }
                        }
                    }
                    e2 = this;
                }
                d6 = (n15 = (n16 = (e2.I ? 1 : 0)));
            }
        }
        if (!r) {
            e e3 = null;
            Label_0845: {
                if (n17 != 0) {
                    e3 = this;
                    if (r) {
                        break Label_0845;
                    }
                    if (this.H != null) {
                        e3 = this;
                        if (r) {
                            break Label_0845;
                        }
                        if (this.H != "") {
                            int n19;
                            final int n18 = n19 = width2;
                            if (!r) {
                                if (n18 == 0) {
                                    width2 = 30;
                                }
                                final int n20;
                                n19 = (n20 = height2);
                            }
                            Label_0796: {
                                if (!r) {
                                    if (n18 != 0) {
                                        break Label_0796;
                                    }
                                    n19 = 30;
                                }
                                height2 = n19;
                            }
                            graphics2.setColor(this.a.f);
                            graphics2.drawString(this.H, this.getSize().width - width2, height2 + 13);
                        }
                    }
                }
                graphics2.setColor(this.a.f);
                e3 = this;
            }
            n16 = (d6 = (e3.d ? 1 : 0));
        }
        int height3 = 0;
        Label_0873: {
            if (!r) {
                if (d6 == 0) {
                    height3 = size.height;
                    if (!r) {
                        break Label_0873;
                    }
                }
                n16 = this.c;
            }
            height3 = n16;
        }
        int n21 = height3 - this.c + this.o;
        lb lb = this.k.b;
        while (true) {
            Label_0996: {
                if (!r) {
                    break Label_0996;
                }
                n21 = ((mb)lb.b).c + height3 - this.c + this.o;
                lb c3;
                do {
                    final lb lb2 = lb;
                    if (!r && ((mb)lb2.b).d + height3 - this.c + this.o - this.K - fontMetrics.getAscent() < 0) {
                        n2 += ((mb)lb.b).b.length();
                        lb = lb.c;
                        break Label_0996;
                    }
                    c3 = lb2;
                } while (r);
                int length8 = 0;
                int n98 = 0;
                Label_3315: {
                    while (true) {
                        Label_3288: {
                            if (!r) {
                                break Label_3288;
                            }
                            String s2 = ((mb)c3.b).b;
                            int n23;
                            int n22 = n23 = this.f + 2;
                            int n24 = n21 - this.K;
                            final boolean d7 = this.d;
                            final int length7;
                            Label_3286: {
                                Label_3272: {
                                    Label_1255: {
                                        final mb mb;
                                        Label_1123: {
                                            if (!r) {
                                                if (!d7) {
                                                    break Label_1255;
                                                }
                                                mb = (mb)c3.b;
                                                if (r) {
                                                    break Label_1123;
                                                }
                                                final boolean g = mb.g;
                                            }
                                            if (!d7) {
                                                int s3;
                                                final int n25 = s3 = (this.s ? 1 : 0);
                                                if (!r) {
                                                    if (n25 == 0) {
                                                        break Label_1255;
                                                    }
                                                    final int n26;
                                                    s3 = (n26 = n24);
                                                }
                                                final int c4 = this.C;
                                                if (!r) {
                                                    if (n25 < c4) {
                                                        break Label_1255;
                                                    }
                                                    s3 = n24;
                                                    final int y = this.y;
                                                }
                                                if (s3 > c4) {
                                                    break Label_1255;
                                                }
                                            }
                                            final mb mb2 = (mb)c3.b;
                                        }
                                        mb.g = true;
                                        if (!r) {
                                            if (d != font) {
                                                d = font;
                                                graphics2.setFont(d);
                                            }
                                            n = n24 - fontMetrics.getAscent();
                                            graphics2.drawImage(this.a.z, 2, n, size.width + 1, n + this.c, 204, 20, 208, 36, this);
                                        }
                                        if (!r) {
                                            if (s2.charAt(0) == '\u0003') {
                                                s2 = s2.substring(2);
                                            }
                                            graphics2.setColor(this.a.f);
                                            graphics2.drawString(s2, n23, n24);
                                            graphics2.setColor(f);
                                        }
                                        if (!r) {
                                            break Label_3272;
                                        }
                                    }
                                    Font font3 = font;
                                    sb.setLength(0);
                                    int n27 = 0;
                                    Color color = this.a.f;
                                    Color color2 = Color.white;
                                    int n28 = 1;
                                    int n29 = 0;
                                    boolean b6 = false;
                                    boolean b7 = false;
                                    final Font font4 = d;
                                    if (r || font4 != font) {
                                        d = font4;
                                        graphics2.setFont(d);
                                    }
                                    int n30 = 0;
                                    int n31 = 0;
                                    int n32 = 0;
                                Label_3063:
                                    while (true) {
                                        int n70 = 0;
                                        int length3 = 0;
                                        Label_3047: {
                                            Label_3040: {
                                                if (!r) {
                                                    break Label_3040;
                                                }
                                                char c8;
                                                char char1;
                                                char c7;
                                                char c6;
                                                final char c5 = c6 = (c7 = (char1 = (c8 = s2.charAt(n30))));
                                                char c13;
                                                char c12;
                                                char c11;
                                                char c10;
                                                final char c9 = c10 = (c11 = (c12 = (c13 = '\u0001')));
                                                if (!r) {
                                                    if (c5 == c9) {
                                                        n31 = n30;
                                                        n32 = s2.length() - 1;
                                                        if (r) {
                                                            break Label_3063;
                                                        }
                                                        if (n31 >= n32) {
                                                            break Label_3063;
                                                        }
                                                        ++n30;
                                                    }
                                                    final char c14;
                                                    c6 = (c14 = (c7 = (char1 = (c8 = s2.charAt(n30)))));
                                                    final char c15;
                                                    c10 = (c15 = (c11 = (c12 = (c13 = '\u0002'))));
                                                }
                                                if (!r) {
                                                    if (c5 == c9) {
                                                        final int n33 = n30;
                                                        final int n34 = s2.length() - 1;
                                                        if (r) {
                                                            break Label_3063;
                                                        }
                                                        if (n33 >= n34) {
                                                            break Label_3063;
                                                        }
                                                        final Font font5 = font3;
                                                        if (!r && font5 == font2) {
                                                            font3 = font;
                                                            if (r) {
                                                                goto Label_1415;
                                                            }
                                                        }
                                                        else {
                                                            font3 = font5;
                                                        }
                                                        ++n30;
                                                    }
                                                    c7 = (c6 = (char1 = (c8 = s2.charAt(n30))));
                                                    c11 = (c10 = (c12 = (c13 = '\u001f')));
                                                }
                                                if (!r) {
                                                    if (c6 == c10) {
                                                        final int n35 = n30;
                                                        final int n36 = s2.length() - 1;
                                                        if (r) {
                                                            break Label_3063;
                                                        }
                                                        if (n35 >= n36) {
                                                            break Label_3063;
                                                        }
                                                        final boolean b8 = b6;
                                                        if (!r && b8) {
                                                            b6 = false;
                                                            if (r) {
                                                                goto Label_1474;
                                                            }
                                                        }
                                                        else {
                                                            b6 = b8;
                                                        }
                                                        ++n30;
                                                    }
                                                    char1 = (c7 = (c8 = s2.charAt(n30)));
                                                    c12 = (c11 = (c13 = '\u0016'));
                                                }
                                                if (!r) {
                                                    if (c7 == c11) {
                                                        final int n37 = n30;
                                                        final int n38 = s2.length() - 1;
                                                        if (r) {
                                                            break Label_3063;
                                                        }
                                                        if (n37 >= n38) {
                                                            break Label_3063;
                                                        }
                                                        color = Color.white;
                                                        color2 = this.a.f;
                                                        ++n30;
                                                    }
                                                    c8 = (char1 = s2.charAt(n30));
                                                    c13 = (c12 = '\u000f');
                                                }
                                                char c17 = '\0';
                                                final int n68;
                                                final int n69;
                                                Label_2162: {
                                                    char char2 = '\0';
                                                    Label_2147: {
                                                        if (!r) {
                                                            if (char1 == c12) {
                                                                final int n39 = n30;
                                                                final int n40 = s2.length() - 1;
                                                                if (r) {
                                                                    break Label_3063;
                                                                }
                                                                if (n39 >= n40) {
                                                                    break Label_3063;
                                                                }
                                                                final boolean b9 = b7;
                                                                if (!r && b9) {
                                                                    color = this.a.f;
                                                                    color2 = this.a.f;
                                                                    b7 = false;
                                                                    if (r) {
                                                                        goto Label_1601;
                                                                    }
                                                                }
                                                                else {
                                                                    b7 = b9;
                                                                    color = this.a.f;
                                                                    color2 = Color.white;
                                                                }
                                                                ++n30;
                                                            }
                                                            char2 = (c8 = s2.charAt(n30));
                                                            if (r) {
                                                                break Label_2147;
                                                            }
                                                            c13 = '\u0003';
                                                        }
                                                        if (c8 == c13) {
                                                            final int n41 = n30;
                                                            final int n42 = s2.length() - 1;
                                                            if (r) {
                                                                break Label_3063;
                                                            }
                                                            if (n41 >= n42) {
                                                                break Label_3063;
                                                            }
                                                            ++n30;
                                                            final char char3;
                                                            final char c16 = char3 = s2.charAt(n30);
                                                            final int n44;
                                                            final int n43 = n44 = 48;
                                                            final int a;
                                                            Label_1727: {
                                                                if (!r) {
                                                                    if (c16 == n43) {
                                                                        final int length = s2.length();
                                                                        final int n45 = n30 + 1;
                                                                        if (r) {
                                                                            break Label_1727;
                                                                        }
                                                                        if (length > n45) {
                                                                            a = (this.a.a(s2.charAt(n30 + 1)) ? 1 : 0);
                                                                            if (r) {
                                                                                break Label_1727;
                                                                            }
                                                                            if (a != 0) {
                                                                                ++n30;
                                                                            }
                                                                        }
                                                                    }
                                                                    s2.charAt(n30);
                                                                }
                                                            }
                                                            c17 = (char)a;
                                                            int n46;
                                                            final char c18 = (char)(n46 = c17);
                                                            final int n47 = -1;
                                                            final int n55;
                                                            Label_2080: {
                                                                Label_1860: {
                                                                    if (!r) {
                                                                        Label_1846: {
                                                                            if (c18 > n47) {
                                                                                final char c19 = (char)(n46 = c17);
                                                                                final int n49;
                                                                                final int n48 = n49 = 10;
                                                                                if (r) {
                                                                                    break Label_1860;
                                                                                }
                                                                                if (c19 < n48) {
                                                                                    final int n50 = s2.length() - n30;
                                                                                    final int n51 = 2;
                                                                                    if (r) {
                                                                                        break Label_3063;
                                                                                    }
                                                                                    if (n50 < n51) {
                                                                                        break Label_3063;
                                                                                    }
                                                                                    final char c20 = (char)(s2.charAt(n30 + 1) - '0');
                                                                                    char c22;
                                                                                    final char c21 = c22 = c20;
                                                                                    Label_1841: {
                                                                                        if (!r) {
                                                                                            if (c21 > -1) {
                                                                                                final char c23 = c22 = c20;
                                                                                                if (r) {
                                                                                                    break Label_1841;
                                                                                                }
                                                                                                if (c23 < '\n') {
                                                                                                    final int n52 = s2.length() - n30;
                                                                                                    final int n53 = 2;
                                                                                                    if (r) {
                                                                                                        break Label_3063;
                                                                                                    }
                                                                                                    if (n52 <= n53) {
                                                                                                        break Label_3063;
                                                                                                    }
                                                                                                    n28 = c20 + '\n';
                                                                                                    n30 += 2;
                                                                                                    if (!r) {
                                                                                                        break Label_1846;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                            c22 = c17;
                                                                                        }
                                                                                    }
                                                                                    n28 = c22;
                                                                                    ++n30;
                                                                                }
                                                                            }
                                                                        }
                                                                        final int char4;
                                                                        final int n54;
                                                                        n46 = (n54 = (char4 = s2.charAt(n30)));
                                                                        if (r) {
                                                                            break Label_2080;
                                                                        }
                                                                    }
                                                                }
                                                                if (n46 == n47) {
                                                                    final int char4;
                                                                    final int n54;
                                                                    n55 = (n54 = (char4 = n30));
                                                                    if (!r) {
                                                                        if (n55 < s2.length() - 1) {
                                                                            ++n30;
                                                                            final char char5;
                                                                            final char c24 = char5 = s2.charAt(n30);
                                                                            final int n57;
                                                                            final int n56 = n57 = 48;
                                                                            int a2 = 0;
                                                                            Label_1954: {
                                                                                final int length2;
                                                                                final int n58;
                                                                                Label_1953: {
                                                                                    if (!r) {
                                                                                        if (c24 == n56) {
                                                                                            length2 = s2.length();
                                                                                            n58 = n30 + 1;
                                                                                            if (r) {
                                                                                                break Label_1953;
                                                                                            }
                                                                                            if (length2 > n58) {
                                                                                                final int n59 = a2 = (this.a.a(s2.charAt(n30 + 1)) ? 1 : 0);
                                                                                                if (r) {
                                                                                                    break Label_1954;
                                                                                                }
                                                                                                if (n59 != 0) {
                                                                                                    ++n30;
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                        s2.charAt(n30);
                                                                                    }
                                                                                }
                                                                                a2 = length2 - n58;
                                                                            }
                                                                            final char c25 = (char)a2;
                                                                            char c27;
                                                                            int n60;
                                                                            final char c26 = (char)(n60 = (c27 = c25));
                                                                            int n63;
                                                                            int n62;
                                                                            final int n61 = n62 = (n63 = -1);
                                                                            Label_2077: {
                                                                                if (!r) {
                                                                                    if (c26 <= n61) {
                                                                                        break Label_2077;
                                                                                    }
                                                                                    final char c28;
                                                                                    n60 = (c28 = (c27 = c25));
                                                                                    final int n64;
                                                                                    n62 = (n64 = (n63 = 10));
                                                                                }
                                                                                if (!r) {
                                                                                    if (c26 >= n61) {
                                                                                        break Label_2077;
                                                                                    }
                                                                                    n60 = s2.length() - n30;
                                                                                    n62 = 2;
                                                                                }
                                                                                if (r) {
                                                                                    break Label_3063;
                                                                                }
                                                                                if (n60 < n62) {
                                                                                    break Label_3063;
                                                                                }
                                                                                final char c29 = (char)(s2.charAt(n30 + 1) - '0');
                                                                                char c31;
                                                                                final char c30 = c31 = c29;
                                                                                Label_2067: {
                                                                                    if (!r) {
                                                                                        if (c30 > -1) {
                                                                                            final char c32 = c31 = c29;
                                                                                            if (r) {
                                                                                                break Label_2067;
                                                                                            }
                                                                                            if (c32 < '\n') {
                                                                                                final int n65 = s2.length() - n30;
                                                                                                final int n66 = 2;
                                                                                                if (r) {
                                                                                                    break Label_3063;
                                                                                                }
                                                                                                if (n65 <= n66) {
                                                                                                    break Label_3063;
                                                                                                }
                                                                                                n29 = c29 + '\n';
                                                                                                n30 += 2;
                                                                                                if (!r) {
                                                                                                    break Label_2080;
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                        c31 = c25;
                                                                                    }
                                                                                }
                                                                                n29 = c31;
                                                                                ++n30;
                                                                                if (!r) {
                                                                                    break Label_2080;
                                                                                }
                                                                            }
                                                                            --n30;
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                            final int n67;
                                                            if (!r) {
                                                                if (n55 >= 0) {
                                                                    final int char4;
                                                                    n67 = (char4 = n28);
                                                                    if (!r) {
                                                                        if (n67 < 16) {
                                                                            color = this.b(n28);
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                            if (r) {
                                                                break Label_2162;
                                                            }
                                                            if (n67 >= 0) {
                                                                n68 = n29;
                                                                n69 = 16;
                                                                if (r) {
                                                                    break Label_2162;
                                                                }
                                                                if (n68 < n69) {
                                                                    color2 = this.b(n29);
                                                                }
                                                            }
                                                        }
                                                        char2 = s2.charAt(n30);
                                                    }
                                                    c17 = char2;
                                                    this.h = fontMetrics.charWidth(c17);
                                                }
                                                if (n68 != n69) {
                                                    final char c27;
                                                    final char c33 = (char)(n70 = (c27 = c17));
                                                    final int n63;
                                                    final int n71 = length3 = (n63 = 3);
                                                    if (r) {
                                                        break Label_3063;
                                                    }
                                                    if (r) {
                                                        break Label_3047;
                                                    }
                                                    if (c33 != n71) {
                                                        final char c34 = (char)(n70 = c17);
                                                        final int n72 = length3 = 15;
                                                        if (r) {
                                                            break Label_3047;
                                                        }
                                                        if (c34 != n72) {
                                                            final char c35 = (char)(n70 = c17);
                                                            final int n73 = length3 = 2;
                                                            if (r) {
                                                                break Label_3047;
                                                            }
                                                            if (c35 != n73) {
                                                                final char c36 = (char)(n70 = c17);
                                                                final int n74 = length3 = 31;
                                                                if (r) {
                                                                    break Label_3047;
                                                                }
                                                                if (c36 != n74) {
                                                                    final char c37 = (char)(n70 = c17);
                                                                    final int n75 = length3 = 22;
                                                                    if (r) {
                                                                        break Label_3047;
                                                                    }
                                                                    if (c37 != n75) {
                                                                        int d8;
                                                                        final int n76 = d8 = (this.d ? 1 : 0);
                                                                        if (!r) {
                                                                            final int n77;
                                                                            final int width3;
                                                                            Label_2532: {
                                                                                if (n76 == 0) {
                                                                                    n77 = n22 + this.h + 1;
                                                                                    width3 = size.width;
                                                                                    if (r) {
                                                                                        break Label_2532;
                                                                                    }
                                                                                    if (n77 >= width3) {
                                                                                        final int n78 = n24 - fontMetrics.getAscent() + this.c;
                                                                                        Label_2524: {
                                                                                            if (!r) {
                                                                                                Label_2523: {
                                                                                                    if (n78 >= 0) {
                                                                                                        final int n79 = n24 - fontMetrics.getAscent();
                                                                                                        final int height4 = size.height;
                                                                                                        if (r) {
                                                                                                            break Label_2523;
                                                                                                        }
                                                                                                        if (n79 <= height4) {
                                                                                                            final int length4 = sb.length();
                                                                                                            if (r) {
                                                                                                                break Label_2524;
                                                                                                            }
                                                                                                            if (length4 > 0) {
                                                                                                                int s4;
                                                                                                                final int n80 = s4 = (this.s ? 1 : 0);
                                                                                                                Label_2490: {
                                                                                                                    Label_2476: {
                                                                                                                        if (!r) {
                                                                                                                            if (n80 == 0) {
                                                                                                                                break Label_2476;
                                                                                                                            }
                                                                                                                            final int n81;
                                                                                                                            s4 = (n81 = n2 + n30);
                                                                                                                        }
                                                                                                                        final int p = this.p;
                                                                                                                        int n83 = 0;
                                                                                                                        int n82 = 0;
                                                                                                                        Label_2364: {
                                                                                                                            if (!r) {
                                                                                                                                if (n80 < p) {
                                                                                                                                    break Label_2476;
                                                                                                                                }
                                                                                                                                n82 = (s4 = (n83 = n2 + n30));
                                                                                                                                if (r) {
                                                                                                                                    break Label_2364;
                                                                                                                                }
                                                                                                                                final int q = this.q;
                                                                                                                            }
                                                                                                                            if (s4 > p) {
                                                                                                                                break Label_2476;
                                                                                                                            }
                                                                                                                            n83 = (n82 = n3);
                                                                                                                        }
                                                                                                                        Label_2394: {
                                                                                                                            if (!r) {
                                                                                                                                if (n82 != 0) {
                                                                                                                                    break Label_2394;
                                                                                                                                }
                                                                                                                                graphics2.drawString(sb.toString(), n23, n24);
                                                                                                                                n83 = 1;
                                                                                                                            }
                                                                                                                            n3 = n83;
                                                                                                                            if (!r) {
                                                                                                                                break Label_2490;
                                                                                                                            }
                                                                                                                        }
                                                                                                                        graphics2.setColor(this.a.h.darker());
                                                                                                                        graphics2.fillRect(n23, n, this.h, this.c);
                                                                                                                        graphics2.setColor(this.a.f);
                                                                                                                        graphics2.drawString(sb.toString(), n23, n24);
                                                                                                                        this.N.append(sb.toString());
                                                                                                                        graphics2.setColor(f);
                                                                                                                        if (!r) {
                                                                                                                            break Label_2490;
                                                                                                                        }
                                                                                                                    }
                                                                                                                    graphics2.drawString(sb.toString(), n23, n24);
                                                                                                                }
                                                                                                                sb.setLength(0);
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                    n22 = (n23 = this.f + 2);
                                                                                                    final int n84;
                                                                                                    n21 = (n84 = n21 + this.c);
                                                                                                    final int k = this.K;
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                        n24 = n78;
                                                                                    }
                                                                                }
                                                                                final int l = this.K;
                                                                            }
                                                                            d8 = n77 - width3;
                                                                        }
                                                                        final int n85 = d8;
                                                                        n = n85 - fontMetrics.getAscent();
                                                                        final int n86 = n + this.c;
                                                                        if (!r) {
                                                                            Label_3031: {
                                                                                if (n86 >= 0) {
                                                                                    final int n87 = n;
                                                                                    final int height5 = size.height;
                                                                                    if (r) {
                                                                                        break Label_3031;
                                                                                    }
                                                                                    if (n87 <= height5) {
                                                                                        int s5;
                                                                                        final int n88 = s5 = (this.s ? 1 : 0);
                                                                                        Label_3017: {
                                                                                            Label_2764: {
                                                                                                if (!r) {
                                                                                                    if (n88 == 0) {
                                                                                                        break Label_2764;
                                                                                                    }
                                                                                                    final int n89;
                                                                                                    s5 = (n89 = n2 + n30);
                                                                                                }
                                                                                                final int p2 = this.p;
                                                                                                int n90 = 0;
                                                                                                int length5 = 0;
                                                                                                Label_2622: {
                                                                                                    if (!r) {
                                                                                                        if (n88 < p2) {
                                                                                                            break Label_2764;
                                                                                                        }
                                                                                                        length5 = (s5 = (n90 = n2 + n30));
                                                                                                        if (r) {
                                                                                                            break Label_2622;
                                                                                                        }
                                                                                                        final int q2 = this.q;
                                                                                                    }
                                                                                                    if (s5 > p2) {
                                                                                                        break Label_2764;
                                                                                                    }
                                                                                                    n90 = (length5 = sb.length());
                                                                                                }
                                                                                                if (!r) {
                                                                                                    if (length5 > 0) {
                                                                                                        final int n91 = n3;
                                                                                                        Label_2735: {
                                                                                                            if ((!r && n91 != 0) || n91 == 1) {
                                                                                                                graphics2.setColor(this.a.h.darker());
                                                                                                                graphics2.fillRect(n23, n, this.h, this.c);
                                                                                                                graphics2.setColor(this.a.f);
                                                                                                                graphics2.drawString(sb.toString(), n23, n24);
                                                                                                                this.N.append(sb.toString());
                                                                                                                if (!r) {
                                                                                                                    break Label_2735;
                                                                                                                }
                                                                                                            }
                                                                                                            graphics2.drawString(sb.toString(), n23, n24);
                                                                                                        }
                                                                                                        n3 = 1;
                                                                                                    }
                                                                                                    sb.setLength(0);
                                                                                                    n23 = n22;
                                                                                                    n90 = n85;
                                                                                                }
                                                                                                n24 = n90;
                                                                                                graphics2.setColor(f);
                                                                                                if (!r) {
                                                                                                    break Label_3017;
                                                                                                }
                                                                                            }
                                                                                            final Color color3 = color2;
                                                                                            final Color white = Color.white;
                                                                                            Label_2835: {
                                                                                                if (r || color3 != white) {
                                                                                                    if (color3 != white) {
                                                                                                        graphics2.setColor(color2);
                                                                                                        graphics2.fillRect(n22, n, this.h, this.c);
                                                                                                        graphics2.setColor(f);
                                                                                                        if (!r) {
                                                                                                            break Label_2835;
                                                                                                        }
                                                                                                    }
                                                                                                    graphics2.fillRect(n22, n, this.h, this.c);
                                                                                                }
                                                                                            }
                                                                                            Label_2976: {
                                                                                                if (d != font3) {
                                                                                                    if (sb.length() > 0) {
                                                                                                        graphics2.drawString(sb.toString(), n23, n24);
                                                                                                    }
                                                                                                    final Color color4 = color;
                                                                                                    Label_2887: {
                                                                                                        if (!r) {
                                                                                                            if (color4 == f) {
                                                                                                                break Label_2887;
                                                                                                            }
                                                                                                            graphics2.setColor(color);
                                                                                                        }
                                                                                                        f = color4;
                                                                                                    }
                                                                                                    sb.setLength(0);
                                                                                                    n23 = n22;
                                                                                                    n24 = n85;
                                                                                                    d = font3;
                                                                                                    graphics2.setFont(d);
                                                                                                    if (!r) {
                                                                                                        break Label_2976;
                                                                                                    }
                                                                                                }
                                                                                                if (color != f) {
                                                                                                    final int length6 = sb.length();
                                                                                                    if (!r) {
                                                                                                        if (length6 > 0) {
                                                                                                            graphics2.drawString(sb.toString(), n23, n24);
                                                                                                        }
                                                                                                        sb.setLength(0);
                                                                                                        n23 = n22;
                                                                                                    }
                                                                                                    n24 = length6;
                                                                                                    graphics2.setColor(color);
                                                                                                    f = color;
                                                                                                }
                                                                                            }
                                                                                            if (b6) {
                                                                                                graphics2.drawLine(n22, n85 + fontMetrics.getDescent() - 3, n22 + this.h - 1, n85 + fontMetrics.getDescent() - 3);
                                                                                            }
                                                                                        }
                                                                                        sb.append(c17);
                                                                                    }
                                                                                }
                                                                                final int h = this.h;
                                                                            }
                                                                        }
                                                                        n22 = n86;
                                                                        ++n27;
                                                                        ++n30;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            n70 = n30;
                                            length3 = s2.length();
                                        }
                                        if (n70 < length3) {
                                            final int n92 = n;
                                            final int height6 = size.height;
                                            if (!r) {
                                                if (n92 <= height6) {
                                                    continue;
                                                }
                                            }
                                        }
                                        break;
                                    }
                                    n2 = n31 + n32;
                                    length7 = sb.length();
                                    if (r) {
                                        break Label_3286;
                                    }
                                    if (length7 > 0) {
                                        int s6;
                                        final int n93 = s6 = (this.s ? 1 : 0);
                                        Label_3258: {
                                            if (!r) {
                                                if (n93 == 0) {
                                                    break Label_3258;
                                                }
                                                final int n94;
                                                s6 = (n94 = n2);
                                            }
                                            final int p3 = this.p;
                                            int n96 = 0;
                                            int n95 = 0;
                                            Label_3125: {
                                                if (!r) {
                                                    if (n93 < p3) {
                                                        break Label_3258;
                                                    }
                                                    n95 = (s6 = (n96 = n2));
                                                    if (r) {
                                                        break Label_3125;
                                                    }
                                                    final int q3 = this.q;
                                                }
                                                if (s6 > p3) {
                                                    break Label_3258;
                                                }
                                                n96 = (n95 = n3);
                                            }
                                            Label_3250: {
                                                Label_3236: {
                                                    Label_3139: {
                                                        if (!r) {
                                                            if (n95 != 0) {
                                                                break Label_3139;
                                                            }
                                                            n96 = n27;
                                                        }
                                                        if (n96 != 1) {
                                                            break Label_3236;
                                                        }
                                                    }
                                                    graphics2.setColor(this.a.h);
                                                    graphics2.fillRect(n23, n, this.h, this.c);
                                                    graphics2.setColor(this.a.f);
                                                    graphics2.drawString(sb.toString(), n23, n24);
                                                    this.N.append(String.valueOf(sb.toString()) + "\n");
                                                    graphics2.setColor(f);
                                                    if (!r) {
                                                        break Label_3250;
                                                    }
                                                }
                                                graphics2.drawString(sb.toString(), n23, n24);
                                            }
                                            n3 = 1;
                                            if (!r) {
                                                break Label_3272;
                                            }
                                        }
                                        graphics2.drawString(sb.toString(), n23, n24);
                                    }
                                }
                                c3 = c3.c;
                                final int n97 = n21 + this.c;
                            }
                            n21 = length7;
                        }
                        if (c3 != null) {
                            n98 = (length8 = n);
                            if (r || r) {
                                break Label_3315;
                            }
                            if (n98 <= size.height) {
                                continue;
                            }
                        }
                        break;
                    }
                    final boolean t;
                    length8 = ((t = this.t) ? 1 : 0);
                }
                e e4 = null;
                Label_3360: {
                    if (!r) {
                        if (n98 != 0) {
                            this.a(graphics2);
                        }
                        graphics.drawImage(image, 0, 0, this);
                        graphics2.dispose();
                        e4 = this;
                        if (r) {
                            break Label_3360;
                        }
                        length8 = this.N.length();
                    }
                    if (length8 <= 0) {
                        return;
                    }
                    e4 = this;
                }
                e4.e = this.N.toString();
                final StringSelection stringSelection = new StringSelection(this.N.toString());
                this.a.nc.setContents(stringSelection, stringSelection);
                this.N.setLength(0);
                return;
            }
            if (lb == null) {
                goto Label_1001;
            }
            continue;
        }
    }
    
    protected Color b(final int n) {
        int n2 = n;
        final int n3 = -1;
        if (!d.r) {
            if (n <= n3) {
                return Color.white;
            }
            n2 = n;
        }
        if (n2 < n3) {
            return this.u[n];
        }
        return Color.white;
    }
    
    public void h(final String h) {
        this.H = h;
        this.I = true;
    }
    
    public void setBackground(final Color background) {
        super.setBackground(background);
        this.repaint();
    }
    
    public void setFont(final Font font) {
        super.setFont(font);
        this.D = font;
        this.f();
        this.repaint();
    }
    
    public void setForeground(final Color foreground) {
        super.setForeground(foreground);
        this.repaint();
    }
    
    public void a(final boolean o) {
        this.O = o;
    }
    
    public void i(final String s) {
        this.b = 1;
        this.k.a();
        this.k.a(new mb(this, s));
        this.f();
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    static {
        final String[] p = new String[13];
        final int n = 0;
        final char[] charArray = "Yk".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'c';
                    break;
                }
                case 1: {
                    c2 = '\u001b';
                    break;
                }
                case 2: {
                    c2 = '\u001e';
                    break;
                }
                case 3: {
                    c2 = '\u0018';
                    break;
                }
                default: {
                    c2 = 't';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        p[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "Yf".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'c';
                    break;
                }
                case 1: {
                    c4 = '\u001b';
                    break;
                }
                case 2: {
                    c4 = '\u001e';
                    break;
                }
                case 3: {
                    c4 = '\u0018';
                    break;
                }
                default: {
                    c4 = 't';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        p[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "Y3".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = 'c';
                    break;
                }
                case 1: {
                    c6 = '\u001b';
                    break;
                }
                case 2: {
                    c6 = '\u001e';
                    break;
                }
                case 3: {
                    c6 = '\u0018';
                    break;
                }
                default: {
                    c6 = 't';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        p[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "Ye".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = 'c';
                    break;
                }
                case 1: {
                    c8 = '\u001b';
                    break;
                }
                case 2: {
                    c8 = '\u001e';
                    break;
                }
                case 3: {
                    c8 = '\u0018';
                    break;
                }
                default: {
                    c8 = 't';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        p[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "Y2".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = 'c';
                    break;
                }
                case 1: {
                    c10 = '\u001b';
                    break;
                }
                case 2: {
                    c10 = '\u001e';
                    break;
                }
                case 3: {
                    c10 = '\u0018';
                    break;
                }
                default: {
                    c10 = 't';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        p[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "X2".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = 'c';
                    break;
                }
                case 1: {
                    c12 = '\u001b';
                    break;
                }
                case 2: {
                    c12 = '\u001e';
                    break;
                }
                case 3: {
                    c12 = '\u0018';
                    break;
                }
                default: {
                    c12 = 't';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        p[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "Y1".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = 'c';
                    break;
                }
                case 1: {
                    c14 = '\u001b';
                    break;
                }
                case 2: {
                    c14 = '\u001e';
                    break;
                }
                case 3: {
                    c14 = '\u0018';
                    break;
                }
                default: {
                    c14 = 't';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        p[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "Y4".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = 'c';
                    break;
                }
                case 1: {
                    c16 = '\u001b';
                    break;
                }
                case 2: {
                    c16 = '\u001e';
                    break;
                }
                case 3: {
                    c16 = '\u0018';
                    break;
                }
                default: {
                    c16 = 't';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        p[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "Y$".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = 'c';
                    break;
                }
                case 1: {
                    c18 = '\u001b';
                    break;
                }
                case 2: {
                    c18 = '\u001e';
                    break;
                }
                case 3: {
                    c18 = '\u0018';
                    break;
                }
                default: {
                    c18 = 't';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        p[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = "Y_".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = 'c';
                    break;
                }
                case 1: {
                    c20 = '\u001b';
                    break;
                }
                case 2: {
                    c20 = '\u001e';
                    break;
                }
                case 3: {
                    c20 = '\u0018';
                    break;
                }
                default: {
                    c20 = 't';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        p[n28] = new String(charArray10).intern();
        final int n31 = 10;
        final char[] charArray11 = "\u000bojhNL4".toCharArray();
        final int length7 = charArray11.length;
        for (int n32 = 0; length7 > n32; ++n32) {
            final int n33 = n32;
            final char c21 = charArray11[n33];
            char c22 = '\0';
            switch (n32 % 5) {
                case 0: {
                    c22 = 'c';
                    break;
                }
                case 1: {
                    c22 = '\u001b';
                    break;
                }
                case 2: {
                    c22 = '\u001e';
                    break;
                }
                case 3: {
                    c22 = '\u0018';
                    break;
                }
                default: {
                    c22 = 't';
                    break;
                }
            }
            charArray11[n33] = (char)(c21 ^ c22);
        }
        p[n31] = new String(charArray11).intern();
        final int n34 = 11;
        final char[] charArray12 = "\u0014li6".toCharArray();
        final int length8 = charArray12.length;
        for (int n35 = 0; length8 > n35; ++n35) {
            final int n36 = n35;
            final char c23 = charArray12[n36];
            char c24 = '\0';
            switch (n35 % 5) {
                case 0: {
                    c24 = 'c';
                    break;
                }
                case 1: {
                    c24 = '\u001b';
                    break;
                }
                case 2: {
                    c24 = '\u001e';
                    break;
                }
                case 3: {
                    c24 = '\u0018';
                    break;
                }
                default: {
                    c24 = 't';
                    break;
                }
            }
            charArray12[n36] = (char)(c23 ^ c24);
        }
        p[n34] = new String(charArray12).intern();
        final int n37 = 12;
        final char[] charArray13 = "<yry\u001a\b".toCharArray();
        final int length9 = charArray13.length;
        for (int n38 = 0; length9 > n38; ++n38) {
            final int n39 = n38;
            final char c25 = charArray13[n39];
            char c26 = '\0';
            switch (n38 % 5) {
                case 0: {
                    c26 = 'c';
                    break;
                }
                case 1: {
                    c26 = '\u001b';
                    break;
                }
                case 2: {
                    c26 = '\u001e';
                    break;
                }
                case 3: {
                    c26 = '\u0018';
                    break;
                }
                default: {
                    c26 = 't';
                    break;
                }
            }
            charArray13[n39] = (char)(c25 ^ c26);
        }
        p[n37] = new String(charArray13).intern();
        e.P = p;
    }
}
