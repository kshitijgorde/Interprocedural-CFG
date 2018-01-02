// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import com.esial.util.c;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Event;
import com.diginet.digichat.util.l;
import com.diginet.digichat.common.bp;
import com.diginet.digichat.util.n;
import com.diginet.digichat.util.ImagesListener;
import java.awt.Panel;

public class cq extends Panel implements ImagesListener
{
    private n a;
    protected int b;
    protected int c;
    private cr d;
    protected cs e;
    protected cs f;
    private boolean fEvent;
    
    public void a() {
        this.a.d();
    }
    
    public void a(final bp bp) {
        this.a.a(bp);
    }
    
    public void a(final int n) {
        this.b(this.a.b(n));
    }
    
    public void b(final int b) {
        final int b2 = this.b;
        this.b = b;
        final int e = this.e();
        if (e == 0) {
            return;
        }
        final int c = e * (this.b / e);
        if (c != this.c) {
            this.c = c;
            this.d.repaint();
        }
        else {
            if (b2 >= 0) {
                this.d.c(b2 - c);
            }
            if (this.b >= 0) {
                this.d.d(this.b - c);
            }
        }
        if (this.fEvent) {
            this.postEvent((this.b < 0 || this.b >= this.a.b()) ? new Event(this, 702, null) : new Event(this, 701, this.a.d(this.b)));
        }
    }
    
    public bp b() {
        return (bp)this.a.d(this.b);
    }
    
    public int c() {
        return this.b;
    }
    
    protected Dimension d() {
        final Dimension size = this.d.size();
        int n = size.width / 43;
        int n2 = size.height / 41;
        final int b = this.a.b();
        if (n * n2 > b) {
            for (int i = (int)Math.ceil(Math.sqrt(b)); i < n; ++i) {
                final int n3 = (int)Math.ceil(b / i);
                if (n3 <= n2) {
                    n = i;
                    n2 = n3;
                    break;
                }
            }
        }
        return new Dimension(n, n2);
    }
    
    protected int e() {
        final Dimension d = this.d();
        return d.height * d.width;
    }
    
    public void resize(final int n, final int n2) {
        this.d.resize(n - 2 * this.e.size().width, n2);
    }
    
    public boolean action(final Event event, final Object o) {
        final int e = this.e();
        final int b = this.a.b();
        if (event.target == this.f) {
            this.c += e;
        }
        else {
            this.c -= e;
        }
        if (this.c >= b) {
            this.c = 0;
        }
        else if (this.c < 0) {
            this.c = b - 1 - (b - 1) % e;
        }
        final Graphics graphics = this.d.getGraphics();
        final Dimension size = this.d.size();
        graphics.setColor(this.d.getBackground());
        graphics.fillRect(0, 0, size.width, size.height);
        graphics.dispose();
        this.d.repaint();
        return true;
    }
    
    static n a(final cq cq) {
        return cq.a;
    }
    
    public void loadDone() {
        this.d.repaint();
    }
    
    public cq(final boolean fEvent) {
        this.a = new n();
        this.b = -1;
        this.c = 0;
        this.fEvent = fEvent;
        this.d = new cr(this);
        this.e = new cs(16, 30, 0);
        this.f = new cs(16, 30, 1);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.gridheight = 0;
        layout.setConstraints(this.e, gridBagConstraints);
        this.add(this.e);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        layout.setConstraints(this.d, gridBagConstraints);
        this.add(this.d);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 0;
        layout.setConstraints(this.f, gridBagConstraints);
        this.add(this.f);
        this.e.a(com.esial.util.c.a("Click here to view more icons."), null);
        this.f.a(com.esial.util.c.a("Click here to view more icons."), null);
    }
    
    public cq() {
        this(false);
    }
}
