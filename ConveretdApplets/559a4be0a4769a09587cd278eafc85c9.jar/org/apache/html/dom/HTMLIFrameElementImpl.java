// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLIFrameElement;

public class HTMLIFrameElementImpl extends HTMLElementImpl implements HTMLIFrameElement
{
    public String getAlign() {
        return this.capitalize(this.getAttribute("align"));
    }
    
    public void setAlign(final String align) {
        this.setAttribute("align", align);
    }
    
    public String getFrameBorder() {
        return this.getAttribute("frameborder");
    }
    
    public void setFrameBorder(final String frameBorder) {
        this.setAttribute("frameborder", frameBorder);
    }
    
    public String getHeight() {
        return this.getAttribute("height");
    }
    
    public void setHeight(final String height) {
        this.setAttribute("height", height);
    }
    
    public String getLongDesc() {
        return this.getAttribute("longdesc");
    }
    
    public void setLongDesc(final String longDesc) {
        this.setAttribute("longdesc", longDesc);
    }
    
    public String getMarginHeight() {
        return this.getAttribute("marginheight");
    }
    
    public void setMarginHeight(final String marginHeight) {
        this.setAttribute("marginheight", marginHeight);
    }
    
    public String getMarginWidth() {
        return this.getAttribute("marginwidth");
    }
    
    public void setMarginWidth(final String marginWidth) {
        this.setAttribute("marginwidth", marginWidth);
    }
    
    public String getName() {
        return this.getAttribute("name");
    }
    
    public void setName(final String name) {
        this.setAttribute("name", name);
    }
    
    public String getScrolling() {
        return this.capitalize(this.getAttribute("scrolling"));
    }
    
    public void setScrolling(final String scrolling) {
        this.setAttribute("scrolling", scrolling);
    }
    
    public String getSrc() {
        return this.getAttribute("src");
    }
    
    public void setSrc(final String src) {
        this.setAttribute("src", src);
    }
    
    public String getWidth() {
        return this.getAttribute("width");
    }
    
    public void setWidth(final String width) {
        this.setAttribute("width", width);
    }
    
    public HTMLIFrameElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
