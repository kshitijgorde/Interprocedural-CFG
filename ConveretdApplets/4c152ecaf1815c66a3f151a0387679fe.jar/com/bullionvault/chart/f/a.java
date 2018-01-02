// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.f;

public class a
{
    protected double a;
    protected double b;
    protected double c;
    private int e;
    protected int d;
    private double f;
    
    public void a(final double a, final double b) {
        this.a = a;
        this.b = b;
        this.c = b - a;
        this.a();
    }
    
    public void a(final int e, final int n) {
        this.e = e;
        this.d = n - e;
        this.a();
    }
    
    private void a() {
        this.f = this.d / this.c;
    }
    
    public final int a(final double n) {
        return (int)Math.round((n - this.a) * this.f + this.e);
    }
}
