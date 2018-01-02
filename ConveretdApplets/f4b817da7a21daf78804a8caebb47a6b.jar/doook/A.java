// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Dimension;
import java.awt.Polygon;
import java.awt.Color;
import java.awt.Graphics;

public class A extends al
{
    private int g;
    
    public boolean inside(final int n, final int n2) {
        return this.a().inside(n, n2);
    }
    
    public void a(final Graphics graphics) {
        this.setBackground(this.getParent().getBackground());
        final Color color = (super.j && super.h) ? Color.gray : aH.b;
        final Color darker = color.darker();
        darker.darker();
        final Polygon a = this.a();
        final Polygon a2 = this.a();
        graphics.setColor(super.m ? Color.black : Color.gray);
        graphics.drawPolygon(a);
        if (super.m) {
            if (this.g == 1) {
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
            if (this.g == 1 || this.g == 0) {
                final int[] ypoints2 = a2.ypoints;
                final int n5 = 0;
                ypoints2[n5] -= 2;
            }
            if (this.g == 0) {
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
            final boolean b = this.g == 1;
            graphics.setColor(color);
            graphics.fillPolygon(a2);
            graphics.setColor((super.j && super.h) ? darker : Color.white);
            if (b) {
                graphics.drawLine(a.xpoints[2] + 1, a.ypoints[2] + 2, a.xpoints[2] + 1, a.ypoints[0] - 2);
            }
            else {
                graphics.drawLine(a.xpoints[0] - 1, a.ypoints[0] - 3, a.xpoints[1] + 1, a.ypoints[1]);
                graphics.drawLine(a.xpoints[1] + 1, a.ypoints[1], a.xpoints[2] - 1, a.ypoints[2] + 2);
            }
            if (!super.j || !super.h) {
                graphics.setColor(darker);
                if (b) {
                    graphics.drawLine(a.xpoints[0] + 2, a.ypoints[0] - 3, a.xpoints[1] - 1, a.ypoints[1]);
                    graphics.drawLine(a.xpoints[1] - 1, a.ypoints[1], a.xpoints[2] + 2, a.ypoints[2] + 3);
                    if (!super.j || !super.h) {
                        graphics.drawLine(a.xpoints[0], a.ypoints[0] + 1, a.xpoints[1], a.ypoints[1] + 1);
                    }
                }
                else {
                    graphics.drawLine(a.xpoints[2] - 1, a.ypoints[2] + 2, a.xpoints[2] - 1, a.ypoints[0] - 2);
                    if (!super.j || !super.h) {
                        graphics.drawLine(a.xpoints[0] + 1, a.ypoints[0] + 1, a.xpoints[0] + 1, a.ypoints[2] + 1);
                    }
                }
            }
        }
    }
    
    public Polygon a() {
        final Dimension size = this.size();
        final int[] array = { size.height - 2, (size.height - 1) / 2, 0 };
        int[] array2 = null;
        switch (this.g) {
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
    
    public A(final int n, final int n2, final int g) {
        super(n, n2);
        this.g = g;
    }
}
