// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLTextAreaElement;

public class HTMLTextAreaElementImpl extends HTMLElementImpl implements HTMLTextAreaElement, HTMLFormControl
{
    public String getDefaultValue() {
        return this.getAttribute("default-value");
    }
    
    public void setDefaultValue(final String defaultValue) {
        this.setAttribute("default-value", defaultValue);
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
    
    public int getCols() {
        return this.getInteger(this.getAttribute("cols"));
    }
    
    public void setCols(final int cols) {
        this.setAttribute("cols", String.valueOf(cols));
    }
    
    public boolean getDisabled() {
        return this.getBinary("disabled");
    }
    
    public void setDisabled(final boolean disabled) {
        this.setAttribute("disabled", disabled);
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
    
    public int getRows() {
        return this.getInteger(this.getAttribute("rows"));
    }
    
    public void setRows(final int rows) {
        this.setAttribute("rows", String.valueOf(rows));
    }
    
    public int getTabIndex() {
        return this.getInteger(this.getAttribute("tabindex"));
    }
    
    public void setTabIndex(final int tabIndex) {
        this.setAttribute("tabindex", String.valueOf(tabIndex));
    }
    
    public String getType() {
        return this.getAttribute("type");
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
    
    public HTMLTextAreaElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
