// 
// Decompiled by Procyon v0.5.30
// 

package com.cc.C;

import com.cc.B.D;

public class B
{
    private Object[] A;
    
    public B() {
        this.A = new Object[0];
    }
    
    public B(final Object[] a) {
        this.A = new Object[0];
        this.A = a;
    }
    
    public Object A(final int n) {
        return this.A[n];
    }
    
    public int A() {
        return this.A.length;
    }
    
    public void A(final Object o) {
        final Object[] a = new Object[this.A.length + 1];
        for (int i = 0; i < this.A.length; ++i) {
            a[i] = this.A[i];
        }
        a[a.length - 1] = o;
        this.A = a;
    }
    
    public void A(final B b) {
        for (int i = 0; i < b.A(); ++i) {
            this.A(b.A(i));
        }
    }
    
    public String toString() {
        String string = "[";
        for (int i = 0; i < this.A.length; ++i) {
            string = string + ((i > 0) ? "," : "") + this.A[i];
        }
        return string + "]";
    }
    
    public int B(final Object o) {
        for (int i = 0; i < this.A.length; ++i) {
            if (o.equals(this.A[i])) {
                return i;
            }
        }
        return -1;
    }
    
    public void C(final Object o) {
        final D[] a = new D[this.A.length - 1];
        int n = 0;
        for (int i = 0; i < this.A.length; ++i) {
            if (this.A[i] == o) {
                n = -1;
            }
            else {
                a[i + n] = (D)this.A[i];
            }
        }
        this.A = a;
    }
    
    public void B(final B b) {
        for (int i = 0; i < b.A(); ++i) {
            this.C(b.A(i));
        }
    }
}
