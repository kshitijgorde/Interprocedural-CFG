// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

import java.util.TimeZone;
import javax.swing.JComponent;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.event.MouseEvent;
import b.a.a.e;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import b.a.d.d;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Font;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.image.BufferedImage;
import b.a.c.q;
import javax.swing.JPanel;
import b.a.e.l;
import java.awt.event.ActionListener;
import b.a.c.a;
import java.awt.LayoutManager;
import b.a.e.m;

public class n extends m implements LayoutManager, a, ActionListener
{
    protected Thread c;
    protected int d;
    protected int e;
    protected int f;
    protected l g;
    protected String h;
    protected int i;
    protected JPanel j;
    protected l k;
    protected a.a.a.m l;
    protected q m;
    protected transient boolean n;
    protected boolean o;
    protected a.a.a.a p;
    protected boolean q;
    protected BufferedImage r;
    protected String s;
    protected String t;
    protected double u;
    protected String v;
    protected String w;
    protected String x;
    protected double y;
    protected String[] z;
    protected String[] A;
    protected String[] B;
    protected static final Image C;
    protected static final Image D;
    protected static final Image E;
    protected static final Image F;
    protected static final Image[] G;
    protected static final Rectangle H;
    protected static final Rectangle I;
    protected static final Rectangle J;
    protected static final Color K;
    protected static final Color L;
    protected static final Color M;
    protected static final Color N;
    protected static final Color O;
    protected static final Color P;
    protected static final Color Q;
    protected static final Color R;
    protected static final Font S;
    protected static final Font T;
    protected static final Font U;
    protected static final Font V;
    protected static final Font W;
    protected static final Font X;
    protected static final int Y;
    protected static final int[] Z;
    protected static final int[] ab;
    protected static final int[] bb;
    protected static Image cb;
    protected static final Point db;
    static Class eb;
    
    public n(final a.a.a.a p) {
        this.c = null;
        this.d = Integer.MIN_VALUE;
        this.e = 0;
        this.f = 0;
        this.m = b.a.c.q.a("C088d13m00sW43d01m00sN00000P360N2DWaukesha, WI");
        this.n = false;
        this.q = true;
        this.p = p;
        this.h = p.c("HELP_URL");
        this.setLayout(this);
        this.setBackground(a.a.a.n.Q);
        this.enableEvents(16L);
        (this.j = new JPanel(new FlowLayout())).setBackground(a.a.a.n.O);
        this.add(this.j);
        final JLabel label = new JLabel("|");
        label.setFont(a.a.a.n.V);
        label.setForeground(a.a.a.n.N);
        this.j.add(label);
        (this.k = new l()).setFont(a.a.a.n.V);
        this.k.a(a.a.a.n.M);
        this.k.b(a.a.a.n.N);
        this.k.setText(this.b(p.c("CHANGE_LOCATION_LINK")));
        this.k.a(this);
        this.j.add(this.k);
        final JLabel label2 = new JLabel("|");
        label2.setFont(a.a.a.n.V);
        label2.setForeground(a.a.a.n.N);
        this.j.add(label2);
        (this.g = new l()).setFont(a.a.a.n.V);
        this.g.a(a.a.a.n.M);
        this.g.b(a.a.a.n.N);
        this.g.setText(this.b(p.c("HELP_LINK")));
        this.g.a(this);
        this.j.add(this.g);
        (this.l = new a.a.a.m()).setBackground(a.a.a.n.Q);
        this.l.setForeground(Color.white);
        this.l.setFont(a.a.a.n.W);
        this.l.a("");
        this.add(this.l);
        this.o = b.a.d.d.b((Object)p.getParameter("PHASECAPTION"));
        this.l.addMouseListener(new b(this, p));
    }
    
    public void a(final q m) {
        if (!this.m.equals(m)) {
            this.m = m;
            this.g();
        }
    }
    
    public void a(final String h) {
        this.h = h;
    }
    
    protected String b(final String s) {
        if (b.a.d.d.b("1.4") >= 0) {
            return s;
        }
        return "<font face=SansSerif size=1><b>" + s + "</b></size>";
    }
    
