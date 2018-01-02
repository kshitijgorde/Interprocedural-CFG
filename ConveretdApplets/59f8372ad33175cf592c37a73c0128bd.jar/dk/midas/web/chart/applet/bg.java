// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.Graphics;
import java.awt.Color;

public class bg extends as
{
    private static final Color f2;
    private static final Color f1;
    
    public bg(final be be, final DataSource dataSource) {
        super("CandleStick", be, dataSource);
    }
    
    protected int r(final int n) {
        return n / bh.do;
    }
    
    protected void c(final Graphics graphics) {
        super.f0 = Math.min(4, (super.fp[1] - super.fp[0]) / 4);
        for (int i = 0; i < super.fs; ++i) {
            final int n = super.fW[i];
            if (n != Integer.MIN_VALUE) {
                final int n2 = super.fp[i];
                final int n3 = (super.fT[i] != Integer.MIN_VALUE) ? super.fT[i] : n;
                this.a(graphics, n2, (super.fV[i] != Integer.MIN_VALUE) ? super.fV[i] : n, (super.fS[i] != Integer.MIN_VALUE) ? super.fS[i] : n, n3, n);
                if (n3 < n) {
                    this.do(graphics, n2, n3, n);
                }
                else if (n3 == n) {
                    this.a(graphics, n2, n3, n);
                }
                else {
                    this.if(graphics, n2, n3, n);
                }
            }
        }
    }
    
    private void a(final Graphics graphics, final int n, final int n2, final int n3) {
        graphics.setColor(super.fd.g9);
        graphics.setColor(super.ft);
        if (super.f0 == 0) {
            graphics.drawLine(n - 1, n2, n + 1, n3);
        }
        else {
            graphics.drawLine(n - super.f0, n2, n + super.f0, n3);
        }
    }
    
    private void if(final Graphics graphics, final int n, final int n2, final int n3) {
        this.a(graphics, n, n2, n3, super.fd.gB, bg.f2, super.fd.gI);
    }
    
    private void do(final Graphics graphics, final int n, final int n2, final int n3) {
        this.a(graphics, n, n2, n3, super.fd.gT, bg.f1, super.fd.gO);
    }
    
    private void a(final Graphics graphics, final int n, final int n2, final int n3, final Color color, final Color color2, final Color color3) {
        if (super.f0 == 0) {
            if (color != null) {
                graphics.setColor(color);
            }
            else {
                graphics.setColor(color2);
            }
            graphics.drawLine(n, n3, n, n2);
        }
        else {
            final int min = Math.min(n2, n3);
            final int abs = Math.abs(n2 - n3);
            if (color != null) {
                graphics.setColor(color);
                graphics.fillRect(n - super.f0, min, 2 * super.f0 + 1, abs);
            }
            if (color3 != null) {
                graphics.setColor(color3);
                graphics.drawRect(n - super.f0, min, 2 * super.f0 + 1, abs);
            }
        }
    }
    
    private void a(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
        graphics.setColor(super.ft);
        final int min = Math.min(n4, n5);
        final int max = Math.max(n4, n5);
        final int min2 = Math.min(n2, n3);
        final int max2 = Math.max(n2, n3);
        if (min2 < min) {
            graphics.drawLine(n, min2, n, min);
        }
        if (max2 > max) {
            graphics.drawLine(n, max2, n, max);
        }
    }
    
    static {
        f2 = Color.green;
        f1 = Color.red;
    }
}
