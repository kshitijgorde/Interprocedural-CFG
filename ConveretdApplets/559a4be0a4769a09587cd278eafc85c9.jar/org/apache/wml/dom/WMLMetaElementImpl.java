// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.wml.dom;

import org.apache.wml.WMLMetaElement;

public class WMLMetaElementImpl extends WMLElementImpl implements WMLMetaElement
{
    public WMLMetaElementImpl(final WMLDocumentImpl owner, final String tagName) {
        super(owner, tagName);
    }
    
    public void setForua(final boolean newValue) {
        this.setAttribute("forua", newValue);
    }
    
    public boolean getForua() {
        return this.getAttribute("forua", false);
    }
    
    public void setScheme(final String newValue) {
        this.setAttribute("scheme", newValue);
    }
    
    public String getScheme() {
        return this.getAttribute("scheme");
    }
    
    public void setClassName(final String newValue) {
        this.setAttribute("class", newValue);
    }
    
    public String getClassName() {
        return this.getAttribute("class");
    }
    
    public void setHttpEquiv(final String newValue) {
        this.setAttribute("http-equiv", newValue);
    }
    
    public String getHttpEquiv() {
        return this.getAttribute("http-equiv");
    }
    
    public void setId(final String newValue) {
        this.setAttribute("id", newValue);
    }
    
    public String getId() {
        return this.getAttribute("id");
    }
    
    public void setContent(final String newValue) {
        this.setAttribute("content", newValue);
    }
    
    public String getContent() {
        return this.getAttribute("content");
    }
    
    public void setName(final String newValue) {
        this.setAttribute("name", newValue);
    }
    
    public String getName() {
        return this.getAttribute("name");
    }
}
