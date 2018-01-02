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

public class aM extends Panel
{
    private k a;
    protected int e;
    protected int f;
    private aR a;
    protected A a;
    protected A b;
    
    public void c() {
        this.a.b();
    }
    
    public void a(final ba ba) {
        this.a.a(ba);
    }
    
    public void b(final int n) {
        this.c(this.a.a(n));
    }
    
    public void c(final int e) {
        final int e2 = this.e;
        this.e = e;
        final int c = this.c();
        if (c == 0) {
            return;
        }
        final int f = c * (this.e / c);
        if (f != this.f) {
            this.f = f;
            this.a.repaint();
        }
        else {
            this.a.e(e2 - f);
            this.a.f(this.e - f);
        }
    }
    
    public ba a() {
        return (ba)this.a.a(this.e);
    }
    
    protected Dimension a() {
        final Dimension size = this.a.getSize();
        int n = size.width / 43;
        int n2 = size.height / 41;
        final int a = this.a.a();
        if (n * n2 > a) {
            for (int i = (int)Math.ceil(Math.sqrt(a)); i < n; ++i) {
                final int n3 = (int)Math.ceil(a / i);
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
    
    public void setSize(final int n, final int n2) {
        this.a.setSize(n - 2 * this.a.getSize().width, n2);
    }
    
    public boolean action(final Event event, final Object o) {
        final int c = this.c();
        final int a = this.a.a();
        if (event.target == this.b) {
            this.f += c;
        }
        else {
            this.f -= c;
        }
        if (this.f >= a) {
            this.f = 0;
        }
        else if (this.f < 0) {
            this.f = a - 1 - (a - 1) % c;
        }
        final Graphics graphics = this.a.getGraphics();
        final Dimension size = this.a.getSize();
        graphics.setColor(this.a.getBackground());
        graphics.fillRect(0, 0, size.width, size.height);
        graphics.dispose();
        this.a.repaint();
        return true;
    }
    
    static k a(final aM am) {
        return am.a;
    }
    
    public aM() {
        this.a = new k();
        this.e = -1;
        this.f = 0;
        this.a = new aR(this, this);
        this.a = new A(16, 30, 0);
        this.b = new A(16, 30, 1);
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
        this.a.a(aG.a("Click here to view more emoticons."), null);
        this.b.a(aG.a("Click here to view more emoticons."), null);
    }
}
