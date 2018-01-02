// 
// Decompiled by Procyon v0.5.30
// 

package dlt.a.c;

import java.util.Iterator;
import dlt.a.b.h;
import java.util.Hashtable;

public class b
{
    private Hashtable a;
    
    public b() {
        this.a = new Hashtable();
    }
    
    public void a(final h h) {
        this.a.put(h, new e(1.0));
    }
    
    public h[] if() {
        if (this.a.keySet().size() > 0) {
            return (h[])this.a.keySet().toArray(new h[this.a.keySet().size()]);
        }
        return null;
    }
    
    public double if(final h h) {
        if (this.a.containsKey(h)) {
            return this.a.get(h).a();
        }
        return 1.0;
    }
    
    public void a(final h h, final double n) {
        if (this.a.containsKey(h)) {
            this.a.get(h).a(n);
        }
        else {
            this.a.put(h, new e(n));
        }
    }
    
    public void a() {
        final Iterator<e> iterator = this.a.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().a(0.999999);
        }
    }
}
