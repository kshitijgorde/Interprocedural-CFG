// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

public class MovAvg extends CompositeAnalysis
{
    public MovAvg(final ChartBody chartBody) {
        this(o.if(41), chartBody);
    }
    
    public MovAvg(final Study study, final ChartBody chartBody) {
        super(study, chartBody);
    }
    
    public static String createNameSuffix(final Object[] array) {
        return "" + Study.a(array);
    }
    
    public void a(final Object[] array) {
        this.try(Study.a(array));
        this.for(createNameSuffix(array));
    }
    
    void try(final int bw) {
        super.bW = bw;
    }
    
    boolean byte(final int n) {
        return super.bW < n;
    }
    
    void j() {
        final bk ag = super.b3.ag();
        float n = 0.0f;
        super.b0 = super.b3.al().D();
        if (super.b2 == null || super.b2.length < super.b0) {
            super.b2 = new float[super.b0];
        }
        if (ag.case() == 0) {
            return;
        }
        int min;
        int i;
        for (min = Math.min(super.bW, super.b0), i = 0; i < min; ++i) {
            n += ag.if(i);
        }
        super.b2[i - 1] = n / super.bW;
        for (int j = 0; j < min; ++j) {
            super.b2[j] = super.b2[super.bW - 1];
        }
        for (int k = min; k < super.b0; ++k) {
            n = n - ag.if(k - super.bW) + ag.if(k);
            super.b2[k] = n / super.bW;
        }
    }
}
