// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.wml.dom;

import org.apache.wml.WMLOptionElement;

public class WMLOptionElementImpl extends WMLElementImpl implements WMLOptionElement
{
    public WMLOptionElementImpl(final WMLDocumentImpl owner, final String tagName) {
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
    
    public void setXmlLang(final String newValue) {
        this.setAttribute("xml:lang", newValue);
    }
    
    public String getXmlLang() {
        return this.getAttribute("xml:lang");
    }
    
    public void setTitle(final String newValue) {
        this.setAttribute("title", newValue);
    }
    
    public String getTitle() {
        return this.getAttribute("title");
    }
    
    public void setId(final String newValue) {
        this.setAttribute("id", newValue);
    }
    
    public String getId() {
        return this.getAttribute("id");
    }
    
    public void setOnPick(final String newValue) {
        this.setAttribute("onpick", newValue);
    }
    
    public String getOnPick() {
        return this.getAttribute("onpick");
    }
}
