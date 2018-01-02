// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl.adt.barcode2;

import java.util.NoSuchElementException;

public class FourColorTreeIterator
{
    int a;
    int b;
    int c;
    int d;
    private FourColorTree e;
    private FourColorNode f;
    private int g;
    
    public FourColorTreeIterator(final FourColorTree fourColorTree) {
        this(fourColorTree, 0, (byte)0);
    }
    
    public FourColorTreeIterator(final FourColorTree e, final int n, final byte b) {
        this.e = e;
        if (n != 0) {
            final int n2 = n - 1;
            this.f = (FourColorNode)e.a(n2, b);
            this.a = e.a(n2, b, (byte)1) + ((this.f.e != 1) ? 1 : 0);
            this.b = e.a(n2, b, (byte)2) + ((this.f.e != 2) ? 1 : 0);
            this.c = e.a(n2, b, (byte)4) + ((this.f.e != 4) ? 1 : 0);
            this.d = e.a(n2, b, (byte)8) + ((this.f.e != 8) ? 1 : 0);
            if (this.f.e == 1) {
                this.g = this.a - e.a(this.f, (byte)1);
            }
            if (this.f.e == 2) {
                this.g = this.b - e.a(this.f, (byte)2);
            }
            if (this.f.e == 4) {
                this.g = this.c - e.a(this.f, (byte)4);
            }
            if (this.f.e == 8) {
                this.g = this.d - e.a(this.f, (byte)8);
            }
        }
        else {
            this.f = null;
            this.g = 0;
        }
    }
    
    public boolean a(final byte b) {
        if (this.f == null) {
            return this.e.a(b) > 0;
        }
        if ((b & this.f.e) != 0x0) {
            return this.f(b) < this.e.a(b) - 1;
        }
        return this.f(b) < this.e.a(b);
    }
    
    public boolean b(final byte b) {
        if (this.f == null) {
            return this.e.a(b) > 0;
        }
        return this.h(b) < this.e.a(b);
    }
    
    public void c(final byte b) {
        if (!this.a(b)) {
            throw new NoSuchElementException();
        }
        if (this.f == null) {
            this.f = this.e.c();
            this.g = 0;
            if ((this.f.e & b) != 0x0) {
                return;
            }
        }
        else if ((this.f.e & b) != 0x0 && this.g < this.f.g - 1) {
            if (this.f.e == 1) {
                ++this.a;
            }
            if (this.f.e == 2) {
                ++this.b;
            }
            if (this.f.e == 4) {
                ++this.c;
            }
            if (this.f.e == 8) {
                ++this.d;
            }
            ++this.g;
            return;
        }
        do {
            if (this.f.e == 1) {
                this.a += this.f.g - this.g;
            }
            if (this.f.e == 2) {
                this.b += this.f.g - this.g;
            }
            if (this.f.e == 4) {
                this.c += this.f.g - this.g;
            }
            if (this.f.e == 8) {
                this.d += this.f.g - this.g;
            }
            this.f = FourColorTree.a(this.f);
            this.g = 0;
        } while ((this.f.e & b) == 0x0);
    }
    
    public void d(final byte b) {
        if (!this.b(b)) {
            throw new NoSuchElementException();
        }
        if (this.f == null) {
            this.f = this.e.c();
            this.g = 0;
            if ((this.f.e & b) != 0x0) {
                return;
            }
        }
        do {
            if (this.f.e == 1) {
                this.a += this.f.g - this.g;
            }
            if (this.f.e == 2) {
                this.b += this.f.g - this.g;
            }
            if (this.f.e == 4) {
                this.c += this.f.g - this.g;
            }
            if (this.f.e == 8) {
                this.d += this.f.g - this.g;
            }
            this.f = FourColorTree.a(this.f);
            this.g = 0;
        } while ((this.f.e & b) == 0x0);
    }
    
    public int e(final byte b) {
        if ((this.f.e & b) != 0x0) {
            return this.f.g;
        }
        return 0;
    }
    
    public byte a() {
        if (this.f == null) {
            throw new IllegalStateException();
        }
        return this.f.e;
    }
    
    public int f(final byte b) {
        if (this.f == null) {
            throw new NoSuchElementException();
        }
        int n = 0;
        if ((b & 0x1) != 0x0) {
            n += this.a;
        }
        if ((b & 0x2) != 0x0) {
            n += this.b;
        }
        if ((b & 0x4) != 0x0) {
            n += this.c;
        }
        if ((b & 0x8) != 0x0) {
            n += this.d;
        }
        return n;
    }
    
    public int g(final byte b) {
        if (this.f == null) {
            throw new NoSuchElementException();
        }
        int n = 0;
        if ((b & 0x1) != 0x0) {
            n += this.a;
        }
        if ((b & 0x2) != 0x0) {
            n += this.b;
        }
        if ((b & 0x4) != 0x0) {
            n += this.c;
        }
        if ((b & 0x8) != 0x0) {
            n += this.d;
        }
        if ((this.f.e & b) != 0x0) {
            n -= this.g;
        }
        return n;
    }
    
    public int h(final byte b) {
        if (this.f == null) {
            throw new NoSuchElementException();
        }
        return this.g(b) + this.e(b);
    }
    
    public Element b() {
        if (this.f == null) {
            throw new IllegalStateException();
        }
        return this.f;
    }
}
