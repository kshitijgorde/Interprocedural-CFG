// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.jaxp;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactoryConfigurationException;
import org.apache.xpath.res.XPATHMessages;
import javax.xml.xpath.XPathFunctionResolver;
import javax.xml.xpath.XPathVariableResolver;
import javax.xml.xpath.XPathFactory;

public class XPathFactoryImpl extends XPathFactory
{
    public static final String FEATURE_NULL_FCN_PREFIX = "null_function_prefix";
    private boolean m_isSecureProcessing;
    private boolean m_allowNullFcnPrefix;
    private XPathVariableResolver m_variableResolver;
    private XPathFunctionResolver m_functionResolver;
    
    public XPathFactoryImpl() {
        this.m_isSecureProcessing = false;
        this.m_allowNullFcnPrefix = false;
        this.m_variableResolver = null;
        this.m_functionResolver = null;
    }
    
    public boolean isObjectModelSupported(final String objectModel) {
        if (objectModel == null) {
            throw new NullPointerException(XPATHMessages.createXPATHMessage("ER_OBJECT_MODEL_NULL", null));
        }
        if (objectModel.length() == 0) {
            throw new IllegalArgumentException(XPATHMessages.createXPATHMessage("ER_OBJECT_MODEL_EMPTY", null));
        }
        return objectModel.equals("http://java.sun.com/jaxp/xpath/dom");
    }
    
    public void setFeature(final String name, final boolean value) throws XPathFactoryConfigurationException {
        if (name == null) {
            throw new NullPointerException(XPATHMessages.createXPATHMessage("ER_SET_FEATURE_NULL_NAME", null));
        }
        if (name.equals("http://javax.xml.XMLConstants/feature/secure-processing")) {
            this.m_isSecureProcessing = value;
        }
        else {
            if (!name.equals("null_function_prefix")) {
                throw new XPathFactoryConfigurationException(XPATHMessages.createXPATHMessage("ER_UNSUPPORTED_FEATURE", new Object[] { name }));
            }
            this.m_allowNullFcnPrefix = value;
        }
    }
    
    public boolean isSecureProcessing() {
        return this.m_isSecureProcessing;
    }
    
    public boolean allowNullFcnPrefix() {
        return this.m_allowNullFcnPrefix;
    }
    
    public boolean getFeature(final String name) throws XPathFactoryConfigurationException {
        if (name == null) {
            throw new NullPointerException(XPATHMessages.createXPATHMessage("ER_GET_FEATURE_NULL_NAME", null));
        }
        if (name.equals("http://javax.xml.XMLConstants/feature/secure-processing")) {
            return this.m_isSecureProcessing;
        }
        if (name.equals("null_function_prefix")) {
            return this.m_allowNullFcnPrefix;
        }
        throw new XPathFactoryConfigurationException(XPATHMessages.createXPATHMessage("ER_UNSUPPORTED_FEATURE", new Object[] { name }));
    }
    
    public void setXPathVariableResolver(final XPathVariableResolver resolver) {
        if (resolver == null) {
            throw new NullPointerException(XPATHMessages.createXPATHMessage("ER_NULL_XPATH_VARIABLE_RESOLVER", null));
        }
        this.m_variableResolver = resolver;
    }
    
    public void setXPathFunctionResolver(final XPathFunctionResolver resolver) {
        if (resolver == null) {
            throw new NullPointerException(XPATHMessages.createXPATHMessage("ER_NULL_XPATH_FUNCTION_RESOLVER", null));
        }
        this.m_functionResolver = resolver;
    }
    
    public XPath newXPath() {
        final XPathImpl xpath = new XPathImpl(this.m_functionResolver, this.m_variableResolver, this.m_isSecureProcessing, this.m_allowNullFcnPrefix);
        return xpath;
    }
}
