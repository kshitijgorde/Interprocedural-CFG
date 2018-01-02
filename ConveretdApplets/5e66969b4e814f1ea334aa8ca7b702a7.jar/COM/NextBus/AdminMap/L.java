// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.text.SimpleDateFormat;
import java.util.Map;

public final class L implements Cloneable
{
    private final Map b;
    private final ae c;
    private final A d;
    public final I a;
    private final O e;
    private final SimpleDateFormat f;
    
    public L(final O e) {
        this.b = new ConcurrentHashMap();
        this.e = e;
        this.c = new ae(this.b);
        this.d = new A(e, this.b);
        this.a = new I(e, this.b);
        (this.f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z")).setTimeZone(e.q());
    }
    
    public final void a() {
        this.c.a();
        this.d.a();
        this.a.a();
        if (this.e.j()) {
            this.a.j(true);
        }
    }
    
    public final void b() {
        this.c.a();
        this.d.b();
        final I a;
        (a = this.a).f(false);
        a.l(false);
        a.d(false);
        a.a(false);
        a.b(false);
        a.m(false);
        a.c(false);
        a.j(false);
        a.k(false);
        a.o(false);
    }
    
    public final Map c() {
        return this.b;
    }
    
    public final void a(final Map map) {
        if (map == null) {
            return;
        }
        for (final String s : map.keySet()) {
            this.b.put(s, map.get(s));
        }
    }
    
    public final ae d() {
        return this.c;
    }
    
    public final A e() {
        return this.d;
    }
    
    public final void f() {
        final String property = System.getProperty("java.version", "unknown");
        final String property2 = System.getProperty("java.vendor", "unknown");
        this.b.put("java.version", property);
        this.b.put("java.vendor", property2);
    }
    
    public final void g() {
        Serializable format = new Date();
        synchronized (this.f) {
            format = this.f.format((Date)format);
        }
        final L l;
        l.b.put("prefs.saved.timestamp", format);
    }
}
