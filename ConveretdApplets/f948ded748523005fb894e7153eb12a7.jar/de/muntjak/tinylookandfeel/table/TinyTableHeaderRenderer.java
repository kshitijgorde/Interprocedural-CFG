// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel.table;

import java.awt.Graphics;
import javax.swing.table.TableModel;
import javax.swing.table.JTableHeader;
import javax.swing.LookAndFeel;
import java.awt.Color;
import de.muntjak.tinylookandfeel.Theme;
import javax.swing.UIManager;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.Icon;
import javax.swing.plaf.UIResource;
import javax.swing.table.DefaultTableCellRenderer;

public class TinyTableHeaderRenderer extends DefaultTableCellRenderer implements UIResource
{
    private static final Icon arrowNo;
    private static final Icon[][] arrows;
    private int[] horizontalAlignments;
    
    public TinyTableHeaderRenderer() {
        this.setHorizontalAlignment(0);
        this.setHorizontalTextPosition(2);
    }
    
    public void setHorizontalAlignments(final int[] horizontalAlignments) {
        this.horizontalAlignments = horizontalAlignments;
    }
    
    public Component getTableCellRendererComponent(final JTable table, final Object o, final boolean b, final boolean b2, final int n, final int n2) {
        final LookAndFeel lookAndFeel = UIManager.getLookAndFeel();
        if (lookAndFeel == null || !"TinyLookAndFeel".equals(lookAndFeel.getName())) {
            if (table != null) {
                final JTableHeader tableHeader = table.getTableHeader();
                if (tableHeader != null) {
                    this.setBackground(tableHeader.getBackground());
                    this.setForeground(tableHeader.getForeground());
                    this.setFont(tableHeader.getFont());
                }
            }
            this.setIcon(null);
            this.setText((o == null) ? "" : o.toString());
            this.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
            return this;
        }
        boolean b3 = false;
        Icon arrowNo = TinyTableHeaderRenderer.arrowNo;
        int n3 = 1;
        int n4 = -1;
        boolean b4 = false;
        if (table != null) {
            final JTableHeader tableHeader2 = table.getTableHeader();
            if (tableHeader2 != null) {
                final Object clientProperty = tableHeader2.getClientProperty("rolloverColumn");
                if (clientProperty != null && (int)clientProperty == n2) {
                    b3 = true;
                }
                final Object clientProperty2 = tableHeader2.getClientProperty("sortedColumn");
                if (clientProperty2 != null) {
                    final int[] array = (int[])clientProperty2;
                    n4 = -1;
                    for (int i = 0; i < array.length; ++i) {
                        if (array[i] == n2) {
                            n4 = i;
                        }
                    }
                    if (n4 > -1) {
                        b4 = true;
                        final Object clientProperty3 = tableHeader2.getClientProperty("sortingDirection");
                        if (clientProperty3 != null) {
                            n3 = ((int[])clientProperty3)[n4];
                        }
                    }
                }
                if (b3) {
                    this.setBackground(Theme.tableHeaderRolloverBackColor[Theme.style].getColor());
                }
                else {
                    this.setBackground(tableHeader2.getBackground());
                }
                this.setForeground(tableHeader2.getForeground());
                this.setFont(tableHeader2.getFont());
            }
            final TableModel model = table.getModel();
            if (model instanceof SortableTableData) {
                if (b4) {
                    final int min = Math.min(3, n4);
                    if (n3 == 1) {
                        if (TinyTableHeaderRenderer.arrows[0][min] == null) {
                            TinyTableHeaderRenderer.arrows[0][min] = new Arrow(false, n4);
                        }
                        arrowNo = TinyTableHeaderRenderer.arrows[0][min];
                    }
                    else {
                        if (TinyTableHeaderRenderer.arrows[1][min] == null) {
                            TinyTableHeaderRenderer.arrows[1][min] = new Arrow(true, n4);
                        }
                        arrowNo = TinyTableHeaderRenderer.arrows[1][min];
                    }
                }
                else if (!((SortableTableData)model).isColumnSortable(table.getColumnModel().getColumn(n2).getModelIndex())) {
                    arrowNo = null;
                }
            }
            else {
                arrowNo = null;
                this.setToolTipText(null);
            }
        }
        this.setIcon(arrowNo);
        this.setText((o == null) ? "" : o.toString());
        if (b3) {
            this.setBorder(UIManager.getBorder("TableHeader.cellRolloverBorder"));
        }
        else {
            this.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        }
        final int modelIndex = table.getColumnModel().getColumn(n2).getModelIndex();
        if (this.horizontalAlignments != null && this.horizontalAlignments.length > modelIndex) {
            this.setHorizontalAlignment(this.horizontalAlignments[modelIndex]);
        }
        return this;
    }
    
