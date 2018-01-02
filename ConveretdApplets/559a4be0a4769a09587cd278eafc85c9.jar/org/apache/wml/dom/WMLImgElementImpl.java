// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.wml.dom;

import org.apache.wml.WMLImgElement;

public class WMLImgElementImpl extends WMLElementImpl implements WMLImgElement
{
    public WMLImgElementImpl(final WMLDocumentImpl owner, final String tagName) {
        super(owner, tagName);
    }
    
    public void setWidth(final String newValue) {
        this.setAttribute("width", newValue);
    }
    
    public String getWidth() {
        return this.getAttribute("width");
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
    
    public void setLocalSrc(final String newValue) {
        this.setAttribute("localsrc", newValue);
    }
    
    public String getLocalSrc() {
        return this.getAttribute("localsrc");
    }
    
    public void setHeight(final String newValue) {
        this.setAttribute("height", newValue);
    }
    
    public String getHeight() {
        return this.getAttribute("height");
    }
    
    public void setAlign(final String newValue) {
        this.setAttribute("align", newValue);
    }
    
    public String getAlign() {
        return this.getAttribute("align");
    }
    
    public void setVspace(final String newValue) {
        this.setAttribute("vspace", newValue);
    }
    
    public String getVspace() {
        return this.getAttribute("vspace");
    }
    
    public void setAlt(final String newValue) {
        this.setAttribute("alt", newValue);
    }
    
    public String getAlt() {
        return this.getAttribute("alt");
    }
    
    public void setId(final String newValue) {
        this.setAttribute("id", newValue);
    }
    
    public String getId() {
        return this.getAttribute("id");
    }
    
    public void setHspace(final String newValue) {
        this.setAttribute("hspace", newValue);
    }
    
    public String getHspace() {
        return this.getAttribute("hspace");
    }
    
    public void setSrc(final String newValue) {
        this.setAttribute("src", newValue);
    }
    
    public String getSrc() {
        return this.getAttribute("src");
    }
}
