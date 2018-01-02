// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.net;

import java.io.IOException;
import java.nio.ByteBuffer;

public interface ILink
{
    void send(final ByteBuffer p0) throws IOException;
    
    boolean isOpen();
    
    void close();
    
    public interface IListener
    {
        void onClose(final ILink p0);
        
        void onReceive(final ILink p0, final ByteBuffer p1);
    }
}
