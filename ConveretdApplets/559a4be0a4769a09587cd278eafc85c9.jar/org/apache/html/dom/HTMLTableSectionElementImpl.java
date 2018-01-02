// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLTableRowElement;
import org.w3c.dom.Node;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLCollection;
import org.w3c.dom.html.HTMLTableSectionElement;

public class HTMLTableSectionElementImpl extends HTMLElementImpl implements HTMLTableSectionElement
{
    private HTMLCollectionImpl _rows;
    
    public String getAlign() {
        return this.capitalize(this.getAttribute("align"));
    }
    
    public void setAlign(final String align) {
        this.setAttribute("align", align);
    }
    
    public String getCh() {
        String ch = this.getAttribute("char");
        if (ch != null && ch.length() > 1) {
            ch = ch.substring(0, 1);
        }
        return ch;
    }
    
    public void setCh(String ch) {
        if (ch != null && ch.length() > 1) {
            ch = ch.substring(0, 1);
        }
        this.setAttribute("char", ch);
    }
    
    public String getChOff() {
        return this.getAttribute("charoff");
    }
    
    public void setChOff(final String chOff) {
        this.setAttribute("charoff", chOff);
    }
    
    public String getVAlign() {
        return this.capitalize(this.getAttribute("valign"));
    }
    
    public void setVAlign(final String vAlign) {
        this.setAttribute("valign", vAlign);
    }
    
    public HTMLCollection getRows() {
        if (this._rows == null) {
            this._rows = new HTMLCollectionImpl(this, (short)7);
        }
        return this._rows;
    }
    
    public HTMLElement insertRow(final int index) {
        final HTMLTableRowElementImpl newRow = new HTMLTableRowElementImpl((HTMLDocumentImpl)this.getOwnerDocument(), "TR");
        newRow.insertCell(0);
        if (this.insertRowX(index, newRow) >= 0) {
            this.appendChild(newRow);
        }
        return newRow;
    }
    
    int insertRowX(int index, final HTMLTableRowElementImpl newRow) {
        for (Node child = this.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child instanceof HTMLTableRowElement) {
                if (index == 0) {
                    this.insertBefore(newRow, child);
                    return -1;
                }
                --index;
            }
        }
        return index;
    }
    
    public void deleteRow(final int index) {
        this.deleteRowX(index);
    }
    
    int deleteRowX(int index) {
        for (Node child = this.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child instanceof HTMLTableRowElement) {
                if (index == 0) {
                    this.removeChild(child);
                    return -1;
                }
                --index;
            }
        }
        return index;
    }
    
    public HTMLTableSectionElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
