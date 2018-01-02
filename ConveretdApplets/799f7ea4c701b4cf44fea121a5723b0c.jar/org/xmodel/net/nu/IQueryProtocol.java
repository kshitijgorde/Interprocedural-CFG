// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.net.nu;

import org.xmodel.IModelObject;
import java.io.IOException;

public interface IQueryProtocol
{
    void sendQueryRequest(final int p0, final String p1, final int p2) throws IOException;
    
    void sendQueryResponse(final int p0, final IModelObject p1) throws IOException;
}
