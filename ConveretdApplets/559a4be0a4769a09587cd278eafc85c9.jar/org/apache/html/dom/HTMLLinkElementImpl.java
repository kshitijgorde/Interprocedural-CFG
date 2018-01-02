// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLLinkElement;

public class HTMLLinkElementImpl extends HTMLElementImpl implements HTMLLinkElement
{
    public boolean getDisabled() {
        return this.getBinary("disabled");
    }
    
    public void setDisabled(final boolean disabled) {
        this.setAttribute("disabled", disabled);
    }
    
    public String getCharset() {
        return this.getAttribute("charset");
    }
    
    public void setCharset(final String charset) {
        this.setAttribute("charset", charset);
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
    
    public String getMedia() {
        return this.getAttribute("media");
    }
    
    public void setMedia(final String media) {
        this.setAttribute("media", media);
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
    
    public HTMLLinkElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
