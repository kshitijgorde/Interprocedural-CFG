// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public abstract class am extends ChartBody
{
    int[] fN;
    int[] fI;
    bk fK;
    bk fH;
    bk fM;
    bk fJ;
    bk fL;
    
    protected am(final String s, final be be, final DataSource dataSource) {
        super(s, be, dataSource);
        this.fK = dataSource.bW;
        this.fH = dataSource.bY;
        this.fM = dataSource.bL;
        this.fJ = dataSource.b5;
        this.fL = new bk(this.fM.try(), 128);
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
        return this.x(n);
    }
    
    private int x(final int n) {
        return this.fN[n];
    }
    
    private float y(final int n) {
        return this.fK.if(n);
    }
    
    private int int(int n, int n2) {
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
        for (int n8 = n4; n8 >= 0 && (if1 = ChartBody.if(n, n2, super.fp[n8], this.fN[n8])) <= n5; --n8) {
            if (if1 < n7) {
                n7 = if1;
                n6 = n8;
            }
        }
        int if2;
        for (int n9 = this.al().l() - this.al().u(), n10 = n4 + 1; n10 <= n9 && (if2 = ChartBody.if(n, n2, super.fp[n10], this.fN[n10])) <= n5; ++n10) {
            if (if2 < n7) {
                n7 = if2;
                n6 = n10;
            }
        }
        return (n6 == -1) ? -1 : (n6 + this.al().u());
    }
    
    protected void aj() {
        this.fH = this.al().bY;
        final int fs = this.al().l() - this.al().u() + 1;
        if (super.fp == null || super.fp.length != this.fK.case()) {
            final int case1 = this.fK.case();
            super.fp = new int[case1];
            this.fI = new int[case1];
            this.fN = new int[case1];
        }
        if (super.fl) {
            super.fe = super.fh.bJ;
            super.e7 = super.fh.bC;
            super.fa = super.fh.bF;
        }
        else {
            super.fe = this.am();
            super.e7 = this.ab();
            super.fa = this.ah();
        }
        if (super.e8) {
            super.e7 = (float)(Math.log(super.e7) / ChartBody.fo);
            super.fa = (float)(Math.log(super.fa) / ChartBody.fo);
        }
        super.fc = (super.fq - super.fi - super.fr) / (super.fa - super.e7);
        for (int i = this.al().u(); i <= this.al().l(); ++i) {
            super.fp[i - this.al().u()] = Math.round((i - this.al().u()) * super.fe);
            this.fI[i - this.al().u()] = Integer.MIN_VALUE;
            float if1 = this.fK.if(i);
            if (if1 == Float.MIN_VALUE) {
                this.fN[i - this.al().u()] = Integer.MIN_VALUE;
            }
            else {
                if (super.e8) {
                    if1 = (float)(Math.log(if1) / ChartBody.fo);
                }
                this.fN[i - this.al().u()] = Math.round(super.fq - super.fi - (if1 - super.e7) * super.fc);
            }
            if (this.fH != null) {
                float if2 = this.fH.if(i);
                if (if2 == Float.MIN_VALUE) {
                    this.fI[i - this.al().u()] = Integer.MIN_VALUE;
                }
                else {
                    if (super.e8) {
                        if2 = (float)(Math.log(if2) / ChartBody.fo);
                    }
                    this.fI[i - this.al().u()] = Math.round(super.fq - super.fi - (if2 - super.e7) * super.fc);
                }
            }
        }
        super.fs = fs;
    }
    
    public void try(final DataSource dataSource) {
        super.try(dataSource);
        this.fK = dataSource.bW;
        this.fH = dataSource.bY;
        this.fM = dataSource.bL;
        this.fJ = dataSource.b5;
    }
    
    public float ai() {
        return this.fK.char();
    }
    
    public bk ag() {
        return this.fK.a();
    }
    
    public void for(final Graphics graphics, final int n) {
        final Color color = graphics.getColor();
        graphics.setColor(Color.red);
        graphics.fillOval(super.e9 + this.q(n) - 2, super.fm + this.x(n) - 2, 4, 4);
        graphics.setColor(color);
    }
    
    public float ab() {
        float n = this.al().a(this.fK);
        if (this.fH != null) {
            n = Math.min(n, this.al().a(this.fH));
        }
        return n;
    }
    
    public float ah() {
        float n = this.al().if(this.fK);
        if (this.fH != null) {
            n = Math.max(n, this.al().if(this.fH));
        }
        return n;
    }
    
    protected int af() {
        int n = this.if(this.fN);
        if (n == Integer.MIN_VALUE) {
            n = this.if(this.fI);
        }
        return n;
    }
}
