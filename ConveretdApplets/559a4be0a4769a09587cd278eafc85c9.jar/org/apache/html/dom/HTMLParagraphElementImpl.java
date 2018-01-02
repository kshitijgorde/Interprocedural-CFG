// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLParagraphElement;

public class HTMLParagraphElementImpl extends HTMLElementImpl implements HTMLParagraphElement
{
    public String getAlign() {
        return this.getAttribute("align");
    }
    
    public void setAlign(final String align) {
        this.setAttribute("align", align);
    }
    
    public HTMLParagraphElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
