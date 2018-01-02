// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui.tree;

import java.util.Vector;
import com.objectbox.runner.gui.listbox.ListSubItem;
import com.objectbox.runner.gui.listbox.ListItem;
import java.awt.Font;

public class Node extends TreeNodeX
{
    boolean m_bVisible;
    String m_pszText;
    int m_nImage;
    int m_nExpandedImage;
    int m_nChildren;
    boolean m_bExpanded;
    Font m_fonFont;
    Object Data;
    ListItem m_itemParent;
    String m_type;
    static final long serialVersionUID = -123456789L;
    
    public Node() {
        this("", -1, -1);
    }
    
    public Node(final String pszText, final int nImage, final int nExpandedImage) {
        this.m_bVisible = false;
        this.m_pszText = null;
        this.m_nImage = -1;
        this.m_nExpandedImage = -1;
        this.m_nChildren = 0;
        this.m_bExpanded = false;
        this.m_fonFont = null;
        this.Data = null;
        this.m_type = "Folder";
        this.m_pszText = pszText;
        this.m_nImage = nImage;
        this.m_nExpandedImage = nExpandedImage;
    }
    
    public void addSubItem(final ListSubItem listSubItem) {
        ListItem listItem = this.getListItem();
        if (listItem == null) {
            listItem = new ListItem();
            this.bindToListItem(listItem);
        }
        listItem.addSubItem(listSubItem);
    }
    
    public void addSubItem(final String s) {
        this.addSubItem(new ListSubItem(s));
    }
    
    public void bindToListItem(final ListItem itemParent) {
        (this.m_itemParent = itemParent).setItemData(this);
    }
    
    public void expand(final boolean b) {
        if (b) {
            this.m_bExpanded = true;
        }
        else {
            this.m_bExpanded = false;
        }
    }
    
    public Object getData() {
        return this.Data;
    }
    
    public int getExpandedImage() {
        return this.m_nExpandedImage;
    }
    
    public Font getFont() {
        return this.m_fonFont;
    }
    
    public int getImage() {
        if (this.isExpanded()) {
            return this.m_nExpandedImage;
        }
        return this.m_nImage;
    }
    
    public ListItem getListItem() {
        return this.m_itemParent;
    }
    
    public Vector getSubItems() {
        final ListItem listItem = this.getListItem();
        if (listItem != null) {
            return listItem.getSubItems();
        }
        return null;
    }
    
    public String getText() {
        return this.m_pszText;
    }
    
    public String getType() {
        return this.m_type;
    }
    
    public boolean isExpanded() {
        return this.m_bExpanded;
    }
    
    public boolean isMultiSelectable() {
        return this.getType().compareTo("Folder") != 0;
    }
    
    boolean isVisible() {
        Node node = this;
        if (!this.m_bVisible) {
            return false;
        }
        while (node.getParent() != null) {
            node = (Node)node.getParent();
            if (!node.isExpanded() || !node.m_bVisible) {
                return false;
            }
        }
        return true;
    }
    
    public void setData(final Object data) {
        this.Data = data;
    }
    
    public void setExpandedImage(final int nExpandedImage) {
        this.m_nExpandedImage = nExpandedImage;
    }
    
    public void setFont(final Font fonFont) {
        this.m_fonFont = fonFont;
    }
    
    public void setImage(final int nImage) {
        this.m_nImage = nImage;
    }
    
    public void setText(final String pszText) {
        this.m_pszText = pszText;
    }
    
    public void setType(final String type) {
        this.m_type = type;
    }
    
    public void setVisible(final boolean bVisible) {
        this.m_bVisible = bVisible;
    }
    
    public String toString() {
        return "GnATT: " + super.toString();
    }
}
