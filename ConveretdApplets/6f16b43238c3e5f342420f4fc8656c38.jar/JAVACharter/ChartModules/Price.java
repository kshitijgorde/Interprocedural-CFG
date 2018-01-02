// 
// Decompiled by Procyon v0.5.30
// 

package JAVACharter.ChartModules;

import java.text.DecimalFormat;
import JAVACharter.c.c;
import java.awt.Color;
import JAVACharter.c.d;
import java.awt.Graphics;
import JAVACharter.b.h;
import JAVACharter.c.e;
import JAVACharter.c.g;
import JAVACharter.c.j;
import java.awt.Rectangle;
import JAVACharter.a.f;
import JAVACharter.StyleManage.a;

public class Price extends b
{
    private int[] al;
    private int[][] an;
    private int n;
    private a am;
    
    public Price(final f f, final Rectangle rectangle, final j j, final g g, final e e, final Integer n) {
        super(f, rectangle, j, g, e);
        this.n = 5;
        super.p = "date";
        super.m = new String[] { "open", "high", "low", "close" };
        super.null = new JAVACharter.b.e[super.m.length];
        f.a(this.n);
        j.if(false);
        this.am = e.do();
    }
    
    public void calc() {
        super.try.a(super.d.a(((h)super.null[1]).case()), super.d.if(((h)super.null[2]).case()));
    }
    
    public void calc_draw() {
        super.try.if(0);
        switch (super.void.byte.a()) {
            case 3: {
                this.al = new int[super.k * 2];
                super.void.byte.if(super.null[3], this.al);
                break;
            }
            case 0: {
                this.an = new int[super.k * 4][2];
                super.void.byte.a((h)super.null[0], (h)super.null[1], (h)super.null[2], (h)super.null[3], this.an);
                break;
            }
            case 1: {
                this.an = new int[2][super.k * 4];
                super.void.byte.a(super.null[3], this.an, super.try.a());
                break;
            }
            case 2: {
                this.al = new int[super.k * 2];
                super.void.byte.if(super.null[3], this.al);
                break;
            }
            case 4: {
                this.an = new int[super.k * 2][2];
                super.void.byte.a(super.null[3], this.an);
                break;
            }
            case 5: {
                this.an = new int[super.k * 4][2];
                super.void.byte.if((h)super.null[0], (h)super.null[1], (h)super.null[2], (h)super.null[3], this.an);
                break;
            }
        }
    }
    
    public void paintData(final Graphics graphics, final JAVACharter.c.b b) {
        super.o.if("mainsymbol");
        final Color a = super.o.a("mainsymbol", 0);
        final Color a2 = super.o.a("mainsymbol", 1);
        b.a(graphics, super.d.null(), a);
        graphics.setColor(a);
        int n = 0;
        switch (super.void.byte.a()) {
            case 2:
            case 3: {
                final int[] array = this.al;
                for (int i = 0; i < super.k; ++i) {
                    if (array[i * 2 + 1] != -1) {
                        ++n;
                    }
                }
                break;
            }
            case 0:
            case 5: {
                final int[][] array2 = this.an;
                for (int j = 0; j < super.k; ++j) {
                    if (array2[j * 4][0] != -1 && array2[j * 4][1] != -1 && array2[j * 4 + 1][0] != -1 && array2[j * 4 + 1][1] != -1) {
                        ++n;
                    }
                }
                break;
            }
            case 1: {
                final int[][] array3 = this.an;
                for (int k = 0; k < super.k; ++k) {
                    if (array3[0][k * 4] != -1 && array3[1][k * 4] != -1 && array3[0][k * 4 + 1] != -1 && array3[1][k * 4 + 1] != -1) {
                        ++n;
                    }
                }
                break;
            }
            default: {
                final int[][] array4 = this.an;
                for (int l = 0; l < super.k; ++l) {
                    if (array4[l * 2][0] != -1 && array4[l * 2][1] != -1 && array4[l * 2 + 1][0] != -1 && array4[l * 2 + 1][1] != -1) {
                        ++n;
                    }
                }
                break;
            }
        }
        if (n == 0) {
            super.void.a("Data is not available for this time.  Please select another.", false);
        }
        else {
            switch (super.void.byte.a()) {
                case 3: {
                    final e void1 = super.void;
                    JAVACharter.c.d.for(this.al, super.k, a, graphics);
                    break;
                }
                case 0: {
                    final e void2 = super.void;
                    JAVACharter.c.d.do(this.an, super.k, a, graphics);
                    break;
                }
                case 1: {
                    final e void3 = super.void;
                    JAVACharter.c.d.if(this.an, super.k, a, graphics);
                    break;
                }
                case 2: {
                    final e void4 = super.void;
                    JAVACharter.c.d.int(this.al, super.k, a, graphics);
                    break;
                }
                case 4: {
                    final e void5 = super.void;
                    JAVACharter.c.d.a(this.an, super.k, a, graphics);
                    break;
                }
                case 5: {
                    final e void6 = super.void;
                    JAVACharter.c.d.a(this.an, super.k, a, a2, graphics);
                    break;
                }
            }
        }
    }
    
    public boolean isOverlay() {
        return false;
    }
    
    public String getName() {
        return "Price";
    }
    
    public void mouseMove(final Graphics graphics, final int n, final int n2, final c c, final boolean b) {
        graphics.setColor(Color.black);
        final DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###,###,###,###");
        if (n >= super.if.x && n <= super.if.x + super.if.width) {
            final int if1 = super.j.if(n);
            if (if1 < super.k && if1 >= 0) {
                final float for1 = ((h)super.null[3]).for(if1);
                super.b = super.try.do(for1);
                if (for1 >= 1000000.0f) {
                    new StringBuffer().append(decimalFormat.format(for1 / 1000000.0f)).append("M").toString();
                }
                else if (for1 >= 1000.0f) {
                    new StringBuffer().append(decimalFormat.format(for1 / 1000.0f)).append("T").toString();
                }
                else {
                    decimalFormat.format(for1);
                }
                graphics.setColor(super.o.a("mouseline", 0));
                if (b) {
                    graphics.drawLine(super.j.a(if1), super.if.y, super.j.a(if1), super.if.y + super.if.height);
                }
                graphics.setXORMode(Color.yellow);
                if (if1 == super.long) {
                    graphics.fillOval(super.j.a(0) - 3, super.b - 3, 7, 7);
                }
                else {
                    graphics.fillOval(super.j.a(if1) - 3, super.b - 3, 7, 7);
                }
                graphics.setPaintMode();
                if (this.am.if()) {
                    graphics.drawLine(super.if.x, super.b, super.if.x + super.if.width, super.b);
                }
                if (((h)super.null[0]).for(if1) != super.long && ((h)super.null[3]).for(if1) != super.long) {
                    c.a(((h)super.null[0]).for(if1), ((h)super.null[1]).for(if1), ((h)super.null[2]).for(if1), ((h)super.null[3]).for(if1), ((JAVACharter.b.f)super.i).do(if1));
                }
            }
            super.c = if1;
        }
    }
}
