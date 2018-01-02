// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLDirectoryElement;

public class HTMLDirectoryElementImpl extends HTMLElementImpl implements HTMLDirectoryElement
{
    public boolean getCompact() {
        return this.getBinary("compact");
    }
    
    public void setCompact(final boolean compact) {
        this.setAttribute("compact", compact);
    }
    
    public HTMLDirectoryElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
