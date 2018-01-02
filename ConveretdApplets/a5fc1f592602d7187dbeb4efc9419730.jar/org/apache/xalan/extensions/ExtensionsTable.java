// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.extensions;

import org.apache.xpath.functions.FuncExtFunction;
import org.apache.xpath.XPathProcessorException;
import org.apache.xalan.res.XSLMessages;
import javax.xml.transform.TransformerException;
import java.util.Vector;
import org.apache.xalan.templates.StylesheetRoot;
import java.util.Hashtable;

public class ExtensionsTable
{
    public Hashtable m_extensionFunctionNamespaces;
    private StylesheetRoot m_sroot;
    
    public ExtensionsTable(final StylesheetRoot sroot) throws TransformerException {
        this.m_extensionFunctionNamespaces = new Hashtable();
        this.m_sroot = sroot;
        final Vector extensions = this.m_sroot.getExtensions();
        for (int i = 0; i < extensions.size(); ++i) {
            final ExtensionNamespaceSupport extNamespaceSpt = extensions.elementAt(i);
            final ExtensionHandler extHandler = extNamespaceSpt.launch();
            if (extHandler != null) {
                this.addExtensionNamespace(extNamespaceSpt.getNamespace(), extHandler);
            }
        }
    }
    
    public ExtensionHandler get(final String extns) {
        return this.m_extensionFunctionNamespaces.get(extns);
    }
    
    public void addExtensionNamespace(final String uri, final ExtensionHandler extNS) {
        this.m_extensionFunctionNamespaces.put(uri, extNS);
    }
    
    public boolean functionAvailable(final String ns, final String funcName) throws TransformerException {
        boolean isAvailable = false;
        if (null != ns) {
            final ExtensionHandler extNS = this.m_extensionFunctionNamespaces.get(ns);
            if (extNS != null) {
                isAvailable = extNS.isFunctionAvailable(funcName);
            }
        }
        return isAvailable;
    }
    
    public boolean elementAvailable(final String ns, final String elemName) throws TransformerException {
        boolean isAvailable = false;
        if (null != ns) {
            final ExtensionHandler extNS = this.m_extensionFunctionNamespaces.get(ns);
            if (extNS != null) {
                isAvailable = extNS.isElementAvailable(elemName);
            }
        }
        return isAvailable;
    }
    
    public Object extFunction(final String ns, final String funcName, final Vector argVec, final Object methodKey, final ExpressionContext exprContext) throws TransformerException {
        Object result = null;
        if (null != ns) {
            final ExtensionHandler extNS = this.m_extensionFunctionNamespaces.get(ns);
            if (null == extNS) {
                throw new XPathProcessorException(XSLMessages.createMessage("ER_EXTENSION_FUNC_UNKNOWN", new Object[] { ns, funcName }));
            }
            try {
                result = extNS.callFunction(funcName, argVec, methodKey, exprContext);
            }
            catch (TransformerException e) {
                throw e;
            }
            catch (Exception e2) {
                throw new TransformerException(e2);
            }
        }
        return result;
    }
    
    public Object extFunction(final FuncExtFunction extFunction, final Vector argVec, final ExpressionContext exprContext) throws TransformerException {
        Object result = null;
        final String ns = extFunction.getNamespace();
        if (null != ns) {
            final ExtensionHandler extNS = this.m_extensionFunctionNamespaces.get(ns);
            if (null == extNS) {
                throw new XPathProcessorException(XSLMessages.createMessage("ER_EXTENSION_FUNC_UNKNOWN", new Object[] { ns, extFunction.getFunctionName() }));
            }
            try {
                result = extNS.callFunction(extFunction, argVec, exprContext);
            }
            catch (TransformerException e) {
                throw e;
            }
            catch (Exception e2) {
                throw new TransformerException(e2);
            }
        }
        return result;
    }
}
