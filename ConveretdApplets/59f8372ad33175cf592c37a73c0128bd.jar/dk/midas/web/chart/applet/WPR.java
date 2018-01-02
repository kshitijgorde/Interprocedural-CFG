// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import dk.midas.web.chart.applet.a.a;

public class WPR extends Analyse
{
    private dk.midas.web.chart.applet.a.a dl;
    private dk.midas.web.chart.applet.a.a dk;
    
    public WPR(final ChartManager chartManager, final DataSource dataSource) {
        this(chartManager, o.if(44), dataSource);
    }
    
    public WPR(final ChartManager chartManager, final Study study, final DataSource dataSource) {
        super(chartManager, study, dataSource, 1);
        super.cC = new float[] { -20.0f, -80.0f };
    }
    
    public static String createNameSuffix(final Object[] array) {
        return "" + Study.a(array);
    }
    
    public void a(final Object[] array) {
        final int a = Study.a(array);
        this.long(a);
        this.int(createNameSuffix(array));
        this.dl = new dk.midas.web.chart.applet.a.a(a);
        this.dk = new dk.midas.web.chart.applet.a.a(a);
    }
    
    public boolean char(final int n) {
        return super.cu < n;
    }
    
    public synchronized void m() {
        final int d = this.null().D();
        this.u();
        final bk a = this.null().bL.a();
        final bk a2 = this.null().b5.a();
        final bk a3 = this.null().bW.a();
        if (a.case() == 0 || a2.case() == 0 || a3.case() == 0) {
            return;
        }
        a.if(0);
        a2.if(0);
        this.dl.if();
        this.dk.if();
        for (int n = 1; n < super.cu && n < d; ++n) {
            final float if1 = a.if(n);
            final float if2 = a2.if(n);
            this.dl.a(if1);
            this.dk.a(if2);
            super.cz[0][n] = 0.0f;
        }
        for (int i = super.cu; i < d; ++i) {
            final float if3 = a.if(i);
            final float if4 = a2.if(i);
            final float if5 = a3.if(i);
            this.dl.a(if3);
            this.dk.a(if4);
            final float do1 = this.dl.do();
            super.cz[0][i] = (do1 - if5) / (do1 - this.dk.for()) * -100.0f;
        }
    }
    
    public int goto(final int n) {
        return super.cu;
    }
}
