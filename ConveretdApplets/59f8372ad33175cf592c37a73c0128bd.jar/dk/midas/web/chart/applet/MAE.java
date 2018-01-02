// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

public class MAE extends CompositeAnalysis
{
    public static final float co = 5.0f;
    public static final int cp = 3;
    float cn;
    
    public MAE(final Study study, final ChartBody chartBody) {
        super(study, chartBody);
    }
    
    public static String createNameSuffix(final Object[] array) {
        final float a = Study.a(array, 0, 5.0f);
        if (a > 1.0) {
            return "" + (int)a;
        }
        return "" + a;
    }
    
    public void a(final Object[] array) {
        if (this.f().a() == 8) {
            this.a(Study.a(array, 0, 5.0f));
        }
        else if (array[0] instanceof Integer) {
            this.case(Study.a(array, 0, 3));
        }
        this.for(createNameSuffix(array));
    }
    
    private void a(final float cn) {
        if (cn > 1.0) {
            this.cn = 2.0f / (1 + (int)cn);
        }
        else {
            this.cn = cn;
        }
        super.bW = 0;
    }
    
    private void case(final int n) {
        this.cn = 2.0f / (1 + n);
        super.bW = 0;
    }
    
    void try(final int bw) {
        super.bW = bw;
    }
    
    boolean byte(final int n) {
        return true;
    }
    
    void j() {
        final bk ag = super.b3.ag();
        super.b0 = super.b3.al().D();
        if (super.b2 == null || super.b2.length < super.b0) {
            super.b2 = new float[super.b0];
        }
        if (ag.case() == 0) {
            return;
        }
        super.b2[0] = ag.if(0);
        for (int i = 1; i < super.b0; ++i) {
            super.b2[i] = (1.0f - this.cn) * super.b2[i - 1] + this.cn * ag.if(i);
        }
    }
}
