// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xpath.objects.XObject;
import org.apache.xpath.VariableStack;
import org.apache.xpath.XPathContext;
import org.apache.xalan.transformer.TransformerImpl;
import javax.xml.transform.SourceLocator;
import javax.xml.transform.TransformerException;
import org.apache.xalan.res.XSLMessages;
import org.apache.xml.utils.QName;

public class ElemCallTemplate extends ElemForEach
{
    public QName m_templateName;
    private ElemTemplate m_template;
    protected ElemWithParam[] m_paramElems;
    
    public ElemCallTemplate() {
        this.m_templateName = null;
        this.m_template = null;
        this.m_paramElems = null;
    }
    
    public void setName(final QName name) {
        this.m_templateName = name;
    }
    
    public QName getName() {
        return this.m_templateName;
    }
    
    public int getXSLToken() {
        return 17;
    }
    
    public String getNodeName() {
        return "call-template";
    }
    
    public void compose(final StylesheetRoot sroot) throws TransformerException {
        super.compose(sroot);
        for (int length = this.getParamElemCount(), i = 0; i < length; ++i) {
            final ElemWithParam ewp = this.getParamElem(i);
            ewp.compose(sroot);
        }
        if (null != this.m_templateName && null == this.m_template) {
            this.m_template = this.getStylesheetRoot().getTemplateComposed(this.m_templateName);
            if (null == this.m_template) {
                final String themsg = XSLMessages.createMessage("ER_ELEMTEMPLATEELEM_ERR", new Object[] { this.m_templateName });
                throw new TransformerException(themsg, this);
            }
            for (int length = this.getParamElemCount(), j = 0; j < length; ++j) {
                final ElemWithParam ewp2 = this.getParamElem(j);
                ewp2.m_index = -1;
                int etePos = 0;
                for (ElemTemplateElement ete = this.m_template.getFirstChildElem(); null != ete && ete.getXSLToken() == 41; ete = ete.getNextSiblingElem()) {
                    final ElemParam ep = (ElemParam)ete;
                    if (ep.getName().equals(ewp2.getName())) {
                        ewp2.m_index = etePos;
                    }
                    ++etePos;
                }
            }
        }
    }
    
    public void endCompose(final StylesheetRoot sroot) throws TransformerException {
        for (int length = this.getParamElemCount(), i = 0; i < length; ++i) {
            final ElemWithParam ewp = this.getParamElem(i);
            ewp.endCompose(sroot);
        }
        super.endCompose(sroot);
    }
    
    public void execute(final TransformerImpl transformer) throws TransformerException {
        if (TransformerImpl.S_DEBUG) {
            transformer.getTraceManager().fireTraceEvent(this);
        }
        if (null != this.m_template) {
            final XPathContext xctxt = transformer.getXPathContext();
            final VariableStack vars = xctxt.getVarStack();
            final int thisframe = vars.getStackFrame();
            final int nextFrame = vars.link(this.m_template.m_frameSize);
            if (this.m_template.m_inArgsSize > 0) {
                vars.clearLocalSlots(0, this.m_template.m_inArgsSize);
                if (null != this.m_paramElems) {
                    final int currentNode = xctxt.getCurrentNode();
                    vars.setStackFrame(thisframe);
                    for (int size = this.m_paramElems.length, i = 0; i < size; ++i) {
                        final ElemWithParam ewp = this.m_paramElems[i];
                        if (ewp.m_index >= 0) {
                            if (TransformerImpl.S_DEBUG) {
                                transformer.getTraceManager().fireTraceEvent(ewp);
                            }
                            final XObject obj = ewp.getValue(transformer, currentNode);
                            if (TransformerImpl.S_DEBUG) {
                                transformer.getTraceManager().fireTraceEndEvent(ewp);
                            }
                            vars.setLocalVariable(ewp.m_index, obj, nextFrame);
                        }
                    }
                    vars.setStackFrame(nextFrame);
                }
            }
            final SourceLocator savedLocator = xctxt.getSAXLocator();
            try {
                xctxt.setSAXLocator(this.m_template);
                transformer.pushElemTemplateElement(this.m_template);
                this.m_template.execute(transformer);
            }
            finally {
                transformer.popElemTemplateElement();
                xctxt.setSAXLocator(savedLocator);
                vars.unlink(thisframe);
            }
        }
        else {
            transformer.getMsgMgr().error(this, "ER_TEMPLATE_NOT_FOUND", new Object[] { this.m_templateName });
        }
        if (TransformerImpl.S_DEBUG) {
            transformer.getTraceManager().fireTraceEndEvent(this);
        }
    }
    
    public int getParamElemCount() {
        return (this.m_paramElems == null) ? 0 : this.m_paramElems.length;
    }
    
    public ElemWithParam getParamElem(final int i) {
        return this.m_paramElems[i];
    }
    
    public void setParamElem(final ElemWithParam ParamElem) {
        if (null == this.m_paramElems) {
            (this.m_paramElems = new ElemWithParam[1])[0] = ParamElem;
        }
        else {
            final int length = this.m_paramElems.length;
            final ElemWithParam[] ewp = new ElemWithParam[length + 1];
            System.arraycopy(this.m_paramElems, 0, ewp, 0, length);
            (this.m_paramElems = ewp)[length] = ParamElem;
        }
    }
    
    public ElemTemplateElement appendChild(final ElemTemplateElement newChild) {
        final int type = newChild.getXSLToken();
        if (2 == type) {
            this.setParamElem((ElemWithParam)newChild);
        }
        return super.appendChild(newChild);
    }
    
    public void callChildVisitors(final XSLTVisitor visitor, final boolean callAttrs) {
        super.callChildVisitors(visitor, callAttrs);
    }
}
