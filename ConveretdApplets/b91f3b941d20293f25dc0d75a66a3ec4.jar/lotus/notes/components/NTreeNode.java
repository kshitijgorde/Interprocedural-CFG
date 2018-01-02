// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.components;

import java.awt.Image;

public class NTreeNode
{
    NTreeNode sibling;
    NTreeNode child;
    NTreeNode parent;
    String text;
    transient Image collapsedImage;
    transient Image expandedImage;
    int numberOfChildren;
    Object dataObject;
    NTreeView treeView;
    int depth;
    boolean isExpanded;
    
    public NTreeNode() {
        this.depth = -1;
        this.isExpanded = false;
    }
    
    public NTreeNode(final String s) {
        this(s, null, null, null);
    }
    
    public NTreeNode(final String s, final Image image, final Image image2) {
        this(s, image, image2, null);
    }
    
    public NTreeNode(final String s, final NTreeView nTreeView) {
        this(s, null, null, nTreeView);
    }
    
    public NTreeNode(final String text, final Image collapsedImage, final Image expandedImage, final NTreeView treeView) {
        this.depth = -1;
        this.isExpanded = false;
        this.text = text;
        this.sibling = null;
        this.child = null;
        this.collapsedImage = collapsedImage;
        this.expandedImage = expandedImage;
        this.numberOfChildren = 0;
        this.dataObject = null;
        this.treeView = treeView;
    }
    
    void setDepth(final int depth) {
        this.depth = depth;
    }
    
    public int getDepth() {
        return this.depth;
    }
    
    public boolean isExpanded() {
        return this.isExpanded;
    }
    
    public boolean isExpandable() {
        return this.child != null;
    }
    
    public void expand() {
        if (this.isExpandable()) {
            this.isExpanded = true;
            if (this.treeView != null) {
                this.treeView.triggerRedraw();
            }
        }
    }
    
    public void collapse() {
        this.isExpanded = false;
        if (this.treeView != null) {
            this.treeView.triggerRedraw();
        }
    }
    
    public void toggle() {
        if (this.isExpanded) {
            this.collapse();
        }
        else if (this.isExpandable()) {
            this.expand();
        }
    }
    
    public Image getImage() {
        return (this.isExpanded && this.expandedImage != null) ? this.expandedImage : this.collapsedImage;
    }
    
    public void setExpandedImage(final Image expandedImage) {
        this.expandedImage = expandedImage;
        if (this.isExpanded() && this.treeView != null) {
            this.treeView.triggerRedraw();
        }
    }
    
    public void setCollapsedImage(final Image collapsedImage) {
        this.collapsedImage = collapsedImage;
        if (!this.isExpanded() && this.treeView != null) {
            this.treeView.triggerRedraw();
        }
    }
    
    public String getText() {
        return this.text;
    }
    
    public void setText(final String s) {
        this.text = new String(s);
        if (this.treeView != null) {
            this.treeView.triggerRedraw();
        }
    }
    
    public Object getDataObject() {
        return this.dataObject;
    }
    
    public void setDataObject(final Object dataObject) {
        this.dataObject = dataObject;
    }
    
    public NTreeNode getParent() {
        return this.parent;
    }
    
    public NTreeNode getChild() {
        return this.child;
    }
    
    public NTreeNode getSibling() {
        return this.sibling;
    }
}
