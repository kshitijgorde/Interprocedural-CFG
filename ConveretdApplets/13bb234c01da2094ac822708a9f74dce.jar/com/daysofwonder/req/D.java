// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.req;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import com.daysofwonder.a.a;

public class D
{
    private String a;
    private int b;
    private int c;
    private float d;
    private int e;
    private boolean f;
    private int g;
    private int h;
    private boolean i;
    private int j;
    private boolean k;
    private int l;
    private a m;
    private Map n;
    private boolean o;
    
    int a() {
        int n = 0;
        n += 20;
        n += 2;
        n += 4;
        int n2 = n + p.a(this.a);
        n2 += 4;
        int n3 = ++n2 + this.m.c() + this.n.size() * 4;
        final Iterator<String> iterator = this.n.keySet().iterator();
        while (iterator.hasNext()) {
            n3 += p.a(iterator.next());
        }
        final int n4 = n3;
        n3 += 4;
        int n5 = n4 + n3;
        n5 += 4;
        return n5;
    }
    
    void a(final k k) {
        k.writeUTF(this.a);
        k.writeInt(this.b);
        k.writeInt(this.c);
        k.writeFloat(this.d);
        k.writeInt(this.e);
        k.writeBoolean(this.f);
        k.writeInt(this.g);
        k.writeInt(this.h);
        k.writeBoolean(this.i);
        k.writeInt(this.j);
        k.writeBoolean(this.k);
        k.writeInt(this.l);
        this.m.a(k);
        k.writeInt(this.n.size());
        for (final String s : this.n.keySet()) {
            k.writeUTF(s);
            k.writeInt((int)this.n.get(s));
        }
        k.writeBoolean(this.o);
    }
    
    static D b(final k k) {
        final D d = new D();
        d.a = k.readUTF();
        d.b = k.readInt();
        d.c = k.readInt();
        d.d = k.readFloat();
        d.e = k.readInt();
        d.f = k.readBoolean();
        d.g = k.readInt();
        d.h = k.readInt();
        d.i = k.readBoolean();
        d.j = k.readInt();
        d.k = k.readBoolean();
        d.l = k.readInt();
        (d.m = com.daysofwonder.a.k.d().b()).b(k);
        final int int1 = k.readInt();
        d.n = new HashMap();
        for (int i = 0; i < int1; ++i) {
            d.n.put(k.readUTF(), k.readInt());
        }
        d.o = k.readBoolean();
        return d;
    }
    
    public String b() {
        return this.a;
    }
    
    public int c() {
        return this.b;
    }
    
    public int d() {
        return this.c;
    }
    
    public float e() {
        return this.d;
    }
    
    public int f() {
        return this.e;
    }
    
    public boolean g() {
        return this.f;
    }
    
    public int h() {
        return this.g;
    }
    
    public boolean i() {
        return this.i;
    }
    
    public int j() {
        return this.j;
    }
    
    public boolean k() {
        return this.k;
    }
    
    public a l() {
        return this.m;
    }
    
    public int m() {
        return this.l;
    }
    
    public Map n() {
        return this.n;
    }
    
    public boolean o() {
        return this.o;
    }
}
