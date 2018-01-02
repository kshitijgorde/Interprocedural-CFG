// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLHtmlElement;

public class HTMLHtmlElementImpl extends HTMLElementImpl implements HTMLHtmlElement
{
    public String getVersion() {
        return this.capitalize(this.getAttribute("version"));
    }
    
    public void setVersion(final String version) {
        this.setAttribute("version", version);
    }
    
    public HTMLHtmlElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
