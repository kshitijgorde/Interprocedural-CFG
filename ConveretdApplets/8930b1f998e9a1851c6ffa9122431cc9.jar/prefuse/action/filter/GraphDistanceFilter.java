// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.filter;

import java.util.Iterator;
import prefuse.data.Tuple;
import prefuse.data.util.FilterIterator;
import prefuse.visual.VisualItem;
import prefuse.visual.expression.InGroupPredicate;
import prefuse.util.PrefuseLib;
import prefuse.data.Graph;
import prefuse.Visualization;
import prefuse.data.util.BreadthFirstIterator;
import prefuse.data.expression.Predicate;
import prefuse.action.GroupAction;

public class GraphDistanceFilter extends GroupAction
{
    protected int m_distance;
    protected String m_sources;
    protected Predicate m_groupP;
    protected BreadthFirstIterator m_bfs;
    
    public GraphDistanceFilter(final String s) {
        this(s, 1);
    }
    
    public GraphDistanceFilter(final String s, final int n) {
        this(s, Visualization.FOCUS_ITEMS, n);
    }
    
    public GraphDistanceFilter(final String s, final String sources, final int distance) {
        super(s);
        this.m_sources = sources;
        this.m_distance = distance;
        this.m_groupP = new InGroupPredicate(PrefuseLib.getGroupName(s, Graph.NODES));
        this.m_bfs = new BreadthFirstIterator();
    }
    
    public int getDistance() {
        return this.m_distance;
    }
    
    public void setDistance(final int distance) {
        this.m_distance = distance;
    }
    
    public String getSources() {
        return this.m_sources;
    }
    
    public void setSources(final String sources) {
        this.m_sources = sources;
    }
    
    public void run(final double n) {
        final Iterator visibleItems = this.m_vis.visibleItems(this.m_group);
        while (visibleItems.hasNext()) {
            visibleItems.next().setDOI(-1.7976931348623157E308);
        }
        this.m_bfs.init(new FilterIterator(this.m_vis.getGroup(this.m_sources).tuples(), this.m_groupP), this.m_distance, 2);
        while (this.m_bfs.hasNext()) {
            final VisualItem visualItem = (VisualItem)this.m_bfs.next();
            final int depth = this.m_bfs.getDepth(visualItem);
            PrefuseLib.updateVisible(visualItem, true);
            visualItem.setDOI(-depth);
            visualItem.setExpanded(depth < this.m_distance);
        }
        final Iterator visibleItems2 = this.m_vis.visibleItems(this.m_group);
        while (visibleItems2.hasNext()) {
            final VisualItem visualItem2 = visibleItems2.next();
            if (visualItem2.getDOI() == -1.7976931348623157E308) {
                PrefuseLib.updateVisible(visualItem2, false);
                visualItem2.setExpanded(false);
            }
        }
    }
}
