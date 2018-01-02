// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom.xpath;

import org.jdom.Document;
import org.jdom.Content;
import org.jdom.Attribute;
import org.jdom.Element;
import org.jaxen.SimpleNamespaceContext;
import org.jaxen.NamespaceContext;
import org.jdom.Namespace;
import org.jaxen.SimpleVariableContext;
import org.jaxen.JaxenException;
import java.util.List;
import org.jdom.JDOMException;
import org.jaxen.jdom.JDOMXPath;

class JaxenXPath extends XPath
{
    private static final String CVS_ID = "@(#) $RCSfile: JaxenXPath.java,v $ $Revision: 1.20 $ $Date: 2007/11/10 05:29:02 $ $Name: jdom_1_1_1 $";
    private transient JDOMXPath xPath;
    private Object currentContext;
    
    public JaxenXPath(final String expr) throws JDOMException {
        this.setXPath(expr);
    }
    
    public List selectNodes(final Object context) throws JDOMException {
        try {
            this.currentContext = context;
            return this.xPath.selectNodes(context);
        }
        catch (JaxenException ex1) {
            throw new JDOMException("XPath error while evaluating \"" + this.xPath.toString() + "\": " + ex1.getMessage(), (Throwable)ex1);
        }
        finally {
            this.currentContext = null;
        }
    }
    
    public Object selectSingleNode(final Object context) throws JDOMException {
        try {
            this.currentContext = context;
            return this.xPath.selectSingleNode(context);
        }
        catch (JaxenException ex1) {
            throw new JDOMException("XPath error while evaluating \"" + this.xPath.toString() + "\": " + ex1.getMessage(), (Throwable)ex1);
        }
        finally {
            this.currentContext = null;
        }
    }
    
    public String valueOf(final Object context) throws JDOMException {
        try {
            this.currentContext = context;
            return this.xPath.stringValueOf(context);
        }
        catch (JaxenException ex1) {
            throw new JDOMException("XPath error while evaluating \"" + this.xPath.toString() + "\": " + ex1.getMessage(), (Throwable)ex1);
        }
        finally {
            this.currentContext = null;
        }
    }
    
    public Number numberValueOf(final Object context) throws JDOMException {
        try {
            this.currentContext = context;
            return this.xPath.numberValueOf(context);
        }
        catch (JaxenException ex1) {
            throw new JDOMException("XPath error while evaluating \"" + this.xPath.toString() + "\": " + ex1.getMessage(), (Throwable)ex1);
        }
        finally {
            this.currentContext = null;
        }
    }
    
    public void setVariable(final String name, final Object value) throws IllegalArgumentException {
        final Object o = this.xPath.getVariableContext();
        if (o instanceof SimpleVariableContext) {
            ((SimpleVariableContext)o).setVariableValue((String)null, name, value);
        }
    }
    
    public void addNamespace(final Namespace namespace) {
        try {
            this.xPath.addNamespace(namespace.getPrefix(), namespace.getURI());
        }
        catch (JaxenException ex) {}
    }
    
    public String getXPath() {
        return this.xPath.toString();
    }
    
    private void setXPath(final String expr) throws JDOMException {
        try {
            (this.xPath = new JDOMXPath(expr)).setNamespaceContext((NamespaceContext)new NSContext());
        }
        catch (Exception ex1) {
            throw new JDOMException("Invalid XPath expression: \"" + expr + "\"", ex1);
        }
    }
    
    public String toString() {
        return this.xPath.toString();
    }
    
    public boolean equals(final Object o) {
        if (o instanceof JaxenXPath) {
            final JaxenXPath x = (JaxenXPath)o;
            return super.equals(o) && this.xPath.toString().equals(x.xPath.toString());
        }
        return false;
    }
    
    public int hashCode() {
        return this.xPath.hashCode();
    }
    
    private class NSContext extends SimpleNamespaceContext
    {
        public String translateNamespacePrefixToUri(final String prefix) {
            if (prefix == null || prefix.length() == 0) {
                return null;
            }
            String uri = super.translateNamespacePrefixToUri(prefix);
            if (uri == null) {
                final Object ctx = JaxenXPath.this.currentContext;
                if (ctx != null) {
                    Element elt = null;
                    if (ctx instanceof Element) {
                        elt = (Element)ctx;
                    }
                    else if (ctx instanceof Attribute) {
                        elt = ((Attribute)ctx).getParent();
                    }
                    else if (ctx instanceof Content) {
                        elt = ((Content)ctx).getParentElement();
                    }
                    else if (ctx instanceof Document) {
                        elt = ((Document)ctx).getRootElement();
                    }
                    if (elt != null) {
                        final Namespace ns = elt.getNamespace(prefix);
                        if (ns != null) {
                            uri = ns.getURI();
                        }
                    }
                }
            }
            return uri;
        }
    }
}
