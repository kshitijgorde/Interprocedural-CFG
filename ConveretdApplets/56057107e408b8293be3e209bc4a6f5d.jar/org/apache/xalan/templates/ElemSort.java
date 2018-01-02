// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.apache.xpath.XPath;

public class ElemSort extends ElemTemplateElement
{
    private XPath m_selectExpression;
    private AVT m_lang_avt;
    private AVT m_dataType_avt;
    private AVT m_order_avt;
    private AVT m_caseorder_avt;
    
    public ElemSort() {
        this.m_selectExpression = null;
        this.m_lang_avt = null;
        this.m_dataType_avt = null;
        this.m_order_avt = null;
        this.m_caseorder_avt = null;
    }
    
    public Node appendChild(final Node newChild) throws DOMException {
        this.error(4, new Object[] { newChild.getNodeName(), this.getNodeName() });
        return null;
    }
    
    public AVT getCaseOrder() {
        return this.m_caseorder_avt;
    }
    
    public AVT getDataType() {
        return this.m_dataType_avt;
    }
    
    public AVT getLang() {
        return this.m_lang_avt;
    }
    
    public String getNodeName() {
        return "sort";
    }
    
    public AVT getOrder() {
        return this.m_order_avt;
    }
    
    public XPath getSelect() {
        return this.m_selectExpression;
    }
    
    public int getXSLToken() {
        return 64;
    }
    
    public void setCaseOrder(final AVT v) {
        this.m_caseorder_avt = v;
    }
    
    public void setDataType(final AVT v) {
        this.m_dataType_avt = v;
    }
    
    public void setLang(final AVT v) {
        this.m_lang_avt = v;
    }
    
    public void setOrder(final AVT v) {
        this.m_order_avt = v;
    }
    
    public void setSelect(final XPath v) {
        if (v.getPatternString().indexOf("{") < 0) {
            this.m_selectExpression = v;
        }
        else {
            this.error(1, null);
        }
    }
}
