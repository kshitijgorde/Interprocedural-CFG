// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.Font;
import java.awt.Insets;

public abstract class a4 extends Analyse
{
    private static final Insets dj;
    
    public a4(final ChartManager chartManager, final Study study, final DataSource dataSource) {
        super(chartManager, study, dataSource, 1);
        super.cC = new float[] { 0.0f };
        this.if(a4.dj);
        super.cD = new Font("Arial", 0, 18);
    }
    
    protected float l() {
        return 0.0f;
    }
    
    protected void k() {
        this.else().setColor(super.bK.gP);
        this.else().drawLine(0, 0, this.b() - 1, 0);
        this.else().drawLine(0, this.char() - 1, this.b() - 1, this.char() - 1);
        this.else().setColor(super.bK.g3);
        final int else1 = this.else(0);
        final int r = this.r();
        final int n = this.char() - 5;
        for (int i = else1 + 1; i <= r; ++i) {
            final int n2 = super.cv[i];
            this.else().drawLine(n2, n, n2, super.cy[0][i]);
        }
    }
    
    protected void t() {
        this.else().setColor(super.bK.gG);
        this.else().fillRect(0, 0, this.b(), this.char());
    }
    
    static {
        dj = new Insets(5, 0, 5, 0);
    }
}
