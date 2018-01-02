// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.b;

import java.awt.Color;
import com.eventim.applet.l;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.JLabel;

public final class ad extends JLabel implements TableCellRenderer
{
    public final Component getTableCellRendererComponent(final JTable table, final Object o, final boolean b, final boolean b2, final int n, final int n2) {
        this.setText((o == null) ? "" : o.toString());
        this.setToolTipText((String)o);
        if (l.a(table.getTableHeader().getBackground())) {
            this.setForeground(Color.white);
        }
        return this;
    }
}
