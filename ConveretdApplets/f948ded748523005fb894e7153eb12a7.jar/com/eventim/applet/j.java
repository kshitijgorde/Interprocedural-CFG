// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet;

import java.awt.Shape;
import java.awt.Rectangle;
import java.awt.Paint;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.LayoutManager;
import javax.swing.JPanel;

final class j extends JPanel
{
    j() {
        super(null);
    }
    
    public final void paint(final Graphics graphics) {
        super.paint(graphics);
        final Graphics2D graphics2D;
        (graphics2D = (Graphics2D)graphics).setPaint(new Color(1.0f, 1.0f, 1.0f, 0.5f));
        graphics2D.fill(new Rectangle(0, 0, this.getWidth(), this.getHeight()));
        this.paintChildren(graphics);
    }
}
