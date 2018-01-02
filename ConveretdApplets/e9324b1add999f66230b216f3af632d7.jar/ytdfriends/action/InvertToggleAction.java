// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.action;

import java.util.Iterator;
import edu.berkeley.guir.prefuse.VisualItem;
import edu.berkeley.guir.prefuse.ItemRegistry;
import edu.berkeley.guir.prefuse.action.AbstractAction;

public class InvertToggleAction extends AbstractAction
{
    private int numFlashes;
    
    public InvertToggleAction() {
        this.numFlashes = 3;
    }
    
    public void run(final ItemRegistry registry, final double frac) {
        final int scalar = 2 * this.numFlashes - 1;
        final Boolean val = ((int)(frac * scalar) % 2 == 0) ? Boolean.TRUE : Boolean.FALSE;
        Iterator iter = registry.getNodeItems();
        while (iter.hasNext()) {
            final VisualItem item = iter.next();
            item.setVizAttribute("invert", (Object)val);
        }
        iter = registry.getEdgeItems();
        while (iter.hasNext()) {
            final VisualItem item = iter.next();
            item.setVizAttribute("invert", (Object)val);
        }
    }
}
