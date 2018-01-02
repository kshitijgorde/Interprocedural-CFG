// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLAnchorElement;

public class HTMLAnchorElementImpl extends HTMLElementImpl implements HTMLAnchorElement
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
    
    public String getCharset() {
        return this.getAttribute("charset");
    }
    
    public void setCharset(final String charset) {
        this.setAttribute("charset", charset);
    }
    
    public String getCoords() {
        return this.getAttribute("coords");
    }
    
    public void setCoords(final String coords) {
        this.setAttribute("coords", coords);
    }
    
    public String getHref() {
        return this.getAttribute("href");
    }
    
    public void setHref(final String href) {
        this.setAttribute("href", href);
    }
    
    public String getHreflang() {
        return this.getAttribute("hreflang");
    }
    
    public void setHreflang(final String hreflang) {
        this.setAttribute("hreflang", hreflang);
    }
    
    public String getName() {
        return this.getAttribute("name");
    }
    
    public void setName(final String name) {
        this.setAttribute("name", name);
    }
    
    public String getRel() {
        return this.getAttribute("rel");
    }
    
    public void setRel(final String rel) {
        this.setAttribute("rel", rel);
    }
    
    public String getRev() {
        return this.getAttribute("rev");
    }
    
    public void setRev(final String rev) {
        this.setAttribute("rev", rev);
    }
    
    public String getShape() {
        return this.capitalize(this.getAttribute("shape"));
    }
    
    public void setShape(final String shape) {
        this.setAttribute("shape", shape);
    }
    
    public int getTabIndex() {
        return this.getInteger(this.getAttribute("tabindex"));
    }
    
    public void setTabIndex(final int tabIndex) {
        this.setAttribute("tabindex", String.valueOf(tabIndex));
    }
    
    public String getTarget() {
        return this.getAttribute("target");
    }
    
    public void setTarget(final String target) {
        this.setAttribute("target", target);
    }
    
    public String getType() {
        return this.getAttribute("type");
    }
    
    public void setType(final String type) {
        this.setAttribute("type", type);
    }
    
    public void blur() {
    }
    
    public void focus() {
    }
    
    public HTMLAnchorElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
