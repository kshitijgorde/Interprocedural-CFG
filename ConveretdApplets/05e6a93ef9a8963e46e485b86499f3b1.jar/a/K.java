// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Dimension;
import java.awt.Polygon;
import java.awt.Color;
import java.awt.Graphics;

public final class K extends N
{
    private int q;
    
    public final boolean inside(final int n, final int n2) {
        return this.q().inside(n, n2);
    }
    
    public final void q(final Graphics graphics) {
        this.setBackground(this.getParent().getBackground());
        final Color color;
        final Color darker;
        (darker = (color = ((super.w && super.q) ? Color.gray : ai.w)).darker()).darker();
        final Polygon q = this.q();
        final Polygon q2 = this.q();
        graphics.setColor(super.q() ? Color.black : Color.gray);
        graphics.drawPolygon(q);
        if (super.q()) {
            if (this.q == 1) {
                final int[] xpoints = q2.xpoints;
                final int n = 0;
                xpoints[n] += 2;
                final int[] xpoints2 = q2.xpoints;
                final int n2 = 1;
                --xpoints2[n2];
                final int[] xpoints3 = q2.xpoints;
                final int n3 = 2;
                xpoints3[n3] += 2;
                final int[] ypoints = q2.ypoints;
                final int n4 = 2;
                ypoints[n4] += 4;
            }
            if (this.q == 1 || this.q == 0) {
                final int[] ypoints2 = q2.ypoints;
                final int n5 = 0;
                ypoints2[n5] -= 2;
            }
            if (this.q == 0) {
                final int[] xpoints4 = q2.xpoints;
                final int n6 = 0;
                --xpoints4[n6];
                final int[] xpoints5 = q2.xpoints;
                final int n7 = 1;
                ++xpoints5[n7];
                final int[] xpoints6 = q2.xpoints;
                final int n8 = 2;
                --xpoints6[n8];
                final int[] ypoints3 = q2.ypoints;
                final int n9 = 2;
                ypoints3[n9] += 3;
            }
            final boolean b = this.q == 1;
            graphics.setColor(color);
            graphics.fillPolygon(q2);
            graphics.setColor((!super.w || !super.q) ? Color.white : darker);
            if (b) {
                graphics.drawLine(q.xpoints[2] + 1, q.ypoints[2] + 2, q.xpoints[2] + 1, q.ypoints[0] - 2);
            }
            else {
                graphics.drawLine(q.xpoints[0] - 1, q.ypoints[0] - 3, q.xpoints[1] + 1, q.ypoints[1]);
                graphics.drawLine(q.xpoints[1] + 1, q.ypoints[1], q.xpoints[2] - 1, q.ypoints[2] + 2);
            }
            if (!super.w || !super.q) {
                graphics.setColor(darker);
                if (b) {
                    graphics.drawLine(q.xpoints[0] + 2, q.ypoints[0] - 3, q.xpoints[1] - 1, q.ypoints[1]);
                    graphics.drawLine(q.xpoints[1] - 1, q.ypoints[1], q.xpoints[2] + 2, q.ypoints[2] + 3);
                    if (!super.w || !super.q) {
                        graphics.drawLine(q.xpoints[0], q.ypoints[0] + 1, q.xpoints[1], q.ypoints[1] + 1);
                    }
                }
                else {
                    graphics.drawLine(q.xpoints[2] - 1, q.ypoints[2] + 2, q.xpoints[2] - 1, q.ypoints[0] - 2);
                    if (!super.w || !super.q) {
                        graphics.drawLine(q.xpoints[0] + 1, q.ypoints[0] + 1, q.xpoints[0] + 1, q.ypoints[2] + 1);
                    }
                }
            }
        }
    }
    
    private Polygon q() {
        final Dimension size;
        final int[] array = { size.height - 2, ((size = this.size()).height - 1) / 2, 0 };
        int[] array2 = null;
        switch (this.q) {
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
    
    public K(final int n, final int n2, final int q) {
        super(16, 30);
        this.q = q;
    }
}
