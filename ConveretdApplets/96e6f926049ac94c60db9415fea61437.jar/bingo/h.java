// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.system.cb;

public abstract class h implements cb
{
    protected s a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    public static int g;
    
    public void a(final int b) {
        this.b = b;
        this.c = 0;
        this.d();
    }
    
    public int a() {
        return this.b;
    }
    
    public g b() {
        return this.a.f(this.b);
    }
    
    public void b(int c) {
        if (c < 0) {
            c = 0;
        }
        final int e = this.b().e();
        if (c >= e) {
            c = e - 1;
        }
        if (this.c == c) {
            return;
        }
        this.c = c;
        this.d();
    }
    
    public int c() {
        return this.c;
    }
    
    protected void d() {
        final g f = this.a.f(this.b);
        this.d = f.b(this.c);
        this.e = f.c(this.c);
        this.f = f.d(this.c);
    }
    
    public int e() {
        return this.d;
    }
    
    public int f() {
        return this.e;
    }
    
    public int g() {
        return this.f;
    }
    
    public void g() {
        this.b = -1;
        this.c = -1;
        this.d = -1;
        this.e = -1;
        this.f = -1;
    }
    
    public void h() {
        this.a = null;
    }
    
    public abstract void f();
    
    public h() {
        this.a = null;
    }
}
