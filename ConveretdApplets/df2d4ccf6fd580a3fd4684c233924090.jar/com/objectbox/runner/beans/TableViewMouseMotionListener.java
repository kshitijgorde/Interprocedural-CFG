// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.beans;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class TableViewMouseMotionListener extends MouseMotionAdapter
{
    TableView tableview;
    boolean cursorstate;
    
    public TableViewMouseMotionListener(final TableView tableview) {
        this.tableview = null;
        this.cursorstate = false;
        this.tableview = tableview;
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
}
