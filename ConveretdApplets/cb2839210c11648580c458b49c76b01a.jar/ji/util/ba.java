// 
// Decompiled by Procyon v0.5.30
// 

package ji.util;

import ji.io.h;

public class ba
{
    private boolean a;
    private boolean b;
    private String c;
    private boolean d;
    
    public ba() {
        this(null);
    }
    
    public ba(final String c) {
        this.a = false;
        this.b = true;
        this.d = false;
        this.c = c;
        this.b = true;
    }
    
    public final void a(final boolean b) {
        this.b = b;
        try {
            if (!b) {
                this.notifyAll();
            }
        }
        catch (Exception ex) {}
    }
    
    public final boolean a(final int n) {
        if (i.c(36)) {
            h.d("lock", "Lock: lock synchronizing ".concat(String.valueOf(String.valueOf(n))));
        }
        synchronized (this) {
            if (i.c(36)) {
                h.d("lock", "Lock: lock synchronized ".concat(String.valueOf(String.valueOf(n))));
            }
            if (this.b) {
                final boolean b = this.b(n);
                if (b) {
                    this.d = true;
                }
                if (i.c(36)) {
                    h.d("lock", "Lock: lock end synchronized ".concat(String.valueOf(String.valueOf(n))));
                }
                // monitorexit(this)
                return b;
            }
            return false;
        }
    }
    
    private final boolean b(final int n) {
        if (this.a) {
            try {
                Thread.yield();
                if (!ji.util.d.b9() && ji.util.d.r) {
                    this.wait();
                }
            }
            catch (Exception ex) {}
        }
        return this.a = true;
    }
    
    public final boolean a() {
        if (i.c(36)) {
            h.d("lock", "Lock: unlock synchronizing");
        }
        synchronized (this) {
            if (i.c(36)) {
                h.d("lock", "Lock: unlock synchronized");
            }
            final boolean b = this.b();
            if (i.c(36)) {
                h.d("lock", "Lock: unlock end synchronized");
            }
            this.d = false;
            return b;
        }
    }
    
    private final boolean b() {
        this.a = false;
        this.notify();
        Thread.yield();
        return true;
    }
}
