// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLOListElement;

public class HTMLOListElementImpl extends HTMLElementImpl implements HTMLOListElement
{
    public boolean getCompact() {
        return this.getBinary("compact");
    }
    
    public void setCompact(final boolean compact) {
        this.setAttribute("compact", compact);
    }
    
    public int getStart() {
        return this.getInteger(this.getAttribute("start"));
    }
    
    public void setStart(final int start) {
        this.setAttribute("start", String.valueOf(start));
    }
    
    public String getType() {
        return this.getAttribute("type");
    }
    
    public void setType(final String type) {
        this.setAttribute("type", type);
    }
    
    public HTMLOListElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
