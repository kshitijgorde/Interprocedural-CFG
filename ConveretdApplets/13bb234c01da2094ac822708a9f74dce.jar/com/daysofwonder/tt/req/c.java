// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.tt.req;

import com.daysofwonder.tt.o;
import com.daysofwonder.req.k;
import com.daysofwonder.util.G;

public class c
{
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private boolean g;
    private G h;
    private G i;
    
    public void a(final k k) {
        k.writeInt(this.a);
        k.writeInt(this.b);
        k.writeInt(this.c);
        k.writeInt(this.d);
        k.writeInt(this.e);
        k.writeInt(this.f);
        k.writeInt(this.h.a());
        for (int i = 0; i < this.h.a(); ++i) {
            k.writeByte(((o)this.h.b(i)).g());
        }
        k.writeByte(this.i.a());
        for (int j = 0; j < this.i.a(); ++j) {
            k.writeByte(((com.daysofwonder.tt.c)this.i.b(j)).f());
        }
        k.writeBoolean(this.g);
    }
    
    public void b(final k k) {
        this.a = k.readInt();
        this.b = k.readInt();
        this.c = k.readInt();
        this.d = k.readInt();
        this.e = k.readInt();
        this.f = k.readInt();
        final int int1 = k.readInt();
        this.h = new G(int1);
        for (int i = 0; i < int1; ++i) {
            this.h.c((Object)(int)k.readByte());
        }
        final byte byte1 = k.readByte();
        this.i = new G(byte1);
        for (byte b = 0; b < byte1; ++b) {
            this.i.c((Object)(int)k.readByte());
        }
        this.g = k.readBoolean();
    }
    
    public int a() {
        int n = 28 + this.h.a() * 1 + this.i.a() * 1;
        return ++n;
    }
    
    public G b() {
        return this.h;
    }
    
    public G c() {
        return this.i;
    }
    
    public int d() {
        return this.a;
    }
    
    public int e() {
        return this.b;
    }
    
    public int f() {
        return this.c;
    }
    
    public int g() {
        return this.d;
    }
    
    public int h() {
        return this.e;
    }
    
    public int i() {
        return this.f;
    }
    
    public boolean j() {
        return this.g;
    }
}
