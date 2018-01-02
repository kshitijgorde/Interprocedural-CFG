// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.run;

import java.awt.Container;
import com.bullionvault.chart.a.i;
import com.bullionvault.chart.c.j;

final class b implements Runnable
{
    private final j a;
    private final ChartApp b;
    
    b(final ChartApp b, final j a) {
        this.b = b;
        this.a = a;
    }
    
    public final void run() {
        this.b.d = new i(this.b, this.b.b, this.b.e, this.b.c, this.b.f, this.a, ChartApp.a(this.b).equals("true"), ChartApp.b(this.b));
        this.b.setContentPane(this.b.d);
        this.b.setJMenuBar(this.b.d.f);
        this.b.repaint();
    }
}
