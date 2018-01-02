// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.req;

import com.daysofwonder.a.i;

public class g extends k
{
    private int l;
    
    public g(final i i) {
        super(104);
        this.l = i.w();
        this.D();
    }
    
    public g(final G g) {
        this.g = 104;
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
        sb.append("[kick=");
        super.a(sb);
        sb.append("]");
        return sb;
    }
    
    public int i() {
        int i = super.i();
        i += 4;
        return i;
    }
}
