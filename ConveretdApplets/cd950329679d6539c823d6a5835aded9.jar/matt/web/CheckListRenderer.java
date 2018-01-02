// 
// Decompiled by Procyon v0.5.30
// 

package matt.web;

import java.awt.Component;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.JCheckBox;

class CheckListRenderer extends JCheckBox implements ListCellRenderer
{
    public Component getListCellRendererComponent(final JList list, final Object value, final int index, final boolean isSelected, final boolean hasFocus) {
        this.setEnabled(list.isEnabled());
        this.setSelected(((CheckListItem)value).isSelected());
        this.setFont(list.getFont());
        this.setBackground(list.getBackground());
        this.setForeground(list.getForeground());
        this.setText(value.toString());
        return this;
    }
}
