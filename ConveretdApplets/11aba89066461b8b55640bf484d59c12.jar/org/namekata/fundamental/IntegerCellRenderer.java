// 
// Decompiled by Procyon v0.5.30
// 

package org.namekata.fundamental;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.JLabel;

public class IntegerCellRenderer extends JLabel implements TableCellRenderer
{
    private int format;
    
    public int getFormat() {
        return this.format;
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
    
    public IntegerCellRenderer() {
        this.format = 0;
        this.setOpaque(true);
        this.setHorizontalAlignment(4);
    }
    
    @Override
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
        this.setHorizontalAlignment(4);
        if (value.getClass() == Frac.class) {
            ((Frac)value).setFormat(this.format);
        }
        this.setText(value.toString());
        if (value.getClass() == String.class) {
            this.setHorizontalAlignment(0);
        }
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
