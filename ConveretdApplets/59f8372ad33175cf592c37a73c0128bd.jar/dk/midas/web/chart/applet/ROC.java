// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

public class ROC extends Analyse
{
    static String cE;
    
    public ROC(final ChartManager chartManager, final DataSource dataSource) {
        this(chartManager, o.if(31), dataSource);
    }
    
    public ROC(final ChartManager chartManager, final Study study, final DataSource dataSource) {
        super(chartManager, study, dataSource, 1);
        super.cC = new float[] { 100.0f };
    }
    
    public static String createNameSuffix(final Object[] array) {
        return "" + Study.a(array);
    }
    
    public void a(final Object[] array) {
        this.long(Study.a(array));
        this.int(createNameSuffix(array));
    }
    
    public boolean char(final int n) {
        return super.cu < n;
    }
    
    public synchronized void m() {
        final bk y = super.parent.eS.y();
        final int d = this.null().D();
        this.u();
        final int min = Math.min(super.cu, d);
        if (y.case() == 0) {
            return;
        }
        for (int i = min; i < d; ++i) {
            super.cz[0][i] = 100.0f * y.if(i) / y.if(i - super.cu);
        }
        for (int min2 = Math.min(super.cu, d - 1), j = 0; j < min2; ++j) {
            super.cz[0][j] = super.cz[0][min2];
        }
    }
    
    public int goto(final int n) {
        return super.cu;
    }
    
    static {
        ROC.cE = new String("10;FFFFFF;000000");
    }
}
