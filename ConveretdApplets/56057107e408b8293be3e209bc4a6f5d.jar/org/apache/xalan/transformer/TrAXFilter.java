// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

import org.xml.sax.ErrorHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.apache.xalan.stree.SourceTreeHandler;
import javax.xml.transform.ErrorListener;
import org.xml.sax.ContentHandler;
import javax.xml.parsers.SAXParser;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import org.xml.sax.SAXException;
import org.xml.sax.InputSource;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.Templates;
import org.xml.sax.helpers.XMLFilterImpl;

public class TrAXFilter extends XMLFilterImpl
{
    private Templates m_templates;
    private TransformerImpl m_transformer;
    
    public TrAXFilter(final Templates templates) throws TransformerConfigurationException {
        this.m_templates = templates;
        this.m_transformer = (TransformerImpl)templates.newTransformer();
    }
    
    public void parse(final String systemId) throws SAXException, IOException {
        this.parse(new InputSource(systemId));
    }
    
    public void parse(final InputSource input) throws SAXException, IOException {
        if (this.getParent() == null) {
            XMLReader reader = null;
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
            XMLReader parent;
            if (reader == null) {
                parent = XMLReaderFactory.createXMLReader();
            }
            else {
                parent = reader;
            }
            try {
                parent.setFeature("http://xml.org/sax/features/namespace-prefixes", true);
                parent.setFeature("http://apache.org/xml/features/validation/dynamic", true);
            }
            catch (SAXException ex3) {}
            this.setParent(parent);
        }
        else {
            this.setupParse();
        }
        if (this.m_transformer.getContentHandler() == null) {
            throw new SAXException("parse can not be called if the ContentHandler has not been set!");
        }
        this.getParent().parse(input);
        final Exception e = this.m_transformer.getExceptionThrown();
        if (e == null) {
            return;
        }
        if (e instanceof SAXException) {
            throw (SAXException)e;
        }
        throw new SAXException(e);
    }
    
    public void setContentHandler(final ContentHandler handler) {
        this.m_transformer.setContentHandler(handler);
    }
    
    public void setErrorListener(final ErrorListener handler) {
        this.m_transformer.setErrorListener(handler);
    }
    
    public void setParent(final XMLReader parent) {
        super.setParent(parent);
        if (parent.getContentHandler() != null) {
            this.setContentHandler(parent.getContentHandler());
        }
        this.setupParse();
    }
    
    private void setupParse() {
        final XMLReader p = this.getParent();
        if (p == null) {
            throw new NullPointerException("No parent for filter");
        }
        final ContentHandler ch = this.m_transformer.getInputContentHandler();
        if (ch instanceof SourceTreeHandler) {
            ((SourceTreeHandler)ch).setUseMultiThreading(true);
        }
        p.setContentHandler(ch);
        if (ch instanceof EntityResolver) {
            p.setEntityResolver((EntityResolver)ch);
        }
        else {
            p.setEntityResolver(this);
        }
        if (ch instanceof DTDHandler) {
            p.setDTDHandler((DTDHandler)ch);
        }
        else {
            p.setDTDHandler(this);
        }
        final ErrorListener elistener = this.m_transformer.getErrorListener();
        if (elistener != null && elistener instanceof ErrorHandler) {
            p.setErrorHandler((ErrorHandler)elistener);
        }
        else {
            p.setErrorHandler(this);
        }
    }
}
