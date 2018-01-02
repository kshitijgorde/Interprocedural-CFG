// 
// Decompiled by Procyon v0.5.30
// 

package equis.metastock;

import java.awt.Component;
import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Canvas;

class b extends Canvas
{
    private int a;
    private double b;
    private int c;
    private int d;
    private int e;
    private int f;
    private boolean g;
    private boolean h;
    private boolean i;
    private boolean j;
    private Font k;
    private Rectangle l;
    private Rectangle m;
    private Rectangle n;
    private Rectangle o;
    private double p;
    private double q;
    private double r;
    private String s;
    private static int t;
    private static int u;
    private static i v;
    private static boolean w;
    
    b() {
        this.a = 80;
        this.g = false;
        this.h = false;
        this.i = false;
        this.j = false;
        this.l = new Rectangle(0, 0, 0, 0);
        this.m = new Rectangle(0, 0, 0, 0);
        this.n = new Rectangle(0, 0, 0, 0);
        this.o = new Rectangle(0, 0, 0, 0);
        this.p = 0.3;
        this.q = 0.5;
        this.r = 0.2;
        this.k = new Font("TimesRoman", 0, 12);
    }
    
    public void a(final int a) {
        this.a = a;
    }
    
    public void a() {
        equis.metastock.b.v.a();
    }
    
    public void a(final String s) {
        this.s = s;
        if (!equis.metastock.b.w && this.s.length() > 0) {
            equis.metastock.b.w = true;
            this.s = String.valueOf(this.s) + "\n\n" + MS4Java.l();
            this.s = this.s.substring(0, this.s.lastIndexOf(46));
        }
        this.repaint();
        MS4Java.b(s);
    }
    
    private void a(Graphics graphics) {
        if (this.s == null) {
            return;
        }
        if (this.s.length() > 0 && graphics != null) {
            int n = 20;
            if (this.p < 1.0 && MS4Java.n > 0) {
                n += MS4Java.n * 6;
            }
            if (this.s == null) {
                graphics = this.getGraphics();
            }
            this.b(graphics);
            graphics.setFont(new Font("Helvetica", 1, 16));
            graphics.setColor(MS4Java.p);
            this.a(graphics, this.n);
            boolean b = false;
            String s = this.s;
            int n2 = 0;
            while (!b) {
                final int index = s.indexOf(10);
                String substring;
                if (index >= 0) {
                    substring = s.substring(0, index);
                    s = s.substring(index + 1, s.length());
                }
                else {
                    substring = s;
                    b = true;
                }
                graphics.drawString(substring, this.n.x * 2, n * (3 + n2));
                ++n2;
            }
        }
    }
    
    private void b(final Graphics graphics) {
        if (this.m.height > 0) {
            this.a(graphics, this.m, MS4Java.bc >= 20000);
        }
        if (this.n.height > 0) {
            this.a(graphics, this.n, MS4Java.bc >= 20000);
        }
        if (this.o.height > 0) {
            this.a(graphics, this.o, MS4Java.bc >= 20000);
        }
    }
    
    private void a(final Graphics graphics, final Rectangle rectangle, final boolean b) {
        final int n = 3;
        final int n2 = 10;
        graphics.setColor(MS4Java.o);
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        graphics.setColor(MS4Java.q);
        graphics.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        if (b) {
            graphics.setColor(Color.gray);
            graphics.fillRect(rectangle.x + n2, rectangle.y + rectangle.height + 1, rectangle.width - n2 + n, n);
            graphics.fillRect(rectangle.x + rectangle.width + 1, rectangle.y + n2, n, rectangle.height - n2 + n);
        }
    }
    
