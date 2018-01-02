// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.tt.req;

import com.daysofwonder.req.G;
import com.daysofwonder.tt.i;

public class k extends a
{
    private int l;
    private int m;
    private int n;
    private int o;
    private boolean p;
    private i q;
    private i r;
    private int s;
    private int t;
    
    public k(final G g) {
        this.g = 1005;
        this.a(g.t());
        this.b();
    }
    
    public void a() {
        super.a();
        this.writeInt(this.l);
        this.writeInt(this.m);
        this.writeBoolean(this.p);
        this.writeShort(this.q.g());
        if (this.m >= 0) {
            if (this.r == null) {
                this.writeShort(-1);
            }
            else {
                this.writeShort(this.r.g());
            }
        }
        this.writeInt(this.n);
        this.writeInt(this.o);
    }
    
    public void b() {
        super.b();
        this.l = this.readInt();
        this.m = this.readInt();
        this.p = this.readBoolean();
        this.s = this.readShort();
        if (this.m >= 0) {
            this.t = this.readShort();
        }
        this.n = this.readInt();
        this.o = this.readInt();
    }
    
    public void c() {
        this.q = (i)this.c.c(this.s);
        this.r = (i)this.c.c(this.t);
    }
    
    public int d() {
        return this.m;
    }
    
    public boolean e() {
        return this.p;
    }
    
    public int f() {
        return this.l;
    }
    
    public int g() {
        return this.n;
    }
    
    public int h() {
        return this.o;
    }
    
    public i j() {
        return this.q;
    }
    
    public i k() {
        return this.r;
    }
    
    public int i() {
        int i = super.i();
        i += 17;
        return i;
    }
}
