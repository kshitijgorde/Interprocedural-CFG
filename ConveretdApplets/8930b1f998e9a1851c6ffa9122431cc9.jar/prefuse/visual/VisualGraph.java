// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.visual;

import prefuse.util.collections.IntIterator;
import prefuse.data.Table;
import prefuse.Visualization;
import prefuse.data.Graph;

public class VisualGraph extends Graph implements VisualTupleSet
{
    private Visualization m_vis;
    private String m_group;
    
    public VisualGraph(final VisualTable visualTable, final VisualTable visualTable2, final boolean b, final String s, final String s2, final String s3) {
        super(visualTable, visualTable2, b, s, s2, s3);
    }
    
    protected void fireGraphEvent(final Table table, final int n, final int n2, final int n3, final int n4) {
        if (n4 == 0 && n3 == VisualItem.IDX_VALIDATED && table == this.getNodeTable()) {
            final VisualTable visualTable = (VisualTable)table;
            final VisualTable visualTable2 = (VisualTable)this.getEdgeTable();
            for (int i = n; i <= n2; ++i) {
                if (!visualTable.isValidated(i)) {
                    if (i < 0) {
                        System.err.println("catch me - VisualGraph fireGraphEvent");
                    }
                    final IntIterator edgeRows = this.edgeRows(i);
                    while (edgeRows.hasNext()) {
                        visualTable2.setValidated(edgeRows.nextInt(), false);
                    }
                }
            }
        }
        super.fireGraphEvent(table, n, n2, n3, n4);
    }
    
    public int getNodeIndex(final int n) {
        if (this.m_nkey == null) {
            return ((VisualTable)this.getNodeTable()).getChildRow(n);
        }
        return super.getNodeIndex(n);
    }
    
    public Visualization getVisualization() {
        return this.m_vis;
    }
    
    public void setVisualization(final Visualization vis) {
        this.m_vis = vis;
    }
    
    public String getGroup() {
        return this.m_group;
    }
    
    public void setGroup(final String group) {
        this.m_group = group;
    }
}
