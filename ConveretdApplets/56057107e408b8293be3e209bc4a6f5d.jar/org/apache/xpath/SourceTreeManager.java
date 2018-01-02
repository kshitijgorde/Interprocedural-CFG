// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath;

import javax.xml.transform.stream.StreamSource;
import org.apache.xml.utils.SystemIDResolver;
import javax.xml.parsers.SAXParser;
import org.xml.sax.helpers.XMLReaderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.ContentHandler;
import org.xml.sax.XMLReader;
import java.io.IOException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.sax.SAXSource;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DeclHandler;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.DTDHandler;
import org.apache.xalan.stree.SourceTreeHandler;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.SourceLocator;
import javax.xml.transform.Source;
import org.w3c.dom.Node;
import org.w3c.dom.Document;
import javax.xml.transform.URIResolver;
import java.util.Vector;

public class SourceTreeManager
{
    private Vector m_sourceTree;
    URIResolver m_uriResolver;
    
    public SourceTreeManager() {
        this.m_sourceTree = new Vector();
    }
    
    public String findURIFromDoc(final Document owner) {
        Node root = owner.getOwnerDocument();
        if (root == null) {
            root = owner;
        }
        String url = null;
        for (int n = this.m_sourceTree.size(), i = 0; i < n; ++i) {
            final SourceTree sTree = this.m_sourceTree.elementAt(i);
            if (root == sTree.m_root) {
                url = sTree.m_url;
                break;
            }
        }
        return url;
    }
    
    public Node getDOMNode(final Source source, final SourceLocator locator) throws TransformerException {
        if (source instanceof DOMSource) {
            return ((DOMSource)source).getNode();
        }
        Node doc = null;
        try {
            final XMLReader reader = this.getXMLReader(source, locator);
            final ContentHandler handler = new SourceTreeHandler();
            if (handler instanceof SourceTreeHandler) {
                ((SourceTreeHandler)handler).setUseMultiThreading(false);
            }
            reader.setContentHandler(handler);
            if (handler instanceof DTDHandler) {
                reader.setDTDHandler((DTDHandler)handler);
            }
            try {
                if (handler instanceof LexicalHandler) {
                    reader.setProperty("http://xml.org/sax/properties/lexical-handler", handler);
                }
                if (handler instanceof DeclHandler) {
                    reader.setProperty("http://xml.org/sax/properties/declaration-handler", handler);
                }
            }
            catch (SAXException ex) {}
            try {
                if (handler instanceof LexicalHandler) {
                    reader.setProperty("http://xml.org/sax/handlers/LexicalHandler", handler);
                }
                if (handler instanceof DeclHandler) {
                    reader.setProperty("http://xml.org/sax/handlers/DeclHandler", handler);
                }
            }
            catch (SAXNotRecognizedException ex2) {}
            final InputSource isource = SAXSource.sourceToInputSource(source);
            reader.parse(isource);
            if (handler instanceof SourceTreeHandler) {
                doc = ((SourceTreeHandler)handler).getRoot();
            }
        }
        catch (IOException ioe) {
            throw new TransformerException(ioe.getMessage(), locator, ioe);
        }
        catch (SAXException e) {
            throw new TransformerException(e.getMessage(), locator, e);
        }
        return doc;
    }
    
    public Node getNode(final Source source) {
        final String url = source.getSystemId();
        if (url == null) {
            return null;
        }
        Node node = null;
        for (int n = this.m_sourceTree.size(), i = 0; i < n; ++i) {
            final SourceTree sTree = this.m_sourceTree.elementAt(i);
            if (url.equals(sTree.m_url)) {
                node = sTree.m_root;
                break;
            }
        }
        return node;
    }
    
    public Node getSourceTree(final String base, final String urlString, final SourceLocator locator) throws TransformerException {
        try {
            final Source source = this.resolveURI(base, urlString, locator);
            return this.getSourceTree(source, locator);
        }
        catch (IOException ioe) {
            throw new TransformerException(ioe.getMessage(), locator, ioe);
        }
    }
    
    public Node getSourceTree(final Source source, final SourceLocator locator) throws TransformerException {
        Node n = this.getNode(source);
        if (n != null) {
            return n;
        }
        n = this.getDOMNode(source, locator);
        if (n != null) {
            this.putDocumentInCache(n, source);
        }
        return n;
    }
    
    public URIResolver getURIResolver() {
        return this.m_uriResolver;
    }
    
    public XMLReader getXMLReader(final Source inputSource, final SourceLocator locator) throws TransformerException {
        try {
            XMLReader reader = (inputSource instanceof SAXSource) ? ((SAXSource)inputSource).getXMLReader() : null;
            if (reader == null) {
                try {
                    final SAXParserFactory factory = SAXParserFactory.newInstance();
                    factory.setNamespaceAware(true);
                    final SAXParser jaxpParser = factory.newSAXParser();
                    reader = jaxpParser.getXMLReader();
                }
                catch (ParserConfigurationException ex) {
                    throw new SAXException(ex);
                }
                catch (FactoryConfigurationError ex2) {
                    throw new SAXException(ex2.toString());
                }
                catch (NoSuchMethodError noSuchMethodError) {}
                catch (AbstractMethodError abstractMethodError) {}
                if (reader == null) {
                    reader = XMLReaderFactory.createXMLReader();
                }
            }
            try {
                reader.setFeature("http://xml.org/sax/features/namespace-prefixes", true);
                reader.setFeature("http://apache.org/xml/features/validation/dynamic", true);
            }
            catch (SAXException ex3) {}
            return reader;
        }
        catch (SAXException se) {
            throw new TransformerException(se.getMessage(), locator, se);
        }
    }
    
    public void putDocumentInCache(final Node n, final Source source) {
        final Node cachedNode = this.getNode(source);
        if (cachedNode == null) {
            if (source.getSystemId() != null) {
                this.m_sourceTree.addElement(new SourceTree(n, source.getSystemId()));
            }
            return;
        }
        if (!cachedNode.equals(n)) {
            throw new RuntimeException("Programmer's Error!  putDocumentInCache found reparse of doc: " + source.getSystemId());
        }
    }
    
    public void reset() {
        this.m_sourceTree = new Vector();
    }
    
    public Source resolveURI(final String base, final String urlString, final SourceLocator locator) throws TransformerException, IOException {
        Source source = null;
        if (this.m_uriResolver != null) {
            source = this.m_uriResolver.resolve(urlString, base);
        }
        if (source == null) {
            final String uri = SystemIDResolver.getAbsoluteURI(urlString, base);
            source = new StreamSource(uri);
        }
        return source;
    }
    
    public void setURIResolver(final URIResolver resolver) {
        this.m_uriResolver = resolver;
    }
}
