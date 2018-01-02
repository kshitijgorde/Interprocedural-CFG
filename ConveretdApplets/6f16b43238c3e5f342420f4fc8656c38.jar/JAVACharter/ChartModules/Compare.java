// 
// Decompiled by Procyon v0.5.30
// 

package JAVACharter.ChartModules;

import java.text.DecimalFormat;
import JAVACharter.c.c;
import JAVACharter.c.d;
import java.awt.Color;
import java.awt.Graphics;
import JAVACharter.b.h;
import JAVACharter.c.g;
import JAVACharter.c.j;
import java.awt.Rectangle;
import JAVACharter.a.f;
import JAVACharter.StyleManage.a;
import JAVACharter.b.e;

public class Compare extends b
{
    private e[][] w;
    private int[][] y;
    private float[][] u;
    private float[][] v;
    private int[] x;
    private int[][] z;
    private int D;
    private int B;
    private int t;
    private int c;
    private int case;
    private int b;
    private String A;
    private int n;
    private a C;
    
    public Compare(final f f, final Rectangle rectangle, final j j, final g g, final JAVACharter.c.e e, final Integer n) {
        super(f, rectangle, j, g, e);
        this.D = 0;
        this.B = 0;
        this.t = 0;
        this.c = -1;
        this.case = 0;
        this.b = 0;
        this.n = 5;
        super.p = "date";
        super.m = new String[] { "open", "high", "low", "close" };
        super.null = new e[super.m.length];
        f.a(this.n);
        j.if(true);
        j.a(false);
        this.C = e.do();
    }
    
    public void calc() {
        final String[] l = super.d.l();
        super.try.if(true);
        this.w = new e[l.length - 1][super.m.length];
        for (int i = 1; i < l.length; ++i) {
            super.d.a(l[i]);
            for (int length = super.m.length, j = 0; j < length; ++j) {
                this.w[i - 1][j] = super.d.goto(super.m[j]);
            }
            super.d.i();
        }
        this.v = new float[l.length][super.k];
        this.a(0, super.k - 1, (h)super.null[3], this.v[0]);
        for (int k = 0; k < l.length - 1; ++k) {
            this.a(0, super.k - 1, (h)this.w[k][3], this.v[k + 1]);
        }
        int n;
        for (n = -1; n < ((h)super.null[0]).a() && (((h)super.null[0]).for(n) == super.long || ((h)super.null[0]).for(n) == 0.0); ++n) {}
        super.try.if(((h)super.null[3]).for(n));
        super.try.a(super.d.a(((h)super.null[1]).case()), super.d.if(((h)super.null[2]).case()));
        super.try.if(super.d.if(this.v), super.d.a(this.v));
    }
    
    public void calc_draw() {
        this.y = new int[super.d.int()][super.k * 2];
        switch (super.void.byte.a()) {
            case 3: {
                super.try.if(1);
                this.x = new int[super.k * 2];
                super.void.byte.if(this.v[0], this.x);
                break;
            }
            case 0: {
                super.try.if(0);
                this.z = new int[super.k * 4][2];
                super.void.byte.a((h)super.null[0], (h)super.null[1], (h)super.null[2], (h)super.null[3], this.z);
                break;
            }
            case 1: {
                super.try.if(1);
                this.z = new int[2][super.k * 4];
                super.void.byte.a(this.v[0], this.z, super.try.a());
                break;
            }
            case 2: {
                super.try.if(1);
                this.x = new int[super.k * 2];
                super.void.byte.if(this.v[0], this.x);
                break;
            }
            case 4: {
                super.try.if(1);
                this.z = new int[super.k * 2][2];
                super.void.byte.a(this.v[0], this.z);
                break;
            }
            case 5: {
                super.try.if(0);
                this.z = new int[super.k * 4][2];
                super.void.byte.if((h)super.null[0], (h)super.null[1], (h)super.null[2], (h)super.null[3], this.z);
                break;
            }
        }
        super.try.if(1);
        for (int i = 1; i < super.d.int(); ++i) {
            super.void.byte.if(this.v[i], this.y[i]);
        }
        super.try.if(0);
    }
    
