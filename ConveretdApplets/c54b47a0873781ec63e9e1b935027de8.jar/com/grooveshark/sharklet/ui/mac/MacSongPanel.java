// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.sharklet.ui.mac;

import com.grooveshark.sharklet.Sharklet;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableColumn;
import javax.swing.table.TableCellEditor;
import com.grooveshark.ui.table.CellEditor;
import com.grooveshark.ui.songspanel.controller.ListenController;
import javax.swing.table.TableCellRenderer;
import com.grooveshark.ui.table.AbstractCell;
import com.grooveshark.ui.table.CellRenderer;
import com.grooveshark.ui.songspanel.renderer.SongCell;
import com.grooveshark.player.MusicPlayer;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.table.JTableHeader;
import com.grooveshark.ui.songeditor.SongEditorListener;
import javax.swing.table.TableModel;
import com.grooveshark.sharklet.controller.EditorController;
import javax.swing.BorderFactory;
import com.grooveshark.ui.component.GradientPanel;
import com.grooveshark.ui.songeditor.SongEditorPanel;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.util.LinkedList;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import com.grooveshark.sharklet.Song;
import com.grooveshark.ui.table.SingleColumnTableModel;
import javax.swing.JTable;
import com.grooveshark.ui.songspanel.SongSelectionListener;
import java.util.List;
import javax.swing.border.Border;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.event.TableModelListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.JPanel;

public class MacSongPanel extends JPanel implements ListSelectionListener, TableModelListener, ActionListener
{
    private static final long serialVersionUID = 4827652378462605911L;
    private static final String RESULTS;
    private static final Color RESULT_FOREGROUND;
    private static final int TABLE_ROW_HEIGHT = 75;
    private static final int SONG_COLUMN = 0;
    private static final Dimension SIDEPANEL_SIZE;
    private static final int BORDER_SIZE = 10;
    private static final Border DEFAULT_BORDER;
    private List<SongSelectionListener> listeners;
    private JPanel searchPanel;
    private JTable table;
    private SingleColumnTableModel<Song> tableModel;
    private JLabel totalFiles;
    
    public MacSongPanel(final SingleColumnTableModel<Song> tableModel) {
        super(new BorderLayout(0, 0));
        this.listeners = new LinkedList<SongSelectionListener>();
        (this.tableModel = tableModel).addTableModelListener(this);
        this.table = this.createTable(tableModel);
        this.searchPanel = this.createSearchPanel();
        final SongEditorPanel editorPanel = this.createEditorPanel();
        this.add(this.searchPanel, "North");
        this.add(new JScrollPane(this.table), "Center");
        this.add(editorPanel, "East");
    }
    
    private JPanel createSearchPanel() {
        this.totalFiles = this.createResultsLabel();
        final GradientPanel searchPanel = new GradientPanel();
        searchPanel.setGradientColor(Color.WHITE, Color.decode("#dadada"), Color.decode("#a0a1a2"));
        searchPanel.setLayout(new BorderLayout(0, 0));
        searchPanel.add(this.totalFiles, "West");
        searchPanel.setVisible(false);
        searchPanel.setPreferredSize(new Dimension(700, 40));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 10));
        final MacResultsController results = new MacResultsController(this.table, this.totalFiles);
        this.tableModel.addTableModelListener(results);
        return searchPanel;
    }
    
    private SongEditorPanel createEditorPanel() {
        final SongEditorPanel editorPanel = new SongEditorPanel();
        final Border b = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.decode("#a0a1a2")), MacSongPanel.DEFAULT_BORDER);
        editorPanel.setBorder(b);
        editorPanel.setPreferredSize(MacSongPanel.SIDEPANEL_SIZE);
        editorPanel.lock(true);
        final EditorController editController = new EditorController(editorPanel, this.tableModel);
        editorPanel.setListener(editController);
        this.addSongSelectionListener(editController);
        return editorPanel;
    }
    
    private JTable createTable(final SingleColumnTableModel<Song> model) {
        final JTable table = new JTable(model);
        table.setRowHeight(75);
        table.setTableHeader(null);
        table.setGridColor(Color.WHITE);
        table.getSelectionModel().addListSelectionListener(this);
        table.addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent e) {
                if (e.getKeyCode() == 127) {
                    MacSongPanel.this.removeSelectedRow();
                }
            }
        });
        final TableColumn songColumn = table.getColumnModel().getColumn(0);
        final MusicPlayer player = new MusicPlayer();
        final SongCell rendererCell = new SongCell(player);
        final CellRenderer renderer = new CellRenderer(rendererCell);
        songColumn.setCellRenderer(renderer);
        final ListenController listenController = new ListenController(player, table, model);
        final SongCell editorCell = new SongCell(player);
        editorCell.addRemoveListener(this);
        editorCell.addListenListener(listenController);
        final CellEditor cellEditor = new CellEditor(editorCell);
        songColumn.setCellEditor(cellEditor);
        this.addEditorRefreshFix(table);
        return table;
    }
    
    private void addEditorRefreshFix(final JTable table) {
        table.getModel().addTableModelListener(new TableModelListener() {
            public void tableChanged(final TableModelEvent e) {
                final int editingRow = table.getEditingRow();
                if (e.getFirstRow() <= editingRow && editingRow <= e.getLastRow()) {
                    table.editingCanceled(new ChangeEvent(this));
                }
            }
        });
    }
    
    private JLabel createResultsLabel() {
        final JLabel totalFiles = new JLabel(String.format(MacSongPanel.RESULTS, "0"), 4);
        totalFiles.setFont(totalFiles.getFont().deriveFont(1).deriveFont(12.0f));
        totalFiles.setForeground(MacSongPanel.RESULT_FOREGROUND);
        return totalFiles;
    }
    
    public void addSongSelectionListener(final SongSelectionListener listener) {
        this.listeners.add(listener);
    }
    
    public void valueChanged(final ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }
        final int[] selectedRows = this.table.getSelectedRows();
        this.notifyListeners(selectedRows);
    }
    
    private void notifyListeners(final int[] selectedIndex) {
        for (final SongSelectionListener l : this.listeners) {
            l.songsSelected(selectedIndex);
        }
    }
    
    public void tableChanged(final TableModelEvent e) {
        this.searchPanel.setVisible(this.tableModel.getRowCount() > 0);
    }
    
    public void actionPerformed(final ActionEvent e) {
        this.removeSelectedRow();
    }
    
    private void removeSelectedRow() {
        final int[] selectedRows = this.table.getSelectedRows();
        if (selectedRows.length == 0) {
            return;
        }
        for (int i = selectedRows.length - 1; i >= 0; --i) {
            final int row = selectedRows[i];
            this.tableModel.remove(row);
        }
    }
    
    static {
        RESULTS = Sharklet.getText("RESULTS");
        RESULT_FOREGROUND = Color.decode("#1879D9");
        SIDEPANEL_SIZE = new Dimension(200, 500);
        DEFAULT_BORDER = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    }
}
