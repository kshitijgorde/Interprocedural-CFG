// 
// Decompiled by Procyon v0.5.30
// 

package D;

import C.Z;
import core.E;
import core.RevolverEngine;
import java.util.Hashtable;
import core.T;
import java.net.URL;

public final class I
{
    private final URL a;
    private T[] b;
    private boolean c;
    private boolean d;
    private boolean e;
    private int f;
    private final Thread g;
    private static final Hashtable h;
    
    static {
        h = new Hashtable(1);
    }
    
    public static final I a(final RevolverEngine revolverEngine) {
        String s;
        if ((s = revolverEngine.getParameter(I.I.I(628))) == null) {
            s = I.I.I(745);
        }
        I i;
        if ((i = I.h.get(s)) == null) {
            i = new I(s, revolverEngine.a(), revolverEngine);
            I.h.put(s, i);
        }
        return i;
    }
    
    private I(final String s, final URL url, final RevolverEngine revolverEngine) {
        this.b = new T[0];
        this.c = false;
        this.d = false;
        this.e = false;
        this.f = 500;
        final URL documentBase = revolverEngine.getDocumentBase();
        this.a = E.a(url, I.I.I(771) + s);
        (this.g = new Thread(new C(this, url, s, revolverEngine, documentBase))).start();
    }
    
    public final D.Z a(final RevolverEngine revolverEngine, final Z z) {
        try {
            this.g.join();
        }
        catch (InterruptedException ex) {}
        return new D.Z(revolverEngine, this.a, z, this.b, this.c, this.d, this.e, this.f);
    }
    
    static final void a(final I i, final boolean c) {
        i.c = c;
    }
    
    static final void b(final I i, final boolean d) {
        i.d = d;
    }
    
    static final void c(final I i, final boolean e) {
        i.e = e;
    }
    
    static final void a(final I i, final int f) {
        i.f = f;
    }
    
    static final void a(final I i, final T[] b) {
        i.b = b;
    }
    
    static final T[] a(final I i) {
        return i.b;
    }
}
