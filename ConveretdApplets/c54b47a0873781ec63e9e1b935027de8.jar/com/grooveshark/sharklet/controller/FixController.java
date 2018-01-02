// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.sharklet.controller;

import com.grooveshark.sharklet.Song;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class FixController
{
    private TableRowSorter<TableModel> sorter;
    private boolean isFiltering;
    
    public FixController(final TableRowSorter<TableModel> sorter) {
        this.sorter = sorter;
    }
    
    public void toggleFilter() {
        this.isFiltering = !this.isFiltering;
        if (this.isFiltering) {
            this.sorter.setRowFilter(new RowFilter<TableModel, Integer>() {
                public boolean include(final Entry<? extends TableModel, ? extends Integer> entry) {
                    final Song s = (Song)entry.getValue(0);
                    return s.isInvalid();
                }
            });
        }
        else {
            this.sorter.setRowFilter(null);
        }
    }
    
    public boolean isFiltering() {
        return this.isFiltering;
    }
}
