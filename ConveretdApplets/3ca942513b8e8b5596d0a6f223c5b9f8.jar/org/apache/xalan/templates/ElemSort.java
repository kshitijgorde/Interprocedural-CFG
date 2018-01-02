// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import javax.xml.transform.TransformerException;
import java.util.Vector;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.apache.xpath.XPath;

public class ElemSort extends ElemTemplateElement
{
    static final long serialVersionUID = -4991510257335851938L;
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
    
    public void setSelect(final XPath v) {
        if (v.getPatternString().indexOf("{") < 0) {
            this.m_selectExpression = v;
        }
        else {
            this.error("ER_NO_CURLYBRACE", null);
        }
    }
    
    public XPath getSelect() {
        return this.m_selectExpression;
    }
    
    public void setLang(final AVT v) {
        this.m_lang_avt = v;
    }
    
    public AVT getLang() {
        return this.m_lang_avt;
    }
    
    public void setDataType(final AVT v) {
        this.m_dataType_avt = v;
    }
    
    public AVT getDataType() {
        return this.m_dataType_avt;
    }
    
    public void setOrder(final AVT v) {
        this.m_order_avt = v;
    }
    
    public AVT getOrder() {
        return this.m_order_avt;
    }
    
    public void setCaseOrder(final AVT v) {
        this.m_caseorder_avt = v;
    }
    
    public AVT getCaseOrder() {
        return this.m_caseorder_avt;
    }
    
    public int getXSLToken() {
        return 64;
    }
    
    public String getNodeName() {
        return "sort";
    }
    
    public Node appendChild(final Node newChild) throws DOMException {
        this.error("ER_CANNOT_ADD", new Object[] { newChild.getNodeName(), this.getNodeName() });
        return null;
    }
    
    public void compose(final StylesheetRoot sroot) throws TransformerException {
        super.compose(sroot);
        final StylesheetRoot.ComposeState cstate = sroot.getComposeState();
        final Vector vnames = cstate.getVariableNames();
        if (null != this.m_caseorder_avt) {
            this.m_caseorder_avt.fixupVariables(vnames, cstate.getGlobalsSize());
        }
        if (null != this.m_dataType_avt) {
            this.m_dataType_avt.fixupVariables(vnames, cstate.getGlobalsSize());
        }
        if (null != this.m_lang_avt) {
            this.m_lang_avt.fixupVariables(vnames, cstate.getGlobalsSize());
        }
        if (null != this.m_order_avt) {
            this.m_order_avt.fixupVariables(vnames, cstate.getGlobalsSize());
        }
        if (null != this.m_selectExpression) {
            this.m_selectExpression.fixupVariables(vnames, cstate.getGlobalsSize());
        }
    }
}
