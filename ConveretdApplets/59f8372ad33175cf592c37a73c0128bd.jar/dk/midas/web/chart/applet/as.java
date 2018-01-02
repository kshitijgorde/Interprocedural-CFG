// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public abstract class as extends ChartBody
{
    static final int fQ = 4;
    int[] fT;
    int[] fW;
    int[] fV;
    int[] fS;
    bk fZ;
    bk fY;
    bk fX;
    bk fU;
    int f0;
    private static boolean fR;
    
    protected as(final String s, final be be, final DataSource dataSource) {
        super(s, be, dataSource);
        this.fZ = this.al().b5;
        this.fY = this.al().bL;
        this.fX = this.al().bX;
        this.fU = this.al().bW;
        this.fZ = dataSource.b5;
        this.fY = dataSource.bL;
        this.fX = dataSource.bX;
        this.fU = dataSource.bW;
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
        if (n2 == 1) {
            return this.fW[n];
        }
        if (Math.abs(this.fS[n] - point.y) < Math.abs(this.fV[n] - point.y)) {
            as.fR = true;
            return this.fS[n];
        }
        as.fR = false;
        return this.fV[n];
    }
    
    public float if(final int n, final int n2) {
        if (n2 == 1) {
            return this.fU.if(n);
        }
        if (as.fR) {
            return this.fY.if(n);
        }
        return this.fZ.if(n);
    }
    
    public float p(final int n) {
        return this.fY.if(n);
    }
    
    public float s(final int n) {
        return this.fZ.if(n);
    }
    
    public void try(final DataSource dataSource) {
        super.try(dataSource);
        this.fZ = dataSource.b5;
        this.fY = dataSource.bL;
        this.fX = dataSource.bX;
        this.fU = dataSource.bW;
    }
    
    private int new(int n, int n2) {
        int i = 0;
        int n3 = this.al().l() - this.al().u();
        int n4 = (i + n3) / 2;
        final int n5 = Math.min(this.f0, 4) + 2;
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
        for (int n8 = n4; n8 >= 0 && (if1 = ChartBody.if(n, n2, super.fp[n8], n2)) <= n5; --n8) {
            if (if1 < n7 && this.fS[n8] <= n2 && n2 <= this.fV[n8]) {
                n7 = if1;
                n6 = n8;
            }
        }
        int if2;
        for (int n9 = this.al().l() - this.al().u(), n10 = n4 + 1; n10 <= n9 && (if2 = ChartBody.if(n, n2, super.fp[n10], n2)) <= n5; ++n10) {
            if (if2 < n7 && this.fS[n10] <= n2 && n2 <= this.fV[n10]) {
                n7 = if2;
                n6 = n10;
            }
        }
        return (n6 == -1) ? -1 : (n6 + this.al().u());
    }
    
    protected void aj() {
        final int fs = this.al().l() - this.al().u() + 1;
        if (super.fp == null || super.fp.length != this.fZ.case()) {
            final int case1 = this.fZ.case();
            super.fp = new int[case1];
            this.fT = new int[case1];
            this.fW = new int[case1];
            this.fV = new int[case1];
            this.fS = new int[case1];
        }
        super.fe = this.am();
        super.e7 = this.ab();
        super.fa = this.ah();
        if (super.e7 == super.fa) {
            super.fa *= 1.05;
            super.e7 *= 0.95;
        }
        if (super.e8) {
            super.e7 = (float)(Math.log(super.e7) / ChartBody.fo);
            super.fa = (float)(Math.log(super.fa) / ChartBody.fo);
        }
        super.fc = (super.fq - super.fi - super.fr) / (super.fa - super.e7);
        for (int i = this.al().u(); i <= this.al().l(); ++i) {
            super.fp[i - this.al().u()] = Math.round((i - this.al().u()) * super.fe);
            float if1 = this.fX.if(i);
            if (if1 == Float.MIN_VALUE) {
                this.fT[i - this.al().u()] = Integer.MIN_VALUE;
            }
            else {
                if (super.e8) {
                    if1 = (float)(Math.log(if1) / ChartBody.fo);
                }
                this.fT[i - this.al().u()] = Math.round(super.fq - super.fi - (if1 - super.e7) * super.fc);
            }
            float if2 = this.fY.if(i);
            if (if2 == Float.MIN_VALUE) {
                this.fS[i - this.al().u()] = Integer.MIN_VALUE;
            }
            else {
                if (super.e8) {
                    if2 = (float)(Math.log(if2) / ChartBody.fo);
                }
                this.fS[i - this.al().u()] = Math.round(super.fq - super.fi - (if2 - super.e7) * super.fc);
            }
            float if3 = this.fZ.if(i);
            if (if3 == Float.MIN_VALUE) {
                this.fV[i - this.al().u()] = Integer.MIN_VALUE;
            }
            else {
                if (super.e8) {
                    if3 = (float)(Math.log(if3) / ChartBody.fo);
                }
                this.fV[i - this.al().u()] = Math.round(super.fq - super.fi - (if3 - super.e7) * super.fc);
            }
            float if4 = this.fU.if(i);
            if (if4 == Float.MIN_VALUE) {
                this.fW[i - this.al().u()] = Integer.MIN_VALUE;
            }
            else {
                if (super.e8) {
                    if4 = (float)(Math.log(if4) / ChartBody.fo);
                }
                this.fW[i - this.al().u()] = Math.round(super.fq - super.fi - (if4 - super.e7) * super.fc);
            }
        }
        super.fs = fs;
    }
    
    public float ai() {
        return this.fU.char();
    }
    
    public bk ag() {
        return this.fU.a();
    }
    
    public void for(final Graphics graphics, final int n) {
        final Color color = graphics.getColor();
        graphics.setColor(Color.red);
        graphics.drawLine(super.e9 + super.fp[n], super.fm + this.fS[n], super.e9 + super.fp[n], super.fm + this.fV[n]);
        graphics.setColor(color);
    }
    
    public float ab() {
        final float[] array = { this.al().a(this.fZ), this.al().a(this.fY), this.al().a(this.fX), this.al().a(this.fU) };
        float min = array[0];
        for (int i = 1; i < 4; ++i) {
            min = Math.min(min, array[i]);
        }
        return min;
    }
    
    public float ah() {
        final float[] array = { this.al().if(this.fZ), this.al().if(this.fY), this.al().if(this.fX), this.al().if(this.fU) };
        float max = array[0];
        for (int i = 1; i < 4; ++i) {
            max = Math.max(max, array[i]);
        }
        return max;
    }
    
    protected int af() {
        return this.if(this.fW);
    }
    
    static {
        as.fR = true;
    }
}