    private void b() {
        double min = 100000.0;
        double max = -100000.0;
        for (int i = 0; i <= MS4Java.n; ++i) {
            MS4Java.d[i].h.a(MS4Java.d[0].m, MS4Java.d[0].n);
            MS4Java.d[i].i.a(MS4Java.d[0].m, MS4Java.d[0].n);
            MS4Java.d[i].j.a(MS4Java.d[0].m, MS4Java.d[0].n);
            MS4Java.d[i].k.a(MS4Java.d[0].m, MS4Java.d[0].n);
            MS4Java.d[i].l.a(MS4Java.d[0].m, MS4Java.d[0].n);
            min = Math.min(min, MS4Java.d[i].j.d);
            max = Math.max(max, MS4Java.d[i].i.e);
            if (MS4Java.d[i].l.d < 0.0) {
                MS4Java.d[i].l.d = 0.0;
            }
            if (MS4Java.d[i].l.e < 0.0) {
                MS4Java.d[i].l.e = 0.0;
            }
        }
        if (MS4Java.n > 0) {
            for (int j = 0; j <= MS4Java.n; ++j) {
                MS4Java.d[j].h.e = max;
                MS4Java.d[j].h.d = min;
                MS4Java.d[j].i.e = max;
                MS4Java.d[j].i.d = min;
                MS4Java.d[j].j.e = max;
                MS4Java.d[j].j.d = min;
                MS4Java.d[j].k.e = max;
                MS4Java.d[j].k.d = min;
                MS4Java.d[j].l.e = 0.0;
                MS4Java.d[j].l.d = 0.0;
            }
        }
    }
    
    private int c() {
        return this.l.height - 16 - 19;
    }
    
