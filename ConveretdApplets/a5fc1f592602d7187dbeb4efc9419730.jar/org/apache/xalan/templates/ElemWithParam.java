// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xpath.XPathVisitor;
import org.apache.xpath.ExpressionOwner;
import org.apache.xpath.XPathContext;
import org.apache.xpath.ExpressionNode;
import org.apache.xpath.objects.XRTreeFrag;
import org.apache.xpath.objects.XString;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xpath.objects.XObject;
import org.apache.xalan.transformer.TransformerImpl;
import javax.xml.transform.TransformerException;
import java.util.Vector;
import org.apache.xalan.processor.TransformerFactoryImpl;
import org.apache.xml.utils.QName;
import org.apache.xpath.XPath;

public class ElemWithParam extends ElemTemplateElement
{
    int m_index;
    private XPath m_selectPattern;
    private QName m_qname;
    int m_qnameID;
    
    public ElemWithParam() {
        this.m_selectPattern = null;
        this.m_qname = null;
    }
    
    public void setSelect(final XPath v) {
        this.m_selectPattern = v;
    }
    
    public XPath getSelect() {
        return this.m_selectPattern;
    }
    
    public void setName(final QName v) {
        this.m_qname = v;
    }
    
    public QName getName() {
        return this.m_qname;
    }
    
    public int getXSLToken() {
        return 2;
    }
    
    public String getNodeName() {
        return "with-param";
    }
    
    public void compose(final StylesheetRoot sroot) throws TransformerException {
        if (null == this.m_selectPattern && TransformerFactoryImpl.m_optimize) {
            final XPath newSelect = ElemVariable.rewriteChildToExpression(this);
            if (null != newSelect) {
                this.m_selectPattern = newSelect;
            }
        }
        this.m_qnameID = sroot.getComposeState().getQNameID(this.m_qname);
        super.compose(sroot);
        final Vector vnames = sroot.getComposeState().getVariableNames();
        if (null != this.m_selectPattern) {
            this.m_selectPattern.fixupVariables(vnames, sroot.getComposeState().getGlobalsSize());
        }
    }
    
    public void setParentElem(final ElemTemplateElement p) {
        super.setParentElem(p);
        p.m_hasVariableDecl = true;
    }
    
    public XObject getValue(final TransformerImpl transformer, final int sourceNode) throws TransformerException {
        final XPathContext xctxt = transformer.getXPathContext();
        xctxt.pushCurrentNode(sourceNode);
        XObject var;
        try {
            if (null != this.m_selectPattern) {
                var = this.m_selectPattern.execute(xctxt, sourceNode, this);
                var.allowDetachToRelease(false);
                if (TransformerImpl.S_DEBUG) {
                    transformer.getTraceManager().fireSelectedEvent(sourceNode, this, "select", this.m_selectPattern, var);
                }
            }
            else if (null == this.getFirstChildElem()) {
                var = XString.EMPTYSTRING;
            }
            else {
                final int df = transformer.transformToRTF(this);
                var = new XRTreeFrag(df, xctxt, this);
            }
        }
        finally {
            xctxt.popCurrentNode();
        }
        return var;
    }
    
    protected void callChildVisitors(final XSLTVisitor visitor, final boolean callAttrs) {
        if (callAttrs && null != this.m_selectPattern) {
            this.m_selectPattern.getExpression().callVisitors(this.m_selectPattern, visitor);
        }
        super.callChildVisitors(visitor, callAttrs);
    }
    
    public ElemTemplateElement appendChild(final ElemTemplateElement elem) {
        if (this.m_selectPattern != null) {
            this.error("ER_CANT_HAVE_CONTENT_AND_SELECT", new Object[] { "xsl:" + this.getNodeName() });
            return null;
        }
        return super.appendChild(elem);
    }
}
