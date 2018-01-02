import java.util.Set;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.Map;
import java.util.AbstractMap;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_bR extends AbstractMap
{
    private final Map a;
    private final int a;
    private final LinkedList a;
    private final ReferenceQueue a;
    
    public rp_bR() {
        this(100);
    }
    
    public rp_bR(final int a) {
        this.a = new HashMap();
        this.a = new LinkedList();
        this.a = new ReferenceQueue();
        this.a = a;
    }
    
    public final Object get(final Object o) {
        Object value = null;
        final SoftReference<Object> softReference;
        if ((softReference = this.a.get(o)) != null) {
            if ((value = softReference.get()) == null) {
                this.a.remove(o);
            }
            else {
                this.a.addFirst(value);
                if (this.a.size() > this.a) {
                    this.a.removeLast();
                }
            }
        }
        return value;
    }
    
    private void a() {
        rp_ga rp_ga;
        while ((rp_ga = (rp_ga)this.a.poll()) != null) {
            this.a.remove(rp_ga.a);
        }
    }
    
    public final Object put(final Object o, final Object o2) {
        this.a();
        return this.a.put(o, new rp_ga(o2, o, this.a));
    }
    
    public final Object remove(final Object o) {
        this.a();
        return this.a.remove(o);
    }
    
    public final void clear() {
        this.a.clear();
        this.a();
        this.a.clear();
    }
    
    public final int size() {
        this.a();
        return this.a.size();
    }
    
    public final Set entrySet() {
        throw new UnsupportedOperationException();
    }
}
