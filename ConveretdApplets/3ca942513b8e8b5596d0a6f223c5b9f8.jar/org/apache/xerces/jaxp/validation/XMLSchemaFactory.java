// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.jaxp.validation;

import org.apache.xerces.xni.grammars.XMLGrammarDescription;
import org.apache.xerces.xni.grammars.Grammar;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXNotRecognizedException;
import org.apache.xerces.util.SAXMessageFormatter;
import org.xml.sax.InputSource;
import java.io.Reader;
import java.io.InputStream;
import java.io.IOException;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.parser.XMLParseException;
import org.apache.xerces.util.DOMInputSource;
import javax.xml.transform.dom.DOMSource;
import org.apache.xerces.util.SAXInputSource;
import org.xml.sax.SAXException;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import javax.xml.validation.Schema;
import javax.xml.transform.Source;
import java.util.Locale;
import org.apache.xerces.xni.parser.XMLErrorHandler;
import org.apache.xerces.xni.parser.XMLEntityResolver;
import org.apache.xerces.util.SecurityManager;
import org.apache.xerces.util.ErrorHandlerWrapper;
import org.apache.xerces.util.DOMEntityResolverWrapper;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.apache.xerces.impl.xs.XMLSchemaLoader;
import javax.xml.validation.SchemaFactory;

public final class XMLSchemaFactory extends SchemaFactory
{
    private static final String SCHEMA_FULL_CHECKING = "http://apache.org/xml/features/validation/schema-full-checking";
    private static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    private static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
    private XMLSchemaLoader fXMLSchemaLoader;
    private ErrorHandler fErrorHandler;
    private LSResourceResolver fLSResourceResolver;
    private DOMEntityResolverWrapper fDOMEntityResolverWrapper;
    private ErrorHandlerWrapper fErrorHandlerWrapper;
    private SecurityManager fSecurityManager;
    private XMLGrammarPoolWrapper fXMLGrammarPoolWrapper;
    
    public XMLSchemaFactory() {
        this.fErrorHandler = null;
        this.fLSResourceResolver = null;
        this.fErrorHandlerWrapper = new ErrorHandlerWrapper(DefaultErrorHandler.getInstance());
        this.fDOMEntityResolverWrapper = new DOMEntityResolverWrapper();
        this.fXMLGrammarPoolWrapper = new XMLGrammarPoolWrapper();
        (this.fXMLSchemaLoader = new XMLSchemaLoader()).setFeature("http://apache.org/xml/features/validation/schema-full-checking", true);
        this.fXMLSchemaLoader.setProperty("http://apache.org/xml/properties/internal/grammar-pool", this.fXMLGrammarPoolWrapper);
        this.fXMLSchemaLoader.setEntityResolver(this.fDOMEntityResolverWrapper);
        this.fXMLSchemaLoader.setErrorHandler(this.fErrorHandlerWrapper);
    }
    
    public boolean isSchemaLanguageSupported(final String s) {
        if (s == null) {
            throw new NullPointerException(JAXPValidationMessageFormatter.formatMessage(Locale.getDefault(), "SchemaLanguageNull", null));
        }
        if (s.length() == 0) {
            throw new IllegalArgumentException(JAXPValidationMessageFormatter.formatMessage(Locale.getDefault(), "SchemaLanguageLengthZero", null));
        }
        return s.equals("http://www.w3.org/2001/XMLSchema");
    }
    
    public void setErrorHandler(final ErrorHandler fErrorHandler) {
        this.fErrorHandler = fErrorHandler;
        this.fErrorHandlerWrapper.setErrorHandler((fErrorHandler != null) ? fErrorHandler : DefaultErrorHandler.getInstance());
        this.fXMLSchemaLoader.setErrorHandler(this.fErrorHandlerWrapper);
    }
    
    public ErrorHandler getErrorHandler() {
        return this.fErrorHandler;
    }
    
    public void setResourceResolver(final LSResourceResolver lsResourceResolver) {
        this.fLSResourceResolver = lsResourceResolver;
        this.fDOMEntityResolverWrapper.setEntityResolver(lsResourceResolver);
        this.fXMLSchemaLoader.setEntityResolver(this.fDOMEntityResolverWrapper);
    }
    
