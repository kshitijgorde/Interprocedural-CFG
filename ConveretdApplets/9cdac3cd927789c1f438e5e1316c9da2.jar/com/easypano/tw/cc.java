// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.util.Vector;

public class cc
{
    int a;
    int b;
    int c;
    Vector d;
    
    public cc() {
        this(10);
    }
    
    public cc(int a) {
        this.a = 10;
        this.b = 0;
        this.c = -1;
        if (!g.q) {
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
    
    public cb b() {
        final boolean q = g.q;
        final int size = this.d.size();
        if (!q) {
            if (size <= 0) {
                return null;
            }
            final Object element = this;
            if (q) {
                return (cb)element;
            }
            final int b = this.b;
        }
        if (size >= this.d.size()) {
            return null;
        }
        final Object element = this.d.elementAt(this.b);
        return (cb)element;
    }
    
    public cb c() {
        Object element = this;
        if (!g.q) {
            if (this.b >= this.c) {
                return null;
            }
            element = this.d.elementAt(++this.b);
        }
        return (cb)element;
    }
    
    public cb d() {
        Object element = this;
        if (!g.q) {
            if (this.b <= 0) {
                return null;
            }
            final Vector d = this.d;
            final int b = this.b - 1;
            this.b = b;
            element = d.elementAt(b);
        }
        return (cb)element;
    }
    
    public int e() {
        return this.d.size();
    }
    
    public void a(final cb cb) {
        final boolean q = g.q;
        if (cb != null) {
            cc cc = this;
            Label_0066: {
                if (!q) {
                    if (this.b >= this.a) {
                        final int n = this.a - 1;
                        this.b = n;
                        this.c = n;
                        this.d.removeElementAt(0);
                        if (!q) {
                            break Label_0066;
                        }
                    }
                    this.c = this.b;
                    cc = this;
                }
                cc.a(this.c);
            }
            this.d.insertElementAt(cb, this.b++);
        }
    }
    
    public void b(final cb cb) {
        final boolean q = g.q;
        if (cb != null) {
            final int b = this.b;
            final int a = this.a;
            cc cc = null;
            Label_0112: {
                if (!q) {
                    if (b >= a) {
                        final int n = this.a - 1;
                        this.b = n;
                        this.c = n;
                        this.d.removeElementAt(0);
                        this.d.insertElementAt(cb, this.b);
                        if (!q) {
                            return;
                        }
                    }
                    cc = this;
                    if (q) {
                        break Label_0112;
                    }
                    final int b2 = this.b;
                    final int c = this.c;
                }
                if (b > a) {
                    this.b = this.c + 1;
                    this.c = this.b;
                    this.d.insertElementAt(cb, this.b);
                    if (!q) {
                        return;
                    }
                }
                cc = this;
            }
            cc.d.setElementAt(cb, this.b);
        }
    }
    
    private void a(final int n) {
        final boolean q = g.q;
        final int size = this.d.size();
        if (!q) {
            if (size <= n) {
                return;
            }
            this.d.size();
        }
        int n2 = size - 1;
        while (true) {
            Label_0049: {
                if (!q) {
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
