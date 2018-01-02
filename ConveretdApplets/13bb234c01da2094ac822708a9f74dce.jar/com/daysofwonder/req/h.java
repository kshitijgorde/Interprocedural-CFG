// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.req;

public class h extends k
{
    private int l;
    
    public h(final G g) {
        this.g = 100;
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
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        this.a(sb);
        return sb.toString();
    }
    
    public StringBuffer a(final StringBuffer sb) {
        sb.append("[YourTurn=");
        super.a(sb);
        sb.append(",nid=").append(this.l).append(']');
        return sb;
    }
    
    public int d() {
        return this.l;
    }
    
    public int i() {
        int i = super.i();
        i += 4;
        return i;
    }
}