    public void paint(final Graphics graphics) {
        this.l = this.bounds();
        graphics.clearRect(0, 0, this.l.width, this.l.height);
        this.a(graphics, this.l);
        if (MS4Java.n < 0) {
            return;
        }
        this.b();
        MS4Java.b.a(MS4Java.d[0].m, MS4Java.d[0].n);
        if (MS4Java.c.a >= 0) {
            MS4Java.c.a(MS4Java.d[0].m, MS4Java.d[0].n);
            final h b = MS4Java.b;
            final h c = MS4Java.c;
            final double min = Math.min(MS4Java.b.d, MS4Java.c.d);
            c.d = min;
            b.d = min;
            final h b2 = MS4Java.b;
            final h c2 = MS4Java.c;
            final double max = Math.max(MS4Java.b.e, MS4Java.c.e);
            c2.e = max;
            b2.e = max;
        }
        final Rectangle m = this.m;
        final Rectangle n = this.n;
        final Rectangle o = this.o;
        final int x = 10;
        o.x = x;
        n.x = x;
        m.x = x;
        final Rectangle i = this.m;
        final Rectangle n2 = this.n;
        final Rectangle o2 = this.o;
        final int width = this.l.width - this.n.x - MS4Java.t();
        o2.width = width;
        n2.width = width;
        i.width = width;
        if (MS4Java.f.e.getSelectedItem().equals(MS4Java.bf[4]) || MS4Java.f.e.getSelectedItem().compareTo(MS4Java.bf[6]) == 0 || MS4Java.f.e.getSelectedItem().compareTo(MS4Java.bf[8]) == 0 || MS4Java.f.e.getSelectedItem().compareTo(MS4Java.bf[11]) == 0 || MS4Java.f.e.getSelectedItem().compareTo(MS4Java.bf[12]) == 0) {
            this.q += this.p;
            this.p = 0.0;
        }
        else if (this.p == 0.0) {
            this.p = this.q / 3.0;
            this.q -= this.p;
        }
        if (MS4Java.d[0].l.d == MS4Java.d[0].l.e) {
            this.q += this.r;
            this.r = 0.0;
        }
        else if (this.r == 0.0) {
            this.r = this.q / 4.0;
            this.q -= this.r;
        }
        this.m.height = (int)(this.p * this.c());
        this.n.height = (int)(this.q * this.c());
        this.o.height = (int)(this.r * this.c());
        this.m.y = 16;
        this.n.y = 16 + this.m.height;
        this.o.y = 16 + this.m.height + this.n.height;
        this.b = (this.n.width - 2 - 10) / (MS4Java.d[0].n - MS4Java.d[0].m + 1.0 + 1.0);
        final int n3 = (int)(this.b / 2.0);
        graphics.setColor(MS4Java.q);
        graphics.drawRect(0, 0, this.l.width - 1, this.l.height - 1);
        graphics.setColor(MS4Java.t);
        graphics.fillRect(0, 0, this.l.width, this.l.height);
        this.b(graphics);
        graphics.setColor(MS4Java.q);
        graphics.drawRect(0, 0, this.l.width - 1, this.l.height - 1);
        this.a(graphics, this.n);
        if (!this.i) {
            this.c(graphics);
            if (this.m.height > 0) {
                this.a(graphics, MS4Java.b.e, MS4Java.b.d, this.m, false, "");
            }
            this.a(graphics, MS4Java.d[0].i.e, MS4Java.d[0].j.d, this.n, true, (MS4Java.n > 0) ? "%" : "");
            if (this.o.height > 0) {
                this.a(graphics, MS4Java.d[0].l.e, 0.0, this.o, false, "");
            }
        }
        for (int j = 0; j <= MS4Java.n; ++j) {
            this.a(graphics);
            if (MS4Java.d[j].k.a < 0) {
                return;
            }
            graphics.setFont(this.k);
            graphics.clipRect(this.n.x, 16, this.n.width, this.l.height - 16 - 19);
            for (int k = MS4Java.d[0].m; k <= MS4Java.d[0].n; ++k) {
                final int b3 = this.b(k);
                graphics.setColor(MS4Java.d[j].k.h);
                final double n4 = MS4Java.d[j].h.c[k];
                final double n5 = MS4Java.d[j].i.c[k];
                final double n6 = MS4Java.d[j].j.c[k];
                final double n7 = MS4Java.d[j].k.c[k];
                final double n8 = MS4Java.d[j].l.c[k];
                final int a = this.a(n4, MS4Java.d[j].i.e, MS4Java.d[j].j.d, this.n);
                final int a2 = this.a(n5, MS4Java.d[j].i.e, MS4Java.d[j].j.d, this.n);
                final int a3 = this.a(n6, MS4Java.d[j].i.e, MS4Java.d[j].j.d, this.n);
                final int a4 = this.a(n7, MS4Java.d[j].i.e, MS4Java.d[j].j.d, this.n);
                if (MS4Java.f.d.getSelectedItem().equals(MS4Java.bf[0]) || MS4Java.f.d.getSelectedItem().equals(MS4Java.bf[1])) {
                    if (MS4Java.f.d.getSelectedItem().equals(MS4Java.bf[1])) {
                        graphics.drawLine(b3 - 3, a, b3 - 1, a);
                    }
                    graphics.drawLine(b3, a2, b3, a3);
                    graphics.drawLine(b3 + 1, a4, b3 + 3, a4);
                }
                else if (MS4Java.f.d.getSelectedItem().equals(MS4Java.bf[2])) {
                    final int min2 = Math.min(a, a4);
                    final int max2 = Math.max(a, a4);
                    graphics.drawLine(b3, a2, b3, min2);
                    graphics.drawLine(b3, a3, b3, max2);
                    final int n9 = b3 - n3;
                    final int n10 = b3 + n3;
                    if (n7 < n4) {
                        graphics.fillRect(n9, min2, n10 - n9, max2 - min2);
                    }
                    else {
                        graphics.drawRect(n9, min2, n10 - n9, max2 - min2);
                    }
                }
                else if (MS4Java.f.d.getSelectedItem().equals(MS4Java.bf[3])) {
                    if (k > 0) {
                        graphics.drawLine((int)(b3 - this.b), this.a(MS4Java.d[j].k.c[k - 1], MS4Java.d[j].i.e, MS4Java.d[j].j.d, this.n), b3, this.a(n7, MS4Java.d[j].i.e, MS4Java.d[j].j.d, this.n));
                    }
                }
                else {
                    MS4Java.a(String.valueOf(MS4Java.bf[43]) + "Error: Unknown price style.");
                }
                if (this.o.height > 0) {
                    graphics.setColor(MS4Java.d[j].l.h);
                    graphics.drawLine(b3, this.o.y + this.o.height - 1, b3, this.o.y + this.o.height - (int)(this.o.height * (n8 / MS4Java.d[j].l.e)));
                }
            }
            MS4Java.g.c();
        }
        this.a(graphics, MS4Java.d[0].m, MS4Java.d[0].n, MS4Java.b);
        this.a(graphics, MS4Java.d[0].m, MS4Java.d[0].n, MS4Java.c);
        graphics.setColor(MS4Java.aa);
        graphics.clipRect(this.n.x, 16, this.n.width, this.l.height - 16 - 19);
        for (int l = 0; l < equis.metastock.b.v.b(); ++l) {
            if (equis.metastock.b.v.b[l] == 0) {
                graphics.drawLine(this.b(equis.metastock.b.v.c[l]), this.a(equis.metastock.b.v.e[l], MS4Java.b.e, MS4Java.b.d, this.m), this.b(equis.metastock.b.v.d[l]), this.a(equis.metastock.b.v.f[l], MS4Java.b.e, MS4Java.b.d, this.m));
            }
            else if (equis.metastock.b.v.b[l] == 1) {
                graphics.drawLine(this.b(equis.metastock.b.v.c[l]), this.a(equis.metastock.b.v.e[l], MS4Java.d[0].i.e, MS4Java.d[0].j.d, this.n), this.b(equis.metastock.b.v.d[l]), this.a(equis.metastock.b.v.f[l], MS4Java.d[0].i.e, MS4Java.d[0].j.d, this.n));
            }
        }
        if (MS4Java.n > 0) {
            final int n11 = 12;
            final int n12 = 6;
            final int n13 = 6;
            graphics.setFont(new Font("Helvetica", 1, 10));
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            int max3 = 1;
            for (int n14 = 0; n14 <= MS4Java.n; ++n14) {
                final int length = MS4Java.d[n14].b.length();
                final char[] array = new char[length + 2];
                MS4Java.d[n14].b.getChars(0, length, array, 0);
                max3 = Math.max(max3, fontMetrics.charsWidth(array, 0, MS4Java.d[n14].b.length()));
            }
            final Rectangle rectangle = new Rectangle(this.n.x * 2, this.n.y + this.n.x, n12 * 4 + max3, (MS4Java.n + 1) * n11 + 4);
            this.a(graphics, rectangle, true);
            for (int n15 = 0; n15 <= MS4Java.n; ++n15) {
                graphics.setColor(MS4Java.d[n15].k.h);
                graphics.fillRect(rectangle.x + n12, rectangle.y + n11 * (n15 + 1) - n13, n12, n13);
                graphics.drawString(MS4Java.d[n15].b, rectangle.x + n12 * 3, rectangle.y + n11 * (n15 + 1));
            }
        }
    }
    
