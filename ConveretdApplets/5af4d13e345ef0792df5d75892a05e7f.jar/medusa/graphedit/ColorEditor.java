// 
// Decompiled by Procyon v0.5.30
// 

package medusa.graphedit;

import javax.swing.JTable;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.JDialog;
import javax.swing.JColorChooser;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.table.TableCellEditor;
import javax.swing.AbstractCellEditor;

public class ColorEditor extends AbstractCellEditor implements TableCellEditor, ActionListener
{
    Color currentColor;
    JButton button;
    JColorChooser colorChooser;
    JDialog dialog;
    protected static final String EDIT = "edit";
    
    public ColorEditor() {
        (this.button = new JButton()).setActionCommand("edit");
        this.button.addActionListener(this);
        this.button.setBorderPainted(false);
        this.colorChooser = new JColorChooser();
        this.dialog = JColorChooser.createDialog(this.button, "Pick a Color", true, this.colorChooser, this, null);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if ("edit".equals(actionEvent.getActionCommand())) {
            this.button.setBackground(this.currentColor);
            this.colorChooser.setColor(this.currentColor);
            this.dialog.setVisible(true);
            this.fireEditingStopped();
        }
        else {
            this.currentColor = this.colorChooser.getColor();
        }
    }
    
    public Object getCellEditorValue() {
        return this.currentColor;
    }
    
    public Component getTableCellEditorComponent(final JTable table, final Object o, final boolean b, final int n, final int n2) {
        this.currentColor = (Color)o;
        return this.button;
    }
}
