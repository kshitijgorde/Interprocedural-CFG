// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import dk.midas.web.chart.applet.a.a;

public class CCI extends Analyse
{
    private dk.midas.web.chart.applet.a.a cY;
    
    public CCI(final ChartManager chartManager, final DataSource dataSource) {
        this(chartManager, o.if(46), dataSource);
    }
    
    public CCI(final ChartManager chartManager, final Study study, final DataSource dataSource) {
        super(chartManager, study, dataSource, 1);
        super.cC = new float[] { -100.0f, 100.0f };
    }
    
    public void m() {
        this.u();
        final DataSource null = this.null();
        final bk a = null.bW.a();
        final bk a2 = null.bL.a();
        final bk a3 = null.b5.a();
        final int d = null.D();
        final int min = Math.min(d, super.cu);
        if (a.case() == 0 || a2.case() == 0 || a3.case() == 0) {
            return;
        }
        this.cY.if();
        for (int i = 0; i < min; ++i) {
            this.cY.a((a.if(i) + a2.if(i) + a3.if(i)) / 3.0f);
        }
        for (int j = super.cu; j < d; ++j) {
            final float n = (a.if(j) + a2.if(j) + a3.if(j)) / 3.0f;
            final float a4 = this.cY.a(n);
            float n2 = 0.0f;
            for (int k = 0; k < super.cu; ++k) {
                n2 += Math.abs(a4 - this.cY.a(k));
            }
            super.cz[0][j] = (float)((n - a4) / (0.015 * (n2 / super.cu)));
        }
    }
    
    public static String createNameSuffix(final Object[] array) {
        return "" + Study.a(array);
    }
    
    public void a(final Object[] array) {
        final int a = Study.a(array);
        this.long(a);
        this.int(createNameSuffix(array));
        this.cY = new dk.midas.web.chart.applet.a.a(a);
    }
    
    public boolean char(final int n) {
        return super.cu <= n;
    }
    
    public int goto(final int n) {
        return super.cu;
    }
}
