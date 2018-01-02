// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.layout.graph;

import prefuse.data.tuple.TupleSet;
import prefuse.data.Graph;
import prefuse.visual.NodeItem;
import prefuse.action.layout.Layout;

public abstract class TreeLayout extends Layout
{
    protected NodeItem m_root;
    
    public TreeLayout() {
    }
    
    public TreeLayout(final String s) {
        super(s);
    }
    
    public void setLayoutRoot(final NodeItem root) {
        if (!root.isInGroup(this.m_group)) {
            throw new IllegalArgumentException("Input node is not a member of this layout's data group");
        }
        this.m_root = root;
    }
    
    public NodeItem getLayoutRoot() {
        if (this.m_root != null) {
            return this.m_root;
        }
        final TupleSet group = this.m_vis.getGroup(this.m_group);
        if (group instanceof Graph) {
            return (NodeItem)((Graph)group).getSpanningTree().getRoot();
        }
        throw new IllegalStateException("This action's data group doesnot resolve to a Graph instance.");
    }
}
