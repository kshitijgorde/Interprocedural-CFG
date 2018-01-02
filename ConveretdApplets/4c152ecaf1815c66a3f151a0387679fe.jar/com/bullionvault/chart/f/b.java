// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.f;

import com.bullionvault.chart.c.h;
import java.util.Date;

public class b
{
    protected Date a;
    protected Date b;
    public int c;
    public int d;
    protected int e;
    private long h;
    private long i;
    private long j;
    protected Date f;
    protected Date g;
    private float k;
    
    public void a(final Date a, final Date b) {
        this.a = a;
        this.b = b;
        this.h = a.getTime();
        this.i = b.getTime();
        this.j = this.i - this.h;
        this.a();
        com.bullionvault.chart.c.h.g("Set date scale range: start=" + a + " end=" + b);
        com.bullionvault.chart.c.h.g(" => t_start=" + this.h + " t_end=" + this.i + " t_range=" + this.j);
    }
    
    public void a(final int c, final int d) {
        this.c = c;
        this.d = d;
        this.e = this.d - this.c;
        this.a();
        com.bullionvault.chart.c.h.g("Set date scale factors: scale_start=" + c + " scale_end=" + d);
        com.bullionvault.chart.c.h.g(" => scale_range=" + this.e);
    }
    
    private void a() {
        this.k = this.e / this.j;
    }
    
    public final int a(final Date date) {
        return this.a(date.getTime());
    }
    
    public final int a(final long n) {
        return Math.round((n - this.h) * this.k + this.c);
    }
}