    static {
        arrowNo = new Arrow(true, -1);
        arrows = new Icon[2][4];
    }
    
    private static class Arrow implements Icon
    {
        private static final int height = 11;
        private boolean descending;
        private int priority;
        
        public Arrow(final boolean descending, final int n) {
            this.descending = descending;
            this.priority = Math.min(3, n);
        }
        
        public void paintIcon(final Component component, final Graphics graphics, final int n, final int n2) {
            if (this.priority == -1) {
                return;
            }
            final int priority = this.priority;
            final int n3 = (6 + this.priority) / 2;
            graphics.translate(n + priority, n2 + n3);
            graphics.setColor(Theme.tableHeaderArrowColor[Theme.style].getColor());
            if (this.descending) {
                switch (this.priority) {
                    case 0: {
                        graphics.drawLine(4, 4, 4, 4);
                        graphics.drawLine(3, 3, 5, 3);
                        graphics.drawLine(2, 2, 6, 2);
                        graphics.drawLine(1, 1, 7, 1);
                        graphics.drawLine(0, 0, 8, 0);
                        break;
                    }
                    case 1: {
                        graphics.drawLine(3, 3, 3, 3);
                        graphics.drawLine(2, 2, 4, 2);
                        graphics.drawLine(1, 1, 5, 1);
                        graphics.drawLine(0, 0, 6, 0);
                        break;
                    }
                    case 2: {
                        graphics.drawLine(2, 2, 2, 2);
                        graphics.drawLine(1, 1, 3, 1);
                        graphics.drawLine(0, 0, 4, 0);
                        break;
                    }
                    default: {
                        graphics.drawLine(1, 1, 1, 1);
                        graphics.drawLine(0, 0, 2, 0);
                        break;
                    }
                }
            }
            else {
                switch (this.priority) {
                    case 0: {
                        graphics.drawLine(4, 0, 4, 0);
                        graphics.drawLine(3, 1, 5, 1);
                        graphics.drawLine(2, 2, 6, 2);
                        graphics.drawLine(1, 3, 7, 3);
                        graphics.drawLine(0, 4, 8, 4);
                        break;
                    }
                    case 1: {
                        graphics.drawLine(3, 0, 3, 0);
                        graphics.drawLine(2, 1, 4, 1);
                        graphics.drawLine(1, 2, 5, 2);
                        graphics.drawLine(0, 3, 6, 3);
                        break;
                    }
                    case 2: {
                        graphics.drawLine(2, 0, 2, 0);
                        graphics.drawLine(1, 1, 3, 1);
                        graphics.drawLine(0, 2, 4, 2);
                        break;
                    }
                    default: {
                        graphics.drawLine(1, 0, 1, 0);
                        graphics.drawLine(0, 1, 2, 1);
                        break;
                    }
                }
            }
            graphics.translate(-(n + priority), -(n2 + n3));
        }
        
        public int getIconWidth() {
            return 9;
        }
        
        public int getIconHeight() {
            return 11;
        }
    }
}
