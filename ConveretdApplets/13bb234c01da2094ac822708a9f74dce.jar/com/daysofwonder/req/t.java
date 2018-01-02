// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.req;

import java.io.DataInput;
import java.util.Enumeration;
import java.io.DataOutput;
import java.util.Vector;
import com.daysofwonder.a.n;

public class t extends k
{
    private String l;
    private String m;
    private String n;
    private int o;
    private int p;
    private boolean q;
    private boolean r;
    private boolean s;
    private boolean t;
    private int u;
    private int v;
    private int w;
    private int x;
    private n y;
    private Vector z;
    
    public t(final String l, final String n, final int o) {
        super(2);
        this.p = -1;
        this.z = new Vector();
        this.l = l;
        this.n = n;
        this.q = false;
        this.o = o;
        this.D();
    }
    
    public t(final String l, final String n, final boolean q, final boolean s, final boolean t, final int w, final int x, final n y) {
        super(2);
        this.p = -1;
        this.z = new Vector();
        this.l = l;
        this.n = n;
        this.q = q;
        this.t = t;
        this.s = s;
        this.w = w;
        this.y = y;
        this.x = x;
        this.D();
    }
    
    public t(final String m, final String l, final String n, final boolean r) {
        super(2);
        this.p = -1;
        this.z = new Vector();
        this.l = l;
        this.n = n;
        this.m = m;
        this.r = r;
        this.q = false;
        this.D();
    }
    
    public t() {
        this.p = -1;
        this.z = new Vector();
    }
    
    public t(final G g) {
        this.p = -1;
        this.z = new Vector();
        this.g = 2;
        this.a(g.t());
        this.b();
    }
    
    public void a() {
        super.a();
        this.writeInt(this.z.size());
        final Enumeration<D> elements = this.z.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().a(this);
        }
        this.writeUTF(this.l);
        this.writeUTF(this.n);
        this.writeInt(this.o);
        this.writeInt(this.p);
        this.writeBoolean(this.q);
        this.writeInt(this.u);
        this.writeInt(this.v);
        this.writeBoolean(this.r);
        if (this.m != null) {
            this.writeUTF(this.m);
        }
        else {
            this.writeUTF("");
        }
        this.writeBoolean(this.s);
        this.writeBoolean(this.t);
        this.writeInt(this.w);
        this.writeBoolean(this.y != null);
        if (this.y != null) {
            this.y.a((DataOutput)this);
        }
        this.writeInt(this.x);
    }
    
    public void b() {
        super.b();
        final int int1 = this.readInt();
        if (int1 != 0) {
            this.z = new Vector(int1);
            for (int i = 0; i < int1; ++i) {
                this.z.addElement(D.b(this));
            }
        }
        else {
            this.z = null;
        }
        this.l = this.readUTF();
        this.n = this.readUTF();
        this.o = this.readInt();
        this.p = this.readInt();
        this.q = this.readBoolean();
        this.u = this.readInt();
        this.v = this.readInt();
        this.r = this.readBoolean();
        this.m = this.readUTF();
        this.s = this.readBoolean();
        this.t = this.readBoolean();
        this.w = this.readInt();
        if (this.readBoolean()) {
            (this.y = com.daysofwonder.a.k.d().c()).a((DataInput)this);
        }
        this.x = this.readInt();
    }
    
    public Vector d() {
        return this.z;
    }
    
    public int e() {
        return this.o;
    }
    
    public int f() {
        return this.p;
    }
    
    public n g() {
        return this.y;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        this.a(sb);
        return sb.toString();
    }
    
    public StringBuffer a(final StringBuffer sb) {
        sb.append("[Table=");
        super.a(sb);
        sb.append(",tid=").append(this.o).append(",pid=").append(this.p).append(']');
        return sb;
    }
    
    public int i() {
        int i = super.i();
        i += 4;
        final Enumeration<D> elements = this.z.elements();
        while (elements.hasMoreElements()) {
            i += elements.nextElement().a();
        }
        int n = i + com.daysofwonder.req.p.a(this.l) + com.daysofwonder.req.p.a(this.n);
        n += 4;
        n += 4;
        ++n;
        n += 4;
        n += 4;
        ++n;
        int n2;
        if (this.m != null) {
            n2 = n + com.daysofwonder.req.p.a(this.m);
        }
        else {
            n2 = n + com.daysofwonder.req.p.a("");
        }
        ++n2;
        ++n2;
        n2 += 4;
        ++n2;
        if (this.y != null) {
            n2 += 4 + this.y.a(this);
        }
        n2 += 4;
        return n2;
    }
}
