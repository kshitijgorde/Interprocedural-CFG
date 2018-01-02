// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLTableCellElement;
import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.Node;
import org.w3c.dom.html.HTMLTableElement;
import org.w3c.dom.html.HTMLTableSectionElement;
import org.w3c.dom.html.HTMLCollection;
import org.w3c.dom.html.HTMLTableRowElement;

public class HTMLTableRowElementImpl extends HTMLElementImpl implements HTMLTableRowElement
{
    HTMLCollection _cells;
    
    public int getRowIndex() {
        Node parent = this.getParentNode();
        if (parent instanceof HTMLTableSectionElement) {
            parent = parent.getParentNode();
        }
        if (parent instanceof HTMLTableElement) {
            return this.getRowIndex(parent);
        }
        return -1;
    }
    
    public void setRowIndex(final int rowIndex) {
        Node parent = this.getParentNode();
        if (parent instanceof HTMLTableSectionElement) {
            parent = parent.getParentNode();
        }
        if (parent instanceof HTMLTableElement) {
            ((HTMLTableElementImpl)parent).insertRowX(rowIndex, this);
        }
    }
    
    public int getSectionRowIndex() {
        final Node parent = this.getParentNode();
        if (parent instanceof HTMLTableSectionElement) {
            return this.getRowIndex(parent);
        }
        return -1;
    }
    
    public void setSectionRowIndex(final int sectionRowIndex) {
        final Node parent = this.getParentNode();
        if (parent instanceof HTMLTableSectionElement) {
            ((HTMLTableSectionElementImpl)parent).insertRowX(sectionRowIndex, this);
        }
    }
    
    int getRowIndex(final Node parent) {
        final NodeList rows = ((HTMLElement)parent).getElementsByTagName("TR");
        for (int i = 0; i < rows.getLength(); ++i) {
            if (rows.item(i) == this) {
                return i;
            }
        }
        return -1;
    }
    
    public HTMLCollection getCells() {
        if (this._cells == null) {
            this._cells = new HTMLCollectionImpl(this, (short)(-3));
        }
        return this._cells;
    }
    
    public void setCells(final HTMLCollection cells) {
        for (Node child = this.getFirstChild(); child != null; child = child.getNextSibling()) {
            this.removeChild(child);
        }
        int i = 0;
        for (Node child = cells.item(i); child != null; child = cells.item(i)) {
            this.appendChild(child);
            ++i;
        }
    }
    
    public HTMLElement insertCell(int index) {
        final HTMLElement newCell = new HTMLTableCellElementImpl((HTMLDocumentImpl)this.getOwnerDocument(), "TD");
        for (Node child = this.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child instanceof HTMLTableCellElement) {
                if (index == 0) {
                    this.insertBefore(newCell, child);
                    return newCell;
                }
                --index;
            }
        }
        this.appendChild(newCell);
        return newCell;
    }
    
    public void deleteCell(int index) {
        for (Node child = this.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child instanceof HTMLTableCellElement) {
                if (index == 0) {
                    this.removeChild(child);
                    return;
                }
                --index;
            }
        }
    }
    
    public String getAlign() {
        return this.capitalize(this.getAttribute("align"));
    }
    
    public void setAlign(final String align) {
        this.setAttribute("align", align);
    }
    
    public String getBgColor() {
        return this.getAttribute("bgcolor");
    }
    
    public void setBgColor(final String bgColor) {
        this.setAttribute("bgcolor", bgColor);
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
    
    public HTMLTableRowElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
