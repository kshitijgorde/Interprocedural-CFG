// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLBodyElement;

public class HTMLBodyElementImpl extends HTMLElementImpl implements HTMLBodyElement
{
    public String getALink() {
        return this.getAttribute("alink");
    }
    
    public void setALink(final String aLink) {
        this.setAttribute("alink", aLink);
    }
    
    public String getBackground() {
        return this.getAttribute("background");
    }
    
    public void setBackground(final String background) {
        this.setAttribute("background", background);
    }
    
    public String getBgColor() {
        return this.getAttribute("bgcolor");
    }
    
    public void setBgColor(final String bgColor) {
        this.setAttribute("bgcolor", bgColor);
    }
    
    public String getLink() {
        return this.getAttribute("link");
    }
    
    public void setLink(final String link) {
        this.setAttribute("link", link);
    }
    
    public String getText() {
        return this.getAttribute("text");
    }
    
    public void setText(final String text) {
        this.setAttribute("text", text);
    }
    
    public String getVLink() {
        return this.getAttribute("vlink");
    }
    
    public void setVLink(final String vLink) {
        this.setAttribute("vlink", vLink);
    }
    
    public HTMLBodyElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
