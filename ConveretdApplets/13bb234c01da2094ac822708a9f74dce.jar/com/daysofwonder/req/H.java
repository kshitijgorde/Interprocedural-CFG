// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.req;

public class H extends k
{
    private int l;
    private boolean m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private String s;
    private String t;
    private String u;
    
    public H(final int l) {
        super(9);
        this.l = l;
        this.D();
    }
    
    public H(final int l, final boolean b) {
        super(9);
        this.l = l;
        this.m = true;
        this.D();
    }
    
    public H(final G g) {
        this.g = 9;
        this.a(g.t());
        this.b();
    }
    
    public void a() {
        super.a();
        this.writeInt(this.l);
        this.writeBoolean(this.m);
        this.writeBoolean(this.s != null);
        if (this.s != null) {
            this.writeInt(this.p);
            this.writeInt(this.o);
            this.writeInt(this.q);
            this.writeInt(this.n);
            this.writeUTF(this.s);
            this.writeBoolean(this.t != null);
            if (this.t != null) {
                this.writeUTF(this.t);
                this.writeUTF(this.u);
            }
        }
        this.writeInt(this.r);
    }
    
    public void b() {
        super.b();
        this.l = this.readInt();
        this.m = this.readBoolean();
        if (this.readBoolean()) {
            this.p = this.readInt();
            this.o = this.readInt();
            this.q = this.readInt();
            this.n = this.readInt();
            this.s = this.readUTF();
            if (this.readBoolean()) {
                this.t = this.readUTF();
                this.u = this.readUTF();
            }
        }
        this.r = this.readInt();
    }
    
    public int d() {
        return this.l;
    }
    
    public String e() {
        return this.s;
    }
    
    public String f() {
        return this.t;
    }
    
    public String g() {
        return this.u;
    }
    
    public int h() {
        return this.p;
    }
    
    public int j() {
        return this.r;
    }
    
    public int k() {
        return this.o;
    }
    
    public int l() {
        return this.q;
    }
    
    public int m() {
        return this.n;
    }
    
    public boolean q() {
        return this.m;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        this.a(sb);
        return sb.toString();
    }
    
    public StringBuffer a(final StringBuffer sb) {
        sb.append("[player_info=");
        super.a(sb);
        sb.append(",pid=").append(this.l).append(",r=").append(this.p);
        sb.append(",l=").append(this.q).append(",nbg=").append(this.n);
        if (this.s != null) {
            sb.append(",tz=").append(this.s);
        }
        if (this.t != null) {
            sb.append(",h1=").append(this.t).append(",h2=").append(this.u);
        }
        sb.append("]");
        return sb;
    }
    
    public int i() {
        int i = super.i();
        i += 4;
        ++i;
        ++i;
        if (this.s != null) {
            i += 4;
            i += 4;
            i += 4;
            i += 4;
            i += com.daysofwonder.req.p.a(this.s);
            ++i;
            if (this.t != null) {
                i = i + com.daysofwonder.req.p.a(this.t) + com.daysofwonder.req.p.a(this.u);
            }
        }
        i += 4;
        return i;
    }
}
