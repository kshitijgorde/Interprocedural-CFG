// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.req;

public class s extends k
{
    public s() {
        super(11);
        this.D();
    }
    
    public s(final G g) {
        this.g = 11;
        this.a(g.t());
        this.b();
    }
    
    public void a() {
        super.a();
    }
    
    public void b() {
        super.b();
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        this.a(sb);
        return sb.toString();
    }
    
    public StringBuffer a(final StringBuffer sb) {
        sb.append("[Start=");
        super.a(sb);
        sb.append(']');
        return sb;
    }
    
    public int i() {
        return super.i();
    }
}
