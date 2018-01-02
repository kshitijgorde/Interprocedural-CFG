// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLTableRowElement;
import org.w3c.dom.html.HTMLCollection;
import org.w3c.dom.html.HTMLTableSectionElement;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.Node;
import org.w3c.dom.html.HTMLTableCaptionElement;
import org.w3c.dom.html.HTMLTableElement;

public class HTMLTableElementImpl extends HTMLElementImpl implements HTMLTableElement
{
    private HTMLCollectionImpl _rows;
    private HTMLCollectionImpl _bodies;
    
    public synchronized HTMLTableCaptionElement getCaption() {
        for (Node child = this.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child instanceof HTMLTableCaptionElement && child.getNodeName().equals("CAPTION")) {
                return (HTMLTableCaptionElement)child;
            }
        }
        return null;
    }
    
    public synchronized void setCaption(final HTMLTableCaptionElement caption) {
        if (caption != null && !caption.getTagName().equals("CAPTION")) {
            throw new IllegalArgumentException("HTM016 Argument 'caption' is not an element of type <CAPTION>.");
        }
        this.deleteCaption();
        if (caption != null) {
            this.appendChild(caption);
        }
    }
    
    public synchronized HTMLElement createCaption() {
        HTMLElement section = this.getCaption();
        if (section != null) {
            return section;
        }
        section = new HTMLTableCaptionElementImpl((HTMLDocumentImpl)this.getOwnerDocument(), "CAPTION");
        this.appendChild(section);
        return section;
    }
    
    public synchronized void deleteCaption() {
        final Node old = this.getCaption();
        if (old != null) {
            this.removeChild(old);
        }
    }
    
    public synchronized HTMLTableSectionElement getTHead() {
        for (Node child = this.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child instanceof HTMLTableSectionElement && child.getNodeName().equals("THEAD")) {
                return (HTMLTableSectionElement)child;
            }
        }
        return null;
    }
    
    public synchronized void setTHead(final HTMLTableSectionElement tHead) {
        if (tHead != null && !tHead.getTagName().equals("THEAD")) {
            throw new IllegalArgumentException("HTM017 Argument 'tHead' is not an element of type <THEAD>.");
        }
        this.deleteTHead();
        if (tHead != null) {
            this.appendChild(tHead);
        }
    }
    
    public synchronized HTMLElement createTHead() {
        HTMLElement section = this.getTHead();
        if (section != null) {
            return section;
        }
        section = new HTMLTableSectionElementImpl((HTMLDocumentImpl)this.getOwnerDocument(), "THEAD");
        this.appendChild(section);
        return section;
    }
    
    public synchronized void deleteTHead() {
        final Node old = this.getTHead();
        if (old != null) {
            this.removeChild(old);
        }
    }
    
    public synchronized HTMLTableSectionElement getTFoot() {
        for (Node child = this.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child instanceof HTMLTableSectionElement && child.getNodeName().equals("TFOOT")) {
                return (HTMLTableSectionElement)child;
            }
        }
        return null;
    }
    
    public synchronized void setTFoot(final HTMLTableSectionElement tFoot) {
        if (tFoot != null && !tFoot.getTagName().equals("TFOOT")) {
            throw new IllegalArgumentException("HTM018 Argument 'tFoot' is not an element of type <TFOOT>.");
        }
        this.deleteTFoot();
        if (tFoot != null) {
            this.appendChild(tFoot);
        }
    }
    
    public synchronized HTMLElement createTFoot() {
        HTMLElement section = this.getTFoot();
        if (section != null) {
            return section;
        }
        section = new HTMLTableSectionElementImpl((HTMLDocumentImpl)this.getOwnerDocument(), "TFOOT");
        this.appendChild(section);
        return section;
    }
    
    public synchronized void deleteTFoot() {
        final Node old = this.getTFoot();
        if (old != null) {
            this.removeChild(old);
        }
    }
    
    public HTMLCollection getRows() {
        if (this._rows == null) {
            this._rows = new HTMLCollectionImpl(this, (short)7);
        }
        return this._rows;
    }
    
    public HTMLCollection getTBodies() {
        if (this._bodies == null) {
            this._bodies = new HTMLCollectionImpl(this, (short)(-2));
        }
        return this._bodies;
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
    
    public String getBorder() {
        return this.getAttribute("border");
    }
    
    public void setBorder(final String border) {
        this.setAttribute("border", border);
    }
    
    public String getCellPadding() {
        return this.getAttribute("cellpadding");
    }
    
    public void setCellPadding(final String cellPadding) {
        this.setAttribute("cellpadding", cellPadding);
    }
    
    public String getCellSpacing() {
        return this.getAttribute("cellspacing");
    }
    
    public void setCellSpacing(final String cellSpacing) {
        this.setAttribute("cellspacing", cellSpacing);
    }
    
    public String getFrame() {
        return this.capitalize(this.getAttribute("frame"));
    }
    
    public void setFrame(final String frame) {
        this.setAttribute("frame", frame);
    }
    
    public String getRules() {
        return this.capitalize(this.getAttribute("rules"));
    }
    
    public void setRules(final String rules) {
        this.setAttribute("rules", rules);
    }
    
    public String getSummary() {
        return this.getAttribute("summary");
    }
    
    public void setSummary(final String summary) {
        this.setAttribute("summary", summary);
    }
    
    public String getWidth() {
        return this.getAttribute("width");
    }
    
    public void setWidth(final String width) {
        this.setAttribute("width", width);
    }
    
    public HTMLElement insertRow(final int index) {
        final HTMLTableRowElementImpl newRow = new HTMLTableRowElementImpl((HTMLDocumentImpl)this.getOwnerDocument(), "TR");
        this.insertRowX(index, newRow);
        return newRow;
    }
    
    void insertRowX(int index, final HTMLTableRowElementImpl newRow) {
        Node lastSection = null;
        for (Node child = this.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child instanceof HTMLTableRowElement) {
                if (index == 0) {
                    this.insertBefore(newRow, child);
                    return;
                }
            }
            else if (child instanceof HTMLTableSectionElementImpl) {
                lastSection = child;
                index = ((HTMLTableSectionElementImpl)child).insertRowX(index, newRow);
                if (index < 0) {
                    return;
                }
            }
        }
        if (lastSection != null) {
            lastSection.appendChild(newRow);
        }
        else {
            this.appendChild(newRow);
        }
    }
    
    public synchronized void deleteRow(int index) {
        for (Node child = this.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child instanceof HTMLTableRowElement) {
                if (index == 0) {
                    this.removeChild(child);
                    return;
                }
            }
            else if (child instanceof HTMLTableSectionElementImpl) {
                index = ((HTMLTableSectionElementImpl)child).deleteRowX(index);
                if (index < 0) {
                    return;
                }
            }
        }
    }
    
    public HTMLTableElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