    private void a(final Graphics graphics, final int n, final int n2, final h h) {
        if (h.a < 0) {
            return;
        }
        graphics.setColor(h.h);
        for (int i = n; i <= n2; ++i) {
            final int b = this.b(i);
            if (i <= h.b) {
                if (h.f == 0) {
                    if (i >= h.a + 1 && i >= n + 1) {
                        if (this.m.height > 0) {
                            graphics.drawLine((int)(b - this.b), this.a(h.c[i - 1], h.e, h.d, this.m), b, this.a(h.c[i], h.e, h.d, this.m));
                        }
                        else {
                            graphics.drawLine((int)(b - this.b), this.a(h.c[i - 1], MS4Java.d[0].i.e, MS4Java.d[0].j.d, this.n), b, this.a(h.c[i], MS4Java.d[0].i.e, MS4Java.d[0].j.d, this.n));
                        }
                    }
                }
                else if (h.f == 1) {
                    if (i >= h.a && i >= n) {
                        graphics.drawLine(b, Math.min(this.a(0.0, h.e, h.d, this.m), 16 + this.m.height), b, this.a(h.c[i], h.e, h.d, this.m));
                    }
                }
                else {
                    MS4Java.a("Error: Unknown line style.");
                }
            }
        }
        equis.metastock.b.t = -1;
        equis.metastock.b.u = -1;
    }
    
