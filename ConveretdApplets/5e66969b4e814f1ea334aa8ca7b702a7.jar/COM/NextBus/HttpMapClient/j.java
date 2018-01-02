// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.HttpMapClient;

import java.util.Collection;
import java.util.Iterator;
import COM.NextBus.Predictor2.rmi.StopTupleBean;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.Map;

final class j
{
    private e d;
    Map a;
    Map b;
    Map c;
    private final Set e;
    private final Set f;
    private long g;
    private Set h;
    
    j() {
        this.d = new e();
        this.a = new Hashtable();
        this.b = new Hashtable();
        this.c = new Hashtable();
        this.e = new HashSet();
        this.f = new HashSet();
        this.g = 0L;
        this.h = new HashSet();
    }
    
    public final synchronized e a() {
        return this.d;
    }
    
    public final synchronized void a(final e d) {
        this.d = d;
    }
    
    public final synchronized long b() {
        return this.g;
    }
    
    public final synchronized void c() {
        this.g = 0L;
    }
    
    public final synchronized List d() {
        if (this.h == null) {
            return null;
        }
        final ArrayList<String> list = new ArrayList<String>(this.h.size());
        final Iterator<StopTupleBean> iterator = this.h.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next().a());
        }
        return list;
    }
    
    public final synchronized void a(final Set h) {
        this.h = h;
    }
    
    public final synchronized List e() {
        return new ArrayList(this.e);
    }
    
    public final synchronized List f() {
        return new ArrayList(this.f);
    }
    
    public final synchronized void a(final long g) {
        final Iterator<String> iterator = this.f.iterator();
        while (iterator.hasNext()) {
            this.e.add(iterator.next());
        }
        this.f.clear();
        this.g = g;
    }
    
    public final synchronized void a(final String s, final boolean b) {
        if (b) {
            if (!this.e.contains(s)) {
                this.f.add(s);
            }
        }
        else {
            this.e.remove(s);
            this.f.remove(s);
        }
    }
}
