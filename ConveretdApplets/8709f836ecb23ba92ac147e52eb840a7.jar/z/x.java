// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;

public class x extends JPanel
{
    private int b;
    private Color c;
    private Color d;
    private boolean e;
    public boolean a;
    private static /* synthetic */ boolean f;
    
    public void paint(final Graphics graphics) {
        if (!this.a) {
            super.paint(graphics);
        }
    }
    
    public x() {
        this.e = false;
        this.setOpaque(this.a = false);
    }
    
    public final void a(int b) {
        if (b < 0) {
            b = 0;
        }
        else if (b > 100) {
            b = 100;
        }
        this.b = b;
        this.repaint();
    }
    
    public final void a(final Color c) {
        this.c = c;
    }
    
    public final void b(final Color d) {
        this.d = d;
    }
    
    public final void a(final boolean b) {
        this.e = true;
    }
    
    protected void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        final int n = this.getLocation().y + (this.getSize().height - this.getPreferredSize().height) / 2;
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, n, this.getSize().width, this.getPreferredSize().height);
        if (this.b > 0) {
            final int b = this.b;
            final int width = this.getSize().width;
            final int n2 = b;
            if (!x.f && (0 > n2 || n2 > 100)) {
                throw new AssertionError();
            }
            final int n3 = (n2 == 100) ? width : ((int)(n2 / 100.0 * width));
            graphics.setColor(this.e ? this.d : this.getForeground());
            graphics.fillRect(0, n, n3, this.getPreferredSize().height);
        }
        graphics.setColor(this.c);
        graphics.drawRect(0, n, this.getSize().width - 1, this.getPreferredSize().height);
    }
    
    static {
        x.f = !x.class.desiredAssertionStatus();
    }
}