    private int a(final double n, final double n2, final double n3, final Rectangle rectangle) {
        int n4;
        if (n2 - n3 == 0.0) {
            n4 = 0;
        }
        else {
            n4 = rectangle.y + rectangle.height - (int)((n - n3) / (n2 - n3) * rectangle.height);
        }
        return n4;
    }
    
    private double a(final int n, final double n2, final double n3, final Rectangle rectangle) {
        return n3 + (rectangle.y + rectangle.height - n) / rectangle.height * (n2 - n3);
    }
    
    private int b(final int n) {
        int n2 = (int)((n - MS4Java.d[0].m) * this.b) + this.n.x;
        return (int)(++n2 + this.b);
    }
    
    private int c(int n) {
        n -= (int)this.b;
        return (int)((--n - this.n.x + this.b / 2.0) / this.b) + MS4Java.d[0].m;
    }
    
    private void c(final Graphics graphics) {
        int b = 0;
        final int n = this.l.height - 3;
        boolean b2 = false;
        final String[] array = { MS4Java.bf[31], MS4Java.bf[32], MS4Java.bf[33], MS4Java.bf[34], MS4Java.bf[35], MS4Java.bf[36], MS4Java.bf[37], MS4Java.bf[38], MS4Java.bf[39], MS4Java.bf[40], MS4Java.bf[41], MS4Java.bf[42] };
        if (MS4Java.d[0].m < 0) {
            return;
        }
        int n2;
        if (MS4Java.d[0].b()) {
            n2 = MS4Java.d[0].c[MS4Java.d[0].m];
        }
        else if (MS4Java.d[0].e()) {
            n2 = MS4Java.d[0].f[MS4Java.d[0].m];
        }
        else {
            n2 = MS4Java.d[0].d[MS4Java.d[0].m];
        }
        for (int i = MS4Java.d[0].m; i <= MS4Java.d[0].n; ++i) {
            boolean b3;
            if (MS4Java.d[0].b()) {
                b3 = (MS4Java.d[0].c[i] != n2);
            }
            else if (MS4Java.d[0].e()) {
                b3 = (MS4Java.d[0].f[i] != n2);
            }
            else {
                b3 = (MS4Java.d[0].d[i] != n2);
            }
            if (b3) {
                final int n3 = b;
                b = this.b(i);
                if (MS4Java.bc >= 20000) {
                    graphics.setColor(MS4Java.r);
                    if (this.m.height > 0) {
                        graphics.drawLine(b, this.m.y + 1, b, this.m.y + this.m.height - 2);
                    }
                    if (this.n.height > 0) {
                        graphics.drawLine(b, this.n.y + 1, b, this.n.y + this.n.height - 2);
                    }
                    if (this.o.height > 0) {
                        graphics.drawLine(b, this.o.y + 1, b, this.o.y + this.o.height - 2);
                    }
                }
                graphics.setColor(MS4Java.q);
                graphics.drawLine(b, this.l.height - 19, b, this.l.height - 19 + 4);
                graphics.setColor(MS4Java.u);
                if (MS4Java.d[0].b()) {
                    n2 = MS4Java.d[0].c[i];
                }
                else if (MS4Java.d[0].e()) {
                    n2 = MS4Java.d[0].f[i];
                }
                else {
                    n2 = MS4Java.d[0].d[i];
                }
                if (n2 >= 1) {
                    if (MS4Java.d[0].b() || (MS4Java.d[0].c() && n2 - 1 == 0)) {
                        graphics.drawString(String.valueOf(MS4Java.d[0].c[i]), b, n);
                    }
                    else if (MS4Java.d[0].e()) {
                        graphics.drawString(MS4Java.d[0].f[i] + ":", b, n);
                    }
                    else {
                        if (b - n3 < 25) {
                            b2 = true;
                        }
                        String substring;
                        if (b2) {
                            substring = array[n2 - 1].substring(0, 1);
                        }
                        else {
                            substring = array[n2 - 1];
                        }
                        graphics.drawString(substring, b, n);
                    }
                }
            }
        }
    }
    
