// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.ui.fileexplorer;

import com.grooveshark.ui.foldertree.FileSelectionState;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import javax.swing.table.TableColumn;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.event.MouseListener;
import java.io.File;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.io.FileFilter;
import javax.swing.JPanel;

public class FileExplorer extends JPanel
{
    private static final long serialVersionUID = 7147156270914019699L;
    private static final int ROW_HEIGHT = 30;
    private static final FileFilter MP3_FILTER;
    private FileExplorerCell cell;
    private FileExplorerModel model;
    private JTable table;
    
    public FileExplorer(final FileExplorerModel model) {
        this.model = model;
        this.table = this.createFilesView(model);
        final JScrollPane scrollPane = new JScrollPane(this.table);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        this.add(scrollPane, "Center");
    }
    
    private JTable createFilesView(final FileExplorerModel model) {
        this.cell = new FileExplorerCell();
        final FileExplorerRenderer renderer = new FileExplorerRenderer(model, this.cell);
        final FileExplorerEditor editor = new FileExplorerEditor(model);
        (this.table = new JTable(model)).setSelectionMode(0);
        this.table.setRowHeight(30);
        this.table.setShowGrid(false);
        this.table.setTableHeader(null);
        this.table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                final int row = FileExplorer.this.table.rowAtPoint(e.getPoint());
                final File file = model.getValueAt(row);
                FileExplorer.this.changeState(file);
            }
        });
        this.addEditorRefreshFix(this.table);
        final TableColumn column = this.table.getColumnModel().getColumn(0);
        column.setCellRenderer(renderer);
        column.setCellEditor(editor);
        return this.table;
    }
    
    public void displayFolder(final File folder) {
        this.model.clear();
        final File[] files = folder.listFiles(FileExplorer.MP3_FILTER);
        final boolean hasValidFiles = files != null && files.length > 0;
        if (hasValidFiles) {
            System.out.println("Adding files from " + folder);
            this.addFiles(files);
            System.out.println("Finished adding files from " + folder);
        }
    }
    
    private void addFiles(final File[] files) {
        final ArrayList<File> sortedFiles = new ArrayList<File>();
        sortedFiles.addAll(Arrays.asList(files));
        Collections.sort(sortedFiles, new Comparator<File>() {
            public int compare(final File f1, final File f2) {
                if (f1.isDirectory() && f2.isFile()) {
                    return -1;
                }
                if (f1.isFile() && f2.isDirectory()) {
                    return 1;
                }
                return f1.getName().compareTo(f2.getName());
            }
        });
        this.model.addAll(sortedFiles);
    }
    
    private void addEditorRefreshFix(final JTable table) {
        table.getModel().addTableModelListener(new TableModelListener() {
            public void tableChanged(final TableModelEvent e) {
                final int editingRow = table.getEditingRow();
                if (e.getFirstRow() <= editingRow && editingRow <= e.getLastRow()) {
                    table.editingCanceled(new ChangeEvent(this));
                    table.editCellAt(editingRow, 0);
                }
            }
        });
    }
    
    public void selectAll() {
        final File file = this.model.getValueAt(0);
        if (file != null) {
            this.model.includeFile(file.getParentFile());
            this.model.refresh();
        }
    }
    
    public void deselectAll() {
        final File file = this.model.getValueAt(0);
        if (file != null) {
            this.model.excludeFile(file.getParentFile());
            this.model.refresh();
        }
    }
    
    public int getTotalSelected() {
        int totalSelected = 0;
        for (int i = 0; i < this.model.getRowCount(); ++i) {
            if (this.model.getFileState(this.model.getValueAt(i)).equals(FileSelectionState.SELECTED)) {
                ++totalSelected;
            }
        }
        return totalSelected;
    }
    
    private void changeState(final File file) {
        final FileSelectionState state = this.model.getFileState(file);
        if (state.equals(FileSelectionState.SELECTED) || state.equals(FileSelectionState.PARTIALLY_SELECTED)) {
            this.model.excludeFile(file);
        }
        else {
            this.model.includeFile(file);
        }
    }
    
    static {
        MP3_FILTER = new FileFilter() {
            public boolean accept(final File file) {
                return (file.isDirectory() || file.getName().toLowerCase().endsWith(".mp3")) && !file.isHidden();
            }
        };
    }
}
