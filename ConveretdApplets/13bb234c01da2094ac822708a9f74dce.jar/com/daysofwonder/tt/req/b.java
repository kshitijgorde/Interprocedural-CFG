// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.tt.req;

import com.daysofwonder.req.G;
import com.daysofwonder.tt.i;

public class b extends a
{
    private i[] l;
    private int[] m;
    private boolean n;
    private boolean o;
    private int p;
    private int q;
    
    public b(final G g) {
        this.g = 1007;
        this.a(g.t());
        this.b();
    }
    
    public void a() {
        super.a();
        this.writeInt(this.l.length);
        for (int i = 0; i < this.l.length; ++i) {
            if (this.l[i] == null) {
                this.writeShort(-1);
            }
            else {
                this.writeShort(this.l[i].g());
            }
        }
        this.writeBoolean(this.n);
        this.writeBoolean(this.o);
        this.writeInt(this.p);
        this.writeInt(this.q);
    }
    
    public void b() {
        super.b();
        final int int1 = this.readInt();
        this.l = new i[int1];
        this.m = new int[int1];
        for (int i = 0; i < int1; ++i) {
            this.m[i] = this.readShort();
        }
        this.n = this.readBoolean();
        this.o = this.readBoolean();
        this.p = this.readInt();
        this.q = this.readInt();
    }
    
    public void c() {
        for (int i = 0; i < this.m.length; ++i) {
            this.l[i] = (i)this.c.c(this.m[i]);
        }
    }
    
    public i[] d() {
        return this.l;
    }
    
    public boolean e() {
        return this.n;
    }
    
    public boolean f() {
        return this.o;
    }
    
    public int g() {
        return this.p;
    }
    
    public int h() {
        return this.q;
    }
    
    public int i() {
        return super.i() + (4 + this.l.length * 2 + 2 + 8);
    }
}
