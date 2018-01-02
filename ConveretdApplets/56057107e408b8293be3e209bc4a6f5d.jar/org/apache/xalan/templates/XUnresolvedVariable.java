// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import javax.xml.transform.TransformerException;
import org.apache.xpath.VariableStack;
import org.apache.xpath.XPathContext;
import org.apache.xalan.transformer.TransformerImpl;
import org.w3c.dom.Node;
import org.apache.xpath.objects.XObject;

public class XUnresolvedVariable extends XObject
{
    private transient Node m_context;
    private transient TransformerImpl m_transformer;
    private transient int m_varStackPos;
    private transient int m_varStackContext;
    private boolean m_isGlobal;
    private transient boolean m_doneEval;
    
    public XUnresolvedVariable(final ElemVariable obj, final Node sourceNode, final TransformerImpl transformer, final int varStackPos, final int varStackContext, final boolean isGlobal) {
        super(obj);
        this.m_varStackPos = -1;
        this.m_doneEval = true;
        this.m_context = sourceNode;
        this.m_transformer = transformer;
        this.m_varStackPos = varStackPos;
        this.m_varStackContext = varStackContext;
        this.m_isGlobal = isGlobal;
    }
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        if (!this.m_doneEval) {
            this.m_transformer.getMsgMgr().error(xctxt.getSAXLocator(), 107, new Object[] { ((ElemVariable)this.object()).getName().getLocalName() });
        }
        final VariableStack vars = xctxt.getVarStack();
        final int savedStart = vars.getSearchStart();
        vars.setSearchStart(this.m_varStackPos);
        vars.pushContextPosition(this.m_varStackContext);
        this.m_doneEval = false;
        final ElemVariable velem = (ElemVariable)super.m_obj;
        final XObject var = velem.getValue(this.m_transformer, this.m_context);
        vars.setSearchStart(savedStart);
        vars.popContextPosition();
        this.m_doneEval = true;
        return var;
    }
    
    public int getType() {
        return 600;
    }
    
    public String getTypeString() {
        return "XUnresolvedVariable (" + this.object().getClass().getName() + ")";
    }
    
    public void setVarStackContext(final int bottom) {
        this.m_varStackContext = bottom;
    }
    
    public void setVarStackPos(final int top) {
        this.m_varStackPos = top;
    }
}
