// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.filter;

import prefuse.data.Node;
import prefuse.visual.EdgeItem;
import java.util.Iterator;
import prefuse.data.Tree;
import prefuse.visual.VisualItem;
import prefuse.visual.expression.InGroupPredicate;
import prefuse.util.PrefuseLib;
import prefuse.data.Graph;
import prefuse.Visualization;
import prefuse.visual.NodeItem;
import prefuse.data.expression.Predicate;
import prefuse.action.GroupAction;

public class FisheyeTreeFilter extends GroupAction
{
    private String m_sources;
    private Predicate m_groupP;
    private int m_threshold;
    private NodeItem m_root;
    private double m_divisor;
    
    public FisheyeTreeFilter(final String s) {
        this(s, 1);
    }
    
    public FisheyeTreeFilter(final String s, final int n) {
        this(s, Visualization.FOCUS_ITEMS, n);
    }
    
    public FisheyeTreeFilter(final String s, final String sources, final int n) {
        super(s);
        this.m_sources = sources;
        this.m_threshold = -n;
        this.m_groupP = new InGroupPredicate(PrefuseLib.getGroupName(s, Graph.NODES));
    }
    
    public int getDistance() {
        return -this.m_threshold;
    }
    
    public void setDistance(final int n) {
        this.m_threshold = -n;
    }
    
    public String getSources() {
        return this.m_sources;
    }
    
    public void setSources(final String sources) {
        this.m_sources = sources;
    }
    
    public void run(final double n) {
        final Tree spanningTree = ((Graph)this.m_vis.getGroup(this.m_group)).getSpanningTree();
        this.m_divisor = spanningTree.getNodeCount();
        this.m_root = (NodeItem)spanningTree.getRoot();
        final Iterator visibleItems = this.m_vis.visibleItems(this.m_group);
        while (visibleItems.hasNext()) {
            final VisualItem visualItem = visibleItems.next();
            visualItem.setDOI(-1.7976931348623157E308);
            visualItem.setExpanded(false);
        }
        final Iterator items = this.m_vis.items(this.m_sources, this.m_groupP);
        while (items.hasNext()) {
            this.visitFocus(items.next(), null);
        }
        this.visitFocus(this.m_root, null);
        final Iterator visibleItems2 = this.m_vis.visibleItems(this.m_group);
        while (visibleItems2.hasNext()) {
            final VisualItem visualItem2 = visibleItems2.next();
            if (visualItem2.getDOI() == -1.7976931348623157E308) {
                PrefuseLib.updateVisible(visualItem2, false);
            }
        }
    }
    
    private void visitFocus(final NodeItem nodeItem, final NodeItem nodeItem2) {
        if (nodeItem.getDOI() <= -1.0) {
            this.visit(nodeItem, nodeItem2, 0, 0);
            if (this.m_threshold < 0) {
                this.visitDescendants(nodeItem, nodeItem2);
            }
            this.visitAncestors(nodeItem);
        }
    }
    
    private void visit(final NodeItem nodeItem, final NodeItem nodeItem2, final int n, final int n2) {
        PrefuseLib.updateVisible(nodeItem, true);
        nodeItem.setDOI(n + -n2 / Math.min(1000.0, this.m_divisor));
        if (nodeItem2 != null) {
            final EdgeItem edgeItem = (EdgeItem)nodeItem2.getParentEdge();
            edgeItem.setDOI(nodeItem2.getDOI());
            PrefuseLib.updateVisible(edgeItem, true);
        }
    }
    
    private void visitAncestors(final NodeItem nodeItem) {
        if (nodeItem == this.m_root) {
            return;
        }
        this.visitFocus((NodeItem)nodeItem.getParent(), nodeItem);
    }
    
    private void visitDescendants(final NodeItem nodeItem, final NodeItem nodeItem2) {
        final int n = (nodeItem2 == null) ? 0 : nodeItem.getChildIndex(nodeItem2);
        final Iterator children = nodeItem.children();
        nodeItem.setExpanded(children.hasNext());
        int n2 = 0;
        while (children.hasNext()) {
            final NodeItem nodeItem3 = children.next();
            if (nodeItem3 != nodeItem2) {
                final int n3 = (int)(nodeItem.getDOI() - 1.0);
                this.visit(nodeItem3, nodeItem3, n3, Math.abs(n - n2));
                if (n3 > this.m_threshold) {
                    this.visitDescendants(nodeItem3, null);
                }
            }
            ++n2;
        }
    }
}
