// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom.transform;

import org.jdom.JDOMException;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotSupportedException;
import org.jdom.output.SAXOutputter;
import java.io.StringReader;
import org.jdom.output.XMLOutputter;
import java.io.Reader;
import org.xml.sax.XMLFilter;
import org.xml.sax.InputSource;
import java.util.ArrayList;
import org.jdom.Element;
import java.util.List;
import org.jdom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.XMLReader;
import javax.xml.transform.sax.SAXSource;

public class JDOMSource extends SAXSource
{
    private static final String CVS_ID = "@(#) $RCSfile: JDOMSource.java,v $ $Revision: 1.20 $ $Date: 2007/11/10 05:29:02 $ $Name: jdom_1_1_1 $";
    public static final String JDOM_FEATURE = "http://org.jdom.transform.JDOMSource/feature";
    private XMLReader xmlReader;
    private EntityResolver resolver;
    
    public JDOMSource(final Document source) {
        this.xmlReader = null;
        this.resolver = null;
        this.setDocument(source);
    }
    
    public JDOMSource(final List source) {
        this.xmlReader = null;
        this.resolver = null;
        this.setNodes(source);
    }
    
    public JDOMSource(final Element source) {
        this.xmlReader = null;
        this.resolver = null;
        final List nodes = new ArrayList();
        nodes.add(source);
        this.setNodes(nodes);
    }
    
    public JDOMSource(final Document source, final EntityResolver resolver) {
        this.xmlReader = null;
        this.resolver = null;
        this.setDocument(source);
        this.resolver = resolver;
    }
    
    public void setDocument(final Document source) {
        super.setInputSource(new JDOMInputSource(source));
    }
    
    public Document getDocument() {
        final Object src = ((JDOMInputSource)this.getInputSource()).getSource();
        Document doc = null;
        if (src instanceof Document) {
            doc = (Document)src;
        }
        return doc;
    }
    
    public void setNodes(final List source) {
        super.setInputSource(new JDOMInputSource(source));
    }
    
    public List getNodes() {
        final Object src = ((JDOMInputSource)this.getInputSource()).getSource();
        List nodes = null;
        if (src instanceof List) {
            nodes = (List)src;
        }
        return nodes;
    }
    
    public void setInputSource(final InputSource inputSource) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
    
    public void setXMLReader(final XMLReader reader) throws UnsupportedOperationException {
        if (reader instanceof XMLFilter) {
            XMLFilter filter;
            for (filter = (XMLFilter)reader; filter.getParent() instanceof XMLFilter; filter = (XMLFilter)filter.getParent()) {}
            filter.setParent(this.buildDocumentReader());
            this.xmlReader = reader;
            return;
        }
        throw new UnsupportedOperationException();
    }
    
    public XMLReader getXMLReader() {
        if (this.xmlReader == null) {
            this.xmlReader = this.buildDocumentReader();
        }
        return this.xmlReader;
    }
    
    private XMLReader buildDocumentReader() {
        final DocumentReader reader = new DocumentReader();
        if (this.resolver != null) {
            reader.setEntityResolver(this.resolver);
        }
        return reader;
    }
    
    private static class JDOMInputSource extends InputSource
    {
        private Object source;
        
        public JDOMInputSource(final Document document) {
            this.source = null;
            this.source = document;
        }
        
        public JDOMInputSource(final List nodes) {
            this.source = null;
            this.source = nodes;
        }
        
        public Object getSource() {
            return this.source;
        }
        
        public void setCharacterStream(final Reader characterStream) throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }
        
        public Reader getCharacterStream() {
            final Object src = this.getSource();
            Reader reader = null;
            if (src instanceof Document) {
                reader = new StringReader(new XMLOutputter().outputString((Document)src));
            }
            else if (src instanceof List) {
                reader = new StringReader(new XMLOutputter().outputString((List)src));
            }
            return reader;
        }
    }
    
    private static class DocumentReader extends SAXOutputter implements XMLReader
    {
        public void parse(final String systemId) throws SAXNotSupportedException {
            throw new SAXNotSupportedException("Only JDOM Documents are supported as input");
        }
        
        public void parse(final InputSource input) throws SAXException {
            if (input instanceof JDOMInputSource) {
                try {
                    final Object source = ((JDOMInputSource)input).getSource();
                    if (source instanceof Document) {
                        this.output((Document)source);
                    }
                    else {
                        this.output((List)source);
                    }
                    return;
                }
                catch (JDOMException e) {
                    throw new SAXException(e.getMessage(), e);
                }
                throw new SAXNotSupportedException("Only JDOM Documents are supported as input");
            }
            throw new SAXNotSupportedException("Only JDOM Documents are supported as input");
        }
    }
}
