// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.Graphics;
import java.awt.Color;

public class aj
{
    public static final int byte = 1;
    public static final int void = 2;
    public static final int q = 3;
    public static final int goto = 4;
    public static final int d = 5;
    public static final int o = 6;
    public static final int g = 7;
    public static final int c = 8;
    public static Color r;
    private Color a;
    private double p;
    private double int;
    private float do;
    private float f;
    private float b;
    private float j;
    private int for;
    private int else;
    private int char;
    private int i;
    private float n;
    private float m;
    private float l;
    private float null;
    private float long;
    private float h;
    private float if;
    private boolean try;
    private int k;
    private double e;
    private double case;
    private boolean new;
    
    protected aj() {
        this.a = Color.red;
        this.new = false;
        this.a(0.0, 0.0f, 0.0, 0.0f, 1);
    }
    
    public aj(final double n, final float n2, final double n3, final float n4, final int n5) {
        this.a = Color.red;
        this.new = false;
        this.a(n, n2, n3, n4, n5);
    }
    
    public float a(final int n, final int n2) {
        float n3 = -1.0f;
        if (this.for()) {
            n3 = (float)(Math.abs(this.n * n + this.m * n2 + this.l) / Math.sqrt(this.n * this.n + this.m * this.m));
        }
        return n3;
    }
    
    private void a(final double n, final float n2, final double n3, final float n4, final int k) {
        if (n < n3) {
            this.e = n;
            this.f = n2;
            this.case = n3;
            this.j = n4;
        }
        else {
            this.e = n3;
            this.f = n4;
            this.case = n;
            this.j = n2;
        }
        this.n = 0.0f;
        this.m = 0.0f;
        this.l = 0.0f;
        this.try = true;
        this.k = k;
    }
    
    public aj void() {
        final aj aj = new aj(this.try(), this.do(), this.else(), this.new(), this.c());
        aj.a((this.new() - this.do()) / (this.int() - this.char()));
        aj.int(-this.n / this.m);
        aj.do(0.0f);
        aj.for(1199.0f);
        return aj;
    }
    
    public void a(final float n, final float n2) {
        this.try(this.case() * this.char() + n2 - this.case() * n);
        this.new(this.case() * this.int() + n2 - this.case() * n);
    }
    
    private int a(final float n, final float n2, final float n3, final float n4) {
        int n5 = (n < 0.0f) ? 1 : 0;
        if (n > n3) {
            n5 = 2;
        }
        if (n2 < 0.0f) {
            n5 |= 0x4;
        }
        if (n2 > n4) {
            n5 |= 0x8;
        }
        return n5;
    }
    
    public void a(final be be) {
        if (this.c() == 2 && be.dO.int() instanceof w) {
            this.if(false);
            return;
        }
        float do1 = this.do();
        float new1 = this.new();
        this.try();
        this.else();
        this.do(be.null().a(this.try()));
        this.for(be.null().a(this.else()));
        if (this.c() == 3) {
            this.for(this.char() + 1.0f);
        }
        if (be.bE) {
            do1 = (float)(Math.log(do1) / be.ef);
            new1 = (float)(Math.log(new1) / be.ef);
        }
        this.do(Math.round((this.char() - be.null().u()) * be.bJ));
        this.a(Math.round((this.int() - be.null().u()) * be.bJ));
        this.if(Math.round(be.char() - be.byte().bottom - (do1 - be.bC) * be.bI));
        this.int(Math.round(be.char() - be.byte().bottom - (new1 - be.bC) * be.bI));
        if (this.c() != 3 || Math.abs(do1 - new1) > 1.0E-4f) {
            this.n = this.goto() - this.a();
            this.m = this.byte() - this.d();
            this.l = this.d() * this.a() - this.byte() * this.goto();
            final float n = be.b();
            final float n2 = be.char() - be.byte().bottom;
            float null = (this.c() == 7) ? this.d() : 0.0f;
            float long1 = (-this.l - this.n * null) / this.m;
            float null2 = n;
            float long2 = (-this.l - this.n * null2) / this.m;
            final float n3 = (long2 - long1) / (null2 - null);
            int i = 0;
            do {
                int a = this.a(null, long1, n, n2);
                final int a2 = this.a(null2, long2, n, n2);
                if (a == 0 && a2 == 0) {
                    i = 1;
                    break;
                }
                if ((a & a2) != 0x0) {
                    break;
                }
                if (a == 0) {
                    this.null = null;
                    null = null2;
                    null2 = this.null;
                    this.long = long1;
                    long1 = long2;
                    long2 = this.long;
                    a = a2;
                }
                if ((a & 0x1) != 0x0) {
                    long1 += -null * n3;
                    null = 0.0f;
                }
                else if ((a & 0x2) != 0x0) {
                    long1 += (n - null) * n3;
                    null = n;
                }
                else if ((a & 0x4) != 0x0) {
                    null += -long1 / n3;
                    long1 = 0.0f;
                }
                else {
                    null += (n2 - long1) / n3;
                    long1 = n2;
                }
            } while (i == 0);
            if (i == 1) {
                this.if(true);
                if (null > this.d()) {
                    this.do(Math.round(null));
                    this.if(Math.round(long1));
                }
                this.null = null2;
                if (this.null > be.b()) {
                    this.null = be.b() - 1;
                }
                this.long = long2;
            }
            else {
                this.if(false);
            }
        }
        else {
            this.n = 0.0f;
            this.m = 1.0f;
            this.l = -this.a();
            this.long = this.a();
            if (this.long < 0.0f || this.long > be.char() - be.byte().bottom) {
                this.if(false);
            }
            else {
                this.if(true);
            }
            this.null = be.b();
        }
        this.a(true);
    }
    
