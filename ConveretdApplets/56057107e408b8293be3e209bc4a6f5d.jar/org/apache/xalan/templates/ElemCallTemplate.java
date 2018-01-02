// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import javax.xml.transform.TransformerException;
import org.apache.xpath.VariableStack;
import org.apache.xpath.XPathContext;
import javax.xml.transform.SourceLocator;
import org.apache.xalan.transformer.TransformerImpl;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import java.util.Vector;
import org.apache.xml.utils.QName;

public class ElemCallTemplate extends ElemForEach
{
    public QName m_templateName;
    private ElemTemplateElement m_template;
    protected Vector m_paramElems;
    
    public ElemCallTemplate() {
        this.m_templateName = null;
        this.m_template = null;
        this.m_paramElems = null;
    }
    
    public Node appendChild(final Node newChild) throws DOMException {
        final int type = ((ElemTemplateElement)newChild).getXSLToken();
        if (type == 2) {
            this.setParamElem((ElemWithParam)newChild);
        }
        return super.appendChild(newChild);
    }
    
    public void compose() {
        super.compose();
        if (this.m_templateName != null && this.m_template == null) {
            this.m_template = this.getStylesheetRoot().getTemplateComposed(this.m_templateName);
        }
    }
    
    public void execute(final TransformerImpl transformer, final Node sourceNode, final QName mode) throws TransformerException {
        if (TransformerImpl.S_DEBUG) {
            transformer.getTraceManager().fireTraceEvent(sourceNode, mode, this);
        }
        if (this.m_template != null) {
            final XPathContext xctxt = transformer.getXPathContext();
            final VariableStack vars = xctxt.getVarStack();
            final int savedSearchStart = vars.getSearchStart();
            if (this.m_paramElems != null) {
                transformer.pushParams(xctxt, this, sourceNode, mode);
            }
            else {
                vars.pushContextMarker();
            }
            vars.setSearchStart(-1);
            final SourceLocator savedLocator = xctxt.getSAXLocator();
            try {
                xctxt.setSAXLocator(this.m_template);
                transformer.pushElemTemplateElement(this.m_template);
                this.m_template.execute(transformer, sourceNode, mode);
                return;
            }
            finally {
                transformer.popElemTemplateElement();
                xctxt.setSAXLocator(savedLocator);
                vars.popCurrentContext();
                vars.setSearchStart(savedSearchStart);
            }
        }
        transformer.getMsgMgr().error(this, 7, new Object[] { this.m_templateName });
    }
    
    public QName getName() {
        return this.m_templateName;
    }
    
    public String getNodeName() {
        return "call-template";
    }
    
    public ElemWithParam getParamElem(final int i) {
        return this.m_paramElems.elementAt(i);
    }
    
    public int getParamElemCount() {
        return (this.m_paramElems == null) ? 0 : this.m_paramElems.size();
    }
    
    public int getXSLToken() {
        return 17;
    }
    
    public void setName(final QName name) {
        this.m_templateName = name;
    }
    
    public void setParamElem(final ElemWithParam ParamElem) {
        if (this.m_paramElems == null) {
            this.m_paramElems = new Vector();
        }
        this.m_paramElems.addElement(ParamElem);
    }
}
