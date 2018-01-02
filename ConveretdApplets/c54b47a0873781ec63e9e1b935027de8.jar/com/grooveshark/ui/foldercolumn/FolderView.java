// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.ui.foldercolumn;

import java.util.Collection;
import java.util.Arrays;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import javax.swing.table.TableColumn;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import com.grooveshark.ui.foldertree.FileSource;
import java.io.File;
import com.grooveshark.ui.table.SingleColumnTableModel;
import javax.swing.JTable;
import javax.swing.JPanel;

public class FolderView extends JPanel
{
    private static final long serialVersionUID = -5705231702750753328L;
    private String currentPath;
    private JTable table;
    private SingleColumnTableModel<File> model;
    private FileSource fileSource;
    
    public FolderView(final FileSource fileSource) {
        (this.model = new SingleColumnTableModel<File>()).setEditable(false);
        this.fileSource = fileSource;
        (this.table = new JTable(this.model)).setTableHeader(null);
        this.table.setShowGrid(false);
        this.table.setRowHeight(24);
        this.table.setBackground(Color.WHITE);
        final TableColumn songColumn = this.table.getColumnModel().getColumn(0);
        songColumn.setCellRenderer(new FolderRenderer(fileSource));
        final JScrollPane pane = new JScrollPane(this.table, 20, 31);
        pane.getViewport().setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        this.add(pane, "Center");
    }
    
    public void addMouseListener(final MouseListener listener) {
        this.table.addMouseListener(listener);
    }
    
    public void addKeyListener(final KeyListener listener) {
        this.table.addKeyListener(listener);
    }
    
    public void displayRoots() {
        this.listFiles("ROOTS");
    }
    
    public void displayFolder(final File folder) {
        if (folder == null) {
            this.model.clear();
            return;
        }
        this.listFiles(folder.getAbsolutePath());
    }
    
    public String getCurrentPath() {
        return this.currentPath;
    }
    
    public File getSelectedFolder() {
        final int index = this.table.getSelectedRow();
        if (index < 0) {
            return null;
        }
        return this.model.getValueAt(index);
    }
    
    private void listFiles(final String path) {
        this.currentPath = path;
        this.model.clear();
        final File[] files = this.fileSource.listChildren(path);
        Arrays.sort(files);
        this.model.addAll(Arrays.asList(files));
        System.out.println(String.format("Loaded %s files for %s", files.length, path));
    }
    
    public void requestFocus() {
        this.table.requestFocus();
    }
    
    public void selectFolder(final File folder) {
        final int index = this.model.indexOf(folder);
        this.select(index);
    }
    
    private void select(final int index) {
        this.table.getSelectionModel().setSelectionInterval(index, index);
        this.table.scrollRectToVisible(this.table.getCellRect(index, this.table.getColumnCount(), true));
    }
}
