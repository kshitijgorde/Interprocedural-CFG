// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.player;

import java.io.FileNotFoundException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.File;
import javazoom.jl.player.Player;

public class MusicPlayer
{
    private Player player;
    private File file;
    
    public void stop() {
        if (this.player == null) {
            return;
        }
        this.killPlayer();
    }
    
    private void killPlayer() {
        this.player.close();
        this.player = null;
    }
    
    public boolean isPlaying(final File file) {
        return this.isPlaying() && file.equals(this.file);
    }
    
    public boolean isPlaying() {
        return this.player != null;
    }
    
    public void play(final File file) {
        try {
            this.stop();
            this.file = file;
            this.startPlayer(this.player = this.createPlayer(file));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private Player createPlayer(final File file) throws Exception {
        final InputStream inputStream = this.createInputStream(file);
        final Player player = new Player(inputStream);
        return player;
    }
    
    private InputStream createInputStream(final File file) throws FileNotFoundException {
        final FileInputStream fileStream = new FileInputStream(file);
        final BufferedInputStream inputStream = new BufferedInputStream(fileStream);
        return inputStream;
    }
    
    private void startPlayer(final Player player) {
        final Thread playerThread = new Thread() {
            public void run() {
                try {
                    player.play();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        playerThread.start();
    }
}
