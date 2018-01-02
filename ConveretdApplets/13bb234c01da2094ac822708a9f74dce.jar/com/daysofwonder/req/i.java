// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.req;

public class i extends k
{
    private boolean l;
    private int m;
    private int n;
    private D o;
    
    public i() {
    }
    
    public void a() {
        super.a();
        this.writeBoolean(this.l);
        this.writeInt(this.m);
        this.writeInt(this.n);
        this.o.a(this);
    }
    
    public void b() {
        super.b();
        this.l = this.readBoolean();
        this.m = this.readInt();
        this.n = this.readInt();
        this.o = D.b(this);
    }
    
    public boolean d() {
        return this.l;
    }
    
    public int e() {
        return this.m;
    }
    
    public int f() {
        return this.n;
    }
    
    public i(final G g) {
        this.g = 101;
        this.a(g.t());
        this.b();
    }
    
    public int i() {
        return super.i() + 1 + 1 + 4 + 4 + 4 + 1 + 4 + this.o.a();
    }
    
    public D g() {
        return this.o;
    }
}
