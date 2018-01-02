import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class TreeNode
{
    TreeNode sibling;
    TreeNode child;
    TreeNode parent;
    String text;
    Image collapsedImage;
    Image expandedImage;
    int depth;
    boolean isExpanded;
    int numberOfChildren;
    
    public TreeNode(final String text) {
        this(text, null, null);
    }
    
    public TreeNode(final String text, final Image collapsedImage, final Image expandedImage) {
        this.depth = -1;
        this.isExpanded = false;
        this.text = text;
        this.sibling = null;
        this.child = null;
        this.collapsedImage = collapsedImage;
        this.expandedImage = expandedImage;
        this.numberOfChildren = 0;
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
        }
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
    
    public void setExpandedImage(final Image image) {
        this.expandedImage = image;
    }
    
    public void setCollapsedImage(final Image image) {
        this.collapsedImage = image;
    }
    
    public String getText() {
        return this.text;
    }
    
    public void setText(final String s) {
        this.text = new String(s);
    }
}
