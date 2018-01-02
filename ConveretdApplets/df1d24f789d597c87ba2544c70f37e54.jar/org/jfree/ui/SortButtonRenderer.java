// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.ui;

import javax.swing.JComponent;
import javax.swing.table.JTableHeader;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.border.Border;
import java.awt.Insets;
import javax.swing.Icon;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.table.TableCellRenderer;

public class SortButtonRenderer implements TableCellRenderer
{
    public static final int NONE = 0;
    public static final int DOWN = 1;
    public static final int UP = 2;
    private int pressedColumn;
    private JButton normalButton;
    private JButton ascendingButton;
    private JButton descendingButton;
    private boolean useLabels;
    private JLabel normalLabel;
    private JLabel ascendingLabel;
    private JLabel descendingLabel;
    
    public SortButtonRenderer() {
        this.pressedColumn = -1;
        this.pressedColumn = -1;
        this.useLabels = UIManager.getLookAndFeel().getID().equals("Aqua");
        final Border border = UIManager.getBorder("TableHeader.cellBorder");
        if (this.useLabels) {
            (this.normalLabel = new JLabel()).setHorizontalAlignment(10);
            (this.ascendingLabel = new JLabel()).setHorizontalAlignment(10);
            this.ascendingLabel.setHorizontalTextPosition(2);
            this.ascendingLabel.setIcon(new BevelArrowIcon(1, false, false));
            (this.descendingLabel = new JLabel()).setHorizontalAlignment(10);
            this.descendingLabel.setHorizontalTextPosition(2);
            this.descendingLabel.setIcon(new BevelArrowIcon(0, false, false));
            this.normalLabel.setBorder(border);
            this.ascendingLabel.setBorder(border);
            this.descendingLabel.setBorder(border);
        }
        else {
            (this.normalButton = new JButton()).setMargin(new Insets(0, 0, 0, 0));
            this.normalButton.setHorizontalAlignment(10);
            (this.ascendingButton = new JButton()).setMargin(new Insets(0, 0, 0, 0));
            this.ascendingButton.setHorizontalAlignment(10);
            this.ascendingButton.setHorizontalTextPosition(2);
            this.ascendingButton.setIcon(new BevelArrowIcon(1, false, false));
            this.ascendingButton.setPressedIcon(new BevelArrowIcon(1, false, true));
            (this.descendingButton = new JButton()).setMargin(new Insets(0, 0, 0, 0));
            this.descendingButton.setHorizontalAlignment(10);
            this.descendingButton.setHorizontalTextPosition(2);
            this.descendingButton.setIcon(new BevelArrowIcon(0, false, false));
            this.descendingButton.setPressedIcon(new BevelArrowIcon(0, false, true));
            this.normalButton.setBorder(border);
            this.ascendingButton.setBorder(border);
            this.descendingButton.setBorder(border);
        }
    }
    
    public Component getTableCellRendererComponent(final JTable table, final Object o, final boolean b, final boolean b2, final int n, final int n2) {
        SwingConstants swingConstants = this.useLabels ? this.normalLabel : this.normalButton;
        final int convertColumnIndexToModel = table.convertColumnIndexToModel(n2);
        if (table != null) {
            final SortableTableModel sortableTableModel = (SortableTableModel)table.getModel();
            if (sortableTableModel.getSortingColumn() == convertColumnIndexToModel) {
                if (sortableTableModel.getAscending()) {
                    swingConstants = (this.useLabels ? this.ascendingLabel : this.ascendingButton);
                }
                else {
                    swingConstants = (this.useLabels ? this.descendingLabel : this.descendingButton);
                }
            }
        }
        final JTableHeader tableHeader = table.getTableHeader();
        final boolean b3 = convertColumnIndexToModel == this.pressedColumn;
        if (tableHeader != null) {
            ((JComponent)swingConstants).setForeground(tableHeader.getForeground());
            ((JComponent)swingConstants).setBackground(tableHeader.getBackground());
            ((JComponent)swingConstants).setFont(tableHeader.getFont());
        }
        if (this.useLabels) {
            ((JLabel)swingConstants).setText((o == null) ? "" : o.toString());
        }
        else {
            final JButton button = (JButton)swingConstants;
            button.setText((o == null) ? "" : o.toString());
            button.getModel().setPressed(b3);
            button.getModel().setArmed(b3);
        }
        return (Component)swingConstants;
    }
    
    public void setPressedColumn(final int pressedColumn) {
        this.pressedColumn = pressedColumn;
    }
}
