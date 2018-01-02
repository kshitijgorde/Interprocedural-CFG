// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLModElement;

public class HTMLModElementImpl extends HTMLElementImpl implements HTMLModElement
{
    public String getCite() {
        return this.getAttribute("cite");
    }
    
    public void setCite(final String cite) {
        this.setAttribute("cite", cite);
    }
    
    public String getDateTime() {
        return this.getAttribute("datetime");
    }
    
    public void setDateTime(final String dateTime) {
        this.setAttribute("datetime", dateTime);
    }
    
    public HTMLModElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
