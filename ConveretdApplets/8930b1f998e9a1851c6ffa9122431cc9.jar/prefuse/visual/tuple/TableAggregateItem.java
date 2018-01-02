// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.visual.tuple;

import prefuse.data.util.FilterIterator;
import prefuse.data.expression.Predicate;
import java.util.Iterator;
import prefuse.visual.VisualItem;
import prefuse.visual.AggregateTable;
import prefuse.data.Graph;
import prefuse.data.Table;
import prefuse.visual.AggregateItem;

public class TableAggregateItem extends TableVisualItem implements AggregateItem
{
    protected void init(final Table table, final Graph graph, final int n) {
        this.m_table = table;
        this.m_row = (this.m_table.isValidRow(n) ? n : -1);
    }
    
    public int getAggregateSize() {
        return ((AggregateTable)this.m_table).getAggregateSize(this.m_row);
    }
    
    public boolean containsItem(final VisualItem visualItem) {
        return ((AggregateTable)this.m_table).aggregateContains(this.m_row, visualItem);
    }
    
    public void addItem(final VisualItem visualItem) {
        ((AggregateTable)this.m_table).addToAggregate(this.m_row, visualItem);
    }
    
    public void removeItem(final VisualItem visualItem) {
        ((AggregateTable)this.m_table).removeFromAggregate(this.m_row, visualItem);
    }
    
    public void removeAllItems() {
        ((AggregateTable)this.m_table).removeAllFromAggregate(this.m_row);
    }
    
    public Iterator items() {
        return ((AggregateTable)this.m_table).aggregatedTuples(this.m_row);
    }
    
    public Iterator items(final Predicate predicate) {
        return new FilterIterator(((AggregateTable)this.m_table).aggregatedTuples(this.m_row), predicate);
    }
}
