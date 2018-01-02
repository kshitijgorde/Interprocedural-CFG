// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public abstract class w extends ChartBody
{
    int[] fC;
    bk fz;
    bk fx;
    bk fB;
    bk fy;
    bk fA;
    
    protected w(final String s, final be be, final DataSource dataSource) {
        super(s, be, dataSource);
        this.fz = dataSource.bW;
        this.fx = new bk(this.fz.try(), 128);
        this.fB = dataSource.bL;
        this.fy = dataSource.b5;
        this.fA = new bk(this.fB.try(), 128);
        if (super.fl) {
            this.fz = dataSource.bW.do();
        }
        if (super.fj) {
            this.fz = dataSource.bW.int();
        }
    }
    
    public void if(final boolean fl, final boolean fj) {
        final DataSource al = this.al();
        super.fl = fl;
        super.fj = fj;
        if (fl) {
            this.fz = al.bW.do();
        }
        if (fj) {
            this.fz = al.bW.int();
        }
    }
    
    public int u(int n) {
        int n2 = 0;
        int n3 = this.al().l() - this.al().u();
        int n4 = (n2 + n3) / 2;
        n -= super.e9;
        while (n2 + 1 < n3) {
            if (super.fp[n4] < n) {
                n2 = n4;
            }
            else {
                if (super.fp[n4] <= n) {
                    break;
                }
                n3 = n4;
            }
            n4 = (n2 + n3) / 2;
        }
        if (n2 + 1 < n3) {
            return n4;
        }
        if (Math.abs(n - super.fp[n2]) < Math.abs(n - super.fp[n3])) {
            return n2;
        }
        return n3;
    }
    
    public int q(final int n) {
        return super.fp[n];
    }
    
    public int a(final int n, final Point point, final int n2) {
        return this.v(n);
    }
    
    int v(final int n) {
        return this.fC[n];
    }
    
    float w(final int n) {
        return this.fz.if(n);
    }
    
    public float if(final int n, final int n2) {
        return this.fz.if(n);
    }
    
    int for(int n, int n2) {
        int i = 0;
        int n3 = this.al().l() - this.al().u();
        int n4 = (i + n3) / 2;
        final int n5 = 25;
        if (n2 == Integer.MIN_VALUE) {
            return -1;
        }
        n -= super.e9;
        n2 -= super.fm;
        while (i <= n3) {
            if (super.fp[n4] < n) {
                i = n4 + 1;
            }
            else {
                if (super.fp[n4] <= n) {
                    break;
                }
                n3 = n4 - 1;
            }
            n4 = (i + n3) / 2;
        }
        int n6 = -1;
        int n7 = 1000000;
        int if1;
        for (int n8 = n4; n8 >= 0 && (if1 = ChartBody.if(n, n2, super.fp[n8], this.fC[n8])) <= n5; --n8) {
            if (if1 < n7) {
                n7 = if1;
                n6 = n8;
            }
        }
        int if2;
        for (int n9 = this.al().l() - this.al().u(), n10 = n4 + 1; n10 <= n9 && (if2 = ChartBody.if(n, n2, super.fp[n10], this.fC[n10])) <= n5; ++n10) {
            if (if2 < n7) {
                n7 = if2;
                n6 = n10;
            }
        }
        return (n6 == -1) ? -1 : (n6 + this.al().u());
    }
    
    protected void aj() {
        final DataSource x = this.al().x();
        final int u = x.u();
        final int l = x.l();
        final int fs = l - u + 1;
        if (super.fp == null || super.fp.length != this.fz.case()) {
            final int case1 = this.fz.case();
            super.fp = new int[case1];
            this.fC = new int[case1];
        }
        if (super.fl || super.fj) {
            super.fe = super.fh.bJ;
            super.e7 = super.fh.bC;
            super.fa = super.fh.bF;
        }
        else {
            super.fe = this.am();
            super.e7 = this.ab();
            super.fa = this.ah();
        }
        if (super.e7 == super.fa) {
            super.fa *= 1.05;
            super.e7 *= 0.95;
        }
        if (super.e8) {
            super.e7 = (float)(Math.log(super.e7) / ChartBody.fo);
            super.fa = (float)(Math.log(super.fa) / ChartBody.fo);
        }
        super.fc = (super.fq - super.fi - super.fr) / (super.fa - super.e7);
        for (int i = u; i <= l; ++i) {
            super.fp[i - u] = Math.round((i - u) * super.fe);
            float if1 = this.fz.if(i);
            if (if1 == Float.MIN_VALUE) {
                this.fC[i - u] = Integer.MIN_VALUE;
            }
            else {
                if (super.e8) {
                    if1 = (float)(Math.log(if1) / ChartBody.fo);
                }
                this.fC[i - u] = Math.round(super.fq - super.fi - (if1 - super.e7) * super.fc);
            }
        }
        super.fs = fs;
    }
    
    public void try(final DataSource dataSource) {
        super.try(dataSource);
        this.fz = dataSource.bW;
        if (super.fl) {
            this.fz = dataSource.bW.do();
        }
        if (super.fj) {
            this.fz = dataSource.bW.int();
        }
        this.fB = dataSource.bL;
        this.fy = dataSource.b5;
    }
    
    public float ai() {
        return this.fz.char();
    }
    
    public bk ag() {
        return this.fz.a();
    }
    
    public void for(final Graphics graphics, final int n) {
        final Color color = graphics.getColor();
        graphics.setColor(Color.red);
        graphics.fillOval(super.e9 + this.q(n) - 2, super.fm + this.v(n) - 2, 4, 4);
        graphics.setColor(color);
    }
    
    public float ab() {
        return this.al().a(this.fz);
    }
    
    public float ah() {
        return this.al().if(this.fz);
    }
    
    protected int af() {
        return this.if(this.fC);
    }
}
