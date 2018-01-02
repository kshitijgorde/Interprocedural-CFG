// 
// Decompiled by Procyon v0.5.30
// 

package medusa.graphedit;

import java.util.Iterator;
import medusa.graph.Edge;
import medusa.graph.Graph;
import javax.swing.table.AbstractTableModel;

public class EdgeTableModel extends AbstractTableModel
{
    private Graph g;
    protected Object[][] data;
    public String[] columnNames;
    protected int columnCount;
    protected int rowCount;
    final Object[] reference;
    
    public EdgeTableModel(final Graph g) {
        this.columnNames = new String[] { "Node 1", "Node 2", "Type", "Orient.", "Confidence", "Mark" };
        this.reference = new Object[] { new String(), new String(), new Integer(0), new Double(0.0), new Float(1.0f), new Boolean(false) };
        this.setG(g);
        this.init();
    }
    
    public EdgeTableModel() {
        this.columnNames = new String[] { "Node 1", "Node 2", "Type", "Orient.", "Confidence", "Mark" };
        this.reference = new Object[] { new String(), new String(), new Integer(0), new Double(0.0), new Float(1.0f), new Boolean(false) };
    }
    
    public void init() {
        this.columnCount = 6;
        this.rowCount = this.getG().getEdgeSize();
        this.data = new Object[this.rowCount][this.columnCount];
        int n = 0;
        final Iterator<Edge> edgesIterator = this.getG().edgesIterator();
        while (edgesIterator.hasNext()) {
            final Edge edge = edgesIterator.next();
            this.data[n][0] = edge.getFromName();
            this.data[n][1] = edge.getToName();
            this.data[n][2] = new Integer(edge.getType());
            this.data[n][3] = new Double(edge.getOrientation());
            this.data[n][4] = new Float(edge.getConf());
            this.data[n][5] = new Boolean(false);
            ++n;
        }
    }
    
    public Object getValueAt(final int n, final int n2) {
        return this.data[n][n2];
    }
    
    public int getColumnCount() {
        return this.columnCount;
    }
    
    public int getRowCount() {
        return this.rowCount;
    }
    
    public String getColumnName(final int n) {
        return this.columnNames[n];
    }
    
    public boolean isCellEditable(final int n, final int n2) {
        return n2 > 1;
    }
    
    public void setValueAt(final Object o, final int n, final int n2) {
        this.data[n][n2] = o;
        this.fireTableCellUpdated(n, n2);
    }
    
    public void removeEdges() {
        for (int i = 0; i < this.getRowCount(); ++i) {
            if (this.data[i][5]) {
                System.out.println("removing " + this.data[i][0] + " " + this.data[i][1]);
                this.g.removeEdge(new Edge((String)this.data[i][0], (String)this.data[i][1], (Integer)this.data[i][2], (Double)this.data[i][3], (Float)this.data[i][4]));
            }
        }
        this.init();
    }
    
    public Graph getG() {
        return this.g;
    }
    
    public void setG(final Graph g) {
        this.g = g;
    }
    
    public Class getColumnClass(final int n) {
        return this.reference[n].getClass();
    }
    
    public Graph getGraph() {
        final Graph graph = new Graph();
        for (int i = 0; i < this.getRowCount(); ++i) {
            graph.addEdge(new Edge((String)this.data[i][0], (String)this.data[i][1], (Integer)this.data[i][2], (Double)this.data[i][3], (Float)this.data[i][4]));
        }
        return graph;
    }
}
