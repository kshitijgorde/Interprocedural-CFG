// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.Graphics;

public class x extends w
{
    public x(final be be, final DataSource dataSource) {
        super("Forest", be, dataSource);
    }
    
    protected void c(final Graphics graphics) {
        graphics.setColor(super.fd.g9);
        graphics.setColor(super.ft);
        int n = Integer.MIN_VALUE;
        for (int i = super.fs - 1; i >= 0; --i) {
            if (super.fC[i] != Integer.MIN_VALUE) {
                n = super.fC[super.fs - 1];
                break;
            }
        }
        for (int j = 0; j < super.fs; ++j) {
            final int n2 = super.fp[j];
            final int n3 = super.fC[j];
            if (n3 != Integer.MIN_VALUE) {
                graphics.drawLine(n2, n, n2, n3);
            }
        }
    }
    
    public void d(final Graphics graphics) {
    }
}
