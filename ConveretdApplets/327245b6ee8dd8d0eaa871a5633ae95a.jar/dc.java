import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

class dc
{
    de a;
    de b;
    de c;
    int d;
    Hashtable e;
    private final db f;
    
    private dc(final db f) {
        this.f = f;
        this.d = 0;
        this.e = new Hashtable(500, 0.75f);
    }
    
    void a(final dd a) {
        if (this.e.get(a) != null) {
            return;
        }
        final de b = new de(this.f, null);
        if (this.a == null) {
            this.a = b;
        }
        if (this.b != null) {
            this.b.b = b;
        }
        b.a = a;
        b.c = this.b;
        this.b = b;
        this.e.put(a, b);
        ++this.d;
    }
    
    void b(final dd dd) {
        this.c = this.e.get(dd);
        if (this.c == null) {
            return;
        }
        this.e.remove(dd);
        if (this.c.b != null) {
            this.c.b.c = this.c.c;
        }
        if (this.c.c != null) {
            this.c.c.b = this.c.b;
        }
        if (this.c == this.a) {
            this.a = this.a.b;
        }
        if (this.c == this.b) {
            this.b = this.b.c;
        }
        this.c.b = null;
        this.c.c = null;
        this.c = this.a;
        --this.d;
    }
    
    dc(final db db, final dy dy) {
        this(db);
    }
}
