// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.util.Vector;

public class cb
{
    int a;
    int b;
    int c;
    Vector d;
    
    public cb() {
        this(10);
    }
    
    public cb(int a) {
        this.a = 10;
        this.b = 0;
        this.c = -1;
        if (h.q == 0) {
            if (a < 10) {
                a = 10;
            }
            this.a = a;
            this.d = new Vector(a);
        }
    }
    
    public int a() {
        return this.b;
    }
    
    public ca b() {
        final int q = h.q;
        final int size = this.d.size();
        if (q == 0) {
            if (size <= 0) {
                return null;
            }
            final Object element = this;
            if (q != 0) {
                return (ca)element;
            }
            final int b = this.b;
        }
        if (size >= this.d.size()) {
            return null;
        }
        final Object element = this.d.elementAt(this.b);
        return (ca)element;
    }
    
    public ca c() {
        Object element = this;
        if (h.q == 0) {
            if (this.b >= this.c) {
                return null;
            }
            element = this.d.elementAt(++this.b);
        }
        return (ca)element;
    }
    
    public ca d() {
        Object element = this;
        if (h.q == 0) {
            if (this.b <= 0) {
                return null;
            }
            final Vector d = this.d;
            final int b = this.b - 1;
            this.b = b;
            element = d.elementAt(b);
        }
        return (ca)element;
    }
    
    public int e() {
        return this.d.size();
    }
    
    public void a(final ca ca) {
        final int q = h.q;
        if (ca != null) {
            cb cb = this;
            Label_0066: {
                if (q == 0) {
                    if (this.b >= this.a) {
                        final int n = this.a - 1;
                        this.b = n;
                        this.c = n;
                        this.d.removeElementAt(0);
                        if (q == 0) {
                            break Label_0066;
                        }
                    }
                    this.c = this.b;
                    cb = this;
                }
                cb.a(this.c);
            }
            this.d.insertElementAt(ca, this.b++);
        }
    }
    
    public void b(final ca ca) {
        final int q = h.q;
        if (ca != null) {
            final int b = this.b;
            final int a = this.a;
            cb cb = null;
            Label_0112: {
                if (q == 0) {
                    if (b >= a) {
                        final int n = this.a - 1;
                        this.b = n;
                        this.c = n;
                        this.d.removeElementAt(0);
                        this.d.insertElementAt(ca, this.b);
                        if (q == 0) {
                            return;
                        }
                    }
                    cb = this;
                    if (q != 0) {
                        break Label_0112;
                    }
                    final int b2 = this.b;
                    final int c = this.c;
                }
                if (b > a) {
                    this.b = this.c + 1;
                    this.c = this.b;
                    this.d.insertElementAt(ca, this.b);
                    if (q == 0) {
                        return;
                    }
                }
                cb = this;
            }
            cb.d.setElementAt(ca, this.b);
        }
    }
    
    private void a(final int n) {
        final int q = h.q;
        final int size = this.d.size();
        if (q == 0) {
            if (size <= n) {
                return;
            }
            this.d.size();
        }
        int n2 = size - 1;
        while (true) {
            Label_0049: {
                if (q == 0) {
                    break Label_0049;
                }
                this.d.removeElementAt(n2);
                --n2;
            }
            if (n2 >= n) {
                continue;
            }
            break;
        }
    }
}
