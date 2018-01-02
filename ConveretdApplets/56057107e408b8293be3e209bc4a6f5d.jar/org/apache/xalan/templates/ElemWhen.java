// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xpath.XPath;

public class ElemWhen extends ElemTemplateElement
{
    private XPath m_test;
    
    public String getNodeName() {
        return "when";
    }
    
    public XPath getTest() {
        return this.m_test;
    }
    
    public int getXSLToken() {
        return 38;
    }
    
    public void setTest(final XPath v) {
        this.m_test = v;
    }
}
