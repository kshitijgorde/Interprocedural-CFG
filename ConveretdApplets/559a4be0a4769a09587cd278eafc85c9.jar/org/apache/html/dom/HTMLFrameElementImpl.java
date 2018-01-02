// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLFrameElement;

public class HTMLFrameElementImpl extends HTMLElementImpl implements HTMLFrameElement
{
    public String getFrameBorder() {
        return this.getAttribute("frameborder");
    }
    
    public void setFrameBorder(final String frameBorder) {
        this.setAttribute("frameborder", frameBorder);
    }
    
    public String getLongDesc() {
        return this.getAttribute("longdesc");
    }
    
    public void setLongDesc(final String longDesc) {
        this.setAttribute("longdesc", longDesc);
    }
    
    public String getMarginHeight() {
        return this.getAttribute("marginheight");
    }
    
    public void setMarginHeight(final String marginHeight) {
        this.setAttribute("marginheight", marginHeight);
    }
    
    public String getMarginWidth() {
        return this.getAttribute("marginwidth");
    }
    
    public void setMarginWidth(final String marginWidth) {
        this.setAttribute("marginwidth", marginWidth);
    }
    
    public String getName() {
        return this.getAttribute("name");
    }
    
    public void setName(final String name) {
        this.setAttribute("name", name);
    }
    
    public boolean getNoResize() {
        return this.getBinary("noresize");
    }
    
    public void setNoResize(final boolean noResize) {
        this.setAttribute("noresize", noResize);
    }
    
    public String getScrolling() {
        return this.capitalize(this.getAttribute("scrolling"));
    }
    
    public void setScrolling(final String scrolling) {
        this.setAttribute("scrolling", scrolling);
    }
    
    public String getSrc() {
        return this.getAttribute("src");
    }
    
    public void setSrc(final String src) {
        this.setAttribute("src", src);
    }
    
    public HTMLFrameElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
