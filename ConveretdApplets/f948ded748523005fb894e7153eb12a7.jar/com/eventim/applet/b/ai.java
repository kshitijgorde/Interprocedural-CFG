// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.b;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JComponent;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public final class ai implements TableCellRenderer
{
    private n a;
    private TableCellRenderer b;
    
    public ai(final TableCellRenderer b, final n a) {
        this.b = b;
        this.a = a;
    }
    
    public final Component getTableCellRendererComponent(final JTable table, final Object o, final boolean b, final boolean b2, final int n, final int n2) {
        final Component tableCellRendererComponent;
        (tableCellRendererComponent = this.b.getTableCellRendererComponent(table, o, b, b2, n, n2)).setEnabled(!this.a.a().contains(new Integer(n)));
        ((JComponent)tableCellRendererComponent).setBorder(new EmptyBorder(0, 0, 0, 0));
        return tableCellRendererComponent;
    }
}
