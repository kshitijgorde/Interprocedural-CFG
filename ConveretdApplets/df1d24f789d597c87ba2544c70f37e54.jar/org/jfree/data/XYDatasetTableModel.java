// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

import javax.swing.table.TableModel;
import javax.swing.table.AbstractTableModel;

public class XYDatasetTableModel extends AbstractTableModel implements TableModel, DatasetChangeListener
{
    XYDataset model;
    
    public XYDatasetTableModel() {
        this.model = null;
    }
    
    public XYDatasetTableModel(final XYDataset dataset) {
        this();
        this.setModel(dataset);
    }
    
    public void setModel(final XYDataset dataset) {
        (this.model = dataset).addChangeListener(this);
        this.fireTableDataChanged();
    }
    
    public int getRowCount() {
        if (this.model == null) {
            return 0;
        }
        return this.model.getItemCount(0);
    }
    
    public int getColumnCount() {
        if (this.model == null) {
            return 0;
        }
        return this.model.getSeriesCount() + 1;
    }
    
    public String getColumnName(final int column) {
        if (this.model == null) {
            return super.getColumnName(column);
        }
        if (column < 1) {
            return "X Value";
        }
        return this.model.getSeriesName(column - 1);
    }
    
    public Object getValueAt(final int row, final int column) {
        if (this.model == null) {
            return null;
        }
        if (column < 1) {
            return this.model.getXValue(0, row);
        }
        return this.model.getYValue(row, column - 1);
    }
    
    public void datasetChanged(final DatasetChangeEvent datasetChangeEvent) {
        this.fireTableDataChanged();
    }
    
    public boolean isCellEditable(final int row, final int column) {
        return false;
    }
    
    public void setValueAt(final Object value, final int row, final int column) {
        if (this.isCellEditable(row, column)) {}
    }
}
