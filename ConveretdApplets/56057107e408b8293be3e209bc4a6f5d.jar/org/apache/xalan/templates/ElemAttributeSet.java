// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import javax.xml.transform.TransformerException;
import org.apache.xalan.res.XSLMessages;
import org.apache.xalan.transformer.TransformerImpl;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.apache.xml.utils.QName;

public class ElemAttributeSet extends ElemUse
{
    public QName m_qname;
    
    public ElemAttributeSet() {
        this.m_qname = null;
    }
    
    public Node appendChild(final Node newChild) throws DOMException {
        final int type = ((ElemTemplateElement)newChild).getXSLToken();
        switch (type) {
            default: {
                this.error(4, new Object[] { newChild.getNodeName(), this.getNodeName() });
                return super.appendChild(newChild);
            }
            case 48: {
                return super.appendChild(newChild);
            }
        }
    }
    
    public void execute(final TransformerImpl transformer, final Node sourceNode, final QName mode) throws TransformerException {
        if (transformer.isRecursiveAttrSet(this)) {
            throw new TransformerException(XSLMessages.createMessage(101, new Object[] { this.m_qname.getLocalPart() }));
        }
        transformer.pushElemAttributeSet(this);
        super.execute(transformer, sourceNode, mode);
        for (ElemAttribute attr = (ElemAttribute)this.getFirstChild(); attr != null; attr = (ElemAttribute)attr.getNextSibling()) {
            attr.execute(transformer, sourceNode, mode);
        }
        transformer.popElemAttributeSet();
    }
    
    public QName getName() {
        return this.m_qname;
    }
    
    public String getNodeName() {
        return "attribute-set";
    }
    
    public int getXSLToken() {
        return 40;
    }
    
    public void recompose(final StylesheetRoot root) {
        root.recomposeAttributeSets(this);
    }
    
    public void setName(final QName name) {
        this.m_qname = name;
    }
}
