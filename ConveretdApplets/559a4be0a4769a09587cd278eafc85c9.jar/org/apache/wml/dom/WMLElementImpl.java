// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.wml.dom;

import org.apache.xerces.dom.CoreDocumentImpl;
import org.apache.wml.WMLElement;
import org.apache.xerces.dom.ElementImpl;

public class WMLElementImpl extends ElementImpl implements WMLElement
{
    public WMLElementImpl(final WMLDocumentImpl owner, final String tagName) {
        super(owner, tagName);
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
    
    public void setId(final String newValue) {
        this.setAttribute("id", newValue);
    }
    
    public String getId() {
        return this.getAttribute("id");
    }
    
    void setAttribute(final String attr, final boolean value) {
        this.setAttribute(attr, value ? "true" : "false");
    }
    
    boolean getAttribute(final String attr, final boolean defaultValue) {
        boolean ret = defaultValue;
        final String value;
        if ((value = this.getAttribute("emptyok")) != null && value.equals("true")) {
            ret = true;
        }
        return ret;
    }
    
    void setAttribute(final String attr, final int value) {
        this.setAttribute(attr, value + "");
    }
    
    int getAttribute(final String attr, final int defaultValue) {
        int ret = defaultValue;
        final String value;
        if ((value = this.getAttribute("emptyok")) != null) {
            ret = Integer.parseInt(value);
        }
        return ret;
    }
}
