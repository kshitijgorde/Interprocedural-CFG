// 
// Decompiled by Procyon v0.5.30
// 

package org.namekata.matrixExercise;

import javax.swing.JFrame;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.JLabel;

public class MyListCellRenderer extends JLabel implements ListCellRenderer
{
    @Override
    public Component getListCellRendererComponent(final JList list, final Object value, final int index, final boolean isSelected, final boolean cellHasFocus) {
        this.setOpaque(true);
        this.setText(((JFrame)value).getTitle());
        if (isSelected) {
            this.setBackground(list.getSelectionBackground());
            this.setForeground(list.getSelectionForeground());
        }
        else {
            this.setBackground(list.getBackground());
            this.setForeground(list.getForeground());
        }
        return this;
    }
}
