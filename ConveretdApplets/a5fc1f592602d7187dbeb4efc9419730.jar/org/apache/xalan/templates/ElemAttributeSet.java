// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import javax.xml.transform.TransformerException;
import org.apache.xalan.res.XSLMessages;
import org.apache.xalan.transformer.TransformerImpl;
import org.apache.xml.utils.QName;

public class ElemAttributeSet extends ElemUse
{
    public QName m_qname;
    
    public ElemAttributeSet() {
        this.m_qname = null;
    }
    
    public void setName(final QName name) {
        this.m_qname = name;
    }
    
    public QName getName() {
        return this.m_qname;
    }
    
    public int getXSLToken() {
        return 40;
    }
    
    public String getNodeName() {
        return "attribute-set";
    }
    
    public void execute(final TransformerImpl transformer) throws TransformerException {
        if (TransformerImpl.S_DEBUG) {
            transformer.getTraceManager().fireTraceEvent(this);
        }
        if (transformer.isRecursiveAttrSet(this)) {
            throw new TransformerException(XSLMessages.createMessage("ER_XSLATTRSET_USED_ITSELF", new Object[] { this.m_qname.getLocalPart() }));
        }
        transformer.pushElemAttributeSet(this);
        super.execute(transformer);
        for (ElemAttribute attr = (ElemAttribute)this.getFirstChildElem(); null != attr; attr = (ElemAttribute)attr.getNextSiblingElem()) {
            attr.execute(transformer);
        }
        transformer.popElemAttributeSet();
        if (TransformerImpl.S_DEBUG) {
            transformer.getTraceManager().fireTraceEndEvent(this);
        }
    }
    
    public ElemTemplateElement appendChildElem(final ElemTemplateElement newChild) {
        final int type = newChild.getXSLToken();
        switch (type) {
            case 48: {
                break;
            }
            default: {
                this.error("ER_CANNOT_ADD", new Object[] { newChild.getNodeName(), this.getNodeName() });
                break;
            }
        }
        return super.appendChild(newChild);
    }
    
    public void recompose(final StylesheetRoot root) {
        root.recomposeAttributeSets(this);
    }
}
