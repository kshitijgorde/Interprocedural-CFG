import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import javax.swing.ListCellRenderer;
import javax.swing.JLabel;

// 
// Decompiled by Procyon v0.5.30
// 

public class JLabelRenderer extends JLabel implements ListCellRenderer, ActionListener
{
    private JComboBox _comboBox;
    private JLabel _labelSelected;
    
    public JLabelRenderer() {
        this.setOpaque(true);
        this.setBorder(new EmptyBorder(1, 1, 1, 1));
    }
    
    public JLabelRenderer(final JComboBox comboBox) {
        this.setOpaque(true);
        this.setBorder(new EmptyBorder(1, 1, 1, 1));
        this._comboBox = comboBox;
        this._labelSelected = (JLabel)comboBox.getSelectedItem();
    }
    
    public Component getListCellRendererComponent(final JList list, final Object value, final int index, final boolean isSelected, final boolean cellHasFocus) {
        this.setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
        this.setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
        if (!((JLabel)value).isEnabled()) {
            this.setBackground(list.getBackground());
            this.setForeground(UIManager.getColor("Label.disabledForeground"));
        }
        this.setFont(list.getFont());
        this.setText((value == null) ? "" : ((JLabel)value).getText());
        return this;
    }
    
    public void actionPerformed(final ActionEvent e) {
        final JLabel labelTemp = (JLabel)this._comboBox.getSelectedItem();
        if (labelTemp != null && !labelTemp.isEnabled()) {
            this._comboBox.setSelectedItem(this._labelSelected);
        }
        else {
            this._labelSelected = labelTemp;
        }
    }
}
