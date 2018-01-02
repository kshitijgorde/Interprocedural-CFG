// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.req;

public class a extends k
{
    private int l;
    private int m;
    private int n;
    private String o;
    private String p;
    private String q;
    private boolean r;
    
    private a(final String p2, final int n) {
        super(7);
        this.n = -1;
        this.p = p2;
        this.n = n;
        this.l = -1;
    }
    
    public a(final String s, final int m, final int n) {
        this(s, n);
        this.o = null;
        this.m = m;
        this.D();
    }
    
    public a(final String s, final String o, final int n) {
        this(s, n);
        this.o = o;
        this.m = -1;
        this.D();
    }
    
    public a(final G g) {
        this.n = -1;
        this.g = 7;
        this.a(g.t());
        this.b();
    }
    
    public void a() {
        super.a();
        this.writeInt(this.m);
        this.writeInt(this.l);
        if (this.o != null) {
            this.writeUTF(this.o);
        }
        if (this.p != null) {
            this.writeUTF(this.p);
        }
        this.writeBoolean(this.q != null);
        if (this.q != null) {
            this.writeUTF(this.q);
        }
        this.writeInt(this.n);
        this.writeBoolean(this.r);
    }
    
    public void b() {
        super.b();
        this.m = this.readInt();
        this.l = this.readInt();
        if (this.m == -1) {
            this.o = this.readUTF();
        }
        if (this.l == -1) {
            this.p = this.readUTF();
        }
        if (this.readBoolean()) {
            this.q = this.readUTF();
        }
        this.n = this.readInt();
        this.r = this.readBoolean();
    }
    
    public int a_() {
        return this.m;
    }
    
    public String d() {
        return this.o;
    }
    
    public String e() {
        return this.p;
    }
    
    public String f() {
        return this.q;
    }
    
    public int g() {
        return this.l;
    }
    
    public int h() {
        return this.n;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        this.a(sb);
        return sb.toString();
    }
    
    public StringBuffer a(final StringBuffer sb) {
        sb.append("[Chat=");
        super.a(sb);
        sb.append("]");
        return sb;
    }
    
    public int i() {
        int i = super.i();
        i += 4;
        i += 4;
        i += 4;
        if (this.o != null) {
            i += com.daysofwonder.req.p.a(this.o);
        }
        if (this.p != null) {
            i += com.daysofwonder.req.p.a(this.p);
        }
        ++i;
        if (this.q != null) {
            i += com.daysofwonder.req.p.a(this.q);
        }
        return ++i;
    }
    
    public boolean j() {
        return this.r;
    }
}
