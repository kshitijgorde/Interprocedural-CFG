// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.query;

import javax.swing.JComponent;
import prefuse.data.tuple.TupleSet;
import prefuse.data.expression.Predicate;

public abstract class DynamicQueryBinding
{
    protected Predicate m_query;
    protected TupleSet m_tuples;
    protected String m_field;
    
    protected DynamicQueryBinding(final TupleSet tuples, final String field) {
        this.m_tuples = tuples;
        this.m_field = field;
    }
    
    public Predicate getPredicate() {
        return this.m_query;
    }
    
    protected void setPredicate(final Predicate query) {
        this.m_query = query;
    }
    
    public abstract JComponent createComponent();
}
