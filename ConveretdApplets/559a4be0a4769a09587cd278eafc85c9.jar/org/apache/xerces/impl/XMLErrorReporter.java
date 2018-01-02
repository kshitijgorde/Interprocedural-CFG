// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl;

import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.parser.XMLComponentManager;
import org.apache.xerces.util.DefaultErrorHandler;
import org.apache.xerces.xni.parser.XMLParseException;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.util.MessageFormatter;
import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.xni.parser.XMLErrorHandler;
import java.util.Hashtable;
import java.util.Locale;
import org.apache.xerces.xni.parser.XMLComponent;

public class XMLErrorReporter implements XMLComponent
{
    public static final short SEVERITY_WARNING = 0;
    public static final short SEVERITY_ERROR = 1;
    public static final short SEVERITY_FATAL_ERROR = 2;
    protected static final String CONTINUE_AFTER_FATAL_ERROR = "http://apache.org/xml/features/continue-after-fatal-error";
    protected static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
    private static final String[] RECOGNIZED_FEATURES;
    private static final Boolean[] FEATURE_DEFAULTS;
    private static final String[] RECOGNIZED_PROPERTIES;
    private static final Object[] PROPERTY_DEFAULTS;
    protected Locale fLocale;
    protected Hashtable fMessageFormatters;
    protected XMLErrorHandler fErrorHandler;
    protected XMLLocator fLocator;
    protected boolean fContinueAfterFatalError;
    protected XMLErrorHandler fDefaultErrorHandler;
    
    public XMLErrorReporter() {
        this.fMessageFormatters = new Hashtable();
    }
    
    public void setLocale(final Locale locale) {
        this.fLocale = locale;
    }
    
    public Locale getLocale() {
        return this.fLocale;
    }
    
    public void setDocumentLocator(final XMLLocator locator) {
        this.fLocator = locator;
    }
    
    public void putMessageFormatter(final String domain, final MessageFormatter messageFormatter) {
        this.fMessageFormatters.put(domain, messageFormatter);
    }
    
    public MessageFormatter getMessageFormatter(final String domain) {
        return this.fMessageFormatters.get(domain);
    }
    
    public MessageFormatter removeMessageFormatter(final String domain) {
        return this.fMessageFormatters.remove(domain);
    }
    
    public void reportError(final String domain, final String key, final Object[] arguments, final short severity) throws XNIException {
        this.reportError(this.fLocator, domain, key, arguments, severity);
    }
    
    public void reportError(final XMLLocator location, final String domain, final String key, final Object[] arguments, final short severity) throws XNIException {
        final MessageFormatter messageFormatter = this.getMessageFormatter(domain);
        String message;
        if (messageFormatter != null) {
            message = messageFormatter.formatMessage(this.fLocale, key, arguments);
        }
        else {
            final StringBuffer str = new StringBuffer();
            str.append(domain);
            str.append('#');
            str.append(key);
            final int argCount = (arguments != null) ? arguments.length : 0;
            if (argCount > 0) {
                str.append('?');
                for (int i = 0; i < argCount; ++i) {
                    str.append(arguments[i]);
                    if (i < argCount - 1) {
                        str.append('&');
                    }
                }
            }
            message = str.toString();
        }
        final XMLParseException parseException = new XMLParseException(location, message);
        XMLErrorHandler errorHandler = this.fErrorHandler;
        if (errorHandler == null) {
            if (this.fDefaultErrorHandler == null) {
                this.fDefaultErrorHandler = new DefaultErrorHandler();
            }
            errorHandler = this.fDefaultErrorHandler;
        }
        switch (severity) {
            case 0: {
                errorHandler.warning(domain, key, parseException);
                break;
            }
            case 1: {
                errorHandler.error(domain, key, parseException);
                break;
            }
            case 2: {
                errorHandler.fatalError(domain, key, parseException);
                if (!this.fContinueAfterFatalError) {
                    throw parseException;
                }
                break;
            }
        }
    }
    
    public void reset(final XMLComponentManager componentManager) throws XNIException {
        try {
            this.fContinueAfterFatalError = componentManager.getFeature("http://apache.org/xml/features/continue-after-fatal-error");
        }
        catch (XNIException e) {
            this.fContinueAfterFatalError = false;
        }
        this.fErrorHandler = (XMLErrorHandler)componentManager.getProperty("http://apache.org/xml/properties/internal/error-handler");
    }
    
    public String[] getRecognizedFeatures() {
        return XMLErrorReporter.RECOGNIZED_FEATURES.clone();
    }
    
    public void setFeature(final String featureId, final boolean state) throws XMLConfigurationException {
        if (featureId.startsWith("http://apache.org/xml/features/")) {
            final String feature = featureId.substring("http://apache.org/xml/features/".length());
            if (feature.equals("continue-after-fatal-error")) {
                this.fContinueAfterFatalError = state;
            }
        }
    }
    
    public boolean getFeature(final String featureId) throws XMLConfigurationException {
        if (featureId.startsWith("http://apache.org/xml/features/")) {
            final String feature = featureId.substring("http://apache.org/xml/features/".length());
            if (feature.equals("continue-after-fatal-error")) {
                return this.fContinueAfterFatalError;
            }
        }
        return false;
    }
    
    public String[] getRecognizedProperties() {
        return XMLErrorReporter.RECOGNIZED_PROPERTIES.clone();
    }
    
    public void setProperty(final String propertyId, final Object value) throws XMLConfigurationException {
        if (propertyId.startsWith("http://apache.org/xml/properties/")) {
            final String property = propertyId.substring("http://apache.org/xml/properties/".length());
            if (property.equals("internal/error-handler")) {
                this.fErrorHandler = (XMLErrorHandler)value;
            }
        }
    }
    
    public Boolean getFeatureDefault(final String featureId) {
        for (int i = 0; i < XMLErrorReporter.RECOGNIZED_FEATURES.length; ++i) {
            if (XMLErrorReporter.RECOGNIZED_FEATURES[i].equals(featureId)) {
                return XMLErrorReporter.FEATURE_DEFAULTS[i];
            }
        }
        return null;
    }
    
    public Object getPropertyDefault(final String propertyId) {
        for (int i = 0; i < XMLErrorReporter.RECOGNIZED_PROPERTIES.length; ++i) {
            if (XMLErrorReporter.RECOGNIZED_PROPERTIES[i].equals(propertyId)) {
                return XMLErrorReporter.PROPERTY_DEFAULTS[i];
            }
        }
        return null;
    }
    
    public XMLErrorHandler getErrorHandler() {
        return this.fErrorHandler;
    }
    
    static {
        RECOGNIZED_FEATURES = new String[] { "http://apache.org/xml/features/continue-after-fatal-error" };
        FEATURE_DEFAULTS = new Boolean[] { null };
        RECOGNIZED_PROPERTIES = new String[] { "http://apache.org/xml/properties/internal/error-handler" };
        PROPERTY_DEFAULTS = new Object[] { null };
    }
}
