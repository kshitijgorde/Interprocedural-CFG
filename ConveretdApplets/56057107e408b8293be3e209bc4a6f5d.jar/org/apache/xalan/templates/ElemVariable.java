// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.w3c.dom.DocumentFragment;
import org.apache.xpath.XPathContext;
import org.apache.xpath.objects.XRTreeFrag;
import org.apache.xpath.objects.XString;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xpath.objects.XObject;
import org.w3c.dom.Node;
import org.apache.xalan.transformer.TransformerImpl;
import javax.xml.transform.TransformerException;
import org.apache.xml.utils.QName;
import org.apache.xpath.XPath;

public class ElemVariable extends ElemTemplateElement
{
    private XPath m_selectPattern;
    private QName m_qname;
    private boolean m_isTopLevel;
    
    public ElemVariable() {
        this.m_isTopLevel = false;
    }
    
    public ElemVariable(final ElemVariable param) throws TransformerException {
        this.m_isTopLevel = false;
        this.m_selectPattern = param.m_selectPattern;
        this.m_qname = param.m_qname;
        this.m_isTopLevel = param.m_isTopLevel;
    }
    
    public void execute(final TransformerImpl transformer, final Node sourceNode, final QName mode) throws TransformerException {
        if (TransformerImpl.S_DEBUG) {
            transformer.getTraceManager().fireTraceEvent(sourceNode, mode, this);
        }
        final XObject var = this.getValue(transformer, sourceNode);
        transformer.getXPathContext().getVarStack().pushVariable(this.m_qname, var);
    }
    
    public boolean getIsTopLevel() {
        return this.m_isTopLevel;
    }
    
    public QName getName() {
        return this.m_qname;
    }
    
    public String getNodeName() {
        return "variable";
    }
    
    public XPath getSelect() {
        return this.m_selectPattern;
    }
    
    public XObject getValue(final TransformerImpl transformer, final Node sourceNode) throws TransformerException {
        XObject var;
        if (this.m_selectPattern != null) {
            final XPathContext xctxt = transformer.getXPathContext();
            var = this.m_selectPattern.execute(xctxt, sourceNode, this);
            if (TransformerImpl.S_DEBUG) {
                transformer.getTraceManager().fireSelectedEvent(sourceNode, this, "select", this.m_selectPattern, var);
            }
        }
        else if (this.getFirstChild() == null) {
            var = XString.EMPTYSTRING;
        }
        else {
            final DocumentFragment df = transformer.transformToRTF(this, sourceNode, null);
            var = new XRTreeFrag(df);
        }
        return var;
    }
    
    public int getXSLToken() {
        return 73;
    }
    
    public void recompose(final StylesheetRoot root) {
        root.recomposeVariables(this);
    }
    
    public void setIsTopLevel(final boolean v) {
        this.m_isTopLevel = v;
    }
    
    public void setName(final QName v) {
        this.m_qname = v;
    }
    
    public void setSelect(final XPath v) {
        this.m_selectPattern = v;
    }
}
