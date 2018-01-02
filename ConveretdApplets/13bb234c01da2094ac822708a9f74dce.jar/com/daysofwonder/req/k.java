// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.req;

import com.daysofwonder.a.j;

public class k extends G
{
    protected int a;
    protected boolean b;
    protected j c;
    
    public k(final int n) {
        super(n, 4096);
        this.a = -1;
        this.a = -1;
    }
    
    public k() {
        this.a = -1;
    }
    
    public synchronized void a(final j c) {
        this.c = c;
    }
    
    public synchronized j n() {
        return this.c;
    }
    
    public void a() {
        super.a();
        this.writeInt(this.a);
    }
    
    public void b() {
        super.b();
        this.a = this.readInt();
    }
    
    public void c() {
    }
    
    public int i() {
        return super.i() + 4;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        this.a(sb);
        return sb.toString();
    }
    
    public StringBuffer a(final StringBuffer sb) {
        sb.append("[GameRequest=");
        super.a(sb);
        sb.append(",table=").append(this.a).append("]");
        return sb;
    }
    
    public void o() {
        this.b = true;
    }
    
    public boolean p() {
        return this.b;
    }
}
