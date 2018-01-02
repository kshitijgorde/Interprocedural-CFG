// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.tt.req;

import com.daysofwonder.req.k;
import com.daysofwonder.req.G;
import com.daysofwonder.tt.c;
import com.daysofwonder.tt.o;
import com.daysofwonder.tt.i;
import com.daysofwonder.tt.n;

public class h extends a
{
    private int l;
    private boolean m;
    private int n;
    private n o;
    private i p;
    private int q;
    private int r;
    private o s;
    private int t;
    private c u;
    private int v;
    
    public h() {
        super(1002);
        this.l = -1;
        this.D();
    }
    
    public h(final com.daysofwonder.a.i i, final n o, final o s) {
        super(1002);
        this.l = -1;
        this.o = o;
        this.q = i.v();
        this.s = s;
        this.t = s.g();
        this.l = 0;
        this.D();
    }
    
    public h(final com.daysofwonder.a.i i, final n o, final c u) {
        super(1002);
        this.l = -1;
        this.o = o;
        this.q = i.v();
        this.u = u;
        this.v = u.f();
        this.l = 3;
        this.D();
    }
    
    public h(final com.daysofwonder.a.i i, final int n) {
        super(1002);
        this.l = -1;
        this.l = 2;
        this.q = i.v();
        this.n = n;
        this.D();
    }
    
    public h(final com.daysofwonder.a.i i) {
        super(1002);
        this.l = -1;
        this.l = 1;
        this.D();
    }
    
    public h(final G g) {
        this.l = -1;
        this.g = 1002;
        this.a(g.t());
        this.b();
    }
    
    public void a() {
        super.a();
        this.writeBoolean(this.m);
        if (!this.m) {
            this.writeInt(this.l);
            this.writeInt(this.q);
            this.writeBoolean(this.o != null);
            if (this.o != null) {
                this.o.b(this);
            }
            if (this.s != null) {
                this.s.a(this);
            }
            else {
                this.writeByte(-1);
            }
            this.writeInt(this.n);
            if (this.p != null) {
                this.writeShort(this.p.g());
            }
            else {
                this.writeShort(-1);
            }
            if (this.u != null) {
                this.u.a(this);
            }
            else {
                this.writeByte(-1);
            }
        }
    }
    
    public void b() {
        super.b();
        if (!(this.m = this.readBoolean())) {
            this.l = this.readInt();
            this.q = this.readInt();
            if (this.readBoolean()) {
                (this.o = new n()).a(this);
            }
            this.t = this.readByte();
            this.n = this.readInt();
            this.r = this.readShort();
            this.v = this.readByte();
        }
    }
    
    public void c() {
        this.p = (i)this.c.c(this.r);
        if (this.o != null) {
            this.o.a(this.c);
        }
    }
    
    public int i() {
        int i = super.i();
        ++i;
        if (!this.m) {
            i += 8;
            i = ++i + ((this.o != null) ? this.o.e() : 0);
            ++i;
            i += 4;
            i += 2;
            ++i;
        }
        return i;
    }
    
    public StringBuffer a(final StringBuffer sb) {
        sb.append("[TTGameActionRequest=");
        super.a(sb);
        sb.append(",action=").append(this.l).append(",pid=").append(this.q);
        sb.append(",card=").append(this.n).append(",route=").append(this.t).append(",city=").append(this.v);
        sb.append(",hand=").append(this.o);
        return sb;
    }
}
