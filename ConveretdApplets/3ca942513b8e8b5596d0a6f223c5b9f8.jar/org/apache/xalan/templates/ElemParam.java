// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xpath.objects.XObject;
import org.apache.xpath.VariableStack;
import org.apache.xalan.transformer.TransformerImpl;
import javax.xml.transform.TransformerException;

public class ElemParam extends ElemVariable
{
    static final long serialVersionUID = -1131781475589006431L;
    int m_qnameID;
    
    public ElemParam() {
    }
    
    public int getXSLToken() {
        return 41;
    }
    
    public String getNodeName() {
        return "param";
    }
    
    public ElemParam(final ElemParam param) throws TransformerException {
        super(param);
    }
    
    public void compose(final StylesheetRoot sroot) throws TransformerException {
        super.compose(sroot);
        this.m_qnameID = sroot.getComposeState().getQNameID(super.m_qname);
        final int parentToken = super.m_parentNode.getXSLToken();
        if (parentToken == 19 || parentToken == 88) {
            final ElemTemplate elemTemplate = (ElemTemplate)super.m_parentNode;
            ++elemTemplate.m_inArgsSize;
        }
    }
    
    public void execute(final TransformerImpl transformer) throws TransformerException {
        if (transformer.getDebug()) {
            transformer.getTraceManager().fireTraceEvent(this);
        }
        final VariableStack vars = transformer.getXPathContext().getVarStack();
        if (!vars.isLocalSet(super.m_index)) {
            final int sourceNode = transformer.getXPathContext().getCurrentNode();
            final XObject var = this.getValue(transformer, sourceNode);
            transformer.getXPathContext().getVarStack().setLocalVariable(super.m_index, var);
        }
        if (transformer.getDebug()) {
            transformer.getTraceManager().fireTraceEndEvent(this);
        }
    }
}
