// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.beans;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class TableViewKeyListener extends KeyAdapter
{
    TableView tableview;
    
    public TableViewKeyListener(final TableView tableview) {
        this.tableview = null;
        this.tableview = tableview;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        final Point current = this.tableview.getCurrent();
        final int x;
        int n = x = current.x;
        final int y;
        int n2 = y = current.y;
        final int keyCode = keyEvent.getKeyCode();
        switch (keyCode) {
            case 16: {
                this.tableview.setModifier(1);
                return;
            }
            case 17: {
                this.tableview.setModifier(2);
                return;
            }
            case 37: {
                --n;
                break;
            }
            case 39: {
                ++n;
                break;
            }
            case 38: {
                --n2;
                break;
            }
            case 40: {
                ++n2;
                break;
            }
            case 34: {
                final int n3 = n2 + (this.tableview.getSize().height / this.tableview.getCellheight() - 2);
                n2 = ((n3 >= this.tableview.getNumberOfRows()) ? (this.tableview.getNumberOfRows() - 1) : n3);
                break;
            }
            case 33: {
                final int n4 = n2 - (this.tableview.getSize().height / this.tableview.getCellheight() - 2);
                n2 = ((n4 < 1) ? 1 : n4);
                break;
            }
            case 36: {
                n2 = 1;
                break;
            }
            case 35: {
                n2 = this.tableview.getNumberOfRows() - 1;
                break;
            }
            case 10: {
                this.tableview.fireCellselected(new CellselectedEvent(keyEvent.getSource(), n, n2));
                this.tableview.startCellEditor(n, n2);
                return;
            }
            default: {
                final int search = this.tableview.search(String.valueOf((char)keyCode));
                if (search != -1) {
                    n2 = search;
                    break;
                }
                break;
            }
        }
        final int numberOfcols = this.tableview.getNumberOfcols();
        final int numberOfRows = this.tableview.getNumberOfRows();
        if (n2 >= 0 && n >= 0 && n < numberOfcols && n2 < numberOfRows && this.tableview.getModel().getCellAttribute(n, n2).getIsselectable()) {
            if (this.tableview.getModifier() == 0) {
                if (this.tableview.row_select_hash.size() > 0) {
                    this.tableview.repaint();
                }
                this.tableview.row_select_hash.clear();
                this.tableview.unhighligthCell(x, y);
                this.tableview.setCurrent(n, n2);
                this.tableview.highligthCell(n, n2);
            }
            else if (this.tableview.getModifier() == 2) {
                if (this.tableview.row_select_hash.get(new Point(0, n2)) != null) {
                    this.tableview.row_select_hash.remove(new Point(0, n2));
                    this.tableview.unhighligthCell(n, n2);
                }
                else {
                    this.tableview.row_select_hash.put(new Point(0, n2), "");
                    this.tableview.setCurrent(n, n2);
                    this.tableview.highligthCell(n, n2);
                }
            }
            else if (this.tableview.getModifier() == 1) {
                this.tableview.row_select_hash.put(new Point(0, n2), "");
                this.tableview.setCurrent(n, n2);
                this.tableview.highligthCell(n, n2);
            }
            this.tableview.fireCellselected(new CellselectedEvent(keyEvent.getSource(), n, n2));
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 16: {
                this.tableview.setModifier(0);
                break;
            }
            case 17: {
                this.tableview.setModifier(0);
                break;
            }
        }
    }
}
