// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.table;

import org.xmodel.xpath.XPath;
import org.xmodel.IModelObject;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.util.List;
import org.xidget.ifeature.tree.IColumnWidthFeature;
import javax.swing.JTable;
import java.util.HashMap;
import org.xidget.tree.Row;
import org.xmodel.xpath.expression.StatefulContext;
import java.util.Map;
import org.xidget.IXidget;
import org.xidget.ifeature.model.IPartialSelectionWidgetFeature;
import org.xidget.ifeature.model.ISelectionWidgetFeature;
import org.xidget.ifeature.tree.ITreeWidgetFeature;
import org.xidget.ifeature.table.ITableWidgetFeature;

public class JTableWidgetFeature implements ITableWidgetFeature, ITreeWidgetFeature, ISelectionWidgetFeature, IPartialSelectionWidgetFeature
{
    private IXidget xidget;
    private Map<StatefulContext, Row> map;
    
    public JTableWidgetFeature(final IXidget xidget) {
        this.xidget = xidget;
        this.map = new HashMap<StatefulContext, Row>();
    }
    
    @Override
    public void setShowGrid(final boolean showGrid) {
        final JTable table = this.xidget.getFeature(JTable.class);
        if (table != null) {
            table.setShowGrid(showGrid);
        }
    }
    
    @Override
    public void insertRows(final Row row, final int n, final Row[] array) {
        final JTable table = this.xidget.getFeature(JTable.class);
        this.createColumns(table);
        ((CustomTableModel)table.getModel()).insertRows(n, array);
        final IColumnWidthFeature columnWidthFeature = this.xidget.getFeature(IColumnWidthFeature.class);
        for (int i = 0; i < array.length; ++i) {
            columnWidthFeature.insertRow(n + i);
        }
    }
    
    @Override
    public void removeRows(final Row row, final int n, final Row[] array, final boolean b) {
        ((CustomTableModel)this.xidget.getFeature(JTable.class).getModel()).removeRows(n, array.length);
        if (!b) {
            final IColumnWidthFeature columnWidthFeature = this.xidget.getFeature(IColumnWidthFeature.class);
            for (int i = 0; i < array.length; ++i) {
                columnWidthFeature.removeRow(n);
            }
        }
    }
    
    @Override
    public void commit(final Row row) {
        ((CustomTableModel)this.xidget.getFeature(JTable.class).getModel()).commit(row);
    }
    
    @Override
    public boolean isVisible(final Row row) {
        return true;
    }
    
    @Override
    public Row findRow(final StatefulContext context) {
        if (!this.map.containsKey(context)) {
            final Row root = ((CustomTableModel)this.xidget.getFeature(JTable.class).getModel()).getRoot();
            root.setContext(context);
            this.map.put(context, root);
            return root;
        }
        return this.map.get(context);
    }
    
    @Override
    public List<Row> getChildren(final Row row) {
        return row.getChildren();
    }
    
    @Override
    public void setTitle(final int n, final String s) {
        final JTable table = this.xidget.getFeature(JTable.class);
        final CustomTableModel customTableModel = (CustomTableModel)table.getModel();
        customTableModel.setColumnName(n, s);
        if (s.length() > 0) {
            table.getTableHeader().setVisible(true);
        }
        customTableModel.fireTableStructureChanged();
        this.xidget.getFeature(IColumnWidthFeature.class).setColumnTitle(n, s);
    }
    
    @Override
    public void setColumnWidth(final int n, final int n2) {
        final TableColumnModel columnModel = this.xidget.getFeature(JTable.class).getColumnModel();
        if (n < columnModel.getColumnCount()) {
            final TableColumn column = columnModel.getColumn(n);
            column.setMinWidth(0);
            column.setMaxWidth(Integer.MAX_VALUE);
            column.setPreferredWidth(n2);
            column.setWidth(n2);
        }
    }
    
    @Override
    public void updateCell(final Row row, final int n) {
        final CustomTableModel customTableModel = (CustomTableModel)this.xidget.getFeature(JTable.class).getModel();
        final int rowIndex = this.findRowIndex(customTableModel, row);
        customTableModel.fireTableCellUpdated(rowIndex, n);
        this.xidget.getFeature(IColumnWidthFeature.class).setColumnText(rowIndex, n, row.getCell(n).text);
    }
    
    private int findRowIndex(final CustomTableModel customTableModel, final Row row) {
        final List<Row> rows = customTableModel.getRows();
        if (rows.size() == 0) {
            return -1;
        }
        if (rows.get(rows.size() - 1) == row) {
            return rows.size() - 1;
        }
        return customTableModel.getRows().indexOf(row);
    }
    
