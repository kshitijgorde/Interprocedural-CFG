// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLAreaElement;

public class HTMLAreaElementImpl extends HTMLElementImpl implements HTMLAreaElement
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
    
    public String getAlt() {
        return this.getAttribute("alt");
    }
    
    public void setAlt(final String alt) {
        this.setAttribute("alt", alt);
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
    
    public boolean getNoHref() {
        return this.getBinary("href");
    }
    
    public void setNoHref(final boolean noHref) {
        this.setAttribute("nohref", noHref);
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
    
    public HTMLAreaElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
