// 
// Decompiled by Procyon v0.5.30
// 

package ji.v1event;

import ji.util.e;
import ji.io.h;
import ji.util.d;
import ji.ext.v;

public class bp
{
    bq a;
    v b;
    static boolean c;
    
    public bp(final as as, final String s) {
        this.a = null;
        this.b = null;
        try {
            final boolean b = !d.cr(s);
            if (b && d.dp()) {
                try {
                    this.a = (bq)d.a2("ji.v1event.jiJava2MouseWheelListener");
                }
                catch (Throwable t) {
                    this.a = null;
                    if (!bp.c) {
                        bp.c = true;
                        h.d(s, "Unable to load mouse wheel listener: ".concat(String.valueOf(String.valueOf(t.getMessage()))));
                    }
                }
                if (this.a != null) {
                    this.a.addMouseWheelListener(as);
                }
            }
            else if (!b && !e.az()) {
                if (this.b == null) {
                    this.b = new v();
                }
                this.b.a(as);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void a(final as as) {
        if (this.a != null) {
            this.a.removeMouseWheelListener(as);
            this.a = null;
        }
        else if (this.b != null) {
            this.b.b(as);
        }
    }
    
    static {
        bp.c = false;
    }
}
