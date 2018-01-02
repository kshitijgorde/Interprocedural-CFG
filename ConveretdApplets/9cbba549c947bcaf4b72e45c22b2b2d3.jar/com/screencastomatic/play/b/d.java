// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play.b;

import java.util.Iterator;
import com.screencastomatic.play.q;
import java.util.TreeMap;

public class d
{
    private TreeMap a;
    private static int b;
    private String c;
    
    public d() {
        this.a = new TreeMap();
        this.c = "";
    }
    
    public synchronized void a(final String c) {
        if (this.c.equalsIgnoreCase(c)) {
            return;
        }
        q.a("Clearing buffers for new url.");
        this.c = c;
        this.a.clear();
    }
    
    public synchronized g a(final int n) {
        g g = this.a.get(n);
        if (g == null) {
            g = new g(n);
            this.a.put(n, g);
            if (this.a.size() > d.b) {
                for (final Integer n2 : this.a.keySet()) {
                    if (Math.abs(n - n2) > d.b / 2 - 1) {
                        if (d.b > 1) {
                            q.a("Too many in cache so removing: " + n2);
                        }
                        this.a.remove(n2);
                        break;
                    }
                }
            }
        }
        return g;
    }
    
    public synchronized g b(final int n) {
        final Iterator iterator = this.a.keySet().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(n) && iterator.hasNext()) {
                return this.a(iterator.next());
            }
        }
        return null;
    }
    
    public synchronized g c(int n) {
        if (this.a.isEmpty()) {
            return null;
        }
        for (final Integer n2 : this.a.keySet()) {
            if (n2 < n) {
                continue;
            }
            if (n2 != n) {
                break;
            }
            ++n;
        }
        return this.a(n - 1);
    }
    
    static {
        d.b = 100;
    }
}
