// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter.jpeg2k;

import ji.filter.jpeg2k.tile.k5;
import ji.image.dx;
import ji.filter.fh;
import ji.io.ac;
import ji.util.m;

public final class ox
{
    private m a;
    private boolean b;
    
    public ox(final Object o) throws Exception {
        this.b = false;
        if (!this.b) {
            this.a = new m(o);
        }
    }
    
    public dx a(final ac ac, final fh fh, final int n, final Object o) throws Exception {
        if (!this.b) {
            if (this.a != null) {
                final Object a = this.a.a("processHeader", ac, fh, new Integer(n), o);
                return (a == null) ? null : ((dx)a);
            }
        }
        throw new Exception("Decoder invoke instance is null");
    }
    
    public void a(final k5 k5, final fh fh) throws Exception {
        if (!this.b) {
            if (this.a == null) {
                throw new Exception("JPEG2000 Decoder invoke instance is null");
            }
            this.a.a("decodeCodestream", k5, fh);
        }
    }
    
    public final void a() throws Exception {
        if (this.a != null) {
            this.a.c("abort");
        }
    }
    
    public final void b() {
        this.a = null;
    }
}
