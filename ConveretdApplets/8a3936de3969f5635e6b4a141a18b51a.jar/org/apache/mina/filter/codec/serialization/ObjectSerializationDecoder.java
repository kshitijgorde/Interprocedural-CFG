// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.filter.codec.serialization;

import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;

public final class ObjectSerializationDecoder extends CumulativeProtocolDecoder
{
    private final ClassLoader classLoader;
    private int maxObjectSize;
    
    public ObjectSerializationDecoder() {
        this(Thread.currentThread().getContextClassLoader());
    }
    
    public ObjectSerializationDecoder(final ClassLoader classLoader) {
        this.maxObjectSize = 1048576;
        if (classLoader == null) {
            throw new IllegalArgumentException("classLoader");
        }
        this.classLoader = classLoader;
    }
    
    @Override
    protected final boolean doDecode$1f3018df(final IoBuffer ioBuffer, final ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        if (!ioBuffer.prefixedDataAvailable(4, this.maxObjectSize)) {
            return false;
        }
        protocolDecoderOutput.write(ioBuffer.getObject(this.classLoader));
        return true;
    }
}
