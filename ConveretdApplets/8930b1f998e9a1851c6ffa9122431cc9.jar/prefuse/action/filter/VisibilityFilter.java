// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.filter;

import java.util.Iterator;
import prefuse.util.PrefuseLib;
import prefuse.data.Tuple;
import prefuse.visual.VisualItem;
import prefuse.data.expression.OrPredicate;
import prefuse.visual.expression.VisiblePredicate;
import prefuse.Visualization;
import prefuse.data.expression.Predicate;
import prefuse.action.GroupAction;

public class VisibilityFilter extends GroupAction
{
    private Predicate m_filter;
    private Predicate m_predicate;
    
    public VisibilityFilter(final Predicate predicate) {
        this.setPredicate(predicate);
    }
    
    public VisibilityFilter(final String s, final Predicate predicate) {
        super(s);
        this.setPredicate(predicate);
    }
    
    public VisibilityFilter(final Visualization visualization, final String s, final Predicate predicate) {
        super(visualization, s);
        this.setPredicate(predicate);
    }
    
    protected void setPredicate(final Predicate predicate) {
        this.m_predicate = predicate;
        this.m_filter = new OrPredicate(predicate, VisiblePredicate.TRUE);
    }
    
    public void run(final double n) {
        final Iterator items = this.m_vis.items(this.m_group, this.m_filter);
        while (items.hasNext()) {
            final VisualItem visualItem = items.next();
            PrefuseLib.updateVisible(visualItem, this.m_predicate.getBoolean(visualItem));
        }
    }
}