    public void paintData(final Graphics graphics, final JAVACharter.c.b b) {
        final String[] l = super.d.l();
        final Color[] array = new Color[l.length];
        super.o.if("mainsymbol");
        super.o.if("compares");
        array[0] = super.o.a("mainsymbol", 0);
        final Color a = super.o.a("mainsymbol", 1);
        b.a(graphics, super.d.null(), array[0]);
        for (int i = 1; i < l.length; ++i) {
            array[i] = super.o.a("compares");
            b.a(graphics, super.d.long(l[i]), array[i]);
        }
        if (super.r != null) {
            for (int j = 0; j < super.r.size(); ++j) {
                b.a(graphics, (String)super.r.elementAt(j) + " (Not Found)", super.o.a("compares"));
            }
        }
        switch (super.void.byte.a()) {
            case 3: {
                final JAVACharter.c.e void1 = super.void;
                JAVACharter.c.d.for(this.x, super.k, array[0], graphics);
                break;
            }
            case 0: {
                final JAVACharter.c.e void2 = super.void;
                JAVACharter.c.d.do(this.z, super.k, array[0], graphics);
                break;
            }
            case 1: {
                final JAVACharter.c.e void3 = super.void;
                JAVACharter.c.d.if(this.z, super.k, array[0], graphics);
                break;
            }
            case 2: {
                final JAVACharter.c.e void4 = super.void;
                JAVACharter.c.d.int(this.x, super.k, array[0], graphics);
                break;
            }
            case 4: {
                final JAVACharter.c.e void5 = super.void;
                JAVACharter.c.d.a(this.z, super.k, array[0], graphics);
                break;
            }
            case 5: {
                final JAVACharter.c.e void6 = super.void;
                JAVACharter.c.d.a(this.z, super.k, array[0], a, graphics);
                break;
            }
        }
        for (int k = 0; k < super.k - 1; ++k) {
            graphics.setColor(Color.black);
            if (this.C.if()) {
                graphics.drawLine(super.if.x, super.try.do(0.0f), super.if.x + super.if.width, super.try.do(0.0f));
            }
            for (int n = 1; n < l.length; ++n) {
                graphics.setColor(array[n]);
                if (this.y[n][k * 2 + 1] != (int)super.s && this.y[n][k * 2 + 3] != (int)super.s) {
                    graphics.drawLine(this.y[n][k * 2], this.y[n][k * 2 + 1], this.y[n][k * 2 + 2], this.y[n][k * 2 + 3]);
                }
            }
        }
    }
    
    public float getCachedVal(final int n) {
        return this.v[0][n];
    }
    
    public String getName() {
        return "Compare";
    }
    
    public boolean isOverlay() {
        return false;
    }
    
    public void mouseMove(final Graphics graphics, final int n, final int n2, final c c, final boolean b) {
        graphics.setColor(Color.black);
        final DecimalFormat decimalFormat = new DecimalFormat("##############.00");
        if (n >= super.if.x && n <= super.if.x + super.if.width) {
            final int if1 = super.j.if(n);
            if (if1 < super.k && if1 >= 0) {
                final float for1 = ((h)super.null[1]).for(if1);
                this.b = super.try.do(for1);
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
                graphics.drawLine(super.j.a(if1), super.if.y, super.j.a(if1), super.if.y + super.if.height);
                graphics.setXORMode(Color.yellow);
                if (if1 != -1) {
                    graphics.fillOval(super.j.a(if1) - 4, super.try.do(this.v[0][if1]) - 4, 9, 9);
                }
                graphics.setPaintMode();
                if (this.C.if()) {
                    graphics.drawLine(super.if.x, super.try.do(this.v[0][if1]), super.if.x + super.if.width, super.try.do(this.v[0][if1]));
                }
                if (((h)super.null[0]).for(if1) != super.long && ((h)super.null[3]).for(if1) != super.long) {
                    c.a(((h)super.null[0]).for(if1), ((h)super.null[1]).for(if1), ((h)super.null[2]).for(if1), ((h)super.null[3]).for(if1), ((JAVACharter.b.f)super.i).do(if1));
                }
            }
            this.c = if1;
        }
    }
}
