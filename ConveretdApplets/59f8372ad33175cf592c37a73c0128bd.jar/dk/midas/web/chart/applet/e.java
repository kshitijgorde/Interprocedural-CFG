// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

public class e extends Analyse
{
    public e(final ChartManager chartManager, final DataSource dataSource) {
        this(chartManager, o.if(-340), dataSource);
    }
    
    public e(final ChartManager chartManager, final Study study, final DataSource dataSource) {
        super(chartManager, study, dataSource, 1);
    }
    
    public void a(final Object[] array) {
    }
    
    public synchronized void m() {
        final bk a = this.null().bS.a();
        final int d = this.null().D();
        this.u();
        if (a.case() == 0) {
            return;
        }
        for (int i = 0; i < d; ++i) {
            super.cz[0][i] = a.if(i);
        }
    }
}
