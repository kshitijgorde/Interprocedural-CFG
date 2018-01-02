// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLStyleElement;

public class HTMLStyleElementImpl extends HTMLElementImpl implements HTMLStyleElement
{
    public boolean getDisabled() {
        return this.getBinary("disabled");
    }
    
    public void setDisabled(final boolean disabled) {
        this.setAttribute("disabled", disabled);
    }
    
    public String getMedia() {
        return this.getAttribute("media");
    }
    
    public void setMedia(final String media) {
        this.setAttribute("media", media);
    }
    
    public String getType() {
        return this.getAttribute("type");
    }
    
    public void setType(final String type) {
        this.setAttribute("type", type);
    }
    
    public HTMLStyleElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
