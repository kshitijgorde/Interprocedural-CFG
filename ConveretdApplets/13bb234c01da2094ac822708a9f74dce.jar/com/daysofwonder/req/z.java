// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.req;

import java.util.Enumeration;
import java.util.Vector;

public class z extends k
{
    private int l;
    private Vector m;
    private Vector n;
    private Vector o;
    private int p;
    private String q;
    
    public z(final G g) {
        this.g = 102;
        this.a(g.t());
        this.b();
    }
    
    public void a() {
        super.a();
        this.writeInt(this.l);
        if (this.m != null) {
            this.writeInt(this.m.size());
            final Enumeration<Integer> elements = this.m.elements();
            while (elements.hasMoreElements()) {
                this.writeInt(elements.nextElement());
            }
        }
        else {
            this.writeInt(0);
        }
        if (this.n != null) {
            this.writeInt(this.n.size());
            final Enumeration<Integer> elements2 = this.n.elements();
            while (elements2.hasMoreElements()) {
                this.writeInt(elements2.nextElement());
            }
        }
        else {
            this.writeInt(0);
        }
        if (this.o != null) {
            this.writeInt(this.o.size());
            final Enumeration<Integer> elements3 = this.o.elements();
            while (elements3.hasMoreElements()) {
                this.writeInt(elements3.nextElement());
            }
        }
        else {
            this.writeInt(0);
        }
        this.writeInt(this.p);
        this.writeUTF(this.q);
    }
    
    public void b() {
        super.b();
        this.l = this.readInt();
        final int int1 = this.readInt();
        if (int1 != 0) {
            this.m = new Vector(int1);
            for (int i = 0; i < int1; ++i) {
                this.m.addElement(this.readInt());
            }
        }
        else {
            this.m = null;
        }
        final int int2 = this.readInt();
        if (int2 != 0) {
            this.n = new Vector(int2);
            for (int j = 0; j < int2; ++j) {
                this.n.addElement(this.readInt());
            }
        }
        else {
            this.n = null;
        }
        final int int3 = this.readInt();
        if (int3 != 0) {
            this.o = new Vector(int3);
            for (int k = 0; k < int3; ++k) {
                this.o.addElement(this.readInt());
            }
        }
        else {
            this.o = null;
        }
        this.p = this.readInt();
        this.q = this.readUTF();
    }
    
    public Vector d() {
        return this.n;
    }
    
    public Vector e() {
        return this.o;
    }
    
    public int i() {
        int i = super.i();
        i += 8;
        if (this.m != null) {
            i += 4 + 4 * this.m.size();
        }
        else {
            i += 4;
        }
        if (this.n != null) {
            i += 4 + 4 * this.n.size();
        }
        else {
            i += 4;
        }
        if (this.o != null) {
            i += 4 + 4 * this.o.size();
        }
        else {
            i += 4;
        }
        return i + com.daysofwonder.req.p.a(this.q);
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        this.a(sb);
        return sb.toString();
    }
    
    public StringBuffer a(final StringBuffer sb) {
        sb.append("[EndMatch=");
        super.a(sb);
        sb.append(",mwin=").append(this.l).append(']');
        return sb;
    }
    
    public String f() {
        return this.q;
    }
}
