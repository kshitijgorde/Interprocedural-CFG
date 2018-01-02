// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.ItemListener;
import java.awt.Component;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Dimension;
import java.awt.Color;

public class q extends f
{
    private static final int l = 10;
    private static final int m = 10;
    protected int n;
    protected boolean o;
    protected Color p;
    protected Color q;
    protected Color r;
    protected Color s;
    protected Dimension t;
    protected dg u;
    
    public q() {
        this.n = 42;
        this.o = false;
        this.p = Color.black;
        this.q = new Color(0, 0, 255);
        this.r = new Color(255, 0, 0);
        this.s = new Color(255, 0, 0);
        this.t = new Dimension(80, 50);
        this.u = new dg();
        this.setLayout(null);
        super.g = new Dimension(0, 0);
    }
    
    public void b(final int n) {
        final boolean q = com.easypano.tw.h.q;
        int n2 = n;
        final int n3 = 42;
        int n5 = 0;
        Label_0042: {
            Label_0033: {
                if (!q) {
                    if (n != n3) {
                        n2 = n;
                        final int n4 = 41;
                        if (q) {
                            break Label_0033;
                        }
                        if (n != n4) {
                            return;
                        }
                    }
                    n5 = (n2 = this.n);
                    if (q) {
                        break Label_0042;
                    }
                }
            }
            if (n2 == n3) {
                return;
            }
            this.n = n;
            n5 = n;
        }
        switch (n5) {
        }
    }
    
    public void a(final Image image, final String s) {
        this.a(new j(image, s));
    }
    
    public void a(final j j) {
        j.d(this.o);
        j.a(this.p);
        ((com.easypano.tw.d.f)j.a()).k(this.s);
        ((com.easypano.tw.d.f)j.a()).l(this.r);
        ((com.easypano.tw.d.f)j.a()).j(this.q);
        j.setSize(this.t);
        this.u.a(j);
        this.add(j);
    }
    
    public void doLayout() {
        final boolean q = com.easypano.tw.h.q;
        q q2 = this;
        Label_0044: {
            if (!q) {
                switch (this.n) {
                    case 42: {
                        q2 = this;
                        break;
                    }
                    case 41: {
                        break Label_0044;
                    }
                }
            }
            q2.e();
            if (!q) {
                return;
            }
        }
        this.f();
    }
    
    private void e() {
        final boolean q = com.easypano.tw.h.q;
        int width = (this.t.width + 10) * this.u.a() + 10;
        int n = this.t.height;
        int n3;
        final int n2 = n3 = width;
        int width2;
        final int n4 = width2 = super.g.width;
        if (!q) {
            if (n2 < n4) {
                width = super.g.width;
            }
            final int n5;
            n3 = (n5 = n);
            final int height;
            width2 = (height = super.g.height);
        }
        if (!q) {
            if (n2 < n4) {
                n = super.g.height;
            }
            n3 = this.getBounds().height - this.t.height;
            width2 = 2;
        }
        final int n6 = n3 / width2;
        super.g.setSize(width, n);
        int n7 = 0;
        while (true) {
            while (true) {
                Label_0150: {
                    if (!q) {
                        break Label_0150;
                    }
                    this.u.a(n7).setLocation(10 + (10 + this.t.width) * n7, n6);
                    ++n7;
                }
                if (n7 < this.u.a()) {
                    continue;
                }
                break;
            }
            if (!q) {
                return;
            }
            continue;
        }
    }
    
    private void f() {
        final boolean q = com.easypano.tw.h.q;
        final int width = super.g.width;
        if (!q && width < this.t.width + 20) {
            super.g.width = this.t.width + 20;
            goto Label_0047;
        }
        int n = width;
        while (true) {
            Label_0057: {
                if (!q) {
                    break Label_0057;
                }
                ++n;
            }
            if ((10 + this.t.width) * n + 10 <= super.g.width) {
                continue;
            }
            break;
        }
        --n;
        final double n2 = (super.g.width - n * this.t.width) / (n + 1);
        final int n3 = this.u.a() / n;
        final int n4 = this.u.a() - n3 * n;
        Label_0196: {
            Label_0173: {
                if (!q) {
                    if (n4 <= 0) {
                        break Label_0173;
                    }
                    super.g.height = 10 + (10 + this.t.height) * (n3 + 1);
                }
                if (!q) {
                    break Label_0196;
                }
            }
            super.g.height = 10 + (10 + this.t.height) * n3;
        }
        double n5 = n2;
        int n6 = 10;
        int n7 = 0;
    Label_0256_Outer:
        while (true) {
            Label_0291: {
                if (!q) {
                    break Label_0291;
                }
                final int n9;
                int n8 = n9;
                while (true) {
                    while (true) {
                        Label_0259: {
                            if (!q) {
                                break Label_0259;
                            }
                            this.u.a(n8 + n7 * n).setLocation((int)n5, n6);
                            n5 += this.t.width + n2;
                            ++n8;
                        }
                        if (n8 < n) {
                            continue Label_0256_Outer;
                        }
                        break;
                    }
                    n5 = n2;
                    n6 += this.t.height + 10;
                    if (q) {
                        continue;
                    }
                    break;
                }
                ++n7;
            }
            if (n7 < n3) {
                continue;
            }
            int n10;
            final int n9 = n10 = n4;
            if (!q) {
                if (!q) {
                    if (n9 <= 0) {
                        return;
                    }
                    n10 = n * n3;
                }
                int n11 = n10;
                while (true) {
                    Label_0363: {
                        if (!q) {
                            break Label_0363;
                        }
                        this.u.a(n11).setLocation((int)n5, n6);
                        n5 += this.t.width + n2;
                        ++n11;
                    }
                    if (n11 < this.u.a()) {
                        continue;
                    }
                    break;
                }
                return;
            }
            continue;
        }
    }
    
