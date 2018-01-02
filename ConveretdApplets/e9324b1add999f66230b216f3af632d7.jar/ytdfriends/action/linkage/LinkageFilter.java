// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.action.linkage;

import java.awt.geom.Point2D;
import edu.berkeley.guir.prefuse.EdgeItem;
import java.util.Iterator;
import edu.berkeley.guir.prefuse.graph.Graph;
import edu.berkeley.guir.prefuse.focus.FocusSet;
import edu.berkeley.guir.prefuse.FocusManager;
import edu.berkeley.guir.prefuse.util.display.DisplayLib;
import edu.berkeley.guir.prefuse.graph.Edge;
import edu.berkeley.guir.prefuse.VisualItem;
import ytdfriends.FriendsLib;
import edu.berkeley.guir.prefuse.graph.Entity;
import edu.berkeley.guir.prefuse.ItemRegistry;
import edu.berkeley.guir.prefuse.NodeItem;
import java.util.ArrayList;
import java.util.HashSet;
import edu.berkeley.guir.prefuse.graph.Node;
import java.util.HashMap;
import edu.berkeley.guir.prefuse.action.filter.Filter;

public class LinkageFilter extends Filter
{
    private static final String[] types;
    private HashMap<Node, int[]> counts;
    private HashSet<Node> keepers;
    private ArrayList<NodeItem> newlyFiltered;
    private boolean includeOthers;
    
    static {
        types = new String[] { "node", "edge" };
    }
    
    public LinkageFilter() {
        this(false);
    }
    
    public LinkageFilter(final boolean gc) {
        super(LinkageFilter.types, gc);
        this.counts = new HashMap<Node, int[]>();
        this.keepers = new HashSet<Node>();
        this.newlyFiltered = new ArrayList<NodeItem>();
        this.includeOthers = true;
    }
    
    public void run(final ItemRegistry registry, final double frac) {
        final FocusManager fman = registry.getFocusManager();
        final FocusSet focusSet = fman.getFocusSet((Object)"clicked");
        if (focusSet.size() < 2) {
            return;
        }
        Node clickNode = null;
        final Graph fg = registry.getFilteredGraph();
        for (final Node n : focusSet) {
            if (clickNode == null) {
                clickNode = n;
            }
            this.keepers.add(n);
            final Iterator<Node> niter = (Iterator<Node>)n.getNeighbors();
            while (niter.hasNext()) {
                final Node nn = niter.next();
                int[] count = this.counts.get(nn);
                if (count == null) {
                    count = new int[] { 1 };
                    this.counts.put(nn, count);
                }
                else {
                    final int[] array = count;
                    final int n4 = 0;
                    ++array[n4];
                }
            }
        }
        for (final Node n2 : this.counts.keySet()) {
            final int[] count2 = this.counts.get(n2);
            if (count2[0] > 1) {
                this.keepers.add(n2);
            }
        }
        final NodeItem clickItem = registry.getNodeItem(clickNode);
        final double size = clickItem.getSize();
        for (final Node n3 : this.keepers) {
            NodeItem item = registry.getNodeItem(n3, false);
            if (item == null) {
                item = registry.getNodeItem(n3, true);
                this.newlyFiltered.add(item);
            }
            item.setSize(size);
            item.touch();
            item.setVisible(true);
            final int hval = focusSet.contains((Entity)n3) ? 0 : 1;
            FriendsLib.setHighlightValue((VisualItem)item, hval);
            item.setHighlighted(true);
            final VisualItem aitem = (VisualItem)registry.getAggregateItem((Entity)n3);
            if (aitem != null) {
                aitem.setHighlighted(true);
                registry.touch(aitem.getItemClass());
            }
            final Iterator<Edge> eiter = (Iterator<Edge>)n3.getEdges();
            while (eiter.hasNext()) {
                final Edge e = eiter.next();
                final Node nn2 = e.getAdjacentNode(n3);
                if (this.keepers.contains(nn2)) {
                    NodeItem nitem = registry.getNodeItem(nn2, false);
                    if (nitem == null) {
                        nitem = registry.getNodeItem(nn2, true);
                        this.newlyFiltered.add(nitem);
                    }
                    EdgeItem eitem = registry.getEdgeItem(e, false);
                    if (eitem == null) {
                        eitem = registry.getEdgeItem(e, true);
                        fg.addEdge((Edge)eitem);
                    }
                    eitem.touch();
                    eitem.setVisible(true);
                    FriendsLib.setHighlightValue((VisualItem)eitem, 1);
                    eitem.setHighlighted(true);
                }
                else {
                    if (!this.includeOthers || registry.getNodeItem(nn2) == null || registry.getEdgeItem(e, false) != null) {
                        continue;
                    }
                    final EdgeItem eitem2 = registry.getEdgeItem(e, true);
                    FriendsLib.setHighlightValue((VisualItem)eitem2, -1);
                    fg.addEdge((Edge)eitem2);
                }
            }
        }
        for (final NodeItem nitem2 : this.newlyFiltered) {
            final Point2D loc = nitem2.getLocation();
            DisplayLib.getCentroid(registry, nitem2.getNeighbors(), loc);
            nitem2.updateLocation(loc.getX(), loc.getY());
        }
        this.counts.clear();
        this.keepers.clear();
        this.newlyFiltered.clear();
        super.run(registry, frac);
    }
}
