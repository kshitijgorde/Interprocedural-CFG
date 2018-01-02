// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.ui;

import java.awt.Color;
import java.text.NumberFormat;
import java.awt.Font;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.DefaultTableCellRenderer;

public class NumberCellRenderer extends DefaultTableCellRenderer implements TableCellRenderer
{
    public NumberCellRenderer() {
        this.setHorizontalAlignment(4);
    }
    
    public Component getTableCellRendererComponent(final JTable table, final Object o, final boolean b, final boolean b2, final int n, final int n2) {
        this.setFont(null);
        this.setText(NumberFormat.getNumberInstance().format(o));
        if (b) {
            this.setBackground(table.getSelectionBackground());
        }
        else {
            this.setBackground(null);
        }
        return this;
    }
}
