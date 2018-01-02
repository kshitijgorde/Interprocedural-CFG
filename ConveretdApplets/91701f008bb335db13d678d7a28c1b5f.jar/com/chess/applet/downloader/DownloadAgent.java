// 
// Decompiled by Procyon v0.5.30
// 

package com.chess.applet.downloader;

import java.util.List;
import com.chess.applet.ChessApplet;
import java.io.IOException;
import com.chess.util.DownloadProgressListener;

public interface DownloadAgent<T, V>
{
    T doInBackground(final DownloadProgressListener p0) throws IOException;
    
    void done(final ChessApplet.Worker<T> p0);
    
    void process(final List<V> p0);
}
