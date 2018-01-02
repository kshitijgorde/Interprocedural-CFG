import java.awt.Component;
import java.awt.MediaTracker;
import java.applet.Applet;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Vector;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class h extends r
{
    static char[][] p;
    public static Image p;
    public static Image[] p;
    public static int p;
    private static final int[][] p;
    private int d;
    int a;
    private int n;
    private int v;
    private int i;
    private int l;
    private int b;
    private boolean p;
    private int[] p;
    private int[][] d;
    private int[][] a;
    private boolean[][] p;
    private Vector[] p;
    private static Font p;
    private static Font d;
    private static Font a;
    private static String p;
    private static int c;
    private static final String[] p;
    private static final String[] d;
    private static final int[] d;
    private static final int[] a;
    private static int[] n;
    private static int[] v;
    private static int[] i;
    private static int[] l;
    private static int[][] n;
    private Dimension p;
    private boolean d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int j;
    private int k;
    private static Color[] p;
    private static final Color p;
    private static final Color d;
    private static final Color a;
    private static final Color n;
    private static final Color v;
    private static final Color i;
    private static boolean a;
    
    public h(final y y) {
        super(y, 600);
        this.p = false;
        this.p = new int[600];
        this.d = new int[10][9];
        this.a = new int[10][9];
        this.p = new boolean[10][9];
        this.p = new Vector[] { new Vector(), new Vector() };
        this.d = false;
        this.l();
    }
    
    public final String d() {
        return "Xiangqi";
    }
    
    public final int d() {
        return 3;
    }
    
    public final int p(final int n) {
        return h.i[n] * 12;
    }
    
    protected final void l() {
        this.a = 0;
        this.n = 0;
        this.p[0].removeAllElements();
        this.p[1].removeAllElements();
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.a[i][j] = (this.d[i][j] = h.p[i][j]);
            }
        }
        this.d = 0;
        final boolean b = false;
        this.b = (b ? 1 : 0);
        this.l = (b ? 1 : 0);
        this.i = (b ? 1 : 0);
        this.v = (b ? 1 : 0);
    }
    
    protected final boolean d(final int n) {
        return this.p(n / 1000, n / 100 % 10, n / 10 % 10, n % 10);
    }
    
    protected final boolean a(final int n) {
        final int n2 = n / 1000;
        final int n3 = n / 100 % 10;
        final int n4 = n / 10 % 10;
        final int n5 = n % 10;
        final int n6 = this.d[n2][n3] / 10 % 2;
        if (this.d[n4][n5] != -1 || (n2 != n4 && this.d[n2][n3] % 10 == 6 && ((this.d[n2][n3] / 10 % 2 == 0 && n2 <= 4) || (this.d[n2][n3] / 10 % 2 == 1 && n2 >= 5)))) {
            this.a = 0;
            if (this.d[n4][n5] != -1) {
                this.p[n6].removeAllElements();
            }
        }
        this.p(n6);
        this.p(n2, n3, n4, n5);
        if (this.i() == super.l % 2 && super.b > 0) {
            super.p = System.currentTimeMillis() + super.b;
            super.b = 0;
        }
        this.p(n6, n2, n3, n4, n5);
        if (this.p[0].size() == 0 && this.p[1].size() == 0) {
            ++this.n;
        }
        if (this.p[0].size() == 0 == (this.p[1].size() == 0)) {
            ++this.a;
        }
        if (this.p(n6) && !this.p(1 - n6) && this.i() == n6) {
            super.p.l("<4>*** You potentially violate CXQ rules. Please be careful.  Change your move if neccessary");
        }
        if (this.a == 40 && this.i() != -1) {
            super.p.l("<12>!!! The game will be drawn soon if no further progress is made in the next few moves");
        }
        if ((super.l / 2 == 580 || this.n == 220) && this.i() != -1) {
            super.p.l("<12>!!! The game will be drawn soon, too many moves");
        }
        return false;
    }
    
    private final boolean n(final int n, final int n2) {
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (this.d[i][j] != -1 && this.d[i][j] / 10 % 2 != this.d[n][n2] / 10 % 2 && this.p(i, j, n, n2)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private final void p(final int n) {
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.p[i][j] = false;
                if (this.d[i][j] != -1 && this.d[i][j] / 10 % 2 != n) {
                    for (int k = 0; k < 10; ++k) {
                        for (int l = 0; l < 9; ++l) {
                            if (this.d[k][l] != -1 && this.d[k][l] / 10 % 2 == n) {
                                final int n2 = this.d[k][l] % 10;
                                final int n3 = this.d[i][j] % 10;
                                if (this.p(k, l, i, j)) {
                                    int n4;
                                    if (n3 == 0) {
                                        n4 = 1;
                                    }
                                    else if (n3 == 3 && n2 != 3) {
                                        n4 = 1;
                                    }
                                    else if (n3 == 6 && ((n == 0 && i <= 4) || (n == 1 && i >= 5))) {
                                        n4 = 0;
                                    }
                                    else {
                                        this.p(k, l, i, j);
                                        n4 = (this.n(i, j) ? 0 : 1);
                                        this.j();
                                    }
                                    if (n4 != 0) {
                                        this.p[i][j] = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    private final void d(final int n, final int n2, final int n3, final int n4) {
        for (int i = 0; i < this.p[n].size(); ++i) {
            final o o = new o(this.p[n].elementAt(i));
            if (o.p(n2, n3, n4)) {
                this.p[n].addElement(o);
            }
        }
        this.p[n].addElement(new o(n2, n3, n4));
    }
    
    private final void p(final int n, final int n2, final int n3, final int n4, final int n5) {
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (!this.p[i][j] && this.d[i][j] != -1 && this.d[i][j] / 10 % 2 != n) {
                    for (int k = 0; k < 10; ++k) {
                        for (int l = 0; l < 9; ++l) {
                            if (this.d[k][l] != -1 && this.d[k][l] / 10 % 2 == n && ((k == n4 && l == n5) || this.d[i][j] % 10 == 0 || (this.d[k][l] % 10 == 4 && ((n4 == k && (n5 - l) * (n5 - j) < 0) || (n5 == l && (n4 - k) * (n4 - i) < 0) || (n2 == k && (n3 - l) * (n3 - j) < 0) || (n3 == l && (n2 - k) * (n2 - i) < 0)))) && this.p(k, l, i, j)) {
                                final int n6 = this.d[k][l] % 10;
                                final int n7 = this.d[i][j] % 10;
                                int n8;
                                if (n6 == n7 && this.p(i, j, k, l)) {
                                    n8 = 0;
                                }
                                else if (n7 == 0) {
                                    n8 = 1;
                                }
                                else if (n6 == 0 || n6 == 6) {
                                    n8 = 0;
                                }
                                else if (n7 == 3 && n6 != 3) {
                                    n8 = 1;
                                }
                                else if (n7 == 6 && ((n == 0 && i <= 4) || (n == 1 && i >= 5))) {
                                    n8 = 0;
                                }
                                else {
                                    this.p(k, l, i, j);
                                    n8 = (this.n(i, j) ? 0 : 1);
                                    this.j();
                                }
                                if (n8 != 0) {
                                    this.d(n, super.l - 1, this.d[i][j], this.d[k][l]);
                                }
                            }
                        }
                    }
                }
            }
        }
        for (int n9 = 0; n9 < this.p[n].size(); ++n9) {
            final o o = this.p[n].elementAt(n9);
            if (o.p != super.l - 1) {
                this.p[n].removeElementAt(n9);
                --n9;
            }
            else {
                for (int n10 = 0; n10 < this.p[n].size(); ++n10) {
                    final o o2 = this.p[n].elementAt(n10);
                    if (o.p(o2) || (n10 > n9 && o2.d(o))) {
                        this.p[n].removeElementAt(n9);
                        --n9;
                        break;
                    }
                }
            }
        }
    }
    
    private final boolean p(final int n) {
        for (int i = 0; i < this.p[n].size(); ++i) {
            if (((o)this.p[n].elementAt(i)).p(1)) {
                return true;
            }
        }
        return false;
    }
    
    private final void c() {
        if (super.p.p().getSize().equals(this.p)) {
            return;
        }
        this.p = super.p.p().getSize();
        final int height = this.p.height;
        final int width = this.p.width;
        h.i[super.p] = Math.min((height - 3 * h.d[super.p] - 10) / 11, width / 12);
        h.n[super.p] = (width - h.i[super.p] * 8) / 2;
        h.v[super.p] = (height - h.i[super.p] * 9 - super.v) / 2;
        h.l[super.p] = h.n[super.p] - h.i[super.p];
        h.n[super.p][0] = h.v[super.p] + 10 * h.i[super.p];
        h.n[super.p][1] = h.v[super.p] - h.i[super.p] - h.d[super.p];
        h.c = super.p.p().getFontMetrics(h.a).stringWidth(h.p);
    }
    
    protected final void h() {
        super.p.p().repaint(h.l[super.p] + 10 * h.i[super.p] - 3 * h.a[super.p], h.n[super.p][0], 3 * h.a[super.p], h.d[super.p]);
        super.p.p().repaint(h.l[super.p] + 10 * h.i[super.p] - 3 * h.a[super.p], h.n[super.p][1], 3 * h.a[super.p], h.d[super.p]);
    }
    
    protected final void k() {
        this.d = false;
        final int n = -1;
        this.f = n;
        this.e = n;
    }
    
    private final void a(final Graphics graphics, final int n, final int n2) {
        final int n3 = h.i[super.p] / 6;
        graphics.drawLine(h.n[super.p] + n2 * h.i[super.p] - 3, h.v[super.p] + n * h.i[super.p] - 3, h.n[super.p] + n2 * h.i[super.p] - 3, h.v[super.p] + n * h.i[super.p] - 3 - n3);
        graphics.drawLine(h.n[super.p] + n2 * h.i[super.p] - 3, h.v[super.p] + n * h.i[super.p] + 3, h.n[super.p] + n2 * h.i[super.p] - 3, h.v[super.p] + n * h.i[super.p] + 3 + n3);
        graphics.drawLine(h.n[super.p] + n2 * h.i[super.p] - 3, h.v[super.p] + n * h.i[super.p] - 3, h.n[super.p] + n2 * h.i[super.p] - 3 - n3, h.v[super.p] + n * h.i[super.p] - 3);
        graphics.drawLine(h.n[super.p] + n2 * h.i[super.p] - 3, h.v[super.p] + n * h.i[super.p] + 3, h.n[super.p] + n2 * h.i[super.p] - 3 - n3, h.v[super.p] + n * h.i[super.p] + 3);
    }
    
    private final void n(final Graphics graphics, final int n, final int n2) {
        final int n3 = h.i[super.p] / 6;
        graphics.drawLine(h.n[super.p] + n2 * h.i[super.p] + 3, h.v[super.p] + n * h.i[super.p] - 3, h.n[super.p] + n2 * h.i[super.p] + 3, h.v[super.p] + n * h.i[super.p] - 3 - n3);
        graphics.drawLine(h.n[super.p] + n2 * h.i[super.p] + 3, h.v[super.p] + n * h.i[super.p] + 3, h.n[super.p] + n2 * h.i[super.p] + 3, h.v[super.p] + n * h.i[super.p] + 3 + n3);
        graphics.drawLine(h.n[super.p] + n2 * h.i[super.p] + 3, h.v[super.p] + n * h.i[super.p] - 3, h.n[super.p] + n2 * h.i[super.p] + 3 + n3, h.v[super.p] + n * h.i[super.p] - 3);
        graphics.drawLine(h.n[super.p] + n2 * h.i[super.p] + 3, h.v[super.p] + n * h.i[super.p] + 3, h.n[super.p] + n2 * h.i[super.p] + 3 + n3, h.v[super.p] + n * h.i[super.p] + 3);
    }
    
    private final void d(final Graphics graphics, final boolean b) {
        super.p.p();
        graphics.setColor(new Color(135, 131, 126));
        graphics.setFont(h.p);
        final int n = h.i[super.p] / 7;
        graphics.drawRect(h.n[super.p] - n, h.v[super.p] - n, 8 * h.i[super.p] + 2 * n, 9 * h.i[super.p] + 2 * n);
        for (int i = 0; i < 10; ++i) {
            final int n2 = b ? (9 - i) : i;
            graphics.drawString(h.d[n2], h.n[super.p] - h.i[super.p] + 7, h.v[super.p] + i * h.i[super.p] + 4);
            graphics.drawString(h.d[n2], h.n[super.p] + 9 * h.i[super.p] - 10, h.v[super.p] + i * h.i[super.p] + 4);
            graphics.drawLine(h.n[super.p], h.v[super.p] + i * h.i[super.p], h.n[super.p] + 8 * h.i[super.p], h.v[super.p] + i * h.i[super.p]);
        }
        for (int j = 0; j < 9; ++j) {
            final int n3 = b ? (8 - j) : j;
            graphics.drawString(h.p[n3], h.n[super.p] + j * h.i[super.p], h.v[super.p] - h.i[super.p] + 12);
            graphics.drawString(h.p[n3], h.n[super.p] + j * h.i[super.p], h.v[super.p] + 10 * h.i[super.p] - 4);
            graphics.drawLine(h.n[super.p] + j * h.i[super.p], h.v[super.p], h.n[super.p] + j * h.i[super.p], h.v[super.p] + 4 * h.i[super.p]);
            graphics.drawLine(h.n[super.p] + j * h.i[super.p], h.v[super.p] + 5 * h.i[super.p], h.n[super.p] + j * h.i[super.p], h.v[super.p] + 9 * h.i[super.p]);
        }
        graphics.drawLine(h.n[super.p], h.v[super.p] + 4 * h.i[super.p], h.n[super.p], h.v[super.p] + 5 * h.i[super.p]);
        graphics.drawLine(h.n[super.p] + 8 * h.i[super.p], h.v[super.p] + 4 * h.i[super.p], h.n[super.p] + 8 * h.i[super.p], h.v[super.p] + 5 * h.i[super.p]);
        graphics.drawLine(h.n[super.p] + 3 * h.i[super.p], h.v[super.p], h.n[super.p] + 5 * h.i[super.p], h.v[super.p] + 2 * h.i[super.p]);
        graphics.drawLine(h.n[super.p] + 5 * h.i[super.p], h.v[super.p], h.n[super.p] + 3 * h.i[super.p], h.v[super.p] + 2 * h.i[super.p]);
        graphics.drawLine(h.n[super.p] + 3 * h.i[super.p], h.v[super.p] + 7 * h.i[super.p], h.n[super.p] + 5 * h.i[super.p], h.v[super.p] + 9 * h.i[super.p]);
        graphics.drawLine(h.n[super.p] + 5 * h.i[super.p], h.v[super.p] + 7 * h.i[super.p], h.n[super.p] + 3 * h.i[super.p], h.v[super.p] + 9 * h.i[super.p]);
        for (int k = 2; k <= 7; k += 5) {
            for (int l = 1; l <= 7; l += 6) {
                this.a(graphics, k, l);
                this.n(graphics, k, l);
            }
        }
        for (int n4 = 3; n4 <= 6; n4 += 3) {
            for (int n5 = 0; n5 <= 6; n5 += 2) {
                this.n(graphics, n4, n5);
                this.a(graphics, n4, n5 + 2);
            }
        }
    }
    
    private final void p(final Graphics graphics, final boolean b) {
        super.p.p();
        graphics.setFont(h.d);
        for (int i = 0; i < 2; ++i) {
            final int n = b ? (1 - i) : i;
            graphics.setColor(new Color(51, 51, 51));
            graphics.fillRect(h.l[super.p], h.n[super.p][i], 10 * h.i[super.p], h.d[super.p]);
            graphics.setColor(new Color(150, 143, 122));
            final String s = (super.p[n] != null) ? (String.valueOf(super.p[n]) + " (" + super.p[n] + ")") : "[None]";
            if (h.p[n] != null) {
                graphics.drawImage(h.p[n], h.l[super.p] + 9, h.n[super.p][i] + 5, super.p.p());
            }
            graphics.drawString(s, h.l[super.p] + 25, h.n[super.p][i] + h.d[super.p] - 4);
            this.p(graphics, du.p(super.p[n].d()), h.l[super.p] + 10 * h.i[super.p] - 2 * h.a[super.p] - 5, h.n[super.p][i] + h.d[super.p] - 3, h.p[n], null);
            this.p(graphics, du.p(super.d[n].d()), h.l[super.p] + 10 * h.i[super.p] - h.a[super.p] - 5, h.n[super.p][i] + h.d[super.p] - 3, h.p[n], null);
            this.p(graphics, du.p(super.a[n].d()), h.l[super.p] + 10 * h.i[super.p] - 5, h.n[super.p][i] + h.d[super.p] - 3, h.p[n], null);
        }
        int n2 = super.l % 2;
        if (b) {
            n2 = 1 - n2;
        }
        graphics.drawImage(super.p, h.l[super.p] - 13, h.n[super.p][n2] + 4, super.p.p());
    }
    
    private final void p(final Graphics graphics, int n, int n2, final boolean b, final Color color) {
        graphics.setColor(color);
        if (b) {
            n = 9 - n;
            n2 = 8 - n2;
        }
        graphics.drawRect(h.n[super.p] + n2 * h.i[super.p] - h.p / 2 - 2, h.v[super.p] + n * h.i[super.p] - h.p / 2 - 2, h.p + 3, h.p + 3);
    }
    
    private final void p(final Graphics graphics, final int n, int n2, int n3) {
        final int n4 = n / 10 % 2 * 7 + n % 10;
        if (h.p == null || n == -1 || n4 < 0 || n4 >= 14) {
            return;
        }
        n2 -= h.p / 2;
        n3 -= h.p / 2;
        graphics.drawImage(h.p, n2, n3, n2 + h.p, n3 + h.p, h.p * n4, 0, h.p * (n4 + 1), h.p, super.p.p());
    }
    
    private static final void p(final Graphics graphics, final int n, final int n2) {
        graphics.setColor(h.p);
        graphics.fillRect(n, n2, 3, 3);
        graphics.setColor(h.d);
        graphics.fillRect(n, n2, 2, 2);
    }
    
    public final void p(final Graphics graphics, final Color color, final int n, final int n2) {
        final Rectangle clipBounds = graphics.getClipBounds();
        Rectangle intersection = new Rectangle(0, 0, n, n2);
        if (clipBounds != null) {
            intersection = clipBounds.intersection(intersection);
        }
        graphics.setClip(intersection.x, intersection.y, intersection.width, intersection.height);
        final int n3 = intersection.x - intersection.x % 3;
        final int n4 = intersection.y - intersection.y % 3;
        p(graphics, n3, n4);
        p(graphics, n3 + 3, n4);
        p(graphics, n3, n4 + 3);
        p(graphics, n3 + 3, n4 + 3);
        du.p(graphics, intersection.x, intersection.y, 3, 3, intersection.width, intersection.height);
        if (clipBounds != null) {
            graphics.setClip(clipBounds.x, clipBounds.y, clipBounds.width, clipBounds.height);
        }
    }
    
    private static final void d(final Graphics graphics, final int n, final int n2) {
        graphics.setColor(h.a);
        graphics.fillRect(n, n2, 8, 8);
        graphics.setColor(h.n);
        graphics.fillRect(n, n2, 4, 4);
        graphics.fillRect(n + 4, n2 + 4, 4, 4);
    }
    
    protected final void p(final Graphics graphics, final Rectangle rectangle) {
        final Rectangle clipBounds = graphics.getClipBounds();
        Rectangle intersection = rectangle;
        if (clipBounds != null) {
            intersection = clipBounds.intersection(intersection);
        }
        graphics.setClip(intersection.x, intersection.y, intersection.width, intersection.height);
        final int n = intersection.x - intersection.x % 8;
        final int n2 = intersection.y - intersection.y % 8;
        d(graphics, n, n2);
        d(graphics, n + 8, n2);
        d(graphics, n, n2 + 8);
        d(graphics, n + 8, n2 + 8);
        du.p(graphics, intersection.x, intersection.y, 8, 8, intersection.width, intersection.height);
        if (clipBounds != null) {
            graphics.setClip(clipBounds.x, clipBounds.y, clipBounds.width, clipBounds.height);
        }
    }
    
    public final void p(final Graphics graphics) {
        if (!h.a) {
            return;
        }
        final Color[] array = dw.p[super.p.p()];
        this.c();
        final boolean b = this.i() == 1 || (this.i() == -1 && this.p);
        final Rectangle rectangle = new Rectangle(h.n[super.p] - h.i[super.p], h.v[super.p] - h.i[super.p], 10 * h.i[super.p], 11 * h.i[super.p]);
        try {
            this.p(graphics, rectangle);
            this.d(graphics, b);
            this.p(graphics, b);
            graphics.setColor(h.v);
            graphics.drawLine(h.l[super.p], h.n[super.p][0], h.l[super.p] + 10 * h.i[super.p], h.n[super.p][0]);
            graphics.drawLine(h.l[super.p], h.n[super.p][1] + h.d[super.p], h.l[super.p] + 10 * h.i[super.p], h.n[super.p][1] + h.d[super.p]);
            graphics.setColor(h.n);
            graphics.drawLine(h.l[super.p], h.n[super.p][0] + h.d[super.p], h.l[super.p] + 10 * h.i[super.p], h.n[super.p][0] + h.d[super.p]);
            graphics.drawLine(h.l[super.p], h.n[super.p][1], h.l[super.p] + 10 * h.i[super.p], h.n[super.p][1]);
            graphics.setColor(h.i);
            graphics.drawLine(h.l[super.p] + 10 * h.i[super.p], h.n[super.p][1], h.l[super.p] + 10 * h.i[super.p], h.n[super.p][0] + h.d[super.p]);
            graphics.drawLine(h.l[super.p], h.n[super.p][0] + h.d[super.p] + 1, h.l[super.p] + 10 * h.i[super.p], h.n[super.p][0] + h.d[super.p] + 1);
            if (this.d && (this.g != this.e || this.h != this.f)) {
                this.p(graphics, this.g, this.h, b, Color.white);
            }
            for (int i = 0; i < 10; ++i) {
                for (int j = 0; j < 9; ++j) {
                    if (this.a[i][j] != -1 && (!this.d || super.v != 0 || this.e != i || this.f != j)) {
                        if (b) {
                            this.p(graphics, this.a[i][j], h.n[super.p] + (8 - j) * h.i[super.p], h.v[super.p] + (9 - i) * h.i[super.p]);
                        }
                        else {
                            this.p(graphics, this.a[i][j], h.n[super.p] + j * h.i[super.p], h.v[super.p] + i * h.i[super.p]);
                        }
                    }
                }
            }
            if (this.d && super.v == 0) {
                this.p(graphics, this.e, this.f, b, Color.cyan);
                this.p(graphics, this.d[this.e][this.f], this.j, this.k);
            }
            if (super.l - super.v > 0) {
                this.p(graphics, super.p[super.l - super.v - 1] / 10 % 10, super.p[super.l - super.v - 1] % 10, b, (super.v == 0) ? new Color(255, 153, 0) : Color.cyan);
            }
            if (!super.d && super.l >= 2 && super.v == 0) {
                graphics.setFont(h.a);
                graphics.setColor(array[5]);
                graphics.drawString(h.p, (this.p.width - h.c) / 2, this.p.height / 2);
            }
            if (super.l - super.v >= 2) {
                graphics.setColor(new Color(204, 204, 153));
                graphics.setFont(dw.e);
                graphics.drawString("M" + (super.l - super.v) / 2, h.l[super.p] + 10 * h.i[super.p] + 5, h.n[super.p][1] + h.d[super.p] + 5 * h.i[super.p] - 4);
                graphics.drawString("E" + this.n / 2, h.l[super.p] + 10 * h.i[super.p] + 5, h.n[super.p][1] + h.d[super.p] + 5 * h.i[super.p] + 16);
                graphics.drawString("P" + this.a / 2, h.l[super.p] + 10 * h.i[super.p] + 5, h.n[super.p][1] + h.d[super.p] + 5 * h.i[super.p] + 36);
            }
            for (int k = 0; k < 2; ++k) {
                final int n = b ? (1 - k) : k;
                if (this.a >= 57 || super.l >= 580 || this.n >= 220 || this.p(k)) {
                    graphics.setColor(Color.red);
                    graphics.fillOval(h.l[super.p] + 10 * h.i[super.p] + 5, h.n[super.p][n] + 4, 10, 10);
                }
                else if (this.p[k].size() > 0) {
                    graphics.setColor(Color.yellow);
                    graphics.fillOval(h.l[super.p] + 10 * h.i[super.p] + 5, h.n[super.p][n] + 4, 10, 10);
                }
                else {
                    graphics.setColor(Color.white);
                    graphics.drawOval(h.l[super.p] + 10 * h.i[super.p] + 5, h.n[super.p][n] + 4, 10, 10);
                }
            }
        }
        catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public final boolean v(final int n, final int n2) {
        if (!this.d) {
            return false;
        }
        this.d = false;
        if (super.v != 0) {
            this.q();
            this.f();
            return true;
        }
        if (n > h.n[super.p] - h.i[super.p] / 2 && n < h.n[super.p] + 8 * h.i[super.p] + h.i[super.p] / 2 && n2 > h.v[super.p] - h.i[super.p] / 2 && n2 < h.v[super.p] + 9 * h.i[super.p] + h.i[super.p] / 2) {
            int n3 = (n2 + h.i[super.p] / 2 - h.v[super.p]) / h.i[super.p];
            int n4 = (n + h.i[super.p] / 2 - h.n[super.p]) / h.i[super.p];
            if (this.i() == 1 || (this.i() == -1 && this.p)) {
                n3 = 9 - n3;
                n4 = 8 - n4;
            }
            if (this.p(this.e, this.f, n3, n4)) {
                if ((super.d || (super.l < 2 && super.p[0] != null && super.p[1] != null)) && this.d[this.e][this.f] / 10 % 2 == this.i() && this.i() == super.l % 2) {
                    this.a(this.e * 1000 + this.f * 100 + n3 * 10 + n4);
                    super.p.h();
                    if (super.l == 2) {
                        super.d = true;
                        this.v();
                    }
                    ++super.v;
                    this.i();
                    this.d = 0;
                    this.n();
                    this.d();
                    this.g();
                    if (this.d()) {
                        super.p.l("<12>***Game over");
                        super.d = false;
                    }
                    this.f();
                    final int i = this.i();
                    this.d("MOVE " + super.p[super.l - 1] + " " + super.p[i].d());
                    if (this.v < 15 && super.l > du.p(15)) {
                        ++this.v;
                        this.i += this.b;
                        this.l += this.b * this.b;
                        if (this.v == 15) {
                            final double n5 = this.i / this.v;
                            final double sqrt = Math.sqrt(Math.abs(this.l / this.v - n5 * n5));
                            if (n5 < 10.0 && sqrt < 10.0) {
                                this.d("USERLOG DRAG " + super.l / 2 + " " + "" + 0.01 * Math.round(n5 * 100.0) + " " + "" + 0.01 * Math.round(sqrt * 100.0) + " " + " " + super.p.n() + " " + super.p.p() + " " + this.d(this.i()));
                            }
                        }
                    }
                    this.b = 0;
                    if (super.p[i].p()) {
                        super.p[i].p();
                    }
                    if (super.d[i].p()) {
                        super.d[i].p();
                    }
                    if (super.a[i].p()) {
                        super.a[i].p();
                    }
                }
                else if (super.p[0] == null || super.p[1] == null) {
                    super.p.l("<12>***please wait for an opponent");
                }
                else {
                    ++this.d;
                    super.p.l("<4>***bad move");
                }
            }
            else if (this.d[this.e][this.f] / 10 % 2 != super.l % 2) {
                ++this.d;
                super.p.l("<4>***Not your turn");
            }
            else if (this.e != n3 || this.f != n4) {
                ++this.d;
                super.p.l("<4>***Invalid move");
            }
        }
        if (this.d >= 3) {
            this.d("REFRESH");
        }
        this.f();
        return true;
    }
    
    public final boolean p(final int j, final int k) {
        if (!this.d) {
            return false;
        }
        if (super.v != 0) {
            this.q();
            this.f();
            return true;
        }
        final int i = this.j;
        final int l = this.k;
        this.j = j;
        this.k = k;
        ++this.b;
        final int abs = Math.abs(j - i);
        final int abs2 = Math.abs(k - l);
        final int min = Math.min(j, i);
        final int min2 = Math.min(k, l);
        if (j > h.n[super.p] - h.i[super.p] / 2 && j < h.n[super.p] + 8 * h.i[super.p] + h.i[super.p] / 2 && k > h.v[super.p] - h.i[super.p] / 2 && k < h.v[super.p] + 9 * h.i[super.p] + h.i[super.p] / 2) {
            this.g = (k + h.i[super.p] / 2 - h.v[super.p]) / h.i[super.p];
            this.h = (j + h.i[super.p] / 2 - h.n[super.p]) / h.i[super.p];
            if (this.i() == 1 || (this.i() == -1 && this.p)) {
                this.g = 9 - this.g;
                this.h = 8 - this.h;
            }
        }
        else {
            this.g = this.e;
            this.h = this.f;
        }
        super.p.p().repaint(min - h.i[super.p] - 10, min2 - h.i[super.p] - 10, abs + 2 * h.i[super.p] + 20, abs2 + 2 * h.i[super.p] + 20);
        return true;
    }
    
    public final boolean a(final int j, final int k) {
        if (System.currentTimeMillis() < super.p) {
            super.p.p().showStatus("Mouse is disabled for " + (super.p - System.currentTimeMillis()) / 1000L + "s. Please always keep your mouse within your game window.");
            return true;
        }
        if (this.i() == -1) {
            int n = -1;
            for (int i = 0; i < 2; ++i) {
                if (j > h.l[super.p] && j < h.l[super.p] + 10 * h.i[super.p] && k > h.n[super.p][i] && k < h.n[super.p][i] + h.d[super.p]) {
                    n = i;
                }
            }
            if (n != -1) {
                if (this.p) {
                    n = 1 - n;
                }
                if (super.p[n] == null && !super.d) {
                    if (n == 0) {
                        super.p.l("<2>*** Requesting red role");
                        this.d("REQUEST 0");
                    }
                    else {
                        super.p.l("<2>*** Requesting black role");
                        this.d("REQUEST 1");
                    }
                }
                else if (super.p[n] != null) {
                    if (n == 0 && this.p) {
                        this.p = false;
                        super.p.l("<2>*** Selecting red view");
                        this.f();
                    }
                    else if (n == 1 && !this.p) {
                        this.p = true;
                        super.p.l("<2>*** Selecting black view");
                        this.f();
                    }
                }
                return true;
            }
        }
        if (this.d) {
            this.d = false;
            this.f();
            return true;
        }
        if (super.v != 0) {
            this.q();
            this.f();
            return true;
        }
        if (j > h.n[super.p] - h.i[super.p] / 2 && j < h.n[super.p] + 8 * h.i[super.p] + h.i[super.p] / 2 && k > h.v[super.p] - h.i[super.p] / 2 && k < h.v[super.p] + 9 * h.i[super.p] + h.i[super.p] / 2) {
            int n2 = (k + h.i[super.p] / 2 - h.v[super.p]) / h.i[super.p];
            int n3 = (j + h.i[super.p] / 2 - h.n[super.p]) / h.i[super.p];
            if (this.i() == 1 || (this.i() == -1 && this.p)) {
                n2 = 9 - n2;
                n3 = 8 - n3;
            }
            if (this.d[n2][n3] != -1 && this.d[n2][n3] / 10 % 2 == this.i() && (super.d || super.l < 2)) {
                this.j = j;
                this.k = k;
                final int n4 = n2;
                this.e = n4;
                this.g = n4;
                final int n5 = n3;
                this.f = n5;
                this.h = n5;
                this.d = true;
                this.b = 0;
                this.f();
            }
        }
        return true;
    }
    
    protected final void m() {
        if (super.v == super.l) {
            return;
        }
        super.v = super.l;
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.a[i][j] = h.p[i][j];
            }
        }
        this.d(200);
    }
    
    protected final void q() {
        if (super.v == 0) {
            return;
        }
        super.v = 0;
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.a[i][j] = this.d[i][j];
            }
        }
        this.d(200);
    }
    
    protected final void p() {
        if (super.v == super.l) {
            return;
        }
        final int n = super.p[super.l - 1 - super.v];
        final int n2 = n / 1000;
        final int n3 = n / 100 % 10;
        final int n4 = n / 10 % 10;
        final int n5 = n % 10;
        this.a[n2][n3] = this.a[n4][n5];
        this.a[n4][n5] = this.p[super.l - 1 - super.v];
        ++super.v;
        this.d(200);
    }
    
    protected final void i() {
        if (super.v == 0) {
            return;
        }
        --super.v;
        final int n = super.p[super.l - 1 - super.v];
        final int n2 = n / 1000;
        final int n3 = n / 100 % 10;
        this.a[n / 10 % 10][n % 10] = this.a[n2][n3];
        this.a[n2][n3] = -1;
        this.d(200);
    }
    
    private final void p(final int n, final int n2, final int n3, final int n4) {
        this.p[super.l] = this.d[n3][n4];
        super.p[super.l] = n * 1000 + n2 * 100 + n3 * 10 + n4;
        this.d[n3][n4] = this.d[n][n2];
        this.d[n][n2] = -1;
        ++super.l;
    }
    
    private final void j() {
        --super.l;
        final int n = super.p[super.l];
        final int n2 = n / 1000;
        final int n3 = n / 100 % 10;
        final int n4 = n / 10 % 10;
        final int n5 = n % 10;
        this.d[n2][n3] = this.d[n4][n5];
        this.d[n4][n5] = this.p[super.l];
    }
    
    private final int p(final int n, final int n2, final int n3) {
        int n4 = 0;
        if (n2 < n3) {
            for (int i = n2 + 1; i <= n3 - 1; ++i) {
                if (this.d[n][i] != -1) {
                    ++n4;
                }
            }
        }
        else if (n2 > n3) {
            for (int j = n3 + 1; j <= n2 - 1; ++j) {
                if (this.d[n][j] != -1) {
                    ++n4;
                }
            }
        }
        return n4;
    }
    
    private final int d(final int n, final int n2, final int n3) {
        int n4 = 0;
        if (n2 < n3) {
            for (int i = n2 + 1; i <= n3 - 1; ++i) {
                if (this.d[i][n] != -1) {
                    ++n4;
                }
            }
        }
        else if (n2 > n3) {
            for (int j = n3 + 1; j <= n2 - 1; ++j) {
                if (this.d[j][n] != -1) {
                    ++n4;
                }
            }
        }
        return n4;
    }
    
    private final boolean d(final int n, final int n2, final int n3, final int n4) {
        if (this.d[n][n2] == -1) {
            return false;
        }
        if (this.d[n3][n4] != -1 && this.d[n][n2] / 10 % 2 == this.d[n3][n4] / 10 % 2) {
            return false;
        }
        final int n5 = this.d[n][n2] % 10;
        if (n5 == 0) {
            return n4 >= 3 && n4 <= 5 && ((Math.abs(n4 - n2) + Math.abs(n3 - n) == 1 && ((n <= 2 && n3 <= 2) || (n >= 7 && n3 >= 7))) || (n2 == n4 && this.d[n3][n4] == 10 - this.d[n][n2] && this.d(n2, n, n3) == 0));
        }
        if (n5 == 1) {
            return n4 >= 3 && n4 <= 5 && (n3 <= 2 || n3 >= 7) && Math.abs(n4 - n2) == 1 && Math.abs(n3 - n) == 1;
        }
        if (n5 == 2) {
            return ((n <= 4 && n3 <= 4) || (n >= 5 && n3 >= 5)) && Math.abs(n4 - n2) == 2 && Math.abs(n3 - n) == 2 && this.d[(n + n3) / 2][(n2 + n4) / 2] == -1;
        }
        if (n5 == 3) {
            return (n == n3 && this.p(n, n2, n4) == 0) || (n2 == n4 && this.d(n2, n, n3) == 0);
        }
        if (n5 == 4) {
            return (n == n3 && this.d[n3][n4] != -1 && this.p(n, n2, n4) == 1) || (n == n3 && this.d[n3][n4] == -1 && this.p(n, n2, n4) == 0) || (n2 == n4 && this.d[n3][n4] != -1 && this.d(n2, n, n3) == 1) || (n2 == n4 && this.d[n3][n4] == -1 && this.d(n2, n, n3) == 0);
        }
        if (n5 == 5) {
            return (Math.abs(n - n3) == 2 && Math.abs(n2 - n4) == 1 && this.d[(n + n3) / 2][n2] == -1) || (Math.abs(n2 - n4) == 2 && Math.abs(n - n3) == 1 && this.d[n][(n2 + n4) / 2] == -1);
        }
        if (n5 == 6 && this.d[n][n2] / 10 % 2 == 0) {
            return (n - n3 == 1 && n2 == n4) || (Math.abs(n2 - n4) == 1 && n == n3 && n <= 4);
        }
        return n5 == 6 && this.d[n][n2] / 10 % 2 == 1 && ((n3 - n == 1 && n2 == n4) || (Math.abs(n2 - n4) == 1 && n == n3 && n >= 5));
    }
    
    private final boolean p(final int n, final int n2, final int n3, final int n4) {
        if (!this.d(n, n2, n3, n4)) {
            return false;
        }
        if (this.d[n3][n4] % 10 == 0) {
            return true;
        }
        final int n5 = this.d[n][n2] / 10 % 2;
        this.p(n, n2, n3, n4);
        final int n6 = (n5 == 0) ? 7 : 0;
        final int n7 = (n5 == 0) ? 9 : 2;
        int n8 = 0;
        int n9 = 0;
        boolean b = false;
        for (int n10 = n6; n10 <= n7 && !b; ++n10) {
            for (int n11 = 3; n11 <= 5 && !b; ++n11) {
                if (this.d[n10][n11] == n5 * 10) {
                    n8 = n10;
                    n9 = n11;
                    b = true;
                }
            }
        }
        boolean b2 = false;
        if (!b) {
            du.p("Unable to find king");
            b2 = true;
        }
        else {
            for (int n12 = 0; n12 < 10 && !b2; ++n12) {
                for (int n13 = 0; n13 < 9 && !b2; ++n13) {
                    if (this.d(n12, n13, n8, n9)) {
                        b2 = true;
                    }
                }
            }
        }
        this.j();
        return !b2;
    }
    
    private final boolean d() {
        final int n = super.l % 2;
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (this.d[i][j] != -1 && this.d[i][j] / 10 % 2 == n) {
                    for (int k = 0; k < 10; ++k) {
                        for (int l = 0; l < 9; ++l) {
                            if (this.p(i, j, k, l)) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
    
    public static final boolean p(final Applet applet, final int n) {
        if (h.a) {
            return true;
        }
        h.p = ((n == 0) ? 25 : ((n == 2) ? 40 : 30));
        (h.p = df.p(applet, "xqpieces" + h.p + ".gif"))[0] = df.p(applet, "reddot.gif");
        h.p[1] = df.p(applet, "greendot.gif");
        final MediaTracker mediaTracker = new MediaTracker(applet);
        mediaTracker.addImage(h.p, 0);
        mediaTracker.addImage(h.p[0], 0);
        mediaTracker.addImage(h.p[1], 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
        return !mediaTracker.isErrorID(0) && (h.a = true);
    }
    
    static {
        h.p = new char[][] { { 'K', 'G', 'B', 'R', 'C', 'N', 'P' }, { 'k', 'g', 'b', 'r', 'c', 'n', 'p' } };
        h.p = null;
        h.p = new Image[2];
        p = new int[][] { { 13, 15, 12, 11, 10, 31, 32, 35, 33 }, { -1, -1, -1, -1, -1, -1, -1, -1, -1 }, { -1, 14, -1, -1, -1, -1, -1, 34, -1 }, { 16, -1, 36, -1, 56, -1, 76, -1, 96 }, { -1, -1, -1, -1, -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1, -1, -1, -1, -1 }, { 6, -1, 26, -1, 46, -1, 66, -1, 86 }, { -1, 4, -1, -1, -1, -1, -1, 24, -1 }, { -1, -1, -1, -1, -1, -1, -1, -1, -1 }, { 3, 5, 2, 1, 0, 21, 22, 25, 23 } };
        h.p = new Font("Courier", 1, 10);
        h.d = new Font("SanSerif", 1, 12);
        h.a = new Font("SanSerif", 1, 24);
        h.p = "GAME OVER";
        h.c = 200;
        p = new String[] { "9", "8", "7", "6", "5", "4", "3", "2", "1" };
        d = new String[] { "J", "I", "H", "G", "F", "E", "D", "C", "B", "A" };
        d = new int[] { 17, 17, 17 };
        a = new int[] { 32, 36, 40 };
        h.n = new int[] { 72, 86, 86 };
        h.v = new int[] { 49, 56, 70 };
        h.i = new int[] { 26, 31, 44 };
        h.l = new int[] { h.n[0] - h.i[0], h.n[1] - h.i[1], h.n[2] - h.i[2] };
        h.n = new int[][] { { h.v[0] + 10 * h.i[0] - 2, h.v[0] - h.i[0] - h.d[0] + 2 }, { h.v[1] + 10 * h.i[1] - 2, h.v[1] - h.i[1] - h.d[1] + 2 }, { h.v[2] + 10 * h.i[2] - 2, h.v[2] - h.i[2] - h.d[2] + 2 } };
        h.p = new Color[] { new Color(204, 153, 102), new Color(204, 204, 153) };
        p = new Color(109, 115, 107);
        d = new Color(82, 89, 81);
        a = new Color(59, 53, 44);
        n = new Color(51, 46, 38);
        v = new Color(102, 102, 102);
        i = new Color(134, 134, 134);
    }
}
