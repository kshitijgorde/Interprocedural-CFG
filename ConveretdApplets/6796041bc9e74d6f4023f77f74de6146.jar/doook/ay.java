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

public abstract class ay extends Panel
{
    public static int g;
    public Image e;
    private Z[] a;
    protected int a;
    protected int b;
    protected int Z;
    protected int ai;
    protected int ap;
    protected e b;
    protected aw a;
    protected Z a;
    protected t i;
    protected boolean s;
    protected boolean t;
    
    public int a() {
        return this.b.size().width;
    }
    
    public synchronized void a(final Z z) {
        try {
            int h = 0;
            int z2 = -1;
            for (int i = 0; i < this.ai; ++i) {
                z2 += this.a(i).h;
            }
            if (z2 != this.Z) {
                this.Z = z2;
            }
            if (this.ai == this.a.length) {
                h = this.a(0).h;
                this.Z -= h;
                final int n = h - this.a;
                if (n > 0) {
                    if (this.i.t == 4) {
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
                    h = 0;
                }
                this.a -= h;
                ++this.ap;
            }
            else {
                ++this.ai;
            }
            this.a(this.ai - 1, z);
            this.a(z, this.a.size().width - 12 - ay.g, this.getFontMetrics(this.i.a.b()));
            final int height = this.a.size().height;
            this.Z += z.h;
            if (this.isShowing() && this.a == this.b.getMaximum() - h) {
                final int n2 = this.Z - this.a - height - 1;
                if (n2 < z.h) {
                    final Graphics graphics = this.a.getGraphics();
                    if (graphics != null) {
                        this.a(z, this.Z - z.h - this.a + 1, graphics);
                    }
                }
                if (n2 > 0) {
                    if (this.i.t == 4) {
                        this.c(-n2);
                    }
                    else {
                        final int n3 = (int)Math.ceil(n2 / Math.pow(3.0, 4 - this.i.t));
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
                this.b.setValues(this.Z, height, 0, this.Z - 1);
                this.a = this.b.getValue();
            }
            else {
                this.b.setValues(this.a, height, 0, this.Z - 1);
            }
        }
        catch (Exception ex) {}
    }
    
    public abstract void a(final Z p0, final int p1, final FontMetrics p2);
    
    protected abstract void a(final Z p0, final int p1, final Graphics p2);
    
    public synchronized Z a(final int n) {
        return this.a[(n + this.ap) % this.a.length];
    }
    
    public int b() {
        return this.ai;
    }
    
    public boolean handleEvent(final Event event) {
        Label_0221: {
            switch (event.id) {
                case 403: {
                    switch (event.key) {
                        case 1004: {
                            this.b.setValue(this.a - this.b.getLineIncrement());
                            break Label_0221;
                        }
                        case 1005: {
                            this.b.setValue(this.a + this.b.getLineIncrement());
                            break Label_0221;
                        }
                        case 1002: {
                            this.b.setValue(this.a - this.b.getPageIncrement());
                            break Label_0221;
                        }
                        case 1003: {
                            this.b.setValue(this.a + this.b.getPageIncrement());
                            break Label_0221;
                        }
                        case 1000: {
                            this.b.setValue(0);
                            break Label_0221;
                        }
                        case 1001: {
                            this.b.setValue(this.b.getMaximum());
                            break Label_0221;
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
    
    public synchronized Z a(final Event event) {
        if (event.x >= ay.g) {
            int n = event.y + this.a;
            final int n2 = event.x - ay.g;
            for (int i = 0; i < this.ai; ++i) {
                final Z a = this.a(i);
                if (n < a.h) {
                    a.b = -1;
                    final FontMetrics fontMetrics = this.getFontMetrics(this.i.a.b());
                    final int height = fontMetrics.getHeight();
                    final int n3 = n - (3 + fontMetrics.getAscent());
                    final int n4 = n3 / height;
                    if (n3 > 0 && n4 < a.i) {
                        int n5;
                        for (n5 = a.a[n4]; n5 < a.a[n4 + 1] && a.l.charAt(n5) == ' '; ++n5) {}
                        final String substring = a.l.substring(n5, a.a[n4 + 1]);
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
                        a.b = n5 + n7;
                    }
                    return a;
                }
                n -= a.h;
            }
        }
        return null;
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        if (this.i.a.aE == 0) {
            graphics.setColor(Color.black);
            graphics.drawRect(0, 0, size.width, size.height - 1);
        }
    }
    
    public synchronized void d() {
        int height = this.a.size().height;
        final boolean b = this.b.getValue() >= this.b.getMaximum();
        if (height < 0) {
            height = 0;
        }
        this.Z = -1;
        if (this.ai == 0) {
            this.a = 0;
            this.b.setValues(0, height, 0, 0);
        }
        else {
            final int n = this.a.size().width - 6 - ay.g;
            final FontMetrics fontMetrics = this.getFontMetrics(this.i.a.b());
            for (int i = 0; i < this.ai; ++i) {
                final Z a = this.a(i);
                this.a(a, n, fontMetrics);
                this.Z += a.h;
            }
            this.b.setValues(b ? this.Z : this.a, height, 0, this.Z);
            this.a = this.b.getValue();
        }
    }
    
    public void b() {
        this.s = false;
        this.repaint();
        this.a.repaint();
    }
    
    public void b(final int n) {
        for (int i = n; i < this.ai - 1; ++i) {
            this.a[(i + this.ap) % this.a.length] = this.a[(i + 1 + this.ap) % this.a.length];
        }
        this.a[(this.ai - 1 + this.ap) % this.a.length] = null;
        --this.ai;
        this.a.a();
        this.d();
        this.a.repaint();
    }
    
    public void resize(final int n, final int n2) {
        this.a.resize(n - this.b.size().width, n2);
    }
    
    public void resize(final Dimension dimension) {
        this.resize(dimension.width, dimension.height);
    }
    
    private final void a(final int n, final Z z) {
        this.a[(n + this.ap) % this.a.length] = z;
    }
    
    public synchronized void c(final int n) {
        final Dimension size = this.a.size();
        this.a -= n;
        this.b -= n;
        this.a.a(this.a.getGraphics(), 0, size.height);
    }
    
    public synchronized void s() {
        final int maximum = this.b.getMaximum();
        final int n = maximum - this.a;
        this.b.setValue(maximum);
        if (n > 0) {
            if (this.i.t == 4) {
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
    
    public ay(final t i, final boolean t) {
        this.e = null;
        this.a = 0;
        this.b = 0;
        this.Z = 0;
        this.ai = 0;
        this.ap = 0;
        this.a = new aw(this);
        this.a = null;
        this.i = i;
        this.a = new Z[75];
        this.t = t;
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
        (this.b = new e(1)).setLineIncrement(10);
        layout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
    }
}
