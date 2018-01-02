import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class j extends r
{
    private static final int[] p;
    private static Image[] p;
    private int[][] p;
    private int[][] d;
    protected boolean p;
    int p;
    int d;
    private static Font p;
    private static Font d;
    private static final Color[] p;
    private static String p;
    private static int a;
    private static final String[] p;
    private static final String[] d;
    private int n;
    private int v;
    private static final int[] d;
    private static int i;
    private static final int[] a;
    private static int[] n;
    private static int[] v;
    private static int[] i;
    private static int[][] a;
    private Dimension p;
    private static Color p;
    private static Color d;
    private static Color a;
    private static Color n;
    private static Color v;
    private static Color i;
    private static Color l;
    
    public j(final y y) {
        super(y, 289);
        this.p = new int[17][17];
        this.d = new int[17][17];
        this.p = false;
        this.p = 2;
        this.n = -1;
        this.v = -1;
        this.d = y.a();
        j.p[0] = df.p(y.p(), "reddot" + j.p[this.d] + ".gif");
        j.p[1] = df.p(y.p(), "greendot" + j.p[this.d] + ".gif");
        this.l();
    }
    
    public final String d() {
        return "Gomoku";
    }
    
    public final int d() {
        return 4;
    }
    
    public final int p(final int n) {
        return j.a[n] * 21;
    }
    
    protected final void l() {
        for (int i = 0; i < 17; ++i) {
            for (int j = 0; j < 17; ++j) {
                this.d[i][j] = (this.p[i][j] = -1);
            }
        }
    }
    
    protected final boolean d(final int n) {
        return this.n(n / 100, n % 100);
    }
    
    protected final boolean a(final int n) {
        this.p(n / 100, n % 100);
        if (this.i() == super.l % 2 && super.b > 0) {
            super.p = System.currentTimeMillis() + super.b;
            super.b = 0;
        }
        return this.d();
    }
    
    private final void c() {
        if (super.p.p().getSize().equals(this.p)) {
            return;
        }
        this.p = super.p.p().getSize();
        final int height = this.p.height;
        final int width = this.p.width;
        j.a[this.d] = Math.min((height - 3 * j.d[this.d] - 10) / 19, width / 21);
        j.n[this.d] = (width - j.a[this.d] * 17) / 2;
        j.v[this.d] = (height - super.v - j.a[this.d] * 17) / 2;
        j.i[this.d] = j.n[this.d] - j.a[this.d];
        j.a[this.d][0] = j.v[this.d] + 18 * j.a[this.d];
        j.a[this.d][1] = j.v[this.d] - j.a[this.d] - j.d[this.d];
        j.a = super.p.p().getFontMetrics(j.d).stringWidth(j.p);
        j.i = super.d.stringWidth(" 00:00");
    }
    
    protected final void h() {
        super.p.p().repaint(j.i[this.d] + 19 * j.a[this.d] - 3 * j.i, j.a[this.d][0], 3 * j.i, j.d[this.d]);
        super.p.p().repaint(j.i[this.d] + 19 * j.a[this.d] - 3 * j.i, j.a[this.d][1], 3 * j.i, j.d[this.d]);
    }
    
    protected final void k() {
        final int n = -1;
        this.v = n;
        this.n = n;
    }
    
    private final void p(final Graphics graphics, final boolean b) {
        graphics.setColor(j.p);
        graphics.setFont(j.p);
        for (int i = 0; i < 17; ++i) {
            final int n = b ? (16 - i) : i;
            graphics.drawString(j.d[n], j.n[this.d] - 9, j.v[this.d] + i * j.a[this.d] + 2 * j.a[this.d] / 3);
            graphics.drawString(j.d[n], j.n[this.d] + 17 * j.a[this.d] + 4, j.v[this.d] + i * j.a[this.d] + 2 * j.a[this.d] / 3);
            graphics.drawLine(j.n[this.d], j.v[this.d] + i * j.a[this.d], j.n[this.d] + 17 * j.a[this.d], j.v[this.d] + i * j.a[this.d]);
        }
        graphics.drawLine(j.n[this.d], j.v[this.d] + 17 * j.a[this.d], j.n[this.d] + 17 * j.a[this.d], j.v[this.d] + 17 * j.a[this.d]);
        for (int j = 0; j < 17; ++j) {
            final int n2 = b ? (16 - j) : j;
            graphics.drawString(j.p[n2], j.n[this.d] + j * j.a[this.d] + j.a[this.d] / 2, j.v[this.d] - 3);
            graphics.drawString(j.p[n2], j.n[this.d] + j * j.a[this.d] + j.a[this.d] / 2, j.v[this.d] + 17 * j.a[this.d] + 12);
            graphics.drawLine(j.n[this.d] + j * j.a[this.d], j.v[this.d], j.n[this.d] + j * j.a[this.d], j.v[this.d] + 17 * j.a[this.d]);
        }
        graphics.drawLine(j.n[this.d] + 17 * j.a[this.d], j.v[this.d], j.n[this.d] + 17 * j.a[this.d], j.v[this.d] + 17 * j.a[this.d]);
    }
    
    private final void d(final Graphics graphics, final boolean b) {
        graphics.setFont(super.d);
        for (int i = 0; i < 2; ++i) {
            final int n = b ? (1 - i) : i;
            graphics.setColor(j.n);
            graphics.fillRect(j.i[this.d], j.a[this.d][i], 19 * j.a[this.d], j.d[this.d]);
            graphics.setColor(j.p[n]);
            graphics.fillRect(j.i[this.d], j.a[this.d][i], 8, j.d[this.d]);
            graphics.setColor(j.v);
            graphics.drawString((super.p[n] != null) ? (String.valueOf(super.p[n]) + " (" + super.p[n] + ")") : "[None]", j.i[this.d] + 15, j.a[this.d][i] + j.d[this.d] - 4);
            graphics.setColor(j.i);
            graphics.fillRect(j.i[this.d] + 19 * j.a[this.d] - 3 * j.i - 12, j.a[this.d][i], 3 * j.i + 12, j.d[this.d]);
            this.p(graphics, du.p(super.p[n].d()), j.i[this.d] + 19 * j.a[this.d] - 2 * j.i - 5, j.a[this.d][i] + j.d[this.d] - 3, j.l, null);
            this.p(graphics, du.p(super.d[n].d()), j.i[this.d] + 19 * j.a[this.d] - j.i - 5, j.a[this.d][i] + j.d[this.d] - 3, j.l, null);
            this.p(graphics, du.p(super.a[n].d()), j.i[this.d] + 19 * j.a[this.d] - 5, j.a[this.d][i] + j.d[this.d] - 3, j.l, null);
        }
        int n2 = super.l % 2;
        if (b) {
            n2 = 1 - n2;
        }
        graphics.drawImage(super.p, j.i[this.d] - 12, j.a[this.d][n2] + 4, super.p.p());
    }
    
    private final void p(final Graphics graphics, int n, int n2, final boolean b) {
        if (b) {
            n = 16 - n;
            n2 = 16 - n2;
        }
        graphics.setColor(Color.white);
        graphics.drawRect(j.n[this.d] + n2 * j.a[this.d] - 1, j.v[this.d] + n * j.a[this.d] - 1, j.a[this.d] + 2, j.a[this.d] + 2);
        graphics.setColor(j.d);
        graphics.fillRect(j.n[this.d] + n2 * j.a[this.d] + 1, j.v[this.d] + n * j.a[this.d] + 1, j.a[this.d] - 2, j.a[this.d] - 2);
    }
    
    private final void p(final Graphics graphics, int n, int n2, final boolean b, final Color color) {
        graphics.setColor(color);
        if (b) {
            n = 16 - n;
            n2 = 16 - n2;
        }
        graphics.drawOval(j.n[this.d] + n2 * j.a[this.d] + j.a[this.d] / 2 - j.p[this.d] / 2, j.v[this.d] + n * j.a[this.d] + j.a[this.d] / 2 - j.p[this.d] / 2, j.p[this.d], j.p[this.d]);
    }
    
    private final void p(final Graphics graphics, final int n, int n2, int n3, final boolean b) {
        if (j.p == null) {
            return;
        }
        if (b) {
            n2 = 16 - n2;
            n3 = 16 - n3;
        }
        if (n >= 0 && n <= 1) {
            graphics.drawImage(j.p[n], j.n[this.d] + n3 * j.a[this.d] + j.a[this.d] / 2 - j.p[this.d] / 2, j.v[this.d] + n2 * j.a[this.d] + j.a[this.d] / 2 - j.p[this.d] / 2, super.p.p());
        }
    }
    
    public final void p(final Graphics graphics) {
        final Color[] array = dw.p[super.p.p()];
        this.c();
        final boolean b = this.i() == 1 || (this.i() == -1 && this.p);
        try {
            graphics.setColor(j.a);
            graphics.fillRect(j.n[this.d] - j.a[this.d], j.v[this.d] - j.a[this.d], 19 * j.a[this.d], 19 * j.a[this.d]);
            this.p(graphics, b);
            this.d(graphics, b);
            for (int i = 0; i < 17; ++i) {
                for (int j = 0; j < 17; ++j) {
                    this.p(graphics, this.d[i][j], i, j, b);
                }
            }
            if (super.l - super.v > 0) {
                final int n = super.p[super.l - super.v - 1] / 100;
                final int n2 = super.p[super.l - super.v - 1] % 100;
                this.p(graphics, n, n2, b);
                this.p(graphics, this.d[n][n2], n, n2, b);
            }
            if (this.n != -1 && this.v != -1) {
                this.p(graphics, this.n, this.v, b, Color.white);
            }
            if (!super.d && super.l >= 2 && super.v == 0) {
                graphics.setFont(j.d);
                graphics.setColor(array[5]);
                graphics.drawString(j.p, (this.p.width - j.a) / 2, this.p.height / 2);
            }
            if (super.l - super.v >= 1) {
                graphics.setFont(dw.i);
                this.p(graphics, "move #" + (super.l - super.v + 1) / 2, this.p.width - 6, this.p.height - 6, array[1], null);
            }
        }
        catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public final boolean i(final int n, final int n2) {
        if (this.i() == -1 || (super.l > 2 && !super.d)) {
            return false;
        }
        if (this.i() != super.l % 2) {
            if (this.n != -1 || this.v != -1) {
                final int n3 = -1;
                this.v = n3;
                this.n = n3;
                this.f();
            }
            return true;
        }
        if (n <= j.n[this.d] || n >= j.n[this.d] + 17 * j.a[this.d] || n2 <= j.v[this.d] || n2 >= j.v[this.d] + 17 * j.a[this.d]) {
            if (this.n != -1 || this.v != -1) {
                this.n = -1;
                this.v = -1;
                this.f();
                return true;
            }
            return false;
        }
        else {
            final boolean b = this.i() == 1 || (this.i() == -1 && this.p);
            int n4 = (n2 - j.v[this.d]) / j.a[this.d];
            int v = (n - j.n[this.d]) / j.a[this.d];
            if (b) {
                n4 = 16 - n4;
            }
            if (b) {
                v = 16 - v;
            }
            if (this.n(n4, v)) {
                this.n = n4;
                this.v = v;
                this.f();
                return true;
            }
            if (this.n != -1 || this.v != -1) {
                this.n = -1;
                this.v = -1;
                this.f();
                return true;
            }
            return false;
        }
    }
    
    public final boolean a(final int n, final int n2) {
        if (System.currentTimeMillis() < super.p) {
            super.p.p().showStatus("Mouse is disabled for " + (super.p - System.currentTimeMillis()) / 1000L + "s. Please always keep your mouse within your game window.");
            return false;
        }
        if (this.i() == -1) {
            int n3 = -1;
            for (int i = 0; i < 2; ++i) {
                if (n > j.i[this.d] && n < j.i[this.d] + 17 * j.a[this.d] && n2 > j.a[this.d][i] && n2 < j.a[this.d][i] + j.d[this.d]) {
                    n3 = i;
                }
            }
            if (n3 != -1) {
                if (this.p) {
                    n3 = 1 - n3;
                }
                if (super.p[n3] == null && !super.d) {
                    if (n3 == 0) {
                        super.p.l("<2>*** Requesting white role");
                        this.d("REQUEST 0");
                    }
                    else {
                        super.p.l("<2>*** Requesting black role");
                        this.d("REQUEST 1");
                    }
                }
                else if (super.p[n3] != null) {
                    if (n3 == 0 && this.p) {
                        this.p = false;
                        super.p.l("<2>*** Selecting white view");
                        this.f();
                    }
                    else if (n3 == 1 && !this.p) {
                        this.p = true;
                        super.p.l("<2>*** Selecting black view");
                        this.f();
                    }
                }
                return true;
            }
        }
        if (super.v != 0) {
            this.q();
            this.f();
            return true;
        }
        if (this.n != -1 && this.v != -1 && super.p[0] != null && super.p[1] != null && this.n(this.n, this.v) && (super.l <= 2 || super.d)) {
            this.a(this.n * 100 + this.v);
            super.p.h();
            if (super.l == 2) {
                super.d = true;
                this.v();
            }
            ++super.v;
            this.i();
            this.n();
            this.d();
            this.g();
            if (this.d()) {
                super.d = false;
                super.p.l("<12>***Game over");
            }
            this.f();
            final int j = this.i();
            this.d("MOVE " + super.p[super.l - 1] + " " + super.p[j].d());
            if (super.p[j].p()) {
                super.p[j].p();
            }
            if (super.d[j].p()) {
                super.d[j].p();
            }
            if (super.a[j].p()) {
                super.a[j].p();
            }
            return true;
        }
        return false;
    }
    
    protected final void m() {
        if (super.v == super.l) {
            return;
        }
        super.v = super.l;
        for (int i = 0; i < 17; ++i) {
            for (int j = 0; j < 17; ++j) {
                this.d[i][j] = -1;
            }
        }
        this.d(200);
    }
    
    protected final void q() {
        if (super.v == 0) {
            return;
        }
        super.v = 0;
        for (int i = 0; i < 17; ++i) {
            for (int j = 0; j < 17; ++j) {
                this.d[i][j] = this.p[i][j];
            }
        }
        this.d(200);
    }
    
    protected final void p() {
        if (super.v == super.l) {
            return;
        }
        final int n = super.p[super.l - 1 - super.v];
        this.d[n / 100][n % 100] = -1;
        ++super.v;
        this.d(200);
    }
    
    protected final void i() {
        if (super.v == 0) {
            return;
        }
        --super.v;
        final int n = super.p[super.l - 1 - super.v];
        this.d[n / 100][n % 100] = (super.l - 1 - super.v) % 2;
        this.d(200);
    }
    
    private final void p(final int n, final int n2) {
        super.p[super.l] = n * 100 + n2;
        this.p[n][n2] = super.l % 2;
        ++super.l;
    }
    
    private final boolean n(final int n, final int n2) {
        if (super.l == 2) {
            final int n3 = super.p[0] / 100;
            final int n4 = super.p[0] % 100;
            if (Math.abs(n3 - n) <= this.p && Math.abs(n4 - n2) <= this.p) {
                return false;
            }
        }
        return n >= 0 && n < 17 && n2 >= 0 && n2 < 17 && this.p[n][n2] == -1;
    }
    
    private final int p(int n, int n2, final int n3, final int n4) {
        if (this.p[n][n2] == -1) {
            return 0;
        }
        while (n < 17 && n2 < 17 && n >= 0 && n2 >= 0 && n + n3 < 17 && n2 + n4 < 17 && n + n3 >= 0 && n2 + n4 >= 0 && this.p[n][n2] == this.p[n + n3][n2 + n4]) {
            n += n3;
            n2 += n4;
        }
        int n5;
        for (n5 = 1; n < 17 && n2 < 17 && n >= 0 && n2 >= 0 && n - n3 < 17 && n2 - n4 < 17 && n - n3 >= 0 && n2 - n4 >= 0 && this.p[n][n2] == this.p[n - n3][n2 - n4]; n -= n3, n2 -= n4, ++n5) {}
        return n5;
    }
    
    private final boolean d() {
        if (super.l < 2) {
            return false;
        }
        final int n = super.p[super.l - 1];
        final int n2 = n / 100;
        final int n3 = n % 100;
        return this.p(n2, n3, 1, 0) == 5 || this.p(n2, n3, 0, 1) == 5 || this.p(n2, n3, 1, 1) == 5 || this.p(n2, n3, 1, -1) == 5;
    }
    
    public final boolean p(final String s, final String s2) {
        if (super.p(s, s2)) {
            return true;
        }
        if (s.equals("BOXSIZE")) {
            final int p2 = du.p(s2, 10);
            if (p2 >= 0) {
                this.p = p2;
            }
            return true;
        }
        return false;
    }
    
    static {
        p = new int[] { 11, 13, 17 };
        j.p = new Image[2];
        j.p = new Font("Courier", 0, 10);
        j.d = new Font("SanSerif", 1, 24);
        p = new Color[] { new Color(159, 27, 41), new Color(30, 136, 30) };
        j.p = "GAME OVER";
        j.a = 200;
        p = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "A", "B", "C", "D", "E", "F", "I" };
        d = new String[] { "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
        d = new int[] { 16, 16, 16 };
        a = new int[] { 16, 18, 25 };
        j.n = new int[] { 68, 60, 45 };
        j.v = new int[] { 38, 46, 65 };
        j.i = new int[] { j.n[0] - j.a[0], j.n[1] - j.a[1], j.n[2] - j.a[2] };
        j.a = new int[][] { { j.v[0] + 18 * j.a[0], j.v[0] - j.a[0] - j.d[0] }, { j.v[1] + 18 * j.a[1], j.v[1] - j.a[1] - j.d[1] }, { j.v[2] + 18 * j.a[2], j.v[2] - j.a[2] - j.d[2] } };
        j.p = new Color(131, 152, 163);
        j.d = new Color(15, 38, 53);
        j.a = new Color(54, 85, 107);
        j.n = new Color(133, 148, 160);
        j.v = new Color(9, 32, 51);
        j.i = new Color(34, 59, 80);
        j.l = new Color(206, 155, 53);
    }
}
