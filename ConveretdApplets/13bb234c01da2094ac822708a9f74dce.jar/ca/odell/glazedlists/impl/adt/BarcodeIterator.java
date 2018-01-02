// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl.adt;

import java.util.NoSuchElementException;
import java.util.Iterator;

public class BarcodeIterator implements Iterator
{
    private Barcode a;
    private BarcodeNode b;
    private int c;
    private int d;
    private int e;
    
    BarcodeIterator(final Barcode a) {
        this.a = null;
        this.b = null;
        this.c = -1;
        this.d = 0;
        this.e = 0;
        final BarcodeNode e = a.e();
        if (e != null) {
            this.b = e;
            while (this.b.b != null) {
                this.b = this.b.b;
            }
        }
        this.a = a;
    }
    
    public boolean hasNext() {
        return this.g() != this.a.a() - 1;
    }
    
    public boolean a() {
        return this.g() < this.a.f() - 1 && this.b != null;
    }
    
    public boolean b() {
        if (this.a.a() != this.a.f()) {
            return this.hasNext();
        }
        return this.b != null && (this.c < this.b.d - 1 || this.e + this.b.d < this.a.b());
    }
    
    public boolean a(final Object o) {
        if (o == Barcode.b) {
            return this.a();
        }
        return this.b();
    }
    
    public Object next() {
        ++this.c;
        if (this.b == null) {
            if (this.g() < this.a.a()) {
                return Barcode.a;
            }
            throw new NoSuchElementException();
        }
        else {
            if (this.c >= this.b.d + this.b.e) {
                if (this.g() < this.a.f()) {
                    this.d += this.b.e;
                    this.e += this.b.d;
                    this.j();
                    this.c = 0;
                }
                else {
                    if (this.g() < this.a.a()) {
                        return Barcode.a;
                    }
                    throw new NoSuchElementException();
                }
            }
            if (this.c < this.b.d) {
                return Barcode.a;
            }
            return Barcode.b;
        }
    }
    
    public Object c() {
        ++this.c;
        if (this.b == null) {
            throw new NoSuchElementException();
        }
        if (this.c < this.b.d) {
            this.c = this.b.d;
        }
        else if (this.c >= this.b.d + this.b.e) {
            if (this.g() >= this.a.f()) {
                throw new NoSuchElementException();
            }
            this.e += this.b.d;
            this.d += this.b.e;
            this.j();
            this.c = this.b.d;
        }
        if (this.c < this.b.d) {
            throw new IllegalStateException();
        }
        return Barcode.b;
    }
    
    public Object d() {
        ++this.c;
        if (this.b == null) {
            if (this.g() < this.a.a()) {
                return Barcode.a;
            }
            throw new NoSuchElementException();
        }
        else {
            if (this.c >= this.b.d) {
                if (this.g() < this.a.f() && this.g() + this.b.e >= this.a.f()) {
                    this.c = this.b.d;
                    this.c += this.b.e;
                }
                if (this.g() < this.a.f()) {
                    this.d += this.b.e;
                    this.e += this.b.d;
                    this.j();
                    this.c = 0;
                }
                else {
                    if (this.g() < this.a.a()) {
                        return Barcode.a;
                    }
                    throw new NoSuchElementException();
                }
            }
            if (this.c >= this.b.d) {
                throw new IllegalStateException();
            }
            return Barcode.a;
        }
    }
    
    public Object b(final Object o) {
        if (o == Barcode.b) {
            return this.c();
        }
        return this.d();
    }
    
    public void remove() {
        if (this.c == -1) {
            throw new NoSuchElementException("Cannot call remove() before next() is called.");
        }
        if (this.b == null || this.g() >= this.a.f()) {
            this.a.e(this.g(), 1);
            --this.c;
        }
        else {
            final BarcodeNode b = this.b;
            if (this.c == 0 && this.b.d == 1 && this.g() != 0) {
                this.k();
                this.d -= this.b.e;
                this.e -= this.b.d;
                this.c += this.b.d + this.b.e;
            }
            else if (this.c == this.b.d && this.b.e == 1) {
                if (this.c == this.a.f() - 1) {
                    this.b = null;
                }
                else if (this.g() == this.a.f() - 1) {
                    this.k();
                    this.d -= this.b.e;
                    this.e -= this.b.d;
                    this.c += this.b.d + this.b.e;
                }
                else {
                    this.j();
                }
            }
            b.e(this.g(), this.c);
            --this.c;
        }
    }
    
