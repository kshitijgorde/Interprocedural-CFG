// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLSelectElement;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.w3c.dom.html.HTMLOptionElement;

public class HTMLOptionElementImpl extends HTMLElementImpl implements HTMLOptionElement
{
    public boolean getDefaultSelected() {
        return this.getBinary("default-selected");
    }
    
    public void setDefaultSelected(final boolean defaultSelected) {
        this.setAttribute("default-selected", defaultSelected);
    }
    
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
    
    public int getIndex() {
        Node parent;
        for (parent = this.getParentNode(); parent != null && !(parent instanceof HTMLSelectElement); parent = parent.getParentNode()) {}
        if (parent != null) {
            final NodeList options = ((HTMLElement)parent).getElementsByTagName("OPTION");
            for (int i = 0; i < options.getLength(); ++i) {
                if (options.item(i) == this) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public void setIndex(final int index) {
        Node parent;
        for (parent = this.getParentNode(); parent != null && !(parent instanceof HTMLSelectElement); parent = parent.getParentNode()) {}
        if (parent != null) {
            final NodeList options = ((HTMLElement)parent).getElementsByTagName("OPTION");
            if (options.item(index) != this) {
                this.getParentNode().removeChild(this);
                final Node item = options.item(index);
                item.getParentNode().insertBefore(this, item);
            }
        }
    }
    
    public boolean getDisabled() {
        return this.getBinary("disabled");
    }
    
    public void setDisabled(final boolean disabled) {
        this.setAttribute("disabled", disabled);
    }
    
    public String getLabel() {
        return this.capitalize(this.getAttribute("label"));
    }
    
    public void setLabel(final String label) {
        this.setAttribute("label", label);
    }
    
    public boolean getSelected() {
        return this.getBinary("selected");
    }
    
    public void setSelected(final boolean selected) {
        this.setAttribute("selected", selected);
    }
    
    public String getValue() {
        return this.getAttribute("value");
    }
    
    public void setValue(final String value) {
        this.setAttribute("value", value);
    }
    
    public HTMLOptionElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
