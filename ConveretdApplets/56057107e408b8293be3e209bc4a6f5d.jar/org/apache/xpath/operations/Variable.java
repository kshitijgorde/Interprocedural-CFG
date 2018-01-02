// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.operations;

import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XNodeSet;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;
import org.apache.xml.utils.QName;
import org.apache.xpath.Expression;

public class Variable extends Expression
{
    protected QName m_qname;
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        XObject result = xctxt.getVariable(this.m_qname);
        if (result == null) {
            this.warn(xctxt, 11, new Object[] { this.m_qname.getLocalPart() });
            result = new XNodeSet();
        }
        return result;
    }
    
    public void setQName(final QName qname) {
        this.m_qname = qname;
    }
}
