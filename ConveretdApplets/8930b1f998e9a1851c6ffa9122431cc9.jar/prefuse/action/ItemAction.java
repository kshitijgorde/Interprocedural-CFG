// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action;

import java.util.Iterator;
import prefuse.visual.VisualItem;
import prefuse.visual.expression.VisiblePredicate;
import prefuse.Visualization;
import prefuse.data.expression.Predicate;

public abstract class ItemAction extends GroupAction
{
    protected Predicate m_predicate;
    
    public ItemAction() {
        this((Visualization)null);
    }
    
    public ItemAction(final Visualization visualization) {
        this(visualization, Visualization.ALL_ITEMS);
    }
    
    public ItemAction(final String s) {
        this(null, s);
    }
    
    public ItemAction(final String s, final Predicate predicate) {
        this(null, s, predicate);
    }
    
    public ItemAction(final Visualization visualization, final String s) {
        this(visualization, s, VisiblePredicate.TRUE);
    }
    
    public ItemAction(final Visualization visualization, final String s, final Predicate predicate) {
        super(visualization, s);
        this.m_predicate = predicate;
    }
    
    public Predicate getFilterPredicate() {
        return this.m_predicate;
    }
    
    public void setFilterPredicate(final Predicate predicate) {
        this.m_predicate = predicate;
    }
    
    public void run(final double n) {
        final Iterator items = this.getVisualization().items(this.m_group, this.m_predicate);
        while (items.hasNext()) {
            this.process(items.next(), n);
        }
    }
    
    public abstract void process(final VisualItem p0, final double p1);
}
