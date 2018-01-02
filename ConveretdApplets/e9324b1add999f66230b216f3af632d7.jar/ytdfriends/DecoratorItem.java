// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends;

import edu.berkeley.guir.prefuse.graph.Entity;
import edu.berkeley.guir.prefuse.ItemRegistry;
import edu.berkeley.guir.prefuse.VisualItem;

public class DecoratorItem extends VisualItem
{
    private VisualItem m_decorated;
    
    public void init(final ItemRegistry registry, final String itemClass, final Entity entity) {
        super.init(registry, itemClass, entity);
        this.m_decorated = registry.getItem("node", entity, false, false);
    }
    
    public VisualItem getDecorated() {
        return this.m_decorated;
    }
    
    public void setDecorated(final VisualItem decorated) {
        this.m_decorated = decorated;
    }
}
