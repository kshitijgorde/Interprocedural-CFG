// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.sharklet.ui;

import com.grooveshark.sharklet.Sharklet;
import javax.swing.SwingUtilities;
import com.grooveshark.net.uploader.UploadState;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableColumn;
import javax.swing.table.TableCellRenderer;
import com.grooveshark.ui.table.AbstractCell;
import com.grooveshark.ui.table.CellRenderer;
import com.grooveshark.ui.songspanel.renderer.UploadCell;
import java.awt.Color;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.Component;
import javax.swing.JScrollPane;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.HashSet;
import com.grooveshark.ui.table.SingleColumnTableModel;
import javax.swing.JTable;
import com.grooveshark.sharklet.Song;
import java.util.Set;
import javax.swing.event.TableModelListener;
import com.grooveshark.ui.wizard.WizardContentPanel;

public class UploadStepPanel extends WizardContentPanel implements TableModelListener
{
    private static final long serialVersionUID = 5036130104711165029L;
    private static final int SONG_COLUMN = 0;
    private static final String TITLE;
    private static final String DESCRIPTION;
    private static final int TABLE_ROW_HEIGHT = 75;
    private Set<Song> filesProcessed;
    private JTable table;
    private SingleColumnTableModel<Song> tableModel;
    
    public UploadStepPanel(final SingleColumnTableModel<Song> tableModel) {
        super(UploadStepPanel.TITLE, UploadStepPanel.DESCRIPTION);
        this.filesProcessed = new HashSet<Song>();
        (this.tableModel = tableModel).addTableModelListener(this);
        this.table = this.createTable(tableModel);
        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(this.table), "Center");
    }
    
    private JTable createTable(final SingleColumnTableModel<Song> model) {
        final JTable table = new JTable(model);
        table.setRowHeight(75);
        table.setTableHeader(null);
        table.setGridColor(Color.WHITE);
        table.setEnabled(false);
        final TableColumn songColumn = table.getColumnModel().getColumn(0);
        final UploadCell rendererCell = new UploadCell();
        final CellRenderer renderer = new CellRenderer(rendererCell);
        songColumn.setCellRenderer(renderer);
        return table;
    }
    
    public boolean isReady() {
        return this.filesProcessed.size() == this.tableModel.getRowCount();
    }
    
    public void tableChanged(final TableModelEvent e) {
        if (e.getType() == 0) {
            final int index = e.getFirstRow();
            final Song song = this.tableModel.getValueAt(index);
            final UploadState state = song.getState();
            if (state.equals(UploadState.COMPLETE) || state.equals(UploadState.FAILED)) {
                this.filesProcessed.add(song);
                this.displayRow(index + 1);
                this.updateReadyStatus();
            }
        }
    }
    
    private void displayRow(final int row) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UploadStepPanel.this.table.scrollRectToVisible(UploadStepPanel.this.table.getCellRect(row, UploadStepPanel.this.table.getColumnCount(), true));
            }
        });
    }
    
    static {
        TITLE = Sharklet.getText("UPLOAD_TITLE");
        DESCRIPTION = Sharklet.getText("UPLOAD_DESCRIPTION");
    }
}
