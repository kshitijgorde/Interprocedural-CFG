// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import javax.xml.transform.TransformerException;
import javax.xml.transform.SourceLocator;
import org.apache.xml.utils.QName;
import org.w3c.dom.Node;
import org.apache.xalan.transformer.TransformerImpl;

public class ElemMessage extends ElemTemplateElement
{
    private boolean m_terminate;
    
    public ElemMessage() {
        this.m_terminate = false;
    }
    
    public void execute(final TransformerImpl transformer, final Node sourceNode, final QName mode) throws TransformerException {
        if (TransformerImpl.S_DEBUG) {
            transformer.getTraceManager().fireTraceEvent(sourceNode, mode, this);
        }
        final String data = transformer.transformToString(this, sourceNode, mode);
        transformer.getMsgMgr().message(this, data, this.m_terminate);
        if (this.m_terminate) {
            transformer.getErrorListener().fatalError(new TransformerException("Stylesheet directed termination"));
        }
    }
    
    public String getNodeName() {
        return "message";
    }
    
    public boolean getTerminate() {
        return this.m_terminate;
    }
    
    public int getXSLToken() {
        return 75;
    }
    
    public void setTerminate(final boolean v) {
        this.m_terminate = v;
    }
}
