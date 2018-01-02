// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.parsers;

import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXNotRecognizedException;
import org.apache.xerces.util.ErrorHandlerWrapper;
import org.apache.xerces.xni.parser.XMLErrorHandler;
import org.xml.sax.ErrorHandler;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.util.EntityResolverWrapper;
import org.apache.xerces.util.EntityResolver2Wrapper;
import org.xml.sax.ext.EntityResolver2;
import org.apache.xerces.xni.parser.XMLEntityResolver;
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
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.xni.parser.XMLParserConfiguration;

public class DOMParser extends AbstractDOMParser
{
    protected static final String USE_ENTITY_RESOLVER2 = "http://xml.org/sax/features/use-entity-resolver2";
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    private static final String[] RECOGNIZED_PROPERTIES;
    protected boolean fUseEntityResolver2;
    
    public DOMParser(final XMLParserConfiguration xmlParserConfiguration) {
        super(xmlParserConfiguration);
        this.fUseEntityResolver2 = true;
    }
    
    public DOMParser() {
        this(null, null);
    }
    
    public DOMParser(final SymbolTable symbolTable) {
        this(symbolTable, null);
    }
    
    public DOMParser(final SymbolTable symbolTable, final XMLGrammarPool xmlGrammarPool) {
        super((XMLParserConfiguration)ObjectFactory.createObject("org.apache.xerces.xni.parser.XMLParserConfiguration", "org.apache.xerces.parsers.XIncludeAwareParserConfiguration"));
        this.fUseEntityResolver2 = true;
        super.fConfiguration.addRecognizedProperties(DOMParser.RECOGNIZED_PROPERTIES);
        if (symbolTable != null) {
            super.fConfiguration.setProperty("http://apache.org/xml/properties/internal/symbol-table", symbolTable);
        }
        if (xmlGrammarPool != null) {
            super.fConfiguration.setProperty("http://apache.org/xml/properties/internal/grammar-pool", xmlGrammarPool);
        }
    }
    
