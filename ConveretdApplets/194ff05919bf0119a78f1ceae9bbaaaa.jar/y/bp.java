// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.util.Hashtable;

public abstract class bp implements cw
{
    public boolean[] e;
    private int a;
    public boolean c;
    public di a;
    
    public bp() {
        this.a = 0;
        this.c = false;
    }
    
    public abstract void a(final Hashtable p0, final di p1);
    
    public abstract void d();
    
    public abstract boolean a(final int p0);
    
    public abstract boolean a();
    
    public boolean c() {
        return this.a();
    }
    
    public final void i() {
        this.a.n();
    }
    
    public final void a(final cw cw) {
        this.c = false;
        this.a.a(cw);
        this.i();
    }
    
    public final void a(final int n, final boolean b) {
        if (this.e[n] != b) {
            this.e[n] = b;
            this.a += (b ? 1 : -1);
            this.i();
        }
    }
    
    public final boolean d() {
        return this.a == this.e.length && !this.c;
    }
    
    public boolean b(final int n) {
        return false;
    }
    
    public void g() {
        this.i();
        if (this.c) {
            this.a.m();
        }
    }
    
    public boolean b() {
        return false;
    }
    
    public static boolean c(final Hashtable hashtable) {
        return hashtable.get("rd") != null;
    }
    
    public static boolean d(final Hashtable hashtable) {
        return hashtable.get("pl") == null;
    }
    
    public static boolean e(final Hashtable hashtable) {
        return hashtable.get("ff") == null;
    }
}