    public void a(final Dimension dimension) {
        final boolean q = com.easypano.tw.h.q;
        int equals;
        final int n = equals = (this.t.equals(dimension) ? 1 : 0);
        if (!q) {
            if (n != 0) {
                return;
            }
            this.t = dimension;
            equals = 0;
        }
        int n2 = equals;
        while (true) {
            Label_0045: {
                if (!q) {
                    break Label_0045;
                }
                this.u.a(n2).setSize(dimension);
                ++n2;
            }
            if (n2 < this.u.a()) {
                continue;
            }
            break;
        }
    }
    
    public void a(final Color q) {
        final boolean q2 = com.easypano.tw.h.q;
        int equals;
        final int n = equals = (q.equals(this.q) ? 1 : 0);
        if (!q2) {
            if (n != 0) {
                return;
            }
            this.q = q;
            equals = 0;
        }
        int n2 = equals;
        while (true) {
            Label_0051: {
                if (!q2) {
                    break Label_0051;
                }
                ((com.easypano.tw.d.f)this.u.a(n2).a()).j(q);
                ++n2;
            }
            if (n2 < this.u.a()) {
                continue;
            }
            break;
        }
    }
    
    public void b(final Color r) {
        final boolean q = com.easypano.tw.h.q;
        int equals;
        final int n = equals = (r.equals(this.r) ? 1 : 0);
        if (!q) {
            if (n != 0) {
                return;
            }
            this.r = r;
            equals = 0;
        }
        int n2 = equals;
        while (true) {
            Label_0051: {
                if (!q) {
                    break Label_0051;
                }
                ((com.easypano.tw.d.f)this.u.a(n2).a()).l(r);
                ++n2;
            }
            if (n2 < this.u.a()) {
                continue;
            }
            break;
        }
    }
    
    public void c(final Color s) {
        final boolean q = com.easypano.tw.h.q;
        int equals;
        final int n = equals = (s.equals(this.s) ? 1 : 0);
        if (!q) {
            if (n != 0) {
                return;
            }
            this.s = s;
            equals = 0;
        }
        int n2 = equals;
        while (true) {
            Label_0051: {
                if (!q) {
                    break Label_0051;
                }
                ((com.easypano.tw.d.f)this.u.a(n2).a()).k(s);
                ++n2;
            }
            if (n2 < this.u.a()) {
                continue;
            }
            break;
        }
    }
    
    public void d(final boolean o) {
        final boolean q = com.easypano.tw.h.q;
        int o2;
        final int n = o2 = (this.o ? 1 : 0);
        if (!q) {
            if (n == (o ? 1 : 0)) {
                return;
            }
            this.o = o;
            o2 = 0;
        }
        int n2 = o2;
        while (true) {
            Label_0042: {
                if (!q) {
                    break Label_0042;
                }
                this.u.a(n2).d(o);
                ++n2;
            }
            if (n2 < this.u.a()) {
                continue;
            }
            break;
        }
    }
    
    public void d(final Color p) {
        final boolean q = com.easypano.tw.h.q;
        int equals;
        final int n = equals = (p.equals(this.p) ? 1 : 0);
        if (!q) {
            if (n != 0) {
                return;
            }
            this.p = p;
            equals = 0;
        }
        int n2 = equals;
        while (true) {
            Label_0045: {
                if (!q) {
                    break Label_0045;
                }
                this.u.a(n2).a(p);
                ++n2;
            }
            if (n2 < this.u.a()) {
                continue;
            }
            break;
        }
    }
    
    public void setPreferredSize(final Dimension g) {
        super.g = g;
    }
    
    public synchronized void a(final ItemListener itemListener) {
        this.u.addItemListener(itemListener);
    }
    
    public synchronized void b(final ItemListener itemListener) {
        this.u.removeItemListener(itemListener);
    }
    
    public void c(final int n) {
        this.u.b(n);
    }
    
    public void d(final int n) {
        this.u.c(n);
    }
    
    public int g() {
        return this.u.b();
    }
    
    public void destroyResource() {
        super.destroyResource();
        this.u.destroyResource();
        this.u = null;
    }
}
