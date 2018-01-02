// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xpath.XPathContext;
import org.apache.xml.utils.PrefixResolver;
import org.w3c.dom.Node;
import org.apache.xalan.extensions.ExtensionHandler;
import org.apache.xalan.extensions.ExtensionsTable;
import org.xml.sax.SAXException;
import javax.xml.transform.SourceLocator;
import org.apache.xalan.res.XSLMessages;
import org.apache.xalan.transformer.TransformerImpl;
import javax.xml.transform.TransformerException;

public class ElemExtensionCall extends ElemLiteralResult
{
    static final long serialVersionUID = 3171339708500216920L;
    String m_extns;
    String m_lang;
    String m_srcURL;
    String m_scriptSrc;
    ElemExtensionDecl m_decl;
    
    public ElemExtensionCall() {
        this.m_decl = null;
    }
    
    public int getXSLToken() {
        return 79;
    }
    
    public void compose(final StylesheetRoot sroot) throws TransformerException {
        super.compose(sroot);
        this.m_extns = this.getNamespace();
        this.m_decl = this.getElemExtensionDecl(sroot, this.m_extns);
        if (this.m_decl == null) {
            sroot.getExtensionNamespacesManager().registerExtension(this.m_extns);
        }
    }
    
    private ElemExtensionDecl getElemExtensionDecl(final StylesheetRoot stylesheet, final String namespace) {
        ElemExtensionDecl decl = null;
        for (int n = stylesheet.getGlobalImportCount(), i = 0; i < n; ++i) {
            final Stylesheet imported = stylesheet.getGlobalImport(i);
            for (ElemTemplateElement child = imported.getFirstChildElem(); child != null; child = child.getNextSiblingElem()) {
                if (85 == child.getXSLToken()) {
                    decl = (ElemExtensionDecl)child;
                    final String prefix = decl.getPrefix();
                    final String declNamespace = child.getNamespaceForPrefix(prefix);
                    if (namespace.equals(declNamespace)) {
                        return decl;
                    }
                }
            }
        }
        return null;
    }
    
    private void executeFallbacks(final TransformerImpl transformer) throws TransformerException {
        for (ElemTemplateElement child = super.m_firstChild; child != null; child = child.m_nextSibling) {
            if (child.getXSLToken() == 57) {
                try {
                    transformer.pushElemTemplateElement(child);
                    ((ElemFallback)child).executeFallback(transformer);
                }
                finally {
                    transformer.popElemTemplateElement();
                }
            }
        }
    }
    
    private boolean hasFallbackChildren() {
        for (ElemTemplateElement child = super.m_firstChild; child != null; child = child.m_nextSibling) {
            if (child.getXSLToken() == 57) {
                return true;
            }
        }
        return false;
    }
    
    public void execute(final TransformerImpl transformer) throws TransformerException {
        if (transformer.getStylesheet().isSecureProcessing()) {
            throw new TransformerException(XSLMessages.createMessage("ER_EXTENSION_ELEMENT_NOT_ALLOWED_IN_SECURE_PROCESSING", new Object[] { this.getRawName() }));
        }
        if (transformer.getDebug()) {
            transformer.getTraceManager().fireTraceEvent(this);
        }
        try {
            transformer.getResultTreeHandler().flushPending();
            final ExtensionsTable etable = transformer.getExtensionsTable();
            final ExtensionHandler nsh = etable.get(this.m_extns);
            if (null == nsh) {
                if (this.hasFallbackChildren()) {
                    this.executeFallbacks(transformer);
                }
                else {
                    final TransformerException te = new TransformerException(XSLMessages.createMessage("ER_CALL_TO_EXT_FAILED", new Object[] { this.getNodeName() }));
                    transformer.getErrorListener().fatalError(te);
                }
                return;
            }
            try {
                nsh.processElement(this.getLocalName(), this, transformer, this.getStylesheet(), this);
            }
            catch (Exception e) {
                if (this.hasFallbackChildren()) {
                    this.executeFallbacks(transformer);
                }
                else if (e instanceof TransformerException) {
                    final TransformerException te2 = (TransformerException)e;
                    if (null == te2.getLocator()) {
                        te2.setLocator(this);
                    }
                    transformer.getErrorListener().fatalError(te2);
                }
                else if (e instanceof RuntimeException) {
                    transformer.getErrorListener().fatalError(new TransformerException(e));
                }
                else {
                    transformer.getErrorListener().warning(new TransformerException(e));
                }
            }
        }
        catch (TransformerException e2) {
            transformer.getErrorListener().fatalError(e2);
        }
        catch (SAXException se) {
            throw new TransformerException(se);
        }
        if (transformer.getDebug()) {
            transformer.getTraceManager().fireTraceEndEvent(this);
        }
    }
    
    public String getAttribute(final String rawName, final Node sourceNode, final TransformerImpl transformer) throws TransformerException {
        final AVT avt = this.getLiteralResultAttribute(rawName);
        if (null != avt && avt.getRawName().equals(rawName)) {
            final XPathContext xctxt = transformer.getXPathContext();
            return avt.evaluate(xctxt, xctxt.getDTMHandleFromNode(sourceNode), this);
        }
        return null;
    }
    
    protected boolean accept(final XSLTVisitor visitor) {
        return visitor.visitExtensionElement(this);
    }
}
