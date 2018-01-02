// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.query;

import prefuse.data.expression.AbstractPredicate;
import prefuse.data.Tuple;
import prefuse.util.ui.JSearchPanel;
import javax.swing.JComponent;
import prefuse.visual.VisualTupleSet;
import prefuse.data.event.TupleSetListener;
import prefuse.data.expression.Predicate;
import prefuse.data.search.PrefixSearchTupleSet;
import prefuse.data.tuple.TupleSet;
import prefuse.data.search.SearchTupleSet;

public class SearchQueryBinding extends DynamicQueryBinding
{
    private SearchTupleSet m_set;
    private Listener m_lstnr;
    private Object m_lock;
    
    public SearchQueryBinding(final TupleSet set, final String s) {
        this(set, s, new PrefixSearchTupleSet());
    }
    
    public SearchQueryBinding(final TupleSet set, final String s, final SearchTupleSet set2) {
        super(set, s);
        this.m_lstnr = new Listener();
        this.setPredicate(new SearchBindingPredicate());
        (this.m_set = set2).index(set.tuples(), s);
        this.m_set.addTupleSetListener(this.m_lstnr);
        if (set instanceof VisualTupleSet) {
            this.m_lock = ((VisualTupleSet)set).getVisualization();
        }
    }
    
    public SearchTupleSet getSearchSet() {
        return this.m_set;
    }
    
    public JComponent createComponent() {
        return this.createSearchPanel();
    }
    
    public JSearchPanel createSearchPanel() {
        return this.createSearchPanel(this.m_set instanceof PrefixSearchTupleSet);
    }
    
    public JSearchPanel createSearchPanel(final boolean b) {
        final JSearchPanel searchPanel = new JSearchPanel(this.m_set, this.m_field, b);
        if (this.m_lock != null) {
            searchPanel.setLock(this.m_lock);
        }
        return searchPanel;
    }
    
    private class Listener implements TupleSetListener
    {
        public void tupleSetChanged(final TupleSet set, final Tuple[] array, final Tuple[] array2) {
            ((SearchBindingPredicate)SearchQueryBinding.this.getPredicate()).touch();
        }
    }
    
    private class SearchBindingPredicate extends AbstractPredicate
    {
        public boolean getBoolean(final Tuple tuple) {
            final String query = SearchQueryBinding.this.m_set.getQuery();
            return query == null || query.length() == 0 || SearchQueryBinding.this.m_set.containsTuple(tuple);
        }
        
        public void touch() {
            this.fireExpressionChange();
        }
    }
}
