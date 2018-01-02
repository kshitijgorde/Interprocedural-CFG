// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.socket;

import java.io.IOException;

public interface Shutdownable
{
    void shutdownInput() throws IOException;
    
    void shutdownOutput() throws IOException;
}
