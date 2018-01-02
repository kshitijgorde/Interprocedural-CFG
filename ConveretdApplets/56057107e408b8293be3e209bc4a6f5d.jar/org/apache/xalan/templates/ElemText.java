// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

public class ElemText extends ElemTemplateElement
{
    private boolean m_disableOutputEscaping;
    
    public ElemText() {
        this.m_disableOutputEscaping = false;
    }
    
    public Node appendChild(final Node newChild) throws DOMException {
        final int type = ((ElemTemplateElement)newChild).getXSLToken();
        switch (type) {
            default: {
                this.error(4, new Object[] { newChild.getNodeName(), this.getNodeName() });
                return super.appendChild(newChild);
            }
            case 78: {
                return super.appendChild(newChild);
            }
        }
    }
    
    public boolean getDisableOutputEscaping() {
        return this.m_disableOutputEscaping;
    }
    
    public String getNodeName() {
        return "text";
    }
    
    public int getXSLToken() {
        return 42;
    }
    
    public void setDisableOutputEscaping(final boolean v) {
        this.m_disableOutputEscaping = v;
    }
}
