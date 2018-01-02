// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLLIElement;

public class HTMLLIElementImpl extends HTMLElementImpl implements HTMLLIElement
{
    public String getType() {
        return this.getAttribute("type");
    }
    
    public void setType(final String type) {
        this.setAttribute("type", type);
    }
    
    public int getValue() {
        return this.getInteger(this.getAttribute("value"));
    }
    
    public void setValue(final int value) {
        this.setAttribute("value", String.valueOf(value));
    }
    
    public HTMLLIElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
