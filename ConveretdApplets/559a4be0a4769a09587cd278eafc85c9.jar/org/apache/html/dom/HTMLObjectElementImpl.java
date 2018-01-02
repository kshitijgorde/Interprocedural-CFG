// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLObjectElement;

public class HTMLObjectElementImpl extends HTMLElementImpl implements HTMLObjectElement, HTMLFormControl
{
    public String getCode() {
        return this.getAttribute("code");
    }
    
    public void setCode(final String code) {
        this.setAttribute("code", code);
    }
    
    public String getAlign() {
        return this.capitalize(this.getAttribute("align"));
    }
    
    public void setAlign(final String align) {
        this.setAttribute("align", align);
    }
    
    public String getArchive() {
        return this.getAttribute("archive");
    }
    
    public void setArchive(final String archive) {
        this.setAttribute("archive", archive);
    }
    
    public String getBorder() {
        return this.getAttribute("border");
    }
    
    public void setBorder(final String border) {
        this.setAttribute("border", border);
    }
    
    public String getCodeBase() {
        return this.getAttribute("codebase");
    }
    
    public void setCodeBase(final String codeBase) {
        this.setAttribute("codebase", codeBase);
    }
    
    public String getCodeType() {
        return this.getAttribute("codetype");
    }
    
    public void setCodeType(final String codeType) {
        this.setAttribute("codetype", codeType);
    }
    
    public String getData() {
        return this.getAttribute("data");
    }
    
    public void setData(final String data) {
        this.setAttribute("data", data);
    }
    
    public boolean getDeclare() {
        return this.getBinary("declare");
    }
    
    public void setDeclare(final boolean declare) {
        this.setAttribute("declare", declare);
    }
    
    public String getHeight() {
        return this.getAttribute("height");
    }
    
    public void setHeight(final String height) {
        this.setAttribute("height", height);
    }
    
    public String getHspace() {
        return this.getAttribute("hspace");
    }
    
    public void setHspace(final String hspace) {
        this.setAttribute("hspace", hspace);
    }
    
    public String getName() {
        return this.getAttribute("name");
    }
    
    public void setName(final String name) {
        this.setAttribute("name", name);
    }
    
    public String getStandby() {
        return this.getAttribute("standby");
    }
    
    public void setStandby(final String standby) {
        this.setAttribute("standby", standby);
    }
    
    public int getTabIndex() {
        try {
            return Integer.parseInt(this.getAttribute("tabindex"));
        }
        catch (NumberFormatException except) {
            return 0;
        }
    }
    
    public void setTabIndex(final int tabIndex) {
        this.setAttribute("tabindex", String.valueOf(tabIndex));
    }
    
    public String getType() {
        return this.getAttribute("type");
    }
    
    public void setType(final String type) {
        this.setAttribute("type", type);
    }
    
    public String getUseMap() {
        return this.getAttribute("useMap");
    }
    
    public void setUseMap(final String useMap) {
        this.setAttribute("useMap", useMap);
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
    
    public HTMLObjectElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
