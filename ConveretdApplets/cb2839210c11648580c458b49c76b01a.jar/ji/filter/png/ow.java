// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter.png;

import ji.io.a5;
import ji.filter.fh;
import ji.image.dx;
import ji.io.ac;
import ji.v1event.af;
import ji.util.m;

public class ow
{
    private m a;
    
    public ow(final Object o) {
        this.a = new m(o);
    }
    
    public dx a(final Object o, final af af, final ac ac, final long n, final String s) throws Exception {
        if (this.a != null) {
            return (dx)this.a.a("processHeader", o, af, ac, new Long(n), s);
        }
        throw new Exception("Decoder invoke instance is null");
    }
    
    public int[] a(final ac ac, final dx dx, final String s) throws Exception {
        if (this.a != null) {
            return (int[])this.a.a("getPalette", ac, dx, s);
        }
        throw new Exception("Decoder invoke instance is null");
    }
    
    public void a(final fh fh, final a5 a5) throws Exception {
        if (this.a != null) {
            try {
                this.a.a("fillDib", fh, a5);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            return;
        }
        throw new Exception("Decoder invoke instance is null");
    }
    
    public void a() throws Exception {
        if (this.a != null) {
            this.a.c("cleanUp");
            return;
        }
        throw new Exception("Decoder invoke instance is null");
    }
}
