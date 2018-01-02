// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.tree;

import java.util.Enumeration;

public interface TreeNode
{
    Enumeration children();
    
    boolean getAllowsChildren();
    
    TreeNode getChildAt(final int p0);
    
    int getChildCount();
    
    int getIndex(final TreeNode p0);
    
    TreeNode getParent();
    
    boolean isLeaf();
}
