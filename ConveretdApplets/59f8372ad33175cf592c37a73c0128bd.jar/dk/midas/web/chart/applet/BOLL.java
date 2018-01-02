// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.Graphics;
import java.util.Stack;
import java.awt.Color;

public class BOLL extends CompositeAnalysis
{
    public static final int cm = 20;
    public static final float ck = 2.0f;
    int[] cj;
    Color ci;
    float cl;
    
    public BOLL(final ChartBody chartBody) {
        this(o.if(91), chartBody);
    }
    
    public BOLL(final Study study, final ChartBody chartBody) {
        super(study, chartBody);
        super.bX = new float[1200];
    }
    
    public static String createNameSuffix(final Object[] array) {
        return "" + Study.a(array, 0, 20) + ";" + Study.a(array, 1, 2.0f);
    }
    
    public void a(final Object[] array) {
        this.try(Study.a(array, 0, 20));
        this.cl = Study.a(array, 1, 2.0f);
        this.for(createNameSuffix(array));
    }
    
    boolean a(final Stack stack) {
        super.bU = stack.pop();
        this.ci = super.bU;
        return true;
    }
    
    void if(final Stack stack) {
        stack.push(super.bU);
    }
    
    void try(final int bw) {
        super.bW = bw;
    }
    
    boolean byte(final int n) {
        return super.bW < n;
    }
    
    void j() {
        final bk ag = super.b3.ag();
        float n = 0.0f;
        super.b0 = super.b3.al().D();
        if (super.b2 == null || super.b2.length < super.b0) {
            super.b2 = new float[super.b0];
            super.bX = new float[super.b0];
        }
        final int min = Math.min(super.bW, super.b0);
        if (ag.case() == 0) {
            return;
        }
        int i;
        for (i = 0; i < min; ++i) {
            n += ag.if(i);
        }
        super.b2[i] = n / super.bW;
        float n2 = 0.0f;
        int j;
        for (j = 0; j < min; ++j) {
            final float if1 = ag.if(j);
            super.b2[j] = super.b2[super.bW];
            n2 += (if1 - super.b2[super.bW]) * (if1 - super.b2[super.bW]);
        }
        super.bX[j - 1] = (float)Math.sqrt(n2 / super.bW);
        while (j < super.b0) {
            n = n - ag.if(j - super.bW) + ag.if(j);
            super.b2[j] = n / super.bW;
            float n3 = 0.0f;
            for (int k = 0; k < super.bW; ++k) {
                final float if2 = ag.if(j - k);
                n3 += (if2 - super.b2[j]) * (if2 - super.b2[j]);
            }
            super.bX[j] = (float)Math.sqrt(n3 / super.bW);
            ++j;
        }
        for (int l = 0; l < super.b0; ++l) {
            final float n4 = super.b2[l];
            super.b2[l] = n4 + this.cl * super.bX[l];
            super.bX[l] = n4 - this.cl * super.bX[l];
        }
    }
    
    void h() {
        final DataSource al = super.b3.al();
        final int new1 = al.y().new();
        if (super.bY == null || super.bY.length < super.b2.length) {
            final int length = super.b2.length;
            super.bY = new int[length];
            super.b1 = new int[length];
            this.cj = new int[length];
        }
        for (int i = al.u(); i <= al.l(); ++i) {
            super.bY[i - al.u()] = Math.round((i - al.u()) * super.b3.fe);
            float n = super.b2[i];
            if (super.b3.e8) {
                n = (float)(Math.log(n) / ChartBody.fo);
            }
            super.b1[i - al.u()] = Math.round(super.b3.fq - super.b3.fi - (n - super.b3.e7) * super.b3.fc);
            float n2 = super.bX[i];
            if (super.b3.e8) {
                n2 = (float)(Math.log(n2) / ChartBody.fo);
            }
            this.cj[i - al.u()] = Math.round(super.b3.fq - super.b3.fi - (n2 - super.b3.e7) * super.b3.fc);
        }
        super.b0 = new1;
    }
    
    void do(final Graphics graphics) {
        final Color color = graphics.getColor();
        this.h();
        graphics.setColor(super.bU);
        final int bw = super.bW;
        int n;
        if (super.b3.al().u() >= bw) {
            n = 0;
        }
        else {
            n = bw - super.b3.al().u();
        }
        int n2 = super.bY[n];
        int n3 = super.b1[n];
        for (int i = n + 1; i < super.b0; ++i) {
            final int n4 = super.bY[i];
            final int n5 = super.b1[i];
            graphics.drawLine(n2, n3, n4, n5);
            n2 = n4;
            n3 = n5;
        }
        graphics.setColor(this.ci);
        int n6 = super.bY[n];
        int n7 = this.cj[n];
        for (int j = n + 1; j < super.b0; ++j) {
            final int n8 = super.bY[j];
            final int n9 = this.cj[j];
            graphics.drawLine(n6, n7, n8, n9);
            n6 = n8;
            n7 = n9;
        }
        graphics.setColor(color);
    }
}
