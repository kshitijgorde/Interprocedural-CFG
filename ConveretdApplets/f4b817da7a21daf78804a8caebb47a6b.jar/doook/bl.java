// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

public abstract class bl extends Panel
{
    public static int e;
    public Image a;
    private aN[] a;
    protected int a;
    protected int b;
    protected int c;
    protected int d;
    protected int g;
    protected bj b;
    protected Y a;
    protected aN a;
    protected be h;
    protected boolean q;
    protected boolean k;
    
    public int i() {
        return this.b.size().width;
    }
    
    public synchronized void a(final aN an) {
        try {
            int f = 0;
            int c = -1;
            for (int i = 0; i < this.d; ++i) {
                c += this.a(i).f;
            }
            if (c != this.c) {
                this.c = c;
            }
            if (this.d == this.a.length) {
                f = this.a(0).f;
                this.c -= f;
                final int n = f - this.a;
                if (n > 0) {
                    if (this.h.H == 4) {
                        this.c(-n);
                    }
                    else {
                        for (int j = 0; j < n / 4; ++j) {
                            this.c(-4);
                        }
                        if (n % 4 != 0) {
                            this.c(-(n % 4));
                        }
                    }
                    this.a = 0;
                    f = 0;
                }
                this.a -= f;
                ++this.g;
            }
            else {
                ++this.d;
            }
            this.a(this.d - 1, an);
            this.a(an, this.a.size().width - 12 - bl.e, this.getFontMetrics(this.h.c.b()));
            final int height = this.a.size().height;
            this.c += an.f;
            if (this.isShowing() && this.a == this.b.getMaximum() - f) {
                final int n2 = this.c - this.a - height - 1;
                if (n2 < an.f) {
                    final Graphics graphics = this.a.getGraphics();
                    if (graphics != null) {
                        this.a(an, this.c - an.f - this.a + 1, graphics);
                    }
                }
                if (n2 > 0) {
                    if (this.h.H == 4) {
                        this.c(-n2);
                    }
                    else {
                        final int n3 = (int)Math.ceil(n2 / Math.pow(3.0, 4 - this.h.H));
                        for (int n4 = n2 / n3, k = 0; k < n4; ++k) {
                            if (k != 0) {
                                Thread.sleep(5L);
                            }
                            this.c(-n3);
                        }
                        if (n2 % n3 != 0) {
                            this.c(-(n2 % n3));
                        }
                    }
                }
                this.b.setValues(this.c, height, 0, this.c - 1);
                this.a = this.b.getValue();
            }
            else {
                this.b.setValues(this.a, height, 0, this.c - 1);
            }
        }
        catch (Exception ex) {}
    }
    
    public abstract void a(final aN p0, final int p1, final FontMetrics p2);
    
    protected abstract void a(final aN p0, final int p1, final Graphics p2);
    
    public synchronized aN a(final int n) {
        return this.a[(n + this.g) % this.a.length];
    }
    
    public int a() {
        return this.d;
    }
    
    public boolean handleEvent(final Event event) {
        Label_0224: {
            switch (event.id) {
                case 403: {
                    switch (event.key) {
                        case 1004: {
                            this.b.setValue(this.a - this.b.getLineIncrement());
                            break Label_0224;
                        }
                        case 1005: {
                            this.b.setValue(this.a + this.b.getLineIncrement());
                            break Label_0224;
                        }
                        case 1002: {
                            this.b.setValue(this.a - this.b.getPageIncrement());
                            break Label_0224;
                        }
                        case 1003: {
                            this.b.setValue(this.a + this.b.getPageIncrement());
                            break Label_0224;
                        }
                        case 1000: {
                            this.b.setValue(0);
                            break Label_0224;
                        }
                        case 1001: {
                            this.b.setValue(this.b.getMaximum());
                            break Label_0224;
                        }
                    }
                    break;
                }
                case 601:
                case 602:
                case 603:
                case 604:
                case 605: {
                    final int n = this.a - this.b.getValue();
                    if (n != 0) {
                        this.c(n);
                    }
                    return true;
                }
                default: {
                    return super.handleEvent(event);
                }
            }
        }
    }
    
