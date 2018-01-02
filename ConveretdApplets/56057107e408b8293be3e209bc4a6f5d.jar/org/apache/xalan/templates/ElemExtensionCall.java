// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xml.utils.PrefixResolver;
import org.apache.xalan.extensions.ExtensionHandler;
import org.apache.xalan.extensions.ExtensionsTable;
import org.apache.xpath.XPathContext;
import org.xml.sax.SAXException;
import javax.xml.transform.SourceLocator;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Element;
import org.apache.xml.utils.QName;
import org.w3c.dom.Node;
import org.apache.xalan.transformer.TransformerImpl;

public class ElemExtensionCall extends ElemLiteralResult
{
    String m_extns;
    String m_lang;
    String m_srcURL;
    String m_scriptSrc;
    ElemExtensionDecl m_decl;
    
    public ElemExtensionCall() {
        this.m_decl = null;
    }
    
    public void compose() {
        this.m_extns = this.getNamespace();
        final StylesheetRoot stylesheet = this.getStylesheetRoot();
        this.m_decl = this.getElemExtensionDecl(stylesheet, this.m_extns);
        if (this.m_decl != null) {
            ElemTemplateElement child = this.m_decl.getFirstChildElem();
            while (child != null) {
                if (child.getXSLToken() == 86) {
                    final ElemExtensionScript sdecl = (ElemExtensionScript)child;
                    this.m_lang = sdecl.getLang();
                    this.m_srcURL = sdecl.getSrc();
                    final ElemTemplateElement childOfSDecl = sdecl.getFirstChildElem();
                    if (childOfSDecl != null && childOfSDecl.getXSLToken() == 78) {
                        final ElemTextLiteral tl = (ElemTextLiteral)childOfSDecl;
                        final char[] chars = tl.getChars();
                        this.m_scriptSrc = new String(chars);
                        break;
                    }
                    break;
                }
                else {
                    child = child.getNextSiblingElem();
                }
            }
        }
    }
    
    public void execute(final TransformerImpl transformer, final Node sourceNode, final QName mode) throws TransformerException {
        try {
            transformer.getResultTreeHandler().flushPending();
            final XPathContext liaison = transformer.getXPathContext();
            final ExtensionsTable etable = liaison.getExtensionsTable();
            ExtensionHandler nsh = etable.get(this.m_extns);
            if (nsh == null) {
                nsh = etable.makeJavaNamespace(this.m_extns);
                if (nsh == null) {
                    this.executeFallbacks(transformer, sourceNode, mode);
                    return;
                }
                etable.addExtensionNamespace(this.m_extns, nsh);
            }
            try {
                nsh.processElement(this.getLocalName(), this, transformer, this.getStylesheet(), sourceNode.getOwnerDocument(), sourceNode, mode, this);
            }
            catch (Exception e) {
                final String msg = e.getMessage();
                TransformerException te;
                if (e instanceof TransformerException) {
                    te = (TransformerException)e;
                }
                else if (msg != null) {
                    te = new TransformerException(e);
                }
                else {
                    te = new TransformerException("Unknown error when calling extension!", e);
                }
                if (te.getLocator() == null) {
                    te.setLocator(this);
                }
                if (msg != null) {
                    if (msg.indexOf("fatal") >= 0) {
                        transformer.getErrorListener().fatalError(te);
                    }
                    else if (e instanceof RuntimeException) {
                        transformer.getErrorListener().error(te);
                    }
                    else {
                        transformer.getErrorListener().warning(te);
                    }
                }
                else {
                    transformer.getErrorListener().error(te);
                }
                this.executeFallbacks(transformer, sourceNode, mode);
            }
        }
        catch (SAXException se) {
            transformer.getErrorListener().fatalError(new TransformerException(se));
        }
    }
    
    public void executeFallbacks(final TransformerImpl transformer, final Node sourceNode, final QName mode) throws TransformerException {
        for (ElemTemplateElement child = super.m_firstChild; child != null; child = child.m_nextSibling) {
            if (child.getXSLToken() == 57) {
                try {
                    transformer.pushElemTemplateElement(child);
                    ((ElemFallback)child).executeFallback(transformer, sourceNode, mode);
                }
                finally {
                    transformer.popElemTemplateElement();
                }
            }
        }
    }
    
    public String getAttribute(final String rawName) {
        final AVT avt = this.getLiteralResultAttribute(rawName);
        if (avt != null && avt.getRawName().equals(rawName)) {
            return avt.getSimpleString();
        }
        return null;
    }
    
    public String getAttribute(final String rawName, final Node sourceNode, final TransformerImpl transformer) throws TransformerException {
        final AVT avt = this.getLiteralResultAttribute(rawName);
        if (avt != null && avt.getRawName().equals(rawName)) {
            final XPathContext xctxt = transformer.getXPathContext();
            return avt.evaluate(xctxt, sourceNode, this);
        }
        return null;
    }
    
    private ElemExtensionDecl getElemExtensionDecl(final StylesheetRoot stylesheet, final String namespace) {
        ElemExtensionDecl decl = null;
        for (int n = stylesheet.getGlobalImportCount(), i = 0; i < n; ++i) {
            final Stylesheet imported = stylesheet.getGlobalImport(i);
            for (ElemTemplateElement child = imported.getFirstChildElem(); child != null; child = child.getNextSiblingElem()) {
                if (child.getXSLToken() == 85) {
                    decl = (ElemExtensionDecl)child;
                    final String prefix = decl.getPrefix();
                    final String declNamespace = child.getNamespaceForPrefix(prefix);
                    if (namespace.equals(declNamespace)) {
                        return decl;
                    }
                }
            }
        }
        return decl;
    }
    
    public int getXSLToken() {
        return 79;
    }
}
