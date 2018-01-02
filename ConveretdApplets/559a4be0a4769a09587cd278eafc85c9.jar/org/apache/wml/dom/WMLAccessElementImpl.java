// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.wml.dom;

import org.apache.wml.WMLAccessElement;

public class WMLAccessElementImpl extends WMLElementImpl implements WMLAccessElement
{
    public WMLAccessElementImpl(final WMLDocumentImpl owner, final String tagName) {
        super(owner, tagName);
    }
    
    public void setClassName(final String newValue) {
        this.setAttribute("class", newValue);
    }
    
    public String getClassName() {
        return this.getAttribute("class");
    }
    
    public void setDomain(final String newValue) {
        this.setAttribute("domain", newValue);
    }
    
    public String getDomain() {
        return this.getAttribute("domain");
    }
    
    public void setId(final String newValue) {
        this.setAttribute("id", newValue);
    }
    
    public String getId() {
        return this.getAttribute("id");
    }
    
    public void setPath(final String newValue) {
        this.setAttribute("path", newValue);
    }
    
    public String getPath() {
        return this.getAttribute("path");
    }
}
