// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xalan.transformer.TransformerImpl;
import org.apache.xalan.extensions.ExtensionNamespacesManager;
import org.apache.xalan.extensions.ExtensionNamespaceSupport;
import javax.xml.transform.TransformerException;
import org.apache.xalan.res.XSLMessages;
import org.apache.xml.utils.StringVector;

public class ElemExtensionDecl extends ElemTemplateElement
{
    static final long serialVersionUID = -4692738885172766789L;
    private String m_prefix;
    private StringVector m_functions;
    private StringVector m_elements;
    
    public ElemExtensionDecl() {
        this.m_prefix = null;
        this.m_functions = new StringVector();
        this.m_elements = null;
    }
    
    public void setPrefix(final String v) {
        this.m_prefix = v;
    }
    
    public String getPrefix() {
        return this.m_prefix;
    }
    
    public void setFunctions(final StringVector v) {
        this.m_functions = v;
    }
    
    public StringVector getFunctions() {
        return this.m_functions;
    }
    
    public String getFunction(final int i) throws ArrayIndexOutOfBoundsException {
        if (null == this.m_functions) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.m_functions.elementAt(i);
    }
    
    public int getFunctionCount() {
        return (null != this.m_functions) ? this.m_functions.size() : 0;
    }
    
    public void setElements(final StringVector v) {
        this.m_elements = v;
    }
    
    public StringVector getElements() {
        return this.m_elements;
    }
    
    public String getElement(final int i) throws ArrayIndexOutOfBoundsException {
        if (null == this.m_elements) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.m_elements.elementAt(i);
    }
    
    public int getElementCount() {
        return (null != this.m_elements) ? this.m_elements.size() : 0;
    }
    
    public int getXSLToken() {
        return 85;
    }
    
    public void compose(final StylesheetRoot sroot) throws TransformerException {
        super.compose(sroot);
        final String prefix = this.getPrefix();
        final String declNamespace = this.getNamespaceForPrefix(prefix);
        String lang = null;
        String srcURL = null;
        String scriptSrc = null;
        if (null == declNamespace) {
            throw new TransformerException(XSLMessages.createMessage("ER_NO_NAMESPACE_DECL", new Object[] { prefix }));
        }
        for (ElemTemplateElement child = this.getFirstChildElem(); child != null; child = child.getNextSiblingElem()) {
            if (86 == child.getXSLToken()) {
                final ElemExtensionScript sdecl = (ElemExtensionScript)child;
                lang = sdecl.getLang();
                srcURL = sdecl.getSrc();
                final ElemTemplateElement childOfSDecl = sdecl.getFirstChildElem();
                if (null != childOfSDecl && 78 == childOfSDecl.getXSLToken()) {
                    final ElemTextLiteral tl = (ElemTextLiteral)childOfSDecl;
                    final char[] chars = tl.getChars();
                    scriptSrc = new String(chars);
                    if (scriptSrc.trim().length() == 0) {
                        scriptSrc = null;
                    }
                }
            }
        }
        if (null == lang) {
            lang = "javaclass";
        }
        if (lang.equals("javaclass") && scriptSrc != null) {
            throw new TransformerException(XSLMessages.createMessage("ER_ELEM_CONTENT_NOT_ALLOWED", new Object[] { scriptSrc }));
        }
        ExtensionNamespaceSupport extNsSpt = null;
        final ExtensionNamespacesManager extNsMgr = sroot.getExtensionNamespacesManager();
        if (extNsMgr.namespaceIndex(declNamespace, extNsMgr.getExtensions()) == -1) {
            if (lang.equals("javaclass")) {
                if (null == srcURL) {
                    extNsSpt = extNsMgr.defineJavaNamespace(declNamespace);
                }
                else if (extNsMgr.namespaceIndex(srcURL, extNsMgr.getExtensions()) == -1) {
                    extNsSpt = extNsMgr.defineJavaNamespace(declNamespace, srcURL);
                }
            }
            else {
                final String handler = "org.apache.xalan.extensions.ExtensionHandlerGeneral";
                final Object[] args = { declNamespace, this.m_elements, this.m_functions, lang, srcURL, scriptSrc, this.getSystemId() };
                extNsSpt = new ExtensionNamespaceSupport(declNamespace, handler, args);
            }
        }
        if (extNsSpt != null) {
            extNsMgr.registerExtension(extNsSpt);
        }
    }
    
    public void runtimeInit(final TransformerImpl transformer) throws TransformerException {
    }
}
