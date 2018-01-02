// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import javax.xml.transform.TransformerException;
import org.apache.xalan.res.XSLMessages;
import javax.xml.transform.SourceLocator;
import org.apache.xalan.transformer.TransformerImpl;

public class ElemMessage extends ElemTemplateElement
{
    static final long serialVersionUID = 1530472462155060023L;
    private boolean m_terminate;
    
    public ElemMessage() {
        this.m_terminate = false;
    }
    
    public void setTerminate(final boolean v) {
        this.m_terminate = v;
    }
    
    public boolean getTerminate() {
        return this.m_terminate;
    }
    
    public int getXSLToken() {
        return 75;
    }
    
    public String getNodeName() {
        return "message";
    }
    
    public void execute(final TransformerImpl transformer) throws TransformerException {
        if (transformer.getDebug()) {
            transformer.getTraceManager().fireTraceEvent(this);
        }
        final String data = transformer.transformToString(this);
        transformer.getMsgMgr().message(this, data, this.m_terminate);
        if (this.m_terminate) {
            transformer.getErrorListener().fatalError(new TransformerException(XSLMessages.createMessage("ER_STYLESHEET_DIRECTED_TERMINATION", null)));
        }
        if (transformer.getDebug()) {
            transformer.getTraceManager().fireTraceEndEvent(this);
        }
    }
}
