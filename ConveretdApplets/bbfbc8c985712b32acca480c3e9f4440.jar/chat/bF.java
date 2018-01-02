// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Panel;

public class bF extends Panel
{
    private Insets a;
    private int a;
    
    public Insets insets() {
        return this.a;
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
        if (this.a != 0) {
            final Color background = this.getBackground();
            graphics.setColor(background);
            graphics.fillRoundRect(0, 0, n, n2, this.a, this.a);
            graphics.drawArc(n - this.a, n2 - this.a - 1, this.a, this.a, 0, -90);
            graphics.setColor(background.darker());
            graphics.drawRoundRect(0, 0, n, n2, this.a, this.a);
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
    
    public bF(final int n, final int n2, final int n3, final int n4, final int a) {
        this(n, n2, n3, n4, (byte)0);
        this.a = a;
    }
    
    public bF(final int n, final int n2, final int n3, final int n4, final byte b) {
        this(n, n2, n3, n4);
    }
    
    private bF(final int n, final int n2, final int n3, final int n4) {
        this.a = 0;
        this.setLayout(new BorderLayout());
        this.a = new Insets(n, n2, n3, n4);
    }
    
    public bF() {
        this(4, 4, 4, 4, (byte)0);
    }
    
    public bF(final int a) {
        this(4, 4, 4, 4, (byte)0);
        this.a = a;
    }
}
