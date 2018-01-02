// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.jaxp.validation;

import org.apache.xerces.xs.AttributePSVI;
import org.apache.xerces.xs.ElementPSVI;
import org.xml.sax.SAXNotSupportedException;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.xml.sax.SAXNotRecognizedException;
import org.apache.xerces.util.SAXMessageFormatter;
import java.io.IOException;
import org.xml.sax.SAXException;
import java.util.Locale;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.apache.xerces.xs.PSVIProvider;
import javax.xml.validation.Validator;

final class XMLSchemaValidator extends Validator implements PSVIProvider
{
    private XMLSchemaValidatorComponentManager fComponentManager;
    private XMLSchemaValidatorHandler fSAXValidatorHelper;
    private DOMValidatorHelper fDOMValidatorHelper;
    private StreamValidatorHelper fStreamValidatorHelper;
    private boolean fConfigurationChanged;
    private boolean fErrorHandlerChanged;
    private boolean fResourceResolverChanged;
    
    public XMLSchemaValidator(final XSGrammarPoolContainer xsGrammarPoolContainer) {
        this.fConfigurationChanged = false;
        this.fErrorHandlerChanged = false;
        this.fResourceResolverChanged = false;
        this.fComponentManager = new XMLSchemaValidatorComponentManager(xsGrammarPoolContainer);
        this.setErrorHandler(null);
        this.setResourceResolver(null);
    }
    
    public void reset() {
        if (this.fConfigurationChanged) {
            this.fComponentManager.restoreInitialState();
            this.setErrorHandler(null);
            this.setResourceResolver(null);
            this.fConfigurationChanged = false;
            this.fErrorHandlerChanged = false;
            this.fResourceResolverChanged = false;
        }
        else {
            if (this.fErrorHandlerChanged) {
                this.setErrorHandler(null);
                this.fErrorHandlerChanged = false;
            }
            if (this.fResourceResolverChanged) {
                this.setResourceResolver(null);
                this.fResourceResolverChanged = false;
            }
        }
    }
    
    public void validate(final Source source, final Result result) throws SAXException, IOException {
        if (source instanceof SAXSource) {
            if (this.fSAXValidatorHelper == null) {
                this.fSAXValidatorHelper = new XMLSchemaValidatorHandler(this.fComponentManager);
            }
            this.fSAXValidatorHelper.validate(source, result);
        }
        else if (source instanceof DOMSource) {
            if (this.fDOMValidatorHelper == null) {
                this.fDOMValidatorHelper = new DOMValidatorHelper(this.fComponentManager);
            }
            this.fDOMValidatorHelper.validate(source, result);
        }
        else if (source instanceof StreamSource) {
            if (this.fStreamValidatorHelper == null) {
                this.fStreamValidatorHelper = new StreamValidatorHelper(this.fComponentManager);
            }
            this.fStreamValidatorHelper.validate(source, result);
        }
        else {
            if (source == null) {
                throw new NullPointerException(JAXPValidationMessageFormatter.formatMessage(Locale.getDefault(), "SourceParameterNull", null));
            }
            throw new IllegalArgumentException(JAXPValidationMessageFormatter.formatMessage(Locale.getDefault(), "SourceNotAccepted", new Object[] { source.getClass().getName() }));
        }
    }
    
    public void setErrorHandler(final ErrorHandler errorHandler) {
        this.fErrorHandlerChanged = (errorHandler != null);
        this.fComponentManager.setErrorHandler(errorHandler);
    }
    
    public ErrorHandler getErrorHandler() {
        return this.fComponentManager.getErrorHandler();
    }
    
    public void setResourceResolver(final LSResourceResolver resourceResolver) {
        this.fResourceResolverChanged = (resourceResolver != null);
        this.fComponentManager.setResourceResolver(resourceResolver);
    }
    
    public LSResourceResolver getResourceResolver() {
        return this.fComponentManager.getResourceResolver();
    }
    
    public boolean getFeature(final String s) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (s == null) {
            throw new NullPointerException();
        }
        try {
            return this.fComponentManager.getFeature(s);
        }
        catch (XMLConfigurationException ex) {
            throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(Locale.getDefault(), (ex.getType() == 0) ? "feature-not-recognized" : "feature-not-supported", new Object[] { ex.getIdentifier() }));
        }
    }
    
    public void setFeature(final String s, final boolean b) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (s == null) {
            throw new NullPointerException();
        }
        try {
            this.fComponentManager.setFeature(s, b);
        }
        catch (XMLConfigurationException ex) {
            throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(Locale.getDefault(), (ex.getType() == 0) ? "feature-not-recognized" : "feature-not-supported", new Object[] { ex.getIdentifier() }));
        }
        this.fConfigurationChanged = true;
    }
    
    public Object getProperty(final String s) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (s == null) {
            throw new NullPointerException();
        }
        try {
            return this.fComponentManager.getProperty(s);
        }
        catch (XMLConfigurationException ex) {
            throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(Locale.getDefault(), (ex.getType() == 0) ? "property-not-recognized" : "property-not-supported", new Object[] { ex.getIdentifier() }));
        }
    }
    
    public void setProperty(final String s, final Object o) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (s == null) {
            throw new NullPointerException();
        }
        try {
            this.fComponentManager.setProperty(s, o);
        }
        catch (XMLConfigurationException ex) {
            throw new SAXNotRecognizedException(SAXMessageFormatter.formatMessage(Locale.getDefault(), (ex.getType() == 0) ? "property-not-recognized" : "property-not-supported", new Object[] { ex.getIdentifier() }));
        }
        this.fConfigurationChanged = true;
    }
    
    public ElementPSVI getElementPSVI() {
        return (this.fSAXValidatorHelper != null) ? this.fSAXValidatorHelper.getElementPSVI() : null;
    }
    
    public AttributePSVI getAttributePSVI(final int n) {
        return (this.fSAXValidatorHelper != null) ? this.fSAXValidatorHelper.getAttributePSVI(n) : null;
    }
    
    public AttributePSVI getAttributePSVIByName(final String s, final String s2) {
        return (this.fSAXValidatorHelper != null) ? this.fSAXValidatorHelper.getAttributePSVIByName(s, s2) : null;
    }
}
