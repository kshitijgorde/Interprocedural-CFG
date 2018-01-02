// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.ui;

import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.JTable;

public class SortableTable extends JTable
{
    private SortableTableHeaderListener headerListener;
    
    public SortableTable(final SortableTableModel sortableTableModel) {
        super(sortableTableModel);
        final SortButtonRenderer headerRenderer = new SortButtonRenderer();
        final TableColumnModel columnModel = this.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); ++i) {
            columnModel.getColumn(i).setHeaderRenderer(headerRenderer);
        }
        final JTableHeader tableHeader = this.getTableHeader();
        tableHeader.addMouseListener(this.headerListener = new SortableTableHeaderListener(sortableTableModel, headerRenderer));
        tableHeader.addMouseMotionListener(this.headerListener);
        sortableTableModel.sortByColumn(0, true);
    }
    
    public void setSortableModel(final SortableTableModel sortableTableModel) {
        super.setModel(sortableTableModel);
        this.headerListener.setTableModel(sortableTableModel);
        final SortButtonRenderer headerRenderer = new SortButtonRenderer();
        final TableColumnModel columnModel = this.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); ++i) {
            columnModel.getColumn(i).setHeaderRenderer(headerRenderer);
        }
        sortableTableModel.sortByColumn(0, true);
    }
}
