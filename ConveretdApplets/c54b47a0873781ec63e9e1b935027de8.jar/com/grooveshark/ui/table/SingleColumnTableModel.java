// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.ui.table;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class SingleColumnTableModel<T> extends AbstractTableModel
{
    private static final long serialVersionUID = -5385071225160338533L;
    private static final int SINGLE_COLUMN = 1;
    private boolean isEditable;
    private List<T> elements;
    
    public SingleColumnTableModel() {
        this.elements = new LinkedList<T>();
        this.isEditable = true;
    }
    
    public int getColumnCount() {
        return 1;
    }
    
    private boolean isValidUpdate(final Object value, final int row) {
        return value != null;
    }
    
    public int getRowCount() {
        return this.elements.size();
    }
    
    public int indexOf(final T value) {
        return this.elements.indexOf(value);
    }
    
    public T getValueAt(final int row) {
        return this.getValueAt(row, 0);
    }
    
    public List<T> getAll() {
        return this.elements;
    }
    
    public T getValueAt(final int row, final int column) {
        if (this.isValidRow(row)) {
            return this.elements.get(row);
        }
        return null;
    }
    
    private boolean isValidRow(final int row) {
        return row >= 0 && row < this.elements.size();
    }
    
    public boolean isCellEditable(final int row, final int column) {
        return this.isEditable;
    }
    
    public void setValueAt(final T value, final int row) {
        this.setValueAt(value, row, 0);
    }
    
    public void setValueAt(final Object value, final int row, final int column) {
        if (this.isValidRow(row) && this.isValidUpdate(value, row)) {
            this.elements.set(row, (T)value);
            this.fireTableCellUpdated(row, 0);
        }
    }
    
    public void addAll(final Collection<T> items) {
        final int firstRow = this.elements.size();
        this.elements.addAll((Collection<? extends T>)items);
        final int lastRow = this.elements.size();
        this.fireTableRowsInserted(firstRow, lastRow);
    }
    
    public void add(final T item) {
        final int row = this.elements.size();
        this.elements.add(item);
        this.fireTableRowsInserted(row, row);
    }
    
    public T remove(final int row) {
        final T element = this.elements.remove(row);
        this.fireTableRowsDeleted(row, row);
        return element;
    }
    
    public void clear() {
        if (this.elements.isEmpty()) {
            return;
        }
        final int lastRow = this.elements.size() - 1;
        this.elements.clear();
        this.fireTableRowsDeleted(0, lastRow);
    }
    
    public void setEditable(final boolean isEditable) {
        this.isEditable = isEditable;
    }
}
