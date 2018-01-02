// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLUListElement;

public class HTMLUListElementImpl extends HTMLElementImpl implements HTMLUListElement
{
    public boolean getCompact() {
        return this.getBinary("compact");
    }
    
    public void setCompact(final boolean compact) {
        this.setAttribute("compact", compact);
    }
    
    public String getType() {
        return this.getAttribute("type");
    }
    
    public void setType(final String type) {
        this.setAttribute("type", type);
    }
    
    public HTMLUListElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
