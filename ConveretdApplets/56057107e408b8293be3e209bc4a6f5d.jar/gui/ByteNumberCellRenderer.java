// 
// Decompiled by Procyon v0.5.30
// 

package gui;

import anon.util.Util;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ByteNumberCellRenderer extends DefaultTableCellRenderer
{
    public ByteNumberCellRenderer() {
        this.setHorizontalAlignment(4);
    }
    
    public Component getTableCellRendererComponent(final JTable table, final Object o, final boolean b, final boolean b2, final int n, final int n2) {
        if (b) {
            super.setForeground(table.getSelectionForeground());
            super.setBackground(table.getSelectionBackground());
        }
        else {
            super.setForeground(table.getForeground());
            super.setBackground(table.getBackground());
        }
        this.setFont(table.getFont());
        if (!(o instanceof Long)) {
            this.setText("Error - no Long!");
            return this;
        }
        this.setText(Util.formatBytesValueWithUnit((long)o));
        return this;
    }
}
