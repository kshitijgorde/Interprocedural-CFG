// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.wml.dom;

import org.apache.wml.WMLTemplateElement;

public class WMLTemplateElementImpl extends WMLElementImpl implements WMLTemplateElement
{
    public WMLTemplateElementImpl(final WMLDocumentImpl owner, final String tagName) {
        super(owner, tagName);
    }
    
    public void setOnTimer(final String newValue) {
        this.setAttribute("ontimer", newValue);
    }
    
    public String getOnTimer() {
        return this.getAttribute("ontimer");
    }
    
    public void setOnEnterBackward(final String newValue) {
        this.setAttribute("onenterbackward", newValue);
    }
    
    public String getOnEnterBackward() {
        return this.getAttribute("onenterbackward");
    }
    
    public void setClassName(final String newValue) {
        this.setAttribute("class", newValue);
    }
    
    public String getClassName() {
        return this.getAttribute("class");
    }
    
    public void setId(final String newValue) {
        this.setAttribute("id", newValue);
    }
    
    public String getId() {
        return this.getAttribute("id");
    }
    
    public void setOnEnterForward(final String newValue) {
        this.setAttribute("onenterforward", newValue);
    }
    
    public String getOnEnterForward() {
        return this.getAttribute("onenterforward");
    }
}
