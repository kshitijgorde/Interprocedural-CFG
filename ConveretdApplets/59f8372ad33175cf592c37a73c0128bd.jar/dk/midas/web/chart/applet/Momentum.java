// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

public class Momentum extends Analyse
{
    static String di;
    
    public Momentum(final ChartManager chartManager, final DataSource dataSource) {
        this(chartManager, o.if(11), dataSource);
    }
    
    public Momentum(final ChartManager chartManager, final Study study, final DataSource dataSource) {
        super(chartManager, study, dataSource, 1);
        super.cC = new float[] { 0.0f };
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
        if (y.case() == 0) {
            return;
        }
        int i;
        int n;
        for (n = (i = Math.min(super.cu, d)); i < d; ++i) {
            super.cz[0][i] = y.if(i) - y.if(i - super.cu);
        }
        for (int j = 0; j < n; ++j) {
            super.cz[0][j] = 0.0f;
        }
    }
    
    public int goto(final int n) {
        return super.cu;
    }
    
    static {
        Momentum.di = new String("10;FFFFFF;000000");
    }
}
