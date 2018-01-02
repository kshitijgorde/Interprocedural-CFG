// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.tt.req;

import com.daysofwonder.tt.e;
import com.daysofwonder.req.k;
import com.daysofwonder.util.G;
import com.daysofwonder.tt.o;
import com.daysofwonder.tt.n;

public class i extends a
{
    private int l;
    private n m;
    private o n;
    private n o;
    private n p;
    private com.daysofwonder.util.G q;
    private boolean r;
    private boolean s;
    private int t;
    
    public i(final int t) {
        super(1009);
        this.t = t;
        this.s = true;
        this.D();
    }
    
    public i(final G g) {
        this.g = 1009;
        this.a(g.t());
        this.b();
    }
    
    public void a() {
        super.a();
        this.writeInt(this.l);
        this.writeBoolean(this.m != null);
        if (this.m != null) {
            this.m.b(this);
        }
        this.writeBoolean(this.n != null);
        if (this.n != null) {
            this.n.a(this);
        }
        this.writeBoolean(this.o != null);
        if (this.o != null) {
            this.o.b(this);
        }
        if (this.q != null) {
            this.writeInt(this.q.a());
            for (int i = 0; i < this.q.a(); ++i) {
                this.writeShort(((com.daysofwonder.tt.i)this.q.b(i)).g());
            }
        }
        else {
            this.writeInt(0);
        }
        this.writeBoolean(this.r);
        this.writeBoolean(this.s);
        this.writeInt(this.t);
        this.writeBoolean(this.p != null);
        if (this.p != null) {
            this.p.b(this);
        }
    }
    
    public void b() {
        super.b();
        this.l = this.readInt();
        if (this.readBoolean()) {
            (this.m = new n()).a(this);
        }
        if (this.readBoolean()) {
            (this.n = new o()).b(this);
        }
        if (this.readBoolean()) {
            (this.o = new n()).a(this);
        }
        final int int1 = this.readInt();
        this.q = new com.daysofwonder.util.G();
        for (int i = 0; i < int1; ++i) {
            this.q.c((Object)(int)this.readShort());
        }
        this.r = this.readBoolean();
        this.s = this.readBoolean();
        this.t = this.readInt();
        if (this.readBoolean()) {
            (this.p = new n()).a(this);
        }
    }
    
    public void c() {
        if (this.c != null) {
            this.q = ((e)this.c).d(this.q);
            if (this.o != null) {
                this.o.a(this.c);
            }
            if (this.m != null) {
                this.m.a(this.c);
            }
            if (this.p != null) {
                this.p.a(this.c);
            }
        }
    }
    
    public int i() {
        int i = super.i();
        i += 4;
        ++i;
        if (this.m != null) {
            i += this.m.e();
        }
        ++i;
        if (this.n != null) {
            i += 4;
        }
        ++i;
        if (this.o != null) {
            i += this.o.e();
        }
        i += 4;
        if (this.q != null) {
            i += this.q.a() * 2;
        }
        ++i;
        ++i;
        i += 4;
        ++i;
        if (this.p != null) {
            i += this.p.e();
        }
        return i;
    }
    
    public o a(final e e) {
        return e.a(this.n);
    }
    
    public com.daysofwonder.util.G d() {
        return this.q;
    }
    
    public n e() {
        return this.o;
    }
    
    public n f() {
        return this.p;
    }
    
    public n g() {
        return this.m;
    }
    
    public int h() {
        return this.l;
    }
    
    public boolean j() {
        return this.r;
    }
    
    public boolean k() {
        return this.s;
    }
    
    public boolean l() {
        return this.t == 1 || this.t == 2;
    }
}
