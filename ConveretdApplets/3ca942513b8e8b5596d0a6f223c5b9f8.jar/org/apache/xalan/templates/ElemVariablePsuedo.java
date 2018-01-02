// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XObject;
import org.apache.xalan.transformer.TransformerImpl;
import org.apache.xpath.XPath;

public class ElemVariablePsuedo extends ElemVariable
{
    static final long serialVersionUID = 692295692732588486L;
    XUnresolvedVariableSimple m_lazyVar;
    
    public void setSelect(final XPath v) {
        super.setSelect(v);
        this.m_lazyVar = new XUnresolvedVariableSimple(this);
    }
    
    public void execute(final TransformerImpl transformer) throws TransformerException {
        transformer.getXPathContext().getVarStack().setLocalVariable(super.m_index, this.m_lazyVar);
    }
}