    public LSResourceResolver getResourceResolver() {
        return this.fLSResourceResolver;
    }
    
    public Schema newSchema(final Source[] array) throws SAXException {
        if (array == null) {
            throw new NullPointerException(JAXPValidationMessageFormatter.formatMessage(Locale.getDefault(), "SchemaSourceArrayNull", null));
        }
        final int length = array.length;
        final LockedGrammarPool grammarPool = new LockedGrammarPool();
        this.fXMLGrammarPoolWrapper.setGrammarPool(grammarPool);
        final XMLInputSource[] array2 = new XMLInputSource[length];
        for (int i = 0; i < length; ++i) {
            final Source source = array[i];
            if (source instanceof StreamSource) {
                final StreamSource streamSource = (StreamSource)source;
                final String publicId = streamSource.getPublicId();
                final String systemId = streamSource.getSystemId();
                final InputStream inputStream = streamSource.getInputStream();
                final Reader reader = streamSource.getReader();
                (array2[i] = new XMLInputSource(publicId, systemId, null)).setByteStream(inputStream);
                array2[i].setCharacterStream(reader);
            }
            else if (source instanceof SAXSource) {
                final SAXSource saxSource = (SAXSource)source;
                final InputSource inputSource = saxSource.getInputSource();
                if (inputSource == null) {
                    throw new SAXException(JAXPValidationMessageFormatter.formatMessage(Locale.getDefault(), "SAXSourceNullInputSource", null));
                }
                array2[i] = new SAXInputSource(saxSource.getXMLReader(), inputSource);
            }
            else if (source instanceof DOMSource) {
                final DOMSource domSource = (DOMSource)source;
                array2[i] = new DOMInputSource(domSource.getNode(), domSource.getSystemId());
            }
            else {
                if (source == null) {
                    throw new NullPointerException(JAXPValidationMessageFormatter.formatMessage(Locale.getDefault(), "SchemaSourceArrayMemberNull", null));
                }
                throw new IllegalArgumentException(JAXPValidationMessageFormatter.formatMessage(Locale.getDefault(), "SchemaFactorySourceUnrecognized", new Object[] { ((DOMSource)source).getClass().getName() }));
            }
        }
        try {
            this.fXMLSchemaLoader.loadGrammar(array2);
        }
        catch (XMLParseException ex) {
            XMLSchemaValidatorHandler.convertToSAXParseException(ex);
        }
        catch (XNIException ex2) {
            XMLSchemaValidatorHandler.convertToSAXException(ex2);
        }
        catch (IOException ex3) {
            throw new SAXException(ex3);
        }
        this.fXMLGrammarPoolWrapper.setGrammarPool(null);
        final int grammarCount = grammarPool.getGrammarCount();
        if (grammarCount > 1) {
            grammarPool.lockPool();
            return new XMLSchema(grammarPool);
        }
        if (grammarCount == 1) {
            return new SimpleXMLSchema(grammarPool.retrieveInitialGrammarSet("http://www.w3.org/2001/XMLSchema")[0]);
        }
        return EmptyXMLSchema.getInstance();
    }
    
    public Schema newSchema() throws SAXException {
        return new WeakReferenceXMLSchema();
    }
    
