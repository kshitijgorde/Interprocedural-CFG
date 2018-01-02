// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.sharklet.ui.mac;

import com.grooveshark.sharklet.Sharklet;
import javax.swing.event.TableModelEvent;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.event.TableModelListener;

public class MacResultsController implements TableModelListener
{
    private static String RESULTS;
    private JLabel label;
    private JTable table;
    
    public MacResultsController(final JTable table, final JLabel label) {
        this.label = label;
        this.table = table;
    }
    
    public void tableChanged(final TableModelEvent e) {
        this.update();
    }
    
    private void update() {
        this.label.setText(String.format(MacResultsController.RESULTS, this.table.getRowCount()));
    }
    
    static {
        MacResultsController.RESULTS = Sharklet.getText("RESULTS");
    }
}
