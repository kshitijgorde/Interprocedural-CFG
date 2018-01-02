// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import wordle.core.fitness.PlacementStrategy;
import java.util.Comparator;
import java.util.Arrays;
import wordle.core.b.b;
import java.awt.geom.Rectangle2D;
import java.awt.Font;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import wordle.core.c.e;
import java.lang.ref.WeakReference;
import wordle.core.b.c;
import wordle.core.f.a;

public final class K implements Iterable
{
    private int a;
    private a b;
    private Double c;
    private Double d;
    private Double e;
    private Double f;
    private final c[] g;
    private WeakReference h;
    
    public static K a(r a, final e e, final wordle.core.b.a.e e2, final double n) {
        final ArrayList<c> list = new ArrayList<c>();
        for (final C c : a = a.a(e.h).a(e.g).a(e.f).a(e.i).a(e.e).a(n)) {
            list.add(new c(c, e.a, e2.a(c), e.d.a(c)));
        }
        return new K(list);
    }
    
    public final String a() {
        final StringBuilder sb = new StringBuilder();
        c[] g;
        for (int length = (g = this.g).length, i = 0; i < length; ++i) {
            final c c;
            if ((c = g[i]).e()) {
                if (sb.length() > 0) {
                    sb.append("\n");
                }
                sb.append(c.a());
            }
        }
        return sb.toString();
    }
    
    public static K a(String s, final Font font) {
        final ArrayList<c> list = new ArrayList<c>();
        String[] split;
        for (int length = (split = s.split("\n")).length, i = 0; i < length; ++i) {
            s = split[i];
            list.add(c.a(s, font));
        }
        return new K(list);
    }
    
    private K(final c[] array) {
        this.b = null;
        this.c = Double.MAX_VALUE;
        this.d = -1.7976931348623157E308;
        this.e = Double.MAX_VALUE;
        this.f = -1.7976931348623157E308;
        this.h = null;
        this.g = array.clone();
    }
    
    private K(final Collection collection) {
        this.b = null;
        this.c = Double.MAX_VALUE;
        this.d = -1.7976931348623157E308;
        this.e = Double.MAX_VALUE;
        this.f = -1.7976931348623157E308;
        this.h = null;
        this.g = collection.toArray(new c[collection.size()]);
    }
    
    public final void a(final Rectangle2D rectangle2D) {
        wordle.core.b.b.a();
        this.a = 0;
        this.b = new a(rectangle2D, 200.0);
        c[] g;
        for (int length = (g = this.g).length, i = 0; i < length; ++i) {
            g[i].c();
        }
    }
    
    public final void b() {
        c[] g;
        for (int length = (g = this.g).length, i = 0; i < length; ++i) {
            g[i].b();
        }
    }
    
    public final boolean a(c c) {
        final c i;
        if ((i = c.i()) != null) {
            final K k = this;
            ++k.a;
            if (c.a(i)) {
                return true;
            }
        }
        if (this.b.a(c)) {
            return true;
        }
        final K j = this;
        c = c;
        this = j;
        j.b.b(c);
        this.b(c);
        return false;
    }
    
    private void b(final c c) {
        final Rectangle2D h = c.h();
        this.d = Math.max(this.d, h.getMaxX());
        this.c = Math.min(this.c, h.getMinX());
        this.f = Math.max(this.f, h.getMaxY());
        this.e = Math.min(this.e, h.getMinY());
    }
    
    public final boolean c() {
        c[] g;
        for (int length = (g = this.g).length, i = 0; i < length; ++i) {
            if (!g[i].e()) {
                return true;
            }
        }
        return false;
    }
    
    public final c a(final double n, final double n2) {
        if (this.h == null || this.h.get() == null) {
            final c[] array = new c[this.g.length];
            System.arraycopy(this.g, 0, array, 0, this.g.length);
            Arrays.sort(array, wordle.core.b.c.b);
            this.h = new WeakReference(array);
        }
        c[] array2;
        for (int length = (array2 = (c[])this.h.get()).length, i = 0; i < length; ++i) {
            final c c;
            if ((c = array2[i]).a(n, n2)) {
                return c;
            }
        }
        return null;
    }
    
