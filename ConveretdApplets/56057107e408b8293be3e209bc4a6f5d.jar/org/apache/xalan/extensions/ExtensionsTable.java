// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.extensions;

import org.apache.xpath.XPathProcessorException;
import java.util.Vector;
import javax.xml.transform.TransformerException;
import java.util.Hashtable;

public class ExtensionsTable
{
    public Hashtable m_extensionFunctionNamespaces;
    
    public ExtensionsTable() {
        this.m_extensionFunctionNamespaces = new Hashtable();
        String uri = "http://xml.apache.org/xslt/java";
        ExtensionHandler fh = new ExtensionHandlerJavaPackage(uri, "xslt-javaclass", "");
        this.addExtensionNamespace(uri, fh);
        uri = "http://xsl.lotus.com/java";
        this.addExtensionNamespace(uri, fh);
        uri = "http://xml.apache.org/xalan";
        fh = new ExtensionHandlerJavaClass(uri, "javaclass", "org.apache.xalan.lib.Extensions");
        this.addExtensionNamespace(uri, fh);
    }
    
    public void addExtensionNamespace(final String uri, final ExtensionHandler extNS) {
        this.m_extensionFunctionNamespaces.put(uri, extNS);
    }
    
    public boolean elementAvailable(final String ns, final String elemName) throws TransformerException {
        boolean isAvailable = false;
        if (ns != null) {
            ExtensionHandler extNS = this.m_extensionFunctionNamespaces.get(ns);
            if (extNS == null) {
                extNS = this.makeJavaNamespace(ns);
                this.addExtensionNamespace(ns, extNS);
            }
            if (extNS != null) {
                isAvailable = extNS.isElementAvailable(elemName);
            }
        }
        return isAvailable;
    }
    
    public Object extFunction(final String ns, final String funcName, final Vector argVec, final Object methodKey, final ExpressionContext exprContext) throws TransformerException {
        Object result = null;
        if (ns != null) {
            ExtensionHandler extNS = this.m_extensionFunctionNamespaces.get(ns);
            if (extNS == null) {
                extNS = this.makeJavaNamespace(ns);
                this.addExtensionNamespace(ns, extNS);
            }
            if (extNS != null) {
                try {
                    result = extNS.callFunction(funcName, argVec, methodKey, exprContext);
                    return result;
                }
                catch (TransformerException e) {
                    throw e;
                }
                catch (Exception e2) {
                    throw new TransformerException(e2);
                }
            }
            throw new XPathProcessorException("Extension function '" + ns + ":" + funcName + "' is unknown");
        }
        return result;
    }
    
    public boolean functionAvailable(final String ns, final String funcName) throws TransformerException {
        boolean isAvailable = false;
        if (ns != null) {
            ExtensionHandler extNS = this.m_extensionFunctionNamespaces.get(ns);
            if (extNS == null) {
                extNS = this.makeJavaNamespace(ns);
                this.addExtensionNamespace(ns, extNS);
            }
            if (extNS != null) {
                isAvailable = extNS.isFunctionAvailable(funcName);
            }
        }
        return isAvailable;
    }
    
    public ExtensionHandler get(final String extns) {
        return this.m_extensionFunctionNamespaces.get(extns);
    }
    
    public ExtensionHandler makeJavaNamespace(final String ns) throws TransformerException {
        if (ns == null || ns.trim().length() == 0) {
            return null;
        }
        String className = ns;
        if (className.startsWith("class:")) {
            className = className.substring(6);
        }
        final int lastSlash = className.lastIndexOf("/");
        if (lastSlash != -1) {
            className = className.substring(lastSlash + 1);
        }
        if (className == null || className.trim().length() == 0) {
            return null;
        }
        try {
            ExtensionHandler.getClassForName(className);
            return new ExtensionHandlerJavaClass(ns, "javaclass", className);
        }
        catch (ClassNotFoundException ex) {
            return new ExtensionHandlerJavaPackage(ns, "javapackage", String.valueOf(className) + ".");
        }
    }
}
