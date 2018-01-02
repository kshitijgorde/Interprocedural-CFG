// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.a;

public abstract class o implements g
{
    protected String e;
    protected j f;
    private String a;
    
    public o(final String e) {
        this.e = e;
    }
    
    public void a(final j f) {
        this.f = f;
    }
    
    public synchronized String f() {
        if (this.a == null) {
            final StringBuffer sb = new StringBuffer();
            sb.append(this.e).append('(').append(this.f.a(this)).append(')');
            this.a = sb.toString();
        }
        return this.a;
    }
    
    public abstract boolean equals(final Object p0);
    
    public synchronized int g() {
        return this.f.a(this);
    }
    
    public synchronized void c(final int n) {
        this.f.a(this, n);
    }
}
