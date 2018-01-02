// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.wml.dom;

import org.apache.wml.WMLTableElement;

public class WMLTableElementImpl extends WMLElementImpl implements WMLTableElement
{
    public WMLTableElementImpl(final WMLDocumentImpl wmlDocumentImpl, final String s) {
        super(wmlDocumentImpl, s);
    }
    
    public void setColumns(final int n) {
        this.setAttribute("columns", n);
    }
    
    public int getColumns() {
        return this.getAttribute("columns", 0);
    }
    
    public void setClassName(final String s) {
        this.setAttribute("class", s);
    }
    
    public String getClassName() {
        return this.getAttribute("class");
    }
    
    public void setXmlLang(final String s) {
        this.setAttribute("xml:lang", s);
    }
    
    public String getXmlLang() {
        return this.getAttribute("xml:lang");
    }
    
    public void setAlign(final String s) {
        this.setAttribute("align", s);
    }
    
    public String getAlign() {
        return this.getAttribute("align");
    }
    
    public void setTitle(final String s) {
        this.setAttribute("title", s);
    }
    
    public String getTitle() {
        return this.getAttribute("title");
    }
    
    public void setId(final String s) {
        this.setAttribute("id", s);
    }
    
    public String getId() {
        return this.getAttribute("id");
    }
}