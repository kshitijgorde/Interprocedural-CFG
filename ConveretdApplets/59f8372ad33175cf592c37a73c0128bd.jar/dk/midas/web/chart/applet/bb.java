// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.Graphics;

public class bb extends am
{
    bk fP;
    boolean fO;
    
    public bb(final be be, final DataSource dataSource) {
        super("BidAsk", be, dataSource);
        this.fO = false;
        if (dataSource.C().do()) {
            this.fO = true;
            this.fP = dataSource.b4;
        }
    }
    
    public void try(final DataSource dataSource) {
        super.try(dataSource);
        if (dataSource.C().do()) {
            this.fO = true;
            this.fP = dataSource.b4;
        }
        else {
            this.fO = false;
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
        int n3 = super.fI[0];
        for (int i = 1; i < super.fs; ++i) {
            if (n != 0) {
                n2 = super.fp[i];
                n3 = super.fI[i];
                n = 0;
            }
            final int n4 = super.fp[i];
            final int n5 = super.fI[i];
            if (n5 == Integer.MIN_VALUE) {
                n = 1;
            }
            else {
                graphics.drawLine(n2, n3, n4, n5);
                n2 = n4;
                n3 = n5;
            }
        }
        int n6 = 1;
        int n7 = super.fp[0];
        int n8 = super.fN[0];
        for (int j = 1; j < super.fs; ++j) {
            if (n6 != 0) {
                n7 = super.fp[j];
                n8 = super.fN[j];
                n6 = 0;
            }
            final int n9 = super.fp[j];
            final int n10 = super.fN[j];
            if (n10 == Integer.MIN_VALUE) {
                n6 = 1;
            }
            else {
                graphics.drawLine(n7, n8, n9, n10);
                n7 = n9;
                n8 = n10;
            }
        }
    }
}
