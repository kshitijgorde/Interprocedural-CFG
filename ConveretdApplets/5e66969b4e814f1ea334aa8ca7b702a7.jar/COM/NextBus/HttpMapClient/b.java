// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.HttpMapClient;

import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.util.Iterator;
import java.awt.Color;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.awt.Toolkit;
import java.util.Map;

public final class b implements n
{
    private final Map a;
    private l b;
    private final Toolkit c;
    private final g d;
    private final List e;
    private static long f;
    
    public b(final l b) {
        this.a = new ConcurrentHashMap(36);
        this.c = Toolkit.getDefaultToolkit();
        this.e = new CopyOnWriteArrayList();
        (this.b = b).a(this);
        this.d = new g(Color.white, 3, 2);
    }
    
    public final void a(final boolean b) {
        this.b.a(b);
        if (this.b.a()) {
            this.a("TileImageSource debug on");
        }
    }
    
    public final boolean a() {
        return this.b.a();
    }
    
    private void a(final String s) {
        if ((this = this).b.a()) {
            System.out.println(s);
        }
    }
    
    public final void a(final d d) {
        this.e.add(d);
    }
    
    private void c(final m m) {
        final Iterator<d> iterator = this.e.iterator();
        while (iterator.hasNext()) {
            iterator.next().a(m);
        }
    }
    
    private i d(final m m) {
        if (this.b.a()) {
            this.a("Creating tile: " + m);
        }
        final i i = new i(this, m);
        if (this.b.a()) {
            this.a("_tilesByTileParameters.size() is: " + this.a.size());
        }
        if (this.a.size() == 36) {
            long n = Long.MAX_VALUE;
            Object o = null;
            for (final m j : this.a.keySet()) {
                final long a;
                if ((a = this.a.get(j).d) < n) {
                    n = a;
                    o = j;
                }
            }
            if (this.b.a()) {
                this.a("Chucking tile: " + o + " use time " + n);
            }
            this.a.remove(o);
        }
        this.a.put(m, i);
        return i;
    }
    
    private synchronized void e(final m m) {
        if (this.b.a()) {
            this.a("Render failed for tile " + m.toString());
        }
        this.b.a(m);
        this.a.remove(m);
        this.c(m);
    }
    
    private synchronized i f(final m m) {
        i d;
        if (this.a.containsKey(m)) {
            d = this.a.get(m);
        }
        else {
            if (this.b.a()) {
                this.a("Tile not in memory cache: " + m.toString());
            }
            d = this.d(m);
        }
        d.d = COM.NextBus.HttpMapClient.b.f++;
        return d;
    }
    
    public final Image a(final m m) {
        return i.b(this.f(m));
    }
    
    public final void b(final m m) {
        final i i;
        if ((i = this.a.get(m)) != null) {
            COM.NextBus.HttpMapClient.i.c(i);
        }
    }
    
    static {
        b.f = 0L;
    }
}
