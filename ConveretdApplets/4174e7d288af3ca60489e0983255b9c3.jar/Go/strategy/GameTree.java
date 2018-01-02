// 
// Decompiled by Procyon v0.5.30
// 

package Go.strategy;

public class GameTree implements GameTreeInterface
{
    protected TreeNodeInterface root;
    
    public GameTree(final Object rootObject) {
        this.root = new TreeNode(rootObject, null);
    }
    
    public TreeNodeInterface getRoot() {
        return this.root;
    }
    
    public TreeNodeInterface getNextSiblingOf(final TreeNodeInterface node) {
        return node.getParent().getChildNextTo(node);
    }
    
    public TreeNodeInterface getFirstChildOf(final TreeNodeInterface parentNode) {
        return parentNode.getFirstChild();
    }
    
    public TreeNodeInterface getParent(final TreeNodeInterface node) {
        return node.getParent();
    }
    
    public boolean isLeaf(final TreeNodeInterface node) {
        return node.isLeaf();
    }
    
    public void add_ChildTo(final TreeNodeInterface child, final TreeNodeInterface parent) {
        parent.addChild(child);
    }
    
    public void remove_ChildFrom(final TreeNodeInterface child, final TreeNodeInterface parent) {
        parent.removeChild(child);
    }
}
