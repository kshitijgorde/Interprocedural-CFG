// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import javax.xml.transform.TransformerException;
import javax.xml.transform.SourceLocator;
import org.apache.xml.utils.QName;
import org.apache.xalan.transformer.TransformerImpl;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

public class ElemApplyImport extends ElemTemplateElement
{
    public Node appendChild(final Node newChild) throws DOMException {
        this.error(4, new Object[] { newChild.getNodeName(), this.getNodeName() });
        return null;
    }
    
    public void execute(final TransformerImpl transformer, final Node sourceNode, final QName mode) throws TransformerException {
        if (transformer.currentTemplateRuleIsNull()) {
            transformer.getMsgMgr().error(this, 95);
        }
        if (TransformerImpl.S_DEBUG) {
            transformer.getTraceManager().fireTraceEvent(sourceNode, mode, this);
        }
        if (sourceNode != null) {
            transformer.applyTemplateToNode(this, null, sourceNode, mode);
        }
        else {
            transformer.getMsgMgr().error(this, 3);
        }
    }
    
    public String getNodeName() {
        return "apply-imports";
    }
    
    public int getXSLToken() {
        return 72;
    }
}
