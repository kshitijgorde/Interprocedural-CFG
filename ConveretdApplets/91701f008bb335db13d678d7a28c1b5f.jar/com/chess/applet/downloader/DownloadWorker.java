// 
// Decompiled by Procyon v0.5.30
// 

package com.chess.applet.downloader;

import java.util.List;
import com.chess.applet.ChessApplet;
import com.chess.util.DownloadProgressListener;
import javax.swing.SwingWorker;

public class DownloadWorker extends SwingWorker<byte[], Object> implements DownloadProgressListener, ChessApplet.Worker<byte[]>
{
    private DownloadAgent<byte[], Object> agent;
    
    public DownloadWorker(final DownloadAgent<byte[], Object> agent) {
        this.agent = agent;
    }
    
    protected byte[] doInBackground() throws Exception {
        return this.agent.doInBackground((DownloadProgressListener)this);
    }
    
    protected void done() {
        this.agent.done(this);
    }
    
    public void sizeDetermined(final int size) {
        ((SwingWorker<T, Object>)this).publish("size", size);
    }
    
    public void progressMade(final int currentProgress) {
        ((SwingWorker<T, Object>)this).publish("progress", currentProgress);
    }
    
    protected void process(final List<Object> chunks) {
        this.agent.process(chunks);
    }
}
