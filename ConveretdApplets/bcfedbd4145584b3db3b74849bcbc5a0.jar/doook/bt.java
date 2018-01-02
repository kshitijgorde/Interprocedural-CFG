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

public abstract class bt extends Panel
{
    public static int c;
    public Image b;
    private bv[] a;
    protected int h;
    protected int i;
    protected int B;
    protected int o;
    protected int p;
    protected aE b;
    protected ab a;
    protected bv a;
    protected as k;
    protected boolean v;
    protected boolean w;
    
    public int a() {
        return this.b.size().width;
    }
    
    public synchronized void b(final bv bv) {
        try {
            int t = 0;
            int b = -1;
            for (int i = 0; i < this.o; ++i) {
                b += this.a(i).t;
            }
            if (b != this.B) {
                this.B = b;
            }
            if (this.o == this.a.length) {
                t = this.a(0).t;
                this.B -= t;
                final int n = t - this.h;
                if (n > 0) {
                    if (this.k.J == 4) {
                        this.b(-n);
                    }
                    else {
                        for (int j = 0; j < n / 4; ++j) {
                            this.b(-4);
                        }
                        if (n % 4 != 0) {
                            this.b(-(n % 4));
                        }
                    }
                    this.h = 0;
                    t = 0;
                }
                this.h -= t;
                ++this.p;
            }
            else {
                ++this.o;
            }
            this.a(this.o - 1, bv);
            this.a(bv, this.a.size().width - 12 - bt.c, this.getFontMetrics(this.k.b.b()));
            final int height = this.a.size().height;
            this.B += bv.t;
            if (this.isShowing() && this.h == this.b.getMaximum() - t) {
                final int n2 = this.B - this.h - height - 1;
                if (n2 < bv.t) {
                    final Graphics graphics = this.a.getGraphics();
                    if (graphics != null) {
                        this.a(bv, this.B - bv.t - this.h + 1, graphics);
                    }
                }
                if (n2 > 0) {
                    if (this.k.J == 4) {
                        this.b(-n2);
                    }
                    else {
                        final int n3 = (int)Math.ceil(n2 / Math.pow(3.0, 4 - this.k.J));
                        for (int n4 = n2 / n3, k = 0; k < n4; ++k) {
                            if (k != 0) {
                                Thread.sleep(5L);
                            }
                            this.b(-n3);
                        }
                        if (n2 % n3 != 0) {
                            this.b(-(n2 % n3));
                        }
                    }
                }
                this.b.setValues(this.B, height, 0, this.B - 1);
                this.h = this.b.getValue();
            }
            else {
                this.b.setValues(this.h, height, 0, this.B - 1);
            }
        }
        catch (Exception ex) {}
    }
    
    public abstract void a(final bv p0, final int p1, final FontMetrics p2);
    
    protected abstract void a(final bv p0, final int p1, final Graphics p2);
    
    public synchronized bv a(final int n) {
        return this.a[(n + this.p) % this.a.length];
    }
    
    public int b() {
        return this.o;
    }
    
    public boolean handleEvent(final Event event) {
        Label_0224: {
            switch (event.id) {
                case 403: {
                    switch (event.key) {
                        case 1004: {
                            this.b.setValue(this.h - this.b.getLineIncrement());
                            break Label_0224;
                        }
                        case 1005: {
                            this.b.setValue(this.h + this.b.getLineIncrement());
                            break Label_0224;
                        }
                        case 1002: {
                            this.b.setValue(this.h - this.b.getPageIncrement());
                            break Label_0224;
                        }
                        case 1003: {
                            this.b.setValue(this.h + this.b.getPageIncrement());
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
                    final int n = this.h - this.b.getValue();
                    if (n != 0) {
                        this.b(n);
                    }
                    return true;
                }
                default: {
                    return super.handleEvent(event);
                }
            }
        }
    }
    
    public synchronized bv a(final Event event) {
        if (event.x >= bt.c) {
            int n = event.y + this.h;
            final int n2 = event.x - bt.c;
            for (int i = 0; i < this.o; ++i) {
                final bv a = this.a(i);
                if (n < a.t) {
                    a.i = -1;
                    final FontMetrics fontMetrics = this.getFontMetrics(this.k.b.b());
                    final int height = fontMetrics.getHeight();
                    final int n3 = n - (3 + fontMetrics.getAscent());
                    final int n4 = n3 / height;
                    if (n3 > 0 && n4 < a.g) {
                        int n5;
                        for (n5 = a.f[n4]; n5 < a.f[n4 + 1] && a.p.charAt(n5) == ' '; ++n5) {}
                        final String substring = a.p.substring(n5, a.f[n4 + 1]);
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
                        a.i = n5 + n7;
                    }
                    return a;
                }
                n -= a.t;
            }
        }
        return null;
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        if (this.k.b.ap == 0) {
            graphics.setColor(Color.black);
            graphics.drawRect(0, 0, size.width, size.height - 1);
        }
    }
    
    public synchronized void g() {
        int height = this.a.size().height;
        final boolean b = this.b.getValue() >= this.b.getMaximum();
        if (height < 0) {
            height = 0;
        }
        this.B = -1;
        if (this.o == 0) {
            this.h = 0;
            this.b.setValues(0, height, 0, 0);
        }
        else {
            final int n = this.a.size().width - 6 - bt.c;
            final FontMetrics fontMetrics = this.getFontMetrics(this.k.b.b());
            for (int i = 0; i < this.o; ++i) {
                final bv a = this.a(i);
                this.a(a, n, fontMetrics);
                this.B += a.t;
            }
            this.b.setValues(b ? this.B : this.h, height, 0, this.B);
            this.h = this.b.getValue();
        }
    }
    
    public void k() {
        this.v = false;
        this.repaint();
        this.a.repaint();
    }
    
    public void resize(final int n, final int n2) {
        this.a.resize(n - this.b.size().width, n2);
    }
    
    public void resize(final Dimension dimension) {
        this.resize(dimension.width, dimension.height);
    }
    
    private final void a(final int n, final bv bv) {
        this.a[(n + this.p) % this.a.length] = bv;
    }
    
    public synchronized void b(final int n) {
        final Dimension size = this.a.size();
        this.h -= n;
        this.i -= n;
        this.a.a(this.a.getGraphics(), 0, size.height);
    }
    
    public synchronized void l() {
        final int maximum = this.b.getMaximum();
        final int n = maximum - this.h;
        this.b.setValue(maximum);
        if (n > 0) {
            if (this.k.J == 4) {
                this.b(n);
            }
            else {
                final int n2 = (int)Math.ceil(n / Math.log(n) / 3.0);
                for (int i = 0; i < n / n2; ++i) {
                    this.b(-n2);
                }
                if (n % n2 > 0) {
                    this.b(-(n % n2));
                }
            }
        }
        this.h = maximum;
    }
    
    public bt(final as k, final boolean w) {
        this.b = null;
        this.h = 0;
        this.i = 0;
        this.B = 0;
        this.o = 0;
        this.p = 0;
        this.a = new ab(this);
        this.a = null;
        this.k = k;
        this.a = new bv[75];
        this.w = w;
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
        (this.b = new aE(1)).setLineIncrement(10);
        layout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
    }
}
