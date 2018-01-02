import java.util.NoSuchElementException;

// 
// Decompiled by Procyon v0.5.30
// 

public final class m
{
    private c b;
    private c c;
    private c d;
    public int a;
    
    public m() {
        this.b = null;
        this.c = null;
        this.d = null;
        this.a = 0;
    }
    
    public final boolean a() {
        return this.e() != null;
    }
    
    public final void a(final Object o) {
        final c c = new c(o, null);
        if (this.b == null) {
            final c c2 = c;
            this.c = c2;
            this.b = c2;
        }
        else {
            this.c.b = c;
            this.c = c;
        }
        ++this.a;
    }
    
    public final Object b() {
        final c e;
        if ((e = this.e()) == null) {
            throw new NoSuchElementException();
        }
        if (this.c == e) {
            this.c = this.d;
        }
        if (this.d != null) {
            this.d.b = e.b;
        }
        else {
            this.b = e.b;
        }
        --this.a;
        return e.a;
    }
    
    public final void c() {
        c b;
        while ((b = this.b) != null) {
            this.b = b.b;
        }
        this.b = null;
        this.c = null;
        this.d = null;
        this.a = 0;
    }
    
    public final Object d() {
        final c b;
        if ((b = this.b) == null) {
            throw new NoSuchElementException();
        }
        this.b = this.b.b;
        --this.a;
        return b.a;
    }
    
    private c e() {
        if (this.d == null) {
            return this.b;
        }
        return this.d.b;
    }
}
