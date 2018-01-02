// 
// Decompiled by Procyon v0.5.30
// 

public class bi
{
    private z a;
    private av[] b;
    private int c;
    private av[] d;
    
    public bi() {
        this.c = -1;
    }
    
    public static void a(final av[] d, final z a) {
        final bi bi = new bi();
        bi.d = d;
        bi.a = a;
        if (bi.a()) {
            return;
        }
        bi.b = new av[d.length];
        bi.c = -1;
        bi.b();
        bi.c();
    }
    
    private boolean a() {
        for (int i = 1; i < this.d.length; ++i) {
            if (this.a.a(this.d[i], this.d[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }
    
    private void b() {
        for (int i = this.d.length - 1; i >= 0; --i) {
            final int n = ++this.c;
            this.b[n] = this.d[i];
            this.a(n);
        }
    }
    
    private void c() {
        for (int i = this.b.length - 1; i >= 0; --i) {
            this.d[i] = this.b[0];
            this.b(0);
        }
    }
    
    private void a(int i) {
        final av av = this.b[i];
        while (i > 0) {
            final int n = (i - 1) / 2;
            final av av2 = this.b[n];
            if (this.a.a(av2, av) >= 0) {
                break;
            }
            this.b[i] = av2;
            i = n;
        }
        this.b[i] = av;
    }
    
    private void b(int i) {
        while (i < this.c) {
            final int n = i * 2 + 1;
            if (n > this.c) {
                this.c(i);
                break;
            }
            final av av = this.b[n];
            final int n2 = n + 1;
            if (n2 > this.c) {
                this.b[i] = av;
                i = n;
                this.c(i);
                break;
            }
            final av av2 = this.b[n2];
            if (this.a.a(av, av2) < 0) {
                this.b[i] = av2;
                i = n2;
            }
            else {
                this.b[i] = av;
                i = n;
            }
        }
        --this.c;
    }
    
    private void c(final int n) {
        if (n < this.c) {
            this.b[n] = this.b[this.c];
            this.a(n);
        }
    }
}
