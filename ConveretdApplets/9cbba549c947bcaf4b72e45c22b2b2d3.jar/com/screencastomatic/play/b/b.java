// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play.b;

import java.util.concurrent.ConcurrentHashMap;
import java.io.InputStream;
import java.util.Map;

public class b
{
    private static Map a;
    private long b;
    private String c;
    private int d;
    private Throwable e;
    private e f;
    private static boolean g;
    
    public b(final String c) {
        this.b = 5242880L;
        this.d = 0;
        this.c = c;
        this.f = com.screencastomatic.play.b.b.a.get(this.c);
        if (this.f == null) {
            this.f = new e(this, null);
            this.f.a = new d();
            com.screencastomatic.play.b.b.a.put(this.c, this.f);
        }
        this.f.a.a(c);
    }
    
    public InputStream a(final long n) {
        return this.f.c = new c(this, n);
    }
    
    public boolean a() {
        final g c = this.f.a.c(this.f.c.a().a);
        return c != null && c.d;
    }
    
    public boolean b() {
        return this.f.b != null && this.f.b.isAlive();
    }
    
    public void b(final long n) {
        this.b = Math.max(n, 614400L);
    }
    
    private long c() {
        return this.b / 204800L + 1L;
    }
    
    public static void a(final boolean g) {
        b.g = g;
    }
    
    static {
        b.a = new ConcurrentHashMap();
        b.g = false;
    }
}
