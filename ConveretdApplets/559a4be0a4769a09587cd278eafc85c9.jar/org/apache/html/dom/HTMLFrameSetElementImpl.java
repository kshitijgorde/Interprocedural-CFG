// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLFrameSetElement;

public class HTMLFrameSetElementImpl extends HTMLElementImpl implements HTMLFrameSetElement
{
    public String getCols() {
        return this.getAttribute("cols");
    }
    
    public void setCols(final String cols) {
        this.setAttribute("cols", cols);
    }
    
    public String getRows() {
        return this.getAttribute("rows");
    }
    
    public void setRows(final String rows) {
        this.setAttribute("rows", rows);
    }
    
    public HTMLFrameSetElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
