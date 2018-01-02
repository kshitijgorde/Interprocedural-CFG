// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.operations;

import org.apache.xpath.XPathVisitor;
import org.apache.xpath.ExpressionOwner;
import org.apache.xpath.XPath;
import org.apache.xpath.ExpressionNode;
import org.apache.xalan.templates.Stylesheet;
import org.apache.xalan.templates.ElemTemplateElement;
import org.apache.xalan.templates.ElemVariable;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xpath.objects.XNodeSet;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;
import org.apache.xml.utils.WrappedRuntimeException;
import javax.xml.transform.SourceLocator;
import javax.xml.transform.TransformerException;
import org.apache.xpath.res.XPATHMessages;
import java.util.Vector;
import org.apache.xml.utils.QName;
import org.apache.xpath.axes.PathComponent;
import org.apache.xpath.Expression;

public class Variable extends Expression implements PathComponent
{
    private boolean m_fixUpWasCalled;
    protected QName m_qname;
    protected int m_index;
    protected boolean m_isGlobal;
    static final String PSUEDOVARNAMESPACE = "http://xml.apache.org/xalan/psuedovar";
    
    public Variable() {
        this.m_fixUpWasCalled = false;
        this.m_isGlobal = false;
    }
    
    public void setIndex(final int index) {
        this.m_index = index;
    }
    
    public int getIndex() {
        return this.m_index;
    }
    
    public void setIsGlobal(final boolean isGlobal) {
        this.m_isGlobal = isGlobal;
    }
    
    public boolean getGlobal() {
        return this.m_isGlobal;
    }
    
    public void fixupVariables(final Vector vars, final int globalsSize) {
        this.m_fixUpWasCalled = true;
        final int sz = vars.size();
        for (int i = vars.size() - 1; i >= 0; --i) {
            final QName qn = vars.elementAt(i);
            if (qn.equals(this.m_qname)) {
                if (i < globalsSize) {
                    this.m_isGlobal = true;
                    this.m_index = i;
                }
                else {
                    this.m_index = i - globalsSize;
                }
                return;
            }
        }
        final String msg = XPATHMessages.createXPATHMessage("ER_COULD_NOT_FIND_VAR", new Object[] { this.m_qname.toString() });
        final TransformerException te = new TransformerException(msg, this);
        throw new WrappedRuntimeException(te);
    }
    
    public void setQName(final QName qname) {
        this.m_qname = qname;
    }
    
    public QName getQName() {
        return this.m_qname;
    }
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        return this.execute(xctxt, false);
    }
    
    public XObject execute(final XPathContext xctxt, final boolean destructiveOK) throws TransformerException {
        final PrefixResolver xprefixResolver = xctxt.getNamespaceContext();
        if (this.m_fixUpWasCalled) {
            XObject result;
            if (this.m_isGlobal) {
                result = xctxt.getVarStack().getGlobalVariable(xctxt, this.m_index, destructiveOK);
            }
            else {
                result = xctxt.getVarStack().getLocalVariable(xctxt, this.m_index, destructiveOK);
            }
            if (null == result) {
                this.warn(xctxt, "WG_ILLEGAL_VARIABLE_REFERENCE", new Object[] { this.m_qname.getLocalPart() });
                result = new XNodeSet(xctxt.getDTMManager());
            }
            return result;
        }
        synchronized (this) {
            final ElemVariable vvar = this.getElemVariable();
            if (null != vvar) {
                this.m_index = vvar.getIndex();
                this.m_isGlobal = vvar.getIsTopLevel();
                this.m_fixUpWasCalled = true;
                return this.execute(xctxt);
            }
        }
        throw new TransformerException(XPATHMessages.createXPATHMessage("ER_VAR_NOT_RESOLVABLE", new Object[] { this.m_qname.toString() }));
    }
    
    public ElemVariable getElemVariable() {
        final ExpressionNode owner = this.getExpressionOwner();
        if (null != owner && owner instanceof ElemTemplateElement) {
            ElemTemplateElement prev = (ElemTemplateElement)owner;
            if (!(prev instanceof Stylesheet)) {
                while (!(prev.getParentNode() instanceof Stylesheet)) {
                    final ElemTemplateElement savedprev = prev;
                    while (null != (prev = prev.getPreviousSiblingElem())) {
                        if (prev instanceof ElemVariable) {
                            final ElemVariable vvar = (ElemVariable)prev;
                            if (vvar.getName().equals(this.m_qname)) {
                                return vvar;
                            }
                            continue;
                        }
                    }
                    prev = savedprev.getParentElem();
                }
            }
            final ElemVariable vvar = prev.getStylesheetRoot().getVariableOrParamComposed(this.m_qname);
            if (null != vvar) {
                return vvar;
            }
        }
        return null;
    }
    
    public boolean isStableNumber() {
        return true;
    }
    
    public int getAnalysisBits() {
        final ElemVariable vvar = this.getElemVariable();
        if (null != vvar) {
            final XPath xpath = vvar.getSelect();
            if (null != xpath) {
                final Expression expr = xpath.getExpression();
                if (null != expr && expr instanceof PathComponent) {
                    return ((PathComponent)expr).getAnalysisBits();
                }
            }
        }
        return 67108864;
    }
    
    public void callVisitors(final ExpressionOwner owner, final XPathVisitor visitor) {
        visitor.visitVariableRef(owner, this);
    }
    
    public boolean deepEquals(final Expression expr) {
        return this.isSameClass(expr) && this.m_qname.equals(((Variable)expr).m_qname) && this.getElemVariable() == ((Variable)expr).getElemVariable();
    }
    
    public boolean isPsuedoVarRef() {
        final String ns = this.m_qname.getNamespaceURI();
        return null != ns && ns.equals("http://xml.apache.org/xalan/psuedovar") && this.m_qname.getLocalName().startsWith("#");
    }
}
