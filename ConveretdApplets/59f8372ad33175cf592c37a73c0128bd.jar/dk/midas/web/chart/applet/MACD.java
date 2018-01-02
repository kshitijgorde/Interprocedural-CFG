// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.Color;

public class MACD extends Analyse
{
    public static final float dd = 26.0f;
    public static final float c4 = 12.0f;
    public static final float dc = 9.0f;
    public static final int da = 26;
    public static final int dg = 12;
    public static final int db = 9;
    static String c7;
    boolean c6;
    float c9;
    float c8;
    float c5;
    int dh;
    int df;
    int de;
    
    public MACD(final ChartManager chartManager, final int n, final DataSource dataSource) {
        this(chartManager, o.if(n), dataSource);
    }
    
    public MACD(final ChartManager chartManager, final Study study, final DataSource dataSource) {
        super(chartManager, study, dataSource, 2);
        super.cC = new float[] { 0.0f };
    }
    
    public static String createNameSuffix(final Object[] array) {
        final float a = Study.a(array, 0, 26.0f);
        final float a2 = Study.a(array, 1, 12.0f);
        final float a3 = Study.a(array, 2, 9.0f);
        final String s = "";
        String s2;
        if (a > 1.0) {
            s2 = s + (int)a + ";";
        }
        else {
            s2 = s + a + ";";
        }
        String s3;
        if (a2 > 1.0) {
            s3 = s2 + (int)a2 + ";";
        }
        else {
            s3 = s2 + a2 + ";";
        }
        String s4;
        if (a3 > 1.0) {
            s4 = s3 + (int)a3;
        }
        else {
            s4 = s3 + a3;
        }
        return s4;
    }
    
    public void a(final Object[] array) {
        if (this.f().a() == 5) {
            this.a(Study.a(array, 0, 26.0f), Study.a(array, 1, 12.0f), Study.a(array, 2, 9.0f));
        }
        else if (array[0] instanceof Integer) {
            this.a(Study.a(array, 0, 26), Study.a(array, 1, 12), Study.a(array, 2, 9));
        }
        this.int(createNameSuffix(array));
    }
    
    protected void long(final int n) {
    }
    
    private void a(final float c9, final float c10, final float c11) {
        if (c9 > 1.0) {
            this.c9 = 2.0f / ((int)c9 + 1);
        }
        else {
            this.c9 = c9;
        }
        if (c10 > 1.0) {
            this.c8 = 2.0f / ((int)c10 + 1);
        }
        else {
            this.c8 = c10;
        }
        if (c11 > 1.0) {
            this.c5 = 2.0f / ((int)c11 + 1);
        }
        else {
            this.c5 = c11;
        }
    }
    
    private void a(final int dh, final int df, final int de) {
        this.c6 = true;
        this.dh = dh;
        this.df = df;
        this.de = de;
        this.c9 = 2.0f / (dh + 1);
        this.c8 = 2.0f / (df + 1);
        this.c5 = 2.0f / (de + 1);
    }
    
    public synchronized void m() {
        final bk y = super.parent.eS.y();
        final int d = this.null().D();
        this.u();
        if (y.case() == 0) {
            return;
        }
        float if1 = y.if(0);
        float if2 = y.if(0);
        super.cz[0][0] = 0.0f;
        super.cz[1][0] = 0.0f;
        for (int i = 1; i < d; ++i) {
            final float if3 = y.if(i);
            final float n = if1;
            final float n2 = if2;
            if1 = (1.0f - this.c9) * n + this.c9 * if3;
            if2 = (1.0f - this.c8) * n2 + this.c8 * if3;
            super.cz[0][i] = if2 - if1;
            super.cz[1][i] = (1.0f - this.c5) * super.cz[1][i - 1] + this.c5 * super.cz[0][i];
        }
    }
    
    protected Color void(final int n) {
        return (n == 0) ? super.bK.g3 : super.bK.gy;
    }
    
    static {
        MACD.c7 = new String("10;FFFFFF;000000");
    }
}
