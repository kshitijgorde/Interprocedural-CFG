// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.visual.expression;

import prefuse.visual.VisualItem;
import prefuse.data.search.SearchTupleSet;
import prefuse.data.Tuple;
import prefuse.data.Schema;

public class QueryExpression extends GroupExpression
{
    public QueryExpression() {
    }
    
    public QueryExpression(final String s) {
        super(s);
    }
    
    public String getName() {
        return "QUERY";
    }
    
    public Class getType(final Schema schema) {
        return String.class;
    }
    
    public Object get(final Tuple tuple) {
        return ((SearchTupleSet)((VisualItem)tuple).getVisualization().getGroup(this.getGroup(tuple))).getQuery();
    }
}
