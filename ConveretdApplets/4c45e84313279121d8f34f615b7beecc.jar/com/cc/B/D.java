// 
// Decompiled by Procyon v0.5.30
// 

package com.cc.B;

import com.cc.C.B;

public class D
{
    protected B B;
    private String F;
    private boolean C;
    private boolean A;
    private boolean D;
    private static long E;
    
    public D(final String f) {
        this.B = new B();
        this.C = false;
        this.A = false;
        this.D = false;
        if (f == null) {
            throw new IllegalArgumentException("Id can not be null!");
        }
        this.F = f;
    }
    
    public boolean D() {
        return this.C;
    }
    
    public void C(final boolean c) {
        this.C = c;
    }
    
    public void B(final E e) {
        this.B.A(e);
    }
    
    public boolean A(final E e) {
        return this.A(e, 0) > -1;
    }
    
    private int A(final E e, final int n) {
        for (int i = n; i < this.B.A(); ++i) {
            if (this.B.A(i) == e) {
                return i;
            }
        }
        return Integer.MIN_VALUE;
    }
    
    public E G() {
        return (this.B.A() == 0) ? null : ((E)this.B.A(0));
    }
    
    public E C() {
        return (this.B.A() == 0) ? null : ((E)this.B.A(this.B.A() - 1));
    }
    
    private E A(final int n) {
        if (n < 0 || n >= this.B.A()) {
            return null;
        }
        return (E)this.B.A(n);
    }
    
    public void A(final A a) {
        if (!a.d) {
            for (int i = 0; i < this.B.A(); ++i) {
                a.D((E)this.B.A(i));
            }
        }
        else if (this.D()) {
            a.C(this);
        }
    }
    
    public boolean F() {
        for (int i = 0; i < this.B.A(); ++i) {
            if (!((E)this.B.A(i)).H()) {
                return false;
            }
        }
        return true;
    }
    
    public String K() {
        String s = "";
        for (int i = 0; i < this.B.A(); ++i) {
            final E e = (E)this.B.A(i);
            if (e.I) {
                s += e.G();
            }
            else {
                s += ((e.S() || (e.C() && e.L().length() > 1)) ? "-" : e.L());
            }
        }
        return s;
    }
    
    public boolean E() {
        for (int i = 0; i < this.B.A(); ++i) {
            final E e = (E)this.B.A(i);
            if (!e.I && (e.S() || e.C())) {
                return false;
            }
        }
        return true;
    }
    
    public String H() {
        return this.F;
    }
    
    public String toString() {
        return this.F;
    }
    
    public D I() {
        final D d = new D("copy - " + com.cc.B.D.E++);
        d.D = true;
        for (int i = 0; i < this.B.A(); ++i) {
            d.B((E)this.B.A(i));
        }
        return d;
    }
    
    public boolean A(final D d) {
        if (this.B.A() != d.B.A()) {
            return false;
        }
        for (int i = 0; i < this.B.A(); ++i) {
            if (!d.A((E)this.B.A(i))) {
                return false;
            }
        }
        return true;
    }
    
    public boolean B() {
        return this.D;
    }
    
    public void B(final boolean d) {
        this.D = d;
    }
    
    public boolean A() {
        return this.A;
    }
    
    public void A(final boolean a) {
        this.A = a;
    }
    
    public B J() {
        return this.B;
    }
    
    public E A(final E e, final E e2) {
        return this.A(e, e2, 1);
    }
    
    public E B(final E e, final E e2) {
        return this.A(e, e2, -1);
    }
    
    public E A(final E e, final E e2, final int n) {
        final int a = this.A(e, 0);
        int i = this.A(e2, 0);
        int n2 = Math.abs(i - a);
        E e3 = this.A(i + n);
        while (i > -1) {
            if (Math.abs(i - a) < n2) {
                n2 = Math.abs(i - a);
                e3 = this.A(i + n);
            }
            i = this.A(e2, i + 1);
        }
        return e3;
    }
    
    static {
        D.E = System.currentTimeMillis();
    }
}
