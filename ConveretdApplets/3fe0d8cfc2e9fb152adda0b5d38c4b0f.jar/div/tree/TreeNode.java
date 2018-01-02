// 
// Decompiled by Procyon v0.5.30
// 

package div.tree;

import java.awt.Image;

public class TreeNode
{
    TreeNode sibling;
    TreeNode child;
    TreeNode parent;
    String key;
    String state;
    String parentKey;
    String text;
    Image collapsedImage;
    Image expandedImage;
    int depth;
    boolean isExpanded;
    int numberOfChildren;
    
    public void setText(final String s) {
        this.text = new String(s);
    }
    
    public Image getExpandedImage() {
        return this.expandedImage;
    }
    
    public void setExpandedImage(final Image expandedImage) {
        this.expandedImage = expandedImage;
    }
    
    public int getDepth() {
        return this.depth;
    }
    
    void setDepth(final int depth) {
        this.depth = depth;
    }
    
    public TreeNode(final String s, final String s2, final String s3, final String s4) {
        this(s, s2, s3, s4, null, null);
    }
    
    public TreeNode(final String text, final String key, final String state, final String parentKey, final Image collapsedImage, final Image expandedImage) {
        this.depth = -1;
        this.text = text;
        this.key = key;
        this.state = state;
        this.parentKey = parentKey;
        this.sibling = null;
        this.child = null;
        this.collapsedImage = collapsedImage;
        this.expandedImage = expandedImage;
        this.numberOfChildren = 0;
    }
    
    public void expand() {
        System.out.println("expand(): " + this.text);
        if (this.isExpandable()) {
            this.isExpanded = true;
        }
    }
    
    public boolean isExpanded() {
        return this.isExpanded;
    }
    
    public String toString() {
        return "" + this.text + " key: " + this.key + " have parent: " + (this.parent != null) + " have sibling: " + (this.sibling != null) + " have child: " + (this.child != null);
    }
    
    public void setValues(final String text, final String state, final String parentKey, final Image collapsedImage, final Image expandedImage) {
        this.text = text;
        this.state = state;
        this.parentKey = parentKey;
        this.collapsedImage = collapsedImage;
        this.expandedImage = expandedImage;
    }
    
    public void collapse() {
        this.isExpanded = false;
    }
    
    public void toggle() {
        if (this.isExpanded) {
            this.collapse();
            return;
        }
        if (this.isExpandable()) {
            this.expand();
        }
    }
    
    public Image getImage() {
        if (this.isExpanded && this.expandedImage != null) {
            return this.expandedImage;
        }
        return this.collapsedImage;
    }
    
    public String getKey() {
        return this.key;
    }
    
    public void setCollapsedImage(final Image collapsedImage) {
        this.collapsedImage = collapsedImage;
    }
    
    public void setKey(final String s) {
        this.key = new String(s);
    }
    
    public String getText() {
        return this.text;
    }
    
    public boolean isExpandable() {
        return this.child != null;
    }
}