    public int e() {
        if (this.c == -1) {
            throw new NoSuchElementException("Cannot call setWhite() before next() is called.");
        }
        if (this.b == null || this.g() >= this.a.f() || this.c < this.b.d) {
            return this.i();
        }
        if (this.g() != this.a.f() - 1) {
            if (this.b.e == 1) {
                final BarcodeNode b = this.b;
                this.j();
                b.a(this.g(), this.c, 1);
                if (this.a.e() != null) {
                    this.a.g();
                }
                if (this.b.d == 0 && this.b.e == 0) {
                    this.b = b;
                }
                return this.e + this.c;
            }
            if (this.c == this.b.d) {
                this.b.a(this.g(), this.c, 1);
                if (this.a.e() != null) {
                    this.a.g();
                }
                return this.e + this.c;
            }
            this.b.a(this.g(), this.c, 1);
            if (this.a.e() != null) {
                this.a.g();
            }
            this.d += this.b.e;
            this.e += this.b.d;
            this.j();
            this.c = 0;
            return this.e;
        }
        else {
            if (this.b.e != 1) {
                this.b.a(this.g(), this.c, 1);
                if (this.a.e() != null) {
                    this.a.g();
                }
                return this.e + this.c - this.b.e;
            }
            final BarcodeNode b2 = this.b;
            if (this.b.d + 1 == this.a.f()) {
                this.b = null;
                b2.a(this.g(), this.c, 1);
                if (this.a.e() != null) {
                    this.a.g();
                }
                return this.c;
            }
            this.k();
            final int c = this.c;
            this.d -= this.b.e;
            this.e -= this.b.d;
            this.c += this.b.d + this.b.e;
            b2.a(this.g(), c, 1);
            if (this.a.e() != null) {
                this.a.g();
            }
            if (this.b.d == 0 && this.b.e == 0) {
                this.b = b2;
            }
            return this.e + this.c - this.b.e;
        }
    }
    
    public int f() {
        if (this.c == -1) {
            throw new NoSuchElementException("Cannot call setBlack() before next() is called.");
        }
        if (this.b == null) {
            this.a.d(this.g(), 1);
            this.b = this.a.e();
            return 0;
        }
        if (this.g() == this.a.f()) {
            this.a.d(this.g(), 1);
            if (this.a.e() != null) {
                this.a.g();
            }
        }
        else if (this.g() > this.a.f()) {
            this.a.d(this.g(), 1);
            if (this.a.e() != null) {
                this.a.g();
            }
            this.e += this.b.d;
            this.d += this.b.e;
            this.c -= this.b.d + this.b.e;
            this.j();
        }
        else if (this.c < this.b.d && this.b.d == 1) {
            if (this.g() == 0) {
                this.b.b(this.g(), this.c, 1);
                if (this.a.e() != null) {
                    this.a.g();
                }
            }
            else {
                final BarcodeNode b = this.b;
                this.k();
                final int c = this.c;
                this.d -= this.b.e;
                this.e -= this.b.d;
                this.c += this.b.d + this.b.e;
                b.b(this.g(), c, 1);
                if (this.a.e() != null) {
                    this.a.g();
                }
                if (this.b.d == 0 && this.b.e == 0) {
                    this.b = b;
                }
            }
        }
        else if (this.c < this.b.d) {
            if (this.c == 0) {
                this.b.b(this.g(), this.c, 1);
                if (this.a.e() != null) {
                    this.a.g();
                }
                ++this.d;
                --this.c;
                return this.d - 1;
            }
            if (this.c == this.b.d - 1) {
                this.b.b(this.g(), this.c, 1);
                if (this.a.e() != null) {
                    this.a.g();
                }
                return this.d;
            }
            this.b.b(this.g(), this.c, 1);
            if (this.a.e() != null) {
                this.a.g();
            }
            this.e += this.c;
            ++this.d;
            this.c = -1;
            return this.d - 1;
        }
        return this.h();
    }
    
    public int g() {
        return this.d + this.e + this.c;
    }
    
    public int h() {
        if (this.c == -1) {
            return this.d - 1;
        }
        if (this.b == null || this.c < this.b.d || this.c >= this.b.d + this.b.e) {
            return -1;
        }
        return this.d + this.c - this.b.d;
    }
    
    public int i() {
        if (this.b == null) {
            if (this.c == -1 && this.e != 0) {
                return this.e - 1;
            }
            return this.c;
        }
        else {
            if (this.c >= this.b.d && this.c < this.b.d + this.b.e) {
                return -1;
            }
            if (this.c >= this.b.d + this.b.e) {
                return this.e + this.c - this.b.e;
            }
            return this.e + this.c;
        }
    }
    
    private void j() {
        if (this.b.c != null) {
            this.b = this.b.c;
            while (this.b.b != null) {
                this.b = this.b.b;
            }
        }
        else if (this.b.a.b == this.b) {
            this.b = this.b.a;
        }
        else {
            if (this.b.a.c != this.b) {
                throw new IllegalStateException();
            }
            while (this.b.a.c == this.b) {
                this.b = this.b.a;
            }
            this.b = this.b.a;
        }
    }
    
    private void k() {
        if (this.b.b != null) {
            this.b = this.b.b;
            while (this.b.c != null) {
                this.b = this.b.c;
            }
        }
        else if (this.b.a.c == this.b) {
            this.b = this.b.a;
        }
        else {
            if (this.b.a.b != this.b) {
                throw new IllegalStateException();
            }
            while (this.b.a.b == this.b) {
                this.b = this.b.a;
            }
            this.b = this.b.a;
        }
    }
}
