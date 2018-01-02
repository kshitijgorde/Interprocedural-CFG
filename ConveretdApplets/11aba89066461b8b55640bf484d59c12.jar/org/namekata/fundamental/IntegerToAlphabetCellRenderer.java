// 
// Decompiled by Procyon v0.5.30
// 

package org.namekata.fundamental;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.JLabel;

public class IntegerToAlphabetCellRenderer extends JLabel implements TableCellRenderer
{
    public IntegerToAlphabetCellRenderer() {
        this(false);
    }
    
    public IntegerToAlphabetCellRenderer(final boolean lower) {
        this.setOpaque(true);
        this.setHorizontalAlignment(0);
        if (lower) {
            MyUtil.toLower();
        }
        else {
            MyUtil.toUpper();
        }
    }
    
    @Override
    public Component getTableCellRendererComponent(final JTable jTable, final Object obj, final boolean isSelected, final boolean param3, final int param4, final int param5) {
        final int value = (int)obj;
        this.setText(MyUtil.intToString(value));
        if (isSelected) {
            this.setForeground(jTable.getSelectionForeground());
            this.setBackground(jTable.getSelectionBackground());
        }
        else {
            this.setForeground(jTable.getForeground());
            this.setBackground(jTable.getBackground());
        }
        return this;
    }
}
