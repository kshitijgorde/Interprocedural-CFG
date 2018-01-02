// 
// Decompiled by Procyon v0.5.30
// 

package com.cc.D;

import java.util.Enumeration;
import java.util.Vector;
import com.cc.B.E;
import com.cc.B.D;
import com.cc.B.A;

public class C
{
    private B[] A;
    private A B;
    
    public C(final A b) {
        this.A = new B[0];
        this.B = b;
    }
    
    public int A(final B b) {
        final B[] a = new B[this.A.length + 1];
        for (int i = 0; i < this.A.length; ++i) {
            a[i] = this.A[i];
        }
        a[a.length - 1] = b;
        this.A = a;
        return this.A.length - 1;
    }
    
    public int B() {
        for (int i = 0; i < this.A.length; ++i) {
            if (this.A[i].G) {
                return i;
            }
        }
        final B b = new B();
        b.G = true;
        return this.A(b);
    }
    
    public int A() {
        int n = 0;
        for (int i = 0; i < this.A.length; ++i) {
            if (!this.A[i].G) {
                ++n;
            }
        }
        return n;
    }
    
    public B[] D() {
        return this.A;
    }
    
    public void A(final B[] a) {
        if (a == null) {
            this.A = new B[0];
        }
        else {
            this.A = a;
        }
    }
    
    public void A(final com.cc.C.B b, final boolean b2) {
        if (this.C() != null) {
            for (int i = 0; i < b.A(); ++i) {
                final com.cc.D.D a = this.A((D)b.A(i));
                boolean b3;
                if (b2) {
                    b3 = (a.E() == this.C().E());
                }
                else {
                    b3 = (a.E() != this.C().E());
                }
                if (b3) {
                    this.B(a);
                    return;
                }
            }
        }
        this.B((b.A() > 0) ? this.A((D)b.A(0)) : null);
    }
    
    public com.cc.D.D A(final D d) {
        final String i = d.I();
        for (int j = 0; j < this.A.length; ++j) {
            for (int k = 0; k < this.A[j].D.length; ++k) {
                if (i.equals(this.A[j].D[k].A())) {
                    return this.A[j].D[k];
                }
            }
        }
        return null;
    }
    
    public void B(final com.cc.D.D d) {
        final String a = d.A();
        if (a != null) {
            this.B.A(this.B.D(a));
        }
    }
    
    public com.cc.D.D A(final com.cc.D.D d) {
        for (int i = 0; i < this.A.length; ++i) {
            for (int j = 0; j < this.A[i].D.length; ++j) {
                if (this.A[i].D[j] == d) {
                    final int a = this.A(j, this.A[i].D.length - 1);
                    final int a2 = this.A(i, this.A.length - 1);
                    return (a == 0) ? this.A[a2].D[a] : this.A[i].D[a];
                }
            }
        }
        throw new IllegalArgumentException("Clue [" + d + "] does not exist!");
    }
    
    public com.cc.D.D C(final com.cc.D.D d) {
        for (int i = 0; i < this.A.length; ++i) {
            for (int j = 0; j < this.A[i].D.length; ++j) {
                if (this.A[i].D[j] == d) {
                    final int b = this.B(j, this.A[i].D.length - 1);
                    final int b2 = this.B(i, this.A.length - 1);
                    return (j == 0) ? this.A[b2].D[this.A[b2].D.length - 1] : this.A[i].D[b];
                }
            }
        }
        throw new IllegalArgumentException("Clue [" + d + "] does not exist!");
    }
    
    private int A(final int n, final int n2) {
        return (n < n2) ? (n + 1) : 0;
    }
    
    private int B(final int n, final int n2) {
        return (n > 0) ? (n - 1) : n2;
    }
    
    public com.cc.D.D[] A(final E e) {
        final Vector vector = new Vector<com.cc.D.D>();
        for (int i = 0; i < this.A.length; ++i) {
            for (int j = 0; j < this.A[i].D.length; ++j) {
                final com.cc.D.D d = this.A[i].D[j];
                if (d.L == e) {
                    vector.addElement(d);
                }
            }
        }
        final com.cc.D.D[] array = new com.cc.D.D[vector.size()];
        final Enumeration<com.cc.D.D> elements = vector.elements();
        for (int k = 0; k < array.length; ++k) {
            array[k] = elements.nextElement();
        }
        return array;
    }
    
    public com.cc.D.D C() {
        return this.A(this.B.M());
    }
}
