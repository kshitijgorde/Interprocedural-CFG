// 
// Decompiled by Procyon v0.5.30
// 

package Go.strategy;

import java.util.Vector;

public interface TreeNodeInterface
{
    Object getContent();
    
    void setContent(final Object p0);
    
    TreeNodeInterface getParent();
    
    void setParent(final TreeNodeInterface p0);
    
    TreeNodeInterface getFirstChild();
    
    Vector getChildren();
    
    TreeNodeInterface getChildNextTo(final TreeNodeInterface p0);
    
    boolean isLeaf();
    
    void addChild(final TreeNodeInterface p0);
    
    void removeChild(final TreeNodeInterface p0);
}
