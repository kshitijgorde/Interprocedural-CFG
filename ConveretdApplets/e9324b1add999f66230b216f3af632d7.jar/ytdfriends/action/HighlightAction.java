// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.action;

import edu.berkeley.guir.prefuse.EdgeItem;
import java.util.Iterator;
import edu.berkeley.guir.prefuse.focus.FocusSet;
import edu.berkeley.guir.prefuse.FocusManager;
import edu.berkeley.guir.prefuse.NodeItem;
import edu.berkeley.guir.prefuse.graph.Edge;
import edu.berkeley.guir.prefuse.graph.Entity;
import ytdfriends.FriendsLib;
import edu.berkeley.guir.prefuse.VisualItem;
import edu.berkeley.guir.prefuse.ItemRegistry;
import java.util.HashSet;
import edu.berkeley.guir.prefuse.graph.Node;
import java.util.ArrayList;
import edu.berkeley.guir.prefuse.action.AbstractAction;

public class HighlightAction extends AbstractAction
{
    private ArrayList<Node> queue;
    private ArrayList<Node> queue2;
    private HashSet<Node> visited;
    private int hops;
    private boolean showEdges;
    private boolean skipFoci;
    
    public HighlightAction() {
        this(2);
    }
    
    public HighlightAction(final int hops) {
        this(2, true, true);
    }
    
    public HighlightAction(final int hops, final boolean showEdges, final boolean skipFoci) {
        this.queue = new ArrayList<Node>();
        this.queue2 = new ArrayList<Node>();
        this.visited = new HashSet<Node>();
        this.hops = 2;
        this.hops = hops;
        this.showEdges = showEdges;
        this.skipFoci = skipFoci;
    }
    
    public void run(final ItemRegistry registry, final double frac) {
        Node nn = null;
        NodeItem n = null;
        final FocusManager fman = registry.getFocusManager();
        final FocusSet highlight = fman.getFocusSet((Object)"highlight");
        final FocusSet mouseover = fman.getFocusSet((Object)"moused");
        final FocusSet search = fman.getFocusSet((Object)"search");
        synchronized (highlight) {
            // monitorenter(set = mouseover)
            try {
                final FocusSet fs = (highlight.size() > 0) ? highlight : mouseover;
                if (fs.size() > 0) {
                    nn = fs.iterator().next();
                    n = registry.getNodeItem(nn);
                }
            }
            // monitorexit(set)
            finally {}
        }
        // monitorexit(highlight)
        final boolean restore = n == null && search.size() == 0;
        final int defaultValue = (restore || search.size() == 0) ? 0 : -1;
        Iterator<NodeItem> iter = (Iterator<NodeItem>)registry.getNodeItems();
        while (iter.hasNext()) {
            final VisualItem item = (VisualItem)iter.next();
            item.setHighlighted(false);
            FriendsLib.setHighlightValue(item, defaultValue);
        }
        iter = (Iterator<NodeItem>)registry.getEdgeItems();
        while (iter.hasNext()) {
            final VisualItem item = (VisualItem)iter.next();
            item.setHighlighted(false);
            FriendsLib.setHighlightValue(item, defaultValue);
        }
        iter = (Iterator<NodeItem>)registry.getAggregateItems();
        while (iter.hasNext()) {
            final VisualItem item = (VisualItem)iter.next();
            item.setHighlighted(restore);
        }
        if (restore) {
            return;
        }
        if (n == null) {
            iter = (Iterator<NodeItem>)search.iterator();
            while (iter.hasNext()) {
                final Node node = (Node)iter.next();
                final NodeItem nitem = registry.getNodeItem(node);
                FriendsLib.setHighlightValue((VisualItem)nitem, 0);
                final VisualItem aitem = (VisualItem)registry.getAggregateItem((Entity)node);
                if (aitem != null) {
                    aitem.setHighlighted(true);
                    registry.touch(aitem.getItemClass());
                }
            }
            return;
        }
        final FocusSet focusSet = registry.getDefaultFocusSet();
        FriendsLib.setHighlightValue((VisualItem)n, 0);
        n.setHighlighted(true);
        this.queue.clear();
        this.queue2.clear();
        this.visited.clear();
        this.queue.add(nn);
        this.visited.add(nn);
        for (int depth = 1; depth <= this.hops; ++depth) {
            for (int i = this.queue.size() - 1; i >= 0; --i) {
                final Node node2 = this.queue.remove(i);
                if (!this.skipFoci || depth <= 1 || !focusSet.contains((Entity)node2)) {
                    iter = (Iterator<NodeItem>)node2.getEdges();
                    while (iter.hasNext()) {
                        final Edge edge = (Edge)iter.next();
                        final Node nnode = edge.getAdjacentNode(node2);
                        final EdgeItem eitem = registry.getEdgeItem(edge);
                        final NodeItem nitem2 = registry.getNodeItem(nnode);
                        final boolean visit = !this.visited.contains(nnode);
                        if (visit && nitem2 != null && nitem2.isVisible()) {
                            nitem2.setHighlighted(true);
                            FriendsLib.setHighlightValue((VisualItem)nitem2, depth);
                            final VisualItem aitem2 = (VisualItem)registry.getAggregateItem(nitem2.getEntity());
                            if (aitem2 != null) {
                                aitem2.setHighlighted(true);
                                registry.touch(aitem2.getItemClass());
                            }
                            registry.touch(nitem2.getItemClass());
                        }
                        if (visit && eitem != null && eitem.isVisible()) {
                            if (this.showEdges) {
                                eitem.setHighlighted(true);
                                FriendsLib.setHighlightValue((VisualItem)eitem, depth);
                                registry.touch(eitem.getItemClass());
                            }
                            else {
                                FriendsLib.setHighlightValue((VisualItem)eitem, 0);
                            }
                        }
                        if (visit && depth < this.hops) {
                            this.queue2.add(nnode);
                            this.visited.add(nnode);
                        }
                    }
                }
            }
            final ArrayList<Node> tmp = this.queue;
            this.queue = this.queue2;
            this.queue2 = tmp;
        }
    }
    
    public int getHops() {
        return this.hops;
    }
    
    public void setHops(final int hops) {
        if (hops < 0 || hops > 2) {
            throw new IllegalArgumentException("Hops value must be between 0 and 2");
        }
        this.hops = hops;
    }
    
    public boolean isShowEdges() {
        return this.showEdges;
    }
    
    public void setShowEdges(final boolean showEdges) {
        this.showEdges = showEdges;
    }
    
    public boolean isSkipFoci() {
        return this.skipFoci;
    }
    
    public void setSkipFoci(final boolean skipFoci) {
        this.skipFoci = skipFoci;
    }
}
