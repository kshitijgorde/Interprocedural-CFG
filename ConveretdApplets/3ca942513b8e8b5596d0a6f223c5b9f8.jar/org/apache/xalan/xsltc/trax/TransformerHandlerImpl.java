// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.trax;

import org.xml.sax.Attributes;
import javax.xml.transform.dom.DOMResult;
import org.apache.xml.dtm.DTMWSFilter;
import org.apache.xalan.xsltc.dom.DOMWSFilter;
import org.apache.xalan.xsltc.StripFilter;
import org.apache.xalan.xsltc.dom.XSLTCDTMManager;
import org.xml.sax.SAXException;
import org.apache.xml.serializer.SerializationHandler;
import javax.xml.transform.Source;
import org.apache.xalan.xsltc.DOM;
import javax.xml.transform.TransformerException;
import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
import javax.xml.transform.Transformer;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Locator;
import javax.xml.transform.Result;
import org.xml.sax.DTDHandler;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.ContentHandler;
import org.apache.xalan.xsltc.dom.SAXImpl;
import org.apache.xalan.xsltc.runtime.AbstractTranslet;
import org.xml.sax.ext.DeclHandler;
import javax.xml.transform.sax.TransformerHandler;

public class TransformerHandlerImpl implements TransformerHandler, DeclHandler
{
    private TransformerImpl _transformer;
    private AbstractTranslet _translet;
    private String _systemId;
    private SAXImpl _dom;
    private ContentHandler _handler;
    private LexicalHandler _lexHandler;
    private DTDHandler _dtdHandler;
    private DeclHandler _declHandler;
    private Result _result;
    private Locator _locator;
    private boolean _done;
    private boolean _isIdentity;
    
    public TransformerHandlerImpl(final TransformerImpl transformer) {
        this._translet = null;
        this._dom = null;
        this._handler = null;
        this._lexHandler = null;
        this._dtdHandler = null;
        this._declHandler = null;
        this._result = null;
        this._locator = null;
        this._done = false;
        this._isIdentity = false;
        this._transformer = transformer;
        if (transformer.isIdentity()) {
            this._handler = new DefaultHandler();
            this._isIdentity = true;
        }
        else {
            this._translet = this._transformer.getTranslet();
        }
    }
    
    public String getSystemId() {
        return this._systemId;
    }
    
    public void setSystemId(final String id) {
        this._systemId = id;
    }
    
    public Transformer getTransformer() {
        return this._transformer;
    }
    
