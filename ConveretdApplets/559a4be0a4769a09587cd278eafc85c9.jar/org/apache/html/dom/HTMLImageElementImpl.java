// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLImageElement;

public class HTMLImageElementImpl extends HTMLElementImpl implements HTMLImageElement
{
    public String getLowSrc() {
        return this.getAttribute("lowsrc");
    }
    
    public void setLowSrc(final String lowSrc) {
        this.setAttribute("lowsrc", lowSrc);
    }
    
    public String getSrc() {
        return this.getAttribute("src");
    }
    
    public void setSrc(final String src) {
        this.setAttribute("src", src);
    }
    
    public String getName() {
        return this.getAttribute("name");
    }
    
    public void setName(final String name) {
        this.setAttribute("name", name);
    }
    
    public String getAlign() {
        return this.capitalize(this.getAttribute("align"));
    }
    
    public void setAlign(final String align) {
        this.setAttribute("align", align);
    }
    
    public String getAlt() {
        return this.getAttribute("alt");
    }
    
    public void setAlt(final String alt) {
        this.setAttribute("alt", alt);
    }
    
    public String getBorder() {
        return this.getAttribute("border");
    }
    
    public void setBorder(final String border) {
        this.setAttribute("border", border);
    }
    
    public String getHeight() {
        return this.getAttribute("height");
    }
    
    public void setHeight(final String height) {
        this.setAttribute("height", height);
    }
    
    public String getHspace() {
        return this.getAttribute("hspace");
    }
    
    public void setHspace(final String hspace) {
        this.setAttribute("hspace", hspace);
    }
    
    public boolean getIsMap() {
        return this.getBinary("ismap");
    }
    
    public void setIsMap(final boolean isMap) {
        this.setAttribute("ismap", isMap);
    }
    
    public String getLongDesc() {
        return this.getAttribute("longdesc");
    }
    
    public void setLongDesc(final String longDesc) {
        this.setAttribute("longdesc", longDesc);
    }
    
    public String getUseMap() {
        return this.getAttribute("useMap");
    }
    
    public void setUseMap(final String useMap) {
        this.setAttribute("useMap", useMap);
    }
    
    public String getVspace() {
        return this.getAttribute("vspace");
    }
    
    public void setVspace(final String vspace) {
        this.setAttribute("vspace", vspace);
    }
    
    public String getWidth() {
        return this.getAttribute("width");
    }
    
    public void setWidth(final String width) {
        this.setAttribute("width", width);
    }
    
    public HTMLImageElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
