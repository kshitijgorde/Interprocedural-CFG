// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.wml.dom;

import org.apache.wml.WMLGoElement;

public class WMLGoElementImpl extends WMLElementImpl implements WMLGoElement
{
    public WMLGoElementImpl(final WMLDocumentImpl owner, final String tagName) {
        super(owner, tagName);
    }
    
    public void setSendreferer(final String newValue) {
        this.setAttribute("sendreferer", newValue);
    }
    
    public String getSendreferer() {
        return this.getAttribute("sendreferer");
    }
    
    public void setAcceptCharset(final String newValue) {
        this.setAttribute("accept-charset", newValue);
    }
    
    public String getAcceptCharset() {
        return this.getAttribute("accept-charset");
    }
    
    public void setHref(final String newValue) {
        this.setAttribute("href", newValue);
    }
    
    public String getHref() {
        return this.getAttribute("href");
    }
    
    public void setClassName(final String newValue) {
        this.setAttribute("class", newValue);
    }
    
    public String getClassName() {
        return this.getAttribute("class");
    }
    
    public void setId(final String newValue) {
        this.setAttribute("id", newValue);
    }
    
    public String getId() {
        return this.getAttribute("id");
    }
    
    public void setMethod(final String newValue) {
        this.setAttribute("method", newValue);
    }
    
    public String getMethod() {
        return this.getAttribute("method");
    }
}