    private void a(final Graphics graphics, final double n, final double n2, final Rectangle rectangle, boolean b, final String s) {
        final int n3 = 15;
        final double[] array = { 0.001, 0.0025, 0.005, 0.01, 0.025, 0.05, 0.1, 0.25, 0.5, 1.0, 2.5, 5.0, 10.0, 25.0, 50.0, 100.0, 250.0, 500.0, 1000.0, 2500.0, 5000.0, 10000.0, 25000.0, 50000.0, 100000.0, 250000.0, 500000.0, 1000000.0, 2500000.0, 5000000.0, 1.0E7, 2.5E7, 5.0E7, 1.0E8, 2.5E8, 5.0E8, 1.0E9, 2.5E9, 5.0E9, 1.0E10, 2.5E10, 5.0E10 };
        if (n2 == n) {
            return;
        }
        if (MS4Java.bc < 20000) {
            b = false;
        }
        final int n4 = rectangle.height / n3;
        final boolean b2 = n > 1000000.0;
        final double n5 = (n - n2) / n4;
        int i = 0;
        double n6 = 0.0;
        while (i < array.length) {
            if (array[i] > n5 * 0.8) {
                n6 = array[i];
                break;
            }
            ++i;
        }
        if (n6 == 0.0) {
            n6 = array[array.length - 1];
        }
        for (double n7 = (int)(n / n6) * n6 - n6; n7 - n6 >= n2; n7 -= n6) {
            final int a = this.a(n7, n, n2, rectangle);
            graphics.setColor(MS4Java.u);
            String s2;
            if (b2) {
                s2 = n7 / 1000000.0 + MS4Java.bf[67];
            }
            else {
                s2 = String.valueOf(n7);
            }
            graphics.drawString(String.valueOf(s2) + s, this.n.x + this.n.width + 10, a + 4);
            if (b) {
                graphics.setColor(MS4Java.r);
                graphics.drawLine(this.n.x + 1, a, this.n.x + this.n.width + 5, a);
            }
            graphics.setColor(MS4Java.q);
            graphics.drawLine(this.n.x + this.n.width, a, this.n.x + this.n.width + 5, a);
        }
    }
    
    private String a(double n, int n2) {
        String s = null;
        if (n > 1.0E9) {
            n /= 1.0E9;
            s = MS4Java.bf[68];
        }
        else if (n > 1000000.0) {
            n /= 1000000.0;
            s = MS4Java.bf[67];
        }
        String s2 = Double.toString(n);
        final int index = s2.indexOf(46);
        if (n2 > 0) {
            ++n2;
        }
        if (s2.length() > index + n2) {
            s2 = s2.substring(0, index + n2);
        }
        if (s != null) {
            s2 = String.valueOf(s2) + s;
        }
        return s2;
    }
    
    private String d(final int n) {
        String s = String.valueOf(n);
        if (s.length() < 2) {
            s = "0" + s;
        }
        return s;
    }
    
