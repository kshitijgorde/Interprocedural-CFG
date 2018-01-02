// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.player;

import com.fluendo.utils.Debug;
import java.awt.Component;
import java.io.InputStream;

public class Demuxer implements Runnable
{
    private DataConsumer audioConsumer;
    private DataConsumer videoConsumer;
    private InputStream inputStream;
    private Component component;
    private Plugin plugin;
    private boolean stopping;
    
    public Demuxer(final InputStream inputStream, final Plugin plugin, final Component component, final DataConsumer audioConsumer, final DataConsumer videoConsumer) {
        this.inputStream = inputStream;
        this.audioConsumer = audioConsumer;
        this.videoConsumer = videoConsumer;
        this.component = component;
        this.stopping = false;
        (this.plugin = plugin).initDemuxer(inputStream, component, audioConsumer, videoConsumer);
    }
    
    public void run() {
        try {
            this.realRun();
        }
        catch (Throwable t) {
            Cortado.shutdown(t);
        }
    }
    
    private void realRun() {
        Debug.log(3, "entering demuxer thread");
        try {
            while (!this.stopping) {
                this.stopping = !this.plugin.demux();
            }
        }
        catch (Exception ex) {
            if (!this.stopping) {
                ex.printStackTrace();
            }
            this.stopping = true;
        }
        Debug.log(3, "exit demuxer thread");
        if (this.audioConsumer != null) {
            this.audioConsumer.setEOS();
        }
        if (this.videoConsumer != null) {
            this.videoConsumer.setEOS();
        }
        Cortado.setEOS();
    }
    
    public void stop() {
        this.plugin.stop();
        this.stopping = true;
    }
}
