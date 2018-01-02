// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.songscanner;

import java.io.File;
import com.grooveshark.sharklet.Song;

public interface SongScannerListener
{
    void newSong(final Song p0);
    
    void scanStarted();
    
    void foundFiles(final int p0);
    
    void scanFinished();
    
    void badFile(final File p0, final Exception p1);
}
