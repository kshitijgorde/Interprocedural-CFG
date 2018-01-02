// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLBaseElement;

public class HTMLBaseElementImpl extends HTMLElementImpl implements HTMLBaseElement
{
    public String getHref() {
        return this.getAttribute("href");
    }
    
    public void setHref(final String href) {
        this.setAttribute("href", href);
    }
    
    public String getTarget() {
        return this.getAttribute("target");
    }
    
    public void setTarget(final String target) {
        this.setAttribute("target", target);
    }
    
    public HTMLBaseElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
