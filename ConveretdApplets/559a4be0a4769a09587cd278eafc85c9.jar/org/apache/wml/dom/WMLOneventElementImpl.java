// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.wml.dom;

import org.apache.wml.WMLOneventElement;

public class WMLOneventElementImpl extends WMLElementImpl implements WMLOneventElement
{
    public WMLOneventElementImpl(final WMLDocumentImpl owner, final String tagName) {
        super(owner, tagName);
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
    
    public void setType(final String newValue) {
        this.setAttribute("type", newValue);
    }
    
    public String getType() {
        return this.getAttribute("type");
    }
}
