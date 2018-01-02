// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLTableColElement;

public class HTMLTableColElementImpl extends HTMLElementImpl implements HTMLTableColElement
{
    public String getAlign() {
        return this.capitalize(this.getAttribute("align"));
    }
    
    public void setAlign(final String align) {
        this.setAttribute("align", align);
    }
    
    public String getCh() {
        String ch = this.getAttribute("char");
        if (ch != null && ch.length() > 1) {
            ch = ch.substring(0, 1);
        }
        return ch;
    }
    
    public void setCh(String ch) {
        if (ch != null && ch.length() > 1) {
            ch = ch.substring(0, 1);
        }
        this.setAttribute("char", ch);
    }
    
    public String getChOff() {
        return this.getAttribute("charoff");
    }
    
    public void setChOff(final String chOff) {
        this.setAttribute("charoff", chOff);
    }
    
    public int getSpan() {
        return this.getInteger(this.getAttribute("span"));
    }
    
    public void setSpan(final int span) {
        this.setAttribute("span", String.valueOf(span));
    }
    
    public String getVAlign() {
        return this.capitalize(this.getAttribute("valign"));
    }
    
    public void setVAlign(final String vAlign) {
        this.setAttribute("valign", vAlign);
    }
    
    public String getWidth() {
        return this.getAttribute("width");
    }
    
    public void setWidth(final String width) {
        this.setAttribute("width", width);
    }
    
    public HTMLTableColElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
