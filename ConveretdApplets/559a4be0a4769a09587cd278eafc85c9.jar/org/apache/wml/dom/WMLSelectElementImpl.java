// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.wml.dom;

import org.apache.wml.WMLSelectElement;

public class WMLSelectElementImpl extends WMLElementImpl implements WMLSelectElement
{
    public WMLSelectElementImpl(final WMLDocumentImpl owner, final String tagName) {
        super(owner, tagName);
    }
    
    public void setMultiple(final boolean newValue) {
        this.setAttribute("multiple", newValue);
    }
    
    public boolean getMultiple() {
        return this.getAttribute("multiple", false);
    }
    
    public void setValue(final String newValue) {
        this.setAttribute("value", newValue);
    }
    
    public String getValue() {
        return this.getAttribute("value");
    }
    
    public void setTabIndex(final int newValue) {
        this.setAttribute("tabindex", newValue);
    }
    
    public int getTabIndex() {
        return this.getAttribute("tabindex", 0);
    }
    
    public void setClassName(final String newValue) {
        this.setAttribute("class", newValue);
    }
    
    public String getClassName() {
        return this.getAttribute("class");
    }
    
    public void setXmlLang(final String newValue) {
        this.setAttribute("xml:lang", newValue);
    }
    
    public String getXmlLang() {
        return this.getAttribute("xml:lang");
    }
    
    public void setTitle(final String newValue) {
        this.setAttribute("title", newValue);
    }
    
    public String getTitle() {
        return this.getAttribute("title");
    }
    
    public void setIValue(final String newValue) {
        this.setAttribute("ivalue", newValue);
    }
    
    public String getIValue() {
        return this.getAttribute("ivalue");
    }
    
    public void setId(final String newValue) {
        this.setAttribute("id", newValue);
    }
    
    public String getId() {
        return this.getAttribute("id");
    }
    
    public void setIName(final String newValue) {
        this.setAttribute("iname", newValue);
    }
    
    public String getIName() {
        return this.getAttribute("iname");
    }
    
    public void setName(final String newValue) {
        this.setAttribute("name", newValue);
    }
    
    public String getName() {
        return this.getAttribute("name");
    }
}
