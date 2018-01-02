// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.w3c.dom.html.HTMLScriptElement;

public class HTMLScriptElementImpl extends HTMLElementImpl implements HTMLScriptElement
{
    public String getText() {
        Node child = this.getFirstChild();
        String text = "";
        while (child != null) {
            if (child instanceof Text) {
                text += ((Text)child).getData();
            }
            child = child.getNextSibling();
        }
        return text;
    }
    
    public void setText(final String text) {
        Node next;
        for (Node child = this.getFirstChild(); child != null; child = next) {
            next = child.getNextSibling();
            this.removeChild(child);
        }
        this.insertBefore(this.getOwnerDocument().createTextNode(text), this.getFirstChild());
    }
    
    public String getHtmlFor() {
        return this.getAttribute("for");
    }
    
    public void setHtmlFor(final String htmlFor) {
        this.setAttribute("for", htmlFor);
    }
    
    public String getEvent() {
        return this.getAttribute("event");
    }
    
    public void setEvent(final String event) {
        this.setAttribute("event", event);
    }
    
    public String getCharset() {
        return this.getAttribute("charset");
    }
    
    public void setCharset(final String charset) {
        this.setAttribute("charset", charset);
    }
    
    public boolean getDefer() {
        return this.getBinary("defer");
    }
    
    public void setDefer(final boolean defer) {
        this.setAttribute("defer", defer);
    }
    
    public String getSrc() {
        return this.getAttribute("src");
    }
    
    public void setSrc(final String src) {
        this.setAttribute("src", src);
    }
    
    public String getType() {
        return this.getAttribute("type");
    }
    
    public void setType(final String type) {
        this.setAttribute("type", type);
    }
    
    public HTMLScriptElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
