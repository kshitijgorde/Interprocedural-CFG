// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.ui.fileexplorer;

import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import com.grooveshark.ui.foldertree.FileSelectionState;
import javax.swing.table.TableColumn;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.event.MouseListener;
import java.io.File;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.JTable;
import java.awt.Component;
import javax.swing.JScrollPane;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class FilesList extends JPanel
{
    private static final long serialVersionUID = 5463320851777749903L;
    private static final int ROW_HEIGHT = 30;
    private FileExplorerModel model;
    
    public FilesList(final FileExplorerModel model) {
        this.model = model;
        final JTable selectedList = this.createFilesView(model);
        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(selectedList), "Center");
    }
    
    private JTable createFilesView(final FileExplorerModel model) {
        final FileExplorerCell cell = new FileExplorerCell();
        final FileExplorerRenderer renderer = new FileExplorerRenderer(model, cell);
        final FileExplorerEditor editor = new FileExplorerEditor(model);
        final JTable table = new JTable(model);
        table.setSelectionMode(0);
        table.setRowHeight(30);
        table.setShowGrid(false);
        table.setTableHeader(null);
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                final int row = table.rowAtPoint(e.getPoint());
                final File file = model.getValueAt(row);
                FilesList.this.changeState(file);
            }
        });
        this.addEditorRefreshFix(table, model);
        final TableColumn column = table.getColumnModel().getColumn(0);
        column.setCellRenderer(renderer);
        column.setCellEditor(editor);
        return table;
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
    
    private void addEditorRefreshFix(final JTable table, final TableModel model) {
        model.addTableModelListener(new TableModelListener() {
            public void tableChanged(final TableModelEvent e) {
                final int editingRow = table.getEditingRow();
                table.editingCanceled(new ChangeEvent(this));
                table.editCellAt(editingRow, 0);
            }
        });
    }
}
