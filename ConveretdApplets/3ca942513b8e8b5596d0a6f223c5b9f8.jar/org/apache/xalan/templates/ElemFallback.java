// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import javax.xml.transform.TransformerException;
import org.apache.xalan.transformer.TransformerImpl;

public class ElemFallback extends ElemTemplateElement
{
    static final long serialVersionUID = 1782962139867340703L;
    
    public int getXSLToken() {
        return 57;
    }
    
    public String getNodeName() {
        return "fallback";
    }
    
    public void execute(final TransformerImpl transformer) throws TransformerException {
    }
    
    public void executeFallback(final TransformerImpl transformer) throws TransformerException {
        final int parentElemType = super.m_parentNode.getXSLToken();
        if (79 == parentElemType || -1 == parentElemType) {
            if (transformer.getDebug()) {
                transformer.getTraceManager().fireTraceEvent(this);
            }
            transformer.executeChildTemplates(this, true);
            if (transformer.getDebug()) {
                transformer.getTraceManager().fireTraceEndEvent(this);
            }
        }
        else {
            System.out.println("Error!  parent of xsl:fallback must be an extension or unknown element!");
        }
    }
}
