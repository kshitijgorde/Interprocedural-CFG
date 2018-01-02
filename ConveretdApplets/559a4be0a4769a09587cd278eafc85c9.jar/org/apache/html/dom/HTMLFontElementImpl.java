// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLFontElement;

public class HTMLFontElementImpl extends HTMLElementImpl implements HTMLFontElement
{
    public String getColor() {
        return this.capitalize(this.getAttribute("color"));
    }
    
    public void setColor(final String color) {
        this.setAttribute("color", color);
    }
    
    public String getFace() {
        return this.capitalize(this.getAttribute("face"));
    }
    
    public void setFace(final String face) {
        this.setAttribute("face", face);
    }
    
    public String getSize() {
        return this.getAttribute("size");
    }
    
    public void setSize(final String size) {
        this.setAttribute("size", size);
    }
    
    public HTMLFontElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
