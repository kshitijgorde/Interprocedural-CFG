// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.net.nu;

import java.io.IOException;

public interface IDebugProtocol
{
    void sendDebugStepIn(final int p0) throws IOException;
    
    void sendDebugStepOut(final int p0) throws IOException;
    
    void sendDebugStepOver(final int p0) throws IOException;
}
