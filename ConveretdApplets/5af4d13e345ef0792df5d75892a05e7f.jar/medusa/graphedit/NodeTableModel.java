// 
// Decompiled by Procyon v0.5.30
// 

package medusa.graphedit;

import java.util.Iterator;
import medusa.graph.Node;
import java.awt.Color;
import medusa.graph.Graph;

public class NodeTableModel extends EdgeTableModel
{
    public String[] columnNames;
    final Object[] reference;
    
    public NodeTableModel(final Graph g) {
        this.columnNames = new String[] { "Name", "x", "y", "Shape", "Color" };
        this.reference = new Object[] { new String(), new Double(0.0), new Double(0.0), new Integer(1), new Color(0, 0, 0) };
        this.setG(g);
        this.init();
    }
    
    public void init() {
        this.columnCount = 5;
        this.rowCount = this.getG().getNodeSize();
        this.data = new Object[this.rowCount][this.columnCount];
        int n = 0;
        final Iterator<Node> nodesIterator = this.getG().nodesIterator();
        while (nodesIterator.hasNext()) {
            final Node node = nodesIterator.next();
            this.data[n][0] = node.getLabel();
            this.data[n][1] = new Double(node.getX());
            this.data[n][2] = new Double(node.getY());
            this.data[n][3] = new Integer(node.getShape());
            this.data[n][4] = node.getColor();
            ++n;
        }
    }
    
    public String getColumnName(final int n) {
        return this.columnNames[n];
    }
    
    public Class getColumnClass(final int n) {
        return this.reference[n].getClass();
    }
    
    public Node[] getNodes() {
        final Node[] array = new Node[this.getRowCount()];
        for (int i = 0; i < this.getRowCount(); ++i) {
            array[i] = new Node((String)this.data[i][0], (Double)this.data[i][1], (Double)this.data[i][2], (Integer)this.data[i][3], (Color)this.data[i][4]);
        }
        return array;
    }
    
    public boolean isCellEditable(final int n, final int n2) {
        return n2 > 0;
    }
}
