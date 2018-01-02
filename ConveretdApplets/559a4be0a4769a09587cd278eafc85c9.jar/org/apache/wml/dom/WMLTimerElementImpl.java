// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.wml.dom;

import org.apache.wml.WMLTimerElement;

public class WMLTimerElementImpl extends WMLElementImpl implements WMLTimerElement
{
    public WMLTimerElementImpl(final WMLDocumentImpl owner, final String tagName) {
        super(owner, tagName);
    }
    
    public void setValue(final String newValue) {
        this.setAttribute("value", newValue);
    }
    
    public String getValue() {
        return this.getAttribute("value");
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
    
    public void setName(final String newValue) {
        this.setAttribute("name", newValue);
    }
    
    public String getName() {
        return this.getAttribute("name");
    }
}
