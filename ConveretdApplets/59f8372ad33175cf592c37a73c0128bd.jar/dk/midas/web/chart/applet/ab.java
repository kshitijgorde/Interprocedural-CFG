// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.Graphics;

public class ab extends w
{
    bk fE;
    boolean fD;
    
    public ab(final be be, final DataSource dataSource) {
        super("Step", be, dataSource);
        this.fD = false;
        if (dataSource.C().do()) {
            this.fD = true;
            this.fE = dataSource.b4;
        }
    }
    
    public void try(final DataSource dataSource) {
        super.try(dataSource);
        if (dataSource.C().do()) {
            this.fD = true;
            this.fE = dataSource.b4;
        }
        else {
            this.fD = false;
        }
    }
    
    protected void c(final Graphics graphics) {
        graphics.setColor(super.fd.g9);
        graphics.setColor(super.ft);
        int n = super.fp[0];
        int n2 = super.fC[0];
        int n3 = (n2 == Integer.MIN_VALUE) ? 1 : 0;
        for (int i = 1; i < super.fs; ++i) {
            if (n3 != 0) {
                n = super.fp[i];
                n2 = super.fC[i];
                n3 = 0;
            }
            final int n4 = super.fp[i];
            final int n5 = super.fC[i];
            if (n5 == Integer.MIN_VALUE) {
                n3 = 1;
            }
            else {
                graphics.drawLine(n, n2, n4, n2);
                graphics.drawLine(n4, n2, n4, n5);
                n = n4;
                n2 = n5;
            }
        }
    }
}
