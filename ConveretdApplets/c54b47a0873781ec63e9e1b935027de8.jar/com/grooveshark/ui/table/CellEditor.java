// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.ui.table;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.AbstractCellEditor;

public class CellEditor extends AbstractCellEditor implements TableCellEditor
{
    private static final long serialVersionUID = -2166486503269218849L;
    private AbstractCell cell;
    
    public CellEditor(final AbstractCell cell) {
        this.cell = cell;
    }
    
    public Component getTableCellEditorComponent(final JTable table, final Object value, final boolean isSelected, final int row, final int column) {
        this.cell.setPreferredSize(table.getSize());
        this.cell.setValue(value);
        this.cell.setStyle(CellStyle.SELECTED);
        return this.cell;
    }
    
    public Object getCellEditorValue() {
        return this.cell.getValue();
    }
}
