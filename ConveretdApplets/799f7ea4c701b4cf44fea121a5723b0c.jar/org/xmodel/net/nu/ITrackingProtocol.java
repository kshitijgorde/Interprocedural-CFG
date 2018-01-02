// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.net.nu;

import java.io.IOException;
import org.xmodel.IModelObject;

public interface ITrackingProtocol
{
    void sendAddChild(final int p0, final String p1, final IModelObject p2, final int p3) throws IOException;
    
    void sendRemoveChild(final int p0, final String p1, final int p2) throws IOException;
    
    void sendChangeAttribute(final int p0, final String p1, final String p2, final Object p3) throws IOException;
    
    void sendChangeDirty(final int p0, final String p1, final boolean p2) throws IOException;
    
    void sendClearAttribute(final int p0, final String p1, final String p2) throws IOException;
}
