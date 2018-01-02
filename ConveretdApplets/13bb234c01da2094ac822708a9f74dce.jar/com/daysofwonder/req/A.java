// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.req;

import java.io.DataInput;
import java.util.Enumeration;
import java.io.DataOutput;
import com.daysofwonder.a.n;
import java.util.Vector;

public class A extends k
{
    private int l;
    private int m;
    private Vector n;
    private Vector o;
    private Vector p;
    private Vector q;
    private int r;
    private n s;
    private byte[] t;
    
    public A(final int l) {
        super(8);
        this.m = -1;
        this.l = l;
        this.D();
    }
    
    public A(final G g) {
        this.m = -1;
        this.g = 8;
        this.a(g.t());
        this.b();
    }
    
    public void a() {
        super.a();
        if (this.n != null) {
            this.writeInt(this.n.size());
            final Enumeration<String> elements = this.n.elements();
            while (elements.hasMoreElements()) {
                this.writeUTF(elements.nextElement());
            }
        }
        else {
            this.writeInt(0);
        }
        if (this.o != null) {
            this.writeInt(this.o.size());
            final Enumeration<Integer> elements2 = this.o.elements();
            while (elements2.hasMoreElements()) {
                this.writeInt(elements2.nextElement());
            }
        }
        else {
            this.writeInt(0);
        }
        if (this.p != null) {
            this.writeInt(this.p.size());
            final Enumeration<Integer> elements3 = this.p.elements();
            while (elements3.hasMoreElements()) {
                this.writeInt(elements3.nextElement());
            }
        }
        else {
            this.writeInt(0);
        }
        if (this.q != null) {
            this.writeInt(this.q.size());
            final Enumeration<Integer> elements4 = this.q.elements();
            while (elements4.hasMoreElements()) {
                this.writeInt(elements4.nextElement());
            }
        }
        else {
            this.writeInt(0);
        }
        this.writeInt(this.l);
        this.writeInt(this.r);
        this.writeBoolean(this.s != null);
        if (this.s != null) {
            this.s.a((DataOutput)this);
        }
        this.writeInt(this.m);
        this.writeBoolean(this.t != null);
        if (this.m != -1 && this.t != null) {
            this.write(this.t);
        }
    }
    
    public void b() {
        super.b();
        final int int1 = this.readInt();
        if (int1 != 0) {
            this.n = new Vector(int1);
            for (int i = 0; i < int1; ++i) {
                this.n.addElement(this.readUTF());
            }
        }
        else {
            this.n = null;
        }
        final int int2 = this.readInt();
        if (int2 != 0) {
            this.o = new Vector(int2);
            for (int j = 0; j < int2; ++j) {
                this.o.addElement(this.readInt());
            }
        }
        else {
            this.o = null;
        }
        final int int3 = this.readInt();
        if (int3 != 0) {
            this.p = new Vector(int3);
            for (int k = 0; k < int3; ++k) {
                this.p.addElement(this.readInt());
            }
        }
        else {
            this.p = null;
        }
        final int int4 = this.readInt();
        if (int4 != 0) {
            this.q = new Vector(int4);
            for (int l = 0; l < int4; ++l) {
                this.q.addElement(this.readInt());
            }
        }
        else {
            this.p = null;
        }
        this.l = this.readInt();
        this.r = this.readInt();
        if (this.readBoolean()) {
            (this.s = com.daysofwonder.a.k.d().c()).a((DataInput)this);
        }
        this.m = this.readInt();
        if (this.m >= 0 && this.readBoolean()) {
            this.t = this.v();
        }
    }
    
    public Vector d() {
        return this.n;
    }
    
    public Vector e() {
        return this.o;
    }
    
    public Vector f() {
        return this.p;
    }
    
    public Vector g() {
        return this.q;
    }
    
    public int h() {
        return this.l;
    }
    
    public int j() {
        return this.m;
    }
    
    public int k() {
        return this.r;
    }
    
    public n l() {
        return this.s;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        this.a(sb);
        return sb.toString();
    }
    
    public StringBuffer a(final StringBuffer sb) {
        sb.append("[Table=");
        super.a(sb);
        sb.append(",tid=").append(this.l).append(",pid=").append(this.m).append(']');
        return sb;
    }
    
    public int i() {
        int i = super.i();
        i += 4;
        if (this.n != null) {
            final Enumeration<String> elements = this.n.elements();
            while (elements.hasMoreElements()) {
                i += com.daysofwonder.req.p.a(elements.nextElement());
            }
        }
        int n = i + (4 + 4 * ((this.o != null) ? this.o.size() : 0)) + (4 + 4 * ((this.p != null) ? this.p.size() : 0)) + (4 + 4 * ((this.q != null) ? this.q.size() : 0));
        n += 4;
        n += 4;
        ++n;
        if (this.s != null) {
            n += 4 + this.s.a(this);
        }
        n += 4;
        ++n;
        if (this.m != -1 && this.t != null) {
            n += 4 + this.t.length * 1;
        }
        return n;
    }
}
