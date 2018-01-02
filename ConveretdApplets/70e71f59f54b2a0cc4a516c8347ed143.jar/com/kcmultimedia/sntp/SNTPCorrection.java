// 
// Decompiled by Procyon v0.5.30
// 

package com.kcmultimedia.sntp;

public class SNTPCorrection
{
    private double a;
    private double b;
    
    protected void a(final double a) {
        this.a = a;
    }
    
    public double getDelay() {
        return this.a;
    }
    
    protected void b(final double b) {
        this.b = b;
    }
    
    public double getCorrection() {
        return this.b;
    }
}
