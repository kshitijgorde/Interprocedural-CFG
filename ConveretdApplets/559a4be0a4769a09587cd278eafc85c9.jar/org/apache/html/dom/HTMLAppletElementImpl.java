// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLAppletElement;

public class HTMLAppletElementImpl extends HTMLElementImpl implements HTMLAppletElement
{
    public String getAlign() {
        return this.getAttribute("align");
    }
    
    public void setAlign(final String align) {
        this.setAttribute("align", align);
    }
    
    public String getAlt() {
        return this.getAttribute("alt");
    }
    
    public void setAlt(final String alt) {
        this.setAttribute("alt", alt);
    }
    
    public String getArchive() {
        return this.getAttribute("archive");
    }
    
    public void setArchive(final String archive) {
        this.setAttribute("archive", archive);
    }
    
    public String getCode() {
        return this.getAttribute("code");
    }
    
    public void setCode(final String code) {
        this.setAttribute("code", code);
    }
    
    public String getCodeBase() {
        return this.getAttribute("codebase");
    }
    
    public void setCodeBase(final String codeBase) {
        this.setAttribute("codebase", codeBase);
    }
    
    public String getHeight() {
        return this.getAttribute("height");
    }
    
    public void setHeight(final String height) {
        this.setAttribute("height", height);
    }
    
    public String getHspace() {
        return this.getAttribute("height");
    }
    
    public void setHspace(final String height) {
        this.setAttribute("height", height);
    }
    
    public String getName() {
        return this.getAttribute("name");
    }
    
    public void setName(final String name) {
        this.setAttribute("name", name);
    }
    
    public String getObject() {
        return this.getAttribute("object");
    }
    
    public void setObject(final String object) {
        this.setAttribute("object", object);
    }
    
    public String getVspace() {
        return this.getAttribute("vspace");
    }
    
    public void setVspace(final String vspace) {
        this.setAttribute("vspace", vspace);
    }
    
    public String getWidth() {
        return this.getAttribute("width");
    }
    
    public void setWidth(final String width) {
        this.setAttribute("width", width);
    }
    
    public HTMLAppletElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}