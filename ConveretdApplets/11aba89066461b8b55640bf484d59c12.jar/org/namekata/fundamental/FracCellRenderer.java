// 
// Decompiled by Procyon v0.5.30
// 

package org.namekata.fundamental;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.JLabel;

public class FracCellRenderer extends JLabel implements TableCellRenderer
{
    private int format;
    
    public FracCellRenderer() {
        this.format = 0;
        this.setOpaque(true);
    }
    
    public void setFormat(final int format) {
        switch (format) {
            case 0:
            case 1:
            case 2: {
                this.format = format;
                break;
            }
        }
    }
    
    @Override
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
        if (value.getClass() == String.class) {
            this.setHorizontalAlignment(0);
        }
        else {
            this.setHorizontalAlignment(4);
        }
        if (value.getClass() == Frac.class) {
            ((Frac)value).setFormat(this.format);
        }
        this.setText(value.toString());
        if (isSelected) {
            this.setForeground(table.getSelectionForeground());
            this.setBackground(table.getSelectionBackground());
        }
        else {
            this.setForeground(table.getForeground());
            this.setBackground(table.getBackground());
        }
        return this;
    }
}
