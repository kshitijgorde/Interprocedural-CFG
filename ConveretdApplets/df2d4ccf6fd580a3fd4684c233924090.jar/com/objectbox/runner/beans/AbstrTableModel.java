// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.beans;

public abstract class AbstrTableModel
{
    private TableView view;
    private TableCellAttribute cellattrib;
    
    public AbstrTableModel() {
        this.view = null;
        this.cellattrib = null;
        this.cellattrib = new TableCellAttribute();
    }
    
    public abstract String getCell(final int p0, final int p1);
    
    public TableCellAttribute getCellAttribute(final int n, final int n2) {
        return this.cellattrib;
    }
    
    public abstract String[] getHeaders();
    
    public abstract int getNumberOfColumns();
    
    public abstract int getNumberOfRows();
    
    public TableView getView() {
        return this.view;
    }
    
    public void setCell(final int n, final int n2, final String s) {
    }
    
    public void setView(final TableView view) {
        this.view = view;
    }
}
