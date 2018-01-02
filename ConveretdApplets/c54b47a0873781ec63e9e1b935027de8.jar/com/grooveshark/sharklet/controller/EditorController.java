// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.sharklet.controller;

import com.grooveshark.sharklet.Song;
import javax.swing.table.TableModel;
import com.grooveshark.ui.songeditor.SongEditorPanel;
import com.grooveshark.ui.songeditor.SongEditorListener;
import com.grooveshark.ui.songspanel.SongSelectionListener;

public class EditorController implements SongSelectionListener, SongEditorListener
{
    private int[] index;
    private SongEditorPanel editor;
    private TableModel model;
    
    public EditorController(final SongEditorPanel editor, final TableModel model) {
        this.editor = editor;
        this.model = model;
        editor.setListener(this);
    }
    
    public void songsSelected(final int[] selectedRows) {
        final boolean noRowsSelected = selectedRows.length == 0;
        this.editor.lock(noRowsSelected);
        if (noRowsSelected) {
            return;
        }
        this.index = selectedRows;
        final boolean multipleSongsSelected = selectedRows.length > 1;
        this.editor.setMultipleEdit(multipleSongsSelected);
        if (!multipleSongsSelected) {
            final Song song = (Song)this.model.getValueAt(this.index[0], 0);
            this.editor.setSong(song);
        }
    }
    
    private void updateSongs(final SongVisitor visitor) {
        for (int i = 0; i < this.index.length; ++i) {
            final Song song = (Song)this.model.getValueAt(this.index[i], 0);
            visitor.changeSong(song);
            this.model.setValueAt(song, this.index[i], 0);
        }
    }
    
    public void albumChanged(final String album) {
        this.updateSongs(new SongVisitor() {
            public void changeSong(final Song song) {
                song.setAlbum(album);
            }
        });
    }
    
    public void artistChanged(final String artist) {
        this.updateSongs(new SongVisitor() {
            public void changeSong(final Song song) {
                song.setArtist(artist);
            }
        });
    }
    
    public void genreChanged(final String genre) {
        this.updateSongs(new SongVisitor() {
            public void changeSong(final Song song) {
                song.setGenre(genre);
            }
        });
    }
    
    public void titleChanged(final String title) {
        this.updateSongs(new SongVisitor() {
            public void changeSong(final Song song) {
                song.setTitle(title);
            }
        });
    }
    
    public void trackChanged(final String track) {
        this.updateSongs(new SongVisitor() {
            public void changeSong(final Song song) {
                int trackAsInt = 0;
                try {
                    trackAsInt = Integer.parseInt(track);
                }
                catch (Exception ex) {}
                song.setTrackNumber(trackAsInt);
            }
        });
    }
    
    public void yearChanged(final String year) {
        this.updateSongs(new SongVisitor() {
            public void changeSong(final Song song) {
                int yearAsInt = 1900;
                try {
                    yearAsInt = Integer.parseInt(year);
                }
                catch (Exception ex) {}
                song.setYear(yearAsInt);
            }
        });
    }
}
