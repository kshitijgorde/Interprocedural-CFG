// 
// Decompiled by Procyon v0.5.30
// 

package Go.strategy;

import java.util.Vector;

public class TreeNode implements TreeNodeInterface
{
    protected Object content;
    protected TreeNodeInterface parent;
    protected Vector children;
    
    public TreeNode(final Object nodeContent, final TreeNodeInterface parentNode) {
        this.content = nodeContent;
        this.parent = parentNode;
        this.children = new Vector();
    }
    
    public Object getContent() {
        return this.content;
    }
    
    public void setContent(final Object newContent) {
        this.content = newContent;
    }
    
    public TreeNodeInterface getParent() {
        return this.parent;
    }
    
    public void setParent(final TreeNodeInterface newParent) {
        this.parent = newParent;
    }
    
    public TreeNodeInterface getFirstChild() {
        return this.children.firstElement();
    }
    
    public Vector getChildren() {
        return this.children;
    }
    
    public TreeNodeInterface getChildNextTo(final TreeNodeInterface child) {
        final int index = this.children.indexOf(child);
        if (index >= 0 && index < this.children.size()) {
            return this.children.elementAt(index);
        }
        return null;
    }
    
    public boolean isLeaf() {
        return this.children.size() < 1;
    }
    
    public void addChild(final TreeNodeInterface child) {
        this.children.addElement(child);
        child.setParent(this);
    }
    
    public void removeChild(final TreeNodeInterface child) {
        this.children.removeElement(child);
    }
}
