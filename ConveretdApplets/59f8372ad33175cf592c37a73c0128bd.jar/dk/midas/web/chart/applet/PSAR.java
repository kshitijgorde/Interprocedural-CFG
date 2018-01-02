// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.Color;
import java.awt.Graphics;

public class PSAR extends CompositeAnalysis
{
    public static final float cf = 0.02f;
    public static final float ch = 0.02f;
    public static final float cd = 0.2f;
    private float cc;
    private float cg;
    private float ce;
    
    public PSAR(final ChartBody chartBody) {
        this(o.if(92), chartBody);
    }
    
    public PSAR(final Study study, final ChartBody chartBody) {
        super(study, chartBody);
        this.cc = 0.02f;
        this.cg = 0.2f;
        this.ce = 0.02f;
    }
    
    public static String createNameSuffix(final Object[] array) {
        return "" + Study.a(array, 0, 0.02f) + ";" + Study.a(array, 1, 0.02f) + ";" + Study.a(array, 2, 0.2f);
    }
    
    public void a(final Object[] array) {
        this.cc = Study.a(array, 0, 0.02f);
        this.ce = Study.a(array, 1, 0.02f);
        this.cg = Study.a(array, 2, 0.2f);
        this.for(createNameSuffix(array));
    }
    
    void try(final int n) {
    }
    
    public void j() {
        final DataSource al = super.b3.al();
        final bk a = al.bL.a();
        final bk a2 = al.b5.a();
        final bk a3 = al.bW.a();
        final int[] array = new int[2];
        if (!this.a(a, a2, a3, array)) {
            return;
        }
        final int n = array[0];
        final int n2 = array[1];
        if (super.b2 == null || super.b2.length < n2) {
            super.b2 = new float[n2];
        }
        if (a3.case() == 0 || a.case() == 0 || a2.case() == 0) {
            return;
        }
        float n3 = this.cc;
        a.if(n);
        float n4;
        int n5;
        if (a3.if(n) < a3.if(1)) {
            n4 = Math.max(a.if(n), a.if(n + 1));
            n5 = 0;
        }
        else {
            n4 = Math.min(a2.if(n), a2.if(n + 1));
            n5 = 1;
        }
        float n6 = n4;
        float n7 = n4;
        int n8 = 0;
        super.b2[0] = 0.0f;
        super.b2[1] = 0.0f;
        super.b2[n + 2] = n7;
        for (int i = 3; i < n2 - n; ++i) {
            final float if1 = a.if(n + i);
            final float if2 = a2.if(n + i);
            if (n5 != 0) {
                if (n7 > if2) {
                    n7 = n6;
                    n6 = if2;
                    n3 = this.cc;
                    n5 = 0;
                }
            }
            else if (n7 < if1) {
                n7 = n6;
                n6 = if1;
                n3 = this.cc;
                n5 = 1;
            }
            if (n5 != 0) {
                if (n6 < if1) {
                    if (n8 != 0) {
                        n3 = Math.min(n3 + this.ce, this.cg);
                    }
                    n8 = 1;
                    n6 = if1;
                }
                n7 += n3 * (n6 - n7);
                final float if3 = a2.if(n + i - 1);
                if (n7 > if2 && n7 > if3) {
                    n7 = Math.min(if2, if3);
                }
            }
            else {
                if (n6 > if2) {
                    if (n8 != 0) {
                        n3 = Math.min(n3 + this.ce, this.cg);
                    }
                    n8 = 1;
                    n6 = if2;
                }
                n7 += n3 * (n6 - n7);
                final float if4 = a.if(n + i - 1);
                if (n7 < if1 && n7 < if4) {
                    n7 = Math.max(if1, if4);
                }
            }
            super.b2[n + i] = n7;
        }
    }
    
    public void do(final Graphics graphics) {
        final Color color = graphics.getColor();
        this.h();
        graphics.setColor(super.bU);
        for (int i = 0; i < super.b0; ++i) {
            graphics.fillRect(super.bY[i] - 1, super.b1[i] - 1, 2, 2);
        }
        graphics.setColor(color);
    }
    
    private boolean a(final bk bk, final bk bk2, final bk bk3, final int[] array) {
        final int min = Math.min(Math.min(bk.case(), bk2.case()), bk3.case());
        int i = 0;
        int n = 0;
        int n2 = 0;
        while (i < min) {
            final float if1 = bk.if(i);
            final float if2 = bk2.if(i);
            final float if3 = bk3.if(i);
            if (if2 <= if3 && if3 <= if1) {
                if (++n == 2) {
                    break;
                }
            }
            else {
                n = 0;
                ++n2;
            }
            ++i;
        }
        int n4;
        for (int n3 = n4 = n2 + 2; n3 < min && bk2.if(n3) <= bk.if(n3); ++n3) {
            ++n4;
        }
        if (n4 > n2 + 2) {
            array[0] = n2;
            array[1] = n4;
            return true;
        }
        return false;
    }
}
