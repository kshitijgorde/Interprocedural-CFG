// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver;

import java.util.Vector;

class j
{
    int a;
    int b;
    int c;
    Vector d;
    
    public j() {
        this(10);
    }
    
    public j(int a) {
        this.a = 10;
        this.b = -1;
        this.c = -1;
        if (!g.v) {
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
    
    public l b() {
        final boolean v = g.v;
        final int size = this.d.size();
        if (!v) {
            if (size <= 0) {
                return null;
            }
            final Object element = this;
            if (v) {
                return (l)element;
            }
            final int b = this.b;
        }
        if (size >= this.d.size()) {
            return null;
        }
        final Object element = this.d.elementAt(this.b);
        return (l)element;
    }
    
    public l c() {
        Object element = this;
        if (!g.v) {
            if (this.b >= this.c) {
                return null;
            }
            element = this.d.elementAt(++this.b);
        }
        return (l)element;
    }
    
    public l d() {
        --this.b;
        Object element = this;
        if (!g.v) {
            if (this.b < 0) {
                return null;
            }
            element = this.d.elementAt(this.b);
        }
        return (l)element;
    }
    
    public int e() {
        return this.d.size();
    }
    
    public void a(final l l) {
        final boolean v = g.v;
        if (l != null) {
            ++this.b;
            j j = this;
            Label_0076: {
                if (!v) {
                    if (this.b >= this.a) {
                        final int n = this.a - 1;
                        this.b = n;
                        this.c = n;
                        this.d.removeElementAt(0);
                        if (!v) {
                            break Label_0076;
                        }
                    }
                    this.c = this.b;
                    j = this;
                }
                j.a(this.c);
            }
            this.d.insertElementAt(l, this.b);
        }
    }
    
    public void b(final l l) {
        final boolean v = g.v;
        if (l != null) {
            final int b = this.b;
            final int a = this.a;
            j j = null;
            Label_0112: {
                if (!v) {
                    if (b >= a) {
                        final int n = this.a - 1;
                        this.b = n;
                        this.c = n;
                        this.d.removeElementAt(0);
                        this.d.insertElementAt(l, this.b);
                        if (!v) {
                            return;
                        }
                    }
                    j = this;
                    if (v) {
                        break Label_0112;
                    }
                    final int b2 = this.b;
                    final int c = this.c;
                }
                if (b > a) {
                    this.b = this.c + 1;
                    this.c = this.b;
                    this.d.insertElementAt(l, this.b);
                    if (!v) {
                        return;
                    }
                }
                j = this;
            }
            j.d.setElementAt(l, this.b);
        }
    }
    
    private void a(final int n) {
        final boolean v = g.v;
        final int size = this.d.size();
        if (!v) {
            if (size <= n) {
                return;
            }
            this.d.size();
        }
        int i = size - 1;
        while (i >= n) {
            this.d.removeElementAt(i);
            --i;
            if (v) {
                break;
            }
        }
    }
}
