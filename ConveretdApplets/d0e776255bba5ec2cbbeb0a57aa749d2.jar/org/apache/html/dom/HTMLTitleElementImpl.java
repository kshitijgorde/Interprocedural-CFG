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
        Node node = this.getFirstChild();
        String string = "";
        while (node != null) {
            if (node instanceof Text) {
                string += ((Text)node).getData();
            }
            node = node.getNextSibling();
        }
        return string;
    }
    
    public void setText(final String s) {
        Node nextSibling;
        for (Node firstChild = this.getFirstChild(); firstChild != null; firstChild = nextSibling) {
            nextSibling = firstChild.getNextSibling();
            this.removeChild(firstChild);
        }
        this.insertBefore(this.getOwnerDocument().createTextNode(s), this.getFirstChild());
    }
    
    public HTMLTitleElementImpl(final HTMLDocumentImpl htmlDocumentImpl, final String s) {
        super(htmlDocumentImpl, s);
    }
}
