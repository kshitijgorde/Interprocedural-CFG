// 
// Decompiled by Procyon v0.5.30
// 

package com.next.gt;

import java.io.IOException;
import java.io.InputStream;
import sun.audio.AudioStream;
import java.io.FileInputStream;
import sun.audio.AudioPlayer;
import java.applet.Applet;

public class AudioManager extends Applet implements Runnable
{
    private Thread _kicker;
    private int _naptime;
    private boolean _keepRunning;
    private boolean playAudioFiles;
    
    public void run() {
        while (this._keepRunning) {
            try {
                Thread.sleep(this._naptime);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void start() {
        (this._kicker = new Thread(this)).start();
    }
    
    public void play(final String s) {
        if (this.playAudioFiles) {
            try {
                AudioPlayer.player.start(new AudioStream(new FileInputStream(s)));
            }
            catch (IOException ex) {
                System.err.println("Can't open audio stream, disabling audio..");
                this.playAudioFiles = false;
            }
        }
    }
    
    public void resumeplay(final String s) {
        AudioPlayer.player.resume();
    }
    
    public void stopplay() {
        AudioPlayer.player.suspend();
    }
    
    public AudioManager() {
        this._naptime = 5;
        this._keepRunning = true;
        this.playAudioFiles = true;
    }
}
