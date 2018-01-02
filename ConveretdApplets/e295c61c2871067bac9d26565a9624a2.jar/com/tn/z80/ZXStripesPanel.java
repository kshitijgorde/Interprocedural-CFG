// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.z80;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Panel;

public class ZXStripesPanel extends Panel
{
    private boolean ivStripesVisible;
    
    public ZXStripesPanel() {
        this.ivStripesVisible = true;
    }
    
    public ZXStripesPanel(final LayoutManager layout) {
        super(layout);
        this.ivStripesVisible = true;
    }
    
    public boolean getStripesVisible() {
        return this.ivStripesVisible;
    }
    
    @Override
    public void paint(final Graphics g) {
        if (this.ivStripesVisible) {
            final int xsize = this.getSize().width;
            final int ysize = this.getSize().height;
            final int x1 = xsize - 48;
            final int y1 = ysize - 48;
            final int x2 = xsize;
            final int y2 = ysize;
            final Color[] stripeColours = { Color.red, Color.yellow, Color.green, Color.cyan };
            for (int n = 0; n < 4; ++n) {
                final int xm1 = x1 + n * (x2 - x1) / 5;
                final int ym1 = y1 + n * (y2 - y1) / 5;
                final int xm2 = x1 + (n + 1) * (x2 - x1) / 5;
                final int ym2 = y1 + (n + 1) * (y2 - y1) / 5;
                g.setColor(stripeColours[n]);
                g.fillPolygon(new int[] { xm1, xm2, x2, x2 }, new int[] { y2, y2, ym2, ym1 }, 4);
            }
        }
        super.paint(g);
    }
    
    public void setStripesVisible(final boolean pStripesVisible) {
        this.ivStripesVisible = pStripesVisible;
        this.repaint();
    }
}
