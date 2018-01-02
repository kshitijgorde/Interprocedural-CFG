// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLInputElement;

public class HTMLInputElementImpl extends HTMLElementImpl implements HTMLInputElement, HTMLFormControl
{
    public String getDefaultValue() {
        return this.getAttribute("defaultValue");
    }
    
    public void setDefaultValue(final String defaultValue) {
        this.setAttribute("defaultValue", defaultValue);
    }
    
    public boolean getDefaultChecked() {
        return this.getBinary("defaultChecked");
    }
    
    public void setDefaultChecked(final boolean defaultChecked) {
        this.setAttribute("defaultChecked", defaultChecked);
    }
    
    public String getAccept() {
        return this.getAttribute("accept");
    }
    
    public void setAccept(final String accept) {
        this.setAttribute("accept", accept);
    }
    
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
    
    public String getAlign() {
        return this.capitalize(this.getAttribute("align"));
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
    
    public boolean getChecked() {
        return this.getBinary("checked");
    }
    
    public void setChecked(final boolean checked) {
        this.setAttribute("checked", checked);
    }
    
    public boolean getDisabled() {
        return this.getBinary("disabled");
    }
    
    public void setDisabled(final boolean disabled) {
        this.setAttribute("disabled", disabled);
    }
    
    public int getMaxLength() {
        return this.getInteger(this.getAttribute("maxlength"));
    }
    
    public void setMaxLength(final int maxLength) {
        this.setAttribute("maxlength", String.valueOf(maxLength));
    }
    
    public String getName() {
        return this.getAttribute("name");
    }
    
    public void setName(final String name) {
        this.setAttribute("name", name);
    }
    
    public boolean getReadOnly() {
        return this.getBinary("readonly");
    }
    
    public void setReadOnly(final boolean readOnly) {
        this.setAttribute("readonly", readOnly);
    }
    
    public String getSize() {
        return this.getAttribute("size");
    }
    
    public void setSize(final String size) {
        this.setAttribute("size", size);
    }
    
    public String getSrc() {
        return this.getAttribute("src");
    }
    
    public void setSrc(final String src) {
        this.setAttribute("src", src);
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
    
    public String getUseMap() {
        return this.getAttribute("useMap");
    }
    
    public void setUseMap(final String useMap) {
        this.setAttribute("useMap", useMap);
    }
    
    public String getValue() {
        return this.getAttribute("value");
    }
    
    public void setValue(final String value) {
        this.setAttribute("value", value);
    }
    
    public void blur() {
    }
    
    public void focus() {
    }
    
    public void select() {
    }
    
    public void click() {
    }
    
    public HTMLInputElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
