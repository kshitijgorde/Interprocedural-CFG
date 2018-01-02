// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import javax.xml.transform.TransformerException;
import org.apache.xalan.transformer.TransformerImpl;

public class ElemUnknown extends ElemLiteralResult
{
    public int getXSLToken() {
        return -1;
    }
    
    private void executeFallbacks(final TransformerImpl transformer) throws TransformerException {
        for (ElemTemplateElement child = super.m_firstChild; child != null; child = child.m_nextSibling) {
            if (child.getXSLToken() == 57) {
                try {
                    transformer.pushElemTemplateElement(child);
                    ((ElemFallback)child).executeFallback(transformer);
                }
                finally {
                    transformer.popElemTemplateElement();
                }
            }
        }
    }
    
    private boolean hasFallbackChildren() {
        for (ElemTemplateElement child = super.m_firstChild; child != null; child = child.m_nextSibling) {
            if (child.getXSLToken() == 57) {
                return true;
            }
        }
        return false;
    }
    
    public void execute(final TransformerImpl transformer) throws TransformerException {
        if (TransformerImpl.S_DEBUG) {
            transformer.getTraceManager().fireTraceEvent(this);
        }
        try {
            if (this.hasFallbackChildren()) {
                this.executeFallbacks(transformer);
            }
        }
        catch (TransformerException e) {
            transformer.getErrorListener().fatalError(e);
        }
        if (TransformerImpl.S_DEBUG) {
            transformer.getTraceManager().fireTraceEndEvent(this);
        }
    }
}
