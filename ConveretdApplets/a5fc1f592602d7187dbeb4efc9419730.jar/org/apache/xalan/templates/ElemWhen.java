// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xpath.XPathVisitor;
import org.apache.xpath.ExpressionOwner;
import javax.xml.transform.TransformerException;
import java.util.Vector;
import org.apache.xpath.XPath;

public class ElemWhen extends ElemTemplateElement
{
    private XPath m_test;
    
    public void setTest(final XPath v) {
        this.m_test = v;
    }
    
    public XPath getTest() {
        return this.m_test;
    }
    
    public int getXSLToken() {
        return 38;
    }
    
    public void compose(final StylesheetRoot sroot) throws TransformerException {
        super.compose(sroot);
        final Vector vnames = sroot.getComposeState().getVariableNames();
        if (null != this.m_test) {
            this.m_test.fixupVariables(vnames, sroot.getComposeState().getGlobalsSize());
        }
    }
    
    public String getNodeName() {
        return "when";
    }
    
    protected void callChildVisitors(final XSLTVisitor visitor, final boolean callAttrs) {
        if (callAttrs) {
            this.m_test.getExpression().callVisitors(this.m_test, visitor);
        }
        super.callChildVisitors(visitor, callAttrs);
    }
}
