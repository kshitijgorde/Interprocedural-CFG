// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.jaxp;

import org.xml.sax.InputSource;
import javax.xml.namespace.QName;
import javax.xml.xpath.XPathConstants;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import org.apache.xml.utils.PrefixResolver;
import javax.xml.xpath.XPathExpression;
import org.apache.xpath.res.XPATHMessages;
import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPathFunctionResolver;
import javax.xml.xpath.XPathVariableResolver;
import javax.xml.xpath.XPath;

public class XPathImpl implements XPath
{
    private XPathVariableResolver m_origVariableResolver;
    private XPathFunctionResolver m_origFunctionResolver;
    private XPathVariableResolver m_variableResolver;
    private XPathFunctionResolver m_functionResolver;
    private NamespaceContext m_namespaceContext;
    private boolean m_allowNullFcnPrefix;
    private boolean m_isSecureProcessing;
    
    protected XPathImpl(final XPathFunctionResolver functionResolver, final XPathVariableResolver variableResolver, final boolean isSecureProcessing, final boolean allowNullFcnPrefix) {
        this.m_origVariableResolver = null;
        this.m_origFunctionResolver = null;
        this.m_variableResolver = null;
        this.m_functionResolver = null;
        this.m_namespaceContext = null;
        this.m_allowNullFcnPrefix = false;
        this.m_isSecureProcessing = false;
        this.m_functionResolver = functionResolver;
        this.m_origFunctionResolver = functionResolver;
        this.m_variableResolver = variableResolver;
        this.m_origVariableResolver = variableResolver;
        this.m_isSecureProcessing = isSecureProcessing;
        this.m_allowNullFcnPrefix = allowNullFcnPrefix;
    }
    
    public XPathVariableResolver getXPathVariableResolver() {
        return this.m_variableResolver;
    }
    
    public void setXPathVariableResolver(final XPathVariableResolver resolver) {
        if (resolver == null) {
            throw new NullPointerException(XPATHMessages.createXPATHMessage("ER_NULL_XPATH_VARIABLE_RESOLVER", null));
        }
        this.m_variableResolver = resolver;
    }
    
    public XPathFunctionResolver getXPathFunctionResolver() {
        return this.m_functionResolver;
    }
    
    public void setXPathFunctionResolver(final XPathFunctionResolver resolver) {
        if (resolver == null) {
            throw new NullPointerException(XPATHMessages.createXPATHMessage("ER_NULL_XPATH_FUNCTION_RESOLVER", null));
        }
        this.m_functionResolver = resolver;
    }
    
    public NamespaceContext getNamespaceContext() {
        return this.m_namespaceContext;
    }
    
    public void setNamespaceContext(final NamespaceContext nsContext) {
        if (nsContext == null) {
            throw new NullPointerException(XPATHMessages.createXPATHMessage("ER_NULL_NAMESPACE_CONTEXT", null));
        }
        this.m_namespaceContext = nsContext;
    }
    
    public void reset() {
        this.m_variableResolver = this.m_origVariableResolver;
        this.m_functionResolver = this.m_origFunctionResolver;
    }
    
    public XPathExpression compile(final String expression) throws XPathExpressionException {
        if (expression == null) {
            throw new NullPointerException(XPATHMessages.createXPATHMessage("ER_NULL_XPATH_EXPRESSION", null));
        }
        final XPathPrefixResolver prefixResolver = new XPathPrefixResolver(this.m_namespaceContext);
        try {
            final org.apache.xpath.XPath xpath = new org.apache.xpath.XPath(expression, prefixResolver, this.m_functionResolver, this.m_variableResolver, this.m_isSecureProcessing, this.m_allowNullFcnPrefix);
            return new XPathExpressionImpl(xpath, prefixResolver, this.m_isSecureProcessing);
        }
        catch (TransformerException te) {
            final Throwable containedException = te.getException();
            if (containedException instanceof XPathExpressionException) {
                throw (XPathExpressionException)containedException;
            }
            throw new XPathExpressionException(te);
        }
    }
    
    public String evaluate(final String expression, final Object item) throws XPathExpressionException {
        return (String)this.evaluate(expression, item, XPathConstants.STRING);
    }
    
    public Object evaluate(final String expression, final Object item, final QName returnType) throws XPathExpressionException {
        final XPathExpression xpathExpr = this.compile(expression);
        return xpathExpr.evaluate(item, returnType);
    }
    
    public String evaluate(final String expression, final InputSource source) throws XPathExpressionException {
        return (String)this.evaluate(expression, source, XPathConstants.STRING);
    }
    
    public Object evaluate(final String expression, final InputSource source, final QName returnType) throws XPathExpressionException {
        final XPathExpression xpathExpr = this.compile(expression);
        return xpathExpr.evaluate(source, returnType);
    }
}
