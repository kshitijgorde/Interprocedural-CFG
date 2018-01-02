// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.Node;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLOptionElement;
import org.w3c.dom.html.HTMLCollection;
import org.w3c.dom.html.HTMLSelectElement;

public class HTMLSelectElementImpl extends HTMLElementImpl implements HTMLSelectElement, HTMLFormControl
{
    private HTMLCollection _options;
    
    public String getType() {
        return this.getAttribute("type");
    }
    
    public String getValue() {
        return this.getAttribute("value");
    }
    
    public void setValue(final String value) {
        this.setAttribute("value", value);
    }
    
    public int getSelectedIndex() {
        final NodeList options = this.getElementsByTagName("OPTION");
        for (int i = 0; i < options.getLength(); ++i) {
            if (((HTMLOptionElement)options.item(i)).getSelected()) {
                return i;
            }
        }
        return -1;
    }
    
    public void setSelectedIndex(final int selectedIndex) {
        final NodeList options = this.getElementsByTagName("OPTION");
        for (int i = 0; i < options.getLength(); ++i) {
            ((HTMLOptionElementImpl)options.item(i)).setSelected(i == selectedIndex);
        }
    }
    
    public HTMLCollection getOptions() {
        if (this._options == null) {
            this._options = new HTMLCollectionImpl(this, (short)6);
        }
        return this._options;
    }
    
    public int getLength() {
        return this.getOptions().getLength();
    }
    
    public boolean getDisabled() {
        return this.getBinary("disabled");
    }
    
    public void setDisabled(final boolean disabled) {
        this.setAttribute("disabled", disabled);
    }
    
    public boolean getMultiple() {
        return this.getBinary("multiple");
    }
    
    public void setMultiple(final boolean multiple) {
        this.setAttribute("multiple", multiple);
    }
    
    public String getName() {
        return this.getAttribute("name");
    }
    
    public void setName(final String name) {
        this.setAttribute("name", name);
    }
    
    public int getSize() {
        return this.getInteger(this.getAttribute("size"));
    }
    
    public void setSize(final int size) {
        this.setAttribute("size", String.valueOf(size));
    }
    
    public int getTabIndex() {
        return this.getInteger(this.getAttribute("tabindex"));
    }
    
    public void setTabIndex(final int tabIndex) {
        this.setAttribute("tabindex", String.valueOf(tabIndex));
    }
    
    public void add(final HTMLElement element, final HTMLElement before) {
        this.insertBefore(element, before);
    }
    
    public void remove(final int index) {
        final NodeList options = this.getElementsByTagName("OPTION");
        final Node removed = options.item(index);
        if (removed != null) {
            removed.getParentNode().removeChild(removed);
        }
    }
    
    public void blur() {
    }
    
    public void focus() {
    }
    
    public NodeList getChildNodes() {
        return this.getChildNodesUnoptimized();
    }
    
    public HTMLSelectElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
