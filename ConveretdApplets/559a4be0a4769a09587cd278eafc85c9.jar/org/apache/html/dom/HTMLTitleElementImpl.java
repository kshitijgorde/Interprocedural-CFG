// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.w3c.dom.html.HTMLTitleElement;

public class HTMLTitleElementImpl extends HTMLElementImpl implements HTMLTitleElement
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
    
    public HTMLTitleElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
