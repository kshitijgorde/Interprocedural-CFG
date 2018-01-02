// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLParamElement;

public class HTMLParamElementImpl extends HTMLElementImpl implements HTMLParamElement
{
    public String getName() {
        return this.getAttribute("name");
    }
    
    public void setName(final String name) {
        this.setAttribute("name", name);
    }
    
    public String getType() {
        return this.getAttribute("type");
    }
    
    public void setType(final String type) {
        this.setAttribute("type", type);
    }
    
    public String getValue() {
        return this.getAttribute("value");
    }
    
    public void setValue(final String value) {
        this.setAttribute("value", value);
    }
    
    public String getValueType() {
        return this.capitalize(this.getAttribute("valuetype"));
    }
    
    public void setValueType(final String valueType) {
        this.setAttribute("valuetype", valueType);
    }
    
    public HTMLParamElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
