// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.serialize;

import org.w3c.dom.Node;
import java.io.IOException;
import org.xml.sax.ContentHandler;
import java.io.Writer;
import java.io.OutputStream;
import java.util.Properties;

public abstract class SerializerFactory
{
    public static Serializer getSerializer(final Properties format) {
        final org.apache.xml.serializer.Serializer ser = org.apache.xml.serializer.SerializerFactory.getSerializer(format);
        final SerializerWrapper si = new SerializerWrapper(ser);
        return si;
    }
    
    private static class SerializerWrapper implements Serializer
    {
        private final org.apache.xml.serializer.Serializer m_serializer;
        private DOMSerializer m_old_DOMSerializer;
        
        SerializerWrapper(final org.apache.xml.serializer.Serializer ser) {
            this.m_serializer = ser;
        }
        
        public void setOutputStream(final OutputStream output) {
            this.m_serializer.setOutputStream(output);
        }
        
        public OutputStream getOutputStream() {
            return this.m_serializer.getOutputStream();
        }
        
        public void setWriter(final Writer writer) {
            this.m_serializer.setWriter(writer);
        }
        
        public Writer getWriter() {
            return this.m_serializer.getWriter();
        }
        
        public void setOutputFormat(final Properties format) {
            this.m_serializer.setOutputFormat(format);
        }
        
        public Properties getOutputFormat() {
            return this.m_serializer.getOutputFormat();
        }
        
        public ContentHandler asContentHandler() throws IOException {
            return this.m_serializer.asContentHandler();
        }
        
        public DOMSerializer asDOMSerializer() throws IOException {
            if (this.m_old_DOMSerializer == null) {
                this.m_old_DOMSerializer = new DOMSerializerWrapper(this.m_serializer.asDOMSerializer());
            }
            return this.m_old_DOMSerializer;
        }
        
        public boolean reset() {
            return this.m_serializer.reset();
        }
    }
    
    private static class DOMSerializerWrapper implements DOMSerializer
    {
        private final org.apache.xml.serializer.DOMSerializer m_dom;
        
        DOMSerializerWrapper(final org.apache.xml.serializer.DOMSerializer domser) {
            this.m_dom = domser;
        }
        
        public void serialize(final Node node) throws IOException {
            this.m_dom.serialize(node);
        }
    }
}
