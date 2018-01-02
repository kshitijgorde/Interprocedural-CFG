// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

import javax.xml.transform.ErrorListener;
import org.xml.sax.SAXParseException;
import org.xml.sax.Attributes;
import org.apache.xml.dtm.DTMManager;
import java.io.IOException;
import org.xml.sax.InputSource;
import javax.xml.transform.Transformer;
import org.apache.xml.serializer.SerializationHandler;
import javax.xml.transform.TransformerException;
import org.apache.xalan.res.XSLMessages;
import org.apache.xml.dtm.ref.IncrementalSAXSource_Filter;
import org.apache.xml.dtm.ref.sax2dtm.SAX2DTM;
import org.xml.sax.SAXException;
import org.apache.xpath.XPathContext;
import org.apache.xml.dtm.DTMWSFilter;
import javax.xml.transform.Source;
import org.apache.xml.dtm.DTM;
import org.xml.sax.Locator;
import javax.xml.transform.Result;
import org.xml.sax.ext.DeclHandler;
import javax.xml.transform.sax.TransformerHandler;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;

public class TransformerHandlerImpl implements EntityResolver, DTDHandler, ContentHandler, ErrorHandler, LexicalHandler, TransformerHandler, DeclHandler
{
    private boolean m_insideParse;
    private static boolean DEBUG;
    private TransformerImpl m_transformer;
    private String m_baseSystemID;
    private Result m_result;
    private Locator m_locator;
    private EntityResolver m_entityResolver;
    private DTDHandler m_dtdHandler;
    private ContentHandler m_contentHandler;
    private ErrorHandler m_errorHandler;
    private LexicalHandler m_lexicalHandler;
    private DeclHandler m_declHandler;
    DTM m_dtm;
    
    public TransformerHandlerImpl(final TransformerImpl transformer, final boolean doFragment, final String baseSystemID) {
        this.m_insideParse = false;
        this.m_result = null;
        this.m_locator = null;
        this.m_entityResolver = null;
        this.m_dtdHandler = null;
        this.m_contentHandler = null;
        this.m_errorHandler = null;
        this.m_lexicalHandler = null;
        this.m_declHandler = null;
        this.m_transformer = transformer;
        this.m_baseSystemID = baseSystemID;
        final XPathContext xctxt = transformer.getXPathContext();
        final DTM dtm = xctxt.getDTM(null, true, transformer, true, true);
        (this.m_dtm = dtm).setDocumentBaseURI(baseSystemID);
        this.m_contentHandler = dtm.getContentHandler();
        this.m_dtdHandler = dtm.getDTDHandler();
        this.m_entityResolver = dtm.getEntityResolver();
        this.m_errorHandler = dtm.getErrorHandler();
        this.m_lexicalHandler = dtm.getLexicalHandler();
    }
    
    protected void clearCoRoutine() {
        this.clearCoRoutine(null);
    }
    
    protected void clearCoRoutine(final SAXException ex) {
        if (null != ex) {
            this.m_transformer.setExceptionThrown(ex);
        }
        if (this.m_dtm instanceof SAX2DTM) {
            if (TransformerHandlerImpl.DEBUG) {
                System.err.println("In clearCoRoutine...");
            }
            try {
                final SAX2DTM sax2dtm = (SAX2DTM)this.m_dtm;
                if (null != this.m_contentHandler && this.m_contentHandler instanceof IncrementalSAXSource_Filter) {
                    final IncrementalSAXSource_Filter sp = (IncrementalSAXSource_Filter)this.m_contentHandler;
                    sp.deliverMoreNodes(false);
                }
                sax2dtm.clearCoRoutine(true);
                this.m_contentHandler = null;
                this.m_dtdHandler = null;
                this.m_entityResolver = null;
                this.m_errorHandler = null;
                this.m_lexicalHandler = null;
            }
            catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            if (TransformerHandlerImpl.DEBUG) {
                System.err.println("...exiting clearCoRoutine");
            }
        }
    }
    
