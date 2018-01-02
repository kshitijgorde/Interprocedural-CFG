// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.action;

import java.awt.Paint;
import java.util.Iterator;
import java.util.Set;
import edu.berkeley.guir.prefuse.FocusManager;
import edu.berkeley.guir.prefuse.VisualItem;
import edu.berkeley.guir.prefuse.graph.Entity;
import edu.berkeley.guir.prefuse.graph.Node;
import edu.berkeley.guir.prefusex.community.CommunitySet;
import edu.berkeley.guir.prefuse.ItemRegistry;
import edu.berkeley.guir.prefuse.AggregateItem;
import java.util.ArrayList;
import edu.berkeley.guir.prefuse.action.filter.Filter;

public class CommunityFilter extends Filter
{
    private Object m_key;
    private ArrayList<AggregateItem> m_aggs;
    
    public CommunityFilter(final Object communityKey) {
        super("aggregate", true);
        this.m_aggs = new ArrayList<AggregateItem>(50);
        this.m_key = communityKey;
    }
    
    public void run(final ItemRegistry registry, final double frac) {
        final FocusManager fman = registry.getFocusManager();
        final CommunitySet comm = (CommunitySet)fman.getFocusSet(this.m_key);
        final int num = comm.getCommunityCount();
        for (int i = 0; i < num; ++i) {
            final Set set = comm.getCommunityMembers(i);
            this.m_aggs.add(this.getAggregate(registry, set));
        }
        super.run(registry, frac);
        for (int i = 0; i < num; ++i) {
            final Set set = comm.getCommunityMembers(i);
            final AggregateItem aitem = this.m_aggs.get(i);
            for (final Node node : set) {
                registry.addMapping((Entity)node, (VisualItem)aitem);
            }
        }
        this.m_aggs.clear();
    }
    
    protected AggregateItem getAggregate(final ItemRegistry registry, final Set set) {
        AggregateItem aitem = null;
        boolean highlight = true;
        Node n = null;
        final Iterator iter = set.iterator();
        while (iter.hasNext()) {
            n = iter.next();
            aitem = registry.getAggregateItem((Entity)n);
            if (aitem != null && aitem.getDirty() > 0) {
                highlight = aitem.isHighlighted();
                break;
            }
            aitem = null;
        }
        if (aitem == null) {
            aitem = registry.getAggregateItem((Entity)n, true);
        }
        registry.removeMappings((VisualItem)aitem);
        final float[] poly = (float[])aitem.getVizAttribute("polygon");
        final Paint color = aitem.getColor();
        final Paint fill = aitem.getFillColor();
        aitem.init(registry, "aggregate", (Entity)n);
        aitem.setVizAttribute("polygon", (Object)poly);
        aitem.setColor(color);
        aitem.setFillColor(fill);
        aitem.setHighlighted(highlight);
        aitem.setLocation(0.0, 0.0);
        aitem.setAggregateSize(set.size());
        aitem.setInteractive(false);
        aitem.setVisible(true);
        return aitem;
    }
}
