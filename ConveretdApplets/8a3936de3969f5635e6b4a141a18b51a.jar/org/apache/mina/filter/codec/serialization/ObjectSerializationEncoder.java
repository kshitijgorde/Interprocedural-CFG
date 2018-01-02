// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.filter.codec.serialization;

import org.apache.mina.core.buffer.IoBuffer;
import java.io.NotSerializableException;
import java.io.Serializable;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.ProtocolEncoder;

public final class ObjectSerializationEncoder extends ProtocolEncoder
{
    private int maxObjectSize;
    
    public ObjectSerializationEncoder() {
        this.maxObjectSize = Integer.MAX_VALUE;
    }
    
    @Override
    public final void encode$a41eff(final Object o, final ProtocolEncoderOutput protocolEncoderOutput) throws Exception {
        if (!(o instanceof Serializable)) {
            throw new NotSerializableException();
        }
        final IoBuffer allocate;
        (allocate = IoBuffer.allocate(64)).setAutoExpand(true);
        allocate.putObject(o);
        final int n;
        if ((n = allocate.position() - 4) > this.maxObjectSize) {
            throw new IllegalArgumentException("The encoded object is too big: " + n + " (> " + this.maxObjectSize + ')');
        }
        allocate.flip();
        protocolEncoderOutput.write(allocate);
    }
}
