// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xpath.XPathVisitor;
import org.apache.xpath.ExpressionOwner;
import org.apache.xpath.Expression;
import org.apache.xpath.objects.XRTreeFragSelectWrapper;
import java.util.Vector;
import org.apache.xpath.XPathContext;
import org.apache.xpath.ExpressionNode;
import org.apache.xpath.objects.XRTreeFrag;
import org.apache.xpath.objects.XString;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xpath.objects.XObject;
import org.apache.xalan.transformer.TransformerImpl;
import javax.xml.transform.TransformerException;
import org.apache.xml.utils.QName;
import org.apache.xpath.XPath;

public class ElemVariable extends ElemTemplateElement
{
    static final long serialVersionUID = 9111131075322790061L;
    protected int m_index;
    int m_frameSize;
    private XPath m_selectPattern;
    protected QName m_qname;
    private boolean m_isTopLevel;
    
    public ElemVariable() {
        this.m_frameSize = -1;
        this.m_isTopLevel = false;
    }
    
    public void setIndex(final int index) {
        this.m_index = index;
    }
    
    public int getIndex() {
        return this.m_index;
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
    
    public void setIsTopLevel(final boolean v) {
        this.m_isTopLevel = v;
    }
    
    public boolean getIsTopLevel() {
        return this.m_isTopLevel;
    }
    
    public int getXSLToken() {
        return 73;
    }
    
    public String getNodeName() {
        return "variable";
    }
    
    public ElemVariable(final ElemVariable param) throws TransformerException {
        this.m_frameSize = -1;
        this.m_isTopLevel = false;
        this.m_selectPattern = param.m_selectPattern;
        this.m_qname = param.m_qname;
        this.m_isTopLevel = param.m_isTopLevel;
    }
    
    public void execute(final TransformerImpl transformer) throws TransformerException {
        if (transformer.getDebug()) {
            transformer.getTraceManager().fireTraceEvent(this);
        }
        final int sourceNode = transformer.getXPathContext().getCurrentNode();
        final XObject var = this.getValue(transformer, sourceNode);
        transformer.getXPathContext().getVarStack().setLocalVariable(this.m_index, var);
        if (transformer.getDebug()) {
            transformer.getTraceManager().fireTraceEndEvent(this);
        }
    }
    
    public XObject getValue(final TransformerImpl transformer, final int sourceNode) throws TransformerException {
        final XPathContext xctxt = transformer.getXPathContext();
        xctxt.pushCurrentNode(sourceNode);
        XObject var;
        try {
            if (null != this.m_selectPattern) {
                var = this.m_selectPattern.execute(xctxt, sourceNode, this);
                var.allowDetachToRelease(false);
                if (transformer.getDebug()) {
                    transformer.getTraceManager().fireSelectedEvent(sourceNode, this, "select", this.m_selectPattern, var);
                }
            }
            else if (null == this.getFirstChildElem()) {
                var = XString.EMPTYSTRING;
            }
            else {
                int df;
                try {
                    if (super.m_parentNode instanceof Stylesheet) {
                        df = transformer.transformToGlobalRTF(this);
                    }
                    else {
                        df = transformer.transformToRTF(this);
                    }
                }
                finally {}
                var = new XRTreeFrag(df, xctxt, this);
            }
        }
        finally {
            xctxt.popCurrentNode();
        }
        return var;
    }
    
    public void compose(final StylesheetRoot sroot) throws TransformerException {
        if (null == this.m_selectPattern && sroot.getOptimizer()) {
            final XPath newSelect = rewriteChildToExpression(this);
            if (null != newSelect) {
                this.m_selectPattern = newSelect;
            }
        }
        final StylesheetRoot.ComposeState cstate = sroot.getComposeState();
        final Vector vnames = cstate.getVariableNames();
        if (null != this.m_selectPattern) {
            this.m_selectPattern.fixupVariables(vnames, cstate.getGlobalsSize());
        }
        if (!(super.m_parentNode instanceof Stylesheet) && this.m_qname != null) {
            this.m_index = cstate.addVariableName(this.m_qname) - cstate.getGlobalsSize();
        }
        else if (super.m_parentNode instanceof Stylesheet) {
            cstate.resetStackFrameSize();
        }
        super.compose(sroot);
    }
    
    public void endCompose(final StylesheetRoot sroot) throws TransformerException {
        super.endCompose(sroot);
        if (super.m_parentNode instanceof Stylesheet) {
            final StylesheetRoot.ComposeState cstate = sroot.getComposeState();
            this.m_frameSize = cstate.getFrameSize();
            cstate.resetStackFrameSize();
        }
    }
    
    static XPath rewriteChildToExpression(final ElemTemplateElement varElem) throws TransformerException {
        final ElemTemplateElement t = varElem.getFirstChildElem();
        if (null != t && null == t.getNextSiblingElem()) {
            final int etype = t.getXSLToken();
            if (30 == etype) {
                final ElemValueOf valueof = (ElemValueOf)t;
                if (!valueof.getDisableOutputEscaping() && valueof.getDOMBackPointer() == null) {
                    varElem.m_firstChild = null;
                    return new XPath(new XRTreeFragSelectWrapper(valueof.getSelect().getExpression()));
                }
            }
            else if (78 == etype) {
                final ElemTextLiteral lit = (ElemTextLiteral)t;
                if (!lit.getDisableOutputEscaping() && lit.getDOMBackPointer() == null) {
                    final String str = lit.getNodeValue();
                    final XString xstr = new XString(str);
                    varElem.m_firstChild = null;
                    return new XPath(new XRTreeFragSelectWrapper(xstr));
                }
            }
        }
        return null;
    }
    
    public void recompose(final StylesheetRoot root) {
        root.recomposeVariables(this);
    }
    
    public void setParentElem(final ElemTemplateElement p) {
        super.setParentElem(p);
        p.m_hasVariableDecl = true;
    }
    
    protected boolean accept(final XSLTVisitor visitor) {
        return visitor.visitVariableOrParamDecl(this);
    }
    
    protected void callChildVisitors(final XSLTVisitor visitor, final boolean callAttrs) {
        if (null != this.m_selectPattern) {
            this.m_selectPattern.getExpression().callVisitors(this.m_selectPattern, visitor);
        }
        super.callChildVisitors(visitor, callAttrs);
    }
    
    public boolean isPsuedoVar() {
        final String ns = this.m_qname.getNamespaceURI();
        return null != ns && ns.equals("http://xml.apache.org/xalan/psuedovar") && this.m_qname.getLocalName().startsWith("#");
    }
    
    public ElemTemplateElement appendChild(final ElemTemplateElement elem) {
        if (this.m_selectPattern != null) {
            this.error("ER_CANT_HAVE_CONTENT_AND_SELECT", new Object[] { "xsl:" + this.getNodeName() });
            return null;
        }
        return super.appendChild(elem);
    }
}
