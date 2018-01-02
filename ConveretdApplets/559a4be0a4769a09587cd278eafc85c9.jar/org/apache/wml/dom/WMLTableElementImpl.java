// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.wml.dom;

import org.apache.wml.WMLTableElement;

public class WMLTableElementImpl extends WMLElementImpl implements WMLTableElement
{
    public WMLTableElementImpl(final WMLDocumentImpl owner, final String tagName) {
        super(owner, tagName);
    }
    
    public void setColumns(final int newValue) {
        this.setAttribute("columns", newValue);
    }
    
    public int getColumns() {
        return this.getAttribute("columns", 0);
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
    
    public void setAlign(final String newValue) {
        this.setAttribute("align", newValue);
    }
    
    public String getAlign() {
        return this.getAttribute("align");
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
}
