// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl.adt;

public final class Barcode
{
    public static final Object a;
    public static final Object b;
    private BarcodeNode c;
    private int d;
    private int e;
    
    public Barcode() {
        this.c = null;
        this.d = 0;
        this.e = 0;
    }
    
    public int a() {
        return this.e + this.d;
    }
    
    public int b() {
        return (this.c == null) ? this.d : (this.c.c() + this.d);
    }
    
    public int c() {
        return (this.c == null) ? 0 : this.c.b();
    }
    
    public int a(final Object o) {
        if (o == Barcode.a) {
            return this.b();
        }
        return this.c();
    }
    
    public void a(final int n, final Object o, final int n2) {
        if (o == Barcode.a) {
            this.a(n, n2);
        }
        else {
            this.b(n, n2);
        }
    }
    
    public void a(final int n, final int n2) {
        if (n2 < 0) {
            throw new IllegalStateException();
        }
        if (n2 == 0) {
            return;
        }
        if (this.c == null || n >= this.e) {
            this.d += n2;
        }
        else {
            this.c.c(n, n2);
            this.g();
        }
    }
    
    public void b(final int n, final int n2) {
        if (n2 < 0) {
            throw new IllegalArgumentException();
        }
        if (n2 == 0) {
            return;
        }
        if (this.c == null) {
            this.c = new BarcodeNode(this, null, n2, n);
            this.e = n + n2;
            this.d -= n;
        }
        else if (n >= this.e) {
            final int n3 = n - this.e;
            this.d -= n3;
            this.c.b(n2, n3);
            this.g();
        }
        else {
            this.c.a(n, n2);
            this.g();
        }
    }
    
    public Object a(final int n) {
        if (this.c(n) == -1) {
            return Barcode.a;
        }
        return Barcode.b;
    }
    
    public void b(final int n, final Object o, int n2) {
        if (n2 < 1) {
            throw new IllegalArgumentException();
        }
        final int n3 = (n > this.e - 1) ? n2 : (n + n2 - this.e);
        if (n3 > 0) {
            if (o == Barcode.b) {
                this.d -= n3;
                this.b(n, n3);
            }
            n2 -= n3;
            if (n2 == 0) {
                return;
            }
        }
        if (this.c != null) {
            this.c.a(n, o, n2);
            if (this.c != null) {
                this.g();
            }
        }
    }
    
    public void c(final int n, final int n2) {
        this.b(n, Barcode.a, n2);
    }
    
    public void d(final int n, final int n2) {
        this.b(n, Barcode.b, n2);
    }
    
    public void e(final int n, int i) {
        if (i < 1) {
            throw new IllegalArgumentException();
        }
        final int n2 = (n > this.e) ? i : (n + i - this.e);
        if (n2 > 0) {
            this.d -= n2;
            i -= n2;
        }
        if (this.c != null && n < this.e) {
            while (i > 0) {
                final int e = this.e;
                this.c.d(n, i);
                if (this.c != null) {
                    this.g();
                }
                i -= e - this.e;
            }
            if (this.c != null) {
                this.g();
            }
        }
    }
    
    public void d() {
        this.e = 0;
        this.d = 0;
        this.c = null;
    }
    
    BarcodeNode e() {
        return this.c;
    }
    
    void a(final BarcodeNode c) {
        this.c = c;
        if (c == null) {
            this.e = 0;
        }
    }
    
    int f() {
        return this.e;
    }
    
    void g() {
        this.e = this.c.a();
    }
    
    public int a(final int n, final Object o) {
        if (o != Barcode.a) {
            return this.c.d(n);
        }
        if (this.c == null) {
            return n;
        }
        if (n >= this.c.c()) {
            return n - this.c.c() + this.e;
        }
        return this.c.c(n);
    }
    
    public int b(final int n, final Object o) {
        if (o == Barcode.a) {
            return this.b(n);
        }
        return this.c(n);
    }
    
    public int b(final int n) {
        if (this.c != null && n < this.e) {
            return this.c.a(n);
        }
        if (this.c != null) {
            return n - this.e + this.c.c();
        }
        return n;
    }
    
    public int c(final int n) {
        if (this.c != null && n < this.e) {
            return this.c.b(n);
        }
        return -1;
    }
    
    public BarcodeIterator h() {
        return new BarcodeIterator(this);
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        final BarcodeIterator h = this.h();
        while (h.hasNext()) {
            sb.append((h.next() == Barcode.b) ? "X" : "_");
        }
        return sb.toString();
    }
    
    static {
        a = Boolean.FALSE;
        b = Boolean.TRUE;
    }
}
