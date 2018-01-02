// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.ui.songspanel;

import com.grooveshark.sharklet.Song;
import java.util.Comparator;

public class SongComparator implements Comparator<Song>
{
    private int column;
    
    public void setSortBy(final int index) {
        this.column = index;
    }
    
    public int compare(final Song o1, final Song o2) {
        switch (this.column) {
            case 1: {
                return o1.getArtist().compareTo(o2.getArtist());
            }
            case 2: {
                return o1.getAlbum().compareTo(o2.getAlbum());
            }
            default: {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        }
    }
}
