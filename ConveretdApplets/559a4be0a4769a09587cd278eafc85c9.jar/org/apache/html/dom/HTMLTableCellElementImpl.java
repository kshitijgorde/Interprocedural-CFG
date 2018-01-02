// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.Node;
import org.w3c.dom.html.HTMLTableRowElement;
import org.w3c.dom.html.HTMLTableCellElement;

public class HTMLTableCellElementImpl extends HTMLElementImpl implements HTMLTableCellElement
{
    public int getCellIndex() {
        final Node parent = this.getParentNode();
        int index = 0;
        if (parent instanceof HTMLTableRowElement) {
            for (Node child = parent.getFirstChild(); child != null; child = child.getNextSibling()) {
                if (child instanceof HTMLTableCellElement) {
                    if (child == this) {
                        return index;
                    }
                    ++index;
                }
            }
        }
        return -1;
    }
    
    public void setCellIndex(int cellIndex) {
        final Node parent = this.getParentNode();
        if (parent instanceof HTMLTableRowElement) {
            for (Node child = parent.getFirstChild(); child != null; child = child.getNextSibling()) {
                if (child instanceof HTMLTableCellElement) {
                    if (cellIndex == 0) {
                        if (this != child) {
                            parent.insertBefore(this, child);
                        }
                        return;
                    }
                    --cellIndex;
                }
            }
        }
        parent.appendChild(this);
    }
    
    public String getAbbr() {
        return this.getAttribute("abbr");
    }
    
    public void setAbbr(final String abbr) {
        this.setAttribute("abbr", abbr);
    }
    
    public String getAlign() {
        return this.capitalize(this.getAttribute("align"));
    }
    
    public void setAlign(final String align) {
        this.setAttribute("align", align);
    }
    
    public String getAxis() {
        return this.getAttribute("axis");
    }
    
    public void setAxis(final String axis) {
        this.setAttribute("axis", axis);
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
    
    public int getColSpan() {
        return this.getInteger(this.getAttribute("colspan"));
    }
    
    public void setColSpan(final int colspan) {
        this.setAttribute("colspan", String.valueOf(colspan));
    }
    
    public String getHeaders() {
        return this.getAttribute("headers");
    }
    
    public void setHeaders(final String headers) {
        this.setAttribute("headers", headers);
    }
    
    public String getHeight() {
        return this.getAttribute("height");
    }
    
    public void setHeight(final String height) {
        this.setAttribute("height", height);
    }
    
    public boolean getNoWrap() {
        return this.getBinary("nowrap");
    }
    
    public void setNoWrap(final boolean noWrap) {
        this.setAttribute("nowrap", noWrap);
    }
    
    public int getRowSpan() {
        return this.getInteger(this.getAttribute("rowspan"));
    }
    
    public void setRowSpan(final int rowspan) {
        this.setAttribute("rowspan", String.valueOf(rowspan));
    }
    
    public String getScope() {
        return this.getAttribute("scope");
    }
    
    public void setScope(final String scope) {
        this.setAttribute("scope", scope);
    }
    
    public String getVAlign() {
        return this.capitalize(this.getAttribute("valign"));
    }
    
    public void setVAlign(final String vAlign) {
        this.setAttribute("valign", vAlign);
    }
    
    public String getWidth() {
        return this.getAttribute("width");
    }
    
    public void setWidth(final String width) {
        this.setAttribute("width", width);
    }
    
    public HTMLTableCellElementImpl(final HTMLDocumentImpl owner, final String name) {
        super(owner, name);
    }
}
