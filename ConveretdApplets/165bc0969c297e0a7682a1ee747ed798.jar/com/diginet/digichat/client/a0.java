// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

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
import com.diginet.digichat.awt.as;
import java.awt.Image;
import java.awt.Panel;

public abstract class a0 extends Panel
{
    public static int a;
    public Image b;
    private bf[] c;
    protected int d;
    protected int e;
    protected int f;
    protected int g;
    protected int h;
    protected as i;
    protected a1 j;
    protected bf k;
    protected i l;
    protected boolean m;
    protected boolean n;
    
    public int a() {
        return this.i.size().width;
    }
    
    public synchronized void a(final bf bf) {
        try {
            int b = 0;
            int f = -1;
            for (int i = 0; i < this.g; ++i) {
                f += this.a(i).b;
            }
            if (f != this.f) {
                this.f = f;
            }
            if (this.g == this.c.length) {
                b = this.a(0).b;
                this.f -= b;
                final int n = b - this.d;
                if (n > 0) {
                    if (this.l.bq == 4) {
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
                    this.d = 0;
                    b = 0;
                }
                this.d -= b;
                ++this.h;
            }
            else {
                ++this.g;
            }
            this.a(this.g - 1, bf);
            this.a(bf, this.j.size().width - 12 - a0.a, this.getFontMetrics(this.l.cc.b()));
            final int height = this.j.size().height;
            this.f += bf.b;
            if (this.isShowing() && this.d == this.i.getMaximum() - b) {
                final int n2 = this.f - this.d - height - 1;
                if (n2 < bf.b) {
                    final Graphics graphics = this.j.getGraphics();
                    if (graphics != null) {
                        this.a(bf, this.f - bf.b - this.d + 1, graphics);
                    }
                }
                if (n2 > 0) {
                    if (this.l.bq == 4) {
                        this.c(-n2);
                    }
                    else {
                        final int n3 = (int)Math.ceil(n2 / Math.pow(3.0, 4 - this.l.bq));
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
                this.i.setValues(this.f, height, 0, this.f - 1);
                this.d = this.i.getValue();
            }
            else {
                this.i.setValues(this.d, height, 0, this.f - 1);
            }
        }
        catch (Exception ex) {}
    }
    
    public abstract void a(final bf p0, final int p1, final FontMetrics p2);
    
    protected abstract void a(final bf p0, final int p1, final Graphics p2);
    
    public synchronized bf a(final int n) {
        return this.c[(n + this.h) % this.c.length];
    }
    
    public int b() {
        return this.g;
    }
    
    public boolean isAnyChecked() {
        for (int i = 0; i < this.g; ++i) {
            if (this.a(i).nCheck == 2) {
                return true;
            }
        }
        return false;
    }
    
    public boolean handleEvent(final Event event) {
        Label_0221: {
            switch (event.id) {
                case 403: {
                    switch (event.key) {
                        case 1004: {
                            this.i.setValue(this.d - this.i.getLineIncrement());
                            break Label_0221;
                        }
                        case 1005: {
                            this.i.setValue(this.d + this.i.getLineIncrement());
                            break Label_0221;
                        }
                        case 1002: {
                            this.i.setValue(this.d - this.i.getPageIncrement());
                            break Label_0221;
                        }
                        case 1003: {
                            this.i.setValue(this.d + this.i.getPageIncrement());
                            break Label_0221;
                        }
                        case 1000: {
                            this.i.setValue(0);
                            break Label_0221;
                        }
                        case 1001: {
                            this.i.setValue(this.i.getMaximum());
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
                    final int n = this.d - this.i.getValue();
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
    
    public synchronized bf a(final Event event) {
        for (int i = 0; i < this.g; ++i) {
            this.a(i).fCheck = false;
        }
        if (event.x >= a0.a) {
            int n = event.y + this.d;
            final int n2 = event.x - a0.a;
            final int n3 = this.j.size().width - event.x;
            final boolean b = n3 > 3 && n3 < 13;
            for (int j = 0; j < this.g; ++j) {
                final bf a = this.a(j);
                if (n < a.b) {
                    if (a.nCheck != 0 && b && n > 2 && n < 12) {
                        a.fCheck = true;
                        return a;
                    }
                    if (a.nType != 4 || a.nLoc >= 0) {
                        a.e = -1;
                        final FontMetrics fontMetrics = this.getFontMetrics(this.l.cc.b());
                        final int height = fontMetrics.getHeight();
                        final int n4 = n - (3 + fontMetrics.getAscent());
                        final int n5 = n4 / height;
                        if (n4 > 0 && n5 < a.c) {
                            int n6;
                            for (n6 = a.l[n5]; n6 < a.l[n5 + 1] && a.i.charAt(n6) == ' '; ++n6) {}
                            final String substring = a.i.substring(n6, a.l[n5 + 1]);
                            int n7 = 0;
                            int k = substring.length();
                            int n8 = (n7 + k) / 2;
                            if (n2 > fontMetrics.stringWidth(substring.substring(0, k))) {
                                return a;
                            }
                            while (k > n7 + 1) {
                                if (fontMetrics.stringWidth(substring.substring(0, n8)) < n2) {
                                    n7 = n8;
                                }
                                else {
                                    k = n8;
                                }
                                n8 = (n7 + k) / 2;
                            }
                            a.e = n6 + n8;
                        }
                        return a;
                    }
                }
                n -= a.b;
            }
        }
        return null;
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        if (this.l.cc.v == 0) {
            graphics.setColor(Color.black);
            graphics.drawRect(0, 0, size.width, size.height - 1);
        }
    }
    
    public synchronized void c() {
        int height = this.j.size().height;
        final boolean b = this.i.getValue() >= this.i.getMaximum();
        if (height < 0) {
            height = 0;
        }
        this.f = -1;
        if (this.g == 0) {
            this.d = 0;
            this.i.setValues(0, height, 0, 0);
        }
        else {
            final int n = this.j.size().width - 6 - a0.a;
            final FontMetrics fontMetrics = this.getFontMetrics(this.l.cc.b());
            for (int i = 0; i < this.g; ++i) {
                final bf a = this.a(i);
                this.a(a, n, fontMetrics);
                this.f += a.b;
            }
            this.i.setValues(b ? this.f : this.d, height, 0, this.f);
            this.d = this.i.getValue();
        }
    }
    
    public void d() {
        this.m = false;
        this.repaint();
        this.j.repaint();
    }
    
    public void b(final int n) {
        for (int i = n; i < this.g - 1; ++i) {
            this.c[(i + this.h) % this.c.length] = this.c[(i + 1 + this.h) % this.c.length];
        }
        this.c[(this.g - 1 + this.h) % this.c.length] = null;
        --this.g;
        this.j.a();
        this.c();
        this.j.repaint();
    }
    
    public void resize(final int n, final int n2) {
        this.j.resize(n - this.i.size().width, n2);
    }
    
    public void resize(final Dimension dimension) {
        this.resize(dimension.width, dimension.height);
    }
    
    private final void a(final int n, final bf bf) {
        this.c[(n + this.h) % this.c.length] = bf;
    }
    
    public synchronized void c(final int n) {
        final Dimension size = this.j.size();
        this.d -= n;
        this.e -= n;
        this.j.a(this.j.getGraphics(), 0, size.height);
    }
    
    public synchronized void e() {
        final int maximum = this.i.getMaximum();
        final int n = maximum - this.d;
        this.i.setValue(maximum);
        if (n > 0) {
            if (this.l.bq == 4) {
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
        this.d = maximum;
    }
    
    public a0(final i l, final boolean n) {
        this.b = null;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.j = new a1(this);
        this.k = null;
        this.l = l;
        this.c = new bf[75];
        this.n = n;
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(1, 1, 1, 0);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        this.j.setBackground(Color.white);
        layout.setConstraints(this.j, gridBagConstraints);
        this.add(this.j);
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 3;
        (this.i = new as(1)).setLineIncrement(10);
        layout.setConstraints(this.i, gridBagConstraints);
        this.add(this.i);
    }
}
