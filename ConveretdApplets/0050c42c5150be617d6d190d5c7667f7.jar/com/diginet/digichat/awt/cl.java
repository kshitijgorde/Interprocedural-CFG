// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.Dimension;
import java.awt.Polygon;
import java.awt.Color;
import java.awt.Graphics;

public class cl extends o
{
    private int a;
    
    public final boolean inside(final int n, final int n2) {
        return this.g().inside(n, n2);
    }
    
    public final void a(final Graphics graphics) {
        this.setBackground(this.getParent().getBackground());
        final Color color = (super.e && super.d) ? Color.gray : ColorChoice.lightGray;
        final Color darker = color.darker();
        darker.darker();
        final Polygon g = this.g();
        final Polygon g2 = this.g();
        graphics.setColor(super.f ? Color.black : Color.gray);
        graphics.drawPolygon(g);
        if (super.f) {
            if (this.a == 1) {
                final int[] xpoints = g2.xpoints;
                final int n = 0;
                xpoints[n] += 2;
                final int[] xpoints2 = g2.xpoints;
                final int n2 = 1;
                --xpoints2[n2];
                final int[] xpoints3 = g2.xpoints;
                final int n3 = 2;
                xpoints3[n3] += 2;
                final int[] ypoints = g2.ypoints;
                final int n4 = 2;
                ypoints[n4] += 4;
            }
            if (this.a == 1 || this.a == 0) {
                final int[] ypoints2 = g2.ypoints;
                final int n5 = 0;
                ypoints2[n5] -= 2;
            }
            if (this.a == 0) {
                final int[] xpoints4 = g2.xpoints;
                final int n6 = 0;
                --xpoints4[n6];
                final int[] xpoints5 = g2.xpoints;
                final int n7 = 1;
                ++xpoints5[n7];
                final int[] xpoints6 = g2.xpoints;
                final int n8 = 2;
                --xpoints6[n8];
                final int[] ypoints3 = g2.ypoints;
                final int n9 = 2;
                ypoints3[n9] += 3;
            }
            final boolean b = this.a == 1;
            graphics.setColor(color);
            graphics.fillPolygon(g2);
            graphics.setColor((super.e && super.d) ? darker : Color.white);
            if (b) {
                graphics.drawLine(g.xpoints[2] + 1, g.ypoints[2] + 2, g.xpoints[2] + 1, g.ypoints[0] - 2);
            }
            else {
                graphics.drawLine(g.xpoints[0] - 1, g.ypoints[0] - 3, g.xpoints[1] + 1, g.ypoints[1]);
                graphics.drawLine(g.xpoints[1] + 1, g.ypoints[1], g.xpoints[2] - 1, g.ypoints[2] + 2);
            }
            if (!super.e || !super.d) {
                graphics.setColor(darker);
                if (b) {
                    graphics.drawLine(g.xpoints[0] + 2, g.ypoints[0] - 3, g.xpoints[1] - 1, g.ypoints[1]);
                    graphics.drawLine(g.xpoints[1] - 1, g.ypoints[1], g.xpoints[2] + 2, g.ypoints[2] + 3);
                    if (!super.e || !super.d) {
                        graphics.drawLine(g.xpoints[0], g.ypoints[0] + 1, g.xpoints[1], g.ypoints[1] + 1);
                    }
                }
                else {
                    graphics.drawLine(g.xpoints[2] - 1, g.ypoints[2] + 2, g.xpoints[2] - 1, g.ypoints[0] - 2);
                    if (!super.e || !super.d) {
                        graphics.drawLine(g.xpoints[0] + 1, g.ypoints[0] + 1, g.xpoints[0] + 1, g.ypoints[2] + 1);
                    }
                }
            }
        }
    }
    
    public final Polygon g() {
        final Dimension size = this.size();
        final int[] array = { size.height - 2, (size.height - 1) / 2, 0 };
        int[] array2 = null;
        switch (this.a) {
            case 1: {
                array2 = new int[] { 0, size.width - 2, 0 };
                break;
            }
            default: {
                array2 = new int[] { size.width - 2, 0, size.width - 2 };
                break;
            }
        }
        return new Polygon(array2, array, 3);
    }
    
    public cl(final int n, final int n2, final int a) {
        super(n, n2);
        this.a = a;
    }
}
