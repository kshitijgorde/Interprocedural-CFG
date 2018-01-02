// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.util.Vector;

public final class ew
{
    private static ew a;
    private e a;
    private Vector a;
    
    private ew(final e a) {
        this.a = a;
        (this.a = new Vector()).addElement(new da());
    }
    
    public static ew a() {
        return ew.a;
    }
    
    public final void a(final z z) {
        this.a.addElement(z);
    }
    
    public final void a(final String s, final e e) {
        final e a = e.a(s, e);
        (this = this).a = a;
        this.b("Setting log level to " + a.toString());
    }
    
    private void a(final e e, final Object o, final Throwable t) {
        if (e.a(this.a)) {
            final w w = new w(e, o, t);
            for (int i = 0; i < this.a.size(); ++i) {
                ((z)this.a.elementAt(i)).a(w);
            }
        }
    }
    
    public final void a(final Object o) {
        this.a(e.f, o, null);
    }
    
    public final void b(final Object o) {
        this.a(e.e, o, null);
    }
    
    public final void c(final Object o) {
        this.a(e.d, o, null);
    }
    
    public final void d(final Object o) {
        this.a(e.c, o, null);
    }
    
    public final void e(final Object o) {
        this.a(e.b, o, null);
    }
    
    public final void a(final Object o, final Throwable t) {
        this.a(e.d, o, t);
    }
    
    public final void b(final Object o, final Throwable t) {
        this.a(e.c, o, t);
    }
    
    public final void c(final Object o, final Throwable t) {
        this.a(e.b, o, t);
    }
    
    static {
        ew.a = new ew(e.a);
    }
}
