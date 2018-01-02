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
    
    public void setHref(final String s) {
        this.setAttribute("href", s);
    }
    
    public String getTarget() {
        return this.getAttribute("target");
    }
    
    public void setTarget(final String s) {
        this.setAttribute("target", s);
    }
    
    public HTMLBaseElementImpl(final HTMLDocumentImpl htmlDocumentImpl, final String s) {
        super(htmlDocumentImpl, s);
    }
}
