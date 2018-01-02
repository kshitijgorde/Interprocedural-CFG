// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xml.utils.QName;
import org.w3c.dom.Node;
import org.apache.xalan.transformer.TransformerImpl;
import org.apache.xpath.XPath;

public class ElemIf extends ElemTemplateElement
{
    private XPath m_test;
    
    public ElemIf() {
        this.m_test = null;
    }
    
    public void execute(final TransformerImpl transformer, final Node sourceNode, final QName mode) throws TransformerException {
        if (TransformerImpl.S_DEBUG) {
            transformer.getTraceManager().fireTraceEvent(sourceNode, mode, this);
        }
        final XPathContext xctxt = transformer.getXPathContext();
        final XObject test = this.m_test.execute(xctxt, sourceNode, this);
        if (TransformerImpl.S_DEBUG) {
            transformer.getTraceManager().fireSelectedEvent(sourceNode, this, "test", this.m_test, test);
        }
        if (test.bool()) {
            transformer.executeChildTemplates(this, sourceNode, mode);
        }
    }
    
    public String getNodeName() {
        return "if";
    }
    
    public XPath getTest() {
        return this.m_test;
    }
    
    public int getXSLToken() {
        return 36;
    }
    
    public void setTest(final XPath v) {
        this.m_test = v;
    }
}
