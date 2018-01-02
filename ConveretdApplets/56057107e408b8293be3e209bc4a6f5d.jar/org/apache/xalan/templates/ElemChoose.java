// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XObject;
import javax.xml.transform.SourceLocator;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xml.utils.QName;
import org.apache.xalan.transformer.TransformerImpl;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

public class ElemChoose extends ElemTemplateElement
{
    public Node appendChild(final Node newChild) throws DOMException {
        final int type = ((ElemTemplateElement)newChild).getXSLToken();
        switch (type) {
            default: {
                this.error(4, new Object[] { newChild.getNodeName(), this.getNodeName() });
                return super.appendChild(newChild);
            }
            case 38:
            case 39: {
                return super.appendChild(newChild);
            }
        }
    }
    
    public void execute(final TransformerImpl transformer, final Node sourceNode, final QName mode) throws TransformerException {
        if (TransformerImpl.S_DEBUG) {
            transformer.getTraceManager().fireTraceEvent(sourceNode, mode, this);
        }
        boolean found = false;
        for (ElemTemplateElement childElem = this.getFirstChildElem(); childElem != null; childElem = childElem.getNextSiblingElem()) {
            final int type = childElem.getXSLToken();
            if (type == 38) {
                found = true;
                final ElemWhen when = (ElemWhen)childElem;
                final XObject test = when.getTest().execute(transformer.getXPathContext(), sourceNode, this);
                if (TransformerImpl.S_DEBUG) {
                    transformer.getTraceManager().fireSelectedEvent(sourceNode, when, "test", when.getTest(), test);
                }
                if (test != null && test.bool()) {
                    transformer.executeChildTemplates(when, sourceNode, mode);
                    return;
                }
            }
            else if (type == 39) {
                found = true;
                transformer.executeChildTemplates(childElem, sourceNode, mode);
                return;
            }
        }
        if (!found) {
            transformer.getMsgMgr().error(this, 94);
        }
    }
    
    public String getNodeName() {
        return "choose";
    }
    
    public int getXSLToken() {
        return 37;
    }
}
