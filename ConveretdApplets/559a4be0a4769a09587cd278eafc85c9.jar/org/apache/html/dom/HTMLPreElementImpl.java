// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLPreElement;

public class HTMLPreElementImpl extends HTMLElementImpl implements HTMLPreElement
{
    public int getWidth() {
        return this.getInteger(this.getAttribute("width"));
    }
    
    public void setWidth(final int width) {
        this.setAttribute("width", String.valueOf(width));
    }
    
    public HTMLPreElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
