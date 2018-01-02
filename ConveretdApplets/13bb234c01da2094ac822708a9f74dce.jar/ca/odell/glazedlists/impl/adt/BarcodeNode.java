// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl.adt;

final class BarcodeNode
{
    BarcodeNode a;
    private Barcode f;
    BarcodeNode b;
    BarcodeNode c;
    private int g;
    private int h;
    private int i;
    private int j;
    int d;
    int e;
    private int k;
    
    private BarcodeNode(final Barcode f, final BarcodeNode a) {
        this.b = null;
        this.c = null;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.d = 0;
        this.e = 1;
        this.k = 1;
        this.f = f;
        this.a = a;
    }
    
    BarcodeNode(final Barcode barcode, final BarcodeNode barcodeNode, final int e, final int d) {
        this(barcode, barcodeNode);
        this.d = d;
        this.e = e;
    }
    
    int a() {
        return this.i + this.d + this.e + this.j;
    }
    
    int b() {
        return this.g + this.e + this.h;
    }
    
    int c() {
        return this.i - this.g + this.d + (this.j - this.h);
    }
    
    void a(final int n, final int n2) {
        final int n3 = n - this.i;
        if (n3 < 0) {
            this.g += n2;
            this.i += n2;
            this.b.a(n, n2);
        }
        else if (n3 > this.d + this.e) {
            this.h += n2;
            this.j += n2;
            this.c.a(n3 - this.d - this.e, n2);
        }
        else if (n3 == this.d + this.e) {
            this.e += n2;
        }
        else if (n3 < this.d) {
            this.d -= n3;
            this.g += n2;
            this.i += n3 + n2;
            if (this.b == null) {
                this.b = new BarcodeNode(this.f, this, n2, n3);
                this.g();
            }
            else {
                this.b.b(n2, n3);
            }
        }
        else {
            this.e += n2;
        }
    }
    
    void b(final int n, final int n2) {
        if (this.c != null) {
            this.h += n;
            this.j += n + n2;
            this.c.b(n, n2);
        }
        else if (n2 == 0) {
            this.e += n;
        }
        else {
            this.h += n;
            this.j += n + n2;
            this.c = new BarcodeNode(this.f, this, n, n2);
            this.g();
        }
    }
    
    void c(final int n, final int n2) {
        final int n3 = n - this.i;
        if (n3 < 0) {
            this.i += n2;
            this.b.c(n, n2);
        }
        else if (n3 > this.d + this.e - 1) {
            this.j += n2;
            this.c.c(n3 - this.d - this.e, n2);
        }
        else if (n3 <= this.d) {
            this.d += n2;
        }
        else {
            final int e = n3 - this.d;
            final int n4 = this.e - e;
            this.e = e;
            this.h += n4;
            this.j += n4 + n2;
            if (this.c == null) {
                this.c = new BarcodeNode(this.f, this, n4, n2);
                this.g();
            }
            else {
                this.c.a(new BarcodeNode(this.f, null, n4, n2));
            }
        }
    }
    
    private void a(final BarcodeNode b) {
        if (this.b != null) {
            this.g += b.e;
            this.i += b.d + b.e;
            this.b.a(b);
        }
        else if (this.d == 0) {
            this.e += b.e;
            this.d += b.d;
            b.f();
        }
        else {
            this.g += b.e;
            this.i += b.d + b.e;
            b.a = this;
            this.b = b;
            this.g();
        }
    }
    
    int a(final int n) {
        return this.f(n, 0);
    }
    
    private int f(final int n, int n2) {
        final int n3 = n - this.i;
        if (n3 < 0) {
            return this.b.f(n, n2);
        }
        if (n3 > this.d + this.e - 1) {
            n2 += this.i - this.g + this.d;
            return this.c.f(n3 - this.d - this.e, n2);
        }
        if (n3 < this.d) {
            return n2 + (this.i - this.g) + n3;
        }
        return -1;
    }
    
    int b(final int n) {
        return this.g(n, 0);
    }
    
    private int g(final int n, final int n2) {
        final int n3 = n - this.i;
        if (n3 < 0) {
            return this.b.g(n, n2);
        }
        if (n3 > this.d + this.e - 1) {
            return this.c.g(n3 - this.d - this.e, n2 + this.g + this.e);
        }
        if (n3 < this.d) {
            return -1;
        }
        return n2 + this.g + n3 - this.d;
    }
    
    public int c(final int n) {
        final int n2 = n - (this.i - this.g);
        if (n2 < 0) {
            return this.b.c(n);
        }
        if (n2 >= this.d) {
            return this.c.c(n2 - this.d) + this.i + this.d + this.e;
        }
        return this.i + n2;
    }
    
