// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.Panel;

public class bV extends Panel
{
    private aG y;
    protected int h;
    protected int i;
    private bT a;
    protected bS a;
    protected bS b;
    
    public void a() {
        this.y.b();
    }
    
    public void a(final as as) {
        this.y.a(as);
    }
    
    public void a(final int n) {
        this.b(this.y.f(n));
    }
    
    public void b(final int h) {
        final int h2 = this.h;
        this.h = h;
        final int c = this.c();
        if (c == 0) {
            return;
        }
        final int i = c * (this.h / c);
        if (i != this.i) {
            this.i = i;
            this.a.repaint();
        }
        else {
            this.a.c(h2 - i);
            this.a.d(this.h - i);
        }
    }
    
    public as a() {
        return (as)this.y.a(this.h);
    }
    
    public int g() {
        return this.h;
    }
    
    protected Dimension a() {
        final Dimension size = this.a.size();
        int n = size.width / 43;
        int n2 = size.height / 41;
        final int b = this.y.b();
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
    
    protected int c() {
        final Dimension a = this.a();
        return a.height * a.width;
    }
    
    public void resize(final int n, final int n2) {
        this.a.resize(n - 2 * this.a.size().width, n2);
    }
    
    public boolean action(final Event event, final Object o) {
        final int c = this.c();
        final int b = this.y.b();
        if (event.target == this.b) {
            this.i += c;
        }
        else {
            this.i -= c;
        }
        if (this.i >= b) {
            this.i = 0;
        }
        else if (this.i < 0) {
            this.i = b - 1 - (b - 1) % c;
        }
        final Graphics graphics = this.a.getGraphics();
        final Dimension size = this.a.size();
        graphics.setColor(this.a.getBackground());
        graphics.fillRect(0, 0, size.width, size.height);
        graphics.dispose();
        this.a.repaint();
        return true;
    }
    
    static aG a(final bV bv) {
        return bv.y;
    }
    
    public bV() {
        this.y = new aG();
        this.h = -1;
        this.i = 0;
        this.a = new bT(this);
        this.a = new bS(16, 30, 0);
        this.b = new bS(16, 30, 1);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.gridheight = 0;
        layout.setConstraints(this.a, gridBagConstraints);
        this.add(this.a);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        layout.setConstraints(this.a, gridBagConstraints);
        this.add(this.a);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 0;
        layout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        this.a.a(ao.e("Click here to view more icons."), null);
        this.b.a(ao.e("Click here to view more icons."), null);
    }
}
