// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLLegendElement;

public class HTMLLegendElementImpl extends HTMLElementImpl implements HTMLLegendElement
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
    
    public String getAlign() {
        return this.getAttribute("align");
    }
    
    public void setAlign(final String align) {
        this.setAttribute("align", align);
    }
    
    public HTMLLegendElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
