// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Dimension;
import java.awt.Polygon;
import java.awt.Color;
import java.awt.Graphics;

public final class cv extends cr
{
    private int a;
    
    public final boolean inside(final int n, final int n2) {
        return this.a().inside(n, n2);
    }
    
    public final void a(final Graphics graphics) {
        this.setBackground(this.getParent().getBackground());
        final Color color;
        final Color darker = (color = ((super.b && super.a) ? Color.gray : o.b)).darker();
        final Polygon a = this.a();
        final Polygon a2 = this.a();
        graphics.setColor(super.c ? Color.black : Color.gray);
        graphics.drawPolygon(a);
        if (super.c) {
            if (this.a == 1) {
                final int[] xpoints = a2.xpoints;
                final int n = 0;
                xpoints[n] += 2;
                final int[] xpoints2 = a2.xpoints;
                final int n2 = 1;
                --xpoints2[n2];
                final int[] xpoints3 = a2.xpoints;
                final int n3 = 2;
                xpoints3[n3] += 2;
                final int[] ypoints = a2.ypoints;
                final int n4 = 2;
                ypoints[n4] += 4;
            }
            if (this.a == 1 || this.a == 0) {
                final int[] ypoints2 = a2.ypoints;
                final int n5 = 0;
                ypoints2[n5] -= 2;
            }
            if (this.a == 0) {
                final int[] xpoints4 = a2.xpoints;
                final int n6 = 0;
                --xpoints4[n6];
                final int[] xpoints5 = a2.xpoints;
                final int n7 = 1;
                ++xpoints5[n7];
                final int[] xpoints6 = a2.xpoints;
                final int n8 = 2;
                --xpoints6[n8];
                final int[] ypoints3 = a2.ypoints;
                final int n9 = 2;
                ypoints3[n9] += 3;
            }
            final boolean b = this.a == 1;
            graphics.setColor(color);
            graphics.fillPolygon(a2);
            graphics.setColor((!super.b || !super.a) ? Color.white : darker);
            if (b) {
                graphics.drawLine(a.xpoints[2] + 1, a.ypoints[2] + 2, a.xpoints[2] + 1, a.ypoints[0] - 2);
            }
            else {
                graphics.drawLine(a.xpoints[0] - 1, a.ypoints[0] - 3, a.xpoints[1] + 1, a.ypoints[1]);
                graphics.drawLine(a.xpoints[1] + 1, a.ypoints[1], a.xpoints[2] - 1, a.ypoints[2] + 2);
            }
            if (!super.b || !super.a) {
                graphics.setColor(darker);
                if (b) {
                    graphics.drawLine(a.xpoints[0] + 2, a.ypoints[0] - 3, a.xpoints[1] - 1, a.ypoints[1]);
                    graphics.drawLine(a.xpoints[1] - 1, a.ypoints[1], a.xpoints[2] + 2, a.ypoints[2] + 3);
                    if (!super.b || !super.a) {
                        graphics.drawLine(a.xpoints[0], a.ypoints[0] + 1, a.xpoints[1], a.ypoints[1] + 1);
                    }
                }
                else {
                    graphics.drawLine(a.xpoints[2] - 1, a.ypoints[2] + 2, a.xpoints[2] - 1, a.ypoints[0] - 2);
                    if (!super.b || !super.a) {
                        graphics.drawLine(a.xpoints[0] + 1, a.ypoints[0] + 1, a.xpoints[0] + 1, a.ypoints[2] + 1);
                    }
                }
            }
        }
    }
    
    private Polygon a() {
        final Dimension size;
        final int[] array = { size.height - 2, ((size = this.size()).height - 1) / 2, 0 };
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
    
    public cv(final int n, final int n2, final int a) {
        super(n, n2);
        this.a = a;
    }
}