    public synchronized aN a(final Event event) {
        if (event.x >= bl.e) {
            int n = event.y + this.a;
            final int n2 = event.x - bl.e;
            for (int i = 0; i < this.d; ++i) {
                final aN a = this.a(i);
                if (n < a.f) {
                    a.c = -1;
                    final FontMetrics fontMetrics = this.getFontMetrics(this.h.c.b());
                    final int height = fontMetrics.getHeight();
                    final int n3 = n - (3 + fontMetrics.getAscent());
                    final int n4 = n3 / height;
                    if (n3 > 0 && n4 < a.a) {
                        int n5;
                        for (n5 = a.e[n4]; n5 < a.e[n4 + 1] && a.d.charAt(n5) == ' '; ++n5) {}
                        final String substring = a.d.substring(n5, a.e[n4 + 1]);
                        int n6 = 0;
                        int j = substring.length();
                        int n7 = (n6 + j) / 2;
                        if (n2 > fontMetrics.stringWidth(substring.substring(0, j))) {
                            return a;
                        }
                        while (j > n6 + 1) {
                            if (fontMetrics.stringWidth(substring.substring(0, n7)) < n2) {
                                n6 = n7;
                            }
                            else {
                                j = n7;
                            }
                            n7 = (n6 + j) / 2;
                        }
                        a.c = n5 + n7;
                    }
                    return a;
                }
                n -= a.f;
            }
        }
        return null;
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        if (this.h.c.r == 0) {
            graphics.setColor(Color.black);
            graphics.drawRect(0, 0, size.width, size.height - 1);
        }
    }
    
    public synchronized void b() {
        int height = this.a.size().height;
        final boolean b = this.b.getValue() >= this.b.getMaximum();
        if (height < 0) {
            height = 0;
        }
        this.c = -1;
        if (this.d == 0) {
            this.a = 0;
            this.b.setValues(0, height, 0, 0);
        }
        else {
            final int n = this.a.size().width - 6 - bl.e;
            final FontMetrics fontMetrics = this.getFontMetrics(this.h.c.b());
            for (int i = 0; i < this.d; ++i) {
                final aN a = this.a(i);
                this.a(a, n, fontMetrics);
                this.c += a.f;
            }
            this.b.setValues(b ? this.c : this.a, height, 0, this.c);
            this.a = this.b.getValue();
        }
    }
    
    public void g() {
        this.q = false;
        this.repaint();
        this.a.repaint();
    }
    
    public void resize(final int n, final int n2) {
        this.a.resize(n - this.b.size().width, n2);
    }
    
    public void resize(final Dimension dimension) {
        this.resize(dimension.width, dimension.height);
    }
    
    private final void a(final int n, final aN an) {
        this.a[(n + this.g) % this.a.length] = an;
    }
    
    public synchronized void c(final int n) {
        final Dimension size = this.a.size();
        this.a -= n;
        this.b -= n;
        this.a.a(this.a.getGraphics(), 0, size.height);
    }
    
    public synchronized void d() {
        final int maximum = this.b.getMaximum();
        final int n = maximum - this.a;
        this.b.setValue(maximum);
        if (n > 0) {
            if (this.h.H == 4) {
                this.c(n);
            }
            else {
                final int n2 = (int)Math.ceil(n / Math.log(n) / 3.0);
                for (int i = 0; i < n / n2; ++i) {
                    this.c(-n2);
                }
                if (n % n2 > 0) {
                    this.c(-(n % n2));
                }
            }
        }
        this.a = maximum;
    }
    
    public bl(final be h, final boolean k) {
        this.a = null;
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.g = 0;
        this.a = new Y(this);
        this.a = null;
        this.h = h;
        this.a = new aN[75];
        this.k = k;
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(1, 1, 1, 0);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        this.a.setBackground(Color.white);
        layout.setConstraints(this.a, gridBagConstraints);
        this.add(this.a);
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 3;
        (this.b = new bj(1)).setLineIncrement(10);
        layout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
    }
}
