// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui.tree;

public class TreeNodeX extends TreeNode
{
    int m_root_nFilterLevel;
    
    public int getFilterLevel() {
        return ((TreeNodeX)this.getRoot()).m_root_nFilterLevel;
    }
    
    public TreeNode getFirstChild() {
        if (super.m_pFirstChild != null && this.getFilterLevel() > 0 && this.getUnfilteredDistanceFromRoot() == this.getFilterLevel() - 1) {
            return super.m_pFirstChild.m_pFirstChild;
        }
        return super.m_pFirstChild;
    }
    
    public TreeNode getNextSibling() {
        if (this.getFilterLevel() <= 0 || this.getUnfilteredDistanceFromRoot() != this.getFilterLevel() + 1 || super.m_pNextSibling != null) {
            return super.m_pNextSibling;
        }
        final TreeNode pParent = super.m_pParent;
        if (pParent == null) {
            return null;
        }
        final TreeNode pNextSibling = pParent.m_pNextSibling;
        if (pNextSibling == null) {
            return null;
        }
        return pNextSibling.m_pFirstChild;
    }
    
    public TreeNode getParent() {
        if (this.getFilterLevel() > 0 && super.m_pParent != null && this.getUnfilteredDistanceFromRoot() == this.getFilterLevel() + 1) {
            return super.m_pParent.m_pParent;
        }
        return super.m_pParent;
    }
    
    public TreeNode getPrevSibling() {
        if (this.getFilterLevel() <= 0 || this.getUnfilteredDistanceFromRoot() != this.getFilterLevel() + 1 || super.m_pPrevSibling != null) {
            return super.m_pPrevSibling;
        }
        final TreeNode pParent = super.m_pParent;
        if (pParent == null) {
            return null;
        }
        final TreeNode pPrevSibling = pParent.m_pPrevSibling;
        if (pPrevSibling == null) {
            return null;
        }
        TreeNode treeNode = pPrevSibling.m_pFirstChild;
        if (treeNode == null) {
            return null;
        }
        while (treeNode.m_pNextSibling != null) {
            treeNode = treeNode.m_pNextSibling;
        }
        return treeNode;
    }
    
    public int getUnfilteredDistanceFromRoot() {
        TreeNode treeNode;
        int n;
        for (treeNode = super.m_pParent, n = 0; treeNode != null; treeNode = treeNode.m_pParent, ++n) {}
        return n;
    }
    
    public boolean setFilterLevel(final int root_nFilterLevel) {
        ((TreeNodeX)this.getRoot()).m_root_nFilterLevel = root_nFilterLevel;
        return true;
    }
    
    public TreeNodeX() {
        this.m_root_nFilterLevel = 0;
    }
}
