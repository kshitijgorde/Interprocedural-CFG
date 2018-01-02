// 
// Decompiled by Procyon v0.5.30
// 

package com.evological;

import java.awt.Component;
import javax.swing.JApplet;

public class evocam extends JApplet
{
    private VideoPanel videoPanel;
    private AudioPanel audioPanel;
    private boolean enableAudio;
    
    public evocam() {
        this.videoPanel = null;
        this.audioPanel = null;
        this.enableAudio = false;
    }
    
    public void init() {
        String video = this.getParameter("video");
        if (video == null) {
            video = "video.cgi";
        }
        (this.videoPanel = new VideoPanel(this.getCodeBase(), video)).setSize(this.getWidth(), this.getHeight());
        this.getContentPane().add(this.videoPanel);
        if (this.enableAudio) {
            final String audio = this.getParameter("audio");
            if (audio != null) {
                (this.audioPanel = new AudioPanel(this.getCodeBase(), audio)).setSize(this.getWidth(), 0);
                this.getContentPane().add(this.audioPanel);
            }
        }
    }
    
    public void start() {
        this.videoPanel.startStream();
        if (this.audioPanel != null) {
            this.audioPanel.startStream();
        }
    }
    
    public void stop() {
        this.videoPanel.stopStream();
        if (this.audioPanel != null) {
            this.audioPanel.stopStream();
        }
    }
}