    final void a(final n n) {
        c[] g;
        for (int length = (g = this.g).length, i = 0; i < length; ++i) {
            g[i].a(n);
        }
    }
    
    public final int d() {
        return this.g.length;
    }
    
    public final void a(final PlacementStrategy placementStrategy) {
        placementStrategy.a(this.g);
    }
    
    public final c[] e() {
        return this.g;
    }
    
    public final K a(final wordle.core.b.a.e e) {
        final c[] array = new c[this.g.length];
        for (int i = 0; i < this.g.length; ++i) {
            array[i] = this.g[i].a(e.a(this.g[i].k()));
        }
        return new K(array);
    }
    
    public final K a(final z z) {
        final c[] array = new c[this.g.length];
        for (int i = 0; i < this.g.length; ++i) {
            array[i] = this.g[i].b(z.a(this.g[i].k()));
        }
        return new K(array);
    }
    
    public final K a(final Font font) {
        final c[] array = new c[this.g.length];
        for (int i = 0; i < this.g.length; ++i) {
            array[i] = this.g[i].a(font);
        }
        return new K(array);
    }
    
    public final K a(final String s) {
        double max = 0.0;
        c[] g;
        for (int length = (g = this.g).length, i = 0; i < length; ++i) {
            final c c;
            if (!(c = g[i]).k().b.equals(s)) {
                max = Math.max(max, c.k().a);
            }
        }
        final ArrayList<c> list = new ArrayList<c>();
        c[] g2;
        for (int length2 = (g2 = this.g).length, j = 0; j < length2; ++j) {
            final c c2;
            if (!(c2 = g2[j]).k().b.equals(s)) {
                list.add(c2.a(1000.0 / max));
            }
        }
        return new K(list);
    }
    
    public final K b(final z z) {
        final c[] array = new c[this.g.length];
        for (int i = 0; i < this.g.length; ++i) {
            array[i] = this.g[i].a(z);
        }
        return new K(array);
    }
    
    public final K f() {
        final c[] array = new c[this.g.length];
        for (int i = 0; i < this.g.length; ++i) {
            array[i] = this.g[i].d();
        }
        return new K(array);
    }
    
    public final D a(final double n) {
        double n2 = 0.0;
        double max = 0.0;
        double max2 = 0.0;
        c[] g;
        for (int length = (g = this.g).length, i = 0; i < length; ++i) {
            final c c = g[i];
            n2 += c.j();
            max = Math.max(max, c.f());
            max2 = Math.max(max2, c.g());
        }
        return new D(Math.max(max, Math.sqrt(n * n2)) * 1.2, Math.max(max2, Math.sqrt(n2 / n)) * 1.5);
    }
    
    public final Rectangle2D.Double g() {
        this.c = Double.MAX_VALUE;
        this.d = -1.7976931348623157E308;
        this.e = Double.MAX_VALUE;
        this.f = -1.7976931348623157E308;
        c[] g;
        for (int length = (g = this.g).length, i = 0; i < length; ++i) {
            this.b(g[i]);
        }
        return new Rectangle2D.Double(this.c, this.e, this.d - this.c, this.f - this.e);
    }
    
    private String h() {
        final StringBuilder sb = new StringBuilder();
        c[] g;
        for (int length = (g = this.g).length, i = 0; i < length; ++i) {
            sb.append("          ").append(g[i]).append("\n");
        }
        return sb.toString();
    }
    
    public final String toString() {
        final StringBuilder sb;
        (sb = new StringBuilder()).append("Drawables ( ").append(super.toString()).append("\n            ").append("drawables = ").append((this.g.length > 1) ? ("[" + this.g.length + " drawables]") : this.h()).append("\n            ").append("byAreaAscending = ").append(this.h).append("\n            ").append(" )");
        return sb.toString();
    }
    
    public final Iterator iterator() {
        return new g(this);
    }
}
