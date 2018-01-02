// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.Graphics;

public class c extends w
{
    public c(final be be, final DataSource dataSource) {
        super("LineDot", be, dataSource);
    }
    
    protected void c(final Graphics graphics) {
        int n = 1;
        int n2 = super.fp[0];
        int n3 = super.fC[0];
        graphics.setColor(super.fd.ha);
        graphics.fillOval(n2 - 3, n3 - 3, 6, 6);
        for (int i = 0; i < super.fs; ++i) {
            if (n != 0) {
                n2 = super.fp[i];
                n3 = super.fC[i];
                n = 0;
            }
            final int n4 = super.fp[i];
            final int n5 = super.fC[i];
            if (n5 == Integer.MIN_VALUE) {
                n = 1;
            }
            else {
                graphics.setColor(super.fd.g9);
                graphics.setColor(super.ft);
                graphics.drawLine(n2, n3, n4, n5);
                graphics.setColor(super.fd.ha);
                graphics.setColor(super.ft);
                graphics.fillOval(n4 - 3, n5 - 3, 6, 6);
                n2 = n4;
                n3 = n5;
            }
        }
    }
}
