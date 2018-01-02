// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

final class i
{
    private d a;
    private d b;
    private d c;
    
    i() {
        this.a = null;
        this.b = null;
        this.c = null;
    }
    
    public final synchronized void a(final Object o) {
        if (this.a == null) {
            final d d = new d(o, null);
            this.b = d;
            this.a = d;
            return;
        }
        final d b = this.b;
        final d d2 = new d(o, null);
        b.b = d2;
        this.b = d2;
    }
    
    public final synchronized void b(final Object o) {
        if (this.a == null) {
            return;
        }
        if (this.a.a == o) {
            this.a = this.a.b;
            return;
        }
        for (d b = this.a; b.b != null; b = b.b) {
            if (b.b.a == o) {
                if (b.b == this.b) {
                    this.b = b;
                }
                b.b = b.b.b;
                return;
            }
        }
    }
    
    public final synchronized Object a() {
        if (this.a == null) {
            return null;
        }
        return this.a.a;
    }
    
    public final synchronized Object b() {
        if (this.a == null) {
            return null;
        }
        this.c = this.a.b;
        return this.a.a;
    }
    
    public final synchronized Object c() {
        if (this.c == null) {
            return null;
        }
        final Object a = this.c.a;
        this.c = this.c.b;
        return a;
    }
}
