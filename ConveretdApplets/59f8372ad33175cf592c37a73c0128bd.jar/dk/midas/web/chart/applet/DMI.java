// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.Font;
import java.awt.Color;

public class DMI extends Analyse
{
    public static final int dz = 14;
    static Color dw;
    static Color dy;
    static Color dx;
    static Color dB;
    static String dA;
    
    public DMI(final ChartManager chartManager, final DataSource dataSource) {
        this(chartManager, o.if(42), dataSource);
    }
    
    public DMI(final ChartManager chartManager, final Study study, final DataSource dataSource) {
        super(chartManager, study, dataSource, 3);
        super.cC = new float[] { 50.0f };
        super.cD = new Font("Arial", 0, 14);
    }
    
    public static String createNameSuffix(final Object[] array) {
        return "" + Study.a(array, 0, 14);
    }
    
    public void a(final Object[] array) {
        this.long(Study.a(array, 0, 14));
        this.int(createNameSuffix(array));
    }
    
    public boolean char(final int n) {
        return super.cu < n - 1;
    }
    
    public synchronized void m() {
        final int d = this.null().D();
        this.u();
        final bk a = this.null().bW.a();
        final bk a2 = this.null().bL.a();
        final bk a3 = this.null().b5.a();
        if (a.case() == 0 || a2.case() == 0 || a3.case() == 0) {
            return;
        }
        float n = 0.0f;
        float n2 = 0.0f;
        float n3 = 0.0f;
        int i;
        for (i = 1; i < Math.min(d, super.cu); ++i) {
            final float if1 = a2.if(i);
            final float if2 = a3.if(i);
            final float if3 = a.if(i - 1);
            final float if4 = a2.if(i - 1);
            final float if5 = a3.if(i - 1);
            n += Math.max(Math.max(Math.abs(if1 - if2), Math.abs(if1 - if3)), Math.abs(if2 - if3));
            float max = Math.max(0.0f, if1 - if4);
            float abs = Math.abs(Math.min(0.0f, if2 - if5));
            if (max < abs) {
                max = 0.0f;
            }
            else if (max > abs) {
                abs = 0.0f;
            }
            else {
                max = 0.0f;
                abs = 0.0f;
            }
            n2 += max;
            n3 += abs;
            super.cz[0][i] = 0.0f;
            super.cz[1][i] = 0.0f;
            super.cz[2][i] = 0.0f;
        }
        float n4 = n;
        float n5 = n2;
        float n6 = n3;
        float n7 = 0.0f;
        while (i < Math.min(d, super.cu * 2)) {
            final float if6 = a2.if(i);
            final float if7 = a3.if(i);
            final float if8 = a.if(i - 1);
            final float if9 = a2.if(i - 1);
            final float if10 = a3.if(i - 1);
            final float max2 = Math.max(Math.max(Math.abs(if6 - if7), Math.abs(if6 - if8)), Math.abs(if7 - if8));
            float max3 = Math.max(0.0f, if6 - if9);
            float abs2 = Math.abs(Math.min(0.0f, if7 - if10));
            if (max3 < abs2) {
                max3 = 0.0f;
            }
            else if (max3 > abs2) {
                abs2 = 0.0f;
            }
            else {
                max3 = 0.0f;
                abs2 = 0.0f;
            }
            final float n8 = n4 - n4 / super.cu + max2;
            final float n9 = n5 - n5 / super.cu + max3;
            final float n10 = n6 - n6 / super.cu + abs2;
            final float n11 = n9 / n8 * 100.0f;
            final float n12 = n10 / n8 * 100.0f;
            n7 += Math.abs(n11 - n12) / (n11 + n12) * 100.0f;
            super.cz[0][i] = 0.0f;
            super.cz[1][i] = n11;
            super.cz[2][i] = n12;
            n4 = n8;
            n5 = n9;
            n6 = n10;
            ++i;
        }
        float n13 = n7 / super.cu;
        while (i < d) {
            final float if11 = a2.if(i);
            final float if12 = a3.if(i);
            final float if13 = a.if(i - 1);
            final float if14 = a2.if(i - 1);
            final float if15 = a3.if(i - 1);
            final float max4 = Math.max(Math.max(Math.abs(if11 - if12), Math.abs(if11 - if13)), Math.abs(if12 - if13));
            float max5 = Math.max(0.0f, if11 - if14);
            float abs3 = Math.abs(Math.min(0.0f, if12 - if15));
            if (max5 < abs3) {
                max5 = 0.0f;
            }
            else if (max5 > abs3) {
                abs3 = 0.0f;
            }
            else {
                max5 = 0.0f;
                abs3 = 0.0f;
            }
            final float n14 = n4 - n4 / super.cu + max4;
            final float n15 = n5 - n5 / super.cu + max5;
            final float n16 = n6 - n6 / super.cu + abs3;
            final float n17 = n15 / n14 * 100.0f;
            final float n18 = n16 / n14 * 100.0f;
            final float n19 = (n13 * (super.cu - 1) + Math.abs(n17 - n18) / (n17 + n18) * 100.0f) / super.cu;
            super.cz[0][i] = n19;
            super.cz[1][i] = n17;
            super.cz[2][i] = n18;
            n4 = n14;
            n5 = n15;
            n6 = n16;
            n13 = n19;
            ++i;
        }
    }
    
    public int e() {
        return (this.f().a() == 19) ? 1 : 3;
    }
    
    public int goto(final int n) {
        switch (n) {
            case 1:
            case 2: {
                return super.cu;
            }
            case 0: {
                return 2 * super.cu;
            }
            default: {
                return 0;
            }
        }
    }
    
    protected Color void(final int n) {
        switch (n) {
            case 1: {
                return DMI.dw;
            }
            case 2: {
                return DMI.dy;
            }
            case 0: {
                return super.bK.g3;
            }
            default: {
                return super.bK.g3;
            }
        }
    }
    
    protected String d(final int n) {
        switch (n) {
            case 1: {
                return "+DI";
            }
            case 2: {
                return "-DI";
            }
            case 0: {
                return "ADX";
            }
            default: {
                return null;
            }
        }
    }
    
    static {
        DMI.dw = Color.green;
        DMI.dy = Color.red;
        DMI.dx = Color.blue;
        DMI.dB = Color.black;
        DMI.dA = new String("10;FFFFFF;000000");
    }
}
