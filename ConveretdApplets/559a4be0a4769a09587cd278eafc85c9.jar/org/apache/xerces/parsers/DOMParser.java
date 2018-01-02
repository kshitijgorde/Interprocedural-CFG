// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.parsers;

import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXNotRecognizedException;
import org.apache.xerces.xni.parser.XMLErrorHandler;
import org.apache.xerces.util.ErrorHandlerWrapper;
import org.xml.sax.ErrorHandler;
import org.apache.xerces.xni.parser.XMLEntityResolver;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.util.EntityResolverWrapper;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.parser.XMLParseException;
import java.io.IOException;
import org.xml.sax.SAXException;
import org.xml.sax.Locator;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.LocatorImpl;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.util.ObjectFactory;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.xni.parser.XMLParserConfiguration;

public class DOMParser extends AbstractDOMParser
{
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    private static final String[] RECOGNIZED_PROPERTIES;
    
    public DOMParser(final XMLParserConfiguration config) {
        super(config);
    }
    
    public DOMParser() {
        this(null, null);
    }
    
    public DOMParser(final SymbolTable symbolTable) {
        this(symbolTable, null);
    }
    
    public DOMParser(final SymbolTable symbolTable, final XMLGrammarPool grammarPool) {
        super((XMLParserConfiguration)ObjectFactory.createObject("org.apache.xerces.xni.parser.XMLParserConfiguration", "org.apache.xerces.parsers.IntegratedParserConfiguration"));
        super.fConfiguration.addRecognizedProperties(DOMParser.RECOGNIZED_PROPERTIES);
        if (symbolTable != null) {
            super.fConfiguration.setProperty("http://apache.org/xml/properties/internal/symbol-table", symbolTable);
        }
        if (grammarPool != null) {
            super.fConfiguration.setProperty("http://apache.org/xml/properties/internal/grammar-pool", grammarPool);
        }
    }
    
    public void parse(final String systemId) throws SAXException, IOException {
        final XMLInputSource source = new XMLInputSource(null, systemId, null);
        try {
            this.parse(source);
        }
        catch (XMLParseException e) {
            final Exception exception = e.getException();
            if (exception == null) {
                final LocatorImpl locatorImpl = new LocatorImpl();
                locatorImpl.setPublicId(e.getPublicId());
                locatorImpl.setSystemId(e.getExpandedSystemId());
                locatorImpl.setLineNumber(e.getLineNumber());
                locatorImpl.setColumnNumber(e.getColumnNumber());
                throw new SAXParseException(e.getMessage(), locatorImpl);
            }
            if (exception instanceof SAXException) {
                throw (SAXException)exception;
            }
            if (exception instanceof IOException) {
                throw (IOException)exception;
            }
            throw new SAXException(exception);
        }
        catch (XNIException e2) {
            e2.printStackTrace();
            final Exception ex = e2.getException();
            if (ex == null) {
                throw new SAXException(e2.getMessage());
            }
            if (ex instanceof SAXException) {
                throw (SAXException)ex;
            }
            if (ex instanceof IOException) {
                throw (IOException)ex;
            }
            throw new SAXException(ex);
        }
    }
    
    public void parse(final InputSource inputSource) throws SAXException, IOException {
        try {
            final XMLInputSource xmlInputSource = new XMLInputSource(inputSource.getPublicId(), inputSource.getSystemId(), null);
            xmlInputSource.setByteStream(inputSource.getByteStream());
            xmlInputSource.setCharacterStream(inputSource.getCharacterStream());
            xmlInputSource.setEncoding(inputSource.getEncoding());
            this.parse(xmlInputSource);
        }
        catch (XMLParseException e) {
            final Exception exception = e.getException();
            if (exception == null) {
                final LocatorImpl locatorImpl = new LocatorImpl();
                locatorImpl.setPublicId(e.getPublicId());
                locatorImpl.setSystemId(e.getExpandedSystemId());
                locatorImpl.setLineNumber(e.getLineNumber());
                locatorImpl.setColumnNumber(e.getColumnNumber());
                throw new SAXParseException(e.getMessage(), locatorImpl);
            }
            if (exception instanceof SAXException) {
                throw (SAXException)exception;
            }
            if (exception instanceof IOException) {
                throw (IOException)exception;
            }
            throw new SAXException(exception);
        }
        catch (XNIException e2) {
            final Exception ex = e2.getException();
            if (ex == null) {
                throw new SAXException(e2.getMessage());
            }
            if (ex instanceof SAXException) {
                throw (SAXException)ex;
            }
            if (ex instanceof IOException) {
                throw (IOException)ex;
            }
            throw new SAXException(ex);
        }
    }
    