    public synchronized void a(final Graphics graphics) {
        graphics.drawImage(a.a.a.n.C, 0, 0, this);
        graphics.drawImage(a.a.a.n.G[this.e], 0, a.a.a.n.C.getHeight(this), this);
        if (this.s == null) {
            return;
        }
        b.a.d.b.a(graphics, true);
        graphics.setFont(a.a.a.n.S);
        graphics.setColor(a.a.a.n.K);
        graphics.drawString(this.s, this.i - 3 - graphics.getFontMetrics().stringWidth(this.s), 15);
        graphics.setFont(a.a.a.n.U);
        graphics.setColor(a.a.a.n.P);
        graphics.drawString(this.t, 8, 60);
        b.a.d.b.a(graphics, b.a.d.d.a());
        graphics.setFont(a.a.a.n.T);
        graphics.setColor(a.a.a.n.L);
        if (this.e == 0) {
            this.b(graphics, this.z[0], a.a.a.n.Z[0], 92);
            this.b(graphics, this.A[0], a.a.a.n.Z[0], 108);
            this.b(graphics, this.z[1], a.a.a.n.Z[1], 92);
            this.b(graphics, this.A[1], a.a.a.n.Z[1], 108);
            graphics.drawString(this.v, 319, 92);
            graphics.drawString(this.x, 319, 108);
            this.a(graphics, a.a.a.n.db.x, a.a.a.n.db.y);
            graphics.setFont(a.a.a.n.X);
            this.a(graphics, this.w, a.a.a.n.ab[1], 125);
        }
        else if (this.e == 1) {
            this.b(graphics, this.z[2], a.a.a.n.Z[0], 92);
            this.b(graphics, this.A[2], a.a.a.n.Z[0], 108);
            this.b(graphics, this.z[3], a.a.a.n.Z[1], 92);
            this.b(graphics, this.A[3], a.a.a.n.Z[1], 108);
            b.a.d.b.a(graphics, true);
            graphics.setFont(a.a.a.n.X);
            this.a(graphics, this.B[2], a.a.a.n.ab[0], 123);
            this.a(graphics, this.B[3], a.a.a.n.ab[1], 123);
        }
        else if (this.e == 2) {
            this.b(graphics, this.z[4], a.a.a.n.Z[0], 92);
            this.b(graphics, this.A[4], a.a.a.n.Z[0], 108);
            this.b(graphics, this.z[5], a.a.a.n.Z[1], 92);
            this.b(graphics, this.A[5], a.a.a.n.Z[1], 108);
            this.b(graphics, this.z[6], a.a.a.n.Z[2], 92);
            this.b(graphics, this.A[6], a.a.a.n.Z[2], 108);
            b.a.d.b.a(graphics, true);
            graphics.setFont(a.a.a.n.X);
            this.a(graphics, this.B[4], a.a.a.n.ab[0], 123);
            this.a(graphics, this.B[5], a.a.a.n.ab[1], 123);
            this.a(graphics, this.B[6], a.a.a.n.ab[2], 123);
        }
    }
    
    protected void a(final Graphics graphics, final String s, final int n, final int n2) {
        graphics.drawString(s, n - graphics.getFontMetrics().stringWidth(s) / 2, n2);
    }
    
    protected void b(final Graphics graphics, final String s, final int n, final int n2) {
        if (!s.startsWith("0")) {
            graphics.drawString(s, n, n2);
        }
        else {
            graphics.drawString(s.substring(1), n + graphics.getFontMetrics().stringWidth("0"), n2);
        }
    }
    
    protected void a(final Graphics graphics, final int n, final int n2) {
        double u = this.u;
        if (172.5 <= u && u <= 187.5) {
            u = 180.0;
        }
        else if (u < 7.5 || u > 352.5) {
            u = 0.0;
        }
        if (this.y != u || this.r == null) {
            this.y = u;
            final int width = n.cb.getWidth(null);
            final double n3 = width / 2.0;
            if (this.r == null) {
                this.r = new BufferedImage(width, width, 2);
            }
            final double n4 = b.a.a.e.b(u) + 1.0;
            double n5 = 0.0;
            for (int i = 0; i < width; ++i) {
                final double n6 = i - n3;
                final double n7 = n6 - n6 / n3 * 0.5;
                final double sqrt = Math.sqrt(Math.max(n3 * n3 - n7 * n7, 0.0));
                int n8;
                int n9;
                int n10;
                if (u == 0.0) {
                    n8 = Math.max((int)Math.floor(n3 - sqrt - 1.5), 0);
                    n9 = Math.min((int)Math.ceil(n3 + sqrt + 1.5), width - 1);
                    n10 = -1;
                }
                else if (u < 180.0) {
                    final double n11 = n3 - sqrt;
                    final double n12 = n11 + sqrt * n4;
                    n8 = Math.max((int)Math.floor(n11 - 1.5), 0);
                    n9 = (int)Math.floor(n12);
                    n10 = Math.min(n9 + 1, width - 1);
                    n5 = 1.0 - (n12 - n9) * 127.0 / 255.0;
                }
                else {
                    final double n13 = n3 + sqrt;
                    final double n14 = n13 - sqrt * n4;
                    n9 = Math.min((int)Math.ceil(n13 + 1.5), width - 1);
                    n8 = (int)Math.ceil(n14);
                    n10 = Math.max(n8 - 1, 0);
                    n5 = 1.0 - (n8 - n14) * 127.0 / 255.0;
                }
                for (int j = 0; j < width; ++j) {
                    final int rgb = ((BufferedImage)n.cb).getRGB(j, i);
                    final int n15 = (rgb & 0xFF000000) >> 24;
                    int n16 = (rgb & 0xFF0000) >> 16;
                    int n17 = (rgb & 0xFF00) >> 8;
                    int n18 = rgb & 0xFF;
                    if (j == n10) {
                        n16 *= (int)n5;
                        n17 *= (int)n5;
                        n18 *= (int)n5;
                    }
                    else if (n8 <= j && j <= n9) {
                        n16 = n16 * 128 / 255;
                        n17 = n17 * 128 / 255;
                        n18 = n18 * 128 / 255;
                    }
                    this.r.setRGB(j, i, n15 << 24 | n16 << 16 | n17 << 8 | n18);
                }
            }
        }
        graphics.drawImage(this.r, n, n2, this);
    }
    
