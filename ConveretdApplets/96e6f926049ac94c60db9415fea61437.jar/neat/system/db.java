// 
// Decompiled by Procyon v0.5.30
// 

package neat.system;

import neat.kb;
import java.net.URL;

public interface db
{
    URL getCodeBase();
    
    URL getDocumentBase();
    
    void a(final URL p0, final kb p1);
    
    boolean isActive();
}
