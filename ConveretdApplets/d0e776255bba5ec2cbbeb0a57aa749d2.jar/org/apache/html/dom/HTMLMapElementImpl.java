// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLCollection;
import org.w3c.dom.html.HTMLMapElement;

public class HTMLMapElementImpl extends HTMLElementImpl implements HTMLMapElement
{
    private HTMLCollection _areas;
    
    public HTMLCollection getAreas() {
        if (this._areas == null) {
            this._areas = new HTMLCollectionImpl(this, (short)(-1));
        }
        return this._areas;
    }
    
    public String getName() {
        return this.getAttribute("name");
    }
    
    public void setName(final String s) {
        this.setAttribute("name", s);
    }
    
    public HTMLMapElementImpl(final HTMLDocumentImpl htmlDocumentImpl, final String s) {
        super(htmlDocumentImpl, s);
    }
}
