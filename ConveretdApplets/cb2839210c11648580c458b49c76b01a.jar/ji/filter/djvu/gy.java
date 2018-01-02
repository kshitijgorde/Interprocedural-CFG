// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter.djvu;

import ji.image.dx;
import ji.filter.fh;

public interface gy
{
    dx loadImageHeaderInternal(final fh p0) throws Exception;
    
    void fillDibInternal(final fh p0) throws Exception;
    
    void abort();
    
    boolean isAborted();
    
    void clearAbort();
    
    void close(final dx p0);
    
    boolean isLoaded();
}
