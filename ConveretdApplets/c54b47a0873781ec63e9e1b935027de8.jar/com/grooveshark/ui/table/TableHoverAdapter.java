// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.ui.table;

import java.util.Iterator;
import javax.swing.event.TableModelEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JTable;
import java.util.List;
import java.awt.event.MouseAdapter;

public class TableHoverAdapter extends MouseAdapter
{
    private static final int NONE = -1;
    private List<TableHoverListener> listeners;
    private int lastColumnHovered;
    private int lastRowHovered;
    private JTable table;
    
    public TableHoverAdapter() {
        this.listeners = new ArrayList<TableHoverListener>();
        this.lastColumnHovered = -1;
        this.lastRowHovered = -1;
    }
    
    public void addToTable(final JTable table) {
        (this.table = table).addMouseListener(this);
        table.addMouseMotionListener(this);
    }
    
    public void addHoverListener(final TableHoverListener listener) {
        this.listeners.add(listener);
    }
    
    public void mouseMoved(final MouseEvent e) {
        final int row = this.table.rowAtPoint(e.getPoint());
        final int column = this.table.columnAtPoint(e.getPoint());
        this.updateCellHovered(row, column);
    }
    
    public void mouseExited(final MouseEvent e) {
        this.updateCellHovered(-1, -1);
    }
    
    private void updateCellHovered(final int row, final int column) {
        if (this.hasCellHoveredChanged(row, column)) {
            this.notifyListeners(row, column);
            this.resetLastCell();
            this.updateCell(row, column);
            this.storeHoveredCell(row, column);
        }
    }
    
    private boolean hasCellHoveredChanged(final int row, final int column) {
        return this.lastRowHovered != row || this.lastColumnHovered != column;
    }
    
    private void storeHoveredCell(final int row, final int column) {
        this.lastRowHovered = row;
        this.lastColumnHovered = column;
    }
    
    private void updateCell(final int row, final int column) {
        if (row == -1 && column == -1) {
            return;
        }
        final int newHoveredCell = this.table.convertRowIndexToModel(row);
        this.table.tableChanged(new TableModelEvent(this.table.getModel(), newHoveredCell));
    }
    
    private void resetLastCell() {
        if (this.lastRowHovered == -1) {
            return;
        }
        final int oldRow = this.table.convertRowIndexToModel(this.lastRowHovered);
        this.table.tableChanged(new TableModelEvent(this.table.getModel(), oldRow));
    }
    
    private void notifyListeners(final int row, final int column) {
        for (final TableHoverListener listener : this.listeners) {
            listener.cellHovered(row, column);
        }
    }
}
