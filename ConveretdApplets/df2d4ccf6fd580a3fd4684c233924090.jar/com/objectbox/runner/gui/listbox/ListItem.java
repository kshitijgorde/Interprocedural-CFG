// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui.listbox;

import java.awt.Font;
import java.awt.Rectangle;
import java.util.Vector;
import java.io.Serializable;

public class ListItem implements Serializable
{
    protected Vector m_arrSubItems;
    protected String m_pszText;
    protected int m_nImage;
    protected int m_nCY;
    protected Rectangle m_rcText;
    protected Rectangle m_rcIcon;
    protected Font m_fonFont;
    protected Object m_objData;
    protected boolean m_bIsSelected;
    protected boolean m_bIsDropHiLited;
    protected boolean m_bIsFocused;
    protected boolean m_bIsMarked;
    protected boolean m_bIsDisabled;
    
    public void addSubItem() {
        this.addSubItem(new ListSubItem());
    }
    
    public void addSubItem(final ListSubItem listSubItem) {
        this.m_arrSubItems.addElement(listSubItem);
    }
    
    public void addSubItem(final String s) {
        this.addSubItem(new ListSubItem(s));
    }
    
    public void copy(final ListItem listItem) {
        this.m_arrSubItems = (Vector)listItem.m_arrSubItems.clone();
        this.m_pszText = new String(listItem.m_pszText);
        this.m_nImage = listItem.m_nImage;
        this.m_nCY = listItem.m_nCY;
        this.m_rcText = listItem.m_rcText;
        this.m_rcIcon = listItem.m_rcIcon;
        this.m_fonFont = listItem.m_fonFont;
        this.m_bIsSelected = listItem.m_bIsSelected;
        this.m_bIsDropHiLited = listItem.m_bIsDropHiLited;
        this.m_bIsFocused = listItem.m_bIsFocused;
        this.m_bIsMarked = listItem.m_bIsMarked;
        this.m_bIsDisabled = listItem.m_bIsDisabled;
    }
    
    public boolean deleteSubItem(final int n) {
        if (n > 0 && n - 1 < this.m_arrSubItems.size()) {
            this.m_arrSubItems.removeElementAt(n - 1);
            return true;
        }
        return false;
    }
    
    public Font getFont() {
        return this.m_fonFont;
    }
    
    public int getHeight() {
        return this.m_nCY;
    }
    
    public int getImage() {
        return this.m_nImage;
    }
    
    public Rectangle getImageBounds() {
        return this.m_rcIcon;
    }
    
    public Object getItemData() {
        return this.m_objData;
    }
    
    public ListSubItem getSubItem(final int n) {
        if (n > 0 && n - 1 < this.m_arrSubItems.size()) {
            return this.m_arrSubItems.elementAt(n - 1);
        }
        return null;
    }
    
    public int getSubItemCount() {
        return this.m_arrSubItems.size();
    }
    
    public Vector getSubItems() {
        return this.m_arrSubItems;
    }
    
    public String getText() {
        return this.m_pszText;
    }
    
    public Rectangle getTextBounds() {
        return this.m_rcText;
    }
    
    public void insertSubItem(int n) {
        final int subItemCount = this.getSubItemCount();
        if (n < 0) {
            n = subItemCount;
        }
        if (n > subItemCount) {
            this.addSubItem();
        }
        else {
            this.m_arrSubItems.insertElementAt(new ListSubItem(), n);
        }
    }
    
    public void setFont(final Font fonFont) {
        this.m_fonFont = fonFont;
    }
    
    public void setHeight(final int ncy) {
        this.m_nCY = ncy;
    }
    
    public void setImage(final int nImage) {
        this.m_nImage = nImage;
    }
    
    public void setImageBounds(final Rectangle rcIcon) {
        this.m_rcIcon = rcIcon;
    }
    
    public void setImageYPos(final int y) {
        this.m_rcIcon.y = y;
    }
    
    public void setItemData(final Object objData) {
        this.m_objData = objData;
    }
    
    public void setSubItems(final Vector arrSubItems) {
        this.m_arrSubItems = arrSubItems;
    }
    
    public void setText(final String pszText) {
        this.m_pszText = pszText;
    }
    
    public void setTextBounds(final int n, final int n2, final int n3, final int n4) {
        this.m_rcText.setBounds(n, n2, n3, n4);
    }
    
    public void setTextBounds(final Rectangle rcText) {
        this.m_rcText = rcText;
    }
    
    public void setTextHeight(final int height) {
        this.m_rcText.height = height;
    }
    
    public ListItem() {
        this.m_arrSubItems = new Vector();
        this.m_pszText = "";
        this.m_nImage = -1;
        this.m_nCY = -1;
        this.m_rcText = new Rectangle();
        this.m_rcIcon = new Rectangle();
        this.m_fonFont = null;
        this.m_bIsSelected = false;
        this.m_bIsDropHiLited = false;
        this.m_bIsFocused = false;
        this.m_bIsMarked = false;
        this.m_bIsDisabled = false;
    }
}
