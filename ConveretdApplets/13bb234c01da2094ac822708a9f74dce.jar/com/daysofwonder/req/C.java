// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.req;

public class C extends k
{
    private String l;
    private String m;
    private String n;
    private String o;
    
    public C(final String l, final String m, final String n, final String o) {
        super(1);
        this.l = l;
        this.m = m;
        this.n = n;
        this.o = o;
        this.D();
    }
    
    public C() {
    }
    
    public C(final G g) {
        this.g = 1;
        this.a(g.t());
        this.b();
    }
    
    public void a() {
        super.a();
        this.writeUTF(this.l);
        this.writeUTF(this.m);
        this.writeBoolean(this.n != null);
        if (this.n != null) {
            this.writeUTF(this.n);
            this.writeUTF(this.o);
        }
    }
    
    public void b() {
        super.b();
        this.l = this.readUTF();
        this.m = this.readUTF();
        if (this.readBoolean()) {
            this.n = this.readUTF();
            this.o = this.readUTF();
        }
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        this.a(sb);
        return sb.toString();
    }
    
    public StringBuffer a(final StringBuffer sb) {
        sb.append("[Auth=");
        super.a(sb);
        sb.append(",name=").append(this.l).append(",pass=").append(this.m).append(",nonce=").append(this.n).append(",hmac=").append(this.o).append(']');
        return sb;
    }
    
    public int i() {
        int n = super.i() + p.a(this.l) + p.a(this.m);
        ++n;
        if (this.n != null) {
            n += p.a(this.n) + p.a(this.o);
        }
        return n;
    }
}
