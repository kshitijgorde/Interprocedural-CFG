// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xpath.VariableStack;
import org.apache.xpath.XPathContext;
import javax.xml.transform.TransformerException;
import javax.xml.transform.SourceLocator;
import org.w3c.dom.Node;
import org.apache.xalan.transformer.TransformerImpl;
import org.apache.xml.utils.QName;

public class ElemApplyTemplates extends ElemCallTemplate
{
    private QName m_mode;
    private boolean m_isDefaultTemplate;
    
    public ElemApplyTemplates() {
        this.m_mode = null;
        this.m_isDefaultTemplate = false;
    }
    
    public void execute(final TransformerImpl transformer, final Node sourceNode, QName mode) throws TransformerException {
        transformer.pushCurrentTemplateRuleIsNull(false);
        try {
            if (TransformerImpl.S_DEBUG) {
                transformer.getTraceManager().fireTraceEvent(sourceNode, mode, this);
            }
            if (sourceNode != null) {
                if (!this.m_isDefaultTemplate) {
                    mode = this.m_mode;
                }
                this.transformSelectedNodes(transformer, sourceNode, null, mode);
            }
            else {
                transformer.getMsgMgr().error(this, 5);
            }
        }
        finally {
            transformer.popCurrentTemplateRuleIsNull();
        }
    }
    
    public QName getMode() {
        return this.m_mode;
    }
    
    public String getNodeName() {
        return "apply-templates";
    }
    
    public int getXSLToken() {
        return 50;
    }
    
    boolean needToPushParams() {
        return true;
    }
    
    void popParams(final XPathContext xctxt, final int savedSearchStart) {
        final VariableStack vars = xctxt.getVarStack();
        vars.popCurrentContext();
        vars.setSearchStart(savedSearchStart);
    }
    
    int pushParams(final TransformerImpl transformer, final XPathContext xctxt, final Node sourceNode, final QName mode) throws TransformerException {
        final VariableStack vars = xctxt.getVarStack();
        final int savedSearchStart = vars.getSearchStart();
        if (super.m_paramElems != null) {
            transformer.pushParams(xctxt, this, sourceNode, mode);
        }
        else {
            vars.pushContextMarker();
        }
        vars.setSearchStart(-1);
        return savedSearchStart;
    }
    
    void reMarkParams(final XPathContext xctxt) {
        final VariableStack vars = xctxt.getVarStack();
        vars.remarkParams();
    }
    
    public void setIsDefaultTemplate(final boolean b) {
        this.m_isDefaultTemplate = b;
    }
    
    public void setMode(final QName mode) {
        this.m_mode = mode;
    }
}
