// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.req;

import com.daysofwonder.a.n;
import java.io.DataInput;
import java.io.DataOutput;
import java.util.Vector;

public class u extends k
{
    private static final Boolean l;
    private static final Boolean m;
    private Vector n;
    private Vector o;
    
    private static Boolean a(final boolean b) {
        return b ? u.l : u.m;
    }
    
    private static boolean b(final Boolean b) {
        return b == u.l;
    }
    
    public u() {
        super(5);
        this.n = new Vector();
        this.o = new Vector();
        this.D();
    }
    
    public u(final G g) {
        this.n = new Vector();
        this.o = new Vector();
        this.g = 5;
        this.a(g.t());
        this.b();
    }
    
    public void a() {
        super.a();
        this.writeInt(this.n.size());
        for (int i = 0; i < this.n.size(); ++i) {
            final F f = this.n.elementAt(i);
            this.writeInt(f.a);
            this.writeUTF((f.c == null) ? "" : f.c);
            this.writeBoolean(f.b);
            this.writeInt(f.i);
            this.writeBoolean(f.j);
            this.writeBoolean(f.k);
            this.writeBoolean(f.l);
            this.writeInt(f.d.length);
            for (int j = 0; j < f.d.length; ++j) {
                this.writeUTF((f.d[j] == null) ? "" : f.d[j]);
            }
            this.writeInt(f.e.length);
            for (int k = 0; k < f.e.length; ++k) {
                this.writeInt(f.e[k]);
            }
            this.writeInt(f.f.length);
            for (int l = 0; l < f.f.length; ++l) {
                this.writeInt(f.f[l]);
            }
            this.writeInt(f.g.length);
            for (int n = 0; n < f.g.length; ++n) {
                this.writeBoolean(f.g[n]);
            }
            this.writeInt(f.h.length);
            for (int n2 = 0; n2 < f.h.length; ++n2) {
                this.writeInt(f.h[n2]);
            }
            this.writeInt(f.m);
            this.writeInt(f.n);
            this.writeBoolean(f.o != null);
            if (f.o != null) {
                f.o.a((DataOutput)this);
            }
        }
        final int size = this.o.size();
        this.writeInt(size);
        int n3 = size;
        while (--n3 >= 0) {
            final c c = this.o.elementAt(n3);
            this.writeUTF((c.b == null) ? "" : c.b);
            this.writeInt(c.a);
            this.writeBoolean(c.c);
            this.writeInt(c.d);
        }
    }
    
    public void b() {
        super.b();
        final int int1 = this.readInt();
        this.n = new Vector();
        for (int i = 0; i < int1; ++i) {
            final int int2 = this.readInt();
            final String utf = this.readUTF();
            final boolean boolean1 = this.readBoolean();
            final int int3 = this.readInt();
            final boolean boolean2 = this.readBoolean();
            final boolean boolean3 = this.readBoolean();
            final boolean boolean4 = this.readBoolean();
            final int int4 = this.readInt();
            Vector<String> vector = new Vector<String>();
            if (int4 > 0) {
                vector.ensureCapacity(int4);
                for (int j = 0; j < int4; ++j) {
                    vector.addElement(this.readUTF());
                }
            }
            else {
                vector = new Vector<String>();
            }
            Vector<Integer> vector2 = new Vector<Integer>();
            final int int5 = this.readInt();
            if (int5 > 0) {
                vector2.ensureCapacity(int5);
                for (int k = 0; k < int5; ++k) {
                    vector2.addElement(this.readInt());
                }
            }
            else {
                vector2 = new Vector<Integer>();
            }
            final int int6 = this.readInt();
            Vector<Integer> vector3 = new Vector<Integer>();
            if (int6 > 0) {
                vector3.ensureCapacity(int6);
                for (int l = 0; l < int6; ++l) {
                    vector3.addElement(this.readInt());
                }
            }
            else {
                vector3 = new Vector<Integer>();
            }
            final int int7 = this.readInt();
            Vector<Boolean> vector4 = new Vector<Boolean>();
            if (int7 > 0) {
                vector4.ensureCapacity(int7);
                for (int n = 0; n < int7; ++n) {
                    vector4.addElement(a(this.readBoolean()));
                }
            }
            else {
                vector4 = new Vector<Boolean>();
            }
            final int int8 = this.readInt();
            Vector<Integer> vector5 = new Vector<Integer>();
            if (int8 > 0) {
                vector5.ensureCapacity(int8);
                for (int n2 = 0; n2 < int8; ++n2) {
                    vector5.addElement(this.readInt());
                }
            }
            else {
                vector5 = new Vector<Integer>();
            }
            final int int9 = this.readInt();
            final int int10 = this.readInt();
            n c = null;
            if (this.readBoolean()) {
                c = com.daysofwonder.a.k.d().c();
                c.a((DataInput)this);
            }
            this.n.addElement(new F(int2, utf, boolean1, vector, vector3, vector4, vector5, vector2, int3, boolean2, boolean3, boolean4, int9, int10, c, null));
            vector.removeAllElements();
            vector2.removeAllElements();
            vector3.removeAllElements();
        }
        final int int11 = this.readInt();
        this.o = new Vector();
        for (int n3 = 0; n3 < int11; ++n3) {
            this.o.addElement(new c(this.readUTF(), this.readInt(), this.readBoolean(), this.readInt(), null));
        }
    }
    
    public int i() {
        int i = super.i();
        this.n.size();
        i += 4;
        for (int j = 0; j < this.n.size(); ++j) {
            final F f = this.n.elementAt(j);
            i += 4;
            int n = i + p.a((f.c == null) ? "" : f.c);
            ++n;
            n += 4;
            ++n;
            ++n;
            ++n;
            n += 4;
            for (int k = 0; k < f.d.length; ++k) {
                n += p.a((f.d[k] == null) ? "" : f.d[k]);
            }
            n += 4;
            for (int l = 0; l < f.e.length; ++l) {
                n += 4;
            }
            n += 4;
            for (int n2 = 0; n2 < f.f.length; ++n2) {
                n += 4;
            }
            n += 4;
            for (int n3 = 0; n3 < f.g.length; ++n3) {
                ++n;
            }
            n += 4;
            for (int n4 = 0; n4 < f.h.length; ++n4) {
                n += 4;
            }
            n += 4;
            n += 4;
            i = ++n + ((f.o != null) ? f.o.a(this) : 0);
        }
        this.o.size();
        i += 4;
        for (int n5 = 0; n5 < this.o.size(); ++n5) {
            final c c = this.o.elementAt(n5);
            i += p.a((c.b == null) ? "" : c.b);
            i += 4;
            ++i;
            i += 4;
        }
        return i;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        this.a(sb);
        return sb.toString();
    }
    
    public StringBuffer a(final StringBuffer sb) {
        sb.append("[tlist=");
        super.a(sb);
        sb.append(']');
        return sb;
    }
    
    static {
        l = new Boolean(true);
        m = new Boolean(false);
    }
}
