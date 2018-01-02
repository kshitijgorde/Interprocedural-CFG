// 
// Decompiled by Procyon v0.5.30
// 

public class dv
{
    private em a;
    private em b;
    private bs c;
    
    public dv(final bs c) {
        this.c = c;
    }
    
    public final void a(final em em) {
        if (em == this.a && em == this.b) {
            final em em2 = null;
            this.b = em2;
            this.a = em2;
        }
        else if (em == this.a) {
            this.a = em.a;
        }
        else if (em == this.b) {
            this.b = em.b;
        }
        else {
            final em b = em.b;
            final em a = em.a;
            b.a = em.a;
            a.b = em.b;
        }
        final em em3 = null;
        em.b = em3;
        em.a = em3;
        final bs c = this.c;
        c.c -= em.a();
        final bs c2 = this.c;
        ++c2.i;
        final bs c3 = this.c;
        --c3.d;
    }
    
    public final em b(final em em) {
        em a = null;
        if (this.c.b()) {
            a = this.a();
        }
        if (this.b == null) {
            this.b = em;
            this.a = em;
            final em em2 = null;
            em.b = em2;
            em.a = em2;
        }
        else {
            this.b.a = em;
            em.a = null;
            em.b = this.b;
            this.b = em;
        }
        final bs c = this.c;
        ++c.d;
        final bs c2 = this.c;
        c2.c += em.a();
        return a;
    }
    
    public final em a() {
        final em a = this.a;
        if (this.a == null && n.e()) {
            n.a(this.b == null, "Lost first element");
        }
        if (this.a != null) {
            if (this.a.a != null) {
                this.a.a.b = null;
            }
            this.a = this.a.a;
            if (this.a == null) {
                this.b = null;
            }
            final bs c = this.c;
            --c.d;
            final bs c2 = this.c;
            c2.c -= a.a();
            final bs c3 = this.c;
            ++c3.i;
        }
        if (a != null) {
            final em em = a;
            final em em2 = a;
            final em em3 = null;
            em2.b = em3;
            em.a = em3;
        }
        return a;
    }
    
    public final void b() {
        final bs c = this.c;
        c.i += this.c.d;
        final em em = null;
        this.b = em;
        this.a = em;
        this.c.d = 0;
        this.c.c = 0;
    }
}
