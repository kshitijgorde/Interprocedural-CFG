// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter.uv;

import ji.v1event.af;
import ji.document.ad;
import ji.io.ac;
import ji.image.dx;
import ji.filter.fh;
import ji.filter.dll.n0;

public interface nz extends n0
{
    dx processHeader(final fh p0, final String p1, final double p2) throws Exception;
    
    boolean isCancelled();
    
    boolean isInvalidPassword();
    
    boolean isKnownNonDisplayable();
    
    boolean isViewingTechnologyLoaded();
    
    int isFileType(final ac p0, final ad p1, final String p2, final String p3, final String p4, final af p5, final String p6);
    
    void resetFlags();
}
