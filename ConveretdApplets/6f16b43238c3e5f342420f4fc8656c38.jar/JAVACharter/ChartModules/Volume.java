// 
// Decompiled by Procyon v0.5.30
// 

package JAVACharter.ChartModules;

import java.text.DecimalFormat;
import java.awt.Color;
import java.awt.Graphics;
import JAVACharter.b.c;
import JAVACharter.c.e;
import JAVACharter.c.g;
import JAVACharter.c.j;
import java.awt.Rectangle;
import JAVACharter.a.f;

public class Volume extends b
{
    private int[][] N;
    private float[][] K;
    private int M;
    private int L;
    
    public Volume(final f f, final Rectangle rectangle, final j j, final g g, final e e, final Integer n) {
        super(f, rectangle, j, g, e);
        this.M = 0;
        this.L = 0;
        super.p = "date";
        super.m = new String[] { "volume" };
        super.null = new JAVACharter.b.e[super.m.length];
        e.case().a(super.char + "label0", "Volume");
    }
    
    public void calc() {
        super.try.a(super.d.if(((c)super.null[0]).char()), 0.0f);
    }
    
    public void calc_draw() {
        final float n = super.if.width / super.k / 2.0f;
        this.N = new int[super.k * 2][2];
        super.void.byte.a(super.null[0], this.N);
    }
    
    public boolean isOverlay() {
        return true;
    }
    
    public String getName() {
        return "Volume";
    }
    
    public void paintData(final Graphics graphics, final JAVACharter.c.b b) {
        final c c = (c)super.null[0];
        final Color a = super.o.a("mainsymbol", 0);
        b.a(graphics, "Volume", a);
        graphics.setColor(a);
        int n = 0;
        for (int i = 0; i < super.k; ++i) {
            if (c.new(i) != super.long) {
                ++n;
                graphics.fillRect(this.N[i * 2][0], this.N[i * 2][1], this.N[i * 2 + 1][0] - this.N[i * 2][0], this.N[i * 2 + 1][1]);
            }
        }
        if (n == 0) {
            super.void.a("Exchange provides no volume data", false);
        }
    }
    
    public void mouseMove(final Graphics graphics, final int n, final int n2, final JAVACharter.c.c c, final boolean b) {
        final DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###,###,###,###");
        if (n >= super.if.x && n <= super.if.x + super.if.width) {
            final int if1 = super.j.if(n);
            if (if1 < super.k && if1 >= 0) {
                final long new1 = ((c)super.null[0]).new(if1);
                super.b = super.try.a(new1);
                if (new1 >= 1000000L) {
                    new StringBuffer().append(decimalFormat.format((double)(new1 / 1000000L))).append("M").toString();
                }
                else if (new1 >= 1000L) {
                    new StringBuffer().append(decimalFormat.format((double)(new1 / 1000L))).append("T").toString();
                }
                else {
                    decimalFormat.format((double)new1);
                }
                if (b) {
                    graphics.setColor(super.o.a("mouseline", 0));
                }
                graphics.drawLine(super.j.a(if1), super.if.y, super.j.a(if1), super.if.y + super.if.height - 1);
                if (((c)super.null[0]).new(if1) != super.s) {
                    c.a(super.char + "field0", this.a(new1));
                }
            }
            super.c = if1;
        }
    }
}
