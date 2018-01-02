// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui.listbox;

import java.awt.Rectangle;
import java.io.Serializable;

public class ListSubItem implements Serializable
{
    String pszText;
    int cchTextMax;
    Rectangle rcText;
    
    public ListSubItem() {
        this.pszText = " ";
        this.cchTextMax = 0;
        this.rcText = new Rectangle();
    }
    
    public ListSubItem(final String pszText) {
        this.pszText = pszText;
        this.cchTextMax = 0;
        this.rcText = new Rectangle();
    }
    
    public String getText() {
        return this.pszText;
    }
    
    public void setText(final String pszText) {
        this.pszText = pszText;
    }
}
