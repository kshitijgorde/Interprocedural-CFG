// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.req;

public class r extends k
{
    int l;
    boolean m;
    
    public r() {
        super(4);
        this.l = -1;
        this.m = false;
        this.D();
    }
    
    public r(final G g) {
        this.g = 4;
        this.a(g.t());
        this.b();
    }
    
    public void a() {
        super.a();
        this.writeInt(this.l);
        this.writeBoolean(this.m);
    }
    
    public void b() {
        super.b();
        this.l = this.readInt();
        this.m = this.readBoolean();
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        this.a(sb);
        return sb.toString();
    }
    
    public StringBuffer a(final StringBuffer sb) {
        sb.append("[Disconnect=");
        super.a(sb);
        sb.append(",playid=").append(this.l).append(",death=").append(this.m).append(']');
        return sb;
    }
    
    public int i() {
        int i = super.i();
        i += 4;
        return ++i;
    }
}
