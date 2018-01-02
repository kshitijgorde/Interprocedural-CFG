// 
// Decompiled by Procyon v0.5.30
// 

package a;

import neat.system.graphics.renderer.m;
import neat.system.cb;

public abstract class t implements cb
{
    private w a;
    protected a.y b;
    private m c;
    private nb d;
    
    public final w a() {
        return this.a;
    }
    
    protected void b() {
        if (this.d != null) {
            this.d.f();
            this.d = null;
        }
    }
    
    protected void c() {
        if (this.c != null) {
            this.b.a(this.c);
            this.c = null;
        }
        this.b();
    }
    
    public void g() {
    }
    
    public void h() {
        if (this.b != null) {
            this.b.a(this);
            this.b = null;
        }
        if (this.a != null) {
            this.a.a(this);
            this.a = null;
        }
    }
    
    public abstract void f();
    
    public t() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
    }
}
