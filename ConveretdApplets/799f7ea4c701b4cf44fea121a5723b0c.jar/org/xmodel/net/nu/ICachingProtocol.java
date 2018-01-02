// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.net.nu;

import org.xmodel.external.IExternalReference;
import org.xmodel.IModelObject;
import java.io.IOException;

public interface ICachingProtocol
{
    void sendAttachRequest(final int p0, final String p1) throws IOException;
    
    void sendAttachResponse(final int p0, final IModelObject p1) throws IOException;
    
    void sendDetachRequest(final int p0) throws IOException;
    
    void sendSyncRequest(final IExternalReference p0) throws IOException;
    
    void sendSyncResponse(final int p0) throws IOException;
}
