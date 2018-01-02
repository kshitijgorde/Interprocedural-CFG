// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.visual.expression;

import prefuse.data.Schema;
import prefuse.visual.VisualItem;
import prefuse.data.Tuple;
import prefuse.data.expression.Predicate;

public class InGroupPredicate extends GroupExpression implements Predicate
{
    public InGroupPredicate() {
    }
    
    public InGroupPredicate(final String s) {
        super(s);
    }
    
    public Object get(final Tuple tuple) {
        return this.getBoolean(tuple) ? Boolean.TRUE : Boolean.FALSE;
    }
    
    public boolean getBoolean(final Tuple tuple) {
        if (!(tuple instanceof VisualItem)) {
            return false;
        }
        final String group = this.getGroup(tuple);
        if (group == null) {
            return false;
        }
        final VisualItem visualItem = (VisualItem)tuple;
        return visualItem.getVisualization().isInGroup(visualItem, group);
    }
    
    public String getName() {
        return "INGROUP";
    }
    
    public Class getType(final Schema schema) {
        return Boolean.TYPE;
    }
}
