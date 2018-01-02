// 
// Decompiled by Procyon v0.5.30
// 

package Go.strategy;

public interface GameTreeInterface
{
    TreeNodeInterface getRoot();
    
    TreeNodeInterface getNextSiblingOf(final TreeNodeInterface p0);
    
    TreeNodeInterface getFirstChildOf(final TreeNodeInterface p0);
    
    TreeNodeInterface getParent(final TreeNodeInterface p0);
    
    boolean isLeaf(final TreeNodeInterface p0);
    
    void add_ChildTo(final TreeNodeInterface p0, final TreeNodeInterface p1);
    
    void remove_ChildFrom(final TreeNodeInterface p0, final TreeNodeInterface p1);
}
