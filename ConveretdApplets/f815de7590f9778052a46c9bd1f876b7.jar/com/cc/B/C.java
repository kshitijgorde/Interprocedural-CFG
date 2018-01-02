// 
// Decompiled by Procyon v0.5.30
// 

package com.cc.B;

import java.awt.Point;

public class C
{
    private A A;
    
    public C(final A a) {
        this.A = a;
    }
    
    public boolean A(final char c) {
        if (!this.A.h) {
            return false;
        }
        for (int i = 0; i < this.A.A; ++i) {
            for (int j = 0; j < this.A.D; ++j) {
                final E a = this.A.A(i, j, false);
                if (a.B() && !a.S() && !a.C() && a.L().charAt(0) == c) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private Point A(final Point point, final int n, final int n2, final int n3) {
        final E a = this.A.A(point.x, point.y, false);
        final Point point2 = new Point(point.x + n, point.y + n2);
        if (point2.x < 0 || point2.x >= this.A.A || point2.y < 0 || point2.y >= this.A.D) {
            return null;
        }
        final E a2 = this.A.A(point2.x, point2.y, false);
        if (a2 == null || (a2.P() & n3) == 0x0 || a == a2) {
            return this.A(point2, n, n2, n3);
        }
        return point2;
    }
    
    public E F(final E e) {
        return this.B(e, 2);
    }
    
    public E D(final E e) {
        return this.A(e, 2);
    }
    
    public E E(final E e) {
        return this.C(e, 2);
    }
    
    public E A(final E e) {
        return this.D(e, 2);
    }
    
    public E H(final E e) {
        return this.B(e, 15);
    }
    
    public E B(final E e) {
        return this.A(e, 15);
    }
    
    public E C(final E e) {
        return this.C(e, 15);
    }
    
    public E G(final E e) {
        return this.D(e, 15);
    }
    
    public E B(final E e, final int n) {
        final Point a = this.A(this.A.C(e), 0, -1, n);
        return (a == null) ? null : this.A.A(a.x, a.y, false);
    }
    
    public E A(final E e, final int n) {
        final Point a = this.A(this.A.C(e), 0, 1, n);
        return (a == null) ? null : this.A.A(a.x, a.y, false);
    }
    
    public E C(final E e, final int n) {
        final Point a = this.A(this.A.C(e), 1, 0, n);
        return (a == null) ? null : this.A.A(a.x, a.y, false);
    }
    
    public E D(final E e, final int n) {
        final Point a = this.A(this.A.C(e), -1, 0, n);
        return (a == null) ? null : this.A.A(a.x, a.y, false);
    }
}
