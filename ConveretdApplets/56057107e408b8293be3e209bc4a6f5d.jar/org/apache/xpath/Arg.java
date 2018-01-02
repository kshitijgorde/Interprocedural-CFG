// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath;

import org.apache.xpath.objects.XObject;
import org.apache.xml.utils.QName;

public class Arg
{
    private QName m_qname;
    private XObject m_val;
    private String m_expression;
    private boolean m_isParamVar;
    
    public Arg() {
        this.m_qname = new QName("");
        this.m_val = null;
        this.m_expression = null;
        this.m_isParamVar = false;
    }
    
    public Arg(final QName qname, final String expression, final boolean isParamVar) {
        this.m_qname = qname;
        this.m_val = null;
        this.m_expression = expression;
        this.m_isParamVar = isParamVar;
    }
    
    public Arg(final QName qname, final XObject val) {
        this.m_qname = qname;
        this.m_val = val;
        this.m_isParamVar = false;
        this.m_expression = null;
    }
    
    public Arg(final QName qname, final XObject val, final boolean isParamVar) {
        this.m_qname = qname;
        this.m_val = val;
        this.m_isParamVar = isParamVar;
        this.m_expression = null;
    }
    
    public boolean equals(final Object obj) {
        if (obj instanceof QName) {
            return this.m_qname.equals(obj);
        }
        return super.equals(obj);
    }
    
    public String getExpression() {
        return this.m_expression;
    }
    
    public QName getQName() {
        return this.m_qname;
    }
    
    public XObject getVal() {
        return this.m_val;
    }
    
    public boolean isParamVar() {
        return this.m_isParamVar;
    }
    
    public void setExpression(final String expr) {
        this.m_expression = expr;
    }
    
    public void setIsParamVar(final boolean b) {
        this.m_isParamVar = b;
    }
    
    public void setQName(final QName name) {
        this.m_qname = name;
    }
    
    public void setVal(final XObject val) {
        this.m_val = val;
    }
}
