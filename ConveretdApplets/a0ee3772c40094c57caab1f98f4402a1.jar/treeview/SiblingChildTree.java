// 
// Decompiled by Procyon v0.5.30
// 

package treeview;

public class SiblingChildTree
{
    protected SiblingChildTree parent;
    protected SiblingChildTree sibling_left;
    protected SiblingChildTree sibling_right;
    protected SiblingChildTree child;
    
    public SiblingChildTree getChild() {
        return this.child;
    }
    
    public SiblingChildTree pruneThisSubtree() {
        if (this.sibling_left != null) {
            this.sibling_left.sibling_right = this.sibling_right;
            if (this.sibling_right != null) {
                this.sibling_right.sibling_left = this.sibling_left;
            }
        }
        else {
            if (this.sibling_right != null) {
                this.sibling_right.sibling_left = null;
            }
            if (this.parent != null) {
                this.parent.child = this.sibling_right;
            }
        }
        SiblingChildTree siblingChildTree;
        for (siblingChildTree = this; siblingChildTree.parent != null; siblingChildTree = siblingChildTree.parent) {}
        while (siblingChildTree.sibling_left != null) {
            siblingChildTree = siblingChildTree.sibling_left;
        }
        if (siblingChildTree == this) {
            siblingChildTree = this.sibling_right;
        }
        this.parent = null;
        this.sibling_left = null;
        this.sibling_right = null;
        return siblingChildTree;
    }
    
    public SiblingChildTree() {
        this.parent = null;
        this.sibling_left = null;
        this.sibling_right = null;
        this.child = null;
    }
    
    protected void setParent(final SiblingChildTree siblingChildTree) {
        this.parent = siblingChildTree;
        if (this.sibling_right != null) {
            this.sibling_right.setParent(siblingChildTree);
        }
    }
    
    public SiblingChildTree getParent() {
        return this.parent;
    }
    
    public SiblingChildTree nextNode() {
        if (this.child != null) {
            return this.child;
        }
        if (this.sibling_right != null) {
            return this.sibling_right;
        }
        for (SiblingChildTree siblingChildTree = this.parent; siblingChildTree != null; siblingChildTree = siblingChildTree.parent) {
            if (siblingChildTree.sibling_right != null) {
                return siblingChildTree.sibling_right;
            }
        }
        return null;
    }
    
    public SiblingChildTree getSibling() {
        return this.sibling_right;
    }
    
    public SiblingChildTree prevNode() {
        if (this.sibling_left != null) {
            SiblingChildTree siblingChildTree = this.sibling_left.child;
            if (siblingChildTree == null) {
                return this.sibling_left;
            }
            while (siblingChildTree.sibling_right != null) {
                siblingChildTree = siblingChildTree.sibling_right;
            }
            return this.lastBeforeMatch(siblingChildTree);
        }
        else {
            if (this.parent != null) {
                return this.lastBeforeMatch(this.parent);
            }
            return null;
        }
    }
    
    private SiblingChildTree lastBeforeMatch(SiblingChildTree siblingChildTree) {
        SiblingChildTree nextNode = null;
        do {
            if (nextNode != null) {
                siblingChildTree = nextNode;
            }
            nextNode = siblingChildTree.nextNode();
        } while (nextNode != this);
        return siblingChildTree;
    }
    
    public SiblingChildTree[] getChildren() {
        final SiblingChildTree[] array = new SiblingChildTree[this.numberOfChildren()];
        SiblingChildTree siblingChildTree = this.child;
        for (int i = 0; i < array.length; ++i) {
            array[i] = siblingChildTree;
            siblingChildTree = siblingChildTree.sibling_right;
        }
        return array;
    }
    
    public int numberOfChildren() {
        int n = 0;
        for (SiblingChildTree siblingChildTree = this.child; siblingChildTree != null; siblingChildTree = siblingChildTree.sibling_right) {
            ++n;
        }
        return n;
    }
    
    public SiblingChildTree pruneChildren() {
        if (this.child == null) {
            return null;
        }
        this.child.setParent(null);
        final SiblingChildTree child = this.child;
        this.child = null;
        return child;
    }
    
    public boolean isSibling(final SiblingChildTree siblingChildTree) {
        if (this == siblingChildTree) {
            return false;
        }
        SiblingChildTree siblingChildTree2;
        for (siblingChildTree2 = this; siblingChildTree2.sibling_left != null; siblingChildTree2 = siblingChildTree2.sibling_left) {}
        while (siblingChildTree2 != null) {
            if (siblingChildTree2 == siblingChildTree) {
                return true;
            }
            siblingChildTree2 = siblingChildTree2.sibling_right;
        }
        return false;
    }
    
    public void addChild(final SiblingChildTree child) {
        if (child == null) {
            throw new IllegalArgumentException("SiblingChildTree.addChild(): child is a null reference");
        }
        if (this.child == null) {
            (this.child = child).setParent(this);
            return;
        }
        this.child.addSibling(child);
    }
    
    public SiblingChildTree getSiblingLeft() {
        return this.sibling_left;
    }
    
    public void addSibling(final SiblingChildTree sibling_right) {
        if (sibling_right == null) {
            throw new IllegalArgumentException("SiblingChildTree.addSibling(): sibling is a null reference");
        }
        if (this.sibling_right == null) {
            this.sibling_right = sibling_right;
            sibling_right.sibling_left = this;
            sibling_right.setParent(this.parent);
            return;
        }
        SiblingChildTree siblingChildTree;
        for (siblingChildTree = this.sibling_right; siblingChildTree.getSibling() != null; siblingChildTree = siblingChildTree.getSibling()) {}
        siblingChildTree.addSibling(sibling_right);
    }
}
