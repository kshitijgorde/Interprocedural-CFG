// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLHRElement;

public class HTMLHRElementImpl extends HTMLElementImpl implements HTMLHRElement
{
    public String getAlign() {
        return this.capitalize(this.getAttribute("align"));
    }
    
    public void setAlign(final String align) {
        this.setAttribute("align", align);
    }
    
    public boolean getNoShade() {
        return this.getBinary("noshade");
    }
    
    public void setNoShade(final boolean noShade) {
        this.setAttribute("noshade", noShade);
    }
    
    public String getSize() {
        return this.getAttribute("size");
    }
    
    public void setSize(final String size) {
        this.setAttribute("size", size);
    }
    
    public String getWidth() {
        return this.getAttribute("width");
    }
    
    public void setWidth(final String width) {
        this.setAttribute("width", width);
    }
    
    public HTMLHRElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
