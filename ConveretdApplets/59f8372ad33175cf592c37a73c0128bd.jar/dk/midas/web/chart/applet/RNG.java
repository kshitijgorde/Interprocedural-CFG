// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.Graphics;
import java.util.Stack;
import java.awt.Color;

public class RNG extends CompositeAnalysis
{
    public static final float b8 = 1.0f;
    public static final int b5 = 20;
    public static final boolean b4 = true;
    public static boolean b7;
    float[] ca;
    int[] b6;
    Color bU;
    Color cb;
    int bW;
    float b9;
    
    public RNG(final ChartBody chartBody) {
        this(o.if(93), chartBody);
    }
    
    public RNG(final Study study, final ChartBody chartBody) {
        super(study, chartBody);
        super.bX = new float[super.b2.length];
    }
    
    public static String createNameSuffix(final Object[] array) {
        return "" + Study.a(array, 0, 1.0f) + ";" + Study.a(array, 1, 20);
    }
    
    public void a(final Object[] array) {
        this.b9 = Study.a(array, 0, 1.0f);
        this.try(Study.a(array, 1, 20));
        RNG.b7 = Study.a(array, 2, true);
        this.b9 = (RNG.b7 ? (this.b9 * 0.01f) : this.b9);
        this.for(createNameSuffix(array));
    }
    
    boolean a(final Stack stack) {
        this.bU = stack.pop();
        this.cb = this.bU;
        return true;
    }
    
    void if(final Stack stack) {
        stack.push(this.bU);
    }
    
    public void try(final int bw) {
        this.bW = bw;
    }
    
    public boolean byte(final int n) {
        return this.bW < n;
    }
    
    public void j() {
        final bk a = super.b3.al().bW.a();
        float n = 0.0f;
        final int case1 = a.case();
        if (case1 == 0) {
            return;
        }
        if (super.b2 == null || super.b2.length < case1) {
            super.b2 = new float[case1];
            super.bX = new float[case1];
        }
        float n2 = 0.0f;
        int min;
        int i;
        for (min = Math.min(this.bW, case1), i = 0; i < min; ++i) {
            n += a.if(i);
        }
        super.b2[i - 1] = n / this.bW;
        for (int j = 0; j < min; ++j) {
            super.b2[j] = super.b2[this.bW - 1];
            final float n3 = a.if(j) - super.b2[this.bW - 1];
            n2 += n3 * n3;
        }
        for (int k = min; k < case1; ++k) {
            n = n - a.if(k - this.bW) + a.if(k);
            super.b2[k] = n / this.bW;
            if (!RNG.b7) {
                float n4 = 0.0f;
                for (int l = 0; l < this.bW; ++l) {
                    final float n5 = a.if(k - l) - super.b2[k];
                    n4 += n5 * n5;
                }
                super.bX[k] = (float)Math.sqrt(n4 / (this.bW - 1));
            }
        }
        for (int n6 = 0; n6 < case1; ++n6) {
            final float n7 = super.b2[n6];
            if (!RNG.b7) {
                super.b2[n6] = n7 + this.b9 * super.bX[n6];
                super.bX[n6] = n7 - this.b9 * super.bX[n6];
            }
            else {
                super.b2[n6] = n7 * (1.0f + this.b9);
                super.bX[n6] = n7 * (1.0f - this.b9);
            }
        }
    }
    
    void h() {
        final DataSource al = super.b3.al();
        final int b0 = al.l() - al.u() + 1;
        if (super.bY == null || super.bY.length < super.b2.length) {
            final int length = super.b2.length;
            super.bY = new int[length];
            super.b1 = new int[length];
            this.b6 = new int[length];
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
            this.b6[i - al.u()] = Math.round(super.b3.fq - super.b3.fi - (n2 - super.b3.e7) * super.b3.fc);
        }
        super.b0 = b0;
    }
    
    void do(final Graphics graphics) {
        graphics.getColor();
        this.h();
        graphics.setColor(this.bU);
        final int bw = this.bW;
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
        graphics.setColor(this.cb);
        int n6 = super.bY[n];
        int n7 = this.b6[n];
        for (int j = n + 1; j < super.b0; ++j) {
            final int n8 = super.bY[j];
            final int n9 = this.b6[j];
            graphics.drawLine(n6, n7, n8, n9);
            n6 = n8;
            n7 = n9;
        }
    }
    
    static {
        RNG.b7 = false;
    }
}
