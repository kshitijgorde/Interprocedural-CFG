// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.wml.dom;

import org.apache.wml.WMLDoElement;

public class WMLDoElementImpl extends WMLElementImpl implements WMLDoElement
{
    public WMLDoElementImpl(final WMLDocumentImpl owner, final String tagName) {
        super(owner, tagName);
    }
    
    public void setOptional(final String newValue) {
        this.setAttribute("optional", newValue);
    }
    
    public String getOptional() {
        return this.getAttribute("optional");
    }
    
    public void setClassName(final String newValue) {
        this.setAttribute("class", newValue);
    }
    
    public String getClassName() {
        return this.getAttribute("class");
    }
    
    public void setXmlLang(final String newValue) {
        this.setAttribute("xml:lang", newValue);
    }
    
    public String getXmlLang() {
        return this.getAttribute("xml:lang");
    }
    
    public void setId(final String newValue) {
        this.setAttribute("id", newValue);
    }
    
    public String getId() {
        return this.getAttribute("id");
    }
    
    public void setLabel(final String newValue) {
        this.setAttribute("label", newValue);
    }
    
    public String getLabel() {
        return this.getAttribute("label");
    }
    
    public void setType(final String newValue) {
        this.setAttribute("type", newValue);
    }
    
    public String getType() {
        return this.getAttribute("type");
    }
    
    public void setName(final String newValue) {
        this.setAttribute("name", newValue);
    }
    
    public String getName() {
        return this.getAttribute("name");
    }
}
