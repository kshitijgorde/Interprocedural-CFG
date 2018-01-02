// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.visual;

import prefuse.data.expression.Predicate;
import java.util.Iterator;

public interface AggregateItem extends VisualItem
{
    int getAggregateSize();
    
    boolean containsItem(final VisualItem p0);
    
    void addItem(final VisualItem p0);
    
    void removeItem(final VisualItem p0);
    
    void removeAllItems();
    
    Iterator items();
    
    Iterator items(final Predicate p0);
}
