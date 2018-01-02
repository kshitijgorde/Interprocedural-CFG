// 
// Decompiled by Procyon v0.5.30
// 

package com.kaon.meson;

import java.util.Vector;

public final class FastVector implements Cloneable
{
    private Object[] a;
    private int b;
    private int c;
    private Class d;
    public static /* synthetic */ Class e;
    
    public FastVector(final int n, final int c) {
        this.d = ((FastVector.e == null) ? (FastVector.e = class$("com.kaon.meson.StringPair")) : FastVector.e);
        this.c = c;
        this.a = new Object[n];
    }
    
    public Object clone() {
        final FastVector fastVector = new FastVector(this.a.length, this.c);
        System.arraycopy(this.a, 0, fastVector.a, 0, this.b);
        fastVector.b = this.b;
        return fastVector;
    }
    
    public synchronized FastVector copyAndFlush() {
        final FastVector fastVector = new FastVector(this.a.length, this.c);
        final Object[] a = this.a;
        this.a = fastVector.a;
        fastVector.a = a;
        fastVector.b = this.b;
        this.b = 0;
        return fastVector;
    }
    
    public Vector createVector() {
        final Vector<Object> vector = new Vector<Object>(this.b);
        for (int i = 0; i < this.b; ++i) {
            vector.addElement(this.a[i]);
        }
        return vector;
    }
    
    public String toString() {
        return "array[" + this.b + "] @ " + super.toString();
    }
    
    public void add(final int n, final Object o) {
        if (n >= this.a.length) {
            final Object[] a = new Object[n + this.c + 1];
            System.arraycopy(this.a, 0, a, 0, this.b);
            this.a = a;
        }
        this.a[n] = o;
        if (n >= this.b) {
            this.b = n + 1;
        }
    }
    
    public void add(final Object o) {
        this.add(this.b, o);
    }
    
    public synchronized void addSync(final Object o) {
        this.add(this.b, o);
    }
    
    public void remove(final int n) {
        if (n >= this.b) {
            return;
        }
        --this.b;
        for (int i = n; i < this.b; ++i) {
            this.a[i] = this.a[i + 1];
        }
    }
    
    public void clear() {
        for (int i = 0; i < this.b; ++i) {
            this.a[i] = null;
        }
        this.b = 0;
    }
    
    public void setSize(final int b) {
        if (b > this.a.length) {
            this.add(b, null);
        }
        else {
            this.b = b;
        }
    }
    
    public int size() {
        return this.b;
    }
    
    public Object get(final int n) {
        return this.a[n];
    }
    
    public void setAdd(final Object o) {
        if (this.indexOf(o) == -1) {
            this.add(o);
        }
    }
    
    public FastVector setUnion(final FastVector fastVector) {
        final FastVector fastVector2 = new FastVector(this.b + fastVector.b, this.c);
        System.arraycopy(this.a, 0, fastVector2.a, 0, this.b);
        fastVector2.b = this.b;
        for (int i = 0; i < fastVector.b; ++i) {
            final Object o = fastVector.a[i];
            if (-1 == this.indexOf(o)) {
                fastVector2.a[fastVector2.b++] = o;
            }
        }
        return fastVector2;
    }
    
    public FastVector setMinus(final FastVector fastVector) {
        final FastVector fastVector2 = new FastVector(this.b, this.c);
        for (int i = 0; i < this.b; ++i) {
            final Object o = this.a[i];
            if (fastVector.indexOf(o) == -1) {
                fastVector2.a[fastVector2.b++] = o;
            }
        }
        return fastVector2;
    }
    
    public FastVector union(final FastVector fastVector) {
        final FastVector fastVector2 = new FastVector(this.b + fastVector.b, this.c);
        System.arraycopy(this.a, 0, fastVector2.a, 0, this.b);
        System.arraycopy(fastVector.a, 0, fastVector2.a, this.b, fastVector.b);
        fastVector2.b = this.b + fastVector.b;
        return fastVector2;
    }
    
    private boolean a(final Object o, final Object o2) {
        if (o == o2) {
            return true;
        }
        if (o == null || o2 == null) {
            return false;
        }
        if (o2.getClass() == this.d) {
            if (o2.equals(o)) {
                return true;
            }
        }
        else if (o.equals(o2)) {
            return true;
        }
        return false;
    }
    
    public int indexOf(final Object o) {
        for (int i = 0; i < this.b; ++i) {
            if (this.a(o, this.a[i])) {
                return i;
            }
        }
        return -1;
    }
    
    public int hashCode() {
        int n = 1;
        for (int i = 0; i < this.b; ++i) {
            final Object o = this.a[i];
            n = 31 * n + ((o == null) ? 0 : o.hashCode());
        }
        return n;
    }
    
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FastVector)) {
            return false;
        }
        final FastVector fastVector = (FastVector)o;
        if (this.b != fastVector.b) {
            return false;
        }
        for (int i = 0; i < this.b; ++i) {
            if (!this.a(this.a[i], fastVector.a[i])) {
                return false;
            }
        }
        return true;
    }
    
    public static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
