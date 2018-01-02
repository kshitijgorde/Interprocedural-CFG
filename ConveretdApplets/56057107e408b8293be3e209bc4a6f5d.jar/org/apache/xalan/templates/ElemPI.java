// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.xml.sax.SAXException;
import javax.xml.transform.TransformerException;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xml.utils.QName;
import org.apache.xalan.transformer.TransformerImpl;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

public class ElemPI extends ElemTemplateElement
{
    private AVT m_name_atv;
    
    public ElemPI() {
        this.m_name_atv = null;
    }
    
    public Node appendChild(final Node newChild) throws DOMException {
        final int type = ((ElemTemplateElement)newChild).getXSLToken();
        switch (type) {
            default: {
                this.error(4, new Object[] { newChild.getNodeName(), this.getNodeName() });
                return super.appendChild(newChild);
            }
            case 9:
            case 17:
            case 28:
            case 30:
            case 35:
            case 36:
            case 37:
            case 42:
            case 50:
            case 72:
            case 73:
            case 74:
            case 75:
            case 78: {
                return super.appendChild(newChild);
            }
        }
    }
    
    public void execute(final TransformerImpl transformer, final Node sourceNode, final QName mode) throws TransformerException {
        if (TransformerImpl.S_DEBUG) {
            transformer.getTraceManager().fireTraceEvent(sourceNode, mode, this);
        }
        final String piName = this.m_name_atv.evaluate(transformer.getXPathContext(), sourceNode, this);
        if (piName.equalsIgnoreCase("xml")) {
            this.error(12);
        }
        else if (!this.isValidNCName(piName)) {
            this.error(13, new Object[] { piName });
        }
        final String data = transformer.transformToString(this, sourceNode, mode);
        try {
            transformer.getResultTreeHandler().processingInstruction(piName, data);
        }
        catch (SAXException se) {
            throw new TransformerException(se);
        }
    }
    
    public AVT getName() {
        return this.m_name_atv;
    }
    
    public String getNodeName() {
        return "processing-instruction";
    }
    
    public int getXSLToken() {
        return 58;
    }
    
    public void setName(final AVT v) {
        this.m_name_atv = v;
    }
}
