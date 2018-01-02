// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.util;

public class G implements Cloneable
{
    private Object[] a;
    private int b;
    
    public G(final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + n);
        }
        this.a = new Object[n];
    }
    
    public G() {
        this(5);
    }
    
    public Object clone() {
        try {
            final G g = (G)super.clone();
            g.a = new Object[this.b];
            System.arraycopy(this.a, 0, g.a, 0, this.b);
            return g;
        }
        catch (CloneNotSupportedException ex) {
            throw new InternalError();
        }
    }
    
    public void a(final int n) {
        final int length = this.a.length;
        if (n > length) {
            final Object[] a = this.a;
            int n2 = length * 3 / 2 + 1;
            if (n2 < n) {
                n2 = n;
            }
            System.arraycopy(a, 0, this.a = new Object[n2], 0, this.b);
        }
    }
    
    public int a() {
        return this.b;
    }
    
    public boolean a(final Object o) {
        return this.b(o) >= 0;
    }
    
    public int b(final Object o) {
        if (o == null) {
            for (int i = 0; i < this.b; ++i) {
                if (this.a[i] == null) {
                    return i;
                }
            }
        }
        else {
            for (int j = 0; j < this.b; ++j) {
                if (o.equals(this.a[j])) {
                    return j;
                }
            }
        }
        return -1;
    }
    
    public Object b(final int n) {
        this.d(n);
        return this.a[n];
    }
    
    public Object a(final int n, final Object o) {
        this.d(n);
        final Object o2 = this.a[n];
        this.a[n] = o;
        return o2;
    }
    
    public boolean c(final Object o) {
        this.a(this.b + 1);
        this.a[this.b++] = o;
        return true;
    }
    
    public void b(final int n, final Object o) {
        if (n > this.b || n < 0) {
            throw new IndexOutOfBoundsException("Index: " + n + ", Size: " + this.b);
        }
        this.a(this.b + 1);
        System.arraycopy(this.a, n, this.a, n + 1, this.b - n);
        this.a[n] = o;
        ++this.b;
    }
    
    public Object c(final int n) {
        this.d(n);
        final Object o = this.a[n];
        final int n2 = this.b - n - 1;
        if (n2 > 0) {
            System.arraycopy(this.a, n + 1, this.a, n, n2);
        }
        this.a[--this.b] = null;
        return o;
    }
    
    public boolean d(final Object o) {
        if (o != null) {
            for (int i = 0; i < this.b; ++i) {
                if (o.equals(this.a[i])) {
                    this.c(i);
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean e(final Object o) {
        if (o != null) {
            for (int i = 0; i < this.b; ++i) {
                if (o == this.a[i]) {
                    this.c(i);
                    return true;
                }
            }
        }
        return false;
    }
    
    public void b() {
        for (int i = 0; i < this.b; ++i) {
            this.a[i] = null;
        }
        this.b = 0;
    }
    
    private void d(final int n) {
        if (n >= this.b) {
            throw new IndexOutOfBoundsException("Index: " + n + ", Size: " + this.b + ", array length: " + this.a.length);
        }
    }
    
    public y c() {
        return new u(this, null);
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("[");
        final y c = this.c();
        boolean b = c.a();
        while (b) {
            final Object b2 = c.b();
            sb.append((b2 == this) ? "(this Collection)" : String.valueOf(b2));
            b = c.a();
            if (b) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