    private void a(final int n, final int n2) {
        if (this.i) {
            return;
        }
        int n3 = this.c(n);
        final int be = MS4Java.be;
        final int n4 = this.l.width - MS4Java.t();
        final Graphics graphics = this.getGraphics();
        graphics.clipRect(this.n.x, 4, n4 - this.n.x - 1, 11);
        graphics.setFont(this.k);
        graphics.setColor(MS4Java.t);
        graphics.fillRect(1, 1, n4, 15);
        graphics.setColor(MS4Java.u);
        if (n3 < MS4Java.d[0].m || n3 > MS4Java.d[0].n || n2 < 14) {
            n3 = MS4Java.d[0].k.b;
        }
        if (n3 >= 0) {
            final String d = this.d(MS4Java.d[0].e[n3]);
            final String d2 = this.d(MS4Java.d[0].d[n3]);
            final String d3 = this.d(MS4Java.d[0].c[n3]);
            String s;
            if (MS4Java.as.equals("DDMMYY")) {
                s = String.valueOf(d) + MS4Java.at + d2 + MS4Java.at + d3;
            }
            else if (MS4Java.as.equals("YYMMDD")) {
                s = String.valueOf(d3) + MS4Java.at + d2 + MS4Java.at + d;
            }
            else {
                s = String.valueOf(d2) + MS4Java.at + d + MS4Java.at + d3;
            }
            graphics.drawString(s, this.n.x, 14);
            if (MS4Java.d[0].e()) {
                graphics.drawString(" " + MS4Java.d[0].g[n3], this.n.x + 40, 14);
            }
            graphics.drawString(String.valueOf(MS4Java.bf[61]) + "=" + this.a(MS4Java.d[0].h.c[n3], be), this.n.x + this.a, 14);
            graphics.drawString(String.valueOf(MS4Java.bf[62]) + "=" + this.a(MS4Java.d[0].i.c[n3], be), this.n.x + this.a * 2, 14);
            graphics.drawString(String.valueOf(MS4Java.bf[63]) + "=" + this.a(MS4Java.d[0].j.c[n3], be), this.n.x + this.a * 3, 14);
            graphics.drawString(String.valueOf(MS4Java.bf[64]) + "=" + this.a(MS4Java.d[0].k.c[n3], be), this.n.x + this.a * 4, 14);
            graphics.drawString(String.valueOf(MS4Java.bf[65]) + "=" + this.a(MS4Java.d[0].l.c[n3], be), this.n.x + this.a * 5, 14);
            if (n3 >= MS4Java.b.a && n3 <= MS4Java.b.b) {
                graphics.drawString(String.valueOf(MS4Java.bf[66]) + "=" + this.a(MS4Java.b.c[n3], be), this.n.x + this.a * 6, 14);
            }
        }
    }
    
    private void a(final Graphics graphics, int b, final int n) {
        b = this.b(this.c(b));
        if (b >= this.n.x && b <= this.n.width) {
            graphics.drawLine(b, 18, b, this.l.height - 19 - 2);
        }
        if (n >= 18 && n <= this.l.height - 19 - 2) {
            graphics.drawLine(this.n.x, n, this.n.x + this.n.width, n);
        }
    }
    
    private boolean a(final int n, final Rectangle rectangle) {
        return MS4Java.bc >= 20000 && rectangle.height > 0 && n > rectangle.y + rectangle.height - 4 && n < rectangle.y + rectangle.height + 4;
    }
    
