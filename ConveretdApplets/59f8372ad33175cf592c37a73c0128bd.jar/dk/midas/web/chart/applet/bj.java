// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.Graphics;

public class bj extends w
{
    public bj(final be be, final DataSource dataSource) {
        super("Dot", be, dataSource);
    }
    
    protected void c(final Graphics graphics) {
        graphics.setColor(super.fd.ha);
        graphics.setColor(super.ft);
        for (int i = 0; i < super.fs; ++i) {
            graphics.fillOval(super.fp[i] - 3, super.fC[i] - 3, 6, 6);
        }
    }
    
    public void d(final Graphics graphics) {
    }
}
