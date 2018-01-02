// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLCollection;
import org.w3c.dom.html.HTMLFormElement;

public class HTMLFormElementImpl extends HTMLElementImpl implements HTMLFormElement
{
    private HTMLCollectionImpl _elements;
    
    public HTMLCollection getElements() {
        if (this._elements == null) {
            this._elements = new HTMLCollectionImpl(this, (short)8);
        }
        return this._elements;
    }
    
    public int getLength() {
        return this.getElements().getLength();
    }
    
    public String getName() {
        return this.getAttribute("name");
    }
    
    public void setName(final String name) {
        this.setAttribute("name", name);
    }
    
    public String getAcceptCharset() {
        return this.getAttribute("accept-charset");
    }
    
    public void setAcceptCharset(final String acceptCharset) {
        this.setAttribute("accept-charset", acceptCharset);
    }
    
    public String getAction() {
        return this.getAttribute("action");
    }
    
    public void setAction(final String action) {
        this.setAttribute("action", action);
    }
    
    public String getEnctype() {
        return this.getAttribute("enctype");
    }
    
    public void setEnctype(final String enctype) {
        this.setAttribute("enctype", enctype);
    }
    
    public String getMethod() {
        return this.capitalize(this.getAttribute("method"));
    }
    
    public void setMethod(final String method) {
        this.setAttribute("method", method);
    }
    
    public String getTarget() {
        return this.getAttribute("target");
    }
    
    public void setTarget(final String target) {
        this.setAttribute("target", target);
    }
    
    public void submit() {
    }
    
    public void reset() {
    }
    
    public NodeList getChildNodes() {
        return this.getChildNodesUnoptimized();
    }
    
    public HTMLFormElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