    public boolean handleEvent(final Event event) {
        final Graphics graphics = this.getGraphics();
        graphics.setColor(MS4Java.aa);
        graphics.setXORMode(MS4Java.o);
        Label_0727: {
            switch (event.id) {
                case 501: {
                    this.c = this.b(this.c(event.x));
                    this.d = event.y;
                    this.e = this.b(this.c(event.x));
                    this.f = event.y;
                    break;
                }
                case 502: {
                    if (MS4Java.bc < 20000) {
                        break;
                    }
                    if (this.j) {
                        this.j = false;
                        if (this.f > this.n.y) {
                            equis.metastock.b.v.a(1, this.c(this.c), this.a(this.d, MS4Java.d[0].i.e, MS4Java.d[0].j.d, this.n), this.c(this.e), this.a(this.f, MS4Java.d[0].i.e, MS4Java.d[0].j.d, this.n));
                            break;
                        }
                        equis.metastock.b.v.a(0, this.c(this.c), this.a(this.d, MS4Java.b.e, MS4Java.b.d, this.m), this.c(this.e), this.a(this.f, MS4Java.b.e, MS4Java.b.d, this.m));
                        break;
                    }
                    else {
                        if (this.i) {
                            this.i = false;
                            this.repaint();
                            break;
                        }
                        break;
                    }
                    break;
                }
                case 506: {
                    if (this.c <= 0 || this.d <= 0) {
                        break Label_0727;
                    }
                    if (!this.j && (this.i || this.g || this.h)) {
                        this.i = true;
                        if (this.f > 0) {
                            if (this.g) {
                                final Rectangle m = this.m;
                                m.height += event.y - this.f;
                                this.p = this.m.height / this.c();
                                this.p = Math.min(this.p, 1.0 - this.r - 0.05);
                                this.p = Math.max(this.p, 0.05);
                                this.q = 1.0 - this.p - this.r;
                            }
                            else if (this.h) {
                                final Rectangle n = this.n;
                                n.height += event.y - this.f;
                                this.q = this.n.height / this.c();
                                this.q = Math.min(this.q, 1.0 - this.p - 0.05);
                                this.q = Math.max(this.q, 0.05);
                                this.r = 1.0 - this.q - this.p;
                            }
                            else {
                                MS4Java.a("Warning: Unexpected condition in MOUSE_DRAG.");
                            }
                            this.repaint();
                        }
                        this.f = event.y;
                        break Label_0727;
                    }
                    this.j = true;
                    graphics.clipRect(this.n.x, 16, this.n.width, this.l.height - 16 - 19);
                    graphics.drawLine(this.c, this.d, this.e, this.f);
                    this.e = this.b(this.c(event.x));
                    this.f = event.y;
                    graphics.drawLine(this.c, this.d, this.e, this.f);
                    break Label_0727;
                }
                case 503: {
                    this.a(event.x, event.y);
                    if ((event.modifiers & 0x1) != 0x0) {
                        if (equis.metastock.b.u >= 0 && equis.metastock.b.t >= 0) {
                            this.a(graphics, equis.metastock.b.t, equis.metastock.b.u);
                        }
                        equis.metastock.b.t = event.x;
                        equis.metastock.b.u = event.y;
                        this.a(graphics, equis.metastock.b.t, equis.metastock.b.u);
                        break;
                    }
                    if (this.i) {
                        MS4Java.a(MS4Java.k).setCursor(8);
                        break;
                    }
                    if (this.a(event.y, this.m)) {
                        MS4Java.a(MS4Java.k).setCursor(8);
                        this.g = true;
                        break;
                    }
                    if (this.a(event.y, this.n)) {
                        MS4Java.a(MS4Java.k).setCursor(8);
                        this.h = true;
                        break;
                    }
                    MS4Java.a(MS4Java.k).setCursor(0);
                    this.i = false;
                    this.g = false;
                    this.h = false;
                    break;
                }
                case 403: {
                    if (event.key == 1008) {
                        MS4Java.a();
                        break;
                    }
                    break;
                }
                case 401: {
                    if (event.key != 10 && event.key != 27 && event.key != 32) {
                        MS4Java.f.a.setText(new Character((char)event.key).toString());
                        MS4Java.f.a.requestFocus();
                        final int length = MS4Java.f.a.getText().length();
                        MS4Java.f.a.select(length, length);
                        break;
                    }
                    break;
                }
            }
        }
        return true;
    }
    
    private void a(final Graphics graphics, final Rectangle rectangle) {
        if (MS4Java.d().equals("MetaStock®")) {
            final Font font = graphics.getFont();
            final Font font2 = new Font("Helvetica", 1, 12);
            final Color color = graphics.getColor();
            int blue = MS4Java.o.getBlue();
            int green = MS4Java.o.getGreen();
            int red = MS4Java.o.getRed();
            final int abs = Math.abs(red - blue);
            final int abs2 = Math.abs(red - green);
            if (abs < 10 && abs2 < 10) {
                if (blue + green + red >= 381) {
                    red = 255;
                    blue = 255;
                    green = 255;
                }
                else {
                    red = 0;
                    blue = 0;
                    green = 0;
                }
            }
            graphics.setColor(new Color(255 - red, 255 - green, 255 - blue));
            graphics.setPaintMode();
            graphics.setFont(font2);
            graphics.drawString(MS4Java.d(), rectangle.x + rectangle.width - graphics.getFontMetrics().stringWidth(MS4Java.d()) - 2, rectangle.y + rectangle.height - 2);
            graphics.setFont(font);
            graphics.setColor(color);
        }
    }
    
    static {
        b.t = -1;
        b.u = -1;
        b.v = new i();
    }
}