    public int d(final int n) {
        final int n2 = n - this.g;
        if (n2 < 0) {
            return this.b.d(n);
        }
        if (n2 >= this.e) {
            return this.c.d(n2 - this.e) + this.i + this.d + this.e;
        }
        return this.i + this.d + n2;
    }
    
    void a(final int n, final Object o, final int n2) {
        if (n2 == 1) {
            this.a(n, n, o);
        }
        else {
            this.a(n, n, o, n2);
        }
    }
    
    private void a(final int n, final int n2, final Object o, final int n3) {
        final int n4 = n2 - this.i;
        if (n4 < 0) {
            this.b.a(n, n2, o, n3);
        }
        else if (n4 > this.d + this.e - 1) {
            this.c.a(n, n4 - this.d - this.e, o, n3);
        }
        else if (o == Barcode.a) {
            this.a(n, n4, n3);
        }
        else {
            this.b(n, n4, n3);
        }
    }
    
    void a(final int n, final int n2, final int n3) {
        final int n4 = n2 + n3 - 1;
        if (n4 >= this.d) {
            if (n2 > this.d - 1) {
                final int min = Math.min(n3, this.d + this.e - n2);
                if (this.e == min) {
                    this.d += min;
                    this.i(-min, this.e = 0);
                    this.e(n - n2);
                }
                else {
                    this.e -= min;
                    if (n2 < this.d + this.e) {
                        this.i(-min, 0);
                        this.c(n2 + this.i, min);
                    }
                    else {
                        this.i(-min, -min);
                        this.f.a(n, min);
                    }
                }
                if (min != n3) {
                    this.f.e(n + min, n3 - min);
                    this.f.a(n + min, n3 - min);
                }
            }
            else if (n2 < this.d + 1 && n4 < this.d + this.e) {
                final int n5 = Math.min(n3, this.d + this.e - n2) + (n2 - this.d);
                this.e -= n5;
                this.d += n5;
                this.i(-n5, 0);
            }
            else {
                this.d += this.e;
                final int n6 = this.d - n2;
                this.e(n - n2);
                if (n6 != n3) {
                    this.f.e(n + n6, n3 - n6);
                    this.f.a(n + n6, n3 - n6);
                }
            }
        }
    }
    
    void b(final int n, final int n2, final int n3) {
        final int n4 = n2 + n3 - 1;
        final int min = Math.min(n3, this.d + this.e - n2);
        if (n2 <= this.d - 1) {
            if (n4 > this.d - 1) {
                final int n5 = this.d - n2;
                this.e += n5;
                this.d -= n5;
                this.i(n5, 0);
                this.f(n - n2);
            }
            else {
                this.d -= n3;
                this.i(0, -n3);
                this.f.b(n, n3);
                this.f(n - n2);
            }
        }
        if (min != n3) {
            this.f.e(n + min, n3 - min);
            this.f.b(n + min, n3 - min);
        }
    }
    
    private void a(final int n, final int n2, final Object o) {
        final int n3 = n2 - this.i;
        if (n3 < 0) {
            this.b.a(n, n2, o);
        }
        else if (n3 > this.d + this.e) {
            this.c.a(n, n3 - this.d - this.e, o);
        }
        else if (n3 == this.d + this.e) {
            if (o != Barcode.a) {
                ++this.e;
                --this.j;
                this.i(1, 0);
                this.c.h(n, n3 - this.d - this.e + 1);
            }
        }
        else if (n3 < this.d) {
            if (o == Barcode.a) {
                return;
            }
            --this.d;
            this.i(1, 0);
            this.a(n2, 1);
            this.f(n);
        }
        else if (n3 == this.d) {
            if (o == Barcode.a) {
                ++this.d;
                --this.e;
                this.i(-1, 0);
                if (this.e == 0) {
                    this.e(n - n3);
                }
            }
        }
        else if (n3 == this.d + this.e - 1) {
            if (o == Barcode.a) {
                --this.e;
                if (this.c != null) {
                    ++this.j;
                    this.c.c(n3 - this.d - this.e, 1);
                    this.i(-1, 0);
                }
                else if (this.a != null && this.a.b == this) {
                    final BarcodeNode a = this.a;
                    ++a.d;
                    final BarcodeNode a2 = this.a;
                    --a2.i;
                    this.a.a(true, -1, 0);
                }
                else {
                    this.i(-1, -1);
                    this.f.a(n, 1);
                }
            }
        }
        else if (o == Barcode.a) {
            --this.e;
            this.i(-1, 0);
            this.c(n2, 1);
        }
    }
    
    private void h(final int n, final int n2) {
        final int n3 = n2 - this.i;
        if (n3 < 0) {
            --this.i;
            this.b.h(n, n2);
        }
        else if (n3 > this.d + this.e - 1) {
            --this.j;
            this.c.h(n, n3 - this.d - this.e);
        }
        else {
            --this.d;
            this.f(n);
        }
    }
    
