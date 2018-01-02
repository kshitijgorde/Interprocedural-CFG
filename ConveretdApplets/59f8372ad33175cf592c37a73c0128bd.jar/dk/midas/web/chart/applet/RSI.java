// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

public class RSI extends Analyse
{
    static String cX;
    
    public RSI(final ChartManager chartManager, final DataSource dataSource) {
        this(chartManager, o.if(21), dataSource);
    }
    
    public RSI(final ChartManager chartManager, final Study study, final DataSource dataSource) {
        super(chartManager, study, dataSource, 1);
        super.cC = new float[] { 50.0f };
    }
    
    public static String createNameSuffix(final Object[] array) {
        return "" + Study.a(array);
    }
    
    public void a(final Object[] array) {
        this.long(Study.a(array));
        this.int(createNameSuffix(array));
    }
    
    public boolean char(final int n) {
        return super.cu < n - 1;
    }
    
    public synchronized void m() {
        final bk y = super.parent.eS.y();
        final int d = this.null().D();
        this.u();
        float n = 0.0f;
        float n2 = 0.0f;
        float n3 = 0.0f;
        float n4 = 0.0f;
        final int min = Math.min(super.cu, d);
        final int min2 = Math.min(super.cu + 1, d);
        if (y.case() == 0) {
            return;
        }
        for (int i = 1; i < min2; ++i) {
            final float if1 = y.if(i);
            final float if2 = y.if(i - 1);
            if (if1 >= if2) {
                n3 = if1 - if2;
                n += n3;
                n4 = 0.0f;
            }
            if (if1 <= if2) {
                n4 = if2 - if1;
                n2 += n4;
                n3 = 0.0f;
            }
        }
        float n5 = n / super.cu;
        float n6 = n2 / super.cu;
        super.cz[0][min2 - 1] = 100.0f * n / (n + n2);
        for (int j = 0; j < min; ++j) {
            super.cz[0][j] = super.cz[0][min2 - 1];
        }
        for (int k = min2; k < d; ++k) {
            final int n7 = k - super.cu;
            final float if3 = y.if(k);
            final float if4 = y.if(k - 1);
            final float if5 = y.if(n7);
            final float if6 = y.if(n7 - 1);
            if (if5 >= if6) {
                n3 = if5 - if6;
                n -= n3;
            }
            if (if5 <= if6) {
                n4 = if6 - if5;
                n2 -= n4;
            }
            if (if3 >= if4) {
                n3 = if3 - if4;
                n += n3;
                n4 = 0.0f;
            }
            if (if3 <= if4) {
                n4 = if4 - if3;
                n2 += n4;
                n3 = 0.0f;
            }
            if (n6 * (super.cu - 1) + n4 != 0.0) {
                final float n8 = (n5 * (super.cu - 1) + n3) / (n6 * (super.cu - 1) + n4);
                super.cz[0][k] = 100.0f * n8 / (1.0f + n8);
            }
            else {
                super.cz[0][k] = 50.0f;
            }
            n5 = (n5 * (super.cu - 1) + n3) / super.cu;
            n6 = (n6 * (super.cu - 1) + n4) / super.cu;
        }
    }
    
    public int goto(final int n) {
        return super.cu + 1;
    }
    
    static {
        RSI.cX = new String("10;FFFFFF;000000");
    }
}
