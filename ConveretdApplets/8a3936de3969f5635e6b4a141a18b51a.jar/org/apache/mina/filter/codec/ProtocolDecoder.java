// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.filter.codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

public abstract class ProtocolDecoder
{
    public abstract void decode(final IoSession p0, final IoBuffer p1, final ProtocolDecoderOutput p2) throws Exception;
    
    public void dispose(final IoSession ioSession) throws Exception {
    }
}