    @Override
    public void select(final Object o) {
        final JTable table = this.xidget.getFeature(JTable.class);
        final int node = this.findNode(((CustomTableModel)table.getModel()).getRows(), o);
        if (node >= 0) {
            table.addRowSelectionInterval(node, node);
        }
    }
    
    @Override
    public void deselect(final Object o) {
        final JTable table = this.xidget.getFeature(JTable.class);
        final int node = this.findNode(((CustomTableModel)table.getModel()).getRows(), o);
        if (node >= 0) {
            table.removeRowSelectionInterval(node, node);
        }
    }
    
    @Override
    public void setSelection(final List<?> list) {
        final JTable table = this.xidget.getFeature(JTable.class);
        final List<Row> rows = ((CustomTableModel)table.getModel()).getRows();
        final Iterator<?> iterator = list.iterator();
        while (iterator.hasNext()) {
            final int node = this.findNode(rows, iterator.next());
            if (node >= 0) {
                table.addRowSelectionInterval(node, node);
            }
        }
    }
    
    @Override
    public List<?> getSelection() {
        final JTable table = this.xidget.getFeature(JTable.class);
        final List<Row> rows = ((CustomTableModel)table.getModel()).getRows();
        final ArrayList<IModelObject> list = new ArrayList<IModelObject>();
        int[] selectedRows;
        for (int length = (selectedRows = table.getSelectedRows()).length, i = 0; i < length; ++i) {
            list.add(rows.get(selectedRows[i]).getContext().getObject());
        }
        return list;
    }
    
    @Override
    public void select(final IXidget xidget, final Object o) {
        final JTable table = this.xidget.getFeature(JTable.class);
        final int node = this.findNode(xidget, ((CustomTableModel)table.getModel()).getRows(), o);
        if (node >= 0) {
            table.addRowSelectionInterval(node, node);
        }
    }
    
    @Override
    public void deselect(final IXidget xidget, final Object o) {
        final JTable table = this.xidget.getFeature(JTable.class);
        final int node = this.findNode(xidget, ((CustomTableModel)table.getModel()).getRows(), o);
        if (node >= 0) {
            table.removeRowSelectionInterval(node, node);
        }
    }
    
    @Override
    public void setSelection(final IXidget xidget, final List<?> list) {
        final JTable table = this.xidget.getFeature(JTable.class);
        final List<Row> rows = ((CustomTableModel)table.getModel()).getRows();
        final Iterator<?> iterator = list.iterator();
        while (iterator.hasNext()) {
            final int node = this.findNode(xidget, rows, iterator.next());
            if (node >= 0) {
                table.addRowSelectionInterval(node, node);
            }
        }
    }
    
    @Override
    public List<?> getSelection(final IXidget xidget) {
        final JTable table = this.xidget.getFeature(JTable.class);
        final List<Row> rows = ((CustomTableModel)table.getModel()).getRows();
        final ArrayList<IModelObject> list = new ArrayList<IModelObject>();
        int[] selectedRows;
        for (int length = (selectedRows = table.getSelectedRows()).length, i = 0; i < length; ++i) {
            final Row row = rows.get(selectedRows[i]);
            if (row.getTable() == xidget) {
                list.add(row.getContext().getObject());
            }
        }
        return list;
    }
    
    private int findNode(final List<Row> list, final Object o) {
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i).getContext().getObject().equals(o)) {
                return i;
            }
        }
        return -1;
    }
    
    private int findNode(final IXidget xidget, final List<Row> list, final Object o) {
        for (int i = 0; i < list.size(); ++i) {
            final Row row = list.get(i);
            if (row.getTable() == xidget && row.getContext().getObject().equals(o)) {
                return i;
            }
        }
        return -1;
    }
    
    private void createColumns(final JTable table) {
        final IModelObject config = this.xidget.getConfig();
        final CustomTableModel customTableModel = (CustomTableModel)table.getModel();
        int configuredColumnCount;
        int i;
        int n;
        for (configuredColumnCount = getConfiguredColumnCount(config), n = (i = table.getColumnCount()); i < configuredColumnCount; ++i) {
            customTableModel.setColumnName(i, "");
            table.addColumn(new TableColumn(i));
        }
        if (n < configuredColumnCount) {
            customTableModel.fireTableStructureChanged();
        }
    }
    
    private static int getConfiguredColumnCount(final IModelObject modelObject) {
        int n = 0;
        for (final IModelObject modelObject2 : XPath.createExpression("nested-or-self::table").query(modelObject, null)) {
            final int numberOfChildren = modelObject2.getNumberOfChildren("column");
            final int numberOfChildren2 = modelObject2.getNumberOfChildren("cell");
            if (n < numberOfChildren) {
                n = numberOfChildren;
            }
            if (n < numberOfChildren2) {
                n = numberOfChildren2;
            }
        }
        return n;
    }
}
