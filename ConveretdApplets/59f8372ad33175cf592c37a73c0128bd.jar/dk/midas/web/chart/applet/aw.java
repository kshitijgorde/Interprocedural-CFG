// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.Graphics;

public class aw extends w
{
    bk fG;
    boolean fF;
    
    public aw(final be be, final DataSource dataSource) {
        super("Line", be, dataSource);
        this.fF = false;
        if (dataSource.C().do()) {
            this.fF = true;
            this.fG = dataSource.b4;
        }
    }
    
    public void try(final DataSource dataSource) {
        super.try(dataSource);
        if (dataSource.C().do()) {
            this.fF = true;
            this.fG = dataSource.b4;
        }
        else {
            this.fF = false;
        }
    }
    
    protected int r(final int n) {
        return n;
    }
    
    protected void c(final Graphics graphics) {
        graphics.setColor(super.fd.g9);
        graphics.setColor(super.ft);
        int n = 1;
        int n2 = super.fp[0];
        int n3 = super.fC[0];
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
                graphics.drawLine(n2, n3, n4, n5);
                n2 = n4;
                n3 = n5;
            }
        }
    }
}
