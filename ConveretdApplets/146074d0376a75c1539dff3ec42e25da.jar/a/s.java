// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Panel;

public class s extends Panel
{
    private Insets q;
    private int q;
    
    public Insets insets() {
        return this.q;
    }
    
    public void paint(final Graphics graphics) {
        final int x = this.location().x;
        final int y = this.location().y;
        final Dimension size;
        final int n = (size = this.size()).width - 1;
        final int n2 = size.height - 1;
        graphics.setColor(this.getParent().getBackground());
        graphics.fillRect(0, 0, size.width, size.width);
        graphics.translate(-x, -y);
        this.getParent().paint(graphics);
        graphics.translate(x, y);
        if (this.q != 0) {
            final Color background = this.getBackground();
            graphics.setColor(background);
            graphics.fillRoundRect(0, 0, n, n2, this.q, this.q);
            graphics.drawArc(n - this.q, n2 - this.q - 1, this.q, this.q, 0, -90);
            graphics.setColor(background.darker());
            graphics.drawRoundRect(0, 0, n, n2, this.q, this.q);
            return;
        }
        final Container parent;
        Color color;
        if ((parent = this.getParent()) == null) {
            color = this.getBackground();
        }
        else {
            color = parent.getBackground();
        }
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, n, n2);
        graphics.setColor(color);
        graphics.drawRect(0, 0, n, n2);
        graphics.drawRect(1, 1, n - 2, n2 - 2);
        graphics.setColor(Color.black);
        graphics.drawLine(2, 1, n - 2, 1);
        graphics.drawLine(n - 1, 2, n - 1, n2 - 2);
        graphics.drawLine(n - 2, n2 - 1, 2, n2 - 1);
        graphics.drawLine(1, n2 - 2, 1, 2);
        graphics.setColor(color.brighter());
        graphics.drawLine(2, n2, n - 2, n2);
        graphics.drawLine(n - 1, n2 - 1, n, n2 - 2);
        graphics.drawLine(n, n2 - 3, n, 2);
        graphics.setColor(color.darker());
        graphics.drawLine(n - 1, 1, n - 2, 0);
        graphics.drawLine(n - 3, 0, 2, 0);
        graphics.drawLine(1, 1, 0, 2);
        graphics.drawLine(0, 3, 0, n2 - 3);
        graphics.drawLine(0, n2 - 2, 1, n2 - 1);
    }
    
    public s(final int n, final int n2, final int n3, final int n4, final int q) {
        this(n, n2, n3, n4);
        this.q = q;
    }
    
    public s(final int n, final int n2, final int n3, final int n4) {
        this.q = 0;
        this.setLayout(new BorderLayout());
        this.q = new Insets(n, n2, n3, n4);
    }
    
    public s() {
        this(4, 4, 4, 4);
    }
    
    public s(final int q) {
        this(4, 4, 4, 4);
        this.q = q;
    }
}