    protected synchronized void b(final MouseEvent mouseEvent) {
        if (mouseEvent.getID() == 500) {
            final Point point = mouseEvent.getPoint();
            int e = this.e;
            if (a.a.a.n.H.contains(point)) {
                e = 0;
            }
            else if (a.a.a.n.I.contains(point)) {
                e = 1;
            }
            else if (a.a.a.n.J.contains(point)) {
                e = 2;
            }
            else if (mouseEvent.isShiftDown() && point.y < 30) {
                if (point.x < 10) {
                    --this.f;
                }
                else if (point.x < 20) {
                    this.f = 0;
                }
                else {
                    ++this.f;
                }
                this.g();
            }
            if (this.e != e) {
                this.e = e;
                this.repaint();
            }
        }
    }
    
    public synchronized void e() {
        if (this.c == null) {
            this.n = true;
            (this.c = new f(this)).start();
        }
    }
    
    public void f() {
        final Thread c;
        synchronized (this) {
            c = this.c;
        }
        if (c != null) {
            this.n = false;
            c.interrupt();
            b.a.d.d.a(c, 3000L);
        }
    }
    
    protected synchronized void g() {
        if (this.c != null) {
            this.q = true;
            this.c.interrupt();
        }
    }
    
    public void addLayoutComponent(final String s, final Component component) {
    }
    
    public void removeLayoutComponent(final Component component) {
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        return new Dimension(415, 146);
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        return new Dimension(415, 146);
    }
    
    public void layoutContainer(final Container container) {
        final int width = this.getWidth();
        final int height = this.getHeight();
        final Dimension preferredSize = this.j.getPreferredSize();
        final int height2 = this.l.getPreferredSize().height;
        this.i = width - 3 - preferredSize.width;
        this.j.setBounds(this.i, a.a.a.n.Y, preferredSize.width, preferredSize.height);
        this.l.setBounds(0, height - 18 + (18 - height2) / 2, width, height2);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.k) {
            q m = a.a.a.j.a(this, this.p);
            if (m != null) {
                String s = m.g();
                final int index;
                if ((index = s.indexOf(" (")) > 0 && s.endsWith(")")) {
                    s = s.substring(0, index);
                    m = m.a(7, s);
                }
                if (m.f() == 1) {
                    m = m.a(4, new Integer(TimeZone.getDefault().getRawOffset() / 60000)).a(7, s + "*");
                }
                this.m = m;
                this.p.a(this.m);
                this.g();
            }
        }
        else if (source == this.g) {
            this.p.a(this.h, "_blank");
        }
    }
    
    static Class c(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        C = b.a.e.a.a("$/top_panel.png", (n.eb == null) ? (n.eb = c("a.a.a.n")) : n.eb);
        D = b.a.e.a.a("$/tab_1_sun_moon.png", (n.eb == null) ? (n.eb = c("a.a.a.n")) : n.eb);
        E = b.a.e.a.a("$/tab_2_merc_ven.png", (n.eb == null) ? (n.eb = c("a.a.a.n")) : n.eb);
        F = b.a.e.a.a("$/tab_3_mr_jp_st.png", (n.eb == null) ? (n.eb = c("a.a.a.n")) : n.eb);
        G = new Image[] { n.D, n.E, n.F };
        H = new Rectangle(0, 27, 85, 20);
        I = new Rectangle(88, 27, 105, 20);
        J = new Rectangle(195, 27, 140, 20);
        K = new Color(2314113);
        L = Color.white;
        M = new Color(2254557);
        N = new Color(1127304);
        O = new Color(10405075);
        P = new Color(10601940);
        Q = new Color(1386576);
        R = Color.white;
        S = new Font("SansSerif", 0, 12);
        T = new Font("SansSerif", 0, 11);
        U = new Font("SansSerif", 0, 9);
        V = new Font("SansSerif", 1, 10);
        W = new Font("SansSerif", 0, 12);
        X = new Font("SansSerif", 0, 9);
        Y = (d.a() ? 0 : -1) - ((d.b("1.4") < 0) ? 3 : 0);
        Z = new int[] { 48, 160, 279 };
        ab = new int[] { 122, 241, 373 };
        bb = new int[] { 0, 10, 1, 2, 4, 5, 6 };
        n.cb = b.a.e.a.a("$/full_moon.png", (n.eb == null) ? (n.eb = c("a.a.a.n")) : n.eb);
        db = new Point(216, 63);
        final int width = n.cb.getWidth(null);
        final BufferedImage cb = new BufferedImage(width, width, 2);
        final Graphics graphics = cb.getGraphics();
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < width; ++j) {
                cb.setRGB(j, i, 0);
            }
        }
        graphics.drawImage(n.cb, 0, 0, null);
        n.cb = cb;
    }
}
