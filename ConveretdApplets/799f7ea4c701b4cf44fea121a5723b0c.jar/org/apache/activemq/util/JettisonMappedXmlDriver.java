// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.util;

import java.io.OutputStream;
import com.thoughtworks.xstream.io.xml.StaxWriter;
import com.thoughtworks.xstream.io.json.JettisonStaxWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import java.io.Writer;
import java.io.InputStream;
import javax.xml.stream.XMLStreamException;
import com.thoughtworks.xstream.io.StreamException;
import com.thoughtworks.xstream.io.xml.StaxReader;
import com.thoughtworks.xstream.io.xml.QNameMap;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import java.io.Reader;
import org.codehaus.jettison.mapped.Configuration;
import org.codehaus.jettison.mapped.MappedNamespaceConvention;
import org.codehaus.jettison.mapped.MappedXMLInputFactory;
import org.codehaus.jettison.mapped.MappedXMLOutputFactory;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;

public class JettisonMappedXmlDriver implements HierarchicalStreamDriver
{
    private final MappedXMLOutputFactory mof;
    private final MappedXMLInputFactory mif;
    private final MappedNamespaceConvention convention;
    private boolean useSerializeAsArray;
    
    public JettisonMappedXmlDriver() {
        this(new Configuration(), true);
    }
    
    public JettisonMappedXmlDriver(final Configuration config, final boolean useSerializeAsArray) {
        this.useSerializeAsArray = true;
        this.mof = new MappedXMLOutputFactory(config);
        this.mif = new MappedXMLInputFactory(config);
        this.convention = new MappedNamespaceConvention(config);
        this.useSerializeAsArray = useSerializeAsArray;
    }
    
    public HierarchicalStreamReader createReader(final Reader reader) {
        try {
            return (HierarchicalStreamReader)new StaxReader(new QNameMap(), this.mif.createXMLStreamReader(reader));
        }
        catch (XMLStreamException e) {
            throw new StreamException((Throwable)e);
        }
    }
    
    public HierarchicalStreamReader createReader(final InputStream input) {
        try {
            return (HierarchicalStreamReader)new StaxReader(new QNameMap(), this.mif.createXMLStreamReader(input));
        }
        catch (XMLStreamException e) {
            throw new StreamException((Throwable)e);
        }
    }
    
    public HierarchicalStreamWriter createWriter(final Writer writer) {
        try {
            if (this.useSerializeAsArray) {
                return (HierarchicalStreamWriter)new JettisonStaxWriter(new QNameMap(), this.mof.createXMLStreamWriter(writer), this.convention);
            }
            return (HierarchicalStreamWriter)new StaxWriter(new QNameMap(), this.mof.createXMLStreamWriter(writer));
        }
        catch (XMLStreamException e) {
            throw new StreamException((Throwable)e);
        }
    }
    
    public HierarchicalStreamWriter createWriter(final OutputStream output) {
        try {
            if (this.useSerializeAsArray) {
                return (HierarchicalStreamWriter)new JettisonStaxWriter(new QNameMap(), this.mof.createXMLStreamWriter(output), this.convention);
            }
            return (HierarchicalStreamWriter)new StaxWriter(new QNameMap(), this.mof.createXMLStreamWriter(output));
        }
        catch (XMLStreamException e) {
            throw new StreamException((Throwable)e);
        }
    }
}
