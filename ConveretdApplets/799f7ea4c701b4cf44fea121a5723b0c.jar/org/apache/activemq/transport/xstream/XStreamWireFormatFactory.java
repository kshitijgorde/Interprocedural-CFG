// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.transport.xstream;

import org.apache.activemq.wireformat.WireFormat;
import org.apache.activemq.wireformat.WireFormatFactory;

public class XStreamWireFormatFactory implements WireFormatFactory
{
    @Override
    public WireFormat createWireFormat() {
        return new XStreamWireFormat();
    }
}
