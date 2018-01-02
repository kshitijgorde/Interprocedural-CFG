// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.ui.table;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class CellRenderer implements TableCellRenderer
{
    private AbstractCell cell;
    
    public CellRenderer(final AbstractCell cell) {
        this.cell = cell;
    }
    
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int index, final int arg5) {
        CellStyle style = (index % 2 == 0) ? CellStyle.DEFAULT : CellStyle.ALTERNATE;
        if (isSelected) {
            style = CellStyle.SELECTED;
        }
        this.cell.setPreferredSize(table.getSize());
        this.cell.setValue(value);
        this.cell.setStyle(style);
        return this.cell;
    }
}
