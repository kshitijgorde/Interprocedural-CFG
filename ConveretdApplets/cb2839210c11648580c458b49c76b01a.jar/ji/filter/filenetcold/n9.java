// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter.filenetcold;

import ji.filter.fh;
import ji.image.dx;
import ji.filter.dll.n0;

public interface n9 extends n0
{
    dx processHeader(final dx p0, final fh p1, final String p2) throws Exception;
    
    boolean isDecoderLoaded();
    
    boolean loadFailedDueToPermissions();
}
