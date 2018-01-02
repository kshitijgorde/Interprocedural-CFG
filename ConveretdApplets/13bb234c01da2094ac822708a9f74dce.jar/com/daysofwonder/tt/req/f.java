// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.tt.req;

import com.daysofwonder.util.t;
import com.daysofwonder.req.k;
import java.util.Hashtable;
import com.daysofwonder.util.G;
import com.daysofwonder.tt.n;
import java.util.Vector;
import com.daysofwonder.tt.i;

public class f extends a
{
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private i[] q;
    private int[] r;
    private int[] s;
    private Vector t;
    private int u;
    private n v;
    private com.daysofwonder.util.G w;
    private boolean x;
    private boolean y;
    private Hashtable z;
    
    public f(final G g) {
        this.g = 1001;
        this.a(g.t());
        this.b();
    }
    
    public void a() {
        super.a();
        this.writeInt(this.u);
        this.writeInt(this.l);
        this.writeInt(this.m);
        this.writeInt(this.n);
        this.writeInt(this.o);
        this.writeInt(this.p);
        this.writeBoolean(this.x);
        this.writeInt(this.q.length);
        for (int i = 0; i < this.q.length; ++i) {
            if (this.q[i] == null) {
                this.writeShort(-1);
            }
            else {
                this.writeShort(this.q[i].g());
            }
        }
        this.v.b(this);
        this.writeInt(this.w.a());
        for (int j = 0; j < this.w.a(); ++j) {
            ((com.daysofwonder.tt.f)this.w.b(j)).a(this);
        }
        if (this.t != null) {
            this.writeInt(this.t.size());
            for (int k = 0; k < this.t.size(); ++k) {
                ((c)this.t.elementAt(k)).a(this);
            }
        }
        else {
            this.writeInt(0);
        }
        this.writeBoolean(this.s != null);
        if (this.s != null) {
            this.writeInt(this.s.length);
            for (int l = 0; l < this.s.length; ++l) {
                this.writeInt(this.s[l]);
            }
        }
        this.writeBoolean(this.y);
    }
    
    public void b() {
        super.b();
        this.u = this.readInt();
        this.l = this.readInt();
        this.m = this.readInt();
        this.n = this.readInt();
        this.o = this.readInt();
        this.p = this.readInt();
        this.x = this.readBoolean();
        final int int1 = this.readInt();
        this.q = new i[int1];
        this.r = new int[int1];
        for (int i = 0; i < int1; ++i) {
            this.r[i] = this.readShort();
        }
        (this.v = new n()).a(this);
        final int int2 = this.readInt();
        if (int2 >= 0) {
            this.w = new com.daysofwonder.util.G(int2);
            for (int j = 0; j < int2; ++j) {
                this.w.c((Object)(int)this.readByte());
            }
        }
        final int int3 = this.readInt();
        if (int3 >= 0) {
            this.t = new Vector(int3);
            for (int k = 0; k < int3; ++k) {
                final c c = new c();
                c.b(this);
                this.t.addElement(c);
            }
        }
        if (this.readBoolean()) {
            final int int4 = this.readInt();
            if (int4 > 0) {
                this.s = new int[int4];
                for (int l = 0; l < int4; ++l) {
                    this.s[l] = this.readInt();
                }
            }
        }
        this.y = this.readBoolean();
    }
    
    public void c() {
        for (int i = 0; i < this.r.length; ++i) {
            this.q[i] = (i)this.c.c(this.r[i]);
        }
        this.v.a(this.c);
    }
    
    private c l(final int n) {
        if (this.z == null) {
            this.z = new Hashtable();
        }
        c c;
        if ((c = this.z.get(n)) == null) {
            for (int i = 0; i < this.t.size(); ++i) {
                final c c2 = this.t.elementAt(i);
                if (c2.d() == n) {
                    c = c2;
                    this.z.put(n, c2);
                    break;
                }
            }
        }
        if (c == null) {
            com.daysofwonder.util.t.d("getEncodedPlayer(" + n + ") is null");
        }
        return c;
    }
    
    public int i() {
        int i = super.i();
        i += 4;
        i += 4;
        i += 4;
        i += 4;
        i += 4;
        i += 4;
        int n = ++i + (4 + this.q.length * 2) + this.v.e();
        n += 4;
        n += 4;
        n += 4;
        n += 4;
        ++n;
        n += 4;
        if (this.t != null) {
            for (int j = 0; j < this.t.size(); ++j) {
                n += ((c)this.t.elementAt(j)).a();
            }
        }
        if (this.s != null) {
            n += (this.s.length + 1) * 4;
        }
        return ++n;
    }
    
    public com.daysofwonder.util.G a(final int n) {
        return this.l(n).b();
    }
    
    public com.daysofwonder.util.G b(final int n) {
        return this.l(n).c();
    }
    
    public int c(final int n) {
        return this.l(n).e();
    }
    
    public int d(final int n) {
        return this.l(n).f();
    }
    
    public int e(final int n) {
        return this.l(n).g();
    }
    
    public int f(final int n) {
        return this.l(n).h();
    }
    
    public int g(final int n) {
        return this.l(n).i();
    }
    
    public boolean h(final int n) {
        return this.l(n).j();
    }
    
    public int d() {
        return this.n;
    }
    
    public int e() {
        return this.o;
    }
    
    public int f() {
        return this.p;
    }
    
    public boolean g() {
        return this.x;
    }
    
    public boolean h() {
        return this.y;
    }
    
    public int j() {
        return this.m;
    }
    
    public n k() {
        return this.v;
    }
    
    public com.daysofwonder.util.G l() {
        return this.w;
    }
    
    public int[] m() {
        return this.s;
    }
    
    public i[] q() {
        return this.q;
    }
    
    public int r() {
        return this.u;
    }
}