    void d(final int n, final int n2) {
        if (n2 == 1) {
            this.e(n, n);
        }
        else {
            this.c(n, n, n2);
        }
    }
    
    private void c(final int n, final int n2, int n3) {
        final int n4 = n2 - this.i;
        if (n4 < 0) {
            this.b.c(n, n2, n3);
        }
        else if (n4 > this.d + this.e - 1) {
            this.c.c(n, n4 - this.d - this.e, n3);
        }
        else {
            n3 = Math.min(n4 + n3, this.d + this.e) - n4;
            final int n5 = n4 + n3 - 1;
            if (n4 < this.d && n5 < this.d + this.e) {
                final int min = Math.min(this.d - n4, n3);
                final int max = Math.max(n5 - this.d + 1, 0);
                this.d -= min;
                this.e -= max;
                this.i(-max, -(min + max));
                this.f(n - n4);
            }
            else if (n4 > this.d - 1) {
                if (n3 == this.e) {
                    this.e(n - n4);
                }
                else {
                    this.e -= n3;
                    this.i(-n3, -n3);
                }
            }
            else {
                final int d = this.d;
                final int e = this.e;
                this.d = 0;
                this.e = 0;
                this.i(-e, -(d + e));
                this.e(n - n4);
            }
        }
    }
    
    void e(final int n, final int n2) {
        final int n3 = n2 - this.i;
        if (n3 < 0) {
            --this.i;
            this.b.e(n, n2);
        }
        else if (n3 > this.d + this.e - 1) {
            --this.j;
            this.c.e(n, n3 - this.d - this.e);
        }
        else if (n3 < this.d) {
            --this.d;
            this.f(n);
        }
        else {
            --this.e;
            if (this.e == 0) {
                this.e = 1;
                this.a(n - n3, false);
            }
            else {
                this.i(-1, 0);
            }
        }
    }
    
    private void e(final int n) {
        this.a(n, true);
    }
    
    private void a(final int n, final boolean b) {
        if (this.c != null && this.b != null) {
            if (this.e != 0) {
                this.a(-this.e, -this.e, b);
            }
            this.d();
        }
        else if (this.c != null) {
            this.a(b);
        }
        else {
            BarcodeNode b2;
            if (this.b != null) {
                b2 = this.b;
                b2.a = this.a;
            }
            else {
                b2 = null;
            }
            if (this.a == null) {
                this.f.a(b2);
                if (this.d != 0) {
                    this.f.a(this.f.a() + 1, this.d);
                }
            }
            else if (this.a.b == this) {
                final BarcodeNode a = this.a;
                a.d += this.d;
                final BarcodeNode a2 = this.a;
                a2.i -= this.d;
                this.a.b = b2;
                this.a.g();
                if (this.e != 0) {
                    this.a.a(true, -this.e, -this.e, b);
                }
                this.f();
            }
            else {
                this.a.c = b2;
                this.a.g();
                if (this.d != 0) {
                    this.a.a(false, -this.e, -(this.d + this.e), b);
                    this.f.a(n, this.d);
                }
                else if (this.e != 0) {
                    this.a.a(false, -this.e, -this.e, b);
                }
                this.f();
            }
        }
    }
    
    private void d() {
        final BarcodeNode e = this.c.e();
        final BarcodeNode a = e.a;
        this.d += e.d;
        this.e = e.e;
        this.j -= e.d + e.e;
        this.h -= e.e;
        if (a == this) {
            this.c = e.c;
            if (this.c != null) {
                this.c.a = this;
            }
            this.g();
        }
        else {
            a.b = e.c;
            if (a.b != null) {
                a.b.a = a;
            }
            a.g();
        }
        e.f();
    }
    
    private void a(final boolean b) {
        this.d += this.c.d;
        final int e = this.e;
        this.e = this.c.e;
        this.c.f();
        this.c = null;
        this.h = 0;
        this.j = 0;
        this.k = 1;
        if (this.a != null) {
            if (e != 0) {
                this.a.a(this.a.b == this, -e, -e, b);
            }
            this.a.g();
        }
    }
    
    private BarcodeNode e() {
        if (this.b != null) {
            final BarcodeNode e = this.b.e();
            this.g -= e.e;
            this.i -= e.d + e.e;
            return e;
        }
        return this;
    }
    
    private void a(final int n, final int n2, final boolean b) {
        if (b) {
            this.i(n, n2);
        }
        else {
            this.i(-1, n2 - n);
        }
    }
    
    private void a(final boolean b, final int n, final int n2, final boolean b2) {
        if (b2) {
            this.a(b, n, n2);
        }
        else {
            this.a(b, -1, n2 - n);
        }
    }
    
    private void i(final int n, final int n2) {
        if (this.a != null) {
            this.a.a(this.a.b == this, n, n2);
        }
        else {
            this.f.g();
        }
    }
    
