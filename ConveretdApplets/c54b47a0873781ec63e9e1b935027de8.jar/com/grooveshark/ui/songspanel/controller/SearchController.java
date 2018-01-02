// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.ui.songspanel.controller;

import javax.swing.RowFilter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.KeyAdapter;

public class SearchController extends KeyAdapter
{
    private TableRowSorter<TableModel> sorter;
    private JTextField searchField;
    
    public SearchController(final TableRowSorter<TableModel> sorter) {
        this.sorter = sorter;
    }
    
    public void addToField(final JTextField searchField) {
        (this.searchField = searchField).addKeyListener(this);
    }
    
    public void keyTyped(final KeyEvent e) {
        final String query = this.searchField.getText().toLowerCase();
        RowFilter<Object, Object> filter = null;
        if (query.length() > 0) {
            filter = RowFilter.regexFilter(query, new int[0]);
        }
        this.sorter.setRowFilter(filter);
    }
}
