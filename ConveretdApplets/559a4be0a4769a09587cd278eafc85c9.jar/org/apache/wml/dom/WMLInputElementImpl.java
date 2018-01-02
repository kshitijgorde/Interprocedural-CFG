// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.wml.dom;

import org.apache.wml.WMLInputElement;

public class WMLInputElementImpl extends WMLElementImpl implements WMLInputElement
{
    public WMLInputElementImpl(final WMLDocumentImpl owner, final String tagName) {
        super(owner, tagName);
    }
    
    public void setSize(final int newValue) {
        this.setAttribute("size", newValue);
    }
    
    public int getSize() {
        return this.getAttribute("size", 0);
    }
    
    public void setFormat(final String newValue) {
        this.setAttribute("format", newValue);
    }
    
    public String getFormat() {
        return this.getAttribute("format");
    }
    
    public void setValue(final String newValue) {
        this.setAttribute("value", newValue);
    }
    
    public String getValue() {
        return this.getAttribute("value");
    }
    
    public void setMaxLength(final int newValue) {
        this.setAttribute("maxlength", newValue);
    }
    
    public int getMaxLength() {
        return this.getAttribute("maxlength", 0);
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
    
    public void setEmptyOk(final boolean newValue) {
        this.setAttribute("emptyok", newValue);
    }
    
    public boolean getEmptyOk() {
        return this.getAttribute("emptyok", false);
    }
    
    public void setTitle(final String newValue) {
        this.setAttribute("title", newValue);
    }
    
    public String getTitle() {
        return this.getAttribute("title");
    }
    
    public void setId(final String newValue) {
        this.setAttribute("id", newValue);
    }
    
    public String getId() {
        return this.getAttribute("id");
    }
    
    public void setType(final String newValue) {
        this.setAttribute("type", newValue);
    }
    
    public String getType() {
        return this.getAttribute("type");
    }
    
    public void setName(final String newValue) {
        this.setAttribute("name", newValue);
    }
    
    public String getName() {
        return this.getAttribute("name");
    }
}
