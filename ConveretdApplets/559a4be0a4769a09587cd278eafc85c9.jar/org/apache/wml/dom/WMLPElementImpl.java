// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.wml.dom;

import org.apache.wml.WMLPElement;

public class WMLPElementImpl extends WMLElementImpl implements WMLPElement
{
    public WMLPElementImpl(final WMLDocumentImpl owner, final String tagName) {
        super(owner, tagName);
    }
    
    public void setClassName(final String newValue) {
        this.setAttribute("class", newValue);
    }
    
    public String getClassName() {
        return this.getAttribute("class");
    }
    
    public void setMode(final String newValue) {
        this.setAttribute("mode", newValue);
    }
    
    public String getMode() {
        return this.getAttribute("mode");
    }
    
    public void setXmlLang(final String newValue) {
        this.setAttribute("xml:lang", newValue);
    }
    
    public String getXmlLang() {
        return this.getAttribute("xml:lang");
    }
    
    public void setAlign(final String newValue) {
        this.setAttribute("align", newValue);
    }
    
    public String getAlign() {
        return this.getAttribute("align");
    }
    
    public void setId(final String newValue) {
        this.setAttribute("id", newValue);
    }
    
    public String getId() {
        return this.getAttribute("id");
    }
}
