// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.board;

import rene.util.list.Tree;

public class TreeNode extends Tree
{
    public TreeNode(final int n) {
        super(new Node(n));
    }
    
    public TreeNode(final Node node) {
        super(node);
    }
    
    public Node node() {
        return (Node)this.content();
    }
    
    public void setaction(final String s, final String s2, final boolean b) {
        this.node().setaction(s, s2, b);
    }
    
    public void setaction(final String s, final String s2) {
        this.node().setaction(s, s2);
    }
    
    public void addaction(final Action action) {
        this.node().addaction(action);
    }
    
    public String getaction(final String s) {
        return this.node().getaction(s);
    }
    
    public boolean isMain() {
        return this.node().main();
    }
    
    public boolean isLastMain() {
        return !this.haschildren() && this.isMain();
    }
    
    public void main(final boolean b) {
        this.node().main(b);
    }
    
    public TreeNode parentPos() {
        return (TreeNode)this.parent();
    }
    
    public TreeNode firstChild() {
        return (TreeNode)this.firstchild();
    }
    
    public TreeNode lastChild() {
        return (TreeNode)this.lastchild();
    }
}
