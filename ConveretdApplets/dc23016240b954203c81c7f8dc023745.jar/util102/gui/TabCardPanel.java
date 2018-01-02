// 
// Decompiled by Procyon v0.5.30
// 

package util102.gui;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Panel;

public class TabCardPanel extends Panel
{
    int topInsets;
    int bottomInsets;
    int leftInsets;
    int rightInsets;
    boolean withTop;
    
    public TabCardPanel() {
        this(2, 2, 2, 2, false);
    }
    
    public TabCardPanel(final int n, final int n2, final int n3, final int n4) {
        this(n, n2, n3, n4, false);
    }
    
    public TabCardPanel(final int topInsets, final int leftInsets, final int bottomInsets, final int rightInsets, final boolean withTop) {
        this.topInsets = 2;
        this.bottomInsets = 2;
        this.leftInsets = 2;
        this.rightInsets = 2;
        this.withTop = false;
        this.topInsets = topInsets;
        this.leftInsets = leftInsets;
        this.bottomInsets = bottomInsets;
        this.rightInsets = rightInsets;
        this.withTop = withTop;
    }
    
    public Insets insets() {
        return new Insets(this.topInsets, this.leftInsets, this.bottomInsets, this.rightInsets);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        final Color background = this.getBackground();
        if (this.withTop) {
            graphics.setColor(background.brighter());
            graphics.drawLine(0, 0, size.width - 2, 0);
        }
        graphics.setColor(Color.black);
        graphics.drawLine(0, size.height - 1, size.width, size.height - 1);
        graphics.drawLine(size.width - 1, 0, size.width - 1, size.height);
        graphics.setColor(background.darker());
        graphics.drawLine(0, size.height - 2, size.width - 2, size.height - 2);
        graphics.drawLine(size.width - 2, 0, size.width - 2, size.height - 2);
        graphics.setColor(background.brighter());
        graphics.drawLine(0, 0, 0, size.height);
    }
}
