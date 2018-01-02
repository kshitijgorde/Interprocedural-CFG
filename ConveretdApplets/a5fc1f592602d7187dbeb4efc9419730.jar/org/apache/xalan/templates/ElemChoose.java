// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;
import javax.xml.transform.SourceLocator;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xalan.transformer.TransformerImpl;

public class ElemChoose extends ElemTemplateElement
{
    public int getXSLToken() {
        return 37;
    }
    
    public String getNodeName() {
        return "choose";
    }
    
    public void execute(final TransformerImpl transformer) throws TransformerException {
        if (TransformerImpl.S_DEBUG) {
            transformer.getTraceManager().fireTraceEvent(this);
        }
        boolean found = false;
        for (ElemTemplateElement childElem = this.getFirstChildElem(); childElem != null; childElem = childElem.getNextSiblingElem()) {
            final int type = childElem.getXSLToken();
            if (38 == type) {
                found = true;
                final ElemWhen when = (ElemWhen)childElem;
                final XPathContext xctxt = transformer.getXPathContext();
                final int sourceNode = xctxt.getCurrentNode();
                if (TransformerImpl.S_DEBUG) {
                    final XObject test = when.getTest().execute(xctxt, sourceNode, when);
                    if (TransformerImpl.S_DEBUG) {
                        transformer.getTraceManager().fireSelectedEvent(sourceNode, when, "test", when.getTest(), test);
                    }
                    if (test.bool()) {
                        transformer.getTraceManager().fireTraceEvent(when);
                        transformer.executeChildTemplates(when, true);
                        transformer.getTraceManager().fireTraceEndEvent(when);
                        return;
                    }
                }
                else if (when.getTest().bool(xctxt, sourceNode, when)) {
                    transformer.executeChildTemplates(when, true);
                    return;
                }
            }
            else if (39 == type) {
                found = true;
                if (TransformerImpl.S_DEBUG) {
                    transformer.getTraceManager().fireTraceEvent(childElem);
                }
                transformer.executeChildTemplates(childElem, true);
                if (TransformerImpl.S_DEBUG) {
                    transformer.getTraceManager().fireTraceEndEvent(childElem);
                }
                return;
            }
        }
        if (!found) {
            transformer.getMsgMgr().error(this, "ER_CHOOSE_REQUIRES_WHEN");
        }
        if (TransformerImpl.S_DEBUG) {
            transformer.getTraceManager().fireTraceEndEvent(this);
        }
    }
    
    public ElemTemplateElement appendChild(final ElemTemplateElement newChild) {
        final int type = newChild.getXSLToken();
        switch (type) {
            case 38:
            case 39: {
                break;
            }
            default: {
                this.error("ER_CANNOT_ADD", new Object[] { newChild.getNodeName(), this.getNodeName() });
                break;
            }
        }
        return super.appendChild(newChild);
    }
    
    public boolean canAcceptVariables() {
        return false;
    }
}
