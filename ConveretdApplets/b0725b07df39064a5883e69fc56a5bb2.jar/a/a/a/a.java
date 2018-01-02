// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

final class a implements h
{
    private i a;
    private static int b;
    private int c;
    private char d;
    private char e;
    private char f;
    private char g;
    private char h;
    private char i;
    private int j;
    private k k;
    private p l;
    
    public a() {
        this.j = 0;
        this.k = null;
        this.l = null;
        this.a = new i();
        this.b();
    }
    
    public final void a(final k k) {
        this.k = k;
    }
    
    public final void a(final p l) {
        this.l = l;
    }
    
    public final void a(final int n) {
        if (this.j > 0) {
            this.j -= n;
            if (this.j <= 0) {
                this.j = 0;
                if (this.k != null) {
                    a.a.a.k.d();
                }
            }
        }
    }
    
    private void a(final boolean b) {
        if (b) {
            this.l.a(b.f);
            return;
        }
        this.l.a(b.g);
    }
    
    public final boolean a() {
        this.b();
        return true;
    }
    
    public final boolean b() {
        this.j = 0;
        this.c = a.a.a.a.b;
        this.a.b();
        this.d = '\0';
        this.e = '\0';
        this.f = '\0';
        this.g = '\0';
        this.h = '\0';
        this.i = '\0';
        return true;
    }
    
    public final char a(final char c) {
        char c2 = '\u00ff';
        switch (c & '\u000f') {
            case '\0': {
                c2 = (char)this.a.d();
                break;
            }
            case '\u0001': {
                c2 = (char)this.a.c();
                break;
            }
            case '\u0004': {
                final a a = this = this;
                final int c3 = a.c - 1;
                a.c = c3;
                if (c3 <= 0) {
                    this.c = a.a.a.a.b;
                    final a a2 = this;
                    a2.d ^= '\u0004';
                }
                int n = this.d & '\u00ff';
                if (this.a.a()) {
                    n |= 0x1;
                }
                c2 = (char)n;
                break;
            }
            case '\u0005': {
                this = this;
                c2 = '\0';
                break;
            }
            case '\u0006': {
                c2 = (this = this).f;
                break;
            }
            case '\u0007': {
                c2 = (this = this).g;
                break;
            }
            case '\b': {
                c2 = (this = this).h;
                break;
            }
            case '\t': {
                c2 = (this = this).i;
                break;
            }
        }
        return c2;
    }
    
    public final void a(final char c, final char i) {
        switch (c & '\u000f') {
            case '\u0001': {
                this.a.a(i);
            }
            case '\u0006': {
                boolean b = false;
                this.f = i;
                if ((this.f & '\u0003') == '\u0003') {
                    this.a.b();
                    b = true;
                }
                if (b) {
                    int a = 3579545;
                    if (this.k != null) {
                        a = this.k.a();
                    }
                    this.j = a >> 16;
                }
                this.a(this.c());
            }
            case '\u0007': {
                final char c2 = (char)(i & '\u00ff');
                this.g = c2;
                if ((i & '\u0080') == '\0') {
                    final char c3 = (char)(1 << (c2 >> 1 & '\u0007'));
                    if ((i & '\u0001') == '\u0001') {
                        this.f |= c3;
                    }
                    else {
                        this.f &= (char)~c3;
                    }
                    if ((i & '\u0004') == '\u0004') {
                        this.a.b();
                        this.a.a(true);
                    }
                }
                this.a(this.c());
            }
            case '\b': {
                this.h = i;
            }
            case '\t': {
                this.i = i;
                break;
            }
        }
    }
    
    public final void a(final int n, final byte[] array) {
        this.a.a(0, array);
    }
    
    private boolean c() {
        return (this.f & '@') == '\0';
    }
    
    static {
        a.b = 12800;
    }
}