    public void setEntityResolver(final EntityResolver resolver) {
        try {
            super.fConfiguration.setProperty("http://apache.org/xml/properties/internal/entity-resolver", new EntityResolverWrapper(resolver));
        }
        catch (XMLConfigurationException ex) {}
    }
    
    public EntityResolver getEntityResolver() {
        EntityResolver entityResolver = null;
        try {
            final XMLEntityResolver xmlEntityResolver = (XMLEntityResolver)super.fConfiguration.getProperty("http://apache.org/xml/properties/internal/entity-resolver");
            if (xmlEntityResolver != null && xmlEntityResolver instanceof EntityResolverWrapper) {
                entityResolver = ((EntityResolverWrapper)xmlEntityResolver).getEntityResolver();
            }
        }
        catch (XMLConfigurationException ex) {}
        return entityResolver;
    }
    
    public void setErrorHandler(final ErrorHandler errorHandler) {
        try {
            super.fConfiguration.setProperty("http://apache.org/xml/properties/internal/error-handler", new ErrorHandlerWrapper(errorHandler));
        }
        catch (XMLConfigurationException ex) {}
    }
    
    public ErrorHandler getErrorHandler() {
        ErrorHandler errorHandler = null;
        try {
            final XMLErrorHandler xmlErrorHandler = (XMLErrorHandler)super.fConfiguration.getProperty("http://apache.org/xml/properties/internal/error-handler");
            if (xmlErrorHandler != null && xmlErrorHandler instanceof ErrorHandlerWrapper) {
                errorHandler = ((ErrorHandlerWrapper)xmlErrorHandler).getErrorHandler();
            }
        }
        catch (XMLConfigurationException ex) {}
        return errorHandler;
    }
    
    public void setFeature(final String featureId, final boolean state) throws SAXNotRecognizedException, SAXNotSupportedException {
        try {
            super.fConfiguration.setFeature(featureId, state);
        }
        catch (XMLConfigurationException e) {
            final String message = e.getMessage();
            if (e.getType() == 0) {
                throw new SAXNotRecognizedException(message);
            }
            throw new SAXNotSupportedException(message);
        }
    }
    
    public boolean getFeature(final String featureId) throws SAXNotRecognizedException, SAXNotSupportedException {
        try {
            return super.fConfiguration.getFeature(featureId);
        }
        catch (XMLConfigurationException e) {
            final String message = e.getMessage();
            if (e.getType() == 0) {
                throw new SAXNotRecognizedException(message);
            }
            throw new SAXNotSupportedException(message);
        }
    }
    
    public void setProperty(final String propertyId, final Object value) throws SAXNotRecognizedException, SAXNotSupportedException {
        try {
            super.fConfiguration.setProperty(propertyId, value);
        }
        catch (XMLConfigurationException e) {
            final String message = e.getMessage();
            if (e.getType() == 0) {
                throw new SAXNotRecognizedException(message);
            }
            throw new SAXNotSupportedException(message);
        }
    }
    
    public Object getProperty(final String propertyId) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (propertyId.equals("http://apache.org/xml/properties/dom/current-element-node")) {
            boolean deferred = false;
            try {
                deferred = this.getFeature("http://apache.org/xml/features/dom/defer-node-expansion");
            }
            catch (XMLConfigurationException ex) {}
            if (deferred) {
                throw new SAXNotSupportedException("Current element node cannot be queried when node expansion is deferred.");
            }
            return (super.fCurrentNode != null && super.fCurrentNode.getNodeType() == 1) ? super.fCurrentNode : null;
        }
        else {
            try {
                return super.fConfiguration.getProperty(propertyId);
            }
            catch (XMLConfigurationException e) {
                final String message = e.getMessage();
                if (e.getType() == 0) {
                    throw new SAXNotRecognizedException(message);
                }
                throw new SAXNotSupportedException(message);
            }
        }
    }
    
    static {
        RECOGNIZED_PROPERTIES = new String[] { "http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/grammar-pool" };
    }
}
