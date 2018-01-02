// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.beans;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class TableViewMouseListener extends MouseAdapter
{
    TableView tableview;
    
    public TableViewMouseListener(final TableView tableview) {
        this.tableview = null;
        this.tableview = tableview;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        try {
            this.tableview.requestFocus();
            final Point current = this.tableview.getCurrent();
            mouseEvent.getModifiers();
            final Point mapScreenToGrid = this.tableview.mapScreenToGrid(new Point(mouseEvent.getX(), mouseEvent.getY()));
            final int x = current.x;
            final int y = current.y;
            if (mouseEvent.getClickCount() >= 1) {
                if (this.tableview.getModifier() == 0) {
                    if (this.tableview.row_select_hash.size() > 0) {
                        this.tableview.repaint();
                    }
                    this.tableview.row_select_hash.clear();
                    this.tableview.unhighligthCell(x, y);
                    this.tableview.setCurrent(mapScreenToGrid.x, mapScreenToGrid.y);
                    this.tableview.highligthCell(mapScreenToGrid.x, mapScreenToGrid.y);
                }
                else if (mouseEvent.isShiftDown()) {
                    int y2 = mapScreenToGrid.y;
                    int y3;
                    if ((y3 = y) <= mapScreenToGrid.y) {
                        y2 = y;
                        y3 = mapScreenToGrid.y;
                    }
                    if (this.tableview.row_select_hash.size() == 0) {
                        this.tableview.row_select_hash.put(new Point(0, y), "");
                    }
                    for (int i = y2; i < y3; ++i) {
                        this.tableview.row_select_hash.put(new Point(0, i), "");
                        this.tableview.highligthCell(mapScreenToGrid.x, i);
                    }
                    this.tableview.setCurrent(mapScreenToGrid.x, y3);
                    this.tableview.highligthCell(mapScreenToGrid.x, y3);
                }
                else if (mouseEvent.isControlDown()) {
                    if (this.tableview.row_select_hash.size() == 0) {
                        this.tableview.row_select_hash.put(new Point(0, y), "");
                    }
                    if (this.tableview.row_select_hash.get(new Point(0, mapScreenToGrid.y)) != null) {
                        this.tableview.row_select_hash.remove(new Point(0, mapScreenToGrid.y));
                        this.tableview.unhighligthCell(mapScreenToGrid.x, mapScreenToGrid.y);
                    }
                    else {
                        this.tableview.row_select_hash.put(new Point(0, mapScreenToGrid.y), "");
                        this.tableview.setCurrent(mapScreenToGrid.x, mapScreenToGrid.y);
                        this.tableview.highligthCell(mapScreenToGrid.x, mapScreenToGrid.y);
                    }
                }
                this.tableview.fireCellChoosen(new CellChoosenEvent(mouseEvent.getSource(), mapScreenToGrid.x, mapScreenToGrid.y));
                if (y > 0) {
                    this.tableview.startCellEditor(mapScreenToGrid.x, mapScreenToGrid.y);
                }
                if (mouseEvent.getY() < this.tableview.getCellheight() && mouseEvent.getModifiers() == 4) {
                    this.tableview.autoFitColumn(mapScreenToGrid.x);
                    this.tableview.repaint();
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
