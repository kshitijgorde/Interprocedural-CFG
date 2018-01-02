// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.req;

public class y extends k
{
    int l;
    boolean m;
    boolean n;
    
    public y() {
        super(700);
        this.l = -1;
        this.m = false;
        this.D();
    }
    
    public y(final int l) {
        super(700);
        this.l = l;
        this.m = false;
        this.D();
    }
    
    public y(final G g) {
        this.g = 700;
        this.a(g.t());
        this.b();
    }
    
    public void a() {
        super.a();
        this.writeInt(this.l);
        this.writeBoolean(this.m);
        this.writeBoolean(this.n);
    }
    
    public void b() {
        super.b();
        this.l = this.readInt();
        this.m = this.readBoolean();
        this.n = this.readBoolean();
    }
    
    public int d() {
        return this.l;
    }
    
    public boolean e() {
        return this.m;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        this.a(sb);
        return sb.toString();
    }
    
    public StringBuffer a(final StringBuffer sb) {
        sb.append("[Leave=");
        super.a(sb);
        sb.append(",playid=").append(this.l).append(",death=").append(this.m).append(']');
        return sb;
    }
    
    public int i() {
        return super.i() + 4 + 2;
    }
    
    public boolean f() {
        return this.n;
    }
}
