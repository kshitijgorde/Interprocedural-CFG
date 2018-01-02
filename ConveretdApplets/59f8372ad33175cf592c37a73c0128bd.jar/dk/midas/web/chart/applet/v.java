// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

public class v extends a4
{
    v(final ChartManager chartManager, final DataSource dataSource) {
        super(chartManager, o.if(-330), dataSource);
    }
    
    public void a(final Object[] array) {
    }
    
    public synchronized void m() {
        final bk a = this.null().b4.a();
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
