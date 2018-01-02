// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.Graphics;

public class ah extends aj
{
    private static final int[] A;
    private static final int[] z;
    private static final int y = 4;
    private aj[] x;
    
    public ah(final be be) {
        this.x = new aj[ah.A.length];
        final float n = be.bC + (be.char() - be.ek.y - be.byte().bottom) * 1 / be.bI;
        for (int i = 0; i < this.x.length; ++i) {
            be.eo = be.dR + ah.A[i];
            this.x[i] = new aj(be.null().b6.elementAt(be.dR), n, be.null().b6.elementAt(be.eo), (be.bR.y < be.ek.y) ? (n + ah.z[i] * be.bJ / be.bI) : (n - ah.z[i] * be.bJ / be.bI), 7);
        }
    }
    
    private ah(final ah ah) {
        this.x = new aj[ah.A.length];
        for (int i = 0; i < this.x.length; ++i) {
            this.x[i] = ah.x[i].void();
        }
    }
    
    public aj[] e() {
        return this.x;
    }
    
    public void a(final be be) {
        for (int i = 0; i < this.x.length; ++i) {
            this.x[i].a(be);
        }
        this.a(true);
    }
    
    public void a(final Graphics graphics, final int n, final int n2) {
        for (int i = 0; i < this.x.length; ++i) {
            this.x[i].a(graphics, n, n2);
        }
    }
    
    public float a(final int n, final int n2) {
        float min = Float.MAX_VALUE;
        for (int i = 0; i < this.x.length; ++i) {
            min = Math.min(min, this.x[i].a(n, n2));
        }
        return min;
    }
    
    public aj void() {
        return new ah(this);
    }
    
    public void if(final Graphics graphics, final int n, final int n2) {
        for (int i = 0; i < this.x.length; ++i) {
            this.x[i].if(graphics, n, n2);
        }
    }
    
    public void a(final float n, final float n2) {
        for (int i = 0; i < this.x.length; ++i) {
            this.x[i].a(n, n2);
        }
    }
    
    public float if(final float n) {
        return this.x[4].if(n);
    }
    
    public String toString() {
        return "GannFanLine";
    }
    
    public double else() {
        return this.x[4].else();
    }
    
    public int c() {
        return 7;
    }
    
    public float case() {
        return this.x[4].case();
    }
    
    public float if() {
        return this.x[4].if();
    }
    
    public double try() {
        return this.x[4].try();
    }
    
    public float char() {
        return this.x[4].char();
    }
    
    public float int() {
        return this.x[4].int();
    }
    
    public int d() {
        return this.x[4].d();
    }
    
    public int byte() {
        return this.x[4].byte();
    }
    
    public double b() {
        return this.x[4].b();
    }
    
    public double null() {
        return this.x[4].null();
    }
    
    public float do() {
        return this.x[4].do();
    }
    
    public float new() {
        return this.x[4].new();
    }
    
    public int goto() {
        return this.x[4].goto();
    }
    
    public int a() {
        return this.x[4].a();
    }
    
    public boolean long() {
        return this.x[4].long();
    }
    
    public boolean for() {
        return this.x[4].for();
    }
    
    public void a(final boolean b) {
    }
    
    public void a(final double n) {
        final double n2 = n - this.else();
        for (int i = 0; i < this.x.length; ++i) {
            this.x[i].a(this.x[i].else() + n2);
        }
    }
    
    public void for(final int n) {
    }
    
    public void a(final float n) {
    }
    
    public void int(final float n) {
    }
    
    public void if(final double n) {
        final double n2 = n - this.try();
        for (int i = 0; i < this.x.length; ++i) {
            this.x[i].if(this.x[i].try() + n2);
        }
    }
    
    public void if(final boolean b) {
    }
    
    public void do(final float n) {
        final float n2 = n - this.char();
        for (int i = 0; i < this.x.length; ++i) {
            this.x[i].do(this.x[i].char() + n2);
        }
    }
    
    public void for(final float n) {
        final float n2 = n - this.int();
        for (int i = 0; i < this.x.length; ++i) {
            this.x[i].for(this.x[i].int() + n2);
        }
    }
    
    public void do(final int n) {
        final int n2 = n - this.d();
        for (int i = 0; i < this.x.length; ++i) {
            this.x[i].do(this.x[i].d() + n2);
        }
    }
    
    public void a(final int n) {
        final int n2 = n - this.byte();
        for (int i = 0; i < this.x.length; ++i) {
            this.x[i].a(this.x[i].byte() + n2);
        }
    }
    
    public void for(final double n) {
        final double n2 = n - this.b();
        for (int i = 0; i < this.x.length; ++i) {
            this.x[i].for(this.x[i].b() + n2);
        }
    }
    
    public void do(final double n) {
        final double n2 = n - this.null();
        for (int i = 0; i < this.x.length; ++i) {
            this.x[i].do(this.x[i].null() + n2);
        }
    }
    
    public void try(final float n) {
        final float n2 = n - this.do();
        for (int i = 0; i < this.x.length; ++i) {
            this.x[i].try(this.x[i].do() + n2);
        }
    }
    
    public void new(final float n) {
        final float n2 = n - this.new();
        for (int i = 0; i < this.x.length; ++i) {
            this.x[i].new(this.x[i].new() + n2);
        }
    }
    
    public void if(final int n) {
        final int n2 = n - this.goto();
        for (int i = 0; i < this.x.length; ++i) {
            this.x[i].if(this.x[i].goto() + n2);
        }
    }
    
    public void int(final int n) {
        final int n2 = n - this.a();
        for (int i = 0; i < this.x.length; ++i) {
            this.x[i].int(this.x[i].a() + n2);
        }
    }
    
    static {
        A = new int[] { 1, 1, 1, 1, 1, 2, 3, 4, 8 };
        z = new int[] { 8, 4, 3, 2, 1, 1, 1, 1, 1 };
    }
}
