// 
// Decompiled by Procyon v0.5.30
// 

package org.w3c.dom.html;

import org.w3c.dom.DOMException;

public interface HTMLTableRowElement extends HTMLElement
{
    int getRowIndex();
    
    int getSectionRowIndex();
    
    HTMLCollection getCells();
    
    String getAlign();
    
    void setAlign(final String p0);
    
    String getBgColor();
    
    void setBgColor(final String p0);
    
    String getCh();
    
    void setCh(final String p0);
    
    String getChOff();
    
    void setChOff(final String p0);
    
    String getVAlign();
    
    void setVAlign(final String p0);
    
    HTMLElement insertCell(final int p0) throws DOMException;
    
    void deleteCell(final int p0) throws DOMException;
}
