// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.tt.req;

import com.daysofwonder.req.G;
import com.daysofwonder.tt.i;
import com.daysofwonder.req.k;

public class m extends k
{
    private i l;
    private int m;
    private short n;
    
    public m(final G g) {
        this.g = 1010;
        this.a(g.t());
        this.b();
    }
    
    public void a() {
        super.a();
        this.writeInt(this.m);
        this.writeShort(this.l.g());
    }
    
    public void b() {
        super.b();
        this.m = this.readInt();
        this.n = this.readShort();
    }
    
    public void c() {
        this.l = (i)this.c.c(this.n);
    }
    
    public int i() {
        int i = super.i();
        i += 6;
        return i;
    }
}
