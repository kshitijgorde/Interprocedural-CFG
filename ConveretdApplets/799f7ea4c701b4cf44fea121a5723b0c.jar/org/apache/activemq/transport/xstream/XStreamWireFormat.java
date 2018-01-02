// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.transport.xstream;

import java.io.Reader;
import org.apache.activemq.command.Command;
import org.apache.activemq.wireformat.WireFormat;
import com.thoughtworks.xstream.XStream;
import org.apache.activemq.transport.util.TextWireFormat;

public class XStreamWireFormat extends TextWireFormat
{
    private XStream xStream;
    private int version;
    
    @Override
    public int getVersion() {
        return this.version;
    }
    
    @Override
    public void setVersion(final int version) {
        this.version = version;
    }
    
    public WireFormat copy() {
        return new XStreamWireFormat();
    }
    
    @Override
    public Object unmarshalText(final String text) {
        return this.getXStream().fromXML(text);
    }
    
    @Override
    public Object unmarshalText(final Reader reader) {
        return this.getXStream().fromXML(reader);
    }
    
    @Override
    public String marshalText(final Object command) {
        return this.getXStream().toXML(command);
    }
    
    public boolean canProcessWireFormatVersion(final int version) {
        return true;
    }
    
    public int getCurrentWireFormatVersion() {
        return 1;
    }
    
    public XStream getXStream() {
        if (this.xStream == null) {
            this.xStream = this.createXStream();
        }
        return this.xStream;
    }
    
    public void setXStream(final XStream xStream) {
        this.xStream = xStream;
    }
    
    protected XStream createXStream() {
        return new XStream();
    }
}