    public void setResult(final Result result) throws IllegalArgumentException {
        if (null == result) {
            throw new IllegalArgumentException(XSLMessages.createMessage("ER_RESULT_NULL", null));
        }
        try {
            final SerializationHandler xoh = this.m_transformer.createSerializationHandler(result);
            this.m_transformer.setSerializationHandler(xoh);
        }
        catch (TransformerException te) {
            throw new IllegalArgumentException(XSLMessages.createMessage("ER_RESULT_COULD_NOT_BE_SET", null));
        }
        this.m_result = result;
    }
    
    public void setSystemId(final String systemID) {
        this.m_baseSystemID = systemID;
        this.m_dtm.setDocumentBaseURI(systemID);
    }
    
    public String getSystemId() {
        return this.m_baseSystemID;
    }
    
    public Transformer getTransformer() {
        return this.m_transformer;
    }
    
    public InputSource resolveEntity(final String publicId, final String systemId) throws SAXException, IOException {
        if (this.m_entityResolver != null) {
            return this.m_entityResolver.resolveEntity(publicId, systemId);
        }
        return null;
    }
    
    public void notationDecl(final String name, final String publicId, final String systemId) throws SAXException {
        if (this.m_dtdHandler != null) {
            this.m_dtdHandler.notationDecl(name, publicId, systemId);
        }
    }
    
    public void unparsedEntityDecl(final String name, final String publicId, final String systemId, final String notationName) throws SAXException {
        if (this.m_dtdHandler != null) {
            this.m_dtdHandler.unparsedEntityDecl(name, publicId, systemId, notationName);
        }
    }
    
    public void setDocumentLocator(final Locator locator) {
        if (TransformerHandlerImpl.DEBUG) {
            System.out.println("TransformerHandlerImpl#setDocumentLocator: " + locator.getSystemId());
        }
        this.m_locator = locator;
        if (null == this.m_baseSystemID) {
            this.setSystemId(locator.getSystemId());
        }
        if (this.m_contentHandler != null) {
            this.m_contentHandler.setDocumentLocator(locator);
        }
    }
    
    public void startDocument() throws SAXException {
        if (TransformerHandlerImpl.DEBUG) {
            System.out.println("TransformerHandlerImpl#startDocument");
        }
        this.m_insideParse = true;
        if (this.m_contentHandler != null) {
            if (DTMManager.getIncremental()) {
                this.m_transformer.setSourceTreeDocForThread(this.m_dtm.getDocument());
                final int cpriority = Thread.currentThread().getPriority();
                this.m_transformer.runTransformThread(cpriority);
            }
            this.m_contentHandler.startDocument();
        }
    }
    
    public void endDocument() throws SAXException {
        if (TransformerHandlerImpl.DEBUG) {
            System.out.println("TransformerHandlerImpl#endDocument");
        }
        this.m_insideParse = false;
        if (this.m_contentHandler != null) {
            this.m_contentHandler.endDocument();
        }
        if (DTMManager.getIncremental()) {
            this.m_transformer.waitTransformThread();
        }
        else {
            this.m_transformer.setSourceTreeDocForThread(this.m_dtm.getDocument());
            this.m_transformer.run();
        }
    }
    
    public void startPrefixMapping(final String prefix, final String uri) throws SAXException {
        if (TransformerHandlerImpl.DEBUG) {
            System.out.println("TransformerHandlerImpl#startPrefixMapping: " + prefix + ", " + uri);
        }
        if (this.m_contentHandler != null) {
            this.m_contentHandler.startPrefixMapping(prefix, uri);
        }
    }
    
    public void endPrefixMapping(final String prefix) throws SAXException {
        if (TransformerHandlerImpl.DEBUG) {
            System.out.println("TransformerHandlerImpl#endPrefixMapping: " + prefix);
        }
        if (this.m_contentHandler != null) {
            this.m_contentHandler.endPrefixMapping(prefix);
        }
    }
    
