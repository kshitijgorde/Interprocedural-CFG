// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.ui.songspanel;

import com.grooveshark.sharklet.Song;
import javax.swing.table.TableModel;
import javax.swing.table.TableStringConverter;

public class SongConverter extends TableStringConverter
{
    public String toString(final TableModel model, final int row, final int column) {
        final Song song = (Song)model.getValueAt(row, column);
        final String songText = String.format("%s %s %s %s", song.getTitle(), song.getAlbum(), song.getArtist(), song.getFile().getName());
        return songText.toLowerCase();
    }
}
