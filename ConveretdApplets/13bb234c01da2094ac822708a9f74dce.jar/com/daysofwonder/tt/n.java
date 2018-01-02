// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.tt;

import org.slf4j.LoggerFactory;
import com.daysofwonder.a.j;
import com.daysofwonder.req.k;
import com.daysofwonder.util.y;
import com.daysofwonder.a.o;
import com.daysofwonder.util.G;
import org.slf4j.Logger;

public class n implements Cloneable
{
    private static final Logger c;
    protected G a;
    protected G b;
    
    public n() {
        this.a = new G();
        this.b = new G();
    }
    
    public Object clone() {
        try {
            final n n = (n)super.clone();
            n.a = (G)this.a.clone();
            return n;
        }
        catch (CloneNotSupportedException ex) {
            n.c.a("Clone error", ex);
            throw new InternalError();
        }
    }
    
    public G a() {
        return this.a;
    }
    
    public synchronized void a(final o o) {
        this.a.c(o);
    }
    
    public synchronized o b(final o o) {
        if (this.a.e(o)) {
            return o;
        }
        return null;
    }
    
    public synchronized void a(final n n) {
        final y c = n.c();
        while (c.a()) {
            this.a((o)c.b());
        }
    }
    
    public synchronized void a(final n n, final int n2) {
        int n3 = 0;
        for (y c = n.c(); c.a() && n3 < n2; ++n3) {
            this.a((o)c.b());
        }
    }
    
    public synchronized void b() {
        this.a.b();
    }
    
    public y c() {
        return new q(this);
    }
    
    public int d() {
        return this.a.a();
    }
    
    public boolean c(final o o) {
        return this.a.a(o);
    }
    
    public o a(final int n) {
        return (o)this.a.b(n);
    }
    
    public void a(final k k) {
        this.a.b();
        this.b.b();
        int int1 = k.readInt();
        while (--int1 >= 0) {
            this.b.c((Object)(int)k.readShort());
        }
    }
    
    public void b(final k k) {
        k.writeInt(this.a.a());
        final y c = this.a.c();
        while (c.a()) {
            k.writeShort(((o)c.b()).g());
        }
    }
    
    public void a(final j j) {
        this.a.b();
        for (int i = 0; i < this.b.a(); ++i) {
            this.a.c(j.c((int)this.b.b(i)));
        }
        this.b.b();
    }
    
    public int e() {
        return 4 + this.a.a() * 2;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        final y c = this.a.c();
        while (c.a()) {
            final o o = (o)c.b();
            if (o != null) {
                sb.append(o.f());
            }
            else {
                sb.append("(NULL)");
            }
            sb.append(',');
        }
        return sb.toString();
    }
    
    public int f() {
        int n = 0;
        for (int i = 0; i < this.a.a(); ++i) {
            final i j = (i)this.a.b(i);
            if (j != null && j.c()) {
                ++n;
            }
        }
        return n;
    }
    
    public int g() {
        int n = 0;
        for (int i = 0; i < this.a.a(); ++i) {
            final i j = (i)this.a.b(i);
            if (j != null && j.d()) {
                ++n;
            }
        }
        return n;
    }
    
    public i h() {
        i i = null;
        for (int j = 0; j < this.a.a(); ++j) {
            final i k = (i)this.a.b(j);
            if (k != null) {
                if (i == null && !k.c() && !k.d()) {
                    i = k;
                }
                else if (i != null && k.b() != i.b() && !k.c() && !k.d()) {
                    return null;
                }
            }
        }
        if (i != null) {
            return i;
        }
        int n = 0;
        int n2 = 0;
        for (int l = 0; l < this.a.a(); ++l) {
            final i m = (i)this.a.b(l);
            if (m != null && m.c()) {
                i = m;
                ++n;
            }
        }
        if (n == this.a.a()) {
            return i;
        }
        for (int n3 = 0; n3 < this.a.a(); ++n3) {
            final i i2 = (i)this.a.b(n3);
            if (i2 != null && i2.d()) {
                i = i2;
                ++n2;
            }
        }
        if (n2 == this.a.a()) {
            return i;
        }
        return null;
    }
    
    static {
        c = LoggerFactory.a(n.class);
    }
}
