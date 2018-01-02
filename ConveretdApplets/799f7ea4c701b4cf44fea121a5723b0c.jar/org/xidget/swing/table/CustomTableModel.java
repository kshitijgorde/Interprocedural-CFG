// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.table;

import org.xmodel.IModelObject;
import java.util.ArrayList;
import org.xidget.IXidget;
import java.util.List;
import org.xidget.tree.Row;
import javax.swing.table.AbstractTableModel;

public class CustomTableModel extends AbstractTableModel
{
    private Row root;
    private List<Column> columns;
    
    public CustomTableModel(final IXidget xidget) {
        this.root = new Row(xidget);
        this.columns = new ArrayList<Column>(5);
    }
    
    public void setColumnName(final int n, final String title) {
        if (this.columns.size() <= n) {
            for (int i = this.columns.size(); i <= n; ++i) {
                this.columns.add(new Column((Column)null));
            }
        }
        this.columns.get(n).title = title;
    }
    
    public void setColumnImage(final int n, final Object image) {
        if (this.columns.size() <= n) {
            for (int i = this.columns.size(); i <= n; ++i) {
                this.columns.add(new Column((Column)null));
            }
        }
        this.columns.get(n).image = image;
    }
    
    @Override
    public String getColumnName(final int n) {
        if (this.columns.size() <= n) {
            return "";
        }
        return this.columns.get(n).title;
    }
    
    public Object getColumnImage(final int n) {
        return this.columns.get(n).image;
    }
    
    @Override
    public int getColumnCount() {
        return this.columns.size();
    }
    
    public void insertRows(final int n, final Row[] array) {
        this.fireTableRowsInserted(n, n + array.length - 1);
    }
    
    public void removeRows(final int n, final int n2) {
        this.fireTableRowsDeleted(n, n + n2 - 1);
    }
    
    public void commit(final Row row) {
    }
    
    public List<Row> getRows() {
        return this.root.getChildren();
    }
    
    @Override
    public int getRowCount() {
        return this.root.getChildren().size();
    }
    
    public Row getRoot() {
        return this.root;
    }
    
    @Override
    public Object getValueAt(final int n, final int n2) {
        final String text = this.root.getChildren().get(n).getCell(n2).text;
        if (text == null) {
            return "";
        }
        if (text.equalsIgnoreCase("true")) {
            return Boolean.TRUE;
        }
        if (text.equalsIgnoreCase("false")) {
            return Boolean.FALSE;
        }
        return text;
    }
    
    @Override
    public void setValueAt(final Object o, final int n, final int n2) {
        this.root.getChildren().get(n).getCell(n2).text = ((o != null) ? o.toString() : "");
        this.fireTableCellUpdated(n, n2);
    }
    
    @Override
    public boolean isCellEditable(final int n, final int n2) {
        return CustomCellEditor.findEditor(this.root.getChildren().get(n), n2) != null;
    }
    
    @Override
    public Class<?> getColumnClass(final int n) {
        return IModelObject.class;
    }
    
    public Object getIconAt(final int n, final int n2) {
        return this.root.getChildren().get(n).getCell(n2).icon;
    }
    
    private class Column
    {
        public String title;
        public Object image;
    }
}
