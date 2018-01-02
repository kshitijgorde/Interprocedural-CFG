// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

final class av
{
    boolean a;
    boolean b;
    boolean c;
    bC d;
    av e;
    av f;
    private final J g;
    
    av(final J g, final bC d) {
        this.g = g;
        this.a = false;
        this.b = false;
        this.c = true;
        this.e = null;
        this.f = null;
        this.d = d;
    }
    
    final void a() {
        this.b = false;
        if (this.a) {
            return;
        }
        this.a = true;
        synchronized (this.g.a) {
            if (!this.c) {
                return;
            }
            this.e.f = this.f;
            this.f.e = this.e;
            this.e = this.g.a[this.g.b];
            this.f = this.g.a[this.g.b].f;
            this.f.e = this;
            this.e.f = this;
        }
    }
    
    final void b() {
        if (this.c) {
            this.b = true;
        }
    }
    
    final void c() {
        this.c = false;
        this.a = false;
        this.b = false;
        synchronized (this.g.a) {
            if (this.f == null) {
                return;
            }
            this.e.f = this.f;
            this.f.e = this.e;
            this.f = null;
        }
    }
}
