// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.sharklet.ui.mac;

import com.grooveshark.sharklet.Sharklet;
import java.io.File;
import javax.swing.event.TableModelEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.LinkedList;
import javax.swing.event.TableModelListener;
import java.util.List;
import com.grooveshark.sharklet.Song;
import com.grooveshark.ui.table.SingleColumnTableModel;
import com.grooveshark.sharklet.ui.EditorPanel;

public class MacEditorStepPanel extends EditorPanel
{
    private static final long serialVersionUID = -4581318913625826551L;
    private static final String PANEL_TITLE;
    private static final String PANEL_DESCRIPTION;
    private boolean scanFinished;
    private SingleColumnTableModel<Song> tableModel;
    private List<Integer> invalidFiles;
    
    public MacEditorStepPanel(final SingleColumnTableModel<Song> tableModel) {
        super(MacEditorStepPanel.PANEL_TITLE, MacEditorStepPanel.PANEL_DESCRIPTION);
        (this.tableModel = tableModel).addTableModelListener(this);
        this.invalidFiles = new LinkedList<Integer>();
        this.setupUI();
    }
    
    private void setupUI() {
        final MacSongPanel songsPanel = new MacSongPanel(this.tableModel);
        songsPanel.setOpaque(false);
        this.setOpaque(false);
        this.setLayout(new BorderLayout());
        this.add(songsPanel, "Center");
    }
    
    public void tableChanged(final TableModelEvent e) {
        final int type = e.getType();
        if (type == 1) {
            final int index = e.getFirstRow();
            final Song s = this.tableModel.getValueAt(index, 0);
            if (s.isInvalid()) {
                this.invalidFiles.add(index);
            }
        }
        else if (type == -1) {
            final int index = e.getFirstRow();
            this.invalidFiles.remove(new Integer(index));
            for (int i = 0; i < this.invalidFiles.size(); ++i) {
                final int invalidIndex = this.invalidFiles.get(i);
                if (invalidIndex > index) {
                    this.invalidFiles.set(i, invalidIndex - 1);
                }
            }
            if (this.tableModel.getRowCount() == 0) {
                this.invalidFiles.clear();
            }
        }
        else if (type == 0) {
            final int index = e.getFirstRow();
            final Song s = this.tableModel.getValueAt(index, 0);
            if (s.isInvalid()) {
                if (!this.invalidFiles.contains(index)) {
                    this.invalidFiles.add(index);
                }
            }
            else {
                this.invalidFiles.remove(new Integer(index));
            }
        }
        this.updateReadyStatus();
    }
    
    public boolean isReady() {
        final boolean hasValidFiles = this.invalidFiles.size() < this.tableModel.getRowCount();
        return hasValidFiles && this.scanFinished;
    }
    
    public void badFile(final File file, final Exception exception) {
    }
    
    public void foundFiles(final int totalFiles) {
    }
    
    public void newSong(final Song song) {
    }
    
    public void scanFinished() {
        this.scanFinished = true;
        this.updateReadyStatus();
    }
    
    public void scanStarted() {
        this.scanFinished = false;
        this.updateReadyStatus();
    }
    
    static {
        PANEL_TITLE = Sharklet.getText("EDITOR_TITLE");
        PANEL_DESCRIPTION = Sharklet.getText("EDITOR_DESCRIPTION");
    }
}
