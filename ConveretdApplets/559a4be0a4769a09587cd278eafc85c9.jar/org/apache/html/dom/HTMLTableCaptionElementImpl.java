// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLTableCaptionElement;

public class HTMLTableCaptionElementImpl extends HTMLElementImpl implements HTMLTableCaptionElement
{
    public String getAlign() {
        return this.getAttribute("align");
    }
    
    public void setAlign(final String align) {
        this.setAttribute("align", align);
    }
    
    public HTMLTableCaptionElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
