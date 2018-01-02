// 
// Decompiled by Procyon v0.5.30
// 

package D;

import C.Z;
import core.K;
import core.RE;
import java.util.Hashtable;
import core.N;
import java.net.URL;

public final class I
{
    private final URL a;
    private N[] b;
    private boolean c;
    private boolean d;
    private boolean e;
    private int f;
    private final Thread g;
    private static final Hashtable h;
    
    static {
        h = new Hashtable(1);
    }
    
    public static final I a(final RE re) {
        String s;
        if ((s = re.getParameter(I.I.I(603))) == null) {
            s = I.I.I(733);
        }
        I i;
        if ((i = I.h.get(s)) == null) {
            i = new I(s, re.a(), re);
            I.h.put(s, i);
        }
        return i;
    }
    
    private I(final String s, final URL url, final RE re) {
        this.b = new N[0];
        this.c = false;
        this.d = false;
        this.e = false;
        this.f = 500;
        final URL documentBase = re.getDocumentBase();
        this.a = K.a(url, I.I.I(759) + s);
        (this.g = new Thread(new C(this, url, s, re, documentBase))).start();
    }
    
    public final D.Z a(final RE re, final Z z) {
        try {
            this.g.join();
        }
        catch (InterruptedException ex) {}
        return new D.Z(re, this.a, z, this.b, this.c, this.d, this.e, this.f);
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
    
    static final void a(final I i, final N[] b) {
        i.b = b;
    }
    
    static final N[] a(final I i) {
        return i.b;
    }
}
