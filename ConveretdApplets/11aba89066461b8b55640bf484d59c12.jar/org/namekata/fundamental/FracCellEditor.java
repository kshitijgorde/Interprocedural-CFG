// 
// Decompiled by Procyon v0.5.30
// 

package org.namekata.fundamental;

import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.AbstractCellEditor;

public class FracCellEditor extends AbstractCellEditor implements TableCellEditor
{
    private FracField editor;
    private int clickCountToStart;
    
    public FracCellEditor() {
        this.clickCountToStart = 2;
        this.editor = new FracField();
    }
    
    public FracCellEditor(final FracField ff) {
        this.clickCountToStart = 2;
        this.editor = ff;
    }
    
    @Override
    public Object getCellEditorValue() {
        return this.editor.getValue();
    }
    
    @Override
    public Component getTableCellEditorComponent(final JTable table, final Object value, final boolean isSelected, final int row, final int column) {
        this.editor.setValue(value);
        return this.editor;
    }
    
    @Override
    public boolean isCellEditable(final EventObject anEvent) {
        return !(anEvent instanceof MouseEvent) || ((MouseEvent)anEvent).getClickCount() >= this.clickCountToStart;
    }
    
    @Override
    public boolean shouldSelectCell(final EventObject anEvent) {
        return true;
    }
}
