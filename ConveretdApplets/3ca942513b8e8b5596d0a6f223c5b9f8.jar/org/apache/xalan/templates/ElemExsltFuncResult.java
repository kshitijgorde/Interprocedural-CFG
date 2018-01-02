// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xpath.XPathContext;
import javax.xml.transform.TransformerException;
import org.apache.xalan.transformer.TransformerImpl;
import org.apache.xpath.objects.XObject;

public class ElemExsltFuncResult extends ElemVariable
{
    static final long serialVersionUID = -3478311949388304563L;
    private boolean m_isResultSet;
    private XObject m_result;
    private int m_callerFrameSize;
    
    public ElemExsltFuncResult() {
        this.m_isResultSet = false;
        this.m_result = null;
        this.m_callerFrameSize = 0;
    }
    
    public void execute(final TransformerImpl transformer) throws TransformerException {
        final XPathContext context = transformer.getXPathContext();
        if (transformer.getDebug()) {
            transformer.getTraceManager().fireTraceEvent(this);
        }
        if (transformer.currentFuncResultSeen()) {
            throw new TransformerException("An EXSLT function cannot set more than one result!");
        }
        final int sourceNode = context.getCurrentNode();
        final XObject var = this.getValue(transformer, sourceNode);
        transformer.popCurrentFuncResult();
        transformer.pushCurrentFuncResult(var);
        if (transformer.getDebug()) {
            transformer.getTraceManager().fireTraceEndEvent(this);
        }
    }
    
    public int getXSLToken() {
        return 89;
    }
    
    public String getNodeName() {
        return "result";
    }
}
