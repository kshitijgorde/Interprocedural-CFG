// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.xml.sax.SAXException;
import javax.xml.transform.TransformerException;
import org.apache.xalan.transformer.TransformerImpl;

public class ElemComment extends ElemTemplateElement
{
    public int getXSLToken() {
        return 59;
    }
    
    public String getNodeName() {
        return "comment";
    }
    
    public void execute(final TransformerImpl transformer) throws TransformerException {
        if (TransformerImpl.S_DEBUG) {
            transformer.getTraceManager().fireTraceEvent(this);
        }
        try {
            final String data = transformer.transformToString(this);
            transformer.getResultTreeHandler().comment(data);
        }
        catch (SAXException se) {
            throw new TransformerException(se);
        }
        finally {
            if (TransformerImpl.S_DEBUG) {
                transformer.getTraceManager().fireTraceEndEvent(this);
            }
        }
    }
    
    public ElemTemplateElement appendChild(final ElemTemplateElement newChild) {
        final int type = newChild.getXSLToken();
        switch (type) {
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
                break;
            }
            default: {
                this.error("ER_CANNOT_ADD", new Object[] { newChild.getNodeName(), this.getNodeName() });
                break;
            }
        }
        return super.appendChild(newChild);
    }
}
