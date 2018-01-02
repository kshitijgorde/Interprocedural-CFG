// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.filter.codec;

import org.apache.mina.filter.codec.serialization.ObjectSerializationDecoder;
import org.apache.mina.filter.codec.serialization.ObjectSerializationEncoder;

public class ProtocolCodecFactory
{
    private final ObjectSerializationEncoder encoder;
    private final ObjectSerializationDecoder decoder;
    
    public ProtocolEncoder getEncoder$3a35968c() throws Exception {
        return this.encoder;
    }
    
    public ProtocolDecoder getDecoder$7e2ed8b4() throws Exception {
        return this.decoder;
    }
    
    public ProtocolCodecFactory() {
        this(Thread.currentThread().getContextClassLoader());
    }
    
    public ProtocolCodecFactory(final ClassLoader classLoader) {
        this.encoder = new ObjectSerializationEncoder();
        this.decoder = new ObjectSerializationDecoder(classLoader);
    }
}
