// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;
import javax.xml.transform.TransformerException;
import org.apache.xalan.transformer.TransformerImpl;

public class ElemExsltFuncResult extends ElemVariable
{
    public void execute(final TransformerImpl transformer) throws TransformerException {
        final XPathContext context = transformer.getXPathContext();
        if (TransformerImpl.S_DEBUG) {
            transformer.getTraceManager().fireTraceEvent(this);
        }
        if (transformer.currentFuncResultSeen()) {
            throw new TransformerException("An EXSLT function cannot set more than one result!");
        }
        final int sourceNode = context.getCurrentNode();
        final XObject var = this.getValue(transformer, sourceNode);
        transformer.popCurrentFuncResult();
        transformer.pushCurrentFuncResult(var);
        if (TransformerImpl.S_DEBUG) {
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
