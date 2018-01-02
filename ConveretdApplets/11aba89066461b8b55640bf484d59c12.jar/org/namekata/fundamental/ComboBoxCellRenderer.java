// 
// Decompiled by Procyon v0.5.30
// 

package org.namekata.fundamental;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.JComboBox;

public class ComboBoxCellRenderer extends JComboBox implements TableCellRenderer
{
    public ComboBoxCellRenderer(final String[] strs) {
        super(strs);
    }
    
    @Override
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
        this.setSelectedItem(value);
        return this;
    }
}
