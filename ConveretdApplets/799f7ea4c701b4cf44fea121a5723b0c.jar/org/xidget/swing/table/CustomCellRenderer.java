// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.table;

import javax.swing.Icon;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomCellRenderer extends DefaultTableCellRenderer
{
    @Override
    public Component getTableCellRendererComponent(final JTable table, final Object o, final boolean b, final boolean b2, final int n, final int n2) {
        if (o instanceof Boolean) {
            return table.getDefaultRenderer(Boolean.class).getTableCellRendererComponent(table, o, b, b2, n, n2);
        }
        super.getTableCellRendererComponent(table, o, b, b2, n, n2);
        final CustomTableModel customTableModel = (CustomTableModel)table.getModel();
        this.setIcon((Icon)customTableModel.getIconAt(n, n2));
        final Object value = customTableModel.getValueAt(n, n2);
        final String s = (value != null) ? value.toString() : "";
        this.setName(s);
        this.setText(s);
        return this;
    }
}
