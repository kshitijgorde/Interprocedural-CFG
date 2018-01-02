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

public final class ck extends Panel
{
    public av a;
    protected int a;
    protected int b;
    private cl a;
    protected cv a;
    protected cv b;
    
    public final void a(final b b) {
        this.a.a(b);
    }
    
    public final void a(final int n) {
        this.b(this.a.a(n));
    }
    
    public final void b(int b) {
        final int a = this.a;
        this.a = b;
        if ((b = this.b()) == 0) {
            return;
        }
        if ((b *= this.a / b) != this.b) {
            this.b = b;
            this.a.repaint();
            return;
        }
        final cl a2 = this.a;
        final int n = a - b;
        final cl cl = a2;
        final Graphics graphics;
        if ((graphics = a2.getGraphics()) != null) {
            final Rectangle a3;
            int x = (a3 = cl.a(n)).x;
            int y = a3.y;
            int height = a3.height;
            int width = a3.width;
            graphics.setColor(cl.getBackground());
            for (int i = 0; i <= 4; ++i) {
                graphics.drawRect(x, y, width, height);
                ++x;
                ++y;
                height -= 2;
                width -= 2;
            }
            graphics.dispose();
        }
        this.a.a(this.a - b);
    }
    
    public final b a() {
        return (b)this.a.a(this.a);
    }
    
    public final int a() {
        return this.a;
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
    
    protected final int b() {
        final Dimension a = this.a();
        return a.height * a.width;
    }
    
    public final void resize(final int n, final int n2) {
        this.a.resize(n - 2 * this.a.size().width, n2);
    }
    
    public final boolean action(final Event event, final Object o) {
        final int b = this.b();
        final int a = this.a.a();
        if (event.target == this.b) {
            this.b += b;
        }
        else {
            this.b -= b;
        }
        if (this.b >= a) {
            this.b = 0;
        }
        else if (this.b < 0) {
            this.b = a - 1 - (a - 1) % b;
        }
        final Graphics graphics = this.a.getGraphics();
        final Dimension size = this.a.size();
        graphics.setColor(this.a.getBackground());
        graphics.fillRect(0, 0, size.width, size.height);
        graphics.dispose();
        this.a.repaint();
        return true;
    }
    
    public ck() {
        this.a = new av();
        this.a = -1;
        this.b = 0;
        this.a = new cl(this);
        this.a = new cv(16, 30, 0);
        this.b = new cv(16, 30, 1);
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
        this.a.a(aS.a(463), null);
        this.b.a(aS.a(463), null);
    }
}
