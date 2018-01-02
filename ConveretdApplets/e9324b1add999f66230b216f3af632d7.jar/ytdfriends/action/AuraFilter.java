// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.action;

import edu.berkeley.guir.prefuse.VisualItem;
import edu.berkeley.guir.prefuse.NodeItem;
import java.util.Iterator;
import edu.berkeley.guir.prefuse.focus.FocusSet;
import edu.berkeley.guir.prefuse.FocusManager;
import edu.berkeley.guir.prefuse.graph.Entity;
import edu.berkeley.guir.prefuse.graph.Node;
import edu.berkeley.guir.prefuse.ItemRegistry;
import edu.berkeley.guir.prefuse.action.filter.Filter;

public class AuraFilter extends Filter
{
    public AuraFilter() {
        super("aura", true);
    }
    
    public void run(final ItemRegistry registry, final double frac) {
        final FocusManager fman = registry.getFocusManager();
        final FocusSet set = fman.getFocusSet((Object)"search");
        synchronized (set) {
            for (final Node n : set) {
                final NodeItem nitem = registry.getNodeItem(n);
                if (nitem != null) {
                    final VisualItem item = registry.getItem("aura", (Entity)n, true, true);
                    item.setInteractive(false);
                }
            }
        }
        // monitorexit(set)
        super.run(registry, frac);
    }
}
