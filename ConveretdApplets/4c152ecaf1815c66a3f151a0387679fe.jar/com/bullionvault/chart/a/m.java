// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.a;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JPanel;

public final class m extends JPanel
{
    private Dimension a;
    
    public m() {
        this.a = new Dimension();
    }
    
    public final void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        graphics.drawLine(0, 0, size.width, size.height);
        final Color background = this.getBackground();
        graphics.setColor(background.darker());
        graphics.drawLine(0, size.height - 2, size.width, size.height - 2);
        graphics.setColor(background.brighter());
        graphics.drawLine(0, size.height - 1, size.width, size.height - 1);
    }
    
    public final Dimension getMinimumSize() {
        return this.getPreferredSize();
    }
    
    public final Dimension getPreferredSize() {
        this.a.height = 2;
        return this.a;
    }
}
