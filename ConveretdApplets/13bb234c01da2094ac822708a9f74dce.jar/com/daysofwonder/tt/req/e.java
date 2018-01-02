// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.tt.req;

import com.daysofwonder.req.k;
import com.daysofwonder.req.G;
import com.daysofwonder.tt.o;
import com.daysofwonder.tt.n;

public class e extends a
{
    private int l;
    private n m;
    private o n;
    
    public e(final G g) {
        this.g = 1004;
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
        return i;
    }
    
    public void c() {
        this.m.a(this.c);
    }
    
    public o a(final com.daysofwonder.tt.e e) {
        return e.a(this.n);
    }
    
    public n d() {
        return this.m;
    }
    
    public int e() {
        return this.l;
    }
}