    private void a(final boolean b, final int n, final int n2) {
        if (b) {
            this.g += n;
            this.i += n2;
        }
        else {
            this.h += n;
            this.j += n2;
        }
        if (this.a != null) {
            this.a.a(this.a.b == this, n, n2);
        }
        else {
            this.f.g();
        }
    }
    
    private void f() {
        this.b = null;
        this.g = 0;
        this.i = 0;
        this.c = null;
        this.h = 0;
        this.j = 0;
        this.f = null;
        this.a = null;
        this.d = 0;
        this.e = 0;
        this.k = -1;
    }
    
    private void a(final BarcodeNode barcodeNode, final BarcodeNode barcodeNode2) {
        if (barcodeNode == this.b) {
            this.b = barcodeNode2;
        }
        else {
            this.c = barcodeNode2;
        }
    }
    
    private void f(final int n) {
        if (this.d != 0) {
            return;
        }
        if (this.a == null) {
            this.g(n);
        }
        else if (this.a.b == this) {
            this.h(n);
        }
        else {
            this.i(n);
        }
    }
    
    private void g(final int n) {
        if (this.b != null) {
            if (this.c == null) {
                final BarcodeNode b = this.b;
                b.e += this.e;
                this.b.a = null;
                this.f.a(this.b);
                this.f();
            }
            else {
                this.b.j(this.e);
                this.g += this.e;
                this.i += this.e;
                this.e = 0;
                this.e(n);
            }
        }
    }
    
    private void h(final int n) {
        if (this.b != null) {
            this.b.j(this.e);
            this.g += this.e;
            this.i += this.e;
            this.e = 0;
            this.e(n);
        }
        else {
            if (n == 0) {
                return;
            }
            this.a.b = this.c;
            if (this.c != null) {
                this.a.b.a = this.a;
            }
            this.a.a(true, -this.e, -this.e);
            this.a.g();
            this.f.b(n - 1, this.e);
            this.f();
        }
    }
    
    private void i(final int n) {
        if (this.b == null) {
            final BarcodeNode a = this.a;
            a.h -= this.e;
            final BarcodeNode a2 = this.a;
            a2.j -= this.e;
            final BarcodeNode a3 = this.a;
            a3.e += this.e;
            this.e = 0;
            this.e(n);
        }
        else {
            this.b.j(this.e);
            this.g += this.e;
            this.i += this.e;
            this.e = 0;
            this.e(n);
        }
    }
    
    private void j(final int n) {
        if (this.c != null) {
            this.h += n;
            this.j += n;
            this.c.j(n);
        }
        else {
            this.e += n;
        }
    }
    
    private void g() {
        final int k = this.k;
        this.h();
        this.i();
        if (this.k != k && this.a != null) {
            this.a.g();
        }
    }
    
    private void h() {
        this.k = 1 + Math.max((this.b == null) ? 0 : this.b.k, (this.c == null) ? 0 : this.c.k);
    }
    
    private void i() {
        final int n = (this.b != null) ? this.b.k : 0;
        final int n2 = (this.c != null) ? this.c.k : 0;
        if (n - n2 >= 2) {
            if (((this.b.c != null) ? this.b.c.k : 0) > ((this.b.b != null) ? this.b.b.k : 0)) {
                this.b.k();
            }
            this.j();
        }
        else if (n2 - n >= 2) {
            if (((this.c.b != null) ? this.c.b.k : 0) > ((this.c.c != null) ? this.c.c.k : 0)) {
                this.c.j();
            }
            this.k();
        }
    }
    
    private void j() {
        final BarcodeNode b = this.b;
        this.b = b.c;
        this.g = b.h;
        this.i = b.j;
        if (b.c != null) {
            b.c.a = this;
        }
        b.c = this;
        b.h = this.b();
        b.j = this.a();
        if (this.a != null) {
            this.a.a(this, b);
        }
        else {
            this.f.a(b);
        }
        b.a = this.a;
        this.a = b;
        this.h();
        b.k = 0;
    }
    
    private void k() {
        final BarcodeNode c = this.c;
        this.c = c.b;
        this.h = c.g;
        this.j = c.i;
        if (c.b != null) {
            c.b.a = this;
        }
        c.b = this;
        c.g = this.b();
        c.i = this.a();
        if (this.a != null) {
            this.a.a(this, c);
        }
        else {
            this.f.a(c);
        }
        c.a = this.a;
        this.a = c;
        this.h();
        c.k = 0;
    }
    
    public String toString() {
        return "[ " + this.b + " (" + this.g + ", " + this.i + ")" + " <" + this.d + "> " + this.e + " <" + this.k + "> " + "(" + this.h + ", " + this.j + ") " + this.c + " ]";
    }
}
