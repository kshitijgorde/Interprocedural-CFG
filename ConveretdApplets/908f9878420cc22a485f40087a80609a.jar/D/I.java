// 
// Decompiled by Procyon v0.5.30
// 

package D;

import C.Z;
import core.RE;
import java.util.Vector;
import core.N;

public final class I
{
    private final String a;
    private N[] b;
    private boolean c;
    private boolean d;
    private boolean e;
    private int f;
    private final Thread g;
    private static final Vector h;
    
    static {
        h = new Vector(1);
    }
    
    public static final I a(final RE re) {
        final String parameter = re.getParameter(I.I.I(615));
        final boolean b;
        if (b = !I.h.contains(parameter)) {
            I.h.addElement(parameter);
        }
        return new I(re, parameter, b);
    }
    
    private I(final RE re, final String a, final boolean b) {
        this.b = new N[0];
        this.c = false;
        this.d = false;
        this.e = false;
        this.f = 500;
        this.a = a;
        (this.g = new Thread(new C(this, re, a, b, re.getDocumentBase()))).start();
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
