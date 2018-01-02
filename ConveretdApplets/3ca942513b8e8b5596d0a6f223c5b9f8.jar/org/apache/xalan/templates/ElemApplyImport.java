// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import javax.xml.transform.TransformerException;
import javax.xml.transform.SourceLocator;
import org.apache.xalan.transformer.TransformerImpl;

public class ElemApplyImport extends ElemTemplateElement
{
    static final long serialVersionUID = 3764728663373024038L;
    
    public int getXSLToken() {
        return 72;
    }
    
    public String getNodeName() {
        return "apply-imports";
    }
    
    public void execute(final TransformerImpl transformer) throws TransformerException {
        if (transformer.currentTemplateRuleIsNull()) {
            transformer.getMsgMgr().error(this, "ER_NO_APPLY_IMPORT_IN_FOR_EACH");
        }
        if (transformer.getDebug()) {
            transformer.getTraceManager().fireTraceEvent(this);
        }
        final int sourceNode = transformer.getXPathContext().getCurrentNode();
        if (-1 != sourceNode) {
            final ElemTemplate matchTemplate = transformer.getMatchedTemplate();
            transformer.applyTemplateToNode(this, matchTemplate, sourceNode);
        }
        else {
            transformer.getMsgMgr().error(this, "ER_NULL_SOURCENODE_APPLYIMPORTS");
        }
        if (transformer.getDebug()) {
            transformer.getTraceManager().fireTraceEndEvent(this);
        }
    }
    
    public ElemTemplateElement appendChild(final ElemTemplateElement newChild) {
        this.error("ER_CANNOT_ADD", new Object[] { newChild.getNodeName(), this.getNodeName() });
        return null;
    }
}
