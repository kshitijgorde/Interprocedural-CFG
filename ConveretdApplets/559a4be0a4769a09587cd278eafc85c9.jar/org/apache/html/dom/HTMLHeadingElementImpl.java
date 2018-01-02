// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLHeadingElement;

public class HTMLHeadingElementImpl extends HTMLElementImpl implements HTMLHeadingElement
{
    public String getAlign() {
        return this.getCapitalized("align");
    }
    
    public void setAlign(final String align) {
        this.setAttribute("align", align);
    }
    
    public HTMLHeadingElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
