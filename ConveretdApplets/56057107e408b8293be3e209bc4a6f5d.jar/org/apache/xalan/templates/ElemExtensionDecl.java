// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xalan.extensions.ExtensionHandler;
import org.apache.xalan.extensions.ExtensionsTable;
import org.apache.xpath.XPathContext;
import org.apache.xalan.extensions.ExtensionHandlerGeneral;
import javax.xml.transform.TransformerException;
import org.apache.xalan.transformer.TransformerImpl;
import org.apache.xml.utils.StringVector;

public class ElemExtensionDecl extends ElemTemplateElement
{
    private String m_prefix;
    private StringVector m_functions;
    private StringVector m_elements;
    
    public ElemExtensionDecl() {
        this.m_prefix = null;
        this.m_functions = new StringVector();
        this.m_elements = null;
    }
    
    public String getElement(final int i) throws ArrayIndexOutOfBoundsException {
        if (this.m_elements == null) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.m_elements.elementAt(i);
    }
    
    public int getElementCount() {
        return (this.m_elements != null) ? this.m_elements.size() : 0;
    }
    
    public StringVector getElements() {
        return this.m_elements;
    }
    
    public String getFunction(final int i) throws ArrayIndexOutOfBoundsException {
        if (this.m_functions == null) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.m_functions.elementAt(i);
    }
    
    public int getFunctionCount() {
        return (this.m_functions != null) ? this.m_functions.size() : 0;
    }
    
    public StringVector getFunctions() {
        return this.m_functions;
    }
    
    public String getPrefix() {
        return this.m_prefix;
    }
    
    public int getXSLToken() {
        return 85;
    }
    
    public void runtimeInit(final TransformerImpl transformer) throws TransformerException {
        String lang = null;
        String srcURL = null;
        String scriptSrc = null;
        final String prefix = this.getPrefix();
        final String declNamespace = this.getNamespaceForPrefix(prefix);
        if (declNamespace == null) {
            throw new TransformerException("Prefix " + prefix + " does not have a corresponding " + "namespace declaration");
        }
        for (ElemTemplateElement child = this.getFirstChildElem(); child != null; child = child.getNextSiblingElem()) {
            if (child.getXSLToken() == 86) {
                final ElemExtensionScript sdecl = (ElemExtensionScript)child;
                lang = sdecl.getLang();
                srcURL = sdecl.getSrc();
                final ElemTemplateElement childOfSDecl = sdecl.getFirstChildElem();
                if (childOfSDecl != null && childOfSDecl.getXSLToken() == 78) {
                    final ElemTextLiteral tl = (ElemTextLiteral)childOfSDecl;
                    final char[] chars = tl.getChars();
                    scriptSrc = new String(chars);
                    if (scriptSrc.trim().length() == 0) {
                        scriptSrc = null;
                    }
                }
            }
        }
        if (lang == null) {
            lang = "javaclass";
        }
        if (lang.equals("javaclass") && scriptSrc != null) {
            throw new TransformerException("Element content not allowed for lang=javaclass " + scriptSrc);
        }
        final XPathContext liaison = transformer.getXPathContext();
        final ExtensionsTable etable = liaison.getExtensionsTable();
        ExtensionHandler nsh = etable.get(declNamespace);
        if (nsh == null) {
            if (lang.equals("javaclass")) {
                if (srcURL == null) {
                    nsh = etable.makeJavaNamespace(declNamespace);
                }
                else {
                    nsh = etable.get(srcURL);
                    if (nsh == null) {
                        nsh = etable.makeJavaNamespace(srcURL);
                    }
                }
            }
            else {
                nsh = new ExtensionHandlerGeneral(declNamespace, this.m_elements, this.m_functions, lang, srcURL, scriptSrc);
            }
            etable.addExtensionNamespace(declNamespace, nsh);
        }
    }
    
    public void setElements(final StringVector v) {
        this.m_elements = v;
    }
    
    public void setFunctions(final StringVector v) {
        this.m_functions = v;
    }
    
    public void setPrefix(final String v) {
        this.m_prefix = v;
    }
}
