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

class d extends Canvas implements MouseListener, MouseMotionListener, ComponentListener
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
    protected jb k;
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
    eb E;
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
    
    public d(final esChat a, final int j, final boolean d) {
        int m = fb.m;
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
        this.k = new jb(a);
        this.l = new Vector();
        this.m = new Vector();
        this.n = new Vector();
        this.p = -1;
        this.r = a.e;
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
        a.Lb = a.x;
        this.j = j;
        this.d = d;
        this.M = a.q;
        this.setBackground(a.g);
        this.setForeground(a.e);
        d d2 = this;
        if (m == 0) {
            if (this.d) {
                this.setBackground(a.g);
                this.setForeground(a.e);
            }
            this.setFont(new Font(a.Y, 0, a.bb));
            d2 = this;
        }
        d2.getFontMetrics(this.D);
        this.h = 0;
        this.addKeyListener(new g(this));
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addComponentListener(this);
        if (esChat.ic) {
            fb.m = ++m;
        }
    }
    
    public int a(final String s, final String s2) {
        final int m = fb.m;
        final int length = s.length();
        final int length2 = s2.length();
        final char c = (char)length;
        char c2;
        if (m == 0 && c < length2) {
            c2 = (char)length;
            if (m != 0) {
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
                    if (m == 0) {
                        break Label_0102;
                    }
                    this.a(s.charAt(c3));
                    final char c5;
                    final char c4 = c5;
                    final char a = this.a(s2.charAt(c3));
                    final char c6 = c4;
                    final char c7 = a;
                    if (m == 0) {
                        if (c6 < c7) {
                            return 1;
                        }
                        final char c8;
                        final int n = c8 = c4;
                        if (m != 0) {
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
            if (m == 0) {
                final char c10 = (char)length2;
                if (m == 0) {
                    if (c5 < c10) {
                        return 1;
                    }
                    final int n2 = c9 = (char)length;
                    if (m != 0) {
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
        final int m = fb.m;
        int n = 0;
        kb kb = this.k.b;
        d d = null;
        Label_0092: {
            while (true) {
                Label_0028: {
                    if (m == 0) {
                        break Label_0028;
                    }
                    kb = kb.c;
                    ++n;
                }
                if (kb != null) {
                    d = this;
                    if (m != 0 || m != 0) {
                        break Label_0092;
                    }
                    if (this.a(s, ((lb)kb.b).b) < 0) {
                        continue;
                    }
                }
                break;
            }
            this.k.a(new lb(this, s), n);
            ++this.b;
            this.f();
            d = this;
        }
        d.repaint();
    }
    
    public void b(final String s) {
        final int m = fb.m;
        int n = 0;
        kb kb = this.k.b;
        d d = null;
        d d2 = null;
        Label_0078: {
            while (true) {
                Label_0028: {
                    if (m == 0) {
                        break Label_0028;
                    }
                    kb = kb.c;
                    ++n;
                }
                if (kb != null) {
                    d = this;
                    d2 = this;
                    if (m != 0 || m != 0) {
                        break Label_0078;
                    }
                    if (this.a(s, ((lb)kb.b).b) < 0) {
                        continue;
                    }
                }
                break;
            }
            this.k.a(new lb(this, s), n);
            d = this;
            d2 = this;
        }
        d.b = d2.b + 1;
    }
    
    public void c(final String s) {
        final int m = fb.m;
        int n = 0;
        kb kb = this.k.b;
        while (true) {
        Label_0083:
            while (true) {
                Label_0079: {
                    if (m == 0) {
                        break Label_0079;
                    }
                    final d d = this;
                    final int int1 = Integer.parseInt(d.a.e(s, " "));
                    final int int2 = Integer.parseInt(this.a.e(((lb)kb.b).b, " "));
                    if (m != 0) {
                        return;
                    }
                    if (int1 > int2) {
                        break Label_0083;
                    }
                    kb = kb.c;
                    ++n;
                }
                if (kb != null) {
                    continue;
                }
                break;
            }
            this.k.a(new lb(this, s), n);
            ++this.b;
            this.f();
            final d d = this;
            if (m != 0) {
                continue;
            }
            break;
        }
        this.repaint();
    }
    
    public void d(final String s) {
        final int m = fb.m;
        final int b = this.b;
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n");
        final int b2 = this.b + stringTokenizer.countTokens();
        int n = b;
        while (true) {
            Label_0072: {
                if (m == 0) {
                    break Label_0072;
                }
                final lb lb = new lb(this, stringTokenizer.nextToken());
                this.a(lb);
                this.k.a(lb);
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
        final int m = fb.m;
        kb b = this.k.b;
        while (true) {
            Label_0070: {
                if (m == 0) {
                    break Label_0070;
                }
                final kb kb = b;
                if (m == 0) {
                    if (((lb)kb.b).b.equalsIgnoreCase(s)) {
                        this.k.a(b);
                        --this.b;
                        this.f();
                        this.repaint();
                        return;
                    }
                    final kb c = b.c;
                }
                b = kb;
            }
            if (b == null) {
                return;
            }
            continue;
        }
    }
    
    protected void a(final int n, final int n2, final int n3, final int n4) {
        final int i = fb.m;
        final FontMetrics fontMetrics = this.getFontMetrics(this.D);
        this.p = -1;
        this.q = -1;
        int q = 0;
        final Dimension size = this.getSize();
        kb kb = this.k.b;
        while (true) {
            while (true) {
                Label_1087: {
                    if (i == 0) {
                        break Label_1087;
                    }
                    final Object b = kb.b;
                    final String b2 = ((lb)b).b;
                    final int n5 = ((lb)kb.b).d + size.height - this.c + this.o - this.K - this.c;
                    int c = n2;
                    Label_1080: {
                        if (i == 0) {
                            if (n2 > n5) {
                                q += b2.length();
                                if (i == 0) {
                                    break Label_1080;
                                }
                            }
                            c = ((lb)kb.b).c;
                        }
                        int n6 = c;
                        final int n7 = n6 + size.height - this.c + this.o - this.K - this.c;
                        int n8 = n4;
                        if (i == 0) {
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
                                    if (i == 0) {
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
                                                    if (i == 0) {
                                                        if (n15 != n16) {
                                                            final char c5 = (char)(n11 = (c4 = c2));
                                                            final int n17 = n12 = (n14 = 2);
                                                            if (i != 0) {
                                                                break Label_0291;
                                                            }
                                                            if (c5 != n17) {
                                                                final char c6 = (char)(n11 = (c4 = c2));
                                                                final int n18 = n12 = (n14 = 31);
                                                                if (i != 0) {
                                                                    break Label_0291;
                                                                }
                                                                if (c6 != n18) {
                                                                    final char c7 = (char)(n11 = (c4 = c2));
                                                                    final int n19 = n12 = (n14 = 22);
                                                                    if (i != 0) {
                                                                        break Label_0291;
                                                                    }
                                                                    if (c7 != n19) {
                                                                        final char c9;
                                                                        c8 = (c9 = c2);
                                                                        final int n21;
                                                                        n20 = (n21 = 15);
                                                                        if (i != 0) {
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
                                                if (i != 0) {
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
                                            if (i == 0) {
                                                Label_0617: {
                                                    if (c8 == n20) {
                                                        n22 = n10;
                                                        n23 = b2.length() - 1;
                                                        if (i != 0) {
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
                                                            if (i == 0) {
                                                                Label_0455: {
                                                                    if (c11 > n24) {
                                                                        final char c12 = c9 = c10;
                                                                        final int n26;
                                                                        final int n25 = n26 = (n21 = 10);
                                                                        if (i != 0) {
                                                                            break Label_0464;
                                                                        }
                                                                        if (c12 < n25) {
                                                                            final int n27 = b2.length() - n10;
                                                                            final int n28 = 2;
                                                                            if (i != 0) {
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
                                                                                if (i == 0) {
                                                                                    if (c14 <= n30) {
                                                                                        break Label_0452;
                                                                                    }
                                                                                    final char c15;
                                                                                    n29 = (c15 = (c4 = c13));
                                                                                    final int n32;
                                                                                    n31 = (n32 = (n14 = 10));
                                                                                }
                                                                                if (i == 0) {
                                                                                    if (c14 >= n30) {
                                                                                        break Label_0452;
                                                                                    }
                                                                                    n29 = b2.length() - n10;
                                                                                    n31 = 2;
                                                                                }
                                                                                if (i != 0) {
                                                                                    break Label_1073;
                                                                                }
                                                                                if (n29 <= n31) {
                                                                                    break Label_1073;
                                                                                }
                                                                                n10 += 2;
                                                                                if (i == 0) {
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
                                                        if (i != 0) {
                                                            break Label_0640;
                                                        }
                                                        if (c11 == n24) {
                                                            n33 = n10;
                                                            n34 = b2.length() - 1;
                                                            if (i != 0) {
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
                                                                    if (i == 0) {
                                                                        if (c17 <= n36) {
                                                                            break Label_0614;
                                                                        }
                                                                        final char c18;
                                                                        n35 = (c18 = (c4 = c16));
                                                                        final int n38;
                                                                        n37 = (n38 = (n14 = 10));
                                                                    }
                                                                    if (i == 0) {
                                                                        if (c17 >= n36) {
                                                                            break Label_0614;
                                                                        }
                                                                        n35 = b2.length() - n10;
                                                                        n37 = 2;
                                                                    }
                                                                    if (i != 0) {
                                                                        break Label_1073;
                                                                    }
                                                                    if (n35 < n37) {
                                                                        break Label_1073;
                                                                    }
                                                                    final char c19 = (char)(b2.charAt(n10 + 1) - '0');
                                                                    if (i == 0) {
                                                                        Label_0606: {
                                                                            if (c19 > -1) {
                                                                                int n39;
                                                                                final char c20 = (char)(n39 = (c4 = c19));
                                                                                int n41;
                                                                                final int n40 = n41 = (n14 = 10);
                                                                                if (i == 0) {
                                                                                    if (c20 >= n40) {
                                                                                        break Label_0606;
                                                                                    }
                                                                                    n39 = b2.length() - n10;
                                                                                    n41 = 2;
                                                                                }
                                                                                if (i != 0) {
                                                                                    break Label_1073;
                                                                                }
                                                                                if (n39 <= n41) {
                                                                                    break Label_1073;
                                                                                }
                                                                                n10 += 2;
                                                                                if (i == 0) {
                                                                                    break Label_0617;
                                                                                }
                                                                            }
                                                                        }
                                                                        ++n10;
                                                                    }
                                                                    if (i == 0) {
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
                                    } while (i != 0);
                                    if (i != 0) {
                                        break Label_1070;
                                    }
                                    if (n15 != n16) {
                                        final char c21 = (char)(n42 = c2);
                                        final int n43 = length = 15;
                                        if (i != 0) {
                                            break Label_1070;
                                        }
                                        if (c21 != n43) {
                                            final char c22 = (char)(n42 = c2);
                                            final int n44 = length = 2;
                                            if (i != 0) {
                                                break Label_1070;
                                            }
                                            if (c22 != n44) {
                                                final char c23 = (char)(n42 = c2);
                                                final int n45 = length = 31;
                                                if (i != 0) {
                                                    break Label_1070;
                                                }
                                                if (c23 != n45) {
                                                    final char c24 = (char)(n42 = c2);
                                                    final int n46 = length = 22;
                                                    if (i != 0) {
                                                        break Label_1070;
                                                    }
                                                    if (c24 != n46) {
                                                        final int n47 = n9 + this.h + 1;
                                                        final int width = size.width;
                                                        if (i == 0) {
                                                            if (n47 >= width) {
                                                                final int n49;
                                                                final int n48 = n49 = n6 + size.height - this.c + this.o - this.K - this.c;
                                                                final int p4;
                                                                Label_0821: {
                                                                    if (i == 0) {
                                                                        if (n2 > n49) {
                                                                            final int n50 = n48 + this.c;
                                                                            if (i != 0) {
                                                                                break Label_0821;
                                                                            }
                                                                            if (n2 < n50) {
                                                                                p4 = this.p;
                                                                                final int n51 = -1;
                                                                                if (i != 0) {
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
                                                        if (i == 0) {
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
                                                            if (i == 0) {
                                                                Label_0958: {
                                                                    if (n54 > n56) {
                                                                        int h;
                                                                        n57 = (h = n53 + this.c);
                                                                        if (i == 0) {
                                                                            if (n2 < n57) {
                                                                                n58 = (p5 = this.p);
                                                                                n59 = -1;
                                                                                if (i == 0) {
                                                                                    if (n58 == n59) {
                                                                                        int n60 = n;
                                                                                        final int n62;
                                                                                        final int n61 = n62 = (h = this.f + 2);
                                                                                        Label_0949: {
                                                                                            if (i == 0) {
                                                                                                if (n <= n61) {
                                                                                                    break Label_0949;
                                                                                                }
                                                                                                n60 = n;
                                                                                            }
                                                                                            if (i != 0) {
                                                                                                break Label_0962;
                                                                                            }
                                                                                            if (n60 < n61) {
                                                                                                break Label_0958;
                                                                                            }
                                                                                            final int n63 = n9 + this.h;
                                                                                            if (i != 0) {
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
                                                            if (i == 0) {
                                                                Label_1051: {
                                                                    if (n58 > n57) {
                                                                        final int n64 = n53 + this.c;
                                                                        if (i != 0) {
                                                                            break Label_1057;
                                                                        }
                                                                        if (n4 < n64) {
                                                                            p6 = this.p;
                                                                            final int n65 = -1;
                                                                            if (i != 0) {
                                                                                break Label_1057;
                                                                            }
                                                                            if (p6 != n65) {
                                                                                int n66 = n3;
                                                                                final int h;
                                                                                final int n67 = h = this.f + 2;
                                                                                Label_1042: {
                                                                                    if (i == 0) {
                                                                                        if (n3 <= n67) {
                                                                                            break Label_1042;
                                                                                        }
                                                                                        n66 = n3;
                                                                                    }
                                                                                    if (i != 0) {
                                                                                        break Label_1057;
                                                                                    }
                                                                                    if (n66 < n67) {
                                                                                        break Label_1051;
                                                                                    }
                                                                                    final int n68 = n9 + this.h;
                                                                                    if (i != 0) {
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
                    kb = kb.c;
                }
                if (kb != null) {
                    continue;
                }
                break;
            }
            final Object b = this;
            if (i == 0) {
                final int p7 = this.p;
                final int n69 = -1;
                d d = null;
                Label_1124: {
                    if (i == 0) {
                        if (p7 == n69) {
                            return;
                        }
                        d = this;
                        if (i != 0) {
                            break Label_1124;
                        }
                        final int q2 = this.q;
                    }
                    if (p7 != n69) {
                        return;
                    }
                    d = this;
                }
                d.q = q;
                return;
            }
            continue;
        }
    }
    
    public char a(final char c) {
        final int m = fb.m;
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
        if (m == 0) {
            if (c2 == c9) {
                return '\0';
            }
            final char c11;
            c3 = (c11 = (c4 = (c5 = (c6 = (c7 = (c8 = lowerCase))))));
            final char c12;
            c10 = (c12 = (char)(n = (n2 = (n3 = (n4 = (n5 = 64))))));
        }
        if (m == 0) {
            if (c2 == c9) {
                return '\u0001';
            }
            c4 = (c3 = (c5 = (c6 = (c7 = (c8 = lowerCase)))));
            n = (c10 = (char)(n2 = (n3 = (n4 = (n5 = 37)))));
        }
        if (m == 0) {
            if (c3 == c10) {
                return '\u0002';
            }
            c5 = (c4 = (c6 = (c7 = (c8 = lowerCase))));
            n2 = (n = (n3 = (n4 = (n5 = 43))));
        }
        if (m == 0) {
            if (c4 == n) {
                return '\u0003';
            }
            c6 = (c5 = (c7 = (c8 = lowerCase)));
            n3 = (n2 = (n4 = (n5 = 44)));
        }
        if (m == 0) {
            if (c5 == n2) {
                return '\u0004';
            }
            c6 = (c8 = lowerCase);
            n3 = (n5 = 97);
        }
        final char c13;
        final int n6;
        if (m == 0) {
            if (c6 >= n3) {
                c13 = (c8 = lowerCase);
                n6 = (n5 = 122);
                if (m == 0) {
                    if (c13 <= n6) {
                        return (char)(lowerCase + ' ');
                    }
                }
            }
        }
        final char c14;
        final char c15;
        if (m == 0) {
            if (c13 >= n6) {
                c14 = lowerCase;
                c15 = '9';
                if (m == 0) {
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
        this.a.k = true;
        this.a.l = true;
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    protected void a(final Graphics graphics) {
        final int m = fb.m;
        final Dimension size = this.getSize();
        int n2;
        final int n = n2 = (this.d ? 1 : 0);
        int height = 0;
        Label_0037: {
            if (m == 0) {
                if (n == 0) {
                    height = size.height;
                    if (m == 0) {
                        break Label_0037;
                    }
                }
                n2 = this.c;
            }
            height = n2;
        }
        int n3 = 0;
        while (true) {
            Label_0178: {
                if (m == 0) {
                    break Label_0178;
                }
                final lb lb = this.n.elementAt(n3);
                final int n4 = lb.d + height - this.c;
                final int n5 = lb.c - 2;
                final int n6 = n4 - this.K;
                Label_0175: {
                    if (m == 0) {
                        if (n6 < 0) {
                            break Label_0175;
                        }
                        final int n7 = n4 - this.K;
                    }
                    if (m == 0) {
                        if (n6 < size.height) {
                            graphics.drawImage(this.a.Lb, n5, n4 - this.K, n5 + 23, n4 - this.K + 17, lb.e * 23, 0, 23 + lb.e * 23, 17, this);
                        }
                    }
                }
                ++n3;
            }
            if (n3 >= this.n.size()) {
                return;
            }
            continue;
        }
    }
    
    public void a(final int k) {
        this.K = k;
        this.repaint();
    }
    
    public String c() {
        final int m = fb.m;
        String s = "";
        kb kb = this.k.b;
        while (true) {
            while (true) {
                Label_0057: {
                    if (m == 0) {
                        break Label_0057;
                    }
                    new StringBuffer(String.valueOf(s)).append(((lb)kb.b).b).append("\n").toString();
                    final String s2;
                    s = s2;
                    kb = kb.c;
                }
                if (kb != null) {
                    continue;
                }
                break;
            }
            final String s2 = s;
            if (m == 0) {
                return s2;
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
        final int m = fb.m;
        final Vector<String> vector = new Vector<String>();
        kb kb = this.k.b;
        while (true) {
            while (true) {
                Label_0043: {
                    if (m == 0) {
                        break Label_0043;
                    }
                    final Vector<String> vector2 = vector;
                    vector2.addElement(((lb)kb.b).b);
                    kb = kb.c;
                }
                if (kb != null) {
                    continue;
                }
                break;
            }
            Vector<String> vector2;
            final Vector<String> vector3 = vector2 = vector;
            if (m == 0) {
                return vector3;
            }
            continue;
        }
    }
    
    public Dimension getMinimumSize() {
        d d = this;
        if (fb.m == 0) {
            if (this.j == 0) {
                return super.getMinimumSize();
            }
            d = this;
        }
        final FontMetrics fontMetrics = d.getFontMetrics(new Font(this.a.Y, 0, this.a.bb));
        return new Dimension(fontMetrics.charWidth('a') * 5, fontMetrics.getHeight());
    }
    
    public Dimension getPreferredSize() {
        d d = this;
        if (fb.m == 0) {
            if (this.j == 0) {
                return super.getPreferredSize();
            }
            d = this;
        }
        final FontMetrics fontMetrics = d.getFontMetrics(new Font(this.a.Y, 0, this.a.bb));
        return new Dimension(fontMetrics.charWidth('a') * this.j, fontMetrics.getHeight());
    }
    
    public String e() {
        final int m = fb.m;
        kb b = this.k.b;
        while (true) {
            Label_0049: {
                if (m == 0) {
                    break Label_0049;
                }
                final kb kb = b;
                if (m == 0) {
                    if (((lb)kb.b).g) {
                        return ((lb)b.b).b;
                    }
                    final kb c = b.c;
                }
                b = kb;
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
        final int m = fb.m;
        kb b = this.k.b;
        while (true) {
            while (true) {
                Label_0044: {
                    if (m == 0) {
                        break Label_0044;
                    }
                    final kb kb = b;
                    Label_0043: {
                        if (m != 0) {
                            break Label_0043;
                        }
                        ((lb)kb.b).b.equalsIgnoreCase(s);
                        final boolean b2;
                        if (b2) {
                            return true;
                        }
                        final kb c = b.c;
                    }
                    b = kb;
                }
                if (b != null) {
                    continue;
                }
                break;
            }
            final boolean b2 = false;
            if (m == 0) {
                return b2;
            }
            continue;
        }
    }
    
    public void g(final String s) {
        this.G = this.a.b(String.valueOf(this.a.H) + "/" + s);
        this.F = true;
    }
    
    public void f() {
        final int m = fb.m;
        final FontMetrics fontMetrics = this.getFontMetrics(this.D);
        this.c = fontMetrics.getHeight();
        this.o = fontMetrics.getAscent();
        this.i = 0;
        this.l.setSize(0);
        this.m.setSize(0);
        kb kb = this.k.b;
        while (true) {
            Label_0078: {
                if (m == 0) {
                    break Label_0078;
                }
                this.a((lb)kb.b);
                kb = kb.c;
            }
            if (kb == null) {
                return;
            }
            continue;
        }
    }
    
    protected void a(final lb lb) {
        final int i = fb.m;
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
        final String b = lb.b;
        lb.c = this.i;
        int n7 = this.i + this.c;
        int n8 = this.f + 2;
        int n9 = 0;
        int length3 = 0;
        int n29 = 0;
        int n33 = 0;
        Label_1874: {
        Label_1870:
            while (true) {
                int n47 = 0;
                int length4 = 0;
                Label_1867: {
                    Label_1860: {
                        if (i == 0) {
                            break Label_1860;
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
                            if (i == 0) {
                                Label_0207: {
                                    if (n18 == n19) {
                                        n20 = (n10 = (n11 = (n12 = sb.length())));
                                        if (i != 0) {
                                            break Label_0207;
                                        }
                                        if (n20 > 0) {
                                            this.l.addElement(new lb(this, sb.toString(), n, n2 - this.c, 0));
                                            sb.setLength(0);
                                        }
                                    }
                                    final int n21;
                                    n10 = (n21 = (n11 = (n12 = sb3.length())));
                                }
                                n15 = (n13 = (n16 = (n17 = 1)));
                            }
                            if (i == 0) {
                                if (n20 > n13) {
                                    boolean b9;
                                    boolean equalsIgnoreCase;
                                    boolean b8;
                                    boolean b7;
                                    boolean b6;
                                    boolean b5;
                                    boolean b4;
                                    boolean b3;
                                    final boolean b2 = b3 = (b4 = (b5 = (b6 = (b7 = (b8 = (equalsIgnoreCase = (b9 = sb3.toString().substring(0, 2).equals(d.P[3]))))))));
                                    final StringBuffer sb4;
                                    Label_0796: {
                                        if (i == 0) {
                                            if (b2) {
                                                this.n.addElement(new lb(this, null, n5, n6 - this.c, 0));
                                                if (i == 0) {
                                                    break Label_0796;
                                                }
                                            }
                                            final boolean b10;
                                            b3 = (b10 = (b4 = (b5 = (b6 = (b7 = (b8 = (equalsIgnoreCase = (b9 = sb3.toString().substring(0, 2).equals(d.P[9])))))))));
                                        }
                                        if (i == 0) {
                                            if (b2) {
                                                this.n.addElement(new lb(this, null, n5, n6 - this.c, 5));
                                                if (i == 0) {
                                                    break Label_0796;
                                                }
                                            }
                                            b4 = (b3 = (b5 = (b6 = (b7 = (b8 = (equalsIgnoreCase = (b9 = sb3.toString().substring(0, 2).equals(d.P[6]))))))));
                                        }
                                        if (i == 0) {
                                            if (b3) {
                                                this.n.addElement(new lb(this, null, n5, n6 - this.c, 6));
                                                if (i == 0) {
                                                    break Label_0796;
                                                }
                                            }
                                            b5 = (b4 = (b6 = (b7 = (b8 = (equalsIgnoreCase = (b9 = sb3.toString().substring(0, 2).equals(d.P[12])))))));
                                        }
                                        if (i == 0) {
                                            if (b4) {
                                                this.n.addElement(new lb(this, null, n5, n6 - this.c, 7));
                                                if (i == 0) {
                                                    break Label_0796;
                                                }
                                            }
                                            b6 = (b5 = (b7 = (b8 = (equalsIgnoreCase = (b9 = sb3.toString().substring(0, 2).equals(d.P[8]))))));
                                        }
                                        if (i == 0) {
                                            if (b5) {
                                                this.n.addElement(new lb(this, null, n5, n6 - this.c, 8));
                                                if (i == 0) {
                                                    break Label_0796;
                                                }
                                            }
                                            b7 = (b6 = (b8 = (equalsIgnoreCase = (b9 = sb3.toString().substring(0, 2).substring(0, 2).equals(d.P[0])))));
                                        }
                                        if (i == 0) {
                                            if (b6) {
                                                this.n.addElement(new lb(this, null, n5, n6 - this.c, 9));
                                                if (i == 0) {
                                                    break Label_0796;
                                                }
                                            }
                                            b8 = (b7 = (equalsIgnoreCase = (b9 = sb3.toString().substring(0, 2).equals(d.P[1]))));
                                        }
                                        if (i == 0) {
                                            if (b7) {
                                                this.n.addElement(new lb(this, null, n5, n6 - this.c, 1));
                                                if (i == 0) {
                                                    break Label_0796;
                                                }
                                            }
                                            equalsIgnoreCase = (b8 = (b9 = sb3.toString().substring(0, 2).equals(d.P[5])));
                                        }
                                        if (i == 0) {
                                            if (b8) {
                                                this.n.addElement(new lb(this, null, n5, n6 - this.c, 2));
                                                if (i == 0) {
                                                    break Label_0796;
                                                }
                                            }
                                            b9 = (equalsIgnoreCase = sb3.toString().substring(0, 2).equalsIgnoreCase(d.P[11]));
                                        }
                                        if (i == 0) {
                                            if (equalsIgnoreCase) {
                                                this.n.addElement(new lb(this, null, n5, n6 - this.c, 3));
                                                if (i == 0) {
                                                    break Label_0796;
                                                }
                                            }
                                            sb4 = sb3;
                                            if (i != 0) {
                                                break Label_0796;
                                            }
                                            b9 = sb4.toString().substring(0, 2).equals(d.P[10]);
                                        }
                                        if (b9) {
                                            this.n.addElement(new lb(this, null, n5, n6 - this.c, 4));
                                        }
                                    }
                                    sb4.setLength(0);
                                }
                                n10 = (n12 = c);
                                n16 = (n15 = (n17 = 32));
                            }
                            final int n22;
                            if (i == 0) {
                                Label_0983: {
                                    if (n10 == n15) {
                                        n22 = (n12 = sb2.length());
                                        if (i != 0) {
                                            break Label_0983;
                                        }
                                        if (n22 > 0) {
                                            final int length = sb2.length();
                                            final int n23 = 4;
                                            final StringBuffer sb5;
                                            Label_0975: {
                                                if (i == 0) {
                                                    Label_0912: {
                                                        if (length > n23) {
                                                            final boolean equalsIgnoreCase2 = sb2.toString().substring(0, 4).equalsIgnoreCase(d.P[2]);
                                                            if (i != 0) {
                                                                break Label_0912;
                                                            }
                                                            if (equalsIgnoreCase2) {
                                                                this.m.addElement(new lb(this, sb2.toString(), n3, n4 - this.c, 0));
                                                                if (i == 0) {
                                                                    break Label_0975;
                                                                }
                                                            }
                                                        }
                                                        sb5 = sb2;
                                                        if (i != 0) {
                                                            break Label_0975;
                                                        }
                                                        sb5.length();
                                                    }
                                                }
                                                if (length > n23) {
                                                    final StringBuffer sb6 = sb2;
                                                    if (i == 0) {
                                                        if (sb6.toString().substring(0, 7).equalsIgnoreCase(d.P[7])) {
                                                            this.m.addElement(new lb(this, sb2.toString(), n3, n4 - this.c, 0));
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
                            Label_1067: {
                                Label_1059: {
                                    Label_1053: {
                                        if (i == 0) {
                                            if (n22 != n16) {
                                                final char c2 = (char)(n12 = c);
                                                final int n24 = n17 = 2;
                                                if (i != 0) {
                                                    break Label_1053;
                                                }
                                                if (c2 != n24) {
                                                    final char c3 = (char)(n12 = c);
                                                    final int n25 = n17 = 31;
                                                    if (i != 0) {
                                                        break Label_1053;
                                                    }
                                                    if (c3 != n25) {
                                                        final char c4 = (char)(n12 = c);
                                                        final int n26 = n17 = 22;
                                                        if (i != 0) {
                                                            break Label_1053;
                                                        }
                                                        if (c4 != n26) {
                                                            final char c6;
                                                            c5 = (c6 = c);
                                                            final int n28;
                                                            n27 = (n28 = 15);
                                                            if (i != 0) {
                                                                break Label_1067;
                                                            }
                                                            if (c5 != n27) {
                                                                break Label_1059;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            final int length2;
                                            n12 = (length2 = (length3 = n9));
                                            if (i != 0) {
                                                break Label_1874;
                                            }
                                            n17 = b.length() - 1;
                                        }
                                    }
                                    if (n12 >= n17) {
                                        break Label_1870;
                                    }
                                    ++n9;
                                }
                                b.charAt(n9);
                            }
                            final int n38;
                            final int n39;
                            Label_1396: {
                                if (i == 0) {
                                    Label_1374: {
                                        if (c5 == n27) {
                                            int length2;
                                            n29 = (length2 = (length3 = n9));
                                            if (i != 0) {
                                                break Label_1874;
                                            }
                                            if (n29 >= b.length() - 1) {
                                                break Label_1870;
                                            }
                                            ++n9;
                                            final char c7 = (char)(b.charAt(n9) - '0');
                                            char c6;
                                            final char c8 = c6 = c7;
                                            int n28;
                                            final int n30 = n28 = -1;
                                            Label_1221: {
                                                if (i == 0) {
                                                    Label_1212: {
                                                        if (c8 > n30) {
                                                            final char c9 = c6 = c7;
                                                            final int n32;
                                                            final int n31 = n32 = (n28 = 10);
                                                            if (i != 0) {
                                                                break Label_1221;
                                                            }
                                                            if (c9 < n31) {
                                                                n33 = (length3 = b.length() - n9);
                                                                if (i != 0) {
                                                                    break Label_1874;
                                                                }
                                                                if (n33 < 2) {
                                                                    break Label_1870;
                                                                }
                                                                final char c10 = (char)(b.charAt(n9 + 1) - '0');
                                                                int n34;
                                                                final char c11 = (char)(n34 = c10);
                                                                int n36;
                                                                final int n35 = n36 = -1;
                                                                Label_1209: {
                                                                    if (i == 0) {
                                                                        if (c11 <= n35) {
                                                                            break Label_1209;
                                                                        }
                                                                        final char c12;
                                                                        n34 = (c12 = c10);
                                                                        final int n37;
                                                                        n36 = (n37 = 10);
                                                                    }
                                                                    if (i == 0) {
                                                                        if (c11 >= n35) {
                                                                            break Label_1209;
                                                                        }
                                                                        n34 = (length2 = (length3 = b.length() - n9));
                                                                        if (i != 0) {
                                                                            break Label_1874;
                                                                        }
                                                                        n36 = 2;
                                                                    }
                                                                    if (n34 <= n36) {
                                                                        break Label_1870;
                                                                    }
                                                                    n9 += 2;
                                                                    if (i == 0) {
                                                                        break Label_1212;
                                                                    }
                                                                }
                                                                ++n9;
                                                            }
                                                        }
                                                    }
                                                    b.charAt(n9);
                                                }
                                            }
                                            if (i != 0) {
                                                break Label_1396;
                                            }
                                            if (c8 == n30) {
                                                n38 = n9;
                                                n39 = b.length() - 1;
                                                if (i != 0) {
                                                    break Label_1396;
                                                }
                                                if (n38 < n39) {
                                                    ++n9;
                                                    final char c13 = (char)(b.charAt(n9) - '0');
                                                    int n40;
                                                    final char c14 = (char)(n40 = c13);
                                                    int n42;
                                                    final int n41 = n42 = -1;
                                                    Label_1371: {
                                                        if (i == 0) {
                                                            if (c14 <= n41) {
                                                                break Label_1371;
                                                            }
                                                            final char c15;
                                                            n40 = (c15 = c13);
                                                            final int n43;
                                                            n42 = (n43 = 10);
                                                        }
                                                        if (i == 0) {
                                                            if (c14 >= n41) {
                                                                break Label_1371;
                                                            }
                                                            n40 = (length2 = (length3 = b.length() - n9));
                                                            if (i != 0) {
                                                                break Label_1874;
                                                            }
                                                            n42 = 2;
                                                        }
                                                        if (n40 < n42) {
                                                            break Label_1870;
                                                        }
                                                        final char c16 = (char)(b.charAt(n9 + 1) - '0');
                                                        if (i == 0) {
                                                            Label_1363: {
                                                                if (c16 > -1) {
                                                                    int n44;
                                                                    final char c17 = (char)(n44 = c16);
                                                                    int n46;
                                                                    final int n45 = n46 = 10;
                                                                    if (i == 0) {
                                                                        if (c17 >= n45) {
                                                                            break Label_1363;
                                                                        }
                                                                        n44 = (length2 = (length3 = b.length() - n9));
                                                                        if (i != 0) {
                                                                            break Label_1874;
                                                                        }
                                                                        n46 = 2;
                                                                    }
                                                                    if (n44 <= n46) {
                                                                        break Label_1870;
                                                                    }
                                                                    n9 += 2;
                                                                    if (i == 0) {
                                                                        break Label_1374;
                                                                    }
                                                                }
                                                            }
                                                            ++n9;
                                                        }
                                                        if (i == 0) {
                                                            break Label_1374;
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
                                break Label_1860;
                            }
                            int n21;
                            n18 = (n21 = (n10 = (n11 = (n12 = (n47 = c)))));
                            n19 = (n13 = (n15 = (n16 = (n17 = (length4 = 3)))));
                        } while (i != 0);
                        if (i != 0) {
                            break Label_1867;
                        }
                        if (n18 != n19) {
                            final char c18 = (char)(n47 = c);
                            final int n48 = length4 = 15;
                            if (i != 0) {
                                break Label_1867;
                            }
                            if (c18 != n48) {
                                final char c19 = (char)(n47 = c);
                                final int n49 = length4 = 2;
                                if (i != 0) {
                                    break Label_1867;
                                }
                                if (c19 != n49) {
                                    final char c20 = (char)(n47 = c);
                                    final int n50 = length4 = 31;
                                    if (i != 0) {
                                        break Label_1867;
                                    }
                                    if (c20 != n50) {
                                        final char c21 = (char)(n47 = c);
                                        final int n51 = length4 = 22;
                                        if (i != 0) {
                                            break Label_1867;
                                        }
                                        if (c21 != n51) {
                                            int n52;
                                            boolean d;
                                            final boolean b11 = d = ((n52 = (this.d ? 1 : 0)) != 0);
                                            final int n53;
                                            Label_1517: {
                                                if (i == 0) {
                                                    if (!b11) {
                                                        n53 = (n52 = n8 + this.h + 1);
                                                        if (i != 0) {
                                                            break Label_1517;
                                                        }
                                                        if (n53 >= size.width) {
                                                            n8 = this.f + 2;
                                                            n7 += this.c;
                                                        }
                                                    }
                                                    d = this.d;
                                                }
                                            }
                                            Label_1858: {
                                                if (i == 0) {
                                                    int n70 = 0;
                                                    int h = 0;
                                                    Label_1850: {
                                                        Label_1844: {
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
                                                                Label_1631: {
                                                                    Label_1626: {
                                                                        if (i == 0) {
                                                                            if (n54 > 0) {
                                                                                sb.append(c);
                                                                                if (i == 0) {
                                                                                    break Label_1626;
                                                                                }
                                                                            }
                                                                            final int n61;
                                                                            n55 = (n61 = (n56 = (n57 = (n58 = (n59 = (n60 = (length5 = (n52 = c))))))));
                                                                        }
                                                                        Label_1590: {
                                                                            if (i == 0) {
                                                                                if (n54 != 35) {
                                                                                    final int n62 = n55 = (n56 = (n57 = (n58 = (n59 = (n60 = (length5 = (n52 = c)))))));
                                                                                    if (i != 0) {
                                                                                        break Label_1590;
                                                                                    }
                                                                                    if (n62 != 60) {
                                                                                        final int n63 = n56 = (n57 = (n58 = (n59 = (n60 = (length5 = (n52 = c))))));
                                                                                        if (i != 0) {
                                                                                            break Label_1631;
                                                                                        }
                                                                                        if (n63 != 38) {
                                                                                            break Label_1626;
                                                                                        }
                                                                                    }
                                                                                }
                                                                                n55 = (n57 = (n58 = (n59 = (n60 = (length5 = (n52 = sb2.length()))))));
                                                                            }
                                                                        }
                                                                        if (i != 0) {
                                                                            break Label_1631;
                                                                        }
                                                                        if (n55 == 0) {
                                                                            n64 = (n57 = (n58 = (n59 = (n60 = (length5 = (n52 = sb3.length()))))));
                                                                            if (i != 0) {
                                                                                break Label_1631;
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
                                                                final int n67;
                                                                Label_1734: {
                                                                    Label_1729: {
                                                                        if (i == 0) {
                                                                            if (n64 > 0) {
                                                                                sb3.append(c);
                                                                                if (i == 0) {
                                                                                    break Label_1729;
                                                                                }
                                                                            }
                                                                            n58 = (n57 = (n59 = (n60 = (length5 = (n52 = c)))));
                                                                        }
                                                                        Label_1693: {
                                                                            if (i == 0) {
                                                                                if (n57 != 58) {
                                                                                    final int n65 = n58 = (n59 = (n60 = (length5 = (n52 = c))));
                                                                                    if (i != 0) {
                                                                                        break Label_1693;
                                                                                    }
                                                                                    if (n65 != 56) {
                                                                                        final int n66 = n59 = (n60 = (length5 = (n52 = c)));
                                                                                        if (i != 0) {
                                                                                            break Label_1734;
                                                                                        }
                                                                                        if (n66 != 59) {
                                                                                            break Label_1729;
                                                                                        }
                                                                                    }
                                                                                }
                                                                                n58 = (n60 = (length5 = (n52 = sb2.length())));
                                                                            }
                                                                        }
                                                                        if (i != 0) {
                                                                            break Label_1734;
                                                                        }
                                                                        if (n58 == 0) {
                                                                            n67 = (n60 = (length5 = (n52 = sb.length())));
                                                                            if (i != 0) {
                                                                                break Label_1734;
                                                                            }
                                                                            if (n67 == 0) {
                                                                                sb3.append(c);
                                                                                n5 = n8;
                                                                                n6 = n7;
                                                                            }
                                                                        }
                                                                    }
                                                                    n60 = (n59 = (length5 = (n52 = sb2.length())));
                                                                }
                                                                if (i == 0) {
                                                                    if (n67 > 0) {
                                                                        sb2.append(c);
                                                                        if (i == 0) {
                                                                            break Label_1844;
                                                                        }
                                                                    }
                                                                    length5 = (n60 = (n52 = c));
                                                                }
                                                                Label_1807: {
                                                                    if (i == 0) {
                                                                        if (n60 != 119) {
                                                                            final int n68 = length5 = (n52 = c);
                                                                            if (i != 0) {
                                                                                break Label_1807;
                                                                            }
                                                                            if (n68 != 87) {
                                                                                final int n69 = length5 = (n52 = c);
                                                                                if (i != 0) {
                                                                                    break Label_1807;
                                                                                }
                                                                                if (n69 != 104) {
                                                                                    final char c22 = (char)(n70 = c);
                                                                                    final int n71 = h = 72;
                                                                                    if (i != 0) {
                                                                                        break Label_1850;
                                                                                    }
                                                                                    if (c22 != n71) {
                                                                                        break Label_1844;
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                        length5 = sb.length();
                                                                    }
                                                                }
                                                                if (i != 0) {
                                                                    break Label_1858;
                                                                }
                                                                if (length5 == 0) {
                                                                    final int length6 = sb3.length();
                                                                    if (i != 0) {
                                                                        break Label_1858;
                                                                    }
                                                                    if (length6 == 0) {
                                                                        sb2.append(c);
                                                                        n3 = n8;
                                                                        n4 = n7;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        n70 = n8;
                                                        h = this.h;
                                                    }
                                                    n8 = n70 + h;
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
        if (i == 0) {
            if (n29 > 0) {
                this.l.addElement(new lb(this, sb.toString(), n, n2 - this.c, 0));
            }
            final int length2;
            length3 = (length2 = sb3.length());
        }
        final int n72 = 1;
        Label_2598: {
            int n74 = 0;
            int length7 = 0;
            int n73 = 0;
            Label_2487: {
                if (i == 0) {
                    if (n33 > n72) {
                        boolean b19;
                        boolean equalsIgnoreCase3;
                        boolean b18;
                        boolean b17;
                        boolean b16;
                        boolean b15;
                        boolean b14;
                        boolean b13;
                        final boolean b12 = b13 = (b14 = (b15 = (b16 = (b17 = (b18 = (equalsIgnoreCase3 = (b19 = sb3.toString().equals(d.P[3]))))))));
                        final StringBuffer sb7;
                        Label_2449: {
                            if (i == 0) {
                                if (b12) {
                                    this.n.addElement(new lb(this, null, n5, n6 - this.c, 0));
                                    if (i == 0) {
                                        break Label_2449;
                                    }
                                }
                                final boolean b20;
                                b13 = (b20 = (b14 = (b15 = (b16 = (b17 = (b18 = (equalsIgnoreCase3 = (b19 = sb3.toString().equals(d.P[9])))))))));
                            }
                            if (i == 0) {
                                if (b12) {
                                    this.n.addElement(new lb(this, null, n5, n6 - this.c, 5));
                                    if (i == 0) {
                                        break Label_2449;
                                    }
                                }
                                b14 = (b13 = (b15 = (b16 = (b17 = (b18 = (equalsIgnoreCase3 = (b19 = sb3.toString().equals(d.P[4]))))))));
                            }
                            if (i == 0) {
                                if (b13) {
                                    this.n.addElement(new lb(this, null, n5, n6 - this.c, 6));
                                    if (i == 0) {
                                        break Label_2449;
                                    }
                                }
                                b15 = (b14 = (b16 = (b17 = (b18 = (equalsIgnoreCase3 = (b19 = sb3.toString().equals(d.P[12])))))));
                            }
                            if (i == 0) {
                                if (b14) {
                                    this.n.addElement(new lb(this, null, n5, n6 - this.c, 7));
                                    if (i == 0) {
                                        break Label_2449;
                                    }
                                }
                                b16 = (b15 = (b17 = (b18 = (equalsIgnoreCase3 = (b19 = sb3.toString().equals(d.P[8]))))));
                            }
                            if (i == 0) {
                                if (b15) {
                                    this.n.addElement(new lb(this, null, n5, n6 - this.c, 8));
                                    if (i == 0) {
                                        break Label_2449;
                                    }
                                }
                                b17 = (b16 = (b18 = (equalsIgnoreCase3 = (b19 = sb3.toString().equals(d.P[0])))));
                            }
                            if (i == 0) {
                                if (b16) {
                                    this.n.addElement(new lb(this, null, n5, n6 - this.c, 9));
                                    if (i == 0) {
                                        break Label_2449;
                                    }
                                }
                                b18 = (b17 = (equalsIgnoreCase3 = (b19 = sb3.toString().equals(d.P[1]))));
                            }
                            if (i == 0) {
                                if (b17) {
                                    this.n.addElement(new lb(this, null, n5, n6 - this.c, 1));
                                    if (i == 0) {
                                        break Label_2449;
                                    }
                                }
                                equalsIgnoreCase3 = (b18 = (b19 = sb3.toString().equals(d.P[5])));
                            }
                            if (i == 0) {
                                if (b18) {
                                    this.n.addElement(new lb(this, null, n5, n6 - this.c, 2));
                                    if (i == 0) {
                                        break Label_2449;
                                    }
                                }
                                b19 = (equalsIgnoreCase3 = sb3.toString().equalsIgnoreCase(d.P[11]));
                            }
                            if (i == 0) {
                                if (equalsIgnoreCase3) {
                                    this.n.addElement(new lb(this, null, n5, n6 - this.c, 3));
                                    if (i == 0) {
                                        break Label_2449;
                                    }
                                }
                                sb7 = sb3;
                                if (i != 0) {
                                    break Label_2449;
                                }
                                b19 = sb7.toString().equals(d.P[10]);
                            }
                            if (b19) {
                                this.n.addElement(new lb(this, null, n5, n6 - this.c, 4));
                            }
                        }
                        sb7.setLength(0);
                    }
                    n73 = (length3 = (length7 = (n74 = sb2.length())));
                    if (i != 0) {
                        break Label_2487;
                    }
                }
                if (length3 <= n72) {
                    break Label_2598;
                }
                length7 = (n73 = (n74 = (sb2.toString().substring(0, 4).equalsIgnoreCase(d.P[2]) ? 1 : 0)));
            }
            if (i == 0) {
                if (n73 != 0) {
                    this.m.addElement(new lb(this, sb2.toString(), n3, n4 - this.c, 0));
                    if (i == 0) {
                        break Label_2598;
                    }
                }
                n74 = (length7 = sb2.length());
            }
            if (i == 0) {
                if (length7 <= 7) {
                    break Label_2598;
                }
                n74 = (sb2.toString().substring(0, 7).equalsIgnoreCase(d.P[7]) ? 1 : 0);
            }
            if (n74 != 0) {
                this.m.addElement(new lb(this, sb2.toString(), n3, n4 - this.c, 0));
            }
        }
        this.i = n7;
        lb.d = n7;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final int m = fb.m;
        final Dimension size = this.getSize();
        int n2;
        final int n = n2 = mouseEvent.getClickCount();
        if (m == 0) {
            if (n != 1) {
                return;
            }
            final boolean d;
            n2 = ((d = this.d) ? 1 : 0);
        }
        if (m == 0) {
            if (n != 0) {
                return;
            }
            n2 = mouseEvent.getX();
        }
        final int n3 = n2;
        final int y = mouseEvent.getY();
        int n4 = 0;
        int n5;
        while (true) {
            while (true) {
                Label_0343: {
                    if (m == 0) {
                        break Label_0343;
                    }
                    n5 = this.l.elementAt(n4).d + size.height - this.c;
                    final int c = this.l.elementAt(n4).c;
                    final String b = this.l.elementAt(n4).b;
                    int n8;
                    int n7;
                    final int n6 = n7 = (n8 = n5 - this.K);
                    int n11;
                    int n10;
                    final int n9 = n10 = (n11 = y);
                    Label_0340: {
                        if (m == 0) {
                            if (n6 > n9) {
                                break Label_0340;
                            }
                            final int n12;
                            n7 = (n12 = (n8 = n5 - this.K + this.c));
                            final int n13;
                            n10 = (n13 = (n11 = y));
                        }
                        if (m == 0) {
                            if (n6 < n9) {
                                break Label_0340;
                            }
                            n8 = (n7 = n3);
                            n11 = (n10 = c);
                        }
                        Label_0330: {
                            final String substring;
                            Label_0220: {
                                int equals = 0;
                                Label_0204: {
                                    if (m == 0) {
                                        if (n7 < n10) {
                                            break Label_0340;
                                        }
                                        equals = (n8 = n3);
                                        if (m != 0) {
                                            break Label_0204;
                                        }
                                        n11 = c + b.length() * this.h;
                                    }
                                    if (n8 > n11) {
                                        break Label_0340;
                                    }
                                    substring = b.substring(0, 1);
                                    if (m != 0) {
                                        break Label_0220;
                                    }
                                    equals = (substring.equals("<") ? 1 : 0);
                                }
                                if (equals == 0) {
                                    break Label_0330;
                                }
                                b.substring(1, b.length() - 1);
                            }
                            final String s2;
                            final String s = s2 = substring;
                            if (m == 0 && s2 == null) {
                                return;
                            }
                            if (s2.length() <= 0) {
                                return;
                            }
                            x i = this.a.i(s);
                            if (m == 0) {
                                if (i == null) {
                                    i = new x(this.a, s);
                                    this.a.Ob.b.a(s, i, false);
                                    this.a.Ob.b.a(i);
                                }
                                this.a.Ob.b.a(i);
                            }
                            if (m == 0) {
                                return;
                            }
                        }
                        this.a.l(b);
                        return;
                    }
                    ++n4;
                }
                if (n4 < this.l.size()) {
                    continue;
                }
                break;
            }
            n5 = 0;
            if (m != 0) {
                continue;
            }
            break;
        }
        while (true) {
            while (true) {
                Label_0577: {
                    if (m == 0) {
                        break Label_0577;
                    }
                    final Object element = this.m.elementAt(n5);
                    final int n14 = ((lb)element).d + size.height - this.c;
                    final int c2 = this.m.elementAt(n5).c;
                    String b2 = this.m.elementAt(n5).b;
                    int n17;
                    int n16;
                    final int n15 = n16 = (n17 = n14 - this.K);
                    int n20;
                    int n19;
                    final int n18 = n19 = (n20 = y);
                    Label_0574: {
                        if (m == 0) {
                            if (n15 > n18) {
                                break Label_0574;
                            }
                            final int n21;
                            n16 = (n21 = (n17 = n14 - this.K + this.c));
                            final int n22;
                            n19 = (n22 = (n20 = y));
                        }
                        if (m == 0) {
                            if (n15 < n18) {
                                break Label_0574;
                            }
                            n17 = (n16 = n3);
                            n20 = (n19 = c2);
                        }
                        Label_0518: {
                            if (m == 0) {
                                if (n16 < n19) {
                                    break Label_0574;
                                }
                                final int equalsIgnoreCase = n17 = n3;
                                if (m != 0) {
                                    break Label_0518;
                                }
                                n20 = c2 + b2.length() * this.h;
                            }
                            if (n17 > n20) {
                                break Label_0574;
                            }
                            try {
                                final String substring2 = b2.substring(0, 1);
                                Label_0544: {
                                    if (m == 0) {
                                        final int equalsIgnoreCase = substring2.equalsIgnoreCase("w") ? 1 : 0;
                                        if (equalsIgnoreCase == 0) {
                                            break Label_0544;
                                        }
                                        new StringBuffer(d.P[7]).append(b2).toString();
                                    }
                                    b2 = substring2;
                                }
                                this.a.getAppletContext().showDocument(new URL(b2), d.P[13]);
                                return;
                            }
                            catch (Exception ex) {
                                return;
                            }
                        }
                    }
                    ++n5;
                }
                if (n5 < this.m.size()) {
                    continue;
                }
                break;
            }
            final Object element = this;
            if (m != 0) {
                continue;
            }
            break;
        }
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final int m = fb.m;
        this.v = mouseEvent.getX();
        this.w = mouseEvent.getY();
        int n2;
        int x;
        final int n = x = (n2 = (this.d ? 1 : 0));
        final boolean b;
        Label_0183: {
            if (m == 0) {
                Label_0177: {
                    if (n != 0) {
                        d d = this;
                        if (m == 0) {
                            if (this.L) {
                                b = ((n2 = (this.O ? 1 : 0)) != 0);
                                if (m != 0) {
                                    break Label_0183;
                                }
                                if (b) {
                                    break Label_0177;
                                }
                            }
                            d = this;
                        }
                        kb b2 = d.k.b;
                        while (true) {
                            Label_0172: {
                                if (m == 0) {
                                    break Label_0172;
                                }
                                final int c = ((lb)b2.b).c;
                                final int n3 = c - this.K;
                                final int w = this.w;
                                final kb kb;
                                Label_0171: {
                                    Label_0167: {
                                        Label_0156: {
                                            int g = 0;
                                            Label_0137: {
                                                if (m == 0) {
                                                    if (n3 > w) {
                                                        break Label_0156;
                                                    }
                                                    final int n4;
                                                    g = (n4 = c - this.K + this.c);
                                                    if (m != 0) {
                                                        break Label_0137;
                                                    }
                                                    final int w2 = this.w;
                                                }
                                                if (n3 < w) {
                                                    break Label_0156;
                                                }
                                                kb = b2;
                                                if (m != 0) {
                                                    break Label_0171;
                                                }
                                                g = (((lb)kb.b).g ? 1 : 0);
                                            }
                                            if (g != 0) {
                                                break Label_0167;
                                            }
                                            ((lb)b2.b).g = true;
                                            if (m == 0) {
                                                break Label_0167;
                                            }
                                        }
                                        ((lb)b2.b).g = false;
                                    }
                                    final kb c2 = b2.c;
                                }
                                b2 = kb;
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
        if (m == 0) {
            if (n > 0) {
                return;
            }
            x = this.x;
        }
        final int n6 = -1;
        Label_0355: {
            d d2 = null;
            Label_0348: {
                final int v;
                Label_0283: {
                    if (m == 0) {
                        if ((b ? 1 : 0) == n6) {
                            v = this.v;
                            final int n7 = -1;
                            if (m != 0) {
                                break Label_0283;
                            }
                            if (v != n7) {
                                this.x = this.v;
                                this.y = this.w;
                                this.C = this.y;
                                this.B = this.x;
                                this.z = this.C;
                                this.A = this.B;
                                if (m == 0) {
                                    break Label_0355;
                                }
                            }
                        }
                        d2 = this;
                        if (m != 0) {
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
                    if (m == 0) {
                        break Label_0355;
                    }
                }
                this.y = this.z;
                this.C = this.w;
                this.x = this.A;
                d2 = this;
            }
            d2.B = this.v;
        }
        this.repaint();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int m = fb.m;
        final int x = this.x;
        d d = null;
        Label_0031: {
            if (m == 0) {
                if (x != -1) {
                    return;
                }
                d = this;
                if (m != 0) {
                    break Label_0031;
                }
                final boolean d2 = this.d;
            }
            if (x != 0) {
                return;
            }
            d = this;
        }
        final Dimension size = d.getSize();
        final int x2 = mouseEvent.getX();
        final int y = mouseEvent.getY();
        int n = 0;
        int n2;
        while (true) {
            while (true) {
                Label_0195: {
                    if (m == 0) {
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
                        if (m == 0) {
                            if (n3 > n6) {
                                break Label_0192;
                            }
                            final int n9;
                            n4 = (n9 = (n5 = n2 - this.K + this.c));
                            final int n10;
                            n7 = (n10 = (n8 = y));
                        }
                        if (m == 0) {
                            if (n3 < n6) {
                                break Label_0192;
                            }
                            n5 = (n4 = x2);
                            n8 = (n7 = c);
                        }
                        if (m == 0) {
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
            if (m != 0) {
                continue;
            }
            break;
        }
        while (true) {
            while (true) {
                Label_0361: {
                    if (m == 0) {
                        break Label_0361;
                    }
                    final Object element = this.m.elementAt(n2);
                    final int n11 = ((lb)element).d + size.height - this.c;
                    final int c2 = this.m.elementAt(n2).c;
                    final String b2 = this.m.elementAt(n2).b;
                    int n14;
                    int n13;
                    final int n12 = n13 = (n14 = n11 - this.K);
                    int n17;
                    int n16;
                    final int n15 = n16 = (n17 = y);
                    Label_0358: {
                        if (m == 0) {
                            if (n12 > n15) {
                                break Label_0358;
                            }
                            final int n18;
                            n13 = (n18 = (n14 = n11 - this.K + this.c));
                            final int n19;
                            n16 = (n19 = (n17 = y));
                        }
                        if (m == 0) {
                            if (n12 < n15) {
                                break Label_0358;
                            }
                            n14 = (n13 = x2);
                            n17 = (n16 = c2);
                        }
                        if (m == 0) {
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
            if (m != 0) {
                continue;
            }
            break;
        }
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final int m = fb.m;
        this.e = "";
        d d = this;
        if (m == 0) {
            if (this.d) {
                d d2 = this;
                if (m == 0) {
                    if (this.i == 0) {
                        return;
                    }
                    d2 = this;
                }
                kb kb = d2.k.b;
                while (true) {
                    while (true) {
                        Label_0168: {
                            if (m == 0) {
                                break Label_0168;
                            }
                            final Object b = kb.b;
                            final int c = ((lb)b).c;
                            final int n = c - this.K;
                            Label_0163: {
                                Object b2 = null;
                                Label_0156: {
                                    Label_0137: {
                                        if (m == 0) {
                                            if (n <= mouseEvent.getY()) {
                                                final int n2 = c - this.K + this.c;
                                                if (m != 0) {
                                                    break Label_0137;
                                                }
                                                if (n2 >= mouseEvent.getY()) {
                                                    ((lb)kb.b).g = false;
                                                    ((lb)kb.b).g = true;
                                                    if (m == 0) {
                                                        break Label_0163;
                                                    }
                                                }
                                            }
                                            b2 = this;
                                            if (m != 0) {
                                                break Label_0156;
                                            }
                                            final boolean l = this.L;
                                        }
                                    }
                                    if (n != 0) {
                                        b2 = this;
                                        if (m != 0) {
                                            break Label_0156;
                                        }
                                        if (this.O) {
                                            break Label_0163;
                                        }
                                    }
                                    b2 = kb.b;
                                }
                                ((lb)b2).g = false;
                            }
                            kb = kb.c;
                        }
                        if (kb != null) {
                            continue;
                        }
                        break;
                    }
                    final Object b = this;
                    if (m != 0) {
                        continue;
                    }
                    break;
                }
                this.repaint();
            }
            d = this;
        }
        d.s = true;
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        final int m = fb.m;
        this.v = -1;
        this.w = -1;
        this.x = -1;
        this.y = -1;
        this.s = false;
        this.repaint();
        d d = this;
        d d2 = this;
        d d3 = this;
        Label_0082: {
            if (m == 0) {
                if (!this.d) {
                    d = this;
                    d2 = this;
                    d3 = this;
                    if (m != 0) {
                        break Label_0082;
                    }
                    if (this.e.length() > 1) {
                        d = this;
                        d2 = this;
                        d3 = this;
                        if (m != 0) {
                            break Label_0082;
                        }
                        if (this.p > 0) {
                            this.a.r(this.e);
                        }
                    }
                }
                d = this;
                d2 = this;
                d3 = this;
            }
        }
        if (m == 0) {
            if (d3.E == null) {
                return;
            }
            d = this;
            d2 = this;
        }
        if (m == 0) {
            if (d2.L) {
                return;
            }
            d = this;
        }
        d.E.requestFocus();
    }
    
    public void paint(final Graphics graphics) {
        final int i = fb.m;
        final Dimension size = this.getSize();
        int n = 0;
        int n2 = 0;
        Color e = this.a.e;
        int n3 = 0;
        final StringBuffer sb = new StringBuffer();
        Font d = this.D;
        final String name = d.getName();
        final int size2 = d.getSize();
        final FontMetrics fontMetrics = this.getFontMetrics(d);
        final Font font = d;
        final Font font2 = new Font(name, 1, size2);
        d d2 = this;
        final char c;
        Label_0406: {
            if (i == 0) {
                Label_0402: {
                    if (this.J != null) {
                        final char c2;
                        c = (c2 = this.M.charAt(8));
                        if (i != 0) {
                            break Label_0406;
                        }
                        if (c == 'h') {
                            final int b;
                            final int n4 = b = this.J.b();
                            if (i == 0) {
                                if (b == this.J.a() - 1) {
                                    final boolean d3;
                                    final boolean b2 = d3 = this.d;
                                    if (i == 0) {
                                        if (b2) {}
                                    }
                                }
                            }
                            final int n5 = b;
                            int n6 = this.i / this.c;
                            final int n7 = (this.J.getSize().height + 1) / this.c;
                            int d4;
                            final boolean b3 = (d4 = (this.d ? 1 : 0)) != 0;
                            if (i == 0) {
                                Label_0342: {
                                    if (b3) {
                                        final int n8 = this.b * this.c;
                                        final b j;
                                        Label_0335: {
                                            if (i == 0) {
                                                if (n8 > this.getSize().height) {
                                                    n6 = n6 - n7 + 1;
                                                    this.J.show();
                                                    this.a.Ob.invalidate();
                                                    this.a.Ob.validate();
                                                    if (i == 0) {
                                                        break Label_0342;
                                                    }
                                                }
                                                n6 = 1;
                                                j = this.J;
                                                if (i != 0) {
                                                    break Label_0335;
                                                }
                                                j.b();
                                            }
                                            if (n8 == 0) {
                                                this.J.setVisible(false);
                                                this.a.Ob.invalidate();
                                                this.a.Ob.validate();
                                                if (i == 0) {
                                                    break Label_0342;
                                                }
                                            }
                                            final b k = this.J;
                                        }
                                        j.a(1, 0, 1, 1);
                                    }
                                }
                                d4 = n5;
                            }
                            if (d4 != 0) {
                                this.K = this.K + (this.i - n4 * this.c) - this.c;
                                this.J.a(n6 - 1, 1, 0, n6);
                                if (i == 0) {
                                    break Label_0402;
                                }
                            }
                            this.J.a(n6);
                        }
                    }
                }
                d2 = this;
            }
            final boolean s = d2.s;
        }
        if (i == 0) {
            if (c != '\0') {
                final char c2;
                final boolean b4 = (c2 = (char)(this.d ? 1 : 0)) != '\0';
                if (i == 0) {
                    if (!b4) {
                        this.a(this.B, this.C, this.x, this.y);
                    }
                }
            }
        }
        final Image image = this.createImage(size.width, size.height);
        final Graphics graphics2 = image.getGraphics();
        final int width = this.a.y.getWidth(this);
        final int height = this.a.y.getHeight(this);
        final int n9 = width;
        int width2 = 0;
        int n12 = 0;
        int height2 = 0;
        int n15 = 0;
        int d5 = 0;
    Label_0557_Outer:
        while (true) {
            Label_0589: {
                if (i != 0) {
                    break Label_0589;
                }
                if (n9 <= 1) {
                    break Label_0589;
                }
                final int n10 = height;
                if (i != 0) {
                    break Label_0589;
                }
                if (n10 <= 1) {
                    break Label_0589;
                }
                width2 = 0;
                Label_0580: {
                    if (i == 0) {
                        break Label_0580;
                    }
                    int n11 = n12;
                    int n13 = 0;
                    while (true) {
                        while (true) {
                            Label_0559: {
                                if (i == 0) {
                                    break Label_0559;
                                }
                                graphics2.drawImage(this.a.y, width2, n11, this);
                                n11 = n13;
                            }
                            if (n11 < size.height) {
                                continue Label_0557_Outer;
                            }
                            break;
                        }
                        n13 = width2 + width;
                        if (i != 0) {
                            continue;
                        }
                        break;
                    }
                    width2 = n13;
                }
                if (width2 < size.width) {
                    continue;
                }
            }
            width2 = n9;
            height2 = 0;
            final int n14;
            n12 = (n14 = (d5 = (n15 = (this.F ? 1 : 0))));
            if (i != 0) {
                continue;
            }
            break;
        }
        final int n16;
        Label_0691: {
            if (i == 0) {
                d d6 = null;
                Label_0688: {
                    if (n12 != 0) {
                        d6 = this;
                        if (i != 0) {
                            break Label_0688;
                        }
                        if (this.G != null) {
                            n16 = (d5 = (n15 = (this.a.j.checkAll() ? 1 : 0)));
                            if (i != 0) {
                                break Label_0691;
                            }
                            if (n16 != 0) {
                                width2 = this.G.getWidth(this);
                                height2 = this.G.getHeight(this);
                                graphics2.drawImage(this.G, this.getSize().width - width2 - 6, 3, this);
                            }
                        }
                    }
                    d6 = this;
                }
                final int n14;
                d5 = (n14 = (n15 = (d6.I ? 1 : 0)));
            }
        }
        if (i == 0) {
            d d7 = null;
            Label_0802: {
                if (n16 != 0) {
                    d7 = this;
                    if (i != 0) {
                        break Label_0802;
                    }
                    if (this.H != null) {
                        d7 = this;
                        if (i != 0) {
                            break Label_0802;
                        }
                        if (this.H != "") {
                            int n18;
                            final int n17 = n18 = width2;
                            if (i == 0) {
                                if (n17 == 0) {
                                    width2 = 30;
                                }
                                final int n19;
                                n18 = (n19 = height2);
                            }
                            Label_0753: {
                                if (i == 0) {
                                    if (n17 != 0) {
                                        break Label_0753;
                                    }
                                    n18 = 30;
                                }
                                height2 = n18;
                            }
                            graphics2.setColor(this.a.e);
                            graphics2.drawString(this.H, this.getSize().width - width2, height2 + 13);
                        }
                    }
                }
                graphics2.setColor(this.a.e);
                d7 = this;
            }
            n15 = (d5 = (d7.d ? 1 : 0));
        }
        int height3 = 0;
        Label_0830: {
            if (i == 0) {
                if (d5 == 0) {
                    height3 = size.height;
                    if (i == 0) {
                        break Label_0830;
                    }
                }
                n15 = this.c;
            }
            height3 = n15;
        }
        int n20 = height3 - this.c + this.o;
        kb kb = this.k.b;
        while (true) {
            Label_0953: {
                if (i == 0) {
                    break Label_0953;
                }
                n20 = ((lb)kb.b).c + height3 - this.c + this.o;
                kb c3;
                do {
                    final kb kb2 = kb;
                    if (i == 0 && ((lb)kb2.b).d + height3 - this.c + this.o - this.K - fontMetrics.getAscent() < 0) {
                        n2 += ((lb)kb.b).b.length();
                        kb = kb.c;
                        break Label_0953;
                    }
                    c3 = kb2;
                } while (i != 0);
                int length6 = 0;
                int n99 = 0;
                Label_3251: {
                    while (true) {
                        Label_3223: {
                            if (i == 0) {
                                break Label_3223;
                            }
                            String s2 = ((lb)c3.b).b;
                            int n22;
                            int n21 = n22 = this.f + 2;
                            int n23 = n20 - this.K;
                            final boolean d8 = this.d;
                            final int length5;
                            Label_3221: {
                                Label_3207: {
                                    Label_1215: {
                                        final lb lb;
                                        Label_1080: {
                                            if (i == 0) {
                                                if (!d8) {
                                                    break Label_1215;
                                                }
                                                lb = (lb)c3.b;
                                                if (i != 0) {
                                                    break Label_1080;
                                                }
                                                final boolean g = lb.g;
                                            }
                                            if (!d8) {
                                                int s3;
                                                final int n24 = s3 = (this.s ? 1 : 0);
                                                if (i == 0) {
                                                    if (n24 == 0) {
                                                        break Label_1215;
                                                    }
                                                    final int n25;
                                                    s3 = (n25 = n23);
                                                }
                                                final int c4 = this.C;
                                                if (i == 0) {
                                                    if (n24 < c4) {
                                                        break Label_1215;
                                                    }
                                                    s3 = n23;
                                                    final int y = this.y;
                                                }
                                                if (s3 > c4) {
                                                    break Label_1215;
                                                }
                                            }
                                            final lb lb2 = (lb)c3.b;
                                        }
                                        lb.g = true;
                                        if (i == 0) {
                                            if (d != font) {
                                                d = font;
                                                graphics2.setFont(d);
                                            }
                                            n = n23 - fontMetrics.getAscent();
                                            graphics2.drawImage(this.a.x, 2, n, size.width + 1, n + this.c, 204, 20, 208, 36, this);
                                        }
                                        if (i == 0) {
                                            if (s2.charAt(0) == '\u0003') {
                                                s2 = s2.substring(2);
                                            }
                                            graphics2.setColor(this.a.e);
                                            graphics2.drawString(s2, n22, n23);
                                            graphics2.setColor(e);
                                        }
                                        if (i == 0) {
                                            break Label_3207;
                                        }
                                    }
                                    Font font3 = font;
                                    sb.setLength(0);
                                    int n26 = 0;
                                    Color color = this.a.e;
                                    Color color2 = Color.white;
                                    int n27 = 1;
                                    int n28 = 0;
                                    int n29 = 0;
                                    int n30 = 0;
                                    final Font font4 = d;
                                    if (i != 0 || font4 != font) {
                                        d = font4;
                                        graphics2.setFont(d);
                                    }
                                    int n31 = 0;
                                    int n32 = 0;
                                    int n33 = 0;
                                Label_2997:
                                    while (true) {
                                        int n71 = 0;
                                        int length = 0;
                                        Label_2980: {
                                            Label_2973: {
                                                if (i == 0) {
                                                    break Label_2973;
                                                }
                                                char c8;
                                                char char1;
                                                char c7;
                                                char c6;
                                                final char c5 = c6 = (c7 = (char1 = (c8 = s2.charAt(n31))));
                                                char c13;
                                                char c12;
                                                char c11;
                                                char c10;
                                                final char c9 = c10 = (c11 = (c12 = (c13 = '\u0001')));
                                                if (i == 0) {
                                                    if (c5 == c9) {
                                                        n32 = n31;
                                                        n33 = s2.length() - 1;
                                                        if (i != 0) {
                                                            break Label_2997;
                                                        }
                                                        if (n32 >= n33) {
                                                            break Label_2997;
                                                        }
                                                        ++n31;
                                                    }
                                                    final char c14;
                                                    c6 = (c14 = (c7 = (char1 = (c8 = s2.charAt(n31)))));
                                                    final char c15;
                                                    c10 = (c15 = (c11 = (c12 = (c13 = '\u0002'))));
                                                }
                                                if (i == 0) {
                                                    if (c5 == c9) {
                                                        final int n34 = n31;
                                                        final int n35 = s2.length() - 1;
                                                        if (i != 0) {
                                                            break Label_2997;
                                                        }
                                                        if (n34 >= n35) {
                                                            break Label_2997;
                                                        }
                                                        final Font font5 = font3;
                                                        if (i == 0 && font5 == font2) {
                                                            font3 = font;
                                                            if (i != 0) {
                                                                goto Label_1375;
                                                            }
                                                        }
                                                        else {
                                                            font3 = font5;
                                                        }
                                                        ++n31;
                                                    }
                                                    c7 = (c6 = (char1 = (c8 = s2.charAt(n31))));
                                                    c11 = (c10 = (c12 = (c13 = '\u001f')));
                                                }
                                                if (i == 0) {
                                                    if (c6 == c10) {
                                                        final int n36 = n31;
                                                        final int n37 = s2.length() - 1;
                                                        if (i != 0) {
                                                            break Label_2997;
                                                        }
                                                        if (n36 >= n37) {
                                                            break Label_2997;
                                                        }
                                                        final int n38 = n29;
                                                        if (i == 0 && n38 != 0) {
                                                            n29 = 0;
                                                            if (i != 0) {
                                                                goto Label_1434;
                                                            }
                                                        }
                                                        else {
                                                            n29 = n38;
                                                        }
                                                        ++n31;
                                                    }
                                                    char1 = (c7 = (c8 = s2.charAt(n31)));
                                                    c12 = (c11 = (c13 = '\u0016'));
                                                }
                                                if (i == 0) {
                                                    if (c7 == c11) {
                                                        final int n39 = n31;
                                                        final int n40 = s2.length() - 1;
                                                        if (i != 0) {
                                                            break Label_2997;
                                                        }
                                                        if (n39 >= n40) {
                                                            break Label_2997;
                                                        }
                                                        color = Color.white;
                                                        color2 = this.a.e;
                                                        ++n31;
                                                    }
                                                    c8 = (char1 = s2.charAt(n31));
                                                    c13 = (c12 = '\u000f');
                                                }
                                                int n47 = 0;
                                                final int n69;
                                                final int n70;
                                                Label_2088: {
                                                    char char2 = '\0';
                                                    Label_2073: {
                                                        if (i == 0) {
                                                            if (char1 == c12) {
                                                                final int n41 = n31;
                                                                final int n42 = s2.length() - 1;
                                                                if (i != 0) {
                                                                    break Label_2997;
                                                                }
                                                                if (n41 >= n42) {
                                                                    break Label_2997;
                                                                }
                                                                final int n43 = n30;
                                                                if (i == 0 && n43 != 0) {
                                                                    color = this.a.e;
                                                                    color2 = this.a.e;
                                                                    n30 = 0;
                                                                    if (i != 0) {
                                                                        goto Label_1561;
                                                                    }
                                                                }
                                                                else {
                                                                    n30 = n43;
                                                                    color = this.a.e;
                                                                    color2 = Color.white;
                                                                }
                                                                ++n31;
                                                            }
                                                            char2 = (c8 = s2.charAt(n31));
                                                            if (i != 0) {
                                                                break Label_2073;
                                                            }
                                                            c13 = '\u0003';
                                                        }
                                                        if (c8 == c13) {
                                                            final int n44 = n31;
                                                            final int n45 = s2.length() - 1;
                                                            if (i != 0) {
                                                                break Label_2997;
                                                            }
                                                            if (n44 >= n45) {
                                                                break Label_2997;
                                                            }
                                                            ++n31;
                                                            final char char3 = s2.charAt(n31);
                                                            final char c16 = '0';
                                                            int a = 0;
                                                            Label_1671: {
                                                                if (i == 0) {
                                                                    if (char3 == c16) {
                                                                        final int n46 = a = (this.a.a(s2.charAt(n31 + 1)) ? 1 : 0);
                                                                        if (i != 0) {
                                                                            break Label_1671;
                                                                        }
                                                                        if (n46 != 0) {
                                                                            ++n31;
                                                                        }
                                                                    }
                                                                    s2.charAt(n31);
                                                                }
                                                                a = char3 - c16;
                                                            }
                                                            final int n48;
                                                            n47 = (n48 = a);
                                                            final int n49 = -1;
                                                            final int n57;
                                                            Label_2006: {
                                                                Label_1803: {
                                                                    if (i == 0) {
                                                                        Label_1789: {
                                                                            if (n48 > n49) {
                                                                                final int n50 = n47;
                                                                                final int n52;
                                                                                final int n51 = n52 = 10;
                                                                                if (i != 0) {
                                                                                    break Label_1803;
                                                                                }
                                                                                if (n50 < n51) {
                                                                                    final int n53 = s2.length() - n31;
                                                                                    final int n54 = 2;
                                                                                    if (i != 0) {
                                                                                        break Label_2997;
                                                                                    }
                                                                                    if (n53 < n54) {
                                                                                        break Label_2997;
                                                                                    }
                                                                                    final char c18;
                                                                                    final char c17 = c18 = (char)(s2.charAt(n31 + 1) - 48);
                                                                                    if (i == 0) {
                                                                                        if (c18 > -1) {
                                                                                            final char c19 = c17;
                                                                                            if (i == 0) {
                                                                                                if (c19 < '\n') {
                                                                                                    final int n55 = s2.length() - n31;
                                                                                                    final int n56 = 2;
                                                                                                    if (i != 0) {
                                                                                                        break Label_2997;
                                                                                                    }
                                                                                                    if (n55 <= n56) {
                                                                                                        break Label_2997;
                                                                                                    }
                                                                                                    n27 = c17 + '\n';
                                                                                                    n31 += 2;
                                                                                                    if (i == 0) {
                                                                                                        break Label_1789;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                    n27 = c18;
                                                                                    ++n31;
                                                                                }
                                                                            }
                                                                        }
                                                                        s2.charAt(n31);
                                                                        if (i != 0) {
                                                                            break Label_2006;
                                                                        }
                                                                    }
                                                                }
                                                                if (n48 == n49) {
                                                                    final int n59;
                                                                    final int n58;
                                                                    n57 = (n58 = (n59 = n31));
                                                                    if (i == 0) {
                                                                        if (n57 < s2.length() - 1) {
                                                                            ++n31;
                                                                            final char char4 = s2.charAt(n31);
                                                                            final char c20 = '0';
                                                                            char a2 = '\0';
                                                                            Label_1880: {
                                                                                if (i == 0) {
                                                                                    if (char4 == c20) {
                                                                                        final boolean b5 = (a2 = (char)(this.a.a(s2.charAt(n31 + 1)) ? 1 : 0)) != '\0';
                                                                                        if (i != 0) {
                                                                                            break Label_1880;
                                                                                        }
                                                                                        if (b5) {
                                                                                            ++n31;
                                                                                        }
                                                                                    }
                                                                                    s2.charAt(n31);
                                                                                }
                                                                                a2 = (char)(char4 - c20);
                                                                            }
                                                                            final char c21 = a2;
                                                                            char c23;
                                                                            int n61;
                                                                            char c22;
                                                                            final int n60 = c22 = (char)(n61 = (c23 = c21));
                                                                            int n64;
                                                                            int n63;
                                                                            final int n62 = n63 = (n64 = -1);
                                                                            Label_2003: {
                                                                                if (i == 0) {
                                                                                    if (n60 <= n62) {
                                                                                        break Label_2003;
                                                                                    }
                                                                                    n61 = (c22 = (c23 = c21));
                                                                                    final int n65;
                                                                                    n63 = (n65 = (n64 = 10));
                                                                                }
                                                                                if (i == 0) {
                                                                                    if (c22 >= n62) {
                                                                                        break Label_2003;
                                                                                    }
                                                                                    n61 = s2.length() - n31;
                                                                                    n63 = 2;
                                                                                }
                                                                                if (i != 0) {
                                                                                    break Label_2997;
                                                                                }
                                                                                if (n61 < n63) {
                                                                                    break Label_2997;
                                                                                }
                                                                                final char c25;
                                                                                final char c24 = c25 = (char)(s2.charAt(n31 + 1) - 48);
                                                                                if (i == 0) {
                                                                                    if (c25 > -1) {
                                                                                        final char c26 = c24;
                                                                                        if (i == 0) {
                                                                                            if (c26 < '\n') {
                                                                                                final int n66 = s2.length() - n31;
                                                                                                final int n67 = 2;
                                                                                                if (i != 0) {
                                                                                                    break Label_2997;
                                                                                                }
                                                                                                if (n66 <= n67) {
                                                                                                    break Label_2997;
                                                                                                }
                                                                                                n28 = c24 + '\n';
                                                                                                n31 += 2;
                                                                                                if (i == 0) {
                                                                                                    break Label_2006;
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                                n28 = c25;
                                                                                ++n31;
                                                                                if (i == 0) {
                                                                                    break Label_2006;
                                                                                }
                                                                            }
                                                                            --n31;
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                            final int n68;
                                                            if (i == 0) {
                                                                if (n57 >= 0) {
                                                                    final int n59;
                                                                    n68 = (n59 = n27);
                                                                    if (i == 0) {
                                                                        if (n68 < 16) {
                                                                            color = this.b(n27);
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                            if (i != 0) {
                                                                break Label_2088;
                                                            }
                                                            if (n68 >= 0) {
                                                                n69 = n28;
                                                                n70 = 16;
                                                                if (i != 0) {
                                                                    break Label_2088;
                                                                }
                                                                if (n69 < n70) {
                                                                    color2 = this.b(n28);
                                                                }
                                                            }
                                                        }
                                                        char2 = s2.charAt(n31);
                                                    }
                                                    n47 = char2;
                                                    this.h = fontMetrics.charWidth((char)n47);
                                                }
                                                if (n69 != n70) {
                                                    final char c23;
                                                    final char c27 = (char)(n71 = (c23 = (char)n47));
                                                    final int n64;
                                                    final int n72 = length = (n64 = 3);
                                                    if (i != 0) {
                                                        break Label_2997;
                                                    }
                                                    if (i != 0) {
                                                        break Label_2980;
                                                    }
                                                    if (c27 != n72) {
                                                        final char c28 = (char)(n71 = n47);
                                                        final int n73 = length = 15;
                                                        if (i != 0) {
                                                            break Label_2980;
                                                        }
                                                        if (c28 != n73) {
                                                            final char c29 = (char)(n71 = n47);
                                                            final int n74 = length = 2;
                                                            if (i != 0) {
                                                                break Label_2980;
                                                            }
                                                            if (c29 != n74) {
                                                                final char c30 = (char)(n71 = n47);
                                                                final int n75 = length = 31;
                                                                if (i != 0) {
                                                                    break Label_2980;
                                                                }
                                                                if (c30 != n75) {
                                                                    final char c31 = (char)(n71 = n47);
                                                                    final int n76 = length = 22;
                                                                    if (i != 0) {
                                                                        break Label_2980;
                                                                    }
                                                                    if (c31 != n76) {
                                                                        int d9;
                                                                        final int n77 = d9 = (this.d ? 1 : 0);
                                                                        if (i == 0) {
                                                                            final int n78;
                                                                            final int width3;
                                                                            Label_2459: {
                                                                                if (n77 == 0) {
                                                                                    n78 = n21 + this.h + 1;
                                                                                    width3 = size.width;
                                                                                    if (i != 0) {
                                                                                        break Label_2459;
                                                                                    }
                                                                                    if (n78 >= width3) {
                                                                                        final int n79 = n23 - fontMetrics.getAscent() + this.c;
                                                                                        Label_2451: {
                                                                                            if (i == 0) {
                                                                                                Label_2450: {
                                                                                                    if (n79 >= 0) {
                                                                                                        final int n80 = n23 - fontMetrics.getAscent();
                                                                                                        final int height4 = size.height;
                                                                                                        if (i != 0) {
                                                                                                            break Label_2450;
                                                                                                        }
                                                                                                        if (n80 <= height4) {
                                                                                                            final int length2 = sb.length();
                                                                                                            if (i != 0) {
                                                                                                                break Label_2451;
                                                                                                            }
                                                                                                            if (length2 > 0) {
                                                                                                                int s4;
                                                                                                                final int n81 = s4 = (this.s ? 1 : 0);
                                                                                                                Label_2417: {
                                                                                                                    Label_2403: {
                                                                                                                        if (i == 0) {
                                                                                                                            if (n81 == 0) {
                                                                                                                                break Label_2403;
                                                                                                                            }
                                                                                                                            final int n82;
                                                                                                                            s4 = (n82 = n2 + n31);
                                                                                                                        }
                                                                                                                        final int p = this.p;
                                                                                                                        int n84 = 0;
                                                                                                                        int n83 = 0;
                                                                                                                        Label_2290: {
                                                                                                                            if (i == 0) {
                                                                                                                                if (n81 < p) {
                                                                                                                                    break Label_2403;
                                                                                                                                }
                                                                                                                                n83 = (s4 = (n84 = n2 + n31));
                                                                                                                                if (i != 0) {
                                                                                                                                    break Label_2290;
                                                                                                                                }
                                                                                                                                final int q = this.q;
                                                                                                                            }
                                                                                                                            if (s4 > p) {
                                                                                                                                break Label_2403;
                                                                                                                            }
                                                                                                                            n84 = (n83 = n3);
                                                                                                                        }
                                                                                                                        Label_2320: {
                                                                                                                            if (i == 0) {
                                                                                                                                if (n83 != 0) {
                                                                                                                                    break Label_2320;
                                                                                                                                }
                                                                                                                                graphics2.drawString(sb.toString(), n22, n23);
                                                                                                                                n84 = 1;
                                                                                                                            }
                                                                                                                            n3 = n84;
                                                                                                                            if (i == 0) {
                                                                                                                                break Label_2417;
                                                                                                                            }
                                                                                                                        }
                                                                                                                        graphics2.setColor(this.a.g.darker());
                                                                                                                        graphics2.fillRect(n22, n, this.h, this.c);
                                                                                                                        graphics2.setColor(this.a.e);
                                                                                                                        graphics2.drawString(sb.toString(), n22, n23);
                                                                                                                        this.N.append(sb.toString());
                                                                                                                        graphics2.setColor(e);
                                                                                                                        if (i == 0) {
                                                                                                                            break Label_2417;
                                                                                                                        }
                                                                                                                    }
                                                                                                                    graphics2.drawString(sb.toString(), n22, n23);
                                                                                                                }
                                                                                                                sb.setLength(0);
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                    n21 = (n22 = this.f + 2);
                                                                                                    final int n85;
                                                                                                    n20 = (n85 = n20 + this.c);
                                                                                                    final int l = this.K;
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                        n23 = n79;
                                                                                    }
                                                                                }
                                                                                final int m = this.K;
                                                                            }
                                                                            d9 = n78 - width3;
                                                                        }
                                                                        final int n86 = d9;
                                                                        n = n86 - fontMetrics.getAscent();
                                                                        final int n87 = n + this.c;
                                                                        if (i == 0) {
                                                                            Label_2964: {
                                                                                if (n87 >= 0) {
                                                                                    final int n88 = n;
                                                                                    final int height5 = size.height;
                                                                                    if (i != 0) {
                                                                                        break Label_2964;
                                                                                    }
                                                                                    if (n88 <= height5) {
                                                                                        int s5;
                                                                                        final int n89 = s5 = (this.s ? 1 : 0);
                                                                                        Label_2950: {
                                                                                            Label_2695: {
                                                                                                if (i == 0) {
                                                                                                    if (n89 == 0) {
                                                                                                        break Label_2695;
                                                                                                    }
                                                                                                    final int n90;
                                                                                                    s5 = (n90 = n2 + n31);
                                                                                                }
                                                                                                final int p2 = this.p;
                                                                                                int n91 = 0;
                                                                                                int length3 = 0;
                                                                                                Label_2552: {
                                                                                                    if (i == 0) {
                                                                                                        if (n89 < p2) {
                                                                                                            break Label_2695;
                                                                                                        }
                                                                                                        length3 = (s5 = (n91 = n2 + n31));
                                                                                                        if (i != 0) {
                                                                                                            break Label_2552;
                                                                                                        }
                                                                                                        final int q2 = this.q;
                                                                                                    }
                                                                                                    if (s5 > p2) {
                                                                                                        break Label_2695;
                                                                                                    }
                                                                                                    n91 = (length3 = sb.length());
                                                                                                }
                                                                                                if (i == 0) {
                                                                                                    if (length3 > 0) {
                                                                                                        final int n92 = n3;
                                                                                                        Label_2666: {
                                                                                                            if ((i == 0 && n92 != 0) || n92 == 1) {
                                                                                                                graphics2.setColor(this.a.g.darker());
                                                                                                                graphics2.fillRect(n22, n, this.h, this.c);
                                                                                                                graphics2.setColor(this.a.e);
                                                                                                                graphics2.drawString(sb.toString(), n22, n23);
                                                                                                                this.N.append(sb.toString());
                                                                                                                if (i == 0) {
                                                                                                                    break Label_2666;
                                                                                                                }
                                                                                                            }
                                                                                                            graphics2.drawString(sb.toString(), n22, n23);
                                                                                                        }
                                                                                                        n3 = 1;
                                                                                                    }
                                                                                                    sb.setLength(0);
                                                                                                    n22 = n21;
                                                                                                    n91 = n86;
                                                                                                }
                                                                                                n23 = n91;
                                                                                                graphics2.setColor(e);
                                                                                                if (i == 0) {
                                                                                                    break Label_2950;
                                                                                                }
                                                                                            }
                                                                                            final Color color3 = color2;
                                                                                            final Color white = Color.white;
                                                                                            Label_2768: {
                                                                                                if (i != 0 || color3 != white) {
                                                                                                    if (color3 != white) {
                                                                                                        graphics2.setColor(color2);
                                                                                                        graphics2.fillRect(n21, n, this.h, this.c);
                                                                                                        graphics2.setColor(e);
                                                                                                        if (i == 0) {
                                                                                                            break Label_2768;
                                                                                                        }
                                                                                                    }
                                                                                                    graphics2.fillRect(n21, n, this.h, this.c);
                                                                                                }
                                                                                            }
                                                                                            Label_2909: {
                                                                                                if (d != font3) {
                                                                                                    if (sb.length() > 0) {
                                                                                                        graphics2.drawString(sb.toString(), n22, n23);
                                                                                                    }
                                                                                                    final Color color4 = color;
                                                                                                    Label_2820: {
                                                                                                        if (i == 0) {
                                                                                                            if (color4 == e) {
                                                                                                                break Label_2820;
                                                                                                            }
                                                                                                            graphics2.setColor(color);
                                                                                                        }
                                                                                                        e = color4;
                                                                                                    }
                                                                                                    sb.setLength(0);
                                                                                                    n22 = n21;
                                                                                                    n23 = n86;
                                                                                                    d = font3;
                                                                                                    graphics2.setFont(d);
                                                                                                    if (i == 0) {
                                                                                                        break Label_2909;
                                                                                                    }
                                                                                                }
                                                                                                if (color != e) {
                                                                                                    final int length4 = sb.length();
                                                                                                    if (i == 0) {
                                                                                                        if (length4 > 0) {
                                                                                                            graphics2.drawString(sb.toString(), n22, n23);
                                                                                                        }
                                                                                                        sb.setLength(0);
                                                                                                        n22 = n21;
                                                                                                    }
                                                                                                    n23 = length4;
                                                                                                    graphics2.setColor(color);
                                                                                                    e = color;
                                                                                                }
                                                                                            }
                                                                                            if (n29 != 0) {
                                                                                                graphics2.drawLine(n21, n86 + fontMetrics.getDescent() - 3, n21 + this.h - 1, n86 + fontMetrics.getDescent() - 3);
                                                                                            }
                                                                                        }
                                                                                        sb.append((char)n47);
                                                                                    }
                                                                                }
                                                                                final int h = this.h;
                                                                            }
                                                                        }
                                                                        n21 = n87;
                                                                        ++n26;
                                                                        ++n31;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            n71 = n31;
                                            length = s2.length();
                                        }
                                        if (n71 < length) {
                                            final int n93 = n;
                                            final int height6 = size.height;
                                            if (i == 0) {
                                                if (n93 <= height6) {
                                                    continue;
                                                }
                                            }
                                        }
                                        break;
                                    }
                                    n2 = n32 + n33;
                                    length5 = sb.length();
                                    if (i != 0) {
                                        break Label_3221;
                                    }
                                    if (length5 > 0) {
                                        int s6;
                                        final int n94 = s6 = (this.s ? 1 : 0);
                                        Label_3193: {
                                            if (i == 0) {
                                                if (n94 == 0) {
                                                    break Label_3193;
                                                }
                                                final int n95;
                                                s6 = (n95 = n2);
                                            }
                                            final int p3 = this.p;
                                            int n97 = 0;
                                            int n96 = 0;
                                            Label_3059: {
                                                if (i == 0) {
                                                    if (n94 < p3) {
                                                        break Label_3193;
                                                    }
                                                    n96 = (s6 = (n97 = n2));
                                                    if (i != 0) {
                                                        break Label_3059;
                                                    }
                                                    final int q3 = this.q;
                                                }
                                                if (s6 > p3) {
                                                    break Label_3193;
                                                }
                                                n97 = (n96 = n3);
                                            }
                                            Label_3185: {
                                                Label_3171: {
                                                    Label_3073: {
                                                        if (i == 0) {
                                                            if (n96 != 0) {
                                                                break Label_3073;
                                                            }
                                                            n97 = n26;
                                                        }
                                                        if (n97 != 1) {
                                                            break Label_3171;
                                                        }
                                                    }
                                                    graphics2.setColor(this.a.g);
                                                    graphics2.fillRect(n22, n, this.h, this.c);
                                                    graphics2.setColor(this.a.e);
                                                    graphics2.drawString(sb.toString(), n22, n23);
                                                    this.N.append(String.valueOf(sb.toString()) + "\n");
                                                    graphics2.setColor(e);
                                                    if (i == 0) {
                                                        break Label_3185;
                                                    }
                                                }
                                                graphics2.drawString(sb.toString(), n22, n23);
                                            }
                                            n3 = 1;
                                            if (i == 0) {
                                                break Label_3207;
                                            }
                                        }
                                        graphics2.drawString(sb.toString(), n22, n23);
                                    }
                                }
                                c3 = c3.c;
                                final int n98 = n20 + this.c;
                            }
                            n20 = length5;
                        }
                        if (c3 != null) {
                            n99 = (length6 = n);
                            if (i != 0 || i != 0) {
                                break Label_3251;
                            }
                            if (n99 <= size.height) {
                                continue;
                            }
                        }
                        break;
                    }
                    final boolean t;
                    length6 = ((t = this.t) ? 1 : 0);
                }
                d d10 = null;
                Label_3296: {
                    if (i == 0) {
                        if (n99 != 0) {
                            this.a(graphics2);
                        }
                        graphics.drawImage(image, 0, 0, this);
                        graphics2.dispose();
                        d10 = this;
                        if (i != 0) {
                            break Label_3296;
                        }
                        length6 = this.N.length();
                    }
                    if (length6 <= 0) {
                        return;
                    }
                    d10 = this;
                }
                d10.e = this.N.toString();
                final StringSelection stringSelection = new StringSelection(this.N.toString());
                this.a.cc.setContents(stringSelection, stringSelection);
                this.N.setLength(0);
                return;
            }
            if (kb == null) {
                goto Label_0958;
            }
            continue;
        }
    }
    
    protected Color b(final int n) {
        int n2 = n;
        final int n3 = -1;
        if (fb.m == 0) {
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
        this.k.a(new lb(this, s));
        this.f();
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    static {
        final String[] p = new String[14];
        final int n = 0;
        final char[] charArray = "JH".toCharArray();
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
                    c2 = 'b';
                    break;
                }
                case 2: {
                    c2 = '\u0012';
                    break;
                }
                case 3: {
                    c2 = '\u0007';
                    break;
                }
                default: {
                    c2 = '{';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        p[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "JJ".toCharArray();
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
                    c4 = 'b';
                    break;
                }
                case 2: {
                    c4 = '\u0012';
                    break;
                }
                case 3: {
                    c4 = '\u0007';
                    break;
                }
                default: {
                    c4 = '{';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        p[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "\u0007\u0015e)".toCharArray();
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
                    c6 = 'b';
                    break;
                }
                case 2: {
                    c6 = '\u0012';
                    break;
                }
                case 3: {
                    c6 = '\u0007';
                    break;
                }
                default: {
                    c6 = '{';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        p[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "JK".toCharArray();
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
                    c8 = 'b';
                    break;
                }
                case 2: {
                    c8 = '\u0012';
                    break;
                }
                case 3: {
                    c8 = '\u0007';
                    break;
                }
                default: {
                    c8 = '{';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        p[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "JE:".toCharArray();
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
                    c10 = 'b';
                    break;
                }
                case 2: {
                    c10 = '\u0012';
                    break;
                }
                case 3: {
                    c10 = '\u0007';
                    break;
                }
                default: {
                    c10 = '{';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        p[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "J&".toCharArray();
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
                    c12 = 'b';
                    break;
                }
                case 2: {
                    c12 = '\u0012';
                    break;
                }
                case 3: {
                    c12 = '\u0007';
                    break;
                }
                default: {
                    c12 = '{';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        p[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "JE".toCharArray();
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
                    c14 = 'b';
                    break;
                }
                case 2: {
                    c14 = '\u0012';
                    break;
                }
                case 3: {
                    c14 = '\u0007';
                    break;
                }
                default: {
                    c14 = '{';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        p[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "\u0018\u0016fwA_M".toCharArray();
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
                    c16 = 'b';
                    break;
                }
                case 2: {
                    c16 = '\u0012';
                    break;
                }
                case 3: {
                    c16 = '\u0007';
                    break;
                }
                default: {
                    c16 = '{';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        p[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "J]".toCharArray();
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
                    c18 = 'b';
                    break;
                }
                case 2: {
                    c18 = '\u0012';
                    break;
                }
                case 3: {
                    c18 = '\u0007';
                    break;
                }
                default: {
                    c18 = '{';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        p[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = "HK".toCharArray();
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
                    c20 = 'b';
                    break;
                }
                case 2: {
                    c20 = '\u0012';
                    break;
                }
                case 3: {
                    c20 = '\u0007';
                    break;
                }
                default: {
                    c20 = '{';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        p[n28] = new String(charArray10).intern();
        final int n31 = 10;
        final char[] charArray11 = "KK".toCharArray();
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
                    c22 = 'b';
                    break;
                }
                case 2: {
                    c22 = '\u0012';
                    break;
                }
                case 3: {
                    c22 = '\u0007';
                    break;
                }
                default: {
                    c22 = '{';
                    break;
                }
            }
            charArray11[n33] = (char)(c21 ^ c22);
        }
        p[n31] = new String(charArray11).intern();
        final int n34 = 11;
        final char[] charArray12 = "J\u0012".toCharArray();
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
                    c24 = 'b';
                    break;
                }
                case 2: {
                    c24 = '\u0012';
                    break;
                }
                case 3: {
                    c24 = '\u0007';
                    break;
                }
                default: {
                    c24 = '{';
                    break;
                }
            }
            charArray12[n36] = (char)(c23 ^ c24);
        }
        p[n34] = new String(charArray12).intern();
        final int n37 = 12;
        final char[] charArray13 = "JM".toCharArray();
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
                    c26 = 'b';
                    break;
                }
                case 2: {
                    c26 = '\u0012';
                    break;
                }
                case 3: {
                    c26 = '\u0007';
                    break;
                }
                default: {
                    c26 = '{';
                    break;
                }
            }
            charArray13[n39] = (char)(c25 ^ c26);
        }
        p[n37] = new String(charArray13).intern();
        final int n40 = 13;
        final char[] charArray14 = "/\u0000~f\u0015\u001b".toCharArray();
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
                    c28 = 'b';
                    break;
                }
                case 2: {
                    c28 = '\u0012';
                    break;
                }
                case 3: {
                    c28 = '\u0007';
                    break;
                }
                default: {
                    c28 = '{';
                    break;
                }
            }
            charArray14[n42] = (char)(c27 ^ c28);
        }
        p[n40] = new String(charArray14).intern();
        d.P = p;
    }
}
