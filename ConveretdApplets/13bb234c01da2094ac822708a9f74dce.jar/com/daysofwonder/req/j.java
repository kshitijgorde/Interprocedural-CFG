// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.req;

public class j extends k
{
    private String l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private boolean t;
    private boolean u;
    
    public j(final G g) {
        this.g = 103;
        this.a(g.t());
        this.b();
    }
    
    public void a() {
        super.a();
        this.writeUTF(this.l);
        this.writeInt(this.m);
        this.writeInt(this.n);
        this.writeInt(this.o);
        this.writeInt(this.p);
        this.writeInt(this.q);
        this.writeInt(this.r);
        this.writeInt(this.s);
        this.writeBoolean(this.t);
        this.writeBoolean(this.u);
    }
    
    public void b() {
        super.b();
        this.l = this.readUTF();
        this.m = this.readInt();
        this.n = this.readInt();
        this.o = this.readInt();
        this.p = this.readInt();
        this.q = this.readInt();
        this.r = this.readInt();
        this.s = this.readInt();
        this.t = this.readBoolean();
        this.u = this.readBoolean();
    }
    
    public String d() {
        return this.l;
    }
    
    public int e() {
        return this.m;
    }
    
    public int f() {
        return this.n;
    }
    
    public int g() {
        return this.p;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        this.a(sb);
        return sb.toString();
    }
    
    public StringBuffer a(final StringBuffer sb) {
        sb.append("[NewPlayer=");
        super.a(sb);
        sb.append(",name=").append(this.l).append(",pid=").append(this.m).append(']');
        return sb;
    }
    
    public int i() {
        int n = super.i() + com.daysofwonder.req.p.a(this.l);
        n += 4;
        n += 4;
        n += 4;
        n += 4;
        n += 12;
        ++n;
        return ++n;
    }
    
    public int h() {
        return this.q;
    }
    
    public int j() {
        return this.r;
    }
    
    public int k() {
        return this.s;
    }
    
    public boolean l() {
        return this.t;
    }
    
    public boolean m() {
        return this.u;
    }
}
