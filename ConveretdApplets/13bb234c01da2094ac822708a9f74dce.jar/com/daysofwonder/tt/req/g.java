// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.tt.req;

import com.daysofwonder.req.p;
import java.util.Enumeration;
import com.daysofwonder.tt.o;
import com.daysofwonder.req.k;
import com.daysofwonder.req.G;
import java.util.Hashtable;
import java.util.Vector;

public class g extends a
{
    private Vector l;
    private Vector m;
    private Hashtable n;
    
    public g(final G g) {
        this.g = 1003;
        this.a(g.t());
        this.b();
    }
    
    public void a() {
        super.a();
        this.writeInt(this.l.size());
        for (int i = 0; i < this.l.size(); ++i) {
            ((j)this.l.elementAt(i)).a(this);
        }
        this.writeInt(this.m.size());
        for (int j = 0; j < this.m.size(); ++j) {
            final com.daysofwonder.util.G g = this.m.elementAt(j);
            this.writeInt(g.a());
            for (int k = 0; k < g.a(); ++k) {
                ((o)g.b(k)).a(this);
            }
        }
        this.writeInt(this.n.size());
        final Enumeration<String> keys = this.n.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            this.writeUTF(s);
            final Vector<Integer> vector = this.n.get(s);
            this.writeShort(vector.size());
            for (int l = 0; l < vector.size(); ++l) {
                this.writeInt(vector.elementAt(l));
            }
        }
    }
    
    public void b() {
        super.b();
        final int int1 = this.readInt();
        this.l = new Vector(int1);
        for (int i = 0; i < int1; ++i) {
            final j j = new j();
            j.b(this);
            this.l.addElement(j);
        }
        final int int2 = this.readInt();
        this.m = new Vector(int2);
        for (int k = 0; k < int2; ++k) {
            final int int3 = this.readInt();
            final com.daysofwonder.util.G g = new com.daysofwonder.util.G(int3);
            for (int l = 0; l < int3; ++l) {
                final o o = new o();
                o.b(this);
                g.c(o);
            }
            this.m.addElement(g);
        }
        final int int4 = this.readInt();
        this.n = new Hashtable(int4);
        for (int n = 0; n < int4; ++n) {
            final String utf = this.readUTF();
            final short short1 = this.readShort();
            final Vector<Integer> vector = new Vector<Integer>();
            for (short n2 = 0; n2 < short1; ++n2) {
                vector.addElement(this.readInt());
            }
            this.n.put(utf, vector);
        }
    }
    
    public int i() {
        int i = super.i();
        i += 4;
        for (int j = 0; j < this.l.size(); ++j) {
            i += ((j)this.l.elementAt(j)).a();
        }
        i += 4;
        for (int k = 0; k < this.m.size(); ++k) {
            i += 4 + ((com.daysofwonder.util.G)this.m.elementAt(k)).a() * 4;
        }
        i += 4;
        i += 4;
        final Enumeration<String> keys = this.n.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            i = i + p.a(s) + (2 + ((Vector)this.n.get(s)).size() * 4);
        }
        return i;
    }
    
    public Vector d() {
        return this.l;
    }
    
    public Vector e() {
        return this.m;
    }
    
    public Hashtable f() {
        return this.n;
    }
}