    public boolean getFeature(final String s) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (s == null) {
            throw new NullPointerException(JAXPValidationMessageFormatter.formatMessage(Locale.getDefault(), "FeatureNameNull", null));
        }
        if (s.equals("http://javax.xml.XMLConstants/feature/secure-processing")) {
            return this.fSecurityManager != null;
        }
        try {
            return this.fXMLSchemaLoader.getFeature(s);
        }
        catch (XMLConfigurationException ex) {
            final String identifier = ex.getIdentifier();
            if (ex.getType() == 0) {
                throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(Locale.getDefault(), "feature-not-recognized", new Object[] { identifier }));
            }
            throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(Locale.getDefault(), "feature-not-supported", new Object[] { identifier }));
        }
    }
    
    public Object getProperty(final String s) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (s == null) {
            throw new NullPointerException(JAXPValidationMessageFormatter.formatMessage(Locale.getDefault(), "ProperyNameNull", null));
        }
        if (s.equals("http://apache.org/xml/properties/security-manager")) {
            return this.fSecurityManager;
        }
        if (s.equals("http://apache.org/xml/properties/internal/grammar-pool")) {
            throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(Locale.getDefault(), "property-not-supported", new Object[] { s }));
        }
        try {
            return this.fXMLSchemaLoader.getProperty(s);
        }
        catch (XMLConfigurationException ex) {
            final String identifier = ex.getIdentifier();
            if (ex.getType() == 0) {
                throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(Locale.getDefault(), "property-not-recognized", new Object[] { identifier }));
            }
            throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(Locale.getDefault(), "property-not-supported", new Object[] { identifier }));
        }
    }
    
    public void setFeature(final String s, final boolean b) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (s == null) {
            throw new NullPointerException(JAXPValidationMessageFormatter.formatMessage(Locale.getDefault(), "FeatureNameNull", null));
        }
        if (s.equals("http://javax.xml.XMLConstants/feature/secure-processing")) {
            this.fSecurityManager = (b ? new SecurityManager() : null);
            this.fXMLSchemaLoader.setProperty("http://apache.org/xml/properties/security-manager", this.fSecurityManager);
            return;
        }
        try {
            this.fXMLSchemaLoader.setFeature(s, b);
        }
        catch (XMLConfigurationException ex) {
            final String identifier = ex.getIdentifier();
            if (ex.getType() == 0) {
                throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(Locale.getDefault(), "feature-not-recognized", new Object[] { identifier }));
            }
            throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(Locale.getDefault(), "feature-not-supported", new Object[] { identifier }));
        }
    }
    
    public void setProperty(final String s, final Object o) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (s == null) {
            throw new NullPointerException(JAXPValidationMessageFormatter.formatMessage(Locale.getDefault(), "ProperyNameNull", null));
        }
        if (s.equals("http://apache.org/xml/properties/security-manager")) {
            this.fSecurityManager = (SecurityManager)o;
            this.fXMLSchemaLoader.setProperty("http://apache.org/xml/properties/security-manager", this.fSecurityManager);
            return;
        }
        if (s.equals("http://apache.org/xml/properties/internal/grammar-pool")) {
            throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(Locale.getDefault(), "property-not-supported", new Object[] { s }));
        }
        try {
            this.fXMLSchemaLoader.setProperty(s, o);
        }
        catch (XMLConfigurationException ex) {
            final String identifier = ex.getIdentifier();
            if (ex.getType() == 0) {
                throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(Locale.getDefault(), "property-not-recognized", new Object[] { identifier }));
            }
            throw new SAXNotSupportedException(SAXMessageFormatter.formatMessage(Locale.getDefault(), "property-not-supported", new Object[] { identifier }));
        }
    }
    
    static class XMLGrammarPoolWrapper implements XMLGrammarPool
    {
        private XMLGrammarPool fGrammarPool;
        
        public Grammar[] retrieveInitialGrammarSet(final String s) {
            return this.fGrammarPool.retrieveInitialGrammarSet(s);
        }
        
        public void cacheGrammars(final String s, final Grammar[] array) {
            this.fGrammarPool.cacheGrammars(s, array);
        }
        
        public Grammar retrieveGrammar(final XMLGrammarDescription xmlGrammarDescription) {
            return this.fGrammarPool.retrieveGrammar(xmlGrammarDescription);
        }
        
        public void lockPool() {
            this.fGrammarPool.lockPool();
        }
        
        public void unlockPool() {
            this.fGrammarPool.unlockPool();
        }
        
        public void clear() {
            this.fGrammarPool.clear();
        }
        
        void setGrammarPool(final XMLGrammarPool fGrammarPool) {
            this.fGrammarPool = fGrammarPool;
        }
        
        XMLGrammarPool getGrammarPool() {
            return this.fGrammarPool;
        }
    }
}
