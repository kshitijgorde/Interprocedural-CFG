// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLLabelElement;

public class HTMLLabelElementImpl extends HTMLElementImpl implements HTMLLabelElement, HTMLFormControl
{
    public String getAccessKey() {
        String accessKey = this.getAttribute("accesskey");
        if (accessKey != null && accessKey.length() > 1) {
            accessKey = accessKey.substring(0, 1);
        }
        return accessKey;
    }
    
    public void setAccessKey(String accessKey) {
        if (accessKey != null && accessKey.length() > 1) {
            accessKey = accessKey.substring(0, 1);
        }
        this.setAttribute("accesskey", accessKey);
    }
    
    public String getHtmlFor() {
        return this.getAttribute("for");
    }
    
    public void setHtmlFor(final String htmlFor) {
        this.setAttribute("for", htmlFor);
    }
    
    public HTMLLabelElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
