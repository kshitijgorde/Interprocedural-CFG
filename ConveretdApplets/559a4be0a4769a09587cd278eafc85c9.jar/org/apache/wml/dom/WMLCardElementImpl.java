// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.wml.dom;

import org.apache.wml.WMLCardElement;

public class WMLCardElementImpl extends WMLElementImpl implements WMLCardElement
{
    public WMLCardElementImpl(final WMLDocumentImpl owner, final String tagName) {
        super(owner, tagName);
    }
    
    public void setOnTimer(final String newValue) {
        this.setAttribute("ontimer", newValue);
    }
    
    public String getOnTimer() {
        return this.getAttribute("ontimer");
    }
    
    public void setOrdered(final boolean newValue) {
        this.setAttribute("ordered", newValue);
    }
    
    public boolean getOrdered() {
        return this.getAttribute("ordered", true);
    }
    
    public void setOnEnterBackward(final String newValue) {
        this.setAttribute("onenterbackward", newValue);
    }
    
    public String getOnEnterBackward() {
        return this.getAttribute("onenterbackward");
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
    
    public void setNewContext(final boolean newValue) {
        this.setAttribute("newcontext", newValue);
    }
    
    public boolean getNewContext() {
        return this.getAttribute("newcontext", false);
    }
    
    public void setId(final String newValue) {
        this.setAttribute("id", newValue);
    }
    
    public String getId() {
        return this.getAttribute("id");
    }
    
    public void setOnEnterForward(final String newValue) {
        this.setAttribute("onenterforward", newValue);
    }
    
    public String getOnEnterForward() {
        return this.getAttribute("onenterforward");
    }
}
