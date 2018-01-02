// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xml.utils.QName;
import org.apache.xpath.XPath;

public class ElemWithParam extends ElemTemplateElement
{
    private XPath m_selectPattern;
    private QName m_qname;
    
    public ElemWithParam() {
        this.m_selectPattern = null;
        this.m_qname = null;
    }
    
    public QName getName() {
        return this.m_qname;
    }
    
    public String getNodeName() {
        return "with-param";
    }
    
    public XPath getSelect() {
        return this.m_selectPattern;
    }
    
    public int getXSLToken() {
        return 2;
    }
    
    public void setName(final QName v) {
        this.m_qname = v;
    }
    
    public void setSelect(final XPath v) {
        this.m_selectPattern = v;
    }
}
