// 
// Decompiled by Procyon v0.5.30
// 

package JAVACharter.ChartModules;

import java.text.DecimalFormat;
import JAVACharter.b.h;
import java.awt.Color;
import java.awt.Graphics;
import JAVACharter.b.c;
import JAVACharter.c.e;
import JAVACharter.c.g;
import JAVACharter.c.j;
import java.awt.Rectangle;
import JAVACharter.a.f;

public class VolPlus extends b
{
    private int[][] bm;
    private int[] bj;
    private long[] for;
    private int bk;
    private int bi;
    private int bl;
    private int n;
    
    public VolPlus(final f f, final Rectangle rectangle, final j j, final g g, final e e, final Integer n) {
        super(f, rectangle, j, g, e);
        this.bk = 0;
        this.bi = 0;
        this.n = 50;
        super.p = "date";
        super.m = new String[] { "volume", "open", "close" };
        super.null = new JAVACharter.b.e[super.m.length];
        f.a(this.n);
        e.case().a(super.char + "label0", "Volume");
    }
    
    public void calc() {
        this.bm = new int[super.k * 2][2];
        this.bj = new int[super.k * 2];
        this.for = new long[super.k];
        this.calc_sma(0, super.k - 1, this.n, (c)super.null[0], this.for);
        final float if1 = super.d.if(((c)super.null[0]).char());
        super.d.a(((c)super.null[0]).char());
        super.try.a(if1, 0.0f);
    }
    
    public void calc_draw() {
        final float n = super.if.width / super.k / 2.0f;
        super.void.byte.if(this.for, this.bj);
        super.void.byte.a(super.null[0], this.bm);
    }
    
    public boolean isOverlay() {
        return false;
    }
    
    public String getName() {
        return "VolPlus";
    }
    
    public void paintData(final Graphics graphics, final JAVACharter.c.b b) {
        super.o.if("compares");
        final Color a = super.o.a("bull", 0);
        final Color a2 = super.o.a("bear", 0);
        final c c = (c)super.null[0];
        final Color color = new Color(100, 25, 25);
        b.a(graphics, "Volume+", null);
        b.a(graphics, "Close Up", a);
        b.a(graphics, "Close Down", a2);
        b.a(graphics, "SMA (50)", color);
        int n = 0;
        for (int i = 0; i < super.k; ++i) {
            float n2;
            if (i > 0) {
                n2 = ((h)super.null[2]).for(i - 1);
            }
            else {
                n2 = ((h)super.null[1]).for(i);
            }
            if (n2 < ((h)super.null[2]).for(i)) {
                graphics.setColor(a);
            }
            else {
                graphics.setColor(a2);
            }
            if (c.new(i) != super.long) {
                ++n;
                graphics.fillRect(this.bm[i * 2][0], this.bm[i * 2][1], this.bm[i * 2 + 1][0] - this.bm[i * 2][0], this.bm[i * 2 + 1][1]);
            }
        }
        for (int j = 0; j < super.k; ++j) {
            graphics.setColor(color);
            if (j < super.k - 1 && this.bj[j * 2 + 1] != (int)super.s && this.bj[j * 2 + 3] != (int)super.s) {
                graphics.drawLine(this.bj[j * 2], this.bj[j * 2 + 1], this.bj[j * 2 + 2], this.bj[j * 2 + 3]);
                graphics.drawLine(this.bj[j * 2], this.bj[j * 2 + 1] - 1, this.bj[j * 2 + 2], this.bj[j * 2 + 3] - 1);
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
                graphics.setColor(super.o.a("mouseline", 0));
                graphics.drawLine(super.j.a(if1), super.if.y, super.j.a(if1), super.if.y + super.if.height);
                if (((c)super.null[0]).new(if1) != super.s) {
                    c.a(super.char + "field0", this.a(new1));
                }
            }
            super.c = if1;
        }
    }
    
    public void calc_sma(final int n, final int n2, final long n3, final c c, final long[] array) {
        for (int i = n; i <= n2; ++i) {
            long n4 = 0L;
            if (c.new(i) != super.s) {
                int n5 = 0;
                int n6 = 0;
                while (n5 < n3) {
                    if (c.new(i - n5) != super.s) {
                        n4 += c.new(i - n5);
                        ++n6;
                    }
                    ++n5;
                }
                if (n6 == n3) {
                    array[i] = n4 / n3;
                }
                else {
                    array[i] = super.s;
                }
            }
            else {
                array[i] = super.s;
            }
        }
    }
}