    public void setResult(final Result result) throws IllegalArgumentException {
        this._result = result;
        if (null == result) {
            final ErrorMsg err = new ErrorMsg("ER_RESULT_NULL");
            throw new IllegalArgumentException(err.toString());
        }
        if (this._isIdentity) {
            try {
                final SerializationHandler outputHandler = this._transformer.getOutputHandler(result);
                this._transformer.transferOutputProperties(outputHandler);
                this._handler = outputHandler;
                this._lexHandler = outputHandler;
            }
            catch (TransformerException e) {
                this._result = null;
            }
        }
        else if (this._done) {
            try {
                this._transformer.setDOM(this._dom);
                this._transformer.transform(null, this._result);
            }
            catch (TransformerException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        }
    }
    
    public void characters(final char[] ch, final int start, final int length) throws SAXException {
        this._handler.characters(ch, start, length);
    }
    
    public void startDocument() throws SAXException {
        if (this._result == null) {
            final ErrorMsg err = new ErrorMsg("JAXP_SET_RESULT_ERR");
            throw new SAXException(err.toString());
        }
        if (!this._isIdentity) {
            final boolean hasIdCall = this._translet != null && this._translet.hasIdCall();
            XSLTCDTMManager dtmManager = null;
            try {
                dtmManager = this._transformer.getTransformerFactory().getDTMManagerClass().newInstance();
            }
            catch (Exception e) {
                throw new SAXException(e);
            }
            DTMWSFilter wsFilter;
            if (this._translet != null && this._translet instanceof StripFilter) {
                wsFilter = new DOMWSFilter(this._translet);
            }
            else {
                wsFilter = null;
            }
            this._dom = (SAXImpl)dtmManager.getDTM(null, false, wsFilter, true, false, hasIdCall);
            this._handler = this._dom.getBuilder();
            this._lexHandler = (LexicalHandler)this._handler;
            this._dtdHandler = (DTDHandler)this._handler;
            this._declHandler = (DeclHandler)this._handler;
            this._dom.setDocumentURI(this._systemId);
            if (this._locator != null) {
                this._handler.setDocumentLocator(this._locator);
            }
        }
        this._handler.startDocument();
    }
    
    public void endDocument() throws SAXException {
        this._handler.endDocument();
        if (!this._isIdentity) {
            if (this._result != null) {
                try {
                    this._transformer.setDOM(this._dom);
                    this._transformer.transform(null, this._result);
                }
                catch (TransformerException e) {
                    throw new SAXException(e);
                }
            }
            this._done = true;
            this._transformer.setDOM(this._dom);
        }
        if (this._isIdentity && this._result instanceof DOMResult) {
            ((DOMResult)this._result).setNode(this._transformer.getTransletOutputHandlerFactory().getNode());
        }
    }
    
    public void startElement(final String uri, final String localName, final String qname, final Attributes attributes) throws SAXException {
        this._handler.startElement(uri, localName, qname, attributes);
    }
    
    public void endElement(final String namespaceURI, final String localName, final String qname) throws SAXException {
        this._handler.endElement(namespaceURI, localName, qname);
    }
    
    public void processingInstruction(final String target, final String data) throws SAXException {
        this._handler.processingInstruction(target, data);
    }
    
    public void startCDATA() throws SAXException {
        if (this._lexHandler != null) {
            this._lexHandler.startCDATA();
        }
    }
    
    public void endCDATA() throws SAXException {
        if (this._lexHandler != null) {
            this._lexHandler.endCDATA();
        }
    }
    
    public void comment(final char[] ch, final int start, final int length) throws SAXException {
        if (this._lexHandler != null) {
            this._lexHandler.comment(ch, start, length);
        }
    }
    
    public void ignorableWhitespace(final char[] ch, final int start, final int length) throws SAXException {
        this._handler.ignorableWhitespace(ch, start, length);
    }
    
    public void setDocumentLocator(final Locator locator) {
        this._locator = locator;
        if (this._handler != null) {
            this._handler.setDocumentLocator(locator);
        }
    }
    
    public void skippedEntity(final String name) throws SAXException {
        this._handler.skippedEntity(name);
    }
    
    public void startPrefixMapping(final String prefix, final String uri) throws SAXException {
        this._handler.startPrefixMapping(prefix, uri);
    }
    
    public void endPrefixMapping(final String prefix) throws SAXException {
        this._handler.endPrefixMapping(prefix);
    }
    
    public void startDTD(final String name, final String publicId, final String systemId) throws SAXException {
        if (this._lexHandler != null) {
            this._lexHandler.startDTD(name, publicId, systemId);
        }
    }
    
    public void endDTD() throws SAXException {
        if (this._lexHandler != null) {
            this._lexHandler.endDTD();
        }
    }
    
    public void startEntity(final String name) throws SAXException {
        if (this._lexHandler != null) {
            this._lexHandler.startEntity(name);
        }
    }
    
    public void endEntity(final String name) throws SAXException {
        if (this._lexHandler != null) {
            this._lexHandler.endEntity(name);
        }
    }
    
    public void unparsedEntityDecl(final String name, final String publicId, final String systemId, final String notationName) throws SAXException {
        if (this._dtdHandler != null) {
            this._dtdHandler.unparsedEntityDecl(name, publicId, systemId, notationName);
        }
    }
    
    public void notationDecl(final String name, final String publicId, final String systemId) throws SAXException {
        if (this._dtdHandler != null) {
            this._dtdHandler.notationDecl(name, publicId, systemId);
        }
    }
    
    public void attributeDecl(final String eName, final String aName, final String type, final String valueDefault, final String value) throws SAXException {
        if (this._declHandler != null) {
            this._declHandler.attributeDecl(eName, aName, type, valueDefault, value);
        }
    }
    
    public void elementDecl(final String name, final String model) throws SAXException {
        if (this._declHandler != null) {
            this._declHandler.elementDecl(name, model);
        }
    }
    
    public void externalEntityDecl(final String name, final String publicId, final String systemId) throws SAXException {
        if (this._declHandler != null) {
            this._declHandler.externalEntityDecl(name, publicId, systemId);
        }
    }
    
    public void internalEntityDecl(final String name, final String value) throws SAXException {
        if (this._declHandler != null) {
            this._declHandler.internalEntityDecl(name, value);
        }
    }
}
