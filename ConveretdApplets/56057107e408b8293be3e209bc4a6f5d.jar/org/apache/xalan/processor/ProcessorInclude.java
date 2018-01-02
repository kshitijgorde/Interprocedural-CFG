// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.processor;

import org.apache.xalan.res.XSLMessages;
import javax.xml.parsers.SAXParser;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.w3c.dom.Node;
import javax.xml.transform.Source;
import javax.xml.transform.URIResolver;
import java.io.IOException;
import org.xml.sax.helpers.XMLReaderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import org.apache.xml.utils.SystemIDResolver;
import org.xml.sax.SAXException;
import javax.xml.transform.TransformerException;
import org.xml.sax.ContentHandler;
import org.apache.xml.utils.TreeWalker;
import javax.xml.transform.dom.DOMSource;
import org.xml.sax.Attributes;

class ProcessorInclude extends XSLTElementProcessor
{
    private String m_href;
    
    ProcessorInclude() {
        this.m_href = null;
    }
    
    public String getHref() {
        return this.m_href;
    }
    
    protected int getStylesheetInclErr() {
        return 39;
    }
    
    protected int getStylesheetType() {
        return 2;
    }
    
    protected void parse(final StylesheetHandler handler, final String uri, final String localName, final String rawName, final Attributes attributes) throws SAXException {
        final TransformerFactoryImpl processor = handler.getStylesheetProcessor();
        final URIResolver uriresolver = processor.getURIResolver();
        try {
            Source source = null;
            if (uriresolver != null) {
                source = uriresolver.resolve(this.getHref(), handler.getBaseIdentifier());
                if (source != null && source instanceof DOMSource) {
                    final Node node = ((DOMSource)source).getNode();
                    final TreeWalker walker = new TreeWalker(handler);
                    try {
                        walker.traverse(node);
                    }
                    catch (SAXException se) {
                        throw new TransformerException(se);
                    }
                    return;
                }
            }
            if (source == null) {
                final String absURL = SystemIDResolver.getAbsoluteURI(this.getHref(), handler.getBaseIdentifier());
                source = new StreamSource(absURL);
            }
            XMLReader reader = null;
            if (source instanceof SAXSource) {
                final SAXSource saxSource = (SAXSource)source;
                reader = saxSource.getXMLReader();
            }
            final InputSource inputSource = SAXSource.sourceToInputSource(source);
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
            }
            if (reader == null) {
                reader = XMLReaderFactory.createXMLReader();
            }
            if (reader != null) {
                reader.setContentHandler(handler);
                try {
                    reader.setFeature("http://apache.org/xml/features/validation/dynamic", true);
                }
                catch (SAXException ex3) {}
                handler.pushBaseIndentifier(inputSource.getSystemId());
                try {
                    reader.parse(inputSource);
                }
                finally {
                    handler.popBaseIndentifier();
                }
            }
        }
        catch (IOException ioe) {
            handler.error(37, new Object[] { this.getHref() }, ioe);
        }
        catch (TransformerException te) {
            handler.error(te.getMessage(), te);
        }
    }
    
    public void setHref(final String baseIdent) {
        this.m_href = baseIdent;
    }
    
    public void startElement(final StylesheetHandler handler, final String uri, final String localName, final String rawName, final Attributes attributes) throws SAXException {
        this.setPropertiesFromAttributes(handler, rawName, attributes, this);
        final String hrefUrl = this.getHref();
        if (handler.importStackContains(hrefUrl)) {
            throw new SAXException(XSLMessages.createMessage(this.getStylesheetInclErr(), new Object[] { hrefUrl }));
        }
        handler.pushImportURL(hrefUrl);
        final int savedStylesheetType = handler.getStylesheetType();
        handler.setStylesheetType(this.getStylesheetType());
        handler.pushNewNamespaceSupport();
        try {
            this.parse(handler, uri, localName, rawName, attributes);
        }
        finally {
            handler.setStylesheetType(savedStylesheetType);
            handler.popImportURL();
            handler.popNamespaceSupport();
        }
    }
}
