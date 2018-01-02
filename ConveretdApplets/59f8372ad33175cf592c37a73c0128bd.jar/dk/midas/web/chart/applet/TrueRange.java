// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.Color;

public class TrueRange extends Analyse
{
    public TrueRange(final ChartManager chartManager, final DataSource dataSource) {
        this(chartManager, o.if(45), dataSource);
    }
    
    public TrueRange(final ChartManager chartManager, final Study study, final DataSource dataSource) {
        super(chartManager, study, dataSource, 2);
        super.cC = null;
    }
    
    public static String createNameSuffix(final Object[] array) {
        return "" + Study.a(array);
    }
    
    public void a(final Object[] array) {
        this.long(Study.a(array));
        this.int(createNameSuffix(array));
    }
    
    protected void long(final int cu) {
        super.cu = cu;
    }
    
    public boolean char(final int n) {
        return 1 < n;
    }
    
    public void m() {
        this.u();
        final DataSource null = this.null();
        final bk a = null.bL.a();
        final bk a2 = null.b5.a();
        final bk a3 = null.bW.a();
        final int d = null.D();
        if (a3.case() == 0 || a.case() == 0 || a2.case() == 0) {
            return;
        }
        super.cz[0][0] = 0.0f;
        for (int i = 1; i < d; ++i) {
            final float if1 = a.if(i);
            final float if2 = a2.if(i);
            final float if3 = a3.if(i - 1);
            super.cz[0][i] = Math.max(if1 - if2, Math.max(Math.abs(if1 - if3), Math.abs(if2 - if3)));
        }
        float n = 0.0f;
        final int min = Math.min(super.cu, d);
        final int n2 = Math.min(super.cu, d) - 1;
        for (int j = 0; j < min; ++j) {
            n += super.cz[0][j];
        }
        super.cz[1][n2] = n / super.cu;
        for (int k = 0; k < min; ++k) {
            super.cz[1][k] = super.cz[1][n2];
        }
        for (int l = min; l < d; ++l) {
            n = n - super.cz[0][l - super.cu] + super.cz[0][l];
            super.cz[1][l] = n / super.cu;
        }
    }
    
    protected String d(final int n) {
        return (n == 0) ? "TR" : "ATR";
    }
    
    public int goto(final int n) {
        return (n == 0) ? 1 : super.cu;
    }
    
    protected Color void(final int n) {
        return (n == 0) ? super.bK.g3 : super.bK.gy;
    }
}
