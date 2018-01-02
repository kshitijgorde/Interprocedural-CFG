// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Panel;

public final class be extends Panel
{
    public W a;
    protected int a;
    protected int b;
    private bf a;
    protected bj a;
    protected bj b;
    
    public final void a(int a) {
        final int a2 = this.a;
        this.a = a;
        if ((a = this.a()) == 0) {
            return;
        }
        if ((a *= this.a / a) != this.b) {
            this.b = a;
            this.a.repaint();
            return;
        }
        final bf a3 = this.a;
        final int n = a2 - a;
        final bf bf = a3;
        final Graphics graphics;
        if ((graphics = a3.getGraphics()) != null) {
            final Rectangle a4;
            int x = (a4 = bf.a(n)).x;
            int y = a4.y;
            int height = a4.height;
            int width = a4.width;
            graphics.setColor(bf.getBackground());
            for (int i = 0; i <= 4; ++i) {
                graphics.drawRect(x, y, width, height);
                ++x;
                ++y;
                height -= 2;
                width -= 2;
            }
            graphics.dispose();
        }
        this.a.a(this.a - a);
    }
    
    public final K a() {
        return (K)this.a.a(this.a);
    }
    
    protected final Dimension a() {
        final Dimension size;
        int n = (size = this.a.size()).width / 43;
        int n2 = size.height / 41;
        final int a = this.a.a();
        if (n * n2 > a) {
            for (int i = (int)Math.ceil(Math.sqrt(a)); i < n; ++i) {
                final int n3;
                if ((n3 = (int)Math.ceil(a / i)) <= n2) {
                    n = i;
                    n2 = n3;
                    break;
                }
            }
        }
        return new Dimension(n, n2);
    }
    
    protected final int a() {
        final Dimension a = this.a();
        return a.height * a.width;
    }
    
    public final void resize(final int n, final int n2) {
        this.a.resize(n - 2 * this.a.size().width, n2);
    }
    
    public final boolean action(final Event event, final Object o) {
        final int a = this.a();
        final int a2 = this.a.a();
        if (event.target == this.b) {
            this.b += a;
        }
        else {
            this.b -= a;
        }
        if (this.b >= a2) {
            this.b = 0;
        }
        else if (this.b < 0) {
            this.b = a2 - 1 - (a2 - 1) % a;
        }
        final Graphics graphics = this.a.getGraphics();
        final Dimension size = this.a.size();
        graphics.setColor(this.a.getBackground());
        graphics.fillRect(0, 0, size.width, size.height);
        graphics.dispose();
        this.a.repaint();
        return true;
    }
    
    public be(final bh bh) {
        this.a = new W();
        this.a = -1;
        this.b = 0;
        this.a = new bf(this);
        this.a = new bj(0);
        this.b = new bj(1);
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
        this.a.a(ak.a(495), null);
        this.b.a(ak.a(495), null);
    }
}
