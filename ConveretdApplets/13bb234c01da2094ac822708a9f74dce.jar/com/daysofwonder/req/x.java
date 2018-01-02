// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.req;

public class x extends k
{
    private float l;
    private String m;
    private String n;
    
    public x() {
        super(10);
        this.D();
    }
    
    public x(final G g) {
        this.g = 10;
        this.a(g.t());
        this.b();
    }
    
    public void a() {
        super.a();
        this.writeFloat(this.l);
        this.writeBoolean(this.m != null);
        if (this.m != null) {
            this.writeUTF(this.m);
        }
        this.writeBoolean(this.n != null);
        if (this.n != null) {
            this.writeUTF(this.n);
        }
    }
    
    public void b() {
        super.b();
        this.l = this.readFloat();
        if (this.readBoolean()) {
            this.m = this.readUTF();
        }
        if (this.readBoolean()) {
            this.n = this.readUTF();
        }
    }
    
    public float d() {
        return this.l;
    }
    
    public String e() {
        return this.m;
    }
    
    public String f() {
        return this.n;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        this.a(sb);
        return sb.toString();
    }
    
    public int i() {
        int i = super.i();
        i += 4;
        ++i;
        if (this.m != null) {
            i += p.a(this.m);
        }
        ++i;
        if (this.n != null) {
            i += p.a(this.n);
        }
        return i;
    }
    
    public StringBuffer a(final StringBuffer sb) {
        sb.append("[find_info=");
        super.a(sb);
        sb.append(this.l).append(']');
        return sb;
    }
}
