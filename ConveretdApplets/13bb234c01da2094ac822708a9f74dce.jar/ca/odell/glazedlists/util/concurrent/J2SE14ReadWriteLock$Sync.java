// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.util.concurrent;

import java.util.HashMap;
import java.io.Serializable;

abstract class J2SE14ReadWriteLock$Sync implements Serializable
{
    transient int a;
    transient Thread b;
    transient int c;
    transient int d;
    transient int e;
    transient HashMap f;
    static final Integer g;
    
    J2SE14ReadWriteLock$Sync() {
        this.a = 0;
        this.b = null;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = new HashMap();
    }
    
    synchronized boolean a() {
        final boolean f = this.f();
        if (!f) {
            ++this.c;
        }
        return f;
    }
    
    synchronized boolean b() {
        final boolean g = this.g();
        if (!g) {
            ++this.d;
        }
        return g;
    }
    
    synchronized boolean c() {
        final boolean f = this.f();
        if (f) {
            --this.c;
        }
        return f;
    }
    
    synchronized boolean d() {
        final boolean g = this.g();
        if (g) {
            --this.d;
        }
        return g;
    }
    
    boolean e() {
        return (this.b == null && this.d == 0) || this.b == Thread.currentThread();
    }
    
    synchronized boolean f() {
        final Thread currentThread = Thread.currentThread();
        final Integer n = this.f.get(currentThread);
        if (n != null) {
            this.f.put(currentThread, new Integer(n + 1));
            ++this.a;
            return true;
        }
        if (this.e()) {
            this.f.put(currentThread, J2SE14ReadWriteLock$Sync.g);
            ++this.a;
            return true;
        }
        return false;
    }
    
    synchronized boolean g() {
        if (this.b == Thread.currentThread()) {
            ++this.e;
            return true;
        }
        if (this.e != 0) {
            return false;
        }
        if (this.a == 0 || (this.f.size() == 1 && this.f.get(Thread.currentThread()) != null)) {
            this.b = Thread.currentThread();
            this.e = 1;
            return true;
        }
        return false;
    }
    
    synchronized int h() {
        final Thread currentThread = Thread.currentThread();
        final Integer n = this.f.get(currentThread);
        if (n == null) {
            throw new IllegalMonitorStateException();
        }
        --this.a;
        if (n != J2SE14ReadWriteLock$Sync.g) {
            final int n2 = n - 1;
            this.f.put(currentThread, (n2 == 1) ? J2SE14ReadWriteLock$Sync.g : new Integer(n2));
            return 0;
        }
        this.f.remove(currentThread);
        if (this.e > 0) {
            return 0;
        }
        if (this.a == 0 && this.d > 0) {
            return 2;
        }
        return 0;
    }
    
    synchronized int i() {
        if (this.b != Thread.currentThread()) {
            throw new IllegalMonitorStateException();
        }
        --this.e;
        if (this.e > 0) {
            return 0;
        }
        this.b = null;
        if (this.c > 0 && this.e()) {
            return 1;
        }
        if (this.d > 0) {
            return 2;
        }
        return 0;
    }
    
    synchronized Thread j() {
        return this.b;
    }
    
    synchronized int k() {
        return this.a;
    }
    
    synchronized boolean l() {
        return this.b == Thread.currentThread();
    }
    
    synchronized int m() {
        return this.l() ? this.e : 0;
    }
    
    static {
        g = new Integer(1);
    }
}
