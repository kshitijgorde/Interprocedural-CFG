// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.HttpMapClient;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Collections;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.Map;
import java.io.File;

public final class l
{
    private File a;
    private a b;
    private final Map c;
    private final List d;
    private final List e;
    private String f;
    private final List g;
    private boolean h;
    
    public l(final String s, final a b, final File a) {
        this.c = new ConcurrentHashMap(27);
        this.d = Collections.synchronizedList(new ArrayList<Object>());
        this.e = Collections.synchronizedList(new ArrayList<Object>());
        this.g = new CopyOnWriteArrayList();
        this.h = false;
        this.h = false;
        this.b = b;
        this.f = s + "/service/map/backgroundMap" + "/gbm";
        boolean b2;
        if (a == null) {
            b2 = false;
        }
        else if (!a.exists()) {
            System.err.println("Cache directory " + a + " does not exist.");
            b2 = false;
        }
        else if (!a.isDirectory()) {
            System.err.println("Cache " + a + " is not a directory.");
            b2 = false;
        }
        else if (!a.canRead()) {
            System.err.println("Cache directory " + a + " is not readable.");
            b2 = false;
        }
        else if (!a.canWrite()) {
            System.err.println("Cache directory " + a + " is not writable.");
            b2 = false;
        }
        else {
            b2 = true;
        }
        if (b2) {
            this.a = a;
            return;
        }
        this.a = null;
    }
    
    public final void a(final boolean h) {
        this.h = h;
    }
    
    public final boolean a() {
        return this.h;
    }
    
    private synchronized h d(m m) {
        h h;
        if (this.c.containsKey(m)) {
            h = this.c.get(m);
        }
        else {
            final l l = this;
            m = m;
            this = l;
            final h h2 = new h(this, m);
            if (this.c.size() == 27) {
                long n = Long.MAX_VALUE;
                Object o = null;
                for (final m i : this.c.keySet()) {
                    final long a;
                    if ((a = this.c.get(i).d) < n) {
                        n = a;
                        o = i;
                    }
                }
                this.c.remove(o);
            }
            this.c.put(m, h2);
            h = h2;
        }
        h.d = System.currentTimeMillis();
        return h;
    }
    
    public final void a(final m m) {
        final File file;
        if (this.a != null && (file = new File(this.a, m.toString())).exists()) {
            file.delete();
        }
        this.c.remove(m);
        if (this.d.contains(m)) {
            this.e.add(m);
            if (this.h) {
                System.out.println("Double render failure for " + m.toString());
            }
        }
        else {
            this.d.add(m);
        }
    }
    
    public final byte[] b(final m m) {
        if (this.e.contains(m)) {
            return null;
        }
        return COM.NextBus.HttpMapClient.h.b(this.d(m));
    }
    
    public final void a(final n n) {
        this.g.add(n);
    }
    
    protected final synchronized void c(final m m) {
        final Iterator<n> iterator = this.g.iterator();
        while (iterator.hasNext()) {
            iterator.next().b(m);
        }
    }
}
