// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core;

import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.Serializable;

public enum f implements Serializable
{
    a("UPPER", 0) {
        x(final String s, final int n) {
        }
        
        public final r a(final List list) {
            return f.a(list, B.a);
        }
    }, 
    b("LOWER", 1) {
        v(final String s, final int n) {
        }
        
        public final r a(final List list) {
            return f.a(list, B.b);
        }
    }, 
    c("MAJORITY", 2) {
        y(final String s, final int n) {
        }
        
        public final r a(final List list) {
            final HashMap<Object, ArrayList<C>> hashMap = new HashMap<Object, ArrayList<C>>();
            for (final C c : list) {
                if (!hashMap.containsKey(c.b.toLowerCase())) {
                    hashMap.put(c.b.toLowerCase(), new ArrayList<C>());
                }
                ((ArrayList<C>)hashMap.get(c.b.toLowerCase())).add(c);
            }
            final r r = new r();
            final Iterator<Map.Entry<String, ArrayList<C>>> iterator2 = hashMap.entrySet().iterator();
            while (iterator2.hasNext()) {
                final Map.Entry<String, ArrayList<C>> entry;
                final String s = (entry = iterator2.next()).getKey();
                final ArrayList<C> list2;
                if ((list2 = entry.getValue()).size() == 1) {
                    r.a((C)list2.get(0));
                }
                else {
                    String b = s;
                    double a = 0.0;
                    double n = 0.0;
                    final Iterator<Object> iterator3 = list2.iterator();
                    while (iterator3.hasNext()) {
                        final C c2;
                        if ((c2 = iterator3.next()).a > a) {
                            a = c2.a;
                            b = c2.b;
                        }
                        n += c2.a;
                    }
                    r.a(new C(n, b));
                }
            }
            return r;
        }
    };
    
    static {
        d = new f[] { f.a, f.b, f.c };
    }
    
    private f(final String s, final int n, final byte b) {
    }
    
    public abstract r a(final List p0);
    
    static /* synthetic */ r a(List list, B b) {
        final List<C> list2 = list;
        b = b;
        list = list2;
        final HashMap<Object, Double> hashMap = new HashMap<Object, Double>();
        for (final C c : list) {
            final String a = b.a(c.b);
            if (hashMap.containsKey(a)) {
                hashMap.put(a, hashMap.get(a) + c.a);
            }
            else {
                hashMap.put(a, c.a);
            }
        }
        final r r = new r();
        for (final Map.Entry<String, Double> entry : hashMap.entrySet()) {
            r.a(new C(entry.getValue(), entry.getKey()));
        }
        return r;
    }
}
