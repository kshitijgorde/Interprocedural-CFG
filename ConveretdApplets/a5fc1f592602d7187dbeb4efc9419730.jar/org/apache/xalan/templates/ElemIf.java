// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xpath.XPathVisitor;
import org.apache.xpath.ExpressionOwner;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xalan.transformer.TransformerImpl;
import javax.xml.transform.TransformerException;
import java.util.Vector;
import org.apache.xpath.XPath;

public class ElemIf extends ElemTemplateElement
{
    private XPath m_test;
    
    public ElemIf() {
        this.m_test = null;
    }
    
    public void setTest(final XPath v) {
        this.m_test = v;
    }
    
    public XPath getTest() {
        return this.m_test;
    }
    
    public void compose(final StylesheetRoot sroot) throws TransformerException {
        super.compose(sroot);
        final Vector vnames = sroot.getComposeState().getVariableNames();
        if (null != this.m_test) {
            this.m_test.fixupVariables(vnames, sroot.getComposeState().getGlobalsSize());
        }
    }
    
    public int getXSLToken() {
        return 36;
    }
    
    public String getNodeName() {
        return "if";
    }
    
    public void execute(final TransformerImpl transformer) throws TransformerException {
        final XPathContext xctxt = transformer.getXPathContext();
        final int sourceNode = xctxt.getCurrentNode();
        if (TransformerImpl.S_DEBUG) {
            final XObject test = this.m_test.execute(xctxt, sourceNode, this);
            if (TransformerImpl.S_DEBUG) {
                transformer.getTraceManager().fireSelectedEvent(sourceNode, this, "test", this.m_test, test);
            }
            if (TransformerImpl.S_DEBUG) {
                transformer.getTraceManager().fireTraceEvent(this);
            }
            if (test.bool()) {
                transformer.executeChildTemplates(this, true);
            }
            if (TransformerImpl.S_DEBUG) {
                transformer.getTraceManager().fireTraceEndEvent(this);
            }
        }
        else if (this.m_test.bool(xctxt, sourceNode, this)) {
            transformer.executeChildTemplates(this, true);
        }
    }
    
    protected void callChildVisitors(final XSLTVisitor visitor, final boolean callAttrs) {
        if (callAttrs) {
            this.m_test.getExpression().callVisitors(this.m_test, visitor);
        }
        super.callChildVisitors(visitor, callAttrs);
    }
}
