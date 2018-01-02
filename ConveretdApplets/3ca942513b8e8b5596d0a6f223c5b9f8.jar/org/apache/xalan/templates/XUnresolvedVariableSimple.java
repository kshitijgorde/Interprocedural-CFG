// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import javax.xml.transform.TransformerException;
import org.apache.xpath.Expression;
import org.apache.xpath.XPathContext;
import org.apache.xpath.objects.XObject;

public class XUnresolvedVariableSimple extends XObject
{
    static final long serialVersionUID = -1224413807443958985L;
    
    public XUnresolvedVariableSimple(final ElemVariable obj) {
        super(obj);
    }
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final Expression expr = ((ElemVariable)super.m_obj).getSelect().getExpression();
        final XObject xobj = expr.execute(xctxt);
        xobj.allowDetachToRelease(false);
        return xobj;
    }
    
    public int getType() {
        return 600;
    }
    
    public String getTypeString() {
        return "XUnresolvedVariableSimple (" + this.object().getClass().getName() + ")";
    }
}
