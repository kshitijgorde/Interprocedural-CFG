// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLHeadElement;

public class HTMLHeadElementImpl extends HTMLElementImpl implements HTMLHeadElement
{
    public String getProfile() {
        return this.getAttribute("profile");
    }
    
    public void setProfile(final String profile) {
        this.setAttribute("profile", profile);
    }
    
    public HTMLHeadElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
