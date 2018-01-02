// 
// Decompiled by Procyon v0.5.30
// 

package wordle.a;

import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

final class h implements TableCellRenderer
{
    private TableCellRenderer a;
    private /* synthetic */ i b;
    
    public h(final i b, final TableCellRenderer a) {
        this.b = b;
        this.a = a;
    }
    
    public final Component getTableCellRendererComponent(final JTable table, final Object o, final boolean b, final boolean b2, final int n, final int n2) {
        final Component tableCellRendererComponent;
        if ((tableCellRendererComponent = this.a.getTableCellRendererComponent(table, o, b, b2, n, n2)) instanceof JLabel) {
            final JLabel label;
            (label = (JLabel)tableCellRendererComponent).setHorizontalTextPosition(2);
            label.setIcon(this.b.b(table.convertColumnIndexToModel(n2), label.getFont().getSize()));
        }
        return tableCellRendererComponent;
    }
}
