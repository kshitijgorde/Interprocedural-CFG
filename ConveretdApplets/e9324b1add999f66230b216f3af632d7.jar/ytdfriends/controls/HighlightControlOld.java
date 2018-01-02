// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.controls;

import edu.berkeley.guir.prefuse.EdgeItem;
import edu.berkeley.guir.prefuse.focus.FocusSet;
import edu.berkeley.guir.prefuse.graph.Edge;
import edu.berkeley.guir.prefuse.graph.Entity;
import java.util.HashSet;
import java.util.ArrayList;
import edu.berkeley.guir.prefuse.graph.Node;
import java.util.Iterator;
import edu.berkeley.guir.prefuse.ItemRegistry;
import ytdfriends.FriendsLib;
import edu.berkeley.guir.prefuse.NodeItem;
import java.awt.event.MouseEvent;
import edu.berkeley.guir.prefuse.VisualItem;
import edu.berkeley.guir.prefuse.activity.Activity;
import edu.berkeley.guir.prefuse.event.ControlAdapter;

public class HighlightControlOld extends ControlAdapter
{
    private Activity update;
    private boolean highlightWithInvisibleEdge;
    private int hops;
    private boolean enabled;
    
    public HighlightControlOld(final int hops) {
        this(hops, null);
    }
    
    public HighlightControlOld(final int hops, final Activity update) {
        this.update = null;
        this.highlightWithInvisibleEdge = false;
        this.hops = 1;
        this.enabled = true;
        this.hops = hops;
        this.update = update;
    }
    
    public void itemEntered(final VisualItem item, final MouseEvent e) {
        if (this.enabled && item instanceof NodeItem && item.getItemRegistry() != null) {
            this.setNeighborHighlight((NodeItem)item, true);
        }
    }
    
    public void itemExited(final VisualItem item, final MouseEvent e) {
        if (this.enabled && item instanceof NodeItem && item.getItemRegistry() != null) {
            this.clearHighlight(item);
        }
    }
    
    public void clearHighlight(final VisualItem src) {
        final ItemRegistry registry = src.getItemRegistry();
        final int defaultValue = 0;
        Iterator iter = registry.getNodeItems();
        while (iter.hasNext()) {
            final VisualItem item = iter.next();
            item.setHighlighted(false);
            FriendsLib.setHighlightValue(item, defaultValue);
        }
        iter = registry.getEdgeItems();
        while (iter.hasNext()) {
            final VisualItem item = iter.next();
            item.setHighlighted(false);
            FriendsLib.setHighlightValue(item, defaultValue);
        }
        if (this.update != null) {
            this.update.runNow();
        }
    }
    
    public void setNeighborHighlight(final NodeItem n, final boolean state) {
        final ItemRegistry registry = n.getItemRegistry();
        final int defaultValue = state ? -1 : 0;
        Iterator<NodeItem> iter = (Iterator<NodeItem>)registry.getNodeItems();
        while (iter.hasNext()) {
            FriendsLib.setHighlightValue((VisualItem)iter.next(), defaultValue);
        }
        iter = (Iterator<NodeItem>)registry.getEdgeItems();
        while (iter.hasNext()) {
            FriendsLib.setHighlightValue((VisualItem)iter.next(), defaultValue);
        }
        final Node nn = (Node)n.getEntity();
        final FocusSet focusSet = registry.getDefaultFocusSet();
        synchronized (registry) {
            FriendsLib.setHighlightValue((VisualItem)n, 0);
            n.setHighlighted(true);
            ArrayList<Node> queue = new ArrayList<Node>();
            ArrayList<Node> queue2 = new ArrayList<Node>();
            final HashSet<Node> visited = new HashSet<Node>();
            queue.add(nn);
            visited.add(nn);
            for (int depth = 1; depth <= this.hops; ++depth) {
                for (int i = queue.size() - 1; i >= 0; --i) {
                    final Node node = queue.remove(i);
                    if (depth <= 1 || !focusSet.contains((Entity)node)) {
                        iter = (Iterator<NodeItem>)node.getEdges();
                        while (iter.hasNext()) {
                            final Edge edge = (Edge)iter.next();
                            final Node nnode = edge.getAdjacentNode(node);
                            final EdgeItem eitem = registry.getEdgeItem(edge);
                            final NodeItem nitem = registry.getNodeItem(nnode);
                            final boolean visit = !visited.contains(nnode);
                            if (visit && nitem != null && nitem.isVisible()) {
                                nitem.setHighlighted(state);
                                FriendsLib.setHighlightValue((VisualItem)nitem, depth);
                                registry.touch(nitem.getItemClass());
                            }
                            if (visit && eitem != null && eitem.isVisible()) {
                                eitem.setHighlighted(state);
                                FriendsLib.setHighlightValue((VisualItem)eitem, depth);
                                registry.touch(eitem.getItemClass());
                            }
                            if (visit && depth < this.hops) {
                                queue2.add(nnode);
                                visited.add(nnode);
                            }
                        }
                    }
                }
                final ArrayList<Node> tmp = queue;
                queue = queue2;
                queue2 = tmp;
            }
        }
        // monitorexit(registry)
        if (this.update != null) {
            this.update.runNow();
        }
    }
    
    public boolean isHighlightWithInvisibleEdge() {
        return this.highlightWithInvisibleEdge;
    }
    
    public void setHighlightWithInvisibleEdge(final boolean highlightWithInvisibleEdge) {
        this.highlightWithInvisibleEdge = highlightWithInvisibleEdge;
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }
}
