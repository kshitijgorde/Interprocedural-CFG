// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.visual.expression;

import prefuse.data.search.SearchTupleSet;
import prefuse.visual.VisualItem;
import prefuse.data.Tuple;
import prefuse.data.expression.BooleanLiteral;
import prefuse.Visualization;
import prefuse.data.expression.Expression;

public class SearchPredicate extends InGroupPredicate
{
    private Expression m_incEmpty;
    private int paramCount;
    
    public SearchPredicate() {
        this(Visualization.SEARCH_ITEMS, true);
        this.paramCount = 0;
    }
    
    public SearchPredicate(final boolean b) {
        this(Visualization.SEARCH_ITEMS, b);
    }
    
    public SearchPredicate(final String s, final boolean b) {
        super(s);
        this.paramCount = 0;
        this.m_incEmpty = new BooleanLiteral(b);
        this.paramCount = 2;
    }
    
    public boolean getBoolean(final Tuple tuple) {
        final String group = this.getGroup(tuple);
        if (group == null) {
            return false;
        }
        final boolean boolean1 = this.m_incEmpty.getBoolean(tuple);
        final VisualItem visualItem = (VisualItem)tuple;
        final Visualization visualization = visualItem.getVisualization();
        final SearchTupleSet set = (SearchTupleSet)visualization.getGroup(group);
        if (set == null && boolean1) {
            return true;
        }
        final String query = set.getQuery();
        return (boolean1 && (query == null || query.length() == 0)) || visualization.isInGroup(visualItem, group);
    }
    
    public void addParameter(final Expression incEmpty) {
        if (this.paramCount == 0) {
            super.addParameter(incEmpty);
        }
        else {
            if (this.paramCount != 1) {
                throw new IllegalStateException("This function takes only 2 parameters.");
            }
            this.m_incEmpty = incEmpty;
        }
    }
    
    public String getName() {
        return "MATCH";
    }
    
    public int getParameterCount() {
        return 2;
    }
    
    public String toString() {
        return this.getName() + "(" + this.m_group + ", " + this.m_incEmpty + ")";
    }
}
