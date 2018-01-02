// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLIsIndexElement;

public class HTMLIsIndexElementImpl extends HTMLElementImpl implements HTMLIsIndexElement
{
    public String getPrompt() {
        return this.getAttribute("prompt");
    }
    
    public void setPrompt(final String prompt) {
        this.setAttribute("prompt", prompt);
    }
    
    public HTMLIsIndexElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
