// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel.controlpanel;

import javax.swing.Icon;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class IconRenderer extends DefaultTableCellRenderer
{
    public IconRenderer() {
        this.setHorizontalAlignment(0);
    }
    
    public Component getTableCellRendererComponent(final JTable table, final Object o, final boolean b, final boolean b2, final int n, final int n2) {
        if (b) {
            super.setBackground(table.getSelectionBackground());
        }
        else {
            super.setBackground(table.getBackground());
        }
        if (o == null || !(o instanceof Icon)) {
            this.setIcon(null);
        }
        else {
            this.setIcon((Icon)o);
        }
        return this;
    }
}
