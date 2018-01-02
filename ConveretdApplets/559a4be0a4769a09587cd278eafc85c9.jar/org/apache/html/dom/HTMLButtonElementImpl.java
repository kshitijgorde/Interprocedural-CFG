// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLButtonElement;

public class HTMLButtonElementImpl extends HTMLElementImpl implements HTMLButtonElement, HTMLFormControl
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
        return this.capitalize(this.getAttribute("type"));
    }
    
    public String getValue() {
        return this.getAttribute("value");
    }
    
    public void setValue(final String value) {
        this.setAttribute("value", value);
    }
    
    public HTMLButtonElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
