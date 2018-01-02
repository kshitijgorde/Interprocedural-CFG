// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

public class OSC extends Analyse
{
    public static final int c3 = 28;
    public static final int cZ = 14;
    static String c2;
    int c1;
    int c0;
    
    public OSC(final ChartManager chartManager, final DataSource dataSource) {
        this(chartManager, o.if(61), dataSource);
    }
    
    public OSC(final ChartManager chartManager, final Study study, final DataSource dataSource) {
        super(chartManager, study, dataSource, 1);
        super.cC = new float[] { 100.0f };
    }
    
    public static String createNameSuffix(final Object[] array) {
        return "" + Study.a(array, 0, 28) + ";" + Study.a(array, 1, 14);
    }
    
    public void a(final Object[] array) {
        this.c1 = Study.a(array, 0, 28);
        this.c0 = Study.a(array, 1, 14);
        this.int(createNameSuffix(array));
    }
    
    public boolean char(final int n) {
        return this.c1 < n;
    }
    
    public synchronized void m() {
        final bk y = super.parent.eS.y();
        float n = 0.0f;
        float n2 = 0.0f;
        final int d = this.null().D();
        this.u();
        if (y.case() == 0) {
            return;
        }
        int min;
        int i;
        for (min = Math.min(this.c1, d), i = 0; i < min; ++i) {
            if (i < this.c0) {
                n2 = (n = n2 + y.if(i));
            }
            else {
                n += y.if(i);
                n2 = n2 - y.if(i - this.c0) + y.if(i);
            }
        }
        super.cz[0][i - 1] = (n2 / this.c0 - n / this.c1) * 100.0f * this.c0 / n2;
        for (int j = 0; j < min; ++j) {
            super.cz[0][j] = super.cz[0][this.c1 - 1];
        }
        for (int k = min; k < d; ++k) {
            n = n - y.if(k - this.c1) + y.if(k);
            n2 = n2 - y.if(k - this.c0) + y.if(k);
            super.cz[0][k] = (n2 / this.c0 - n / this.c1) * 100.0f * this.c0 / n2;
        }
    }
    
    public int goto(final int n) {
        return this.c1;
    }
    
    static {
        OSC.c2 = new String("10;FFFFFF;000000");
    }
}
