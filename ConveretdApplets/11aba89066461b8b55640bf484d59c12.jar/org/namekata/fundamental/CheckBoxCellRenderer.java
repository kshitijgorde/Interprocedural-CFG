// 
// Decompiled by Procyon v0.5.30
// 

package org.namekata.fundamental;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.JCheckBox;

public class CheckBoxCellRenderer extends JCheckBox implements TableCellRenderer
{
    public CheckBoxCellRenderer(final String str, final boolean set) {
        super(str, set);
    }
    
    @Override
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
        if (value.equals(new Boolean(true))) {
            this.setSelected(true);
        }
        else {
            this.setSelected(false);
        }
        return this;
    }
}
