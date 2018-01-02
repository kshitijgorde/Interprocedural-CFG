// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.ui.songspanel.controller;

import java.io.File;
import javax.swing.event.TableModelEvent;
import java.awt.event.ActionEvent;
import com.grooveshark.sharklet.Song;
import com.grooveshark.ui.table.SingleColumnTableModel;
import javax.swing.JTable;
import com.grooveshark.player.MusicPlayer;
import java.awt.event.ActionListener;

public class ListenController implements ActionListener
{
    private MusicPlayer player;
    private JTable table;
    private SingleColumnTableModel<Song> model;
    private int lastSelectedIndex;
    
    public ListenController(final MusicPlayer player, final JTable table, final SingleColumnTableModel<Song> model) {
        this.lastSelectedIndex = -1;
        this.player = player;
        this.table = table;
        this.model = model;
    }
    
    public void actionPerformed(final ActionEvent e) {
        int selectedRow = this.table.getSelectedRow();
        if (selectedRow == -1) {
            return;
        }
        selectedRow = this.table.convertRowIndexToModel(selectedRow);
        final Song song = this.model.getValueAt(selectedRow);
        final File file = song.getFile();
        if (this.player.isPlaying(file)) {
            this.player.stop();
        }
        else {
            selectedRow = this.table.convertRowIndexToModel(selectedRow);
            this.player.play(song.getFile());
        }
        this.table.editCellAt(selectedRow, 0);
        this.table.tableChanged(new TableModelEvent(this.table.getModel(), selectedRow));
        if (this.lastSelectedIndex != -1) {
            this.table.tableChanged(new TableModelEvent(this.table.getModel(), this.lastSelectedIndex));
        }
        this.lastSelectedIndex = selectedRow;
    }
}
