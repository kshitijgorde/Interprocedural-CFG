// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.Color;

public class PKS extends Analyse
{
    public static final int du = 5;
    public static final int dp = 5;
    public static final int dv = 5;
    static String dq;
    float[] dr;
    float[] dn;
    int ds;
    int dt;
    int dm;
    
    public PKS(final ChartManager chartManager, final DataSource dataSource) {
        this(chartManager, o.if(71), dataSource);
    }
    
    public PKS(final ChartManager chartManager, final Study study, final DataSource dataSource) {
        super(chartManager, study, dataSource, 2);
        this.dr = new float[1200];
        this.dn = new float[1200];
        super.cC = new float[] { 100.0f };
    }
    
    public static String createNameSuffix(final Object[] array) {
        return "" + Study.a(array, 0, 5) + ";" + Study.a(array, 1, 5) + ";" + Study.a(array, 2, 5);
    }
    
    public void a(final Object[] array) {
        this.ds = Study.a(array, 0, 5);
        this.dt = Study.a(array, 1, 5);
        this.dm = Study.a(array, 2, 5);
        super.cu = this.ds + this.dt - 1;
        this.int(createNameSuffix(array));
    }
    
    public boolean char(final int n) {
        return super.cu < n;
    }
    
    public synchronized void m() {
        final bk y = super.parent.eS.y();
        final bk a = this.null().bL.a();
        final bk a2 = this.null().b5.a();
        final int d = this.null().D();
        this.u();
        if (this.dr == null || this.dr.length < d) {
            this.dr = new float[d];
            this.dn = new float[d];
        }
        if (y.case() == 0 || a.case() == 0 || a2.case() == 0) {
            return;
        }
        final int min = Math.min(this.ds - 1, d);
        final int min2 = Math.min(this.ds + this.dt - 1, d);
        for (int i = min; i < d; ++i) {
            this.dn[i] = 0.0f;
            this.dr[i] = 1.0E12f;
            for (int j = 0; j < this.ds; ++j) {
                final float if1 = a.if(i - j);
                final float if2 = a2.if(i - j);
                if (this.dr[i] > if2) {
                    this.dr[i] = if2;
                }
                if (this.dn[i] < if1) {
                    this.dn[i] = if1;
                }
            }
        }
        float n = 0.0f;
        int k;
        for (k = min; k < min2; ++k) {
            final float n2 = y.if(k) - this.dr[k];
            final float n3 = this.dn[k] - this.dr[k];
            n += (float)((n3 == 0.0f) ? 0.5 : (n2 / n3));
        }
        super.cz[0][k - 1] = n * 100.0f / this.dt;
        for (int l = min2; l < d; ++l) {
            final float n4 = y.if(l - this.dt) - this.dr[l - this.dt];
            final float n5 = this.dn[l - this.dt] - this.dr[l - this.dt];
            final float n6 = (float)(n - ((n5 == 0.0f) ? 0.5 : (n4 / n5)));
            final float n7 = y.if(l) - this.dr[l];
            final float n8 = this.dn[l] - this.dr[l];
            n = (float)(n6 + ((n8 == 0.0f) ? 0.5 : (n7 / n8)));
            super.cz[0][l] = n * 100.0f / this.dt;
        }
        final int n9 = this.ds + this.dt - 2;
        float n10 = 0.0f;
        int n11;
        for (n11 = 0; n11 < this.dm; ++n11) {
            n10 += super.cz[0][n9 + n11];
        }
        super.cz[1][n9 + n11 - 1] = n10 / this.dm;
        for (int n12 = n9 + n11; n12 < d; ++n12) {
            n10 = n10 - super.cz[0][n12 - this.dm] + super.cz[0][n12];
            super.cz[1][n12] = n10 / this.dm;
        }
    }
    
    public int goto(final int n) {
        switch (n) {
            case 0: {
                return this.ds + this.dt - 2;
            }
            case 1: {
                return this.ds + this.dt + this.dm - 3;
            }
            default: {
                return super.cu;
            }
        }
    }
    
    protected Color void(final int n) {
        return (n == 0) ? super.bK.g3 : super.bK.gy;
    }
    
    static {
        PKS.dq = new String("10;FFFFFF;000000");
    }
}
