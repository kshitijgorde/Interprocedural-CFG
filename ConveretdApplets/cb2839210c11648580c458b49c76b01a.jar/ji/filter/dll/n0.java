// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter.dll;

import ji.annotate.df;
import ji.document.ad;
import ji.filter.fh;
import ji.image.dx;
import ji.io.ac;

public interface n0
{
    void setParentId(final String p0);
    
    int[] getPalette(final ac p0, final dx p1) throws Exception;
    
    void fillDib(final fh p0, final String p1) throws Exception;
    
    df getAnnotations(final String p0, final dx p1, final ad p2, final int p3) throws Exception;
    
    void close(final dx p0) throws Exception;
    
    void abort(final dx p0) throws Exception;
    
    void removeDecoder(final ad p0, final String p1);
    
    boolean isAborted();
    
    void clearAbort();
}
