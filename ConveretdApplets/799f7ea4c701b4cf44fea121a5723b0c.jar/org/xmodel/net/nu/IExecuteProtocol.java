// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.net.nu;

import java.io.IOException;
import org.xmodel.IModelObject;

public interface IExecuteProtocol
{
    void sendExecuteRequest(final int p0, final IModelObject p1, final int p2) throws IOException;
    
    void sendExecuteResponse(final int p0, final IModelObject p1) throws IOException;
}