    public void startElement(final String uri, final String localName, final String qName, final Attributes atts) throws SAXException {
        if (TransformerHandlerImpl.DEBUG) {
            System.out.println("TransformerHandlerImpl#startElement: " + qName);
        }
        if (this.m_contentHandler != null) {
            this.m_contentHandler.startElement(uri, localName, qName, atts);
        }
    }
    
    public void endElement(final String uri, final String localName, final String qName) throws SAXException {
        if (TransformerHandlerImpl.DEBUG) {
            System.out.println("TransformerHandlerImpl#endElement: " + qName);
        }
        if (this.m_contentHandler != null) {
            this.m_contentHandler.endElement(uri, localName, qName);
        }
    }
    
    public void characters(final char[] ch, final int start, final int length) throws SAXException {
        if (TransformerHandlerImpl.DEBUG) {
            System.out.println("TransformerHandlerImpl#characters: " + start + ", " + length);
        }
        if (this.m_contentHandler != null) {
            this.m_contentHandler.characters(ch, start, length);
        }
    }
    
    public void ignorableWhitespace(final char[] ch, final int start, final int length) throws SAXException {
        if (TransformerHandlerImpl.DEBUG) {
            System.out.println("TransformerHandlerImpl#ignorableWhitespace: " + start + ", " + length);
        }
        if (this.m_contentHandler != null) {
            this.m_contentHandler.ignorableWhitespace(ch, start, length);
        }
    }
    
    public void processingInstruction(final String target, final String data) throws SAXException {
        if (TransformerHandlerImpl.DEBUG) {
            System.out.println("TransformerHandlerImpl#processingInstruction: " + target + ", " + data);
        }
        if (this.m_contentHandler != null) {
            this.m_contentHandler.processingInstruction(target, data);
        }
    }
    
    public void skippedEntity(final String name) throws SAXException {
        if (TransformerHandlerImpl.DEBUG) {
            System.out.println("TransformerHandlerImpl#skippedEntity: " + name);
        }
        if (this.m_contentHandler != null) {
            this.m_contentHandler.skippedEntity(name);
        }
    }
    
    public void warning(final SAXParseException e) throws SAXException {
        final ErrorListener errorListener = this.m_transformer.getErrorListener();
        if (errorListener instanceof ErrorHandler) {
            ((ErrorHandler)errorListener).warning(e);
        }
        else {
            try {
                errorListener.warning(new TransformerException(e));
            }
            catch (TransformerException te) {
                throw e;
            }
        }
    }
    
    public void error(final SAXParseException e) throws SAXException {
        final ErrorListener errorListener = this.m_transformer.getErrorListener();
        if (errorListener instanceof ErrorHandler) {
            ((ErrorHandler)errorListener).error(e);
            if (null != this.m_errorHandler) {
                this.m_errorHandler.error(e);
            }
        }
        else {
            try {
                errorListener.error(new TransformerException(e));
                if (null != this.m_errorHandler) {
                    this.m_errorHandler.error(e);
                }
            }
            catch (TransformerException te) {
                throw e;
            }
        }
    }
    
    public void fatalError(final SAXParseException e) throws SAXException {
        if (null != this.m_errorHandler) {
            try {
                this.m_errorHandler.fatalError(e);
            }
            catch (SAXParseException ex) {}
        }
        final ErrorListener errorListener = this.m_transformer.getErrorListener();
        if (errorListener instanceof ErrorHandler) {
            ((ErrorHandler)errorListener).fatalError(e);
            if (null != this.m_errorHandler) {
                this.m_errorHandler.fatalError(e);
            }
        }
        else {
            try {
                errorListener.fatalError(new TransformerException(e));
                if (null != this.m_errorHandler) {
                    this.m_errorHandler.fatalError(e);
                }
            }
            catch (TransformerException te) {
                throw e;
            }
        }
    }
    
