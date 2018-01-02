// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

import javax.xml.transform.ErrorListener;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import java.io.IOException;
import javax.xml.parsers.SAXParser;
import org.apache.xalan.res.XSLMessages;
import org.xml.sax.helpers.XMLReaderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
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
    
    public TransformerImpl getTransformer() {
        return this.m_transformer;
    }
    
    public void setParent(final XMLReader parent) {
        super.setParent(parent);
        if (null != parent.getContentHandler()) {
            this.setContentHandler(parent.getContentHandler());
        }
        this.setupParse();
    }
    
    public void parse(final InputSource input) throws SAXException, IOException {
        if (null == this.getParent()) {
            XMLReader reader = null;
            try {
                final SAXParserFactory factory = SAXParserFactory.newInstance();
                factory.setNamespaceAware(true);
                if (this.m_transformer.getStylesheet().isSecureProcessing()) {
                    try {
                        factory.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
                    }
                    catch (SAXException ex4) {}
                }
                final SAXParser jaxpParser = factory.newSAXParser();
                reader = jaxpParser.getXMLReader();
            }
            catch (ParserConfigurationException ex) {
                throw new SAXException(ex);
            }
            catch (FactoryConfigurationError ex2) {
                throw new SAXException(ex2.toString());
            }
            catch (NoSuchMethodError ex3) {}
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
            }
            catch (SAXException ex5) {}
            this.setParent(parent);
        }
        else {
            this.setupParse();
        }
        if (null == this.m_transformer.getContentHandler()) {
            throw new SAXException(XSLMessages.createMessage("ER_CANNOT_CALL_PARSE", null));
        }
        this.getParent().parse(input);
        final Exception e = this.m_transformer.getExceptionThrown();
        if (null == e) {
            return;
        }
        if (e instanceof SAXException) {
            throw (SAXException)e;
        }
        throw new SAXException(e);
    }
    
    public void parse(final String systemId) throws SAXException, IOException {
        this.parse(new InputSource(systemId));
    }
    
    private void setupParse() {
        final XMLReader p = this.getParent();
        if (p == null) {
            throw new NullPointerException(XSLMessages.createMessage("ER_NO_PARENT_FOR_FILTER", null));
        }
        final ContentHandler ch = this.m_transformer.getInputContentHandler();
        p.setContentHandler(ch);
        p.setEntityResolver(this);
        p.setDTDHandler(this);
        p.setErrorHandler(this);
    }
    
    public void setContentHandler(final ContentHandler handler) {
        this.m_transformer.setContentHandler(handler);
    }
    
    public void setErrorListener(final ErrorListener handler) {
        this.m_transformer.setErrorListener(handler);
    }
}
