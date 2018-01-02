// 
// Decompiled by Procyon v0.5.30
// 

package medusa.graphedit;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComponent;
import medusa.graph.Graph;
import javax.swing.table.TableCellEditor;
import javax.swing.AbstractCellEditor;

public class NodeNameEditor extends AbstractCellEditor implements TableCellEditor
{
    Graph g;
    String label;
    EditGraphDialog parent;
    JComponent component;
    
    public NodeNameEditor(final Graph g, final EditGraphDialog parent) {
        this.component = new JTextField();
        this.g = g;
        this.parent = parent;
    }
    
    public Object getCellEditorValue() {
        final String newValue = ((JTextField)this.component).getText();
        this.parent.notifyNodeNameChange(this.label, newValue);
        return newValue;
    }
    
    public boolean stopCellEditing() {
        final boolean retValue = super.stopCellEditing();
        return retValue;
    }
    
    public Component getTableCellEditorComponent(final JTable table, final Object value, final boolean isSelected, final int row, final int column) {
        this.label = (String)value;
        System.out.println(value);
        ((JTextField)this.component).setText((String)value);
        return this.component;
    }
}
