// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.xml.sax.SAXException;
import javax.xml.transform.TransformerException;
import org.apache.xml.utils.QName;
import org.apache.xalan.transformer.TransformerImpl;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

public class ElemComment extends ElemTemplateElement
{
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
        try {
            if (TransformerImpl.S_DEBUG) {
                transformer.getTraceManager().fireTraceEvent(sourceNode, mode, this);
            }
            final String data = transformer.transformToString(this, sourceNode, mode);
            transformer.getResultTreeHandler().comment(data);
        }
        catch (SAXException e) {
            throw new TransformerException(e);
        }
    }
    
    public String getNodeName() {
        return "comment";
    }
    
    public int getXSLToken() {
        return 59;
    }
}
