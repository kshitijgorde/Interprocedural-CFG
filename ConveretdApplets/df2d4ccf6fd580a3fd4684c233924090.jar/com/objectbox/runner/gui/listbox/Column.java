// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui.listbox;

import java.awt.Font;
import java.io.Serializable;

public class Column implements Serializable
{
    protected int m_nCX;
    protected String m_strText;
    protected Font m_fonFont;
    protected int m_cchTextMax;
    protected int m_iSubItem;
    protected int m_nFmt;
    protected int m_nHeaderFmt;
    
    public int getAlignment() {
        return this.m_nFmt;
    }
    
    public Font getFont() {
        return this.m_fonFont;
    }
    
    public int getHeaderAlignment() {
        return this.m_nHeaderFmt;
    }
    
    public String getText() {
        return this.m_strText;
    }
    
    public int getWidth() {
        return this.m_nCX;
    }
    
    public void setAlignment(final int nFmt) {
        this.m_nFmt = nFmt;
    }
    
    public void setFont(final Font fonFont) {
        this.m_fonFont = fonFont;
    }
    
    public void setHeaderAlignment(final int nHeaderFmt) {
        this.m_nHeaderFmt = nHeaderFmt;
    }
    
    public void setText(final String strText) {
        this.m_strText = strText;
    }
    
    public void setWidth(final int ncx) {
        this.m_nCX = ncx;
    }
    
    public Column() {
        this.m_nCX = 0;
        this.m_strText = "";
        this.m_fonFont = new Font("Dialog", 0, 12);
        this.m_cchTextMax = 0;
        this.m_iSubItem = -1;
        this.m_nFmt = 0;
        this.m_nHeaderFmt = 0;
    }
}
