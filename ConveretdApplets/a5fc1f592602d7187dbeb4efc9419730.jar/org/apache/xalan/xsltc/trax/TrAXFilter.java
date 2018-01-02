// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.trax;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.Result;
import javax.xml.transform.sax.SAXResult;
import org.xml.sax.ContentHandler;
import java.io.IOException;
import org.apache.xml.utils.XMLReaderManager;
import org.xml.sax.InputSource;
import javax.xml.parsers.SAXParser;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.Templates;
import org.xml.sax.helpers.XMLFilterImpl;

public class TrAXFilter extends XMLFilterImpl
{
    private Templates _templates;
    private TransformerImpl _transformer;
    private TransformerHandlerImpl _transformerHandler;
    
    public TrAXFilter(final Templates templates) throws TransformerConfigurationException {
        this._templates = templates;
        this._transformer = (TransformerImpl)templates.newTransformer();
        this._transformerHandler = new TransformerHandlerImpl(this._transformer);
    }
    
    public Transformer getTransformer() {
        return this._transformer;
    }
    
    private void createParent() throws SAXException {
        XMLReader parent = null;
        try {
            final SAXParserFactory pfactory = SAXParserFactory.newInstance();
            pfactory.setNamespaceAware(true);
            final SAXParser saxparser = pfactory.newSAXParser();
            parent = saxparser.getXMLReader();
        }
        catch (ParserConfigurationException e) {
            throw new SAXException(e);
        }
        catch (FactoryConfigurationError e2) {
            throw new SAXException(e2.toString());
        }
        if (parent == null) {
            parent = XMLReaderFactory.createXMLReader();
        }
        this.setParent(parent);
    }
    
    public void parse(final InputSource input) throws SAXException, IOException {
        XMLReader managedReader = null;
        try {
            if (this.getParent() == null) {
                try {
                    managedReader = XMLReaderManager.getInstance().getXMLReader();
                    this.setParent(managedReader);
                }
                catch (SAXException e) {
                    throw new SAXException(e.toString());
                }
            }
            this.getParent().parse(input);
        }
        finally {
            if (managedReader != null) {
                XMLReaderManager.getInstance().releaseXMLReader(managedReader);
            }
        }
    }
    
    public void parse(final String systemId) throws SAXException, IOException {
        this.parse(new InputSource(systemId));
    }
    
    public void setContentHandler(final ContentHandler handler) {
        this._transformerHandler.setResult(new SAXResult(handler));
        if (this.getParent() == null) {
            try {
                this.createParent();
            }
            catch (SAXException e) {
                return;
            }
        }
        this.getParent().setContentHandler(this._transformerHandler);
    }
    
    public void setErrorListener(final ErrorListener handler) {
    }
}
