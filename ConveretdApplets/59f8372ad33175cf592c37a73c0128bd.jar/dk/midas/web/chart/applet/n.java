// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.Graphics;

public class n extends as
{
    public n(final be be, final DataSource dataSource) {
        super("BarHLC", be, dataSource);
    }
    
    protected void c(final Graphics graphics) {
        graphics.setColor(super.fd.g9);
        graphics.setColor(super.ft);
        super.f0 = (super.fp[1] - super.fp[0]) / 4;
        if (super.f0 > 4) {
            super.f0 = 4;
        }
        for (int i = 0; i < super.fs; ++i) {
            final int n = super.fp[i];
            final int n2 = super.fT[i];
            final int n3 = super.fW[i];
            int n4 = super.fV[i];
            int n5 = super.fS[i];
            if ((n4 == Integer.MIN_VALUE || n5 == Integer.MIN_VALUE) && n4 != n5) {
                if (n4 == Integer.MIN_VALUE) {
                    n4 = n5 + 3;
                }
                else {
                    n5 = n4 - 3;
                }
            }
            if (super.f0 < 2) {
                if (n4 != Integer.MIN_VALUE && n5 != Integer.MIN_VALUE) {
                    graphics.drawLine(n, n5, n, n4);
                }
                if (n3 != Integer.MIN_VALUE) {
                    graphics.drawLine(n, n3, n + 2, n3);
                }
            }
            else {
                if (n4 != Integer.MIN_VALUE && n5 != Integer.MIN_VALUE) {
                    graphics.fillRect(n - super.f0, n5, 2 * super.f0, n4 - n5 + 1);
                }
                if (n3 != Integer.MIN_VALUE) {
                    graphics.drawLine(n + super.f0, n3, n + 2 * super.f0, n3);
                }
            }
        }
    }
}
