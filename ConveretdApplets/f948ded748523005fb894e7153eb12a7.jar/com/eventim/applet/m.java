// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet;

import javax.swing.JComponent;
import java.awt.Component;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.JComboBox;
import javax.swing.DefaultCellEditor;
import com.eventim.applet.b.o;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;
import javax.swing.JTable;

final class m extends JTable
{
    private final EventimApplet a;
    
    m(final EventimApplet a, final TableModel tableModel) {
        super(tableModel);
        this.a = a;
    }
    
    public final TableCellEditor getCellEditor(final int n, final int n2) {
        final o o;
        if (n2 == 4 && (o = EventimApplet.a(this.a).get(n)).a() > 1) {
            return new DefaultCellEditor(o);
        }
        return super.getCellEditor(n, n2);
    }
    
    public final TableCellRenderer getCellRenderer(final int n, final int n2) {
        if (n2 != 4) {
            return super.getCellRenderer(n, n2);
        }
        final o o;
        if ((o = EventimApplet.a(this.a).get(n)).a() > 1) {
            return o;
        }
        return new DefaultTableCellRenderer();
    }
    
    public final Component prepareRenderer(final TableCellRenderer tableCellRenderer, final int n, final int n2) {
        final Component prepareRenderer;
        if ((prepareRenderer = super.prepareRenderer(tableCellRenderer, n, n2)) instanceof JComponent) {
            final JComponent component = (JComponent)prepareRenderer;
            if (n2 == 3) {
                component.setToolTipText(EventimApplet.b(this.a).A().a("Lupe"));
            }
            else if (n2 == 6) {
                component.setToolTipText(EventimApplet.b(this.a).A().a("Loeschen"));
            }
        }
        return prepareRenderer;
    }
}
