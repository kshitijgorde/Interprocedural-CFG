// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

public class ElemText extends ElemTemplateElement
{
    private boolean m_disableOutputEscaping;
    
    public ElemText() {
        this.m_disableOutputEscaping = false;
    }
    
    public void setDisableOutputEscaping(final boolean v) {
        this.m_disableOutputEscaping = v;
    }
    
    public boolean getDisableOutputEscaping() {
        return this.m_disableOutputEscaping;
    }
    
    public int getXSLToken() {
        return 42;
    }
    
    public String getNodeName() {
        return "text";
    }
    
    public ElemTemplateElement appendChild(final ElemTemplateElement newChild) {
        final int type = newChild.getXSLToken();
        switch (type) {
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
