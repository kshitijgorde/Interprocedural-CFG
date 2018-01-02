// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import javax.xml.transform.TransformerException;
import org.apache.xml.utils.QName;
import org.w3c.dom.Node;
import org.apache.xalan.transformer.TransformerImpl;

public class ElemFallback extends ElemTemplateElement
{
    public void execute(final TransformerImpl transformer, final Node sourceNode, final QName mode) throws TransformerException {
    }
    
    public void executeFallback(final TransformerImpl transformer, final Node sourceNode, final QName mode) throws TransformerException {
        if (super.m_parentNode.getXSLToken() == 79) {
            if (TransformerImpl.S_DEBUG) {
                transformer.getTraceManager().fireTraceEvent(sourceNode, mode, this);
            }
            transformer.executeChildTemplates(this, sourceNode, mode);
        }
        else {
            System.out.println("Error!  parent of xsl:fallback must be an extension element!");
        }
    }
    
    public String getNodeName() {
        return "fallback";
    }
    
    public int getXSLToken() {
        return 57;
    }
}
