// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xpath.Arg;
import org.apache.xpath.VariableStack;
import org.apache.xml.utils.QName;
import org.w3c.dom.Node;
import org.apache.xalan.transformer.TransformerImpl;
import javax.xml.transform.TransformerException;

public class ElemParam extends ElemVariable
{
    public ElemParam() {
    }
    
    public ElemParam(final ElemParam param) throws TransformerException {
        super(param);
    }
    
    public void execute(final TransformerImpl transformer, final Node sourceNode, final QName mode) throws TransformerException {
        final VariableStack vars = transformer.getXPathContext().getVarStack();
        final Arg arg = vars.getParamArg(this.getName());
        if (arg == null) {
            super.execute(transformer, sourceNode, mode);
        }
        else {
            arg.setIsParamVar(false);
            if (TransformerImpl.S_DEBUG) {
                transformer.getTraceManager().fireTraceEvent(sourceNode, mode, this);
            }
        }
    }
    
    public String getNodeName() {
        return "param";
    }
    
    public int getXSLToken() {
        return 41;
    }
}
