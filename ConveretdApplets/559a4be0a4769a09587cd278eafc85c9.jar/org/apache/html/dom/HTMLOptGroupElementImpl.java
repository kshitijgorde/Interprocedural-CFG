// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLOptGroupElement;

public class HTMLOptGroupElementImpl extends HTMLElementImpl implements HTMLOptGroupElement
{
    public boolean getDisabled() {
        return this.getBinary("disabled");
    }
    
    public void setDisabled(final boolean disabled) {
        this.setAttribute("disabled", disabled);
    }
    
    public String getLabel() {
        return this.capitalize(this.getAttribute("label"));
    }
    
    public void setLabel(final String label) {
        this.setAttribute("label", label);
    }
    
    public HTMLOptGroupElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