    public void startDTD(final String name, final String publicId, final String systemId) throws SAXException {
        if (TransformerHandlerImpl.DEBUG) {
            System.out.println("TransformerHandlerImpl#startDTD: " + name + ", " + publicId + ", " + systemId);
        }
        if (null != this.m_lexicalHandler) {
            this.m_lexicalHandler.startDTD(name, publicId, systemId);
        }
    }
    
    public void endDTD() throws SAXException {
        if (TransformerHandlerImpl.DEBUG) {
            System.out.println("TransformerHandlerImpl#endDTD");
        }
        if (null != this.m_lexicalHandler) {
            this.m_lexicalHandler.endDTD();
        }
    }
    
    public void startEntity(final String name) throws SAXException {
        if (TransformerHandlerImpl.DEBUG) {
            System.out.println("TransformerHandlerImpl#startEntity: " + name);
        }
        if (null != this.m_lexicalHandler) {
            this.m_lexicalHandler.startEntity(name);
        }
    }
    
    public void endEntity(final String name) throws SAXException {
        if (TransformerHandlerImpl.DEBUG) {
            System.out.println("TransformerHandlerImpl#endEntity: " + name);
        }
        if (null != this.m_lexicalHandler) {
            this.m_lexicalHandler.endEntity(name);
        }
    }
    
    public void startCDATA() throws SAXException {
        if (TransformerHandlerImpl.DEBUG) {
            System.out.println("TransformerHandlerImpl#startCDATA");
        }
        if (null != this.m_lexicalHandler) {
            this.m_lexicalHandler.startCDATA();
        }
    }
    
    public void endCDATA() throws SAXException {
        if (TransformerHandlerImpl.DEBUG) {
            System.out.println("TransformerHandlerImpl#endCDATA");
        }
        if (null != this.m_lexicalHandler) {
            this.m_lexicalHandler.endCDATA();
        }
    }
    
    public void comment(final char[] ch, final int start, final int length) throws SAXException {
        if (TransformerHandlerImpl.DEBUG) {
            System.out.println("TransformerHandlerImpl#comment: " + start + ", " + length);
        }
        if (null != this.m_lexicalHandler) {
            this.m_lexicalHandler.comment(ch, start, length);
        }
    }
    
    public void elementDecl(final String name, final String model) throws SAXException {
        if (TransformerHandlerImpl.DEBUG) {
            System.out.println("TransformerHandlerImpl#elementDecl: " + name + ", " + model);
        }
        if (null != this.m_declHandler) {
            this.m_declHandler.elementDecl(name, model);
        }
    }
    
    public void attributeDecl(final String eName, final String aName, final String type, final String valueDefault, final String value) throws SAXException {
        if (TransformerHandlerImpl.DEBUG) {
            System.out.println("TransformerHandlerImpl#attributeDecl: " + eName + ", " + aName + ", etc...");
        }
        if (null != this.m_declHandler) {
            this.m_declHandler.attributeDecl(eName, aName, type, valueDefault, value);
        }
    }
    
    public void internalEntityDecl(final String name, final String value) throws SAXException {
        if (TransformerHandlerImpl.DEBUG) {
            System.out.println("TransformerHandlerImpl#internalEntityDecl: " + name + ", " + value);
        }
        if (null != this.m_declHandler) {
            this.m_declHandler.internalEntityDecl(name, value);
        }
    }
    
    public void externalEntityDecl(final String name, final String publicId, final String systemId) throws SAXException {
        if (TransformerHandlerImpl.DEBUG) {
            System.out.println("TransformerHandlerImpl#externalEntityDecl: " + name + ", " + publicId + ", " + systemId);
        }
        if (null != this.m_declHandler) {
            this.m_declHandler.externalEntityDecl(name, publicId, systemId);
        }
    }
    
    static {
        TransformerHandlerImpl.DEBUG = false;
    }
}
