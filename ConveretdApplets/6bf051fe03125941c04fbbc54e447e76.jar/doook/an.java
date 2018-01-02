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

public class an extends Panel
{
    private aT a;
    protected int t;
    protected int g;
    private am a;
    protected al a;
    protected al b;
    
    public void f() {
        this.a.g();
    }
    
    public void a(final af af) {
        this.a.a(af);
    }
    
    public void a(final int n) {
        this.b(this.a.g(n));
    }
    
    public void b(final int t) {
        final int t2 = this.t;
        this.t = t;
        final int f = this.f();
        if (f == 0) {
            return;
        }
        final int g = f * (this.t / f);
        if (g != this.g) {
            this.g = g;
            this.a.repaint();
        }
        else {
            this.a.c(t2 - g);
            this.a.m(this.t - g);
        }
    }
    
    public af a() {
        return (af)this.a.a(this.t);
    }
    
    protected Dimension a() {
        final Dimension size = this.a.getSize();
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
    
    protected int f() {
        final Dimension a = this.a();
        return a.height * a.width;
    }
    
    public void setSize(final int n, final int n2) {
        this.a.setSize(n - 2 * this.a.getSize().width, n2);
    }
    
    public boolean action(final Event event, final Object o) {
        final int f = this.f();
        final int b = this.a.b();
        if (event.target == this.b) {
            this.g += f;
        }
        else {
            this.g -= f;
        }
        if (this.g >= b) {
            this.g = 0;
        }
        else if (this.g < 0) {
            this.g = b - 1 - (b - 1) % f;
        }
        final Graphics graphics = this.a.getGraphics();
        final Dimension size = this.a.getSize();
        graphics.setColor(this.a.getBackground());
        graphics.fillRect(0, 0, size.width, size.height);
        graphics.dispose();
        this.a.repaint();
        return true;
    }
    
    static aT a(final an an) {
        return an.a;
    }
    
    public an() {
        this.a = new aT();
        this.t = -1;
        this.g = 0;
        this.a = new am(this);
        this.a = new al(16, 30, 0);
        this.b = new al(16, 30, 1);
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
        this.a.a(ar.b("Click here to view more icons."), null);
        this.b.a(ar.b("Click here to view more icons."), null);
    }
}
