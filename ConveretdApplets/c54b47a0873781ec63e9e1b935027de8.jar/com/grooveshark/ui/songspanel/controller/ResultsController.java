// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.ui.songspanel.controller;

import com.grooveshark.sharklet.Sharklet;
import javax.swing.event.TableModelEvent;
import javax.swing.event.RowSorterEvent;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.event.TableModelListener;
import javax.swing.event.RowSorterListener;

public class ResultsController implements RowSorterListener, TableModelListener
{
    private static String RESULTS;
    private JLabel label;
    private JTable table;
    
    public ResultsController(final JTable table, final JLabel label) {
        this.label = label;
        this.table = table;
    }
    
    public void sorterChanged(final RowSorterEvent e) {
        this.update();
    }
    
    public void tableChanged(final TableModelEvent e) {
        this.update();
    }
    
    private void update() {
        this.label.setText(String.format(ResultsController.RESULTS, this.table.getRowCount()));
    }
    
    static {
        ResultsController.RESULTS = Sharklet.getText("RESULTS");
    }
}