    public float if(final float n) {
        return (this.do() - this.new()) / (this.char() - this.int()) * (n - this.char()) + this.do();
    }
    
    public void a(final Graphics graphics, final int n, final int n2) {
        if (this.for()) {
            final Color color = graphics.getColor();
            graphics.setColor(aj.r);
            if (this.c() != 3) {
                graphics.drawLine(n + this.d(), n2 + this.goto(), n + Math.round(this.null) - 1, n2 + Math.round(this.long));
            }
            else {
                graphics.drawLine(n, n2 + Math.round(this.long), n + Math.round(this.null) - 1, n2 + Math.round(this.long));
            }
            graphics.setColor(color);
        }
    }
    
    public void if(final Graphics graphics, final int n, final int n2) {
        if (this.for()) {
            final Color color = graphics.getColor();
            graphics.setColor(this.a);
            if (this.c() != 3) {
                graphics.drawLine(n + this.d(), n2 + this.goto(), n + Math.round(this.null), n2 + Math.round(this.long));
            }
            else {
                graphics.drawLine(n, n2 + Math.round(this.long), n + Math.round(this.null), n2 + Math.round(this.long));
            }
            graphics.setColor(color);
        }
    }
    
    public String toString() {
        return "TLine " + this.char() + " " + this.do() + " " + this.int() + " " + this.new() + " " + this.try() + " " + this.else();
    }
    
    public void for(final double p) {
        this.p = p;
    }
    
    public double b() {
        return this.p;
    }
    
    public void do(final double int1) {
        this.int = int1;
    }
    
    public double null() {
        return this.int;
    }
    
    public void do(final float do1) {
        this.do = do1;
    }
    
    public float char() {
        return this.do;
    }
    
    public void try(final float f) {
        this.f = f;
    }
    
    public float do() {
        return this.f;
    }
    
    public void for(final float b) {
        this.b = b;
    }
    
    public float int() {
        return this.b;
    }
    
    public void new(final float j) {
        this.j = j;
    }
    
    public float new() {
        return this.j;
    }
    
    public void do(final int for1) {
        this.for = for1;
    }
    
    public int d() {
        return this.for;
    }
    
    public void if(final int else1) {
        this.else = else1;
    }
    
    public int goto() {
        return this.else;
    }
    
    public void a(final int char1) {
        this.char = char1;
    }
    
    public int byte() {
        return this.char;
    }
    
    public void int(final int i) {
        this.i = i;
    }
    
    public int a() {
        return this.i;
    }
    
    public void a(final float h) {
        this.h = h;
    }
    
    public float case() {
        return this.h;
    }
    
    public void int(final float if1) {
        this.if = if1;
    }
    
    public float if() {
        return this.if;
    }
    
    public void if(final boolean try1) {
        this.try = try1;
    }
    
    public boolean for() {
        return this.try;
    }
    
    public void for(final int k) {
        this.k = k;
    }
    
    public int c() {
        return this.k;
    }
    
    public void if(final double e) {
        this.e = e;
    }
    
    public double try() {
        return this.e;
    }
    
    public void a(final double case1) {
        this.case = case1;
    }
    
    public double else() {
        return this.case;
    }
    
    public void a(final boolean new1) {
        this.new = new1;
    }
    
    public boolean long() {
        return this.new;
    }
    
    static {
        aj.r = Color.black;
    }
}
