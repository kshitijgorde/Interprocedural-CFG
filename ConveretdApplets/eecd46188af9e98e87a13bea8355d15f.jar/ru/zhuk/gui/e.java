// 
// Decompiled by Procyon v0.5.30
// 

package ru.zhuk.gui;

class e
{
    public double b;
    public double a;
    
    public e() {
    }
    
    public e(final double b, final double a) {
        this.b = b;
        this.a = a;
    }
    
    public double c() {
        return this.b;
    }
    
    public double a() {
        return this.a;
    }
    
    public e a(final e e) {
        return new e(this.b + e.b, this.a + e.a);
    }
    
    public double b(final e e) {
        return this.b * e.b + this.a * e.a;
    }
    
    public e a(final double n) {
        return new e(this.b * n, this.a * n);
    }
    
    public double b() {
        return Math.sqrt(this.b(this));
    }
}
