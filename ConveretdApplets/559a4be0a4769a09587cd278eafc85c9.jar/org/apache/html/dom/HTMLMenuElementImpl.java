// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLMenuElement;

public class HTMLMenuElementImpl extends HTMLElementImpl implements HTMLMenuElement
{
    public boolean getCompact() {
        return this.getBinary("compact");
    }
    
    public void setCompact(final boolean compact) {
        this.setAttribute("compact", compact);
    }
    
    public HTMLMenuElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
