// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

import javax.xml.transform.TransformerException;
import org.apache.xalan.res.XSLMessages;
import org.apache.xml.utils.ObjectStack;
import org.apache.xalan.templates.ElemTemplateElement;
import org.apache.xalan.templates.ElemTemplate;

public class StackGuard
{
    public static int m_recursionLimit;
    TransformerImpl m_transformer;
    
    public int getRecursionLimit() {
        return StackGuard.m_recursionLimit;
    }
    
    public void setRecursionLimit(final int limit) {
        StackGuard.m_recursionLimit = limit;
    }
    
    public StackGuard(final TransformerImpl transformerImpl) {
        this.m_transformer = transformerImpl;
    }
    
    public int countLikeTemplates(final ElemTemplate templ, final int pos) {
        final ObjectStack elems = this.m_transformer.getCurrentTemplateElements();
        int count = 1;
        for (int i = pos - 1; i >= 0; --i) {
            if (elems.elementAt(i) == templ) {
                ++count;
            }
        }
        return count;
    }
    
    private ElemTemplate getNextMatchOrNamedTemplate(final int pos) {
        final ObjectStack elems = this.m_transformer.getCurrentTemplateElements();
        for (int i = pos; i >= 0; --i) {
            final ElemTemplateElement elem = (ElemTemplateElement)elems.elementAt(i);
            if (null != elem && elem.getXSLToken() == 19) {
                return (ElemTemplate)elem;
            }
        }
        return null;
    }
    
    public void checkForInfinateLoop() throws TransformerException {
        final int nTemplates = this.m_transformer.getCurrentTemplateElementsCount();
        if (nTemplates < StackGuard.m_recursionLimit) {
            return;
        }
        if (StackGuard.m_recursionLimit <= 0) {
            return;
        }
        for (int i = nTemplates - 1; i >= StackGuard.m_recursionLimit; --i) {
            final ElemTemplate template = this.getNextMatchOrNamedTemplate(i);
            if (null == template) {
                break;
            }
            final int loopCount = this.countLikeTemplates(template, i);
            if (loopCount >= StackGuard.m_recursionLimit) {
                final String idIs = XSLMessages.createMessage((null != template.getName()) ? "nameIs" : "matchPatternIs", null);
                final Object[] msgArgs = { new Integer(loopCount), idIs, (null != template.getName()) ? template.getName().toString() : template.getMatch().getPatternString() };
                final String msg = XSLMessages.createMessage("recursionTooDeep", msgArgs);
                throw new TransformerException(msg);
            }
        }
    }
    
    static {
        StackGuard.m_recursionLimit = -1;
    }
}
