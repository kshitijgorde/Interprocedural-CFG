// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Component;
import javax.swing.JTable;
import java.text.DateFormat;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.DefaultTableCellRenderer;

public class DateCellRenderer extends DefaultTableCellRenderer implements TableCellRenderer
{
    private DateFormat formatter;
    
    public DateCellRenderer() {
        this(DateFormat.getDateTimeInstance());
    }
    
    public DateCellRenderer(final DateFormat formatter) {
        this.formatter = formatter;
        this.setHorizontalAlignment(0);
    }
    
    public Component getTableCellRendererComponent(final JTable table, final Object o, final boolean b, final boolean b2, final int n, final int n2) {
        this.setFont(null);
        this.setText(this.formatter.format(o));
        if (b) {
            this.setBackground(table.getSelectionBackground());
        }
        else {
            this.setBackground(null);
        }
        return this;
    }
}
