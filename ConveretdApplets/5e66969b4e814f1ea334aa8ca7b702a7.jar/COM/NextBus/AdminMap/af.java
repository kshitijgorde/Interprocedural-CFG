// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import COM.NextBus.HttpMapClient.ConnectionException;
import COM.NextBus.HttpMapClient.ResponseComponent;
import COM.NextBus.HttpMapClient.Update;
import COM.NextBus.Predictor2Comm.TitleInfo;
import COM.NextBus.HttpMapClient.e;
import java.util.Hashtable;
import COM.NextBus.util.c;
import java.util.Iterator;
import COM.NextBus.HttpMapClient.l;
import java.util.Collections;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import COM.NextBus.HttpMapClient.b;
import COM.NextBus.HttpMapClient.a;

public final class af
{
    private final O a;
    private final ai b;
    private final a c;
    private final b d;
    private final List e;
    private final Map f;
    private volatile boolean g;
    private static int h;
    private static int i;
    private static int j;
    
    public af(final O a) {
        this.b = new ai();
        this.a = a;
        final List w = a.w();
        final ArrayList<t> list = new ArrayList<t>();
        this.f = new HashMap();
        for (final String s : w) {
            final t t = new t(a, s);
            this.f.put(s, t);
            list.add(t);
        }
        this.e = Collections.unmodifiableList((List<?>)list);
        this.c = new a(this.a.c(), this.a.d(), this.a.z(), this.a.w(), this.a.e(), this.a.f());
        this.a.e = this.c;
        this.d = new b(new l(this.a.c(), this.a.e, this.a.r()));
    }
    
    final b a() {
        return this.d;
    }
    
    public final t a(final String s) {
        return this.f.get(s);
    }
    
    public final List b() {
        return this.e;
    }
    
    private final synchronized void j() {
        final String string = this.a.k().toLowerCase() + "s";
        this.a.j.a(COM.NextBus.AdminMap.a.b("Requesting list of") + " " + string + " " + COM.NextBus.AdminMap.a.b("from server."));
        this.c.a();
        final String b = COM.NextBus.AdminMap.a.b("Requesting information for all agencies.");
        this.a.b.c.setEnabled(false);
        this.a.j.a(b);
        COM.NextBus.util.c.a(this.c.a(this.a.u()).e().a());
        final Hashtable<String, List> hashtable = new Hashtable<String, List>();
        List w;
        for (final String s : w = this.a.w()) {
            this.a.j.a(COM.NextBus.AdminMap.a.b("Requesting information for agency") + " " + s + ".");
            this.a.b.c.setEnabled(false);
            final e a;
            final TitleInfo c = (a = this.c.a(s)).c();
            final TitleInfo d = a.d();
            if (c != null) {
                this.a.a(s, c);
            }
            if (d != null) {
                this.a.b(s, d);
            }
            final t t = this.f.get(s);
            final List b2 = this.c.b(s);
            t.d();
            t.a(b2);
            hashtable.put(s, b2);
        }
        this.a.j.a(COM.NextBus.AdminMap.a.b("Requesting information for all") + " " + string + ".");
        final Iterator<String> iterator2 = w.iterator();
        while (iterator2.hasNext()) {
            this.c.c(iterator2.next());
        }
        final Iterator<String> iterator3 = w.iterator();
        while (iterator3.hasNext()) {
            ((t)this.f.get(iterator3.next())).i();
        }
        this.a.j.a(COM.NextBus.AdminMap.a.b("Loading default configuration."));
        this.a.K().a(r.a);
        this.a.j.a(COM.NextBus.AdminMap.a.b("Loading user configuration."));
        this.a.D();
        this.a.f.c();
        this.a.E();
        if (this.a.o()) {
            this.a.f.a(false);
        }
        this.a.b.c.setEnabled(true);
        this.a.a = true;
    }
    
    public final void c() {
        new Thread(new ar(this), "Connection Starter").start();
    }
    
    final void d() {
        final Iterator<String> iterator = this.a.w().iterator();
        while (iterator.hasNext()) {
            ((t)this.f.get(iterator.next())).a();
        }
    }
    
    final boolean a(final Map map) {
        boolean b = false;
        for (final String s : this.a.w()) {
            final t t = this.f.get(s);
            final Update update;
            final int d = (update = map.get(s)).d();
            final int a;
            if ((a = this.c.a(s).a()) != -1 && d != -1 && a != d) {
                b = true;
            }
            t.a(update.a());
            t.a(update.b(), update.c());
            t.k();
        }
        return b;
    }
    
    public final void e() {
        this.g = true;
    }
    
    public final void b(final Map map) {
        this.c.a(map);
        if (map != null) {
            this.g = true;
        }
    }
    
    private synchronized void k() {
        final Thread thread;
        (thread = new Thread(new Z(this), "Updater")).setDaemon(true);
        thread.start();
    }
    
    public final void f() {
        final Iterator<t> iterator = this.e.iterator();
        while (iterator.hasNext()) {
            iterator.next().e();
        }
    }
    
    public final void a(final o o) {
        final Iterator<t> iterator = this.e.iterator();
        while (iterator.hasNext()) {
            iterator.next().a(o);
        }
    }
    
    public final void a(final boolean b, final o o) {
        final Iterator<t> iterator = this.e.iterator();
        while (iterator.hasNext()) {
            iterator.next().a(b, o);
        }
    }
    
    static {
        af.h = 0;
        af.i = 1;
        af.j = 2;
    }
}
