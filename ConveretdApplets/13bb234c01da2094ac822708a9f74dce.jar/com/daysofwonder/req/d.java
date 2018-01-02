// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.req;

public class d extends k
{
    private int l;
    
    public d() {
        super(500);
        this.l = 0;
        this.D();
    }
    
    public d(final G g) {
        this.g = 500;
        this.a(g.t());
        this.b();
    }
    
    public void a() {
        super.a();
        this.writeInt(this.l);
    }
    
    public void b() {
        super.b();
        this.l = this.readInt();
    }
    
    public int d() {
        return this.l;
    }
    
    public int i() {
        return super.i() + 4;
    }
}
