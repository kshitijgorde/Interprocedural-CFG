// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.table;

import javax.swing.JTable;
import org.xidget.IXidget;
import org.xidget.feature.tree.ColumnWidthFeature;

public class JTableColumnWidthFeature extends ColumnWidthFeature
{
    public JTableColumnWidthFeature(final IXidget xidget) {
        super(xidget);
    }
    
    @Override
    protected int getMaxWidth() {
        final JTable table = this.xidget.getFeature(JTable.class);
        if (table != null) {
            return table.getFontMetrics(table.getFont()).getMaxAdvance();
        }
        return 0;
    }
    
    @Override
    protected int getTextWidth(final String s, final boolean b) {
        if (s == null) {
            return 0;
        }
        final JTable table = this.xidget.getFeature(JTable.class);
        if (table != null) {
            return (b ? table.getTableHeader().getDefaultRenderer() : table.getDefaultRenderer(String.class)).getTableCellRendererComponent(table, s, true, true, 0, 0).getPreferredSize().width;
        }
        return 10;
    }
}