    public void parse(final String s) throws SAXException, IOException {
        final XMLInputSource xmlInputSource = new XMLInputSource(null, s, null);
        try {
            this.parse(xmlInputSource);
        }
        catch (XMLParseException ex) {
            final Exception exception = ex.getException();
            if (exception == null) {
                final LocatorImpl locatorImpl = new LocatorImpl();
                locatorImpl.setPublicId(ex.getPublicId());
                locatorImpl.setSystemId(ex.getExpandedSystemId());
                locatorImpl.setLineNumber(ex.getLineNumber());
                locatorImpl.setColumnNumber(ex.getColumnNumber());
                throw new SAXParseException(ex.getMessage(), locatorImpl);
            }
            if (exception instanceof SAXException) {
                throw (SAXException)exception;
            }
            if (exception instanceof IOException) {
                throw (IOException)exception;
            }
            throw new SAXException(exception);
        }
        catch (XNIException ex2) {
            ex2.printStackTrace();
            final Exception exception2 = ex2.getException();
            if (exception2 == null) {
                throw new SAXException(ex2.getMessage());
            }
            if (exception2 instanceof SAXException) {
                throw (SAXException)exception2;
            }
            if (exception2 instanceof IOException) {
                throw (IOException)exception2;
            }
            throw new SAXException(exception2);
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
        catch (XMLParseException ex) {
            final Exception exception = ex.getException();
            if (exception == null) {
                final LocatorImpl locatorImpl = new LocatorImpl();
                locatorImpl.setPublicId(ex.getPublicId());
                locatorImpl.setSystemId(ex.getExpandedSystemId());
                locatorImpl.setLineNumber(ex.getLineNumber());
                locatorImpl.setColumnNumber(ex.getColumnNumber());
                throw new SAXParseException(ex.getMessage(), locatorImpl);
            }
            if (exception instanceof SAXException) {
                throw (SAXException)exception;
            }
            if (exception instanceof IOException) {
                throw (IOException)exception;
            }
            throw new SAXException(exception);
        }
        catch (XNIException ex2) {
            final Exception exception2 = ex2.getException();
            if (exception2 == null) {
                throw new SAXException(ex2.getMessage());
            }
            if (exception2 instanceof SAXException) {
                throw (SAXException)exception2;
            }
            if (exception2 instanceof IOException) {
                throw (IOException)exception2;
            }
            throw new SAXException(exception2);
        }
    }
    
    public void setEntityResolver(final EntityResolver entityResolver) {
        try {
            final XMLEntityResolver xmlEntityResolver = (XMLEntityResolver)super.fConfiguration.getProperty("http://apache.org/xml/properties/internal/entity-resolver");
            if (this.fUseEntityResolver2 && entityResolver instanceof EntityResolver2) {
                if (xmlEntityResolver instanceof EntityResolver2Wrapper) {
                    ((EntityResolver2Wrapper)xmlEntityResolver).setEntityResolver((EntityResolver2)entityResolver);
                }
                else {
                    super.fConfiguration.setProperty("http://apache.org/xml/properties/internal/entity-resolver", new EntityResolver2Wrapper((EntityResolver2)entityResolver));
                }
            }
            else if (xmlEntityResolver instanceof EntityResolverWrapper) {
                ((EntityResolverWrapper)xmlEntityResolver).setEntityResolver(entityResolver);
            }
            else {
                super.fConfiguration.setProperty("http://apache.org/xml/properties/internal/entity-resolver", new EntityResolverWrapper(entityResolver));
            }
        }
        catch (XMLConfigurationException ex) {}
    }
    
    public EntityResolver getEntityResolver() {
        EntityResolver entityResolver = null;
        try {
            final XMLEntityResolver xmlEntityResolver = (XMLEntityResolver)super.fConfiguration.getProperty("http://apache.org/xml/properties/internal/entity-resolver");
            if (xmlEntityResolver != null) {
                if (xmlEntityResolver instanceof EntityResolverWrapper) {
                    entityResolver = ((EntityResolverWrapper)xmlEntityResolver).getEntityResolver();
                }
                else if (xmlEntityResolver instanceof EntityResolver2Wrapper) {
                    entityResolver = ((EntityResolver2Wrapper)xmlEntityResolver).getEntityResolver();
                }
            }
        }
        catch (XMLConfigurationException ex) {}
        return entityResolver;
    }
    
    public void setErrorHandler(final ErrorHandler errorHandler) {
        try {
            final XMLErrorHandler xmlErrorHandler = (XMLErrorHandler)super.fConfiguration.getProperty("http://apache.org/xml/properties/internal/error-handler");
            if (xmlErrorHandler instanceof ErrorHandlerWrapper) {
                ((ErrorHandlerWrapper)xmlErrorHandler).setErrorHandler(errorHandler);
            }
            else {
                super.fConfiguration.setProperty("http://apache.org/xml/properties/internal/error-handler", new ErrorHandlerWrapper(errorHandler));
            }
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
    
    public void setFeature(final String s, final boolean fUseEntityResolver2) throws SAXNotRecognizedException, SAXNotSupportedException {
        try {
            if (s.equals("http://xml.org/sax/features/use-entity-resolver2")) {
                if (fUseEntityResolver2 != this.fUseEntityResolver2) {
                    this.fUseEntityResolver2 = fUseEntityResolver2;
                    this.setEntityResolver(this.getEntityResolver());
                }
                return;
            }
            super.fConfiguration.setFeature(s, fUseEntityResolver2);
        }
        catch (XMLConfigurationException ex) {
            final String message = ex.getMessage();
            if (ex.getType() == 0) {
                throw new SAXNotRecognizedException(message);
            }
            throw new SAXNotSupportedException(message);
        }
    }
    
    public boolean getFeature(final String s) throws SAXNotRecognizedException, SAXNotSupportedException {
        try {
            return super.fConfiguration.getFeature(s);
        }
        catch (XMLConfigurationException ex) {
            final String message = ex.getMessage();
            if (ex.getType() == 0) {
                throw new SAXNotRecognizedException(message);
            }
            throw new SAXNotSupportedException(message);
        }
    }
    
    public void setProperty(final String s, final Object o) throws SAXNotRecognizedException, SAXNotSupportedException {
        try {
            super.fConfiguration.setProperty(s, o);
        }
        catch (XMLConfigurationException ex) {
            final String message = ex.getMessage();
            if (ex.getType() == 0) {
                throw new SAXNotRecognizedException(message);
            }
            throw new SAXNotSupportedException(message);
        }
    }
    
    public Object getProperty(final String s) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (s.equals("http://apache.org/xml/properties/dom/current-element-node")) {
            boolean feature = false;
            try {
                feature = this.getFeature("http://apache.org/xml/features/dom/defer-node-expansion");
            }
            catch (XMLConfigurationException ex2) {}
            if (feature) {
                throw new SAXNotSupportedException("Current element node cannot be queried when node expansion is deferred.");
            }
            return (super.fCurrentNode != null && super.fCurrentNode.getNodeType() == 1) ? super.fCurrentNode : null;
        }
        else {
            try {
                return super.fConfiguration.getProperty(s);
            }
            catch (XMLConfigurationException ex) {
                final String message = ex.getMessage();
                if (ex.getType() == 0) {
                    throw new SAXNotRecognizedException(message);
                }
                throw new SAXNotSupportedException(message);
            }
        }
    }
    
    public XMLParserConfiguration getXMLParserConfiguration() {
        return super.fConfiguration;
    }
    
    static {
        RECOGNIZED_PROPERTIES = new String[] { "http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/grammar-pool" };
    }
}
