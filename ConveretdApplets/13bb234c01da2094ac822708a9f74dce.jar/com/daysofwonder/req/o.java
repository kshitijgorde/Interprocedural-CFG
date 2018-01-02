// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.req;

public class o extends k
{
    private long l;
    
    public o(final long l) {
        super(777);
        this.l = l;
        this.D();
    }
    
    public o() {
    }
    
    public void a() {
        super.a();
        this.writeLong(this.l);
    }
    
    public void b() {
        super.b();
        this.l = this.readLong();
    }
    
    public long d() {
        return this.l;
    }
    
    public o(final G g) {
        this.g = 777;
        this.a(g.t());
        this.b();
    }
    
    public int i() {
        return super.i() + 8;
    }
}
