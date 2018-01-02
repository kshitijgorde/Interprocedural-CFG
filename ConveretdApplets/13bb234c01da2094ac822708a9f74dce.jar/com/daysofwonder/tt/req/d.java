// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.tt.req;

import com.daysofwonder.req.k;
import com.daysofwonder.tt.f;
import com.daysofwonder.util.G;
import java.util.Vector;

public class d extends a
{
    private Vector l;
    private int[] m;
    private Vector n;
    private int o;
    private int p;
    private int q;
    private int r;
    private com.daysofwonder.util.G s;
    
    public d(final Vector vector, final int q) {
        super(1003);
        this.l = new Vector();
        (this.n = new Vector()).ensureCapacity(vector.size());
        for (int i = 0; i < vector.size(); ++i) {
            this.n.addElement(vector.elementAt(i));
        }
        this.q = q;
        this.D();
    }
    
    public d(final G g) {
        this.l = new Vector();
        this.n = new Vector();
        this.g = 1003;
        this.a(g.t());
        this.b();
    }
    
    public void a() {
        super.a();
        this.writeInt(this.o);
        this.writeInt(this.p);
        this.writeInt(this.q);
        this.writeInt(this.l.size());
        for (int i = 0; i < this.l.size(); ++i) {
            ((f)this.l.elementAt(i)).a(this);
        }
        this.writeInt(this.n.size());
        for (int j = 0; j < this.n.size(); ++j) {
            this.writeInt((int)this.n.elementAt(j));
        }
        if (this.s != null) {
            this.writeInt(this.s.a());
            for (int k = 0; k < this.s.a(); ++k) {
                ((f)this.s.b(k)).a(this);
            }
        }
        else {
            this.writeInt(0);
        }
        this.writeInt(this.r);
    }
    
    public void b() {
        super.b();
        this.o = this.readInt();
        this.p = this.readInt();
        this.q = this.readInt();
        final int int1 = this.readInt();
        if (int1 > 0) {
            this.m = new int[int1];
            for (int i = 0; i < int1; ++i) {
                this.m[i] = this.readByte();
            }
        }
        final int int2 = this.readInt();
        this.n.ensureCapacity(int2);
        for (int j = 0; j < int2; ++j) {
            this.n.addElement(this.readInt());
        }
        final int int3 = this.readInt();
        if (int3 > 0) {
            this.s = new com.daysofwonder.util.G(int3);
            for (int k = 0; k < int3; ++k) {
                this.s.c((Object)(int)this.readByte());
            }
        }
        this.r = this.readInt();
    }
    
    public int[] d() {
        return this.m;
    }
    
    public int e() {
        return this.o;
    }
    
    public int f() {
        return this.p;
    }
    
    public int g() {
        return this.r;
    }
    
    public int h() {
        return this.q;
    }
    
    public Vector j() {
        return this.n;
    }
    
    public com.daysofwonder.util.G k() {
        return this.s;
    }
    
    public int i() {
        int i = super.i();
        i += 16;
        int n = i + this.l.size() * 1 + (4 + 4 * this.n.size()) + (4 + ((this.s != null) ? (this.s.a() * 1) : 0));
        n += 4;
        return n;
    }
}
