// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.ui.about;

import java.awt.Component;
import javax.swing.JScrollPane;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.table.TableModel;
import javax.swing.JTable;
import javax.swing.JPanel;

public class LibraryPanel extends JPanel
{
    private JTable table;
    private TableModel model;
    
    public LibraryPanel(final List list) {
        this.setLayout(new BorderLayout());
        this.model = new LibraryTableModel(list);
        this.table = new JTable(this.model);
        this.add(new JScrollPane(this.table));
    }
}
