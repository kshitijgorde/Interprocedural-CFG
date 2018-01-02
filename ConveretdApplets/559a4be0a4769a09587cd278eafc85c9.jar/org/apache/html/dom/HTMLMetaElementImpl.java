// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLMetaElement;

public class HTMLMetaElementImpl extends HTMLElementImpl implements HTMLMetaElement
{
    public String getContent() {
        return this.getAttribute("content");
    }
    
    public void setContent(final String content) {
        this.setAttribute("content", content);
    }
    
    public String getHttpEquiv() {
        return this.getAttribute("http-equiv");
    }
    
    public void setHttpEquiv(final String httpEquiv) {
        this.setAttribute("http-equiv", httpEquiv);
    }
    
    public String getName() {
        return this.getAttribute("name");
    }
    
    public void setName(final String name) {
        this.setAttribute("name", name);
    }
    
    public String getScheme() {
        return this.getAttribute("scheme");
    }
    
    public void setScheme(final String scheme) {
        this.setAttribute("scheme", scheme);
    }
    
    public HTMLMetaElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
