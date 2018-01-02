// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.visual.tuple;

import prefuse.visual.VisualTable;
import prefuse.visual.VisualItem;
import prefuse.data.Graph;
import prefuse.data.Table;
import prefuse.visual.DecoratorItem;

public class TableDecoratorItem extends TableVisualItem implements DecoratorItem
{
    protected void init(final Table table, final Graph graph, final int n) {
        this.m_table = table;
        this.m_row = (this.m_table.isValidRow(n) ? n : -1);
    }
    
    public VisualItem getDecoratedItem() {
        final VisualTable visualTable = (VisualTable)this.getTable();
        return (VisualItem)visualTable.getParentTable().getTuple(visualTable.getParentRow(this.getRow()));
    }
}
