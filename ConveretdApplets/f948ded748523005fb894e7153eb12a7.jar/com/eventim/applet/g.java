// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet;

import javax.swing.JComponent;
import java.awt.Component;
import javax.swing.table.TableCellRenderer;
import javax.swing.JTable;

final class g extends JTable
{
    private final EventimApplet a;
    
    g(final EventimApplet a) {
        this.a = a;
    }
    
    public final Component prepareRenderer(final TableCellRenderer tableCellRenderer, final int n, final int n2) {
        final Component prepareRenderer;
        if ((prepareRenderer = super.prepareRenderer(tableCellRenderer, n, n2)) instanceof JComponent) {
            final JComponent component = (JComponent)prepareRenderer;
            if (n2 == 0) {
                if (this.getValueAt(n, 0)) {
                    component.setToolTipText(EventimApplet.b(this.a).A().a("Preiskategorie_deaktivieren"));
                }
                else {
                    component.setToolTipText(EventimApplet.b(this.a).A().a("Preiskategorie_aktivieren"));
                }
            }
        }
        return prepareRenderer;
    }
}
