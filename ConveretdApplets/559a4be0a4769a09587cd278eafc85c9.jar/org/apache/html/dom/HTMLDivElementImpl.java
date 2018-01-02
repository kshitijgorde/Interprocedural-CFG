// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLDivElement;

public class HTMLDivElementImpl extends HTMLElementImpl implements HTMLDivElement
{
    public String getAlign() {
        return this.capitalize(this.getAttribute("align"));
    }
    
    public void setAlign(final String align) {
        this.setAttribute("align", align);
    }
    
    public HTMLDivElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
