// 
// Decompiled by Procyon v0.5.30
// 

package com.pokw.shooter.weapon;

abstract class n implements A
{
    protected String b;
    protected int a;
    
    protected n() {
        this.b = null;
        this.a = 0;
    }
    
    protected n(final String b, final int a) {
        this.b = b;
        this.a = a;
    }
    
    public int b() {
        return this.a;
    }
    
    public String a() {
        return this.b;
    }
}
