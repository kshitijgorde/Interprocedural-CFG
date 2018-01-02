// 
// Decompiled by Procyon v0.5.30
// 

package org.namekata.fundamental;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.JLabel;

public class MyCellRenderer extends JLabel implements TableCellRenderer
{
    public MyCellRenderer() {
        this.setOpaque(true);
    }
    
    @Override
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
        final Class cl = value.getClass();
        if (cl == String.class) {
            this.setHorizontalAlignment(0);
        }
        else {
            this.setHorizontalAlignment(4);
        }
        this.setText(value.toString());
        if (isSelected) {
            this.setForeground(table.getSelectionForeground());
            this.setBackground(table.getSelectionBackground());
        }
        else {
            this.setForeground(table.getForeground());
            this.setBackground(table.getBackground());
        }
        return this;
    }
}
