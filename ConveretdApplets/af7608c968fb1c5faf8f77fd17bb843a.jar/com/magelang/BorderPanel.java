// 
// Decompiled by Procyon v0.5.30
// 

package com.magelang;

import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;

public class BorderPanel extends Panel
{
    public BorderPanel() {
        this.setLayout(new BorderLayout());
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        graphics.setColor(this.getBackground());
        graphics.draw3DRect(2, 2, size.width - 5, size.height - 5, true);
        graphics.draw3DRect(3, 3, size.width - 7, size.height - 7, false);
    }
    
    public BorderPanel(final LayoutManager layoutManager) {
        super(layoutManager);
    }
    
    public Insets getInsets() {
        return new Insets(5, 5, 5, 5);
    }
}
