// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.b;

import java.text.NumberFormat;
import com.bullionvault.chart.resources.Resources;
import java.math.BigDecimal;
import com.bullionvault.chart.c.h;
import java.awt.FontMetrics;
import java.util.Vector;
import com.bullionvault.chart.f.a;

public final class f extends a
{
    public Vector e;
    private FontMetrics h;
    private int i;
    private int j;
    public double[] f;
    public int g;
    
    public f(final double n, final double n2, final int n3, final int n4, final FontMetrics h) {
        this.i = 3;
        this.j = 0;
        this.f = new double[] { 0.001, 0.002, 0.005, 0.01, 0.02, 0.05, 0.1, 0.2, 0.25, 0.5, 1.0, 2.0, 2.5, 5.0, 10.0, 20.0, 25.0, 50.0, 100.0, 200.0, 250.0, 500.0, 1000.0, 2000.0, 2500.0, 5000.0, 10000.0, 20000.0, 25000.0, 50000.0, 100000.0, 200000.0, 250000.0, 500000.0, 1000000.0 };
        this.g = 2;
        com.bullionvault.chart.c.h.f("Initialising float Axis");
        this.h = h;
        this.a(n, n2);
        this.a(n3, n4);
        this.a();
    }
    
    public final void a(final double n, final double n2) {
        com.bullionvault.chart.c.h.f("floatAxis setRange");
        super.a(n, n2);
        this.a();
    }
    
    public final void a(final int n, final int n2) {
        super.a(n, n2);
        this.a();
    }
    
    public final void a() {
        this.e = new Vector();
        int n = 0;
        double n2 = this.f[0];
        final int n3 = this.d / (this.h.getHeight() + this.i);
        final double n4 = this.c / n3;
        com.bullionvault.chart.c.h.g("scale_range=" + this.d);
        com.bullionvault.chart.c.h.g("maxMarks=" + n3 + ", minIntervalValue=" + n4);
        for (int n5 = (int)(this.c / n2); n5 > n3 && n < this.f.length - 1; ++n, n2 = this.f[n], n5 = (int)(this.c / n2)) {
            com.bullionvault.chart.c.h.g("IntervalID=" + n + ", interval=" + n2 + ", drawMarks=" + n5);
        }
        com.bullionvault.chart.c.h.g("Set Vertical scale to [" + n2 + "]");
        BigDecimal add = new BigDecimal(this.a - this.a % n2 + n2);
        final BigDecimal bigDecimal = new BigDecimal(n2);
        final BigDecimal bigDecimal2 = new BigDecimal(this.b);
        final NumberFormat instance;
        (instance = NumberFormat.getInstance(Resources.a())).setMaximumFractionDigits(this.g);
        instance.setMinimumFractionDigits(this.g);
        final NumberFormat instance2;
        (instance2 = NumberFormat.getInstance(Resources.a())).setParseIntegerOnly(true);
        this.j = 0;
        while (add.compareTo(bigDecimal2) == -1) {
            add.setScale(this.g, 4);
            final e e;
            (e = new e()).a = add.doubleValue();
            e.b = this.a(e.a);
            e.c = ((e.a > 999.0) ? instance2.format(e.a) : instance.format(e.a));
            e.d = this.h.stringWidth(e.c);
            com.bullionvault.chart.c.h.g(e.toString());
            add = add.add(bigDecimal);
            this.e.addElement(e);
            if (e.d > this.j) {
                this.j = e.d;
            }
        }
    }
}
