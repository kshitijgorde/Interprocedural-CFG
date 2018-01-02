// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter;

import ji.document.ad;
import ji.io.ac;
import ji.image.dx;

public class fd extends cj
{
    public final String getFilterName() {
        return "jiDummyFilter";
    }
    
    public dx loadImageHeaderInternal(final fh fh) throws Exception {
        return null;
    }
    
    public final boolean d() {
        return false;
    }
    
    public boolean isAborted(final dx dx, final String s) {
        return false;
    }
    
    public final void clearAbort(final dx dx, final String s) {
    }
    
    public void fillDibInternal(final fh fh) throws Exception {
    }
    
    public int[] getPalette(final ac ac, final dx dx, final String s) throws Exception {
        return new int[0];
    }
    
    public void abort(final dx dx) {
    }
    
    public void close(final dx dx, final ad ad) {
    }
    
    public void a(final String s) {
    }
    
    public boolean e() {
        return false;
    }
}
