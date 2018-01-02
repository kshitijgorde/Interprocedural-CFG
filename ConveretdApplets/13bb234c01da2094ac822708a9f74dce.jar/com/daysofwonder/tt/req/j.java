// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.tt.req;

import com.daysofwonder.tt.o;
import com.daysofwonder.tt.f;
import com.daysofwonder.req.k;
import com.daysofwonder.util.G;

public class j
{
    private int a;
    private int b;
    private int c;
    private int d;
    private int[] e;
    private G f;
    private G g;
    private int h;
    
    public void a(final k k) {
        k.writeInt(this.a);
        k.writeInt(this.b);
        k.writeInt(this.c);
        k.writeInt(this.d);
        k.writeInt(this.e.length);
        for (int i = 0; i < this.e.length; ++i) {
            k.writeInt(this.e[i]);
        }
        k.writeInt(this.f.a());
        for (int j = 0; j < this.f.a(); ++j) {
            ((f)this.f.b(j)).a(k);
        }
        if (this.g != null) {
            k.writeInt(this.g.a());
            for (int l = 0; l < this.g.a(); ++l) {
                ((o)this.g.b(l)).a(k);
            }
        }
        else {
            k.writeInt(0);
        }
        k.writeInt(this.h);
    }
    
    public void b(final k k) {
        this.a = k.readInt();
        this.b = k.readInt();
        this.c = k.readInt();
        this.d = k.readInt();
        final int int1 = k.readInt();
        this.e = new int[int1];
        for (int i = 0; i < int1; ++i) {
            this.e[i] = k.readInt();
        }
        final int int2 = k.readInt();
        this.f = new G(int2);
        for (int j = 0; j < int2; ++j) {
            this.f.c((Object)(int)k.readByte());
        }
        final int int3 = k.readInt();
        this.g = new G(int3);
        for (int l = 0; l < int3; ++l) {
            this.g.c((Object)(int)k.readByte());
        }
        this.h = k.readInt();
    }
    
    public int a() {
        int n = 24 + this.e.length * 4 + this.f.a() * 1 + (4 + ((this.g != null) ? (this.g.a() * 1) : 0));
        n += 4;
        return n;
    }
    
    public int[] b() {
        return this.e;
    }
    
    public int c() {
        return this.a;
    }
    
    public int d() {
        return this.b;
    }
    
    public int e() {
        return this.c;
    }
    
    public int f() {
        return this.h;
    }
    
    public G g() {
        return this.f;
    }
    
    public G h() {
        return this.g;
    }
    
    public int i() {
        return this.d;
    }
}
